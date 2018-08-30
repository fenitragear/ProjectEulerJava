import java.util.Arrays;

/**
 * https://projecteuler.net/problem=182
 * 
 * @author Stéphan R.
 *
 */
public class RSAEncryption {
	
	static long trialDivision2(long n) {		
		while((n & 1) == 0) {
			n /= 2;
		}

		if(n == 1)
			return 2;
		
		int f = 3;

		while((f * f) <= n) {
			if(n % f == 0) {
				n /= f;
			} else {
				f += 2;
			}
		}
		
		return (n > 2) ? n : f;
	}
	
	static int gcd(int a, int b) {
	    while (a * b != 0) {
	        if (a >= b) {
	        	a %= b;
	        } else {
	        	b %= a;
	        }
	    }
	    
	    return a + b;
	}
		
	/**
	 * e and phi must be coprime
	 * Unconcealed message is at the minimum => (1 + gcd((e - 1), (p - 1)) * (1 + gcd((e - 1), (q - 1))) == 9
	 * 
	 * @param p -> p - 1
	 * @param q -> q - 1
	 * 
	 * @return
	 */
	static long numberOfUnconcealedMessage(long p, long q) {
		long pLargestPrimeFactor = trialDivision2(p);
		long qLargestPrimeFactor = trialDivision2(q);		
		Long phi = p * q;
		long sumValueOfE = 0;
		int length = phi.intValue();
		boolean[] unconceleadMessage = new boolean[length + 1];
		int[] gcd = new int[length + 1];
		
		Arrays.fill(unconceleadMessage, Boolean.TRUE);
		Arrays.fill(gcd, -1);
				
		long start = System.nanoTime();
		for(int e = 1; e < length; e += 2) {
			int previousE = e - 1;
			
			if(unconceleadMessage[e]) {
				if(e % pLargestPrimeFactor <= 1 || e % qLargestPrimeFactor <= 1) {
					unconceleadMessage[e] = false;
				} else {
					gcd[e] = gcd(e, length);
															
					if(gcd[e] != 1) {
						unconceleadMessage[e] = false;
					} else {
						if(gcd[previousE] == -1) {
							gcd[previousE] = gcd(previousE, length);
						}
					}
				}
			}
			
			// Remove all even number
			unconceleadMessage[previousE] = false;
		}
		
		long end = System.nanoTime();
		System.out.printf("Loop I took %dms%n", ((end - start) / 1000000));
		
		start = System.nanoTime();
		
		for(int e = 1; e < length; e++) {
			if(unconceleadMessage[e]) {
				if(gcd[e - 1] == 2) {
					sumValueOfE += e;
				}
			}
		}
		
		end = System.nanoTime();
		System.out.printf("Loop II took %dms%n", ((end - start) / 1000000));
		
		return sumValueOfE;
	}

	/**
	 * The RSA encryption is based on the following procedure:
	 * 
	 * Generate two distinct primes p and q.
	 * Compute n = pq and φ = (p-1)(q-1).
	 * Find an integer e, 1 < e < φ, such that gcd(e,φ) = 1.
	 * 
	 * A message in this system is a number in the interval [0,n-1].
	 * A text to be encrypted is then somehow converted to messages (numbers in the interval [0,n-1]).
	 * To encrypt the text, for each message, m, c = m^e mod n is calculated.
	 * 
	 * To decrypt the text, the following procedure is needed: calculate d such that ed = 1 mod φ, then for each encrypted message, c, 
	 * calculate m = c^d mod n.
	 * There exist values of e and m such that me mod n = m.
	 * We call messages m for which me mod n = m unconcealed messages.
	 * 
	 * An issue when choosing e is that there should not be too many unconcealed messages.
	 * For instance, let p = 19 and q = 37.
	 * Then n = 19 * 37 = 703 and φ = 18 * 36 = 648.
	 * If we choose e = 181, then, although gcd(181,648) = 1 it turns out that all possible messages
	 * m (0 ≤ m ≤ n-1) are unconcealed when calculating m^e mod n.
	 * For any valid choice of e there exist some unconcealed messages.
	 * It's important that the number of unconcealed messages is at a minimum.
	 * 
	 * Choose p = 1009 and q = 3643.
	 * Find the sum of all values of e, 1 < e < φ(1009,3643) and gcd(e,φ) = 1, so that the number of unconcealed messages for this value of 
	 * e is at a minimum
	 * 
	 * @param args
	 */
	public static void main(String[] args) {		
		long start = System.nanoTime();
	
		//System.out.println(numberOfUnconcealedMessage(991777 - 1, 999983 - 1));
		System.out.println(numberOfUnconcealedMessage(1009 - 1, 3643 - 1));
		//System.out.println(numberOfUnconcealedMessage(11 - 1, 13 - 1));
		
		long end = System.nanoTime();
		System.out.printf("Solution took %dms", ((end - start) / 1000000));
	}
}
