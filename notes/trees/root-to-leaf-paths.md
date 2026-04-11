# Root to Leaf Path Problems

> **[← Back to Trees Overview](Notes.md)**

---

## When to Use

✅ **Use path tracking when problems require collecting or validating paths from root to leaves**

### Keywords
- "root to leaf"
- "path sum"
- "all paths"
- "sum of paths"
- "path with sum"
- "maximum path"

### Examples
- Find all root-to-leaf paths
- Check if path sum equals target
- Calculate sum of all root-to-leaf numbers
- Find maximum/minimum path sum
- Validate path properties

---

## Core Concept

**Root-to-Leaf Path** is a sequence of nodes from root to a leaf. Use **DFS with backtracking** to explore all paths while maintaining current path state.

**Key Pattern**:
1. Traverse tree using DFS
2. Maintain current path/sum
3. When leaf reached, process path
4. Backtrack to explore other paths

**Complexity**: O(n) time, O(h) space where h = height

---

## Pattern 1: All Root-to-Leaf Paths

**Use Case**: Collect all paths from root to leaves

**Algorithm**:
1. DFS with current path tracking
2. Add current node to path
3. If leaf, add path to result
4. Recurse on children
5. Backtrack (remove current node)

**Complexity**: O(n) time, O(h) space

### Template

```java
public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();
    if (root == null) return result;

    dfs(root, new ArrayList<>(), result);
    return result;
}

private void dfs(TreeNode node, List<Integer> path, List<String> result) {
    if (node == null) return;

    // Add current node to path
    path.add(node.val);

    // If leaf, add path to result
    if (node.left == null && node.right == null) {
        result.add(formatPath(path));
    } else {
        // Explore children
        dfs(node.left, path, result);
        dfs(node.right, path, result);
    }

    // Backtrack
    path.remove(path.size() - 1);
}

private String formatPath(List<Integer> path) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < path.size(); i++) {
        sb.append(path.get(i));
        if (i < path.size() - 1) sb.append("->");
    }
    return sb.toString();
}
```

### Visual Example

```
Tree:     1
        /   \
       2     3
      / \
     4   5

Paths:
1. [1, 2, 4] → "1->2->4"
2. [1, 2, 5] → "1->2->5"
3. [1, 3]    → "1->3"

DFS Trace:
path=[1] → path=[1,2] → path=[1,2,4] → record → backtrack
                      → path=[1,2,5] → record → backtrack
        → path=[1,3] → record → backtrack
```

---

## Pattern 2: Path Sum with Target

**Use Case**: Check if there exists a path with given sum

**Algorithm**:
1. Track remaining sum as we traverse
2. Subtract current value from target
3. At leaf, check if remaining sum equals current value
4. Return true if any path matches

**Complexity**: O(n) time, O(h) space

### Template

```java
public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) return false;

    // Leaf node: check if remaining sum equals leaf value
    if (root.left == null && root.right == null) {
        return root.val == targetSum;
    }

    // Explore paths with updated sum
    int remaining = targetSum - root.val;
    return hasPathSum(root.left, remaining) ||
           hasPathSum(root.right, remaining);
}
```

---

## Pattern 3: Sum Root to Leaf Numbers ⭐ **IMPORTANT** ⭐

**Use Case**: Treat each path as a number and sum all path numbers

**Algorithm**:
1. Build number as we traverse (multiply by 10 and add digit)
2. At leaf, add complete number to sum
3. Backtrack and continue

**Complexity**: O(n) time, O(h) space

### Template

```java
class Solution {
    private int totalSum = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return totalSum;
    }

    private void dfs(TreeNode node, int currentNum) {
        if (node == null) return;

        // Build number: shift left and add current digit
        currentNum = currentNum * 10 + node.val;

        // Leaf: add to total sum
        if (node.left == null && node.right == null) {
            totalSum += currentNum;
            return;
        }

        // Explore children with updated number
        dfs(node.left, currentNum);
        dfs(node.right, currentNum);
    }
}
```

### Visual Example

```
Tree:     1
        /   \
       2     3

Path 1: 1→2 = 12
Path 2: 1→3 = 13
Total = 12 + 13 = 25

Building numbers:
Start: 0
Node 1: 0*10+1 = 1
Node 2: 1*10+2 = 12 (leaf, add to sum)
Node 3: 1*10+3 = 13 (leaf, add to sum)
```

---

## Pattern 4: Maximum Path Sum ⭐ **IMPORTANT** ⭐

**Use Case**: Find maximum sum path (can start and end at any nodes)

**Algorithm**:
1. For each node, calculate max path through it
2. Path through node = left + right + node.val
3. Track global maximum
4. Return max single path for parent (left or right + node)

**Complexity**: O(n) time, O(h) space

### Template

```java
class Solution {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        // Calculate max gain from left and right (ignore negative)
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // Path through current node
        int pathThroughNode = node.val + leftGain + rightGain;

        // Update global max
        maxSum = Math.max(maxSum, pathThroughNode);

        // Return max single path for parent
        return node.val + Math.max(leftGain, rightGain);
    }
}
```

---

## Common Mistakes

### ❌ Mistake 1: Not Checking for Leaf Node

```java
// WRONG - checks at null instead of leaf
public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) return sum == 0;  // Wrong!
    return hasPathSum(root.left, sum - root.val) ||
           hasPathSum(root.right, sum - root.val);
}

// CORRECT - check at leaf
public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null) return false;
    if (root.left == null && root.right == null) {
        return root.val == sum;
    }
    return hasPathSum(root.left, sum - root.val) ||
           hasPathSum(root.right, sum - root.val);
}
```

### ❌ Mistake 2: Not Copying Path List

```java
// WRONG - all paths reference same list
if (leaf) {
    result.add(path);  // Wrong! Path gets modified
}

// CORRECT - add copy of path
if (leaf) {
    result.add(new ArrayList<>(path));
}
```

### ❌ Mistake 3: Forgetting Backtracking

```java
// WRONG - path keeps growing
void dfs(TreeNode node, List<Integer> path) {
    path.add(node.val);
    dfs(node.left, path);
    dfs(node.right, path);
    // Missing: path.remove(path.size() - 1);
}

// CORRECT - backtrack after exploring
void dfs(TreeNode node, List<Integer> path) {
    path.add(node.val);
    dfs(node.left, path);
    dfs(node.right, path);
    path.remove(path.size() - 1);  // Backtrack
}
```

---

## Problems

- [x] [Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/) - Easy
- [x] [Path Sum II](https://leetcode.com/problems/path-sum-ii/) - Medium
- [x] [Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Smallest String Starting From Leaf](https://leetcode.com/problems/smallest-string-starting-from-leaf) - Medium
- [x] [Insufficient Nodes in Root to Leaf Paths](https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/) - Medium
- [x] [Pseudo-Palindromic Paths in a Binary Tree](https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/) - Medium
- [x] [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/) - Hard ⭐ **IMPORTANT** ⭐


## Key Takeaways

1. **DFS with backtracking** for path problems
2. **Check leaf condition**: `left == null && right == null`
3. **Add copy of path** to result: `new ArrayList<>(path)`
4. **Backtrack after exploring**: `path.remove(path.size() - 1)`
5. **Pass by value** avoids backtracking: `currentNum * 10 + val`
6. **Two return values**: Update global state, return value for parent
7. **Complexity**: O(n) time, O(h) space

---

> **[← Back to Trees Overview](Notes.md)** | **[Ancestor Problems →](ancestor-problems.md)**
