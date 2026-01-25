package dev.shubham.algorithms.set;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * Java TreeSet are ordered set
 * <ol>
 *     <li>ordered set</li>
 *     <li>does not contain duplicates</li>
 * </ol>
 */
public class JavaTreeSet {


    private static void testTreeSet() {
        Set<Integer> st = new TreeSet<>();

        st.add(1);
        st.add(5);
        st.add(2);
        st.add(3);
        st.add(4);
        st.add(4);
        st.add(4);

        st.forEach(
                (Integer a) -> {
                    System.out.println("a : " + a);
                }
        );


        Iterator<Integer> it = st.iterator();

        while (it.hasNext()) {
            System.out.println("st element : " + it.next());
        }
    }

    public static void main(String[] args) {
//        testTreeSet();

        longestSubarray(new int[]{8, 2, 4, 7}, 4);
    }

    public static int longestSubarray(int[] nums, int limit) {
        int n = nums.length, curLimit = 0, maxLength = 0, start = 0;

        /**
         * Map of
         * num -> count
         */
        TreeMap<Integer, Integer> counts = new TreeMap<>();

        for (int end = 0; end < n; end++) {

            counts.merge(nums[end], 1, Integer::sum);

            curLimit = counts.lastKey() - counts.firstKey();

            while (curLimit > limit) {
                int currentCount = counts.get(nums[start]);
                if (currentCount == 1) {
                    counts.remove(nums[start]);
                } else {
                    counts.put(nums[start], currentCount - 1);
                }
                start++;
                curLimit = counts.lastKey() - counts.firstKey();
            }

            System.out.println("start : " + start + ", end : " + end + " and curLimit : " + curLimit);
            maxLength = Math.max(maxLength, end - start + 1);

        }
        return maxLength;
    }
}
