# Modified Binary Search

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Search in sorted/rotated arrays, find peaks, or optimize by searching in answer space**

### Keywords
- "rotated sorted array", "find peak/minimum/maximum"
- "minimize/maximize", "smallest/largest value that satisfies"
- "binary search on answer space"

### Examples
- Search in rotated sorted array
- Find peak/minimum in array
- Minimize maximum (ship capacity, eating speed)
- Single element in sorted array

---

## Core Concept

Binary search works on arrays that aren't strictly sorted or to find optimal values by searching in the answer space.

**Key Insight**: Binary search only needs a way to decide which half to discard, not a fully sorted array.

**Complexity**: O(log n) time, O(1) space

**Template Pattern**: Define space → While loop → Calculate mid → Decide direction → Adjust boundaries

---

## Pattern 1: Search in Rotated Sorted Array

**Algorithm**: Handle duplicates → Find which half is sorted → Check if target in sorted range → Search appropriate half

**Complexity**: O(log n) time, O(1) space (worst case O(n) with duplicates)

```java
public int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;

        // Handle duplicates - the "trap" condition
        if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
            left++;
            right--;
            continue;
        }

        // Left half sorted
        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // Right half sorted
        else {
            if (nums[mid] < target && target <= nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
    return -1;
}
```

**Key Point**: Handle duplicates first (trap condition), then identify sorted half and search accordingly.

---

## Pattern 2: Find Peak Element

**Algorithm**: Compare mid with mid+1 → If ascending, search right → Otherwise search left

**Complexity**: O(log n) time, O(1) space

```java
public int findPeakElement(int[] nums) {
    int left = 0, right = nums.length - 1;

    while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < nums[mid + 1]) {
            left = mid + 1;  // Peak on right
        } else {
            right = mid;     // Peak on left or at mid
        }
    }
    return left;
}
```

**Key Point**: Use `left < right` and `right = mid` to converge to peak without missing it.

---

## Pattern 3: Find Minimum in Rotated Array

**Algorithm**: Compare mid with right → If mid > right, min on right → Otherwise min on left or at mid

**Complexity**: O(log n) time, O(1) space

```java
public int findMin(int[] nums) {
    int left = 0, right = nums.length - 1;

    while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] > nums[right]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return nums[left];
}
```

---

## Pattern 4: Binary Search on Answer Space ⭐ **IMPORTANT** ⭐

**Algorithm**: Define answer range [min, max] → Binary search on answers → Validate each mid → Find optimal value

**Complexity**: O(log(max - min) × validation) time, O(1) space

```java
public int minimizeMaximum(int[] arr, int constraint) {
    int left = minPossible, right = maxPossible;

    while (left < right) {
        int mid = left + (right - left) / 2;
        if (canSatisfy(arr, mid, constraint)) {
            right = mid;      // Try smaller
        } else {
            left = mid + 1;   // Need larger
        }
    }
    return left;
}
```

**Key Point**: Search in solution space, not input array. If value X works, check if smaller/larger works.

---

## Pattern 5: Single Element in Sorted Array

**Algorithm**: Ensure mid at pair start (even index) → Check pair alignment → Adjust based on pattern

**Complexity**: O(log n) time, O(1) space

```java
public int singleNonDuplicate(int[] nums) {
    int left = 0, right = nums.length - 1;

    while (left < right) {
        int mid = left + (right - left) / 2;
        if (mid % 2 == 1) mid--;  // Ensure even index

        if (nums[mid] == nums[mid + 1]) {
            left = mid + 2;  // Single on right
        } else {
            right = mid;     // Single on left or at mid
        }
    }
    return nums[left];
}
```

**Key Point**: Before single element, pairs start at even indices; after, they start at odd indices.

---

## Common Mistakes

### ❌ Not Handling Duplicates in Rotated Array

```java
// WRONG - doesn't handle duplicates
if (nums[left] <= nums[mid]) {
    // Can't determine which half is sorted when all equal
}

// CORRECT - handle trap condition first
if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
    left++;
    right--;
    continue;
}
```

### ❌ Wrong Loop Condition with right = mid

```java
// WRONG - infinite loop possible
while (left <= right) {
    right = mid;  // Can loop forever
}

// CORRECT
while (left < right) {
    right = mid;  // Will converge
}
```

### ❌ Not Adjusting Mid for Pairs

```java
// WRONG - mid might be at odd index
if (nums[mid] == nums[mid + 1]) { ... }

// CORRECT - ensure mid is even
if (mid % 2 == 1) mid--;
if (nums[mid] == nums[mid + 1]) { ... }
```

### ❌ Incorrect Answer Space

```java
// WRONG - too narrow
int left = 0, right = n;

// CORRECT - proper range
int left = maxElement, right = sumOfElements;
```

### ❌ Integer Overflow in Mid Calculation

```java
// WRONG - can overflow for large arrays
int mid = (left + right) / 2;

// CORRECT - avoids overflow
int mid = left + (right - left) / 2;
```

### 💡 Tip: Integer Ceiling Division

```java
// Instead of using floating point
int result = (int) Math.ceil((double) a / b);

// Use integer arithmetic (avoids float operations)
int result = (a + b - 1) / b;
```

---

## Problems

