# Backtracking

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Generate all possible solutions, solve constraint puzzles, or explore decision trees**

### Keywords
- "all combinations", "all permutations", "all subsets"
- "generate all", "find all solutions"
- "valid arrangements", "satisfy constraints"
- "explore all paths", "decision tree"
- "n-queens", "sudoku", "word search"

### Examples
- Generate all permutations/combinations
- Solve N-Queens puzzle
- Sudoku solver
- Word search in grid
- Generate valid parentheses
- Palindrome partitioning

---

## Core Concept

Backtracking explores all possible solutions by building candidates incrementally and abandoning ("backtracking") when a candidate cannot lead to a valid solution.

**Key Insight**: Build solution step-by-step. If current path fails constraints, undo last choice (backtrack) and try next option.

**Complexity**: Typically O(b^d) where b=branching factor, d=depth. Space: O(d) for recursion stack.

**Template Pattern**:
```
1. Make a choice
2. Explore with that choice (recursion)
3. Undo the choice (backtrack)
4. Try next choice
```

---

## Pattern 1: Permutations and Combinations

**Use Case**: Generate all arrangements or selections from a set

**Key Difference**:
- **Permutations**: Order matters (ABC ≠ BAC)
- **Combinations**: Order doesn't matter (ABC = BAC)

**Algorithm (Permutations)**:
1. For each unused element
2. Add to current path
3. Recursively generate rest
4. Remove from path (backtrack)

**Complexity**: O(n! × n) time for permutations, O(n × 2^n) for subsets/combinations

### Template: Permutations

```java
public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> current,
                       int[] nums, boolean[] used) {
    // Base case: found complete permutation
    if (current.size() == nums.length) {
        result.add(new ArrayList<>(current));
        return;
    }

    for (int i = 0; i < nums.length; i++) {
        if (used[i]) continue;  // Skip used elements

        // Make choice
        current.add(nums[i]);
        used[i] = true;

        // Explore
        backtrack(result, current, nums, used);

        // Undo choice (backtrack)
        current.remove(current.size() - 1);
        used[i] = false;
    }
}
```

### Template: Combinations

```java
public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), 1, n, k);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> current,
                       int start, int n, int k) {
    // Base case: found k elements
    if (current.size() == k) {
        result.add(new ArrayList<>(current));
        return;
    }

    for (int i = start; i <= n; i++) {
        current.add(i);                          // Choose
        backtrack(result, current, i + 1, n, k); // Explore
        current.remove(current.size() - 1);      // Unchoose
    }
}
```

**Key Point**: Combinations use `start` index to avoid duplicates (only explore elements after current).

---

## Pattern 2: Subsets (Power Set)

**Use Case**: Generate all possible subsets of a set

**Algorithm**:
1. For each element, make two choices: include or exclude
2. Recursively generate subsets for remaining elements
3. Collect all generated subsets

**Complexity**: O(n × 2^n) time, O(n) space

### Template

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), nums, 0);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> current,
                       int[] nums, int start) {
    // Add current subset (every state is valid)
    result.add(new ArrayList<>(current));

    for (int i = start; i < nums.length; i++) {
        current.add(nums[i]);                    // Include
        backtrack(result, current, nums, i + 1); // Explore
        current.remove(current.size() - 1);      // Backtrack
    }
}
```

### Visual Example

```
nums = [1, 2, 3]

Tree structure:
                    []
          /         |         \
        [1]        [2]        [3]
       /   \        |
    [1,2] [1,3]   [2,3]
      |
   [1,2,3]

Result: [], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]
```

---

## Pattern 3: Constraint Satisfaction ⭐ **IMPORTANT** ⭐

**Use Case**: Solve puzzles with constraints (N-Queens, Sudoku)

**Algorithm**:
1. Try placing element in valid position
2. Check if constraints satisfied
3. Recursively solve next position
4. If fails, backtrack and try next position

**Complexity**: O(b^d) where b=branching factor, d=search depth

### Template: N-Queens

```java
public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    char[][] board = new char[n][n];
    for (char[] row : board) Arrays.fill(row, '.');

    backtrack(result, board, 0);
    return result;
}

