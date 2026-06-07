# Partition DP (Matrix Chain Multiplication)

> **[← Back to Dynamic Programming Overview](Notes.md)**

---

## When to Use

✅ **Optimally split a sequence into pieces, where the cost of a piece depends on its boundaries (not just its contents)**

### Keywords
- "matrix chain multiplication", "MCM"
- "minimum cost to cut", "burst balloons"
- "partition array", "palindrome partitioning"
- "split into k parts", "merge stones"
- "optimal way to combine / divide a range"

### Examples
- Cheapest order to multiply a chain of matrices
- Max coins from bursting balloons in some order
- Min cuts to partition a string into palindromes
- Min cost to cut a wooden stick at given positions

---

## Core Concept

Two flavors of partition DP appear in this family:

- **Interval DP (MCM-style)**: `dp[i][j]` = optimal cost over subrange `[i..j]`. Try every split point `k`, combine the two halves: `dp[i][j] = best over k of (dp[i][k] + dp[k+1][j] + cost(i, k, j))`. Fill **by length** — small ranges before large ones.
- **Linear Partition DP**: `dp[i]` = optimal answer for `s[0..i)`. Look back at every cut point `j < i` and combine: `dp[i] = best over j of (dp[j] + cost(j, i))`.

**Key Insight**: In interval DP, the split index `k` is the *thing* that distinguishes one subproblem from another — pick the split that names a fixed element (last-to-burst, last-matrix-to-multiply) and the recurrence usually clicks.

**Complexity**: O(n³) time, O(n²) space (interval DP); O(n²) time, O(n) space (linear)

---

## Pattern: Interval DP (Matrix Chain Multiplication)

**Use Case**: Combine adjacent elements of a range, where each combination's cost depends on the range boundaries.

**Algorithm**:
1. `dp[i][j]` = optimal cost of resolving subrange `[i..j]`
2. Base: `dp[i][i] = 0` (or domain-specific) — single element costs nothing
3. Iterate by **length** `len = 2..n`, then `i` (with `j = i + len - 1`)
4. For each `(i, j)`, try every split `k` in `[i, j-1]`:
   `dp[i][j] = min over k of (dp[i][k] + dp[k+1][j] + costToMerge(i, k, j))`
5. Answer: `dp[0][n-1]`

**Complexity**: O(n³) time, O(n²) space

### Template (Matrix Chain Multiplication)

```java
public int matrixChainOrder(int[] dims) {
    int n = dims.length - 1;                 // n matrices, dims has n+1 sizes
    int[][] dp = new int[n][n];

    for (int len = 2; len <= n; len++) {
        for (int i = 0; i + len - 1 < n; i++) {
            int j = i + len - 1;
            dp[i][j] = Integer.MAX_VALUE;
            for (int k = i; k < j; k++) {
                int cost = dp[i][k] + dp[k + 1][j] + dims[i] * dims[k + 1] * dims[j + 1];
                dp[i][j] = Math.min(dp[i][j], cost);
            }
        }
    }
    return dp[0][n - 1];
}
```

---

## Pattern: Burst Balloons (Last-to-Act) ⭐ **IMPORTANT** ⭐

**Use Case**: Combine elements of a range where each element's cost depends on its **current** neighbors after earlier moves have removed others.

**Why Important**: The naive framing "which balloon to burst *first*" entangles every subproblem with the past — neighbors keep shifting. The trick is to flip it: pick the balloon that bursts **last** in the range. By definition, that balloon's neighbors are the original boundaries `nums[i-1]` and `nums[j+1]` — past bursts on each side don't reach across it. This decouples the two halves and lets MCM-style DP work.

**Algorithm**:
1. Pad `nums` with `1` on both ends: `[1, ...nums, 1]` (virtual neighbors at boundaries)
2. `dp[i][j]` = max coins from bursting all balloons in open range `(i, j)` (exclusive)
3. Pick `k` ∈ `(i, j)` as the **last** balloon to burst in this range
4. Coins from `k` last = `nums[i] * nums[k] * nums[j]` (its neighbors are `i` and `j` because everything between is already gone in the subproblems)
5. Recurrence: `dp[i][j] = max over k of (dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j])`
6. Iterate by length; answer: `dp[0][n+1]`

**Complexity**: O(n³) time, O(n²) space

### Template

```java
public int maxCoins(int[] nums) {
    int n = nums.length;
    int[] arr = new int[n + 2];
    arr[0] = arr[n + 1] = 1;
    for (int i = 0; i < n; i++) arr[i + 1] = nums[i];

    int[][] dp = new int[n + 2][n + 2];

    for (int len = 2; len <= n + 1; len++) {                  // gap between i and j
        for (int i = 0; i + len <= n + 1; i++) {
            int j = i + len;
            for (int k = i + 1; k < j; k++) {                 // k = last to burst in (i, j)
                int coins = arr[i] * arr[k] * arr[j];
                dp[i][j] = Math.max(dp[i][j],
                                    dp[i][k] + dp[k][j] + coins);
            }
        }
    }
    return dp[0][n + 1];
}
```

