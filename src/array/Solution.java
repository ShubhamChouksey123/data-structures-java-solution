package array;

import java.util.*;

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

        boolean[] isRowZero = new boolean[m];


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    isRowZero[i] = true;
                }
            }
        }

        System.out.println("After Setting top Row as zero");
        print(matrix);

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                setEntireColumnZero(matrix, j, m);
            }
        }

        System.out.println("Setting entire column zero");
        print(matrix);

        for (int i = 0; i < m; i++) {
            if (isRowZero[i]) {
                setEntireRowZero(matrix, i, n);
            }
        }


        System.out.println("Setting entire row zero");
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

        if (st.isEmpty())
            return true;

        return false;

    }

    public int longestConsecutive(int[] nums) {

        Set<Integer> st = new HashSet<>();

        for (int i : nums) {
            st.add(i);
        }

        int n = nums.length;
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (st.contains(num - 1)) {
                continue;
            }

            int length = 1;
            while (st.contains(num + 1)) {
                num = num + 1;
                length++;
            }
            maxLength = Math.max(length, maxLength);
        }

        System.out.println("maxLength : " + maxLength);
        return maxLength;
    }

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> valueIndexMap = new HashMap<>();

        int[] ans = new int[2];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (valueIndexMap.containsKey(target - num)) {
                ans[0] = i;
                ans[1] = valueIndexMap.get(target - num);
                break;
            }

            valueIndexMap.put(num, i);
        }
        return ans;
    }
}

