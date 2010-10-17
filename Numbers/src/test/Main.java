package test;

import arit.Arithmetics;
import arit.impl.DoubleExactArithmetics;
import arit.numbers.MyInteger;
import arit.wrappers.IntegralArithmeticsImpl;

import complex.GenericComplex;

public class Main
{

	public static void main(String[] args)
	{
		Arithmetics<MyInteger> arithmetics = new IntegralArithmeticsImpl<MyInteger>(new MyInteger(0), new MyInteger(1));
		
		System.out.println(arithmetics.add(new MyInteger(5), new MyInteger(7)));
		
		
		
		Arithmetics<Double> arit = DoubleExactArithmetics.getInstance();
		GenericComplex<Double> c1 = new GenericComplex<Double>(1.,-1.,arit);
		GenericComplex<Double> c2 = new GenericComplex<Double>(0.,1.,arit);
		System.out.println(c1 + "/" + c2 + " = " + c1.divide(c2));
	}
}
