package com.shubham.app.binarysearch;

import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // int[][] matrix = new int[][]{
        // {1, 3, 5, 7},
        // {10, 11, 16, 20},
        // {23, 30, 33, 60}
        // };

        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int[] ans = solution.searchRange(nums, 8);

        int[][] matrix = new int[][]{{1, 3, 5}};

        // boolean ans = solution.searchMatrix(matrix, 1);
        System.out.println("ans : " + Arrays.toString(ans));

        // if(ans[0] != -1){
        // System.out.println("first elemnt = ");
        // }

    }
}
