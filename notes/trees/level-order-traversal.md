# Level Order Traversal (BFS in Binary Tree)

> **[← Back to Trees Overview](Notes.md)**

---

## When to Use

✅ **Use level order traversal when you need to process nodes level by level, find shortest paths, or track level-specific information**

### Keywords
- "level by level"
- "level order"
- "width of tree"
- "zigzag traversal"
- "nodes at distance k"
- "even/odd levels"

### Examples
- Print tree level by level
- Find nodes at a specific level
- Check if tree satisfies level-wise properties
- Serialize/deserialize tree
- Find width of tree at each level

---

## Core Concept

**Level Order Traversal** visits all nodes at depth `d` before visiting nodes at depth `d+1`. Uses a **queue** (FIFO) to maintain nodes at current level.

**Key Insight**: Queue ensures we process parent nodes before their children, maintaining level order.

**Complexity**: O(n) time, O(w) space where w = maximum width of tree

---

## Pattern 1: Basic Level Order Traversal

**Use Case**: Process nodes level by level, grouping nodes at same depth

**Algorithm**:
1. Initialize queue with root
2. For each level:
   - Get current level size
   - Process all nodes in current level
   - Add their children to queue
3. Repeat until queue is empty

**Complexity**: O(n) time, O(w) space

### Template

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();

        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            currentLevel.add(node.val);

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        result.add(currentLevel);
    }

    return result;
}
```

### Visual Example

```
Tree:      1
         /   \
        2     3
       / \   /
      4   5 6

Level 0: [1]
Level 1: [2, 3]
Level 2: [4, 5, 6]

Queue progression:
[1] → [2,3] → [3,4,5] → [4,5,6] → [5,6] → [6] → []
```

---

## Pattern 2: Find Nodes at Distance K

**Use Case**: Find all nodes at a specific distance from a target node

**Algorithm**:
1. Build parent pointers using BFS/DFS
2. BFS from target node treating tree as undirected graph
3. Track visited nodes to avoid cycles
4. Collect nodes at distance k

**Complexity**: O(n) time, O(n) space

---

## Common Mistakes

### ❌ Mistake 1: Not Capturing Level Size

```java
// WRONG - processes all nodes in queue, not level by level
while (!queue.isEmpty()) {
    TreeNode node = queue.poll();
    // This processes nodes as they arrive, mixing levels
}

// CORRECT - process one level at a time
while (!queue.isEmpty()) {
    int levelSize = queue.size();  // Capture current level size
    for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        // Process level by level
    }
}
```

### ❌ Mistake 2: Modifying Queue While Iterating

```java
// WRONG - queue size changes during iteration
for (int i = 0; i < queue.size(); i++) {
    TreeNode node = queue.poll();
    queue.offer(node.left);  // Changes queue.size()!
}

