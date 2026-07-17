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

    private static int minTimeToReachEnd(int[] times){

        int n = times.length;

        if(n <= 2) return 0;

        int skipTwoTime = times[0];
        int skipOneTime = times[1];
        int skipZeroTime = times[2];

        for(int i = 3 ; i < n  i++){
            int timToReach = times[i] + Math.min(skipZeroTime, Math.min(skipOneTime, skipTwoTime));
            skipTwoTime = skipOneTime;
            skipOneTime = skipZeroTime;
            skipZeroTime = timToReach;
        }

        return Math.min(skipTwoTime, Math.min(skipOneTime, skipZeroTime));
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

