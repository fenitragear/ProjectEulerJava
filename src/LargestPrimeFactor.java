/**
 * https://projecteuler.net/problem=3
 * 
 * @author Stéphan R.
 *
 */
public class LargestPrimeFactor {
	
	static long trialDivision(long n) {
		long f = 2;
		
		while(n > 1) {
			if(n % f == 0) {
				n /= f;
			} else {
				f++;
			}
		}
		
		return f;
	}
	
	/**
	 * The prime factors of 13195 are 5, 7, 13 and 29.
	 * What is the largest prime factor of the number 600851475143 ?
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		System.out.println(trialDivision(Long.parseLong("600851475143")));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}

