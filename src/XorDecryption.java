import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * https://projecteuler.net/problem=59
 * 
 * @author Stéphan R.
 *
 */
public class XorDecryption {
	
	private static byte[] cipher = new byte[] {
			79, 59, 12, 2, 79, 35, 8, 28, 20, 2, 3, 68, 8, 9, 68, 45, 0, 12, 9, 67, 68, 4, 7, 5, 23, 27, 1, 21, 79, 85, 78, 79, 85, 71, 38, 10, 71, 27, 
			12, 2, 79, 6, 2, 8, 13, 9, 1, 13, 9, 8, 68, 19, 7, 1, 71, 56, 11, 21, 11, 68, 6, 3, 22, 2, 14, 0, 30, 79, 1, 31, 6, 23, 19, 10, 0, 73, 79, 
			44, 2, 79, 19, 6, 28, 68, 16, 6, 16, 15, 79, 35, 8, 11, 72, 71, 14, 10, 3, 79, 12, 2, 79, 19, 6, 28, 68, 32, 0, 0, 73, 79, 86, 71, 39, 1, 71, 
			24, 5, 20, 79, 13, 9, 79, 16, 15, 10, 68, 5, 10, 3, 14, 1, 10, 14, 1, 3, 71, 24, 13, 19, 7, 68, 32, 0, 0, 73, 79, 87, 71, 39, 1, 71, 12, 22, 
			2, 14, 16, 2, 11, 68, 2, 25, 1, 21, 22, 16, 15, 6, 10, 0, 79, 16, 15, 10, 22, 2, 79, 13, 20, 65, 68, 41, 0, 16, 15, 6, 10, 0, 79, 1, 31, 6, 23, 
			19, 28, 68, 19, 7, 5, 19, 79, 12, 2, 79, 0, 14, 11, 10, 64, 27, 68, 10, 14, 15, 2, 65, 68, 83, 79, 40, 14, 9, 1, 71, 6, 16, 20, 10, 8, 1, 79, 
			19, 6, 28, 68, 14, 1, 68, 15, 6, 9, 75, 79, 5, 9, 11, 68, 19, 7, 13, 20, 79, 8, 14, 9, 1, 71, 8, 13, 17, 10, 23, 71, 3, 13, 0, 7, 16, 71, 27, 
			11, 71, 10, 18, 2, 29, 29, 8, 1, 1, 73, 79, 81, 71, 59, 12, 2, 79, 8, 14, 8, 12, 19, 79, 23, 15, 6, 10, 2, 28, 68, 19, 7, 22, 8, 26, 3, 15, 79,
			16, 15, 10, 68, 3, 14, 22, 12, 1, 1, 20, 28, 72, 71, 14, 10, 3, 79, 16, 15, 10, 68, 3, 14, 22, 12, 1, 1, 20, 28, 68, 4, 14, 10, 71, 1, 1, 17, 10,
			22, 71, 10, 28, 19, 6, 10, 0, 26, 13 , 20, 7, 68, 14, 27, 74, 71, 89, 68, 32, 0, 0, 71, 28, 1, 9, 27, 68, 45, 0, 12, 9, 79, 16, 15, 10, 68, 37, 14,
			20, 19, 6, 23, 19, 79, 83, 71, 27, 11, 71, 27, 1, 11, 3, 68, 2, 25, 1, 21, 22, 11, 9, 10, 68, 6, 13, 11, 18, 27, 68, 19, 7, 1, 71, 3, 13, 0, 7, 16,
			71, 28, 11, 71, 27, 12, 6, 27, 68, 2, 25, 1, 21, 22, 11, 9, 10, 68, 10, 6, 3, 15, 27, 68, 5, 10, 8, 14, 10, 18, 2, 79, 6, 2, 12, 5, 18, 28, 1, 71, 0,
			2, 71, 7, 13, 20, 79, 16, 2, 28, 16, 14, 2, 11, 9, 22, 74, 71, 87, 68, 45, 0, 12, 9, 79, 12, 14, 2, 23, 2, 3, 2, 71, 24, 5, 20, 79, 10, 8, 27, 68, 19,
			7, 1, 71, 3, 13, 0, 7, 16, 92, 79, 12, 2, 79, 19, 6, 28, 68, 8, 1, 8, 30, 79, 5, 71, 24, 13, 19, 1, 1, 20, 28, 68, 19, 0, 68, 19, 7, 1, 71, 3, 13, 0, 7,
			16, 73, 79, 93, 71, 59, 12, 2, 79, 11, 9, 10, 68, 16, 7, 11, 71, 6, 23, 71, 27 , 12, 2, 79, 16, 21, 26, 1, 71, 3, 13, 0, 7, 16, 75, 79, 19, 15, 0, 68, 0,
			6, 18, 2, 28, 68, 11, 6, 3, 15, 27, 68, 19, 0, 68, 2, 25, 1, 21, 22, 11, 9, 10, 72, 71, 24, 5, 20, 79, 3, 8, 6, 10, 0, 79, 16, 8, 79, 7, 8, 2, 1, 71, 6,
			10, 19, 0, 68, 19, 7, 1, 71, 24, 11, 21, 3, 0, 73, 79, 85, 87, 79, 38, 18, 27, 68, 6, 3, 16, 15, 0, 17, 0, 7, 68, 19, 7, 1, 71, 24, 11, 21, 3, 0, 71, 24,
			5, 20, 79, 9, 6, 11, 1, 71, 27, 12, 21, 0, 17, 0, 7, 68, 15, 6, 9, 75, 79, 16, 15, 10, 68, 16, 0, 22, 11, 11, 68, 3, 6, 0, 9, 72, 16, 71, 29, 1, 4, 0 , 3, 9,
			6, 30, 2, 79, 12, 14, 2, 68, 16, 7, 1, 9, 79, 12, 2, 79, 7, 6, 2, 1, 73, 79, 85, 86, 79, 33, 17, 10, 10, 71, 6, 10, 71, 7, 13, 20, 79, 11, 16, 1, 68, 11, 14,
			10, 3, 79, 5, 9, 11, 68, 6, 2, 11, 9, 8, 68, 15, 6, 23, 71, 0, 19, 9, 79, 20, 2, 0, 20, 11, 10, 72, 71, 7, 1, 71, 24, 5, 20, 79, 10, 8, 27, 68, 6, 12, 7, 2,
			31, 16, 2, 11, 74, 71, 94, 86, 71, 45, 17, 19, 79, 16, 8, 79, 5, 11, 3, 68, 16, 7, 11, 71, 13, 1, 11, 6, 1, 17, 10, 0, 71, 7, 13, 10, 79, 5, 9, 11, 68, 6, 12,
			7, 2, 31, 16, 2, 11, 68, 15, 6, 9, 75, 79, 12, 2 , 79, 3, 6, 25, 1, 71, 27, 12, 2, 79, 22, 14, 8, 12, 19, 79, 16, 8, 79, 6, 2, 12, 11, 10, 10, 68, 4, 7, 13, 11,
			11, 22, 2, 1, 68, 8, 9, 68, 32, 0, 0, 73, 79, 85, 84, 79, 48, 15, 10, 29, 71, 14, 22, 2, 79, 22, 2, 13, 11, 21, 1, 69, 71, 59, 12, 14, 28, 68, 14, 28, 68, 9, 0,
			16, 71, 14, 68, 23, 7, 29, 20, 6, 7, 6, 3, 68, 5, 6, 22, 19, 7, 68, 21, 10, 23, 18, 3, 16, 14, 1, 3, 71, 9, 22, 8, 2, 68, 15, 26, 9, 6, 1, 68, 23, 14, 23, 20, 6,
			11, 9, 79, 11, 21, 79, 20, 11, 14, 10, 75, 79, 16, 15, 6, 23, 71, 29, 1, 5, 6, 22, 19, 7, 68 , 4, 0, 9, 2, 28, 68, 1, 29, 11, 10, 79, 35, 8, 11, 74, 86, 91, 68, 52,
			0, 68, 19, 7, 1, 71, 56, 11, 21, 11, 68, 5, 10, 7, 6, 2, 1, 71, 7, 17, 10, 14, 10, 71, 14, 10, 3, 79, 8, 14, 25, 1, 3, 79, 12, 2, 29, 1, 71, 0, 10, 71, 10, 5, 21, 27,
			12, 71, 14, 9, 8, 1, 3, 71 , 26, 23, 73, 79, 44, 2, 79, 19, 6, 28, 68, 1, 26, 8, 11, 79, 11, 1, 79, 17, 9, 9, 5, 14, 3, 13, 9, 8, 68, 11, 0, 18, 2, 79, 5, 9, 11, 68, 1,
			14, 13, 19, 7, 2, 18, 3, 10, 2, 28, 23, 73, 79, 37, 9, 11, 68, 16, 10, 68, 15, 14, 18, 2, 79, 23, 2, 10, 10, 71, 7, 13, 20 , 79, 3, 11, 0, 22, 30, 67, 68, 19, 7, 1, 71,
			8, 8, 8, 29, 29, 71, 0, 2, 71, 27, 12, 2, 79, 11, 9, 3, 29, 71, 60, 11, 9, 79, 11, 1, 79, 16, 15, 10, 68, 33, 14, 16, 15, 10, 22, 73
	};
	
