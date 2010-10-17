package common.point;

import tuples.Pair;

public class PointG<T> extends Pair<T, T> implements APoint<T>
{
	public PointG(T x, T y)
	{
		super(x, y);
	}

	@Override
	public T x()
	{
		return p;
	}
	
	@Override
	public T y()
	{
		return q;
	}
}
