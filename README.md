# Amazon OA Prep

## Pre-Submit Checklist (EVERY problem)
- [ ] `import java.util.*;` present
- [ ] Exact class name + method signature as given — never rename
- [ ] No `package` line, no unused variables
- [ ] Traced one small input by hand
- [ ] RAN it (`java File.java`) — output matches expected
- [ ] Time AND space complexity, each with a reason

## My Recurring Mistakes (review before OA)
1. Not running before submitting
2. Mixing what variables MEAN — key vs value, counter vs answer, letter vs count
3. Scope — declaring a variable inside a loop, then using it outside
4. Inverted conditions — `==` vs `!=`, return true/false swapped
5. "All must pass" — return false on FIRST failure inside loop, true AFTER loop
6. Silencing a compiler error instead of asking "which variable SHOULD be here?"
7. Not applying every review fix before resubmitting

## Translation Method (picture → code)
Don't jump from the whole animation to the whole code.
1. List the EVENTS in the animation (element enters, window breaks, element leaves...)
2. For each event, ask: what changes, and when?
3. Each answer = one line of Java
4. Drop those lines into the pattern template

## Java Syntax Cheat Sheet

### HashMap
1. Declare: `Map<KEY, VALUE> map = new HashMap<>();`
   KEY = what you look up by, VALUE = what you want back.
   Inside `< >` use wrappers: `Character`, `Integer` (not `char`, `int`)
2. Count frequency: `map.put(x, map.getOrDefault(x, 0) + 1);`
3. Get value: `map.get(key)` → null if missing; use `getOrDefault` when unsure
4. Loop over keys: `for (char c : map.keySet())`
5. Compare contents: `map1.equals(map2)` — never `==` on objects (Maps, Strings)

### Strings / Chars / Arrays
6. Length: `arr.length` | `s.length()` | `list.size()`
7. Char at index: `s.charAt(i)` — NOT `s[i]` (that's C)
8. Primitives (`char`, `int`) have no `.methods()` → use `Character.xxx(c)`
9. `Character.isLetterOrDigit(c)`, `Character.toLowerCase(c)`
10. Is c in a set: `"aeiou".indexOf(c) != -1`
11. Print array: `Arrays.toString(arr)` (println(arr) prints an address)
12. Strings are immutable — changing one builds a NEW string (O(n))

### Control Flow / Math
13. Scope: a variable declared inside `{ }` dies at the closing `}`
14. `continue;` → skip to next loop iteration
15. `Math.max(a, b)` / `Math.min(a, b)`
16. `int / int` drops decimals → `(double) a / b`
17. `return x;` instead of `if (x) return true; else return false;`

### Running
18. Run one file: `java FileName.java` (bypasses VS Code build errors)
19. "Could not find or load main class" = compile failed → check Problems tab

### HashSet / Windows
20. `Set<Character> set = new HashSet<>();` → `add`, `remove`, `contains` (all O(1))
21. Window length = `right - left + 1` (both ends included)

## Big O Rules

### Time
- Sequential steps add → biggest term wins: O(n log n) + O(n) = O(n log n)
- Nested loops multiply → O(n²)
- Order: O(1) < O(log n) < O(n) < O(n log n) < O(n²)
- Halving each step → O(log n). Sorting → O(n log n)
- n ≤ 10⁵ → O(n²) fails, aim for O(n) or O(n log n)
- A while inside a for is NOT O(n²) if the inner pointer never resets
  → count TOTAL pointer moves

### Space
- Count only what YOU create, not the input
- Few variables → O(1)
- Map/array that can grow with n → O(n)
- Map/set with keys from a fixed set (26 letters, 128 ASCII) → O(1)
- Recursion depth d → O(d) call stack

## Pattern Templates

### 1. HashMap
Trigger: brute force = "for each element, search the rest"
- Lookup (Two Sum): check `containsKey(need)` BEFORE `put`
- Counting: pass 1 builds counts, pass 2 uses them
- "All must pass": return false on first failure, true AFTER loop

### 2a. Two Pointers — opposite ends
Trigger: sorted array pairs, palindrome, compare from both ends
    int left = 0, right = n - 1;
    while (left < right) { ... left++ or right-- ... }
Every path must move a pointer or exit (else infinite loop)

### 2b. Two Pointers — read/write
Trigger: "in place", "O(1) space", remove/move/filter
    int w = 0;
    for (int r = 0; r < n; r++) {
        if (keep nums[r]) { nums[w] = nums[r]; w++; }
    }
R moves every step, W only on keep. Copy THEN w++.

### 3a. Sliding Window — fixed size k
Trigger: "subarray/substring of length k"
    // build first window [0..k-1]
    int left = 0;
    for (int right = k; right < n; right++) {
        // add s[right], remove s[left], left++, update max
    }
Track ONE number (sum/count), never the window contents.

### 3b. Sliding Window — variable size
Trigger: "longest/shortest subarray/substring such that...", "at most k"
    int left = 0, best = 0;   // + counter for what BREAKS the window
    for (int right = 0; right < n; right++) {
        // add nums[right] to counter
        while (window broken) {
            // remove nums[left] from counter
            left++;
        }
        best = Math.max(best, right - left + 1);
    }
Counter = what BREAKS the window. Answer = right - left + 1.
O(n): left never resets → 2n total moves.

## Problems Solved
| # | Problem | Pattern | File |
|---|---|---|---|
| 1 | First Unique Character (LC 387) | HashMap — counting | 01_hashmap/FirstUniqChar.java |
| 2 | Ransom Note (LC 383) | HashMap — counting | 01_hashmap/RansomNote.java |
| 3 | Valid Anagram (LC 242) | HashMap — map equality | 01_hashmap/ValidAnagram.java |
| 4 | Valid Palindrome (LC 125) | Two Pointers — opposite | 02_two_pointers/ValidPalindrome.java |
| 5 | Move Zeroes (LC 283) | Two Pointers — read/write | 02_two_pointers/MoveZeroes.java |
| 6 | Max Vowels in Substring (LC 1456) | Sliding Window — fixed | 03_sliding_window/MaxVowels.java |
| 7 | Max Consecutive Ones III (LC 1004) | Sliding Window — variable | 03_sliding_window/LongestOnes.java |