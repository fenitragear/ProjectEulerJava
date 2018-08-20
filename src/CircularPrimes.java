import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://projecteuler.net/problem=35
 * 
 * @author Stéphan R.
 *
 */
public class CircularPrimes {

	private static int[] primes = new int[(int) (Math.pow(10, 6) + 1)];
	private static boolean[] tab = new boolean[primes.length];
	private static List<Integer> circularPrimes = new ArrayList<Integer>();
		
	static boolean isCircular(char[] arr, int length, int shift) {
		boolean skipRotation = (shift % length == 0) ? true : false;
		boolean isCircular = false;
		shift = (skipRotation) ? 1 : shift % length;

		// When shift modulo by the length of the array is equal to 
        // zero then no rotation is needed
		for(int r = 0; r < length; r++) {
			for(int i = 0; i < length; i++) {
				// Avoid the last xor operation
				if(i != length - 1) {
					int a = Character.getNumericValue(arr[i]);
					int b = Character.getNumericValue(arr[length - 1]);
					
					arr[i] = String.valueOf(b).toCharArray()[0];
					arr[length - 1] = String.valueOf(a).toCharArray()[0];
				}
			}
			
			Integer rotateResult = Integer.parseInt(String.join(" ", Arrays.toString(arr).replace(",", "").replace("[", "").replace("]", "")).replace(" ", ""));
						
			if(rotateResult <= tab.length) {
				if(tab[rotateResult] || rotateResult.toString().length() != length) {
					isCircular = true;
					break;
				}
				
				isCircular = tab[rotateResult];
			}
		}
		
		return isCircular;
	}
	
	static void loadPrimes() {
		int index = 0;
		
		for(int i = 2; i < Math.sqrt(primes.length); i++) {
			if(!tab[i]) {
				for(int j = (i * i); j < primes.length; j += i) {
					tab[j] = true;
				}
			}
		}
		
		for(int f = 2; f < tab.length; f++) {        	
        	if(!tab[f]) {
        		primes[index++] = f;   
        		
        		char[] array = String.valueOf(f).toCharArray();
				
				if(!isCircular(array, array.length, array.length)) {
					circularPrimes.add(f);
				}
        	}
        }
	}
	
	/**
	 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
	 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
	 * How many circular primes are there below one million?
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		loadPrimes();
		
		System.out.println(circularPrimes.size());
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
