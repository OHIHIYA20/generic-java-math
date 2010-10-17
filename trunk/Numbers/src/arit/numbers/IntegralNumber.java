package arit.numbers;


public abstract class IntegralNumber<T extends IntegralNumber<T>> extends ArithmeticNumber<T> 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842562095427658938L;

	
    public abstract T[] divideAndRemainder(T val);
    public abstract T remainder(T val);
    
    public abstract T gcd(T val);
    
    public abstract T mod(T m);
    public abstract T modPow(T exponent, T m);
    public abstract T modInverse(T m);
    
  
    public abstract T shiftLeft(int n);  
    public abstract T shiftRight(int n);

  
    public abstract T and(T val);
    public abstract T or(T val);
    public abstract T xor(T val);
    public abstract T not();
    public abstract T andNot(T val);
    
    public abstract boolean testBit(int n);
    public abstract T setBit(int n) ;
    public abstract T clearBit(int n);
    public abstract T flipBit(int n);
    public abstract int getLowestSetBit();


    public abstract int bitLength();
    public abstract int bitCount();

    public abstract String toStringWithRadix(int radix);
}
