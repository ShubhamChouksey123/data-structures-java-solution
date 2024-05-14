package com.shubham.app.priorityqueue;

import java.util.PriorityQueue;

public class MedianFinder {

    /** top element is greatest : Max Heap */
    private PriorityQueue<Integer> minQueue;

    /** top element is smallest : Min Heap */
    private PriorityQueue<Integer> maxQueue;

    public MedianFinder() {

        minQueue = new PriorityQueue<>((Integer a, Integer b) -> (b - a));
        maxQueue = new PriorityQueue<>();
    }

    public void makeSizeNearlyEqual() {

        if (minQueue.size() == maxQueue.size() || minQueue.size() - maxQueue.size() == 1) {
            return;
        } else if (minQueue.size() > maxQueue.size()) {
            int t1 = minQueue.poll();
            maxQueue.add(t1);
        } else {
            int t2 = maxQueue.poll();
            minQueue.add(t2);
        }
    }

    public void addNum(int num) {

        if (minQueue.isEmpty()) {
            minQueue.add(num);
            return;
        }

        Integer t1 = minQueue.peek();

        Integer t2 = Integer.MAX_VALUE;
        if (!maxQueue.isEmpty()) {
            t2 = maxQueue.peek();
        }

        if (num < t1) {
            minQueue.add(num);
        } else if (num > t2) {
            maxQueue.add(num);
        } else {
            minQueue.add(num);
        }

        // minQueue.add(num);

        makeSizeNearlyEqual();
    }

    public double findMedian() {

        Integer t1 = minQueue.peek();
        Integer t2 = maxQueue.peek();

        if (t1 == null && t2 == null) {
            return 0.0;
        } else if (t2 == null) {
            return t1;
        } else if (t1 == null) {
            return t2;
        }

        if (minQueue.size() == maxQueue.size()) {
            return ((double) t1 + t2) / 2;
        }

        System.out.println("priority queue : " + minQueue);

        return t1;
    }
}
