
/**
 * https://projecteuler.net/problem=6
 * 
 * @author Stéphan R.
 *
 */
public class SumSquareDifference {
	
	/**
	 * @param n
	 * @return
	 */
	static long sumSquareDifference(int n) {
		// n *(n + 1)(2n + 1) / 6
		long sumOfTheSquares = (n  * ((n + 1) * ((2 * n) + 1))) / 6;
		
		// (n * (n + 1) / 2)^2
		long squareOfTheSum = (long) Math.pow((n * (n + 1)) / 2, 2);
		
		return squareOfTheSum - sumOfTheSquares;
	}

	/**
	 * The sum of the squares of the first ten natural numbers is,
	 * 		1^2 + 2^2 + ... + 10^2 = 385
	 * The square of the sum of the first ten natural numbers is,
	 * 		(1 + 2 + ... + 10)^2 = 55^2 = 3025 
	 * 
	 * Hence the difference between the sum of the squares of the first ten natural numbers 
	 * and the square of the sum is 3025 − 385 = 2640. Find the difference between the sum of the squares 
	 * of the first one hundred natural numbers and the square of the sum.

	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		System.out.println(sumSquareDifference(100));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
