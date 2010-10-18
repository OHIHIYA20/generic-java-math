package tuples.mutable;

import tuples.Holder;
import tuples.interfaces.IHolder;
import utils.Utils;

public class MutableHolder<P> implements IHolder<P>
{
	protected P p;
	
	public MutableHolder() {
		p = null;
	}
	
	public MutableHolder(P p) {
		this.p = p;
	}	
	
	public MutableHolder(IHolder<P> holder) {
		p = holder.first();
	}
	
	public P get() {
		return p;
	}
	
	public void set(P p) {
		this.p = p;
	}

	public P first() {
		return p;
	}

	public void setFirst(P p)
	{
		this.p = p;
	}

	@Override
	public String toString()
	{
		return "[" + p + "]";
	}
	
	@Override
	public int hashCode()
	{		
		return Utils.Common.hashCode(p);
	}
	
	@Override
	public boolean equals(Object obj)
	{		
		if (obj==null) 
			return false;
		if (this.getClass().equals(obj.getClass()))
		{
			MutableHolder<?> v = (MutableHolder<?>) obj;
			return Utils.Common.equals(p, v.p);
		}
		if (Holder.class.equals(obj.getClass()))
		{
			Holder<?> v = (Holder<?>) obj;
			return Utils.Common.equals(p, v.first());
		}
		return false;
	}

	

}
