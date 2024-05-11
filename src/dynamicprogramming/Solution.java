package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }

        int last = 2;
        int secondLast = 1;
        int current = 0;
        for (int i = 3; i <= n; i++) {
            current = last + secondLast;
            secondLast = last;
            last = current;
        }
        return current;
    }


    public int rob(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return nums[0];
        }

        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        dp[1][0] = nums[0];
        dp[1][1] = nums[1];

        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = nums[i] + dp[i - 1][0];
        }
        System.out.println("nums : " + Arrays.toString(nums));
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    public int coinChange(int[] coins, int amount) {

        int k = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

//        for (int j = 0; j < k; j++) {
//            dp[coins[j]] = 1;
//        }

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < k; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
                }
            }
        }

        System.out.println("dp : " + Arrays.toString(dp));
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1;
        }

        return dp[amount];

    }


    public int lengthOfLIS(int[] nums) {

        int n = nums.length;
        /**
         * where longestSubSequence[i] represent longest subsequence start from index i
         */
        int[] longestSubSequence = new int[n];
        longestSubSequence[n - 1] = 1;
        int ans = 1;

        for (int i = n - 2; i >= 0; i--) {
            longestSubSequence[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (nums[i] < nums[j]) {
                    longestSubSequence[i] = Math.max(longestSubSequence[j] + 1, longestSubSequence[i]);
                }
            }
            ans = Math.max(ans, longestSubSequence[i]);
        }
        System.out.println("longestSubSequence : " + Arrays.toString(longestSubSequence));
        return longestSubSequence[0];
    }


    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();
        List<List<Integer>> sumTilNow = new ArrayList<>();
//        Arrays.fill(sumTilNow, Integer.MAX_VALUE);

        for (int i = 0; i < triangle.size(); i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < triangle.get(i).size(); j++) {
                list.add(Integer.MAX_VALUE);
            }
            sumTilNow.add(list);
        }
        int corner = triangle.get(0).get(0);
        sumTilNow.get(0).set(0, corner);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {

                int element = triangle.get(i).get(j);
                if (j != 0) {
                    int val = Math.min(sumTilNow.get(i).get(j), sumTilNow.get(i - 1).get(j - 1) + element);
                    sumTilNow.get(i).set(j, val);
                }
                if (i != j) {
                    int val = Math.min(sumTilNow.get(i).get(j), sumTilNow.get(i - 1).get(j) + element);
                    sumTilNow.get(i).set(j, val);
                }
//                if (j + 1 < i) {
//                    int val = Math.min(sumTilNow.get(i).get(j), sumTilNow.get(i - 1).get(j + 1) + element);
//                    sumTilNow.get(i).set(j, val);
//                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.min(ans, sumTilNow.get(n - 1).get(j));
        }

        return ans;
    }


    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] pathSum = new int[m][n];
        pathSum[0][0] = grid[0][0];
//        Arrays.fill(pathSum, -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    continue;
                int val = grid[i][j];
                int a = Integer.MAX_VALUE;
                int b = Integer.MAX_VALUE;
                if (liesInRange(m, n, i - 1, j)) {
                    a = pathSum[i - 1][j] + val;
                }
                if (liesInRange(m, n, i, j - 1)) {
                    b = pathSum[i][j - 1] + +val;
                }
                pathSum[i][j] = Math.min(a, b);
            }
        }

        System.out.println("pathSum  : " + Arrays.deepToString(pathSum));
        return pathSum[m - 1][n - 1];

    }

    public boolean liesInRange(int m, int n, int x, int y) {
        if (x >= 0 && x < m && y >= 0 && y < n)
            return true;
        return false;
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        int[][] uniquePaths = new int[m][n];
        uniquePaths[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                int totalPath = 0;
                if (liesInRange(m, n, i - 1, j) && obstacleGrid[i - 1][j] == 0) {
                    totalPath += uniquePaths[i - 1][j];
                }
                if (liesInRange(m, n, i, j - 1) && obstacleGrid[i][j - 1] == 0) {
                    totalPath += uniquePaths[i][j - 1];
                }
                uniquePaths[i][j] = totalPath;
            }
        }
        return uniquePaths[m - 1][n - 1];

    }


    public String longestPalindrome(String s) {

        int n = s.length();
        boolean[][] longestPalLength = new boolean[n][n];

        int maxLength = 1;
        int maxLengthIndex = 0;
        for (int i = 0; i < n; i++) {
            longestPalLength[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                longestPalLength[i][i + 1] = true;
                maxLength = 2;
                maxLengthIndex = i;

            }
        }

        for (int len = 3; len <= n; len++) {
            int start = 0;
            for (int j = 0; j <= n - len; j++) {
                if (s.charAt(start) == s.charAt(start + len - 1) && longestPalLength[start + 1][start + len - 2]) {
                    longestPalLength[start][start + len - 1] = true;
                    if (len > maxLength) {
                        maxLength = len;
                        maxLengthIndex = start;
                    }

                }
                start++;
            }
        }
        System.out.println("maxLength : " + maxLength);
//        System.out.println("string : " + s.substring(maxLengthIndex, maxLengthIndex + maxLength));


        return s.substring(maxLengthIndex, maxLengthIndex + maxLength);
    }

}
