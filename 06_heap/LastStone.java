import java.util.*;

/*
 * Problem: Last Stone Weight (LC 1046)
 * Pattern: Heap — repeatedly take the largest
 * Given: int array of stone weights
 * Return: weight of last stone, or 0
 */
class LastStone {
    // Which heap: MAX-heap — I always need the two heaviest on top
    // Loop runs while: heap.size() > 1
    // Each turn: poll two; if different, push the difference back
    // End: one stone left -> return it; none -> return 0

    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

        // Phase 1: setup
        for (int x : stones) {
            heap.offer(x);
        }

        // Phase 2: smash
        while (heap.size() > 1) {
            int a = heap.poll();
            int b = heap.poll();
            if (a != b) {
                heap.offer(a - b);
            }
        }

        return heap.isEmpty() ? 0 : heap.peek();
    }

    public static void main(String[] args) {
        System.out.println(lastStoneWeight(new int[]{2,7,4,1,8,1}));   // 1
        System.out.println(lastStoneWeight(new int[]{1}));             // 1
        System.out.println(lastStoneWeight(new int[]{2,2}));           // 0
        System.out.println(lastStoneWeight(new int[]{3,7,2}));         // 2
        System.out.println(lastStoneWeight(new int[]{10,4,2,10}));     // 2
    }
}
/*
 * Time:  O(n log n) — up to n smashes, each poll/offer is O(log n)
 * Space: O(n) — the heap I created holds all n stones
 *
 * MISTAKES I MADE:
 * - Mixed setup and smash in ONE loop -> polled an almost-empty heap
 *   -> poll() returned null -> NullPointerException.
 * - Pushed 0 when stones were equal -> fake stone. Destroyed = push nothing.
 * - peek() on a possibly empty heap at the end.
 * - Left the 4 planning lines blank (they would have shown the 2 phases).
 * - Complexity: called heap ops O(1); counted the input as "same space".
 *
 * WHAT I LEARNED:
 * - poll()/peek() on empty heap -> null -> crash when stored in int.
 * - Separate phases: load everything, THEN process.
 * - Heap ops cost O(log n) (except peek).
 * - Ternary: cond ? a : b
 * - Read the exception: it named the method and the line.
 *
 * FOCUS FOR NEXT PROBLEM:
 * - Fill in the planning lines first. Every time.
 */