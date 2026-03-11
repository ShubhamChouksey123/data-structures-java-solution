# K-way Merge

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Merge multiple sorted arrays, lists, or streams efficiently**

### Keywords
- "merge K sorted"
- "smallest range"
- "K pairs with smallest sums"
- "sorted matrix"
- "multiple sorted inputs"

### Examples
- Merge K sorted linked lists
- Find smallest range covering elements from K lists
- K pairs with smallest sums
- Kth smallest element in sorted matrix
- Merge K sorted arrays

---

## Core Concept

Use a **min-heap** to efficiently track the smallest element across K sorted inputs. Always extract the minimum element and add the next element from the same input.

**Key Insight**: Instead of comparing all K elements repeatedly (O(K) per element), use a heap to find minimum in O(log K) time.

**Complexity**: O(N log K) time, O(K) space
- N = total number of elements across all inputs
- K = number of inputs

---

## Pattern 1: Merge K Sorted Lists

**Use Case**: Merge multiple sorted lists into one sorted list

**Algorithm**:
1. Add first element from each list to min-heap
2. Extract minimum element from heap
3. Add next element from same list to heap
4. Repeat until heap is empty

**Complexity**: O(N log K) time, O(K) space

### Template

```java
public ListNode mergeKLists(ListNode[] lists) {
    Queue<ListNode> minHeap = new PriorityQueue<>(
        (ListNode a, ListNode b) -> Integer.compare(a.val, b.val)
    );

    // Add first node from each list
    for (ListNode node : lists) {
        if (node != null) {
            minHeap.offer(node);
        }
    }

    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    while (!minHeap.isEmpty()) {
        ListNode min = minHeap.poll();
        current.next = min;
        current = current.next;

        if (min.next != null) {
            minHeap.offer(min.next);
        }
    }

    return dummy.next;
}
```

### Visual Example

```
Lists: [1→4→5], [1→3→4], [2→6]

Step 1: Heap = [1(L1), 1(L2), 2(L3)]
        Extract 1(L1), add 4(L1)

Step 2: Heap = [1(L2), 2(L3), 4(L1)]
        Extract 1(L2), add 3(L2)

Step 3: Heap = [2(L3), 3(L2), 4(L1)]
        Extract 2(L3), add 6(L3)

Result: 1→1→2→3→4→4→5→6
```

---

## Pattern 2: Kth Smallest in Sorted Matrix

**Use Case**: Find Kth smallest element in row and column sorted matrix

**Algorithm**:
1. Add first element from each row to min-heap
2. Extract minimum K times
3. When extracting element at (row, col), add (row, col+1) to heap
4. Kth extracted element is the answer

**Complexity**: O(K log K) time, O(K) space

### Template

```java
public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    Queue<int[]> minHeap = new PriorityQueue<>(
        (int[] a, int[] b) -> Integer.compare(matrix[a[0]][a[1]], matrix[b[0]][b[1]])
    );

    // Add first element from each row
    for (int r = 0; r < Math.min(n, k); r++) {
        minHeap.offer(new int[]{r, 0});
    }

    int result = 0;
    for (int i = 0; i < k; i++) {
        int[] min = minHeap.poll();
        int row = min[0], col = min[1];
        result = matrix[row][col];

        // Add next element from same row
        if (col + 1 < n) {
            minHeap.offer(new int[]{row, col + 1});
        }
    }

    return result;
}
```

---

## Pattern 3: Smallest Range Covering Elements from K Lists ⭐ **IMPORTANT** ⭐

**Use Case**: Find smallest range that includes at least one element from each of K sorted lists

**Algorithm**:
1. Add first element from each list to min-heap with list index
2. Track current maximum element in heap
3. Extract minimum, calculate range [min, max]
4. Add next element from same list, update max
5. Track smallest range seen

**Complexity**: O(N log K) time, O(K) space

### Template

