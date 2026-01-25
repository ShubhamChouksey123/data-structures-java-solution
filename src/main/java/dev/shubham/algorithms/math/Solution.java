package dev.shubham.algorithms.math;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class Solution {

    private int maxCount = 0;

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

    private String getSlopeBetweenTwoPoints(int[][] points, int index1, int index2) {

        int x1 = points[index1][0];
        int y1 = points[index1][1];

        int x2 = points[index2][0];
        int y2 = points[index2][1];

        if (x1 == x2) {
            return "inf";
        }
        return String.valueOf(((double) y2 - y1) / ((double) x2 - x1));
    }

    private int getCountOfSameSlopePoints(int[][] points, int n, int index) {

        Map<String, Integer> countNumber = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (i == index)
                continue;

            String slope = getSlopeBetweenTwoPoints(points, index, i);
            Integer oldCount = countNumber.get(slope);
            if (oldCount == null) {
                countNumber.put(slope, 2);
                maxCount = Math.max(2, maxCount);
            } else {
                countNumber.put(slope, oldCount + 1);
                maxCount = Math.max(oldCount + 1, maxCount);
            }
        }

        return 0;
    }

    public int maxPoints(int[][] points) {

        int n = points.length;
        if (n <= 1)
            return 1;

        for (int i = 0; i < n; i++) {
            getCountOfSameSlopePoints(points, points.length, i);
        }

        return maxCount;
    }

    private double myPowUtilOuter(double x, BigInteger n) {

        if (x < 0 && n.doubleValue() % 2 == 0) {
            x = x * -1;
        }

        if (Objects.equals(n, BigInteger.valueOf(Integer.MIN_VALUE))) {
            return myPowUtil(1 / x, Integer.MAX_VALUE);
        }
        if (n.compareTo(BigInteger.ZERO) < 0) {
            return myPowUtil(1 / x, n.intValue() * -1);
        }

        return myPowUtil(x, n.intValue());
    }

    private double myPowUtil1(double x, int n) {

        double newValue = 1;
        double currentValue = 1;
        while (n > 0) {
            newValue = currentValue * x;
            if (newValue == currentValue) {
                return currentValue;
            }
            currentValue = newValue;
            n--;
        }

        return currentValue;
    }

    private double myPowUtil(double x, int n, Map<Integer, Double> mp) {

        if (mp.containsKey(n)) {
            return mp.get(n);
        }

        if (n % 2 == 0) {
            double value = myPowUtil(x, n / 2, mp);
            mp.put(n, value * value);
            return mp.get(n);
        }

        double value = myPowUtil(x, n / 2, mp);
        mp.put(n, x * value * value);
        return mp.get(n);
    }

    private double myPowUtil(double x, int n) {

        if (n == Integer.MAX_VALUE) {
            long nValue = (long) Integer.MAX_VALUE - 1;
            n = (int) nValue;
        }

        Map<Integer, Double> mp = new HashMap<>();
        mp.put(0, 1.0);
        mp.put(1, x);
        mp.put(2, x * x);

        return myPowUtil(x, n, mp);
    }

    public double myPow(double x, int n) {

        if (n == -1) {
            return 1 / x;
        }
        if (n == 1) {
            return x;
        }
        if (x < 0 && (double) n % 2 == 0) {
            x = x * -1;
        }
        if (x == 0 || x == 1) {
            return x;
        }
        if (x == -1 && Objects.equals(n, Integer.MAX_VALUE)) {
            return -1;
        }
        if (x == -1 && Objects.equals(n, Integer.MIN_VALUE)) {
            return -1;
        }
        if (n == 0) {
            return 1;
        }

        if (Objects.equals(n, Integer.MIN_VALUE)) {
            return myPowUtil(1 / x, Integer.MAX_VALUE);
        }
        if (n < 0) {
            return myPowUtil(1 / x, n * -1);
        }

        return myPowUtil(x, n);
    }

    private boolean isPerfectSquare(int num) {

        int rootValue = (int) Math.sqrt(num);

        if (rootValue * rootValue == num) {
            return true;
        }

        return false;
    }

    public boolean judgeSquareSum(int c) {

        if (c < 2) {
            return true;
        }

        for (int a = 0; a < Math.sqrt(c); a++) {
            int b = c - (a * a);

            if (isPerfectSquare(b)) {
                return true;
            }
        }

        return false;
    }
}
