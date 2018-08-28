
/**
 * https://projecteuler.net/problem=182
 * 
 * @author Stéphan R.
 *
 */
public class RSAEncryption {
	
	static long wheelFactorization(long n) {
		int[] inc = new int[] { 4, 2, 4, 2, 4, 6, 2, 6 };
		int k = 7;
		int i = 1;
		
		while(n % 2 == 0) {
			n /= 2;
		}
		
		while(n % 3 == 0) {
			n /= 3;
		}
		
		while(n % 5 == 0) {
			n /= 5;
		}
				
		while(k * k <= n) {
			if(n % k == 0) {
				n /= k;
			} else {
				k += inc[i];
				i = (i < 8) ? i++ : 1;
			}
		}
		
		return n;
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
				long temp = a;
				a = b;
				b = temp;
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
		long pLargestPrimeFactor = wheelFactorization(p);
		long qLargestPrimeFactor = wheelFactorization(q);		
		long phi = p * q;
		long sumValueOfE = 0;
		
		for(long e = 3; e < phi; e += 2) {
			if(e % 12 == 11) {
				if(e % pLargestPrimeFactor > 1 && e % qLargestPrimeFactor > 1) {
					if(isCoprime(e, phi)) {						
						if(binary((e - 1), phi) == 2) {
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
		double start = System.nanoTime();
	
		//System.out.println(numberOfUnconcealedMessage(991777 - 1, 999983 - 1));
		System.out.println(numberOfUnconcealedMessage(1009 - 1, 3643 - 1));
		
		double end = System.nanoTime();
		System.out.printf("Solution took %f.6ms", ((end - start) / 1000000));
	}
}
