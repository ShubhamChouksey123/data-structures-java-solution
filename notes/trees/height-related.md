# Height Related Problems

> **[← Back to Trees Overview](Notes.md)**

---

## When to Use

✅ **Use height-based algorithms for problems involving tree depth, balance, or diameter calculations**

### Keywords
- "height" / "depth"
- "balanced"
- "diameter"
- "maximum depth"
- "minimum depth"
- "distance between nodes"

### Examples
- Check if tree is balanced
- Find longest path in tree
- Calculate tree depth
- Find minimum depth to leaf
- Verify height constraints

---

## Core Concept

**Height** is the longest path from a node to any leaf in its subtree. **Depth** is the distance from root to a node.

**Key Relationships**:
- **Height of node** = 1 + max(left height, right height)
- **Height of null** = 0 (or -1 in some definitions)
- **Height of leaf** = 1 (or 0 in some definitions)
- **Diameter** = max path length between any two nodes

**Complexity**: O(n) time, O(h) space where h = height

---

## Pattern 1: Maximum Depth (Height)

**Use Case**: Find the height of the tree (longest path from root to leaf)

**Algorithm**:
1. Base case: null node has height 0
2. Recursively calculate left and right heights
3. Return 1 + max(left, right)

**Complexity**: O(n) time, O(h) space

### Template

```java
public int maxDepth(TreeNode root) {
    if (root == null) return 0;

    int leftHeight = maxDepth(root.left);
    int rightHeight = maxDepth(root.right);

    return 1 + Math.max(leftHeight, rightHeight);
}
```

### Visual Example

```
Tree:       3
          /   \
         9    20
             /  \
            15   7

Heights:
- Node 9: 1
- Node 15: 1
- Node 7: 1
- Node 20: 1 + max(1,1) = 2
- Node 3: 1 + max(1,2) = 3

Max Depth = 3
```

---

## Pattern 2: Minimum Depth

**Use Case**: Find shortest path from root to any leaf

**Algorithm**:
1. Base case: null node returns infinity (or large value)
2. Leaf node returns 1
3. Return 1 + min(left, right)
4. **Important**: If one subtree is null, use the other (not null subtree might have leaf)

**Complexity**: O(n) time, O(h) space

### Template

```java
public int minDepth(TreeNode root) {
    if (root == null) return 0;

    // Leaf node
    if (root.left == null && root.right == null) {
        return 1;
    }

    // If one subtree is null, use the other
    if (root.left == null) return 1 + minDepth(root.right);
    if (root.right == null) return 1 + minDepth(root.left);

    // Both subtrees exist
    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
}
```

---

## Pattern 3: Balanced Binary Tree

**Use Case**: Check if tree is height-balanced (left and right heights differ by at most 1)

**Algorithm**:
1. Calculate height of each subtree
2. Check if |left height - right height| ≤ 1
3. Recursively check if left and right subtrees are balanced
4. **Optimization**: Return -1 for unbalanced, combine height check with balance check

**Complexity**: O(n) time, O(h) space

### Template

```java
public boolean isBalanced(TreeNode root) {
    return height(root) != -1;
}

// Returns height if balanced, -1 if unbalanced
private int height(TreeNode root) {
    if (root == null) return 0;

    int leftHeight = height(root.left);
    if (leftHeight == -1) return -1;  // Left subtree unbalanced

    int rightHeight = height(root.right);
    if (rightHeight == -1) return -1;  // Right subtree unbalanced

    // Check if current node is balanced
    if (Math.abs(leftHeight - rightHeight) > 1) {
        return -1;
    }

    return 1 + Math.max(leftHeight, rightHeight);
}
```

### Visual Example

```
Balanced:       Unbalanced:
    1               1
   / \             /
  2   3           2
 / \             /
4   5           3
                /
               4

Left: height=3   Left: height=4
Right: height=2  Right: height=0
|3-2|=1 ✓        |4-0|=4 ✗
```

---

## Pattern 4: Diameter of Binary Tree ⭐ **IMPORTANT** ⭐

**Use Case**: Find longest path between any two nodes (may not pass through root)

**Algorithm**:
1. Diameter at node = left height + right height
2. Track global maximum diameter
3. Return height for parent calculation
4. **Key Insight**: Diameter may not pass through root, check all nodes

**Complexity**: O(n) time, O(h) space

### Template

```java
class Solution {
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return maxDiameter;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // Update diameter: path through current node
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
```

### Visual Example

```
Tree:       1
          /   \
         2     3
        / \
       4   5

At node 1: diameter = 2+1 = 3
At node 2: diameter = 1+1 = 2
At node 3: diameter = 0+0 = 0
At node 4: diameter = 0+0 = 0
At node 5: diameter = 0+0 = 0

Max Diameter = 3 (path: 4→2→5 or 4→2→1)
```

---

## Common Mistakes

### ❌ Mistake 1: Minimum Depth - Not Handling One Null Child

```java
// WRONG - returns 1 for tree with only right child
public int minDepth(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
}
// For tree: 1 → 2 (only right child), returns 1 instead of 2

// CORRECT - check for null children
public int minDepth(TreeNode root) {
    if (root == null) return 0;
    if (root.left == null) return 1 + minDepth(root.right);
    if (root.right == null) return 1 + minDepth(root.left);
    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
}
```

### ❌ Mistake 2: Confusing Height Definitions

```java
// Two common definitions:
// Definition 1: Null = 0, Leaf = 1
int height(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(height(root.left), height(root.right));
}

// Definition 2: Null = -1, Leaf = 0
int height(TreeNode root) {
    if (root == null) return -1;
    return 1 + Math.max(height(root.left), height(root.right));
}

// Be consistent with the definition!
```

### ❌ Mistake 3: Diameter - Not Checking All Nodes

```java
// WRONG - only checks diameter through root
public int diameter(TreeNode root) {
    if (root == null) return 0;
    return height(root.left) + height(root.right);
}

// CORRECT - checks all nodes
private int maxDiameter = 0;
public int diameter(TreeNode root) {
    height(root);
    return maxDiameter;
}
private int height(TreeNode root) {
    if (root == null) return 0;
    int left = height(root.left);
    int right = height(root.right);
    maxDiameter = Math.max(maxDiameter, left + right);
    return 1 + Math.max(left, right);
}
```

---

## Problems

- [x] [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/) - Easy
- [x] [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/) - Easy ⭐ **IMPORTANT** ⭐
- [x] [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/) - Easy ⭐ **IMPORTANT** ⭐
- [x] [Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/) - Easy


## Key Takeaways

1. **Height**: Bottom-up calculation using post-order
2. **Minimum depth**: Handle one null child specially
3. **Balanced check**: Use -1 to signal unbalanced, optimize to single pass
4. **Diameter**: Track globally, may not pass through root
5. **Common pattern**: Return value for parent, update global state
6. **Null definitions**: Be consistent (0 or -1)
7. **Complexity**: O(n) time, O(h) space for all patterns

---

> **[← Back to Trees Overview](Notes.md)** | **[Root to Leaf Paths →](root-to-leaf-paths.md)**
