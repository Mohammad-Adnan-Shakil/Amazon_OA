import java.util.*;

/*
 * Problem: Max Consecutive Ones III (LC 1004)
 * Pattern: Sliding Window — variable size
 * Given: binary array nums (up to 10^5), integer k
 * Return: longest subarray with at most k zeros
 */
class LongestOnes {
    // My window tracks: number of zeros inside it
    // Window is INVALID when: zeros > k

    public static int longestOnes(int[] nums, int k) {
        int zeros = 0;
        int left = 0;
        int best = 0;

        for (int right = 0; right < nums.length; right++) {
            // element walks in
            if (nums[right] == 0) {
                zeros++;
            }
            // shrink while broken
            while (zeros > k) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }
            // window is valid -> record its length
            best = Math.max(right - left + 1, best);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));                    // 6
        System.out.println(longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));   // 10
        System.out.println(longestOnes(new int[]{1,1,1}, 0));                                    // 3
        System.out.println(longestOnes(new int[]{0,0,0}, 0));                                    // 0
        System.out.println(longestOnes(new int[]{0,0,0}, 3));                                    // 3
    }
}
/*
 * Time:  O(n) — while inside for, but left only moves forward, never resets.
 *        right moves n times + left moves at most n times = 2n total.
 * Space: O(1) — only zeros, left, right, best.
 *
 * MISTAKES I MADE:
 * - Tried to count the ANSWER (consecutive ones) instead of the thing
 *   that BREAKS the window (zeros). Length comes free: right - left + 1.
 * - Answered in English instead of Java — twice.
 * - Left old code above the declarations -> used vars before they existed.
 * - int best = ;  (no value)
 * - best = right - left + 1 overwrote -> kept LAST window, not BIGGEST.
 * - Math.max(best, zeros) -> compared a length with a counter. Unrelated.
 * - Declared max inside the loop -> died before return (scope again).
 * - Used Run button (broken by workspace) instead of java File.java.
 * - Submitted 6 times without running.
 *
 * WHAT I LEARNED:
 * - Variable window: right grows every step, while(broken) shrinks left.
 * - Counter = what can BREAK the window. Answer = window length.
 *   Two different numbers. Never mix them.
 * - Translation method: animation -> events -> one line per event.
 * - A while inside a for is NOT n^2 if the inner pointer never resets.
 *
 * FOCUS FOR NEXT PROBLEM:
 * - For each variable, say what it MEANS before using it in Math.max
 *   or a condition.
 * - Run with java File.java before every message.
 */