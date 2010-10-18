package tuples.mutable;

import tuples.Quadruple;
import tuples.interfaces.IQuadruple;
import utils.Utils;

public class MutableQuadruple<P, Q, R, S> extends MutableTriple<P, Q, R> implements IQuadruple<P, Q, R, S>
{
	protected S s;

	public MutableQuadruple(P p, Q q, R r, S s)
	{
		super(p,q, r);
		this.s = s;
	}

	public MutableQuadruple(IQuadruple<P, Q, R, S> quadruple) {
		super(quadruple);
		this.s = quadruple.forth();
	}
	
	public S forth()
	{
		return s;
	}

	
	public void setForth(S s)
	{
		this.s = s;
	}
	
	@Override
	public String toString()
	{
		return "[" + p +", "+ q +", "+ r +", "+ s +"]";
	}

	@Override
	public int hashCode()
	{		
		return Utils.Common.hashCode(p)^Utils.Common.hashCode(q)^Utils.Common.hashCode(r)^Utils.Common.hashCode(s);
	}
	
	@Override
	public boolean equals(Object obj)
	{	
		if (obj==this) return true;
		if (obj==null) return false;
		if (obj.getClass().equals(this.getClass()))
		{
			MutableQuadruple<?, ?, ?, ?> quad = (MutableQuadruple<?, ?, ?, ?>) obj;
			return Utils.Common.equals(p,quad.p) && Utils.Common.equals(q, quad.q) && Utils.Common.equals(r, quad.r)&& Utils.Common.equals(s, quad.s);
		}
		if (Quadruple.class.equals(this.getClass()))
		{
			Quadruple<?, ?, ?, ?> quad = (Quadruple<?, ?, ?, ?>) obj;
			return Utils.Common.equals(p,quad.first()) && Utils.Common.equals(q, quad.second()) && Utils.Common.equals(r, quad.third())&& Utils.Common.equals(s, quad.forth());
		}
		return false;
	}

}
