package arit.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import arit.DecimalArithmetics;

public class BigDecimalArithmetics implements DecimalArithmetics<BigDecimal>
{
	private final MathContext mc;

	public BigDecimalArithmetics()
	{
		mc = MathContext.UNLIMITED;
	}

	public BigDecimalArithmetics(MathContext mc)
	{
		this.mc = mc;
	}

	@Override
	public BigDecimal add(BigDecimal val1, BigDecimal val2)
	{
		return val1.add(val2, mc);
	}

	@Override
	public BigDecimal subtract(BigDecimal val1, BigDecimal val2)
	{
		return val1.subtract(val2, mc);
	}
	
	@Override
	public BigDecimal multiply(BigDecimal val1, BigDecimal val2)
	{
		return val1.multiply(val2, mc);
	}
	@Override
	public BigDecimal divide(BigDecimal val1, BigDecimal val2)
	{
		return val1.divide(val2, mc);
	}

	@Override
	public BigDecimal max(BigDecimal val1, BigDecimal val2)
	{
		return val1.max(val2);
	}

	@Override
	public BigDecimal min(BigDecimal val1, BigDecimal val2)
	{
		return val1.min(val2);
	}

	@Override
	public BigDecimal pow(BigDecimal val, int n)
	{
		return val.pow(n, mc);
	}

	@Override
	public BigDecimal abs(BigDecimal val)
	{
		return val.abs();
	}
	
	@Override
	public BigDecimal negate(BigDecimal val)
	{
		return val.negate();
	}



	@Override
	public int compare(BigDecimal o1, BigDecimal o2)
	{
		return o1.compareTo(o2);
	}

	@Override
	public boolean equals(BigDecimal val1, BigDecimal val2)
	{
		return val1.equals(val2);
	}
	
	@Override
	public BigDecimal one()
	{
		return BigDecimal.ONE;
	}

	@Override
	public BigDecimal zero()
	{
		return BigDecimal.ZERO;
	}

	
}
