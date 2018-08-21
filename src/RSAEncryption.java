
/**
 * https://projecteuler.net/problem=182
 * 
 * @author Stéphan R.
 *
 */
public class RSAEncryption {
	
	/**
	 * Euclid Algorithm
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static long gcd(long a, long b) {
		if (a < 0 || b < 0)
			throw new IllegalArgumentException("Negative number");
		
		while (b != 0) {
			long z = a % b;
			a = b;
			b = z;
		}
		
		return a;
	}
	
	/**
	 * For integer e, 1 < e < phi, such that gcd(e, phi) == 1
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static Boolean isCoprime(long a, long b) {
		if(((a|b) & 1) == 0)
			return false;
		
		return gcd(a, b) == 1;
	}
	
	/**
	 * e and phi must be coprime
	 * Unconcealed message is at the minimum => (1 + gcd((e - 1), (p - 1)) * (1 + gcd((e - 1), (q - 1)))
	 * 
	 * @param p
	 * @param q
	 * 
	 * @return
	 */
	static long sumOfAllValueE(long p, long q) {
		long phi = (p - 1) * (q - 1);
		long sumValueOfE = 0;
		
		for(long e = 11; e < phi; e += 12) {
			if(isCoprime(e, phi)) {
				if(gcd((e -1), (p - 1)) + 1 == 3 && gcd((e - 1), (q - 1)) + 1 == 3) {
					sumValueOfE += e;
				}
			}
		}
						
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
		long start = System.currentTimeMillis();
		
		System.out.println(sumOfAllValueE(1009, 3643));
		
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
