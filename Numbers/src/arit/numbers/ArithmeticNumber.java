package arit.numbers;


public abstract class ArithmeticNumber<T extends ArithmeticNumber<T>> extends Number implements Comparable<T>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5479977004927719439L;

	
	public abstract T add(T val);
	public abstract T subtract(T val);
	public abstract T multiply(T val);
	public abstract T divide(T val);
	
	public abstract T min(T val);
	public abstract T max(T val);
	
	
    public abstract T pow(int n); 
    public abstract T abs();
   
    public abstract T negate();

    public abstract int signum();

    @Override
    public abstract boolean equals(Object obj);
    @Override
    public abstract int hashCode();
}
