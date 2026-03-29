# Monotonic Stack

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Find next/previous greater/smaller element efficiently in arrays**

### Keywords
- "next greater element"
- "previous smaller element"
- "nearest larger/smaller"
- "range queries"
- "visibility problems"
- "histogram"
- "temperature increase"

### Examples
- Next greater element in array
- Daily temperatures problem
- Stock span problem
- Largest rectangle in histogram
- Maximum width ramp

---

## Core Concept

A **monotonic stack** maintains elements in either strictly increasing or decreasing order. When a new element violates the monotonicity, we pop elements until the order is restored.

**Key Insight**: When we pop an element, we've found its next greater/smaller element (depending on stack type).

**Types**:
- **Monotonic Increasing Stack**: Elements increase from bottom to top (pop smaller elements)
- **Monotonic Decreasing Stack**: Elements decrease from bottom to top (pop larger elements)

**Complexity**: O(n) time, O(n) space - each element pushed and popped at most once

---

## Pattern 1: Next Greater Element

**Use Case**: For each element, find the next element to its right that is greater

**Algorithm**:
1. Use monotonic decreasing stack (stores elements in decreasing order)
2. Iterate array from left to right
3. While stack top < current element, pop and record answer
4. Push current element to stack
5. Remaining elements in stack have no next greater element

**Complexity**: O(n) time, O(n) space

### Template

```java
public int[] nextGreaterElement(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    Arrays.fill(result, -1);  // default: no greater element
    Stack<Integer> stack = new Stack<>();  // stores indices

    for (int i = 0; i < n; i++) {
        // Pop elements smaller than current
        while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
            int idx = stack.pop();
            result[idx] = nums[i];  // found next greater element
        }
        stack.push(i);
    }

    return result;
}
```

---

## Pattern 2: Previous Greater Element

**Use Case**: For each element, find the previous element to its left that is greater

**Algorithm**:
1. Use monotonic decreasing stack
2. Iterate array from left to right
3. While stack top <= current element, pop
4. If stack not empty, stack.peek() is previous greater element
5. Push current element

**Complexity**: O(n) time, O(n) space

### Template

```java
public int[] previousGreaterElement(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    Arrays.fill(result, -1);
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < n; i++) {
        // Pop elements <= current
        while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
            stack.pop();
        }

        // Stack top is previous greater element
        if (!stack.isEmpty()) {
            result[i] = nums[stack.peek()];
        }

        stack.push(i);
    }

    return result;
}
```

---

## Pattern 3: Next Greater Element in Circular Array ⭐ **IMPORTANT** ⭐

**Use Case**: Find next greater element where array is treated as circular

**Algorithm**:
1. Use monotonic decreasing stack
2. Iterate array twice (simulate circular by using `i % n`)
3. In second iteration, only process without pushing to stack
4. Same logic as Pattern 1

**Complexity**: O(n) time, O(n) space

### Template

```java
public int[] nextGreaterElements(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];
    Arrays.fill(result, -1);
    Stack<Integer> stack = new Stack<>();

    // Iterate twice for circular array
    for (int i = 0; i < 2 * n; i++) {
        int idx = i % n;

        while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
            result[stack.pop()] = nums[idx];
        }

        // Only push in first iteration
        if (i < n) {
            stack.push(idx);
        }
    }

    return result;
}
```

---

## Pattern 5: Largest Rectangle in Histogram ⭐ **IMPORTANT** ⭐

**Use Case**: Find largest rectangle area in histogram

**Algorithm**:
1. Use monotonic increasing stack (stores indices)
2. When current bar is shorter than stack top, calculate area
3. Height = height of popped bar
4. Width = current index - index after popped element - 1
5. Add sentinel 0 at end to pop all remaining bars

**Complexity**: O(n) time, O(n) space

### Template

```java
public int largestRectangleArea(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    int maxArea = 0;
    int n = heights.length;

    for (int i = 0; i < n; i++) {
        // Pop taller bars and calculate area
        while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
            int h = heights[stack.pop()];
            int w = stack.isEmpty() ? i : i - stack.peek() - 1;
            maxArea = Math.max(maxArea, h * w);
        }
        stack.push(i);
    }

    // Process remaining bars
    while (!stack.isEmpty()) {
        int h = heights[stack.pop()];
        int w = stack.isEmpty() ? n : n - stack.peek() - 1;
        maxArea = Math.max(maxArea, h * w);
    }

    return maxArea;
}
```

### Visual Example

```
Heights: [2, 1, 5, 6, 2, 3]
         Indices: 0  1  2  3  4  5

i=0, h=2: stack=[0]
i=1, h=1:
    - pop 0 (h=2), w=1, area=2
    - stack=[1]
i=2, h=5: stack=[1,2]
i=3, h=6: stack=[1,2,3]
i=4, h=2:
    - pop 3 (h=6), w=1, area=6
    - pop 2 (h=5), w=2, area=10 ✓ (maximum)
    - stack=[1,4]
i=5, h=3: stack=[1,4,5]

Final:
- pop 5 (h=3), w=1, area=3
- pop 4 (h=2), w=3, area=6
- pop 1 (h=1), w=6, area=6

Maximum area = 10
```

