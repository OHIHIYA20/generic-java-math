package algo.intersections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;

import utils.Utils;
import utils.generation.GenerationUtils;
import arit.Arithmetics;
import arit.impl.DoubleRelativeArithmetics;

import common.analytic.AnalyticGeometry;
import common.analytic.DecimalAnalyticGeometry;
import common.analytic.generic.DecimalAnalyticGeometryG;
import common.point.APoint;
import common.point.PointG;
import common.segment.ASegment;
import common.segment.SegmentG;


public class AllSegmentIntersections
{

	enum TipDogadjaja {
		START,
		INTERSECTION,
		END
	}
	
	static class EventPoint<T, P extends APoint<T>, S extends ASegment<T, P>> implements Comparable<EventPoint<T, P, S>> {
		public final T y;
		public final T x;
		public final TipDogadjaja tip;
		
		public final S duz;
		public final S drugaDuz;
		private final AnalyticGeometry<T, P, S> geom;
		
		public EventPoint(T y, T x, TipDogadjaja tip, S duz, S drugaDuz, AnalyticGeometry<T, P, S> geom) {
			this.y = y;
			this.x = x;
			this.tip = tip;
			this.duz = duz;
			this.drugaDuz = drugaDuz;
			this.geom = geom;
		}

		@Override
		public boolean equals(Object obj) {
			EventPoint<T, P, S> d = (EventPoint<T, P, S>) obj;
			return geom.arithmetics().equals(y, d.y) && tip.equals(d.tip) && duz.equals(d.duz) && ((drugaDuz==null)?(d.drugaDuz==null):(drugaDuz.equals(d.drugaDuz)));
		}
		
		@Override
		public int compareTo(EventPoint<T, P, S> o) {
			int cmp = geom.arithmetics().compare(y, o.y);
			return (cmp!=0)?cmp:tip.compareTo(o.tip);				
		}
	}
	
	static class StatusComparator<T, P extends APoint<T>, S extends ASegment<T, P>> implements Comparator<S> {
		private final AnalyticGeometry<T, P, S> geom;
		private final HashMap<S, T> cacheCurY = new HashMap<S, T>();
		private final HashMap<S, T> cacheCurYPlus1 = new HashMap<S, T>(4);
		private T curY;
		private T curYplus1;
		
		public void setCurY(T curY) {
			this.curY = curY;
			this.curYplus1 = geom.arithmetics().add(curY, geom.arithmetics().one());
			cacheCurY.clear();
			cacheCurYPlus1.clear();
		}
		
		public T getCurY()
		{
			return curY;
		}

		
		public StatusComparator(AnalyticGeometry<T, P, S> geom)
		{
			this.geom = geom;
		}

		@Override
		public int compare(S o1, S o2) {
			T x1 = getXcur(o1);
			T x2 = getXcur(o2);
			int cmp = geom.arithmetics().compare(x1, x2);
			if (cmp!=0) return cmp;
			x1 = getXcurPlus1(o1);
			x2 = getXcurPlus1(o2);
			return geom.arithmetics().compare(x1, x2);
		}	
		
		
		T getXcur(S duz) {
			return getX(duz, curY, cacheCurY);
		}
		T getXcurPlus1(S duz) {
			return getX(duz, curYplus1, cacheCurYPlus1);
		}

		
		T getX(S duz, T curY, HashMap<S, T> cache)
		{
			T cached = cache.get(duz);
			if (cached != null) {
				return cached;
			}
			
			Arithmetics<T> arit = geom.arithmetics();
			T rez = arit.add(duz.p1().x(), arit.divide(arit.multiply(geom.dx(duz), arit.subtract(curY, duz.p1().y())), geom.dy(duz)));
			cache.put(duz, rez);
			return rez;
		}
		
	}
	

	
	
