package common.segment;

import tuples.Pair;

import common.point.APoint;

public abstract class ASegment<T, P extends APoint<T>> extends Pair<P, P> 
{
	public ASegment(P p, P q)
	{
		super(p, q);
	}

	public P p1() {
		return p;
	}
	
	public P p2() {
		return q;
	}
	
}
