# Trees

> **[← Back to Questions List](../../docs/questions-list.md)**

---

## When to Use

✅ **Use tree algorithms when dealing with hierarchical data, parent-child relationships, or problems requiring efficient search/insert/delete operations**

### Keywords
- "binary tree"
- "BST" / "binary search tree"
- "tree traversal"
- "level order"
- "ancestor"
- "path from root to leaf"
- "tree construction"
- "diameter" / "height" / "depth"

### Examples
- Traversing directory structures
- Expression parsing (expression trees)
- Implementing autocomplete (trie)
- Range queries (segment trees)
- Hierarchical data organization

---

## Core Concept

A **tree** is a hierarchical data structure with nodes connected by edges. In binary trees, each node has at most two children (left and right).

**Key Properties**:
- **Root**: Top node with no parent
- **Leaf**: Node with no children
- **Height**: Longest path from root to any leaf
- **Depth**: Distance from root to a node
- **Balanced**: Height difference between subtrees ≤ 1

**Complexity** (Binary Search Tree):
- Search/Insert/Delete: O(log n) average, O(n) worst case
- Traversal: O(n) time, O(h) space for recursion stack

---

## Tree Sub-Topics

This section is divided into specialized topics. Click on any topic below to dive deeper:

### 1. [Level Order Traversal](level-order-traversal.md) (8 problems)
BFS-based traversal visiting nodes level by level. Essential for problems requiring level-wise processing.

---

### 2. [Tree Construction](tree-construction.md) (4 problems)
Building trees from traversal sequences (preorder, inorder, postorder). Requires understanding traversal properties.

**Completed**: 0/4

---

### 3. [Height Related Problems](height-related.md) (4 problems)
Problems involving tree height, depth, diameter, and balance. Core concept: height = 1 + max(left, right).

**Completed**: 0/4

---

### 4. [Root to Leaf Path Problems](root-to-leaf-paths.md) (7 problems)
Path-based problems from root to leaves. Common pattern: backtracking with path tracking.

**Completed**: 0/7

---

### 5. [Ancestor Problems](ancestor-problems.md) (4 problems)
Finding lowest common ancestors and ancestor-related queries. Key technique: post-order traversal.

**Completed**: 0/4

---

### 6. [Binary Search Tree](binary-search-tree.md) (5 problems)
BST-specific operations leveraging the ordering property: left < root < right.

**Completed**: 0/5

---

## Common Tree Traversals

### Inorder (Left → Root → Right)
```java
void inorder(TreeNode root) {
    if (root == null) return;
    inorder(root.left);
    process(root);
    inorder(root.right);
}
```
**Use**: BST operations (gives sorted order)

### Preorder (Root → Left → Right)
```java
void preorder(TreeNode root) {
    if (root == null) return;
    process(root);
    preorder(root.left);
    preorder(root.right);
}
```
**Use**: Tree construction, serialization

### Postorder (Left → Right → Root)
```java
void postorder(TreeNode root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    process(root);
}
```
**Use**: Tree deletion, calculating subtree properties

### Level Order (BFS)
```java
void levelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        process(node);
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
}
```
**Use**: Level-wise processing, shortest path in unweighted tree

---

## Common Mistakes

### ❌ Mistake 1: Not Handling Null Nodes

```java
// WRONG - NullPointerException
int height(TreeNode root) {
    return 1 + Math.max(height(root.left), height(root.right));
}

// CORRECT
int height(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(height(root.left), height(root.right));
}
```

### ❌ Mistake 2: Confusing Height and Depth

```java
// Height: Distance from node to deepest leaf (bottom-up)
// Depth: Distance from root to node (top-down)

// Height is calculated bottom-up (postorder)
int height(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(height(root.left), height(root.right));
}

// Depth is calculated top-down (preorder)
int depth(TreeNode root, TreeNode target, int d) {
    if (root == null) return -1;
    if (root == target) return d;
    int left = depth(root.left, target, d + 1);
    return left != -1 ? left : depth(root.right, target, d + 1);
}
```

### ❌ Mistake 3: Incorrect Base Case for Single Node

```java
// WRONG - Single node returns 0
int height(TreeNode root) {
    if (root == null) return 0;
    if (root.left == null && root.right == null) return 0;
    return 1 + Math.max(height(root.left), height(root.right));
}

// CORRECT - Single node returns 1
int height(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(height(root.left), height(root.right));
}
```

---

## Tree Node Definition

```java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
```

---

## Key Takeaways

1. **Base case first**: Always check for null before processing
2. **Recursion is natural**: Trees are recursive structures - use it
3. **Choose traversal wisely**:
   - **Preorder** for top-down processing
   - **Postorder** for bottom-up aggregation
   - **Inorder** for BST sorted order
   - **Level order** for level-wise processing
4. **Height vs Depth**: Height is bottom-up, depth is top-down
5. **BST property**: Left < Root < Right (leverage for O(log n) operations)
6. **Space complexity**: Recursion uses O(h) stack space where h = height

---

## Progress Summary

**Total**: 3/36 problems completed (8.3%)

**By Sub-Topic**:
- Level Order Traversal: 3/8 ✓
- Tree Construction: 0/4
- Height Related: 0/4
- Root to Leaf Paths: 0/7
- Ancestor Problems: 0/4
- Binary Search Tree: 0/5

---

> **[← Back to Questions List](../../docs/questions-list.md)** | **[Level Order Traversal →](level-order-traversal.md)**
