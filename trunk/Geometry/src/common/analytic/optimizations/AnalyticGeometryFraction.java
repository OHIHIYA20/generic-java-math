package common.analytic.optimizations;

import common.analytic.generic.DecimalAnalyticGeometryG;
import common.point.PointG;

import fraction.Fraction;

public class AnalyticGeometryFraction extends DecimalAnalyticGeometryG<Fraction>
{
	private AnalyticGeometryFraction()
	{
		super(Fraction.createArithmetics());
	}

	private Fraction calculateBxMinusAxMulCyMinusAy(PointG<Fraction> a, PointG<Fraction> b, PointG<Fraction> c) {
		Fraction ax = a.x();
		Fraction bx = b.x();
		Fraction ay = a.y();
		Fraction cy = c.y();
		
		long baBR = bx.br*ax.im-bx.im*ax.br;
		long baIM = bx.im*ax.im;
		
		long baGCD = Fraction.gcd(baBR, baIM);
		baBR/=baGCD;
		baIM/=baGCD;
		
		long caBR = cy.br*ay.im-cy.im*ay.br;
		long caIM = cy.im*ay.im;
		
		long caGCD = Fraction.gcd(caBR, caIM);
		caBR/=caGCD;
		caIM/=caGCD;
		
		return new Fraction(baBR*caBR, baIM * caIM);
	}
	
	@Override
	public Fraction VP(PointG<Fraction> a, PointG<Fraction> b, PointG<Fraction> c)
	{
		return calculateBxMinusAxMulCyMinusAy(a, b, c).subtract(calculateBxMinusAxMulCyMinusAy(a, c, b));
				
//				arit.multiply(arit.subtract(b.x(), a.x()), arit.subtract(c.y(), a.y())), 
//				arit.multiply(arit.subtract(b.y(), a.y()), arit.subtract(c.x(), a.x())));
	}
	
	@Override
	public int signVP(PointG<Fraction> a, PointG<Fraction> b, PointG<Fraction> c)
	{
		return calculateBxMinusAxMulCyMinusAy(a, b, c).compareTo(calculateBxMinusAxMulCyMinusAy(a, c, b));
	}
	
	
	private static final AnalyticGeometryFraction arit = new AnalyticGeometryFraction();
		
	public static AnalyticGeometryFraction getInstance() {
		return arit;
	}

}
