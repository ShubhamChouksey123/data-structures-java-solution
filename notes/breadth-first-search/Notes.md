# Breadth First Search (BFS)

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Find shortest path, level-order traversal, or explore all nodes at same distance**

### Keywords
- "shortest path"
- "level by level", "level-order"
- "minimum distance/steps"
- "nearest", "closest"
- "all nodes at distance k"

### Examples
- Shortest path in unweighted graph/grid
- Level-order tree traversal
- Rotting oranges (multi-source BFS)
- Word ladder transformations

---

## Core Concept

BFS explores nodes level by level using a **queue**. Process all nodes at distance `d` before moving to distance `d+1`.

**Key Insight**: First time you reach a node = shortest path in unweighted graph.

**Complexity**: O(V + E) for graphs, O(m×n) for grids where V=nodes, E=edges, m×n=grid size

---

## Pattern 1: Basic BFS Template

**Use Case**: Tree level-order or graph traversal with shortest path

**Algorithm**:
1. Add start node(s) to queue
2. Mark as visited
3. While queue not empty:
   - Poll node
   - Process node
   - Add unvisited neighbors to queue
   - Mark neighbors as visited

**Complexity**: O(V + E) time, O(V) space

### Template

```java
public void bfs(Node start) {
    Queue<Node> queue = new LinkedList<>();
    Set<Node> visited = new HashSet<>();

    queue.offer(start);
    visited.add(start);

    while (!queue.isEmpty()) {
        Node node = queue.poll();
        // Process node

        for (Node neighbor : node.neighbors) {
            if (!visited.contains(neighbor)) {
                queue.offer(neighbor);
                visited.add(neighbor);
            }
        }
    }
}
```

---

## Pattern 2: BFS on Grid (4-directional)

**Use Case**: Shortest path in 2D grid (binary matrix, obstacles, etc.)

**Algorithm**:
1. Add start cell(s) to queue with distance 0
2. Mark as visited
3. For each cell, explore 4 directions: up, down, left, right
4. Track distance/level for shortest path

**Complexity**: O(m×n) time, O(m×n) space

### Template

```java
public int bfsGrid(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[m][n];
    int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};  // up, down, left, right

    // Add start cell(s)
    queue.offer(new int[]{startRow, startCol, 0});  // {row, col, distance}
    visited[startRow][startCol] = true;

    while (!queue.isEmpty()) {
        int[] curr = queue.poll();
        int row = curr[0], col = curr[1], dist = curr[2];

        // Check if reached target
        if (isTarget(row, col)) return dist;

        // Explore 4 directions
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (isValid(newRow, newCol, m, n) &&
                !visited[newRow][newCol] &&
                grid[newRow][newCol] != OBSTACLE) {

                queue.offer(new int[]{newRow, newCol, dist + 1});
                visited[newRow][newCol] = true;
            }
        }
    }

    return -1;  // No path found
}
```

---

## Pattern 3: Multi-Source BFS ⭐ **IMPORTANT** ⭐

**Use Case**: Multiple starting points spreading simultaneously (rotting oranges, fire spreading)

**Algorithm**:
1. Add **all source cells** to queue initially
2. Process level by level (all sources spread together)
3. Track time/rounds needed

**Complexity**: O(m×n) time, O(m×n) space

### Key Insight

Start with all sources in queue at once → they spread simultaneously in parallel.

### Visual Example

```
Rotting Oranges (2=rotten, 1=fresh):

Time 0:     Time 1:     Time 2:
2 1 1       2 2 1       2 2 2
1 1 0  →    2 1 0  →    2 2 0
0 1 1       0 1 1       0 2 1

Queue starts with both rotten oranges (0,0)
All fresh neighbors rot simultaneously each minute
```

---

## Pattern 4: Level-by-Level Processing

**Use Case**: Need to track level/depth explicitly (tree level-order, word ladder)

**Algorithm**:
1. Process queue in levels using size snapshot
2. Track level count explicitly

**Complexity**: O(V + E) time, O(V) space

### Template

```java
public List<List<Integer>> bfsLevels(Node root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
        int levelSize = queue.size();  // Snapshot of current level
        List<Integer> currentLevel = new ArrayList<>();

        for (int i = 0; i < levelSize; i++) {
            Node node = queue.poll();
            currentLevel.add(node.val);

            // Add children for next level
            for (Node child : node.children) {
                queue.offer(child);
            }
        }

        result.add(currentLevel);
    }

    return result;
}
```

---

## Common Mistakes

### ❌ Marking Visited After Polling (Not When Adding)

```java
// WRONG - can add same node multiple times to queue
while (!queue.isEmpty()) {
    Node node = queue.poll();
    visited.add(node);  // Too late! Already in queue multiple times

    for (Node neighbor : node.neighbors) {
        if (!visited.contains(neighbor)) {
            queue.offer(neighbor);
        }
    }
}

// CORRECT - mark visited when adding to queue
while (!queue.isEmpty()) {
    Node node = queue.poll();

    for (Node neighbor : node.neighbors) {
        if (!visited.contains(neighbor)) {
            queue.offer(neighbor);
            visited.add(neighbor);  // Mark immediately
        }
    }
}
```

### ❌ Bounds Checking in Grid

```java
// WRONG - missing bounds check
int newRow = row + dir[0];
int newCol = col + dir[1];
if (!visited[newRow][newCol]) {  // ArrayIndexOutOfBoundsException!
    queue.offer(new int[]{newRow, newCol});
}

// CORRECT - check bounds first
if (newRow >= 0 && newRow < m &&
    newCol >= 0 && newCol < n &&
    !visited[newRow][newCol]) {
    queue.offer(new int[]{newRow, newCol});
}
```

### ❌ Forgetting to Mark Start as Visited

```java
// WRONG - start node not marked, can revisit
queue.offer(start);
// Missing: visited.add(start);

// CORRECT
queue.offer(start);
visited.add(start);
```

---

## Problems

- [x] [Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/) - Medium
- [x] [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [As Far from Land as Possible](https://leetcode.com/problems/as-far-from-land-as-possible/) - Medium
- [ ] [Word Ladder](https://leetcode.com/problems/word-ladder/) - Hard

### Rotting Oranges ⭐ **IMPORTANT** ⭐

**Problem**: [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) - Medium

**Why Important**: Classic multi-source BFS, tests understanding of simultaneous spreading

**Approach**:
1. Add all initially rotten oranges to queue
2. Track fresh orange count
3. BFS level-by-level (each level = 1 minute)
4. Return minutes when all fresh oranges rot (or -1 if impossible)

**Complexity**: O(m×n) time, O(m×n) space

**Key Points**:
- Multi-source BFS: start with ALL rotten oranges in queue
- Level-by-level processing to track time
- Check if any fresh oranges remain unreachable
- Each BFS level represents 1 minute passing

---

## Key Takeaways

1. **Queue + Visited Set**: Core data structures for BFS
2. **Mark visited when adding**: Not when polling (prevents duplicates)
3. **First reach = shortest path**: In unweighted graphs/grids
4. **Level-by-level**: Use `queue.size()` snapshot for explicit levels
5. **Multi-source BFS**: Add all sources to queue initially for simultaneous spread
6. **Grid bounds**: Always check `0 <= row < m && 0 <= col < n`
7. **O(V+E) time, O(V) space**: Optimal for graph traversal

---

> **[← Back to Overview](../README.md)** | **[DFS →](../depth-first-search/Notes.md)**
