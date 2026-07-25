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

    private static boolean hasDuplicates(int[] arr){

        Set<Integer> occuredElements = new HashSet<>();

        for(int num : arr){
            if(occuredElements.contains(num)){
                return true;
            }
            occuredElements.add(num);
        }

        return false;
    }

    public static void main(String[] args) {

        // int[] arr = new int[]{3, 2, 1, 2};
        // int[] arr = new int[]{1, 3, 2, 4};
        // int[] arr = new int[]{2, 4};
        // int[] arr = new int[]{4, 4, 4, 4};
        // int[] arr = new int[]{};
        int[] arr = new int[]{1};
        boolean arrayHasDuplicate = hasDuplicates(arr);
        System.out.println("arr : " + Arrays.toString(arr) + ", has duplicate : " + arrayHasDuplicate);
    }
}

// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # Has Duplicate

// You have an array of integers called `arr`. Determine if there is any integer that shows up multiple times. If at least one duplicate exists, return true; if no duplicates are found, return false. You may not use sorting to solve this problem.

// Example 1:
// Input: arr = [3, 2, 1, 2]
// Output: true
// Explanation: The element 2 appears more than once, so we return true.

// Example 2:
// Input: arr = [1, 3, 2, 4]
// Output: false
// Explanation: All elements are distinct, therefore we return false.

// Example 3:
// Input: arr = [2, 4]
// Output: false
// Explanation: All elements are distinct, therefore we return false.

// Example 4:
// Input: arr = [4, 4, 4, 4]
// Output: true
// Explanation: Element 4 appears more than once, so we return true.

// Constraints:

// - The length of `arr` is at most `10^5`
// - Each element in `arr` is an integer between `-10^9` and `10^9`
// - You may not use sorting to solve this problem


/**
 *
 *
 * Interview Feedback:
 *
 * You did a great job on this problem. You quickly identified the right approach, correctly ruled out sorting due to the constraint, and recognized that a HashSet was a cleaner choice than a HashMap since you only need to track presence rather than full frequency counts. Your code was correct on the first attempt, you accurately analyzed time and space complexity, and you identified relevant edge cases. Communication was clear throughout.
 *
 * Verdict: Pass ✅
 *
 * Ratings:
 *
 * Coding ability: 4/4 — Clean, correct code on the first try with good variable naming and test cases.
 * Problem solving ability: 3.5/4 — You identified multiple approaches, picked the optimal one, and refined from HashMap to HashSet. Solid reasoning throughout.
 * Communication skills: 3.5/4 — You explained your thought process clearly and walked through your approach before coding. Could be slightly more structured in initial problem analysis, but overall very good.
 * Nice work, Shubham!
 *
 * Absolutely, Shubham! Here are some tips for your next interview:
 *
 * Structure your initial analysis more clearly. When you first read the problem, try to explicitly state the brute force approach and its complexity before jumping to the optimal solution. For example, you could have mentioned that comparing every pair of elements would be O(n²), which motivates why we need a better approach. Interviewers love seeing that progression of thought.
 *
 * Be more deliberate with terminology. A couple of times you said "return false" when you meant "return true" and vice versa. In a real interview, small mix-ups like that can create confusion. Take a moment to be precise with your language.
 *
 * Talk through edge cases before coding. You identified edge cases like empty arrays and single-element arrays after coding. Try to think about those upfront — it shows the interviewer you're thorough and can help you avoid bugs.
 *
 * Explain your data structure choice proactively. You initially said HashMap, then I nudged you toward thinking about whether you needed full frequency counts. In your next interview, try to make that refinement on your own — articulate why a Set is sufficient over a Map without being prompted.
 *
 * Dry run your code out loud. After writing your solution, walk through one example step by step with the interviewer. This demonstrates confidence in your solution and helps catch any subtle bugs.
 *
 * Overall, you're in a strong position. Keep practicing and focus on tightening up your communication. Good luck with your next interview!
 */