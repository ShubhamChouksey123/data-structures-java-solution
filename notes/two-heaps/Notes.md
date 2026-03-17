# Two Heaps

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Track median or balance elements between two halves of a dataset dynamically**

### Keywords
- "find median"
- "data stream"
- "sliding window median"
- "balance two halves"
- "dynamic median"
- "maximize/minimize with constraints"

### Examples
- Find median from data stream
- Sliding window median
- IPO problem (maximize capital)
- Balance workload between two groups

---

## Core Concept

Use **two heaps** to partition data into two halves:
- **Max heap**: stores smaller half (largest element on top)
- **Min heap**: stores larger half (smallest element on top)

**Key Insight**: Top of max heap and top of min heap give access to middle elements, enabling O(1) median calculation.

**Complexity**: O(log n) insertion, O(1) median retrieval

---

## Pattern 1: Find Median from Data Stream ⭐ **IMPORTANT** ⭐

**Use Case**: Track median of numbers as they arrive one by one

**Algorithm**:
1. Maintain two heaps: maxHeap (smaller half) and minHeap (larger half)
2. Balance heaps: maxHeap.size() = minHeap.size() or maxHeap.size() = minHeap.size() + 1
3. For insertion:
   - Add to maxHeap if number ≤ maxHeap.peek() (or maxHeap is empty)
   - Otherwise add to minHeap
   - Rebalance if size difference > 1
4. For median:
   - If sizes equal: (maxHeap.peek() + minHeap.peek()) / 2.0
   - Otherwise: maxHeap.peek()

**Complexity**: O(log n) insertion, O(1) median retrieval

### Template

```java
class MedianFinder {
    Queue<Integer> maxHeap; // smaller half (max on top)
    Queue<Integer> minHeap; // larger half (min on top)

    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // max heap
        minHeap = new PriorityQueue<>(); // min heap
    }

    public void addNum(int num) {
        // Add to maxHeap first
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // Balance heaps
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }
}
```


---

## Pattern 2: Sliding Window Median

**Use Case**: Find median in a sliding window of size k

**Algorithm**:
1. Use two heaps like Pattern 1
2. Track elements in current window using HashMap for removal
3. For each window:
   - Add new element to appropriate heap
   - Remove old element (lazy deletion with HashMap)
   - Rebalance heaps
   - Calculate median

**Complexity**: O(n log k) time, O(k) space

### Key Differences from Pattern 1

- **Lazy deletion**: Mark elements for removal in HashMap, remove when at heap top
- **Window tracking**: Need to know which elements to remove
- **Rebalancing**: Must account for "logically removed" elements

### Template

```java
public double[] medianSlidingWindow(int[] nums, int k) {
    Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    Queue<Integer> minHeap = new PriorityQueue<>();
    Map<Integer, Integer> toRemove = new HashMap<>(); // lazy deletion map
    double[] result = new double[nums.length - k + 1];

    // Initialize first window
    for (int i = 0; i < k; i++) {
        maxHeap.offer(nums[i]);
    }

    // Move half to minHeap to balance
    for (int i = 0; i < k / 2; i++) {
        minHeap.offer(maxHeap.poll());
    }

    // Process each window
    for (int i = k; i <= nums.length; i++) {
        // Get median for current window
        result[i - k] = getMedian(maxHeap, minHeap, k);

        if (i == nums.length) break;

        // Element to remove (going out of window)
        int toRemoveNum = nums[i - k];
        // Element to add (coming into window)
        int toAddNum = nums[i];

        // Track balance change
        int balance = 0;

        // Mark element for removal (lazy deletion)
        toRemove.put(toRemoveNum, toRemove.getOrDefault(toRemoveNum, 0) + 1);

        // Update balance based on which heap the removed element was in
        if (toRemoveNum <= maxHeap.peek()) {
            balance--;
        } else {
            balance++;
        }

        // Add new element to appropriate heap
        if (maxHeap.isEmpty() || toAddNum <= maxHeap.peek()) {
            maxHeap.offer(toAddNum);
            balance++;
        } else {
            minHeap.offer(toAddNum);
            balance--;
        }

        // Rebalance if needed
        if (balance < 0) {
            maxHeap.offer(minHeap.poll());
        } else if (balance > 0) {
            minHeap.offer(maxHeap.poll());
        }

        // Clean up heaps (remove expired elements from tops)
        cleanHeap(maxHeap, toRemove);
        cleanHeap(minHeap, toRemove);
    }

    return result;
}

private void cleanHeap(Queue<Integer> heap, Map<Integer, Integer> toRemove) {
    while (!heap.isEmpty() && toRemove.containsKey(heap.peek())) {
        int num = heap.poll();
        int count = toRemove.get(num);
        if (count == 1) {
            toRemove.remove(num);
        } else {
            toRemove.put(num, count - 1);
        }
    }
}

private double getMedian(Queue<Integer> maxHeap, Queue<Integer> minHeap, int k) {
    if (k % 2 == 1) {
        return (double) maxHeap.peek();
    }
    return ((long) maxHeap.peek() + (long) minHeap.peek()) / 2.0;
}
```

---

## Pattern 3: IPO (Maximize Capital)

