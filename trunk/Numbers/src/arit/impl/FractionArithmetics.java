package arit.impl;

import arit.DecimalArithmetics;
import fraction.Fraction;

public class FractionArithmetics implements DecimalArithmetics<Fraction>
{
	@Override
	public int compare(Fraction o1, Fraction o2)
	{
		return o1.compareTo(o2);
	}

	@Override
	public boolean equals(Fraction val1, Fraction val2)
	{
		return val1.equals(val2);
	}

	@Override
	public Fraction add(Fraction val1, Fraction val2)
	{
		return val1.add(val2);
	}

	@Override
	public Fraction subtract(Fraction val1, Fraction val2)
	{
		return val1.subtract(val2);
	}
	
	@Override
	public Fraction multiply(Fraction val1, Fraction val2)
	{
		return val1.multiply(val2);
	}

	@Override
	public Fraction divide(Fraction val1, Fraction val2)
	{
		return val1.divide(val2);
	}

	
	@Override
	public Fraction max(Fraction val1, Fraction val2)
	{
		return val1.max(val2);
	}

	@Override
	public Fraction min(Fraction val1, Fraction val2)
	{
		return val1.min(val2);
	}


	@Override
	public Fraction pow(Fraction val, int n)
	{
		return val.pow(n);
	}
	
	@Override
	public Fraction abs(Fraction val)
	{
		return val.abs();
	}

	@Override
	public Fraction negate(Fraction val)
	{
		return val.negate();
	}

	@Override
	public Fraction one()
	{
		return Fraction.ONE;
	}


	@Override
	public Fraction zero()
	{
		return Fraction.ZERO;
	}

	private static final FractionArithmetics arit = new FractionArithmetics();
	
	private FractionArithmetics() {}
	
	public static FractionArithmetics getInstance() {
		return arit;
	}
}
