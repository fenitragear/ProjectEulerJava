
/**
 * https://projecteuler.net/problem=182
 * 
 * @author Stéphan R.
 *
 */
public class RSAEncryption {
	
	static long trialDivision(long n) {
		while(n % 2 == 0) {
			n /= 2;
		}

		if(n == 1)
			return 2;
		
		long f = 3;

		while((f * f) <= n) {
			if(n % f == 0) {
				n /= f;
			} else {
				f += 2;
			}
		}

		return (n > 2) ? n : f;
	}
	
	static long binary(long a, long b) {
		int shift;
		
		if(a == 0) {
			return b;
		}
		
		if(b == 0) {
			return a;
		}
		
		for(shift = 0; ((a | b) & 1) == 0; ++shift) {
			a >>= 1;
			b >>= 1;
		}
		
		while((a & 1) == 0) {
			a >>= 1;
		}
		
		do {
			while((b & 1) == 0) {
				b >>= 1;
			}
			
			if(a > b) {
				a ^= b;
				b ^= a;
				a ^= b;
			}
			
			b = b - a;
		} while(b != 0);
		
		return a << shift;
	}
	
	/**
	 * For integer e, 1 < e < phi, such that gcd(e, phi) == 1
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static Boolean isCoprime(long a, long b) {
		if(((a | b) & 1) == 0)
			return false;
		
		return binary(a, b) == 1;
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
		long pLargestPrimeFactor = trialDivision(p);
		long qLargestPrimeFactor = trialDivision(q);
		long phi = p * q;
		long sumValueOfE = 0;
		
		for(long e = 3; e < phi; e += 2) {
			if(e % 12 == 11) {
				if(e % pLargestPrimeFactor > 1 && e % qLargestPrimeFactor > 1) {
					if(isCoprime(e, phi)) {
						long E = (e - 1);
						
						if(binary(E, phi) == 2) {
							sumValueOfE += e;
						}
					}	
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
	
		System.out.println(numberOfUnconcealedMessage(1009 - 1, 3643 - 1));
		
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
