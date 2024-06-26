package com.shubham.app.bitmanupulation;

import java.util.BitSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainClass {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // String ans = solution.addBinary("11", "1");
        // System.out.println("ans : " + ans);

        int[] nums = new int[]{3, 4, 5, 6, 7, 8};
        // solution.singleNumber2(nums);

        // solution.reverseBits(0);
        // bitwiseOperations();

        // int ans = solution.subsetXORSum(nums);
        // System.out.println("ans : " + ans);

        testNumSteps();
        testCountTriplets();
    }

    private static void testNumSteps() {
        Solution solution = new Solution();
        assertEquals(6, solution.numSteps("1101"));
        assertEquals(1, solution.numSteps("10"));
        assertEquals(0, solution.numSteps("1"));
    }

    private static void testCountTriplets() {
        Solution solution = new Solution();
        assertEquals(4, solution.countTriplets(new int[]{2, 3, 1, 6, 7}));
        assertEquals(10, solution.countTriplets(new int[]{1, 1, 1, 1, 1}));
        // assertEquals(4, solution.countTriplets(new int[]{2, 3, 1, 6, 7}));

    }

    private static void bitwiseOperations() {
        // Variables Definition and Initialization
        int num1 = 30, num2 = 6, num3 = 0;

        // Bitwise AND
        System.out.println("num1 & num2 = " + (num1 & num2));

        // Bitwise OR
        System.out.println("num1 | num2 = " + (num1 | num2));

        // Bitwise XOR
        System.out.println("num1 ^ num2 = " + (num1 ^ num2));

        // Binary Complement Operator
        System.out.println("~num1 = " + ~num1);

        // Binary Left Shift Operator
        num3 = num1 << 2;
        System.out.println("num1 << 1 = " + num3);

        // Binary Right Shift Operator
        num3 = num1 >> 2;
        System.out.println("num1 >> 1  = " + num3);

        // Shift right zero fill operator
        num3 = num1 >>> 2;
        System.out.println("num1 >>> 1 = " + num3);
    }

    private static void biSetExample() {

        BitSet bitset = new BitSet(4);
    }
}
