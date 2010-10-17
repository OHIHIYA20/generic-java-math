package arit;


public interface Arithmetics<T> extends SimpleArithmetics<T>
{
	T add(T val1, T val2);
	T subtract(T val1, T val2);
	T multiply(T val1, T val2);
	T divide(T val1, T val2);
	
	T min(T val1, T val2);
	T max(T val1, T val2);	
	
    T pow(T val, int n); 
    T abs(T val);
    T negate(T val);

    T zero();
    T one();
}