	private static Map<String, Double> letterFrequency = new HashMap<String, Double>() {{
		put("a", 8.167); put("b", 1.492); put("c", 2.782); put("d", 4.253);
		put("e", 12.702); put("f", 2.228); put("g", 2.015); put("h", 6.094);
		put("i", 6.966); put("j", 0.153); put("k", 0.772); put("l", 4.025);
		put("m", 2.406); put("n", 6.749); put("o", 7.507); put("p", 1.929);
		put("q", 0.095); put("r", 5.987); put("s", 6.327); put("t", 9.056);		
		put("u", 2.758); put("v", 0.978); put("w", 2.360); put("x", 0.150);
		put("y", 1.974); put("z", 0.074);
	}};
	
	private static Map<String, Double> firstLetterFrequency = new HashMap<String, Double>() {{
		put("a", 11.682); put("b", 4.434); put("c", 5.238); put("d", 3.174);
		put("e", 2.799); put("f", 4.027); put("g", 1.642); put("h", 4.200);
		put("i", 7.294); put("j", 0.511); put("k", 0.456); put("l", 2.415);
		put("m", 3.826); put("n", 2.284); put("o", 7.631); put("p", 4.319);
		put("q", 0.222); put("r", 2.826); put("s", 6.686); put("t", 15.978);		
		put("u", 1.183); put("v", 0.824); put("w", 5.497); put("x", 0.045);
		put("y", 0.763); put("z", 0.045);
	}};
	
