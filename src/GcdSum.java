
/**
 * https://projecteuler.net/problem=625
 * TODO
 * 
 * @author Stéphan R.
 *
 */
public class GcdSum {

	/**
	 * <pre>{@code
	 * gcd(a, b) = phi(a * b) == phi(a) * phi(b)
	 * 
	 * Where 
	 * 	phi(n) is the Euler's totient function
	 * }</pre>
	 * 
	 * @param a
	 * @param b
	 * 
	 * @return
	 */
	static long gcd(long a, long b) {
		return phi(a * b) / (phi(a) * phi(b));
	}
	
	/**
	 * Euler's totient function
	 * 
	 * Consider all prime factors of n and for every prime 
	 * factor p, multiply result with (1 - 1/p)
	 * 
	 * Check if p is a prime factor. If yes, then update n and result
	 * If n has a prime factor greater than sqrt(n)
	 * (There can be at-most one such prime factor)
	 * 
	 * @param n
	 * @return
	 */
	static long phi(long n) {
		// Initialize result as n
        float result = n;
 
        for (long p = 2; p * p <= n; ++p) {
            if (n % p == 0) {
                while (n % p == 0) {
                	 n /= p;
                }                   
                
                result *= (1.0 - (1.0 / (float) p));
            }
        }
        
        if (n > 1)
            result *= (1.0 - (1.0 / (float) n));
 
        return (int) result;
	}
	
	static long gcdSum(int n) {
		long sum = 0;
		
		for(int j = 1; j <= n; j++) {
			for(int i  = 1; i <= j; i++) {
				sum += gcd(i, j);
			}
		}
		
		return sum;
	}
	
	/**
	 * G(N) = ∑N<-j=1 ∑j<-i=1 gcd(i,j) . 
	 * You are given: G(10) = 122.
	 * Find G(10^11). Give your answer modulo 998244353

	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		System.out.println(gcdSum((int) Math.pow(10, 1)));
		System.out.println(phi(10));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}