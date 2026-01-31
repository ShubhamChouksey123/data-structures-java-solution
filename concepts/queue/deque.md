# Java Deque (Double-Ended Queue) Concepts

## What is Deque?

**Type**: Double-ended queue - supports insertion and removal from both ends
**Interface**: `java.util.Deque` (extends Queue)
**Main Implementation**: **ArrayDeque** (recommended)

**Key Characteristics**:
- Can operate as **Queue** (FIFO) or **Stack** (LIFO)
- Add/remove from **both front and back** in O(1)
- **Allows duplicates** ✅
- **Does NOT allow null** (ArrayDeque)
- **Not thread-safe** (use `ConcurrentLinkedDeque` for thread-safe)

---

## Deque vs Queue vs Stack

| Feature | Queue | Stack | Deque |
|---------|-------|-------|-------|
| **Add at front** | ❌ | ✅ (top) | ✅ |
| **Add at back** | ✅ | ❌ | ✅ |
| **Remove from front** | ✅ | ❌ | ✅ |
| **Remove from back** | ❌ | ✅ (top) | ✅ |
| **Use case** | FIFO only | LIFO only | **Both** ⭐ |

**Key Insight**: Deque is more flexible - can replace both Queue and Stack!

---

## Deque Method Categories

Deque provides **three sets of methods** for the same operations:

### 1. Deque-specific Methods (Recommended)

| Operation | First Element (Front) | Last Element (Back) |
|-----------|----------------------|---------------------|
| **Insert** | `offerFirst(e)` | `offerLast(e)` |
| **Remove** | `pollFirst()` | `pollLast()` |
| **Examine** | `peekFirst()` | `peekLast()` |

**Returns**: Special value (false/null) on failure

---

### 2. Queue Methods (For Queue Behavior)

| Queue Method | Equivalent Deque Method |
|--------------|------------------------|
| `offer(e)` | `offerLast(e)` - add to back |
| `poll()` | `pollFirst()` - remove from front |
| `peek()` | `peekFirst()` - view front |

---

### 3. Stack Methods (For Stack Behavior)

| Stack Method | Equivalent Deque Method |
|--------------|------------------------|
| `push(e)` | `addFirst(e)` - add to front |
| `pop()` | `removeFirst()` - remove from front |
| `peek()` | `peekFirst()` - view front |

---

## ArrayDeque (Primary Implementation)

**Implementation**: Resizable circular array
**Recommended**: Use ArrayDeque for all Deque operations

```java
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
```

**Time Complexity**: All operations O(1) amortized

**Space**: O(n)

---

## Complete Method Reference

### Adding Elements

```java
Deque<Integer> deque = new ArrayDeque<>();

// Add to front (head)
deque.offerFirst(1);    // true/false - preferred
deque.addFirst(2);      // void - throws exception if fails
deque.push(3);          // void - stack operation (same as addFirst)

// Add to back (tail)
deque.offerLast(4);     // true/false - preferred
deque.addLast(5);       // void - throws exception if fails
deque.offer(6);         // true/false - queue operation (same as offerLast)
deque.add(7);           // true - throws exception if fails
```

### Removing Elements

```java
// Remove from front
int x = deque.pollFirst();     // null if empty - preferred
int x = deque.removeFirst();   // exception if empty
int x = deque.poll();          // null if empty - queue operation
int x = deque.remove();        // exception if empty
int x = deque.pop();           // exception if empty - stack operation

// Remove from back
int x = deque.pollLast();      // null if empty - preferred
int x = deque.removeLast();    // exception if empty
```

### Examining Elements

```java
// View front
int x = deque.peekFirst();     // null if empty - preferred
int x = deque.getFirst();      // exception if empty
int x = deque.peek();          // null if empty - queue operation
int x = deque.element();       // exception if empty

// View back
int x = deque.peekLast();      // null if empty - preferred
int x = deque.getLast();       // exception if empty
```

### Other Operations

```java
int size = deque.size();
boolean empty = deque.isEmpty();
deque.clear();
boolean contains = deque.contains(5);
```

---

## Using Deque as Queue (FIFO)

```java
Deque<Integer> queue = new ArrayDeque<>();

// Add to back, remove from front
queue.offerLast(1);     // or queue.offer(1)
queue.offerLast(2);
queue.offerLast(3);

queue.pollFirst();      // 1 - or queue.poll()
queue.pollFirst();      // 2
```

**Pattern**: `offerLast()` + `pollFirst()` = FIFO

---

## Using Deque as Stack (LIFO)