**Key Points**:
- **Reverse the question**: not "which to burst first?" but "which bursts last?" — this is the entire trick
- **Pad with 1s**: virtual boundaries simplify `nums[i-1]` and `nums[j+1]` edge cases
- **Open interval `(i, j)`**: `dp[i][j]` excludes endpoints; `k` ranges strictly between them
- **Length = gap, not count**: iterate `len = j - i` from `2` up so smaller gaps fill first
- Same recurrence shape works for **Min Cost to Cut a Stick**: sort cut positions, prepend `0` and append stick length, then `dp[i][j] = min over k of (dp[i][k] + dp[k][j] + cuts[j] - cuts[i])`

---

## Pattern: Linear Partition DP

**Use Case**: Split a sequence `s[0..n)` into a sequence of segments to minimize/maximize a sum, where each segment's cost depends only on the segment itself.

**Algorithm**:
1. `dp[i]` = optimal answer for the prefix `s[0..i)`
2. Base: `dp[0] = 0` (empty prefix)
3. For each `i`, consider every cut point `j` such that the last segment is `s[j..i)`:
   `dp[i] = best over j of (dp[j] + cost(j, i))`
4. Answer: `dp[n]`

**Complexity**: O(n²) time × cost-fn evaluation, O(n) space

### Template (Palindrome Partitioning II — min cuts)

```java
public int minCut(String s) {
    int n = s.length();
    boolean[][] pal = new boolean[n][n];                      // pal[i][j] = s[i..j] palindrome?
    for (int len = 1; len <= n; len++) {
        for (int i = 0; i + len - 1 < n; i++) {
            int j = i + len - 1;
            pal[i][j] = s.charAt(i) == s.charAt(j) && (len <= 2 || pal[i + 1][j - 1]);
        }
    }

    int[] dp = new int[n];                                    // dp[i] = min cuts for s[0..i]
    for (int i = 0; i < n; i++) {
        if (pal[0][i]) { dp[i] = 0; continue; }               // whole prefix is palindrome
        dp[i] = Integer.MAX_VALUE;
        for (int j = 1; j <= i; j++) {
            if (pal[j][i]) dp[i] = Math.min(dp[i], dp[j - 1] + 1);
        }
    }
    return dp[n - 1];
}
```

**Variations**:
- **Partition Array for Max Sum**: `dp[i] = max over (i - k <= j < i) of (dp[j] + max(arr[j..i)) * (i - j))` — last segment of size at most `k`
- **Palindrome Partitioning II**: precompute palindrome table, then `dp[i] = 1 + min over valid cuts`

---

## Common Mistakes

- ❌ **Filling interval DP row-by-row** — must iterate by **length** (short → long), or `dp[i][k]` and `dp[k+1][j]` won't be ready
- ❌ **Burst Balloons: thinking "burst first"** — entangles subproblems; always frame as "burst last"
- ❌ **Off-by-one with open vs closed intervals** — Burst Balloons uses open `(i, j)` (endpoints excluded); MCM uses closed `[i, j]`. Pick one and stay consistent
- ❌ **Forgetting to pad** in Burst Balloons / Min Cost to Cut a Stick — virtual boundaries collapse edge-case handling
- ❌ **Recomputing `cost(j, i)` inside the inner loop** for Palindrome Partitioning II — precompute the palindrome table or you'll get O(n³) instead of O(n²)

---

## Problems

- [x] [Partition Array for Maximum Sum](https://leetcode.com/problems/partition-array-for-maximum-sum/) - Medium
- [x] [Burst Balloons](https://leetcode.com/problems/burst-balloons/) - Hard ⭐ **IMPORTANT** ⭐
- [x] [Minimum Cost to Cut a Stick](https://leetcode.com/problems/minimum-cost-to-cut-a-stick/) - Hard
- [x] [Palindrome Partitioning II](https://leetcode.com/problems/palindrome-partitioning-ii/) - Hard

---

## Key Takeaways

1. **Two flavors**: interval DP `dp[i][j]` (MCM-style, O(n³)) and linear partition `dp[i]` (O(n²))
2. **Iterate by length** in interval DP — short ranges before long ones, since a long range's recurrence reads short-range values
3. **Pick a split point that names a fixed element** — for Burst Balloons / Min Cost to Cut a Stick, that element is the "last to act"
4. **Pad with sentinels** at the boundaries when the cost function reads neighbors — eliminates edge cases
5. **Precompute reusable predicates** (palindrome table) when the cost function is queried inside a tight loop
6. **The split `k` is your DP knob** — the recurrence is always "best over `k` of two halves plus a join cost"

---

> **[← Back to Dynamic Programming Overview](Notes.md)**
