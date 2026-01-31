# Java Queue Concepts

## What is Queue?

**Type**: FIFO (First In, First Out) data structure
**Interface**: `java.util.Queue`
**Main Implementations**: LinkedList, ArrayDeque, PriorityQueue

**Key Characteristics**:
- **FIFO ordering** - first element added is first removed
- **Allows duplicates** ✅
- **Allows null** - depends on implementation (LinkedList: yes, ArrayDeque: no)
- **Not thread-safe** - use `ConcurrentLinkedQueue` for thread-safe operations

---

## Queue Interface Methods

### Core Operations

| Method | Throws Exception | Returns Special Value | Description |
|--------|------------------|----------------------|-------------|
| **add(e)** | Yes | **offer(e)** - Returns false | Add to tail |
| **remove()** | Yes | **poll()** - Returns null | Remove from head |
| **element()** | Yes | **peek()** - Returns null | View head |

**Best Practice**: Use `offer()`, `poll()`, `peek()` - they return special values instead of throwing exceptions.

---

## Common Implementations

### 1. LinkedList (as Queue)

**Implementation**: Doubly linked list
**Use Case**: Standard FIFO queue

```java
Queue<Integer> queue = new LinkedList<>();

queue.offer(1);    // Add to tail
queue.offer(2);
queue.offer(3);

queue.peek();      // 1 (view head, doesn't remove)
queue.poll();      // 1 (remove and return head)
queue.poll();      // 2
```

**Time Complexity**:
- Enqueue (offer): O(1)
- Dequeue (poll): O(1)
- Peek: O(1)
- Size: O(1)

**Space**: O(n)

**Characteristics**:
- Allows null elements
- Slower than ArrayDeque (pointer overhead)
- Use when you need true queue behavior with nulls

---

### 2. ArrayDeque (as Queue) ⭐ **RECOMMENDED**

**Implementation**: Resizable circular array
**Use Case**: High-performance FIFO queue

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

queue.peek();      // 10
queue.poll();      // 10
```

**Time Complexity**:
- Enqueue (offer): O(1) amortized
- Dequeue (poll): O(1)
- Peek: O(1)
- Size: O(1)

**Space**: O(n)

**Characteristics**:
- **Does NOT allow null** elements
- **Faster than LinkedList** (better cache locality)
- Grows dynamically (doubles capacity when full)
- **Preferred choice** for queue operations

---

### 3. PriorityQueue (Priority-based)

**Implementation**: Min-heap (binary heap)
**Use Case**: Elements ordered by priority, not FIFO

```java
Queue<Integer> pq = new PriorityQueue<>();

pq.offer(30);
pq.offer(10);
pq.offer(20);

pq.peek();      // 10 (smallest, not first added)
pq.poll();      // 10
pq.poll();      // 20
```

**Time Complexity**:
- Enqueue (offer): O(log n)
- Dequeue (poll): O(log n)
- Peek: O(1)

**Note**: NOT a true FIFO queue - elements ordered by priority!

---

## Basic Queue Operations

```java
// Create queue (use ArrayDeque for best performance)
Queue<Integer> queue = new ArrayDeque<>();

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

## Common Patterns & Use Cases

### Pattern 1: Level-Order Traversal (BFS)

**Use Case**: Tree/graph traversal by levels

```java
Queue<TreeNode> queue = new ArrayDeque<>();
queue.offer(root);

while (!queue.isEmpty()) {
    TreeNode node = queue.poll();
    process(node);

    if (node.left != null) queue.offer(node.left);
    if (node.right != null) queue.offer(node.right);
}
```

**Complexity**: O(n) time, O(w) space where w = max width

---

### Pattern 2: Sliding Window Maximum/Minimum

**Use Case**: Track max/min in sliding window

```java
Deque<Integer> deque = new ArrayDeque<>();
// Use deque to maintain indices of elements in decreasing order
```

---

### Pattern 3: Process Tasks in Order

**Use Case**: Task scheduling, request processing

```java
Queue<Task> taskQueue = new ArrayDeque<>();

// Add tasks
taskQueue.offer(new Task("task1"));
taskQueue.offer(new Task("task2"));

// Process in FIFO order
while (!taskQueue.isEmpty()) {
    Task task = taskQueue.poll();
    task.execute();
}
```

---

### Pattern 4: Multi-source BFS

**Use Case**: Multiple starting points (rotting oranges, fire spread)

```java
Queue<int[]> queue = new ArrayDeque<>();

// Add all sources
for (Source src : sources) {
    queue.offer(new int[]{src.x, src.y});
}

// BFS from all sources simultaneously
while (!queue.isEmpty()) {
    int[] pos = queue.poll();
    // Process and add neighbors
}
```

