package fraction;

import java.math.BigDecimal;
import java.math.BigInteger;

import arit.DecimalArithmetics;
import arit.impl.BigIntegerArithmetics;
import arit.impl.LongArithmetics;
import arit.numbers.ArithmeticNumber;
import arit.wrappers.DecimalArithmeticsImpl;



public class Fraction extends ArithmeticNumber<Fraction>
{

	private static final long serialVersionUID = 8073627015094850161L;
	
	
	public static long gcd(long a, long b)
	{
		while(b!=0){
			long r = a %b;
			a = b;
			b = r;
		}		
		return a;
	}
	
	private static int compareLong(long a, long b) {
		return (a<b ? -1 : (a==b ? 0 : 1));
	}

	
	
	
	public static Fraction ZERO = new Fraction(0,1);
	public static Fraction ONE = new Fraction(1,1);
	public static Fraction TWO = new Fraction(2,1);
	
	public final long br,im;

	public Fraction(long n) {
		br = n;
		im = 1;
	}
	
	public Fraction(long br, long im)
	{
		if (im < 0)
		{
			im = -im;
			br = -br;
		}
		else if (im == 0) 
			throw new IllegalArgumentException(br + "/" + im);
		long d = gcd(br,im);
		this.br = br/d;
		this.im = im/d;
	}
	
	private Fraction(long br, long im, boolean safe) {
		this.br = br;
		this.im = im;
	}
	
	@Override
	public int compareTo(Fraction o) {
		return compareLong(br*o.im, o.br*im);
	}
	
	@Override
	public boolean equals(Object obj) {
		return compareTo((Fraction)obj)==0;
	}
	
	@Override
	public String toString() {
		if (im == 1) return ""+br;
		return br+"/"+im;
	}

	@Override
	public double doubleValue() {
		return 1.0*br/im;
	}

	@Override
	public float floatValue()
	{
		return 1.0F*br/im;
	}

	@Override
	public int intValue()
	{
		return (int)(br/im);
	}

	@Override
	public long longValue()
	{
		return br/im;
	}
	
	@Override
	public Fraction add(Fraction r) {
		return new Fraction(br*r.im+im*r.br,im*r.im);
	}
	
	@Override
	public Fraction subtract(Fraction r) {
		return new Fraction(br*r.im-im*r.br,im*r.im);
	}
	
	@Override
	public Fraction multiply(Fraction r) {
		return new Fraction(br*r.br,im*r.im);
	}

	@Override
	public Fraction divide(Fraction r) {
		return new Fraction(br*r.im,im*r.br);
	}
	
	@Override
	public int hashCode()
	{
		long xor = br ^ Long.reverse(im);
		return (int)(xor ^ (xor >>> 32));		
	}

	@Override
	public Fraction abs()
	{
		return br>=0 ? this : negate();
	}

	@Override
	public Fraction max(Fraction val)
	{
		return compareTo(val)>=0?this:val;
	}

	@Override
	public Fraction min(Fraction val)
	{
		return compareTo(val)<=0?this:val;
	}

	@Override
	public Fraction negate()
	{
		return new Fraction(-br, im, true);
	}

	@Override
	public Fraction pow(int exponent)
	{
		long valbr = br;
		long valim = im;
		long resbr = 1;
		long resim = 0;
		while (exponent != 0)
		{
			if ((exponent & 1) == 1)
			{
				resbr *= valbr;
				resim *= valim;
			}
			if ((exponent >>>= 1) != 0)
			{
				valbr *= valbr;
				valim *= valim;
			}
		}
		return new Fraction(resbr, resim);
	}

	@Override
	public int signum()
	{
		return br>0?1:br==0?0:-1;
	}

	public GenericFraction<Long> toGenericFraction() {
		return new GenericFraction<Long>(br, im, LongArithmetics.getInstance());
	}
	
	public GenericFraction<BigInteger> toGenericBigIntegerFraction() {
		return new GenericFraction<BigInteger>(BigInteger.valueOf(br), BigInteger.valueOf(im), BigIntegerArithmetics.getInstance());
	}
	
	public BigDecimal toBigDecimal() { 
		return BigDecimal.valueOf(br).divide(BigDecimal.valueOf(im));
	}
	
	public static final DecimalArithmetics<Fraction> instance = new DecimalArithmeticsImpl<Fraction>(ZERO, ONE);
	
	public static DecimalArithmetics<Fraction> createArithmetics() {
		return instance;
	}
}