	public static <T, P extends APoint<T>, S extends ASegment<T, P>> ArrayList<P> calculateAll(ArrayList<S> duzi, DecimalAnalyticGeometry<T, P, S> geom) {
		PriorityQueue<EventPoint<T, P, S>> events = new PriorityQueue<EventPoint<T, P, S>>();
		TreeSet<P> res = new TreeSet<P>(geom.pointsComparatorXY());
		
		Arithmetics<T> arit = geom.arithmetics();
		
		T minY = null;
		
		for(S d : duzi) {
			T curMin = arit.min(d.p1().y(),d.p2().y());
			if (minY==null || arit.compare(minY, curMin)>0)
				minY = curMin;
			events.add(new EventPoint<T, P, S>(arit.min(d.p1().y(),d.p2().y()),null, TipDogadjaja.START, d, null, geom));
			events.add(new EventPoint<T, P, S>(arit.max(d.p1().y(),d.p2().y()),null, TipDogadjaja.END, d, null, geom));
		}
		StatusComparator<T, P, S> statusComparator = new StatusComparator<T, P, S>(geom);
		
		TreeSet<S> status = new TreeSet<S>(statusComparator);
		
		statusComparator.setCurY(arit.subtract(minY, geom.two()));
		
		while (events.size()>0)
		{
			EventPoint<T, P, S> next = events.poll();
			ArrayList<EventPoint<T, P, S>> trenutni = new ArrayList<EventPoint<T, P, S>>();
			trenutni.add(next);
			while (events.size()>0 && arit.equals(next.y, events.peek().y))
				trenutni.add(events.poll());

			for (EventPoint<T, P, S> cur : trenutni)
				switch (cur.tip) {
					case END:
						status.remove(cur.duz);
						break;
					case INTERSECTION:
//						if (arit.equals(statusComparator.getXcur(cur.duz), cur.x))
//							System.out.println("los X ? " +statusComparator.getXcur(cur.duz) + " " + cur.x );
						res.add(geom.createPoint(cur.x, cur.y));
						status.remove(cur.duz);
						status.remove(cur.drugaDuz);
						break;
				}
			
			statusComparator.setCurY(next.y);
			
			for (EventPoint<T, P, S> cur : trenutni)
				switch (cur.tip) {
					case START:
						dodajIProveriSusede(status, cur.duz, events, statusComparator.curY, geom);
						break;
					case INTERSECTION:
						dodajIProveriSusede(status, cur.duz, events, statusComparator.curY, geom);
						dodajIProveriSusede(status, cur.drugaDuz, events, statusComparator.curY, geom);
						break;
				}
		}
		
		return new ArrayList<P>(res);
	}
	
	private static <T, P extends APoint<T>, S extends ASegment<T, P>> void dodajIProveriSusede(TreeSet<S> status, S duz, PriorityQueue<EventPoint<T, P, S>> events, T curY, DecimalAnalyticGeometry<T, P, S> geom) {
		S higher = status.higher(duz);
		S lower = status.lower(duz);
		status.add(duz);

		Arithmetics<T> arit = geom.arithmetics();

		if (higher!=null) {
			P presek = geom.intersection(duz, higher);
			if (presek!=null && arit.compare(presek.y(),curY)>0)
				events.add(new EventPoint<T, P, S>(presek.y(), presek.x(), TipDogadjaja.INTERSECTION, duz, higher, geom));				
		}
		
		if (lower!=null) {
			P presek = geom.intersection(duz, lower);
			if (presek!=null && arit.compare(presek.y(),curY)>0)
				events.add(new EventPoint<T, P, S>(presek.y(), presek.x(), TipDogadjaja.INTERSECTION, duz, lower, geom));				
		}
	}
	
	
	public static void main(String[] args) {
		ArrayList<SegmentG<Double>> ulaz = GenerationUtils.generateArray(100, GenerationUtils.creatorSegmentGDouble);
		System.out.println(ulaz);
		DecimalAnalyticGeometryG<Double> geom = new DecimalAnalyticGeometryG<Double>(DoubleRelativeArithmetics.getInstance());
		
		long timestart = Utils.Time.getNanoTime();
		ArrayList<PointG<Double>> res = calculateAll(ulaz, geom);
		System.out.println(Utils.Time.secondsPassed(timestart));
		Collections.sort(res, geom.pointsComparatorXY());
		System.out.println(res.size());
		System.out.println(res);
		
		TreeSet<PointG<Double>> set = new TreeSet<PointG<Double>>(geom.pointsComparatorXY());
		set.addAll(res);
		System.out.println(set.size());
	}
	
}
