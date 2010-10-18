package matrix;


import junit.framework.Assert;

import org.junit.Test;

import arit.impl.IntegerArithmetics;


public class FullMatrixTest {

	@Test
	public void testRekurentna() {
		Integer a[] = new Integer[] { 1, 0, 2 };
		Integer[] koeficijenti = new Integer[] { 1, 2, -3 };
		for (int i = 1; i < 20; i++) {
			System.out.println(i
					+ " "
					+ FullMatrix.resiRekurentnu(a, koeficijenti, i,
							IntegerArithmetics.getInstance()));
		}
	}
	
	
	@Test
	public void testMnozenje() {
//		FullMatrix<Integer> m = FullMatrix.one(2, IntegerArithmetics.getInstance());
		FullMatrix<Integer> m = new FullMatrix<Integer>(new Integer[][]{{1,1},{0,1}}, IntegerArithmetics.getInstance());
		FullMatrix<Integer> check = FullMatrix.one(2,IntegerArithmetics.getInstance());
		for(int i = 0;i<1000;i++) {
			Assert.assertEquals(check, m.pow(i));
			check = check.multiply(m);
		}
	}
}

//
//1 1
//2 0
//3 2
//4 -1
//5 -1
//6 -5
//7 -1
//8 -15
//9 -5
//10 -34
//11 -1
//12 -65
//13 -15
//14 -256
//15 -5
//16 -120
//17 -34
//18 -665
//19 -1
//
//
//1 1
//2 0
//3 2
//4 -1
//5 3
//6 -5
//7 4
//8 -15
//9 8
//10 -34
//11 27
//12 -65
//13 91
//14 -120
//15 257
//16 -256
//17 618
//18 -665
//19 1339
