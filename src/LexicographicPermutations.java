import java.util.Arrays;

/**
 * https://projecteuler.net/problem=24
 * TODO
 * 
 * @author Stéphan R.
 *
 */
public class LexicographicPermutations {

	static int factorial(int n) {
		if(n == 0 || n == 1) 
			return 1;
		
		return n * factorial(n - 1);
	}
	
	static String lexicographicOrder(String n) {		
		int lim = factorial(n.length());
		int step = 0;
		
		for(int count = 0, i = 0; count < lim; count++) {
			byte[] b = Arrays.copyOf(n.getBytes(), n.length());
			
			if(i >= (b.length - 1)) {
				i = 0;
				step--;
			}
						
			if(count > 0 && (i + step) < b.length) {
				byte temp = b[i];
				b[i] = b[i + step];
				b[i + step] = temp;
				step++;
			}
			
			i++;
			System.out.println(new String(b));
		}
		
		return null;
	}
	
	/**
	 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. 
	 * If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations 
	 * of 0, 1 and 2 are:
	 * 
	 * 		012   021   102   120   201   210
	 * 
	 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		System.out.println(lexicographicOrder("012"));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
