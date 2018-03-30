package string;

/*
 * KMP (Knuth Morris Pratt) Pattern Searching
 * The KMP matching algorithm uses degenerating property (pattern having
 * same sub-patterns appearing more than once in the pattern) of the 
 * pattern and improves the worst case complexity to O(n). The basic idea
 * behind KMP’s algorithm is: whenever we detect a mismatch (after some
 * matches), we already know some of the characters in the text of next 
 * window. We take advantage of this information to avoid matching the 
 * characters that we know will anyway match. Let us consider below example 
 * to understand this.
 */
public class KMPStringSearch {
	public static String ptr = "abcdabd";
	public static String pattern = "TEST";
	public static String src = "THIS IS A TEST TEXT";
	public static String pattern1 = "AABA";
	public static String src1 = "AABAACAADAABAABA";

	public static int[] store = new int[ptr.length()];

	public KMPStringSearch() {
	}

	public static void main(String[] args) {
		System.out.println("KMP");
		KMPStringSearch kmp = new KMPStringSearch();
		kmp.findMatchingString(src, pattern);
		System.out.println("\n ******************************");
		kmp.findMatchingString(src1, pattern1);
	}

	/*
	 * Creating the lookup table ("Partial match" table).
	 * The goal of the table is to allow the algorithm not to match any character
	 * of S more than once .we "pre-search" the pattern itself and compile a list
	 * of all possible fallback positions that bypass a maximum of hopeless 
	 * characters while not sacrificing any potential matches in doing so.
	 */
	public int[] findLookup(String entry) {
		int[] repos = new int[entry.length()];
		int k = 0;
		repos[1] = 0;
		for (int i = 1; i < entry.length(); i++) {
			if (entry.charAt(k) == entry.charAt(i)) {
				k = k + 1;
				repos[i] = k;
			} else {
				if (k > 0) {
					k = repos[i];
				}
			}
		}
		return repos;
	}

	public int findMatchingString(String src, String pattern) {
		int count = 0;
		int[] lookup = findLookup(pattern);
		int srclen = src.length();
		int ptrnlen = pattern.length();
		int matched = 0;
		for (int i = 0; i < srclen; i++) {
			if (src.charAt(i) == pattern.charAt(matched)) {
				matched = matched + 1;
			} else {
				if (matched > 0) {
					matched = lookup[matched];
				}
			}
			if (matched == ptrnlen) {
				count = count + 1;
				System.out.println(pattern + " is found in source = " + src
						+ " at position = " + (i - matched + 1));
				matched = lookup[matched - 1];
			}
		}
		System.out.println("\n Total number of occurance of '" + pattern
				+ "' in source [ " + src + " ] is = " + count);
		return count;
	}
}
