package matrix;

import java.util.Arrays;

import arit.Arithmetics;
import arit.numbers.ArithmeticNumber;

public class FullMatrix<T>  extends ArithmeticNumber<FullMatrix<T>> implements Matrix<T> {
	private T[][] a;
	private final Arithmetics<T> arithmetics;

	@SuppressWarnings("unchecked")
	public FullMatrix(int n, int m, Arithmetics<T> arithmetics) {
		a = (T[][]) (new Object[n][m]);
		this.arithmetics = arithmetics;
	}

	@SuppressWarnings("unchecked")
	public FullMatrix(T[][] mat, Arithmetics<T> arithmetics) {
		a = (T[][]) (new Object[mat.length][]);
		for(int i = 0;i<mat.length;i++) 
		{
			if (mat[i].length!=mat[0].length)
				throw new IllegalArgumentException("Svi redovi nemaju istu duzinu");
			a[i] = mat[i].clone();
		}
		this.arithmetics = arithmetics;
	}
	
	public void set(int i, int j, T value) {
		a[i][j] = value;
	}

	public T get(int i, int j) {
		return a[i][j];
	}

	public int n() {
		return a.length;
	}

	public int m() {
		return a[0].length;
	}

	@Override
	public FullMatrix<T> add(FullMatrix<T> m) {
		if ((n() != m.n()) || (m() != m.m()))
			try {
				throw new IllegalArgumentException("Ne mogu da se saberu");
			} catch (Exception e) {
				e.printStackTrace();
			}

		FullMatrix<T> res = new FullMatrix<T>(n(), m(), arithmetics);
		for (int i = 0; i < n(); i++)
			for (int j = 0; j < m(); j++)
				res.set(i, j, arithmetics.add(get(i, j), m.get(i, j)));
		return res;
	}

	public FullMatrix<T> multiplyWithKoef(T alfa) {
		FullMatrix<T> res = new FullMatrix<T>(n(), m(), arithmetics);
		for (int i = 0; i < n(); i++)
			for (int j = 0; j < m(); j++)
				res.set(i, j, arithmetics.multiply(get(i, j), alfa));
		return res;
	}


	public FullMatrix<T> multiply(FullMatrix<T> m) {
		if (m() != m.n())
			try {
				throw new IllegalArgumentException("Ne mogu da se pomnoze");
			} catch (Exception e) {
				e.printStackTrace();
			}

		FullMatrix<T> res = new FullMatrix<T>(n(), m.m(), arithmetics);
		for (int i = 0; i < n(); i++)
			for (int j = 0; j < m.m(); j++) {
				res.set(i, j, arithmetics.zero());
				for (int k = 0; k < m(); k++)
					res.set(i, j, arithmetics
							.add(res.get(i, j), arithmetics.multiply(get(
									i, k), m.get(k, j))));
			}
		return res;
	}
	
	@Override
	public FullMatrix<T> pow(int exponent) {
		if (m() != n())
			try {
				throw new IllegalArgumentException("Ne moze da se stepenuje");
			} catch (Exception e) {
				e.printStackTrace();
			}

		FullMatrix<T> res = one(n(), arithmetics);
		FullMatrix<T> pow = this;
		while (exponent != 0) {
		    if ((exponent & 1) == 1) {
		    	res = res.multiply(pow);
		    }
		    if ((exponent >>>= 1) != 0) {
		    	pow = pow.multiply(pow);
		    }
		}
		return res;		
	}

	public static <T> FullMatrix<T> one(int n, Arithmetics<T> arit) {
		FullMatrix<T> res = new FullMatrix<T>(n, n, arit);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				res.set(i, j, (i == j) ? arit.one() : arit.zero());
		return res;
	}
	
	public static <T> FullMatrix<T> zero(int n, int m, Arithmetics<T> arit) {
		return new FullMatrix<T>(n, m, arit);
	}	
	


