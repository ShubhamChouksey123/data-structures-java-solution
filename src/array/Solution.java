package array;

import java.util.Objects;
import java.util.Stack;

public class Solution {


    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int tmp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = tmp;
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

    private boolean isTopCornerZero(int[][] matrix, int m, int n) {

        if (matrix[0][0] == 0)
            return true;

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0)
                return true;
        }

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0)
                return true;
        }

        return false;
    }

    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        boolean isCornerElementZero = isTopCornerZero(matrix, m, n);
        int originalValue = matrix[0][0];


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        matrix[0][0] = originalValue;
        System.out.println("Before everything");
        print(matrix);

        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                setEntireColumnZero(matrix, j, m);
            }
        }

        System.out.println("Before everything");
        print(matrix);

        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                setEntireRowZero(matrix, i, n);
            }
        }


        System.out.println("After 1");
        print(matrix);

        if (isCornerElementZero) {
            matrix[0][0] = 0;
        }


        System.out.println("Answer");
        print(matrix);

    }

    private void setEntireColumnZero(int[][] matrix, int column, int m) {

        for (int i = 0; i < m; i++) {
            matrix[i][column] = 0;
        }
    }

    private void setEntireRowZero(int[][] matrix, int row, int n) {
        for (int j = 0; j < n; j++) {
            matrix[row][j] = 0;
        }
    }

    private boolean isValidClosing(char openingBracket, char closingBracket) {

        if (openingBracket == '(' && closingBracket == ')')
            return true;
        if (openingBracket == '{' && closingBracket == '}')
            return true;
        if (openingBracket == '[' && closingBracket == ']')
            return true;

        return false;
    }

    public boolean isValid(String s) {

        int n = s.length();

        if (n % 2 != 0)
            return false;

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Objects.equals(c, '(') || Objects.equals(c, '{') || Objects.equals(c, '[')) {
                st.push(c);
                continue;
            }

            if (st.isEmpty()) {
                return false;
            }
            char openingBracket = st.pop();

            if (!isValidClosing(openingBracket, c)) {
                return false;
            }
        }

        if(st.isEmpty())
            return true;

        return false;

    }
}

