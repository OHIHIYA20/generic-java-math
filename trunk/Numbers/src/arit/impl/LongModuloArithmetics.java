package arit.impl;


public class LongModuloArithmetics extends LongArithmetics
{
	private final long mod;
	private final Long MOD;

	public LongModuloArithmetics(long mod)
	{
		this.mod = mod;
		this.MOD = mod;
	}

	long shortMod(long val) {
		return val >= mod ? (val - mod) : val;
	}
	
	long fullSafeMod(long val) {
		return shortMod(val% mod + mod);
	}
	
	@Override
	public Long add(Long val1, Long val2)
	{
		return shortMod(val1 + val2);
	}

	@Override
	public Long divide(Long val1, Long val2)
	{
		return multiply(val1, modInverse(val2, MOD));
	}

	@Override
	public Long multiply(Long val1, Long val2)
	{
		return (val1*val2)%mod;
	}

	@Override
	public Long negate(Long val)
	{
		return shortMod(mod - val);
	}

	@Override
	public Long pow(Long val, int n)
	{
		return modPow(val, (long)n, MOD);
	}

	@Override
	public Long subtract(Long val1, Long val2)
	{
		return shortMod(mod + val1 - val2);
	}

}
