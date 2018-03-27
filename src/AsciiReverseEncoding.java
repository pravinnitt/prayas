

import java.util.HashSet;

/**
 * Encode a given string => convert to ASCII code-> reverse the position i.e. 1st to nth, 2nd to n-1th and so on
 * Decode => return back original string
 * valid input strings are "A-Z", "a-z" and " "
 * @author pravin
 *
 */
public class AsciiReverseEncoding {
	public static HashSet<String> validAscii = new HashSet<>();
	public static String input = "abcde";

	public AsciiReverseEncoding() {
	}

	public static void main(String[] args) {
		System.out.println("AsciiReverseEncoding.main()");
		AsciiReverseEncoding ARE = new AsciiReverseEncoding();
		ARE.populateValidInputList();
		ARE.generateEncode(input);
		ARE.decodeEncodedString("101001998979");
		ARE.reverseString(input);
	}

	public void generateEncode(String param) {
		StringBuilder result = new StringBuilder();
		for (int i = param.length() - 1; i >= 0; i--) {
			int temp = param.charAt(i);
			String rvrs = reverseNumber(temp);
			result.append(rvrs);
		}
		System.out.println("for input = " + param + " encrypted string = "
				+ result.toString());
	}

	public void decodeEncodedString(String param) {
		String cntString = new String();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < param.length(); i++) {
			char ch = param.charAt(i);
			cntString = cntString + ch;
			if (validAscii.contains(cntString.intern())) {
				result.append((char) reverseNumber(cntString));
				cntString = new String();
			}
		}
		System.out.println("Decrypted string for code [ " + param + "] is = "
				+ reverseString(result.toString()));
	}

	public String reverseString(String param) {
		char[] local = param.toCharArray();
		int len = local.length - 1;
		for (int i = 0; i <= len / 2; i++) {
			char t = local[i];
			local[i] = local[len - i];
			local[len - i] = t;
		}
		return new String(local);
	}

	public void populateValidInputList() {
		/*
		 * Store lower case Alphabet
		 */
		for (int i = 0; i < 26; i++) {
			validAscii.add(reverseNumber('a' + i).intern());
		}
		/*
		 * Store lower case Alphabet
		 */
		for (int i = 0; i < 26; i++) {
			validAscii.add(reverseNumber('A' + i).intern());
		}
		validAscii.add(reverseNumber(' ' + 0).intern());
	}

	public String reverseNumber(int num) {
		StringBuilder sb = new StringBuilder();
		while (num > 0) {
			int reminder = num % 10;
			sb.append(reminder);
			num = num / 10;
		}
		return sb.toString();
	}

	public int reverseNumber(String param) {
		StringBuilder result = new StringBuilder();
		for (int i = param.length() - 1; i >= 0; i--) {
			char ch = param.charAt(i);
			result.append(ch);
		}
		return Integer.parseInt(result.toString());

	}
}
