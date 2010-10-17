package arit.impl;

import arit.DecimalArithmetics;

public class DoubleExactArithmetics implements DecimalArithmetics<Double>
{
	@Override
	public int compare(Double o1, Double o2)
	{
		return o1.compareTo(o2);
	}

	@Override
	public boolean equals(Double val1, Double val2)
	{
		return val1.equals(val2);
	}
	
	@Override
	public Double add(Double val1, Double val2)
	{
		return val1 + val2;
	}

	@Override
	public Double subtract(Double val1, Double val2)
	{
		return val1 - val2;
	}

	@Override
	public Double multiply(Double val1, Double val2)
	{
		return val1 * val2;
	}

	@Override
	public Double divide(Double val1, Double val2)
	{
		return val1 / val2;
	}


	@Override
	public Double max(Double value1, Double value2)
	{
		if (Double.isNaN(value1) || Double.isNaN(value2))
			return Double.NaN;
		return value1 >= value2 ? value1 : value2;
	}

	@Override
	public Double min(Double value1, Double value2)
	{
		if (Double.isNaN(value1) || Double.isNaN(value2))
			return Double.NaN;
		return value1 <= value2 ? value1 : value2;
	}

	@Override
	public Double pow(Double value, int exponent)
	{
		double result = 1;
		double val = value;
		while (exponent != 0)
		{
			if ((exponent & 1) == 1)
			{
				result *= val;
			}
			if ((exponent >>>= 1) != 0)
			{
				result *= result;
			}
		}
		return result;
	}

	@Override
	public Double abs(Double val)
	{
		if (Double.isNaN(val))
			return val;
		return val >= 0 ? val : -val;
	}

	@Override
	public Double negate(Double val)
	{
		return -val;
	}


	private static final Double ZERO = Double.valueOf(0.0);
	private static final Double ONE = Double.valueOf(1.0);

	@Override
	public Double zero()
	{
		return ZERO;
	}

	@Override
	public Double one()
	{
		return ONE;
	}

	
	
	private static final DoubleExactArithmetics arit = new DoubleExactArithmetics();
	
	DoubleExactArithmetics() {}
	
	public static DoubleExactArithmetics getInstance() {
		return arit;
	}
}
