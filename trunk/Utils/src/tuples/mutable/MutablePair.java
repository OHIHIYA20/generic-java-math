package tuples.mutable;

import java.util.Map.Entry;

import tuples.Pair;
import tuples.interfaces.IPair;
import utils.Utils;

public class MutablePair<P, Q> extends MutableHolder<P> implements IPair<P, Q> {

	protected Q q;

	public MutablePair(Entry<P, Q> entry) {
		this.p = entry.getKey();
		this.q = entry.getValue();
	}
	
	public MutablePair(P p, Q q)
	{
		super(p);
		this.q = q;
	}

	public MutablePair(IPair<P, Q> pair) {
		super(pair);
		this.q = pair.second();
	}
	
	public Q second() {
		return q;
	}

	public void setSecond(Q q)
	{
		this.q = q;
	}


	@Override
	public String toString()
	{
		return "[" + p +", "+ q +"]";
	}

	@Override
	public int hashCode()
	{		
		return Utils.Common.hashCode(p)^Utils.Common.hashCode(q);
	}
	
	@Override
	public boolean equals(Object obj)
	{	
		if (obj==this) return true;
		if (obj==null) return false;
		if (obj.getClass().equals(this.getClass()))
		{
			MutablePair<?, ?> pair = (MutablePair<?, ?>) obj;
			return Utils.Common.equals(p,pair.p) && Utils.Common.equals(q, pair.q);
		}
		if (Pair.class.equals(this.getClass()))
		{
			Pair<?, ?> pair = (Pair<?, ?>) obj;
			return Utils.Common.equals(p,pair.first()) && Utils.Common.equals(q, pair.second());
		}
		return false;
	}
}
