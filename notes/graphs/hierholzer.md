# Hierholzer's Algorithm

> **[← Back to Graphs Overview](Notes.md)**

---

## When to Use

✅ **Find a path/circuit that visits every edge exactly once**

### Keywords
- "visit every edge exactly once"
- "Eulerian path / circuit"
- "reconstruct itinerary"
- "valid arrangement"
- "use all tickets / edges"

### Examples
- Reconstruct flight itinerary using all tickets
- Find valid arrangement of pairs
- Drawing a figure without lifting the pen

---

## Core Concept

**Eulerian Circuit**: A path that visits every edge exactly once and returns to the start.

**Eulerian Path**: A path that visits every edge exactly once (may not return to start).

### Visual Guide

**Eulerian Circuit** — closed loop, all edges used, returns to start:

```
    JFK
   ↗   ↘
LAX ← SFO

Edges: JFK→SFO, SFO→LAX, LAX→JFK
All nodes: in-degree == out-degree == 1

Circuit: JFK → SFO → LAX → JFK ✓  (returns to start)
```

**Eulerian Path** — open trail, all edges used, different start and end:

```
JFK → SFO → ATL → LAX

Edges: JFK→SFO, SFO→ATL, ATL→LAX
JFK: out=1, in=0  →  start  (out − in = 1)
LAX: out=0, in=1  →  end    (in − out = 1)

Path: JFK → SFO → ATL → LAX  (does NOT return to start)
```

**Key Insight**: Hierholzer's algorithm uses DFS with **post-order insertion** — add a node to the result only after all its outgoing edges have been explored. Reversing the post-order gives the correct path.

**Complexity**: O(E log E) time (log E for priority queue), O(E) space

---

## Eulerian Existence Conditions

| Graph Type | Eulerian Circuit | Eulerian Path |
|------------|-----------------|---------------|
| **Undirected** | All vertices have even degree | Exactly 0 or 2 vertices have odd degree |
| **Directed** | Every vertex: in-degree == out-degree | One vertex: out − in = 1 (start); one: in − out = 1 (end); rest equal |

---

## Pattern: DFS with Post-order (Hierholzer's)

**Use Case**: Find Eulerian path/circuit; optionally get lexicographically smallest path

**Algorithm**:
1. Build adjacency list (use `PriorityQueue` for lexicographic ordering)
2. Find the starting node (node with `out - in = 1`, or any node with edges)
3. DFS: while current node has unvisited edges, follow the smallest next edge
4. When stuck (no outgoing edges), add current node to **front** of result
5. Result is the Eulerian path in correct order

**Complexity**: O(E log E) time, O(E) space

### Template

```java
public List<String> hierholzer(Map<String, PriorityQueue<String>> graph, String start) {
    LinkedList<String> result = new LinkedList<>();
    Deque<String> stack = new ArrayDeque<>();
    stack.push(start);

    while (!stack.isEmpty()) {
        String curr = stack.peek();
        PriorityQueue<String> neighbors = graph.get(curr);
        if (neighbors != null && !neighbors.isEmpty()) {
            stack.push(neighbors.poll());  // Follow next edge
        } else {
            result.addFirst(stack.pop());  // Post-order: add to front when stuck
        }
    }

    return result;
}
```

### Visual Example

```
Tickets: JFK→ATL, JFK→SFO, ATL→JFK, ATL→SFO, SFO→ATL

Graph (PQ = sorted):
JFK → [ATL, SFO]
ATL → [JFK, SFO]
SFO → [ATL]

DFS trace (stack | action):
[JFK]                → follow JFK→ATL
[JFK, ATL]           → follow ATL→JFK
[JFK, ATL, JFK]      → follow JFK→SFO
[JFK, ATL, JFK, SFO] → follow SFO→ATL
[JFK, ATL, JFK, SFO, ATL] → ATL→SFO
[JFK, ATL, JFK, SFO, ATL, SFO] → SFO stuck → addFirst(SFO) result=[SFO]
[JFK, ATL, JFK, SFO, ATL]      → ATL stuck → addFirst(ATL) result=[ATL,SFO]
[JFK, ATL, JFK, SFO]           → SFO stuck → addFirst(SFO) result=[SFO,ATL,SFO]
[JFK, ATL, JFK]                → JFK stuck → addFirst(JFK) result=[JFK,SFO,ATL,SFO]
[JFK, ATL]                     → ATL stuck → addFirst(ATL) result=[ATL,JFK,SFO,ATL,SFO]
[JFK]                          → JFK stuck → addFirst(JFK) result=[JFK,ATL,JFK,SFO,ATL,SFO] ✓
```

---

## Common Mistakes

### ❌ Adding Node to Result Too Early

```java
// WRONG - adds node before exploring all its edges (gives wrong order)
result.addFirst(curr);
stack.push(neighbors.poll());

// CORRECT - add only when stuck (post-order)
if (neighbors != null && !neighbors.isEmpty()) {
    stack.push(neighbors.poll());
} else {
    result.addFirst(stack.pop());
}
```

### ❌ Using List Instead of PriorityQueue for Neighbors

```java
// WRONG - doesn't guarantee lexicographic order
graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);

// CORRECT - PriorityQueue gives smallest neighbor first
graph.computeIfAbsent(from, k -> new PriorityQueue<>()).add(to);
```

### ❌ Wrong Starting Node for Eulerian Path

