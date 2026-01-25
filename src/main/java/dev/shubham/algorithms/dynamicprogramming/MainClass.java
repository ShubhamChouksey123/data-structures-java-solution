package dev.shubham.algorithms.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {

        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        Solution solution = new Solution();
        // int ans = solution.rob(nums);

        // int ans = solution.coinChange(nums, 3);
        // System.out.println("ans : " + ans);

        // int ans = solution.lengthOfLIS(nums);
        // System.out.println("ans : " + ans);

        int[][] matrix = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}
                // {4, 1, 8, 3}
        };
        // List<List<Integer>> triangle = new ArrayList<>();
        // for (int i = 0; i < matrix.length; i++) {
        // List<Integer> list = new ArrayList<>();
        // for (int j = 0; j < matrix[i].length; j++) {
        // list.add(matrix[i][j]);
        // }
        // triangle.add(list);
        // }

        // int ans = solution.minimumTotal(triangle);
        // System.out.println("ans : " + ans);

        // int ans = solution.minPathSum(matrix);
        // System.out.println("ans : " + ans);

        // int ans = solution.uniquePathsWithObstacles(matrix);
        // System.out.println("ans : " + ans);

        // String ans = solution.longestPalindrome("cbbd");
        // System.out.println("ans : " + ans);

        String[] st = new String[]{"cats", "dog", "sand", "and", "cat"};
        List<String> wordDict = Arrays.asList(st);

        // boolean ans = solution.wordBreak("catsandog", wordDict);
        // System.out.println("ans : " + ans);

        // boolean ans = solution.isInterleave("aabcc", "dbbca", "aadbbcbcac");
        // boolean ans = solution.isInterleave("ab", "bc", "babc");
        // System.out.println("ans : " + ans);

        int ans = solution.minDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically");
        System.out.println("ans : " + ans);
    }
}
