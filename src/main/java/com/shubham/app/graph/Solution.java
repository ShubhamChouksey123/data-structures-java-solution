package com.shubham.app.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    private Integer minDistance = Integer.MAX_VALUE;

    public void print(boolean[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void print(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

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


    /**
     * x => i y =? j
     */
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


    private void createMemo(int m, int n, int[][] memo) {

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = Integer.MAX_VALUE;
            }
        }

    }

    private int bfs(int m, int n, int[][] distance, int x, int y, boolean[][] visited, int minDistanceInThisPath, int[][] memo) {

        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || distance[x][y] == 0) {
            return 0;
        }

        if (memo[x][y] != Integer.MAX_VALUE && memo[x][y] != 0) {
            return memo[x][y];
        }

        visited[x][y] = true;
        minDistanceInThisPath = Math.min(minDistanceInThisPath, distance[x][y]);

        if (x == m - 1 && y == n - 1) {
            visited[x][y] = false;
            System.out.println("minDistanceInThisPath : " + minDistanceInThisPath);
            if (minDistance == Integer.MAX_VALUE) {
                minDistance = minDistanceInThisPath;
            } else {
                minDistance = Math.max(minDistanceInThisPath, minDistance);
            }
            return minDistanceInThisPath;
        }

        int maxValue = 0;
        int a = 0, b = 0, c = 0, d = 0;

        if (x + 1 < m && distance[x + 1][y] != 0 && !visited[x + 1][y]) {
            a = distance[x + 1][y];
            maxValue = a;
        }
        if (y + 1 < n && distance[x][y + 1] != 0 && !visited[x][y + 1]) {
            c = distance[x][y + 1];
            maxValue = Math.max(c, maxValue);
        }
        if (x - 1 >= 0 && distance[x - 1][y] != 0 && !visited[x - 1][y]) {
            b = distance[x - 1][y];
            maxValue = Math.max(b, maxValue);
        }
        if (y - 1 >= 0 && distance[x][y - 1] != 0 && !visited[x][y - 1]) {
            d = distance[x][y - 1];
            maxValue = Math.max(d, maxValue);
        }

        if (maxValue == 0) {
            visited[x][y] = false;
            memo[x][y] = 0;
            return memo[x][y];
        }


        int ans1 = 0, ans2 = 0, ans3 = 0, ans4 = 0;
        if (x + 1 < m && a == maxValue) {
            ans1 = bfs(m, n, distance, x + 1, y, visited, minDistanceInThisPath, memo);
        }
        if (y + 1 < n && c == maxValue) {
            ans3 = bfs(m, n, distance, x, y + 1, visited, minDistanceInThisPath, memo);
        }
        if (x - 1 >= 0 && b == maxValue) {
            ans2 = bfs(m, n, distance, x - 1, y, visited, minDistanceInThisPath, memo);
        }
        if (y - 1 >= 0 && d == maxValue) {
            ans4 = bfs(m, n, distance, x, y - 1, visited, minDistanceInThisPath, memo);
        }
        visited[x][y] = false;
        memo[x][y] = Math.max(Math.max(ans1, ans2), Math.max(ans3, ans4));

        return memo[x][y];
    }


    private void createMinDistanceFromOneThief(List<List<Integer>> grid, int m, int n, int[][] distance, int x, int y) {

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int manDist = Math.abs(x - i) + Math.abs(y - j);
                distance[i][j] = Math.min(manDist, distance[i][j]);
            }
        }
    }


    private boolean liesInRange(int m, int n, int x, int y) {
        if (x >= 0 && x < m && y >= 0 && y < n) {
            return true;
        }
        return false;
    }

    private void createMinDistance(List<List<Integer>> grid, int m, int n, int[][] distance) {

        Queue<Integer[]> minQ = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    distance[i][j] = 0;
                    minQ.add(new Integer[]{i, j, 0});
                } else {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int x = 0, y = 0;
        while (!minQ.isEmpty()) {
            Integer[] top = minQ.poll();
            x = top[0];
            y = top[1];
            int minDist = top[2];
            distance[x][y] = minDist;
            if (liesInRange(m, n, x + 1, y) && distance[x + 1][y] == Integer.MAX_VALUE) {
                minQ.add(new Integer[]{x + 1, y, minDist + 1});
            }
            if (liesInRange(m, n, x, y + 1) && distance[x][y + 1] == Integer.MAX_VALUE) {
                minQ.add(new Integer[]{x, y + 1, minDist + 1});
            }
            if (liesInRange(m, n, x - 1, y) && distance[x - 1][y] == Integer.MAX_VALUE) {
                minQ.add(new Integer[]{x - 1, y, minDist + 1});
            }
            if (liesInRange(m, n, x, y - 1) && distance[x][y - 1] == Integer.MAX_VALUE) {
                minQ.offer(new Integer[]{x, y - 1, minDist + 1});
            }
        }
    }

    private int dAlgorithm(int m, int n, int[][] distance) {


        PriorityQueue<int[]> maxPQ = new PriorityQueue<>((int[] o1, int[] o2) -> (o2[0] - o1[0]));
        maxPQ.offer(new int[]{distance[0][0], 0, 0});

        int x = 0, y = 0, minValue = Integer.MAX_VALUE;
        boolean haveReachedEnd = false;
        while (!maxPQ.isEmpty()) {

            int[] top = maxPQ.poll();
//            System.out.println("we are at {" + x + " ," + y + "} and value is " + distance[x][y]);
            x = top[1];
            y = top[2];
            minValue = Math.min(distance[x][y], minValue);
            if (x == m - 1 && y == n - 1) {
                haveReachedEnd = true;
                break;
            }

            if (liesInRange(m, n, x + 1, y) && distance[x + 1][y] != 0) {
                maxPQ.add(new int[]{distance[x + 1][y], x + 1, y});
            }
            if (liesInRange(m, n, x, y + 1) && distance[x][y + 1] != 0) {
                maxPQ.add(new int[]{distance[x][y + 1], x, y + 1});
            }
            if (liesInRange(m, n, x - 1, y) && distance[x - 1][y] != 0) {
                maxPQ.add(new int[]{distance[x - 1][y], x - 1, y});
            }
            if (liesInRange(m, n, x, y - 1) && distance[x][y - 1] != 0) {
                maxPQ.add(new int[]{distance[x][y - 1], x, y - 1});
            }
        }
        if (minValue == Integer.MAX_VALUE || !haveReachedEnd) {
            return 0;
        }
        return minValue;

    }

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int m = grid.size();
        int n = grid.get(0).size();
        int[][] distance = new int[m][n];

        createMinDistance(grid, m, n, distance);

        return dAlgorithm(m, n, distance);
    }


}