```java
// WRONG - starting from an arbitrary node may miss valid path start
String start = graph.keySet().iterator().next();

// CORRECT - find the node with out-degree - in-degree == 1
String start = "default";
for (String node : allNodes) {
    if (outDegree[node] - inDegree[node] == 1) { start = node; break; }
}
```

---

## Problems

- [x] [Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Valid Arrangement of Pairs](https://leetcode.com/problems/valid-arrangement-of-pairs/) - Hard ⭐ **IMPORTANT** ⭐

### Reconstruct Itinerary ⭐ **IMPORTANT** ⭐

**Problem**: [Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/) - Medium

**Why Important**: Classic Hierholzer's pattern — the post-order insertion trick is non-obvious, frequently asked in FAANG interviews

**Approach**:
1. Build adjacency list with `PriorityQueue` (lexicographic order)
2. Start DFS from `"JFK"`
3. When no more outgoing edges from current node, `addFirst` to result
4. Result is valid itinerary using all tickets

**Complexity**: O(E log E) time, O(E) space

**Solution**:

```java
public List<String> findItinerary(List<List<String>> tickets) {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    for (List<String> ticket : tickets)
        graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));

    LinkedList<String> result = new LinkedList<>();
    Deque<String> stack = new ArrayDeque<>();
    stack.push("JFK");

    while (!stack.isEmpty()) {
        String curr = stack.peek();
        PriorityQueue<String> neighbors = graph.get(curr);
        if (neighbors != null && !neighbors.isEmpty()) {
            stack.push(neighbors.poll());
        } else {
            result.addFirst(stack.pop());
        }
    }

    return result;
}
```

**Key Points**:
- `PriorityQueue` for each node's neighbors ensures lexicographically smallest path
- `addFirst` (not `addLast`) because post-order traversal produces reversed path
- No explicit cycle detection needed — Hierholzer's naturally handles cycles
- Problem guarantees a valid Eulerian path exists (no need to check existence)

### Valid Arrangement of Pairs ⭐ **IMPORTANT** ⭐

**Problem**: [Valid Arrangement of Pairs](https://leetcode.com/problems/valid-arrangement-of-pairs/) - Hard

**Why Important**: Generalizes Reconstruct Itinerary — start node is not given, must be derived from degree conditions; result is pairs not a path; no lexicographic requirement so O(E) instead of O(E log E)

**Approach**:
1. Build adjacency list with `ArrayDeque` (no lexicographic ordering needed)
2. Track `inDegree` and `outDegree` per node
3. Find start: node with `out − in == 1`; if none exists, any node with edges (Eulerian circuit)
4. Run Hierholzer's algorithm — same post-order DFS pattern
5. Convert the `n+1` node path into `n` result pairs (consecutive node pairs)

**Complexity**: O(E) time, O(E) space

**Solution**:

```java
public int[][] validArrangement(int[][] pairs) {
    Map<Integer, Deque<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> inDegree = new HashMap<>();
    Map<Integer, Integer> outDegree = new HashMap<>();

    for (int[] pair : pairs) {
        int u = pair[0], v = pair[1];
        graph.computeIfAbsent(u, k -> new ArrayDeque<>()).add(v);
        outDegree.merge(u, 1, Integer::sum);
        inDegree.merge(v, 1, Integer::sum);
    }

    // Find start: out - in == 1; else any node (Eulerian circuit case)
    int start = graph.keySet().iterator().next();
    for (int node : outDegree.keySet()) {
        if (outDegree.getOrDefault(node, 0) - inDegree.getOrDefault(node, 0) == 1) {
            start = node;
            break;
        }
    }

    LinkedList<Integer> path = new LinkedList<>();
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(start);

    while (!stack.isEmpty()) {
        int curr = stack.peek();
        Deque<Integer> neighbors = graph.get(curr);
        if (neighbors != null && !neighbors.isEmpty()) {
            stack.push(neighbors.poll());
        } else {
            path.addFirst(stack.pop());
        }
    }

    // path has n+1 nodes → n pairs
    List<Integer> pathList = new ArrayList<>(path);
    int n = pairs.length;
    int[][] result = new int[n][2];
    for (int i = 0; i < n; i++) {
        result[i] = new int[]{pathList.get(i), pathList.get(i + 1)};
    }
    return result;
}
```

**Key Points**:
- **Start node is not fixed**: find node where `out − in == 1`; if none, it's an Eulerian circuit and any node with edges works
- **`ArrayDeque` instead of `PriorityQueue`**: "any valid arrangement" → no ordering constraint → O(E) not O(E log E)
- **Path → pairs**: Hierholzer gives `n+1` nodes; consecutive pairs `(path[i], path[i+1])` form the `n` result edges
- Both `inDegree` and `outDegree` maps are needed — can't derive one from the other with a single map

---

## Key Takeaways

1. **Hierholzer's** finds Eulerian path/circuit via DFS with post-order insertion
2. **Post-order trick**: add node to result **only when stuck** (no more outgoing edges)
3. **`addFirst`** or reverse at end — post-order gives reversed path
4. **`PriorityQueue`** for lexicographically smallest valid path
5. **Eulerian path start**: node with `out-degree − in-degree = 1` in directed graphs
6. O(E log E) time due to priority queue; O(E log E) is unavoidable if lexicographic order required

---

> **[← Back to Graphs Overview](Notes.md)**
