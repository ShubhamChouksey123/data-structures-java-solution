package com.shubham.app.heap;

import java.util.*;


/**
 * aakankshasirohi@gmail.com
 * https://www.linkedin.com/in/aakanksha-sirohi-2a6029195/
 */
public class Solution {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        int m = nums1.length;
        int n = nums2.length;

        PriorityQueue<int[]> minPQ = new PriorityQueue<>((int[] a, int[] b) -> (b[0] + b[1]) - (a[0] + a[1]));

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < Math.min(n, k); j++) {
                if (minPQ.size() < k) {
                    minPQ.add(new int[]{nums1[i], nums2[j]});
                    continue;
                }
                int[] top = minPQ.peek();
                if (top[0] + top[1] > nums1[i] + nums2[j]) {
                    minPQ.poll();
                    minPQ.add(new int[]{nums1[i], nums2[j]});
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        while (!minPQ.isEmpty()) {
            int[] top = minPQ.poll();
            List<Integer> arr = List.of(top[0], top[1]);
            ans.add(arr);
        }
        return ans;
    }


    public boolean isNStraightHand(int[] hand, int groupSize) {

        Queue<Integer> numbers = new PriorityQueue<>();
        for (int num : hand) {
            numbers.add(num);
        }

        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : hand) {
            Integer currentCount = counts.get(num);
            if (currentCount == null) {
                counts.put(num, 1);
            } else {
                counts.put(num, currentCount + 1);
            }
        }

        while (!numbers.isEmpty()) {
            int number = numbers.poll();
            int count = counts.get(number);

            if (count == 0) {
                continue;
            }

            counts.put(number, 0);

            int k = groupSize - 1;
            int nextNumber = number + 1;
            while (k-- > 0) {
                Integer nextNumberCount = counts.get(nextNumber);
                if (nextNumberCount == null || nextNumberCount < count) {
                    return false;
                }
                counts.put(nextNumber, nextNumberCount - count);
                nextNumber++;
            }


        }

        return true;
    }


    public int threeSumClosestOld(ArrayList<Integer> A, int B) {
        Collections.sort(A);

        int closestSum = A.get(0) + A.get(1) + A.get(2);
        int minDiff = Math.abs(closestSum - B);
        int n = A.size();

        for (int i = 0; i < n - 2; i++) {

            int left = i + 1;
            int right = n - 1;

            int sum = A.get(i);

            while (left < right) {
                sum = A.get(left) + A.get(right);
                int diff = Math.abs(sum - B);
                minDiff = Math.min(minDiff, diff);

                if (sum == B) {
                    break;
                } else if (sum < B) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return minDiff;
    }

    public int threeSumClosest(ArrayList<Integer> A, int B) {
        if (A == null || A.size() < 3) {
            return 0;
        }
        Collections.sort(A);

        int closestSum = A.get(0) + A.get(1) + A.get(2);
        int minDiff = Math.abs(closestSum - B);

        for (int i = 0; i < A.size() - 2; i++) {
            int left = i + 1;
            int right = A.size() - 1;
            while (left < right) {
                int sum = A.get(i) + A.get(left) + A.get(right);
                int diff = Math.abs(sum - B);

                if (diff < minDiff) {
                    minDiff = diff;
                    closestSum = sum;
                }
                if (sum < B) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closestSum;
    }
}



