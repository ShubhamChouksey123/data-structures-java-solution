package com.shubham.app.math;

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

        double ans = solution.myPow(1.0000000000002, -2147483648);
        System.out.println("ans : " + ans);

        // int ans = solution.mySqrt(12);
        // System.out.println("ans : " + ans);
        //
        // for (int i = 1; i <= 100; i++) {
        // System.out.println("for i : " + i + " sqrt of it is " + solution.mySqrt(i));
        // }

    }
}
