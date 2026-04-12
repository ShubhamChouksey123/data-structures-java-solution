# DSA Interview Questions - Pattern-Based Study Guide

> A curated collection of 160+ LeetCode problems organized by common patterns and techniques for technical interview preparation.

**📌 Reference**: This list is inspired by and adapted from [anubhav-0910/DSA](https://github.com/anubhav-0910/DSA/)

---

## 📊 Progress Tracking

Track your overall progress: `118/188 problems completed`

---

## 📚 Table of Contents

Date Format is DD-MM-YYYY

| # | Topic | Notes | Questions | Solved | Revised | Last Revision                      |
|---|-------|-------|-----------|--------|---|------------------------------------|
| 1 | [Fast and Slow Pointer](#1-fast-and-slow-pointer) | [📝](../notes/fast-and-slow-pointer/Notes.md) | 4 | ✅      | [ ] | 29-03-2026                         |
| 2 | [Overlapping Intervals](#2-overlapping-intervals) | [📝](../notes/overlapping-intervals/Notes.md) | 5 | 4/5    | [ ] | 30-03-2026, 03-04-2026             |
| 3 | [Prefix Sum](#3-prefix-sum) | [📝](../notes/prefix-sum/Notes.md) | 5 | ✅      | ✅ | 01-02-2026, 30-03-2026, 03-04-2026 |
| 4 | [Sliding Window](#4-sliding-window) | [📝](../notes/sliding-window/Notes.md) | 13 | ✅      | [ ] | 04-04-2026                         |
| 5 | [Two Pointers](#5-two-pointers) | [📝](../notes/two-pointers/Notes.md) | 6 | ✅      | [ ] |                                    |
| 6 | [Cyclic Sort](#6-cyclic-sort-index-based) | [📝](../notes/cyclic-sort/Notes.md) | 4 | ✅      | [ ] |                                    |
| 7 | [Reversal of Linked List](#7-reversal-of-linked-list-in-place) | [📝](../notes/reversal-of-linked-list/Notes.md) | 3 | ✅      | [ ] |                                    |
| 8 | [Matrix Manipulation](#8-matrix-manipulation) | [📝](../notes/matrix-manipulation/Notes.md) | 4 | ✅      | [ ] |                                    |
| 9 | [Breadth First Search (BFS)](#9-breadth-first-search-bfs) | [📝](../notes/breadth-first-search/Notes.md) | 4 | 3/4    | [ ] |                                    |
| 10 | [Depth First Search (DFS)](#10-depth-first-search-dfs) | [📝](../notes/depth-first-search/Notes.md) | 5 | ✅      | [ ] |                                    |
| 11 | [Backtracking](#11-backtracking) | [📝](../notes/backtracking/Notes.md) | 7 | ✅      | ✅ | 07-03-2026                         |
| 12 | [Modified Binary Search](#12-modified-binary-search) | [📝](../notes/binary-search/Notes.md) | 9 | ✅      | [ ] |                                    |
| 13 | [Bitwise XOR](#13-bitwise-xor) | [📝](../notes/bitwise-xor/Notes.md) | 5 | ✅      | [ ] |                                    |
| 14 | [Top 'K' Elements](#14-top-k-elements) | [📝](../notes/top-k-elements/Notes.md) | 4 | ✅      | [ ] |                                    |
| 15 | [K-way Merge](#15-k-way-merge) | [📝](../notes/k-way-merge/Notes.md) | 4 | ✅      | [ ] |                                    |
| 16 | [Two Heaps](#16-two-heaps) | [📝](../notes/two-heaps/Notes.md) | 3 | ✅      | [ ] | 17-03-2026                         |
| 17 | [Monotonic Stack](#17-monotonic-stack) | [📝](../notes/monotonic-stack/Notes.md) | 6 | ✅      | [ ] | 29-03-2026, 03-04-2026             |
| 18 | [Trees](#18-trees) | [📝](../notes/trees/Notes.md) | 32 | 29/32  | [ ] |                                    |
| 19 | [Dynamic Programming](#19-dynamic-programming) | - | 39 | [ ]    | [ ] |                                    |
| 20 | [Graphs](#20-graphs) | - | 13 | [ ]    | [ ] |                                    |
| 21 | [Greedy](#21-greedy) | - | 7 | [ ]    | [ ] |                                    |
| 22 | [Design Data Structure](#22-design-data-structure) | - | 6 | [ ]    | [ ] |                                    |

**📖 [Useful Learning Resources](#useful-articles-on-leetcode)**

---

## 1. Fast and Slow Pointer

**Description**: This technique uses two pointers moving at different speeds to solve problems involving cycles, such as finding the middle of a list, detecting loops, or checking for palindromes.

**Problems (4)**:
- [x] [Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/) - Medium
- [x] [Remove nth Node from the End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) - Medium
- [x] [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) - Medium
- [x] [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/) - Easy

---
## 2. Overlapping Intervals

**Description**: Intervals are often manipulated through sorting and merging based on their start and end times.

**Problems (5)**:
- [x] [Merge Intervals](https://leetcode.com/problems/merge-intervals/) - Medium
- [x] [Insert Interval](https://leetcode.com/problems/insert-interval/) - Medium
- [ ] [My Calendar II](https://leetcode.com/problems/my-calendar-ii/) - Medium
- [x] [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/) - Medium
- [x] [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) - Medium

---

## 3. Prefix Sum

**Description**: Prefix Sums/Products are techniques that store cumulative sums or products up to each index, allowing for quick subarray range queries.

**Problems (5)**:
- [x] [Find the Middle Index in Array](https://leetcode.com/problems/find-the-middle-index-in-array/) - Easy
- [x] [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) - Medium
- [x] [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) - Medium
- [x] [Number of Ways to Split Array](https://leetcode.com/problems/number-of-ways-to-split-array/) - Medium
- [x] [Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/) - Medium

---
## 4. Sliding Window

**Description**: A sliding window is a subarray or substring that moves over data to solve problems efficiently in linear time.

### Fixed Size Window (6 problems)
- [x] [Maximum Sum of Distinct Subarrays With Length K](https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/) - Medium
- [x] [Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold](https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/) - Medium
- [x] [Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences/) - Medium
- [x] [Permutation in String](https://leetcode.com/problems/permutation-in-string/) - Medium
- [x] [Sliding Subarray Beauty](https://leetcode.com/problems/sliding-subarray-beauty/) - Medium
- [x] [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) - Hard

### Variable Size Window (7 problems)
- [x] [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) - Medium
- [x] [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) - Medium
- [x] [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/) - Medium
- [x] [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) - Medium
- [x] [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) - Medium
- [x] [Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays) - Medium
- [x] [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) - Hard

---
## 5. Two Pointers

**Description**: The two pointers technique involves having two different indices move through the input at different speeds to solve various array or linked list problems.

**Problems (6)**:
- [x] [Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) - Medium
- [x] [Sort Colors](https://leetcode.com/problems/sort-colors/) - Medium *(Dutch National Flag)*
- [x] [Next Permutation](https://leetcode.com/problems/next-permutation/) - Medium
- [x] [Bag of Tokens](https://leetcode.com/problems/bag-of-tokens/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) - Hard

---
## 6. Cyclic Sort (Index-Based)

**Description**: Cyclic sort is an efficient approach to solve problems where numbers are consecutively ordered and must be placed in the correct index.

**Problems (4)**:
- [x] [Missing Number](https://leetcode.com/problems/missing-number/) - Easy
- [x] [Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) - Easy ⭐ **IMPORTANT** ⭐
- [x] [Set Mismatch](https://leetcode.com/problems/set-mismatch/) - Easy
- [x] [First Missing Positive](https://leetcode.com/problems/first-missing-positive/) - Hard

---
## 7. Reversal of Linked List (In-place)

**Description**: Reversing a linked list in place without using extra space is key for problems that require in-place list manipulations.

**Problems (3)**:
- [x] [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) - Easy
- [x] [Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/) - Medium
- [x] [Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) - Hard


---

## 8. Matrix Manipulation

**Description**: Problems involving 2D arrays (matrices) are often solved using row-column traversal or manipulation based on matrix properties.

**Problems (4)**:
- [x] [Rotate Image](https://leetcode.com/problems/rotate-image/) - Medium
- [x] [Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) - Medium
- [x] [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) - Medium
- [x] [Game of Life](https://leetcode.com/problems/game-of-life/) - Medium

---
## 9. Breadth First Search (BFS)

**Description**: BFS explores nodes level by level using a queue. It is particularly useful for shortest path problems.

**Problems (4)**:
- [x] [Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/) - Medium
- [x] [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) - Medium
- [x] [As Far from Land as Possible](https://leetcode.com/problems/as-far-from-land-as-possible/) - Medium
- [ ] [Word Ladder](https://leetcode.com/problems/word-ladder/) - Hard

---
## 10. Depth First Search (DFS)

**Description**: DFS explores as far as possible along a branch before backtracking. It's useful for graph traversal, pathfinding, and connected components.

**Problems (5)**:
- [x] [Number of Closed Islands](https://leetcode.com/problems/number-of-closed-islands/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Coloring a Border](https://leetcode.com/problems/coloring-a-border/) - Medium
- [x] [Number of Enclaves](https://leetcode.com/problems/number-of-enclaves/) - Medium *(DFS from boundary)*
- [x] [Time Needed to Inform All Employees](https://leetcode.com/problems/time-needed-to-inform-all-employees/) - Medium
- [x] [Find Eventual Safe States](https://leetcode.com/problems/find-eventual-safe-states/) - Medium *(Cycle detection)*

---
## 11. Backtracking

**Description**: Backtracking helps in problems where you need to explore all potential solutions, such as solving puzzles, generating combinations, or finding paths.

**Problems (7)**:
- [x] [Permutations II](https://leetcode.com/problems/permutations-ii/) - Medium
- [x] [Combination Sum](https://leetcode.com/problems/combination-sum/) - Medium
- [x] [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) - Medium
- [x] [N-Queens](https://leetcode.com/problems/n-queens/) - Hard
- [x] [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) - Hard
- [x] [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) - Medium
- [x] [Word Search](https://leetcode.com/problems/word-search/) - Medium

---


## 12. Modified Binary Search

**Description**: A modified version of binary search that applies to rotated arrays, unsorted arrays, or specialized conditions.

**Problems (9)**:
- [x] [Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/) - Medium
- [x] [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) - Medium
- [x] [Find Peak Element](https://leetcode.com/problems/find-peak-element/) - Medium
- [x] [Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/) - Medium
- [x] [Minimum Speed to Arrive on Time](https://leetcode.com/problems/minimum-speed-to-arrive-on-time/) - Medium
- [x] [Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/) - Medium
- [x] [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas) - Medium
- [x] [Find in Mountain Array](https://leetcode.com/problems/find-in-mountain-array/) - Hard
- [x] [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) - Hard

---

## 13. Bitwise XOR

**Description**: XOR is a powerful bitwise operator that can solve problems like finding single numbers or efficiently pairing elements.

**Problems (5)**:
- [x] [Missing Number](https://leetcode.com/problems/missing-number/) - Easy ⭐ **IMPORTANT** ⭐
- [x] [Single Number II](https://leetcode.com/problems/single-number-ii/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Single Number III](https://leetcode.com/problems/single-number-iii/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Find The Original Array of Prefix Xor](https://leetcode.com/problems/find-the-original-array-of-prefix-xor/) - Medium
- [x] [XOR Queries of a Subarray](https://leetcode.com/problems/xor-queries-of-a-subarray/) - Medium

---
## 14. Top 'K' Elements

**Description**: This pattern uses heaps or quickselect to efficiently find the top 'K' largest/smallest elements from a dataset.

**Problems (4)**:
- [x] [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) - Medium
- [x] [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) - Medium
- [x] [Ugly Number II](https://leetcode.com/problems/ugly-number-ii/) - Medium
- [x] [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) - Medium

---
## 15. K-way Merge

**Description**: The K-way merge technique uses a heap to efficiently merge multiple sorted lists or arrays.

**Problems (4)**:
- [x] [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/) - Medium
- [x] [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) - Medium
- [x] [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) - Hard
- [x] [Smallest Range Covering Elements from K Lists](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/) - Hard

---
## 16. Two Heaps

**Description**: This pattern uses two heaps (max heap and min heap) to solve problems involving tracking medians and efficiently managing dynamic data.

**Problems (3)**:
- [x] [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) - Hard
- [x] [Sliding Window Median](https://leetcode.com/problems/sliding-window-median/) - Hard
- [x] [IPO](https://leetcode.com/problems/ipo/) - Hard

---
## 17. Monotonic Stack

**Description**: A monotonic stack helps solve range queries by maintaining a stack of elements in increasing or decreasing order.

**Problems (6)**:
- [x] [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/) - Medium
- [x] [Next Greater Node In Linked List](https://leetcode.com/problems/next-greater-node-in-linked-list/) - Medium
- [x] [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) - Medium
- [x] [Online Stock Span](https://leetcode.com/problems/online-stock-span/) - Medium
- [x] [Maximum Width Ramp](https://leetcode.com/problems/maximum-width-ramp/) - Medium
- [x] [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) - Hard

---
## 18. Trees

**Description**: Comprehensive tree problems covering traversals, construction, height calculations, path problems, ancestors, and BST operations.

### Level Order Traversal (BFS in Binary Tree) - 8 problems
- [x] [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) - Medium
- [x] [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) - Medium
- [x] [Even Odd Tree](https://leetcode.com/problems/even-odd-tree/) - Medium
- [x] [Reverse Odd Levels of Binary Tree](https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/) - Medium
- [x] [Deepest Leaves Sum](https://leetcode.com/problems/deepest-leaves-sum/) - Medium
- [x] [Add One Row to Tree](https://leetcode.com/problems/add-one-row-to-tree/) - Medium
- [x] [Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/) - Medium
- [ ] [All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/) - Medium

### Tree Construction - 4 problems
- [x] [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) - Medium
- [x] [Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/) - Medium
- [x] [Maximum Binary Tree](https://leetcode.com/problems/maximum-binary-tree/) - Medium
- [x] [Construct Binary Search Tree from Preorder Traversal](https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/) - Medium

### Height Related Problems - 4 problems
- [x] [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) - Easy
- [x] [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/) - Easy
- [x] [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) - Easy
- [x] [Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/) - Easy

### Root to Leaf Path Problems - 7 problems
- [x] [Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/) - Easy
- [x] [Path Sum II](https://leetcode.com/problems/path-sum-ii/) - Medium
- [x] [Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers/) - Medium
- [x] [Smallest String Starting From Leaf](https://leetcode.com/problems/smallest-string-starting-from-leaf) - Medium
- [x] [Insufficient Nodes in Root to Leaf Paths](https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/) - Medium
- [x] [Pseudo-Palindromic Paths in a Binary Tree](https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/) - Medium
- [x] [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/) - Hard


### Ancestor Problems - 4 problems
- [x] [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) - Medium
- [x] [Maximum Difference Between Node and Ancestor](https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/) - Medium
- [ ] [Lowest Common Ancestor of Deepest Leaves](https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/) - Medium
- [ ] [Kth Ancestor of a Tree Node](https://leetcode.com/problems/kth-ancestor-of-a-tree-node/) - Hard

### Binary Search Tree - 5 problems
- [x] [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/) - Medium
- [x] [Range Sum of BST](https://leetcode.com/problems/range-sum-of-bst/) - Easy
- [x] [Minimum Absolute Difference in BST](https://leetcode.com/problems/minimum-absolute-difference-in-bst/) - Easy
- [x] [Insert into a Binary Search Tree](https://leetcode.com/problems/insert-into-a-binary-search-tree/) - Medium
- [x] [Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) - Medium

---
## 19. Dynamic Programming

**Description**: Comprehensive collection of DP problems covering various patterns including knapsack, subsequences, grids, strings, stocks, and partition problems.

### Take / Not Take (0/1 Knapsack) - 5 problems
**Pattern**: Solve optimization problems like selecting items with the max/min value under certain constraints.

- [ ] [House Robber II](https://leetcode.com/problems/house-robber-ii/) - Medium
- [ ] [Target Sum](https://leetcode.com/problems/target-sum/) - Medium
- [ ] [Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/) - Medium
- [ ] [Ones and Zeroes](https://leetcode.com/problems/ones-and-zeroes/) - Medium
- [ ] [Last Stone Weight II](https://leetcode.com/problems/last-stone-weight-ii/) - Medium

### Infinite Supply (Unbounded Knapsack) - 4 problems
**Pattern**: Similar to the 0/1 knapsack, but items can be chosen multiple times.

- [ ] [Coin Change](https://leetcode.com/problems/coin-change/) - Medium
- [ ] [Coin Change II](https://leetcode.com/problems/coin-change-ii/) - Medium
- [ ] [Perfect Squares](https://leetcode.com/problems/perfect-squares/) - Medium
- [ ] [Minimum Cost For Tickets](https://leetcode.com/problems/minimum-cost-for-tickets/) - Medium

### Longest Increasing Subsequence (LIS) - 5 problems
**Pattern**: Finding the longest subsequence of a given sequence where the elements are in ascending order.

- [ ] [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence) - Medium
- [ ] [Largest Divisible Subset](https://leetcode.com/problems/largest-divisible-subset/) - Medium
- [ ] [Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/) - Medium
- [ ] [Number of Longest Increasing Subsequence](https://leetcode.com/problems/number-of-longest-increasing-subsequence/) - Medium
- [ ] [Longest String Chain](https://leetcode.com/problems/longest-string-chain/) - Medium


### DP on Grids - 7 problems
**Pattern**: Solving problems on matrices that can be broken down into smaller overlapping subproblems.

- [ ] [Unique Paths II](https://leetcode.com/problems/unique-paths-ii/) - Medium
- [ ] [Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/) - Medium
- [ ] [Triangle](https://leetcode.com/problems/triangle/) - Medium
- [ ] [Minimum Falling Path Sum](https://leetcode.com/problems/minimum-falling-path-sum/) - Medium
- [ ] [Maximal Square](https://leetcode.com/problems/maximal-square/) - Medium
- [ ] [Cherry Pickup](https://leetcode.com/problems/cherry-pickup/) - Hard
- [ ] [Dungeon Game](https://leetcode.com/problems/dungeon-game/) - Hard

### DP on Strings - 9 problems
**Pattern**: Problems involving 2 strings where matching/mismatching characters determine the optimal solution.

- [ ] [Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/) - Medium
- [ ] [Longest Palindromic Subsequence](https://leetcode.com/problems/longest-palindromic-subsequence/) - Medium
- [ ] [Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/) - Medium
- [ ] [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) - Medium
- [ ] [Edit Distance](https://leetcode.com/problems/edit-distance/) - Medium
- [ ] [Minimum ASCII Delete Sum for Two Strings](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/) - Medium
- [ ] [Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences/) - Hard
- [ ] [Shortest Common Supersequence](https://leetcode.com/problems/shortest-common-supersequence/) - Hard
- [ ] [Wildcard Matching](https://leetcode.com/problems/wildcard-matching/) - Hard



### DP on Stocks - 5 problems
**Pattern**: Maximizing profit from buying and selling stocks over time while considering constraints.

- [ ] [Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/) - Medium
- [ ] [Best Time to Buy and Sell Stock III](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/) - Hard
- [ ] [Best Time to Buy and Sell Stock IV](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/) - Hard
- [ ] [Best Time to Buy and Sell Stock with Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) - Medium
- [ ] [Best Time to Buy and Sell Stock with Transaction Fee](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) - Medium

### Partition DP (Matrix Chain Multiplication) - 4 problems
**Pattern**: Dividing a sequence into partitions optimally to minimize or maximize a cost function.

- [ ] [Partition Array for Maximum Sum](https://leetcode.com/problems/partition-array-for-maximum-sum/) - Medium
- [ ] [Burst Balloons](https://leetcode.com/problems/burst-balloons/) - Hard
- [ ] [Minimum Cost to Cut a Stick](https://leetcode.com/problems/minimum-cost-to-cut-a-stick/) - Hard
- [ ] [Palindrome Partitioning II](https://leetcode.com/problems/palindrome-partitioning-ii/) - Hard

---

## 20. Graphs

**Description**: Advanced graph algorithms including topological sort, union-find, and shortest path algorithms.

### Topological Sort - 5 problems
**Pattern**: Dependency resolution using in-degree in directed acyclic graphs (DAGs).

- [ ] [Course Schedule](https://leetcode.com/problems/course-schedule/) - Medium
- [ ] [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) - Medium
- [ ] [Strange Printer II](https://leetcode.com/problems/strange-printer-ii/) - Hard
- [ ] [Sequence Reconstruction](https://leetcode.com/problems/sequence-reconstruction/) - Medium
- [ ] [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) - Hard



### Union Find (Disjoint Set) - 4 problems
**Pattern**: Solving problems involving connectivity or grouping in graphs.

- [ ] [Number of Operations to Make Network Connected](https://leetcode.com/problems/number-of-operations-to-make-network-connected/) - Medium
- [ ] [Redundant Connection](https://leetcode.com/problems/redundant-connection/) - Medium
- [ ] [Accounts Merge](https://leetcode.com/problems/accounts-merge/) - Medium
- [ ] [Satisfiability of Equality Equations](https://leetcode.com/problems/satisfiability-of-equality-equations/) - Medium


### Graph Algorithms - 4 problems
**Pattern**: Shortest paths, minimum spanning trees, and advanced graph traversal.

- [ ] [Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/) - Medium *(Kruskal's Algorithm)*
- [ ] [Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/) - Medium *(Dijkstra's Algorithm)*
- [ ] [Find the City With the Smallest Number of Neighbors at a Threshold Distance](https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/) - Medium *(Floyd-Warshall)*
- [ ] [Network Delay Time](https://leetcode.com/problems/network-delay-time) - Medium *(Bellman-Ford)*

---

## 21. Greedy

**Description**: Greedy algorithms make local optimal choices at each step, which lead to a global optimal solution for problems like scheduling and resource allocation.

**Problems (7)**:
- [ ] [Jump Game II](https://leetcode.com/problems/jump-game-ii/) - Medium
- [ ] [Gas Station](https://leetcode.com/problems/gas-station/) - Medium
- [ ] [Bag of Tokens](https://leetcode.com/problems/bag-of-tokens/) - Medium
- [ ] [Boats to Save People](https://leetcode.com/problems/boats-to-save-people/) - Medium
- [ ] [Wiggle Subsequence](https://leetcode.com/problems/wiggle-subsequence/) - Medium
- [ ] [Car Pooling](https://leetcode.com/problems/car-pooling/) - Medium
- [ ] [Candy](https://leetcode.com/problems/candy/) - Hard

---
## 22. Design Data Structure

**Description**: Building custom data structures to efficiently handle specific operations, like managing data access, updates, and memory usage. Focuses on optimizing performance and resource management.

**Problems (6)**:
- [ ] [Design Twitter](https://leetcode.com/problems/design-twitter/) - Medium
- [ ] [Design Browser History](https://leetcode.com/problems/design-browser-history/) - Medium
- [ ] [Design Circular Deque](https://leetcode.com/problems/design-circular-deque/) - Medium
- [ ] [Snapshot Array](https://leetcode.com/problems/snapshot-array/) - Medium
- [ ] [LRU Cache](https://leetcode.com/problems/lru-cache/) - Medium
- [ ] [LFU Cache](https://leetcode.com/problems/lfu-cache/) - Hard

---
---

## Useful Articles on LeetCode

**Curated learning resources for each pattern to deepen your understanding.**

### Two Pointers
- [Solved all Two Pointers problems in 100 days](https://leetcode.com/discuss/study-guide/1688903/Solved-all-two-pointers-problems-in-100-days)

### Sliding Window
- [Sliding Window Technique and Question Bank](https://leetcode.com/discuss/study-guide/1773891/Sliding-Window-Technique-and-Question-Bank)
- [C++ Maximum Sliding Window Cheatsheet Template!](https://leetcode.com/problems/frequency-of-the-most-frequent-element/solutions/1175088/C++-Maximum-Sliding-Window-Cheatsheet-Template/)

### Greedy
- [Greedy for Beginners: Problems & Sample Solutions](https://leetcode.com/discuss/general-discussion/669996/greedy-for-beginners-problems-sample-solutions)
- [Top Greedy Questions](https://leetcode.com/discuss/interview-question/3972722/Top-Greedy-Questions-helpful-for-OA-and-Interviews)

### Linked List
- [Become Master In Linked List](https://leetcode.com/discuss/study-guide/1800120/become-master-in-linked-list)
- [Must-Do LinkedList Problems on LeetCode](https://sarthak-acoustic.medium.com/must-do-linkedlist-problems-on-leetcode-19f47dc88fff)

### Trees
- [Tree Question Pattern | 2021 Placement](https://leetcode.com/discuss/study-guide/1337373/Tree-question-pattern-oror2021-placement)
- [Master Tree Patterns  ](https://leetcode.com/discuss/study-guide/5020529/Master-Tree-Patterns/)

### Binary Search
- [5 Variations of Binary Search](https://leetcode.com/discuss/interview-question/1322500/5-variations-of-Binary-search-(A-Self-Note))
- [Binary Search for Beginners: Problems & Patterns](https://leetcode.com/discuss/general-discussion/691825/Binary-Search-for-Beginners-Problems-or-Patterns-or-Sample-solutions)

### Dynamic Programming (DP)
- [Dynamic Programming Patterns](https://leetcode.com/discuss/general-discussion/458695/Dynamic-Programming-Patterns)
- [DP for Beginners: Problems & Patterns](https://leetcode.com/discuss/general-discussion/662866/DP-for-Beginners-Problems-or-Patterns-or-Sample-Solutions)


### Graphs
- [Graph For Beginners ](https://leetcode.com/discuss/general-discussion/655708/graph-for-beginners-problems-pattern-sample-solutions/)
- [Become Master In Graph](https://leetcode.com/discuss/study-guide/2360573/become-master-in-graph)
- [Graph algorithms + problems to practice](https://leetcode.com/discuss/study-guide/1326900/graph-algorithms-problems-to-practice)

### Bit Manipulation
- [Bit Manipulation Problem solving](https://leetcode.com/problems/sum-of-two-integers/solutions/84278/A-summary:-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently/)
- [All Types of Patterns for Bits Manipulations and How to use it](https://leetcode.com/discuss/interview-question/3695233/all-types-of-patterns-for-bits-manipulations-and-how-to-use-it)
----------------------------

**Happy LeetCoding !**