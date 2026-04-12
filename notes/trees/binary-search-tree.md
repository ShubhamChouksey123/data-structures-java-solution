# Binary Search Tree (BST)

> **[← Back to Trees Overview](Notes.md)**

---

## When to Use

✅ **Use BST algorithms when the tree follows the ordering property: left < root < right**

### Keywords
- "binary search tree" / "BST"
- "sorted"
- "validate BST"
- "inorder gives sorted"
- "search in BST"
- "insert into BST"
- "range sum"

### Examples
- Validate if tree is BST
- Search for value in BST
- Insert/delete nodes in BST
- Find kth smallest element
- Range sum queries
- Convert sorted array to BST

---

## Core Concept

A **Binary Search Tree** maintains the property: For every node, all nodes in **left subtree < node < all nodes in right subtree**.

**Key Properties**:
- **Inorder traversal** gives sorted sequence
- **Search/Insert/Delete**: O(log n) average, O(n) worst case
- **Range queries** efficient due to ordering

**Complexity**:
- Balanced BST: O(log n) for search/insert/delete
- Unbalanced: O(n) worst case (becomes linked list)

---

## Pattern 1: Validate BST ⭐ **IMPORTANT** ⭐

**Use Case**: Check if a binary tree is a valid BST

**Algorithm**:
1. Track valid range [min, max] for each node
2. Current node must be in range
3. Left child range: [min, node.val)
4. Right child range: (node.val, max]

**Complexity**: O(n) time, O(h) space

### Template

```java
public boolean isValidBST(TreeNode root) {
    return validate(root, null, null);
}

private boolean validate(TreeNode node, Integer min, Integer max) {
    if (node == null) return true;

    // Check if current node violates constraints
    if ((min != null && node.val <= min) ||
        (max != null && node.val >= max)) {
        return false;
    }

    // Validate left subtree: [min, node.val)
    // Validate right subtree: (node.val, max]
    return validate(node.left, min, node.val) &&
           validate(node.right, node.val, max);
}
```



## Pattern 2: Search in BST

**Use Case**: Find if value exists in BST

**Algorithm**:
1. Compare with current node
2. If equal, found
3. If less, search left
4. If greater, search right

**Complexity**: O(log n) average, O(n) worst case

### Template

```java
// Recursive
public TreeNode searchBST(TreeNode root, int val) {
    if (root == null || root.val == val) return root;

    return val < root.val ?
        searchBST(root.left, val) :
        searchBST(root.right, val);
}

// Iterative (space-efficient)
public TreeNode searchBST(TreeNode root, int val) {
    while (root != null && root.val != val) {
        root = val < root.val ? root.left : root.right;
    }
    return root;
}
```

---

## Pattern 3: Insert into BST

**Use Case**: Insert a new node while maintaining BST property

**Algorithm**:
1. Find correct position (leaf where it should be)
2. Create new node
3. Attach to parent

**Complexity**: O(log n) average, O(n) worst case

### Template

```java
// Recursive
public TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);

    if (val < root.val) {
        root.left = insertIntoBST(root.left, val);
    } else {
        root.right = insertIntoBST(root.right, val);
    }

    return root;
}

// Iterative
public TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null) return new TreeNode(val);

    TreeNode curr = root;
    while (true) {
        if (val < curr.val) {
            if (curr.left == null) {
                curr.left = new TreeNode(val);
                break;
            }
            curr = curr.left;
        } else {
            if (curr.right == null) {
                curr.right = new TreeNode(val);
                break;
            }
            curr = curr.right;
        }
    }

    return root;
}
```

---

## Pattern 4: Range Sum in BST

**Use Case**: Sum all node values within a given range [low, high]

**Algorithm**:
1. If node.val < low, skip left subtree
2. If node.val > high, skip right subtree
3. If in range, add to sum and check both subtrees

**Complexity**: O(n) worst case, O(log n + k) average where k = nodes in range

### Template

