package common.analytic;

import arit.IntegralArithmetics;

import common.point.APoint;
import common.segment.ASegment;

public interface IntegralAnalyticGeometry<T, P extends APoint<T>, S extends ASegment<T, P>, TF, PF extends APoint<TF>, SF extends ASegment<TF, PF>> extends AnalyticIntersectionGeometry<T, P, S, TF, PF, SF>
{
	IntegralArithmetics<T> arithmetics();
}
