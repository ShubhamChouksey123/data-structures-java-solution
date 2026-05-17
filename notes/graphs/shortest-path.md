# Shortest Path Algorithms

> **[← Back to Graphs Overview](Notes.md)**

---

## When to Use

✅ **Find minimum distance/cost path between nodes in weighted graphs**

### Keywords
- "shortest path"
- "minimum cost", "minimum distance"
- "cheapest route"
- "network delay"
- "flight connections"

### Examples
- GPS navigation (shortest route)
- Network routing (minimum delay)
- Flight booking (cheapest flights)
- Game pathfinding

---

## Core Concept

Different algorithms for different graph properties:

| Algorithm | Use Case | Weights | Complexity | Output |
|-----------|----------|---------|------------|--------|
| **Dijkstra** | Single-source, non-negative weights | ≥ 0 | O((V+E) log V) | Single source |
| **Bellman-Ford** | Single-source, negative weights OK | Any | O(V × E) | Single source + cycle detection |
| **Floyd-Warshall** | All-pairs shortest paths | Any | O(V³) | All pairs |

**Key Insight**: Choose based on graph properties and what you need to compute.

---

## Pattern 1: Dijkstra's Algorithm ⭐ **IMPORTANT** ⭐

**Use Case**: Single-source shortest path with **non-negative** weights

**Algorithm**:
1. Initialize distances: 0 for source, ∞ for others
2. Use min-heap priority queue (distance, node)
3. Extract minimum distance node
4. Update neighbors if shorter path found
5. Repeat until all reachable nodes processed

**Complexity**: O((V + E) log V) time with heap, O(V) space

**Key Property**: Greedy algorithm - once a node is processed, its distance is final.

