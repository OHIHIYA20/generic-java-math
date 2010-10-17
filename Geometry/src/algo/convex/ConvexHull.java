package algo.convex;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

import utils.generation.GenerationUtils;
import arit.Arithmetics;
import arit.impl.LongArithmetics;

import common.analytic.AnalyticGeometry;
import common.analytic.AnalyticGeometry.Side;
import common.analytic.generic.AnalyticGeometryG;
import common.analytic.longp.AnalyticGeometryL;
import common.point.APoint;
import common.point.PointG;
import common.point.PointL;
import common.segment.ASegment;

public class ConvexHull {

	protected static <T, P extends APoint<T>> ArrayList<P> nadjiParce(P prva, P druga, ArrayList<P> ostale, int iter, AnalyticGeometry<T, P, ? extends ASegment<T,P>> geom)
	{
		Arithmetics<T> arit = geom.arithmetics();

		T maxd = arit.zero();
		P najdalja = null;
		
		for(P tacka : ostale)
		{
			T trd = arit.abs(geom.VP(prva, druga, tacka));
			int cmp = arit.compare(trd, maxd);
			if (cmp>0 || (cmp==0 && geom.comparePointsXY(tacka, najdalja)>0))
			{
				maxd = trd;
				najdalja = tacka;
			}
		}
		if (najdalja == null)
			return new ArrayList<P>(0);
		
		ArrayList<P> prve = new ArrayList<P>(ostale.size());
		ArrayList<P> druge = new ArrayList<P>(ostale.size());
		for(P tacka : ostale)
		{
			if (geom.onWhichSide(prva, najdalja,tacka)==Side.RIGHT)			
				prve.add(tacka);			
			else
			if (geom.onWhichSide(najdalja,druga,tacka)==Side.RIGHT) 
				druge.add(tacka);
		}
		
		prve = nadjiParce(prva, najdalja,prve,iter+1, geom);
		druge = nadjiParce(najdalja,druga, druge,iter+1, geom);
		prve.add(najdalja);
		for(P tacka : druge)
			prve.add(tacka);
		return prve;
	}
	
	public static <T, P extends APoint<T>> ArrayList<P> quickHull(ArrayList<P> tacke, AnalyticGeometry<T, P, ? extends ASegment<T,P>> geom)
	{
		P levo, desno;
		desno = levo = tacke.get(0);
		
		for(P tacka : tacke)
		{
			if (geom.comparePointsXY(levo,tacka)>0) levo = tacka;
			if (geom.comparePointsXY(desno,tacka)<0) desno = tacka;
		}
		
		ArrayList<P> dole = new ArrayList<P>(tacke.size());
		ArrayList<P> gore = new ArrayList<P>(tacke.size());
		
		for(P tacka : tacke)
		{
			Side side = geom.onWhichSide(levo, desno,tacka);
			if (side==Side.RIGHT)
				dole.add(tacka);
			else if (side==Side.LEFT)
				gore.add(tacka);
		}

		dole = nadjiParce(levo, desno, dole, 0, geom);
		gore = nadjiParce(desno, levo, gore, 0, geom);
		
		dole.add(0, levo);
		if (!levo.equals(desno))
			dole.add(desno);
		for(P tacka : gore)
			dole.add(tacka);
		return dole;
	}
	

	public static <T, P extends APoint<T>> ArrayList<P> giftWrapping(ArrayList<P> tacke, final AnalyticGeometry<T, P, ? extends ASegment<T,P>> geom) {
		final Arithmetics<T> arit = geom.arithmetics();
		P pocetna = tacke.get(0);
		for(int i = 1;i<tacke.size();i++)
			if (geom.comparePointsXY(pocetna,tacke.get(i))>0)
				pocetna = tacke.get(i);
		tacke.remove(pocetna);
		final P poc = pocetna;
		Collections.sort(tacke, new Comparator<P>() {
			@Override
			public int compare(P o1, P o2) {
				int cmp = geom.onWhichSide(o1, poc, o2).cmp;
				if (cmp!=0)
					return cmp;
				return arit.compare(geom.distanceSquared(poc, o1), geom.distanceSquared(poc, o2));
			}} );

//		System.out.println(tacke);
		
		ArrayList<P> omotac = new ArrayList<P>();
		omotac.add(pocetna);
		omotac.add(tacke.get(0));
		for(int i=1;i<tacke.size();i++)
		{
			P t = tacke.get(i);
			if (geom.comparePointsXY(t, omotac.get(omotac.size()-1))!=0) {
				while(geom.onWhichSide(omotac.get(omotac.size()-2), omotac.get(omotac.size()-1), t)!=Side.LEFT)
					omotac.remove(omotac.size()-1);
			}
			omotac.add(t);
		}
		return omotac;
	}
	

