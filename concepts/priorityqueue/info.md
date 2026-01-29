# Java PriorityQueue (Heap) Concepts

## What is PriorityQueue?

**Type**: Min-heap by default (smallest element at top)
**Implementation**: Binary heap using array
**Package**: `java.util.PriorityQueue`

**Key Characteristics**:
- Elements ordered by priority (natural order or custom comparator)
- **NOT thread-safe** (use `PriorityBlockingQueue` for thread-safe)
- Does NOT allow `null` elements
- Not sorted - only guarantees top element is min/max

---

## Time Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **add(e) / offer(e)** | O(log n) | Insert element |
| **poll() / remove()** | O(log n) | Remove top element |
| **peek() / element()** | O(1) | View top element |
| **remove(Object)** | O(n) | Remove specific element |
| **contains(Object)** | O(n) | Linear search |
| **size()** | O(1) | Get size |

**Space**: O(n)

---

## Basic Operations

```java
// Create min-heap (default)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Create max-heap (reverse order)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// Add elements
pq.add(5);           // Throws exception if fails
pq.offer(10);        // Returns false if fails (prefer this)

// View top element
int top = pq.peek();     // Returns null if empty
int top = pq.element();  // Throws exception if empty

// Remove and return top
int top = pq.poll();     // Returns null if empty
int top = pq.remove();   // Throws exception if empty

// Size and empty check
int size = pq.size();
boolean empty = pq.isEmpty();
```

---

## Min-Heap vs Max-Heap

```java
// MIN-HEAP (default) - smallest element at top
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
minHeap.addAll(Arrays.asList(5, 2, 8, 1));
minHeap.peek();  // 1 (smallest)

// MAX-HEAP - largest element at top
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
maxHeap.addAll(Arrays.asList(5, 2, 8, 1));
maxHeap.peek();  // 8 (largest)

// Alternative max-heap syntax
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
```

---

## Custom Comparators

### For Custom Objects

```java
class Task {
    String name;
    int priority;
}

// Sort by priority (ascending)
PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> a.priority - b.priority);

// Sort by priority (descending)
PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> b.priority - a.priority);

// Multiple criteria
PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> {
    if (a.priority != b.priority) return a.priority - b.priority;
    return a.name.compareTo(b.name);
});
```

### Common Patterns

```java
// Sort pairs/arrays by first element
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

// Sort by absolute value
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Math.abs(a) - Math.abs(b));

// Sort strings by length
PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> a.length() - b.length());
```

---

## Common Patterns & Use Cases

### 1. Top K Elements

**Pattern**: Use max-heap of size K (or min-heap for K smallest)

```java
// Find K largest elements
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int num : nums) {
    minHeap.offer(num);
    if (minHeap.size() > k) {
        minHeap.poll();  // Remove smallest
    }
}
// minHeap contains K largest elements
```

### 2. Kth Largest/Smallest

```java
// Kth largest - use min-heap of size K
PriorityQueue<Integer> pq = new PriorityQueue<>();
for (int num : nums) {
    pq.offer(num);
    if (pq.size() > k) pq.poll();
}
int kthLargest = pq.peek();

// Kth smallest - use max-heap of size K
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
for (int num : nums) {
    pq.offer(num);
    if (pq.size() > k) pq.poll();
}
int kthSmallest = pq.peek();
```

### 3. Merge K Sorted Lists/Arrays

```java
// Use min-heap to track smallest element from each list
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
// Add: {value, listIndex, elementIndex}
```

### 4. Running Median (Two Heaps)

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Left half
PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Right half

// Keep maxHeap.size() >= minHeap.size()
// Median = maxHeap.peek() or average of both tops
```

---

## Important Gotchas

```java
// ❌ WRONG - PriorityQueue is NOT fully sorted
PriorityQueue<Integer> pq = new PriorityQueue<>(Arrays.asList(5, 2, 8, 1));
// Don't iterate and expect sorted order!
for (int x : pq) { /* NOT guaranteed sorted */ }

// ✅ CORRECT - Poll elements to get sorted order
while (!pq.isEmpty()) {
    System.out.println(pq.poll());  // Sorted order
}

// ❌ WRONG - null elements
pq.add(null);  // NullPointerException

// ❌ WRONG - Comparator overflow
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
// Can overflow if a and b have large values with different signs

// ✅ CORRECT - Use Integer.compare()
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
// Or simply: new PriorityQueue<>() for natural order
```

---

## Quick Reference

### Creation
```java
// Min-heap
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max-heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

// Custom comparator
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

// With initial capacity
PriorityQueue<Integer> pq = new PriorityQueue<>(100);
```

### Essential Operations
```java
pq.offer(x)      // Add element (preferred)
pq.poll()        // Remove and return top (null if empty)
pq.peek()        // View top (null if empty)
pq.size()        // Get size
pq.isEmpty()     // Check if empty
pq.clear()       // Remove all elements
```

### Common Patterns
- **Top K elements**: Min-heap of size K (for K largest)
- **Kth largest**: Min-heap of size K, answer = `peek()`
- **Kth smallest**: Max-heap of size K, answer = `peek()`
- **Merge K lists**: Min-heap with `{value, source}`
- **Running median**: Two heaps (max + min)

---

## Interview Tips

### When to Use PriorityQueue
✅ Finding Kth largest/smallest element
✅ Top K elements
✅ Merge K sorted arrays/lists
✅ Running median
✅ Dijkstra's algorithm (shortest path)
✅ Continuous stream of data with priority

### Remember
- **Default is min-heap** (smallest at top)
- Use `Collections.reverseOrder()` for max-heap
- **Not sorted** - only top element guaranteed
- `offer()` over `add()`, `poll()` over `remove()`
- For large integers, use `Integer.compare(a, b)` to avoid overflow
- Top K largest → use **min-heap** of size K
- Top K smallest → use **max-heap** of size K

### Time Complexity Quick Check
- Add/Remove: O(log n)
- Peek: O(1)
- Find K largest/smallest: O(n log k)
- Sort using heap: O(n log n)

---

**Key Insight**: For "K largest", think **min-heap** of size K. For "K smallest", think **max-heap** of size K!
