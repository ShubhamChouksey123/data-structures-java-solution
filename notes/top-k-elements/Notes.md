# Top 'K' Elements

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Find the K largest/smallest elements, K most/least frequent elements, or K closest elements using heaps or quickselect**

### Keywords
- "K largest elements"
- "K smallest elements"
- "top K frequent"
- "K closest points"
- "Kth largest"
- "Kth smallest"

### Examples
- Find K largest elements in an unsorted array
- Find K most frequent elements
- Find K closest points to origin
- Find Kth largest element
- Merge K sorted lists

---

## Core Concept

Use a **heap** (priority queue) to efficiently maintain the top K elements. The key insight is choosing the right heap type:

**Key Rule**:
- **K largest** → Use **min-heap of size K** (keep removing smallest)
- **K smallest** → Use **max-heap of size K** (keep removing largest)

**Why?** The heap acts as a "gatekeeper" - for K largest, the min-heap keeps the K largest by removing smaller elements.

**Complexity**:
- Heap approach: O(n log k) time, O(k) space
- Quickselect approach: O(n) average time, O(1) space

---

## Pattern 1: Kth Largest Element ⭐ **IMPORTANT** ⭐

**⚠️ Key Pattern - Review Regularly**

**Use Case**: Find the Kth largest element in an unsorted array

**Algorithm**:
1. Use min-heap of size K
2. Add first K elements to heap
3. For remaining elements, if element > heap.peek(), remove min and add element
4. Return heap.peek() (Kth largest)

**Complexity**: O(n log k) time, O(k) space

**Why Important**: Most fundamental pattern, demonstrates min-heap for K largest (counter-intuitive), frequently asked

### Template

```java
// Min-heap approach (most common)
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    // Add first k elements
    for (int i = 0; i < k; i++) {
        minHeap.offer(nums[i]);
    }

    // For remaining elements, maintain heap of size k
    for (int i = k; i < nums.length; i++) {
        if (nums[i] > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(nums[i]);
        }
    }

    return minHeap.peek();  // Kth largest
}
```

---

## Pattern 2: K Most Frequent Elements

**Use Case**: Find K elements that appear most frequently

**Algorithm**:
1. Count frequency of each element using HashMap
2. Use min-heap of size K with custom comparator (by frequency)
3. Maintain K most frequent elements
4. Return heap elements

**Complexity**: O(n log k) time, O(n) space

### Template

```java
public int[] topKFrequent(int[] nums, int k) {
    // Step 1: Count frequencies
    Map<Integer, Integer> freqMap = new HashMap<>();
    for (int num : nums) {
        freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
    }

    // Step 2: Min-heap of size k (by frequency)
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(
        (a, b) -> freqMap.get(a) - freqMap.get(b)
    );

    // Step 3: Maintain k most frequent
    for (int num : freqMap.keySet()) {
        minHeap.offer(num);
        if (minHeap.size() > k) {
            minHeap.poll();  // Remove least frequent
        }
    }

    // Step 4: Extract result
    int[] result = new int[k];
    for (int i = 0; i < k; i++) {
        result[i] = minHeap.poll();
    }

    return result;
}
```

### Visual Example

```
Array: [1, 1, 1, 2, 2, 3], k = 2

Step 1: Frequency map
{1: 3, 2: 2, 3: 1}

Step 2: Min-heap by frequency (size 2)
- Add 1 (freq=3): Heap: [1]
- Add 2 (freq=2): Heap: [2, 1] (2 has lower freq)
- Add 3 (freq=1): Heap: [3, 1] → size > 2, remove 3

Result: [2, 1] (top 2 frequent)
```

---

## Pattern 3: K Closest Points to Origin

**Use Case**: Find K points closest to origin (0, 0) by Euclidean distance

**Algorithm**:
1. Use max-heap of size K (by distance)
2. For each point, calculate distance
3. If heap size < K, add point
4. Else if distance < max distance, remove max and add point

**Complexity**: O(n log k) time, O(k) space

### Template

```java
public int[][] kClosest(int[][] points, int k) {
    // Max-heap by distance (for k closest, use max-heap)
    Queue<int[]> maxHeap = new PriorityQueue<>(
        (int[] a, int[] b) -> {
            return Integer.compare((b[0] * b[0]) + b[1] * b[1], (a[0] * a[0] + a[1] * a[1]));
        }
    );

    for (int[] point : points) {
        maxHeap.offer(point);

        if (maxHeap.size() > k) {
            maxHeap.poll();  // Remove farthest point
        }
    }

    // Extract result
    int[][] result = new int[k][2];
    for (int i = 0; i < k; i++) {
        result[i] = maxHeap.poll();
    }

    return result;
}
```

