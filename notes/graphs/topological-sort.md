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
- [x] [Sequence Reconstruction](https://www.lintcode.com/problem/605/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Alien Dictionary](https://www.lintcode.com/problem/892/) - Hard ⭐ **IMPORTANT** ⭐

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

### Sequence Reconstruction ⭐ **IMPORTANT** ⭐

**Problem**: [Sequence Reconstruction](https://www.lintcode.com/problem/605/) - Medium

**Why Important**: Checks uniqueness of topological ordering — the key trick of checking `queue.size() > 1` is non-obvious and frequently tested

**Approach**:
1. Build directed graph from consecutive pairs in each sequence (`seqs[i][j] → seqs[i][j+1]`)
2. Calculate in-degrees for all nodes
3. Run Kahn's algorithm (BFS topological sort)
4. At each step, if queue has **more than 1 node**, return false (multiple valid orderings exist)
5. Verify result matches `org` exactly

**Complexity**: O(V + E) time, O(V + E) space

**Solution**:

```java
public boolean sequenceReconstruction(int[] org, int[][] seqs) {
    int n = org.length;
    List<Integer>[] adj = new ArrayList[n + 1];
    for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

    int[] inDegree = new int[n + 1];
    boolean[] inSeq = new boolean[n + 1];
    int elementCount = 0;
    boolean hasElements = false;

    for (int[] seq : seqs) {
        if (seq.length > 0) hasElements = true;
        for (int i = 0; i < seq.length; i++) {
            int u = seq[i];
            if (u < 1 || u > n) return false;
            if (!inSeq[u]) { inSeq[u] = true; elementCount++; }
            if (i == seq.length - 1) break;
            int v = seq[i + 1];
            if (v < 1 || v > n) return false;
            if (!inSeq[v]) { inSeq[v] = true; elementCount++; }
            adj[u].add(v);
            inDegree[v]++;
        }
    }

    if (n > 0 && !hasElements) return false;
    if (elementCount != n) return false;

    Deque<Integer> queue = new ArrayDeque<>();
    for (int i = 1; i <= n; i++)
        if (inDegree[i] == 0) queue.offerLast(i);

    int idx = 0;
    while (!queue.isEmpty()) {
        if (queue.size() != 1) return false;  // ambiguous ordering
        int node = queue.pollLast();
        if (idx >= n || org[idx++] != node) return false;
        for (int neighbor : adj[node]) {
            if (--inDegree[neighbor] == 0) queue.offerLast(neighbor);
        }
    }

    for (int i = 1; i <= n; i++)
        if (inDegree[i] > 0) return false;

    return true;
}
```

**Key Points**:
- **Unique ordering check**: `queue.size() != 1` at any step → multiple valid orderings exist → not unique
- `inSeq[]` + `elementCount` validates all elements 1..n appear in seqs — guards against missing nodes
- Out-of-range check (`u < 1 || u > n`) handles invalid sequence values early
- Cycle detection at end: any `inDegree[i] > 0` means a node was never processed
- 1-indexed arrays (size `n+1`) match the problem's 1-to-n numbering

---

### Alien Dictionary ⭐ **IMPORTANT** ⭐

**Problem**: [Alien Dictionary](https://www.lintcode.com/problem/892/) - Hard

**Why Important**: Classic problem that combines string comparison with topological sort — the graph-building step (extracting ordering from word pairs) is the non-obvious part

**Approach**:
1. Initialize all unique characters in the graph
2. Compare adjacent words pairwise — find the first differing character: `words[i][j] → words[i+1][j]`
3. If `words[i]` is a prefix of `words[i+1]` but longer → invalid input, return `""`
4. Run Kahn's algorithm on the character graph
5. If not all characters processed → cycle (invalid), return `""`

**Complexity**: O(C) time and space where C = total length of all words

**Solution**:

```java
public String alienOrder(String[] words) {
    List<Integer>[] adj = new ArrayList[26];
    for (int i = 0; i < 26; i++) adj[i] = new ArrayList<>();

    int[] inDegree = new int[26];
    boolean[] exists = new boolean[26];

    for (String word : words)
        for (char c : word.toCharArray())
            exists[c - 'a'] = true;

    for (int i = 1; i < words.length; i++) {
        if (!addEdge(words[i - 1], words[i], adj, inDegree)) return "";
    }

    Queue<Integer> queue = new PriorityQueue<>();
    for (int i = 0; i < 26; i++)
        if (exists[i] && inDegree[i] == 0) queue.add(i);

    StringBuilder sb = new StringBuilder();
    while (!queue.isEmpty()) {
        int node = queue.poll();
        sb.append((char) ('a' + node));
        for (int neighbor : adj[node]) {
            if (--inDegree[neighbor] == 0) queue.add(neighbor);
        }
    }

    for (int i = 0; i < 26; i++)
        if (inDegree[i] > 0) return "";

    return sb.toString();
}

private boolean addEdge(String w1, String w2, List<Integer>[] adj, int[] inDegree) {
    if (w1.length() > w2.length() && w1.startsWith(w2)) return false;
    for (int i = 0; i < Math.min(w1.length(), w2.length()); i++) {
        if (w1.charAt(i) != w2.charAt(i)) {
            adj[w1.charAt(i) - 'a'].add(w2.charAt(i) - 'a');
            inDegree[w2.charAt(i) - 'a']++;
            break;
        }
    }
    return true;
}
```

**Key Points**:
- Uses **index arrays (size 26)** instead of HashMaps — cleaner and avoids boxing overhead
- `exists[]` tracks which chars actually appear — needed because `inDegree[i] == 0` alone can't distinguish "no deps" from "not in alphabet"
- `PriorityQueue` produces lexicographically smallest valid ordering
- Cycle detection at end: any `inDegree[i] > 0` means node was never processed (stuck in cycle)
- Invalid input: `w1` longer than `w2` but `w1.startsWith(w2)` (e.g., `["abc", "ab"]`)

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