private void backtrack(List<List<String>> result, char[][] board, int row) {
    // Base case: placed all queens
    if (row == board.length) {
        result.add(construct(board));
        return;
    }

    // Try each column
    for (int col = 0; col < board.length; col++) {
        if (!isValid(board, row, col)) continue;

        board[row][col] = 'Q';               // Place queen
        backtrack(result, board, row + 1);   // Explore next row
        board[row][col] = '.';               // Remove queen (backtrack)
    }
}

private boolean isValid(char[][] board, int row, int col) {
    int n = board.length;

    // Check column
    for (int i = 0; i < row; i++) {
        if (board[i][col] == 'Q') return false;
    }

    // Check diagonal (top-left)
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 'Q') return false;
    }

    // Check diagonal (top-right)
    for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
        if (board[i][j] == 'Q') return false;
    }

    return true;
}

private List<String> construct(char[][] board) {
    List<String> result = new ArrayList<>();
    for (char[] row : board) {
        result.add(new String(row));
    }
    return result;
}
```

---

## Pattern 4: Grid Path Finding

**Use Case**: Find paths in 2D grid (Word Search, Rat in Maze)

**Algorithm**:
1. Start from cell, mark as visited
2. Try all 4 directions
3. If path found, return true
4. Backtrack: unmark cell, try next direction

**Complexity**: O(m × n × 4^L) where L=word length (Word Search)

### Template: Word Search

```java
public boolean exist(char[][] board, String word) {
    int m = board.length, n = board[0].length;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (backtrack(board, word, i, j, 0)) {
                return true;
            }
        }
    }

    return false;
}

private boolean backtrack(char[][] board, String word, int row, int col, int index) {
    // Base case: found word
    if (index == word.length()) return true;

    // Check bounds and character match
    int m = board.length, n = board[0].length;
    if (row < 0 || row >= m || col < 0 || col >= n ||
        board[row][col] != word.charAt(index)) {
        return false;
    }

    // Mark as visited
    char temp = board[row][col];
    board[row][col] = '#';

    // Explore 4 directions
    boolean found = backtrack(board, word, row + 1, col, index + 1) ||
                    backtrack(board, word, row - 1, col, index + 1) ||
                    backtrack(board, word, row, col + 1, index + 1) ||
                    backtrack(board, word, row, col - 1, index + 1);

    // Backtrack: restore cell
    board[row][col] = temp;

    return found;
}
```

---

## Pattern 5: Combination Sum (With Constraints)

**Use Case**: Find all combinations that sum to target (with repetition allowed)

**Algorithm**:
1. For each candidate, decide to include or not
2. If include, can use same candidate again (unbounded)
3. Reduce target by candidate value
4. Backtrack when target becomes 0 or negative

**Complexity**: O(2^target) time in worst case

### Template

```java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates);  // Optional: for early termination
    backtrack(result, new ArrayList<>(), candidates, target, 0);
    return result;
}

private void backtrack(List<List<Integer>> result, List<Integer> current,
                       int[] candidates, int remain, int start) {
    if (remain < 0) return;  // Exceeded target

    if (remain == 0) {
        result.add(new ArrayList<>(current));
        return;
    }

    for (int i = start; i < candidates.length; i++) {
        current.add(candidates[i]);
        // Note: i (not i+1) allows reusing same element
        backtrack(result, current, candidates, remain - candidates[i], i);
        current.remove(current.size() - 1);
    }
}
```

---

## Common Mistakes

### ❌ Forgetting to Create New List in Result

```java
// WRONG - all results point to same list
if (current.size() == k) {
    result.add(current);  // Reference to mutable list!
}

// CORRECT - create new list
if (current.size() == k) {
    result.add(new ArrayList<>(current));
}
```

### ❌ Not Backtracking Properly

```java
// WRONG - modifying state without backtracking
void backtrack(List<Integer> current, int[] nums, int i) {
    current.add(nums[i]);
    backtrack(current, nums, i + 1);
    // Missing: current.remove(current.size() - 1);
}

