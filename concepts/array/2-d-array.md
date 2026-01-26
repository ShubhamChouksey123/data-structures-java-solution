# 2D Array Concepts

## Core Characteristics

### Structure

A 2D array is an **array of arrays** - essentially a matrix structure.

```
int[][] matrix = new int[rows][cols];

matrix[i][j] = value;  // Row i, Column j
```

**Key Points**:
- **Rows**: `matrix.length` - number of rows
- **Columns**: `matrix[0].length` - number of columns in first row
- **Access**: O(1) - direct index access
- **Memory**: Contiguous for each row, rows may be non-contiguous
- **Java allows jagged arrays**: Different rows can have different lengths

---

## 2D Array Creation

### Regular Rectangle Matrix

```
// Declaration with size
int[][] matrix = new int[3][4];  // 3 rows, 4 columns

// Declaration with values
int[][] matrix = {
    {1, 2, 3, 4},
    {5, 6, 7, 8},
    {9, 10, 11, 12}
};

// Mixed approach
int[][] matrix = new int[3][];
matrix[0] = new int[]{1, 2, 3};
matrix[1] = new int[]{4, 5, 6};
matrix[2] = new int[]{7, 8, 9};
```

### Jagged Array (Irregular)

```
int[][] jagged = new int[3][];
jagged[0] = new int[2];    // Row 0 has 2 columns
jagged[1] = new int[4];    // Row 1 has 4 columns
jagged[2] = new int[1];    // Row 2 has 1 column

// Example: Triangle
[[1],
 [2, 3],
 [4, 5, 6]]
```

---

## Sorting 2D Arrays

### 1. Sort by First Column (Ascending)

```
Arrays.sort(matrix, (a, b) -> Integer.compare(a[0], b[0]));

// Or shorter:
Arrays.sort(matrix, (a, b) -> a[0] - b[0]);
```

**Example**:
```
Before: [[3, 4], [1, 2], [2, 5]]
After:  [[1, 2], [2, 5], [3, 4]]
```

**Complexity**: O(n log n) where n = number of rows

---

### 2. Sort by Second Column (Ascending)

```
Arrays.sort(matrix, (a, b) -> Integer.compare(a[1], b[1]));

// Or:
Arrays.sort(matrix, (a, b) -> a[1] - b[1]);
```

**Example**:
```
Before: [[3, 4], [1, 2], [2, 5]]
After:  [[1, 2], [3, 4], [2, 5]]
```

---

### 3. Sort by First Column Descending

```
Arrays.sort(matrix, (a, b) -> Integer.compare(b[0], a[0]));

// Or:
Arrays.sort(matrix, (a, b) -> b[0] - a[0]);
```

**Example**:
```
Before: [[3, 4], [1, 2], [2, 5]]
After:  [[3, 4], [2, 5], [1, 2]]
```

---

### 4. Sort by Multiple Columns (Priority Order)

**Sort by first column, then by second column if first is equal**:

```
Arrays.sort(matrix, (a, b) -> {
    if (a[0] != b[0]) {
        return Integer.compare(a[0], b[0]);  // First column
    }
    return Integer.compare(a[1], b[1]);      // Second column (tie-breaker)
});

// Shorter version using ternary:
Arrays.sort(matrix, (a, b) ->
    a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]
);
```

**Example**:
```
Before: [[2, 5], [1, 3], [2, 1], [1, 2]]
After:  [[1, 2], [1, 3], [2, 1], [2, 5]]
        ↑ sorted by col 0, then col 1
```

---

### 5. Sort by Multiple Columns (Different Order)

**Sort by first column ascending, second column descending**:

```
Arrays.sort(matrix, (a, b) -> {
    if (a[0] != b[0]) {
        return a[0] - b[0];      // First column ascending
    }
    return b[1] - a[1];          // Second column descending
});
```

**Example**:
```
Before: [[2, 5], [1, 3], [2, 1], [1, 2]]
After:  [[1, 3], [1, 2], [2, 5], [2, 1]]
        ↑ col 0 asc,  ↑ col 1 desc when col 0 equal
```

---

### 6. Sort by Sum of Elements

```
Arrays.sort(matrix, (a, b) -> {
    int sumA = Arrays.stream(a).sum();
    int sumB = Arrays.stream(b).sum();
    return Integer.compare(sumA, sumB);
});
```

**Example**:
```
Before: [[1, 5], [2, 2], [3, 1]]  // sums: 6, 4, 4
After:  [[2, 2], [3, 1], [1, 5]]  // sums: 4, 4, 6
```

---

### 7. Sort by Custom Logic

