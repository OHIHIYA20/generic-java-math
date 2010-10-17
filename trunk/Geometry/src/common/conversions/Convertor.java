package common.conversions;

import java.math.BigDecimal;
import java.math.BigInteger;

import common.point.PointG;
import common.point.PointL;
import common.segment.SegmentG;
import common.segment.SegmentL;

import fraction.Fraction;
import fraction.GenericFraction;

public class Convertor
{
	public static class FromL {

		public static PointG<Long> toPointGLong(PointL p) {
			return new PointG<Long>(p.x, p.y);
		}

		public static SegmentG<Long> toSegmentGLong(SegmentL s) {
			return new SegmentG<Long>(toPointGLong(s.p1()), toPointGLong(s.p2()));
		}
		
		public static PointG<Double> toPointGDouble(PointL p) {
			return new PointG<Double>((double)p.x, (double)p.y);
		}

		public static SegmentG<Double> toSegmentGDouble(SegmentL s) {
			return new SegmentG<Double>(toPointGDouble(s.p1()), toPointGDouble(s.p2()));
		}
		
		public static PointG<BigInteger> toPointGBigInteger(PointL p) {
			return new PointG<BigInteger>(BigInteger.valueOf(p.x), BigInteger.valueOf(p.y));
		}

		public static SegmentG<BigInteger> toSegmentGBigInteger(SegmentL s) {
			return new SegmentG<BigInteger>(toPointGBigInteger(s.p1()), toPointGBigInteger(s.p2()));
		}
		
	}
	

	public static class FromFraction {

		public static PointG<GenericFraction<Long>> toPointGFractionLong(PointG<Fraction> p) {
			return new PointG<GenericFraction<Long>>(p.x().toGenericFraction(), p.y().toGenericFraction());
		}

		public static SegmentG<GenericFraction<Long>> toSegmentGFractionLong(SegmentG<Fraction> s) {
			return new SegmentG<GenericFraction<Long>>(toPointGFractionLong(s.p1()), toPointGFractionLong(s.p2()));
		}
		
		public static PointG<GenericFraction<BigInteger>> toPointGFractionBigInteger(PointG<Fraction> p) {
			return new PointG<GenericFraction<BigInteger>>(p.x().toGenericBigIntegerFraction(), p.y().toGenericBigIntegerFraction());
		}

		public static SegmentG<GenericFraction<BigInteger>> toSegmentGFractionBigInteger(SegmentG<Fraction> s) {
			return new SegmentG<GenericFraction<BigInteger>>(toPointGFractionBigInteger(s.p1()), toPointGFractionBigInteger(s.p2()));
		}
		
		
		public static PointG<Double> toPointGDouble(PointG<Fraction> p) {
			return new PointG<Double>(p.x().doubleValue(), p.y().doubleValue());
		}

		public static SegmentG<Double> toSegmentGDouble(SegmentG<Fraction> s) {
			return new SegmentG<Double>(toPointGDouble(s.p1()), toPointGDouble(s.p2()));
		}
		
		public static PointG<BigDecimal> toPointGBigDecimal(PointG<Fraction> p) {
			return new PointG<BigDecimal>(p.x().toBigDecimal(), p.y().toBigDecimal());
		}

		public static SegmentG<BigDecimal> toSegmentGBigDecimal(SegmentG<Fraction> s) {
			return new SegmentG<BigDecimal>(toPointGBigDecimal(s.p1()), toPointGBigDecimal(s.p2()));
		}
		
	}
	
	
	public static class FromGLong {
		
		public static PointL toPointL(PointG<Long> p) {
			return new PointL(p.x(), p.y());
		}
		
		public static SegmentL toSegmentL(SegmentG<Long> s) {
			return new SegmentL(toPointL(s.p1()), toPointL(s.p2()));
		}
		
		
	}
	
	
	
	
}
