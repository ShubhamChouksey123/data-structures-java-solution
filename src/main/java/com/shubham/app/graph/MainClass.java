package com.shubham.app.graph;

public class MainClass {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid2 = new int[][]{{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}};

        int[][] grid = new int[][]{{8, 1, 0, 38, 0, 5}, {0, 27, 18, 36, 8, 15}, {20, 31, 0, 0, 4, 33},
                {0, 0, 17, 13, 36, 0}, {9, 1, 0, 26, 5, 11}, {0, 0, 19, 14, 24, 7}};
        int ans = solution.getMaximumGold(grid);
        System.out.println("\nans : " + ans);
    }
}
