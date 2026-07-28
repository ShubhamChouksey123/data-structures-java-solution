# Height Related Problems

> **[← Back to Trees Overview](Notes.md)**

---

## When to Use

✅ **Problems involving tree depth, balance, or diameter.**

**Keywords**: height / depth, balanced, diameter, maximum / minimum depth, longest path.

**Examples**: check if a tree is balanced, find the longest path, compute tree depth, shortest root→leaf depth.

---

## Core Concept

- **Height** = longest path from a node down to a leaf. **Depth** = distance from root down to a node.
- **Height(node) = 1 + max(left height, right height)**; computed **bottom-up** (postorder).
- **Null = 0, leaf = 1** (or null = −1, leaf = 0 — pick one and stay consistent).
- **Diameter** = longest path between any two nodes (need not pass through root).

**Complexity** (all patterns): O(n) time, O(h) recursion-stack space.

---

## Pattern 1: Maximum Depth (Height)

**Algorithm**: null → 0; otherwise `1 + max(left, right)`.

```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}
```

---

## Pattern 2: Minimum Depth

**Algorithm**: shortest root→leaf. A leaf returns 1. **If one child is null, recurse into the other** (a null child is not a leaf) — otherwise use `min` of both.

```java
public int minDepth(TreeNode root) {
    if (root == null) return 0;
    if (root.left == null)  return 1 + minDepth(root.right);   // one side null → take the other
    if (root.right == null) return 1 + minDepth(root.left);
    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
}
```

---

## Pattern 3: Balanced Binary Tree ⭐ **IMPORTANT** ⭐

**Use Case**: is every node's left/right height differing by ≤ 1?

**Algorithm**: single-pass — a helper returns the height, or **`-1` to signal "unbalanced"** and short-circuit up the tree.

**Complexity**: O(n) time, O(h) space.

```java
public boolean isBalanced(TreeNode root) {
    return height(root) != -1;
}

private int height(TreeNode root) {              // -1 = unbalanced somewhere below
    if (root == null) return 0;

    int left = height(root.left);
    if (left == -1) return -1;
    int right = height(root.right);
    if (right == -1) return -1;

    if (Math.abs(left - right) > 1) return -1;
    return 1 + Math.max(left, right);
}
```

---

## Pattern 4: Diameter of Binary Tree ⭐ **IMPORTANT** ⭐

**Use Case**: longest path between any two nodes — **may not pass through the root**.

**Algorithm**: while computing height, at each node the longest path *through* it is `left + right`; track that in a global max.

**Complexity**: O(n) time, O(h) space.

```java
class Solution {
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return maxDiameter;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        maxDiameter = Math.max(maxDiameter, left + right);   // path through this node
        return 1 + Math.max(left, right);
    }
}
```

**Visual** (diameter need not go through root):

```
        1
      /   \
     2     3        Max diameter = 3, path 4→2→5 (or 4→2→1)
    / \
   4   5
```

---

## Common Mistakes

1. **Min depth with one null child** — `1 + min(left, right)` wrongly returns 1 for a node with a single child. A null child isn't a leaf; recurse into the non-null side.
2. **Inconsistent height base case** — decide null = 0 (leaf = 1) *or* null = −1 (leaf = 0) and use it everywhere.
3. **Diameter only through root** — the longest path can sit entirely in a subtree, so update a **global** max at every node, don't just return `leftHeight + rightHeight` at the root.

---

## Problems

- [x] [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) - Easy
- [x] [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/) - Easy ⭐ **IMPORTANT** ⭐
- [x] [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) - Easy ⭐ **IMPORTANT** ⭐
- [x] [Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/) - Easy


## Key Takeaways

1. **Height is bottom-up** (postorder): `1 + max(left, right)`.
2. **Min depth**: handle a single null child specially.
3. **Balanced**: return `-1` to short-circuit — single pass, O(n).
4. **Diameter**: track a global max (`left + right`); may not pass through root.
5. **Pattern**: return a value for the parent while updating global state.

---

> **[← Back to Trees Overview](Notes.md)** | **[Root to Leaf Paths →](root-to-leaf-paths.md)**
