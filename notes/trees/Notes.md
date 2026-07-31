# Trees

> **[← Back to Questions List](../../docs/questions-list.md)**

---

## When to Use

✅ **Hierarchical / parent-child data, or efficient search/insert/delete (BST).**

**Keywords**: binary tree, BST, traversal, level order, ancestor, root-to-leaf path, construction, diameter / height / depth.

**Examples**: directory trees, expression parsing, tries (autocomplete), segment trees (range queries).

---

## Core Concept

A **tree** is nodes connected by edges; a **binary tree** node has ≤ 2 children (`left`, `right`).

- **Root** = no parent · **Leaf** = no children · **Height** = longest root→leaf path · **Depth** = root→node distance · **Balanced** = subtree heights differ by ≤ 1.
- **Balanced height**: for a balanced tree with **N** nodes, height **H = O(log N)** (each level ~doubles the node count). A skewed/unbalanced tree degrades to **H = O(N)** — this is why balance keeps operations O(log n).
- **Array representation**: store a tree in an array so a node at index `i` has **left child at `2*i + 1`**, **right child at `2*i + 2`**, and **parent at `(i - 1) / 2`** (0-indexed). Compact for complete/perfect trees; used by heaps and segment trees.
- **BST complexity**: search / insert / delete O(log n) average, O(n) worst. Traversal O(n) time, O(h) recursion-stack space.

```java
class TreeNode { int val; TreeNode left, right; TreeNode(int v) { val = v; } }
```

---

## Sub-Topics & Revision Tracker

> 32 problems across 6 sub-topics — too many for one day. Revise **one sub-topic per session**; log the date (DD-MM-YYYY) and revise the row with the **oldest / empty** date next.

| # | Sub-Topic | Focus | Solved | Last Revision |
|---|-----------|-------|--------|---------------|
| 1 | [Level Order Traversal](level-order-traversal.md) | BFS level-by-level | 8/8 ✅ | 28-07-2026 |
| 2 | [Tree Construction](tree-construction.md) | build from pre / in / post-order | 4/4 ✅ | 28-07-2026 |
| 3 | [Height Related](height-related.md) | `height = 1 + max(L, R)` | 4/4 ✅ | 28-07-2026 |
| 4 | [Root to Leaf Paths](root-to-leaf-paths.md) | backtracking with path | 7/7 ✅ | 29-07-2026 |
| 5 | [Ancestor Problems](ancestor-problems.md) | LCA via post-order | 4/4 ✅ |  |
| 6 | [Binary Search Tree](binary-search-tree.md) | `left < root < right` | 5/5 ✅ |  |

**Total: 32/32 (100%) ✅** · append dates comma-separated after each revision.

---

## Patterns — Traversals

| Traversal | Order | Use |
|-----------|-------|-----|
| Inorder | L → Root → R | BST sorted order |
| Preorder | Root → L → R | construction, serialization |
| Postorder | L → R → Root | subtree aggregation, deletion |
| Level order | BFS, by level | level-wise processing, shortest path (unweighted) |

```java
// DFS — move process() to get pre-/in-/post-order
void dfs(TreeNode root) {
    if (root == null) return;      // base case first, always
    dfs(root.left);
    process(root);                 // here = inorder; before recursions = preorder; after = postorder
    dfs(root.right);
}

// BFS — level order
void levelOrder(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    if (root != null) q.offer(root);
    while (!q.isEmpty()) {
        TreeNode node = q.poll();
        process(node);
        if (node.left != null)  q.offer(node.left);
        if (node.right != null) q.offer(node.right);
    }
}
```

---

## Common Mistakes

1. **Missing null base case** → `NullPointerException`. Every recursion must start with `if (root == null) return ...;` before touching `.left` / `.right`.
2. **Wrong height base case**: `null` returns **0**, a single node returns **1**. Don't special-case a leaf to 0.
3. **Height vs Depth**: height is **bottom-up** (postorder, node → deepest leaf); depth is **top-down** (preorder, root → node).

```java
int height(TreeNode root) {                 // bottom-up; null = 0, leaf = 1
    if (root == null) return 0;
    return 1 + Math.max(height(root.left), height(root.right));
}
```

---

## Key Takeaways

1. **Base case first** — null check before touching children.
2. **Recursion is natural** — trees are recursive; lean on it.
3. **Pick the traversal**: preorder (top-down) · postorder (bottom-up aggregation) · inorder (BST sorted) · level order (level-wise).
4. **Height bottom-up, depth top-down.**
5. **BST invariant** `left < root < right` → O(log n) ops.
6. **Space** = O(h) recursion stack.

---

> **[← Back to Questions List](../../docs/questions-list.md)** | **[Level Order Traversal →](level-order-traversal.md)**
