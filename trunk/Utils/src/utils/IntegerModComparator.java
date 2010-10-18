package utils;
import java.util.Comparator;


public class IntegerModComparator implements Comparator<Integer>
{
	private final int mod;
	
	public IntegerModComparator(int mod) {
		this.mod = mod;
	}

	@Override
	public int compare(Integer o1, Integer o2)
	{
		return o1%mod - o2%mod;
	}

}
