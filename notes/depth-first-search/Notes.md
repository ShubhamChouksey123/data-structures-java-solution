# Depth First Search (DFS)

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Explore all paths, detect cycles, connected components, or exhaustive search**

### Keywords
- "all paths", "explore all possibilities"
- "connected components", "islands"
- "cycle detection"
- "backtracking", "exhaustive search"
- "from boundary", "flood fill"

### Examples
- Count islands/connected components
- Find all paths from source to target
- Detect cycles in graph
- Flood fill (paint fill tool)
- DFS from boundary (enclaves, closed islands)

---

## Core Concept

DFS explores as far as possible along each branch before backtracking. Uses **stack** (explicit or recursion call stack).

**Key Insight**: Go deep first, backtrack when stuck. Good for exhaustive exploration and finding any path (not shortest).

**Complexity**: O(V + E) for graphs, O(m×n) for grids where V=nodes, E=edges

---

## Pattern 1: Basic DFS (Recursive)

**Use Case**: Graph/tree traversal, connected components, path finding

**Algorithm**:
1. Mark current node as visited
2. Process current node
3. Recursively visit all unvisited neighbors
4. Backtrack when no unvisited neighbors

**Complexity**: O(V + E) time, O(V) space (recursion stack + visited set)

### Template

```java
public void dfs(Node node, Set<Node> visited) {
    if (node == null || visited.contains(node)) {
        return;
    }

    visited.add(node);
    // Process node

    for (Node neighbor : node.neighbors) {
        if (!visited.contains(neighbor)) {
            dfs(neighbor, visited);
        }
    }
}
```

---

## Pattern 2: DFS on Grid (4-directional)

**Use Case**: Islands, flood fill, connected regions in 2D grid

**Algorithm**:
1. Start DFS from a cell
2. Mark as visited
3. Recursively explore 4 directions (up, down, left, right)
4. Stop at boundaries or visited cells

**Complexity**: O(m×n) time, O(m×n) space (worst case recursion depth)

### Template

```java
public void dfsGrid(int[][] grid, int row, int col, boolean[][] visited) {
    int m = grid.length, n = grid[0].length;

    // Base cases: out of bounds, visited, or invalid cell
    if (row < 0 || row >= m || col < 0 || col >= n ||
        visited[row][col] || grid[row][col] == 0) {
        return;
    }

    visited[row][col] = true;
    // Process cell

    // Explore 4 directions
    dfsGrid(grid, row - 1, col, visited);  // up
    dfsGrid(grid, row + 1, col, visited);  // down
    dfsGrid(grid, row, col - 1, visited);  // left
    dfsGrid(grid, row, col + 1, visited);  // right
}

// Count islands example
public int numIslands(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    boolean[][] visited = new boolean[m][n];
    int count = 0;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == 1 && !visited[i][j]) {
                dfsGrid(grid, i, j, visited);
                count++;  // Found a new island
            }
        }
    }

    return count;
}
```

---

## Pattern 3: DFS from Boundary ⭐ **IMPORTANT** ⭐

**Use Case**: Closed islands, enclaves (regions NOT connected to boundary)

**Algorithm**:
1. DFS from all boundary cells to mark cells connected to boundary
2. Remaining unmarked cells are isolated (closed islands/enclaves)
3. Count or process isolated regions

**Complexity**: O(m×n) time, O(m×n) space

### Key Insight

Instead of checking if region touches boundary during DFS, mark all boundary-connected cells first, then process unmarked cells.

### Template

```java
public int closedIslands(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    boolean[][] visited = new boolean[m][n];

    // Step 1: DFS from all boundary cells
    for (int i = 0; i < m; i++) {
        dfs(grid, i, 0, visited);        // left boundary
        dfs(grid, i, n - 1, visited);    // right boundary
    }
    for (int j = 0; j < n; j++) {
        dfs(grid, 0, j, visited);        // top boundary
        dfs(grid, m - 1, j, visited);    // bottom boundary
    }

    // Step 2: Count closed islands (unmarked land cells)
    int count = 0;
    for (int i = 1; i < m - 1; i++) {
        for (int j = 1; j < n - 1; j++) {
            if (grid[i][j] == 1 && !visited[i][j]) {
                dfs(grid, i, j, visited);
                count++;
            }
        }
    }

    return count;
}
```

### Visual Example

```
Grid (1=land, 0=water):
1 1 1 1 1
1 0 0 0 1
1 0 1 0 1   ← Closed island (not touching boundary)
1 0 0 0 1
1 1 1 1 1

Step 1: DFS from boundary marks all outer 1s
Step 2: Middle island (row 2, col 2) remains unmarked → count it
```

---

## Pattern 4: Cycle Detection with DFS

**Use Case**: Detect cycles in directed/undirected graphs

