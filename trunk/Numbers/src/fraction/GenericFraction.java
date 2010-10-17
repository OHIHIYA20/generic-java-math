package fraction;

import arit.DecimalArithmetics;
import arit.IntegralArithmetics;
import arit.numbers.ArithmeticNumber;
import arit.wrappers.DecimalArithmeticsImpl;

public class GenericFraction<T extends Number> extends ArithmeticNumber<GenericFraction<T>>
{
	private static final long serialVersionUID = -1898509855841674637L;

	
	public final T br;
	public final T im;
	public final IntegralArithmetics<T> arit;

	public GenericFraction(T n, IntegralArithmetics<T> arithmetics) {
		br = n;
		im = arithmetics.one();
		this.arit = arithmetics;
	}
	
	public GenericFraction(T br, T im, IntegralArithmetics<T> arithmetics)
	{
		int signum = arithmetics.signum(im);
		if (signum < 0)
		{
			im = arithmetics.negate(im);
			br = arithmetics.negate(br);
		}
		else if (signum == 0) 
			throw new IllegalArgumentException(br + "/" + im);
		T d = arithmetics.gcd(br,im);
		this.br = arithmetics.divide(br, d);
		this.im = arithmetics.divide(im, d);
		this.arit = arithmetics;
	}
	
	private GenericFraction(T br, T im, IntegralArithmetics<T> arithmetics, boolean safe) {
		this.br = br;
		this.im = im;
		this.arit = arithmetics;
	}
	
	@Override
	public int compareTo(GenericFraction<T> o) {
		return arit.compare(arit.multiply(br, o.im), arit.multiply(o.br, im)); 
	}
	
	@Override
	public boolean equals(Object obj) {
		return compareTo((GenericFraction<T>)obj)==0;
	}
	
	@Override
	public String toString() {
//		if (im == 1) return ""+br;
		return br+"/"+im;
	}

	@Override
	public double doubleValue() {
		return 1.0*br.doubleValue()/im.doubleValue();
	}

	@Override
	public float floatValue()
	{
		return 1.0F*br.floatValue()/im.floatValue();
	}

	@Override
	public int intValue()
	{
		return (br.intValue()/im.intValue());
	}

	@Override
	public long longValue()
	{
		return br.longValue()/im.longValue();
	}
	
	@Override
	public GenericFraction<T> add(GenericFraction<T> r) {
		return new GenericFraction<T>(arit.add(arit.multiply(br, r.im), arit.multiply(im, r.br)), arit.multiply(im, r.im), arit);
	}
	
	@Override
	public GenericFraction<T> subtract(GenericFraction<T> r) {
		return new GenericFraction<T>(arit.subtract(arit.multiply(br, r.im), arit.multiply(im, r.br)), arit.multiply(im, r.im), arit);
	}
	
	@Override
	public GenericFraction<T> multiply(GenericFraction<T> r) {
		return new GenericFraction<T>(arit.multiply(br, r.br), arit.multiply(im, r.im), arit);
	}

	@Override
	public GenericFraction<T> divide(GenericFraction<T> r) {
		return new GenericFraction<T>(arit.multiply(br, r.im), arit.multiply(im, r.br), arit);
	}
	
	@Override
	public int hashCode()
	{
		int xor = br.hashCode() ^ Integer.reverse(im.hashCode());
		return xor;		
	}

	@Override
	public GenericFraction<T> abs()
	{
		return arit.signum(br)>=0 ? this : negate();
	}

	@Override
	public GenericFraction<T> max(GenericFraction<T> val)
	{
		return compareTo(val)>=0?this:val;
	}

	@Override
	public GenericFraction<T> min(GenericFraction<T> val)
	{
		return compareTo(val)<=0?this:val;
	}

	@Override
	public GenericFraction<T> negate()
	{
		return new GenericFraction<T>(arit.negate(br), im, arit, true);
	}

	@Override
	public GenericFraction<T> pow(int exponent)
	{
		T resbr = arit.pow(br, exponent);
		T resim = arit.pow(im, exponent);
		return new GenericFraction<T>(resbr, resim, arit);
	}

	@Override
	public int signum()
	{
		return arit.signum(br);
	}

	public static <T extends Number> GenericFraction<T> zero(IntegralArithmetics<T> arithmetics) { 
		return new GenericFraction<T>(arithmetics.zero(), arithmetics.one(), arithmetics);
	}
	
	public static <T extends Number> GenericFraction<T> one(IntegralArithmetics<T> arithmetics) { 
		return new GenericFraction<T>(arithmetics.one(), arithmetics.one(), arithmetics);
	}
	
	public static <T extends Number> DecimalArithmetics<GenericFraction<T>> createArithmetics(IntegralArithmetics<T> arithmetics) {
		return new DecimalArithmeticsImpl<GenericFraction<T>>(zero(arithmetics), one(arithmetics));
	}
}
