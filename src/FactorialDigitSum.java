import java.math.BigInteger;

/**
 * https://projecteuler.net/problem=20
 * 
 * @author Stéphan R.
 *
 */
public class FactorialDigitSum {

	/**
	 * Digit sum formula:
	 * 		For n = 0 ... log10(n)
	 * 			1/b^n * (x mod b^(n + 1) - x mod b^n)
	 * 
	 * Where
	 * 		x is the number to calculate the digit
	 * 		b is the base
	 *  
	 * @param n
	 * @return
	 */
	static long sum(int n) {
		BigInteger factorial = factorial(n);
		BigInteger sum = BigInteger.valueOf(0);
		
		for(int j = 0; j < Math.log10(factorial.longValue()); j++) {			
			sum = sum.add(new BigInteger(String.valueOf(1 / Math.pow(10, j))).multiply(
								factorial.mod(
										new BigInteger(String.valueOf(Math.pow(10, j + 1)))
								).subtract(
										factorial.mod(
												new BigInteger(String.valueOf(Math.pow(10, j)))
										)
									)
								)
					);
		}
		
		System.out.println(String.format("factorial of %d! = %d", n, factorial));
		
		return sum.longValue();
	}
	
	/**
	 * Factorial Prime Factorization formula:
	 * 		n! = product(s_i^r_i) = 2^r_1 * 3^r_2 * 5^r_3 * ... * p
	 * 
	 * Where:
	 * 		p denote the largest prime
	 * 		r denote the power of each prime number
	 * 
	 * @param n
	 * @return
	 */
	static BigInteger factorial(int n) {
		int[][] primeFactorizations = getPrimeFactorization(n);
		BigInteger factorial = BigInteger.valueOf(1);
		
		for(int i = 0; i < primeFactorizations.length; i++) {
			int s = primeFactorizations[i][0];
			int r = primeFactorizations[i][1];
			
			factorial = factorial.multiply(
						BigInteger.valueOf((long) Math.pow(s, r))
					);
		}
		
		return factorial;
	}
	
	/**
	 * Sieve Of Eratosthenes
	 * 
	 * Compute r:
	 * 		for each prime number denoted by x
	 * 			val1 = the number that x can divide between 1 and n
	 * 				while val1 is greater than 1
	 * 					find the number that x can divide between 1 and val1
	 * 
	 * @param n
	 * 
	 * @return tab[s][r] - where s is the prime factorization and r the power
	 */
	static int[][] getPrimeFactorization(int n) {
		int[][] primes = new int[(n - 1) / 2][2];
		boolean[] tab = new boolean[n + 1];
		int index = 0;
		
		for(int i = 2; i < Math.sqrt(tab.length); i++) {
			if(!tab[i]) {
				for(int j = (i * i); j < tab.length; j += i) {
					tab[j] = true;
				}
			}
		}
		
		for(int i = 2; i < tab.length; i++) {
			if(!tab[i]) {
				int power = 1;
				
				if(index < (primes.length - 1)) {
					int r = n;
					power = 0;
					
					while(r > 1) {
						int count = 0;
						
						for(int x = i; x <= r; x += i) {
							count++;
						}
						
						r = count++;
						power += r;
					}
				}				
				
				primes[index][0] = i;
				primes[index++][1] = power;
			}
		}
		
		return primes;
	}
	
	/**
	 * n! means n × (n − 1) × ... × 3 × 2 × 1
	 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800, 
	 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
	 * 
	 * Find the sum of the digits in the number 100!
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		System.out.println(sum(10));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