---

## LinkedList vs ArrayDeque as Queue

| Feature | LinkedList | ArrayDeque |
|---------|-----------|------------|
| **Implementation** | Doubly linked list | Circular array |
| **Performance** | Slower | **Faster** ⭐ |
| **Memory** | More overhead | Less overhead |
| **Null elements** | Allowed | **Not allowed** |
| **Cache locality** | Poor | **Good** |
| **Resizing** | No need | Doubles when full |
| **Preferred** | Rarely | **Yes** ⭐ |

**Recommendation**: Use **ArrayDeque** unless you need null elements or List operations.

---

## Common Queue Problems

### BFS Problems
- Binary Tree Level Order Traversal
- Rotting Oranges
- Word Ladder
- Shortest Path in Binary Matrix

### Design Problems
- Design Circular Queue
- Implement Queue using Stacks
- Design Hit Counter

### Sliding Window
- Sliding Window Maximum (with Deque)

---

## Important Gotchas

### 1. Null Elements

**❌ WRONG** (ArrayDeque):
```java
Queue<Integer> queue = new ArrayDeque<>();
queue.offer(null);  // NullPointerException
```

**✅ CORRECT** (LinkedList if needed):
```java
Queue<Integer> queue = new LinkedList<>();
queue.offer(null);  // Works, but avoid if possible
```

### 2. Exception vs Special Value

**❌ RISKY**:
```java
queue.remove();    // NoSuchElementException if empty
queue.element();   // NoSuchElementException if empty
```

**✅ SAFE**:
```java
queue.poll();      // Returns null if empty
queue.peek();      // Returns null if empty
```

### 3. Using LinkedList Methods

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

### 4. PriorityQueue is NOT FIFO

**❌ WRONG**:
```java
Queue<Integer> queue = new PriorityQueue<>();
queue.offer(3);
queue.offer(1);
queue.offer(2);
queue.poll();  // Returns 1, not 3 (not FIFO!)
```

**✅ CORRECT** (for FIFO):
```java
Queue<Integer> queue = new ArrayDeque<>();
```

---

## Queue vs Stack

| Feature | Queue | Stack |
|---------|-------|-------|
| **Order** | FIFO (First In, First Out) | LIFO (Last In, First Out) |
| **Add** | offer() - add to tail | push() - add to top |
| **Remove** | poll() - remove from head | pop() - remove from top |
| **View** | peek() - view head | peek() - view top |
| **Use Cases** | BFS, task scheduling | DFS, undo operations |

---

## Time Complexity Summary

### ArrayDeque (Recommended)
```
offer(e)      - O(1) amortized
poll()        - O(1)
peek()        - O(1)
size()        - O(1)
isEmpty()     - O(1)
contains(e)   - O(n)
remove(e)     - O(n)
```

### LinkedList
```
offer(e)      - O(1)
poll()        - O(1)
peek()        - O(1)
size()        - O(1)
contains(e)   - O(n)
remove(e)     - O(n)
```

---

## Quick Reference

### Creation
```java
// Recommended: ArrayDeque
Queue<Integer> queue = new ArrayDeque<>();

// LinkedList (if nulls needed)
Queue<Integer> queue = new LinkedList<>();

// With initial capacity (ArrayDeque)
Queue<Integer> queue = new ArrayDeque<>(100);
```

### Essential Operations
```java
queue.offer(x)      // Add to tail (preferred)
queue.poll()        // Remove from head (preferred)
queue.peek()        // View head (preferred)
queue.size()        // Get size
queue.isEmpty()     // Check if empty
queue.clear()       // Remove all elements
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

---

## Interview Tips

### When to Use Queue
✅ BFS traversal (level-order)
✅ Task scheduling (FIFO order)
✅ Shortest path in unweighted graph
✅ Process elements in insertion order
✅ Multi-source BFS problems

### Remember
- **ArrayDeque is faster** than LinkedList for queue operations
- **Use offer/poll/peek** instead of add/remove/element
- ArrayDeque **does NOT allow null** elements
- Queue is **FIFO**, Stack is **LIFO**
- For priority-based ordering, use PriorityQueue (not true FIFO)

### Common Mistakes
- Using LinkedList when ArrayDeque is better
- Forgetting null check when using poll()/peek()
- Using PriorityQueue expecting FIFO order
- Not checking isEmpty() before poll()

---

**Key Insight**: ArrayDeque is the go-to implementation for standard queue operations due to better performance and memory efficiency!
