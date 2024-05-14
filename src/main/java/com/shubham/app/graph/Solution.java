package com.shubham.app.graph;

public class Solution {

    public int find_max(int[][] arr, int[][] visited, int i, int j) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || visited[i][j] == 1 || arr[i][j] == 0)
            return -1000000;

        visited[i][j] = 1;
        int a = find_max(arr, visited, i + 1, j);
        if (a < 0)
            a = 0;
        int b = find_max(arr, visited, i - 1, j);
        if (b < 0)
            b = 0;
        int c = find_max(arr, visited, i, j + 1);
        if (c < 0)
            c = 0;
        int d = find_max(arr, visited, i, j - 1);
        if (d < 0)
            d = 0;
        visited[i][j] = 0;
        int max = Math.max(Math.max(a, b), Math.max(c, d));
        return max + arr[i][j];
    }

    public int getMaximumGold2(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int maxScore = 0;
        boolean[][] visited = new boolean[m][n];
        int[][] memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    // visited[i][j] = true;
                    System.out.println(" ");
                    System.out.println("started from : " + grid[i][j]);
                    System.out.println(" ");
                    int currentScore = getGold(grid, m, n, i, j, visited);
                    maxScore = Math.max(currentScore, maxScore);

                    // visited[i][j] = false;
                }
            }
        }

        return maxScore;
    }

    public int getMaximumGold(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int maxScore = 0;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    int currentScore = getGold(grid, m, n, i, j, visited);
                    maxScore = Math.max(currentScore, maxScore);
                }
            }
        }

        return maxScore;
    }

    private boolean liesInRange(int m, int n, int x, int y) {
        if (x >= 0 && x < m && y >= 0 && y < n) {
            return true;
        }
        return false;
    }

    /** x => i y =? j */
    public int getGold2(int[][] grid, int m, int n, int x, int y, boolean[][] visited, int[][] memo) {

        if (grid[x][y] == 0 || visited[x][y]) {
            return 0;
        }
        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        System.out.print(" " + grid[x][y]);
        visited[x][y] = true;
        int score = grid[x][y];

        int score1 = 0, score2 = 0, score3 = 0, score4 = 0;

        if (liesInRange(m, n, x + 1, y) && grid[x + 1][y] > 0 && !visited[x + 1][y]) {
            score1 = getGold(grid, m, n, x + 1, y, visited);
        }
        if (liesInRange(m, n, x, y + 1) && grid[x][y + 1] > 0 && !visited[x][y + 1]) {
            score2 = getGold(grid, m, n, x, y + 1, visited);
        }
        if (liesInRange(m, n, x - 1, y) && grid[x - 1][y] > 0 && !visited[x - 1][y]) {
            score3 = getGold(grid, m, n, x - 1, y, visited);
        }
        if (liesInRange(m, n, x, y - 1) && grid[x][y - 1] > 0 && !visited[x][y - 1]) {
            score4 = getGold(grid, m, n, x, y - 1, visited);
        }

        visited[x][y] = false;
        memo[x][y] = score + Math.max(Math.max(score1, score2), Math.max(score3, score4));
        return memo[x][y];
    }

    public int getGold(int[][] grid, int m, int n, int x, int y, boolean[][] visited) {

        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0 || visited[x][y]) {
            return 0;
        }

        visited[x][y] = true;

        int score1 = 0, score2 = 0, score3 = 0, score4 = 0;

        score1 = getGold(grid, m, n, x + 1, y, visited);
        score3 = getGold(grid, m, n, x - 1, y, visited);
        score2 = getGold(grid, m, n, x, y + 1, visited);
        score4 = getGold(grid, m, n, x, y - 1, visited);

        visited[x][y] = false;
        return Math.max(Math.max(score1, score2), Math.max(score3, score4)) + grid[x][y];
    }
}
