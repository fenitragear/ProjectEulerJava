import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * https://projecteuler.net/problem=20
 * 
 * @author Stéphan R.
 *
 */
public class FactorialDigitSum {
	
	private static final double LOG10 = Math.log(10.0);

	/**
	 * Computes the natural logarithm of a BigInteger. Works for really big
	 * integers (practically unlimited)
	 * 
	 * @param val Argument, positive integer
	 * @return Natural logarithm, as in <tt>Math.log()</tt>
	 */
	public static double logBigInteger(BigInteger val) {
	    int blex = val.bitLength() - 1022;
	    
	    if (blex > 0)
	        val = val.shiftRight(blex);
	    
	    double res = Math.log10(val.doubleValue());
	    
	    return blex > 0 ? res + blex * LOG10 : res;
	}
	
	/**
	 * Digit sum formula:
	 * 		For n = 0 ... log10(n)
	 * 			1/b^n * (x mod b^(n + 1) - x mod b^n)
	 * 
	 * Where
	 * 		x is the number to calculate the digit
	 * 		b is the base
	 *  
	 * @param x
	 * 
	 * @return
	 */
	static int sum(int x) {
		BigInteger factorial = factorial(x);
		BigDecimal sum = BigDecimal.valueOf(0);
		long lim = Math.round(logBigInteger(factorial));
		
		for(int n = 0; n < lim; n++) {		
			BigDecimal a = BigDecimal.valueOf(1 / Math.pow(10, n));
			BigInteger b = factorial.mod(BigDecimal.valueOf(Math.pow(10, n + 1)).toBigInteger());
			BigInteger c = factorial.mod(BigDecimal.valueOf(Math.pow(10, n)).toBigInteger());
			
			sum = sum.add(a.multiply(new BigDecimal(b.subtract(c))).setScale(1, BigDecimal.ROUND_CEILING));
		}
				
		return sum.intValue();
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
	 * Factorial Prime Factorization formula:
	 * 		n! = product(s_i^r_i) = 2^r_1 * 3^r_2 * 5^r_3 * ... * p
	 * 
	 * Where:
	 * 		p denote the largest prime
	 * 		r denote the power of each prime number
	 * 
	 * @param n
	 * 
	 * @return
	 */
	static BigInteger factorial(int n) {
		BigInteger factorial = BigInteger.valueOf(1);
		boolean[] tab = new boolean[n + 1];
		
		for(int i = 2; i < Math.sqrt(tab.length); i++) {
			if(!tab[i]) {
				for(int j = (i * i); j < tab.length; j += i) {
					tab[j] = true;
				}
			}
		}
		
		for(int i = 2; i < tab.length; i++) {
			if(!tab[i]) {
				int r = n;
				int power = 0;
				
				while(r > 1) {
					int count = 0;
					
					for(int x = i; x <= r; x += i) {
						count++;
					}
					
					r = count++;
					power += r;
				}		
								
				factorial = factorial.multiply(BigDecimal.valueOf(i).pow(power).toBigInteger());
			}
		}
		
		return factorial;
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
		
		System.out.println(sum(100));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
