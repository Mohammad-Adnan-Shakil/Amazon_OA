import java.util.*;

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> ransomCount = new HashMap<>();
        Map<Character, Integer> magCount = new HashMap<>();

        // Loop 1: count every letter in ransomNote into ransomCount
        // (you wrote this exact loop in FirstUniqChar — same thing)
         for (int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            ransomCount.put(c, ransomCount.getOrDefault(c, 0) + 1);
        }


        // Loop 2: count every letter in magazine into magCount
         for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            magCount.put(c,magCount.getOrDefault(c, 0) + 1);
        }


        // Compare
        for (char c : ransomCount.keySet()) {
            int need = ransomCount.get(c);
            int have = magCount.getOrDefault(c, 0);
            if (have < need) {
                return false;
            }
        }
        return true;
    }
}

/*
 * Problem: Ransom Note (LC 383)
 * Pattern: HashMap — counting + compare two frequency maps
 * Given: ransomNote, magazine (lowercase, up to 10^5)
 * Return: true if note can be built from magazine letters, each used once
 *
 * Time:  O(n + m) — two counting loops + one compare loop, all sequential
 * Space: O(1) — each map holds at most 26 keys
 *
 * MISTAKES I MADE:
 * - Confused KEY with VALUE: wrote need = c (the letter) instead of
 *   ransomCount.get(c) (its count). char -> int silently gives ASCII (97),
 *   no error, just a wrong answer.
 * - Wrote have = magazine (String into int) and compared whole strings
 *   with < instead of comparing the counts.
 * - Put code directly inside the class with no method.
 * - Referenced maps I never declared or filled.
 * - Explained logic in English but stalled converting it to Java.
 *
 * WHAT I LEARNED:
 * - c is the question, map.get(c) is the answer.
 * - "All must pass" checks: return false on the FIRST failure inside
 *   the loop, return true only AFTER the loop.
 * - getOrDefault(c, 0) when the key may not exist in that map.
 * - Iterate map keys: for (char c : map.keySet())
 * - Optimization: one map works too — count magazine, then decrement
 *   for each note letter; if any count goes below 0 -> false.
 *
 * FOCUS FOR NEXT PROBLEM:
 * - Before writing any line, name its TYPE: is this a letter (char),
 *   a count (int), or a whole string (String)? Never mix them.
 */