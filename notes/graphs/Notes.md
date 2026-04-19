# Graphs

> **[← Back to Overview](../README.md)**

---

## Overview

Graphs consist of **vertices (nodes)** connected by **edges**. Different algorithms solve specific graph problems efficiently.

This topic is split into focused sections:

1. **[Topological Sort](topological-sort.md)** - Order nodes respecting dependencies (DAG)
2. **[Union Find](union-find.md)** - Track connected components with near-constant operations
3. **[Shortest Path Algorithms](shortest-path.md)** - Find minimum distance/cost paths (Dijkstra, Bellman-Ford, Floyd-Warshall)
4. **[Minimum Spanning Tree](minimum-spanning-tree.md)** - Connect all nodes with minimum total weight (Kruskal's, Prim's)

---

## Quick Reference

### When to Use Each Algorithm

| Problem Type | Algorithm | Time | Section |
|-------------|-----------|------|---------|
| **Order tasks with dependencies** | Topological Sort | O(V + E) | [→](topological-sort.md) |
| **Detect cycle (undirected)** | Union Find | O(α(n)) | [→](union-find.md) |
| **Track connected components** | Union Find | O(α(n)) | [→](union-find.md) |
| **Shortest path (non-negative)** | Dijkstra | O((V+E) log V) | [→](shortest-path.md) |
| **Shortest path (negative weights)** | Bellman-Ford | O(V × E) | [→](shortest-path.md) |
| **All-pairs shortest paths** | Floyd-Warshall | O(V³) | [→](shortest-path.md) |
| **Connect all nodes (min cost)** | Kruskal's MST | O(E log E) | [→](minimum-spanning-tree.md) |

### Keywords to Algorithm Mapping

**"prerequisites", "dependencies", "order", "course schedule"**
→ [Topological Sort](topological-sort.md)

**"connected components", "disjoint sets", "cycle detection", "merge groups"**
→ [Union Find](union-find.md)

**"shortest path", "minimum cost", "minimum distance", "network delay"**
→ [Shortest Path Algorithms](shortest-path.md)

**"minimum cost to connect", "connect all points", "spanning tree"**
→ [Minimum Spanning Tree](minimum-spanning-tree.md)

---

## Core Concepts

### Graph Properties

**Directed vs Undirected**
- Directed: One-way edges (A → B)
- Undirected: Two-way edges (A ↔ B)

**Weighted vs Unweighted**
- Weighted: Edges have costs/distances
- Unweighted: All edges equal weight

**Cyclic vs Acyclic (DAG)**
- Cyclic: Contains cycles
- DAG (Directed Acyclic Graph): No cycles (required for topological sort)

**Connected vs Disconnected**
- Connected: Path exists between all vertex pairs
- Disconnected: Some vertices unreachable

### Graph Representation

**Adjacency List** (Most common)
```java
List<Integer>[] graph = new ArrayList[n];
for (int i = 0; i < n; i++) {
    graph[i] = new ArrayList<>();
}
// Add edge u → v
graph[u].add(v);
```

**Adjacency Matrix** (Dense graphs)
```java
boolean[][] graph = new boolean[n][n];
// Add edge u → v
graph[u][v] = true;
```

**Edge List** (For sorting edges)
```java
int[][] edges = new int[m][3];  // {u, v, weight}
```

---

## Algorithm Summaries

### 1. Topological Sort
**[→ Detailed Notes](topological-sort.md)**

Order nodes respecting dependencies (prerequisite → course).

**Kahn's Algorithm** (BFS-based):
- Calculate in-degrees
- Process nodes with in-degree 0
- Decrease neighbors' in-degrees
- O(V + E) time

**Use Cases**: Course scheduling, build systems, task ordering

---

### 2. Union Find (Disjoint Set Union)
**[→ Detailed Notes](union-find.md)**

Track and merge disjoint sets with near-constant operations.

**Operations**:
- Find(x): Get root with path compression
- Union(x, y): Merge sets with union by rank
- O(α(n)) ≈ O(1) per operation

**Use Cases**: Connected components, cycle detection, Kruskal's MST

---

### 3. Shortest Path Algorithms
**[→ Detailed Notes](shortest-path.md)**

Find minimum distance/cost paths between nodes.

**Dijkstra's Algorithm**:
- Single-source, non-negative weights
- Priority queue (greedy)
- O((V + E) log V) time

**Bellman-Ford Algorithm**:
- Single-source, handles negative weights
- Relax edges V-1 times
- O(V × E) time

**Floyd-Warshall Algorithm**:
- All-pairs shortest paths
- Dynamic programming
- O(V³) time

**Use Cases**: Navigation, network routing, flight booking

---

### 4. Minimum Spanning Tree (MST)
**[→ Detailed Notes](minimum-spanning-tree.md)**

Connect all nodes with minimum total edge weight.

**Kruskal's Algorithm**:
- Sort edges + Union Find
- O(E log E) time
- Best for sparse graphs

**Prim's Algorithm**:
- Grow tree + Priority Queue
- O((V + E) log V) time
- Best for dense graphs

**Use Cases**: Network design, clustering, circuit design

---

## Decision Tree

```
Need to solve a graph problem?
│
├─ Order tasks with dependencies?
│  └─ Topological Sort (Kahn's or DFS)
│
├─ Track/merge groups dynamically?
│  └─ Union Find
│
├─ Find shortest/cheapest path?
│  ├─ Single source, non-negative weights → Dijkstra
│  ├─ Single source, negative weights → Bellman-Ford
│  └─ All pairs → Floyd-Warshall
│
└─ Connect all nodes with min cost?
   ├─ Sparse graph → Kruskal's MST
   └─ Dense graph → Prim's MST
```

---

## Key Takeaways

1. **Topological Sort**: O(V + E), DAG only, Kahn's algorithm preferred
2. **Union Find**: O(α(n)) ≈ O(1), path compression + union by rank essential
3. **Dijkstra**: O((V+E) log V), non-negative weights only, greedy
4. **Bellman-Ford**: O(V × E), handles negative weights, detects cycles
5. **Floyd-Warshall**: O(V³), all-pairs, dynamic programming
6. **Kruskal's MST**: O(E log E), sort edges + Union Find
7. **Choose wisely**: Match algorithm to graph properties and requirements

---

## Next Steps

Choose a section to study in detail:

- **[Topological Sort →](topological-sort.md)** - Dependency ordering with Kahn's algorithm
- **[Union Find →](union-find.md)** - Disjoint sets with path compression
- **[Shortest Path →](shortest-path.md)** - Dijkstra, Bellman-Ford, Floyd-Warshall
- **[Minimum Spanning Tree →](minimum-spanning-tree.md)** - Kruskal's and Prim's algorithms

---

> **[← Back to Overview](../README.md)**
