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

---

## Core Concept

`dp[i][j]` = best (count / min / max) result for reaching cell `(i, j)` from a fixed origin.

**Key Insight**: Most grid DPs combine answers from a small set of neighbors — typically `dp[i-1][j]` (top), `dp[i][j-1]` (left), and sometimes `dp[i-1][j-1]` (diagonal). Fill row-by-row, left-to-right; the first row and column are the base cases.

**Complexity**: O(m × n) time, O(m × n) → O(min(m, n)) space (rolling 1D array)

---

## Pattern: Standard 2D DP (Path / Min-Cost)

**Use Case**: Reach `(m-1, n-1)` from `(0, 0)` minimizing/counting along top + left moves

**Algorithm**: Seed first row/column from one side; then `dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])` (or `+=` for path counting). Answer is `dp[m-1][n-1]`.

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

**Why Important**: The `min(top, left, diagonal) + 1` transition is non-obvious; easy to confuse with histogram-based "Maximal Rectangle."

**Algorithm**: `dp[i][j]` = side of the largest square with **bottom-right corner** at `(i, j)`. If `grid[i][j] == '0'` → `0`; else `dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])`. Answer is `max(dp)²`.

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
- **`min` of three neighbors**: a side-`s` square needs side-`s-1` squares at all three preceding corners; the smallest bounds you
- **Padded `(m+1) × (n+1)`** array skips boundary checks; answer is `best * best` (area), not `best` (side)

---

## Pattern: Backwards DP ⭐ **IMPORTANT** ⭐

**Use Case**: When the answer at `(i, j)` depends on what comes **after**, not before — e.g. minimum starting health (Dungeon Game)

**Why Important**: Forward DP fails — end-cost constrains start. Filling from `(m-1, n-1)` back to `(0, 0)` is the trick interviewers probe.

**Algorithm**: `dp[i][j]` = min HP **entering** `(i, j)` to survive. Fill bottom-right to top-left: `need = min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j]`; `dp[i][j] = max(1, need)`. Answer is `dp[0][0]`.

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
- **Direction matters**: forward DP can't decide starting HP without knowing future losses
- **Clamp to `max(1, need)`** — HP must stay ≥ 1 at every cell, even after a positive room
- **Sentinel `MAX_VALUE`** at virtual boundary cells; `dp[m][n-1] = dp[m-1][n] = 1`

---

## Pattern: Two Simultaneous Paths ⭐ **IMPORTANT** ⭐

**Use Case**: Round-trip cherry collection from `(0, 0)` → `(n-1, n-1)` → back, maximizing cherries (Cherry Pickup)

**Why Important**: Greedy "best forward, then best back" is **wrong** — locally optimal paths starve the return. Reframe as **two paths walking forward simultaneously**, sharing cherries on overlap.

**Algorithm**: Walk two paths from `(0,0)` to `(n-1,n-1)`, both moving down/right. After `t` steps both lie on `r + c = t`, so state is `(r1, c1, c2)` with `r2 = r1 + c1 - c2`. Collect both cells' cherries; count once if `c1 == c2`. 4 next states (each path picks down or right). Thorn (`-1`) or OOB → `MIN_VALUE`; answer is `max(0, dp(0,0,0))`.

**Complexity**: O(n³) time, O(n³) space

### Template

```java
public int cherryPickup(int[][] grid) {
    int n = grid.length;
    Integer[][][] memo = new Integer[n][n][n];
    return Math.max(0, dp(grid, 0, 0, 0, memo));
}

private int dp(int[][] grid, int r1, int c1, int c2, Integer[][][] memo) {
    int n = grid.length;
    int r2 = r1 + c1 - c2;

    if (r1 >= n || c1 >= n || r2 >= n || c2 >= n
            || grid[r1][c1] == -1 || grid[r2][c2] == -1) {
        return Integer.MIN_VALUE;
    }
    if (r1 == n - 1 && c1 == n - 1) return grid[r1][c1];
    if (memo[r1][c1][c2] != null) return memo[r1][c1][c2];

    int cherries = grid[r1][c1];
    if (c1 != c2) cherries += grid[r2][c2];

    int next = Math.max(
        Math.max(dp(grid, r1 + 1, c1, c2, memo), dp(grid, r1 + 1, c1, c2 + 1, memo)),
        Math.max(dp(grid, r1, c1 + 1, c2, memo), dp(grid, r1, c1 + 1, c2 + 1, memo))
    );

    return memo[r1][c1][c2] = cherries + next;
}
```

**Key Points**:
- **Two forward paths ≡ round-trip**: a backward path reversed is a forward path; easier to express as DP
- **Diagonal invariant** `r1 + c1 == r2 + c2` derives `r2` → saves a dimension
- **Don't double-count overlap**: when `c1 == c2`, also `r1 == r2` — add `grid[r1][c1]` once
- **`Integer[][][]` over `int[][][]`**: `null` marks unvisited cleanly; `-1` collides with thorn cells

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
- [x] [Cherry Pickup](https://leetcode.com/problems/cherry-pickup/) - Hard ⭐ **IMPORTANT** ⭐
- [x] [Cherry Pickup II](https://leetcode.com/problems/cherry-pickup-ii/) - Hard
- [x] [Dungeon Game](https://leetcode.com/problems/dungeon-game/) - Hard ⭐ **IMPORTANT** ⭐

---

> **[← Back to Dynamic Programming Overview](Notes.md)**