**Use Case**: Select up to k projects to maximize capital, where each project has capital requirement and profit

**Algorithm**:
1. Use min heap for available projects (sorted by capital requirement)
2. Use max heap for projects we can afford (sorted by profit)
3. For each of k iterations:
   - Move all affordable projects from minHeap to maxHeap
   - Pick project with maximum profit from maxHeap
   - Add profit to current capital

**Complexity**: O(n log n) time, O(n) space

### Template

```java
public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    // Min heap: projects sorted by capital requirement
    Queue<int[]> minHeap = new PriorityQueue<>(
        (a, b) -> Integer.compare(a[0], b[0])
    );

    // Max heap: affordable projects sorted by profit
    Queue<Integer> maxHeap = new PriorityQueue<>(
        (a, b) -> Integer.compare(b, a)
    );

    // Add all projects to minHeap
    for (int i = 0; i < profits.length; i++) {
        minHeap.offer(new int[]{capital[i], profits[i]});
    }

    int currentCapital = w;
    for (int i = 0; i < k; i++) {
        // Move all affordable projects to maxHeap
        while (!minHeap.isEmpty() && minHeap.peek()[0] <= currentCapital) {
            int[] project = minHeap.poll();
            maxHeap.offer(project[1]); // add profit
        }

        if (maxHeap.isEmpty()) break;
        currentCapital += maxHeap.poll();
    }

    return currentCapital;
}
```

---

## Common Mistakes

### ❌ Mistake 1: Wrong Heap Comparator

```java
// WRONG - both are min heaps
Queue<Integer> maxHeap = new PriorityQueue<>();
Queue<Integer> minHeap = new PriorityQueue<>();

// CORRECT - maxHeap has reversed comparator
Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
Queue<Integer> minHeap = new PriorityQueue<>();
```

### ❌ Mistake 2: Not Balancing Heaps

```java
// WRONG - heaps can become very unbalanced
public void addNum(int num) {
    if (num < maxHeap.peek()) {
        maxHeap.offer(num);
    } else {
        minHeap.offer(num);
    }
    // Missing rebalancing logic!
}

// CORRECT - always rebalance after insertion
public void addNum(int num) {
    // ... add logic ...

    if (maxHeap.size() > minHeap.size() + 1) {
        minHeap.offer(maxHeap.poll());
    } else if (minHeap.size() > maxHeap.size()) {
        maxHeap.offer(minHeap.poll());
    }
}
```

### ❌ Mistake 3: Integer Overflow in Median Calculation

```java
// WRONG - can overflow with large integers
return (maxHeap.peek() + minHeap.peek()) / 2;

// CORRECT - use double to avoid overflow
return (maxHeap.peek() + minHeap.peek()) / 2.0;
```

### ❌ Mistake 4: Not Handling Empty Heaps

```java
// WRONG - NullPointerException if maxHeap is empty
public void addNum(int num) {
    if (num < maxHeap.peek()) {  // maxHeap might be empty!
        maxHeap.offer(num);
    }
}

// CORRECT - check for empty heap
public void addNum(int num) {
    if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
        maxHeap.offer(num);
    } else {
        minHeap.offer(num);
    }
}
```

### ❌ Mistake 5: Removing Elements Immediately in Sliding Window

```java
// WRONG - O(k) to find and remove element from heap
public void removeElement(Queue<Integer> heap, int num) {
    heap.remove(num);  // Linear time operation!
}

// CORRECT - use lazy deletion with HashMap
Map<Integer, Integer> toRemove = new HashMap<>();
toRemove.put(num, toRemove.getOrDefault(num, 0) + 1);

// Clean only when element is at top
private void cleanHeap(Queue<Integer> heap, Map<Integer, Integer> toRemove) {
    while (!heap.isEmpty() && toRemove.containsKey(heap.peek())) {
        int val = heap.poll();
        if (toRemove.get(val) == 1) {
            toRemove.remove(val);
        } else {
            toRemove.put(val, toRemove.get(val) - 1);
        }
    }
}
```

---

## Problems

- [x] [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/) - Hard ⭐ **IMPORTANT** ⭐
- [x] [Sliding Window Median](https://leetcode.com/problems/sliding-window-median/) - Hard
- [x] [IPO](https://leetcode.com/problems/ipo/) - Hard


## Key Takeaways

1. **Two heaps partition data** - max heap for smaller half, min heap for larger half
2. **Balance is crucial** - maintain size difference ≤ 1
3. **Max heap on left, min heap on right** - tops give access to middle elements
4. **O(log n) insertion, O(1) median** - efficient for streaming data
5. **Use Queue interface** with PriorityQueue for heap implementation
6. **Reverse comparator for max heap** - `(a, b) -> Integer.compare(b, a)`
7. **Check empty before peek()** - avoid NullPointerException
8. **Use 2.0 in division** - avoid integer division and overflow
9. **Lazy deletion for sliding window** - use HashMap to mark elements for removal, clean from heap tops
10. **Use long for large sums** - prevent overflow when calculating median with large integers

---

> **[← Back to Overview](../README.md)** | **[K-way Merge →](../k-way-merge/Notes.md)**
