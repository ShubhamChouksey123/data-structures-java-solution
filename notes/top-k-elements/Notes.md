# Top 'K' Elements

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Find the K largest/smallest, K most/least frequent, or K closest elements using heaps or quickselect**

### Keywords
- "K largest / K smallest"
- "top K frequent"
- "K closest points"
- "Kth largest / Kth smallest"

### Examples
- K largest elements in an unsorted array
- K most frequent elements
- K closest points to origin
- Kth largest element

---

## Core Concept

Use a **heap of size K** as a "gatekeeper" — keep removing the unwanted extreme so only the top K survive.

**Key Rule** (counter-intuitive):
- **K largest** → **min-heap** of size K (poll the smallest)
- **K smallest** → **max-heap** of size K (poll the largest)

**Complexity**:
- Heap: O(n log k) time, O(k) space
- Quickselect: O(n) average time, O(1) space

---

## Pattern 1: Kth Largest Element ⭐ **IMPORTANT** ⭐

**⚠️ Key Pattern - Review Regularly**

**Use Case**: Kth largest element in an unsorted array

**Algorithm**:
1. Maintain a min-heap of size K
2. Offer each element; if size exceeds K, poll the smallest
3. The heap top is the Kth largest

**Complexity**: O(n log k) time, O(k) space

**Why Important**: Foundational pattern; demonstrates the counter-intuitive min-heap-for-largest insight; frequently asked.

### Template

```java
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (int num : nums) {
        minHeap.offer(num);
        if (minHeap.size() > k) {
            minHeap.poll();  // drop smallest, keep K largest
        }
    }
    return minHeap.peek();  // Kth largest
}
```

---

## Pattern 2: K Most Frequent Elements

**Use Case**: K elements that appear most often

**Algorithm**:
1. Count frequencies in a HashMap
2. Min-heap of size K with comparator **by frequency**: `(a, b) -> freq.get(a) - freq.get(b)`
3. Offer each key; poll when size exceeds K (removes least frequent)
4. Remaining heap holds the K most frequent

**Complexity**: O(n log k) time, O(n) space

### Visual Example

```
Array: [1,1,1,2,2,3], k = 2   →   freq = {1:3, 2:2, 3:1}

Min-heap by freq (size 2):
  offer 1 → [1]
  offer 2 → [2,1]          (2 lower freq, sits on top)
  offer 3 → size>2, poll 3 → [2,1]

Result: {1, 2}  (top 2 frequent)
```

---

## Pattern 3: Ugly Number II

**Use Case**: Find the nth ugly number (positive integers whose only prime factors are 2, 3, 5)

**Algorithm** (min-heap, generate in order):
1. Start with `1` in a min-heap; use a set to skip duplicates
2. Poll the smallest n times — that's the next ugly number each round
3. On each poll, push `val*2`, `val*3`, `val*5` (if unseen)
4. The nth polled value is the answer

**Complexity**: O(n log n) time, O(n) space

### Visual Example

```
heap: [1]                      poll 1 → push 2,3,5
heap: [2,3,5]                  poll 2 → push 4,6,10
heap: [3,4,5,6,10]             poll 3 → push 6(seen),9,15
heap: [4,5,6,9,10,15]          poll 4 → ...

Sequence: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, ...
```

**Key Point**: A min-heap yields ugly numbers in sorted order. The O(n) alternative uses **three pointers** (i2, i3, i5) into the result array, taking `min(res[i2]*2, res[i3]*3, res[i5]*5)` each step — no heap or set needed.

---

## Pattern 4: Quickselect (Alternative)

**Use Case**: Only need the Kth element (not all K), and can modify the array

**Algorithm** (partition-based selection):
1. Partition around a pivot (Lomuto/Hoare); pivot lands at its sorted index
2. If pivot index == target, return it
3. Else recurse only into the side containing the target

**Complexity**: O(n) average, O(n²) worst, O(1) space

**When to prefer**: single Kth value, in-place allowed, average O(n) acceptable. Otherwise use the heap (guaranteed O(n log k)).

---

## Common Mistakes

### ❌ Mistake 1: Wrong Heap Type

```java
// WRONG - max-heap for K largest keeps the K SMALLEST
maxHeap.offer(num);
if (maxHeap.size() > k) maxHeap.poll();  // drops largest ✗

// CORRECT - min-heap keeps the K largest
minHeap.offer(num);
if (minHeap.size() > k) minHeap.poll();  // drops smallest ✓
```

- K **largest** → **min-heap**
- K **smallest** → **max-heap**

### ❌ Mistake 2: Peeking an Empty Heap

Guard size before comparing against `heap.peek()` (e.g. `if (heap.size() < k) offer; else if (...)`) — peeking an empty heap returns `null`.

### ❌ Mistake 3: Comparator on the Wrong Field

For custom objects (e.g. points), compare the full key — full distance `x² + y²`, not just `x[0]`. Comparing one field gives the wrong ordering.

---

## Problems

- [x] [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) - Medium
- [x] [Ugly Number II](https://leetcode.com/problems/ugly-number-ii/) - Medium
- [x] [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) - Medium

---

## Key Takeaways

1. **Heap choice is opposite**: K largest → min-heap, K smallest → max-heap
2. **Heap = gatekeeper**: poll the unwanted extreme to keep size at K
3. **Complexity**: heap O(n log k); quickselect O(n) average
4. **Custom objects**: supply a comparator over the full key (e.g. squared distance for points)
5. **Min-heap generates in sorted order** (Ugly Number II); a multi-pointer DP can replace it for O(n)
6. **Quickselect** when you only need the Kth element and can mutate the array

---

> **[← Back to Overview](../README.md)** | **[Bitwise XOR ←](../bitwise-xor/Notes.md)** | **[K-way Merge →](../k-way-merge/Notes.md)**
