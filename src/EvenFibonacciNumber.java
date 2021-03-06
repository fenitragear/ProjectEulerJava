import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * https://projecteuler.net/problem=2
 * 
 * @author Stéphan R.
 *
 */
public class EvenFibonacciNumber {

	/**
	 * @param n
	 * @return
	 */
	static BigInteger sumEvenNumber(BigInteger n) {
		// Store even fibonacci number less than 100
		List<BigInteger> memoization = new LinkedList<>();
		memoization.add(0, BigInteger.valueOf(2));
		memoization.add(1, BigInteger.valueOf(8));
				
		BigInteger sumEvenNumber = memoization.get(0).add(memoization.get(1));
		BigInteger currentEvenNumber = BigInteger.valueOf(0);
		int i = 2;
		
		if(n.compareTo(BigInteger.valueOf(10)) > 0) {
			while(BigInteger.valueOf(i).compareTo(n) <= 0) {
				currentEvenNumber = memoization.get(i - 2).add(memoization.get(i - 1).add(memoization.get(i - 1)
						.add(memoization.get(i - 1).add(memoization.get(i - 1)))));
				
				if(currentEvenNumber.compareTo(n) >= 0) {
					break;
				} else {
					memoization.add(i, currentEvenNumber);
					sumEvenNumber = sumEvenNumber.add(memoization.get(i));
					i++;
				}				
			}
		} else {
			sumEvenNumber = (n.compareTo(BigInteger.valueOf(8)) >= 0) ? sumEvenNumber : sumEvenNumber.subtract(memoization.get(i));
		}
		
		return sumEvenNumber;
	}

	/**
	 * Each new term in the Fibonacci sequence is generated by adding the previous two terms. 
	 * By starting with 1 and 2, the first 10 terms will be: 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
	 * By considering the terms in the Fibonacci sequence whose values do not exceed four million, 
	 * find the sum of the even-valued terms.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {		
		long start = System.currentTimeMillis();
		
		System.out.println(sumEvenNumber(BigInteger.valueOf(4000000)));
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
