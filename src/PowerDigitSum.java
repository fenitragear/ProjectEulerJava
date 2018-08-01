import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * https://projecteuler.net/problem=16
 * 
 * @author Stéphan R.
 *
 */
public class PowerDigitSum {
		
	/**
	 * Digit sum formula:
	 * 		For n = 0 ... log10(n)
	 * 			1/b^n * (x mod b^(n + 1) - x mod b^n)
	 * 
	 * Where
	 * 		x is the number to calculate the digit
	 * 		b is the base
	 *  
	 * @param x
	 * 
	 * @return
	 */
	static int sum(int n, int p) {
		BigInteger powerDigit = BigInteger.valueOf(n).pow(p);
		BigDecimal sum = BigDecimal.ZERO;
		long lim = Math.round(Math.log10(powerDigit.doubleValue()));
		
		for(int i = 0; i <= lim; i++) {
			BigDecimal tenPowI = BigDecimal.TEN.pow(i);
			BigDecimal a = BigDecimal.ONE.divide(tenPowI);
			BigInteger b = powerDigit.remainder(BigInteger.TEN.pow(i + 1));
			BigInteger c = powerDigit.remainder(tenPowI.toBigInteger());
			
			sum = sum.add(a.multiply(new BigDecimal(b.subtract(c))));
		}
		
		return sum.intValue();
	}
	
	/**
	 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
	 * What is the sum of the digits of the number 21000?
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		System.out.println(sum(2, 1000));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
