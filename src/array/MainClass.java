package array;

public class MainClass {


    public static void main(String[] args) {

        int nums[] = new int[]{0, 1, 2, 2, 3, 0, 4, 2};

        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 0, 7, 8},
                {0, 1, 1, 1},
                {1, 1, 1, 0}
        };
        print(matrix);

        Solution solution = new Solution();
        solution.setZeroes(matrix);
        System.out.println();

//        print(matrix);
    }

    private static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void print(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
