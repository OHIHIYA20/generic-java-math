Classes for generic manipulation with mathematic constructs:
  * numbers
  * complex numbers
  * fractions
  * matrixes
  * geometry
  * ...

For example, to create a generic sum or coprime function:

```

	
public static <T> T sum(Collection<T> elements, Arithmetics<T> arit) {
	T res = arit.zero();
	for(T t : elements) 
		res = arit.add(res, t);
	return res;
}

public static <T> boolean coprime(T a, T b, IntegralArithmetics<T> arit) {
	return arit.gcd(a, b).equals(arit.one());
}

```

And calling it:

```

ArrayList<Integer> integerList;
ArrayList<Double> doubleList;

sum(integerList, IntegerArithmetics.getInstance());
sum(doubleList, DoubleExactArithmetics.getInstance());
	
coprime(55, 128, IntegerArithmetics.getInstance());
coprime(new BigInteger("55"), new BigInteger("128"), BigIntegerArithmetics.getInstance());


```