**📹 Video Tutorial**: [Dijkstra's Algorithm Explained](https://youtu.be/0W8WoRaw5Es?si=anDwlfokkJ2L_8gq)

### Template

```java
public int dijkstra(int n, int[][] edges, int source, int target) {
    // Build adjacency list
    List<int[]>[] graph = new ArrayList[n];
    for (int i = 0; i < n; i++) {
        graph[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
        int u = edge[0], v = edge[1], weight = edge[2];
        graph[u].add(new int[]{v, weight});
        // For undirected: graph[v].add(new int[]{u, weight});
    }

    // Dijkstra's algorithm
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[source] = 0;

    // Min-heap: (distance, node)
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    pq.offer(new int[]{0, source});

    while (!pq.isEmpty()) {
        int[] curr = pq.poll();
        int distance = curr[0], node = curr[1];

        // Skip outdated entries (already found better path)
        if (distance > dist[node]) continue;

        // Early termination (optional)
        if (node == target) return distance;

        // Update neighbors
        for (int[] neighbor : graph[node]) {
            int nextNode = neighbor[0], weight = neighbor[1];
            int newDist = distance + weight;

            if (newDist < dist[nextNode]) {
                dist[nextNode] = newDist;
                pq.offer(new int[]{newDist, nextNode});
            }
        }
    }

    return dist[target] == Integer.MAX_VALUE ? -1 : dist[target];
}
```

### Visual Example

```
Graph:
    4
  0 → 1
  |   |
  2   3
  |   |
  3 → 2

Find shortest path from 0 to 2

Initial: dist = [0, ∞, ∞, ∞]
PQ: [(0,0)]

Step 1: Process (0,0)
  Update dist[1] = 4, dist[3] = 2
  PQ: [(2,3), (4,1)]

Step 2: Process (2,3)
  Update dist[2] = 2+3 = 5
  PQ: [(4,1), (5,2)]

Step 3: Process (4,1)
  Update dist[2] = min(5, 4+3) = 5 (no change)
  PQ: [(5,2)]

Step 4: Process (5,2)
  Target reached!

Result: Shortest path 0 → 3 → 2 = 5
```

---

## Pattern 2: Bellman-Ford Algorithm

**📹 Video Tutorial**: [Bellman-Ford Algorithm Explained](https://www.youtube.com/watch?v=Mn9bFIIyXIM)

**Use Case**: Single-source shortest path with **negative weights**, detects negative cycles

**Algorithm**:
1. Initialize distances: 0 for source, ∞ for others
2. Relax all edges V-1 times:
   - For each edge (u, v, w): dist[v] = min(dist[v], dist[u] + w)
3. Check for negative cycles (one more relaxation)

**Complexity**: O(V × E) time, O(V) space

**Key Property**: Can handle negative weights, slower than Dijkstra.

### Template

```java
public int bellmanFord(int n, int[][] edges, int source, int target) {
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[source] = 0;

    // Relax all edges V-1 times
    for (int i = 0; i < n - 1; i++) {
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], weight = edge[2];

            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
            }
        }
    }

    // Check for negative cycles
    for (int[] edge : edges) {
        int u = edge[0], v = edge[1], weight = edge[2];

        if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
            return -1;  // Negative cycle detected
        }
    }

    return dist[target] == Integer.MAX_VALUE ? -1 : dist[target];
}
```

### Visual Example

```
Graph with negative weight:
  0 → 1 (4)
  ↓   ↓
  2   3
  ↑   ↓
  -5  2

Source: 0

Iteration 1:
  dist = [0, 4, ∞, ∞]

Iteration 2:
  dist = [0, 4, ∞, 6]

Iteration 3:
  dist = [0, 4, ∞, 6]
  Update: dist[2] = 6 + (-5) = 1

Iteration 4:
  dist = [0, 1, 1, 6]
  Update: dist[1] = 1 + 4 = 5 (worse, no change)

Final: dist = [0, 4, 1, 6]
```

---

## Pattern 3: Floyd-Warshall Algorithm

**📹 Video Tutorial**: [Floyd-Warshall Algorithm Explained](https://www.youtube.com/watch?v=8kO5nCIMbH8)

**Use Case**: All-pairs shortest paths (dense graphs)

**Algorithm**:
1. Initialize distance matrix:
   - dist[i][i] = 0
   - dist[i][j] = edge weight if edge exists, else ∞
2. For each intermediate node k:
   - For all pairs (i, j):
     - dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])

**Complexity**: O(V³) time, O(V²) space

**Key Property**: Dynamic programming, computes all-pairs distances.

### Template

```java
public int[][] floydWarshall(int n, int[][] edges) {
    int[][] dist = new int[n][n];

    // Initialize distances
    for (int i = 0; i < n; i++) {
        Arrays.fill(dist[i], Integer.MAX_VALUE / 2);  // Avoid overflow
        dist[i][i] = 0;
    }

    // Add edge weights
    for (int[] edge : edges) {
        int u = edge[0], v = edge[1], weight = edge[2];
        dist[u][v] = weight;
        // For undirected: dist[v][u] = weight;
    }

    // Floyd-Warshall: try all intermediate nodes
    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }

    return dist;
}
```

### Visual Example

```
Graph: 0 → 1 (3)
       ↓   ↓
       2   3
       ↓ ↙
       4

Initial:
     0  1  2  3  4
  0 [0  3  1  ∞  ∞]
  1 [∞  0  ∞  2  ∞]
  2 [∞  ∞  0  ∞  1]
  3 [∞  ∞  ∞  0  4]
  4 [∞  ∞  ∞  ∞  0]

k=0: Try 0 as intermediate
  dist[2][1] = min(∞, dist[2][0] + dist[0][1]) = min(∞, ∞) = ∞

k=1: Try 1 as intermediate
  dist[0][3] = min(∞, dist[0][1] + dist[1][3]) = min(∞, 3+2) = 5

k=2: Try 2 as intermediate
  dist[0][4] = min(∞, dist[0][2] + dist[2][4]) = min(∞, 1+1) = 2

Final:
     0  1  2  3  4
  0 [0  3  1  5  2]
  1 [∞  0  ∞  2  6]
  2 [∞  ∞  0  ∞  1]
  3 [∞  ∞  ∞  0  4]
  4 [∞  ∞  ∞  ∞  0]
```

---

## Comparison Table

| Feature | Dijkstra | Bellman-Ford | Floyd-Warshall |
|---------|----------|--------------|----------------|
| **Weights** | Non-negative only | Any (including negative) | Any (including negative) |
| **Output** | Single-source | Single-source | All-pairs |
| **Negative cycle** | Not supported | Detects | Detects |
| **Time** | O((V+E) log V) | O(V × E) | O(V³) |
| **Space** | O(V) | O(V) | O(V²) |
| **Best for** | Sparse graphs, non-neg | Negative weights | Dense graphs, all-pairs |

---

## Common Mistakes

### ❌ Using Dijkstra with Negative Weights

```java
// WRONG - Dijkstra fails with negative weights
public int shortestPath(int[][] edges) {
    // Check for negative weights first!
    for (int[] edge : edges) {
        if (edge[2] < 0) {
            // Use Bellman-Ford instead
        }
    }
}

// CORRECT - Choose appropriate algorithm
boolean hasNegative = hasNegativeWeights(edges);
return hasNegative ? bellmanFord(...) : dijkstra(...);
```

### ❌ Not Skipping Outdated Entries in Dijkstra

```java
// WRONG - processes outdated entries
while (!pq.isEmpty()) {
    int[] curr = pq.poll();
    int distance = curr[0], node = curr[1];

    // May process same node with worse distance
    for (int[] neighbor : graph[node]) {
        // Update...
    }
}

// CORRECT - skip outdated entries
while (!pq.isEmpty()) {
    int[] curr = pq.poll();
    int distance = curr[0], node = curr[1];

    if (distance > dist[node]) continue;  // Skip outdated

    for (int[] neighbor : graph[node]) {
        // Update...
    }
}
```

### ❌ Integer Overflow in Floyd-Warshall

```java
// WRONG - may overflow
int[][] dist = new int[n][n];
Arrays.fill(dist[i], Integer.MAX_VALUE);
dist[i][j] = dist[i][k] + dist[k][j];  // Overflow!

// CORRECT - use safer infinity value
int[][] dist = new int[n][n];
Arrays.fill(dist[i], Integer.MAX_VALUE / 2);  // Safe from overflow
dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
```

### ❌ Wrong PQ Comparator

```java
// WRONG - may give incorrect results or overflow
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
// If a[0] - b[0] overflows, comparison breaks

// CORRECT - use Integer.compare or careful subtraction
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
    Integer.compare(a[0], b[0])
);
```

---

## Problems

- [x] [Network Delay Time](https://leetcode.com/problems/network-delay-time) - Medium *(Dijkstra/Bellman-Ford)* ⭐ **IMPORTANT** ⭐
- [x] [Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/) - Medium *(Modified Bellman-Ford)* ⭐ **IMPORTANT** ⭐
- [x] [Find the City With the Smallest Number of Neighbors at a Threshold Distance](https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/) - Medium *(Floyd-Warshall)*
- [x] [Path with Maximum Probability](https://leetcode.com/problems/path-with-maximum-probability/) - Medium *(Modified Dijkstra)*
- [x] [Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/description/) - Medium *(Dijkstra on 2D Grid)*

### Network Delay Time ⭐ **IMPORTANT** ⭐

**Problem**: [Network Delay Time](https://leetcode.com/problems/network-delay-time) - Medium

**Why Important**: Classic Dijkstra application, tests understanding of single-source shortest path

**Approach**:
1. Build adjacency list from edges
2. Run Dijkstra from source node k
3. Find maximum distance among all reachable nodes
4. If any node unreachable, return -1

**Complexity**: O((V + E) log V) time, O(V + E) space

**Solution**:

```java
public int networkDelayTime(int[][] times, int n, int k) {
    // Build adjacency list
    List<int[]>[] graph = new ArrayList[n + 1];  // 1-indexed
    for (int i = 1; i <= n; i++) {
        graph[i] = new ArrayList<>();
    }

    for (int[] time : times) {
        int u = time[0], v = time[1], w = time[2];
        graph[u].add(new int[]{v, w});
    }

    // Dijkstra's algorithm
    int[] dist = new int[n + 1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[k] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    pq.offer(new int[]{0, k});  // {distance, node}

    while (!pq.isEmpty()) {
        int[] curr = pq.poll();
        int distance = curr[0], node = curr[1];

        // Skip outdated entries
        if (distance > dist[node]) continue;

        // Update neighbors
        for (int[] neighbor : graph[node]) {
            int nextNode = neighbor[0], weight = neighbor[1];
            int newDist = distance + weight;

            if (newDist < dist[nextNode]) {
                dist[nextNode] = newDist;
                pq.offer(new int[]{newDist, nextNode});
            }
        }
    }

    // Find maximum distance (time for all nodes to receive signal)
    int maxTime = 0;
    for (int i = 1; i <= n; i++) {
        if (dist[i] == Integer.MAX_VALUE) {
            return -1;  // Node unreachable
        }
        maxTime = Math.max(maxTime, dist[i]);
    }

    return maxTime;
}
```

**Key Points**:
- **Max distance**: Signal reaches all nodes when slowest node receives it
- **Unreachable nodes**: If any dist[i] == ∞, return -1
- **1-indexed**: LeetCode uses 1-indexed nodes, not 0-indexed
- **Skip outdated entries**: Critical optimization to avoid reprocessing
- Can also solve with Bellman-Ford: O(V × E) but simpler code

### Cheapest Flights Within K Stops ⭐ **IMPORTANT** ⭐

**Problem**: [Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/) - Medium

**Why Important**: Standard Dijkstra fails here — a higher-cost path with fewer stops may be better for future legs. Requires modified Bellman-Ford with bounded iterations.

**Approach**:
1. Run Bellman-Ford for exactly **K+1** rounds (K stops = K+1 flights/edges)
2. In each round, try relaxing all flights
3. Use a **temp copy** of prices before each round — prevents cascading updates within the same round (one round = one more hop allowed)
4. Return `prices[dst]`, or `-1` if unreachable

**Complexity**: O(K × E) time, O(V) space

**Solution**:

```java
public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    int[] prices = new int[n];
    Arrays.fill(prices, Integer.MAX_VALUE);
    prices[src] = 0;

    // K stops = K+1 flights/edges, so relax K+1 times
    for (int i = 0; i <= k; i++) {
        // Copy prevents cascading updates within the same round
        int[] temp = Arrays.copyOf(prices, n);

        for (int[] flight : flights) {
            int u = flight[0], v = flight[1], cost = flight[2];

            if (prices[u] != Integer.MAX_VALUE && prices[u] + cost < temp[v]) {
                temp[v] = prices[u] + cost;
            }
        }

        prices = temp;
    }

    return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
}
```

**Key Points**:
- **Why not standard Dijkstra?** Dijkstra greedily finalizes cheapest node, but a cheap path with many stops may block a valid path within K stops
- **Why temp copy?** Without it, a single round could chain multiple flights — each round must represent exactly one additional flight
- **K+1 rounds**: K stops between src and dst means K+1 edges to traverse
- **Modified Bellman-Ford**: Same relaxation idea, but bounded to K+1 iterations instead of V-1

---

### Path with Maximum Probability

**Problem**: [Path with Maximum Probability](https://leetcode.com/problems/path-with-maximum-probability/) - Medium

**Approach**:
1. Build adjacency list with probabilities as edge weights
2. Run **modified Dijkstra** using a **max-heap** (maximize probability instead of minimizing distance)
3. Start with `prob[start] = 1.0`, all others `0.0`
4. For each neighbor: `newProb = currProb * edgeProb`; update if greater
5. Return `prob[end]`, or `0.0` if unreachable

**Complexity**: O((V + E) log V) time, O(V + E) space

**Key Points**:
- **Max-heap** (negate probability or use `(b[0] - a[0])` comparator) instead of min-heap
- **Multiply** probabilities along path (not add distances)
- Skip if `currProb < prob[node]` (outdated entry — same pattern as Dijkstra)
- Same Dijkstra structure, just flipped optimization direction

### Path With Minimum Effort

**Problem**: [Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/description/) - Medium

**Approach**:
1. Dijkstra on a 2D grid — state is `(effort, row, col)`
2. `effort[row][col]` = minimum effort (max absolute difference along path) to reach that cell
3. For each neighbor: `newEffort = max(currEffort, abs(heights[row][col] - heights[newRow][newCol]))`
4. Update if `newEffort < effort[newRow][newCol]`
5. Return `effort[m-1][n-1]` when destination is reached

**Complexity**: O(m×n × log(m×n)) time, O(m×n) space

**Key Points**:
- **Effort = max diff along path**, not sum — so we take `max` instead of `+`
- **Min-heap on effort**: greedily process the path with least max-diff so far
- Same Dijkstra skeleton as weighted graph, applied to 4-directional grid
- Skip if `currEffort >= effort[row][col]` (outdated entry)

---

## Key Takeaways

1. **Dijkstra**: Best for non-negative weights, O((V+E) log V), greedy approach
2. **Bellman-Ford**: Handles negative weights, detects cycles, O(V × E)
3. **Floyd-Warshall**: All-pairs shortest paths, O(V³), DP approach
4. **Choose wisely**: Non-negative → Dijkstra, Negative → Bellman-Ford, All-pairs → Floyd-Warshall
5. **Dijkstra optimization**: Skip outdated PQ entries with `if (distance > dist[node]) continue`
6. **Negative cycles**: Only Bellman-Ford and Floyd-Warshall detect them
7. **Sparse vs Dense**: Dijkstra better for sparse, Floyd-Warshall for dense (all-pairs)

---

> **[← Union Find](union-find.md)** | **[Back to Graphs Overview](Notes.md)** | **[MST →](minimum-spanning-tree.md)**
