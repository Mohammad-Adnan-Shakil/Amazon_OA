# Amazon OA Prep

## Java Syntax Cheat Sheet
1. Count frequency: `map.put(x, map.getOrDefault(x, 0) + 1);`
2. Get value: `map.get(key)` → returns null if missing; use `getOrDefault` when unsure
3. Length: `arr.length` | `s.length()` | `list.size()`
4. Scope: a variable declared inside `{ }` dies at the closing `}`

## Big O Rules
- Sequential steps add → biggest term wins: O(n log n) + O(n) = O(n log n)
- Nested loops multiply → O(n²)
- Order: O(1) < O(log n) < O(n) < O(n log n) < O(n²)
- n ≤ 10⁵ → O(n²) fails, aim for O(n) or O(n log n)
- Fixed-size map (e.g. 26 letters) = O(1) space