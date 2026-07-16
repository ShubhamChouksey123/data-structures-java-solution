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
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }

        int[] primes = new int[]{5};
        int k = 3;
        int n = primes.length;
        int mod = 1_000_000_007 ;

        Arrays.sort(primes);
        /**
         minHeap containing {value, prime}
         */
        Queue<long[]> minHeap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));

        for(int i = 0; i < Math.min(n, k) ; i++){
            minHeap.add(new long[]{(long)primes[i], (long)primes[i]});
        }

        int[] ans = new int[k];
        int count = 0;
        long sum = 0;
        while(count < k){
            long[] top = minHeap.poll();
            long val = top[0];
            long prime = top[1];
            if(mod > val * prime){
                minHeap.add(new long[]{val * prime, prime});
            }


            ans[count] = (int)val;
            sum += val;
            sum = sum % mod;
            count++;
        }
        System.out.println("ans : " + Arrays.toString(ans));
        System.out.println("sum : " + sum);
    }
}

// Time complexity : O(k * log(k))
// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # Sum Of First K Prime Powers

// Given a non-empty array, `primes`, of **distinct prime** numbers, and a positive number `k`, return the sum of the first `k` numbers that are a positive power of a number in `primes`.

// If the answer is larger than `10^9+7`, return it modulo `10^9+7`.

// Example 1: primes = [2], k = 1
// Output: 2
// The first positive power of 2 is 2^1 = 2.

// Example 2: primes = [5], k = 3
// Output: 155
// The first 3 positive powers of 5 are 5, 25, and 125.

// Example 3: primes = [2, 3], k = 7
// Output: 69
// The first 7 numbers that are a positive power of 2 or 3 are 2, 3, 4, 8, 9, 16, and 27.

// Constraints:

// - `1 <= primes.length <= 10^4`
// - Each element in `primes` is a distinct prime number
// - `0 <= k <= 10^6`


// PriorityQueue<int[]>

// {value, prime}


/**
 * Overall Feedback:
 *
 * You came up with the right approach - using a min-heap with {value, prime} pairs, which is the optimal solution. Your code works correctly for the test cases. However, there were some areas of concern:
 *
 * Would you pass? This would be a borderline pass/fail in a real interview.
 *
 * Ratings:
 *
 * Coding ability: 3/4 - You wrote working code that handles the core logic and edge cases (like stopping when values exceed the threshold). The code could be cleaner (e.g., extracting it into a method rather than keeping it all in main), but it works.
 *
 * Problem solving ability: 2/4 - You identified the right data structure and approach quickly, which is great. However, you struggled significantly with the modulo issue and the overflow concern. It took several hints to arrive at the idea of simply not reinserting values that exceed the threshold.
 *
 * Communication skills: 2/4 - Your explanations were sometimes hard to follow, and there were moments where it was unclear what you were trying to convey. In a real interview, clearly articulating your thought process is critical. Also, you struggled with the time complexity analysis, which is something interviewers expect you to nail.
 *
 * Key takeaway: Practice explaining your thought process more clearly, and make sure you're comfortable analyzing time
 *
 *
 *
 * You're welcome! Here are a few tips for your next interview:
 *
 * Practice complexity analysis - Make sure you can confidently identify the size of your data structures and the cost of operations on them. Knowing that heap operations cost O(log n) where n is the heap size should be second nature.
 *
 * Think about edge cases early - The overflow/modulo issue was a significant part of this problem. Try to identify these tricky aspects upfront before coding.
 *
 * Communicate clearly - Try to speak in complete, structured thoughts. For example: "My approach is X. I'll use Y data structure because Z. The time complexity is A because B." This helps the interviewer follow your reasoning.
 *
 * Clean code structure - In a real interview, extract your solution into a proper method with parameters and a return value rather than writing everything in main. It shows good software engineering habits.
 *
 * Good luck with your preparation! Keep practicing and you'll get there.
 */