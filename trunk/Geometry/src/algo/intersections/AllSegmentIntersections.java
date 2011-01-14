package algo.intersections;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;

import utils.AnalyticGeometries;
import utils.Utils;
import utils.generation.GenerationUtils;
import arit.Arithmetics;
import arit.DecimalArithmetics;
import arit.impl.DoubleRelativeArithmetics;

import common.analytic.AnalyticIntersectionGeometry;
import common.analytic.DecimalAnalyticGeometry;
import common.analytic.generic.DecimalAnalyticGeometryG;
import common.point.APoint;
import common.segment.ASegment;
import common.segment.SegmentG;
import common.segment.SegmentL;


public class AllSegmentIntersections
{

	enum EventType {
		START,
		INTERSECTION,
		END
	}
	
	static class EventPoint<T, P extends APoint<T>, S extends ASegment<T, P>, TF> 
	implements Comparable<EventPoint<T, P, S, TF>> {
		public final TF y;
		public final TF x;
		public final EventType tip;
		
		public final S duz;
		public final S drugaDuz;
		private final Arithmetics<TF> aritF; 
		
		public EventPoint(TF y, TF x, EventType tip, S duz, S drugaDuz, Arithmetics<TF> arithmeticsF) {
			this.y = y;
			this.x = x;
			this.tip = tip;
			this.duz = duz;
			this.drugaDuz = drugaDuz;
			this.aritF = arithmeticsF;
		}

		@Override
		public boolean equals(Object obj) {
			EventPoint<T, P, S, TF> d = (EventPoint<T, P, S, TF>) obj;
			return aritF.equals(y, d.y) && tip.equals(d.tip) && duz.equals(d.duz) && ((drugaDuz==null)?(d.drugaDuz==null):(drugaDuz.equals(d.drugaDuz)));
		}
		
		@Override
		public int compareTo(EventPoint<T, P, S, TF> o) {
			int cmp = aritF.compare(y, o.y);
			return (cmp!=0)?cmp:tip.compareTo(o.tip);				
		}
		
		@Override
		public String toString()
		{
			return tip + " " + y;
		}
	}
	
	static class StatusComparator<T, P extends APoint<T>, S extends ASegment<T, P>, TF, PF extends APoint<TF>, SF extends ASegment<TF, PF>> 
		implements Comparator<S> {
		private final AnalyticIntersectionGeometry<T, P, S, TF, PF, SF> geom; 
		private final DecimalArithmetics<TF> aritF;
		private final HashMap<S, TF> cacheCurY = new HashMap<S, TF>();
		private final HashMap<S, TF> cacheCurYPlus1 = new HashMap<S, TF>(4);
		private TF curY;
		private TF curYplus1;
		
		public void setCurY(TF curY) {
			if (aritF.compare(this.curY, curY) >= 0)
				System.out.println("WHY!?!?" + this.curY + " " + curY);
//			System.out.println("curY = " + curY);
			this.curY = curY;
			this.curYplus1 = aritF.add(curY, aritF.one());
			cacheCurY.clear();
			cacheCurYPlus1.clear();
		}
		
		public TF getCurY()
		{
			return curY;
		}
		
		public StatusComparator(TF curY, AnalyticIntersectionGeometry<T, P, S, TF, PF, SF> geom)
		{
			this.curY = curY;
			this.geom = geom;
			this.aritF = geom.toDecimalGeometry().arithmetics();
		}

		@Override
		public int compare(S o1, S o2) {
			TF x1 = getXcur(o1);
			TF x2 = getXcur(o2);
			int cmp = aritF.compare(x1, x2);
			if (cmp!=0) return cmp;
			x1 = getXcurPlus1(o1);
			x2 = getXcurPlus1(o2);
			return aritF.compare(x1, x2);
		}	
		
		
		TF getXcur(S duz) {
			return getX(duz, curY, cacheCurY);
		}
		TF getXcurPlus1(S duz) {
			return getX(duz, curYplus1, cacheCurYPlus1);
		}

		TF getX(S duz, TF curY, HashMap<S, TF> cache)
		{
			TF cached = cache.get(duz);
			if (cached != null) {
				return cached;
			}
			
			TF rez = aritF.add(
					geom.toFraction(duz.p1().x()), 
					aritF.divide(
							aritF.multiply(
									geom.toFraction(geom.dx(duz)), 
									aritF.subtract(
											curY, 
											geom.toFraction(duz.p1().y())
											)
									), 
							geom.toFraction(geom.dy(duz))
							)
					);
			cache.put(duz, rez);
			return rez;
		}
	}
	

	
	
