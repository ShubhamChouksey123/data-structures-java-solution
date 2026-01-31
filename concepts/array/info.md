# Java Array Concepts

## 2. What is Array?

**Type**: Fixed-size, contiguous memory data structure
**Syntax**: `int[] arr` or `int arr[]`

**Key Characteristics**:
- **Fixed size** - cannot grow or shrink after creation
- **Contiguous memory** - elements stored consecutively
- **Index-based** - O(1) access by index (zero-indexed)
- **Homogeneous** - all elements same type
- **Cache-friendly** - excellent memory locality

---

## 3. Time & Space Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **Access** `arr[i]` | O(1) | Direct index access |
| **Search** (unsorted) | O(n) | Linear scan |
| **Search** (sorted) | O(log n) | Binary search |
| **Insert** at end | O(1) | If space available |
| **Insert** at position | O(n) | Shifts elements |
| **Delete** from end | O(1) | Just decrement size |
| **Delete** from position | O(n) | Shifts elements |
| **Update** `arr[i] = x` | O(1) | Direct access |
| **Traverse** | O(n) | Visit each element |
| **Sort** | O(n log n) | Best comparison sort |

**Space**: O(n) - exactly n elements

---

## 4. Common Operations & Methods

### Array Creation

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Create with size** | `new int[5]` | O(n) | Default values |
| **Create with values** | `{1, 2, 3}` | O(n) | Array literal |
| **Clone** | `arr.clone()` | O(n) | Shallow copy |
| **Copy** | `Arrays.copyOf(arr, len)` | O(n) | New array |
| **Copy range** | `Arrays.copyOfRange(arr, from, to)` | O(n) | Subarray |
| **Fill** | `Arrays.fill(arr, val)` | O(n) | Set all to value |

### Arrays Utility Methods

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Sort** | `Arrays.sort(arr)` | O(n log n) | DualPivotQuicksort |
| **Reverse sort** | `Arrays.sort(arr, Collections.reverseOrder())` | O(n log n) | Only for Object arrays |
| **Binary search** | `Arrays.binarySearch(arr, key)` | O(log n) | Must be sorted |
| **Compare** | `Arrays.equals(arr1, arr2)` | O(n) | Content comparison |
| **To string** | `Arrays.toString(arr)` | O(n) | For debugging |
| **To list** | `Arrays.asList(arr)` | O(1) | Fixed-size list |
| **Stream** | `Arrays.stream(arr)` | O(1) | Create stream |

**Default Values**: `int/long/short/byte` → 0, `float/double` → 0.0, `boolean` → false, `char` → '\u0000', Objects → null

---

## 5. Core Characteristics/Creation

```java
// Declaration & creation
int[] arr = new int[5];              // [0, 0, 0, 0, 0]
int[] arr = {1, 2, 3, 4, 5};         // Array literal
int[] arr = new int[]{1, 2, 3};      // Explicit

// Copy
int[] copy = arr.clone();
int[] copy = Arrays.copyOf(arr, arr.length);
int[] range = Arrays.copyOfRange(arr, 0, 3);

// Fill
Arrays.fill(arr, 10);                // All elements = 10

// Length
int len = arr.length;                // Field, not method
```

**2D Arrays**: See `2-d-array.md` for multi-dimensional array concepts

---

## 6. Comparison with Similar Structures

| Feature | Array | ArrayList |
|---------|-------|-----------|
| **Size** | Fixed | Dynamic |
| **Type** | Primitives or Objects | Objects only (boxing) |
| **Performance** | Faster | Slower (overhead) |
| **Syntax** | `arr[i]` | `list.get(i)` |
| **Length** | `arr.length` (field) | `list.size()` (method) |
| **Memory** | Less overhead | More overhead |

**When to Use Array**:
- Size is known and fixed
- Need primitive types (performance)
- Memory efficiency important

**When to Use ArrayList**:
- Size changes dynamically
- Need List operations (add, remove)
- Size flexibility > performance

---

## 7. Common Patterns & Use Cases

---

### Pattern 4: Kadane's Algorithm

**Use Case**: Maximum subarray sum

```java
int maxSoFar = arr[0];
int maxEndingHere = arr[0];

for (int i = 1; i < arr.length; i++) {
    maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
    maxSoFar = Math.max(maxSoFar, maxEndingHere);
}
```

**Complexity**: O(n)

---

