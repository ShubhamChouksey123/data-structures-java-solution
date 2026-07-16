/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {

    private static boolean[] buildingsSpared(int[] street, int k){

        int n = street.length;
        /**
         * boolean representing there exists a next greater element on right within k distance
         */
        boolean[] existNextGreaterElement = new boolean[n];

        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0 ; i < n ; i++){
            while(!stack.isEmpty() && street[stack.peekLast()] < street[i] ){
                int topIndex = stack.pollLast();
                if(i - topIndex <= k){
                    existNextGreaterElement[topIndex] = true;
                }
            }
            stack.offerLast(i);
        }
        System.out.println("existNextGreaterElement : " + Arrays.toString(existNextGreaterElement));

        stack.clear();

        /**
         * boolean representing there exists a revious lesser element on left within k distance
         */
        boolean[] existPreviousSmallerElement = new boolean[n];

        for(int i = n - 1 ; i >= 0 ; i--){
            while(!stack.isEmpty() && street[stack.peekLast()] > street[i] ){
                int topIndex = stack.pollLast();
                if(Math.abs(i - topIndex) <= k){
                    existPreviousSmallerElement[topIndex] = true;
                }
            }
            stack.offerLast(i);
        }
        System.out.println("existPreviousSmallerElement : " + Arrays.toString(existPreviousSmallerElement));

        boolean[] buildingsSpared = new boolean[n];

        for(int i = 0 ; i < n ; i++){
            buildingsSpared[i] = existNextGreaterElement[i] && existPreviousSmallerElement[i];
        }

        return buildingsSpared;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }

        // int[] street = new int[]{10, 20, 30, 15, 5};
        // int k = 2;

        int[] street = new int[]{1, 10, 5, 20};
        int k = 1;

        boolean[] buildingsSpared = buildingsSpared(street, k);
        System.out.println("buildingsSpared : " + Arrays.toString(buildingsSpared));

    }
}

// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # King Kong Vs Godzilla In The Fog

// King Kong and Godzilla are rampaging a city. You're given a non-empty array of positive integers, `street`, representing the heights of buildings in a street from left to right. It is foggy, so King Kong and Godzilla can only see the next `k` buildings in front of them.

// - King Kong starts at the beginning of the street and prefers to smash tall buildings. King Kong will only spare a building `street[i]` if there is a **taller building to the right less than `k` buildings away**.
// - Godzilla starts at the end of the street and prefers crushing small buildings. Godzilla will only spare a building `street[i]` if there is a **shorter building to the left less than `k` buildings away**.

// Return a boolean array of length `n`, where `n` is the length of `street`, indicating which buildings were spared by both King Kong and Godzilla. Index `i` should be true if `street[i]` was spared.

// Example 1: street = [10, 20, 30, 15, 5], k = 2
// Output: [False, True, False, False, False]

// King Kong does the following:
// 1. King Kong starts at building 10 and can see the next 2 buildings: 20 and 30.
// It spares it since it sees a taller building (20).
// 2. King Kong moves to building 20 and can see the next 2 buildings: 30 and 15.
// It spares 20 since it sees a taller building (30).
// 3. King Kong moves to building 30. It can see the next 2 buildings: 15 and 5.
// It destroys 30 since it is taller than the other buildings it sees.
// 4. King Kong moves to building 15. It can see the next building: 5.
// It destroys 15 since it is taller than the other buildings it sees.
// 5. King Kong moves to building 5. There are no more buildings to see.
// It destroys 5 since it is the last building.

// Godzilla does the following:
// 1. Godzilla starts at building 5 and can see the next 2 buildings: 15 and 30.
// It destroys 5 since it sees a taller building (15).
// 2. Godzilla moves to building 15 and can see the next 2 buildings: 30 and 20.
// It destroys 15 since it sees a taller building (30).
// 3. Godzilla moves to building 30. It can see the next 2 buildings: 20 and 10.
// It spares 30 since it sees a shorter building (20).
// 4. Godzilla moves to building 20. It can see the next building: 10.
// It spares 20 since it sees a shorter building (10).
// 5. Godzilla moves to building 10. There are no more buildings to see.
// It destroys 10 since it is the last building.

// The only building spared by both is 20.

// Example 2: street = [10, 20, 30, 40, 50], k = 3
// Output: [False, True, True, True, False]
// Godzilla destroys the first building and King King destroys the last.

// Example 3: street = [50, 40, 30, 20, 10], k = 3
// Output: [False, False, False, False, False]
// King Kong destroys all buildings, and so does Godzilla.

// Example 4: street = [1, 10, 5, 20], k = 2
// Output: [False, True, True, False]

// Example 5: street = [1, 10, 5, 20], k = 1
// Output: [False, False, False, False]
// Building (10) has a taller building (20) to the right, but it is hidden by the fog.

// Constraints:

// - The length of street is at most 10^5
// - Each element in street is a positive integer less than 10^3
// - 1 ≤ k ≤ len(street)


/**
 *
 * Overall Assessment: Pass
 *
 * You demonstrated a solid understanding of monotonic stacks and correctly identified the right approach for both King Kong and Godzilla. Your code was clean and produced correct results across all test cases.
 *
 * Ratings:
 *
 * Coding ability: 3.5/4 — Your code was well-structured and mostly clean. You handled both passes correctly and combined the results properly. Minor point: you could have been a bit more careful with the distance check initially (i - topIndex sign issue), but you caught and fixed it quickly.
 *
 * Problem solving ability: 3.5/4 — You identified the monotonic stack approach right away, correctly decomposed the problem into two subproblems (next greater element and previous smaller element), and combined them. Strong problem decomposition.
 *
 * Communication skills: 3/4 — You explained your approach before coding, which was good. Your complexity analysis was correct and you gave a solid justification for why the nested loops are still O(n). You could improve by being a bit more precise and structured when initially explaining your approach — some of your early descriptions mixed up details (left/right, less than vs less
 *
 *
 * Thanks for asking! Here are some tips for improvement:
 *
 * Communication:
 *
 * Before jumping into your approach, take a moment to clearly restate the problem in your own words. This shows the interviewer you fully understand what's being asked.
 * When explaining your approach, try to be more structured. For example: "Step 1: I'll handle King Kong by finding... Step 2: I'll handle Godzilla by finding... Step 3: I'll combine the results."
 * Be precise with terminology early on — you initially went back and forth on "less than k" vs "less than or equal to k." In a real interview, clarifying constraints upfront before coding saves time.
 * Coding:
 *
 * Consider testing edge cases proactively before being asked — things like single-element arrays, all equal heights, or k equal to the array length.
 * Remove debug print statements (like your System.out.println on lines 35 and 53) before presenting your final solution. It shows attention to detail.
 * Problem Solving:
 *
 * After arriving at a solution, consider whether there are alternative approaches. For instance, could you have reused your King Kong logic for Godzilla by transforming the input (reversing and negating the array)? Mentioning alternatives shows depth of thinking even if you don't implement them.
 * General Interview Tips:
 *
 * Always verify your understanding of the problem with a small example before coding.
 * Trace through your code with at least one example after writing it, walking the interviewer through it step by step.
 * When discussing complexity, lead with the reasoning rather
 */