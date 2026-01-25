package dev.shubham.algorithms.graph;

import java.util.ArrayList;
import java.util.List;

public class SnakeLadder {

    private void snakesAndLadders(int[][] board, int m, int n, List<List<Integer>> graph) {

        for (int i = 0; i < n * m - 1; i++) {
            List<Integer> element = new ArrayList<>();
            element.add(i + 1);
            graph.add(element);
        }

        for (int i = 1; i < n * m; i++) {
            graph.get(i).add(i - 1);
        }

        // int number = m*n;
        boolean isLeftToRight = m % 2 != 0;
        for (int i = 0; i < m; i++) {

            // number--;
            if (isLeftToRight) {

                for (int j = 0; j < n; j++) {
                    int number = ((m - 1 - i) * n) + j;
                    if (board[i][j] != -1) {
                        graph.get(number).add(board[i][j]);
                    }
                }
                isLeftToRight = false;
                continue;
            }
            for (int j = n - 1; j >= 0; i--) {
                int number = ((m - 1 - i) * n) - 1 + (n - j);
                graph.get(number).add(board[i][j]);
            }
        }

        for (int i = 0; i < graph.size(); i++) {
            System.out.println("i -> (" + graph.get(i) + ")");
        }


    }

    public int snakesAndLadders(int[][] board) {

        int m = board.length;
        int n = board[0].length;
        List<List<Integer>> graph = new ArrayList<>();


        snakesAndLadders(board, m, n, graph);
        return 0;
    }
}