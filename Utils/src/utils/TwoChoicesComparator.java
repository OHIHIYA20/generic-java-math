package utils;

import java.util.Comparator;

public class TwoChoicesComparator<T> implements Comparator<T>
{
	private final Comparator<T> firstChoice;
	private final Comparator<T> secondChoice;

	public TwoChoicesComparator(Comparator<T> firstChoice, Comparator<T> secondChoice)
	{
		this.firstChoice = firstChoice;
		this.secondChoice = secondChoice;
	}

	@Override
	public int compare(T o1, T o2)
	{
		int cmp = firstChoice.compare(o1, o2);
		if (cmp != 0) return cmp;
		return secondChoice.compare(o1, o2);
	}
}
