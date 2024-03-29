package common.analytic;

import common.point.APoint;
import common.segment.ASegment;

public interface AnalyticIntersectionGeometry<T, P extends APoint<T>, S extends ASegment<T, P>, TF, PF extends APoint<TF>, SF extends ASegment<TF, PF>> extends AnalyticGeometry<T, P, S>
{
	TF toFraction(T t);
	PF toFraction(P p);
	SF toFraction(S s);
	
	DecimalAnalyticGeometry<TF, PF, SF> toDecimalGeometry();
	
	PF middleOfSegment(S duz);	
	SF symetral(S a);
	
	PF intersection(S a, S b);
	PF intersectionOfLines(S a, S b);
}
