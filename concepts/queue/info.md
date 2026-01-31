# Java Queue Concepts

## 2. What is Queue?

**Type**: FIFO (First In, First Out) interface
**Package**: `java.util.Queue`
**Main Implementation**: ArrayDeque (recommended)

**Key Characteristics**:
- **FIFO ordering** - first added is first removed
- **Allows duplicates** ✅
- **Null support** - depends on implementation
- **Not thread-safe** - use ConcurrentLinkedQueue for thread-safe

---

## 3. Time & Space Complexity

| Operation | ArrayDeque | LinkedList | Notes |
|-----------|------------|------------|-------|
| **offer (add)** | O(1) amortized | O(1) | Add to tail |
| **poll (remove)** | O(1) | O(1) | Remove from head |
| **peek (view)** | O(1) | O(1) | View head |
| **size** | O(1) | O(1) | Get size |
| **contains** | O(n) | O(n) | Linear search |
| **remove(Object)** | O(n) | O(n) | Specific element |

**Space**: O(n)

**Recommendation**: Use **ArrayDeque** for better performance

---

## 4. Common Operations & Methods

### Queue Interface Methods

| Operation | Throws Exception | Returns Special Value | Description |
|-----------|------------------|----------------------|-------------|
| **Insert** | `add(e)` | `offer(e)` ⭐ | Add to tail |
| **Remove** | `remove()` | `poll()` ⭐ | Remove from head |
| **Examine** | `element()` | `peek()` ⭐ | View head |

**Best Practice**: Use `offer()`, `poll()`, `peek()` - they return special values (false/null) instead of throwing exceptions.

### Common Queue Operations

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Add element** | `queue.offer(e)` | O(1) | Returns false if fails |
| **Remove head** | `queue.poll()` | O(1) | Returns null if empty |
| **View head** | `queue.peek()` | O(1) | Returns null if empty |
| **Get size** | `queue.size()` | O(1) | Number of elements |
| **Check empty** | `queue.isEmpty()` | O(1) | true if empty |
| **Clear all** | `queue.clear()` | O(n) | Remove all elements |

---

## 5. Core Characteristics/Creation

```java
// Recommended: ArrayDeque (fast, no nulls)
Queue<Integer> queue = new ArrayDeque<>();

// LinkedList (allows nulls, slower)
Queue<Integer> queue = new LinkedList<>();

// With initial capacity (ArrayDeque)
Queue<Integer> queue = new ArrayDeque<>(100);

// Add elements
queue.offer(1);         // Add to tail - returns true/false
queue.add(2);           // Add to tail - throws exception if fails

// View head
int head = queue.peek();    // Returns null if empty
int head = queue.element(); // Throws exception if empty

// Remove head
int val = queue.poll();     // Returns null if empty
int val = queue.remove();   // Throws exception if empty

// Size and empty check
int size = queue.size();
boolean empty = queue.isEmpty();

// Clear queue
queue.clear();
```

---

## 6. Comparison with Similar Structures

| Feature | ArrayDeque | LinkedList | PriorityQueue |
|---------|-----------|------------|---------------|
| **Implementation** | Circular array | Doubly linked list | Binary heap |
| **Order** | FIFO ⭐ | FIFO | Priority (not FIFO) |
| **Performance** | Fast ⭐ | Slower | Medium |
| **Memory** | Less overhead | More overhead | Medium |
| **Null elements** | ❌ Not allowed | ✅ Allowed | ❌ Not allowed |
| **Cache locality** | Good ⭐ | Poor | Medium |
| **Use case** | General queue | Need nulls | Priority-based |

**When to Use ArrayDeque**: Default choice (fastest, most efficient)

**When to Use LinkedList**: Need null elements or List interface

**When to Use PriorityQueue**: Need priority-based ordering (not FIFO)

---

## 7. Common Patterns & Use Cases



## 8. Common Gotchas & Best Practices

