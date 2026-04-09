# Tree Construction

> **[← Back to Trees Overview](Notes.md)**

---

## When to Use

✅ **Use tree construction when you need to build a tree from traversal sequences or array representations**

### Keywords
- "construct tree"
- "build tree"
- "preorder and inorder"
- "inorder and postorder"
- "from array"
- "deserialize"

### Examples
- Reconstruct tree from traversals
- Deserialize tree from string
- Build tree from parent array
- Convert array to tree structure

---

## Core Concept

**Tree Construction** involves rebuilding a binary tree from traversal sequences. Different traversal combinations provide different information:

**Key Insight**:
- **Preorder/Postorder**: Tells root position
- **Inorder**: Separates left and right subtrees

**Complexity**: O(n) time, O(n) space with HashMap optimization

---

## Pattern 1: Construct from Preorder + Inorder ⭐ **IMPORTANT** ⭐

**Use Case**: Build tree when given preorder and inorder traversals

**Algorithm**:
1. **Preorder** gives root (first element)
2. Find root in **inorder** using HashMap
3. Elements left of root in inorder → left subtree
4. Elements right of root in inorder → right subtree
5. Recursively build left and right subtrees

**Complexity**: O(n) time, O(n) space

### Template

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
    // Build HashMap for O(1) lookup of inorder indices
    Map<Integer, Integer> inorderMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
        inorderMap.put(inorder[i], i);
    }

    return build(preorder, 0, preorder.length - 1,
                 inorder, 0, inorder.length - 1, inorderMap);
}

private TreeNode build(int[] preorder, int preStart, int preEnd,
                       int[] inorder, int inStart, int inEnd,
                       Map<Integer, Integer> inorderMap) {
    if (preStart > preEnd || inStart > inEnd) return null;

    // Root is first element in preorder
    int rootVal = preorder[preStart];
    TreeNode root = new TreeNode(rootVal);

    // Find root position in inorder
    int inRoot = inorderMap.get(rootVal);
    int leftSize = inRoot - inStart;

    // Build left subtree
    root.left = build(preorder, preStart + 1, preStart + leftSize,
                      inorder, inStart, inRoot - 1, inorderMap);

    // Build right subtree
    root.right = build(preorder, preStart + leftSize + 1, preEnd,
                       inorder, inRoot + 1, inEnd, inorderMap);

    return root;
}
```

### Visual Example

```
Preorder: [3, 9, 20, 15, 7]
Inorder:  [9, 3, 15, 20, 7]

Step 1: Root = 3 (first in preorder)
        Find 3 in inorder → index 1
        Left: [9], Right: [15, 20, 7]

Step 2: Left subtree
        Preorder: [9], Inorder: [9]
        Root = 9 (leaf)

Step 3: Right subtree
        Preorder: [20, 15, 7], Inorder: [15, 20, 7]
        Root = 20
        Left: [15], Right: [7]

Result:     3
          /   \
         9    20
             /  \
            15   7
```

---

## Pattern 2: Construct BST from Preorder

**Use Case**: Build BST given only preorder traversal (BST property helps)

**Algorithm**:
1. Use preorder values with BST property
2. Values < root go left, values > root go right
3. Track valid range for each subtree [min, max]

**Complexity**: O(n) time, O(n) space

### Template

```java
public TreeNode bstFromPreorder(int[] preorder) {
    return build(preorder, 0, preorder.length - 1);
}

private TreeNode build(int[] preorder, int start, int end) {
    if (start > end) return null;

    TreeNode root = new TreeNode(preorder[start]);

    // Find first element > root (start of right subtree)
    int i = start + 1;
    while (i <= end && preorder[i] < root.val) {
        i++;
    }

    root.left = build(preorder, start + 1, i - 1);
    root.right = build(preorder, i, end);

    return root;
}
```

---

## Common Mistakes

### ❌ Mistake 1: Not Using HashMap for Inorder Lookup

```java
// WRONG - O(n²) time due to linear search in each call
int findInorder(int[] inorder, int val) {
    for (int i = 0; i < inorder.length; i++) {
        if (inorder[i] == val) return i;
    }
    return -1;
}

// CORRECT - O(n) time with HashMap preprocessing
Map<Integer, Integer> inorderMap = new HashMap<>();
for (int i = 0; i < inorder.length; i++) {
    inorderMap.put(inorder[i], i);
}
```

### ❌ Mistake 2: Wrong Index Calculation

```java
// WRONG - incorrect left subtree size calculation
int leftSize = inRoot;  // Wrong: doesn't account for inStart offset

// CORRECT
int leftSize = inRoot - inStart;
```

---

## Problems

- [x] [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/) - Medium                                
- [x] [Maximum Binary Tree](https://leetcode.com/problems/maximum-binary-tree/) - Medium                                                                                                              
- [x] [Construct Binary Search Tree from Preorder Traversal](https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/) - Medium

### Construct Binary Tree from Preorder and Inorder Traversal ⭐ **IMPORTANT** ⭐

**Problem**: [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) - Medium

**Why Important**: Fundamental tree construction problem testing understanding of traversal properties. Frequently asked in interviews. Template applies to similar problems.

**Approach**:
1. Preorder gives root (first element)
2. Find root in inorder to split left/right subtrees
3. Calculate left subtree size for preorder split
4. Recursively build left and right subtrees

**Complexity**: O(n) time with HashMap, O(n) space

**Solution**:
```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
    // HashMap for O(1) inorder index lookup
    Map<Integer, Integer> inorderMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
        inorderMap.put(inorder[i], i);
    }

    return build(preorder, 0, preorder.length - 1,
                 inorder, 0, inorder.length - 1, inorderMap);
}

private TreeNode build(int[] preorder, int preStart, int preEnd,
                       int[] inorder, int inStart, int inEnd,
                       Map<Integer, Integer> inorderMap) {
    if (preStart > preEnd || inStart > inEnd) {
        return null;
    }

    // Root is first element in preorder range
    int rootVal = preorder[preStart];
    TreeNode root = new TreeNode(rootVal);

    // Find root position in inorder
    int inRoot = inorderMap.get(rootVal);
    int leftSize = inRoot - inStart;

    // Build left subtree: next elements in preorder, left of root in inorder
    root.left = build(preorder, preStart + 1, preStart + leftSize,
                      inorder, inStart, inRoot - 1, inorderMap);

    // Build right subtree: remaining preorder, right of root in inorder
    root.right = build(preorder, preStart + leftSize + 1, preEnd,
                       inorder, inRoot + 1, inEnd, inorderMap);

    return root;
}
```

**Key Points**:
- **Preorder**: Root, Left, Right → root is always first
- **Inorder**: Left, Root, Right → root splits left and right
- **leftSize = inRoot - inStart** → accounts for offset
- **HashMap optimization** → O(1) lookup vs O(n) linear search
- Common mistake: using `inRoot` directly instead of `inRoot - inStart`

---

## Key Takeaways

1. **Preorder identifies root**, **Inorder separates subtrees**
2. **HashMap for O(1) lookup** of inorder indices
3. **Calculate left subtree size** carefully: `inRoot - inStart`
4. **Preorder + Inorder**: Build left first
5. **BST from Preorder**: Use BST property, no inorder needed
6. **Time**: O(n) with HashMap, **Space**: O(n)

---

> **[← Back to Trees Overview](Notes.md)** | **[Height Related →](height-related.md)**