	private static List<Integer> acceptableValue = new ArrayList<Integer>() {{
		// A-Z
		add(65); add(66); add(67); add(68); add(69); add(70); add(71); add(72); add(73); add(74); add(75);
		add(76); add(77); add(78); add(79); add(80); add(81); add(82); add(83); add(84); add(85); add(86);
		add(87); add(88); add(89); add(90);
		 
		// a-z
		add(97); add(98); add(99); add(100); add(101); add(102); add(103); add(104); add(105); add(106); add(107);
		add(108); add(109); add(110); add(111); add(112); add(113); add(114); add(115); add(116); add(117); add(118);
		add(119); add(120); add(121); add(122);
		
		// (;:,.'?-!)space
		add(40); add(59); add(58); add(44); add(46); add(39); add(63); add(45); add(33); add(41); add(32);
		
		// 0-9
		add(48); add(49); add(50); add(51); add(52); add(53); add(54); add(55); add(56); add(57);
	}};
	
	static void analyze() {
		int x = 0;
		int sum = 0;
		int temp = 0;
		System.out.println("size = " + cipher.length);
		byte[] keys = new byte[3];
		byte[] cipherCopy = Arrays.copyOf(cipher, cipher.length);
		boolean isNextFirstLetter = false;
		boolean isProbableKey = true;
		
		for(int k = 0; k < keys.length; k++) {
			for(int i = 0; i < letterFrequency.size(); i++) {
				byte key = (i == 0 || isNextFirstLetter) ? ((String) firstLetterFrequency.keySet().toArray()[x++]).getBytes()[0] :
					((String) letterFrequency.keySet().toArray()[i]).getBytes()[0];
				
				for(int j = k; j < cipherCopy.length; j += 3) {
					cipherCopy[j] ^= key;
					
					if((i ==  0 && cipherCopy[j] == 32) || !acceptableValue.contains((int) cipherCopy[j])) {
						isProbableKey = false;
						isNextFirstLetter = false;
						cipherCopy = Arrays.copyOf(cipher, cipher.length);
						temp = 0;
						break;
					} else {
						isProbableKey = true;
						
						if(!isNextFirstLetter)
							isNextFirstLetter = cipherCopy[j] == 32;
						
						temp += cipherCopy[j];
					}
				}
				
				if(isProbableKey) {
					isProbableKey = true;
					keys[k] = key;
					sum += temp;
					cipher = Arrays.copyOf(cipherCopy, cipher.length);
					
					if(k == 2) {
						System.out.println("decrypted => " + new String(cipher));
						System.out.println("key => " + new String(keys));						
						System.out.println("sum => " + sum);
					}
					
					break;
				}
			}
		}
	}

	/**
	 * Each character on a computer is assigned a unique code and the preferred standard is ASCII (American Standard Code for Information Interchange). 
	 * For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
	 * 
	 * A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each byte with a given value, taken from a secret key. 
	 * The advantage with the XOR function is that using the same encryption key on the cipher text, restores the plain text; for example, 65 XOR 42 = 107, 
	 * then 107 XOR 42 = 65. For unbreakable encryption, the key is the same length as the plain text message, and the key is made up of random bytes. 
	 * The user would keep the encrypted message and the encryption key in different locations, and without both "halves", it is impossible to decrypt the
	 * message.
	 *  
	 * Unfortunately, this method is impractical for most users, so the modified method is to use a password as a key. If the password is shorter than the 
	 * message, which is likely, the key is repeated cyclically throughout the message. The balance for this method is using a sufficiently long password
	 * key for security, but short enough to be memorable.
	 *  
	 * Your task has been made easy, as the encryption key consists of three lower case characters. Using cipher.txt, a file containing the encrypted ASCII
	 * codes, and the knowledge that the plain text must contain common English words, decrypt the message and find the sum of the ASCII values in the 
	 * original text.

	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		letterFrequency = letterFrequency.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		firstLetterFrequency = firstLetterFrequency.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));						
		
		analyze();
		
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
