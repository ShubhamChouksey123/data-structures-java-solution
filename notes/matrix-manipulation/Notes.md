# Matrix Manipulation

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **2D array transformations, in-place modifications, or special traversal patterns**

### Keywords
- "rotate matrix", "rotate image"
- "spiral order", "spiral matrix"
- "set matrix zeroes", "in-place"
- "transpose", "clockwise/counter-clockwise"

### Examples
- Rotate image 90 degrees
- Traverse matrix in spiral order
- Set entire row/column to zero if element is zero
- Transpose matrix

---

## Core Concept

Matrix manipulation problems involve transforming 2D arrays through rotation, traversal patterns, or in-place modifications using row-column relationships.

**Key Insight**: Most problems can be solved by understanding layer-by-layer processing or row-column coordinate transformations.

**Complexity**: Typically O(m×n) time where m=rows, n=cols

---

## Pattern 1: Rotate Matrix 90° Clockwise

**Use Case**: Rotate n×n matrix 90 degrees clockwise in-place

**Algorithm**:
1. Transpose matrix (swap elements across diagonal: `matrix[i][j] ↔ matrix[j][i]`)
2. Reverse each row

**Complexity**: O(n²) time, O(1) space

### Visual Example

```
Original:       Transpose:      Reverse rows:
1 2 3           1 4 7           7 4 1
4 5 6     →     2 5 8     →     8 5 2
7 8 9           3 6 9           9 6 3

Result: 90° clockwise rotation
```

**Variations**:
- **90° counter-clockwise**: Transpose + reverse each column
- **180°**: Reverse each row + reverse each column

---

## Pattern 2: Spiral Matrix Traversal

**Use Case**: Traverse m×n matrix in spiral order (outer layer to inner)

**Algorithm**:
1. Define boundaries: `top`, `bottom`, `left`, `right`
2. Process in order: left→right (top row), top→bottom (right col), right→left (bottom row), bottom→top (left col)
3. Shrink boundaries after each direction
4. Repeat until all elements visited

**Complexity**: O(m×n) time, O(1) space (excluding result array)

### Template

```java
public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> result = new ArrayList<>();
    if (matrix == null || matrix.length == 0) return result;

    int top = 0, bottom = matrix.length - 1;
    int left = 0, right = matrix[0].length - 1;

    while (top <= bottom && left <= right) {
        // Left to right (top row)
        for (int col = left; col <= right; col++) {
            result.add(matrix[top][col]);
        }
        top++;

        // Top to bottom (right column)
        for (int row = top; row <= bottom; row++) {
            result.add(matrix[row][right]);
        }
        right--;

        // Right to left (bottom row) - check if row exists
        if (top <= bottom) {
            for (int col = right; col >= left; col--) {
                result.add(matrix[bottom][col]);
            }
            bottom--;
        }

        // Bottom to top (left column) - check if column exists
        if (left <= right) {
            for (int row = bottom; row >= top; row--) {
                result.add(matrix[row][left]);
            }
            left++;
        }
    }

    return result;
}
```

### Visual Example

```
Matrix:          Spiral Order:
1  2  3  4       1 → 2 → 3 → 4
5  6  7  8                   ↓
9  10 11 12      5 → 6 → 7   8
                 ↓           ↓
                 9   10← 11← 12

Result: [1,2,3,4,8,12,11,10,9,5,6,7]
```

---

## Pattern 3: Set Matrix Zeroes ⭐ **IMPORTANT** ⭐

**Use Case**: If element is 0, set entire row and column to 0 (in-place with O(1) space)

**Algorithm**:
1. Use first row and first column as markers
2. Track separately if first row/column need zeroing (use flags)
3. Scan matrix, mark first row/col if zero found
4. Use markers to set zeros
5. Handle first row/column using flags

**Complexity**: O(m×n) time, O(1) space

### Template

```java
public void setZeroes(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    boolean firstRowZero = false, firstColZero = false;

    // Check if first row needs to be zero
    for (int j = 0; j < n; j++) {
        if (matrix[0][j] == 0) {
            firstRowZero = true;
            break;
        }
    }

    // Check if first column needs to be zero
    for (int i = 0; i < m; i++) {
        if (matrix[i][0] == 0) {
            firstColZero = true;
            break;
        }
    }

    // Use first row/col as markers
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;  // Mark row
                matrix[0][j] = 0;  // Mark column
            }
        }
    }

    // Set zeros based on markers
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }

    // Handle first row
    if (firstRowZero) {
        for (int j = 0; j < n; j++) {
            matrix[0][j] = 0;
        }
    }

    // Handle first column
    if (firstColZero) {
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 0;
        }
    }
}
```

**Why Important**: Achieving O(1) space is non-obvious - uses first row/column as storage

---

## Common Mistakes

### ❌ Wrong Transpose Loop Range

```java
// WRONG - processes each pair twice
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {  // Should start from i+1
        swap(matrix[i][j], matrix[j][i]);
    }
}

// CORRECT - start j from i+1 to avoid double swap
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        swap(matrix[i][j], matrix[j][i]);
    }
}
```

### ❌ Spiral Matrix Boundary Checks

```java
// WRONG - doesn't check if row/column still exists
while (top <= bottom && left <= right) {
    // ... traverse top, right ...

    // Missing check - may process same row/col twice
    for (int col = right; col >= left; col--) {
        result.add(matrix[bottom][col]);
    }
}

// CORRECT - check before processing
if (top <= bottom) {  // Check if bottom row exists
    for (int col = right; col >= left; col--) {
        result.add(matrix[bottom][col]);
    }
    bottom--;
}
```

### ❌ Set Matrix Zeroes - Modifying First Row/Col Too Early

```java
// WRONG - loses information about original first row/col
for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 0) {
            matrix[i][0] = 0;
            matrix[0][j] = 0;
        }
    }
}
// Now you can't tell if first row/col were originally zero

// CORRECT - use separate flags for first row/col
boolean firstRowZero = false, firstColZero = false;
// Check first row/col separately before using them as markers
```

---

## Problems

- [x] [Rotate Image](https://leetcode.com/problems/rotate-image/) - Medium
- [x] [Spiral Matrix](https://leetcode.com/problems/spiral-matrix/) - Medium
- [x] [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Game of Life](https://leetcode.com/problems/game-of-life/) - Medium

### Set Matrix Zeroes ⭐ **IMPORTANT** ⭐

**Problem**: [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/) - Medium

**Why Important**: O(1) space solution is non-intuitive, uses first row/column as markers

**Approach**:
1. Use separate flags to track if first row/column need zeroing
2. Use first row/column as markers for remaining matrix
3. Process matrix based on markers
4. Finally handle first row/column using flags

**Complexity**: O(m×n) time, O(1) space

**Key Points**:
- Can't use first row/col as markers without checking them first
- Must process first row/col LAST to avoid losing information
- Alternative O(m+n) space: use two boolean arrays for rows and columns
- Alternative O(1) with encoding: use special marker value (works if values are bounded)

---

## Key Takeaways

1. **Rotate 90° clockwise**: Transpose + reverse each row
2. **Spiral traversal**: Track 4 boundaries, shrink after each layer, check boundaries before bottom/left passes
3. **In-place O(1) space**: Use first row/column as markers, handle them separately with flags
4. **Layer-by-layer**: Many matrix problems process outer layer first, then move inward
5. **Coordinate transformation**: Rotation involves swapping (i,j) coordinates systematically

---

> **[← Back to Overview](../README.md)** | **[Cyclic Sort →](../cyclic-sort/Notes.md)**
