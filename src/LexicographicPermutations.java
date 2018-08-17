
/**
 * https://projecteuler.net/problem=24
 * 
 * @author Stéphan R.
 *
 */
public class LexicographicPermutations {
	
	/**
	 * 		1/ Find the largest x such that P[x]<P[x+1].
	 * 		   (If there is no such x, P is the last permutation.)
	 * 		2/ Find the largest y such that P[x]<P[y].
	 * 		3/ Swap P[x] and P[y].
	 * 		4/ Reverse P[x+1 .. n].
	 * 
	 * @param array
	 */
	static void lexicographicOrder(int[] array) {
		// Find longest non-increasing suffix
	    int i = array.length - 1;
	    
	    while (i > 0 && array[i - 1] >= array[i])
	        i--;
	    // Now i is the head index of the suffix
	    
	    // Are we at the last permutation already?
	    if (i <= 0)
	        return;
	    
	    // Let array[i - 1] be the pivot
	    // Find rightmost element that exceeds the pivot
	    int j = array.length - 1;
	    
	    while (array[j] <= array[i - 1])
	        j--;
	    // Now the value array[j] will become the new pivot
	    // Assertion: j >= i
	    
	    // Swap the pivot with j
	    int temp = array[i - 1];
	    array[i - 1] = array[j];
	    array[j] = temp;
	    
	    // Reverse the suffix
	    j = array.length - 1;
	    
	    while (i < j) {
	        temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	        i++;
	        j--;
	    }
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
		int[] array = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		
		for (int i = 0; i < 999999; i++) {
			lexicographicOrder(array);
		}		
		
		String ans = "";
		
		for (int i = 0; i < array.length; i++)
			ans += array[i];
		
		System.out.println(ans);
		
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
