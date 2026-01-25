package dev.shubham.algorithms.matrix;

public class MainClass {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid = new int[][]{{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};
        // solution.largestLocal(grid);
        int ans = solution.matrixScore(grid);
        System.out.println("ans : " + ans);
    }
}
