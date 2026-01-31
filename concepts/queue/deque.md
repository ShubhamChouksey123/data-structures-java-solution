# Java Deque (Double-Ended Queue) Concepts

## 2. What is Deque?

**Type**: Double-ended queue interface
**Package**: `java.util.Deque` (extends Queue)
**Main Implementation**: ArrayDeque (recommended)

**Key Characteristics**:
- **Operations at both ends** - add/remove from front and back in O(1)
- **Can be Queue (FIFO) or Stack (LIFO)** ⭐
- **Allows duplicates** ✅
- **Does NOT allow null** (ArrayDeque)
- **Not thread-safe** - use ConcurrentLinkedDeque for thread-safe

---

## 3. Time & Space Complexity

| Operation | ArrayDeque | LinkedList | Notes |
|-----------|------------|------------|-------|
| **offerFirst (add front)** | O(1) | O(1) | Add to front |
| **offerLast (add back)** | O(1) | O(1) | Add to back |
| **pollFirst (remove front)** | O(1) | O(1) | Remove from front |
| **pollLast (remove back)** | O(1) | O(1) | Remove from back |
| **peekFirst (view front)** | O(1) | O(1) | View front |
| **peekLast (view back)** | O(1) | O(1) | View back |
| **size** | O(1) | O(1) | Get size |
| **contains** | O(n) | O(n) | Linear search |

**Space**: O(n)

**Recommendation**: Use **ArrayDeque** (faster, better cache locality)

---

## 4. Common Operations & Methods

### Deque-Specific Methods (Recommended)

| Operation | Front (First) | Back (Last) | Notes |
|-----------|--------------|-------------|-------|
| **Add** | `offerFirst(e)` | `offerLast(e)` | Returns boolean |
| **Remove** | `pollFirst()` | `pollLast()` | Returns null if empty |
| **View** | `peekFirst()` | `peekLast()` | Returns null if empty |

### Queue Methods (FIFO Behavior)

| Operation | Deque Method | Equivalent To |
|-----------|--------------|---------------|
| **Enqueue** | `offer(e)` | `offerLast(e)` |
| **Dequeue** | `poll()` | `pollFirst()` |
| **Peek** | `peek()` | `peekFirst()` |

### Stack Methods (LIFO Behavior)

| Operation | Deque Method | Equivalent To |
|-----------|--------------|---------------|
| **Push** | `push(e)` | `addFirst(e)` |
| **Pop** | `pop()` | `removeFirst()` |
| **Peek** | `peek()` | `peekFirst()` |

**Best Practice**: Use `offerFirst/offerLast` and `pollFirst/pollLast` for clarity when using Deque operations.

---

## 5. Core Characteristics/Creation

```java
// Recommended: ArrayDeque
Deque<Integer> deque = new ArrayDeque<>();

// Add elements
deque.offerFirst(1);    // [1]
deque.offerLast(2);     // [1, 2]
deque.offerFirst(0);    // [0, 1, 2]
deque.offerLast(3);     // [0, 1, 2, 3]

// View elements
deque.peekFirst();      // 0 (front)
deque.peekLast();       // 3 (back)

// Remove elements
deque.pollFirst();      // 0 → [1, 2, 3]
deque.pollLast();       // 3 → [1, 2]

// Size and empty check
int size = deque.size();
boolean empty = deque.isEmpty();
```

### As Queue (FIFO)
```java
Deque<Integer> queue = new ArrayDeque<>();
queue.offerLast(1);     // Add to back
queue.offerLast(2);
queue.pollFirst();      // 1 (remove from front)
```

### As Stack (LIFO)
```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(1);          // Add to front
stack.push(2);
stack.pop();            // 2 (remove from front)
```

---

## 6. Comparison with Similar Structures

| Feature | ArrayDeque | LinkedList | Stack (legacy) |
|---------|-----------|------------|----------------|
| **Implementation** | Circular array | Doubly linked list | Array-based |
| **Performance** | Fast ⭐ | Slower | Medium |
| **Memory** | Less overhead | More overhead | Medium |
| **Null elements** | ❌ Not allowed | ✅ Allowed | ✅ Allowed |
| **Thread-safe** | No | No | Yes (synchronized) |
| **Recommended** | Yes ⭐ | Only if nulls needed | No (legacy) |

