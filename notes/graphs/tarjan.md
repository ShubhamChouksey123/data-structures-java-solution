# Tarjan's Algorithm

> **[← Back to Graphs Overview](Notes.md)**

---

## When to Use

✅ **Find bridges (critical connections) or articulation points in an undirected graph**

### Keywords
- "critical connections", "bridges"
- "articulation points", "cut vertices"
- "removing an edge disconnects the graph"
- "single point of failure"

### Examples
- Critical connections in a network
- Finding vulnerable links in infrastructure
- Articulation points that split a network if removed

---

## Core Concept

Tarjan's algorithm uses DFS with two key values per node:
- **`disc[u]`** — discovery time when node `u` was first visited
- **`low[u]`** — lowest discovery time reachable from the subtree rooted at `u`

**Key Insight**: An edge `(u, v)` is a **bridge** if `low[v] > disc[u]` — meaning there's no back-edge from v's subtree back to u or any ancestor of u. Removing this edge would disconnect the graph.

**Complexity**: O(V + E) time, O(V) space

---

## Pattern: Bridge Finding (Critical Connections)

**Use Case**: Find all edges whose removal disconnects the graph

**Algorithm**:
1. DFS through the graph, tracking discovery time for each node
2. Compute `low[v]` = min of:
   - `disc[v]` (own discovery time)
   - `low[child]` for all DFS children
   - `disc[neighbor]` for all back-edges (skip the parent edge)
3. After visiting a neighbor `v` from `u`: if `low[v] > disc[u]`, edge `(u, v)` is a bridge

**Complexity**: O(V + E) time, O(V) space

### Template

```java
public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    // Build adjacency list
    List<Integer>[] graph = new ArrayList[n];
    for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
    for (List<Integer> conn : connections) {
        int u = conn.get(0), v = conn.get(1);
        graph[u].add(v);
        graph[v].add(u);
    }

    int[] disc = new int[n];
    int[] low = new int[n];
    boolean[] visited = new boolean[n];
    List<List<Integer>> bridges = new ArrayList<>();
    int[] timer = {0};

    // Run DFS from each unvisited node (handles disconnected graphs)
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            dfs(graph, i, -1, disc, low, visited, bridges, timer);
        }
    }

    return bridges;
}

private void dfs(List<Integer>[] graph, int u, int parent,
                 int[] disc, int[] low, boolean[] visited,
                 List<List<Integer>> bridges, int[] timer) {
    visited[u] = true;
    disc[u] = low[u] = timer[0]++;

    for (int v : graph.get(u)) {
        if (!visited[v]) {
            dfs(graph, v, u, disc, low, visited, bridges, timer);
            // After exploring v's subtree, update low[u]
            low[u] = Math.min(low[u], low[v]);
            // Bridge condition: no back-edge from v's subtree reaches u or above
            if (low[v] > disc[u]) {
                bridges.add(Arrays.asList(u, v));
            }
        } else if (v != parent) {
            // Back-edge: update low[u] with discovery time of ancestor
            low[u] = Math.min(low[u], disc[v]);
        }
    }
}
```

### Visual Example

```
Graph: 0 - 1 - 2 - 3
           |       |
           +---4---+

disc: [0, 1, 2, 3, 4] (DFS order)
low:  [0, 0, 0, 3, 0]

Edge (1,2): low[2]=0 <= disc[1]=1 → NOT a bridge (back-edge exists via 4)
Edge (2,3): low[3]=3 > disc[2]=2 → BRIDGE ✓
```

---

## Problems

- [x] [Critical Connections in a Network](https://leetcode.com/problems/critical-connections-in-a-network/description/) - Hard

### Critical Connections in a Network

**Problem**: [Critical Connections in a Network](https://leetcode.com/problems/critical-connections-in-a-network/description/) - Hard

**Approach**:
1. Build adjacency list from connections
2. Run Tarjan's DFS tracking `disc` and `low` arrays
3. For each tree edge `(u, v)`: if `low[v] > disc[u]`, add `(u, v)` to result

**Complexity**: O(V + E) time, O(V + E) space

**Key Points**:
- Skip parent edge (not a back-edge); use `v != parent` check
- `low[v] > disc[u]` → no alternative path from v's subtree back to u's level
- Works for connected graphs; loop over all nodes for disconnected graphs

---

## Key Takeaways

1. **`disc[u]`**: When node was first visited (DFS timestamp)
2. **`low[u]`**: Earliest ancestor reachable from u's subtree (via back-edges)
3. **Bridge condition**: `low[v] > disc[u]` — v's subtree has no back-edge to u or above
4. **Skip parent**: `v != parent` prevents treating the tree edge as a back-edge
5. **O(V + E)**: Single DFS pass — optimal for bridge finding

---

> **[← Back to Graphs Overview](Notes.md)**