**Example: Sort intervals by start time, then by duration**:

```
// intervals[i] = [start, end]
Arrays.sort(intervals, (a, b) -> {
    if (a[0] != b[0]) {
        return a[0] - b[0];              // Sort by start
    }
    return (a[1] - a[0]) - (b[1] - b[0]); // Sort by duration
});
```

---

### 8. Sort with Comparator.comparing()

**Modern Java 8+ approach**:

```
// Sort by first column
Arrays.sort(matrix, Comparator.comparingInt(a -> a[0]));

// Sort by second column descending
Arrays.sort(matrix, Comparator.comparingInt((int[] a) -> a[1]).reversed());

// Sort by multiple columns
Arrays.sort(matrix,
    Comparator.comparingInt((int[] a) -> a[0])
              .thenComparingInt(a -> a[1])
);
```

---

## Common Patterns & Use Cases

### Pattern: Merge Intervals

**Problem**: Merge overlapping intervals

**Approach**:
1. Sort by start time: `Arrays.sort(intervals, (a, b) -> a[0] - b[0])`
2. Merge overlapping intervals

**Example**:
```
Input:  [[1, 3], [2, 6], [8, 10], [15, 18]]
Sort:   Already sorted by start
Output: [[1, 6], [8, 10], [15, 18]]
```

---

### Pattern: Meeting Rooms

**Problem**: Determine if person can attend all meetings

**Approach**:
1. Sort by start time
2. Check if any meeting overlaps with next

```
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
for (int i = 1; i < intervals.length; i++) {
    if (intervals[i][0] < intervals[i-1][1]) {
        return false;  // Overlap found
    }
}
return true;
```

---

### Pattern: Matrix Traversal

**Row-wise traversal**:
```
for (int i = 0; i < matrix.length; i++) {          // Rows
    for (int j = 0; j < matrix[i].length; j++) {   // Columns
        process(matrix[i][j]);
    }
}
```

**Column-wise traversal**:
```
for (int j = 0; j < matrix[0].length; j++) {       // Columns
    for (int i = 0; i < matrix.length; i++) {      // Rows
        process(matrix[i][j]);
    }
}
```

**Diagonal traversal** (top-left to bottom-right):
```
for (int i = 0; i < matrix.length; i++) {
    process(matrix[i][i]);  // Diagonal elements
}
```

**Spiral traversal**:
```
// Use 4 pointers: top, bottom, left, right
// Process: top row → right column → bottom row → left column
// Shrink boundaries after each direction
```

---

### Pattern: Matrix Search

**Search in sorted matrix** (each row sorted, first element of row > last of previous):

**Approach**: Binary search
1. Treat as 1D array
2. Convert index to (row, col): `row = mid / cols`, `col = mid % cols`

**Complexity**: O(log(m*n))

```
int rows = matrix.length, cols = matrix[0].length;
int left = 0, right = rows * cols - 1;

while (left <= right) {
    int mid = left + (right - left) / 2;
    int midVal = matrix[mid / cols][mid % cols];

    if (midVal == target) return true;
    else if (midVal < target) left = mid + 1;
    else right = mid - 1;
}
```

---

### Pattern: Matrix Rotation

**Rotate 90° clockwise**:
1. Transpose (swap matrix[i][j] with matrix[j][i])
2. Reverse each row

**Rotate 90° counter-clockwise**:
1. Transpose
2. Reverse each column

---

## Complexity Analysis

### Sorting 2D Arrays

| Operation | Time | Space | Notes |
|-----------|------|-------|-------|
| Sort by column | O(n log n) | O(log n) | n = number of rows, O(log n) for sort stack |
| Sort by multiple columns | O(n log n) | O(log n) | Comparator adds O(1) per comparison |
| Sort by custom logic | O(n log n) | O(log n) | Depends on comparator complexity |

### Traversal

| Pattern | Time | Space | Notes |
|---------|------|-------|-------|
| Row-wise | O(m * n) | O(1) | m = rows, n = cols |
| Column-wise | O(m * n) | O(1) | Less cache-friendly |
| Diagonal | O(min(m, n)) | O(1) | Only main diagonal |
| Spiral | O(m * n) | O(1) | Visit each cell once |

### Search

| Pattern | Time | Space | Notes |
|---------|------|-------|-------|
| Linear search | O(m * n) | O(1) | Unsorted matrix |
| Binary search | O(log(m*n)) | O(1) | Fully sorted matrix |
| Row binary search | O(m log n) | O(1) | Each row sorted |

---

## Common Gotchas

### 1. Integer Overflow in Comparator