---

## Common Mistakes

### ❌ Mistake 1: Using Values Instead of Indices

```java
// WRONG - loses position information
Stack<Integer> stack = new Stack<>();
stack.push(nums[i]);  // stores value

// CORRECT - stores indices
Stack<Integer> stack = new Stack<>();
stack.push(i);  // stores index
int value = nums[stack.peek()];  // access value when needed
```

### ❌ Mistake 2: Wrong Stack Type

```java
// WRONG - for next greater element
while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
    // This is monotonic increasing, but we need decreasing
}

// CORRECT - monotonic decreasing for next greater
while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
    stack.pop();
}
```

### ❌ Mistake 3: Not Handling Remaining Elements

```java
// WRONG - missing final processing
for (int i = 0; i < n; i++) {
    // process elements
}
// Stack may still have elements!

// CORRECT - process remaining elements
for (int i = 0; i < n; i++) {
    // process elements
}
while (!stack.isEmpty()) {
    // handle remaining elements
}
```

### ❌ Mistake 4: Circular Array Wrong Implementation

```java
// WRONG - only one iteration
for (int i = 0; i < n; i++) {
    // won't handle circular cases
}

// CORRECT - iterate twice
for (int i = 0; i < 2 * n; i++) {
    int idx = i % n;
    // handle circular
    if (i < n) stack.push(idx);  // only push in first pass
}
```

### ❌ Mistake 5: Wrong Width Calculation in Histogram

```java
// WRONG - incorrect width
int w = i - stack.peek();

// CORRECT - account for elements in between
int w = stack.isEmpty() ? i : i - stack.peek() - 1;
```

---

## Problems

- [x] [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Next Greater Node In Linked List](https://leetcode.com/problems/next-greater-node-in-linked-list/) - Medium
- [x] [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) - Medium
- [x] [Online Stock Span](https://leetcode.com/problems/online-stock-span/) - Medium
- [x] [Maximum Width Ramp](https://leetcode.com/problems/maximum-width-ramp/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) - Hard ⭐ **IMPORTANT** ⭐

### Maximum Width Ramp ⭐ **IMPORTANT** ⭐

**Problem**: [Maximum Width Ramp](https://leetcode.com/problems/maximum-width-ramp/) - Medium

**Why Important**: Non-intuitive monotonic stack application, requires understanding of building stack from left and processing from right, frequently asked

**Approach**:
1. Build monotonic decreasing stack from left to right (stores indices where values decrease)
2. Traverse array from right to left
3. For each position, pop from stack while current value >= stack top value
4. Track maximum width (current index - popped index)
5. Monotonic stack ensures we find the leftmost smaller element

**Complexity**: O(n) time, O(n) space

**Solution**:
```java
public int maxWidthRamp(int[] nums) {
    int n = nums.length;
    Stack<Integer> stack = new Stack<>();

    // Build monotonic decreasing stack (left to right)
    // Only keep indices where values strictly decrease
    for (int i = 0; i < n; i++) {
        if (stack.isEmpty() || nums[stack.peek()] > nums[i]) {
            stack.push(i);
        }
    }

    int maxWidth = 0;

    // Traverse from right to left
    for (int j = n - 1; j >= 0; j--) {
        // Pop all indices from stack where nums[i] <= nums[j]
        while (!stack.isEmpty() && nums[stack.peek()] <= nums[j]) {
            int i = stack.pop();
            maxWidth = Math.max(maxWidth, j - i);
        }
    }

    return maxWidth;
}
```

**Key Points**:
- Build stack left to right, but only push when value decreases (monotonic decreasing)
- Process from right to left to maximize width for each stack element
- Pop from stack when we find a valid ramp (nums[i] <= nums[j])
- Stack contains potential left boundaries in decreasing order
- Each element pushed and popped at most once → O(n) time
- Why traverse right to left? To find the maximum j for each i in stack

---

## Key Takeaways

1. **Two types**: Monotonic increasing (pop smaller) vs decreasing (pop larger)
2. **Store indices, not values** - need position information for calculations
3. **O(n) time** - each element pushed and popped exactly once
4. **Next greater → decreasing stack** - pop elements smaller than current
5. **Previous greater → decreasing stack** - stack top is answer before pushing
6. **Circular array → iterate 2*n** - use modulo, only push in first pass
7. **Distance problems** - calculate `i - stack.pop()` for days/steps
8. **Area problems** - width = `current - previous_in_stack - 1`
9. **Always handle remaining elements** - process stack after main loop
10. **Monotonicity determines answer** - what you pop gives you the answer

---

> **[← Back to Overview](../README.md)** | **[Trees →](../trees/Notes.md)**