	public static 
	<T, P extends APoint<T>, S extends ASegment<T, P>, TF, PF extends APoint<TF>, SF extends ASegment<TF, PF>> 
	ArrayList<PF> calculateAll(ArrayList<S> duzi, AnalyticIntersectionGeometry<T, P, S, TF, PF, SF> geom) {
		
		Arithmetics<T> arit = geom.arithmetics();
		DecimalAnalyticGeometry<TF, PF, SF> geomF = geom.toDecimalGeometry();
		Arithmetics<TF> aritF = geom.toDecimalGeometry().arithmetics();
		
		PriorityQueue<EventPoint<T, P, S, TF>> events = new PriorityQueue<EventPoint<T, P, S, TF>>();
		TreeSet<PF> res = new TreeSet<PF>(geomF.pointsComparatorXY());

		TF minY = null;
		
		for(S d : duzi) {
			TF curMin = geom.toFraction(arit.min(d.p1().y(),d.p2().y()));
			if (minY==null || aritF.compare(minY, curMin)>0)
				minY = curMin;
			events.add(new EventPoint<T, P, S, TF>(geom.toFraction(arit.min(d.p1().y(),d.p2().y())),null, EventType.START, d, null, aritF));
			events.add(new EventPoint<T, P, S, TF>(geom.toFraction(arit.max(d.p1().y(),d.p2().y())),null, EventType.END, d, null, aritF));
		}
		StatusComparator<T, P, S, TF, PF, SF> statusComparator = new StatusComparator<T, P, S, TF, PF, SF>(aritF.subtract(minY, geomF.two()), geom);
		
		TreeSet<S> status = new TreeSet<S>(statusComparator);		
		
		while (events.size()>0)
		{
			EventPoint<T, P, S, TF> next = events.poll();
			ArrayList<EventPoint<T, P, S, TF>> trenutni = new ArrayList<EventPoint<T, P, S, TF>>();
			trenutni.add(next);
			while (events.size()>0 && aritF.equals(next.y, events.peek().y))
				trenutni.add(events.poll());

			for (EventPoint<T, P, S, TF> cur : trenutni)
				System.out.println("Event : " + cur);
			
			for (EventPoint<T, P, S, TF> cur : trenutni)
				switch (cur.tip) {
					case END:
						status.remove(cur.duz);
						break;
					case INTERSECTION:
//						if (arit.equals(statusComparator.getXcur(cur.duz), cur.x))
//							System.out.println("los X ? " +statusComparator.getXcur(cur.duz) + " " + cur.x );
						res.add(geomF.createPoint(cur.x, cur.y));
						status.remove(cur.duz);
						status.remove(cur.drugaDuz);
						break;
				}
			
			statusComparator.setCurY(next.y);
			
			for (EventPoint<T, P, S, TF> cur : trenutni)
				switch (cur.tip) {
					case START:
						System.out.println("\t1");
						dodajIProveriSusede(status, cur.duz, events, statusComparator.curY, geom);
						break;
					case INTERSECTION:
						System.out.println("\t2");
						dodajIProveriSusede(status, cur.duz, events, statusComparator.curY, geom);
						System.out.println("\t3");
						dodajIProveriSusede(status, cur.drugaDuz, events, statusComparator.curY, geom);
						break;
					case END: 
						proveriSusede(status, cur.duz, events, statusComparator.curY, geom);
						break;
				}
		}
		
		return new ArrayList<PF>(res);
	}
	
