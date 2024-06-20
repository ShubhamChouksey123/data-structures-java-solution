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

    private boolean isEven(String s) {
        char c = s.charAt(s.length() - 1);
        if (c == '1')
            return false;
        return true;
    }

    private String performEvenOperation(String s) {
        return s.substring(0, s.length() - 1);
    }

    private String performOddOperation(String s) {
        StringBuilder str = new StringBuilder(s);
        for (int i = s.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '0') {
                str.setCharAt(i, '1');
                break;
            }
            str.setCharAt(i, '0');
        }
        if (str.charAt(0) == '0') {
            return "1" + str.toString();
        }
        return str.toString();
    }

    public int numSteps(String s) {

        int countOperations = 0;

        while (s.length() > 1) {
            if (isEven(s)) {
                s = performEvenOperation(s);
            } else {
                s = performOddOperation(s);
            }
            countOperations++;
        }
        return countOperations;
    }

    public int countTriplets(int[] arr) {

        int n = arr.length;
        if (n <= 1) {
            return 0;
        }

        int count = 0;
        int a = 0, b = 0;

        for (int i = 0; i < n - 1; i++) {
            a = 0;
            for (int j = i + 1; j < n; j++) {
                a = a ^ arr[j - 1];
                b = 0;
                for (int k = j; k < n; k++) {
                    b = b ^ arr[k];
                    if (a == b) {
                        count++;
                        // System.out.println("(i, j , k): " + i + " " + j + " " + k);
                    }
                }
            }
        }

        return count;
    }


    private boolean isNthBitSet(long num, long nth) {

        num = num >> nth;

        if ((num & 1) == 1) {
            return true;
        }
        return false;
    }

    public int[] singleNumber3(int[] nums) {

        long xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        long nth = 0;
        while ((xor & 1) == 0) {
            xor >>= 1;
            nth++;
        }


        long firstNumber = 0;
        long secondNumber = 0;
        for (int num : nums) {
            if (isNthBitSet(num, nth)) {
                firstNumber ^= num;
            } else {
                secondNumber ^= num;
            }
        }

        return new int[]{(int) firstNumber, (int) secondNumber};
    }

}
