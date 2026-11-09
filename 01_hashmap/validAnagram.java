import java.util.*;

/*
 * Problem: Valid Anagram (LC 242)
 * Pattern: HashMap — counting + map equality
 * Given: strings s, t (lowercase, up to 5 * 10^4)
 * Return: true if t is an anagram of s
 */
class Solution {
    public boolean isAnagram(String s, String t) {
        // Early exit: different lengths can never be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> sc = new HashMap<>();
        Map<Character, Integer> tc = new HashMap<>();

        // Count letters in s
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sc.put(c, sc.getOrDefault(c, 0) + 1);
        }

        // Count letters in t
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            tc.put(c, tc.getOrDefault(c, 0) + 1);
        }

        return sc.equals(tc);
    }
}
/*
 * Time:  O(n) — length check is O(1); after it, n == m so two loops = 2n -> O(n)
 * Space: O(1) — each map holds at most 26 keys
 *
 * MISTAKES I MADE:
 * - Renamed method (validAnagram vs isAnagram) -> grader can't find it.
 *   NEVER change the given signature.
 * - Class name lowercase (solution vs Solution)
 * - Missing import java.util.*
 * - if (x) return true; else return false;  ->  just return x;
 * - Forgot space complexity (3rd time)
 *
 * WHAT I LEARNED:
 * - map1.equals(map2) compares all key-value pairs. Never use ==
 *   on objects (Maps, Strings) — == compares memory location.
 * - Early exit: check cheap disqualifying conditions FIRST
 * - First problem solved with NO skeleton and NO hints
 *
 * FOCUS FOR NEXT PROBLEM:
 * - Before submitting: check import, class name, method signature,
 *   and write BOTH time and space.
 */