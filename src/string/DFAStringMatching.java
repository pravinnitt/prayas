package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * String matching with finite automata
 * 
 * The string-matching automaton is very efficient: it examines each character in the text
 * exactly once and reports all the valid shifts in O(n) time. 
 *
 * The basic idea is to build a automaton in which
 *   Each character in the pattern has a state.
 *   Each match sends the automaton into a new state.
 *   If all the characters in the pattern has been matched, the automaton enters 
 *          the accepting state.
 *   Otherwise, the automaton will return to a suitable state according to the current state
 *       and the input character such that this returned state reflects the maximum advantage
 *       we can take from the previous matching.
 *   the matching takes O(n) time since each character is examined once.
 */
public class DFAStringMatching {

	// public static String pattern = "ababaca";
	// public static String source = "abababacaba";
	public static String pattern = "AABA";
	public static String source = "AABAACAADAABAABA";
	public static HashMap<Integer, HashMap<Character, Integer>> lookup;

	public DFAStringMatching() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		System.out.println("finite automata");
		DFAStringMatching DSM = new DFAStringMatching();
		DSM.patternMatch(source, pattern);
	}

	public void patternMatch(String src, String ptrn) {
		lookup = createStateTransitionLookup(ptrn);
		int state = 0, count = 0, appearance = 0;
		List<Integer> places = new ArrayList<Integer>();
		while (count < src.length()) {
			char temp = src.charAt(count);
			HashMap<Character, Integer> map = lookup.get(state);
			if (map.containsKey(temp)) {
				state = map.get(temp);
			} else {
				state = 0;
			}
			if (state == ptrn.length()) {
				appearance++;
				places.add(count - ptrn.length()+1);
			}
			count++;
		}
		if (appearance > 0) {

			System.out.println("\n pattern " + ptrn
					+ " is matched  in source = " + src + "  " + appearance
					+ " time at locations " + places);
		} else {
			System.out.println("\n pattern is not matched");

		}
	}

	public HashMap<Integer, HashMap<Character, Integer>> createStateTransitionLookup(
			String pattern) {
		HashSet<Character> input = findDistinctChar(pattern);
		HashMap<Integer, HashMap<Character, Integer>> store = new HashMap<>();

		// Filling table for o'th or starting state
		char first = pattern.charAt(0);
		HashMap<Character, Integer> zeroth = new HashMap<>();
		HashMap<Character, Integer> last = new HashMap<>();
		zeroth.put(first, 1);
		for (Character character : input) {
			if (!zeroth.containsKey(character)) {
				zeroth.put(character, 0);
			}
		}
		store.put(0, zeroth);
		store.put(pattern.length(), last);
		/*
		 * Fill the basic input states AFTER FIRST STATE
		 */
		for (int i = 1; i < pattern.length(); i++) {
			HashMap<Character, Integer> cur = new HashMap<>();
			char next = pattern.charAt(i);
			cur.put(next, i + 1);
			store.put(i, cur);
		}
		/*
		 * Fill the remaining transition for all states
		 */
		StringBuilder covered = new StringBuilder();
		for (int i = 1; i <= pattern.length(); i++) {
			HashMap<Character, Integer> local = store.get(i);
			covered.append(pattern.charAt(i - 1));
			StringBuilder running = new StringBuilder(covered);
			for (Character character : input) {
				if (!local.containsKey(character)) {
					StringBuilder use = new StringBuilder(running)
							.append(character);
					local.put(character,
							findLongestCommonPrefixSuffixLength(use.toString()));
				}
			}

		}

		System.out.println("\n State transition matrix is = " + store);
		return store;
	}

	private HashSet<Character> findDistinctChar(String pattern) {
		HashSet<Character> store = new HashSet<>();
		for (int i = 0; i < pattern.length(); i++) {
			store.add(pattern.charAt(i));
		}
		return store;
	}

	public int findLongestCommonPrefixSuffixLength(String param) {
		int len = param.length();
		for (int i = 1; i <= len; i++) {
			String prefix = param.substring(0, (len - i));
			String suffix = param.substring(i, (len));

			if (suffix.equals(prefix)) {
				return suffix.length();
			}
		}
		return 0;
	}
}
