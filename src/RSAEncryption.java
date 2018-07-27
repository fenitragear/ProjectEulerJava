import java.math.BigInteger;

/**
 * https://projecteuler.net/problem=182
 * TODO
 * 
 * @author Stéphan R.
 *
 */
public class RSAEncryption {
	
	/**
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
		long u0 = 0;
		long u1 = 1;
		long u2 = 0;
		long u3 = 0;
		
		for(int e = 1; e < (q/2); e++) {
			if(!isCoprime(e, phi)) {
				if((gcd((e -1), (p - 1)) + 1) * (gcd((e - 1), (q - 1)) + 1) == 9) {
					u2 = e;
					System.out.println("U2 = " + u2);
					break;
				}
			}
		}
		
		for(int e = 1; e < (p/2); e++) {
			if(!isCoprime(e, phi)) {
				if((gcd((e -1), (p - 1)) + 1) * (gcd((e - 1), (q - 1)) + 1) == 9) {
					u3 = e;
					System.out.println("U3 = " + u3);
					break;
				}
			}
		}
		
		long u4 = (u2 + u3) % phi;
		long u5 = phi - u4;
		long u6 = phi - u3;
		long u7 = phi - u2;
		long u8 = phi - u1;
		
		System.out.println("U4 = U2 + U3 Mod N = " + u4);
		System.out.println("U5 = N - U4 = " + u5);
		System.out.println("U6 = N - U3 = " + u6);
		System.out.println("U7 = N - U2 = " + u7);
		System.out.println("U8 = N - U1 = " + u8);
		
		System.out.println("phi = " + phi);
		
		System.out.println("U1 + U8 = " + (u1 + u8));
		System.out.println("U2 + U7 = " + (u2 + u7));
		System.out.println("U3 + U6 = " + (u3 + u6));
		System.out.println("U4 + U5 = " + (u4 + u5));
		
		return sumValueOfE;
	}

	public static void main(String[] args) {		
		long start = System.currentTimeMillis();
		
		//438
		System.out.println(sumOfAllValueE(11, 13));
		// 399788195976
		//System.out.println(sumOfAllValueE(1009, 3643));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
