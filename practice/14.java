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

    private static boolean isRedundantParenthesis(String expression){

        int n = expression.length();

        Deque<Character> stack = new ArrayDeque<>();

        for(int i = 0 ; i < n ; i++){
            char c = expression.charAt(i);

            if( (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) continue;

            if(c != ')'){
                stack.offerLast(c);
                continue;
            }

            // we found the closing parenthesis
            boolean foundMathematicalOperator = false;
            while(!stack.isEmpty()){

                char topChar = stack.pollLast();
                if(topChar == '('){
                    if(!foundMathematicalOperator)
                        return true;
                    break;
                }
                if(topChar == '+' || topChar == '-' || topChar == '/' || topChar == '*'){
                    foundMathematicalOperator = true;
                }

            }
        }

        return false;
    }

    public static void main(String[] args) {

        System.out.println("(a): " + isRedundantParenthesis("(a)"));
        System.out.println("(a+b) : " + isRedundantParenthesis("(a+b)"));
        System.out.println("((a+b)) : " + isRedundantParenthesis("((a+b))"));
        System.out.println("(a-((b+(d*e))/f)-g) : " + isRedundantParenthesis("(a-((b+(d*e))/f)-g)"));
        System.out.println("a+b*(c) : " + isRedundantParenthesis("a+b*(c)"));
    }
}

// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # Redundant Parentheses

// Given a string representing a mathematical expression, return whether it has redundant parentheses.

// In this problem, a pair of parentheses is redundant when it does not group a subexpression consisting of two or more operands.

// Example 1:
// Input: expression = "(a)"
// Output: true
// Explanation: We don't need the parentheses because they aren't grouping two operands.

// Example 2:
// Input: expression = "(a+b)"
// Output: false
// Explanation: We need the parentheses because they are grouping two operands.

// Example 3:
// Input: expression = "((a+b))"
// Output: true
// Explanation: We don't need an outer pair of parentheses because the inner pair already groups the two operands.

// Example 4:
// Input: expression = "(a-((b+(d*e))/f)-g)"
// Output: false
// Explanation: All parentheses are necessary.

// Example 5:
// Input: expression = "a+b*(c)"
// Output: true

// Constraints:

// - `0 ≤ expression.length ≤ 10^4`
// - `expression` consists of lowercase letters, digits, and the characters `(`, `)`, `+`, `-`, `*`, `/`
// - The input is guaranteed to be a valid mathematical expression (no two consecutive operators, no two consecutive letters, no unbalanced parentheses, no operator without an operand on each side)


/**
 *
 * Here's my detailed feedback on your performance:
 *
 * Coding Ability: Score: 4/4 Justification: Code is correct, clean, and passes all test cases including edge cases. Good use of Deque as a stack.
 *
 * Problem-Solving: Score: 4/4 Justification: Identified the optimal stack-based approach independently with no hints needed. Correctly recognized the key insight about checking for operators.
 *
 * Communication: Score: 3/4 Justification: Initial explanation was a bit scattered and could have been more structured before jumping into the approach. Tracing through examples was good though.
 *
 * Overall Feedback: You would pass this interview. You quickly identified the right approach — using a stack and checking for operators between matching parentheses — without any hints. Your code is correct, handles all provided test cases, and your complexity analysis was spot on, including the nuanced explanation of why the nested while loop is still O(n). To reach a perfect score, work on articulating your thought process more clearly and concisely upfront before diving into the solution. For instance, explicitly stating "I'll skip operands, push operators and open parens, and when I see a closing paren I'll check if any operator exists before the matching open paren" would have been a crisp summary before coding.
 */