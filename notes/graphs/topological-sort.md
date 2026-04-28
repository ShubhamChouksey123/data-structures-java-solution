# Topological Sort

> **[← Back to Graphs Overview](Notes.md)**

---

## When to Use

✅ **Order tasks/nodes respecting dependencies in a Directed Acyclic Graph (DAG)**

### Keywords
- "prerequisites", "dependencies"
- "order", "sequence"
- "course schedule"
- "task ordering"
- "build order"

### Examples
- Course prerequisites (Course Schedule)
- Task scheduling with dependencies
- Build systems (compile order)
- Package dependency resolution

---

## Core Concept

Topological sort produces a linear ordering of vertices where for every directed edge (u → v), vertex u comes before v in the ordering.

**Key Insight**: Only possible for Directed Acyclic Graphs (DAGs). If cycle exists, no valid ordering.

**Complexity**: O(V + E) time, O(V) space

---

## Pattern: Kahn's Algorithm (BFS-based)

**Use Case**: Find topological ordering using in-degrees and BFS

**Algorithm**:
1. Calculate in-degree for all nodes (number of incoming edges)
2. Add all nodes with in-degree 0 to queue (no dependencies)
3. While queue not empty:
   - Remove node from queue, add to result
   - Decrease in-degree of all neighbors
   - Add neighbors with in-degree 0 to queue
4. If all nodes processed → valid ordering, else cycle exists

**Complexity**: O(V + E) time, O(V) space

