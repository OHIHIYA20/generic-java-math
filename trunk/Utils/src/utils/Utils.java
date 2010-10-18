package utils;

import java.util.Random;

public class Utils
{
	private static final ThreadLocal<Random> random = new ThreadLocal<Random>(){
		@Override 
		protected Random initialValue() {
			return new Random();
		}
	};

	public static Random RANDOM() {
		return random.get();
	}

	
	public static class Common {

		public static boolean equalsDouble(double x1, double x2) {
			return Math.abs(x1-x2)<1e-8*(Math.abs(x1)+Math.abs(x2));
		}
	
		public static int hashCode(long value){		    
		   	return (int)(value ^ (value >>> 32));		   
		}
		
		public static int compare(double x1, double x2) {
			return (equalsDouble(x1, x2))?0:(x1>x2)?1:-1;
		}

		public static boolean equalsFloat(float x1, float x2) {
			return Math.abs(x1-x2)<1e-6*(Math.abs(x1)+Math.abs(x2));
		}
		
		public static int compare(float x1, float x2) {
			return (equalsFloat(x1, x2))?0:(x1>x2)?1:-1;
		}
		
		public static int compare(long a, long b) {
			return (a<b ? -1 : (a==b ? 0 : 1));
		}
	
		public static int compare(int a, int b) {
			return (a<b ? -1 : (a==b ? 0 : 1));
		}
		
		public static int[] randomPermumation(int length) {
			int[] res = new int[length];
			for(int i = 0;i<length;i++)
				res[i] = i;
			
			Random rand = RANDOM();
			for(int i = 1;i<length;i++)
			{			
				int j = rand.nextInt(i);
				if (i!=j)
				{
					int pom = res[i];
					res[i] = res[j];
					res[j] = pom;
				}
			}		
			return res;
		}
		
		public static<T> void randomPermutation(T[] array) { 
			Random rand = RANDOM();
			for(int i = 1;i<array.length;i++)
			{			
				int j = rand.nextInt(i);
				if (i!=j)
				{
					T pom = array[i];
					array[i] = array[j];
					array[j] = pom;
				}
			}		
		}
	
		public static boolean equals(Object o1, Object o2) {
			return o1 == null?false:o1.equals(o2);
		}
		
		public static int hashCode(Object o) {
			return o==null?0:o.hashCode();
		}
	}

	
	
	public static class Time {							
		private static final int TIME_MULTIPLICATION = 1;
		
		public static final long NANOS_IN_SECOND = 1000L * 1000L * 1000L;

		public static final long HOUR = 60*60/TIME_MULTIPLICATION;
		public static final long DAY = 24*60*60/TIME_MULTIPLICATION;
		public static final long WEEK = 7*24*60*60/TIME_MULTIPLICATION;
				
		public static long getNanoTime() {
			return System.nanoTime() * TIME_MULTIPLICATION;
		}
		
		public static long getRealSystemTime(long nanoTime) {
			return nanoTime / TIME_MULTIPLICATION;
		}

		public static double secondsPassed(long from) {
			return (getNanoTime() - from) * 1.0e-9;
		}

		public static double secondsPassed(long from, long to) {
			return (to - from) * 1.0e-9;
		}
		
		public static long secondsToNanos(double time) {
			return (long)(time * 1000000000);
		}

		public static double secondsLeft(long to) {
			return (to - getNanoTime()) * 1.0e-9;
		}
	}
		

}