```java
public int[] smallestRange(List<List<Integer>> nums) {
    Queue<int[]> minHeap = new PriorityQueue<>(
        (int[] a, int[] b) -> Integer.compare(a[0], b[0])
    );

    int max = Integer.MIN_VALUE;
    int k = nums.size();

    // Add first element from each list
    for (int i = 0; i < k; i++) {
        int val = nums.get(i).get(0);
        minHeap.offer(new int[]{val, i, 0}); // {value, listIndex, elementIndex}
        max = Math.max(max, val);
    }

    int rangeStart = 0, rangeEnd = Integer.MAX_VALUE;

    while (minHeap.size() == k) {
        int[] min = minHeap.poll();
        int minVal = min[0], listIdx = min[1], elemIdx = min[2];

        // Update smallest range
        if (max - minVal < rangeEnd - rangeStart) {
            rangeStart = minVal;
            rangeEnd = max;
        }

        // Add next element from same list
        if (elemIdx + 1 < nums.get(listIdx).size()) {
            int nextVal = nums.get(listIdx).get(elemIdx + 1);
            minHeap.offer(new int[]{nextVal, listIdx, elemIdx + 1});
            max = Math.max(max, nextVal);
        }
    }

    return new int[]{rangeStart, rangeEnd};
}
```

---

## Pattern 4: K Pairs with Smallest Sums

**Use Case**: Find K pairs (one from each of two sorted arrays) with smallest sums

**Algorithm**:
1. Add pairs (nums1[i], nums2[0]) for all i to min-heap
2. Extract K pairs with smallest sums
3. When extracting (nums1[i], nums2[j]), add (nums1[i], nums2[j+1])

**Complexity**: O(K log K) time, O(K) space

### Template

```java
public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    Queue<int[]> minHeap = new PriorityQueue<>(
        (int[] a, int[] b) -> Integer.compare(a[0] + a[1], b[0] + b[1])
    );

    // Add first k pairs
    for (int i = 0; i < Math.min(k, nums1.length); i++) {
        minHeap.offer(new int[]{nums1[i], nums2[0], 0}); // {nums1[i], nums2[j], j}
    }

    List<List<Integer>> result = new ArrayList<>();
    while (k-- > 0 && !minHeap.isEmpty()) {
        int[] min = minHeap.poll();
        result.add(Arrays.asList(min[0], min[1]));

        int j = min[2];
        if (j + 1 < nums2.length) {
            minHeap.offer(new int[]{min[0], nums2[j + 1], j + 1});
        }
    }

    return result;
}
```

---

## Common Mistakes

### ❌ Mistake 1: Not Tracking Element Source

```java
// WRONG - can't add next element from same list
Queue<Integer> minHeap = new PriorityQueue<>();
minHeap.offer(nums[i][j]); // Lost track of which list this came from

// CORRECT - track source information
Queue<int[]> minHeap = new PriorityQueue<>(...);
minHeap.offer(new int[]{value, listIndex, elementIndex});
```

### ❌ Mistake 2: Adding All Elements to Heap

```java
// WRONG - O(N log N) space
for (int[] list : lists) {
    for (int val : list) {
        minHeap.offer(val);
    }
}

// CORRECT - Only K elements in heap at a time
for (int[] list : lists) {
    if (list.length > 0) {
        minHeap.offer(new int[]{list[0], listIndex, 0});
    }
}
```

### ❌ Mistake 3: Wrong Comparator for Arrays

```java
// WRONG - compares array references, not values
Queue<int[]> minHeap = new PriorityQueue<>();

// CORRECT - compare actual values
Queue<int[]> minHeap = new PriorityQueue<>(
    (int[] a, int[] b) -> Integer.compare(a[0], b[0])
);
```

### ❌ Mistake 4: Not Handling Empty Lists

```java
// WRONG - NullPointerException or empty list error
for (ListNode node : lists) {
    minHeap.offer(node); // What if node is null?
}

// CORRECT - check for null/empty
for (ListNode node : lists) {
    if (node != null) {
        minHeap.offer(node);
    }
}
```

---

## Problems

- [x] [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/) - Medium
- [x] [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) - Medium
- [x] [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) - Hard ⭐ **IMPORTANT** ⭐
- [ ] [Smallest Range Covering Elements from K Lists](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/) - Hard ⭐ **IMPORTANT** ⭐
- [ ] [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) - Easy

### Merge k Sorted Lists ⭐ **IMPORTANT** ⭐

**Problem**: [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) - Hard

**Why Important**: Foundation of K-way merge pattern, frequently asked in interviews, demonstrates efficient use of heap for merging

