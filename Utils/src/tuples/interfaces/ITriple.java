package tuples.interfaces;

public interface ITriple<P, Q, R> extends IPair<P, Q>
{
	public R third();
}
