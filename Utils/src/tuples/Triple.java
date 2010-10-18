package tuples;

import java.util.Comparator;

import tuples.interfaces.ITriple;
import tuples.mutable.MutableTriple;
import utils.Utils;

public class Triple<P, Q, R> extends Pair<P, Q> implements ITriple<P, Q, R>
{
	protected final R r;

	public Triple(P p, Q q, R r)
	{
		super(p,q);
		this.r = r;
	}

	public Triple(ITriple<P, Q, R> triple) {
		super(triple);
		this.r = triple.third();
	}
	
	public R third()
	{
		return r;
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
			Triple<?, ?, ?> triple = (Triple<?, ?, ?>) obj;
			return Utils.Common.equals(p,triple.p) && Utils.Common.equals(q, triple.q) && Utils.Common.equals(r, triple.r);
		}
		if (MutableTriple.class.equals(this.getClass()))
		{
			MutableTriple<?, ?, ?> triple = (MutableTriple<?, ?, ?>) obj;
			return Utils.Common.equals(p,triple.first()) && Utils.Common.equals(q, triple.second()) && Utils.Common.equals(r, triple.third());
		}
		return false;
	}


	public static class ThirdComparator<R> implements Comparator<ITriple<?, ?, R>> {

		private final Comparator<R> cmp;
		
		public ThirdComparator(Comparator<R> cmp)
		{
			this.cmp = cmp;
		}
		
		public int compare(ITriple<?, ?, R> o1, ITriple<?, ?, R> o2)
		{
			return cmp.compare(o1.third(),o2.third());
		}
	}
	
	public static class ThirdAscendingComparator<R extends Comparable<R>> implements Comparator<ITriple<?, ?, R>> {

		public int compare(ITriple<?, ?, R> o1, ITriple<?, ?, R> o2)
		{
			return o1.third().compareTo(o2.third());
		}
	}

	public static class ThirdDescendingComparator<R extends Comparable<R>> implements Comparator<ITriple<?, ?, R>> {

		public int compare(ITriple<?, ?, R> o1, ITriple<?, ?, R> o2)
		{
			return -o1.third().compareTo(o2.third());
		}
	}
	

	public static <R> Comparator<ITriple<?, ?, R>> thirdComparator(Comparator<R> cmp) {
		return new ThirdComparator<R>(cmp);
	}
	
	public static <R extends Comparable<R>> Comparator<ITriple<?, ?, R>> thirdAscendingComparator() {
		return new ThirdAscendingComparator<R>();
	}
	
	public static <R extends Comparable<R>> Comparator<ITriple<?, ?, R>> thirdDescendingComparator() {
		return new ThirdDescendingComparator<R>();
	}
	

}
