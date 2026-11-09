import java.util.*;

/*
 * Problem: Valid Palindrome (LC 125)
 * Pattern: Two Pointers — opposite ends, skip invalid chars
 * Given: string s (up to 2 * 10^5), may contain spaces/punctuation
 * Return: true if alphanumeric chars, lowercased, read same both ways
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
/*
 * Time:  O(n) — every step moves a pointer inward; at most n moves total
 * Space: O(1) — only two int pointers, regardless of input size
 *
 * MISTAKES I MADE:
 * - return true INSIDE the loop -> exited after 1 iteration.
 *   Same "all must match" bug as Ransom Note — didn't transfer the rule.
 * - s[left] (C habit) instead of s.charAt(left)
 * - s.toLowerCase() on whole string = new O(n) string, breaks O(1) space
 * - c.toLowerCase() on a char -> primitives have no methods;
 *   use Character.toLowerCase(c)
 * - Moved pointers on MISMATCH, did nothing on MATCH -> infinite loop
 * - Left invalid package line + unused variable through 3 reviews
 *
 * WHAT I LEARNED:
 * - Skip, don't remove: move pointers past junk instead of building
 *   a cleaned copy -> keeps O(1) space
 * - continue; = jump to next loop iteration
 * - Every path through a while loop must move a pointer or exit,
 *   otherwise infinite loop
 * - Primitives (char, int) have no dot-methods
 *
 * FOCUS FOR NEXT PROBLEM:
 * - Apply EVERY review fix before resubmitting. Go line by line.
 * - Before running: trace one small input by hand ("aa", "ab")
 */