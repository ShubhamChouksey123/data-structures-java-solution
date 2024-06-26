package com.shubham.app.math;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainClass {

    public static void main(String[] args) {

        int[] digits = new int[]{10, 3, 8, 9, 4};
        Solution solution = new Solution();
        // int[] ans = solution.plusOne(digits);
        // System.out.println("ans : " + Arrays.toString(ans));

        // String[] ans = solution.findRelativeRanks(digits);
        // System.out.println("ans : " + Arrays.toString(ans));

        // int ans = solution.trailingZeroes(36);
        // System.out.println("ans : " + ans);

        // double ans = solution.myPow(1.0000000000002, -2147483648);
        // System.out.println("ans : " + ans);

        // int ans = solution.mySqrt(12);
        // System.out.println("ans : " + ans);
        //
        // for (int i = 1; i <= 100; i++) {
        // System.out.println("for i : " + i + " sqrt of it is " + solution.mySqrt(i));
        // }

        // int[][] points = new int[][]{{1, 1}, {2, 2}, {3, 3}};

        // int[][] points = new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        // int ans = solution.maxPoints(points);
        // System.out.println("ans : " + ans);

        double ans = solution.myPow(-1.00000, Integer.MAX_VALUE);
        System.out.println("ans : " + ans);


        testJudgeSquareSum();
    }

    private static void testJudgeSquareSum() {
        Solution solution = new Solution();
        assertTrue(solution.judgeSquareSum(0));
        assertTrue(solution.judgeSquareSum(1));
        assertTrue(solution.judgeSquareSum(2));

        assertTrue(solution.judgeSquareSum(4));
        assertTrue(solution.judgeSquareSum(5));

        assertFalse(solution.judgeSquareSum(6));
        assertFalse(solution.judgeSquareSum(7));
        assertFalse(solution.judgeSquareSum(8));

        assertTrue(solution.judgeSquareSum(9));


    }

}
