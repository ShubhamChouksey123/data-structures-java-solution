# Union Find (Disjoint Set Union)

> **[← Back to Graphs Overview](Notes.md)**

---

## When to Use

✅ **Track connected components, detect cycles, or group elements dynamically**

### Keywords
- "connected components"
- "disjoint sets"
- "group", "merge"
- "redundant connection"
- "cycle detection" (in undirected graphs)
- "network connectivity"

### Examples
- Number of connected components in graph
- Detect redundant connections (cycle detection)
- Merge accounts/groups
- Network connectivity (can nodes communicate?)
- Kruskal's MST algorithm

---

## Core Concept

Union Find (Disjoint Set Union) maintains a collection of disjoint sets and supports two operations efficiently:
- **Find**: Determine which set an element belongs to (find root)
- **Union**: Merge two sets into one

**Key Optimizations**:
1. **Path Compression**: Flatten tree during Find
2. **Union by Rank**: Attach smaller tree under larger tree

**Complexity**: O(α(n)) per operation ≈ O(1), where α is inverse Ackermann function

---

## Pattern: Union Find with Optimizations

**Use Case**: Efficiently track and merge disjoint sets

**Operations**:
1. **Find(x)**: Find root of x's set (with path compression)
2. **Union(x, y)**: Merge sets containing x and y (with union by rank)
3. **Connected(x, y)**: Check if x and y are in same set

**Algorithm**:
- Initialize: Each element is its own parent
- Find: Follow parent pointers to root, compress path
- Union: Connect roots, prefer attaching smaller to larger (rank)

**Complexity**: O(α(n)) per operation, where α(n) ≤ 4 for practical values

### Template

```java
class UnionFind {
    private int[] parent;
    private int[] rank;
    private int components;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        components = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i;  // Each node is its own parent
            rank[i] = 1;    // Initial rank 1
        }
    }

    // Find with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }

    // Union by rank
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;  // Already connected (cycle detected)
        }

        // Union by rank: attach smaller tree under larger
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        components--;
        return true;
    }

    // Check if two nodes are connected
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    // Get number of connected components
    public int getComponents() {
        return components;
    }
}
```

### Visual Example

```
Initial: 5 nodes, each is its own component
parent = [0, 1, 2, 3, 4]
rank   = [1, 1, 1, 1, 1]

Union(0, 1):
  0
  └── 1
parent = [0, 0, 2, 3, 4]
rank   = [2, 1, 1, 1, 1]

Union(2, 3):
  0       2
  └── 1   └── 3
parent = [0, 0, 2, 2, 4]
rank   = [2, 1, 2, 1, 1]

Union(1, 3): find(1) = 0, find(3) = 2
  0
  ├── 1
  └── 2
      └── 3
parent = [0, 0, 0, 2, 4]
rank   = [3, 1, 2, 1, 1]

Find(3): 3 → 2 → 0 (path compression)
After path compression:
parent = [0, 0, 0, 0, 4]
```

---

## Union by Rank vs Union by Size

When merging two sets, we need a strategy to decide which tree becomes the root. Two common approaches:

### Union by Rank

**Concept**: Rank represents the **upper bound** of the tree's height (not exact height due to path compression).

**Rule**: Attach the tree with smaller rank under the tree with larger rank. If ranks are equal, attach either tree and increment the rank of the new root.

**Properties**:
- Rank is an approximation of tree height
- Path compression doesn't update ranks (keeps implementation simple)
- Guarantees tree height ≤ log(n)

**Code**:
```java
class UnionFind {
    private int[] parent;
    private int[] rank;  // Upper bound of tree height

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;  // Initial rank = 1 (or 0)
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false;

        // Attach smaller rank tree under larger rank tree
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;  // Increment rank only when ranks are equal
        }

        return true;
    }
}
```

### Union by Size

**Concept**: Size represents the **exact number of elements** in the tree/set.

**Rule**: Attach the smaller tree (fewer elements) under the larger tree (more elements). Always update the size of the new root.

**Properties**:
- Size is the exact count of elements in the set
- Size must be updated after every union
- Also guarantees tree height ≤ log(n)
- Useful when you need to know set sizes

**Code**:
```java
class UnionFind {
    private int[] parent;
    private int[] size;  // Exact number of elements in set

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;  // Each set initially has 1 element
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false;

        // Attach smaller tree under larger tree
        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];  // Update size of new root
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];  // Update size of new root
        }

        return true;
    }

    // Get size of set containing x
    public int getSize(int x) {
        return size[find(x)];
    }
}
```

