# Backtracking

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Generate all possible solutions, solve constraint puzzles, or explore decision trees**

### Keywords
- "all combinations", "all permutations", "all subsets"
- "generate all", "find all solutions"
- "valid arrangements", "satisfy constraints"
- "n-queens", "sudoku", "word search"

---

## Core Concept

Build candidates incrementally; abandon a path the moment it can't lead to a valid solution.

**Template**: Choose → Explore (recurse) → Unchoose (restore state) → Try next.

**Key Insight**: Always restore mutated state before returning, otherwise sibling branches see corrupted data.

**Complexity**: Typically O(b^d) time (b = branching factor, d = depth), O(d) space for recursion stack.

---

## Pattern 1: Permutations and Combinations

**Use Case**: Generate all arrangements (order matters) or selections (order ignored) from a set.

**Algorithm**:
1. Permutations — for each unused element, add to path, recurse, remove
2. Combinations — pass a `start` index so each recursion only considers later elements (prevents duplicates)

**Complexity**: O(n! × n) for permutations, O(n × 2^n) for combinations/subsets

### Template (Permutations)

```java
private void backtrack(List<List<Integer>> result, List<Integer> current,
                       int[] nums, boolean[] used) {
    if (current.size() == nums.length) {
        result.add(new ArrayList<>(current));
        return;
    }
    for (int i = 0; i < nums.length; i++) {
        if (used[i]) continue;
        current.add(nums[i]); used[i] = true;
        backtrack(result, current, nums, used);
        current.remove(current.size() - 1); used[i] = false;
    }
}
```

### Template (Combinations)

```java
private void backtrack(List<List<Integer>> result, List<Integer> current,
                       int start, int n, int k) {
    if (current.size() == k) {
        result.add(new ArrayList<>(current));
        return;
    }
    for (int i = start; i <= n; i++) {
        current.add(i);
        backtrack(result, current, i + 1, n, k);   // i + 1 enforces non-decreasing order
        current.remove(current.size() - 1);
    }
}
```

**Difference from Permutations**: no `used[]`; the `start` parameter (advancing to `i + 1` on recursion) ensures each combination is generated only once, in non-decreasing index order.

---

## Pattern 2: Subsets (Power Set)

**Use Case**: Generate all 2^n subsets of a set.

**Algorithm**: At each `start` index, record the current path (every state is a valid subset), then for each later index include it and recurse.

**Complexity**: O(n × 2^n) time, O(n) space

### Template

```java
private void backtrack(List<List<Integer>> result, List<Integer> current,
                       int[] nums, int start) {
    result.add(new ArrayList<>(current));   // every state is valid
    for (int i = start; i < nums.length; i++) {
        current.add(nums[i]);
        backtrack(result, current, nums, i + 1);
        current.remove(current.size() - 1);
    }
}
```

---

## Pattern 3: Constraint Satisfaction (N-Queens / Sudoku)

**Use Case**: Place items on a board under hard constraints; backtrack the moment a placement is invalid.

**Algorithm**: Try each candidate position; if `isValid` passes, place, recurse to next row/cell, then unplace.

**Complexity**: O(b^d) — exponential, pruning shrinks `b` heavily in practice

### Template (N-Queens)

```java
private void backtrack(List<List<String>> result, char[][] board, int row) {
    if (row == board.length) { result.add(construct(board)); return; }
    for (int col = 0; col < board.length; col++) {
        if (!isValid(board, row, col)) continue;
        board[row][col] = 'Q';
        backtrack(result, board, row + 1);
        board[row][col] = '.';                   // unchoose
    }
}
```

**`isValid`**: scan the column above, the up-left diagonal, and the up-right diagonal — any `'Q'` found means conflict. Lower rows are still empty so they need no check.

---

## Pattern 4: Grid Path Finding (Word Search)

**Use Case**: Find a path through a 2D grid that matches a sequence (e.g., spell out a word).

