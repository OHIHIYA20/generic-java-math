package common;

import java.math.BigInteger;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

import arit.impl.BigIntegerArithmetics;
import arit.impl.DoubleRelativeArithmetics;
import arit.impl.FractionArithmetics;
import arit.impl.LongArithmetics;

import common.analytic.generic.DecimalAnalyticGeometryG;
import common.analytic.generic.IntegralAnalyticGeometryG;
import common.analytic.longp.AnalyticGeometryL;
import common.conversions.Convertor;
import common.point.PointG;
import common.segment.SegmentG;
import common.segment.SegmentL;

import fraction.Fraction;
import fraction.GenericFraction;

public class IntersectionTest
{
	private void testOneIntersection(SegmentL s1, SegmentL s2) {
		AnalyticGeometryL geomL = AnalyticGeometryL.getInstance();
		PointG<Fraction> rezL = geomL.intersection(s1, s2);
		System.out.println(rezL);
		
		
		SegmentG<Long> s1GL = Convertor.FromL.toSegmentGLong(s1);
		SegmentG<Long> s2GL = Convertor.FromL.toSegmentGLong(s2);
		IntegralAnalyticGeometryG<Long> geomGL = new IntegralAnalyticGeometryG<Long>(LongArithmetics.getInstance());
		PointG<GenericFraction<Long>> rezGL = geomGL.intersection(s1GL, s2GL);

		System.out.println(rezGL);
		if (rezL == null)
			Assert.assertNull(rezGL);
		else
			Assert.assertEquals(Convertor.FromFraction.toPointGFractionLong(rezL), rezGL);

		
		SegmentG<BigInteger> s1GB = Convertor.FromL.toSegmentGBigInteger(s1);
		SegmentG<BigInteger> s2GB = Convertor.FromL.toSegmentGBigInteger(s2);
		IntegralAnalyticGeometryG<BigInteger> geomGB = new IntegralAnalyticGeometryG<BigInteger>(BigIntegerArithmetics.getInstance());
		PointG<GenericFraction<BigInteger>> rezGB = geomGB.intersection(s1GB, s2GB);

		System.out.println(rezGB);
		if (rezL == null)
			Assert.assertNull(rezGB);
		else
			Assert.assertEquals(Convertor.FromFraction.toPointGFractionBigInteger(rezL), rezGB);

		
		SegmentG<Double> s1GD = Convertor.FromL.toSegmentGDouble(s1);
		SegmentG<Double> s2GD = Convertor.FromL.toSegmentGDouble(s2);
		DecimalAnalyticGeometryG<Double> geomGD = new DecimalAnalyticGeometryG<Double>(DoubleRelativeArithmetics.getInstance());
		PointG<Double> rezGD = geomGD.intersection(s1GD, s2GD);

		System.out.println(rezGD);
		if (rezL == null)
			Assert.assertNull(rezGD);
		else {
			PointG<Double> p2 = Convertor.FromFraction.toPointGDouble(rezL);
			Assert.assertEquals(p2.x(), rezGD.x(), 1e-8);
			Assert.assertEquals(p2.y(), rezGD.y(), 1e-8);
		}

		
		SegmentG<Fraction> s1F = s1.toFraction();
		SegmentG<Fraction> s2F = s2.toFraction();
		DecimalAnalyticGeometryG<Fraction> geomF = new DecimalAnalyticGeometryG<Fraction>(FractionArithmetics.getInstance());
		PointG<Fraction> rezF = geomF.intersection(s1F, s2F);

		System.out.println(rezF);
		if (rezL == null)
			Assert.assertNull(rezF);
		else
			Assert.assertEquals(rezL, rezF);

	}
	

	@Test
	public void testIntersection() {
		testOneIntersection(new SegmentL(0,0,2,2),
				new SegmentL(0,2,2,0));
		
		Random rand = new Random(11707);
		for(int i = 0; i < 100000; i++) {
			testOneIntersection(
					new SegmentL(rand.nextInt(1000000), rand.nextInt(1000000), rand.nextInt(1000000), rand.nextInt(1000000)),
					new SegmentL(rand.nextInt(1000000), rand.nextInt(1000000), rand.nextInt(1000000), rand.nextInt(1000000)));
		}
		
	}
}
