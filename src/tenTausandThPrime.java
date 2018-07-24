
/**
 * https://projecteuler.net/problem=7
 * TODO
 * 
 * @author Stéphan R.
 *
 */
public class tenTausandThPrime {

	/**
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13. 
	 * What is the 10 001st prime number?
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		//System.out.println(trialDivision(Long.parseLong("600851475143")));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}