### Pattern 5: Dutch National Flag

**Use Case**: Partition into 3 parts (sort 0s, 1s, 2s)

```java
int low = 0, mid = 0, high = arr.length - 1;
while (mid <= high) {
    if (arr[mid] == 0) swap(arr, low++, mid++);
    else if (arr[mid] == 1) mid++;
    else swap(arr, mid, high--);
}
```

**Complexity**: O(n)

---

## 8. Common Gotchas & Best Practices

### 1. Index Out of Bounds

**❌ WRONG**:
```java
int[] arr = new int[5];
int x = arr[5];  // Exception (valid: 0-4)
```

**✅ CORRECT**:
```java
if (i >= 0 && i < arr.length) {
    int x = arr[i];
}
```

---

### 2. Array Assignment Copies Reference

**❌ WRONG**:
```java
int[] arr1 = {1, 2, 3};
int[] arr2 = arr1;  // Same reference!
arr2[0] = 99;       // arr1[0] is now 99
```

**✅ CORRECT**:
```java
int[] arr2 = arr1.clone();
int[] arr2 = Arrays.copyOf(arr1, arr1.length);
```

---

### 3. Array Comparison Uses == (Reference)

**❌ WRONG**:
```java
int[] arr1 = {1, 2, 3};
int[] arr2 = {1, 2, 3};
arr1 == arr2  // false (compares references)
```

**✅ CORRECT**:
```java
Arrays.equals(arr1, arr2)  // true (compares content)
```

---

### 4. Enhanced For Loop Doesn't Modify Array

**❌ WRONG**:
```java
for (int x : arr) {
    x = 10;  // Only modifies local variable
}
```

**✅ CORRECT**:
```java
for (int i = 0; i < arr.length; i++) {
    arr[i] = 10;  // Modifies array
}
```

---

### 5. Arrays.binarySearch Requires Sorted Array

**❌ WRONG**:
```java
int[] arr = {3, 1, 4, 2};
Arrays.binarySearch(arr, 4);  // Incorrect result
```

**✅ CORRECT**:
```java
Arrays.sort(arr);
Arrays.binarySearch(arr, 4);  // Now correct
```

---

## 9. Interview Tips

### When to Use Array
✅ Size is known and fixed
✅ Need primitive types (performance)
✅ Memory efficiency important
✅ Simple indexing operations

### When NOT to Use Array
❌ Size changes dynamically → Use ArrayList
❌ Need frequent insertions/deletions → Use ArrayList
❌ Need built-in methods → Use ArrayList

### Remember
- **Arrays are fixed size** - cannot grow
- **Zero-indexed** - first element at index 0
- Use `arr.length` (field), not `arr.length()`
- `Arrays.sort()` required before `Arrays.binarySearch()`
- Use `Arrays.equals()` for content comparison, not `==`
- Use `Arrays.toString()` for debugging, not `arr.toString()`
- Array assignment copies reference, use `clone()` for deep copy

### Time Complexity Quick Check
- Access: O(1)
- Search (unsorted): O(n)
- Search (sorted): O(log n)
- Insert/Delete: O(n) (shifts elements)
- Sort: O(n log n)

---

## 10. Quick Reference

### Creation
```java
int[] arr = new int[5];              // With size
int[] arr = {1, 2, 3};               // With values
int[] copy = arr.clone();            // Clone
int[] copy = Arrays.copyOf(arr, len); // Copy
```

### Common Operations
```java
arr[i]                               // Access - O(1)
arr.length                           // Length - O(1)
Arrays.sort(arr)                     // Sort - O(n log n)
Arrays.binarySearch(arr, key)        // Search - O(log n)
Arrays.fill(arr, val)                // Fill - O(n)
Arrays.equals(arr1, arr2)            // Compare - O(n)
Arrays.toString(arr)                 // Debug - O(n)
```

### Iteration
```java
// Index-based
for (int i = 0; i < arr.length; i++) { }

// Enhanced for (read-only)
for (int x : arr) { }

// Stream
Arrays.stream(arr).forEach(x -> { });
```

### Array ↔ List Conversion
```java
// Array → List
List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

// List → Array
int[] arr = list.stream().mapToInt(i -> i).toArray();
```

---

## 11. Key Insight

Arrays provide **O(1) random access** with minimal memory overhead but are **fixed size**. Use arrays when size is known and performance is critical. For dynamic sizing, use ArrayList!
