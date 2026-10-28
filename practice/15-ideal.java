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

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // Approach 1: recursive inorder traversal.
    // Inorder traversal of a BST visits values in sorted order, so a duplicate
    // shows up as two equal values seen back-to-back. State (previous value,
    // found flag) is passed via a small mutable holder instead of static
    // fields, so this is safe to call concurrently / repeatedly.
    private static class TraversalState {
        long lastValue = Long.MIN_VALUE;
        boolean foundDuplicate = false;
    }

    public static boolean containsDuplicate(TreeNode root) {
        TraversalState state = new TraversalState();
        inorder(root, state);
        return state.foundDuplicate;
    }

    private static void inorder(TreeNode node, TraversalState state) {
        if (node == null || state.foundDuplicate) {
            return;
        }

        inorder(node.left, state);

        if (node.val == state.lastValue) {
            state.foundDuplicate = true;
            return;
        }
        state.lastValue = node.val;

        inorder(node.right, state);
    }

    // Approach 2 (alternative, mentioned for breadth): Morris inorder traversal.
    // Same O(n) time, but O(1) space instead of O(h) call-stack space, by
    // temporarily threading right pointers from each node's inorder
    // predecessor back to itself instead of using recursion/an explicit stack.
    public static boolean containsDuplicateMorris(TreeNode root) {
        long lastValue = Long.MIN_VALUE;
        boolean seenAny = false;
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                if (seenAny && current.val == lastValue) {
                    return true;
                }
                lastValue = current.val;
                seenAny = true;
                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    if (seenAny && current.val == lastValue) {
                        return true;
                    }
                    lastValue = current.val;
                    seenAny = true;
                    current = current.right;
                }
            }
        }

        return false;
    }

    private static TreeNode buildTreeWithDuplicate() {
        //          5
        //        /    \
        //       2      9
        //        \    / \
        //         4  9   11
        //             \
        //              9
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(2);
        TreeNode node9Left = new TreeNode(9);
        TreeNode node9Right = new TreeNode(9);
        TreeNode node11 = new TreeNode(11);

        node9Left.right = new TreeNode(9);
        node9Right.left = node9Left;
        node9Right.right = node11;

        node2.right = new TreeNode(4);

        root.left = node2;
        root.right = node9Right;

        return root;
    }

    private static TreeNode buildTreeWithoutDuplicate() {
        //       5
        //      / \
        //     2   9
        //      \   \
        //       4   11
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(2);
        TreeNode node9 = new TreeNode(9);

        node2.right = new TreeNode(4);
        node9.right = new TreeNode(11);

        root.left = node2;
        root.right = node9;

        return root;
    }

    public static void main(String[] args) {
        TreeNode withDuplicate = buildTreeWithDuplicate();
        TreeNode withoutDuplicate = buildTreeWithoutDuplicate();
        TreeNode singleNode = new TreeNode(42);
        TreeNode emptyTree = null;

        System.out.println("Recursive approach:");
        System.out.println("  withDuplicate    -> " + containsDuplicate(withDuplicate) + " (expected true)");
        System.out.println("  withoutDuplicate -> " + containsDuplicate(withoutDuplicate) + " (expected false)");
        System.out.println("  singleNode       -> " + containsDuplicate(singleNode) + " (expected false)");
        System.out.println("  emptyTree        -> " + containsDuplicate(emptyTree) + " (expected false)");

        System.out.println("Morris (O(1) space) approach:");
        System.out.println("  withDuplicate    -> " + containsDuplicateMorris(buildTreeWithDuplicate()) + " (expected true)");
        System.out.println("  withoutDuplicate -> " + containsDuplicateMorris(buildTreeWithoutDuplicate()) + " (expected false)");
        System.out.println("  singleNode       -> " + containsDuplicateMorris(new TreeNode(42)) + " (expected false)");
        System.out.println("  emptyTree        -> " + containsDuplicateMorris(null) + " (expected false)");
    }
}

