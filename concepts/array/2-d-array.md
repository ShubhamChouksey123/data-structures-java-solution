# Java 2D Array Concepts

## 2. What is 2D Array?

**Type**: Array of arrays (matrix structure)
**Syntax**: `int[][] matrix` or `int matrix[][]`

**Key Characteristics**:
- **Matrix structure** - rows and columns
- **Array of arrays** - each row is an independent array
- **O(1) access** - direct access to any element
- **Jagged arrays** - rows can have different lengths
- **Row-major order** - rows stored contiguously

---

## 3. Time & Space Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **Access** `matrix[i][j]` | O(1) | Direct index access |
| **Traverse** | O(m * n) | Visit all elements |
| **Row traversal** | O(n) | Single row |
| **Column traversal** | O(m) | Single column |
| **Sort by column** | O(m log m) | m = number of rows |
| **Search** (unsorted) | O(m * n) | Linear scan |
| **Search** (sorted) | O(log(m*n)) | Binary search |

**Space**: O(m * n) where m = rows, n = columns

---

## 4. Common Operations & Methods

### Creation & Access

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Create** | `new int[m][n]` | O(m * n) | m rows, n columns |
| **Create jagged** | `new int[m][]` | O(m) | Different row lengths |
| **Access element** | `matrix[i][j]` | O(1) | Direct access |
| **Get rows** | `matrix.length` | O(1) | Number of rows |
| **Get columns** | `matrix[0].length` | O(1) | Assumes non-empty |

### Sorting Operations

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Sort by first column** | `Arrays.sort(matrix, (a,b) -> a[0] - b[0])` | O(m log m) | Ascending |
| **Sort by multiple columns** | `Arrays.sort(matrix, comparator)` | O(m log m) | Custom comparator |

---

## 5. Core Characteristics/Creation

```java
// Regular matrix
int[][] matrix = new int[3][4];        // 3 rows, 4 columns
int[][] matrix = {{1, 2}, {3, 4}};     // Array literal

// Access
int value = matrix[row][col];
int rows = matrix.length;
int cols = matrix[0].length;

// Jagged array (different row lengths)
int[][] jagged = new int[3][];
jagged[0] = new int[2];    // Row 0: 2 columns
jagged[1] = new int[4];    // Row 1: 4 columns
jagged[2] = new int[1];    // Row 2: 1 column
```

---

## 6. Comparison with Similar Structures

| Need | Use |
|------|-----|
| **Fixed 2D matrix** | `int[][]` |
| **Dynamic 2D list** | `List<List<Integer>>` |
| **Graph representation** | Adjacency matrix or list |
| **Sparse matrix** | HashMap<Pair, Integer> |

---

## 7. Common Patterns & Use Cases


### Pattern 4: Sorting by Column

```java
// Sort by first column (ascending)
Arrays.sort(matrix, (a, b) -> Integer.compare(a[0], b[0]));

// Sort by first column, then second (if tied)
Arrays.sort(matrix, (a, b) ->
    a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]
);
```

**Use Cases**: Merge intervals, meeting rooms

**Complexity**: O(m log m)


## 8. Common Gotchas & Best Practices

### 1. Assuming Rectangle Matrix

**❌ WRONG**:
```java
int cols = matrix[0].length;
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < cols; j++) {  // May throw for jagged
        process(matrix[i][j]);
    }
}
```

**✅ CORRECT**:
```java
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {  // Use row's length
        process(matrix[i][j]);
    }
}
```

---

### 2. Empty Matrix Check

**❌ WRONG**:
```java
if (matrix.length == 0) return;
int cols = matrix[0].length;  // NullPointer if matrix[0] doesn't exist
```

**✅ CORRECT**:
```java
if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    return;
}
```

---

### 3. Integer Overflow in Comparator

**❌ WRONG**:
```java
Arrays.sort(matrix, (a, b) -> a[0] - b[0]);  // Can overflow
```

**✅ CORRECT**:
```java
Arrays.sort(matrix, (a, b) -> Integer.compare(a[0], b[0]));
```

---

### 4. Row vs Column Indexing

**Common Mistake**: Confusing row and column indices

```java
// Correct: matrix[row][column]
int value = matrix[i][j];  // i = row, j = column

// rows = matrix.length (vertical)
// cols = matrix[0].length (horizontal)
```

---

## 9. Interview Tips

### When to Use 2D Array
✅ Matrix operations (rotation, spiral)
✅ Grid-based problems (islands, paths)
✅ Dynamic programming tables
✅ Graph adjacency matrix
✅ Image processing (pixels)

### Remember
- **matrix[row][column]** - row first, then column
- **matrix.length** = rows (vertical count)
- **matrix[0].length** = columns (horizontal count)
- Use `matrix[i].length` for jagged arrays
- Sort by column using custom comparator
- Use `Integer.compare()` to avoid overflow
- Always check bounds before accessing

### Common 2D Array Problems
- **Matrix rotation** (90°, 180°)
- **Spiral traversal**
- **Search in sorted matrix**
- **Islands** (BFS/DFS)
- **Dynamic programming** (grid-based)
- **Merge intervals** (sort by start time)

### Time Complexity Quick Check
- Access element: O(1)
- Traverse all: O(m * n)
- Sort by column: O(m log m)
- Search (sorted): O(log(m*n))

---

## 10. Quick Reference

### Creation
```java
int[][] matrix = new int[m][n];        // m rows, n cols
int[][] matrix = {{1, 2}, {3, 4}};     // Literal
int[][] jagged = new int[m][];         // Jagged array
```

### Access
```java
matrix[i][j]                           // Element at (i, j)
matrix.length                          // Number of rows
matrix[0].length                       // Number of columns
```

### Traversal
```java
// Row-wise (cache-friendly)
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) { }
}

// Enhanced for
for (int[] row : matrix) {
    for (int val : row) { }
}
```

### Sorting
```java
// Sort by first column
Arrays.sort(matrix, (a, b) -> Integer.compare(a[0], b[0]));

// Sort by multiple columns
Arrays.sort(matrix, (a, b) ->
    a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]
);
```

### Direction Arrays
```java
// 4 directions (up, down, left, right)
int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

// 8 directions (including diagonals)
int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
```

---

## 11. Key Insight

2D arrays are **arrays of arrays** where each row is independent. Always use **matrix[row][column]** indexing and **matrix[i].length** for jagged arrays. Prefer row-wise traversal for better cache performance!
