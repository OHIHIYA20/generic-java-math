package arit.wrappers;

import arit.Arithmetics;
import arit.numbers.ArithmeticNumber;

public class ArithmeticsImpl<T extends ArithmeticNumber<T>> implements Arithmetics<T>
{
	public ArithmeticsImpl(T zero, T one)
	{
		ZERO = zero;
		ONE = one;
	}

	@Override
	public T abs(T val)
	{
		return val.abs();
	}

	@Override
	public T add(T val1, T val2)
	{
		return val1.add(val2);
	}

	@Override
	public T divide(T val1, T val2)
	{
		return val1.divide(val2);
	}

	@Override
	public T max(T val1, T val2)
	{
		return val1.max(val2);
	}

	@Override
	public T min(T val1, T val2)
	{
		return val1.min(val2);
	}

	@Override
	public T multiply(T val1, T val2)
	{
		return val1.multiply(val2);
	}

	@Override
	public T negate(T val)
	{
		return val.negate();
	}

	@Override
	public T pow(T val, int n)
	{
		return val.pow(n);
	}

	@Override
	public T subtract(T val1, T val2)
	{
		return val1.subtract(val1);
	}

	@Override
	public int compare(T val1, T val2)
	{
		return val1.compareTo(val2);
	}
	
	public boolean equals(T val1, T val2) {
		return val1.equals(val2);
	}
	
	private final T ZERO;
	private final T ONE;
	
	@Override
	public T zero()
	{
		return ZERO;
	}
	
	@Override
	public T one()
	{
		return ONE;
	}

}
