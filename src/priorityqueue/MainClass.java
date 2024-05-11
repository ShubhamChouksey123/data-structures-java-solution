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


        int[] nums1 = new int[]{1, 2, 11, 37, 83, 89};
        int[] nums2 = new int[]{2, 4, 6};


        Solution2 solution = new Solution2();

        int[] ans = solution.kthSmallestPrimeFraction(nums1, 11);
        System.out.println("ans : " + Arrays.toString(ans));


    }

}
