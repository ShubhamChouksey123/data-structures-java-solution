package com.shubham.app.bitmanupulation;

import java.util.BitSet;

public class Solution {

    private int totalSum = 0;

    public String addBinary(String a, String b) {

        int m = a.length();
        int n = b.length();

        StringBuilder stringBuilder = new StringBuilder();

        int index1 = m - 1;
        int index2 = n - 1;
        int carry = 0;
        while (index1 >= 0 || index2 >= 0) {
            int sum = 0;
            if (index1 >= 0) {
                int num1 = a.charAt(index1) - '0';
                sum = sum + num1;
                index1--;
            }

            if (index2 >= 0) {
                int num2 = b.charAt(index2) - '0';
                sum = sum + num2;
                index2--;
            }
            sum += carry;
            if (sum >= 2) {
                carry = 1;
            } else {
                carry = 0;
            }
            stringBuilder.append(sum % 2);
        }

        if (carry > 0) {
            stringBuilder.append("1");
        }

        stringBuilder.reverse();

        return stringBuilder.toString();
    }

    public int reverseBits(int n) {

        if (n == 0) {
            return 0;
        }

        BitSet bitSet = new BitSet(32);

        for (int i = 0; i < 32; i++) {
            int val = n & 1;
            if (val == 1) {
                bitSet.set(32 - i - 1);
            }
            n = n >> 1;
        }
        System.out.println("bitSet : " + bitSet);

        long l = bitSet.toLongArray()[0];
        System.out.println("l : " + l);
        return (int) l;
    }

    public int hammingWeight(int n) {

        int count = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    public int singleNumber(int[] nums) {

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = ans ^ nums[i];
        }
        return ans;
    }

    public int singleNumber2(int[] nums) {

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                sum = sum + ((num >> i) & 1);
            }
            sum = sum % 3;
            ans = ans | (sum << i);
        }

        return ans;
    }

    public int rangeBitwiseAnd(int left, int right) {

        int shift = 0;
        while (left != right) {
            left = left >> 1;
            right = right >> 1;
            shift++;
        }

        left = left << shift;
        return left;
    }

    private void subsetXORSumUtil(int[] nums, int n, int index, int sum) {

        if (index == n) {
            totalSum += sum;
            return;
        }

        subsetXORSumUtil(nums, n, index + 1, sum);
        subsetXORSumUtil(nums, n, index + 1, sum ^ nums[index]);

    }

    public int subsetXORSum(int[] nums) {

        subsetXORSumUtil(nums, nums.length, 0, 0);
        return totalSum;
    }
}
