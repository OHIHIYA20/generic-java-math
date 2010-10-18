package complex;

import arit.Arithmetics;
import arit.numbers.ArithmeticNumber;

public class GenericComplex<T extends Number> extends ArithmeticNumber<GenericComplex<T>>
{
	private final T real;
	private final T imag;
	
	public final Arithmetics<T> arit;

	
	
	public GenericComplex(T real, T imag, Arithmetics<T> arit)
	{
		this.arit = arit;
		this.real = real;
		this.imag = imag;
	}
	
	
	
	@Override
	public String toString()
	{	
		return real + "+" + imag + "i";
	}


	public GenericComplex<T> conjugation() {
		return new GenericComplex<T>(real, arit.negate(imag), arit);
	}

	
	public T getReal()
	{
		return real;
	}

	public T getImag()
	{
		return imag;
	}

	public T absValue()
	{
		return arit.add(arit.multiply(real, real), arit.multiply(imag, imag));
	}
	
	@Override
	public GenericComplex<T> abs()
	{
		return new GenericComplex<T>(arit.add(arit.multiply(real, real), arit.multiply(imag, imag)), arit.zero(), arit);
	}

	@Override
	public GenericComplex<T> add(GenericComplex<T> val)
	{
		return new GenericComplex<T>(arit.add(real, val.real), arit.add(imag, val.imag), arit);
	}

	@Override
	public GenericComplex<T> subtract(GenericComplex<T> val)
	{
		return new GenericComplex<T>(arit.subtract(real, val.real), arit.subtract(imag, val.imag), arit);
	}

	@Override
	public GenericComplex<T> multiply(GenericComplex<T> val)
	{
		return new GenericComplex<T>(arit.subtract(arit.multiply(real, val.real), arit.multiply(imag, val.imag)),
				arit.add(arit.multiply(real, val.imag), arit.multiply(imag, val.real)), arit);
	}

	@Override
	public GenericComplex<T> divide(GenericComplex<T> val)
	{		
		GenericComplex<T> above = this.multiply(val.conjugation());
		T below = val.absValue();
		return new GenericComplex<T>(arit.divide(above.real, below), arit.divide(above.imag, below), arit);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof GenericComplex<?>) {
			GenericComplex<T> val = (GenericComplex<T>)obj;
			return arit == val.arit && arit.equals(real, val.real) && arit.equals(imag, val.imag);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return real.hashCode() ^ imag.hashCode();
	}
	
	@Override
	public GenericComplex<T> max(GenericComplex<T> val)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public GenericComplex<T> min(GenericComplex<T> val)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public GenericComplex<T> negate()
	{
		return new GenericComplex<T>(arit.negate(real), arit.negate(imag), arit);
	}

	@Override
	public GenericComplex<T> pow(int n)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int signum()
	{
		throw new UnsupportedOperationException();
	}


	@Override
	public double doubleValue()
	{
		return real.doubleValue();
	}

	@Override
	public float floatValue()
	{
		return real.floatValue();
	}

	@Override
	public int intValue()
	{
		return real.intValue();
	}

	@Override
	public long longValue()
	{
		return real.longValue();
	}

	@Override
	public int compareTo(GenericComplex<T> arg0)
	{
		throw new UnsupportedOperationException();
	}
	

}