```java
Deque<Integer> stack = new ArrayDeque<>();

// Add to front, remove from front
stack.push(1);          // or stack.offerFirst(1)
stack.push(2);
stack.push(3);

stack.pop();            // 3 - or stack.pollFirst()
stack.pop();            // 2
stack.peek();           // 1
```

**Pattern**: `push()` / `offerFirst()` + `pop()` / `pollFirst()` = LIFO

**Note**: Prefer Deque over Stack class (Stack is legacy and synchronized)

---

## Common Patterns & Use Cases

### Pattern 1: Sliding Window Maximum ⭐ **IMPORTANT**

**Use Case**: Find maximum in each window of size k

**Key Technique**: Monotonic decreasing deque

```java
Deque<Integer> deque = new ArrayDeque<>();  // Stores indices
int[] result = new int[n - k + 1];

for (int i = 0; i < n; i++) {
    // Remove elements outside window
    while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
        deque.pollFirst();
    }

    // Remove smaller elements (maintain decreasing order)
    while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
        deque.pollLast();
    }

    deque.offerLast(i);

    // Window is ready
    if (i >= k - 1) {
        result[i - k + 1] = nums[deque.peekFirst()];
    }
}
```

**Complexity**: O(n) time, O(k) space

**Why Deque?**: Need to remove from both ends

---

### Pattern 2: Sliding Window Minimum

**Same as maximum, but maintain increasing order**:

```java
// Remove larger elements (maintain increasing order)
while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
    deque.pollLast();
}
```

---

### Pattern 3: Valid Parentheses with Indices

**Use Case**: Track positions while validating

```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(-1);  // Base for valid substring

for (int i = 0; i < s.length(); i++) {
    if (s.charAt(i) == '(') {
        stack.push(i);
    } else {
        stack.pop();
        if (stack.isEmpty()) {
            stack.push(i);
        } else {
            maxLen = Math.max(maxLen, i - stack.peek());
        }
    }
}
```

---

### Pattern 4: Implement Queue using Deque

```java
class MyQueue {
    Deque<Integer> deque = new ArrayDeque<>();

    void enqueue(int x) {
        deque.offerLast(x);    // Add to back
    }

    int dequeue() {
        return deque.pollFirst();  // Remove from front
    }

    int peek() {
        return deque.peekFirst();
    }
}
```

---

### Pattern 5: Implement Stack using Deque

```java
class MyStack {
    Deque<Integer> deque = new ArrayDeque<>();

    void push(int x) {
        deque.offerFirst(x);    // Add to front
    }

    int pop() {
        return deque.pollFirst();  // Remove from front
    }

    int peek() {
        return deque.peekFirst();
    }
}
```

---

### Pattern 6: LRU Cache (with LinkedHashMap + Deque concept)

**Use Case**: Track recently used items with access from both ends

---

## Monotonic Deque Pattern ⭐ **IMPORTANT**

**Concept**: Maintain elements in monotonic (increasing/decreasing) order

### Monotonic Decreasing Deque

**Use Case**: Track maximum in sliding window

**Invariant**: Elements in deque are in **decreasing** order

```java
Deque<Integer> deque = new ArrayDeque<>();

// When adding element x:
while (!deque.isEmpty() && deque.peekLast() < x) {
    deque.pollLast();  // Remove smaller elements
}
deque.offerLast(x);

// Front element is always maximum
int max = deque.peekFirst();
```

### Monotonic Increasing Deque

**Use Case**: Track minimum in sliding window

**Invariant**: Elements in deque are in **increasing** order

```java
Deque<Integer> deque = new ArrayDeque<>();

// When adding element x:
while (!deque.isEmpty() && deque.peekLast() > x) {
    deque.pollLast();  // Remove larger elements
}
deque.offerLast(x);

// Front element is always minimum
int min = deque.peekFirst();
```

---

## ArrayDeque vs LinkedList as Deque

| Feature | ArrayDeque | LinkedList |
|---------|-----------|------------|
| **Implementation** | Circular array | Doubly linked list |
| **Performance** | **Faster** ⭐ | Slower |
| **Memory** | Less overhead | More overhead (pointers) |
| **Null elements** | **Not allowed** | Allowed |
| **Random access** | O(n) | O(n) |
| **Cache locality** | **Better** | Poor |
| **Recommended** | **Yes** ⭐ | Only if nulls needed |

**Recommendation**: Always use **ArrayDeque** unless you need null elements.

---

## Common Deque Problems

