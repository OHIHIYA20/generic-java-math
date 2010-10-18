package tuples;

import java.util.Comparator;

import tuples.interfaces.IHolder;
import tuples.mutable.MutableHolder;
import utils.Utils;

public class Holder<P> implements IHolder<P>
{
	protected final P p;
	
	public Holder() {
		p = null;
	}
	
	public Holder(P p) {
		this.p = p;
	}	
	
	public Holder(IHolder<P> holder) {
		p = holder.first();
	}
	
	public P get() {
		return p;
	}
	
	public P first() {
		return p;
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
		if (obj==this) return true;
		if (obj==null) return false;
		if (this.getClass().equals(obj.getClass()))
		{
			Holder<?> v = (Holder<?>) obj;
			return Utils.Common.equals(p, v.p);
		}
		if (MutableHolder.class.equals(obj.getClass()))
		{
			MutableHolder<?> v = (MutableHolder<?>) obj;
			return Utils.Common.equals(p, v.first());
		}
		return false;
	}

	
	public static class FirstComparator<P> implements Comparator<IHolder<P>> {

		private final Comparator<P> cmp;
		
		public FirstComparator(Comparator<P> cmp)
		{
			this.cmp = cmp;
		}
		
		public int compare(IHolder<P> o1, IHolder<P> o2)
		{
			return cmp.compare(o1.first(),o2.first());
		}
	}
	
	public static class FirstAscendingComparator<P extends Comparable<P>> implements Comparator<IHolder<P>> {

		public int compare(IHolder<P> o1, IHolder<P> o2)
		{
			return o1.first().compareTo(o2.first());
		}
	}

	public static class FirstDescendingComparator<P extends Comparable<P>> implements Comparator<IHolder<P>> {

		public int compare(IHolder<P> o1, IHolder<P> o2)
		{
			return -o1.first().compareTo(o2.first());
		}
	}

	
	public static <P> Comparator<IHolder<P>> firstComparator(Comparator<P> cmp) {
		return new FirstComparator<P>(cmp);
	}
	
	public static <P extends Comparable<P>> Comparator<IHolder<P>> firstAscendingComparator() {
		return new FirstAscendingComparator<P>();
	}
	
	public static <P extends Comparable<P>> Comparator<IHolder<P>> firstDescendingComparator() {
		return new FirstDescendingComparator<P>();
	}
	

}
