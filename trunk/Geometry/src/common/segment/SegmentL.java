package common.segment;

import common.point.PointG;
import common.point.PointL;

import fraction.Fraction;

public class SegmentL extends ASegment<Long, PointL>
{
	public SegmentL(PointL p, PointL q)
	{
		super(p, q);
	}
	
	public SegmentL(long x1, long y1, long x2, long y2)
	{
		super(new PointL(x1, y1), new PointL(x2, y2));
	}
	
	
	public long dx()
	{
		return q.x-p.x;
	}
	
	public long dy()
	{
		return q.y-p.y;
	}
	
	
	public PointG<Fraction> middleOfSegment()
	{
		return new PointG<Fraction>(new Fraction(p1().x()+p2().x(),2), new Fraction(p1().y()+p2().y(),2));
	}



	public SegmentG<Fraction> toFraction()
	{
		return new SegmentG<Fraction>(p.toFraction(), q.toFraction());
	}
}
