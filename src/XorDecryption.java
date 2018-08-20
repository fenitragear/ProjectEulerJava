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
	
	private static byte[] cipher;
	
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
						break;
					} else {
						isProbableKey = true;
						
						if(!isNextFirstLetter)
							isNextFirstLetter = cipherCopy[j] == 32;
					}
				}
				
				if(isProbableKey) {
					isProbableKey = true;
					keys[k] = key;
					cipher = Arrays.copyOf(cipherCopy, cipher.length);
					
					if(k == 2) {
						System.out.println("decrypted => " + new String(cipher));
						System.out.println("key => " + new String(keys));
					}
					
					break;
				}
			}
		}
	}
	
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
		
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		cipher = new byte[n];
		
		for(int i = 0; i < n; i++) {
			cipher[i] = scanner.nextByte();
		}
		
		scanner.close();
		
		analyze();
		
		System.out.println("Solution took " + (System.currentTimeMillis() - start) + "ms");
	}
}
