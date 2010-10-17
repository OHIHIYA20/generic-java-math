package common.segment;

import arit.Arithmetics;

import common.point.PointG;

public class SegmentG<T> extends ASegment<T, PointG<T>>
{
	public SegmentG(PointG<T> p, PointG<T> q)
	{
		super(p, q);
	}	
	
	public T dx(Arithmetics<T> arit) {
		return arit.subtract(p2().x(), p1().x());
	}
	
	public T dy(Arithmetics<T> arit) {
		return arit.subtract(p2().y(), p1().y());
	}

}
