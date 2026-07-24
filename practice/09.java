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

    private static int[] mergedSortedArray(int[] arr1, int[] arr2){

        int n = arr1.length, m = arr2.length;
        int[] result = new int[n + m];
        int pointer1 = 0, pointer2 = 0;

        while(pointer1 < n || pointer2 < m){

            if(pointer1 < n && pointer2 < m){
                if(arr1[pointer1] <= arr2[pointer2]){
                    result[pointer1 + pointer2] = arr1[pointer1];

                    pointer1++;
                }
                else{
                    result[pointer1 + pointer2] = arr2[pointer2];
                    pointer2++;
                }
            }
            else if(pointer1 < n){
                result[pointer1 + pointer2] = arr1[pointer1];
                pointer1++;
            }
            else{
                result[pointer1 + pointer2] = arr2[pointer2];
                pointer2++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // int[] arr1 = new int[]{1, 3, 4, 5};
        // int[] arr2 = new int[]{2, 4, 4};

        // int[] arr1 = new int[]{-1};
        // int[] arr2 = new int[]{};

        // int[] arr1 = new int[]{1, 3, 5};
        // int[] arr2 = new int[]{2, 4, 6};

        int[] arr1 = new int[]{-1, 3, 5, 5, 5 , 5, 5,11, 15, 24};
        int[] arr2 = new int[]{0, 1, 1, 1, 5, 5, 5, 6, 6, 11, 11, 12};


        int[] result = mergedSortedArray(arr1, arr2);
        System.out.println(Arrays.toString(result));
    }
}

// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # Merge Two Sorted Arrays

// Given two sorted arrays of integers, `arr1` and `arr2`, return a new array that contains all the elements in `arr1` and `arr2` in sorted order, including duplicates.

// Example 1:
// Input:
// arr1 = [1, 3, 4, 5]
// arr2 = [2, 4, 4]
// Output: [1, 2, 3, 4, 4, 4, 5]
// Explanation: All elements are merged in sorted order.

// Example 2:
// Input:
// arr1 = [-1]
// arr2 = []
// Output: [-1]
// Explanation: When one array is empty, the result is just the other array.

// Example 3:
// Input:
// arr1 = [1, 3, 5]
// arr2 = [2, 4, 6]
// Output: [1, 2, 3, 4, 5, 6]

// Constraints:

// - arr1 and arr2 are sorted in ascending order
// - 0 ≤ arr1.length, arr2.length ≤ 10^6
// - -10^9 ≤ arr1[i], arr2[i] ≤ 10^9


/**
 *
 * Would you pass? Yes, you would pass this interview.
 *
 * Ratings:
 *
 * Coding ability: 3.5/4 — Your code is correct and handles all edge cases well. The use of pointer1 + pointer2 as the index was a nice touch. The code could be slightly cleaner (for instance, using separate while loops for the remaining elements instead of the nested if-else would improve readability), but overall solid.
 *
 * Problem solving ability: 3.5/4 — You quickly identified the two-pointer approach and understood how to handle the case when one pointer reaches the end. Good instincts.
 *
 * Communication skills: 3/4 — You explained your approach and walked through your code clearly. One area for improvement: try to be a bit more concise and structured when explaining. For example, stating the approach upfront, then the edge cases, then jumping into code would make the explanation flow more smoothly.
 *
 * Great job overall!
 *
 *
 * Structure your explanation upfront — Before jumping into the approach, briefly restate the problem in your own words, mention any assumptions, and then describe your approach. This shows the interviewer you fully understand the problem before coding.
 *
 * Mention edge cases early — Before or right after describing your approach, proactively list out edge cases you're thinking about (e.g., empty arrays, arrays of different lengths, all duplicates). This shows thoroughness.
 *
 * Be concise — When explaining your approach, try to keep it crisp. For example: "I'll use two pointers, one for each array. At each step, I pick the smaller element and advance that pointer. When one array is exhausted, I append the rest of the other." That's all you need before coding.
 *
 * Discuss complexity proactively — Don't wait to be asked. Once you finish coding, immediately state the time and space complexity. It shows confidence.
 *
 * Consider code readability — In your solution, the nested if-else inside the while loop works, but using three separate while loops (one for the main merge, one for leftover elements in arr1, one for arr2) is often cleaner and easier to follow. Interviewers appreciate readable code.
 *
 * Dry run your code — After writing your solution, quickly trace through a small example on paper or verbally. This catches bugs and shows the interviewer your attention to detail.
 *
 * Good luck with your next interview! You've got a solid foundation.
 */