**Algorithm** (Directed Graph):
1. Track three states: UNVISITED, VISITING, VISITED
2. During DFS, mark node as VISITING
3. If encounter VISITING node, cycle exists
4. After exploring all neighbors, mark as VISITED

**Complexity**: O(V + E) time, O(V) space

### Template

```java
private static final int UNVISITED = 0;
private static final int VISITING = 1;
private static final int VISITED = 2;

public boolean hasCycle(List<List<Integer>> graph) {
    int n = graph.size();
    int[] state = new int[n];

    for (int i = 0; i < n; i++) {
        if (state[i] == UNVISITED) {
            if (dfsCycle(graph, i, state)) {
                return true;
            }
        }
    }

    return false;
}

private boolean dfsCycle(List<List<Integer>> graph, int node, int[] state) {
    state[node] = VISITING;  // Mark as currently exploring

    for (int neighbor : graph.get(node)) {
        if (state[neighbor] == VISITING) {
            return true;  // Back edge → cycle
        }
        if (state[neighbor] == UNVISITED) {
            if (dfsCycle(graph, neighbor, state)) {
                return true;
            }
        }
    }

    state[node] = VISITED;  // Done exploring this node
    return false;
}
```

---

## Common Mistakes

### ❌ Not Checking Bounds in Base Case

```java
// WRONG - check visited before bounds
if (visited[row][col] || row < 0 || row >= m) {
    return;  // ArrayIndexOutOfBoundsException!
}

// CORRECT - check bounds first
if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col]) {
    return;
}
```

### ❌ Modifying Grid Instead of Using Visited Array

```java
// PROBLEMATIC - loses original data
void dfs(int[][] grid, int row, int col) {
    grid[row][col] = 0;  // Mark visited by changing grid
    // Can't restore original values
}

// BETTER - use separate visited array
void dfs(int[][] grid, int row, int col, boolean[][] visited) {
    visited[row][col] = true;  // Preserves original grid
}
```

### ❌ Forgetting to Mark Visited Before Recursive Calls

```java
// WRONG - mark visited after processing neighbors
void dfs(Node node, Set<Node> visited) {
    for (Node neighbor : node.neighbors) {
        if (!visited.contains(neighbor)) {
            dfs(neighbor, visited);
        }
    }
    visited.add(node);  // Too late! Can revisit node
}

// CORRECT - mark visited immediately
void dfs(Node node, Set<Node> visited) {
    visited.add(node);  // Mark first
    for (Node neighbor : node.neighbors) {
        if (!visited.contains(neighbor)) {
            dfs(neighbor, visited);
        }
    }
}
```

---

## Problems

- [ ] [Number of Closed Islands](https://leetcode.com/problems/number-of-closed-islands/) - Medium ⭐ **IMPORTANT** ⭐
- [ ] [Coloring a Border](https://leetcode.com/problems/coloring-a-border/) - Medium
- [ ] [Number of Enclaves](https://leetcode.com/problems/number-of-enclaves/) - Medium
- [ ] [Time Needed to Inform All Employees](https://leetcode.com/problems/time-needed-to-inform-all-employees/) - Medium
- [ ] [Find Eventual Safe States](https://leetcode.com/problems/find-eventual-safe-states/) - Medium

### Number of Closed Islands ⭐ **IMPORTANT** ⭐

**Problem**: [Number of Closed Islands](https://leetcode.com/problems/number-of-closed-islands/) - Medium

**Why Important**: Tests understanding of "DFS from boundary" technique, commonly asked

**Approach**:
1. DFS from all boundary cells (edges) to mark connected regions
2. These regions touch the boundary, so they're NOT closed islands
3. Count remaining unmarked land regions (these are closed islands)

**Complexity**: O(m×n) time, O(m×n) space

**Key Points**:
- Two-pass approach: mark boundary-connected cells, then count isolated regions
- More efficient than checking boundary during each DFS
- Can modify grid in-place (mark boundary cells as water) or use visited array
- Similar technique for "Number of Enclaves" problem

---

## Key Takeaways

1. **Recursion or Stack**: DFS uses call stack (recursion) or explicit stack
2. **Mark visited first**: Before recursive calls to avoid infinite loops
3. **Base cases matter**: Check bounds before array access in grid DFS
4. **DFS from boundary**: Mark boundary-connected regions first, then process isolated regions
5. **Cycle detection**: Use 3 states (UNVISITED, VISITING, VISITED) for directed graphs
6. **Not for shortest path**: DFS finds any path, use BFS for shortest path
7. **O(V+E) time, O(V) space**: V for visited set/recursion, E for traversing edges

---

> **[← BFS](../breadth-first-search/Notes.md)** | **[Back to Overview](../README.md)** | **[Backtracking →](../backtracking/Notes.md)**
