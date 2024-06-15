package com.shubham.app.priorityqueue;

import java.util.*;

public class Solution {

    public int findKthLargest(int[] nums, int k) {

        int n = nums.length;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (pq.size() < k) {
                pq.add(nums[i]);
            } else {
                int top = pq.peek();
                if (top < nums[i]) {
                    pq.poll();
                    pq.add(nums[i]);
                }
            }
        }

        return pq.peek();
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        PriorityQueue<Integer[]> minPQ = new PriorityQueue<>((Integer[] a, Integer[] b) -> a[0] - b[0]);

        int n = profits.length;
        for (int i = 0; i < n; i++) {
            minPQ.add(new Integer[]{capital[i], profits[i]});
        }

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((Integer a, Integer b) -> (b - a));
        int times = k;
        while (times-- > 0) {

            while (!minPQ.isEmpty() && minPQ.peek()[0] <= w) {
                Integer[] top = minPQ.poll();
                int c = top[0];
                int profit = top[1];
                maxPQ.add(profit);
            }

            if (!maxPQ.isEmpty()) {
                w += maxPQ.poll();
            }
        }

        System.out.println("minPQ : " + minPQ);
        System.out.println("w : " + w);

        return w;
    }

    public long maximumHappinessSum(int[] happiness, int k) {

        int n = happiness.length;
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            if (pq.size() < k) {
                pq.add((long) happiness[i]);
                continue;
            }

            Long top = pq.peek();
            Long element = Long.valueOf(happiness[i]);
            if (top < element) {
                pq.poll();
                pq.add(element);
            }
        }

        Long[] kTopElements = new Long[pq.size()];
        // List<Integer> kTopElements = new ArrayList<>(pq.size());
        int index = k - 1;
        while (!pq.isEmpty()) {
            Long element = pq.poll();
            kTopElements[index] = element;
            index--;
        }

        Long sum = 0L;
        for (int i = 0; i < k; i++) {
            if (kTopElements[i] - i > 0) {
                sum += kTopElements[i] - i;
            }
        }

        return sum;
    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {

        int n = arr.length;
        // PriorityQueue<Integer[]> pq = new PriorityQueue<>((Integer[] a, Integer[] b)
        // -> (b[0]
        // / b[1] - a[0] / a[1]));
        PriorityQueue<Double[]> pq = new PriorityQueue<>(new IntegerArrayComparator());
        for (int i = 0; i < Math.min(k, n); i++) {
            int maxResults = k;
            for (int j = n - 1; j >= 0 && maxResults >= 0 && i != j; j--, maxResults--) {
                if (pq.size() < k) {
                    pq.add(new Double[]{(double) arr[i], (double) arr[j]});
                    System.out.println(
                            "after adding " + arr[i] + " and " + arr[j] + " pq.peek : " + Arrays.toString(pq.peek()));
                    continue;
                }
                Double[] top = pq.peek();
                double topValue = top[0] / top[1];
                double currentValue = (double) arr[i] / arr[j];
                if (currentValue < topValue) {
                    pq.poll();
                    pq.add(new Double[]{(double) arr[i], (double) arr[j]});
                }
            }
        }

        Double[] current = pq.peek();
        double topValue = current[0] / current[1];
        double[] ans = new double[]{current[0], current[1]};
        while (!pq.isEmpty()) {
            current = pq.poll();
            Double currentValue = current[0] / current[1];

            if (currentValue > topValue) {
                topValue = currentValue;
                ans = new double[]{current[0], current[1]};
            }
        }

        int topVal = (int) Math.round(ans[0]);
        int bottomVal = (int) Math.round(ans[1]);
        return new int[]{topVal, bottomVal};
    }

    public long maxScore(int[] nums1, int[] nums2, int k) {

        int n = nums1.length;

        Integer[][] arr = new Integer[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums1[i];
            arr[i][1] = nums2[i];
        }

        Arrays.sort(arr, new maxComparator());
        System.out.println("arr : " + Arrays.deepToString(arr));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        long sum = 0;
        for (int i = 0; i < k; i++) {
            pq.add(arr[i][0]);
            sum += arr[i][0];
        }

        long score = sum * arr[k - 1][1];
        long maxScore = score;

        for (int i = k; i < n; i++) {
            int topElement = pq.poll();
            sum = sum - topElement + arr[i][0];
            score = sum * arr[i][1];
            maxScore = Math.max(maxScore, score);
            pq.add(arr[i][0]);
        }
        return maxScore;
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {

        int n = quality.length;
        Double[][] arr = new Double[n][3];

        for (int i = 0; i < n; i++) {
            arr[i][0] = (double) quality[i];
            arr[i][1] = (double) wage[i];
            arr[i][2] = (double) wage[i] / (double) quality[i];
        }

        Arrays.sort(arr, new WagerComparator());
        System.out.println("arr : " + Arrays.deepToString(arr));

        PriorityQueue<Double> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        double totalQuality = 0;
        for (int i = 0; i < k; i++) {
            totalQuality = totalQuality + arr[i][0];
            maxPQ.add(arr[i][0]);
        }

        double amount = totalQuality * arr[k - 1][2];
        double maxAmount = amount;

        for (int i = k; i < n; i++) {
            totalQuality = totalQuality - maxPQ.poll() + arr[i][0];
            amount = totalQuality * arr[i][2];
            maxAmount = Math.min(amount, maxAmount);
            maxPQ.add(arr[i][0]);
        }

        return maxAmount;
    }

    public class WagerComparator implements Comparator<Double[]> {

        @Override
        public int compare(Double[] o1, Double[] o2) {
            if (Objects.equals(o1[2], o2[2])) {
                return o1[0].compareTo(o2[0]);
            }
            return o1[2].compareTo(o2[2]);
        }
    }

    public class minComparator implements Comparator<Integer[]> {

        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            if (Objects.equals(o1[1], o2[1])) {
                return o1[0].compareTo(o2[0]);
            }
            return o1[1].compareTo(o2[1]);
        }
    }

    public class maxComparator implements Comparator<Integer[]> {

        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            if (Objects.equals(o1[1], o2[1])) {
                return o1[0].compareTo(o2[0]);
            }
            return o2[1].compareTo(o1[1]);
        }
    }

    public class Pair implements Comparable<Pair> {

        public int a;
        public int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.b > o.b) {
                return 1;
            }
            return -1;
        }
    }

    public class IntegerArrayComparator implements Comparator<Double[]> {

        @Override
        public int compare(Double[] o1, Double[] o2) {

            return (int) ((double) (o2[0] / o2[1]) - ((double) o1[0] / o1[1]) * 10000);
        }
    }

    public class CapitalComparator implements Comparator<int[]> {
        /**
         * int[] arr arr[0] = profit arr[1] = capital
         *
         * @param o1
         *            the first object to be compared.
         * @param o2
         *            the second object to be compared.
         * @return
         */
        @Override
        public int compare(int[] o1, int[] o2) {

            /** same capital */
            return o1[0] - o2[0];
        }
    }

    public class IntegerMinComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return 01 - o2;
        }
    }

    public class IntegerMaxComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return 02 - o1;
        }
    }


}
