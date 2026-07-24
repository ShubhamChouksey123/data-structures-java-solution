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

    private static String reverseSentence(String sentence){

        int n = sentence.length();
        List<String> words = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for(int i = 0 ; i < n ; i++){

            char c = sentence.charAt(i);
            if(c == ' '){
                String word = builder.toString();
                if(word.length() > 0){
                    words.add(word);
                    builder = new StringBuilder();
                }
            }
            else{
                builder.append(c);
            }
        }

        String word = builder.toString();
        if(word.length() > 0){
            words.add(word);
        }

        builder = new StringBuilder();
        if(words.size() > 0){
            builder.append(words.get(words.size() - 1));
        }

        for(int i = words.size() - 2 ; i >= 0 ; i--){
            builder.append(" ");
            builder.append(words.get(i));
        }

        return builder.toString();
    }

    public static void main(String[] args) {

        // String sentence = "   keep moving forward   ";
        // String sentence = "practice   makes perfect";
        // String sentence = "stay positive";
        String sentence = "   ";
        String ans = reverseSentence(sentence);
        System.out.println("ans : `" + ans + "`");
    }
}

// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # Reverse Words in String

// Given a string, `sentence`, reverse the sequence of words in it.

// - Words are defined as contiguous sequences of non-space characters.
// - The input string may include multiple spaces between words, as well as leading or trailing spaces.
// - The output should have words in reversed order, separated by a single space, with no leading or trailing spaces.

// Example 1:
// Input: "   keep moving forward   "
// Output: "forward moving keep"

// Example 2:
// Input: "practice   makes perfect"
// Output: "perfect makes practice"

// Example 3:
// Input: "stay positive"
// Output: "positive stay"

// Example 4:
// Input: "   "
// Output: ""

// Constraints:

// - `0 ≤ sentence.length ≤ 10^5`
// - `sentence` consists of printable ASCII characters


/**
 *
 * Your current solution works correctly, handles all the edge cases well, and you were able to clearly articulate the time and space complexity — O(n) time and O(n) space.
 *
 * You also demonstrated awareness of the in-place reversal approach (reverse entire string, then reverse each word), which is the more optimal approach in terms of auxiliary space usage, even though the overall space complexity remains O(n) due to the output string.
 *
 * If this were a real interview, I'd say you would pass. You arrived at a working solution, understood its complexity, and showed awareness of a more optimized approach. Your code was clean and handled edge cases properly.
 *
 * Here are your ratings:
 *
 * Coding ability: 3/4 — Your code is clean, correct, and handles edge cases. You didn't implement the in-place approach, but your solution is solid.
 * Problem solving ability: 3/4 — You solved the problem correctly and understood the optimization direction when guided, though you needed a nudge to get to the in-place reversal idea.
 * Communication skills: 3/4 — You explained your approach clearly and walked through examples. Could have been a bit more structured when discussing trade-offs between approaches.
 * Overall, solid performance! Nice job.
 *
 *
 * Sure! Here are some tips based on today's interview:
 *
 * Explore multiple approaches before coding. Today you jumped into coding your first approach fairly quickly. In a real interview, it's valuable to spend a couple of minutes brainstorming 2-3 possible approaches, briefly discussing their trade-offs, and then picking the best one to implement. This shows stronger problem-solving depth.
 *
 * Think about in-place techniques. When you see string or array manipulation problems, always ask yourself — can I do this in-place? Techniques like two-pointer swaps and in-place reversals come up frequently. Practice recognizing when they apply.
 *
 * Be more structured in your initial explanation. When you first describe your approach, try to lay it out in clear numbered steps before diving into details. For example: "Step 1: Parse words. Step 2: Reverse the order. Step 3: Build output." This makes it easier for the interviewer to follow.
 *
 * Drive the conversation proactively. When asked about optimizations, try to brainstorm on your own before saying "I'm not sure." Even if your ideas aren't perfect, showing that you're actively thinking through possibilities leaves a stronger impression.
 *
 * Discuss edge cases upfront. Before coding, explicitly list out edge cases — empty string, all spaces, single word, etc. This shows thoroughness and can help you avoid bugs.
 *
 * Practice the "reverse twice" pattern. This is a classic technique that shows up in many string and array problems. Make sure it's in your toolkit so you
 */