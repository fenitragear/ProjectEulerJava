import java.math.BigInteger;

/**
 * https://projecteuler.net/problem=3
 * 
 * @author Stéphan R.
 *
 */
public class LargestPrimeFactor {

	/**
	 * 
	 * @param a
	 * @param b
	 * 
	 * @return
	 */
	static long gcd(long a, long b) {
		if (a < 0 || b < 0)
			throw new IllegalArgumentException("Negative number");
		
		while (b != 0) {
			long z = a % b;
			a = b;
			b = z;
		}
		
		return a;
	}
	
	/**
	 * @param a
	 * @return
	 */
	static BigInteger eulerFractionMethod(long n) {
		long a = (long) Math.abs(Math.sqrt(n));
		long b = (long) Math.abs(Math.sqrt(n - a));
		long c = 0;
		long d = 0;
		
		return null;
	}
	
	/**
	 * The prime factors of 13195 are 5, 7, 13 and 29.
	 * What is the largest prime factor of the number 600851475143 ?
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
