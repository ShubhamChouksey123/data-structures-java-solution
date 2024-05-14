package com.shubham.app.matrix;

import java.util.Arrays;

public class Solution {

    public int[][] largestLocal(int[][] grid) {

        int n = grid.length;

        int[][] arr = new int[n - 2][n - 2];

        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                arr[i][j] = maxValueInMatrix(grid, i, i + 2, j, j + 2);
            }
        }
        System.out.println("arr : " + Arrays.deepToString(arr));
        return arr;
    }

    public int maxValueInMatrix(int[][] grid, int rowStart, int rowEnd, int columnStart, int columnEnd) {

        int maxvalue = grid[rowStart][columnStart];

        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = columnStart; j <= columnEnd; j++) {
                maxvalue = Math.max(maxvalue, grid[i][j]);
            }
        }

        return maxvalue;
    }

    private void flipEntireRow(int[][] grid, int row, int m, int n) {

        for (int j = 0; j < n; j++) {
            if (grid[row][j] == 0) {
                grid[row][j] = 1;
            } else {
                grid[row][j] = 0;
            }
        }
    }

    private boolean isColumnFlipRequired(int[][] grid, int column, int m, int n) {

        int zeroCount = 0;
        for (int i = 0; i < m; i++) {
            if (grid[i][column] == 0)
                zeroCount++;
        }
        return zeroCount > m / 2;
    }

    private void flipEntireColumn(int[][] grid, int column, int m, int n) {

        for (int i = 0; i < m; i++) {
            if (grid[i][column] == 0) {
                grid[i][column] = 1;
            } else {
                grid[i][column] = 0;
            }
        }
    }

    private int calculateNumericalValue(int[][] grid, int row, int m, int n) {

        int sum = 0;
        for (int j = 0; j < n; j++) {
            if (grid[row][j] == 1) {
                sum = sum + (int) Math.pow(2, n - j - 1);
            }
        }
        return sum;
    }

    /**
     * i and m j and n
     *
     * @param grid
     * @return
     */
    public int matrixScore(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0) {
                flipEntireRow(grid, i, m, n);
            }
        }

        for (int j = 0; j < n; j++) {
            if (isColumnFlipRequired(grid, j, m, n)) {
                flipEntireColumn(grid, j, m, n);
            }
        }

        int totalScore = 0;
        for (int i = 0; i < m; i++) {
            totalScore += calculateNumericalValue(grid, i, m, n);
        }

        return totalScore;
    }
}
