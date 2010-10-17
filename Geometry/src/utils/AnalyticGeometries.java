package utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import arit.IntegralArithmetics;
import arit.impl.BigDecimalArithmetics;
import arit.impl.BigIntegerArithmetics;
import arit.impl.DoubleExactArithmetics;
import arit.impl.DoubleRelativeArithmetics;
import arit.impl.IntegerArithmetics;
import arit.impl.LongArithmetics;

import common.analytic.generic.DecimalAnalyticGeometryG;
import common.analytic.generic.IntegralAnalyticGeometryG;
import common.analytic.longp.AnalyticGeometryL;
import common.analytic.optimizations.AnalyticGeometryFraction;

import fraction.Fraction;
import fraction.GenericFraction;

public class AnalyticGeometries
{	
	// ----- ALL INTEGRAL ARITHMETICS -----
	
	public static final AnalyticGeometryL geometryL = AnalyticGeometryL.getInstance();
	
	public static final IntegralAnalyticGeometryG<Integer> geometryGInteger = IntegralAnalyticGeometryG.create(IntegerArithmetics.getInstance());
	public static final IntegralAnalyticGeometryG<Long> geometryGLong = IntegralAnalyticGeometryG.create(LongArithmetics.getInstance());
	public static final IntegralAnalyticGeometryG<BigInteger> geometryGBigInteger = IntegralAnalyticGeometryG.create(BigIntegerArithmetics.getInstance());
	

	
	// ----- ALL DECIMAL ARITHMETICS -----
	
	public static final DecimalAnalyticGeometryG<Fraction> geometryFraction = AnalyticGeometryFraction.getInstance();
					//DecimalAnalyticGeometryG.create(FractionArithmetics.getInstance());

	
	public static <T extends Number> DecimalAnalyticGeometryG<GenericFraction<T>> geometryGFraction(IntegralArithmetics<T> arithmetics) {
		return DecimalAnalyticGeometryG.create(GenericFraction.createArithmetics(arithmetics));
	}
	
	public static final DecimalAnalyticGeometryG<Double> geometryGDoubleRelative = DecimalAnalyticGeometryG.create(DoubleRelativeArithmetics.getInstance());
	public static final DecimalAnalyticGeometryG<Double> geometryGDoubleExact = DecimalAnalyticGeometryG.create(DoubleExactArithmetics.getInstance());

	public static final DecimalAnalyticGeometryG<BigDecimal> geometryGBigDecimalUnlimited = DecimalAnalyticGeometryG.create(new BigDecimalArithmetics());
	
	public static DecimalAnalyticGeometryG<BigDecimal> geometryGBigDecimal(MathContext mc) {
		return DecimalAnalyticGeometryG.create(new BigDecimalArithmetics(mc));
	}

}
