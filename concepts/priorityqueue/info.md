# Java PriorityQueue (Heap) Concepts

## 2. What is PriorityQueue?

**Type**: Min-heap by default (smallest element at top)
**Package**: `java.util.PriorityQueue`
**Implementation**: Binary heap using array

**Key Characteristics**:
- **Priority ordering** - natural order or custom comparator
- **Allows duplicates** ✅
- **Does NOT allow null** ❌
- **NOT thread-safe** - use PriorityBlockingQueue for thread-safe
- **Not sorted** - only top element is guaranteed min/max

---

## 3. Time & Space Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **offer/add** | O(log n) | Insert element |
| **poll/remove** | O(log n) | Remove top element |
| **peek/element** | O(1) | View top element |
| **remove(Object)** | O(n) | Remove specific element |
| **contains(Object)** | O(n) | Linear search |
| **size** | O(1) | Get size |

**Space**: O(n)

**Important**: Insertion and removal are O(log n), not O(1) like regular queues

---

## 4. Common Operations & Methods

### PriorityQueue Methods

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Add element** | `pq.offer(e)` | O(log n) | Returns false if fails (preferred) |
| **Add element** | `pq.add(e)` | O(log n) | Throws exception if fails |
| **Remove top** | `pq.poll()` | O(log n) | Returns null if empty (preferred) |
| **Remove top** | `pq.remove()` | O(log n) | Throws exception if empty |
| **View top** | `pq.peek()` | O(1) | Returns null if empty (preferred) |
| **View top** | `pq.element()` | O(1) | Throws exception if empty |
| **Get size** | `pq.size()` | O(1) | Number of elements |
| **Check empty** | `pq.isEmpty()` | O(1) | true if empty |
| **Clear all** | `pq.clear()` | O(n) | Remove all elements |

**Best Practice**: Use `offer()`, `poll()`, `peek()` - they return special values instead of throwing exceptions.

---

## 5. Core Characteristics/Creation

```java
// MIN-HEAP (default) - smallest at top
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(5);
minHeap.offer(2);
minHeap.offer(8);
minHeap.peek();  // 2 (smallest)

// MAX-HEAP - largest at top
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
maxHeap.offer(5);
maxHeap.offer(2);
maxHeap.offer(8);
maxHeap.peek();  // 8 (largest)

// Alternative max-heap syntax
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

// Custom comparator (avoid overflow)
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

// With initial capacity
PriorityQueue<Integer> pq = new PriorityQueue<>(100);

// Basic operations
pq.offer(10);        // Add element
int top = pq.peek(); // View top (null if empty)
int val = pq.poll(); // Remove top (null if empty)
int size = pq.size();
boolean empty = pq.isEmpty();
```

### Custom Comparators

```java
class Task {
    String name;
    int priority;
}

// Sort by priority (ascending)
PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> a.priority - b.priority);

// Sort by priority (descending)
PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> b.priority - a.priority);

// Sort arrays by first element
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

// Sort arrays by multiple criteria (first element, then second)
PriorityQueue<int[]> pq = new PriorityQueue<>(
    (a, b) -> {
        if (a[0] != b[0]) {
            return Integer.compare(a[0], b[0]);
        }
        return Integer.compare(a[1], b[1]);
    }
);

// Sort by absolute value
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Math.abs(a) - Math.abs(b));
```

---

## 6. Comparison with Similar Structures

| Feature | PriorityQueue | TreeSet | ArrayDeque |
|---------|---------------|---------|------------|
| **Order** | Priority (heap) | Sorted | FIFO/LIFO |
| **Duplicates** | ✅ Allowed | ❌ Not allowed | ✅ Allowed |
| **Top element** | O(1) | O(log n) | O(1) |
| **Add/Remove** | O(log n) | O(log n) | O(1) |
| **Null** | ❌ Not allowed | ❌ Not allowed | ❌ Not allowed |
| **Use case** | Priority-based | Sorted + unique | FIFO/LIFO |

**When to Use PriorityQueue**: Need priority-based ordering, Kth largest/smallest, top K elements

**When to Use TreeSet**: Need sorted order + no duplicates

**When to Use ArrayDeque**: Need FIFO/LIFO without priority

---

## 7. Common Patterns & Use Cases

### Pattern 1: Top K Elements

**Use Case**: Find K largest elements

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int num : nums) {
    minHeap.offer(num);
    if (minHeap.size() > k) {
        minHeap.poll();  // Remove smallest
    }
}
// minHeap contains K largest elements
```

**Complexity**: O(n log k)

**Key Insight**: For K largest, use **min-heap** of size K

---

### Pattern 2: Kth Largest/Smallest

**Kth Largest**:
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int num : nums) {
    minHeap.offer(num);
    if (minHeap.size() > k) minHeap.poll();
}
int kthLargest = minHeap.peek();
```

