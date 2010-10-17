package common.analytic.generic;

import arit.DecimalArithmetics;

import common.analytic.DecimalAnalyticGeometry;
import common.point.PointG;
import common.segment.SegmentG;

public class DecimalAnalyticGeometryG<T> extends AnalyticGeometryG<T> implements DecimalAnalyticGeometry<T, PointG<T>, SegmentG<T>>
{
	protected final DecimalArithmetics<T> arit;
	
	public DecimalAnalyticGeometryG(DecimalArithmetics<T> arit)
	{
		super(arit);
		this.arit = arit;
	}
	
	
	public PointG<T> middleOfSegment(SegmentG<T> duz) {
		return new PointG<T>(
				arit.divide(arit.add(duz.p1().x(), duz.p2().x()), two()),
				arit.divide(arit.add(duz.p1().y(), duz.p2().y()), two()));
	}
	
	public SegmentG<T> symetral(SegmentG<T> a) {
		PointG<T> mid = middleOfSegment(a);
		return new SegmentG<T>(mid, 
				new PointG<T>(
						arit.subtract(mid.x(), a.dy(arit)),
						arit.add(mid.y(), a.dx(arit))));
	}
	
	
	
	private T intersectionFractionOrNull(SegmentG<T> a, SegmentG<T> b) {
		T adx = a.dx(arit);
		T ady = a.dy(arit);
		
		T upperPlus = intersectionUpperPlusPartOfFraction(a, b, ady);
		T upperMinus = intersectionUpperMinusPartOfFraction(a, b, adx);
		if (arit.compare(upperPlus, upperMinus)==0)
			return null;
		
		return arit.divide(arit.subtract(upperPlus, upperMinus), 
				intersectionLowerPartOfFraction(adx, ady, b));
	}
	
	
	private PointG<T> intersectionPoint(SegmentG<T> a, SegmentG<T> b, T s) {
		return new PointG<T>(
				arit.add(arit.multiply(b.dx(arit), s), b.p1().x()), 
				arit.add(arit.multiply(b.dy(arit), s), b.p1().y()));
	}
	
	public PointG<T> intersection(SegmentG<T> a, SegmentG<T> b) {
		T s = intersectionFractionOrNull(a, b); 
		if (s == null)
			return null;
		if ((arit.compare(s, arit.zero()) >= 0)
				&& (arit.compare(s, arit.one()) <= 0))
			return intersectionPoint(a, b, s);
		else
			return null;
	}

	public PointG<T> intersetcionOfLines(SegmentG<T> a, SegmentG<T> b) {
		T s = intersectionFractionOrNull(a, b); 
		if (s == null)
			return null;
		return intersectionPoint(a, b, s);
	}


	public static <T> DecimalAnalyticGeometryG<T> create(DecimalArithmetics<T> arit) {
		return new DecimalAnalyticGeometryG<T>(arit);
	}
}
