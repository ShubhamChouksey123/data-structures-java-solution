package com.shubham.app.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
}
