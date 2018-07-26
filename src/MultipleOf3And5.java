
/**
 * https://projecteuler.net/problem=1
 * 
 * @author Stéphan R.
 *
 */
public class MultipleOf3And5 {
	
	/**
	 * @param n
	 * @return
	 */
	static long sumOfMultipleOf3And5(int n) {
		long sum = 0;
		long p = (n - 1) / 3;
	    sum = ((3 *p * (p + 1)) / 2);

	    p = (n - 1) / 5;
	    sum = sum + ((5 * p * (p + 1)) / 2);

	    p = (n - 1) / 15;
	    sum = sum - ((15 * p * (p + 1)) / 2);
				
		return sum;
	}
		
	/**
	 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. 
	 * The sum of these multiples is 23. Find the sum of all the multiples of 3 or 5 below 1000.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		System.out.println(sumOfMultipleOf3And5(10));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");		
	}
}