```java
public int rangeSumBST(TreeNode root, int low, int high) {
    if (root == null) return 0;

    int sum = 0;

    // Current node in range
    if (root.val >= low && root.val <= high) {
        sum += root.val;
    }

    // Only search left if current > low
    if (root.val > low) {
        sum += rangeSumBST(root.left, low, high);
    }

    // Only search right if current < high
    if (root.val < high) {
        sum += rangeSumBST(root.right, low, high);
    }

    return sum;
}
```

### Visual Example

```
BST:       10
         /    \
        5     15
       / \      \
      3   7     18

Range [7, 15]:
- At 10: in range, sum += 10, check both
- At 5: < 7, skip left, check right
- At 7: in range, sum += 7
- At 15: in range, sum += 15, check right
- At 18: > 15, skip

Result: 7 + 10 + 15 = 32
```

---

## Pattern 5: Minimum Absolute Difference in BST

**Use Case**: Find minimum difference between any two nodes

**Algorithm**:
1. Use inorder traversal (gives sorted order)
2. Track previous node
3. Calculate difference with previous
4. Track minimum

**Complexity**: O(n) time, O(h) space

### Template

```java
class Solution {
    private Integer prev = null;
    private int minDiff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        // Process current node
        if (prev != null) {
            minDiff = Math.min(minDiff, node.val - prev);
        }
        prev = node.val;

        inorder(node.right);
    }
}
```

---

## Pattern 6: LCA in BST

**Use Case**: Find lowest common ancestor leveraging BST property

**Algorithm**:
1. If both < current, go left
2. If both > current, go right
3. Otherwise, current is LCA

**Complexity**: O(h) time, O(1) space (iterative)

### Template

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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

---

## Common Mistakes

### ❌ Mistake 1: Validating BST Without Range

```java
// WRONG - only checks immediate children
public boolean isValidBST(TreeNode root) {
    if (root == null) return true;
    if (root.left != null && root.left.val >= root.val) return false;
    if (root.right != null && root.right.val <= root.val) return false;
    return isValidBST(root.left) && isValidBST(root.right);
}
// Fails for:    5
//             /   \
//            3     7
//             \   /
//              6 4  ← 6 > 5, invalid but not caught!

// CORRECT - track min/max range
public boolean isValidBST(TreeNode root) {
    return validate(root, null, null);
}
private boolean validate(TreeNode node, Integer min, Integer max) {
    if (node == null) return true;
    if ((min != null && node.val <= min) ||
        (max != null && node.val >= max)) return false;
    return validate(node.left, min, node.val) &&
           validate(node.right, node.val, max);
}
```

### ❌ Mistake 2: Using <= or >= Instead of < and >

```java
// WRONG - BST doesn't allow duplicates in standard definition
if (node.val <= min || node.val >= max) return false;

// CORRECT - use strict inequalities
if ((min != null && node.val <= min) ||
    (max != null && node.val >= max)) return false;
```

### ❌ Mistake 3: Not Handling Null in Range Check

```java
// WRONG - NullPointerException when min or max is null
if (node.val <= min || node.val >= max) return false;

// CORRECT - check for null first
if ((min != null && node.val <= min) ||
    (max != null && node.val >= max)) return false;
```

---

## Problems

- [x] [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Range Sum of BST](https://leetcode.com/problems/range-sum-of-bst/) - Easy
- [x] [Minimum Absolute Difference in BST](https://leetcode.com/problems/minimum-absolute-difference-in-bst/) - Easy
- [x] [Insert into a Binary Search Tree](https://leetcode.com/problems/insert-into-a-binary-search-tree/) - Medium
- [x] [Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) - Medium


## Key Takeaways

1. **BST property**: Left < Root < Right (strictly)
2. **Inorder traversal** gives sorted sequence
3. **Range validation** for checking BST validity
4. **Leverage ordering** to skip subtrees in range queries
5. **Iterative often better** for search/insert (O(1) space)
6. **Common pattern**: Compare with current, recurse on one side
7. **Complexity**: O(log n) average, O(n) worst case
8. **Use Integer** (not int) for min/max to handle nulls

---

> **[← Back to Trees Overview](Notes.md)** | **[Next Pattern →](../../docs/questions-list.md)**
