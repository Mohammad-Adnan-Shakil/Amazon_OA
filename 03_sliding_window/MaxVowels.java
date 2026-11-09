import java.util.*;

/*
 * Problem: Maximum Number of Vowels in a Substring of Given Length (LC 1456)
 * Pattern: Sliding Window — fixed size
 * Given: lowercase string s (up to 10^5), integer k
 * Return: max vowels in any substring of length k
 */
class MaxVowels {
    // My window tracks: the number of vowels currently inside it

    public static int maxVowels(String s, int k) {
        // Step 1: count vowels in the first window [0 .. k-1]
        int count = 0;
        for (int i = 0; i < k; i++) {
            if ("aeiou".indexOf(s.charAt(i)) != -1) {
                count++;
            }
        }
        int max = count;

        // Step 2: slide — right enters, left leaves, size stays k
        int left = 0;
        for (int right = k; right < s.length(); right++) {
            if ("aeiou".indexOf(s.charAt(right)) != -1) {
                count++;
            }
            if ("aeiou".indexOf(s.charAt(left)) != -1) {
                count--;
            }
            left++;
            max = Math.max(count, max);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxVowels("abciiidef", 3));   // 3
        System.out.println(maxVowels("aeiou", 2));       // 2
        System.out.println(maxVowels("leetcode", 3));    // 2
        System.out.println(maxVowels("bcdfg", 2));       // 0
        System.out.println(maxVowels("a", 1));           // 1
    }
}
/*
 * Time:  O(n) — first loop k steps + slide loop (n - k) steps = n.
 *        Each step is O(1): one charAt + an indexOf on a 5-char string.
 * Space: O(1) — only count, max, left, right. No substring is ever built.
 *
 * MISTAKES I MADE:
 * - Mental block: wanted to store the substring and add/remove letters.
 *   That rebuilds a string each slide = O(n*k) brute force.
 * - Used i (first loop's variable) inside the slide loop -> scope error.
 * - "Fixed" the scope error by moving int i outside -> compiled, but i was
 *   frozen at k, so +1 and -1 cancelled. Silenced the compiler instead of
 *   asking which variable SHOULD be there.
 * - right < s.length() - 1 -> skipped the last char.
 * - "Could not find main class" = compile failed, not a logic error.
 *
 * WHAT I LEARNED:
 * - Bouncer model: don't store who's inside, keep ONE number.
 *   Enter -> +1 if it matters. Leave -> -1 if it matters.
 * - Fixed window = two pointers that both move +1 every step,
 *   so size stays k automatically. left == right - k.
 * - Step 1 builds the first window, Step 2 slides.
 * - Run single files with: java FileName.java
 *
 * FOCUS FOR NEXT PROBLEM:
 * - On a compiler error, ask "which variable should be here?",
 *   not "how do I make this one exist?"
 */