**Approach**:
1. Use min-heap to track smallest node across all lists
2. Add first node from each list to heap
3. Extract minimum, add to result
4. Add next node from same list to heap
5. Repeat until heap is empty

**Complexity**: O(N log K) time, O(K) space
- N = total nodes across all lists
- K = number of lists

**Solution**:

```java
public ListNode mergeKLists(ListNode[] lists) {
    // Min-heap comparing node values
    Queue<ListNode> minHeap = new PriorityQueue<>(
        (ListNode a, ListNode b) -> Integer.compare(a.val, b.val)
    );

    // Add first node from each list
    for (ListNode node : lists) {
        if (node != null) {
            minHeap.offer(node);
        }
    }

    ListNode dummy = new ListNode(0);
    ListNode current = dummy;

    // Extract minimum and add next node from same list
    while (!minHeap.isEmpty()) {
        ListNode min = minHeap.poll();
        current.next = min;
        current = current.next;

        // Add next node from same list
        if (min.next != null) {
            minHeap.offer(min.next);
        }
    }

    return dummy.next;
}
```

**Key Points**:
- Use **dummy node** to simplify result list construction
- Store **nodes** in heap, not just values (to maintain list structure)
- Check `node != null` before adding to heap
- Only K elements in heap at any time → O(K) space
- Each of N nodes added/removed once → O(N log K) time

---

### Smallest Range Covering Elements from K Lists ⭐ **IMPORTANT** ⭐

**Problem**: [Smallest Range Covering Elements from K Lists](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/) - Hard

**Why Important**: Advanced K-way merge application, requires tracking both min and max, tricky range update logic

**Approach**:
1. Use min-heap with elements from all K lists
2. Track current maximum element in heap
3. Current range = [heap_min, current_max]
4. Extract minimum, add next from same list
5. Update max if new element is larger
6. Track smallest range seen

**Complexity**: O(N log K) time, O(K) space

**Solution**:

```java
public int[] smallestRange(List<List<Integer>> nums) {
    // Min-heap: {value, listIndex, elementIndex}
    Queue<int[]> minHeap = new PriorityQueue<>(
        (int[] a, int[] b) -> Integer.compare(a[0], b[0])
    );

    int max = Integer.MIN_VALUE;
    int k = nums.size();

    // Initialize heap with first element from each list
    for (int i = 0; i < k; i++) {
        int val = nums.get(i).get(0);
        minHeap.offer(new int[]{val, i, 0});
        max = Math.max(max, val);
    }

    int rangeStart = 0, rangeEnd = Integer.MAX_VALUE;

    // Continue while all K lists are represented
    while (minHeap.size() == k) {
        int[] min = minHeap.poll();
        int minVal = min[0], listIdx = min[1], elemIdx = min[2];

        // Update smallest range if current is better
        if (max - minVal < rangeEnd - rangeStart) {
            rangeStart = minVal;
            rangeEnd = max;
        }

        // Add next element from same list
        if (elemIdx + 1 < nums.get(listIdx).size()) {
            int nextVal = nums.get(listIdx).get(elemIdx + 1);
            minHeap.offer(new int[]{nextVal, listIdx, elemIdx + 1});
            max = Math.max(max, nextVal);
        }
        // If no more elements, stop (can't cover all K lists)
    }

    return new int[]{rangeStart, rangeEnd};
}
```

**Key Points**:
- Must maintain **at least one element from each list** in consideration
- Track **max separately** (heap only gives us min efficiently)
- Stop when any list is exhausted (can't form valid range)
- Range = [current_min, current_max] where min comes from heap
- Update max only when adding new element (not when removing)

---

## Key Takeaways

1. **Use min-heap** to efficiently find smallest element across K inputs - O(log K) vs O(K)
2. **Track source information** in heap - store {value, listIndex, elementIndex}
3. **Only K elements in heap** at any time → O(K) space, not O(N)
4. **Time complexity**: O(N log K) where N = total elements, K = number of inputs
5. **Always check for null/empty** before adding to heap
6. **Use Integer.compare()** for safer integer comparison in comparators
7. **Dummy node pattern** simplifies linked list construction
8. **For range problems**: track both min (from heap) and max (separately)

---

> **[← Back to Overview](../README.md)** | **[Top 'K' Elements →](../top-k-elements/Notes.md)**
