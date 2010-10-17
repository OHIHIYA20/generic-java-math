package algo.closest;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

import arit.Arithmetics;

import common.analytic.AnalyticGeometry;
import common.analytic.longp.AnalyticGeometryL;
import common.point.APoint;
import common.point.PointL;
import common.segment.ASegment;


public class MinDistance {

	
	public static <T, P extends APoint<T>> T calculateSquareMin(ArrayList<P> a, AnalyticGeometry<T, P, ? extends ASegment<T,P>> geom) {
		Collections.sort(a, geom.pointsComparatorXY());
		
		TreeSet<P> ts = new TreeSet<P>(geom.pointsComparatorYX());
		
		int j = 0;
		ts.add(a.get(0));
		ts.add(a.get(1));
		T minD = geom.distanceSquared(a.get(0),a.get(1));
		
		Arithmetics<T> arit = geom.arithmetics();
		for(int i = 2;i<a.size();i++)
		{
			while (arit.compare(geom.square(arit.subtract(a.get(i).x(),a.get(j).x())),minD) > 0)
			{
				ts.remove(a.get(j));
				j++;
			}
			
			ts.add(a.get(i));
			P pom = ts.higher(a.get(i));
			if (pom!=null)
			{
				minD = arit.min(minD, geom.distanceSquared(pom, a.get(i)));
				pom = ts.higher(pom);
				if (pom!=null)
					minD = arit.min(minD, geom.distanceSquared(pom, a.get(i)));
			}
			pom = ts.lower(a.get(i));
			if (pom!=null)
			{
				minD = arit.min(minD, geom.distanceSquared(pom, a.get(i)));
				pom = ts.lower(pom);
				if (pom!=null)
					minD = arit.min(minD, geom.distanceSquared(pom,a.get(i)));
			}
		}
		
		return minD;
	}
	
	public MinDistance(int n) {
		Random rand = new Random(100);
		ArrayList<PointL> pom = new ArrayList<PointL>();
		for(int i = 0;i<n;i++)
			pom.add(new PointL(rand.nextInt()/65536,rand.nextInt()/65536));		
		System.out.println(new TreeSet<PointL>(pom).toArray().length);
		System.out.println(calculateSquareMin(pom, AnalyticGeometryL.getInstance()));
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		new MinDistance(in.nextInt());
	}
	
}
