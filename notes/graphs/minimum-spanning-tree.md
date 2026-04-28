# Minimum Spanning Tree (MST)

> **[← Back to Graphs Overview](Notes.md)**

---

## When to Use

✅ **Connect all nodes with minimum total edge weight**

### Keywords
- "minimum cost to connect"
- "connect all points"
- "minimum spanning tree"
- "connect with minimum weight"
- "network design"

### Examples
- Connect cities with minimum total cable cost
- Design network with minimum wire length
- Connect all points on a plane with minimum distance
- Build road network connecting all towns (minimum cost)

---

## Core Concept

A **Minimum Spanning Tree (MST)** is a subset of edges that:
1. Connects all vertices (spanning)
2. Forms a tree (no cycles, exactly V-1 edges)
3. Has minimum total edge weight

**Key Properties**:
- For a graph with V vertices, MST has exactly **V-1 edges**
- MST is not unique (multiple MSTs can exist with same weight)
- Only exists for connected graphs

**Algorithms**:
- **Kruskal's Algorithm**: Sort edges + Union Find
- **Prim's Algorithm**: Greedy + Priority Queue (similar to Dijkstra)

---

## Pattern: Kruskal's Algorithm ⭐ **IMPORTANT** ⭐

**Use Case**: Build MST by selecting edges in order of increasing weight

**Algorithm**:
1. Sort all edges by weight (ascending)
2. Initialize Union Find with V vertices
3. For each edge (u, v, weight):
   - If u and v are not connected (different components):
     - Add edge to MST
     - Union(u, v)
   - If already connected, skip (would create cycle)
4. Stop when V-1 edges added

**Complexity**: O(E log E) time (dominated by sorting), O(V) space

**Key Insight**: Greedy approach - always pick the smallest available edge that doesn't create a cycle.