### Monotonic Deque
- [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) - Hard ⭐
- Sliding Window Median
- Shortest Subarray with Sum at Least K

### Stack Problems (using Deque)
- Valid Parentheses
- Daily Temperatures
- Next Greater Element

### Queue Problems (using Deque)
- Design Circular Deque
- Moving Average from Data Stream

### Design
- LRU Cache (concept with Deque)
- Design Browser History

---

## Important Gotchas

### 1. Null Elements

**❌ WRONG** (ArrayDeque):
```java
Deque<Integer> deque = new ArrayDeque<>();
deque.offerFirst(null);  // NullPointerException
```

### 2. Exception vs Return Value

**Prefer methods that return special values**:

| Throws Exception | Returns Special Value |
|------------------|----------------------|
| `addFirst(e)` | ✅ `offerFirst(e)` |
| `addLast(e)` | ✅ `offerLast(e)` |
| `removeFirst()` | ✅ `pollFirst()` |
| `removeLast()` | ✅ `pollLast()` |
| `getFirst()` | ✅ `peekFirst()` |
| `getLast()` | ✅ `peekLast()` |

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

### 4. Clear Method Naming

**Be explicit about which end**:
```java
deque.offerFirst(x);   // Clear: add to front
deque.offerLast(x);    // Clear: add to back

// Avoid ambiguous methods when using as Deque
deque.offer(x);        // Same as offerLast, but less clear
```

---

## Time Complexity Summary

**ArrayDeque (All O(1) amortized)**:
```
offerFirst(e)      - O(1)
offerLast(e)       - O(1)
pollFirst()        - O(1)
pollLast()         - O(1)
peekFirst()        - O(1)
peekLast()         - O(1)
size()             - O(1)
isEmpty()          - O(1)
contains(e)        - O(n)
remove(e)          - O(n)
```

---

## Quick Reference

### Creation
```java
// Recommended
Deque<Integer> deque = new ArrayDeque<>();

// With initial capacity
Deque<Integer> deque = new ArrayDeque<>(100);
```

### Essential Operations
```java
// Add
deque.offerFirst(x)    // Add to front (preferred)
deque.offerLast(x)     // Add to back (preferred)

// Remove
deque.pollFirst()      // Remove from front (preferred)
deque.pollLast()       // Remove from back (preferred)

// View
deque.peekFirst()      // View front (preferred)
deque.peekLast()       // View back (preferred)

// Size
deque.size()
deque.isEmpty()
```

### As Queue (FIFO)
```java
deque.offerLast(x);    // Enqueue
deque.pollFirst();     // Dequeue
deque.peekFirst();     // Peek
```

### As Stack (LIFO)
```java
deque.push(x);         // Push (same as offerFirst)
deque.pop();           // Pop (same as pollFirst)
deque.peek();          // Peek (same as peekFirst)
```

### Monotonic Deque Template
```java
Deque<Integer> deque = new ArrayDeque<>();

// Monotonic decreasing (for maximum)
while (!deque.isEmpty() && deque.peekLast() < current) {
    deque.pollLast();
}
deque.offerLast(current);

// Monotonic increasing (for minimum)
while (!deque.isEmpty() && deque.peekLast() > current) {
    deque.pollLast();
}
deque.offerLast(current);
```

---

## Interview Tips

### When to Use Deque
✅ Need operations on both ends (front and back)
✅ Sliding window maximum/minimum problems
✅ Monotonic deque patterns
✅ Replacing legacy Stack class
✅ More flexible than Queue or Stack alone

### Remember
- **ArrayDeque is the best choice** for Deque operations
- Deque can replace **both Queue and Stack**
- Use **offer/poll/peek methods** (return special values)
- Deque **does NOT allow null** in ArrayDeque
- **Monotonic deque** is powerful for sliding window problems
- Prefer **Deque over Stack class** (Stack is legacy)

### Method Cheat Sheet
```
Front (First)          Operation          Back (Last)
-------------          ---------          -----------
offerFirst(e)    ←     ADD        →       offerLast(e)
pollFirst()      ←     REMOVE     →       pollLast()
peekFirst()      ←     VIEW       →       peekLast()
```

### Common Patterns
1. **Sliding Window Max/Min**: Monotonic deque
2. **Stack Replacement**: Use push/pop methods
3. **Queue Replacement**: Use offerLast/pollFirst
4. **Palindrome Check**: Compare first and last

---

**Key Insight**: Deque is your Swiss Army knife - it can efficiently handle operations that require access to both ends, making it perfect for sliding window, monotonic patterns, and replacing both Queue and Stack!