// CORRECT - always backtrack
void backtrack(List<Integer> current, int[] nums, int i) {
    current.add(nums[i]);
    backtrack(current, nums, i + 1);
    current.remove(current.size() - 1);  // Restore state
}
```

### ❌ Wrong Start Index for Combinations

```java
// WRONG - generates duplicates
for (int i = 0; i < nums.length; i++) {  // Always starts at 0
    backtrack(result, current, nums, i + 1);
}

// CORRECT - use start index to avoid duplicates
void backtrack(List<List<Integer>> result, List<Integer> current,
               int[] nums, int start) {
    for (int i = start; i < nums.length; i++) {  // Start from 'start'
        backtrack(result, current, nums, i + 1);
    }
}
```

### ❌ Modifying Grid Without Restoring

```java
// WRONG - not restoring cell after backtracking
board[row][col] = '#';
backtrack(board, row + 1, col);
// Missing: restore original value

// CORRECT - save and restore
char temp = board[row][col];
board[row][col] = '#';
backtrack(board, row + 1, col);
board[row][col] = temp;  // Restore
```

---

## Problems

- [x] [Permutations II](https://leetcode.com/problems/permutations-ii/) - Medium
- [x] [Combination Sum](https://leetcode.com/problems/combination-sum/) - Medium
- [x] [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [N-Queens](https://leetcode.com/problems/n-queens/) - Hard
- [x] [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) - Hard
- [x] [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) - Medium
- [x] [Word Search](https://leetcode.com/problems/word-search/) - Medium

### Generate Parentheses ⭐ **IMPORTANT** ⭐

**Problem**: [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) - Medium

**Why Important**: Classic backtracking with constraints, demonstrates pruning invalid paths, frequently asked

**Approach**:
1. Use backtracking to build valid combinations
2. Track count of open '(' and close ')' parentheses
3. Add '(' if open < n
4. Add ')' if close < open (ensures valid)
5. Base case: when string length = 2n

**Complexity**: O(4^n / √n) time (Catalan number), O(n) space

**Solution**:

```java
public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    backtrack(result, new StringBuilder(), 0, 0, n);
    return result;
}

private void backtrack(List<String> result, StringBuilder current,
                       int open, int close, int n) {
    // Base case: formed valid combination
    if (current.length() == 2 * n) {
        result.add(current.toString());
        return;
    }

    // Add '(' if we haven't used all n open parentheses
    if (open < n) {
        current.append('(');
        backtrack(result, current, open + 1, close, n);
        current.deleteCharAt(current.length() - 1);  // Backtrack
    }

    // Add ')' only if it doesn't exceed open count (ensures validity)
    if (close < open) {
        current.append(')');
        backtrack(result, current, open, close + 1, n);
        current.deleteCharAt(current.length() - 1);  // Backtrack
    }
}
```

**Key Points**:
- **Constraint pruning**: Only add ')' when `close < open` prevents invalid combinations
- **Early termination**: Check `open < n` before adding '(' avoids exploring invalid paths
- More efficient than generating all and filtering invalid ones
- Can use `StringBuilder` for better performance than String concatenation
- Time complexity is Catalan number C(n) = (2n)! / ((n+1)! × n!)

---

## Key Takeaways

1. **Template**: Choose → Explore → Unchoose (backtrack)
2. **Always create new list** when adding to result (avoid reference issues)
3. **Use start index** in combinations to avoid duplicates
4. **Restore state** after recursive call (backtrack properly)
5. **Pruning**: Add constraints in loop to avoid exploring invalid paths
6. **Permutations vs Combinations**: Order matters vs order doesn't matter
7. **Typical complexity**: O(b^d) exponential time, O(d) space for recursion
8. **Mark visited** in grid problems, unmark when backtracking

---

> **[← DFS](../depth-first-search/Notes.md)** | **[Back to Overview](../README.md)** | **[Binary Search →](../binary-search/Notes.md)**