- [x] [Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/) - Medium
- [x] [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) - Medium
- [x] [Find Peak Element](https://leetcode.com/problems/find-peak-element/) - Medium
- [x] [Single Element in a Sorted Array](https://leetcode.com/problems/single-element-in-a-sorted-array/) - Medium
- [x] [Minimum Speed to Arrive on Time](https://leetcode.com/problems/minimum-speed-to-arrive-on-time/) - Medium
- [x] [Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas) - Medium
- [x] [Find in Mountain Array](https://leetcode.com/problems/find-in-mountain-array/) - Hard
- [x] [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) - Hard  **IMPORTANT** ⭐

### Capacity To Ship Packages Within D Days ⭐ **IMPORTANT** ⭐

**Problem**: [Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/) - Medium

**Why Important**: Classic "binary search on answer space" pattern, demonstrates minimizing maximum capacity, frequently asked, non-intuitive approach

**Approach**:
1. Define answer space: [max(weights), sum(weights)]
2. Binary search on capacity values
3. For each capacity, check if can ship all packages in D days
4. If possible, try smaller capacity; otherwise need larger
5. Find minimum capacity that works

**Complexity**: O(n × log(sum - max)) time, O(1) space

**Solution**:

```java
public int shipWithinDays(int[] weights, int days) {
    int left = 0, right = 0;

    // Define answer space
    for (int weight : weights) {
        left = Math.max(left, weight);    // At least max weight
        right += weight;                   // At most sum of all
    }

    while (left < right) {
        int mid = left + (right - left) / 2;

        if (canShip(weights, mid, days)) {
            right = mid;      // Try smaller capacity
        } else {
            left = mid + 1;   // Need larger capacity
        }
    }

    return left;
}

private boolean canShip(int[] weights, int capacity, int days) {
    int daysNeeded = 1;
    int currentLoad = 0;

    for (int weight : weights) {
        if (currentLoad + weight > capacity) {
            daysNeeded++;              // Need another day
            currentLoad = weight;      // Start new day

            if (daysNeeded > days) {
                return false;          // Exceeded days limit
            }
        } else {
            currentLoad += weight;
        }
    }

    return true;
}
```

**Key Points**:
- **Not searching in the array** - searching in possible capacity values (answer space)
- **Minimize maximum** - find minimum capacity that satisfies constraint
- **Greedy validation** - pack as much as possible each day
- **Binary search pattern** - if capacity X works, all capacities > X also work (monotonic)
- Similar problems: Koko Eating Bananas, Minimum Speed to Arrive on Time, Split Array Largest Sum
- Time complexity: O(n) to validate × O(log(sum)) binary search iterations

---

### Median of Two Sorted Arrays ⭐ **IMPORTANT** ⭐

**Problem**: [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) - Hard

**Why Important**: Advanced binary search on partition point, O(log(min(m,n))) requirement, tests deep understanding of binary search invariants, frequently asked at top companies

**Approach**:
1. Binary search on smaller array to find partition point
2. Partition both arrays such that left half size = right half size
3. Ensure max(left half) ≤ min(right half)
4. Calculate median from boundary elements
5. Adjust partition based on comparison

**Complexity**: O(log(min(m, n))) time, O(1) space

**Solution**:

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // Ensure nums1 is smaller array
    if (nums1.length > nums2.length) {
        return findMedianSortedArrays(nums2, nums1);
    }

    int m = nums1.length, n = nums2.length;
    int left = 0, right = m;

    while (left <= right) {
        int partition1 = left + (right - left) / 2;
        int partition2 = (m + n + 1) / 2 - partition1;

        // Handle edge cases with Integer.MIN/MAX_VALUE
        int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
        int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

        int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
        int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

        // Found correct partition
        if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
            // Odd total length
            if ((m + n) % 2 == 1) {
                return Math.max(maxLeft1, maxLeft2);
            }
            // Even total length
            return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
        }
        // Move partition1 left
        else if (maxLeft1 > minRight2) {
            right = partition1 - 1;
        }
        // Move partition1 right
        else {
            left = partition1 + 1;
        }
    }

    throw new IllegalArgumentException("Input arrays are not sorted");
}
```

**Key Points**:
- **Binary search on smaller array** - ensures O(log(min(m,n))) complexity
- **Partition invariant** - left half size = right half size (±1 for odd)
- **Edge case handling** - use MIN/MAX_VALUE for array boundaries
- **Two conditions** - maxLeft1 ≤ minRight2 AND maxLeft2 ≤ minRight1
- **Partition formula** - partition2 = (m + n + 1) / 2 - partition1
- **Median calculation** - differs for odd/even total length
- Not a traditional "search for target" problem - searching for optimal partition point

---

## Key Takeaways

1. **Binary search needs decision rule**, not full sorted array
2. **Five patterns**: rotated array, find peak, find min, answer space, single element
3. **Rotated array**: Identify sorted half, check if target in range
4. **Answer space**: Search possible solutions, not input array (minimize/maximize problems)
5. **Use `left < right`** when converging to element (peak/min), `left <= right` for exact search
6. **Validation function** must be efficient for answer space search
7. **Monotonic property**: If X works, all values in one direction also work
8. **Typical complexity**: O(log n) time, O(1) space

---

> **[← Backtracking](../backtracking/Notes.md)** | **[Back to Overview](../README.md)** | **[Bitwise XOR →](../bitwise-xor/Notes.md)**