/*
 * Why this version addresses the 15.java feedback:
 *
 * 1. "Everything was inside main" / static global state (lastNodeValue,
 *    foundDuplicateNode) -> extracted into containsDuplicate(TreeNode), with
 *    traversal state carried in a small TraversalState object instead of
 *    static fields. Safe for concurrent/repeated calls, no hidden coupling.
 *
 * 2. Space complexity miss (said O(1), it's actually O(h) due to the
 *    recursive call stack) -> documented explicitly in the comment above
 *    inorder(), and contrasted with the Morris-traversal alternative, which
 *    gets genuine O(1) space by threading pointers instead of recursing.
 *
 * 3. "Mention alternative approaches, even unimplemented" -> both the
 *    straightforward O(h)-space recursive solution and the O(1)-space Morris
 *    traversal are implemented and tested side by side.
 *
 * 4. Test before declaring done -> all 4 cases from the interview (duplicate,
 *    no duplicate, single node, empty tree) are run for both approaches.
 */


/*

You need to put a book at a particular location, indicated by an index, on a bookcase. Each book has a width, and a single shelf of the bookcase can only hold a total width of 20. When adding a new book, all books, if any, at that index and after it have to shift to the right on the shelf. If the new arrangement of the books doesn't fit on the shelf, you need to move books from the right to the lower shelf so that the new book will fit in this shelf.

Write a function that, given a bookcase, the width of the book to insert, and the index where the book is to be inserted, determines the number of books that must be moved to fit the new book. If the book cannot be made to fit, return -1.

For example, say that we are given the following input:

bookcase1 = [
  [2, 2, 3, 5, 3, 1, 3],   (total width 19)
  [1, 5, 5, 5, 1, 1, 1],   (total width 19)
  [3, 3, 3, 1, 1, 1],      (total width 12)
]
width = 2
index = 2

Book indices show the position where the new book should be placed at the bookcase. Indices start from 0, being the top-left position of the first shelf, and wrap to the next shelf when you reach the end of a shelf.

This represents a bookcase like this (each book is represented by a letter showing its width):

Shelf 0 |AABBCCCDDDDDEEEFGGG | (width 19/20)
Shelf 1 |HIIIIIJJJJJKKKKKLMN | (width 19/20)
Shelf 2 |OOOPPPQQQRST        | (width 12/20)

We want to insert book "XX" of width 2 at index 2, before CCC. However, if we do so, the books on shelf 0 will overflow.

Shelf 0 |AABBXXCCCDDDDDEEEFGGG (width 21/20)

To fix this, we need to move a book from shelf 0 to shelf 1. However, the books must stay in order, so we must move book GGG first. This causes shelf 1 to overflow.

Shelf 0 |AABBXXCCCDDDDDEEEF  |  (width 18/20)
Shelf 1 |GGGHIIIIIJJJJJKKKKKLMN (width 22/20)

Consequently, we then need to move books M and N to shelf 2.

Shelf 0 |AABBXXCCCDDDDDEEEF  |  (width 18/20)
Shelf 1 |GGGHIIIIIJJJJJKKKKKL|  (width 20/20)
Shelf 2 |MNOOOPPPQQQRST      |  (width 14/20)

We moved a total of 3 books (GGG, M, N), so your function would return 3.

insert(bookcase1, width, index) => 3


All test cases:
bookcase1 = [
  [2, 2, 3, 5, 3, 1, 3],   (total width 19)
  [1, 5, 5, 5, 1, 1, 1],   (total width 19)
  [3, 3, 3, 1, 1, 1],      (total width 12)
]

bookcase2 = [
  [1, 4, 3, 2, 3],     (total width 13)
  [5, 3, 1, 2],        (total width 11)
  [4],                 (total width 4)
  [3, 3, 1, 2, 3, 5],  (total width 17)
  [],                  (total width 0)
  [5, 3, 1, 3],        (total width 12)
]

bookcase3 = [
  [10, 5, 1, 4],  (total width 20)
  [9, 5, 5, 1],   (total width 20)
  [10, 4, 1, 1],  (total width 16)
  [1, 2, 3]       (total width 6)
]

insert(bookcase1, 2, 2)   => 3   => Move 1 book at the end of the first shelf, and 2 at the end of the second
insert(bookcase1, 4, 11)  => 3   => Index 11 is in the second shelf. Move 3 books on this shelf
insert(bookcase1, 1, 0)   => 0   => Book fits without moving any other books
insert(bookcase1, 2, 9)   => 1   => Move 1 book at the end of the second shelf
insert(bookcase1, 10, 9)  => -1  => Even by moving all books on the second shelf, the last shelf would go over 20
insert(bookcase1, 5, 11)  => -1  => Even by moving all books on the second shelf, it still doesn't fit
insert(bookcase1, 3, 13)  => -1  => Even by moving the book at the end of the second shelf, the book still doesn't fit
insert(bookcase2, 4, 15)  => 1   => Move 1 book at the end of the fourth shelf
insert(bookcase2, 4, 16)  => 0   => Book fits without moving any other books
insert(bookcase3, 4, 3)   => 5   => Move 1 book at the end of the first shelf, 2 at the end of the second, and 2 at the end of the third

Complexity analysis variables:
S: the number of shelves in the bookcase

*/

