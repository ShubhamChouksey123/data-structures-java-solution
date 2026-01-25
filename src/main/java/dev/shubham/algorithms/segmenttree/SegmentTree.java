package dev.shubham.algorithms.segmenttree;

public class SegmentTree {

    private int[] segTree;

    private int build(int[] nums, int left, int right, int index) {

        if (left == right) {
            segTree[index] = nums[left];
            return segTree[index];
        }

        int middle = (left + right) / 2;
        int leftSum = build(nums, left, middle, 2 * index + 1);
        int rightSum = build(nums, middle + 1, right, 2 * index + 2);

        segTree[index] = leftSum + rightSum;
        return segTree[index];
    }

    public SegmentTree(int[] nums) {
        int n = nums.length;
        this.segTree = new int[4 * n];

        build(nums, 0, nums.length - 1, 0);
    }

    private void printSegTree() {
        for (int i = 0; i < segTree.length; i++)
            System.out.print(segTree[i] + " ");
        System.out.println("");
    }

    private int sumRange(int left, int right, int startIndex, int endIndex, int nodeIndex) {
        if (endIndex < left || startIndex > right) {
            return 0;
        }

        if (startIndex <= left && right <= endIndex) {
            return segTree[nodeIndex];
        }

        int middle = (left + right) / 2;

        int leftSum = sumRange(left, middle, startIndex, endIndex, 2 * nodeIndex + 1);
        int rightSum = sumRange(middle + 1, right, startIndex, endIndex, 2 * nodeIndex + 2);
        return leftSum + rightSum;
    }

    private int update(int left, int right, int nodeIndex, int newVal, int newValIndex) {

        if (left == right && left == newValIndex) {
            segTree[nodeIndex] = newVal;
            return segTree[nodeIndex];
        }
        int leftSum = segTree[2 * nodeIndex + 1];
        int rightSum = segTree[2 * nodeIndex + 2];

        int middle = (left + right) / 2;

        if (newValIndex >= left && newValIndex <= middle) {
            leftSum = update(left, middle, 2 * nodeIndex + 1, newVal, newValIndex);
        }

        if (newValIndex >= middle + 1 && newValIndex <= right) {
            rightSum = update(middle + 1, right, 2 * nodeIndex + 2, newVal, newValIndex);
        }
        segTree[nodeIndex] = leftSum + rightSum;
        return segTree[nodeIndex];
    }

    private int sumRange(int left, int right, int[] nums) {

        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {

        int[] nums = {1, 3, 5, 7, 9, 11};
        SegmentTree segmentTree = new SegmentTree(nums);

        segmentTree.printSegTree();
        int ans = segmentTree.sumRange(0, nums.length - 1, 0, 1, 0);
        System.out.println("ans : " + ans);

        segmentTree.update(0, nums.length - 1, 0, 10, 1);

        ans = segmentTree.sumRange(0, nums.length - 1, 0, 1, 0);
        System.out.println("ans : " + ans);
    }
}
