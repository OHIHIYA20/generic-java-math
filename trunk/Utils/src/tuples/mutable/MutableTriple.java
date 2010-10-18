package tuples.mutable;

import tuples.Triple;
import tuples.interfaces.ITriple;
import utils.Utils;

public class MutableTriple<P, Q, R> extends MutablePair<P, Q> implements ITriple<P, Q, R>
{
	protected R r;

	public MutableTriple(P p, Q q, R r)
	{
		super(p,q);
		this.r = r;
	}

	public MutableTriple(ITriple<P, Q, R> triple) {
		super(triple);
		this.r = triple.third();
	}
	
	public R third()
	{
		return r;
	}

	public void setThird(R r)
	{
		this.r = r;
	}

	
	@Override
	public String toString()
	{
		return "[" + p +", "+ q +", "+ r +"]";
	}

	@Override
	public int hashCode()
	{		
		return Utils.Common.hashCode(p)^Utils.Common.hashCode(q)^Utils.Common.hashCode(r);
	}
	
	@Override
	public boolean equals(Object obj)
	{	
		if (obj==this) return true;
		if (obj==null) return false;
		if (obj.getClass().equals(this.getClass()))
		{
			MutableTriple<?, ?, ?> triple = (MutableTriple<?, ?, ?>) obj;
			return Utils.Common.equals(p,triple.p) && Utils.Common.equals(q, triple.q) && Utils.Common.equals(r, triple.r);
		}
		if (Triple.class.equals(this.getClass()))
		{
			Triple<?, ?, ?> triple = (Triple<?, ?, ?>) obj;
			return Utils.Common.equals(p,triple.first()) && Utils.Common.equals(q, triple.second()) && Utils.Common.equals(r, triple.third());
		}
		return false;
	}

	public static <P, Q, R> MutableTriple<P, Q, R> create(P p, Q q, R r) {
		return new MutableTriple<P, Q, R>(p,q,r);
	}
}
