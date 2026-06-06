# Dynamic Programming

> **[← Back to Questions List](../../docs/questions-list.md)**

---

## Overview

Dynamic Programming (DP) solves problems by breaking them into **overlapping subproblems** and caching their results. The same recurrence is reused via either **top-down memoization** or **bottom-up tabulation**.

This topic is split into focused sections by recurrence shape:

1. **[Take / Not Take (0/1 Knapsack)](0-1-knapsack.md)** - Pick/skip each item once under a capacity constraint
2. **[Infinite Supply (Unbounded Knapsack)](unbounded-knapsack.md)** - Items can be reused unlimited times
3. **[Longest Increasing Subsequence (LIS)](longest-increasing-subsequence.md)** - Best subsequence under an ordering relation
4. **[DP on Grids](dp-on-grids.md)** - Path-counting and min/max-cost on 2D matrices
5. **[DP on Strings](dp-on-strings.md)** - Two-string recurrences (LCS, edit distance, palindromes)
6. **[DP on Stocks](dp-on-stocks.md)** - State-machine DP for buy/sell with constraints
7. **Partition DP (MCM)** *(upcoming)* - Choose where to split a sequence optimally

---

## Quick Reference

### When to Use Each Pattern

| Problem Type | Pattern | Time | Section |
|-------------|---------|------|---------|
| **Pick/skip items, each used once** | 0/1 Knapsack | O(n × W) | [→](0-1-knapsack.md) |
| **Pick items, each reusable** | Unbounded Knapsack | O(n × W) | [→](unbounded-knapsack.md) |
| **Longest chain under an order** | LIS | O(n²) or O(n log n) | [→](longest-increasing-subsequence.md) |
| **Paths / min cost on a 2D grid** | DP on Grids | O(m × n) | [→](dp-on-grids.md) |
| **Compare/transform two strings** | DP on Strings | O(m × n) | [→](dp-on-strings.md) |
| **Buy/sell with k transactions or cooldown** | DP on Stocks | O(n × k) | [→](dp-on-stocks.md) |
| **Optimal partition of a sequence** | Partition DP | O(n³) | *upcoming* |

### Keywords to Pattern Mapping

**"subset sum", "partition equal", "pick or skip", "each item once"**
→ [Take / Not Take (0/1 Knapsack)](0-1-knapsack.md)

**"coin change", "minimum coins", "number of ways to make change", "unlimited supply"**
→ [Infinite Supply (Unbounded Knapsack)](unbounded-knapsack.md)

**"longest increasing", "longest chain", "divisible subset", "follower / predecessor"**
→ [Longest Increasing Subsequence (LIS)](longest-increasing-subsequence.md)

**"unique paths", "minimum path sum", "falling path", "robot in grid"**
→ [DP on Grids](dp-on-grids.md)

**"longest common subsequence", "edit distance", "palindromic substring", "wildcard match"**
→ [DP on Strings](dp-on-strings.md)

**"buy and sell stock", "max profit", "transaction fee", "cooldown"**
→ [DP on Stocks](dp-on-stocks.md)

**"matrix chain multiplication", "burst balloons", "minimum cost to cut", "partition for max sum"**
→ Partition DP *(upcoming)*

---

## Core Concepts

### What Makes a DP Problem

A problem is DP-solvable when it has both:

1. **Optimal Substructure** - Optimal answer can be built from optimal answers to subproblems
2. **Overlapping Subproblems** - The same subproblem is solved many times by naive recursion

### Two Implementation Styles

**Top-Down (Memoization)** - Recursive, cache results
```java
int solve(int i, int j) {
    if (memo[i][j] != -1) return memo[i][j];
    return memo[i][j] = ...recurrence...;
}
```

**Bottom-Up (Tabulation)** - Iterative, fill a table from base cases up
```java
for (int i = 0; i < n; i++)
    for (int j = 0; j <= W; j++)
        dp[i][j] = ...recurrence...;
```

### Space Optimization

Many 2D tabulations only read from the **previous row/column**, so they reduce to a 1D array. Iteration direction (forward vs reverse) matters when reusing the same array — the canonical example is **0/1 vs unbounded knapsack**.

---

## Pattern Summaries

### 1. Take / Not Take (0/1 Knapsack)
**[→ Detailed Notes](0-1-knapsack.md)**

For each item, decide: take it (use capacity, gain value) or skip it. Each item used **at most once**.

**1D Tabulation**: iterate capacity **reverse** (high → low) so `dp[j - weight]` reads the value before this item.

**Variants**:
- Maximize value (`max`)
- Boolean reachability (`||`) — Partition Equal Subset Sum
- Count ways (`+=`) — Target Sum

**Complexity**: O(n × W) time, O(W) space

---

### 2. Infinite Supply (Unbounded Knapsack)
**[→ Detailed Notes](unbounded-knapsack.md)**

Same as 0/1 knapsack except each item can be used **unlimited times**.

**1D Tabulation**: iterate capacity **forward** (low → high) so `dp[j - item]` already includes the item from this pass.

**Variants**:
- Minimize count — Coin Change
- Count ways — Coin Change II