**When to Use ArrayDeque**: Default choice (faster than LinkedList, replaces Stack)

**When to Use LinkedList**: Need null elements or List interface

**When NOT to Use**: Never use legacy Stack class - use ArrayDeque instead

---

## 7. Common Patterns & Use Cases



## 8. Common Gotchas & Best Practices

### 1. Null Elements Not Allowed

**❌ WRONG**:
```java
Deque<Integer> deque = new ArrayDeque<>();
deque.offerFirst(null);  // NullPointerException
```

**✅ CORRECT**:
```java
// Use LinkedList if nulls needed (rare)
Deque<Integer> deque = new LinkedList<>();
deque.offerFirst(null);  // Works
```

---

### 2. Use Specific Method Names

**❌ UNCLEAR**:
```java
deque.offer(x);        // Which end? (actually offerLast)
deque.poll();          // Which end? (actually pollFirst)
```

**✅ CLEAR**:
```java
deque.offerFirst(x);   // Explicit: add to front
deque.offerLast(x);    // Explicit: add to back
deque.pollFirst();     // Explicit: remove from front
deque.pollLast();      // Explicit: remove from back
```

---

### 3. Don't Use Legacy Stack Class

**❌ WRONG**:
```java
Stack<Integer> stack = new Stack<>();  // Legacy, synchronized
```

**✅ CORRECT**:
```java
Deque<Integer> stack = new ArrayDeque<>();  // Modern, faster
stack.push(1);
stack.pop();
```

---

### 4. Monotonic Deque Order Confusion

**Remember**:
- **Decreasing deque** → track **maximum**
- **Increasing deque** → track **minimum**

---

## 9. Interview Tips

### When to Use Deque
✅ Sliding window maximum/minimum
✅ Monotonic deque patterns
✅ Need operations at both ends
✅ Replace Stack class (use as LIFO)
✅ Replace Queue (use as FIFO)

### When NOT to Use Deque
❌ Only need FIFO → Use Queue<ArrayDeque>
❌ Only need LIFO → Use Deque as Stack
❌ Need random access → Use List

### Remember
- **ArrayDeque is fastest** - always use by default
- **Replaces both Queue and Stack** - more flexible
- **Deque does NOT allow null** in ArrayDeque
- **Monotonic deque** is key pattern for sliding window problems
- **Sliding window max** uses monotonic **decreasing** deque
- **Sliding window min** uses monotonic **increasing** deque
- Use **offerFirst/offerLast** for clarity

### Time Complexity Quick Check
- All add/remove/peek operations: O(1)
- Sliding window maximum: O(n) using monotonic deque

---

## 10. Quick Reference

### Creation
```java
// Recommended
Deque<Integer> deque = new ArrayDeque<>();

// With capacity
Deque<Integer> deque = new ArrayDeque<>(100);
```

### Essential Operations
```java
// Add
deque.offerFirst(x)    // Add to front
deque.offerLast(x)     // Add to back

// Remove
deque.pollFirst()      // Remove from front
deque.pollLast()       // Remove from back

// View
deque.peekFirst()      // View front
deque.peekLast()       // View back

// Size
deque.size()
deque.isEmpty()
```

### As Queue (FIFO)
```java
deque.offerLast(x);    // Enqueue
deque.pollFirst();     // Dequeue
```

### As Stack (LIFO)
```java
deque.push(x);         // Push (or offerFirst)
deque.pop();           // Pop (or pollFirst)
```


### Monotonic Deque Template
```java
// Decreasing (for max)
while (!deque.isEmpty() && deque.peekLast() < current) {
    deque.pollLast();
}
deque.offerLast(current);

// Increasing (for min)
while (!deque.isEmpty() && deque.peekLast() > current) {
    deque.pollLast();
}
deque.offerLast(current);
```

---

## 11. Key Insight

**Deque is your Swiss Army knife** - it efficiently handles operations at both ends, making it perfect for **sliding window problems** (monotonic deque), and it **replaces both Queue and Stack**! Remember: **decreasing deque for max, increasing deque for min**!