### Comparison

| Aspect | Union by Rank | Union by Size |
|--------|--------------|---------------|
| **Tracks** | Upper bound of height | Exact element count |
| **Update frequency** | Only when ranks equal | Every union |
| **Complexity** | O(α(n)) | O(α(n)) |
| **Memory** | O(n) | O(n) |
| **Use when** | Standard cases | Need set sizes |
| **Implementation** | Simpler (no size updates) | Slightly more bookkeeping |

### Which to Use?

**Use Union by Rank when**:
- You only care about connectivity
- Standard Union Find operations
- Slightly simpler implementation

**Use Union by Size when**:
- You need to know the size of each component
- Problems ask "how many elements in the set?"
- Example: "Find size of largest connected component"

**Key Insight**: Both achieve O(α(n)) complexity with path compression. The choice depends on whether you need to track set sizes.

### Visual Comparison

```
Union by Rank Example:
Initial: rank = [1, 1, 1, 1]

union(0, 1): rank[0] = rank[1], so rank[0]++
  0 (rank=2)
  └── 1 (rank=1)

union(2, 3): rank[2] = rank[3], so rank[2]++
  2 (rank=2)
  └── 3 (rank=1)

union(0, 2): rank[0] = rank[2], so rank[0]++
  0 (rank=3)
  ├── 1 (rank=1)
  └── 2 (rank=2)
      └── 3 (rank=1)

Union by Size Example:
Initial: size = [1, 1, 1, 1]

union(0, 1): size[0] = 1, size[1] = 1, so size[0] = 2
  0 (size=2)
  └── 1 (size=1)

union(2, 3): size[2] = 1, size[3] = 1, so size[2] = 2
  2 (size=2)
  └── 3 (size=1)

union(0, 2): size[0] = 2, size[2] = 2, so size[0] = 4
  0 (size=4)
  ├── 1 (size=1)
  └── 2 (size=2)
      └── 3 (size=1)
```

---

## Pattern: Cycle Detection in Undirected Graph

**Use Case**: Detect if adding an edge creates a cycle

**Algorithm**:
1. Initialize Union Find with n nodes
2. For each edge (u, v):
   - If find(u) == find(v), cycle detected
   - Otherwise, union(u, v)

**Complexity**: O(E × α(n)) time

### Template

```java
public boolean hasCycle(int n, int[][] edges) {
    UnionFind uf = new UnionFind(n);

    for (int[] edge : edges) {
        int u = edge[0], v = edge[1];

        // If already connected, adding this edge creates cycle
        if (uf.connected(u, v)) {
            return true;
        }

        uf.union(u, v);
    }

    return false;
}
```

---

## Pattern: Count Connected Components

**Use Case**: Count number of disjoint sets after all unions

**Algorithm**:
1. Initialize Union Find with n components
2. For each edge, union the nodes
3. Return remaining component count

**Complexity**: O(E × α(n)) time

### Template

```java
public int countComponents(int n, int[][] edges) {
    UnionFind uf = new UnionFind(n);

    for (int[] edge : edges) {
        uf.union(edge[0], edge[1]);
    }

    return uf.getComponents();
}
```

---

## Why Optimizations Matter

### Without Path Compression

```
Find operations without compression:
Tree: 0 → 1 → 2 → 3 → 4

find(4): 4 → 3 → 2 → 1 → 0 (5 steps)
find(4): 4 → 3 → 2 → 1 → 0 (5 steps again!)

Time: O(n) per find
```

### With Path Compression

```
After first find(4):
Tree flattened: 4 → 0, 3 → 0, 2 → 0, 1 → 0

find(4): 4 → 0 (2 steps)
find(4): 4 → 0 (2 steps)

Time: O(α(n)) ≈ O(1) per find
```

### Union by Rank

```
Without rank (always attach right to left):
union(0,1): 0 → 1
union(0,2): 0 → 1 → 2
union(0,3): 0 → 1 → 2 → 3  (becomes linear!)

With rank (attach smaller to larger):
union(0,1): 0-1 (rank[0]=2)
union(2,3): 2-3 (rank[2]=2)
union(0,2):
    0
   ├─ 1
   └─ 2
      └─ 3
Tree stays balanced!
```

---

## Common Mistakes

### ❌ Forgetting Path Compression

