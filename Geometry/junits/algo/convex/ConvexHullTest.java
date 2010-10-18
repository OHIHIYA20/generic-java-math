package algo.convex;

import java.math.BigInteger;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import utils.AnalyticGeometries;
import utils.Utils;
import utils.generation.GenerationUtils;

import common.analytic.longp.AnalyticGeometryL;
import common.conversions.Convertor;
import common.point.PointG;
import common.point.PointL;

import fraction.Fraction;

public class ConvexHullTest
{

	private void testHulls(ArrayList<PointL> array) {
		ArrayList<PointL> aL = new ArrayList<PointL>(array);
		long ts1 = Utils.Time.getNanoTime();
		ArrayList<PointL> quick = ConvexHull.quickHull(aL, AnalyticGeometries.geometryL);
		double check1 = Utils.Time.secondsPassed(ts1);
		
		aL = new ArrayList<PointL>(array);
		long ts2 = Utils.Time.getNanoTime();
		ArrayList<PointL> gift = ConvexHull.giftWrapping(aL, AnalyticGeometries.geometryL);
		double check2 = Utils.Time.secondsPassed(ts2);
		
		ArrayList<PointG<Long>> aG = convertToGLong(array);
		long ts3 = Utils.Time.getNanoTime();
		ArrayList<PointG<Long>> quickG = ConvexHull.quickHull(aG, AnalyticGeometries.geometryGLong);
		double check3 = Utils.Time.secondsPassed(ts3);
		
		ArrayList<PointG<BigInteger>> aB = convertGBigInt(array);
		long ts4 = Utils.Time.getNanoTime();
		ArrayList<PointG<BigInteger>> quickB = ConvexHull.quickHull(aB, AnalyticGeometries.geometryGBigInteger);
		double check4 = Utils.Time.secondsPassed(ts4);
		
		ArrayList<PointG<Fraction>> aF = convertGFraction(array);
		long ts5 = Utils.Time.getNanoTime();
		ArrayList<PointG<Fraction>> quickF = ConvexHull.quickHull(aF, AnalyticGeometries.geometryFraction);
		double check5 = Utils.Time.secondsPassed(ts5);
		
		
		System.out.println("Quick: " + check1 
				+ "\tGift: " + check2
				+"\tQuickLong: "+ check3
				+"\tQuickBigInt: "+ check4
				+"\tQuickFraction: "+ check5
				);
		Assert.assertEquals(quick, gift);
		Assert.assertEquals(convertToGLong(quick), quickG);
		Assert.assertEquals(convertGBigInt(quick), quickB);
		Assert.assertEquals(convertGFraction(quick), quickF);
	}
	
	private ArrayList<PointG<Long>> convertToGLong(ArrayList<PointL> list) {
		ArrayList<PointG<Long>> res = new ArrayList<PointG<Long>>();
		for(PointL p : list)
			res.add(Convertor.FromL.toPointGLong(p));
		return res;
	}
	
	private ArrayList<PointG<BigInteger>> convertGBigInt(ArrayList<PointL> list) {
		ArrayList<PointG<BigInteger>> res = new ArrayList<PointG<BigInteger>>();
		for(PointL p : list)
			res.add(Convertor.FromL.toPointGBigInteger(p));
		return res;
	}

	private ArrayList<PointG<Fraction>> convertGFraction(ArrayList<PointL> list) {
		ArrayList<PointG<Fraction>> res = new ArrayList<PointG<Fraction>>();
		for(PointL p : list)
			res.add(p.toFraction());
		return res;
	}
	
	@Test
	public void testHull()
	{
		int[] nn = new int[] {100, 10000, 100000, 100000, 100000, 500000};
		int[] mm = new int[] {100, 10000, 100000, 700000,  10000, 500000};
		
		for (int i = 0;i<nn.length;i++) {
			int n = nn[i];
			int m = mm[i];
			System.out.println(n + ", " + m);
			for(int j = 0;j<3;j++) {
				ArrayList<PointL> array = GenerationUtils.generateArray(n, GenerationUtils.creatorPointLOnQuaziCircle(m));
				testHulls(array);
			}
		}
		for (int i = 0;i<nn.length;i++) {
			int n = nn[i];	
			System.out.println(n);
			for(int j = 0;j<3;j++) {
				ArrayList<PointL> array = GenerationUtils.generateArray(n, GenerationUtils.creatorPointL);
				testHulls(array);
			}
		}
	}

	
	@Test
	public void nesto() {
		AnalyticGeometryL geom = AnalyticGeometries.geometryL;
		PointL najdalja = new PointL(-686373,-137448);

		PointL prva = new PointL(-686527,-136677);
		PointL druga = new PointL(-686278,-137922);

		System.out.println(geom.VP(prva, najdalja, druga));
		System.out.println(geom.VP(prva, druga, najdalja));
		System.out.println(geom.onWhichSide(prva, druga, najdalja));
		
		PointL ostala1 = new PointL(-686467,-136978);
		PointL ostala2 = new PointL(-686362,-137503);

		System.out.println(geom.VP(prva, ostala1, druga));
		System.out.println(geom.VP(prva, ostala2, druga));
		

		System.out.println(geom.VP(najdalja, ostala2, ostala1));
		
		PointL p1 = new PointL(-1179,-699999);	
		PointL p2 = new PointL(692,-699999);
		PointL p3 = new PointL(1181,-699999);
		
		System.out.println(geom.VP(p1, p2, p3));
		System.out.println(geom.comparePointsXY(p1, p2));
		System.out.println(geom.comparePointsXY(p3, p2));
		System.out.println(geom.comparePointsXY(p1, p3));

		
//		(-686373,-137448), (-686403,-137298), (-686385,-137388), (-686372,-137453), (-686380,-137413), (-686390,-137363), (-686418,-137223), (-686402,-137303), (-686362,-137503), (-686415,-137238), (-686374,-137443), (-686467,-136978)

	}
}
