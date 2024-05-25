package com.shubham.app.backtracking;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {

    private Integer count = 0;
    private int maxScore = 0;

    private void subsets(int[] nums, List<List<Integer>> ans, int index, int n, List<Integer> st) {

        if (index == n) {
            ans.add(new ArrayList<>(st));
            return;
        }

        subsets(nums, ans, index + 1, n, st);

        st.add(nums[index]);
        subsets(nums, ans, index + 1, n, st);

        st.remove(st.size() - 1);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        subsets(nums, ans, 0, nums.length, new ArrayList<>());
        return ans;
    }

    private void isPalindrome(String str1, String str2) {
        isPalindrome(str1 + str2);
    }

    private boolean isPalindrome(String str) {
        int n = str.length();
        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private void partitionUtil(String s, List<List<String>> ans, List<String> cur, int index, int n) {

        if (index == n) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        for (int i = index; i < n; i++) {
            String str = s.substring(index, i + 1);
            if (!isPalindrome(str))
                continue;

            cur.add(str);
            partitionUtil(s, ans, cur, i + 1, n);
            cur.remove(cur.size() - 1);
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        partitionUtil(s, ans, new ArrayList<>(), 0, s.length());
        return ans;
    }

    private boolean isValidSubSet(List<Integer> elements, int number, Integer k) {

        if (elements == null || elements.isEmpty()) {
            return true;
        }

        for (Integer ele : elements) {
            if (Objects.equals(k, Math.abs(ele - number))) {
                return false;
            }
        }
        return true;
    }

    private void beautifulSubsetUtil(int[] nums, int k, int n, int index, List<Integer> elements) {
        if (index == n) {
            if (!elements.isEmpty()) {
                System.out.println("elements :" + elements);
                count++;
            }

            return;
        }

        if (isValidSubSet(elements, nums[index], k)) {
            elements.add(nums[index]);
            beautifulSubsetUtil(nums, k, n, index + 1, elements);
            elements.remove(elements.size() - 1);
        }

        beautifulSubsetUtil(nums, k, n, index + 1, elements);
    }

    public int beautifulSubsets(int[] nums, int k) {
        beautifulSubsetUtil(nums, k, nums.length, 0, new ArrayList<>());
        return count;
    }

    private boolean isPossible(String word, Integer[] letters) {

        Map<Character, Long> wordCharCount = word.chars().mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return wordCharCount.entrySet().stream().allMatch(c -> {
            int val = c.getKey() - 'a';
            int countActual = Math.toIntExact(c.getValue());
            Integer countMax = letters[val];
            return countActual <= countMax;
        });

        // Stream<Character> sch = word.chars().mapToObj(i -> (char) i);

        // sch.forEach(c -> {
        // System.out.println("character c : " + c);
        // });

        // sch.map

        // Map<Object, Long> mp = sch.collect(Collectors.groupingBy(e -> e,
        // Collectors.counting()));
        // System.out.println("mp : " + mp);
    }

    private void reduceCount(String word, Integer[] letters) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            letters[c - 'a']--;
        }
    }

    private void increaseCount(String word, Integer[] letters) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            letters[c - 'a']++;
        }
    }

    private int calculateScore(String word, int[] score) {
        int totalScore = 0;
        for (int i = 0; i < word.length(); i++) {
            totalScore += score[word.charAt(i) - 'a'];
        }
        return totalScore;
    }

    private void maxScoreWordsUtil(String[] words, Integer[] letterCount, int[] score, int n, int currentScore) {

        for (int i = 0; i < n; i++) {
            String word = words[i];
            if (isPossible(word, letterCount)) {
                reduceCount(word, letterCount);
                int thisScore = calculateScore(word, score);
                maxScoreWordsUtil(words, letterCount, score, n, currentScore + thisScore);
                increaseCount(word, letterCount);
            }
        }

        maxScore = Math.max(maxScore, currentScore);
    }

    private Integer[] getLetterCount(char[] letters) {
        Integer[] letterCount = new Integer[26];
        Arrays.fill(letterCount, 0);

        for (int i = 0; i < letters.length; i++) {
            letterCount[letters[i] - 'a']++;
        }
        return letterCount;
    }

    public int maxScoreWords(String[] words, char[] letters, int[] score) {

        Integer[] letterCount = getLetterCount(letters);

        // boolean isPossible = isPossible("cad", letterCount);
        // System.out.println("isPossible : " + isPossible);
        maxScoreWordsUtil(words, letterCount, score, words.length, 0);
        return maxScore;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        return null;
    }
}
