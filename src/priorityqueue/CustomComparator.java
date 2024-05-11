package priorityqueue;

import java.util.*;

public class CustomComparator {


    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {


        int m = nums1.length;
        int n = nums2.length;

        PriorityQueue<SmallPair> pq = new PriorityQueue<>(new SmallPairMaxComparator());

        for (int i = 0; i < Math.min(m, k); i++) {
            for (int j = 0; j < Math.min(n, k); j++) {

                if (pq.size() < k) {
                    pq.add(new SmallPair(nums1[i], nums2[j]));
                    continue;
                }

                SmallPair topSmallPair = pq.peek();

                if (topSmallPair.getSum() > nums1[i] + nums2[j]) {
                    pq.poll();
                    pq.add(new SmallPair(nums1[i], nums2[j]));
                }

                System.out.println("after adding " + nums1[i] + " and " + nums2[j] + " pq.peek : " + pq.peek());
            }
        }

        List<List<Integer>> ans = new ArrayList<>();

        while (!pq.isEmpty()) {
            List<Integer> pair = new ArrayList<>();
            SmallPair topSmallPair = pq.poll();
            pair.add(topSmallPair.getA());
            pair.add(topSmallPair.getB());
            ans.add(pair);
        }

        return ans;
    }

    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {


        int m = nums1.length;
        int n = nums2.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>(((int[] a, int[] b) -> ((b[0] + b[1]) - (a[0] + a[1]))));

        for (int i = 0; i < Math.min(m, k); i++) {
            for (int j = 0; j < Math.min(n, k); j++) {

                if (pq.size() < k) {
                    int[] pair = new int[]{nums1[i], nums2[j]};
                    pq.add(pair);
                    continue;
                }

                int[] topSmallPair = pq.peek();

                if (topSmallPair[0] + topSmallPair[1] > nums1[i] + nums2[j]) {
                    pq.poll();
                    int[] pair = new int[]{nums1[i], nums2[j]};
                    pq.add(pair);
                }

                System.out.println("after adding " + nums1[i] + " and " + nums2[j] + " pq.peek : " + pq.peek());
            }
        }

        List<List<Integer>> ans = new ArrayList<>();

        while (!pq.isEmpty()) {
            List<Integer> pair = new ArrayList<>();
            int[] topSmallPair = pq.poll();
            pair.add(topSmallPair[0]);
            pair.add(topSmallPair[1]);
            ans.add(pair);
        }

        return ans;
    }

    public void testVariousComparator() {

        PriorityQueue<Integer> pq = new PriorityQueue<>(new MaxComparatorForInt());
        pq.add(1);
        System.out.println("top : " + pq.peek() + " and pq : " + pq);
        pq.add(2);
        System.out.println("top : " + pq.peek() + " and pq : " + pq);

        pq.add(3);
        System.out.println("top : " + pq.peek() + " and pq : " + pq);
        pq.add(4);
        System.out.println("top : " + pq.peek() + " and pq : " + pq);


        PriorityQueue<Double[]> pqDiv = new PriorityQueue<>(new MaxComparatorForDivision());
        pqDiv.add(new Double[]{1.00, 2.00});
        System.out.println("top : " + Arrays.toString(pqDiv.peek()) + " and pqDiv : " + pqDiv);
        pqDiv.add(new Double[]{1.00, 3.00});
        System.out.println("top : " + Arrays.toString(pqDiv.peek()) + " and pqDiv : " + pqDiv);

    }

    public class SmallPairMinComparator implements Comparator<SmallPair> {

        @Override
        public int compare(SmallPair o1, SmallPair o2) {
            return (o1.getA() + o1.getB()) - (o2.getA() + o2.getB());
        }
    }

    public class SmallPairMaxComparator implements Comparator<SmallPair> {

        @Override
        public int compare(SmallPair o1, SmallPair o2) {
            return (o2.getA() + o2.getB()) - (o1.getA() + o1.getB());
        }
    }

    public class SmallPair {
        public int a;
        public int b;

        public SmallPair(int a, int b) {
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

        public int getSum() {
            return this.getA() + this.getB();
        }

        @Override
        public String toString() {
            return "SmallPair{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    private class MinComparatorForInt implements Comparator<Integer> {


        @Override
        public int compare(Integer o1, Integer o2) {
            System.out.println("o1 : " + o1 + " and o2 : " + o2 + " and value return is : " + (o1 - o2));
            return o1 - o2;
        }
    }

    private class MaxComparatorForInt implements Comparator<Integer> {


        @Override
        public int compare(Integer o1, Integer o2) {
            System.out.println("o1 : " + o1 + " and o2 : " + o2 + " and value return is : " + (o1 - o2));
            return o2 - o1;
        }
    }

    private class MinComparatorForDivision implements Comparator<Double[]> {


        @Override
        public int compare(Double[] o1, Double[] o2) {
            System.out.println("o1[0]/o1[1] : " + o1[0] / o1[1] + " and o2[0]/o2[1] : " + o2[0] / o2[1] + " and value return is : " + 10000*(o1[0] / o1[1] - o2[0] / o2[1]));
            return (int) (((o1[0] / o1[1]) - (o2[0] / o2[1])) * 100000);
        }


    }

    private class MaxComparatorForDivision implements Comparator<Double[]> {


        @Override
        public int compare(Double[] o1, Double[] o2) {
            System.out.println("o1[0]/o1[1] : " + o1[0] / o1[1] + " and o2[0]/o2[1] : " + o2[0] / o2[1] + " and value return is : " + 1000*(o2[0] / o2[1] - o1[0] / o1[1]));
            return (int) (((o2[0] / o2[1]) - (o1[0] / o1[1])) * 100000);
        }
    }
    /**
     * greater the value least is priority
     * -1 > 0 > 1 > 2
     */
}