### 1. Null Elements with ArrayDeque

**❌ WRONG**:
```java
Queue<Integer> queue = new ArrayDeque<>();
queue.offer(null);  // NullPointerException
```

**✅ CORRECT**:
```java
// Use LinkedList if nulls needed (but avoid if possible)
Queue<Integer> queue = new LinkedList<>();
queue.offer(null);  // Works
```

---

### 2. Exception vs Special Value Methods

**❌ RISKY**:
```java
queue.remove();    // NoSuchElementException if empty
queue.element();   // NoSuchElementException if empty
```

**✅ SAFE**:
```java
Integer val = queue.poll();      // Returns null if empty
Integer head = queue.peek();     // Returns null if empty
```

---

### 3. PriorityQueue is NOT FIFO

**❌ WRONG**:
```java
Queue<Integer> queue = new PriorityQueue<>();
queue.offer(3);
queue.offer(1);
queue.offer(2);
queue.poll();  // Returns 1 (smallest), not 3 (first added)
```

**✅ CORRECT** for FIFO:
```java
Queue<Integer> queue = new ArrayDeque<>();
```

---

### 4. Using LinkedList Deque Methods

**❌ WRONG** (breaks queue abstraction):
```java
Queue<Integer> queue = new LinkedList<>();
((LinkedList<Integer>) queue).addFirst(1);  // Breaks FIFO
```

**✅ CORRECT**:
```java
Queue<Integer> queue = new ArrayDeque<>();
queue.offer(1);    // Use queue methods only
```

---

## 9. Interview Tips

### When to Use Queue
✅ BFS traversal (tree, graph)
✅ Level-order processing
✅ Task scheduling (FIFO order)
✅ Shortest path in unweighted graph
✅ Multi-source BFS problems

### When NOT to Use Queue
❌ Need LIFO order → Use Stack (Deque)
❌ Need priority-based → Use PriorityQueue
❌ Need random access → Use List

### Remember
- **ArrayDeque is fastest** - use by default
- **Use offer/poll/peek** instead of add/remove/element
- ArrayDeque **does NOT allow null**
- Queue is **FIFO**, Stack is **LIFO**
- **BFS uses Queue**, DFS uses Stack
- Always check `isEmpty()` before `poll()`

### Time Complexity Quick Check
- Enqueue (offer): O(1)
- Dequeue (poll): O(1)
- Peek: O(1)
- Contains/Remove specific: O(n)

---

## 10. Quick Reference

### Creation
```java
// Recommended
Queue<Integer> queue = new ArrayDeque<>();

// With capacity
Queue<Integer> queue = new ArrayDeque<>(100);

// LinkedList (if nulls needed)
Queue<Integer> queue = new LinkedList<>();
```

### Essential Operations
```java
queue.offer(x)      // Add to tail (preferred)
queue.poll()        // Remove from head (preferred)
queue.peek()        // View head (preferred)
queue.size()        // Get size
queue.isEmpty()     // Check if empty
queue.clear()       // Remove all
```

### BFS Template
```java
Queue<Node> queue = new ArrayDeque<>();
queue.offer(startNode);
Set<Node> visited = new HashSet<>();
visited.add(startNode);

while (!queue.isEmpty()) {
    Node node = queue.poll();
    process(node);

    for (Node neighbor : node.neighbors) {
        if (!visited.contains(neighbor)) {
            visited.add(neighbor);
            queue.offer(neighbor);
        }
    }
}
```

### Level-Order Template
```java
Queue<TreeNode> queue = new ArrayDeque<>();
queue.offer(root);

while (!queue.isEmpty()) {
    int size = queue.size();
    for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        // Process node
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
}
```

---

## 11. Key Insight

Use **ArrayDeque** for queue operations - it's faster, more memory efficient, and the standard choice! Remember: **Queue for BFS (FIFO)**, **Stack for DFS (LIFO)**!
