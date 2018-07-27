
/**
 * https://projecteuler.net/problem=7
 * 
 * @author Stéphan R.
 *
 */
public class TenTausandFirstPrime {

	private static long[] prime = new long[(int) (Math.pow(10, 4) + 2)];
	
	/**
	 * Sieve Of Eratosthenes Algorithm
	 * 
	 * Initialize an array of boolean default false
	 * Initialize an array of sum
	 * 
	 * 		For 2 ... sqrt(n)
	 * 			If a[i] is true then i is prime
	 * 				Mark to true all it's multiple, lesser or equal, than n (k * i ≤ n, k ≥ 2);
	 * 			Otherwise, if i is marked, then it is a composite number.
	 * 
	 * 		Output: all i such that A[i] is false.
	 * 
	 */
	static void loadAllPrime() {
		boolean arr[] = new boolean[(int) (Math.pow(10, 8) + 1)];
		int index = 0;
		
	    for(int j = 2; j < (int) Math.sqrt(arr.length); j++) {	    	
	    	if(!arr[j]) {
	    		for(int k = j * 2; k < arr.length; k += j) {
		            arr[k] = true;
		        }
		    }
		}
	    
        for(int f = 2; f < arr.length; f++) {        	
        	if(!arr[f]) {
        		if(++index < prime.length) {
        			prime[index] = f;
        		} else {
        			break;
        		}        		
        	}
        }
	}
	
	/**
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13. 
	 * What is the 10001st prime number?
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		loadAllPrime();
		
		System.out.println(prime[10001]);
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}