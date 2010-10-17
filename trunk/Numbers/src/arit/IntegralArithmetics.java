package arit;


public interface IntegralArithmetics<T> extends Arithmetics<T>
{
    int signum(T val);
    
    T[] divideAndRemainder(T val1, T val2);
    T remainder(T val1, T val2);
    
    T gcd(T val1, T val2);
    
    T mod(T val, T m);
    T modPow(T val, T exponent, T m);
    T modInverse(T val, T m);
    
  
    T shiftLeft(T val, int n);  
    T shiftRight(T val, int n);

  
    T and(T val1, T val2);
    T or(T val1, T val2);
    T xor(T val1, T val2);
    T not(T val);
    T andNot(T val1, T val2);
    
    boolean testBit(T val, int n);
    T setBit(T val, int n) ;
    T clearBit(T val, int n);
    T flipBit(T val, int n);
    int getLowestSetBit(T val);


    int bitLength(T val);
    int bitCount(T val);

    String toStringWithRadix(T val, int radix);
}
