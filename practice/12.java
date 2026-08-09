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

    private static int[] findShortestDistance(List<int[]>[] adj, int V, int source){

        int[] minDist = new int[V];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[1], b[1]);
        });
        boolean[] visited = new boolean[V];

        minHeap.add(new int[]{source, 0});
        minDist[source] = 0;

        while(!minHeap.isEmpty()){

            int[] nodeDist = minHeap.poll();
            int node = nodeDist[0], dist = nodeDist[1];

            if(minDist[node] < dist) continue;
            visited[node] = true;

            for(int[] neighbourDist : adj[node]){
                int neighbour = neighbourDist[0];
                int newDistance = dist + neighbourDist[1];

                if(visited[neighbour]) continue;

                minDist[neighbour] = Math.min(minDist[neighbour], newDistance);
                minHeap.add(new int[]{neighbour, newDistance});
            }
        }

        return minDist;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());


        int[][] edges = new int[][]{{0, 1, 1}, {1, 2, 2}, {0, 2, 4}};
        int V = 3;
        int source = 0;
        // int source = 1;

        List<int[]>[] adj = new ArrayList[V];
        for(int i = 0 ; i < V ; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < edges.length ; i++){
            int[] edge = edges[i];
            int u = edge[0], v = edge[1],  w = edge[2];
            adj[u].add(new int[]{v, w});
        }

        int[] minDistances = findShortestDistance(adj, V, source);
        System.out.println(Arrays.toString(minDistances));

        String[] stringMinDistances = new String[V];
        for(int i = 0 ; i < V ; i++){
            if(minDistances[i] != Integer.MAX_VALUE) stringMinDistances[i] = String.valueOf(minDistances[i]);
            else stringMinDistances[i] = "inf";
        }
        System.out.println(Arrays.toString(stringMinDistances));

    }
}

// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # Distance in Weighted Graph

// You are given:

// - an edge list, `edges`, representing a directed weighted graph with positive weights,
// - `V`, the number of nodes in the graph, and
// - `source`, the index of a node

// Return an array of length `V` where the element at index `i` is the distance from `source` to node `i`, or infinity if there is no path from `source` to `i`.

// The edge list is a list of tuples `(u, v, w)` where `u` is the source node, `v` is the destination node, and `w` is the weight of the edge. All nodes are integers between `0` and `V-1`.

// Example 1:
// edges = [(0, 1, 1), (1, 2, 2), (0, 2, 4)]
// V = 3
// source = 0

// Output: [0, 1, 3]
// Explanation:
// - The shortest path from 0 to 1 is direct with weight 1.
// - The shortest path from 0 to 2 goes through 1: 0 -> 1 -> 2 with total weight 1 + 2 = 3, which is shorter than the direct edge with weight 4.

// Example 2:
// edges = [(0, 1, 1), (1, 2, 2), (0, 2, 4)]
// V = 3
// source = 1

// Output: [inf, 0, 2]
// Explanation:
// - Node 1 cannot reach node 0, so the distance is infinity.
// - Node 1 can reach itself with distance 0. Node 1 can reach node 2 directly with weight 2.

// Constraints:

// - The number of nodes `V` is at most `10^4`
// - The number of edges is at most `10^5`
// - All edge weights are positive integers
// - All nodes are integers between `0` and `V-1` (inclusive)


/***
 *
 * Overall: Pass
 *
 * You correctly identified Dijkstra's algorithm as the right approach, explained the lazy Dijkstra technique for handling duplicate heap entries, wrote clean and correct code, and arrived at the right time and space complexity (with a small nudge on the time complexity).
 *
 * Ratings:
 *
 * Coding ability: 3.5/4 — Your code was clean, correct, and well-structured. The adjacency list construction and Dijkstra implementation were both solid. Minor note: the visited array and the minDist[node] < dist check are slightly redundant, but it doesn't affect correctness.
 *
 * Problem solving ability: 3.5/4 — You immediately identified the right algorithm and understood the key nuances like handling obsolete copies in the heap. You needed a small nudge on time complexity but got there.
 *
 * Communication skills: 3/4 — You explained your approach and reasoning, but at times your explanations could have been a bit more structured and concise. In a real interview, clearly articulating each step before jumping into code helps a lot.
 *
 * Great job overall, Shubham!
 * Absolutely, here are some tips for your next interview:
 *
 * Structure your explanation before coding. Before jumping into code, take 30 seconds to lay out your approach step-by-step in a clear, numbered fashion. For example: "Step 1: Build adjacency list. Step 2: Initialize distance array. Step 3: Run Dijkstra with a min-heap." This makes it easier for the interviewer to follow along.
 *
 * Complexity analysis should be ready to go. In a real interview, you'll almost always be asked about time and space complexity. Practice deriving these on the spot so you can answer confidently without needing nudges. A good habit is to think about complexity while you're designing your approach, not just after.
 *
 * Talk through your code as you write it. You wrote the code and then asked me to review it. In a real interview, narrate what you're doing line by line as you write. This shows the interviewer your thought process and gives them a chance to course-correct you early if needed.
 *
 * Eliminate redundancy in your code. You had both a visited array and the minDist[node] < dist check. Either one alone is sufficient for correctness. Being aware of these redundancies shows attention to detail and code quality.
 *
 * Proactively discuss edge cases. Don't wait for the interviewer to ask — mention edge cases upfront when describing your approach. Things like empty graphs, disconnected nodes, single-node graphs, etc. This demonstrates thoroughness.
 */