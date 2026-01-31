# Java Stack Concepts

## 2. What is Stack?

**Type**: LIFO (Last In, First Out) data structure
**Implementation**: Use Deque (ArrayDeque) - NOT legacy Stack class
**Package**: `java.util.Deque` (use as Stack)

**Key Characteristics**:
- **LIFO ordering** - last added is first removed
- **O(1) operations** - push, pop, peek
- **Allows duplicates** ✅
- **Does NOT allow null** (ArrayDeque)

---

## 3. Time & Space Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **push** | O(1) | Add to top |
| **pop** | O(1) | Remove from top |
| **peek** | O(1) | View top |
| **search** | O(n) | Find element |
| **size** | O(1) | Get size |

**Space**: O(n)

---

## 4. Common Operations & Methods

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Push** | `stack.push(e)` | O(1) | Add to top |
| **Pop** | `stack.pop()` | O(1) | Remove from top (throws if empty) |
| **Peek** | `stack.peek()` | O(1) | View top (null if empty) |
| **Size** | `stack.size()` | O(1) | Number of elements |
| **Empty** | `stack.isEmpty()` | O(1) | Check if empty |
| **Clear** | `stack.clear()` | O(n) | Remove all |

**Best Practice**: Use `push()`, `pop()`, `peek()` for clarity

---

## 5. Core Characteristics/Creation

```java
// RECOMMENDED: Use Deque as Stack
Deque<Integer> stack = new ArrayDeque<>();

// Push elements
stack.push(1);    // [1]
stack.push(2);    // [2, 1]
stack.push(3);    // [3, 2, 1]

// Peek top (doesn't remove)
int top = stack.peek();  // 3

// Pop elements
int val = stack.pop();   // 3 → [2, 1]

// Size and empty check
int size = stack.size();
boolean empty = stack.isEmpty();
```

### Why NOT Legacy Stack?

```java
// ❌ DON'T USE
Stack<Integer> stack = new Stack<>();  // Slow, synchronized, legacy

// ✅ USE
Deque<Integer> stack = new ArrayDeque<>();  // Fast, modern
```

---

## 6. Comparison with Similar Structures

| Feature | Stack | Queue | ArrayList |
|---------|-------|-------|-----------|
| **Order** | LIFO | FIFO | Index-based |
| **Access** | Top only | Front/back | Any index |
| **Use case** | DFS, backtracking | BFS, scheduling | Random access |

**When to Use Stack**: DFS, backtracking, expression evaluation, parentheses validation

---

## 7. Common Patterns & Use Cases



### Pattern 2: Monotonic Stack

**Use Case**: Next Greater Element, Daily Temperatures

```java
Deque<Integer> stack = new ArrayDeque<>();  // Stores indices
int[] result = new int[n];
Arrays.fill(result, -1);

for (int i = 0; i < n; i++) {
    while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
        result[stack.pop()] = arr[i];
    }
    stack.push(i);
}
```

**Complexity**: O(n) - each element pushed/popped once

---



### Pattern 4: Expression Evaluation

**Use Case**: Evaluate postfix expressions

```java
Deque<Integer> stack = new ArrayDeque<>();

for (String token : tokens) {
    if (isOperator(token)) {
        int b = stack.pop();
        int a = stack.pop();
        stack.push(evaluate(a, b, token));
    } else {
        stack.push(Integer.parseInt(token));
    }
}
return stack.pop();
```

**Complexity**: O(n)

---

## 8. Common Gotchas & Best Practices

### 1. Don't Use Legacy Stack

**❌ WRONG**:
```java
Stack<Integer> stack = new Stack<>();
```

**✅ CORRECT**:
```java
Deque<Integer> stack = new ArrayDeque<>();
```

---

### 2. Pop on Empty Stack

**❌ WRONG**:
```java
stack.pop();  // Throws if empty
```

**✅ CORRECT**:
```java
if (!stack.isEmpty()) {
    int val = stack.pop();
}
```

---

### 3. Peek Returns Null

**❌ WRONG**:
```java
int top = stack.peek();  // NullPointerException if empty
```

**✅ CORRECT**:
```java
if (!stack.isEmpty()) {
    int top = stack.peek();
}
```

---

## 9. Interview Tips

### When to Use Stack
✅ DFS (iterative)
✅ Backtracking
✅ Parentheses validation
✅ Expression evaluation
✅ Next Greater/Smaller Element (monotonic stack)
✅ Undo operations

### When NOT to Use Stack
❌ BFS → Use Queue
❌ Random access → Use ArrayList

### Remember
- **LIFO** - Last In, First Out
- **Use Deque (ArrayDeque)** - never legacy Stack
- **All operations O(1)** at the top
- **Monotonic stack** - powerful for next greater/smaller
- **DFS uses Stack**, BFS uses Queue
- Always check `isEmpty()` before `pop()`

### Common Problems
- Valid Parentheses
- Daily Temperatures (monotonic)
- Next Greater Element (monotonic)
- Asteroid Collision
- Min Stack / Max Stack
- Binary Tree Inorder Traversal (iterative)

### Time Complexity Quick Check
- Push/Pop/Peek: O(1)
- Monotonic stack: O(n)

---

## 10. Quick Reference


## 11. Key Insight

Use **Deque (ArrayDeque)** for Stack - faster than legacy Stack class! **Stack for DFS (LIFO)**, **Queue for BFS (FIFO)**. **Monotonic stack** is your secret weapon for "next greater/smaller" problems!
