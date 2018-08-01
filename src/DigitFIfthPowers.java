
/**
 * https://projecteuler.net/problem=30
 * 
 * @author Stéphan R.
 *
 */
public class DigitFIfthPowers {

	/**
	 * Define upper bound:
	 * 
	 * Since 95 = 59049, we need at least 5 digits. 595 = 295245, so with 5 digits 
	 * we can make a 6 digit number. 6*95 = 354294. So 355000 seems like a reasonable 
	 * upper bound to use.
	 * 
	 * @param p
	 * @return
	 */
	static int sum(int p) {
		int sumFifth = 0;
		
		for(int i = 2; i < ((p + 1) * Math.pow(9, p)); i++) {
			int sum = 0;
			int m = i;
			
			while(m > 0) {				
				int n = m % 10;	            
	            m /= 10;
	            sum += Math.pow(n, p);
	        }
			
			if(i == sum) {
				sumFifth += i;
			}
		}
		
		return sumFifth;
	}
	
	/**
	 * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
	 * 		1634 = 1^4 + 6^4 + 3^4 + 4^4
	 * 		8208 = 8^4 + 2^4 + 0^4 + 8^4
	 * 		9474 = 9^4 + 4^4 + 7^4 + 4^4
	 * 
	 * As 1 = 14 is not a sum it is not included.
	 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
	 * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		System.out.println(sum(5));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
