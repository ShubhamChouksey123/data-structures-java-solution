# Java Array Concepts

## Core Characteristics

### Fixed Size & Contiguous Memory

**Key Points**:
- **Fixed size**: Cannot grow or shrink after creation
- **Contiguous memory**: Elements stored in consecutive memory locations
- **Index-based**: O(1) access to any element by index
- **Homogeneous**: All elements must be same type
- **Zero-indexed**: First element at index 0

**Benefits**:
- Fast random access: O(1)
- Cache-friendly (memory locality)
- Predictable memory usage
- Low overhead

**Drawbacks**:
- Fixed size (can't resize)
- Costly insertion/deletion (requires shifting)
- Wasted space if not fully utilized

---

## Array Creation & Initialization

### Declaration and Creation

```
// Declaration
int[] arr;                    // Preferred style
int arr[];                    // Alternative (C-style)

// Creation with size
int[] arr = new int[5];       // [0, 0, 0, 0, 0] (default values)

// Creation with values
int[] arr = {1, 2, 3, 4, 5};  // Array literal
int[] arr = new int[]{1, 2, 3, 4, 5};  // Explicit
```

### Default Values

| Type | Default Value |
|------|---------------|
| `int`, `short`, `byte`, `long` | `0` |
| `float`, `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` (null character) |
| Object references | `null` |

### Common Creation Patterns

```
// Empty array
int[] arr = new int[0];

// Copy array
int[] copy = arr.clone();
int[] copy = Arrays.copyOf(arr, arr.length);

// Copy range
int[] range = Arrays.copyOfRange(arr, start, end);

// Fill with value
int[] arr = new int[5];
Arrays.fill(arr, 10);  // [10, 10, 10, 10, 10]
```

---

## Array vs ArrayList

| Feature | Array | ArrayList |
|---------|-------|-----------|
| **Size** | Fixed | Dynamic (auto-resize) |
| **Type** | Primitives or Objects | Objects only (uses boxing) |
| **Performance** | Faster (no overhead) | Slower (resizing, boxing) |
| **Syntax** | `arr[i]` | `list.get(i)` |
| **Length** | `arr.length` (field) | `list.size()` (method) |
| **Type Safety** | Compile-time | Compile-time (with generics) |
| **Memory** | Less overhead | More overhead (capacity) |

### When to Use

**Array**:
- Size is known and fixed
- Need primitive types (performance)
- Simple use case
- Memory efficiency important

**ArrayList**:
- Size changes dynamically
- Need List operations (add, remove)
- Want convenience methods
- Size flexibility > performance

---

## Common Operations & Complexity

### Time Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **Access** `arr[i]` | O(1) | Direct index access |
| **Search** (unsorted) | O(n) | Linear scan required |
| **Search** (sorted) | O(log n) | Binary search |
| **Insert** at end | O(1) | If space available |
| **Insert** at position | O(n) | Requires shifting elements |
| **Delete** from end | O(1) | Just decrement size |
| **Delete** from position | O(n) | Requires shifting elements |
| **Update** `arr[i] = x` | O(1) | Direct access |
| **Traverse** | O(n) | Visit each element |
| **Sort** | O(n log n) | Best comparison sort |

### Space Complexity

- **Array**: O(n) - exactly n elements
- **Operations**: Usually O(1) extra space (in-place)

---

## Multi-Dimensional Arrays

### 2D Arrays

```
// Declaration and creation
int[][] matrix = new int[3][4];        // 3 rows, 4 columns
int[][] matrix = {{1, 2}, {3, 4}};     // Array literal

// Access
int value = matrix[row][col];

// Dimensions
int rows = matrix.length;              // Number of rows
int cols = matrix[0].length;           // Number of columns (first row)

// Jagged array (different column sizes)
int[][] jagged = new int[3][];
jagged[0] = new int[2];
jagged[1] = new int[3];
jagged[2] = new int[1];
```

### Common Traversal Patterns

**Row-wise traversal**:
```
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        process(matrix[i][j]);
    }
}
```

**Column-wise traversal**:
```
for (int j = 0; j < matrix[0].length; j++) {
    for (int i = 0; i < matrix.length; i++) {
        process(matrix[i][j]);
    }
}
```

**Enhanced for loop**:
```
for (int[] row : matrix) {
    for (int val : row) {
        process(val);
    }
}
```

---

## Array Manipulation Techniques

### Reversing

```
// In-place reversal - O(n) time, O(1) space
int left = 0, right = arr.length - 1;
while (left < right) {
    // Swap
    int temp = arr[left];
    arr[left] = arr[right];
    arr[right] = temp;
    left++;
    right--;
}
```

### Rotating

```
// Rotate right by k positions
// Approach: Reverse entire, reverse first k, reverse remaining
1. Reverse entire array
2. Reverse first k elements
3. Reverse remaining elements
```

### Partitioning

```
// Partition around pivot (used in QuickSort)
// Two-pointer technique
int left = 0, right = arr.length - 1;
while (left < right) {
    while (arr[left] < pivot) left++;
    while (arr[right] > pivot) right--;
    if (left < right) swap(arr, left, right);
}
```

---

## Arrays Utility Class

### Common Methods

| Method | Description | Complexity |
|--------|-------------|------------|
| `Arrays.sort(arr)` | Sorts array | O(n log n) |
| `Arrays.binarySearch(arr, key)` | Binary search (sorted array) | O(log n) |
| `Arrays.fill(arr, val)` | Fill with value | O(n) |
| `Arrays.copyOf(arr, len)` | Copy array | O(n) |
| `Arrays.copyOfRange(arr, from, to)` | Copy range | O(n) |
| `Arrays.equals(arr1, arr2)` | Compare arrays | O(n) |
| `Arrays.toString(arr)` | String representation | O(n) |
| `Arrays.stream(arr)` | Create stream | O(1) |
| `Arrays.asList(arr)` | Convert to List | O(1) |

### Important Notes

**Arrays.sort()**:
- Primitives: DualPivotQuicksort (O(n log n) average, O(n²) worst)
- Objects: TimSort (O(n log n), stable)
- Can provide custom comparator for objects

**Arrays.binarySearch()**:
- Array **must be sorted** first
- Returns index if found, negative value if not found
- Negative value = `-(insertion_point) - 1`

**Arrays.asList()**:
- Returns fixed-size list (backed by array)
- Cannot add/remove elements
- Can modify existing elements
- Changes reflect in original array

---

## Common Array Patterns

### 1. Two Pointers

**Use Cases**:
- Sorted array problems
- Pair sum problems
- Palindrome checking
- Remove duplicates

**Template**:
```
int left = 0, right = arr.length - 1;
while (left < right) {
    if (condition) {
        // Process
        left++;
    } else {
        right--;
    }
}
```

### 2. Sliding Window

**Use Cases**:
- Subarray problems
- Maximum/minimum in window
- Finding patterns in consecutive elements

**Template**:
```
int windowStart = 0;
for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
    // Add arr[windowEnd] to window

    while (window needs shrinking) {
        // Remove arr[windowStart] from window
        windowStart++;
    }

    // Update result
}
```

### 3. Prefix Sum

**Use Cases**:
- Range sum queries
- Subarray sum problems
- Equilibrium index

**Template**:
```
int[] prefix = new int[arr.length + 1];
for (int i = 0; i < arr.length; i++) {
    prefix[i + 1] = prefix[i] + arr[i];
}
// Sum from i to j = prefix[j+1] - prefix[i]
```

### 4. Fast & Slow Pointers

**Use Cases**:
- Finding cycle
- Finding middle element
- Removing nth element from end

### 5. Kadane's Algorithm

**Use Case**: Maximum subarray sum

**Template**:
```
int maxSoFar = arr[0];
int maxEndingHere = arr[0];

for (int i = 1; i < arr.length; i++) {
    maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
    maxSoFar = Math.max(maxSoFar, maxEndingHere);
}
```

### 6. Dutch National Flag

**Use Case**: Partition into 3 parts (0s, 1s, 2s)

**Template**:
```
int low = 0, mid = 0, high = arr.length - 1;
while (mid <= high) {
    if (arr[mid] == 0) {
        swap(arr, low++, mid++);
    } else if (arr[mid] == 1) {
        mid++;
    } else {
        swap(arr, mid, high--);
    }
}
```

---

## Common Gotchas & Best Practices

### 1. Index Out of Bounds

**❌ WRONG**:
```
int[] arr = new int[5];
int x = arr[5];  // IndexOutOfBoundsException (valid: 0-4)
```

**✅ CORRECT**:
```
if (i >= 0 && i < arr.length) {
    int x = arr[i];
}
```

### 2. Array Assignment

**Gotcha**:
```
int[] arr1 = {1, 2, 3};
int[] arr2 = arr1;  // Copies reference, NOT array!
arr2[0] = 99;       // arr1[0] is now also 99
```

**Correct copying**:
```
int[] arr2 = arr1.clone();
int[] arr2 = Arrays.copyOf(arr1, arr1.length);
```

### 3. Array Comparison

**❌ WRONG**:
```
int[] arr1 = {1, 2, 3};
int[] arr2 = {1, 2, 3};
arr1 == arr2  // false (compares references)
```

**✅ CORRECT**:
```
Arrays.equals(arr1, arr2)  // true (compares content)
```

### 4. Modified Array in Enhanced For Loop

**❌ WRONG**:
```
for (int x : arr) {
    x = 10;  // Doesn't modify array, only local variable
}
```

**✅ CORRECT**:
```
for (int i = 0; i < arr.length; i++) {
    arr[i] = 10;  // Modifies array
}
```

### 5. Arrays.asList() Limitations

**❌ WRONG**:
```
List<Integer> list = Arrays.asList(1, 2, 3);
list.add(4);  // UnsupportedOperationException (fixed-size)
```

**✅ CORRECT**:
```
List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
list.add(4);  // Works
```

### 6. Primitive Arrays to List

**❌ WRONG**:
```
int[] arr = {1, 2, 3};
List<Integer> list = Arrays.asList(arr);  // Creates List<int[]>, not List<Integer>
```

**✅ CORRECT**:
```
int[] arr = {1, 2, 3};
List<Integer> list = Arrays.stream(arr)
                           .boxed()
                           .collect(Collectors.toList());
```

---

## Interview Tips

### Time Complexity Optimization

| Approach | Complexity | Use Case |
|----------|------------|----------|
| **Brute Force** | O(n²) or O(n³) | Check all pairs/triplets |
| **Sorting** | O(n log n) | Enables binary search, two pointers |
| **Hash Map** | O(n) | Trade space for time |
| **Two Pointers** | O(n) | Sorted array optimization |
| **Sliding Window** | O(n) | Contiguous subarray problems |

### Space Complexity Reduction

**Instead of creating new array**:
- Use two pointers for in-place operations
- Swap elements instead of copying
- Use constant extra space

**Trade-offs**:
- HashMap: O(n) space for O(1) lookup
- Sorting: O(1) space but modifies array
- Prefix sum: O(n) space for O(1) range queries

### Common Problem Types

1. **Searching**: Binary search, two pointers
2. **Sorting**: QuickSort, MergeSort, custom comparators
3. **Subarray**: Sliding window, Kadane's algorithm
4. **Range queries**: Prefix sum, segment tree
5. **Partitioning**: Dutch National Flag, QuickSelect
6. **Matrix**: BFS/DFS, dynamic programming
7. **Intervals**: Merge intervals, interval scheduling

---

## Best Practices

1. **Check bounds** before accessing array elements
2. **Use Arrays.toString()** for debugging (not arr.toString())
3. **Clone or copy** when you need independent array
4. **Use enhanced for loop** for read-only iteration
5. **Use Arrays.equals()** for content comparison
6. **Consider ArrayList** if size is dynamic
7. **Sort before binary search** - binarySearch requires sorted array
8. **Use appropriate data structure**:
   - Fixed size → Array
   - Dynamic size → ArrayList
   - Frequent lookups → HashMap/HashSet
   - Order matters → LinkedList/Deque

---

## Common Array Problems

### Easy
- Two Sum
- Remove Duplicates from Sorted Array
- Move Zeroes
- Best Time to Buy and Sell Stock

### Medium
- Three Sum
- Container With Most Water
- Product of Array Except Self
- Maximum Subarray (Kadane's)
- Rotate Array
- Merge Intervals

### Hard
- Trapping Rain Water
- Median of Two Sorted Arrays
- First Missing Positive
- Sliding Window Maximum

---

## Quick Reference

### Array Creation
```
int[] arr = new int[size];              // With size
int[] arr = {1, 2, 3};                  // With values
int[] arr = new int[]{1, 2, 3};         // Explicit
int[] copy = arr.clone();               // Clone
int[] copy = Arrays.copyOf(arr, len);   // Copy
```

### Array Iteration
```
// Index-based
for (int i = 0; i < arr.length; i++) { }

// Enhanced for loop (read-only)
for (int x : arr) { }

// Stream
Arrays.stream(arr).forEach(x -> { });
```

### Array Sorting
```
Arrays.sort(arr);                           // Ascending
Arrays.sort(arr, Collections.reverseOrder()); // Descending (Integer[])
Arrays.sort(arr, (a, b) -> b - a);          // Custom comparator
```

### Array Searching
```
int idx = Arrays.binarySearch(arr, key);  // Binary search
boolean found = Arrays.stream(arr).anyMatch(x -> x == target);
```

### Array Conversion
```
// Array to List
List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

// List to Array
int[] arr = list.stream().mapToInt(i -> i).toArray();
```

---

**Remember**: Arrays provide O(1) access but O(n) insertion/deletion. Choose the right data structure based on your primary operations!
