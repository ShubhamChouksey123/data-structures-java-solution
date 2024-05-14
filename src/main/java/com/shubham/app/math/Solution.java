package com.shubham.app.math;

import java.util.Objects;
import java.util.PriorityQueue;

public class Solution {

    public boolean isPalindrome(int x) {

        String num = String.valueOf(x);
        int n = num.length();

        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) != num.charAt(n - i - 1)) {
                return false;
            }
        }

        return true;
    }

    public int[] plusOne(int[] digits) {

        int n = digits.length;

        int carry = 1;
        for (int i = n - 1; i >= 0; i--) {
            int num = digits[i] + carry;
            if (num == 10) {
                digits[i] = 0;
                carry = 1;
            } else {
                digits[i] = num;
                carry = 0;
            }
        }

        if (carry == 0) {
            return digits;
        }

        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }

    public String[] findRelativeRanks(int[] score) {

        int n = score.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> (a[0] - b[0]));

        for (int i = 0; i < n; i++) {
            int[] element = new int[]{score[i], i};
            pq.add(element);
        }

        String[] ans = new String[n];
        int rank = n;
        while (!pq.isEmpty()) {
            int[] element = pq.poll();
            int index = element[1];
            ans[index] = String.valueOf(rank);
            if (rank == 3) {
                ans[index] = "Bronze Medal";
            } else if (rank == 2) {
                ans[index] = "Silver Medal";
            } else if (rank == 1) {
                ans[index] = "Gold Medal";
            }
            rank--;
        }

        return ans;
    }

    private int countFiveInExpansion(int num) {

        int count = 0;
        while (num > 0 && num % 5 == 0) {
            num = num / 5;
            count++;
        }

        return count;
    }

    private int nearestFive(int n) {
        int num = n;
        while (num % 5 != 0) {
            num--;
        }
        return num;
    }

    public int trailingZeroes(int n) {

        n = nearestFive(n);
        int num = n;
        int count = 0;
        for (int i = num; i > 0; i = i - 5) {
            count += countFiveInExpansion(i);
        }

        return count;
    }

    public long mySqrt(int x, long start, long end) {

        if (start == end) {
            return start;
        }

        long mid = (start + end) / 2;

        if (mid * mid == x) {
            return mid;
        }
        if ((mid + 1) * (mid + 1) > x && (mid * mid < x)) {
            return mid;
        }

        if (mid * mid > x) {
            return mySqrt(x, start, mid - 1);
        }
        return mySqrt(x, mid + 1, end);
    }

    public int mySqrt(int x) {

        if (x == 0)
            return 0;
        if (x == 1 || x == 2 || x == 3) {
            return 1;
        }
        return (int) mySqrt(x, (long) 2, (long) x / 2);
    }

    /**
     * when n is an integer and n > 0
     *
     * @param x
     * @param n
     * @return
     */
    public double myPowOfPositive(double x, double n) {

        if (x == 0)
            return 0;

        boolean isPositive = x > 0;
        if (!isPositive) {
            x = x * -1;
        }
        double ans = 1;
        for (int i = 0; i < Math.min(n, 100); i++) {
            ans = ans * x;
            if (ans < 1e-5) {
                return 0;
            }
        }

        if (isPositive || n % 2 == 0)
            return ans;
        ans = ans * -1;
        return ans;
    }

    public double myPow(double x, int n) {

        if (x == 0 || x == 1)
            return x;

        if (x == -1) {
            if ((n & 1) == 0) {
                return 1;
            }
            return -1;
        }

        if (n == 0) {
            return 1;
        }
        if (n > 0) {
            return myPowOfPositive(x, n);
        }
        double xNew = 1 / x;

        double nNew = -n;
        if (Objects.equals(n, Integer.MIN_VALUE))
            nNew = Integer.MAX_VALUE;

        return myPowOfPositive(xNew, nNew);
    }
}