**Reference**: [Kruskal's Algorithm - Video](https://www.youtube.com/watch?v=OxfTT8slSLs)

### Template

```java
public int kruskalMST(int n, int[][] edges) {
    // Sort edges by weight
    Arrays.sort(edges, (a, b) -> a[2] - b[2]);

    UnionFind uf = new UnionFind(n);
    int totalCost = 0;
    int edgesUsed = 0;

    for (int[] edge : edges) {
        int u = edge[0], v = edge[1], weight = edge[2];

        // Add edge if it connects different components (no cycle)
        if (uf.union(u, v)) {
            totalCost += weight;
            edgesUsed++;

            // MST complete when V-1 edges added
            if (edgesUsed == n - 1) {
                break;
            }
        }
    }

    // Check if MST is possible (graph connected)
    return edgesUsed == n - 1 ? totalCost : -1;
}

class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;  // Already connected (cycle)
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }
}
```

### Visual Example

```
Graph (6 nodes, 9 edges):
    1
  0---1    Edge weights:
  |\ /|    0-1: 1
  | X |    0-2: 4
  |/ \|    0-3: 3
  2---3    1-2: 2
           1-3: 5
Edges sorted by weight:    2-3: 6
(0,1,1), (1,2,2), (0,3,3), (0,2,4), (1,3,5), (2,3,6)

Step 1: Add (0,1,1) - Cost: 1, Edges: 1
  Components: {0,1} {2} {3}

Step 2: Add (1,2,2) - Cost: 3, Edges: 2
  Components: {0,1,2} {3}

Step 3: Add (0,3,3) - Cost: 6, Edges: 3
  Components: {0,1,2,3}

Step 4: Skip (0,2,4) - Already connected (cycle)

MST Complete! Edges: 3 (= 4-1 ✓), Total Cost: 6

MST:
    1
  0---1
  |\ /
  | 2
  |
  3
```

---

## Pattern: Prim's Algorithm

**Use Case**: Build MST by growing tree from starting vertex

**Algorithm**:
1. Start from any vertex, add to MST
2. Use min-heap to track edges from MST to non-MST vertices
3. Repeatedly:
   - Pick minimum weight edge to new vertex
   - Add vertex and edge to MST
   - Add new edges to heap
4. Stop when all vertices in MST

**Complexity**: O((V + E) log V) time with heap, O(V) space

**Reference**: [Prim's Algorithm - Video](https://www.youtube.com/watch?v=20QfaLQPLqQ)

### Template

```java
public int primMST(int n, int[][] edges) {
    // Build adjacency list
    List<int[]>[] graph = new ArrayList[n];
    for (int i = 0; i < n; i++) {
        graph[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
        int u = edge[0], v = edge[1], weight = edge[2];
        graph[u].add(new int[]{v, weight});
        graph[v].add(new int[]{u, weight});  // Undirected
    }

    // Prim's algorithm
    boolean[] inMST = new boolean[n];
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    int totalCost = 0;
    int edgesUsed = 0;

    // Start from node 0
    pq.offer(new int[]{0, 0});  // {node, weight}

    while (!pq.isEmpty() && edgesUsed < n) {
        int[] curr = pq.poll();
        int node = curr[0], weight = curr[1];

        if (inMST[node]) continue;  // Skip if already in MST

        // Add to MST
        inMST[node] = true;
        totalCost += weight;
        edgesUsed++;

        // Add edges to neighbors not in MST
        for (int[] neighbor : graph[node]) {
            int nextNode = neighbor[0], edgeWeight = neighbor[1];
            if (!inMST[nextNode]) {
                pq.offer(new int[]{nextNode, edgeWeight});
            }
        }
    }

    return edgesUsed == n ? totalCost : -1;
}
```

---

## Kruskal vs Prim

| Aspect | Kruskal's | Prim's |
|--------|-----------|--------|
| **Approach** | Edge-based (sort edges) | Vertex-based (grow from start) |
| **Data Structure** | Union Find | Priority Queue |
| **Time** | O(E log E) | O((V + E) log V) |
| **Best for** | Sparse graphs | Dense graphs |
| **Implementation** | Simpler (sort + union find) | Similar to Dijkstra |
| **Edge selection** | Global (smallest edge) | Local (smallest from MST) |

---

## Common Mistakes

### ❌ Not Sorting Edges in Kruskal's

```java
// WRONG - may not give minimum cost
for (int[] edge : edges) {
    if (uf.union(edge[0], edge[1])) {
        totalCost += edge[2];
    }
}

// CORRECT - sort first
Arrays.sort(edges, (a, b) -> a[2] - b[2]);
for (int[] edge : edges) {
    if (uf.union(edge[0], edge[1])) {
        totalCost += edge[2];
    }
}
```

### ❌ Not Checking Edge Count

```java
// WRONG - may add more than V-1 edges
for (int[] edge : edges) {
    if (uf.union(edge[0], edge[1])) {
        totalCost += edge[2];
    }
}
return totalCost;

// CORRECT - stop at V-1 edges
int edgesUsed = 0;
for (int[] edge : edges) {
    if (uf.union(edge[0], edge[1])) {
        totalCost += edge[2];
        edgesUsed++;
        if (edgesUsed == n - 1) break;  // MST complete
    }
}
```

### ❌ Checking Connectivity Without Union Find

```java
// WRONG - O(V²) to check connectivity
boolean[][] connected = new boolean[n][n];
for (int[] edge : edges) {
    if (!connected[edge[0]][edge[1]]) {
        // Check if adds cycle...
    }
}

// CORRECT - O(α(n)) with Union Find
if (uf.union(edge[0], edge[1])) {
    // Edge added (no cycle)
}
```

### ❌ Forgetting Undirected Edges in Prim's

```java
// WRONG - treats as directed
for (int[] edge : edges) {
    graph[edge[0]].add(new int[]{edge[1], edge[2]});
}

// CORRECT - add both directions
for (int[] edge : edges) {
    graph[edge[0]].add(new int[]{edge[1], edge[2]});
    graph[edge[1]].add(new int[]{edge[0], edge[2]});
}
```

---

## Problems

- [x] [Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/) - Medium ⭐ **IMPORTANT** ⭐

### Min Cost to Connect All Points ⭐ **IMPORTANT** ⭐

**Problem**: [Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/) - Medium

**Why Important**: Classic MST application with complete graph, tests understanding of Kruskal's algorithm

**Approach**:
1. Treat points as vertices in complete graph
2. Generate all possible edges (V² edges)
3. Distance = Manhattan distance: |x₁-x₂| + |y₁-y₂|
4. Apply Kruskal's algorithm: sort edges + union find
5. Return total cost when V-1 edges added

**Complexity**: O(V² log V) time, O(V²) space

**Solution**:

```java
public int minCostConnectPoints(int[][] points) {
    int n = points.length;
    List<int[]> edges = new ArrayList<>();

    // Generate all edges with Manhattan distance
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int distance = Math.abs(points[i][0] - points[j][0]) +
                          Math.abs(points[i][1] - points[j][1]);
            edges.add(new int[]{i, j, distance});
        }
    }

    // Sort edges by distance (Kruskal's algorithm)
    Collections.sort(edges, (a, b) -> a[2] - b[2]);

    // Union Find
    UnionFind uf = new UnionFind(n);
    int totalCost = 0;
    int edgesUsed = 0;

    for (int[] edge : edges) {
        int u = edge[0], v = edge[1], cost = edge[2];

        if (uf.union(u, v)) {
            totalCost += cost;
            edgesUsed++;

            // MST complete when n-1 edges added
            if (edgesUsed == n - 1) {
                break;
            }
        }
    }

    return totalCost;
}

class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }
}
```

**Key Points**:
- **Complete graph**: Every point connected to every other point (V² edges)
- **Manhattan distance**: Sum of absolute differences in coordinates
- **Early termination**: Stop when n-1 edges added (MST property)
- **Union Find**: Critical for O(α(n)) cycle detection
- Alternative: Prim's algorithm also works, similar time complexity

---

## Key Takeaways

1. **MST properties**: V-1 edges, connects all vertices, minimum total weight
2. **Kruskal's algorithm**: Sort edges + Union Find, O(E log E)
3. **Prim's algorithm**: Grow from start + Priority Queue, O((V + E) log V)
4. **Choose algorithm**: Kruskal for sparse graphs, Prim for dense graphs
5. **Union Find essential**: Efficient cycle detection in Kruskal's
6. **Complete graphs**: V² edges, still use same algorithms
7. **Applications**: Network design, clustering, circuit design

---

> **[← Shortest Path](shortest-path.md)** | **[Back to Graphs Overview](Notes.md)**
