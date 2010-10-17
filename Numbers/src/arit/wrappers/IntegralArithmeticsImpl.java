package arit.wrappers;

import arit.IntegralArithmetics;
import arit.numbers.IntegralNumber;

public class IntegralArithmeticsImpl<T extends IntegralNumber<T>> extends ArithmeticsImpl<T> implements IntegralArithmetics<T>
{
	public IntegralArithmeticsImpl(T zero, T one)
	{
		super(zero, one);
	}

	@Override
	public T and(T val1, T val2)
	{
		return val1.and(val2);
	}

	@Override
	public T andNot(T val1, T val2)
	{
		return val1.andNot(val2);
	}

	@Override
	public int bitCount(T val)
	{
		return val.bitCount();
	}

	@Override
	public int bitLength(T val)
	{
		return val.bitLength();
	}

	@Override
	public T clearBit(T val, int n)
	{
		return val.clearBit(n);
	}

	@Override
	public T[] divideAndRemainder(T val1, T val2)
	{
		return val1.divideAndRemainder(val2);
	}

	@Override
	public T flipBit(T val, int n)
	{
		return val.flipBit(n);
	}

	@Override
	public T gcd(T val1, T val2)
	{
		return val1.gcd(val2);
	}

	@Override
	public int getLowestSetBit(T val)
	{
		return val.getLowestSetBit();
	}

	@Override
	public T mod(T val, T m)
	{
		return val.mod(m);
	}

	@Override
	public T modInverse(T val, T m)
	{
		return val.modInverse(m);
	}

	@Override
	public T modPow(T val, T exponent, T m)
	{
		return val.modPow(exponent, m);
	}

	@Override
	public T not(T val)
	{
		return val.not();
	}

	@Override
	public T or(T val1, T val2)
	{
		return val1.or(val2);
	}

	@Override
	public T remainder(T val1, T val2)
	{
		return val1.remainder(val2);
	}

	@Override
	public T setBit(T val, int n)
	{
		return val.setBit(n);
	}

	@Override
	public T shiftLeft(T val, int n)
	{
		return val.shiftLeft(n);
	}

	@Override
	public T shiftRight(T val, int n)
	{
		return val.shiftRight(n);
	}

	@Override
	public boolean testBit(T val, int n)
	{
		return val.testBit(n);
	}

	@Override
	public String toStringWithRadix(T val, int radix)
	{
		return val.toStringWithRadix(radix);
	}

	@Override
	public T xor(T val1, T val2)
	{
		return val1.xor(val2);
	}

	@Override
	public int signum(T val)
	{
		return val.signum();
	}
}
