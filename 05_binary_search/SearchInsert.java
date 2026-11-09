import java.util.*;

/*
 * Problem: Search Insert Position (LC 35)
 * Pattern: Binary Search
 * Given: sorted distinct int array, target
 * Return: index of target, or index where it would be inserted
 */
class SearchInsert {
    // If found     -> return mid
    // If not found -> after the loop, low points to the insert position

    public static int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,3,5,6}, 5));   // 2
        System.out.println(searchInsert(new int[]{1,3,5,6}, 2));   // 1
        System.out.println(searchInsert(new int[]{1,3,5,6}, 7));   // 4
        System.out.println(searchInsert(new int[]{1,3,5,6}, 0));   // 0
        System.out.println(searchInsert(new int[]{1}, 1));         // 0
    }
}
/*
 * Time:  O(log n) — the range halves every iteration -> ~log2(n) iterations
 * Space: O(1) — only low, high, mid
 *
 * MISTAKES I MADE:
 * - Swapped time and space: wrote time O(n), space O(log n).
 *   Halving = number of ITERATIONS = time. Memory = 3 ints = O(1).
 * - "Single loop -> O(n)" shortcut is wrong. Count how many times
 *   the loop actually runs.
 * - Used the Run button instead of java File.java (again)
 * - Skipped the hand trace
 *
 * WHAT I LEARNED:
 * - Template: lo <= hi, mid = lo + (hi - lo) / 2, lo = mid + 1, hi = mid - 1
 * - After the loop ends without finding, lo = first index where
 *   nums[i] >= target (the insert position)
 * - Code compiled + passed FIRST run — template memorized correctly
 *
 * FOCUS FOR NEXT PROBLEM:
 * - For complexity: time = how many steps, space = what I created.
 *   Answer each separately.
 */