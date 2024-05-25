package com.shubham.app;

import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;

public class MainClass {

    /**
     * Given a string s and a dictionary of strings wordDict, return true if s can
     * be segmented into a space-separated sequence of one or more dictionary words.
     *
     * <p>
     * Note that the same word in the dictionary may be reused multiple times in the
     * segmentation.
     */
    public static void main(String[] args) {
        System.out.println("started");

        String[] wordDict = new String[]{"cats", "dog", "sand", "and", "cat"};
        String s = "catsanddog";

        // String[] wordDict = new String[]{"leet", "code"};
        // String s = "leetcode";

        boolean isPossible = isSegmentPossible(wordDict, s);
        System.out.println("isPossible : " + isPossible);
    }

    private static boolean isSegmentPossible(String[] wordDict, String s) {
        int len = s.length();
        boolean[] isPossible = new boolean[len];
        PriorityQueue<Integer> minIndexes = new PriorityQueue<>();
        minIndexes.add(-1);
        return isSegmentPossibleUtil(wordDict, wordDict.length, s, s.length(), isPossible, minIndexes);
    }

    private static boolean isSegmentPossibleUtil(String[] wordDict, int n, String s, int len, boolean[] isPossible,
            PriorityQueue<Integer> minIndexes) {

        if (minIndexes.isEmpty()) {
            return false;
        }

        while (!minIndexes.isEmpty()) {
            int index = minIndexes.poll() + 1;

            if (index == len) {
                System.out.println("isPossible : " + Arrays.toString(isPossible));
                return true;
            }

            for (int i = 0; i < n; i++) {
                String dictWord = wordDict[i];
                int lengthOfWord = dictWord.length();
                if (index + lengthOfWord > len) {
                    continue;
                }
                String actualWord = s.substring(index, index + lengthOfWord);
                if (Objects.equals(actualWord, dictWord)) {

                    if (!isPossible[index + lengthOfWord - 1]) {
                        isPossible[index + lengthOfWord - 1] = true;
                        minIndexes.add(index + lengthOfWord - 1);
                    }
                }
            }
        }
        System.out.println("isPossible : " + Arrays.toString(isPossible));
        return isPossible[len - 1];
    }
}
