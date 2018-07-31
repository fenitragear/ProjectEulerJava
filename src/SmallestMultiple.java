
/**
 * https://projecteuler.net/problem=5
 * 
 * @author Stéphan R.
 *
 */
public class SmallestMultiple {
	
		
	/**
	 * @param a
	 * @param b
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
	 * Least Common Multiple: ab/gcd(a, b)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static long lcm(long a, long b) {
		return (a * b) / gcd(a, b);
	}
	
	/**
	 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder. 
	 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long lcm = lcm(19, 20);
		
		for(int i = 18; i > 0; i--) {
			lcm = lcm(i, lcm);
		}
		
		System.out.println(lcm);
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
