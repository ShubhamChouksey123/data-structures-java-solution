# Ancestor Problems

> **[← Back to Trees Overview](Notes.md)**

---

## When to Use

✅ **Use ancestor algorithms for problems involving relationships between nodes at different levels**

### Keywords
- "lowest common ancestor" / "LCA"
- "ancestor"
- "common parent"
- "find parent"
- "node distance"
- "path between nodes"

### Examples
- Find lowest common ancestor of two nodes
- Calculate distance between nodes
- Find kth ancestor
- Determine if one node is ancestor of another
- Find path between two nodes

---

## Core Concept

An **ancestor** of a node is any node on the path from root to that node. **Lowest Common Ancestor (LCA)** is the deepest node that is an ancestor of both target nodes.

**Key Insight**: Use **post-order traversal** to propagate information from children to parent. LCA is found when both children return non-null.

**Complexity**: O(n) time, O(h) space

---

## Pattern 1: Lowest Common Ancestor (Binary Tree) ⭐ **IMPORTANT** ⭐

**Use Case**: Find LCA of two nodes in a binary tree

**Algorithm**:
1. If current node is null or matches either target, return it
2. Recursively search left and right subtrees
3. If both return non-null, current node is LCA
4. If only one returns non-null, return that (LCA is in that subtree)

**Complexity**: O(n) time, O(h) space

### Template

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // Base cases
    if (root == null) return null;
    if (root == p || root == q) return root;

    // Search in left and right subtrees
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);

    // Both found in different subtrees → current node is LCA
    if (left != null && right != null) {
        return root;
    }

    // Return whichever is not null (or null if both are null)
    return left != null ? left : right;
}
```

### Visual Example

```
Tree:        3
          /     \
         5       1
        / \     / \
       6   2   0   8
          / \
         7   4

Find LCA(5, 1):
- At 3: left=5, right=1 → both found → LCA = 3

Find LCA(5, 4):
- At 3: left=5 (contains both), right=null → LCA in left
- At 5: left=null, right=2 (contains 4) → both sides → LCA = 5

Find LCA(6, 4):
- At 5: left=6, right=2 (contains 4) → LCA = 5
```

---

## Pattern 2: Lowest Common Ancestor (BST)

**Use Case**: Find LCA in Binary Search Tree (leverage BST property)

**Algorithm**:
1. If both nodes < current, LCA in left subtree
2. If both nodes > current, LCA in right subtree
3. Otherwise, current node is LCA (nodes split or one equals current)

**Complexity**: O(h) time, O(1) space (iterative)

### Template

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // Iterative solution leveraging BST property
    while (root != null) {
        // Both in left subtree
        if (p.val < root.val && q.val < root.val) {
            root = root.left;
        }
        // Both in right subtree
        else if (p.val > root.val && q.val > root.val) {
            root = root.right;
        }
        // Split point or one equals root
        else {
            return root;
        }
    }
    return null;
}
```

### Visual Example

```
BST:         6
          /     \
         2       8
        / \     / \
       0   4   7   9
          / \
         3   5

Find LCA(2, 8):
- At 6: 2 < 6 and 8 > 6 → split → LCA = 6

Find LCA(2, 4):
- At 6: both < 6 → go left
- At 2: 2 == root or split → LCA = 2
```

---

## Pattern 3: Maximum Difference Between Node and Ancestor

**Use Case**: Find max difference between a node and its ancestor

**Algorithm**:
1. Track min and max values from root to current node
2. At each node, calculate: `max(|node.val - min|, |node.val - max|)`
3. Update min and max for children
4. Use DFS to explore all paths

**Complexity**: O(n) time, O(h) space

### Template

```java
class Solution {
    private int maxDiff = 0;

    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;
        dfs(root, root.val, root.val);
        return maxDiff;
    }

    private void dfs(TreeNode node, int minVal, int maxVal) {
        if (node == null) return;

        // Update max difference
        int currentDiff = Math.max(
            Math.abs(node.val - minVal),
            Math.abs(node.val - maxVal)
        );
        maxDiff = Math.max(maxDiff, currentDiff);

        // Update min and max for children
        int newMin = Math.min(minVal, node.val);
        int newMax = Math.max(maxVal, node.val);

        dfs(node.left, newMin, newMax);
        dfs(node.right, newMin, newMax);
    }
}
```

---

## Pattern 4: Kth Ancestor

**Use Case**: Find the kth ancestor of a given node

**Algorithm**:
1. Build parent pointers or path from root
2. Use binary lifting for efficient queries
3. Or DFS to find path and return kth node

**Complexity**:
- O(n) preprocessing, O(log n) query (binary lifting)
- O(n) time, O(h) space (simple DFS)

### Template (Simple Approach)

