package dev.shubham.algorithms.binarysearch;

public class Solution {

    public int searchInsert(int[] nums, int target, int start, int end) {

        if (start == end && nums[start] == target)
            return start;

        if (start == end) {
            if (nums[start] < target) {
                return start + 1;
            } else {
                return start;
            }
        }

        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (target > nums[mid]) {
            return searchInsert(nums, target, mid + 1, end);
        }
        return searchInsert(nums, target, start, mid);
    }

    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        return searchInsert(nums, target, 0, n - 1);
    }

    private int getRowNumber(int[][] matrix, int target, int m, int n, int start, int end) {

        if (end - start == 1) {
            if (target < matrix[end][0]) {
                return start;
            } else {
                return end;
            }
        }

        if (start == end) {
            return start;
        }

        int mid = (start + end) / 2;

        if (target == matrix[mid][0]) {
            return mid;
        } else if (target < matrix[mid][0]) {
            return getRowNumber(matrix, target, m, n, start, mid - 1);
        }
        return getRowNumber(matrix, target, m, n, mid, end);
    }

    private boolean isExistInRow(int[][] matrix, int target, int row, int start, int end) {

        if (start == end) {
            if (target == matrix[row][start]) {
                return true;
            }
            return false;
        }

        int mid = (start + end) / 2;

        if (target == matrix[row][mid]) {
            return true;
        } else if (target < matrix[row][mid]) {
            return isExistInRow(matrix, target, row, start, mid);
        }
        return isExistInRow(matrix, target, row, mid + 1, end);
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }

        int row = getRowNumber(matrix, target, m, n, 0, m - 1);
        System.out.println("row : " + row);

        return isExistInRow(matrix, target, row, 0, n - 1);
    }

    public int findPeakElementUtil(int[] nums, int n, int start, int end) {

        if (start == end) {
            return start;
        }

        int mid = (start + end) / 2;

        if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
            return mid;
        } else if (nums[mid - 1] > nums[mid]) {
            return findPeakElementUtil(nums, n, start, mid);
        }
        return findPeakElementUtil(nums, n, mid + 1, end);
    }

    public int findPeakElement(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[n - 1] > nums[n - 2]) {
            return n - 1;
        }
        return findPeakElementUtil(nums, n, 0, n - 1);
    }

    private int searchUtil(int[] nums, int target, int start, int end) {

        if (start == end) {
            if (nums[start] == target) {
                return start;
            }
            return -1;
        }

        int mid = (start + end) / 2;

        if (target == nums[mid]) {
            return mid;
        }

        if (nums[start] < nums[mid]) {
            /** first half is purely increasing */
            if (target >= nums[start] && target < nums[mid]) {
                return searchUtil(nums, target, start, mid);
            } else {
                return searchUtil(nums, target, mid + 1, end);
            }
        } else if (nums[mid] < nums[end]) {
            /** second half is purely increasing */
            if (target > nums[mid] && target <= nums[end]) {
                return searchUtil(nums, target, mid + 1, end);
            } else {
                return searchUtil(nums, target, start, mid);
            }
        }

        if (start == mid) {
            return searchUtil(nums, target, mid + 1, end);
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        return searchUtil(nums, target, 0, nums.length - 1);
    }

    private int findMin(int[] nums, int n, int start, int end) {

        if (start == end) {
            return nums[start];
        }

        if (start + 1 == end) {
            return Math.min(nums[start], nums[end]);
        }
        int mid = (start + end) / 2;

        if (nums[mid - 1] > nums[mid] && nums[mid + 1] > nums[mid]) {
            return nums[mid];
        }

        if (nums[start] < nums[mid]) {
            /** first half is monotonic increasing */
            if (nums[start] > nums[end]) {
                return findMin(nums, n, mid + 1, end);
            } else {
                return findMin(nums, n, start, mid);
            }
        }
        if (nums[mid] < nums[end]) {
            /** second half is monotonic increasing */
            if (nums[start] > nums[end]) {
                return findMin(nums, n, start, mid);
            } else {
                return findMin(nums, n, mid + 1, end);
            }
        }
        System.out.println("edge case with " + start + " and end : " + end);
        return -1;
    }

    public int findMin(int[] nums) {
        int n = nums.length;
        return findMin(nums, n, 0, n - 1);
    }

    public int findFirstIndex(int[] nums, int target, int start, int end) {

        if (start == end) {
            if (nums[start] == target) {
                return start;
            }
            return -1;
        }

        int mid = (start + end) / 2;

        if (target <= nums[mid]) {
            return findFirstIndex(nums, target, start, mid);
        } else if (target > nums[mid]) {
            return findFirstIndex(nums, target, mid + 1, end);
        }

        return 0;
    }

    public int findLastIndex(int[] nums, int target, int start, int end) {

        if (start == end) {
            if (nums[start] == target) {
                return start;
            }
            return -1;
        }
        if (start + 1 == end) {
            if (nums[end] == target) {
                return end;
            }
            if (nums[start] == target) {
                return start;
            }
            return -1;
        }

        int mid = (start + end) / 2;

        if (target < nums[mid]) {
            return findLastIndex(nums, target, start, mid - 1);
        } else if (target >= nums[mid]) {
            return findLastIndex(nums, target, mid, end);
        }

        return 0;
    }

    public int[] searchRange(int[] nums, int target) {

        int n = nums.length;
        int[] ans = new int[2];
        ans[0] = -1;
        ans[1] = -1;

        if (n < 1) {
            return ans;
        }

        ans[0] = findFirstIndex(nums, target, 0, n - 1);
        ans[1] = findLastIndex(nums, target, 0, n - 1);

        return ans;
    }
}