	public static <T, P extends APoint<T>> boolean checkHull(ArrayList<P> tacke, ArrayList<P> hull, AnalyticGeometry<T, P, ? extends ASegment<T,P>> geom) {
		int n = tacke.size();
		int h = hull.size();
		for(int i = 0;i<n;i++) 
		{
			P t = tacke.get(i);
			int cneg = 0;
			int cpos = 0;
			for(int j = 0;j<h;j++)
			{
				P h1 = hull.get(j);
				P h2 = hull.get((j+1)%h);
				T a = geom.VP(h1,h2,t);
				int cmp = geom.arithmetics().compare(a, geom.arithmetics().zero());
				if (cmp>0)
					cpos++;
				else if (cmp<0)
					cneg++;
			}
			
			if (cpos>0 && cneg>0)
			{
				System.out.println(cpos + " " + cneg);
				System.out.println("tacka " + t + " nije u omotacu, ili omotac nije konveksan");
				System.out.println(hull);
				return false;
			}
		}			
		return true;
	}
	
	
	public ConvexHull(int n) {
		
		Random rand = new Random(991);
		
		ArrayList<PointL> tacke = new ArrayList<PointL>();
		
		for(int i = 0;i<n;i++)
		{
			int r = 700000+ rand.nextInt(700000/n);
			double fi = rand.nextDouble()*2*Math.PI;
			tacke.add(new PointL((int) (r*Math.cos(fi)),(int)(r*Math.sin(fi))));
		}
		
		ArrayList<PointL> hull = quickHull(tacke, AnalyticGeometryL.getInstance());
		System.out.println("omotac ima " + hull.size());
		System.out.println(checkHull(tacke, hull, AnalyticGeometryL.getInstance()));		
	}
	
	
	public static void main(String[] args) {

		
//		System.out.println(quickHull(GenerationUtils.generateArray(10, GenerationUtils.creatorPointL),AnalyticGeometryL.getInstance()));

		int n = 500000;
		int m = 500000;
		
		ArrayList<PointL> array = GenerationUtils.generateArray(n, GenerationUtils.creatorPointLOnQuaziCircle(m));
		ArrayList<PointL> quick = quickHull(new ArrayList<PointL>(array),AnalyticGeometryL.getInstance());
		ArrayList<PointL> gift = giftWrapping(new ArrayList<PointL>(array),AnalyticGeometryL.getInstance());
		
		System.out.println(quick.size());
		System.out.println(gift.size());
		
		System.out.println("---");
		System.out.println(new HashSet<PointL>(quick).size());
		System.out.println(new HashSet<PointL>(gift).size());
		System.out.println("---");
		
		GenerationUtils.reset();
		ArrayList<PointG<Long>> arrayG = GenerationUtils.generateArray(n, GenerationUtils.creatorPointGLongLOnQuaziCircle(m));
		ArrayList<PointG<Long>> quickG = quickHull(new ArrayList<PointG<Long>>(arrayG),new AnalyticGeometryG<Long>(LongArithmetics.getInstance()));
		ArrayList<PointG<Long>> giftG = giftWrapping(new ArrayList<PointG<Long>>(arrayG),new AnalyticGeometryG<Long>(LongArithmetics.getInstance()));

		System.out.println(quickG.size());
		System.out.println(giftG.size());

		
		
//		Scanner in = new Scanner(System.in);
//		System.out.println("broj tacaka:");
//		new ConvexHull(in.nextInt());
	}
	
}
