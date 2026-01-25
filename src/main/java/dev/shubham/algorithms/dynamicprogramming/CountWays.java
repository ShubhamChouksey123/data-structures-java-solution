package dev.shubham.algorithms.dynamicprogramming;

public class CountWays {

    private static int countWaysUtils(int n, int k) {

        if (k == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        if (n == 1 && k >= n) {
            return 1;
        }

        int ways = 0;
        for (int i = 1; i <= k; i++) {
            ways += countWaysUtils(n - i, k);
        }
        return ways;
    }

    public static void main(String[] args) {
        int ans = countWaysUtils(2, 2);
        System.out.println("ans : " + ans);
    }


}
