package common.analytic.generic;

import arit.DecimalArithmetics;
import arit.IntegralArithmetics;

import common.analytic.DecimalAnalyticGeometry;
import common.analytic.IntegralAnalyticGeometry;
import common.point.PointG;
import common.segment.SegmentG;

import fraction.GenericFraction;

public class IntegralAnalyticGeometryG<T extends Number> extends AnalyticGeometryG<T> implements IntegralAnalyticGeometry<T, PointG<T>, SegmentG<T>, GenericFraction<T>, PointG<GenericFraction<T>>, SegmentG<GenericFraction<T>>>
{
	private final IntegralArithmetics<T> arit;
	private final DecimalArithmetics<GenericFraction<T>> fracArit;
	
	public IntegralAnalyticGeometryG(IntegralArithmetics<T> arit)
	{
		super(arit);
		this.arit = arit;
		this.fracArit = GenericFraction.createArithmetics(arit);
	}

	@Override
	public IntegralArithmetics<T> arithmetics()
	{	
		return arit;
	}
	
	@Override
	public GenericFraction<T> toFraction(T t) {
		return new GenericFraction<T>(t, arit);
	}
	
	@Override
	public PointG<GenericFraction<T>> toFraction(PointG<T> p)
	{
		return new PointG<GenericFraction<T>>(toFraction(p.x()), toFraction(p.y()));
	}
	
	@Override
	public SegmentG<GenericFraction<T>> toFraction(SegmentG<T> s)
	{
		return new SegmentG<GenericFraction<T>>(toFraction(s.p1()), toFraction(s.p2()));
	}
	
	@Override
	public DecimalAnalyticGeometry<GenericFraction<T>, PointG<GenericFraction<T>>, SegmentG<GenericFraction<T>>> toDecimalGeometry()
	{
		return new DecimalAnalyticGeometryG<GenericFraction<T>>(fracArit);
	}
	
	
	
	public PointG<GenericFraction<T>> middleOfSegment(SegmentG<T> duz) {
		return new PointG<GenericFraction<T>>(
				new GenericFraction<T>(arit.add(duz.p1().x(), duz.p2().x()), two(), arit),
				new GenericFraction<T>(arit.add(duz.p1().y(), duz.p2().y()), two(), arit));
	}
		
	public SegmentG<GenericFraction<T>> symetral(SegmentG<T> a) {
		PointG<GenericFraction<T>> mid = middleOfSegment(a);
		return new SegmentG<GenericFraction<T>>(mid, 
				new PointG<GenericFraction<T>>(
						fracArit.subtract(mid.x(), new GenericFraction<T>(a.dy(arit), arit)),
						fracArit.add(mid.y(), new GenericFraction<T>(a.dx(arit), arit))));
	}
	
	

	
	
	private GenericFraction<T> intersectionFractionOrNull(SegmentG<T> a, SegmentG<T> b) {
		T adx = dx(a);
		T ady = dy(a);
		
		T lower = intersectionLowerPartOfFraction(adx, ady, b);
		if (arit.equals(lower, arit.zero()))
			return null;
		return new GenericFraction<T>(intersectionUpperPartOfFraction(a, b, adx, ady), lower, arit);
	}
	
	private PointG<GenericFraction<T>> intersectionPoint(SegmentG<T> a, SegmentG<T> b, GenericFraction<T> s) {
		return new PointG<GenericFraction<T>>(
				new GenericFraction<T>(
						arit.add(
								arit.multiply(b.dx(arit), s.br),
								arit.multiply(b.p1().x(), s.im))
						, s.im, arit), 
				new GenericFraction<T>(
						arit.add(
								arit.multiply(b.dy(arit), s.br),
								arit.multiply(b.p1().y(), s.im))
						, s.im, arit));
	}
	
	public PointG<GenericFraction<T>> intersection(SegmentG<T> a, SegmentG<T> b) {
		GenericFraction<T> s = intersectionFractionOrNull(a, b); 
		if (s == null)
			return null;
		if ((fracArit.compare(s, fracArit.zero()) >= 0)
				&& (fracArit.compare(s, fracArit.one()) <= 0))
			return intersectionPoint(a, b, s);
		else
			return null;
	}

	public PointG<GenericFraction<T>> intersectionOfLines(SegmentG<T> a, SegmentG<T> b) {
		GenericFraction<T> s = intersectionFractionOrNull(a, b); 
		if (s == null)
			return null;
		return intersectionPoint(a, b, s);
	}



	public static <T extends Number> IntegralAnalyticGeometryG<T> create(IntegralArithmetics<T> arit) {
		return new IntegralAnalyticGeometryG<T>(arit);
	}

}
