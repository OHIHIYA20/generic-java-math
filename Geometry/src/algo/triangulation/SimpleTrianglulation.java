package algo.triangulation;


import java.util.ArrayList;
import java.util.LinkedList;

import arit.Arithmetics;

import common.analytic.AnalyticGeometry;
import common.analytic.AnalyticGeometry.Side;
import common.analytic.longp.AnalyticGeometryL;
import common.point.APoint;
import common.point.PointL;
import common.segment.ASegment;

public class SimpleTrianglulation {

	
	
	private static <T> T getTop(ArrayList<T> niz)
	{
		return niz.get(niz.size()-1);
	}
	
	private static <T> void removeTop(ArrayList<T> niz)
	{
		niz.remove(niz.size()-1);
	}
	
	private static <T> T getBeforeTop(ArrayList<T> niz)
	{
		if (niz.size()>1)
			return niz.get(niz.size()-2);
		return null;
	}
	
	public static <T, P extends APoint<T>, S extends ASegment<T, P>> ArrayList<S> triangulate(LinkedList<P> gore, LinkedList<P> dole, AnalyticGeometry<T, P, S> geom)
	{
		ArrayList<S> res = new ArrayList<S>();
		
		ArrayList<P> gornjiStek = new ArrayList<P>();
		ArrayList<P> donjiStek = new ArrayList<P>();
		gornjiStek.add(gore.removeFirst());
		gornjiStek.add(gore.removeFirst());
		donjiStek.add(dole.removeFirst());
		donjiStek.add(dole.removeFirst());
		
		Arithmetics<T> arit = geom.arithmetics();
		while ((0<gore.size()) || (0<dole.size()))
		{
			 if (arit.compare(getTop(gornjiStek).x(),getTop(donjiStek).x())<0)
			 {
				P pom = gore.removeFirst();
				while ((geom.onWhichSide(getBeforeTop(gornjiStek), getTop(gornjiStek), pom)==Side.LEFT) && (arit.compare(pom.x(),getTop(donjiStek).x())<0))
				{
					gornjiStek.add(pom);
					pom = gore.removeFirst();
				}
			 	if (arit.compare(pom.x(),getTop(donjiStek).x())<0)
			 	{
			 		do {
			 			res.add(napraviPrvaManja(getTop(gornjiStek),pom, geom));
			 			removeTop(gornjiStek);
			 		} while ((getBeforeTop(gornjiStek)!=null) &&  (geom.onWhichSide(getBeforeTop(gornjiStek), getTop(gornjiStek), pom)==Side.RIGHT));
			 		gornjiStek.add(pom);
			 	}
			 	else {
			 		while (getBeforeTop(gornjiStek)!=null)
			 		{
			 			res.add(napraviPrvaManja(getTop(gornjiStek),getTop(donjiStek), geom));
			 			removeTop(gornjiStek);
			 		}
			 		gornjiStek.add(pom);
			 	}
			 }
			 else
			 {
					P pom = dole.removeFirst();
					while ((geom.onWhichSide(getBeforeTop(donjiStek), getTop(donjiStek), pom)==Side.RIGHT) && (arit.compare(pom.x(),getTop(gornjiStek).x())<0))
					{
						donjiStek.add(pom);
						pom = dole.removeFirst();
					}
				 	if (arit.compare(pom.x(),getTop(gornjiStek).x())<0)
				 	{
				 		do {
				 			res.add(napraviPrvaManja(getTop(donjiStek),pom, geom));
				 			removeTop(donjiStek);
				 		} while ((getBeforeTop(donjiStek)!=null) &&  (geom.onWhichSide(getBeforeTop(donjiStek), getTop(donjiStek), pom)==Side.LEFT));
				 		donjiStek.add(pom);
				 	}
				 	else {
				 		while (getBeforeTop(donjiStek)!=null)
				 		{
				 			res.add(napraviPrvaManja(getTop(donjiStek),getTop(gornjiStek), geom));
				 			removeTop(donjiStek);
				 		}
				 		donjiStek.add(pom);
				 	}
				 
			 }
		}
						
		return res;
	}
	
	static <T, P extends APoint<T>, S extends ASegment<T, P>> S napraviPrvaManja(P p1, P p2, AnalyticGeometry<T, P, S> geom) {
		if (geom.comparePointsXY(p1, p2)>0)
		{
			P pom = p1;
			p1 = p2;
			p2 = pom;
		}
		return geom.createSegment(p1, p2);
	}
	
	
	public SimpleTrianglulation()
	{
		LinkedList<PointL> gore = new LinkedList<PointL>();
		LinkedList<PointL> dole = new LinkedList<PointL>();
		
		gore.add(new PointL(0,0));
		gore.add(new PointL(1,5));
		gore.add(new PointL(2,0));
		gore.add(new PointL(3,-2));
		gore.add(new PointL(4,5));
		gore.add(new PointL(10,0));
		
		dole.add(new PointL(0,0));
		dole.add(new PointL(2,-5));
		dole.add(new PointL(4,0));
		dole.add(new PointL(5,-1));
		dole.add(new PointL(6,-3));
		dole.add(new PointL(7,-6));
		dole.add(new PointL(10,0));
		
//		System.out.println(gore);
//		System.out.println(dole);
		
		System.out.println(triangulate(gore, dole, AnalyticGeometryL.getInstance()));
	}
	
	public static void main(String[] args) {
		new SimpleTrianglulation();
	}
	
}
