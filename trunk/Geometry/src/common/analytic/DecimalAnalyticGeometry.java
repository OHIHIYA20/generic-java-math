package common.analytic;

import common.point.APoint;
import common.segment.ASegment;

public interface DecimalAnalyticGeometry<T, P extends APoint<T>, S extends ASegment<T, P>> extends AnalyticGeometry<T, P, S>
{
	P middleOfSegment(S duz);
	
	S symetral(S a);
	

	P intersection(S a, S b);

	P intersetcionOfLines(S a, S b);
}