**❌ WRONG** (can overflow):
```
Arrays.sort(matrix, (a, b) -> a[0] - b[0]);  // If a[0] - b[0] overflows
```

**✅ CORRECT**:
```
Arrays.sort(matrix, (a, b) -> Integer.compare(a[0], b[0]));
```

### 2. Assuming Rectangle Matrix

**❌ WRONG** (assumes all rows have same length):
```
int cols = matrix[0].length;
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < cols; j++) {  // May throw exception for jagged
        process(matrix[i][j]);
    }
}
```

**✅ CORRECT**:
```
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {  // Use row's actual length
        process(matrix[i][j]);
    }
}
```

### 3. Empty Matrix Check

**❌ WRONG**:
```
if (matrix.length == 0) return;
int cols = matrix[0].length;  // NullPointer if matrix[0] doesn't exist
```

**✅ CORRECT**:
```
if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    return;
}
```

### 4. Modifying During Sort

**❌ WRONG**:
```
// Don't modify array elements during comparison
Arrays.sort(matrix, (a, b) -> {
    a[0]++;  // Modifying array during sort
    return a[0] - b[0];
});
```

---

## Common Interview Problems

### Problems Using Sorting

| Problem | Sort Strategy | Complexity |
|---------|---------------|------------|
| **Merge Intervals** | Sort by start time | O(n log n) |
| **Meeting Rooms** | Sort by start time | O(n log n) |
| **Non-overlapping Intervals** | Sort by end time | O(n log n) |
| **Minimum Meeting Rooms** | Sort start and end separately | O(n log n) |
| **Car Pooling** | Sort by location | O(n log n) |
| **Insert Interval** | Sort by start time | O(n log n) |

### Matrix Problems

| Problem | Technique | Complexity |
|---------|-----------|------------|
| **Search 2D Matrix** | Binary search | O(log(m*n)) |
| **Rotate Image** | Transpose + Reverse | O(m * n) |
| **Spiral Matrix** | Boundary traversal | O(m * n) |
| **Set Matrix Zeroes** | Mark first row/col | O(m * n) |
| **Word Search** | DFS/Backtracking | O(m * n * 4^L) |

---

## Best Practices

### 1. Sorting

- **Use Integer.compare()** instead of subtraction to avoid overflow
- **Chain comparators** for multiple column sorting
- **Cache calculations** if comparator is expensive
- **Consider stability** if order of equal elements matters (Arrays.sort is stable for objects)

### 2. Traversal

- **Check bounds** before accessing elements
- **Handle jagged arrays** by using `matrix[i].length`
- **Cache length** for inner loop if accessing frequently
- **Consider direction** (row-wise is more cache-friendly)

### 3. Edge Cases

- **Empty matrix**: `matrix.length == 0`
- **Empty rows**: `matrix[0].length == 0`
- **Null matrix**: `matrix == null`
- **Single element**: `matrix.length == 1 && matrix[0].length == 1`
- **Jagged arrays**: Different row lengths

---

## Quick Reference

### Sorting by Column

```
// First column ascending
Arrays.sort(matrix, (a, b) -> a[0] - b[0]);

// Second column ascending
Arrays.sort(matrix, (a, b) -> a[1] - b[1]);

// First column descending
Arrays.sort(matrix, (a, b) -> b[0] - a[0]);

// Multiple columns (col 0 asc, then col 1 asc)
Arrays.sort(matrix, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

// Using Comparator.comparing
Arrays.sort(matrix, Comparator.comparingInt((int[] a) -> a[0])
                              .thenComparingInt(a -> a[1]));
```

### Common Checks

```
// Check if empty
if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

// Get dimensions
int rows = matrix.length;
int cols = matrix[0].length;  // Assumes non-empty

// Check if square
boolean isSquare = (rows == cols);

// Check bounds
if (i >= 0 && i < rows && j >= 0 && j < matrix[i].length) {
    // Safe to access matrix[i][j]
}
```

### Direction Arrays (for traversal)

```
// 4 directions (up, down, left, right)
int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

// 8 directions (including diagonals)
int[][] directions = {
    {-1, -1}, {-1, 0}, {-1, 1},
    {0, -1},           {0, 1},
    {1, -1},  {1, 0},  {1, 1}
};

// Usage
for (int[] dir : directions) {
    int newRow = row + dir[0];
    int newCol = col + dir[1];
    if (isValid(newRow, newCol)) {
        process(matrix[newRow][newCol]);
    }
}
```

---

**Remember**: 2D arrays are arrays of arrays. Each row is an independent array, allowing for jagged structures. Always validate dimensions before accessing elements!
