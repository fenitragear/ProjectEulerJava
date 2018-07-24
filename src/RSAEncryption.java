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
	 *  
	 * @param a
	 * @param b
	 * @return
	 */
	static BigInteger gcd(BigInteger a, BigInteger b) {
		if (a.compareTo(BigInteger.valueOf(0)) < 0 || b.compareTo(BigInteger.valueOf(0)) < 0)
			throw new IllegalArgumentException("Negative number");
		
		while (b.compareTo(BigInteger.valueOf(0)) != 0) {
			BigInteger z = a.mod(b);
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
	static Boolean isCoprime(BigInteger a, BigInteger b) {
		if(((a.or(b)).and(BigInteger.valueOf(1))).compareTo(BigInteger.valueOf(0)) == 0)
			return false;
		
		return gcd(a, b).compareTo(BigInteger.valueOf(1)) == 0;
	}
	
	/**
	 * @param p
	 * @param q
	 * 
	 * @return
	 */
	static BigInteger sumOfAllValueE(long p, long q) {
		BigInteger phi = BigInteger.valueOf(p - 1).multiply(BigInteger.valueOf(q - 1));
		BigInteger sumValueOfE = BigInteger.valueOf(0);
		
		for(BigInteger e = BigInteger.valueOf(3); e.compareTo(phi) <= 0; e = e.add(BigInteger.valueOf(3))) {
			// e and phi must be coprime
			if(!isCoprime(e, phi)) {
				// Unconcealed message is at the minimum => (1 + gcd((e - 1), (p - 1)) * (1 + gcd((e - 1), (q - 1)))
				if(BigInteger.valueOf(1).add(gcd(e.subtract(BigInteger.valueOf(1)), BigInteger.valueOf(p - 1)))
						.multiply(BigInteger.valueOf(1).add(gcd(e.subtract(BigInteger.valueOf(1)), BigInteger.valueOf(q - 1))))
						.compareTo(BigInteger.valueOf(9)) == 0) {
					sumValueOfE = sumValueOfE.add(e);
				}
			}
		}
		
		return sumValueOfE;
	}

	public static void main(String[] args) {		
		long start = System.currentTimeMillis();
		
		// 399788195976
		System.out.println(sumOfAllValueE(1009, 3643));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
