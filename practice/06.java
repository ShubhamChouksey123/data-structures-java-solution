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

    private static int maxDescendentChain;

    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value, TreeNode left, TreeNode right){
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int value){
            this.value = value;
        }
    }

    private static int descendantChainLength(TreeNode root, int depth){


        if(root == null)
            return 0;

        int leftChainDescendent = descendantChainLength(root.left, depth + 1);
        int rightChainDescendent = descendantChainLength(root.right, depth + 1);

        if(root.value != depth){
            return 0;
        }

        int descendentChainLegth = 1 + Math.max(leftChainDescendent, rightChainDescendent);
        maxDescendentChain = Math.max(maxDescendentChain, descendentChainLegth);

        return descendentChainLegth;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }

        TreeNode root = new TreeNode(7);

        // left subtree of root
        TreeNode depth1node1 = new TreeNode(1);
        root.left = depth1node1;


        TreeNode depth2node2left = new TreeNode(2);
        TreeNode node8 = new TreeNode(8);

        depth1node1.left = depth2node2left;
        depth1node1.right = node8;

        TreeNode node4 = new TreeNode(4);
        TreeNode depth3node3_1 = new TreeNode(3);

        depth2node2left.left = node4;
        depth2node2left.right = depth3node3_1;


        // right subtree of root

        TreeNode depth1node3 = new TreeNode(3);
        root.right = depth1node3;

        TreeNode depth2node2right = new TreeNode(2);
        depth1node3.right = depth2node2right;

        TreeNode depth3node3_2 = new TreeNode(3);
        TreeNode depth3node3_3 = new TreeNode(3);
        depth2node2right.left = depth3node3_2;
        depth2node2right.right = depth3node3_3;

        maxDescendentChain = 0;
        descendantChainLength(root, 0);
        System.out.println("maxDescendentChain : " + maxDescendentChain);
    }
}

// Your previous Plain Text content is preserved below:

// Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

// # Aligned Chain

// Given a binary tree, we say a node is _aligned_ if its value is equal to its depth (distance from root).
// A _descendant chain_ is a sequence of nodes where each node is the parent of the next node.
// Return the length of the longest descendant chain of aligned nodes. The chain does not need to start at the root.

// Example:
//                 7
//                / \
//               1   3
//              / \   \
//             2   8   2
//            / \     / \
//           4   3   3   3

// Output: 3
// The aligned nodes are the circled ones:
// Depth
//   0             7
//                / \
//   1          (1)   3
//              / \   \
//   2        (2)  8  (2)
//            / \     / \
//   3       4  (3) (3) (3)

// The longest descendant chain of aligned nodes is 1 -> 2 -> 3 on the left subtree.

// Here is a drawing of the same example:

// https://iio-beyond-ctci-images.s3.us-east-1.amazonaws.com/trees_fig9.png

// Constraints:

// - The number of nodes is at most `10^5`
// - The height of the tree is at most `500`
// - Each node has a value between `0` and `10^9`

/**
 *
 * Would you pass? Yes, I'd give you a pass.
 *
 * Ratings:
 *
 * Coding ability: 3/4 — You arrived at a correct and clean solution. The redundant leaf node case is minor, but the core logic is solid and works correctly.
 *
 * Problem solving ability: 3/4 — You identified the right approach quickly — passing depth down, returning chain length up, and tracking a global max. You didn't need any hints to get there.
 *
 * Communication skills: 3/4 — You explained your approach and walked through your code clearly. You could improve by being a bit more concise and structured in your explanations, but overall you communicated your thinking well.
 *
 * Nice job!
 */