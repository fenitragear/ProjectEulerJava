
/**
 * https://projecteuler.net/problem=4
 * 
 * @author Stéphan R.
 *
 */
public class LargestPalindromeProduct {
	
	/**
	 * Calculating reverse of product
	 * to check whether it is palindrome or not
	 * 
	 * @param n
	 * @return
	 */
	static boolean isPalindrome(int n) {
		int number = n;
        int reverse = 0;

        while (number != 0) {
            reverse = reverse * 10 + number % 10;
            number /= 10;
        }
        
        return (n == reverse);
	}
	
	/**
	 * @param k
	 * @return
	 */
	static int find(int k) {
		int maxPalindrome = 0;
		int upperBound = 0;
	     
        // Loop to calculate upper bound (largest number of n-digit)
        for (int i = 1; i <= k; upperBound *= 10, upperBound += 9, i++);
     
        // largest number of n-1 digit. One plus this number 
        // is lower limit which is product of two numbers.
        int lowerBound = 1 + upperBound / 10;
		
        for(int i = upperBound; i >= lowerBound; i--) {
        	for(int j = i; j >= lowerBound; j--) {
        		int product = i * j;
        		
        		if (product < maxPalindrome)
                    break;
        		                     
                // update new product if exist and if
                // greater than previous one
                if (isPalindrome(product) && product > maxPalindrome)
                	maxPalindrome = product;
        	}
        	
        }
		
		return maxPalindrome;
	}

	/**
	 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
	 * Find the largest palindrome made from the product of two 3-digit numbers.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.nanoTime();
		
		System.out.println(find(3));
		
		long end = System.nanoTime();
		System.out.println("Solution took " + ((end - start) / 1000000) + "ms");
	}
}