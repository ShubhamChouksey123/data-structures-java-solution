package com.shubham.app.backtracking;

import java.util.List;

public class MainClass {

    public static void main(String[] args) {

        int[] nums = new int[]{1};

        Solution solution = new Solution();
        // List<List<Integer>> ans = solution.subsets(nums);

        // List<List<String>> ans = solution.partition("a");
        // for (List<String> a : ans) {
        // System.out.println(a);
        // }

        // int ans = solution.beautifulSubsets(nums, 2);
        // System.out.println("ans : " + ans);

        // String[] words = new String[]{"dog", "cat", "dad", "good"};
        // char[] letters = new char[]{'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'};
        // char[] letters = new char[]{'a', 'a', 'c', 'd'};
        // int[] score = new int[]{1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0,
        // 0, 0, 0,
        // 0, 0, 0, 0, 0};

        // String[] words = new String[]{"add", "dda", "bb", "ba", "add"};
        // char[] letters = new char[]{'a', 'a', 'a', 'a', 'b', 'b', 'b', 'b', 'c', 'c',
        // 'c',
        // 'c', 'c', 'd', 'd', 'd'};
        // int[] score = new int[]{3, 9, 8, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        // 0, 0, 0,
        // 0, 0, 0, 0, 0};
        // int ans = solution.maxScoreWords(words, letters, score);
        // System.out.println("ans : " + ans);

        // String[] wordDict = new
        // String[]{"apple","pen","applepen","pine","pineapple"};
        // List<String> list = List.of(wordDict);
        // List<String> ans = solution.wordBreak("pineapplepenapple", list);
        // System.out.println("ans : " + ans);

        int ans = solution.checkRecord(4);
        System.out.println("ans : " + ans);

        testLetterCombinations();

        testCombine();
    }

    private static void testLetterCombinations() {
        Solution solution = new Solution();
        List<String> ans = solution.letterCombinations("5");
//        ans.forEach(s -> System.out.println(s));
    }

    private static void testCombine() {
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.combine(4, 2);
        ans.forEach(s -> System.out.println(s));


        ans = solution.combine(4, 3);
        ans.forEach(s -> System.out.println(s));
    }
}