```java
// WRONG - O(n) per find
public int find(int x) {
    while (parent[x] != x) {
        x = parent[x];
    }
    return x;
}

// CORRECT - O(α(n)) with path compression
public int find(int x) {
    if (parent[x] != x) {
        parent[x] = find(parent[x]);  // Compress path
    }
    return parent[x];
}
```

### ❌ Not Using Rank/Size for Union

```java
// WRONG - can create unbalanced trees
public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    parent[rootY] = rootX;  // Always attach Y to X
}

// CORRECT - union by rank
public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);

    if (rank[rootX] < rank[rootY]) {
        parent[rootX] = rootY;
    } else if (rank[rootX] > rank[rootY]) {
        parent[rootY] = rootX;
    } else {
        parent[rootY] = rootX;
        rank[rootX]++;
    }
}
```

### ❌ Union Before Find

```java
// WRONG - unioning indices instead of roots
public void union(int x, int y) {
    parent[y] = x;  // Wrong! Should find roots first
}

// CORRECT
public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    // Union roots...
}
```

### ❌ Not Checking If Already Connected

```java
// WRONG - may incorrectly decrement components
public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    parent[rootY] = rootX;
    components--;  // Wrong if already connected!
}

// CORRECT
public boolean union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);

    if (rootX == rootY) {
        return false;  // Already connected
    }

    parent[rootY] = rootX;
    components--;
    return true;
}
```

---

## Problems

- [x] [Find if Path Exists in Graph](https://leetcode.com/problems/find-if-path-exists-in-graph/description/) - Easy
- [x] [Number of Operations to Make Network Connected](https://leetcode.com/problems/number-of-operations-to-make-network-connected/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Redundant Connection](https://leetcode.com/problems/redundant-connection/) - Medium
- [x] [Accounts Merge](https://leetcode.com/problems/accounts-merge/) - Medium
- [x] [Satisfiability of Equality Equations](https://leetcode.com/problems/satisfiability-of-equality-equations/) - Medium
- [x] [Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/) - Medium

### Number of Operations to Make Network Connected ⭐ **IMPORTANT** ⭐

**Problem**: [Number of Operations to Make Network Connected](https://leetcode.com/problems/number-of-operations-to-make-network-connected/) - Medium

**Why Important**: Combines cycle detection with component counting, tests understanding of Union Find properties

**Approach**:
1. Need at least n-1 edges to connect n computers
2. Use Union Find to count:
   - Redundant edges (cycles)
   - Connected components
3. To connect k components, need k-1 edges
4. If redundant edges ≥ (components - 1), possible

**Complexity**: O(E × α(n)) time, O(n) space

**Solution**:

```java
public int makeConnected(int n, int[][] connections) {
    // Need at least n-1 edges to connect n nodes
    if (connections.length < n - 1) {
        return -1;
    }

    UnionFind uf = new UnionFind(n);
    int redundant = 0;

    // Count redundant edges (cycles)
    for (int[] conn : connections) {
        if (!uf.union(conn[0], conn[1])) {
            redundant++;  // Edge creates cycle (redundant)
        }
    }

    int components = uf.getComponents();

    // Need (components - 1) edges to connect all components
    int needed = components - 1;

    return redundant >= needed ? needed : -1;
}

class UnionFind {
    private int[] parent;
    private int[] rank;
    private int components;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        components = n;

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
            return false;  // Already connected (redundant edge)
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        components--;
        return true;
    }

    public int getComponents() {
        return components;
    }
}
```

**Key Points**:
- **Minimum edges needed**: n-1 to connect n nodes
- **Redundant edges**: When union returns false (already connected)
- **Connect k components**: Need k-1 edges
- **Key insight**: Redundant edges can be reused to connect components
- Union Find naturally tracks both cycles and components

---

## Key Takeaways

1. **Union Find** maintains disjoint sets with near-constant time operations
2. **Path compression** flattens tree during Find: O(α(n)) ≈ O(1)
3. **Union by rank** keeps trees balanced: attach smaller to larger
4. **Both optimizations** needed for optimal O(α(n)) complexity
5. **Cycle detection**: If find(u) == find(v), edge (u,v) creates cycle
6. **Component counting**: Track count, decrease on successful union
7. **Applications**: Connected components, cycle detection, Kruskal's MST, network connectivity

---

> **[← Topological Sort](topological-sort.md)** | **[Back to Graphs Overview](Notes.md)** | **[Shortest Path →](shortest-path.md)**
