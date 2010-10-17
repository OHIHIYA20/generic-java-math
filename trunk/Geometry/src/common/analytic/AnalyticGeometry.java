package common.analytic;

import java.util.Comparator;

import arit.Arithmetics;

import common.point.APoint;
import common.segment.ASegment;

public interface AnalyticGeometry<T, P extends APoint<T>, S extends ASegment<T, P>>
{
	Arithmetics<T> arithmetics();
	
	
	T two();

	T square(T t);

	int comparePointsXY(P a, P b);
	int comparePointsYX(P a, P b);

	int compareSegments(S a, S b);	
	
	Comparator<P> pointsComparatorXY();
	Comparator<P> pointsComparatorYX();
	Comparator<S> segmentsComparator();
	
	P createPoint(T x, T y);
	S createSegment(P p1, P p2);
	
	
	T distanceSquared(P a, P b);
	
	T dx(S duz);
	T dy(S duz);
	

	T VP(P a, P b, P c);
	
	T VP(S a,S b);
	
	T SP(P a, P b, P c);
	
	T SP(S a,S b);
	

	boolean parallel(S a, S b);
	
	boolean orthogonal(S a, S b);
	
	Side onWhichSide(S d, P a);
	Side onWhichSide(P a, P b, P c);
	
	boolean onTheSameSide(S d, P a, P b);
			
	public enum Side {
		NONE(0),
		LEFT(1), 
		RIGHT(-1);
		
		public final int cmp;
		
		private Side(int cmp) {
			this.cmp = cmp;
		}
		
		
		
		public static Side getSide(int cmp) {
			return cmp==0?NONE:cmp<0?RIGHT:LEFT;
		}
		public static Side getSide(long cmp) {
			return cmp==0?NONE:cmp<0?RIGHT:LEFT;
		}
	};
}
