import java.util.*;

/*
 * Problem: Baseball Game (LC 682)
 * Pattern: Stack — undo / most recent
 * Given: array of operations (strings)
 * Return: sum of all valid scores at the end
 */
class BaseballGame {
    // Events:
    // number walks in  -> parse once, push
    // "C" walks in     -> pop (cancel most recent)
    // "D" walks in     -> peek, double, push
    // "+" walks in     -> pop top, peek second, push top back, push sum
    // end              -> pop everything, add to sum

    public static int calPoints(String[] operations) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];
            if (op.equals("D")) {
                stack.push(2 * stack.peek());
            } else if (op.equals("C")) {
                stack.pop();
            } else if (op.equals("+")) {
                int top = stack.pop();
                int second = stack.peek();
                stack.push(top);
                stack.push(top + second);
            } else {
                stack.push(Integer.parseInt(op));
            }
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(calPoints(new String[]{"5","2","C","D","+"}));               // 30
        System.out.println(calPoints(new String[]{"5","-2","4","C","D","9","+","+"}));  // 27
        System.out.println(calPoints(new String[]{"1","C"}));                           // 0
        System.out.println(calPoints(new String[]{"3","D","D"}));                       // 21
    }
}
/*
 * Time:  O(n) — one pass over operations, each push/pop/peek is O(1);
 *        final sum loop pops at most n items -> 2n total
 * Space: O(n) — the stack can hold up to n scores, so it grows with n
 *
 * MISTAKES I MADE:
 * - Wrong stack type (Character) for numbers -> 8 compile errors,
 *   parseInt / valueOf scattered everywhere.
 * - "+" popped both scores and never put them back -> lost data.
 * - Summing loop inside the for loop (missing brace).
 * - int sum with no value, then declared sum twice.
 * - == on Strings instead of .equals() (again).
 * - Didn't apply the root-cause fix on 2nd attempt; pasted a reason
 *   that contradicted my own answer (O(1) + "grows with n").
 *
 * WHAT I LEARNED:
 * - Pick the stack type = what you STORE. Convert at the door, once.
 * - Read the 2nd item: pop top, peek, push top back.
 * - Always isEmpty() before pop() in general problems.
 * - Events in English first caught the "+" logic — code it exactly.
 *
 * FOCUS FOR NEXT PROBLEM:
 * - Apply EVERY review item. Read my reason before writing it.
 */