**Key Point**: For K **closest**, use **max-heap** - keep removing the **farthest** point to maintain K closest.

---

## Pattern 4: Quickselect (Alternative Approach)

**Use Case**: Find Kth largest/smallest in average O(n) time

**Algorithm** (Partition-based selection):
1. Choose pivot and partition array
2. If pivot position == k, return pivot
3. If pivot position > k, search left
4. If pivot position < k, search right

**Complexity**: O(n) average, O(n²) worst case time, O(1) space

### Template

```java
public int findKthLargest(int[] nums, int k) {
    return quickSelect(nums, 0, nums.length - 1, nums.length - k);
}

private int quickSelect(int[] nums, int left, int right, int k) {
    if (left == right) return nums[left];

    // Partition
    int pivotIndex = partition(nums, left, right);

    if (pivotIndex == k) {
        return nums[k];
    } else if (pivotIndex < k) {
        return quickSelect(nums, pivotIndex + 1, right, k);
    } else {
        return quickSelect(nums, left, pivotIndex - 1, k);
    }
}

private int partition(int[] nums, int left, int right) {
    int pivot = nums[right];
    int i = left;

    for (int j = left; j < right; j++) {
        if (nums[j] < pivot) {
            swap(nums, i, j);
            i++;
        }
    }

    swap(nums, i, right);
    return i;
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

**When to Use**:
- When you only need the Kth element (not all K elements)
- When you can modify the input array
- When average O(n) is preferred over guaranteed O(n log k)

---

## Common Mistakes

### ❌ Mistake 1: Wrong Heap Type

```java
// WRONG - Using max-heap for K largest
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
for (int num : nums) {
    maxHeap.offer(num);
    if (maxHeap.size() > k) {
        maxHeap.poll();  // Removes largest, keeps K smallest!
    }
}

// CORRECT - Use min-heap for K largest
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int num : nums) {
    minHeap.offer(num);
    if (minHeap.size() > k) {
        minHeap.poll();  // Removes smallest, keeps K largest ✓
    }
}
```

**Remember**:
- K **largest** → **min-heap** (remove smallest)
- K **smallest** → **max-heap** (remove largest)

---

### ❌ Mistake 2: Not Checking Heap Size Before Polling

```java
// WRONG - Can cause issues if k > nums.length
for (int num : nums) {
    if (num > minHeap.peek()) {  // NullPointerException if heap empty!
        minHeap.poll();
        minHeap.offer(num);
    }
}

// CORRECT - Check size first
for (int num : nums) {
    if (minHeap.size() < k) {
        minHeap.offer(num);
    } else if (num > minHeap.peek()) {
        minHeap.poll();
        minHeap.offer(num);
    }
}
```

---

### ❌ Mistake 3: Wrong Comparator for Custom Objects

```java
// WRONG - Comparing wrong values
PriorityQueue<int[]> heap = new PriorityQueue<>(
    (a, b) -> a[0] - b[0]  // Only compares x-coordinate!
);

// CORRECT - Compare distances
PriorityQueue<int[]> heap = new PriorityQueue<>(
    (a, b) -> (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1])
);
```

---

## Problems

- [x] [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/) - Medium
- [x] [Ugly Number II](https://leetcode.com/problems/ugly-number-ii/) - Medium
- [x] [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) - Medium


## Key Takeaways

1. **Heap Choice**: K **largest** → **min-heap**, K **smallest** → **max-heap** (opposite!)
2. **Time Complexity**: Heap = O(n log k), Quickselect = O(n) average
3. **Space Efficiency**: Heap uses O(k) space, very efficient
4. **Custom Comparator**: Use lambda `(a, b) -> compare(a, b)` for custom objects
5. **Heap as Gatekeeper**: Heap "guards" the top K by removing unwanted elements
6. **Check Size**: Always verify heap size before polling to avoid errors
7. **When to Use Heap**: Finding top K from stream, maintaining sliding window of K elements
8. **When to Use Quickselect**: Only need Kth element, can modify array, want O(n) average

---

> **[← Back to Overview](../README.md)** | **[Bitwise XOR ←](../bitwise-xor/Notes.md)** | **[K-way Merge →](../k-way-merge/Notes.md)**
