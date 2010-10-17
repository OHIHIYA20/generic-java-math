package common.point;

import tuples.interfaces.IPair;

public interface APoint<T> extends IPair<T, T>
{
	T x();
	T y();
}
