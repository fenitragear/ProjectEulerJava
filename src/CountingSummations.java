import java.math.BigInteger;

/**
 * https://projecteuler.net/problem=76
 * 
 * @author Stéphan R.
 *
 */
public class CountingSummations {
	
	/**
	 * @param n
	 * @return
	 */
	static BigInteger partitionNumber(int n) {
		BigInteger[] ways = new BigInteger[n + 1];
		ways[0] = BigInteger.valueOf(1);
		 
		for (int i = 1; i <= (n - 1); i++) {
			for (int j = i; j <= n; j++) {
				ways[j] = (ways[j] == null) ? BigInteger.valueOf(0) : ways[j];
		        ways[j] = ways[j].add(ways[j - i]);
		    }	    
		}
				
		return ways[n];
	}

	/**
	 * It is possible to write five as a sum in exactly six different ways:
	 * 	4 + 1
	 * 	3 + 2
	 * 	3 + 1 + 1
	 * 	2 + 2 + 1
	 * 	2 + 1 + 1 + 1
	 * 	1 + 1 + 1 + 1 + 1
	 * 
	 * How many different ways can one hundred be written as a sum of at least two 
	 * positive integers?
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		System.out.println(partitionNumber(100));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