// CORRECT - capture size first
int levelSize = queue.size();
for (int i = 0; i < levelSize; i++) {
    TreeNode node = queue.poll();
    queue.offer(node.left);
}
```

---

## Problems

- [x] [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/) - Medium
- [x] [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) - Medium
- [x] [Even Odd Tree](https://leetcode.com/problems/even-odd-tree/) - Medium
- [x] [Reverse Odd Levels of Binary Tree](https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/) - Medium ⭐ IMPORTANT ⭐
- [x] [Deepest Leaves Sum](https://leetcode.com/problems/deepest-leaves-sum/) - Medium
- [x] [Add One Row to Tree](https://leetcode.com/problems/add-one-row-to-tree/) - Medium
- [x] [Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/) - Medium
- [ ] [All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/) - Medium

### Reverse Odd Levels of Binary Tree ⭐ **IMPORTANT** ⭐

**Problem**: [Reverse Odd Levels of Binary Tree](https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/) - Medium

**Why Important**: Combines level-order traversal with value manipulation. Tests understanding of level tracking, in-place modification, and working with perfect binary trees. Common pattern in tree manipulation problems.

**Approach**:
1. Use BFS to traverse level by level
2. Track current level number (0-indexed)
3. For odd levels, collect all node values in a list
4. Reverse the list
5. Reassign reversed values back to nodes at that level

**Complexity**: O(n) time, O(w) space where w = maximum width

**Solution**:
```java
public TreeNode reverseOddLevels(TreeNode root) {
    if (root == null) return null;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int level = 0;

    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<TreeNode> currentLevelNodes = new ArrayList<>();

        // Collect all nodes at current level
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            currentLevelNodes.add(node);

            // Add children to queue for next level
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        // If odd level, reverse the values
        if (level % 2 == 1) {
            int left = 0;
            int right = currentLevelNodes.size() - 1;

            while (left < right) {
                // Swap values (not nodes!)
                TreeNode leftNode = currentLevelNodes.get(left);
                TreeNode rightNode = currentLevelNodes.get(right);

                int temp = leftNode.val;
                leftNode.val = rightNode.val;
                rightNode.val = temp;

                left++;
                right--;
            }
        }

        level++;
    }

    return root;
}
```

**Key Points**:
- **Store nodes, not values**: Need node references to modify values in place
- **Level tracking**: Use counter (0-indexed), odd levels are 1, 3, 5...
- **Two-pointer swap**: Efficient O(n/2) reversal without extra space for values
- **Perfect binary tree**: Problem guarantees perfect tree, so all levels except last are complete
- Common mistake: Trying to swap nodes instead of values

### Alternative: DFS with Mirror Traversal ⭐

This approach leverages the **perfect binary tree** property to process mirror nodes simultaneously.

**Key Insight**: In a perfect binary tree, nodes at the same level form mirror pairs. We can process two nodes at once, swapping their values when at odd levels.

**Algorithm**:
1. Start with root's left and right children
2. At each level, process two mirror nodes together
3. If at odd level (0-indexed, so even in code), swap values
4. Recurse with mirrored children:
   - node1.left with node2.right
   - node1.right with node2.left

**Complexity**: O(n) time, O(h) space for recursion stack

**Solution**:
```java
class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        // Start DFS from root's children (level 0)
        reverseOddLevelsUtil(root.left, root.right, 0);
        return root;
    }

    private void reverseOddLevelsUtil(TreeNode node1, TreeNode node2, int level) {
        // Base case: reached null
        if (node1 == null || node2 == null) {
            return;
        }

        // Swap values at odd levels
        // Note: level 0 here means level 1 in tree (root's children)
        if (level % 2 == 0) {
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }

        // Process mirror children
        reverseOddLevelsUtil(node1.left, node2.right, level + 1);
        reverseOddLevelsUtil(node1.right, node2.left, level + 1);
    }
}
```

**Why This Works**:
```
Perfect Binary Tree:
        1
      /   \
     2     3      ← Mirror pair at level 1
    / \   / \
   4   5 6   7    ← Mirror pairs: (4,7) and (5,6) at level 2

DFS traversal processes:
- (2, 3) at level 0 → swap (odd level 1)
- (4, 7) at level 1 → don't swap (even level 2)
- (5, 6) at level 1 → don't swap (even level 2)
```

**Advantages**:
- **Space efficient**: O(h) vs O(w) for BFS approach
- **Elegant**: Leverages tree structure
- **Simultaneous processing**: Processes mirror pairs together
- **No extra storage**: No need to store level nodes

**Trade-offs**:
- BFS approach: More intuitive, works with level visualization
- DFS approach: More efficient, leverages perfect tree property

---

## Key Takeaways

1. **Capture level size** before processing to avoid mixing levels
2. **Queue + BFS** for level order traversal
3. **O(n) time, O(w) space** where w = maximum width
4. **Level tracking** enables validation of level-specific properties
5. **Use prev variable** for level-wise comparisons when needed

---

> **[← Back to Trees Overview](Notes.md)** | **[Tree Construction →](tree-construction.md)**
