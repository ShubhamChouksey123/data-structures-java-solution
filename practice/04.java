/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    private static final int TOTAL_CHARS = 26;

    private static Set<String> findDistinctPermutations(String template, String pattern){

        int n = template.length();
        int m = pattern.length();
        if(n > m) return new HashSet<>();

        int[] countInTemplate = new int[TOTAL_CHARS];
        for(char c : template.toCharArray()){
            countInTemplate[c - 'a']++;
        }

        // each window in pattern (S2) is of length n
        int[] countInWindow = new int[TOTAL_CHARS];
        for(int i = 0; i < n ; i++){
            char c = pattern.charAt(i);
            countInWindow[c - 'a']++;
        }

        // found permutations in pattern (s2) string
        Set<String> foundPermutations = new HashSet<>();


        int missing = 0;
        for(int i = 0 ; i < TOTAL_CHARS ; i++){
            missing += Math.max(0, countInTemplate[i] - countInWindow[i]);
        }

        if(missing == 0){
            foundPermutations.add(pattern.substring(0, n));
        }

        for(int i = n ; i < m ; i++){
            int removeCharIndex = pattern.charAt(i - n) - 'a';

            if(countInTemplate[removeCharIndex] >= countInWindow[removeCharIndex] ){
                missing++;
            }
            countInWindow[removeCharIndex]--;

            int newCharIndex = pattern.charAt(i) - 'a';
            if(countInTemplate[newCharIndex] > countInWindow[newCharIndex] ){
                missing--;
            }
            countInWindow[newCharIndex]++;

            String permutation = pattern.substring(i - n + 1, i + 1);
            if(missing == 0 && !foundPermutations.contains(permutation)){
                foundPermutations.add(permutation);
            }
        }

        return foundPermutations;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }

        // String s1 = "bat";
        // String s2 = "tabbathat";

        // String s1 = "a";
        // String s2 = "aa";

        String s1 = "aaa";
        String s2 = "aaaa";
        Set<String> foundPermutations = findDistinctPermutations(s1, s2);
        System.out.println("foundPermutations : " + foundPermutations);
    }
}

// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # Sub Permutations

// Given two strings, `s1` and `s2`, count how many permutations of `s1` are substrings in `s2`. A permutation of a string `s` is a string with the same letters of `s`, in any order. Assume that `n1 <= n2`, where `n1` is the length of `s1` and `n2` is the length of `s2`.

// Example 1: s1 = "bat", s2 = "tabbathat"
// Output: 2. The permutations are "tab" and "bat".

// Example 2: s1 = "a", s2 = "aa"
// Output: 1. Permutation "a" is counted once even though it appears multiple times.

// Constraints:

// - `1 <= s1.length <= 100`
// - `s1.length <= s2.length <= 10^5`
// - `s1` and `s2` consist of lowercase English letters only

/**
 * Good, I can see you tested it on line 88-89 with s1 = "aaa" and s2 = "aaaa" and it's returning the correct result of 1.
 *
 * Alright, I think we've covered the main aspects of this problem. Let me give you my overall assessment.
 *
 * Would you pass? Yes, I'd give you a pass. You identified the sliding window approach, implemented a working solution, handled deduplication with a HashSet, and your code produces correct results across the test cases.
 *
 * Ratings:
 *
 * Coding ability: 3/4 — Your implementation is clean and works correctly. The missing variable optimization for checking window matches is nice. Minor note: you could simplify by not needing the foundPermutations.contains() check on line 64 since HashSet.add() already handles duplicates.
 *
 * Problem solving ability: 3/4 — You quickly identified the sliding window approach and the need for frequency counting, which is the right direction. You also recognized the need for deduplication. However, you needed some prompting to think through edge cases and complexity.
 *
 * Communication skills: 2.5/4 — You explained your high-level approach well at the start, but at times your explanations were a bit unclear, especially when discussing the missing variable conditions and the complexity analysis. In a real interview, being more precise and structured when walking through your logic would strengthen your performance.
 *
 * Overall, solid work! The key takeaway would be to practice articulating your reasoning more
 *
 *
 *
 * Sure! Here are some tips for your next interview:
 *
 * Think out loud consistently — Even when you're unsure, verbalize your thought process. Interviewers want to see how you reason, not just the final answer. When you paused to think silently about the missing variable logic, it was hard to evaluate your understanding.
 *
 * Trace through examples before coding — Before jumping into code, walk through a concrete example step by step on paper or verbally. This helps catch bugs early and demonstrates your problem-solving process clearly.
 *
 * Know your complexity analysis cold — Be precise about time and space complexity. Use n1 and n2 instead of just "n." Remember that string operations like substring(), hashing, and comparison are O(length), not O(1).
 *
 * Test edge cases proactively — Don't wait for the interviewer to suggest edge cases. Before they ask, mention cases like empty strings, single characters, all identical characters, or when s1 equals s2.
 *
 * Use precise language — Instead of saying "headset" or "we delete the top," use the correct terminology like "HashSet," "remove from the left of the window." Precision in communication signals confidence and competence.
 *
 * Revisit your conditions carefully — When writing tricky logic like your missing counter updates, walk the interviewer through a specific example to prove it works. This builds confidence in your solution.
 *
 * Consider optimizations upfront — Mention potential optimizations even if you
 */
