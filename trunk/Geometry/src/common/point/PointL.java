package common.point;

import utils.Utils;
import fraction.Fraction;


public class PointL implements APoint<Long>, Comparable<PointL>
{
	public final long x;
	public final long y;

	public PointL(long x, long y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "("+x+","+y+")";
	}

	@Override
	public boolean equals(Object obj) {
		return compareTo((PointL)obj)==0;
	}
	
	@Override
	public int compareTo(PointL o) {
		if (x!=o.x) return x>o.x ? 1 : -1;
		return Utils.Common.compare(y, o.y);
	}
	
	@Override
	public int hashCode()
	{
		long xor = x ^ Long.reverse(y);
		return (int)(xor ^ (xor >>> 32));	
	}

	@Override
	public Long x()
	{
		return x;
	}

	@Override
	public Long y()
	{
		return y;
	}

	

	@Override
	public Long get()
	{
		return x;
	}
	
	@Override
	public Long first()
	{
		return x;
	}

	@Override
	public Long second()
	{
		return y;
	}

	public PointG<Fraction> toFraction()
	{
		return new PointG<Fraction>(new Fraction(x), new Fraction(y));
	}	
}
