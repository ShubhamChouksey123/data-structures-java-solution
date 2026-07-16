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

    private static int getMaxNonOverlappingIntervals(int[][] intervals){

        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> {
                    return Integer.compare(a[1], b[1]);
                }
        );

        int startIndex = 0, endIndex = 0;
        int nonOverlappingCount = 0;

        while(startIndex < n){

            int endTime = intervals[startIndex][1];
            endIndex = startIndex;

            while(endIndex + 1 < n && endTime >= intervals[endIndex + 1][0] ){
                endIndex++;
            }

            nonOverlappingCount++;
            startIndex = endIndex + 1;
        }

        return nonOverlappingCount;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }

        // int[][] intervals = new int[][]{{2, 3}, {1, 4}, {2, 3}, {3, 6}, {8, 9}};
        // int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}};
        // int[][] intervals = new int[][]{{1, 10}, {8, 9}, {2, 3}};
        // int[][] intervals = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        // int[][] intervals = new int[][]{{1, 3}, {2, 4}, {5, 6}};
        // int[][] intervals = new int[][]{{2, 3}, {1, 5}, {4, 6}, {7, 8}};
        int[][] intervals = new int[][]{{1, 3}, {2, 5}, {4, 6}, {7, 8}};
        int maxNonOverlappingIntervals = getMaxNonOverlappingIntervals(intervals);
        System.out.println("maxNonOverlappingIntervals : " + maxNonOverlappingIntervals);

    }
}

// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # Most Non-Overlapping Intervals

// Given a list, `intervals`, where each element is a pair of integers `[l, r]`, with `l ≤ r`, representing an interval (with both endpoints included). Return the largest number of non-overlapping intervals.

// Example 1:
// intervals = [[2, 3], [1, 4], [2, 3], [3, 6], [8, 9]]
// Output: 2
// For instance, [2, 3] and [8, 9] don't overlap. We can't add [3, 6] because it overlaps with [2, 3] at value 3.

// Example 2:
// intervals = [[1, 2], [2, 3], [3, 4]]
// Output: 2

// Example 3:
// intervals = [[1, 10], [8, 9], [2, 3]]
// Output: 2

// Constraints:

// - `0 ≤ intervals.length ≤ 10^5`
// - `intervals[i].length = 2`
// - `0 ≤ intervals[i][j] ≤ 10^9`


/**
 *
 *
 * Overall Verdict: Pass
 *
 * You identified the correct greedy approach — sorting by end time and greedily selecting non-overlapping intervals. Your code works correctly, you tested it against multiple cases, and you correctly identified the time and space complexity.
 *
 * Ratings:
 *
 * Coding ability: 3/4 — You wrote working code without needing help, and it handles the test cases correctly. The implementation is a bit unconventional (grouping rather than the more standard pick-and-track approach), but it produces correct results.
 *
 * Problem solving ability: 3/4 — You quickly identified the greedy approach and sorting by end time, which is the key insight. However, you had some difficulty explaining why your approach works and tracing through edge cases when challenged.
 *
 * Communication skills: 2/4 — This was the weakest area. There were times when it was hard to follow your explanations, and when I asked you to trace through examples, you sometimes gave answers without showing the trace. In a real interview, clearly articulating your thought process is critical.
 *
 * Tip for improvement: Practice explaining your
 *
 *
 * Sure! Here are some tips for improvement:
 *
 * Communication:
 *
 * Before jumping into code, take a moment to clearly explain your approach in plain English. For example: "I'll sort by end time, then greedily pick the first available interval and skip everything that overlaps with it."
 * When asked to trace through an example, walk through it step by step out loud. Show the interviewer exactly what's happening at each iteration — this builds confidence that you understand your own code.
 * When the interviewer challenges your approach, don't just say "it works" — explain why it works. For this problem, you could say something like: "Picking the interval that ends earliest leaves the most room for future intervals, so it's always a safe choice."
 * Problem Solving:
 *
 * When you arrive at a solution, try to think about why it's correct, not just that it passes test cases. Passing a few test cases doesn't prove correctness — interviewers want to hear your reasoning.
 * Try to come up with your own edge cases before the interviewer does. Think about: empty input, single element, all overlapping, none overlapping, intervals that share exactly one endpoint.
 * Coding:
 *
 * Consider writing the simpler and more standard version of the algorithm. Your grouping approach works, but the more conventional approach of tracking prevEnd and comparing each interval against it is easier to read and explain. Simpler code means fewer chances for bugs and easier discussion with the interviewer.
 * General Interview Tips:
 *
 * Think out loud throughout the process. Even if you're unsure, sharing your thought
 */