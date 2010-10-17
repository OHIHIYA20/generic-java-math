package arit;

import java.util.Comparator;

public interface SimpleArithmetics<T> extends Comparator<T>
{
	T add(T val1, T val2);
	T subtract(T val1, T val2);
	T multiply(T val1, T val2);
	T divide(T val1, T val2);
	
	boolean equals(T val1, T val2);
}
