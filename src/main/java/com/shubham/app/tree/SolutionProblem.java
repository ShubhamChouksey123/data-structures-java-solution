package com.shubham.app.tree;

// /* Problem Name is &&& Search Tree &&& PLEASE DO NOT REMOVE THIS LINE. */


// import org.junit.Test;
// import org.junit.runner.JUnitCore;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// import static org.junit.Assert.*;

// /*
//  Instructions to candidate.
//   1) Run this code in the REPL to observe its behaviour. The
//    execution entry point is main().
//   2) Implement the "put" and "contains" methods.
//   3) Fix the "inOrderTraversal" method.
//   4) Add additional relevant tests
//   5) If time permits, try to improve your implementation.
// */

// public class Solution {

//   static class BST  {

//     private Node root;


//     public BST() {
//       this.root = new Node();
//     }


//     private boolean containsUtil(Node root, int value){

//       if(root == null || root.val == null){
//         return false;
//       }
//       if(root.val == value){
//         return true;
//       }
//       else if(root.val < value){
//         return containsUtil( root.right,  value);
//       }
//       return containsUtil(root.left,  value);
//     }

//     private void putUtil(Node node, Integer value){

//       if(node == null){
//         node = new Node();
//         node.val = value;
//         return;
//       }
//       if(node.val == null){
//         node.val = value;
//         return;
//       }
//       if(node.val <= value){
//         putUtil(node.right,  value);
//         return;
//       }
//       if(root.left == null){
//         root.left = new Node();
//         node.val = value;
//         return;
//       }
//       putUtil(node.left,  value);
//     }

//     public void put(int value) {
//       // TODO: implement me
//       putUtil(this.root,  value);
//     }

//     public boolean contains(int value) {
//       // TODO :
//       return containsUtil(this.root,  value);
//     }

//     public List<Integer> inOrderTraversal() {
//       final ArrayList<Integer> acc = new ArrayList<>();
//       inOrderTraversal(root, acc);
//       return acc;
//     }

//     private void inOrderTraversal(Node node, List<Integer> acc) {
//       if (node == null) {
//         return;
//       }
//       if (node.val == null) {
//         return;
//       }
//       inOrderTraversal(node.left, acc);
//       acc.add(node.val);
//       inOrderTraversal(node.right, acc);
//     }

//     private static class Node {
//       Integer val;
//       Node left;
//       Node right;
//     }
//   }


//   @Test
//   public void testBST() throws Exception {
//     final BST searchTree = new BST();

//     searchTree.put(3);
//     searchTree.put(1);
//     searchTree.put(2);
//     searchTree.put(5);

//     assertFalse(searchTree.contains(0));
//     assertTrue(searchTree.contains(1));
//     assertTrue(searchTree.contains(2));
//     assertTrue(searchTree.contains(3));
//     assertFalse(searchTree.contains(4));
//     assertTrue(searchTree.contains(5));
//     assertFalse(searchTree.contains(6));

//     assertEquals(Arrays.asList(1,2,3,5), searchTree.inOrderTraversal());
//   }

//   // TODO: write some more tests

//   public static void main(String[] args) {
//     JUnitCore.main("Solution");
//   }

// }

/* Problem Name is &&& Largest Tree &&& PLEASE DO NOT REMOVE THIS LINE. */
/*
 **  Instructions:
 **
 **  Given a forest ( one or more disconnected trees ), find the root of largest tree
 **  and return its Id. If there are multiple such roots, return the smallest Id of them.
 **
 **  Complete the largestTree function in the editor below.
 **  It has one parameter, immediateParent, which is a map containing key-value pair indicating
 **  child -> parent relationship. The key is child and value is the corresponding
 **  immediate parent.
 **  Constraints
 **    - Child cannot have more than one immediate parent.
 **    - Parent can have more than one immediate child.
 **    - The given key-value pair forms a well-formed forest ( a tree of n nodes will have n-1 edges )
 **
 **  Example:
 **
 **  Input:
 **  { { 1 -> 2 }, { 3 -> 4 } }
 **
 **  Expected output: 2
 **  Explanation: There are two trees one having root of Id 2 and another having root of Id 4.
 **  Both trees have size 2. The smaller number of 2 and 4 is 2. Hence the answer is 2.
 */

import java.util.*;

class SolutionProblem {
    /*
     **  Find the largest tree.
     */


    public static int largestTreeUtil(Map<Integer, List<Integer>> graph, int value, Set<Integer> visited) {


        return 0;
    }

    public static void createGraph(Map<Integer, List<Integer>> graph, Map<Integer, Integer> immediateParent) {

        immediateParent.forEach(
                (child, parent) -> {

                    if (graph.containsKey(parent)) {
                        List list = new ArrayList<>();
                        list.add(child);
                        graph.put(parent, list);
                    } else {
                        graph.get(parent).add(child);
                    }
                }
        );
    }

    public static int largestTree(final Map<Integer, Integer> immediateParent) {


        Map<Integer, List<Integer>> graph = new HashMap<>();
        createGraph(graph, immediateParent);

        Set<Integer> visited = new HashSet<>();

        int connectedNodes = 1;
        int maxConnectedNodes = 1;
        graph.forEach(
                (parent, childs) -> {
                    if (visited.contains(parent)) {
                        return;
                    }


                }
        );


        return 0;
    }

    /*
     **  Returns true if the tests pass. Otherwise, returns false;
     */
    public static boolean testsPass() {
        // map of test cases to expected results
        final Map<Map<Integer, Integer>, Integer> testCases = new HashMap<>();

        // example
        final Map<Integer, Integer> testCaseOneKey = new HashMap<>() {{
            put(1, 2);
            put(3, 4);
        }};
        testCases.put(testCaseOneKey, 2);


        boolean passed = true;
        for (var entry : testCases.entrySet()) {
            final int actual = largestTree(entry.getKey());
            if (actual != entry.getValue()) {
                passed = false;
                System.out.printf("Failed for %s%n expected %s, actual %s%n", entry.getKey(), entry.getValue(), actual);
            }
        }

        return passed;
    }

    /*
     **  Execution entry point.
     */
    public static void main(String[] args) {
        // Run the tests
        if (testsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("Tests fail.");
        }
    }
}