	/**
	 * an = a(n-1) * x1 + a(n-2) * x2 + ... + a(n-k) * xk
	 * 
	 * x1 = koeficijenti[0] x2 = koeficijenti[1] ...
	 * 
	 * @return
	 */
	public static <T> FullMatrix<T> getMatricaZaRekurentnu(
			Arithmetics<T> arithmetics, T[] koeficijenti) {
		FullMatrix<T> res = new FullMatrix<T>(koeficijenti.length,
				koeficijenti.length, arithmetics);

		for (int j = 0; j < koeficijenti.length; j++)
			res.set(0, j, koeficijenti[j]);

		for (int i = 1; i < koeficijenti.length; i++)
			for (int j = 0; j < koeficijenti.length; j++)
				if (i == j + 1)
					res.set(i, j, arithmetics.one());
				else
					res.set(i, j, arithmetics.zero());

		return res;
	}

	/**
	 * an = a(n-1) * x1 + a(n-2) * x2 + ... + a(n-k) * xk
	 * 
	 * x1 = koeficijenti[0] x2 = koeficijenti[1] ...
	 * 
	 * a1 = a[0] a2 = a[1] ...
	 * 
	 * @return an
	 */
	public static <T> T resiRekurentnu(T[] a, T[] koeficijenti, int n,
			Arithmetics<T> arithmetics) {
		if (n <= a.length)
			return a[n - 1];
		FullMatrix<T> m = getMatricaZaRekurentnu(arithmetics, koeficijenti);
		m = m.pow(n - a.length);

		FullMatrix<T> res = new FullMatrix<T>(a.length, 1, arithmetics);
		for (int i = 0; i < a.length; i++)
			res.set(i, 0, a[a.length - i - 1]);

		res = m.multiply(res);

		return res.get(0, 0);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (obj==this) return true;
		if (obj==null) return false;
		if (obj.getClass().equals(this.getClass()))
		{
			FullMatrix<T> o = (FullMatrix<T>)obj;
			if (n()!=o.n() || m()!=o.m())
				return false;
			for (int i = 0;i<n();i++)
				for (int j = 0;j<m();j++)
					if (!arithmetics.equals(get(i,j), o.get(i, j)))
						return false;
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(FullMatrix<T> o) {
		if (n()!=o.n())
			return n()-o.n(); 			
		if (m()!=o.m())
			return m()-o.m(); 			
		for (int i = 0;i<n();i++)
			for (int j = 0;j<m();j++) {
				int cmp  = arithmetics.compare(get(i,j), o.get(i, j));
				if (cmp!=0)
					return cmp;
			}
		return 0;
	}

	@Override
	public FullMatrix<T> negate() {
		FullMatrix<T> res = new FullMatrix<T>(n(), m(), arithmetics);
		for (int i = 0;i<n();i++)
			for (int j = 0;j<m();j++)
				res.set(i, j, arithmetics.negate(get(i,j)));
		return null;
	}

	@Override
	public FullMatrix<T> subtract(FullMatrix<T> val) {
		return this.add(val.negate());
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0;
		for (int i = 0;i<n();i++)
			for (int j = 0;j<m();j++)				
				hashCode = 31 * hashCode + get(i,j).hashCode();
		return hashCode;
	}
	
	@Override
	public FullMatrix<T> abs() {
		throw new UnsupportedOperationException();
	}

	@Override
	public FullMatrix<T> divide(FullMatrix<T> val) {
		throw new UnsupportedOperationException();	}


	@Override
	public FullMatrix<T> max(FullMatrix<T> val) {
		throw new UnsupportedOperationException();	}

	@Override
	public FullMatrix<T> min(FullMatrix<T> val) {
		throw new UnsupportedOperationException();	}

	@Override
	public int signum() {
		throw new UnsupportedOperationException();	}


	@Override
	public double doubleValue() {
		throw new UnsupportedOperationException();	}

	@Override
	public float floatValue() {
		throw new UnsupportedOperationException();	}

	@Override
	public int intValue() {
		throw new UnsupportedOperationException();	}

	@Override
	public long longValue() {
		throw new UnsupportedOperationException();	}


	
	
	@Override
	public String toString() {
		return Arrays.deepToString(a);
	}
}