**Visual Guide**: [GeeksforGeeks Interactive Visualization](https://www.geeksforgeeks.org/dsa/topological-sorting-indegree-based-solution/)

### Template

```java
public int[] topologicalSort(int n, int[][] edges) {
    // Build adjacency list and calculate in-degrees
    List<Integer>[] graph = new ArrayList[n];
    int[] inDegree = new int[n];

    for (int i = 0; i < n; i++) {
        graph[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
        int from = edge[0], to = edge[1];
        graph[from].add(to);
        inDegree[to]++;
    }

    // Add all nodes with in-degree 0
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < n; i++) {
        if (inDegree[i] == 0) {
            queue.offer(i);
        }
    }

    // Process nodes
    int[] result = new int[n];
    int idx = 0;

    while (!queue.isEmpty()) {
        int node = queue.poll();
        result[idx++] = node;

        // Decrease in-degrees of neighbors
        for (int neighbor : graph[node]) {
            inDegree[neighbor]--;
            if (inDegree[neighbor] == 0) {
                queue.offer(neighbor);
            }
        }
    }

    // Check if all nodes processed (no cycle)
    return idx == n ? result : new int[0];
}
```

### Visual Example

```
Graph (Prerequisites):
0 → 1 → 3
↓   ↓
2 → 4

In-degrees: [0, 1, 1, 1, 2]

Initial Queue: [0] (only node with in-degree 0)

Step 1: Process 0
  - Decrease: inDegree[1] = 0, inDegree[2] = 0
  - Queue: [1, 2]
  - Result: [0]

Step 2: Process 1
  - Decrease: inDegree[3] = 0, inDegree[4] = 1
  - Queue: [2, 3]
  - Result: [0, 1]

Step 3: Process 2
  - Decrease: inDegree[4] = 0
  - Queue: [3, 4]
  - Result: [0, 1, 2]

Step 4: Process 3
  - No neighbors
  - Queue: [4]
  - Result: [0, 1, 2, 3]

Step 5: Process 4
  - Queue: []
  - Result: [0, 1, 2, 3, 4] ✓

Valid topological order!
```

---

## Alternative: DFS-based Topological Sort

**Use Case**: Find topological ordering using DFS and post-order traversal

**Algorithm**:
1. Maintain visited set and recursion stack (for cycle detection)
2. For each unvisited node, run DFS
3. Add node to result **after** visiting all neighbors (post-order)
4. Reverse result at the end

**Complexity**: O(V + E) time, O(V) space

**References**:
- [Video Tutorial: DFS-based Topological Sort](https://www.youtube.com/watch?v=7J3GadLzydI)
- [GeeksforGeeks: Topological Sort using DFS](https://www.geeksforgeeks.org/dsa/topological-sort-using-dfs/)

### Template

```java
public int[] topologicalSortDFS(int n, int[][] edges) {
    // Build adjacency list
    List<Integer>[] graph = new ArrayList[n];
    for (int i = 0; i < n; i++) {
        graph[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
        graph[edge[0]].add(edge[1]);
    }

    boolean[] visited = new boolean[n];
    boolean[] recStack = new boolean[n];
    Stack<Integer> stack = new Stack<>();

    // DFS from each unvisited node
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            if (hasCycleDFS(i, graph, visited, recStack, stack)) {
                return new int[0];  // Cycle detected
            }
        }
    }

    // Build result (reverse of post-order)
    int[] result = new int[n];
    for (int i = 0; i < n; i++) {
        result[i] = stack.pop();
    }
    return result;
}

private boolean hasCycleDFS(int node, List<Integer>[] graph,
                            boolean[] visited, boolean[] recStack,
                            Stack<Integer> stack) {
    visited[node] = true;
    recStack[node] = true;

    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            if (hasCycleDFS(neighbor, graph, visited, recStack, stack)) {
                return true;
            }
        } else if (recStack[neighbor]) {
            return true;  // Cycle detected
        }
    }

    recStack[node] = false;
    stack.push(node);  // Post-order
    return false;
}
```

---

## Common Mistakes

### ❌ Not Checking for Cycles

```java
// WRONG - doesn't detect cycles
while (!queue.isEmpty()) {
    int node = queue.poll();
    result[idx++] = node;
    // Process...
}
return result;

// CORRECT - verify all nodes processed
int processed = 0;
while (!queue.isEmpty()) {
    int node = queue.poll();
    result[processed++] = node;
    // Process...
}
// If cycle exists, some nodes won't be processed
return processed == n ? result : new int[0];
```

### ❌ Wrong Edge Direction

```java
// WRONG - reversed edge direction
for (int[] prereq : prerequisites) {
    int course = prereq[0], prerequisite = prereq[1];
    graph[course].add(prerequisite);  // Wrong!
    inDegree[prerequisite]++;
}

// CORRECT - prerequisite → course
for (int[] prereq : prerequisites) {
    int course = prereq[0], prerequisite = prereq[1];
    graph[prerequisite].add(course);  // prerequisite must come first
    inDegree[course]++;
}
```

### ❌ Forgetting to Initialize Graph

```java
// WRONG - NullPointerException
List<Integer>[] graph = new ArrayList[n];
// Missing initialization
graph[0].add(1);  // NPE!

// CORRECT
List<Integer>[] graph = new ArrayList[n];
for (int i = 0; i < n; i++) {
    graph[i] = new ArrayList<>();
}
```

---

## Problems

- [x] [Course Schedule](https://leetcode.com/problems/course-schedule/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) - Medium
- [x] [Strange Printer II](https://leetcode.com/problems/strange-printer-ii/) - Hard
- [x] [Sequence Reconstruction](https://leetcode.com/problems/sequence-reconstruction/) - Medium
- [x] [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) - Hard

### Course Schedule ⭐ **IMPORTANT** ⭐

**Problem**: [Course Schedule](https://leetcode.com/problems/course-schedule/) - Medium

**Why Important**: Classic topological sort problem, fundamental pattern for dependency resolution, frequently asked in interviews

**Approach**:
1. Build adjacency list: prerequisite → course
2. Calculate in-degrees (number of prerequisites per course)
3. Use Kahn's algorithm with BFS
4. Start with courses having no prerequisites (in-degree 0)
5. Process courses and decrease neighbors' in-degrees
6. If all courses completed, return true (no cycle)

**Complexity**: O(V + E) time, O(V) space

**Solution**:

```java
public boolean canFinish(int numCourses, int[][] prerequisites) {
    // Build graph and calculate in-degrees
    List<Integer>[] graph = new ArrayList[numCourses];
    int[] inDegree = new int[numCourses];

    for (int i = 0; i < numCourses; i++) {
        graph[i] = new ArrayList<>();
    }

    for (int[] prereq : prerequisites) {
        int course = prereq[0];
        int prerequisite = prereq[1];
        graph[prerequisite].add(course);  // prerequisite → course
        inDegree[course]++;
    }

    // Add all courses with no prerequisites
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
        if (inDegree[i] == 0) {
            queue.offer(i);
        }
    }

    // Process courses
    int completed = 0;

    while (!queue.isEmpty()) {
        int course = queue.poll();
        completed++;

        // Decrease in-degrees of dependent courses
        for (int nextCourse : graph[course]) {
            inDegree[nextCourse]--;
            if (inDegree[nextCourse] == 0) {
                queue.offer(nextCourse);
            }
        }
    }

    // All courses completed if no cycle exists
    return completed == numCourses;
}
```

**Key Points**:
- **Edge direction matters**: prerequisite → course (not reverse)
- In-degree = number of prerequisites remaining for a course
- If `completed < numCourses`, cycle exists (impossible to finish all courses)
- Course Schedule II asks for the ordering: return `result` array instead of boolean
- BFS approach preferred over DFS for cleaner cycle detection and easier to understand

---

## Key Takeaways

1. **Topological sort** works only on Directed Acyclic Graphs (DAGs)
2. **Kahn's algorithm** uses BFS with in-degrees: O(V + E) time
3. **In-degree** represents number of dependencies/prerequisites
4. **Cycle detection**: If not all nodes processed, cycle exists
5. **Edge direction**: dependency → dependent (prerequisite → course)
6. **DFS alternative**: Post-order traversal + reverse, but requires cycle detection
7. **Applications**: Course scheduling, build systems, task ordering

---

> **[← Back to Graphs Overview](Notes.md)** | **[Union Find →](union-find.md)**
