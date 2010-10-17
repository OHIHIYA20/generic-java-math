package arit.impl;

import arit.IntegralArithmetics;

public class IntegerArithmetics implements IntegralArithmetics<Integer>
{
	public int compare(Integer value1, Integer value2)
	{
		int val1 = value1;
		int val2 = value2;
		return (val1 < val2 ? -1 : (val1 == val2 ? 0 : 1));
	}

	@Override
	public boolean equals(Integer val1, Integer val2)
	{
		return val1.equals(val2);
	}
	
	@Override
	public Integer add(Integer value1, Integer value2)
	{
		return Integer.valueOf(value1+value2);
	}

	@Override
	public Integer subtract(Integer value1, Integer value2)
	{
		return Integer.valueOf(value1-value2);
	}
	
	@Override
	public Integer multiply(Integer value1, Integer value2)
	{
		return Integer.valueOf(value1*value2);
	}
	
	@Override
	public Integer divide(Integer value1, Integer value2)
	{
		return Integer.valueOf(value1/value2);
	}

	
	@Override
	public Integer max(Integer value1, Integer value2)
	{
		return value1>=value2?value1:value2;
	}

	@Override
	public Integer min(Integer value1, Integer value2)
	{
		return value1<=value2?value1:value2;
	}

	
	@Override
	public Integer pow(Integer value, int exponent)
	{
		int result = 1;
		int val = value;
		while (exponent != 0) {
		    if ((exponent & 1)==1) {
		    	result *= val;
		    }
		    if ((exponent >>>= 1) != 0) {
		    	result *= result;
		    }
		}
		return Integer.valueOf(result);
	}
	
	@Override
	public Integer abs(Integer value)
	{
		return value>=0?value:-value;
	}

	@Override
	public Integer negate(Integer value)
	{
		return Integer.valueOf(-value);
	}

	
	private static final Integer ZERO = Integer.valueOf(0);
	private static final Integer ONE = Integer.valueOf(1);

	@Override
	public Integer zero()
	{
		return ZERO;
	}
	
	@Override
	public Integer one()
	{
		return ONE;
	}
	

	


	@Override
	public int signum(Integer value)
	{
		int val = value;
		return val>0?1:val<0?-1:0;
	}

	@Override
	public Integer and(Integer value1, Integer value2)
	{
		return Integer.valueOf(value1 & value2);
	}

	@Override
	public Integer andNot(Integer value1, Integer value2)
	{
		return Integer.valueOf(value1 & (~value2));
	}

	@Override
	public int bitCount(Integer value)
	{
		return Integer.bitCount(value);
	}

	@Override
	public int bitLength(Integer value)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer not(Integer value)
	{
		return Integer.valueOf(~value);
	}

	@Override
	public Integer or(Integer value1, Integer value2)
	{
		return Integer.valueOf(value1 | value2);
	}
	
	@Override
	public Integer xor(Integer value1, Integer value2)
	{
		return Integer.valueOf(value1 ^ value2);
	}
	
	@Override
	public Integer shiftLeft(Integer value, int n)
	{
		return Integer.valueOf(value<<n);
	}

	@Override
	public Integer shiftRight(Integer value, int n)
	{
		return Integer.valueOf(value>>n);
	}

	@Override
	public Integer[] divideAndRemainder(Integer value1, Integer value2)
	{
		return new Integer[]{divide(value1, value2), remainder(value1, value2)};
	}

	@Override
	public Integer remainder(Integer value1, Integer value2)
	{
		return Integer.valueOf(value1 % value2);
	}

	@Override
	public Integer mod(Integer value, Integer mod)
	{
		int remainder = value % mod;
		return Integer.valueOf(remainder >= 0 ? remainder : (remainder + mod));
	}
	
	@Override
	public String toStringWithRadix(Integer value, int radix)
	{
		return Integer.toString(value, radix);
	}

	
	



	@Override
	public Integer gcd(Integer value1, Integer value2)
	{
		int a = value1;
		int b = value2;
		while(b!=0){
			int r = a %b;
			a = b;
			b = r;
		}		
		return a;	
	}
	
	@Override
	public Integer modInverse(Integer value, Integer m)
	{
		int a = value;
		int b = m;
		int naua = 1, naub = 0;
		
		while(b!=0){
			int r = a %b;
			int naur = naua-a/b*naub;
			a = b;
			naua = naub;
			b = r;
			naub = naur;
		}					
		b = m;
		naua = naua % b;
		return naua>=0?naua:((naua+b)%b);
	}

	@Override
	public Integer modPow(Integer value, Integer exponent, Integer m)
	{
		int x = value;
		int pow = exponent;
		int mod = m;
		if (pow == 1) return x;
		int rez = modPow(x, pow>>1, mod);
		return ((pow&1)==0)?(rez*rez)%mod:(rez*rez*x)%mod;
	}

	
	
	@Override
	public int getLowestSetBit(Integer value)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer clearBit(Integer value, int n)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Integer flipBit(Integer value, int n)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer setBit(Integer value, int n)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean testBit(Integer value, int n)
	{		
		throw new UnsupportedOperationException();

	}


	
	private static final IntegerArithmetics arit = new IntegerArithmetics();
	
	private IntegerArithmetics() {}
	
	public static IntegerArithmetics getInstance() {
		return arit;
	}
	

}
