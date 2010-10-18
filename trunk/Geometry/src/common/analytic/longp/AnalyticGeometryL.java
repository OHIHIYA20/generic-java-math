package common.analytic.longp;

import java.util.Comparator;

import utils.Utils;
import arit.Arithmetics;
import arit.impl.LongArithmetics;

import common.analytic.IntegralAnalyticGeometry;
import common.analytic.generic.DecimalAnalyticGeometryG;
import common.point.PointG;
import common.point.PointL;
import common.segment.SegmentG;
import common.segment.SegmentL;

import fraction.Fraction;

public class AnalyticGeometryL implements IntegralAnalyticGeometry<Long, PointL, SegmentL, Fraction, PointG<Fraction>, SegmentG<Fraction>>
{
	@Override
	public Arithmetics<Long> arithmetics()
	{
		return LongArithmetics.getInstance();
	}
	
	public Long two() {
		return 2L;
	}

	public Long square(Long t) {
		return t*t;
	}
	
	protected long squarePrimitive(long l) {
		return l*l;
	}

	@Override
	public int comparePointsXY(PointL a, PointL b)
	{
		if (a.x!=b.x) return a.x>b.x ? 1 : -1;
		return Utils.Common.compare(a.y, b.y);
	}
	
	@Override
	public int comparePointsYX(PointL a, PointL b)
	{
		if (a.y!=b.y) return a.y>b.y ? 1 : -1;
		return Utils.Common.compare(a.x, b.x);
	}
	
	@Override
	public int compareSegments(SegmentL a, SegmentL b)
	{
		int cmp = comparePointsXY(a.p1(), b.p1());
		if (cmp!=0)
			return cmp;
		return comparePointsXY(a.p2(), b.p2());
	}
	
	@Override
	public Comparator<PointL> pointsComparatorXY()
	{
		return new Comparator<PointL>() {

			@Override
			public int compare(PointL o1, PointL o2)
			{
				return comparePointsXY(o1, o2);
			}};
	}
	
	@Override
	public Comparator<PointL> pointsComparatorYX()
	{
		return new Comparator<PointL>() {

			@Override
			public int compare(PointL o1, PointL o2)
			{
				return comparePointsYX(o1, o2);
			}};
	}
	
	@Override
	public Comparator<SegmentL> segmentsComparator()
	{
		return new Comparator<SegmentL>() {

			@Override
			public int compare(SegmentL o1, SegmentL o2)
			{
				return compareSegments(o1, o2);
			}};
	}
	
	
	@Override
	public PointL createPoint(Long x, Long y)
	{
		return new PointL(x, y);
	}
	
	@Override
	public SegmentL createSegment(PointL p1, PointL p2)
	{
		return new SegmentL(p1, p2);
	}
	
	protected Long distanceSquaredPrimitive(PointL a, PointL b) {
		return squarePrimitive(a.x-b.x)+squarePrimitive(a.y-b.y);
	}
	
	public Long distanceSquared(PointL a, PointL b) {
		return distanceSquaredPrimitive(a,b);
	}
	
	
	public Long dx(SegmentL duz) {
		return duz.dx();
	}
	
	public Long dy(SegmentL duz) {
		return duz.dy();
	}
	
	
	public Long VP(PointL a, PointL b, PointL c)
	{
		return (b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x);
	}
	
	public Long VP(SegmentL a,SegmentL b)
	{
		return a.dx()*b.dy()-a.dy()*b.dx();
	}
	
	public Long SP(PointL a,PointL b, PointL c){
		return (b.x-a.x)*(c.x-a.x)+(b.y-a.y)*(c.y-a.y);
	}
	
	public Long SP(SegmentL a, SegmentL b){
		return a.dx()*b.dx()+a.dy()*b.dy();
	}

	public boolean parallel(SegmentL a, SegmentL b) {
		return VP(a,b)==0;
	}
	
	public boolean orthogonal(SegmentL a, SegmentL b) {
		return SP(a,b)==0;
	}
	
	public Side onWhichSide(SegmentL d, PointL a) {
		long cmp = VP(d.p1(), d.p2(), a);
		return Side.getSide(cmp);
	}
	
	@Override
	public Side onWhichSide(PointL a, PointL b, PointL c)
	{
		long cmp = VP(a, b, c);
		return Side.getSide(cmp);
	}
	
	public boolean onTheSameSide(SegmentL d, PointL a, PointL b) {
		return onWhichSide(d, a)==onWhichSide(d, b);
	}
	
	
	@Override
	public PointG<Fraction> toFraction(PointL p)
	{
		return p.toFraction();
	}
	
	@Override
	public SegmentG<Fraction> toFraction(SegmentL s)
	{
		return s.toFraction();
	}
	
	@Override
	public DecimalAnalyticGeometryG<Fraction> toDecimalGeometry()
	{
		return new DecimalAnalyticGeometryG<Fraction>(Fraction.createArithmetics());
	}
	
	
	@Override
	public PointG<Fraction> middleOfSegment(SegmentL duz)
	{
		return duz.middleOfSegment();
	}
	
	
	@Override
	public SegmentG<Fraction> symetral(SegmentL a)
	{
		PointG<Fraction> mid = a.middleOfSegment();
		return new SegmentG<Fraction>(mid, new PointG<Fraction>(mid.x().subtract(new Fraction(a.dy())),mid.y().add(new Fraction(a.dx()))));
	}	
	
	protected Fraction intersectionFractionOrNull(SegmentL a, SegmentL b)
	{
		long adx = a.dx();
		long ady = a.dy();
		
		long lower = ady * b.dx()- adx * b.dy();
		if (lower == 0)
			return null;
		return new Fraction((a.p1().x - b.p1().x) * ady - (a.p1().y - b.p1().y) * adx, lower);
	}
		
	private PointG<Fraction> intersectionPoint(SegmentL a, SegmentL b, Fraction s) {
		return new PointG<Fraction>(new Fraction(b.dx() * s.br + b.p1().x * s.im,
				s.im), new Fraction(b.dy() * s.br + b.p1().y * s.im, s.im));
	}
	
	public  PointG<Fraction> intersection(SegmentL a, SegmentL b) {
		Fraction s = intersectionFractionOrNull(a, b);
		if (s == null)
			return null;
		if ((s.compareTo(Fraction.ZERO) >= 0)
				&& (s.compareTo(Fraction.ONE) <= 0))
			return intersectionPoint(a, b, s);
		else
			return null;
	}

	
	@Override
	public PointG<Fraction> intersectionOfLines(SegmentL a, SegmentL b)
	{
		Fraction s = intersectionFractionOrNull(a, b);
		if (s == null)
			return null;
		return intersectionPoint(a, b, s);
	}
	
	private static final AnalyticGeometryL geom = new AnalyticGeometryL();
	
	private AnalyticGeometryL() {}
	
	public static AnalyticGeometryL getInstance() {
		return geom;
	}
}