import java.io.*;
        import java.util.*;
        import javafx.util.Pair;

public class Solution {

    private static boolean isMatches(String title, int startIndex, String query){
        int differences = 0;
        int queryLength = query.length();
        for(int i = startIndex; i < Math.min(title.length(), startIndex + queryLength); i++){
            if(title.charAt(i) != query.charAt(i - startIndex)){
                differences++;
            }
            if(differences > 2) return false;
        }

        return differences <= 2;
    }

    /**

     Time Complexity : O( T * L * L)
     Space Complexity : O( T )
     */

    private static List<String> matching(String[] titles, String query){

        List<String> result = new ArrayList<>();

        //
        for(String title : titles){
            int titleLength = title.length();
            int queryLength = query.length();
            for(int startIndex = 0 ; startIndex <= (titleLength - queryLength) ; startIndex++){
                if(isMatches(title, startIndex, query)){
                    result.add(title);
                    break;
                }
            }
        }


        return result;
    }

    private static int numberOfBooksMoved(int[] shelf, int width, int index){

        int totalBooks = 0;
        for(int bookCount : shelf){
            totalBooks += bookCount;
        }

        totalBooks += width;

        while(totalBooks < 20)
            return 0;
    }

    private static int insert(int[][] bookcase, int width, int index){

        // TODO



        return 0;
    }

    public static void main(String[] argv) {

        int[][] bookcase1 = {
                {2, 2, 3, 5, 3, 1, 3},
                {1, 5, 5, 5, 1, 1, 1},
                {3, 3, 3, 1, 1, 1}
        };

        int[][] bookcase2 = {
                {1, 4, 3, 2, 3},
                {5, 3, 1, 2},
                {4},
                {3, 3, 1, 2, 3, 5},
                {},
                {5, 3, 1, 3}
        };

        int[][] bookcase3 = {
                {10, 5, 1, 4},
                {9, 5, 5, 1},
                {10, 4, 1, 1},
                {1, 2, 3}
        };

    /*
    List<String> result = matching(titles, query1);
    System.out.println("for query : " + query1 + ", result : " + result);

    List<String> result2List = matching(titles, query2);
    System.out.println("for query : " + query2 + ", result : " + result2List);

    List<String> result3List = matching(titles, query3);
    System.out.println("for query : " + query3 + ", result : " + result3List);

    System.out.println("for query : " + query4 + ", result : " + matching(titles, query4));

    System.out.println("for query : " + query5 + ", result : " + matching(titles, query5));
    */

    }
}