**Algorithm**: For each cell, DFS in 4 directions matching the next char. Mark the cell visited (overwrite with sentinel) before recursing; restore after.

**Complexity**: O(m × n × 4^L) where L = word length

### Template

```java
private boolean backtrack(char[][] board, String word, int r, int c, int idx) {
    if (idx == word.length()) return true;
    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length
            || board[r][c] != word.charAt(idx)) return false;

    char temp = board[r][c]; board[r][c] = '#';   // mark visited
    boolean found = backtrack(board, word, r + 1, c, idx + 1)
            || backtrack(board, word, r - 1, c, idx + 1)
            || backtrack(board, word, r, c + 1, idx + 1)
            || backtrack(board, word, r, c - 1, idx + 1);
    board[r][c] = temp;                           // restore
    return found;
}
```

---

## Pattern 5: Combination Sum (Reuse Allowed)

**Use Case**: Find all combinations summing to a target where each candidate may be used multiple times.

**Algorithm**: Same shape as Combinations, but recurse with `i` (not `i + 1`) so the current candidate can be reused. Prune when `remain < 0`.

**Complexity**: O(2^target) worst case

### Template

```java
private void backtrack(List<List<Integer>> result, List<Integer> current,
                       int[] candidates, int remain, int start) {
    if (remain < 0) return;
    if (remain == 0) { result.add(new ArrayList<>(current)); return; }
    for (int i = start; i < candidates.length; i++) {
        current.add(candidates[i]);
        backtrack(result, current, candidates, remain - candidates[i], i);   // i, not i+1
        current.remove(current.size() - 1);
    }
}
```

---

## Common Mistakes

- ❌ **`result.add(current)`** stores a live reference — wrap in `new ArrayList<>(current)` to snapshot
- ❌ **Forgetting to restore state** after recursing (path bullet, `used[]`, board cell) — siblings see corruption
- ❌ **Combinations without `start` index** — generates duplicates like `[1,2]` and `[2,1]`
- ❌ **Grid: not restoring the cell** after the four recursive calls — later searches treat it as still visited

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

**Why Important**: Classic backtracking with on-the-fly pruning — generate only valid candidates instead of all `2^(2n)` strings. Frequently asked.

**Approach**: Track running `open` and `close` counts. Add `'('` only if `open < n`; add `')'` only if `close < open` (otherwise the prefix becomes invalid). Stop when length is `2n`.

**Complexity**: O(4^n / √n) time (Catalan number), O(n) recursion depth

**Solution**:

```java
public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    backtrack(result, new StringBuilder(), 0, 0, n);
    return result;
}

private void backtrack(List<String> result, StringBuilder current,
                       int open, int close, int n) {
    if (current.length() == 2 * n) {
        result.add(current.toString());
        return;
    }
    if (open < n) {
        current.append('(');
        backtrack(result, current, open + 1, close, n);
        current.deleteCharAt(current.length() - 1);
    }
    if (close < open) {
        current.append(')');
        backtrack(result, current, open, close + 1, n);
        current.deleteCharAt(current.length() - 1);
    }
}
```

**Key Points**:
- **`close < open`** is the validity guard — never close more than is open
- Pruning at choice-time beats generate-and-filter (`2^(2n)` → Catalan)
- `StringBuilder` avoids `String` concat overhead in the recursion

---

## Key Takeaways

1. **Choose → Explore → Unchoose** — always restore state on the way back up
2. **Snapshot** with `new ArrayList<>(current)` when adding to results
3. **`start` index** prevents duplicate combinations; **`used[]`** tracks consumed elements for permutations
4. **Prune at choice-time** (validity guards) instead of validating leaves — exponential savings
5. **Mark / unmark** in grid problems with a sentinel like `'#'`, then restore

---

> **[← DFS](../depth-first-search/Notes.md)** | **[Back to Overview](../README.md)** | **[Binary Search →](../binary-search/Notes.md)**
