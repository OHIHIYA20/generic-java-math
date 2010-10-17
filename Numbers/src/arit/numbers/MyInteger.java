package arit.numbers;


public class MyInteger extends IntegralNumber<MyInteger>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5950073171057731072L;


	/**
	 * The value of the <code>Integer</code>.
	 * 
	 * @serial
	 */
	private final int value;

	/**
	 * Constructs a newly allocated <code>Integer</code> object that represents the specified
	 * <code>int</code> value.
	 * 
	 * @param value
	 *            the value to be represented by the <code>Integer</code> object.
	 */
	public MyInteger(int value)
	{
		this.value = value;
	}

	/**
	 * Returns the value of this <code>Integer</code> as a <code>byte</code>.
	 */
	@Override
	public byte byteValue()
	{
		return (byte) value;
	}

	/**
	 * Returns the value of this <code>Integer</code> as a <code>short</code>.
	 */
	@Override
	public short shortValue()
	{
		return (short) value;
	}

	/**
	 * Returns the value of this <code>Integer</code> as an <code>int</code>.
	 */
	@Override
	public int intValue()
	{
		return value;
	}

	/**
	 * Returns the value of this <code>Integer</code> as a <code>long</code>.
	 */
	@Override
	public long longValue()
	{
		return value;
	}

	/**
	 * Returns the value of this <code>Integer</code> as a <code>float</code>.
	 */
	@Override
	public float floatValue()
	{
		return value;
	}

	/**
	 * Returns the value of this <code>Integer</code> as a <code>double</code>.
	 */
	@Override
	public double doubleValue()
	{
		return value;
	}

	/**
	 * Returns a <code>String</code> object representing this <code>Integer</code>'s value. The
	 * value is converted to signed decimal representation and returned as a string, exactly as if
	 * the integer value were given as an argument to the {@link java.lang.Integer#toString(int)}
	 * method.
	 * 
	 * @return a string representation of the value of this object in base&nbsp;10.
	 */
	@Override
	public String toString()
	{
		return String.valueOf(value);
	}

	/**
	 * Returns a hash code for this <code>Integer</code>.
	 * 
	 * @return a hash code value for this object, equal to the primitive <code>int</code> value
	 *         represented by this <code>Integer</code> object.
	 */
	@Override
	public int hashCode()
	{
		return value;
	}

	/**
	 * Compares this object to the specified object. The result is <code>true</code> if and only if
	 * the argument is not <code>null</code> and is an <code>Integer</code> object that contains the
	 * same <code>int</code> value as this object.
	 * 
	 * @param obj
	 *            the object to compare with.
	 * @return <code>true</code> if the objects are the same; <code>false</code> otherwise.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof MyInteger)
		{
			return value == ((MyInteger) obj).intValue();
		}
		return false;
	}

	/**
	 * Compares two <code>Integer</code> objects numerically.
	 * 
	 * @param anotherInteger
	 *            the <code>Integer</code> to be compared.
	 * @return the value <code>0</code> if this <code>Integer</code> is equal to the argument
	 *         <code>Integer</code>; a value less than <code>0</code> if this <code>Integer</code>
	 *         is numerically less than the argument <code>Integer</code>; and a value greater than
	 *         <code>0</code> if this <code>Integer</code> is numerically greater than the argument
	 *         <code>Integer</code> (signed comparison).
	 * @since 1.2
	 */
	public int compareTo(MyInteger anotherInteger)
	{
		int thisVal = this.value;
		int anotherVal = anotherInteger.value;
		return (thisVal < anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1));
	}

	@Override
	public MyInteger add(MyInteger val)
	{
		return new MyInteger(value+val.value);
	}

	@Override
	public MyInteger divide(MyInteger val)
	{
		return new MyInteger(value/val.value);
	}

	@Override
	public MyInteger max(MyInteger val)
	{
		return value>=val.value?this:val;
	}

	@Override
	public MyInteger min(MyInteger val)
	{
		return value<=val.value?this:val;
	}

	@Override
	public MyInteger multiply(MyInteger val)
	{
		return new MyInteger(value*val.value);
	}

	@Override
	public MyInteger subtract(MyInteger val)
	{
		return new MyInteger(value-val.value);
	}

	@Override
	public MyInteger abs()
	{
		return value>=0?this:negate();
	}

	@Override
	public MyInteger negate()
	{
		return new MyInteger(-value);
	}

	@Override
	public MyInteger pow(int n)
	{
		int res = 1;
		for(int i = 0;i<n;i++)
			res*=value;
		return new MyInteger(res);
	}

	@Override
	public int signum()
	{
		return value>0?1:value<0?-1:0;
	}

	@Override
	public MyInteger and(MyInteger val)
	{
		return new MyInteger(value & val.value);
	}

	@Override
	public MyInteger andNot(MyInteger val)
	{
		return new MyInteger(value & (~val.value));
	}

	@Override
	public int bitCount()
	{
		return Integer.bitCount(value);
	}

	@Override
	public int bitLength()
	{
		return 0;
	}

	@Override
	public MyInteger not()
	{
		return new MyInteger(~value);
	}

	@Override
	public MyInteger or(MyInteger val)
	{
		return new MyInteger(value | val.value);
	}
	
	@Override
	public MyInteger xor(MyInteger val)
	{
		return new MyInteger(value ^ val.value);
	}
	
	@Override
	public MyInteger shiftLeft(int n)
	{
		return new MyInteger(value<<n);
	}

	@Override
	public MyInteger shiftRight(int n)
	{
		return new MyInteger(value>>n);
	}

	@Override
	public MyInteger[] divideAndRemainder(MyInteger val)
	{
		return new MyInteger[]{divide(val), remainder(val)};
	}

	@Override
	public MyInteger remainder(MyInteger val)
	{
		return new MyInteger(value % val.value);
	}

	@Override
	public MyInteger mod(MyInteger m)
	{
		int remainder = value % m.value;
		return new MyInteger(remainder >= 0 ? remainder : (remainder + m.value));
	}
	
	@Override
	public String toStringWithRadix(int radix)
	{
		return Integer.toString(value, radix);
	}

	
	

	@Override
	public MyInteger clearBit(int n)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public MyInteger flipBit(int n)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyInteger gcd(MyInteger val)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLowestSetBit()
	{
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public MyInteger modInverse(MyInteger m)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyInteger modPow(MyInteger exponent, MyInteger m)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyInteger setBit(int n)
	{
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean testBit(int n)
	{
		// TODO Auto-generated method stub
		return false;
	}



}
