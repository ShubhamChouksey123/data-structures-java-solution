# Java LinkedList Concepts

## 2. What is LinkedList?

**Type**: Doubly linked list implementation
**Package**: `java.util.LinkedList`
**Implements**: List, Deque, Queue

**Key Characteristics**:
- **Doubly linked** - each node has prev and next pointers
- **O(1) operations at both ends** ⭐
- **O(n) random access** ⚠️
- **High memory overhead** - 2 pointers per node

---

## 3. Time & Space Complexity

| Operation | LinkedList | ArrayList | Notes |
|-----------|------------|-----------|-------|
| **get(i)** | O(n) ⚠️ | O(1) ⭐ | LinkedList must traverse |
| **add at end** | O(1) | O(1) | Both efficient |
| **addFirst(e)** | O(1) ⭐ | O(n) | LinkedList advantage |
| **removeFirst()** | O(1) ⭐ | O(n) | LinkedList advantage |
| **Memory** | High | Low | 2 pointers per node |

**Space**: O(n) + pointer overhead

---

## 4. Common Operations & Methods

**LinkedList-Specific Deque Operations** (All O(1)):

| Operation | Method | Notes |
|-----------|--------|-------|
| **Add at beginning** | `list.addFirst(e)` | Main advantage |
| **Add at end** | `list.addLast(e)` | Main advantage |
| **Remove from beginning** | `list.removeFirst()` | Main advantage |
| **Remove from end** | `list.removeLast()` | Main advantage |
| **Peek first** | `list.peekFirst()` | Returns null if empty |
| **Peek last** | `list.peekLast()` | Returns null if empty |

See `info.md` for common List operations (get, set, add, remove, etc.)

---

## 5. Core Characteristics/Creation

```java
LinkedList<Integer> list = new LinkedList<>();

// Deque operations (O(1))
list.addFirst(1);      // Add at beginning
list.addLast(5);       // Add at end
list.removeFirst();    // Remove from beginning
list.removeLast();     // Remove from end
```

---

## 6. Comparison with Similar Structures

| Feature | LinkedList | ArrayList | ArrayDeque |
|---------|-----------|-----------|------------|
| **Random access** | O(n) | O(1) ⭐ | O(n) |
| **Add at beginning** | O(1) ⭐ | O(n) | O(1) ⭐ |
| **Memory** | High | Low ⭐ | Low ⭐ |
| **List interface** | Yes | Yes | No |
| **Queue/Stack** | Good | Poor | Best ⭐ |

**Recommendation**:
- **95% of cases** → Use ArrayList
- **Queue/Stack** → Use ArrayDeque (faster than LinkedList)
- **LinkedList** → Almost never needed

---

## 7. Common Patterns & Use Cases

**LinkedList is rarely the right choice.**

Use instead:
- **List operations** → `ArrayList` (faster for 95% of cases)
- **Queue/Stack** → `ArrayDeque` (faster than LinkedList)

**Only use LinkedList** when you need List interface with O(1) operations at both ends (extremely rare).

---

## 8. Common Gotchas & Best Practices

### 1. Never Use Indexed Loop

**❌ WRONG**:
```java
for (int i = 0; i < list.size(); i++) {
    process(list.get(i));  // O(n²) - get(i) is O(n)!
}
```

**✅ CORRECT**:
```java
for (int val : list) {
    process(val);  // O(n) - use iterator
}
```

---

### 2. Use ArrayDeque Instead

**❌ SUBOPTIMAL**:
```java
Queue<Integer> queue = new LinkedList<>();
Deque<Integer> deque = new LinkedList<>();
```

**✅ BETTER**:
```java
Queue<Integer> queue = new ArrayDeque<>();
Deque<Integer> deque = new ArrayDeque<>();
```

---

### 3. LinkedList is Rarely Needed

**Reality**:
- **95%** → ArrayList
- **4%** → ArrayDeque
- **1%** → LinkedList

Only use LinkedList if you need List interface with O(1) at both ends.

---

## 9. Interview Tips

### When to Use LinkedList
✅ Need List interface with O(1) operations at both ends (rare)
✅ Frequent insertions/deletions at beginning
✅ No random access needed

### When NOT to Use LinkedList
❌ Random access needed → Use ArrayList
❌ Queue/Stack operations → Use ArrayDeque
❌ General list → Use ArrayList
❌ Performance matters → Use ArrayList or ArrayDeque

### Remember
- **O(n) random access** - never use indexed loops
- **ArrayDeque is faster** for queue/stack
- **ArrayList is better** for 95% of cases
- **High memory overhead** - 2 pointers per node
- LinkedList implements List, Deque, and Queue

---

## 10. Quick Reference

### Deque Operations (O(1))
```java
list.addFirst(x)      // Add at beginning
list.addLast(x)       // Add at end
list.removeFirst()    // Remove from beginning
list.removeLast()     // Remove from end
list.peekFirst()      // View first (null if empty)
list.peekLast()       // View last (null if empty)
```

### Avoid (O(n))
```java
list.get(i)           // Use iterator instead
list.set(i, x)        // Use iterator instead
```

### Iteration
```java
for (int x : list) { }  // Use this, not indexed loop
```

---

## 11. Key Insight

**Don't use LinkedList!** Use **ArrayList** for lists (95% of cases) or **ArrayDeque** for queues/stacks (faster). LinkedList is almost never the best choice!