	private static 
	<T, P extends APoint<T>, S extends ASegment<T, P>, TF, PF extends APoint<TF>, SF extends ASegment<TF, PF>> 
	void dodajIProveriSusede(TreeSet<S> status, S duz, PriorityQueue<EventPoint<T, P, S, TF>> events, TF curY, AnalyticIntersectionGeometry<T, P, S, TF, PF, SF> geom) {
		S higher = status.higher(duz);
		S lower = status.lower(duz);
		status.add(duz);

		System.out.println("\t\t1");
		checkForIntersection(events, curY, geom, duz, higher);
		System.out.println("\t\t2");
		checkForIntersection(events, curY, geom, duz, lower);
	}
	
	private static 
	<T, P extends APoint<T>, S extends ASegment<T, P>, TF, PF extends APoint<TF>, SF extends ASegment<TF, PF>> 
	void proveriSusede(TreeSet<S> status, S duz, PriorityQueue<EventPoint<T, P, S, TF>> events, TF curY, 
			AnalyticIntersectionGeometry<T, P, S, TF, PF, SF> geom) {
		S higher = status.higher(duz);
		S lower = status.lower(duz);
		
		checkForIntersection(events, curY, geom, lower, higher);
	}

	private static 
	<T, P extends APoint<T>, S extends ASegment<T, P>, TF, PF extends APoint<TF>, SF extends ASegment<TF, PF>> 
	void checkForIntersection(PriorityQueue<EventPoint<T, P, S, TF>> events, TF curY,
			AnalyticIntersectionGeometry<T, P, S, TF, PF, SF> geom, S segment1, S segment2)
	{
		Arithmetics<TF> aritF = geom.toDecimalGeometry().arithmetics();
		if (segment1 != null && segment2 != null) {
			PF presek = geom.intersection(segment2, segment1);
			if (presek!=null && aritF.compare(presek.y(),curY)>0) {
				System.out.println("Presek " + segment1 + " " + segment2 + "\t\t" + presek.x() + " " + presek.y());
				events.add(new EventPoint<T, P, S, TF>(presek.y(), presek.x(), EventType.INTERSECTION, segment2, segment1, aritF));
			}
			else System.out.println("Not Presek " + segment1 + " " + segment2);
		}
	}
	
	
	public static void main(String[] args) {
		ArrayList<SegmentG<Double>> ulazG = GenerationUtils.generateArray(6, GenerationUtils.creatorSegmentGDouble);
		System.out.println(ulazG);
		DecimalAnalyticGeometryG<Double> geomG = new DecimalAnalyticGeometryG<Double>(DoubleRelativeArithmetics.getInstance());
		test(ulazG, geomG);
		
		GenerationUtils.reset();
		
		ArrayList<SegmentL> ulazL = GenerationUtils.generateArray(6, GenerationUtils.creatorSegmentL);
		System.out.println(ulazL);		
		test(ulazL, AnalyticGeometries.geometryL);

		GenerationUtils.reset();
		
		ArrayList<SegmentG<BigInteger>> ulazBI = GenerationUtils.generateArray(6, GenerationUtils.creatorSegmentGBigIntFromL(GenerationUtils.creatorSegmentL));
		System.out.println(ulazL);		
		test(ulazBI, AnalyticGeometries.geometryGBigInteger);
	}

	private static
	<T, P extends APoint<T>, S extends ASegment<T, P>, TF, PF extends APoint<TF>, SF extends ASegment<TF, PF>> 
	void test(ArrayList<S> ulaz, AnalyticIntersectionGeometry<T, P, S, TF, PF, SF> geom)
	{
		long timestart = Utils.Time.getNanoTime();
		ArrayList<PF> res = calculateAll(ulaz, geom);
		System.out.println(Utils.Time.secondsPassed(timestart));
		Collections.sort(res, geom.toDecimalGeometry().pointsComparatorXY());
		System.out.println(res.size());
		System.out.println(res);
		
		TreeSet<PF> set = new TreeSet<PF>(geom.toDecimalGeometry().pointsComparatorXY());
		set.addAll(res);
		System.out.println(set.size());
	}
	
}
