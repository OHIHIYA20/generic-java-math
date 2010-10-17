package arit.impl;

import java.math.BigInteger;

import arit.IntegralArithmetics;

public class BigIntegerArithmetics implements IntegralArithmetics<BigInteger>
{

	@Override
	public BigInteger and(BigInteger val1, BigInteger val2)
	{
		return val1.and(val2);
	}

	@Override
	public BigInteger andNot(BigInteger val1, BigInteger val2)
	{
		return val1.andNot(val2);
	}

	@Override
	public int bitCount(BigInteger val)
	{
		return val.bitCount();
	}

	@Override
	public int bitLength(BigInteger val)
	{
		return val.bitLength();
	}

	@Override
	public BigInteger clearBit(BigInteger val, int n)
	{
		return val.clearBit(n);
	}

	@Override
	public BigInteger[] divideAndRemainder(BigInteger val1, BigInteger val2)
	{
		return val1.divideAndRemainder(val2);
	}

	@Override
	public BigInteger flipBit(BigInteger val, int n)
	{
		return val.flipBit(n);
	}

	@Override
	public BigInteger gcd(BigInteger val1, BigInteger val2)
	{
		return val1.gcd(val2);
	}

	@Override
	public int getLowestSetBit(BigInteger val)
	{
		return val.getLowestSetBit();
	}

	@Override
	public BigInteger mod(BigInteger val, BigInteger m)
	{
		return val.mod(m);
	}

	@Override
	public BigInteger modInverse(BigInteger val, BigInteger m)
	{
		return val.modInverse(m);
	}

	@Override
	public BigInteger modPow(BigInteger val, BigInteger exponent, BigInteger m)
	{
		return val.modPow(exponent, m);
	}

	@Override
	public BigInteger not(BigInteger val)
	{
		return val.not();
	}

	@Override
	public BigInteger or(BigInteger val1, BigInteger val2)
	{
		return val1.or(val2);
	}

	@Override
	public BigInteger remainder(BigInteger val1, BigInteger val2)
	{
		return val1.remainder(val2);
	}

	@Override
	public BigInteger setBit(BigInteger val, int n)
	{
		return val.setBit(n);
	}

	@Override
	public BigInteger shiftLeft(BigInteger val, int n)
	{
		return val.shiftLeft(n);
	}

	@Override
	public BigInteger shiftRight(BigInteger val, int n)
	{
		return val.shiftRight(n);
	}

	@Override
	public boolean testBit(BigInteger val, int n)
	{
		return val.testBit(n);
	}

	@Override
	public String toStringWithRadix(BigInteger val, int radix)
	{
		return val.toString(radix);
	}

	@Override
	public BigInteger xor(BigInteger val1, BigInteger val2)
	{
		return val1.xor(val2);
	}

	@Override
	public BigInteger abs(BigInteger val)
	{
		return val.abs();
	}

	@Override
	public BigInteger add(BigInteger val1, BigInteger val2)
	{
		return val1.add(val2);
	}

	@Override
	public BigInteger divide(BigInteger val1, BigInteger val2)
	{
		return val1.divide(val2);
	}

	@Override
	public BigInteger max(BigInteger val1, BigInteger val2)
	{
		return val1.max(val2);
	}

	@Override
	public BigInteger min(BigInteger val1, BigInteger val2)
	{
		return val1.min(val2);
	}

	@Override
	public BigInteger multiply(BigInteger val1, BigInteger val2)
	{
		return val1.multiply(val2);
	}

	@Override
	public BigInteger negate(BigInteger val)
	{
		return val.negate();
	}

	@Override
	public BigInteger one()
	{
		return BigInteger.ONE;
	}

	@Override
	public BigInteger pow(BigInteger val, int n)
	{
		return val.pow(n);
	}

	@Override
	public int signum(BigInteger val)
	{
		return val.signum();
	}

	@Override
	public BigInteger subtract(BigInteger val1, BigInteger val2)
	{
		return val1.subtract(val2);
	}

	@Override
	public BigInteger zero()
	{
		return BigInteger.ZERO;
	}

	@Override
	public int compare(BigInteger o1, BigInteger o2)
	{
		return o1.compareTo(o2);
	}

	@Override
	public boolean equals(BigInteger val1, BigInteger val2)
	{
		return val1.equals(val2);
	}
	
	
	private static final BigIntegerArithmetics arit = new BigIntegerArithmetics();
	
	private BigIntegerArithmetics() {}
	
	public static BigIntegerArithmetics getInstance() {
		return arit;
	}
}