```java
class Solution {
    public TreeNode findKthAncestor(TreeNode root, TreeNode target, int k) {
        List<TreeNode> path = new ArrayList<>();
        if (!findPath(root, target, path)) return null;

        // kth ancestor is at position (path.size() - 1 - k)
        int ancestorIndex = path.size() - 1 - k;
        return ancestorIndex >= 0 ? path.get(ancestorIndex) : null;
    }

    private boolean findPath(TreeNode node, TreeNode target, List<TreeNode> path) {
        if (node == null) return false;

        path.add(node);

        if (node == target) return true;

        if (findPath(node.left, target, path) ||
            findPath(node.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }
}
```

---

## Pattern 5: LCA of Deepest Leaves

**Use Case**: Find LCA of all deepest leaves

**Algorithm**:
1. Find depth of deepest leaves
2. Find all nodes at that depth
3. Find LCA of those nodes
4. **Optimization**: Combine in single pass

**Complexity**: O(n) time, O(h) space

### Template

```java
class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).node;
    }

    private Result dfs(TreeNode node) {
        if (node == null) return new Result(null, 0);

        Result left = dfs(node.left);
        Result right = dfs(node.right);

        // Same depth: current node is LCA
        if (left.depth == right.depth) {
            return new Result(node, left.depth + 1);
        }

        // Different depths: return deeper one
        return left.depth > right.depth ?
            new Result(left.node, left.depth + 1) :
            new Result(right.node, right.depth + 1);
    }

    class Result {
        TreeNode node;
        int depth;
        Result(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
```

---

## Common Mistakes

### ❌ Mistake 1: Not Handling Node Equals Target

```java
// WRONG - doesn't handle when node itself is target
TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return null;
    // Missing: if (root == p || root == q) return root;
    TreeNode left = lca(root.left, p, q);
    TreeNode right = lca(root.right, p, q);
    return (left != null && right != null) ? root : ...;
}

// CORRECT - check if current node is target
TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    ...
}
```

### ❌ Mistake 2: BST LCA - Wrong Comparison

```java
// WRONG - doesn't handle when p or q equals root
if (p.val < root.val && q.val < root.val) {
    return lca(root.left, p, q);
} else if (p.val > root.val && q.val > root.val) {
    return lca(root.right, p, q);
}
// What if p == root or q == root?

// CORRECT - use <= and >=, or handle split condition
if (p.val < root.val && q.val < root.val) {
    return lca(root.left, p, q);
} else if (p.val > root.val && q.val > root.val) {
    return lca(root.right, p, q);
} else {
    return root;  // Handles equal case and split
}
```

### ❌ Mistake 3: Confusing Depth and Height

```java
// Depth: Distance from root (top-down)
// Height: Distance to deepest leaf (bottom-up)

// For deepest leaves, we need HEIGHT (bottom-up)
int depth(TreeNode root) {
    // This is actually HEIGHT, not depth!
    if (root == null) return 0;
    return 1 + Math.max(depth(root.left), depth(root.right));
}
```

---

## Problems

- [x] [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Maximum Difference Between Node and Ancestor](https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/) - Medium
- [ ] [Lowest Common Ancestor of Deepest Leaves](https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/) - Medium
- [ ] [Kth Ancestor of a Tree Node](https://leetcode.com/problems/kth-ancestor-of-a-tree-node/) - Hard

### Lowest Common Ancestor of a Binary Tree ⭐ **IMPORTANT** ⭐

**Problem**: [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) - Medium

**Why Important**: Fundamental tree problem appearing frequently in interviews. Tests understanding of post-order traversal and propagating information upward.

**Approach**:
1. Post-order traversal: check children first
2. If current node is p or q, return it
3. If both children return non-null, current is LCA
4. Otherwise return whichever child is non-null

**Complexity**: O(n) time, O(h) space

**Solution**:
```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // Base case: null or found target
    if (root == null || root == p || root == q) {
        return root;
    }

    // Search in subtrees (post-order)
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);

    // Both found in different subtrees → current is LCA
    if (left != null && right != null) {
        return root;
    }

    // One or both are null → return non-null one
    // If left is null: LCA is in right (or doesn't exist)
    // If right is null: LCA is in left (or doesn't exist)
    return left != null ? left : right;
}
```

**Key Points**:
- **Post-order**: Process children before parent
- **Three cases**:
  1. Both non-null → current is LCA
  2. One non-null → LCA is in that subtree
  3. Both null → targets not in this subtree
- **Return early**: If current is target, no need to search deeper
- **Works when**: p is ancestor of q (returns p as LCA)
- Common mistake: Not returning when `root == p || root == q`

---

## Key Takeaways

1. **Post-order traversal** for ancestor problems
2. **LCA pattern**: Check children, propagate upward
3. **BST optimization**: O(h) time using BST property
4. **Track min/max** for ancestor difference problems
5. **Three return cases**: both found, one found, none found
6. **Binary lifting** for efficient kth ancestor queries
7. **Complexity**: O(n) time, O(h) space for most problems

---

> **[← Back to Trees Overview](Notes.md)** | **[Binary Search Tree →](binary-search-tree.md)**