**Kth Smallest**:
```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
for (int num : nums) {
    maxHeap.offer(num);
    if (maxHeap.size() > k) maxHeap.poll();
}
int kthSmallest = maxHeap.peek();
```

**Complexity**: O(n log k)

---

### Pattern 3: Merge K Sorted Lists/Arrays

**Use Case**: Merge multiple sorted lists efficiently

```java
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
// Add {value, listIndex, elementIndex}
// Poll smallest, add next from same list
```

**Complexity**: O(n log k) where n = total elements, k = number of lists

---

### Pattern 4: Running Median (Two Heaps)

**Use Case**: Find median in stream of numbers

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Left half
PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Right half

// Keep maxHeap.size() >= minHeap.size()
// Median = maxHeap.peek() or average of both tops
```

**Complexity**: O(log n) per insertion

---

## 8. Common Gotchas & Best Practices

### 1. Not Fully Sorted

**❌ WRONG**:
```java
PriorityQueue<Integer> pq = new PriorityQueue<>(Arrays.asList(5, 2, 8, 1));
// Don't iterate expecting sorted order!
for (int x : pq) { /* NOT guaranteed sorted */ }
```

**✅ CORRECT**:
```java
// Poll elements to get sorted order
while (!pq.isEmpty()) {
    System.out.println(pq.poll());  // Sorted: 1, 2, 5, 8
}
```

---

### 2. Null Elements Not Allowed

**❌ WRONG**:
```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.offer(null);  // NullPointerException
```

**✅ CORRECT**:
```java
// Check before adding
if (value != null) {
    pq.offer(value);
}
```

---

### 3. Comparator Integer Overflow

**❌ WRONG**:
```java
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
// Can overflow if a and b have large different signs
```

**✅ CORRECT**:
```java
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
// Or use default: new PriorityQueue<>()
```

---

### 4. Heap Type Confusion

**Remember**:
- **K largest** → use **min-heap** of size K
- **K smallest** → use **max-heap** of size K

---

## 9. Interview Tips

### When to Use PriorityQueue
✅ Finding Kth largest/smallest element
✅ Top K elements
✅ Merge K sorted arrays/lists
✅ Running median (two heaps)
✅ Dijkstra's shortest path
✅ Task scheduling by priority

### When NOT to Use PriorityQueue
❌ Need fully sorted order → Use TreeSet or sort array
❌ Need FIFO without priority → Use Queue (ArrayDeque)
❌ Need fast lookup → Use HashSet

### Remember
- **Default is min-heap** (smallest at top)
- Use `Collections.reverseOrder()` for max-heap
- **Duplicates allowed** ✅
- **PriorityQueue is NOT sorted** - only top is guaranteed
- **Top K largest** → use **min-heap** of size K
- **Top K smallest** → use **max-heap** of size K
- Use `Integer.compare(a, b)` to avoid overflow
- Use `offer/poll/peek` over `add/remove/element`

### Time Complexity Quick Check
- Add/Remove: O(log n)
- Peek: O(1)
- Top K: O(n log k)
- Merge K lists: O(n log k)

---

## 10. Quick Reference

### Creation
```java
// Min-heap (default)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max-heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

// Custom comparator (avoid overflow)
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

// With capacity
PriorityQueue<Integer> pq = new PriorityQueue<>(100);
```

### Essential Operations
```java
pq.offer(x)      // Add element (preferred)
pq.poll()        // Remove top (null if empty)
pq.peek()        // View top (null if empty)
pq.size()        // Get size
pq.isEmpty()     // Check if empty
pq.clear()       // Remove all
```

### Top K Largest Template
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int num : nums) {
    minHeap.offer(num);
    if (minHeap.size() > k) {
        minHeap.poll();
    }
}
// Result: minHeap contains K largest
```

### Top K Smallest Template
```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
for (int num : nums) {
    maxHeap.offer(num);
    if (maxHeap.size() > k) {
        maxHeap.poll();
    }
}
// Result: maxHeap contains K smallest
```

### Running Median Template
```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Maintain: maxHeap.size() >= minHeap.size()
// Median = maxHeap.peek() or (maxHeap.peek() + minHeap.peek()) / 2.0
```

---

## 11. Key Insight

For **K largest**, use **min-heap** of size K! For **K smallest**, use **max-heap** of size K! Remember: PriorityQueue is a **heap**, not a sorted list - only the top element is guaranteed to be min/max!
