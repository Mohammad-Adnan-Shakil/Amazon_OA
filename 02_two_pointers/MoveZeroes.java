import java.util.*;

/*
 * Problem: Move Zeroes (LC 283)
 * Pattern: Two Pointers — same direction (read/write)
 * Given: int array nums (up to 10^4)
 * Do: move all 0s to end in place, keep order of non-zeros
 */
class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        // Loop 1: R reads everything, W packs non-zeros to the front
        int w = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] != 0) {
                nums[w] = nums[r];
                w++;
            }
        }
        // Loop 2: everything from W to end is junk -> 0
        for (int i = w; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 0, 3, 12};
        moveZeroes(a);
        System.out.println(Arrays.toString(a));   // [1, 3, 12, 0, 0]

        int[] b = {0};
        moveZeroes(b);
        System.out.println(Arrays.toString(b));   // [0]

        int[] c = {1, 2, 3};
        moveZeroes(c);
        System.out.println(Arrays.toString(c));   // [1, 2, 3]
    }
}
/*
 * Time:  O(n) — loop 1 covers [0, n), loop 2 covers [w, n) -> at most 2n
 * Space: O(1) — only r, w, i
 *
 * MISTAKES I MADE:
 * - Kept the wrong thing: nums[r] == 0 instead of != 0 (3 attempts)
 * - Swapped roles: moved R conditionally and W every step
 * - Used 3 variables (i, r, w) -> r never moved, read nums[0] forever
 * - nums.length() on an array (arrays: .length, no brackets)
 * - Submitted twice without running
 *
 * WHAT I LEARNED:
 * - Read/write: R moves every step, W moves only on keep.
 *   Order: nums[w] = nums[r]; THEN w++
 * - Make R the for-loop variable -> can't forget to move it
 * - W never passes R -> overwriting is always safe
 * - Running the code + reading output finds bugs faster than staring
 *
 * FOCUS FOR NEXT PROBLEM:
 * - Before coding, say in one sentence: "I keep ___."
 * - RUN before every submit. No exceptions.
 */