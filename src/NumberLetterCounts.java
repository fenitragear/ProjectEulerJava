import java.util.HashMap;

/**
 * https://projecteuler.net/problem=17
 * TODO
 * 
 * @author Stéphan R.
 *
 */
public class NumberLetterCounts {

	private static final HashMap<Integer, Integer> words = new HashMap<Integer, Integer>() {{
		put(1, "one".length()); put(2, "two".length()); put(3, "three".length()); put(4, "four".length()); put(5, "five".length());
		put(6, "six".length()); put(7, "seven".length()); put(8, "eight".length()); put(9, "nine".length()); put(10, "ten".length()); put(11, "eleven".length());
		put(12, "twelve".length()); put(13, "thirteen".length()); put(14, "fourteen".length()); put(15, "fifteen".length()); put(16, "sixteen".length());
		put(17, "seventeen".length()); put(18, "eighteen".length()); put(19, "nineteen".length()); put(20, "twenty".length()); put(30, "thirty".length());
		put(40, "forty".length()); put(50, "fifty".length()); put(60, "sixty".length()); put(70, "seventy".length()); put(80, "eighty".length());
		put(90, "ninety".length()); put(100, "hundred".length()); put(1000, "tausand".length());
	}};
	
	static int count(int n) {
		int sum = 0;
		int reminder = 0;
		
		if(n < 20 || words.get(n) != null) {
			return words.get(n);
		} else {			
			if(n > 999 && n < 10000) {
				reminder = n % 1000;
				sum += count((n - reminder) / 1000) + count(1000);
			} else if(n > 99 && n < 1000) {
				reminder = n % 100;
				sum += count((n - reminder) / 100) + count(100);
			} else {
				sum += count(n - (n % 10)) + count(n % 10);
			}
		}
		
		return (reminder > 0) ? sum + count(reminder) : sum;
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int sum = 0;
		
		for(int i = 1; i <= 1000; i++) {
			sum += count(i);
		}
		
		// We have “and” occurring 999 times with 3 letters => 999*3 = 2997
		System.out.println(sum + 2997);
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
