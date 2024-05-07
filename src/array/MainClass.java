package array;

public class MainClass {


    public static void main(String[] args) {

        int nums1[] = new int[]{5,-3,5};
        int nums2[] = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 0, 7, 8},
                {0, 1, 1, 1},
                {1, 1, 1, 0}
        };
//        print(matrix);

        Solution solution = new Solution();
        Integer ans = solution.maxSubarraySumCircular(nums1);
        //        int[] ans = solution.twoSum(nums1, 9);
        System.out.println("ans : " + ans);

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
