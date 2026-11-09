import java.util.*;

/*
 * Problem: First Unique Character in a String (LC 387)
 * Pattern: HashMap — counting
 * Given: string s (lowercase, length up to 10^5)
 * Return: index of first char that appears exactly once, else -1
 */
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> count = new HashMap<>();

        // Pass 1: count every character
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // Pass 2: walk string in order, first count==1 is the answer
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (count.get(c) == 1) {
                return i;
            }
        }

        return -1;
    }
}
/*
 * Time:  O(n) — two separate passes, n + n = 2n -> O(n)
 * Space: O(1) — map holds at most 26 keys (lowercase only), fixed size
 *
 * MISTAKES I MADE:
 * - Logic: "remove key when seen twice" -> a 3rd occurrence gets re-added
 *   as unique. "aaa" returned 2 instead of -1. Deleting = losing info.
 * - s.length instead of s.length() (String uses a method, array uses a field)
 * - Wrote map.put instead of count.put — copied cheat-sheet name blindly
 * - Pass 2 had no loop -> used c and i outside their scope
 * - Typo: reutrn
 * - Redundant if/else — getOrDefault already handles missing keys
 * - Skipped complexity analysis
 * - "Retyped from scratch" was actually recall of the given fix, not my own code
 *
 * WHAT I LEARNED:
 * - Count, don't delete: track frequency instead of seen/not-seen
 * - Two-pass pattern: pass 1 builds info, pass 2 uses it in original order
 * - Iterating in order = lowest index for free, no min-search needed
 * - return exits immediately — first match wins
 *
 * FOCUS FOR NEXT PROBLEM:
 * - Before typing, say where each variable lives. Every loop that uses
 *   i or c needs its own declaration. Write it myself, no recall.
 */