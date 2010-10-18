package arit.impl;

public class DoubleRelativeArithmetics extends DoubleExactArithmetics
{
	@Override
	public boolean equals(Double val1, Double val2)
	{
		double x1 = val1;
		double x2 = val2;
		return Math.abs(x1-x2)<=1e-8*(Math.abs(x1)+Math.abs(x2));
	}

	@Override
	public int compare(Double o1, Double o2)
	{
		double x1 = o1;
		double x2 = o2;
		return (equals(x1, x2))?0:(x1>x2)?1:-1;
	}
	
	private static final DoubleRelativeArithmetics arit = new DoubleRelativeArithmetics();
	
	private DoubleRelativeArithmetics() {}
	
	public static DoubleRelativeArithmetics getInstance() {
		return arit;
	}
}

