package dev.shubham.algorithms.heap;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainClass {

    public static void main(String[] args) {

//        testIsNStraightHand();

        testThreeSumClosest();

    }


    private static void testIsNStraightHand() {
        Solution solution = new Solution();


        assertEquals(true, solution.isNStraightHand(new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8}, 3));
    }

    private static void testThreeSumClosest() {
        Solution solution = new Solution();

        ArrayList<Integer> list = new ArrayList<>();
        //** -1,0,1,2,-1,-4
        // -1, 2, 1, -4

        list.add(-1);
        list.add(2);
        list.add(1);
        list.add(-4);

        solution.threeSumClosest(list, 1);


        assertEquals(2, solution.threeSumClosest(list, 1));
    }
}
