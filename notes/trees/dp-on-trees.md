# DP on Trees

> **[← Back to Trees Overview](Notes.md)**

---

## When to Use

✅ **Use DP on trees when an answer for a node depends on aggregated results from its subtree (or the whole tree, via rerooting)**

### Keywords
- "sum of distances"
- "every node as root"
- "subtree sum / count"
- "rerooting"
- "maximum path sum through a node"

### Examples
- Sum of distances from every node to all others
- Subtree size / sum queries
- Diameter of a tree
- Maximum path sum passing through each node

---

## Core Concept

Trees have no cycles, so a **postorder DFS** naturally aggregates child results into a parent (bottom-up DP). When an answer is needed for **every** node (not just a fixed root), re-running the DFS per node is O(n²) — instead, **reroot** using a second DFS that reuses the parent's already-computed answer.

**Key Insight**: Compute the answer for one root in O(n), then derive every other node's answer from its parent's answer in O(1) by accounting for what changes when the root shifts by one edge.

**Complexity**: O(n) time, O(n) space (two DFS passes)

---

## Pattern 1: Rerooting — Sum of Distances in Tree ⭐ **IMPORTANT** ⭐

**Use Case**: Need an aggregate value (distance sum, subtree count, etc.) computed as if every node were the root, without O(n²) brute force.

**Algorithm**:
1. Build an adjacency list from the edges (tree = undirected graph, n-1 edges).
2. First DFS (postorder, rooted at 0): fill `count[node]` = size of `node`'s subtree, and accumulate `answer[0]` = sum of distances from node 0 to every other node via `answer[node] += answer[child] + count[child]`.
3. Second DFS (preorder, rerooting): moving the root from `parent` to `child` brings it 1 step closer to `child`'s subtree (`count[child]` nodes) and 1 step farther from the rest (`n - count[child]` nodes): `answer[child] = answer[parent] - count[child] + (n - count[child])`.
4. Return `answer`.

**Complexity**: O(n) time, O(n) space

### Template

```java
public int[] sumOfDistancesInTree(int n, int[][] edges) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
    for (int[] edge : edges) {
        graph.get(edge[0]).add(edge[1]);
        graph.get(edge[1]).add(edge[0]);
    }

    int[] count = new int[n];   // subtree sizes, rooted at 0
    int[] answer = new int[n];  // answer[0] filled first, rest via rerooting

    postOrder(0, -1, graph, count, answer);
    preOrder(0, -1, graph, count, answer, n);

    return answer;
}

private void postOrder(int node, int parent, List<List<Integer>> graph, int[] count, int[] answer) {
    count[node] = 1;
    for (int child : graph.get(node)) {
        if (child == parent) continue;
        postOrder(child, node, graph, count, answer);
        count[node] += count[child];
        answer[node] += answer[child] + count[child];
    }
}

private void preOrder(int node, int parent, List<List<Integer>> graph, int[] count, int[] answer, int n) {
    for (int child : graph.get(node)) {
        if (child == parent) continue;
        // moving root node -> child: child's subtree gets 1 closer, rest of tree gets 1 farther
        answer[child] = answer[node] - count[child] + (n - count[child]);
        preOrder(child, node, graph, count, answer, n);
    }
}
```

### Visual Example

```
Tree:      0
         / | \
        1  2  3
           |
           4

count (rooted at 0): count[4]=1, count[2]=2, count[1]=1, count[3]=1, count[0]=5
answer[0] = sum of distances from 0 = 1(to 1) + 1(to 3) + 1(to 2) + 2(to 4) = 5

Reroot 0 -> 1: answer[1] = answer[0] - count[1] + (n - count[1]) = 5 - 1 + 4 = 8
Reroot 0 -> 2: answer[2] = answer[0] - count[2] + (n - count[2]) = 5 - 2 + 3 = 6
Reroot 2 -> 4: answer[4] = answer[2] - count[4] + (n - count[4]) = 6 - 1 + 4 = 9
```

---

## Common Mistakes

### ❌ Mistake 1: Recomputing From Scratch Per Node

```java
// WRONG - O(n^2): runs a fresh BFS/DFS from every node
for (int i = 0; i < n; i++) answer[i] = bfsSumOfDistances(i);

// CORRECT - O(n): one postorder pass + one rerooting pass
postOrder(0, -1, graph, count, answer);
preOrder(0, -1, graph, count, answer, n);
```

### ❌ Mistake 2: Forgetting to Exclude the Parent When Walking Neighbors

```java
// WRONG - revisits the parent and infinite-loops (tree has no "visited" array by default)
for (int neighbor : graph.get(node)) {
    dfs(neighbor, node, ...);   // missing: if (neighbor == parent) continue;
}
```

---

## Problems

- [x] [Sum of Distances in Tree](https://leetcode.com/problems/sum-of-distances-in-tree/) - Hard ⭐ **IMPORTANT** ⭐

---

## Key Takeaways

1. **Postorder first** — aggregate subtree info (counts, sums) bottom-up for one fixed root.
2. **Reroot second** — derive every other node's answer from its parent's answer in O(1), using the aggregated subtree info.
3. **Always exclude the parent** when iterating neighbors in a tree DFS (no cycles, so no separate `visited` array needed).
4. **O(n) total**, not O(n²) — the whole point of rerooting.

---

> **[← Back to Trees Overview](Notes.md)**
