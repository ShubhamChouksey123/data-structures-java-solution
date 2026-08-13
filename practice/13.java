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

    private static int getStartIndexOfMaxSalesWindow(int[] sales, int k){

        int n = sales.length;
        if(n == 0) return -1;
        if(n <= k) return 0;

        int windowSum = 0;
        for(int i = 0 ; i < k; i++){
            windowSum += sales[i];
        }
        int maxWindowSum = windowSum, maxWindowStartIndex = 0;

        for(int i = 1; i <= n - k ; i++){
            windowSum += sales[i + k - 1];

            windowSum -= sales[i - 1];

            if(windowSum > maxWindowSum){
                maxWindowSum = windowSum;
                maxWindowStartIndex = i;
            }
        }

        return maxWindowStartIndex;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());


        int[] sales = new int[]{8, 1, 3, 7};
        int k = 2;

        // int[] sales = new int[]{5, 10, 15, 5};
        // int k = 1;

        // int[] sales = new int[]{1, 2, 3};
        // int k = 3;

        int startIndexOfMaxSalesWindow = getStartIndexOfMaxSalesWindow(sales, k);
        System.out.println("startIndexOfMaxSalesWindow : " + startIndexOfMaxSalesWindow);

    }
}

// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # Most Sales In K Days

// Given the array `sales` and a number `k` with `1 ≤ k ≤ len(sales)`, find the most sales in any k-day period.

// Return the first day of that period (days start at `0`). If there are multiple k-day periods with the most sales, return the first day of the first one.

// Example 1: sales = [8, 1, 3, 7], k = 2
// Output: 2
// The subarray of length 2 with maximum sum is [3, 7], which starts at index 2.

// Example 2: sales = [5, 10, 15, 5], k = 1
// Output: 2
// The day with most sales is day 2 with 15 sales.

// Example 3: sales = [1, 2, 3], k = 3
// Output: 0
// The only valid period is the entire array.

// Constraints:

// - The length of `sales` is at most `10^6`
// - Each element in `sales` is a non-negative integer less than `10^3`
// - `1 ≤ k ≤ len(sales)`


/**
 *
 * Thanks Shubham! It was great interviewing you. Here's my assessment:
 *
 * Overall: Pass ✅
 *
 * You would pass this interview. Here's the breakdown:
 *
 * Coding Ability: 4/4 Your code was clean, correct, and worked on all test cases. You implemented the sliding window efficiently with no bugs.
 *
 * Problem Solving Ability: 4/4 You immediately recognized this as a sliding window problem and jumped to the optimal O(n) time, O(1) space solution without needing any hints. You understood the key optimization of adding/removing elements instead of recomputing the window sum from scratch.
 *
 * Communication Skills: 3/4 You explained your approach clearly before coding, identified the technique, and discussed time/space complexity correctly. The trace-through at the end was a nice touch, though it got a little jumbled in places. In a real interview, being a bit more concise and structured when walking through examples would strengthen your communication.
 *
 * Great job overall — you demonstrated strong problem-solving instincts and clean coding. Keep it up! 🎉
 *
 * Absolutely! Here are some tips for your next interview:
 *
 * What you did well:
 *
 * You restated the problem in your own words before jumping in — that's a great habit, keep doing it.
 * You identified the optimal approach right away without needing hints.
 * You discussed time and space complexity proactively without being asked.
 * Your code was clean and well-structured.
 * Areas to improve:
 *
 * Test before declaring done. You had examples 2 and 3 commented out. In a real interview, make sure you uncomment and run all provided test cases before saying you're done. It shows thoroughness.
 *
 * Think about edge cases proactively. I had to prompt you about the tie-breaking case (multiple windows with the same max sum). Try to bring up edge cases yourself — things like empty arrays, single elements, all equal values, ties, etc. It shows depth of thinking.
 *
 * Be more structured in your trace-throughs. When tracing through an example, try to be methodical — state the window boundaries, the sum, and the current max clearly at each step. Something like: "Window [0,1]: sum=9, max=9. Window [1,2]: sum=4, max=9. Window [2,3]: sum=10, max=10, update bestStart=2." This makes it easier for the interviewer to follow.
 *
 * Clean up unnecessary code. You had leftover boilerplate code (the ArrayList of strings on lines 44-47) that wasn't relevant. Small thing, but
 */