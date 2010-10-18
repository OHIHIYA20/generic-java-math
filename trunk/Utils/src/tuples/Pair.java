package tuples;

import java.util.Comparator;
import java.util.Map.Entry;

import tuples.interfaces.IPair;
import tuples.mutable.MutablePair;
import utils.Utils;

public class Pair<P,Q> extends Holder<P> implements IPair<P, Q> {

	protected final Q q;

	public Pair(Entry<P, Q> entry) {
		super(entry.getKey());
		this.q = entry.getValue();
	}
	
	public Pair(P p, Q q)
	{
		super(p);
		this.q = q;
	}

	public Pair(IPair<P, Q> pair) {
		super(pair);
		this.q = pair.second();
	}
	
	public Q second() {
		return q;
	}



	@Override
	public String toString()
	{
		return "[" + p +", "+ q +"]";
	}

	private int hash;
	
	@Override
	public int hashCode()
	{		
		int h = hash;
		if (h == 0) {
			h = Utils.Common.hashCode(p)^Utils.Common.hashCode(q);
			hash = h;
	    }
	    return h;
	    
//		return Utils.Common.hashCode(p)^Utils.Common.hashCode(q);
	}
	
	@Override
	public boolean equals(Object obj)
	{	
		if (obj==this) return true;
		if (obj==null) return false;
		if (obj.getClass().equals(this.getClass()))
		{
			Pair<?, ?> pair = (Pair<?, ?>) obj;
			return Utils.Common.equals(p,pair.p) && Utils.Common.equals(q, pair.q);
		}
		if (MutablePair.class.equals(this.getClass()))
		{
			MutablePair<?, ?> pair = (MutablePair<?, ?>) obj;
			return Utils.Common.equals(p,pair.first()) && Utils.Common.equals(q, pair.second());
		}
		return false;
	}

	public static <P, Q> Pair<P, Q> create(P p, Q q) {
		return new Pair<P, Q>(p,q);
	}

	
	public static class SecondComparator<Q> implements Comparator<IPair<?, Q>> {

		private final Comparator<Q> cmp;
		
		public SecondComparator(Comparator<Q> cmp)
		{
			this.cmp = cmp;
		}
		
		public int compare(IPair<?, Q> o1, IPair<?, Q> o2)
		{
			return cmp.compare(o1.second(),o2.second());
		}
	}
	
	public static class SecondAscendingComparator<Q extends Comparable<Q>> implements Comparator<IPair<?, Q>> {

		public int compare(IPair<?, Q> o1, IPair<?, Q> o2)
		{
			return o1.second().compareTo(o2.second());
		}
	}

	public static class SecondDescendingComparator<Q extends Comparable<Q>> implements Comparator<IPair<?, Q>> {

		public int compare(IPair<?, Q> o1, IPair<?, Q> o2)
		{
			return -o1.second().compareTo(o2.second());
		}
	}
	

	public static <Q> Comparator<IPair<?, Q>> secondComparator(Comparator<Q> cmp) {
		return new SecondComparator<Q>(cmp);
	}
	
	public static <Q extends Comparable<Q>> Comparator<IPair<?, Q>> secondAscendingComparator() {
		return new SecondAscendingComparator<Q>();
	}
	
	public static <Q extends Comparable<Q>> Comparator<IPair<?, Q>> secondDescendingComparator() {
		return new SecondDescendingComparator<Q>();
	}
}
