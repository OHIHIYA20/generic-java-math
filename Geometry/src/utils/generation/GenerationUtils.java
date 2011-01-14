package utils.generation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import tuples.Pair;

import common.conversions.Convertor;
import common.point.PointG;
import common.point.PointL;
import common.segment.SegmentG;
import common.segment.SegmentL;

public class GenerationUtils
{
	
	private static final Random rand = new Random(991);
	
	public final static Creator<PointL> creatorPointL = new Creator<PointL>() {
		@Override
		public PointL create() {
			return new PointL(rand.nextInt(50000), rand.nextInt(50000));
		}		
	};
	
	public static Creator<PointL> creatorPointLOnQuaziCircle(final int n) {
		return new Creator<PointL>() {
			@Override
			public PointL create() {
				int r = 700000 + rand.nextInt(700000 / n);
				double fi = rand.nextDouble() * 2 * Math.PI;
				return new PointL((int) (r * Math.cos(fi)), (int) (r * Math.sin(fi)));
			}
		};
	}
	
	public static Creator<PointG<Long>> creatorPointGLongLOnQuaziCircle(final int n) {
		return new Creator<PointG<Long>>() {
			@Override
			public PointG<Long> create() {
				int r = 700000 + rand.nextInt(700000 / n);
				double fi = rand.nextDouble() * 2 * Math.PI;
				return new PointG<Long>((long) (r * Math.cos(fi)), (long) (r * Math.sin(fi)));
			}
		};
	}
	
	public static Creator<PointG<Double>> creatorPointGDoubleFromL(final Creator<PointL> creatorPointL) {
		return new Creator<PointG<Double>>() {
			@Override
			public PointG<Double> create() {
				return Convertor.FromL.toPointGDouble(creatorPointL.create());
			}		
		};
	}
	
	public static Creator<PointG<BigInteger>> creatorPointGBigIntFromL(final Creator<PointL> creatorPointL) {
		return new Creator<PointG<BigInteger>>() {
			@Override
			public PointG<BigInteger> create() {
				return Convertor.FromL.toPointGBigInteger(creatorPointL.create());
			}		
		};
	}
	
	public static Creator<SegmentG<BigInteger>> creatorSegmentGBigIntFromL(final Creator<SegmentL> creatorSegmentL) {
		return new Creator<SegmentG<BigInteger>>() {
			@Override
			public SegmentG<BigInteger> create() {
				return Convertor.FromL.toSegmentGBigInteger(creatorSegmentL.create());
			}		
		};
	}
	
	
	public final static Creator<PointG<Double>> creatorPointGDouble = creatorPointGDoubleFromL(creatorPointL);
	
	public static Creator<PointG<Double>> creatorPointGDoubleOnQuaziCircle(final int n) {
		return creatorPointGDoubleFromL(creatorPointLOnQuaziCircle(n));
	}
	
//	public static <T, P extends APoint<T>, S extends ASegment<T, P>> Creator<S> creatorSegmentFromPoint(final Creator<P> creatorPoint) {
//		return new Creator<S>() {
//			@Override
//			public S create() {
//				return new S(creatorPoint, creatorPoint);
//			}
//		};
//	}
	
	public final static Creator<SegmentL> creatorSegmentL = new Creator<SegmentL>() {
		@Override
		public SegmentL create() {
			return new SegmentL(creatorPointL.create(), creatorPointL.create());
		}
	};
	
	public final static Creator<SegmentG<Double>> creatorSegmentGDouble = new Creator<SegmentG<Double>>() {
		@Override
		public SegmentG<Double> create() {
			return new SegmentG<Double>(creatorPointGDouble.create(), creatorPointGDouble.create());
		}
	};
	
	
	public static Pair<ArrayList<PointL>, ArrayList<PointL>> generateMonotonicPolygon(int n) 
	{
		ArrayList<PointL> gore = new ArrayList<PointL>();
		ArrayList<PointL> dole = new ArrayList<PointL>();
			
		gore.add(new PointL(0,0));
		dole.add(gore.get(0));
		
		for(int i = 0;i< n;i++)
			if (rand.nextBoolean()) {
				PointL last = gore.get(gore.size()-1);
				gore.add(new PointL(last.x+1+rand.nextInt(5),1+rand.nextInt(10000)));
			}
			else {
				PointL last = dole.get(dole.size()-1);
				dole.add(new PointL(last.x+1+rand.nextInt(5),-(1+rand.nextInt(10000))));
			}
			
		PointL last = new PointL(Math.max( gore.get(gore.size()-1).x,dole.get(dole.size()-1).x)+1, 0);
			
		gore.add(last);
		dole.add(last);
		return new Pair<ArrayList<PointL>, ArrayList<PointL>>(gore, dole);
	}
	
	
	public static <T> ArrayList<T> generateArray(int n, Creator<T> kreator) { 
		ArrayList<T> res = new ArrayList<T>();
		for(int i = 0;i<n;i++)
			res.add(kreator.create());
		return res;
	}
		
	
	public static void randomize() {
		rand.setSeed(System.nanoTime());
	}
	
	public static void reset() {
		rand.setSeed(991);
	}

}
