package priorityqueue;

import java.util.Arrays;

public class MainClass {

    public static void main2(String[] args) {


        int[] nums1 = new int[]{1, 2, 11, 37, 83, 89};
        int[] nums2 = new int[]{2, 4, 6};

        CustomComparator customComparator = new CustomComparator();
//        customComparator.testVariousComparator();


        Solution solution = new Solution();

        int[] profits = new int[]{1, 2, 3, 4, 1, 5, 1, 3, 2, 1};
        int[] capital = new int[]{0, 1, 1, 1, 2, 2, 2, 3, 4, 4};
//        solution.findMaximizedCapital(3, 0, profits, capital);

//        int ans = (int) solution.maximumHappinessSum(nums1, 3);
//        System.out.println("ans : " + ans);

        int[] ans = solution.kthSmallestPrimeFraction(nums1, 11);
        System.out.println("ans : " + Arrays.toString(ans));


//        MedianFinder medianFinder = new MedianFinder();
//        medianFinder.addNum(1);
//        System.out.println("median : " + medianFinder.findMedian());
//        medianFinder.addNum(2);
//        System.out.println("median : " + medianFinder.findMedian());
//
//        medianFinder.addNum(3);
//        System.out.println("median : " + medianFinder.findMedian());


    }

    public static void main(String[] args) {


        int[] nums1 = new int[]{3, 4, 3};
        int[] nums2 = new int[]{13, 8, 20};


//        int[] nums1 = new int[]{1, 3, 3, 2, 3, 2, 1};
//        int[] nums2 = new int[]{2, 1, 3, 4, 1, 1, 1};

//        Solution2 solution2 = new Solution2();
//
//        int[] ans = solution2.kthSmallestPrimeFraction(nums1, 11);
//        System.out.println("ans : " + Arrays.toString(ans));


        Solution solution = new Solution();
//        long ans = solution.maxScore(nums1, nums2, 2);
//        System.out.println("ans : " + ans);

        double ans = solution.mincostToHireWorkers(nums1, nums2, 3);
        System.out.println("ans : " + ans);


    }

}