**Complexity**: O(n × W) time, O(W) space

---

### 3. Longest Increasing Subsequence (LIS)
**[→ Detailed Notes](longest-increasing-subsequence.md)**

`dp[i]` = length of the best subsequence ending at index `i`. Look back at every `j < i` and extend if the relation holds.

**Two approaches**:
- O(n²) DP — works for any extendable relation; supports reconstruction
- O(n log n) Patience Sorting — binary search on `tails[]`; only gives length

**Variants**:
- Reconstruction via `prev[]` — Largest Divisible Subset
- Length + count tracking — Number of LIS
- String-keyed DP — Longest String Chain

**Complexity**: O(n²) or O(n log n) time, O(n) space

---

### 4. DP on Grids
**[→ Detailed Notes](dp-on-grids.md)**

`dp[i][j]` = best result reaching cell `(i, j)`. Transitions usually come from `dp[i-1][j]` (top) and `dp[i][j-1]` (left).

**Variants**:
- Standard 2D DP (Unique Paths, Min Path Sum, Triangle, Falling Path)
- Maximal Square — `dp[i][j] = 1 + min(top, left, diagonal)`
- Backwards DP (Dungeon Game) — fill from bottom-right when future cost constrains the past

**Complexity**: O(m × n) time, O(min(m, n)) space (rolling)

---

### 5. DP on Strings
**[→ Detailed Notes](dp-on-strings.md)**

`dp[i][j]` = best result over `s1[0..i]` and `s2[0..j]`. Match → diagonal; mismatch → some combination of `dp[i-1][j]`, `dp[i][j-1]`, `dp[i-1][j-1]`. A second flavor uses `dp[i][j]` over a single substring `s[i..j]` (palindromic DP).

**Variants**:
- Two-string LCS-style — Longest Common Subsequence, Distinct Subsequences, Shortest Common Supersequence
- Edit Distance — three operations (insert / delete / replace)
- Palindromic substring DP — `dp[i][j]` filled by length
- Wildcard Matching — `?` and `*` with two-case `*` transition

**Complexity**: O(m × n) time, O(m × n) space

---

### 6. DP on Stocks
**[→ Detailed Notes](dp-on-stocks.md)**

State-machine DP — `dp[i][holding]` or `dp[i][k][holding]` tracks day, transactions used, and whether currently holding a share.

**Variants**:
- Unlimited transactions — two rolling scalars (`hold`, `cash`)
- K transactions — adds `j` dimension, count transaction once (on buy or sell)
- Cooldown — lag cash by one day before buying
- Transaction fee — subtract fee on sell

**Complexity**: O(n × k) time, O(k) space (rolling)

---

### 7. Partition DP (MCM) *(upcoming)*

`dp[i][j]` = optimal cost over the subrange `[i..j]`. Try every split point `k` between `i` and `j`.

**Use Cases**: Matrix Chain Multiplication, Burst Balloons, Palindrome Partitioning II, Min Cost to Cut Stick

**Complexity**: O(n³) time, O(n²) space

---

## Decision Tree

```
Need to solve a DP problem?
│
├─ Pick or skip items?
│  ├─ Each item once → 0/1 Knapsack (reverse iteration)
│  └─ Each item reusable → Unbounded Knapsack (forward iteration)
│
├─ Best subsequence under an ordering?
│  └─ LIS family (dp[i] = best ending at i)
│
├─ Path / cost on a 2D grid?
│  └─ DP on Grids (dp[i][j] from top + left)
│
├─ Operations between two strings?
│  └─ DP on Strings (dp[i][j] over s1, s2)
│
├─ Buy/sell with constraints?
│  └─ DP on Stocks (state machine over day × transactions × holding)
│
└─ Optimal way to split a sequence?
   └─ Partition DP (try every split point k in [i..j])
```

---

## Key Takeaways

1. **Two ingredients**: optimal substructure + overlapping subproblems
2. **Memoization vs tabulation**: same complexity, different implementation; tabulation often allows space optimization
3. **0/1 knapsack**: reverse iteration in 1D — `dp[j-w]` must be the *old* value
4. **Unbounded knapsack**: forward iteration in 1D — `dp[j-w]` should already include the item
5. **LIS**: `dp[i]` = best ending at `i`; answer is `max(dp)`, not `dp[n-1]`
6. **Reconstruction**: track parent pointers (`prev[]`) and walk back from the best index
7. **Space optimization**: many 2D tables reduce to 1D when only the previous row/column is read
8. **Identify the state first**: what changes between subproblems? That's your DP dimension(s)

---

## Next Steps

Choose a section to study in detail:

- **[Take / Not Take (0/1 Knapsack) →](0-1-knapsack.md)** - Pick/skip each item once
- **[Infinite Supply (Unbounded Knapsack) →](unbounded-knapsack.md)** - Items reusable unlimited times
- **[Longest Increasing Subsequence →](longest-increasing-subsequence.md)** - Best subsequence under an order
- **[DP on Grids →](dp-on-grids.md)** - Path-counting and min/max-cost on 2D matrices

---

> **[← Back to Questions List](../../docs/questions-list.md)**
