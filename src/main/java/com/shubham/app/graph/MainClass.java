package com.shubham.app.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // int[][] grid2 = new int[][]{{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9,
        // 0, 20}};
        //
        // int[][] grid = new int[][]{{8, 1, 0, 38, 0, 5}, {0, 27, 18, 36, 8, 15}, {20,
        // 31, 0, 0,
        // 4, 33},
        // {0, 0, 17, 13, 36, 0}, {9, 1, 0, 26, 5, 11}, {0, 0, 19, 14, 24, 7}};
        // int ans = solution.getMaximumGold(grid);
        // System.out.println("\nans : " + ans);

        int[][] grid2 = new int[][]{{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}};

        List<List<Integer>> grid = new ArrayList<>();
        // Arrays.stream(grid2).forEach(p -> {
        // System.out.println(p);
        // System.out.println("p : " + p.getClass());
        // });

        // Integer[] arr1 = new Integer[]{0, 0, 0};
        // Integer[] arr2 = new Integer[]{0, 0, 0};
        // Integer[] arr3 = new Integer[]{1, 0, 0};
        // grid.add(Arrays.asList(arr1));
        // grid.add(Arrays.asList(arr2));
        // grid.add(Arrays.asList(arr3));

        // Integer[] arr1 = new Integer[]{0, 1};
        // Integer[] arr2 = new Integer[]{1, 0};
        // grid.add(Arrays.asList(arr1));
        // grid.add(Arrays.asList(arr2));

        // Integer[] arr1 = new Integer[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
        // Integer[] arr2 = new Integer[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1};
        // Integer[] arr3 = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1};
        // Integer[] arr4 = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1};
        // Integer[] arr5 = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        // Integer[] arr6 = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        // Integer[] arr7 = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        // Integer[] arr8 = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        // Integer[] arr9 = new Integer[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        // Integer[] ar10 = new Integer[]{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        // Integer[] ar11 = new Integer[]{1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0};
        //
        // grid.add(Arrays.asList(arr1));
        // grid.add(Arrays.asList(arr2));
        // grid.add(Arrays.asList(arr3));
        // grid.add(Arrays.asList(arr4));
        // grid.add(Arrays.asList(arr5));
        // grid.add(Arrays.asList(arr6));
        // grid.add(Arrays.asList(arr7));
        // grid.add(Arrays.asList(arr8));
        // grid.add(Arrays.asList(arr9));
        // grid.add(Arrays.asList(ar10));
        // grid.add(Arrays.asList(ar11));

        // int ans = solution.maximumSafenessFactor(grid);
        // System.out.println("\nans : " + ans);

        // char[][] board = new char[][]{
        // {'X', 'X', 'X', 'X'},
        // {'X', 'O', 'O', 'X'},
        // {'X', 'X', 'O', 'X'},
        // {'X', 'O', 'X', 'X'}
        // };

        char[][] board = new char[][]{{'O', 'O', 'O', 'O', 'X', 'X'}, {'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'X', 'O', 'X', 'O', 'O'}, {'O', 'X', 'O', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X', 'O', 'O'},
                {'O', 'X', 'O', 'O', 'O', 'O'}};

        // solution.solve(board);

        int numCourses = 5;
        int[][] prerequisites = new int[][]{{1, 0}};

        // int numCourses = 5;
        // int[][] prerequisites = new int[][]{
        // {1, 2},
        // {2, 3},
        // {2, 4},
        // {4, 1}
        // };
        // solution.canFinish(numCourses, prerequisites);
        //
        //
        // assertFalse(solution.canFinish(5, new int[][]{
        // {1, 2},
        // {2, 3},
        // {2, 4},
        // {4, 1}
        // }));
        //
        // assertFalse(solution.canFinish(2, new int[][]{
        // {1, 0},
        // {0, 1}
        // }));
        // assertTrue(solution.canFinish(2, new int[][]{
        // {1, 0}
        // }));
        //
        // assertTrue(solution.canFinish(20, new int[][]{
        // {1, 2},
        // {2, 3},
        // {2, 4},
        // {4, 6},
        // {4, 5},
        // {6, 11},
        // {13, 14},
        // {14, 16}
        // }));
        //
        // assertTrue(solution.canFinish(5, new int[][]{
        // {3, 1},
        // {3, 2},
        // {1, 4},
        // {2, 4}
        // }));

        int[] ans = solution.findOrder(4, new int[][]{{1, 2}, {2, 3}, {3, 1}});
        System.out.println("ans : " + Arrays.toString(ans));

        ans = solution.findOrder(4, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}});
        System.out.println("ans : " + Arrays.toString(ans));
    }
}

/**
 * [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * [["X"]]
 * [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","X","X","X"]]
 * [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","X","O","X"]]
 */
