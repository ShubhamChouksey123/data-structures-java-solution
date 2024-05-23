package com.shubham.app.backtracking;

import java.util.List;

public class MainClass {

    public static void main(String[] args) {

        int[] nums = new int[]{1};

        Solution solution = new Solution();
//        List<List<Integer>> ans = solution.subsets(nums);

//        List<List<String>> ans = solution.partition("a");
//        for (List<String> a : ans) {
//            System.out.println(a);
//        }

        int ans = solution.beautifulSubsets(nums, 2);
        System.out.println("ans : " + ans) ;
    }
}
