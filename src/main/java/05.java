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

    private static int minTimeToDetour(int[] times){
        int n = times.length;

        if(n <= 2) return 0;

        int skipTwoTime = times[0];
        int skipOneTime = times[1];
        int skipZeroTime = times[2];

        for(int i = 3 ; i < n ; i++){
            int timeToReach = times[i] + Math.min(skipZeroTime, Math.min(skipOneTime, skipTwoTime));
            skipTwoTime = skipOneTime;
            skipOneTime = skipZeroTime;
            skipZeroTime = timeToReach;
        }

        return Math.min(skipZeroTime, Math.min(skipOneTime, skipTwoTime));
    }
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }

        int[] times = new int[]{8, 1, 2, 3, 9, 6, 2, 4};
        // int[] times = new int[]{8, 1, 2, 3, 9, 3, 2, 4};
        // int[] times = new int[]{10, 10};
        // int[] times = new int[]{5};
        // int[] times = new int[]{};
        int minDetourTime = minTimeToDetour(times);
        System.out.println("minDetourTime : " + minDetourTime);

    }
}

// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # Road Trip

// We are driving down a road with `n` rest stops between us and our destination. For each rest stop, our mapping software tells us how long of a detour it would be to stop there. We start before the first rest stop and our destination is past the last one.

// We are given an array of `n` positive integers, `times`, indicating the delay incurred to stop at each rest stop. If we don't want to go more than 2 rest stops without taking a break, what's the least amount of time we have to spend on detours?

// Example 1:
// times = [8, 1, 2, 3, 9, 6, 2, 4]
// Output: 6. The optimal rest stops are: [8, *1*, 2, *3*, 9, 6, *2*, 4]

// Example 2:
// times = [8, 1, 2, 3, 9, 3, 2, 4]
// Output: 5. The optimal rest stops are: [8, 1, *2*, 3, 9, *3*, 2, 4]

// Example 3:
// times = [10, 10]
// Output: 0. We don't need to make any stops.

// Example 4:
// times = [10]
// Output: 0. We don't need to make any stops.

// Example 5:
// times = []
// Output: 0. We don't need to make any stops.

// Check out the figure below for an illustration of the first example:

// https://iio-beyond-ctci-images.s3.us-east-1.amazonaws.com/road-trip-1.png

// Constraints:

// - `n` is at least `0` and at most `10^6`.
// - `times[i]` is at least `1` and at most `10^3`.


/**
 *
 *
 * Interview Assessment:
 *
 * If this were a real interview, you would pass. You arrived at the optimal solution, your code works correctly, you didn't need any major hints, and you correctly analyzed the time and space complexity.
 *
 * Ratings:
 *
 * Coding ability: 3.5/4 — Clean, working code with the space optimization. Variable naming could be slightly more descriptive, but overall solid.
 * Problem solving ability: 3/4 — You identified the DP approach and the recurrence relation. Early on you had the recurrence slightly off (adding 1 instead of times[i]), but you corrected it quickly. The space optimization was a nice touch.
 * Communication skills: 2.5/4 — You explained your approach, but it took a few rounds of back-and-forth to clearly articulate the recurrence relation. In a real interview, try to be more precise and concise when describing your approach upfront before jumping into code.
 * Well done!
 *
 * Sure! Here's some detailed feedback:
 *
 * What you did well:
 *
 * You correctly identified this as a dynamic programming problem
 * You came up with the right recurrence relation
 * You proactively thought about the space optimization from O(n) to O(1) before I even asked — that shows strong algorithmic thinking
 * You handled edge cases correctly (n ≤ 2 returns 0)
 * Your final code was clean and correct
 * Areas for improvement:
 *
 * Lead with a clear, structured explanation upfront. Before coding, try to lay out your full approach in one go. Something like: "This is a DP problem. I'll define dp[i] as the minimum detour time if I stop at rest stop i. The recurrence is dp[i] = times[i] + min(dp[i-1], dp[i-2], dp[i-3]). Base cases are the first three elements. Final answer is min of the last three values. I can optimize space to O(1) using three rolling variables." That kind of crisp summary would really impress an interviewer.
 *
 * Be precise with terminology early on. You initially said "jump at i = jump at i-1 + 1" which confused the cost with a count. In a real interview, getting the recurrence right on the first attempt signals strong problem-solving skills. Take an extra moment to think before speaking.
 *
 * Explain your variable names. Names like skipTwoTime, skipOneTime, skipZeroTime are decent, but during the
 */