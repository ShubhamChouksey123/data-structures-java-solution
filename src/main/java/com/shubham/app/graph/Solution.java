package com.shubham.app.graph;

import java.util.*;

public class Solution {

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
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

    public void print(List<List<Integer>> matrix) {

        int m = matrix.size();
        int n = matrix.get(0).size();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public void print(char[][] matrix) {

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

    private void createMemo(int m, int n, int[][] memo) {

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    private int bfs(int m, int n, int[][] distance, int x, int y, boolean[][] visited, int minDistanceInThisPath,
            int[][] memo) {

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

        int[][] maxSafeness = new int[m][n];
        for (int[] row : maxSafeness)
            Arrays.fill(row, -1);

        maxSafeness[0][0] = distance[0][0];

        int x = 0, y = 0, d = Integer.MAX_VALUE;
        while (!maxPQ.isEmpty()) {

            int[] top = maxPQ.poll();
            x = top[1];
            y = top[2];
            d = top[0];
            // System.out.println("we are at {" + x + " ," + y + "} and value is " +
            // distance[x][y]);

            if (x == m - 1 && y == n - 1) {
                return d;
            }

            for (int[] dir : DIRECTIONS) {
                int xNext = x + dir[0], yNext = y + dir[1];
                if (xNext >= 0 && xNext < m && yNext >= 0 && yNext < n) {
                    int newSafe = Math.min(distance[xNext][yNext], d);
                    if (newSafe > maxSafeness[xNext][yNext]) {
                        maxSafeness[xNext][yNext] = newSafe;
                        // System.out.println("adding {" + xNext + " ," + yNext + "} with
                        // value : " + distance[xNext][yNext]);
                        maxPQ.offer(new int[]{newSafe, xNext, yNext});
                    }
                }
            }
        }
        return -1;
    }

    public int maximumSafenessFactor(int m, int n, int[][] distance) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]); // Sort by decreasing safeness factor
        pq.offer(new int[]{0, 0, distance[0][0]}); // Start from cell (0, 0) with safeness factor 0
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0], c = curr[1], safeness = curr[2];
            if (r == n - 1 && c == n - 1)
                return safeness; // Reached the bottom-right cell
            for (int[] dir : DIRECTIONS) {
                int nr = r + dir[0], nc = c + dir[1];
                if (liesInRange(m, n, nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    int newSafeness = Math.min(safeness, distance[nr][nc]);
                    pq.offer(new int[]{nr, nc, newSafeness});
                }
            }
        }
        return -1; // No valid path exists
    }

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int m = grid.size();
        int n = grid.get(0).size();
        int[][] distance = new int[m][n];

        createMinDistance(grid, m, n, distance);
        print(distance);

        return maximumSafenessFactor(m, n, distance);
    }

    private void numIslandsUtil(char[][] grid, int m, int n, boolean[][] visited, int x, int y) {

        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] == '0') {
            return;
        }

        visited[x][y] = true;

        numIslandsUtil(grid, m, n, visited, x + 1, y);
        numIslandsUtil(grid, m, n, visited, x - 1, y);
        numIslandsUtil(grid, m, n, visited, x, y + 1);
        numIslandsUtil(grid, m, n, visited, x, y - 1);
    }

    public int numIslands(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        int totalIsland = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    numIslandsUtil(grid, m, n, visited, i, j);
                    totalIsland++;
                }
            }
        }
        return totalIsland;
    }

    private void flipToA(char[][] board, int m, int n, boolean[][] visitedDFS, int x, int y) {

        if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] == 'X' || board[x][y] == 'b' || board[x][y] == 'a')
            return;

        if (visitedDFS[x][y])
            return;

        visitedDFS[x][y] = true;
        board[x][y] = 'a';

        flipToA(board, m, n, visitedDFS, x + 1, y);
        flipToA(board, m, n, visitedDFS, x - 1, y);
        flipToA(board, m, n, visitedDFS, x, y + 1);
        flipToA(board, m, n, visitedDFS, x, y - 1);
    }

    private boolean canReachEnd(char[][] board, int m, int n, boolean[][] visited, int x, int y) {

        if (x < 0 || x >= m || y < 0 || y >= n)
            return true;

        if (board[x][y] == 'X')
            return false;

        if (visited[x][y])
            return false;

        visited[x][y] = true;

        return canReachEnd(board, m, n, visited, x + 1, y) || canReachEnd(board, m, n, visited, x - 1, y)
                || canReachEnd(board, m, n, visited, x, y + 1) || canReachEnd(board, m, n, visited, x, y - 1);
    }

    private void flipToDesired(char[][] board, int m, int n, boolean[][] visitedDFS, int x, int y, char to) {

        if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] == 'X' || board[x][y] == 'b' || board[x][y] == 'a')
            return;

        if (visitedDFS[x][y])
            return;

        visitedDFS[x][y] = true;
        board[x][y] = to;

        flipToDesired(board, m, n, visitedDFS, x + 1, y, to);
        flipToDesired(board, m, n, visitedDFS, x - 1, y, to);
        flipToDesired(board, m, n, visitedDFS, x, y + 1, to);
        flipToDesired(board, m, n, visitedDFS, x, y - 1, to);
    }

    public void solve(char[][] board) {

        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {

                if (board[i][j] == 'O' && !visited[i][j]) {

                    boolean[][] visitedDFS = new boolean[m][n];
                    if (!canReachEnd(board, m, n, visited, i, j)) {
                        flipToDesired(board, m, n, visitedDFS, i, j, 'b');
                    } else {
                        flipToDesired(board, m, n, visitedDFS, i, j, 'a');
                    }
                }
            }
        }

        print(board);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'b') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'a') {
                    board[i][j] = 'O';
                }
            }
        }
        System.out.println();
        print(board);
    }

    private void createMapOfNode(Node node, Map<Node, Node> mappedNodes) {

        if (node == null)
            return;

        if (mappedNodes.containsKey(node)) {
            return;
        }
        Node mappedNode = new Node(node.val);
        mappedNodes.put(node, mappedNode);

        for (Node nodeSide : node.neighbors) {
            createMapOfNode(nodeSide, mappedNodes);
        }
    }

    private void connectCopyGraph(Node nodeOriginal, Map<Node, Node> mappedNodes, Set<Node> isVisited) {
        if (nodeOriginal == null)
            return;

        if (isVisited.contains(nodeOriginal)) {
            return;
        }

        isVisited.add(nodeOriginal);

        Node copyNode = mappedNodes.get(nodeOriginal);
        List<Node> neighborsCopy = new ArrayList<>();

        for (Node nodeSideOriginal : nodeOriginal.neighbors) {
            Node nodeSideCopy = mappedNodes.get(nodeSideOriginal);
            neighborsCopy.add(nodeSideCopy);
        }
        copyNode.neighbors = neighborsCopy;

        for (Node nodeSideOriginal : nodeOriginal.neighbors) {
            connectCopyGraph(nodeSideOriginal, mappedNodes, isVisited);
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> mappedNodes = new HashMap<>();
        createMapOfNode(node, mappedNodes);

        // System.out.println(mappedNodes);

        connectCopyGraph(node, mappedNodes, new HashSet<>());

        return mappedNodes.get(node);
    }

    private boolean isCycleExists(int startCourse, List<List<Integer>> graph, boolean[] visited) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startCourse);

        while (!queue.isEmpty()) {
            int course = queue.poll();
            if (visited[course]) {
                return true;
            }
            visited[course] = true;
            queue.addAll(graph.get(course));
        }
        return false;
    }

    private void createGraph(List<List<Integer>> graph, int[][] prerequisites, int numCourses) {

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preCourse = prerequisite[1];
            graph.get(course).add(preCourse);
        }
    }

    private boolean isCycleExists(List<List<Integer>> graph, int[] state, int startCourse) {

        if (state[startCourse] == 1) {
            return true;
        }
        if (state[startCourse] == 2) {
            return false;
        }

        state[startCourse] = 1;
        for (Integer preCourse : graph.get(startCourse)) {
            if (isCycleExists(graph, state, preCourse)) {
                return true;
            }
        }
        state[startCourse] = 2;
        return false;
    }

    /**
     * if there exists a cycle in the directed graph return false else true
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        createGraph(graph, prerequisites, numCourses);

        int[] state = new int[numCourses];

        for (int course = 0; course < numCourses; course++) {
            if (state[course] == 0 && isCycleExists(graph, state, course)) {
                return false;
            }
        }

        return true;
    }

    private boolean isCycleExist(List<List<Integer>> graph, int startCourse, int[] state, List<Integer> correctOrder) {

        if (state[startCourse] == 1) {
            return true;
        }

        if (state[startCourse] == 2)
            return false;

        state[startCourse] = 1;

        for (Integer preCourse : graph.get(startCourse)) {
            if (isCycleExist(graph, preCourse, state, correctOrder)) {
                return true;
            }
        }
        correctOrder.add(startCourse);
        state[startCourse] = 2;
        return false;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();
        createGraph(graph, prerequisites, numCourses);
        System.out.println(graph);

        int[] state = new int[numCourses];
        List<Integer> correctOrder = new ArrayList<>();

        for (int course = 0; course < numCourses; course++) {
            if (state[course] == 0 && isCycleExist(graph, course, state, correctOrder)) {
                return new int[0];
            }
        }

        int[] order = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            order[i] = correctOrder.get(i);
        }
        return order;
    }
}
