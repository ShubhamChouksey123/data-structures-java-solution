# DP on Grids

> **[← Back to Dynamic Programming Overview](Notes.md)**

---

## When to Use

✅ **Path counting or min/max-cost problems on a 2D matrix where each cell's answer depends on its neighbors**

### Keywords
- "unique paths", "number of paths", "robot in grid"
- "minimum path sum", "minimum cost", "falling path"
- "largest / maximal square (or rectangle)"
- "cherry pickup", "dungeon game"
- "from top-left to bottom-right" (or any fixed start/end)

### Examples
- Count paths from top-left to bottom-right with obstacles
- Min cost path through a weighted grid
- Largest all-1s square submatrix
- Min HP needed to traverse a dungeon

---

## Core Concept

`dp[i][j]` = best (count / min / max) result for reaching cell `(i, j)` from a fixed origin.

**Key Insight**: Most grid DPs combine answers from a small set of neighbors — typically `dp[i-1][j]` (top), `dp[i][j-1]` (left), and sometimes `dp[i-1][j-1]` (diagonal). Fill row-by-row, left-to-right; the first row and column are the base cases.

**Complexity**: O(m × n) time, O(m × n) → O(min(m, n)) space (rolling 1D array)

---

## Pattern: Standard 2D DP (Path / Min-Cost)

**Use Case**: Reach `(m-1, n-1)` from `(0, 0)` minimizing/counting along top + left moves

**Algorithm**:
1. Base case: `dp[0][0] = grid[0][0]`; first row/column accumulate from one side
2. For each `(i, j)`: `dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])` (or `+=` for path counting)
3. Answer is `dp[m-1][n-1]`

**Complexity**: O(m × n) time, O(n) space (1D rolling)

### Template (Minimum Path Sum)

```java
public int minPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m][n];
    dp[0][0] = grid[0][0];

    for (int j = 1; j < n; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];
    for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
        }
    }
    return dp[m - 1][n - 1];
}
```

**Variations**:
- **Unique Paths II**: `dp[i][j] = dp[i-1][j] + dp[i][j-1]`; obstacle cell → `dp[i][j] = 0`
- **Triangle**: row `i` has `i+1` cells; `dp[i][j] = triangle[i][j] + min(dp[i-1][j-1], dp[i-1][j])` (handle edges)
- **Minimum Falling Path Sum**: 3 neighbors above (`j-1, j, j+1`) instead of 2

---

## Pattern: Maximal Square ⭐ **IMPORTANT** ⭐

**Use Case**: Find the side length of the largest all-1s square submatrix

**Why Important**: The transition `min(top, left, diagonal) + 1` is non-obvious — it's the unique-trick interview problem in this section. Easy to confuse with histogram-based "Maximal Rectangle."

**Algorithm**:
1. `dp[i][j]` = side length of the largest square whose **bottom-right corner** is at `(i, j)`
2. If `grid[i][j] == '0'`, then `dp[i][j] = 0`
3. Else `dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])`
4. Answer: square of the maximum value across `dp`

**Complexity**: O(m × n) time, O(m × n) → O(n) space

### Template

```java
public int maximalSquare(char[][] grid) {
    int m = grid.length, n = grid[0].length, best = 0;
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (grid[i - 1][j - 1] == '1') {
                dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                best = Math.max(best, dp[i][j]);
            }
        }
    }
    return best * best;
}
```

**Key Points**:
- **Why `min` of three neighbors?** A square of side `s` at `(i, j)` requires squares of side `s-1` at all three preceding corners (top, left, diagonal). The smallest of those bounds the square here.
- **Padded `(m+1) × (n+1)` array** avoids boundary checks for `i=0` / `j=0`
- Answer is `best * best` (area), not `best` (side length)

---

## Pattern: Backwards DP ⭐ **IMPORTANT** ⭐

**Use Case**: When the answer at `(i, j)` depends on what comes **after**, not before — e.g. minimum starting health (Dungeon Game)

**Why Important**: Forward DP fails because the cost at the end constrains the start. Recognizing when to fill the table from `(m-1, n-1)` back to `(0, 0)` is the trick interviewers probe.

**Algorithm (Dungeon Game)**:
1. `dp[i][j]` = minimum HP needed **entering** cell `(i, j)` to survive to the princess
2. Fill from bottom-right: `dp[m-1][n-1] = max(1, 1 - dungeon[m-1][n-1])`
3. Transition: `need = min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]`; clamp to `max(1, need)`
4. Answer: `dp[0][0]`

**Complexity**: O(m × n) time, O(n) space (1D rolling)

### Template

```java
public int calculateMinimumHP(int[][] dungeon) {
    int m = dungeon.length, n = dungeon[0].length;
    int[][] dp = new int[m + 1][n + 1];
    for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
    dp[m][n - 1] = dp[m - 1][n] = 1;

    for (int i = m - 1; i >= 0; i--) {
        for (int j = n - 1; j >= 0; j--) {
            int need = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
            dp[i][j] = Math.max(1, need);
        }
    }
    return dp[0][0];
}
```

**Key Points**:
- **Direction matters**: forward DP can't decide minimum starting HP without knowing future losses
- **Clamp to `max(1, need)`**: HP must stay ≥ 1 at every cell, even after a positive room
- **Sentinel `MAX_VALUE`** at virtual boundary cells; `dp[m][n-1]` and `dp[m-1][n]` set to `1` (need 1 HP to "exit")

---

## Common Mistakes

- ❌ **Forgetting to seed first row/column** — these are base cases, not part of the recurrence
- ❌ **Returning `best` instead of `best * best`** in Maximal Square (it's an area, not a side)
- ❌ **Forward DP on Dungeon Game** — fails because future damage constrains the past
- ❌ **Triangle: not handling `j == 0` and `j == i` edges** — they have only one parent above

---

## Problems

- [x] [Unique Paths II](https://leetcode.com/problems/unique-paths-ii/) - Medium
- [x] [Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/) - Medium
- [x] [Triangle](https://leetcode.com/problems/triangle/) - Medium
- [x] [Minimum Falling Path Sum](https://leetcode.com/problems/minimum-falling-path-sum/) - Medium
- [x] [Maximal Square](https://leetcode.com/problems/maximal-square/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Cherry Pickup](https://leetcode.com/problems/cherry-pickup/) - Hard
- [x] [Cherry Pickup II](https://leetcode.com/problems/cherry-pickup-ii/) - Hard
- [ ] [Dungeon Game](https://leetcode.com/problems/dungeon-game/) - Hard ⭐ **IMPORTANT** ⭐

---

> **[← Back to Dynamic Programming Overview](Notes.md)**
