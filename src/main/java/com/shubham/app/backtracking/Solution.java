package com.shubham.app.backtracking;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {

    private Integer count = 0;
    private int mod = 1000000007;
    private int maxScore = 0;
    private List<String> sequences = new ArrayList<>();
    private int totalCount = 0;

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

    private void convertToString(List<String> currentSeq) {
        if (currentSeq.isEmpty())
            return;

        StringBuilder ans = new StringBuilder();
        ans.append(currentSeq.get(0));

        for (int i = 1; i < currentSeq.size(); i++) {
            ans.append(" ");
            ans.append(currentSeq.get(i));
        }
        sequences.add(String.valueOf(ans));
    }

    private void wordBreakUtil(String s, int len, List<String> wordDict, int n, int index, List<String> currentSeq) {

        if (index == len) {
            convertToString(currentSeq);
            return;
        }

        for (int i = 0; i < n; i++) {
            String wordExpected = wordDict.get(i);
            int wordLength = wordExpected.length();
            if (index + wordLength > len) {
                continue;
            }
            String wordActual = s.substring(index, index + wordLength);
            if (Objects.equals(wordActual, wordExpected)) {
                currentSeq.add(wordExpected);
                wordBreakUtil(s, len, wordDict, n, index + wordLength, currentSeq);
                currentSeq.remove(currentSeq.size() - 1);
            }
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        wordBreakUtil(s, s.length(), wordDict, wordDict.size(), 0, new ArrayList<>());
        return sequences;
    }

    private void checkRecordUtil(int n, int index, int countA, int lastLCount) {

        if (index == n) {
            totalCount++;
            totalCount = totalCount % mod;
            // System.out.println("s : " + s);
            return;
        }
        checkRecordUtil(n, index + 1, countA, 0);

        if (countA == 0) {
            checkRecordUtil(n, index + 1, 1, 0);
        }

        if (lastLCount < 2) {
            checkRecordUtil(n, index + 1, countA, lastLCount + 1);
        }
    }

    public int checkRecord(int n) {
        checkRecordUtil(n, 0, 0, 0);
        return totalCount;
    }

    public void letterCombinationsUtil(String digits, int n, int index, String s, char[][] mappedChar, List<String> ans) {

        if (index == n) {
            ans.add(s);
            return;
        }

        int digit = digits.charAt(index) - '2';

        for (int i = 0; i < mappedChar[digit].length; i++) {
            letterCombinationsUtil(digits, n, index + 1, s + mappedChar[digit][i], mappedChar, ans);
        }
    }

    public List<String> letterCombinations(String digits) {

        List<String> ans = new ArrayList<>();
        int n = digits.length();
        if (n == 0) {
            return ans;
        }


        char[][] mappedChar = new char[][]{
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'},
        };

        letterCombinationsUtil(digits, n, 0, "", mappedChar, ans);

        return ans;
    }

    public void combineUtils(int n, int k, int index, int start, List<Integer> combination, List<List<Integer>> ans) {

        if (index == k) {
            if (combination.size() == k) {
                ans.add(new ArrayList<>(combination));
            }
            return;
        }

        for (int i = start; i <= n; i++) {
            combination.add(i);
            combineUtils(n, k, index + 1, i + 1, combination, ans);
            combination.remove(combination.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> ans = new ArrayList<>();
        combineUtils(n, k, 0, 1, new ArrayList<>(), ans);

        return ans;
    }

}
