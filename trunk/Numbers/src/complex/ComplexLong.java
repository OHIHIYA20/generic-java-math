package complex;

import arit.numbers.ArithmeticNumber;

public class ComplexLong extends ArithmeticNumber<ComplexLong>
{
	private final long real;
	private final long imag;
	
	
	public ComplexLong(long real, long imag)
	{
		this.real = real;
		this.imag = imag;
	}
	
	@Override
	public String toString()
	{	
		return real + "+" + imag + "i";
	}
	

	@Override
	public ComplexLong abs()
	{
		return new ComplexLong(real*real + imag*imag, 0);
	}

	public long absValue()
	{
		return (real * real) + (imag * imag);
	}
	

	public ComplexLong conjugation() {
		return new ComplexLong(real, -imag);
	}

	
	@Override
	public ComplexLong add(ComplexLong val)
	{
		return new ComplexLong(real + val.real, imag + val.imag);
	}

	@Override
	public ComplexLong multiply(ComplexLong val)
	{
		return new ComplexLong((real * val.real) - (imag * val.imag),
				(real * val.imag) + (imag * val.real));
	}

	@Override
	public ComplexLong divide(ComplexLong val)
	{		
		ComplexLong above = multiply(val.conjugation());
		long below = val.absValue();
		return new ComplexLong((above.real / below), (above.imag / below));
	}
	

	@Override
	public boolean equals(Object obj)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ComplexLong max(ComplexLong val)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexLong min(ComplexLong val)
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ComplexLong negate()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplexLong pow(int n)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int signum()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ComplexLong subtract(ComplexLong val)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double doubleValue()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float floatValue()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int intValue()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long longValue()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(ComplexLong o)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
