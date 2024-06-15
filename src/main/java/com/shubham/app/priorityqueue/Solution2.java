package com.shubham.app.priorityqueue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2 {



    public int[] kthSmallestPrimeFraction(int[] arr, int k) {

        int n = arr.length;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < Math.min(k, n); i++) {
            int maxResults = k;
            for (int j = n - 1; j >= 0 && maxResults >= 0 && i != j; j--, maxResults--) {
                double currentValue = (double) arr[i] / arr[j];
                if (pq.size() < k) {
                    pq.add(new Pair(currentValue, arr[i], arr[j]));
                    continue;
                }
                Pair top = pq.peek();
                double topValue = top.fraction;
                if (currentValue < topValue) {
                    pq.poll();
                    pq.add(new Pair(currentValue, arr[i], arr[j]));
                }
            }
        }

        Pair current = pq.peek();
        return new int[]{current.a, current.b};
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        PriorityQueue<Integer[]> lowestCapitals = new PriorityQueue<>(
                (Integer[] a, Integer[] b) -> {
                    return a[0] - b[0];
                }
        );

        int n = profits.length;
        for (int i = 0; i < n; i++) {

            Integer[] temp = new Integer[]{capital[i], profits[i]};
            lowestCapitals.add(temp);
        }

        PriorityQueue<Integer> maximumProfits = new PriorityQueue<>((a, b) -> b - a);

        int totalProfit = 0;
        while (k-- > 0) {
            while (!lowestCapitals.isEmpty() && lowestCapitals.peek()[0] <= w) {
                Integer[] lowestCapital = lowestCapitals.poll();
                maximumProfits.add(lowestCapital[1]);
            }

            if (!maximumProfits.isEmpty()) {
                totalProfit += maximumProfits.poll();
                w += totalProfit;
            }
        }

        return totalProfit;
    }

    public class IntegerArrayComparator implements Comparator<Double[]> {

        @Override
        public int compare(Double[] o1, Double[] o2) {

            return (int) ((o2[0] / o2[1]) - (o1[0] / o1[1]) * 100000);
        }
    }

    public class Pair implements Comparable<Pair> {
        double fraction;
        int a;
        int b;

        public Pair(double fraction, int a, int b) {
            this.fraction = fraction;
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.fraction > o.fraction) {
                return 1;
            }
            return -1;
        }
    }
}
