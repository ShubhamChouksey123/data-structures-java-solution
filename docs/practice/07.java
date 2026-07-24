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

    private static String longestBalancedSubsequence(String s){

        int n = s.length();
        /**
         * stack always containg index of opening brackets in s
         */
        Deque<Integer> stack = new ArrayDeque<>();

        boolean[] isValidIndex = new boolean[n];

        for(int i = 0 ; i < n ; i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.offerLast(i);
            }
            else {
                // s == ')'
                if(stack.isEmpty()){
                    isValidIndex[i] = false;
                }
                else {
                    // stack containg at least one opening bracket
                    isValidIndex[i] = true;
                    isValidIndex[stack.pollLast()] = true;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            if(isValidIndex[i]){
                builder.append(s.charAt(i));
            }
        }


        return builder.toString();
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }

        System.out.println("for s : `))(())(()` , balanced string : " + longestBalancedSubsequence("))(())(()"));

        System.out.println("for s : `(()()` , balanced string : " + longestBalancedSubsequence("(()()"));
        System.out.println("for s : `())(()` , balanced string : " + longestBalancedSubsequence("())(()"));
        System.out.println("for s : `(` , balanced string : " + longestBalancedSubsequence("("));
        ;
    }
}

// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # Longest Balanced Subsequence

// Given a string of parentheses, `s`, return the longest balanced _subsequence_.

// A subsequence of `s` (not a subarray) is a string obtained by removing some of the letters in `s`. In other words, you have to delete the smallest number of characters necessary to make `s` balanced and return the resulting string. There may be more than one valid answer.

// Example 1: s = "))(())(()"
// Output: "(())()". We removed the following characters:

//    "))(())(()".
//     ^^    ^
// We could have also removed

//    "))(())(()".
//     ^^     ^

// Example 2: s = "(()()"
// Output: "()()". We removed the following character:

//    "(()()"
//     ^
// We could have also removed

//    "(()()"
//        ^

// So "(())" is also a valid output.

// Example 3: s = "())(()"
// Output: "()()". We removed the following characters:

//    "())(()"
//       ^^
// There are several other ways to reach the same answer. For example, we could have also removed

//    "())(()"
//      ^  ^

// Example 4: s = "("
// Output: ""

// Constraints:

// - `0 <= s.length <= 10^5`
// - `s` consists only of `'('` and `')'`


/***
 * That wraps up our interview. Here's my assessment:
 *
 * Result: Pass
 *
 * If this were a real interview, I'd give you a hire recommendation. You identified the optimal approach, implemented working code, and correctly analyzed time and space complexity without needing hints on the core algorithm.
 *
 * Ratings:
 *
 * Coding ability: 3.5/4 — Your code is clean, correct, and handles edge cases well. The use of a boolean array instead of a set for tracking valid indices is a nice touch. Minor nit: the isValidIndex[i] = false on line 35 is redundant since boolean arrays default to false in Java, but that's a stylistic point.
 *
 * Problem solving ability: 3/4 — You arrived at the optimal solution. You initially struggled a bit with how to construct the output string, but you worked through it on your own and landed on the right approach of tracking valid indices rather than trying to build the result during the matching phase.
 *
 * Communication skills: 3/4 — You explained your thought process throughout and gave a clear walkthrough of your final solution. At times your explanations were a bit verbose, but overall you communicated your reasoning well.
 *
 * Nice work!
 *
 *
 *
 * Sure! Here are some tips based on this interview:
 *
 * Think before you code. You jumped into coding before fully solidifying your approach. In this case it worked out, but in harder problems, spending a few more minutes on the whiteboard phase — working through examples by hand and nailing down exactly how your algorithm constructs the output — will save you time debugging later.
 *
 * Use concrete examples to validate your approach. When I asked you how you'd maintain ordering in the result, you gave an abstract answer rather than tracing through a specific example. Walking through a small input step-by-step is the fastest way to catch flaws in your logic and also makes it much easier for your interviewer to follow along.
 *
 * Identify all cases upfront. You initially focused on unmatched opening brackets but hadn't explicitly thought about unmatched closing brackets until I asked. Before coding, try to enumerate all the cases your algorithm needs to handle. For parentheses problems, that means: what happens with extra (s? What happens with extra )s?
 *
 * Be more concise in explanations. Your walkthrough was thorough but could have been tighter. Practice explaining your approach in 3-4 sentences before diving into details. Something like: "I use a stack to match parentheses. I track which indices are part of a valid pair using a boolean array. Unmatched closing brackets are caught when the stack is empty, and unmatched opening brackets are whatever remains in the stack. Finally, I build the result from valid indices." Then elaborate if the interviewer wants more detail.
 */