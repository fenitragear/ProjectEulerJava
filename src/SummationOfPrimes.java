
/**
 * https://projecteuler.net/problem=10
 * 
 * @author Stéphan R.
 *
 */
public class SummationOfPrimes {

	/**
	 * Sieve Of Eratosthenes Algorithm
	 * 
	 * Initialize an array of boolean default false
	 * Initialize a variable to count the total of prime number
	 * 
	 * 		For 2 ... sqrt(n)
	 * 			If a[i] is false then i is prime
	 * 				Add i to count variable
	 * 				Mark all it's multiple, lesser or equal, than n (k * i ≤ n, k ≥ 2);
	 * 			Otherwise, if i is marked, then it is a composite number.
	 * 
	 * 		Output: all i such that A[i] is true.
	 * 		For sqrt(n) + 1 ... n
	 * 			If a[i] is false then i is prime
	 * 				Add i to count variable
	 * 
	 * @param n
	 * @return
	 */
	static long sumPrime(int n) {
		boolean[] a = new boolean[n + 1];
		int upperBound = (int) Math.sqrt(n);
		long sum = 0;
		
		for(int i = 2; i * i <= n; i++) {				
			if(!a[i]) {
				sum += i;
				
				for(int j = (i * 2); j <= n; j += i) {
					a[j] = true;
				}
			}
		}
		
		for (int m = upperBound + 1; m <= n; m++) {			
			if (!a[m]) {
				sum += m;
			}
		}	            		
	    				
	    return sum;
	}
	
	/**
	 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17. 
	 * Find the sum of all the primes below two million.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		System.out.println(sumPrime(2000000));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
