package common.analytic.generic;

import java.util.Comparator;

import arit.Arithmetics;

import common.analytic.AnalyticGeometry;
import common.point.PointG;
import common.segment.SegmentG;

public class AnalyticGeometryG<T> implements AnalyticGeometry<T, PointG<T>, SegmentG<T>>
{
	protected final Arithmetics<T> arit;
	protected final T two;
	
	public AnalyticGeometryG(Arithmetics<T> arit)
	{
		this.arit = arit;
		this.two = arit.add(arit.one(), arit.one());
	}
	
	@Override
	public Arithmetics<T> arithmetics()
	{
		return arit;
	}

	public T two() {
		return two;
	}

	public T square(T t) {
		return arit.multiply(t, t);
	}
	
	@Override
	public int comparePointsXY(PointG<T> a, PointG<T> b)
	{
		int cmp = arit.compare(a.x(), b.x());
		if (cmp!=0) return cmp;
		return arit.compare(a.y(), b.y());
	}
	
	@Override
	public int comparePointsYX(PointG<T> a, PointG<T> b)
	{
		int cmp = arit.compare(a.y(), b.y());
		if (cmp!=0) return cmp;
		return arit.compare(a.x(), b.x());
	}
	
	@Override
	public int compareSegments(SegmentG<T> a, SegmentG<T> b)
	{
		int cmp = comparePointsXY(a.p1(), b.p1());
		if (cmp!=0)
			return cmp;
		return comparePointsXY(a.p2(), b.p2());
	}
	
	@Override
	public Comparator<PointG<T>> pointsComparatorXY()
	{
		return new Comparator<PointG<T>>() {

			@Override
			public int compare(PointG<T> o1, PointG<T> o2)
			{
				return comparePointsXY(o1, o2);
			}};
	}
	
	@Override
	public Comparator<PointG<T>> pointsComparatorYX()
	{
		return new Comparator<PointG<T>>() {

			@Override
			public int compare(PointG<T> o1, PointG<T> o2)
			{
				return comparePointsYX(o1, o2);
			}};
	}
	
	@Override
	public Comparator<SegmentG<T>> segmentsComparator()
	{
		return new Comparator<SegmentG<T>>() {

			@Override
			public int compare(SegmentG<T> o1, SegmentG<T> o2)
			{
				return compareSegments(o1, o2);
			}};
	}
	
	public PointG<T> createPoint(T x, T y) {
		return new PointG<T>(x, y);
	}
	
	@Override
	public SegmentG<T> createSegment(PointG<T> p1, PointG<T> p2)
	{
		return new SegmentG<T>(p1, p2);
	}
	
	public T distanceSquared(PointG<T> a, PointG<T> b) {
		return arit.add(square(arit.subtract(a.x(), b.x())), square(arit.subtract(a.y(), b.y())));
	}
	
	public T dx(SegmentG<T> duz) {
		return arit.subtract(duz.p2().x(), duz.p1().x());
	}
	
	public T dy(SegmentG<T> duz)
	{
		return arit.subtract(duz.p2().y(), duz.p1().y());
	}
	
	public T VP(PointG<T> a, PointG<T> b, PointG<T> c)
	{
		return arit.subtract(
				arit.multiply(arit.subtract(b.x(), a.x()), arit.subtract(c.y(), a.y())), 
				arit.multiply(arit.subtract(b.y(), a.y()), arit.subtract(c.x(), a.x())));
	}
	
	public int signVP(PointG<T> a, PointG<T> b, PointG<T> c)
	{
		return arit.compare(
				arit.multiply(arit.subtract(b.x(), a.x()), arit.subtract(c.y(), a.y())), 
				arit.multiply(arit.subtract(b.y(), a.y()), arit.subtract(c.x(), a.x())));
	}
	
	public T VP(SegmentG<T> a,SegmentG<T> b)
	{
		return arit.subtract(
				arit.multiply(a.dx(arit), b.dy(arit)), 
				arit.multiply(a.dy(arit), b.dx(arit)));
	}
	
	public T SP(PointG<T> a, PointG<T> b, PointG<T> c)
	{
		return arit.add(
				arit.multiply(arit.subtract(b.x(), a.x()), arit.subtract(c.x(), a.x())), 
				arit.multiply(arit.subtract(b.y(), a.y()), arit.subtract(c.y(), a.y())));
	}
	
	public T SP(SegmentG<T> a,SegmentG<T> b)
	{
		return arit.add(
				arit.multiply(a.dx(arit), b.dx(arit)), 
				arit.multiply(a.dy(arit), b.dy(arit)));
	}
	

	public boolean parallel(SegmentG<T> a, SegmentG<T> b) {
		return VP(a,b).equals(arit.zero());
	}
	
	public boolean orthogonal(SegmentG<T> a, SegmentG<T> b) {
		return SP(a,b).equals(arit.zero());
	}
	
	public Side onWhichSide(SegmentG<T> d, PointG<T> a) {
		int cmp = signVP(d.p1(), d.p2(), a);
		return Side.getSide(cmp);
	}
	
	@Override
	public Side onWhichSide(PointG<T> a, PointG<T> b, PointG<T> c)
	{
		int cmp = signVP(a, b, c);
		return Side.getSide(cmp);
	}
	
	public boolean onTheSameSide(SegmentG<T> d, PointG<T> a, PointG<T> b) {
		return onWhichSide(d, a)==onWhichSide(d, b);
	}

	
	
	protected T intersectionUpperPlusPartOfFraction(SegmentG<T> a, SegmentG<T> b, T ady) {
		return arit.multiply(arit.subtract(a.p1().x(), b.p1().x()), ady);
	}
	
	protected T intersectionUpperMinusPartOfFraction(SegmentG<T> a, SegmentG<T> b, T adx) {
		return arit.multiply(arit.subtract(a.p1().y(), b.p1().y()), adx);
	}
	
	protected T intersectionUpperPartOfFraction(SegmentG<T> a, SegmentG<T> b, T adx, T ady) {
		return arit.subtract(
				intersectionUpperPlusPartOfFraction(a, b, ady),
				intersectionUpperMinusPartOfFraction(a, b, adx))
				;
	}
	
	protected T intersectionLowerPlusPartOfFraction(T adx, T ady, SegmentG<T> b) {
		return arit.multiply(ady, b.dx(arit));
	}
	protected T intersectionLowerMinusPartOfFraction(T adx, T ady, SegmentG<T> b) {
		return arit.multiply(adx, b.dy(arit));
	}
	
	
	protected T intersectionLowerPartOfFraction(T adx, T ady, SegmentG<T> b) {
		return arit.subtract(
				intersectionLowerPlusPartOfFraction(adx, ady, b),
				intersectionLowerMinusPartOfFraction(adx, ady, b));
	}

}
