import java.util.*;

/*
 * Problem: Top K Frequent Elements (LC 347)
 * Pattern: HashMap counting + Heap
 * Given: int array nums, int k
 * Return: the k most frequent elements (any order)
 */
class TopKFrequent {
    // Phase 1 (count):  HashMap number -> frequency
    // Phase 2 (heap):   max-heap ordered by frequency, offer every distinct number
    // Phase 3 (result): poll k times -> k most frequent

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));

        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (int num : map.keySet()) {
            heap.offer(num);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] r1 = topKFrequent(new int[]{1,1,1,2,2,3}, 2);
        Arrays.sort(r1); System.out.println(Arrays.toString(r1));   // [1, 2]
        int[] r2 = topKFrequent(new int[]{1}, 1);
        Arrays.sort(r2); System.out.println(Arrays.toString(r2));   // [1]
        int[] r3 = topKFrequent(new int[]{4,4,4,6,6,7,7,7,7}, 2);
        Arrays.sort(r3); System.out.println(Arrays.toString(r3));   // [4, 7]
    }
}
/*
 * Time:  O(n log n) — count O(n) + offer up to n items at O(log n) each
 *        + poll k at O(log n). Biggest term wins.
 *        (Optimal: size-k min-heap -> O(n log k))
 * Space: O(n) — map and heap each hold up to n numbers
 *
 * MISTAKES I MADE:
 * - Counted indices (map.put(i, ...)) instead of values (nums[i]).
 *   Key vs value confusion again -> use for-each to get values directly.
 * - peek() instead of poll() -> same top every time -> [1, 1].
 * - Complexity: wrote only Phase 3's cost. Total EVERY phase.
 *
 * WHAT I LEARNED:
 * - Custom heap order: new PriorityQueue<>((a, b) -> map.get(b) - map.get(a))
 * - Heap op = O(log n): tournament bracket has log n rounds.
 * - Heap time = (# offers/polls) x log(heap size)
 * - peek = look, poll = remove so the next one rises
 */