# Game Theory

> **[← Back to Questions List](../../docs/questions-list.md)**

---

## When to Use

✅ **Two players take turns, both play optimally, and you must decide who wins (or the winning margin).**

**Keywords**: "two players", "take turns", "both play optimally", "Alice and Bob", "win the game", "last stone", "cannot make a move loses".

**Examples**: pick coins from either end of a row, remove stones under a rule, choose a number to reach a target.

---

## Core Concept

Almost every interview "game" is **minimax in disguise**: the mover maximizes *their own* outcome, which is the same as maximizing the gap over the opponent. Since both play optimally, the opponent's best reply is baked into your value.

**Two solving styles**:
- **DP / minimax** — general; model the state and recurse. Works when there's no clever shortcut.
- **Math / parity shortcut** — some games collapse to a one-liner (Nim, Divisor Game). Spot these to skip the DP entirely.

**Key trick**: score as a **relative difference** (current player − opponent) instead of two separate totals. One player always maximizes it; sign of the final value decides the winner.

**Complexity**: minimax DP is typically O(n²) time/space for interval games; shortcuts are O(1).

---

## Pattern 1: Minimax via Score Difference ⭐ **IMPORTANT** ⭐

**Use Case**: Turn-based games where you pick from a sequence (usually the two ends) and want to know if / by how much the first player wins.

**Algorithm**:
1. Let `dp[i][j]` = **best score difference** (mover − opponent) achievable on subarray `[i, j]`.
2. Base: a single element `dp[i][i] = nums[i]` — the mover takes it.
3. Transition: the mover takes `nums[i]` **or** `nums[j]`; the opponent then plays optimally on the rest, contributing their own best difference which is *subtracted*:
   `dp[i][j] = max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1])`.
4. First player wins (or ties) iff `dp[0][n-1] >= 0`.

**Complexity**: O(n²) time, O(n²) space (reducible to O(n)).

### Template

```java
// dp[i][j] = max (mover - opponent) score difference on nums[i..j]
int n = nums.length;
int[][] dp = new int[n][n];
for (int i = 0; i < n; i++) dp[i][i] = nums[i];

for (int len = 2; len <= n; len++) {
    for (int i = 0; i + len - 1 < n; i++) {
        int j = i + len - 1;
        dp[i][j] = Math.max(nums[i] - dp[i + 1][j],
                            nums[j] - dp[i][j - 1]);
    }
}
// dp[0][n-1] >= 0  →  first player wins or ties
```

---

## Pattern 2: Nim & Parity Shortcuts

**Use Case**: Games where a state reduces to a **counting/parity fact**, so no DP is needed. Recognize these to answer in O(1).

**Algorithm** (reason from small cases, find the losing positions):
- **Nim Game** (take 1–3 stones, taking the last stone wins): you lose **iff** `n % 4 == 0`. Any other count lets you move to a multiple of 4 and mirror the opponent.
- **Divisor Game** (subtract a proper divisor; who can't move loses): first player wins **iff** `n` is **even**.
- **Stone Game** (even count, fixed total, pick from ends): first player **always** wins — parity lets them claim all odd- or all even-indexed piles.

**Complexity**: O(1) time and space.

```java
public boolean canWinNim(int n) {
    return n % 4 != 0;   // losing positions are the multiples of 4
}
```

**How to spot one**: hand-simulate n = 1, 2, 3, 4, 5… and list which are losses for the mover. A clean period (every 4th, every even) means a shortcut exists; no pattern means fall back to Pattern 1.

---

## Common Mistakes

1. **Tracking two separate scores** instead of one difference — doubles the state and the bugs. Model `mover − opponent` and let a single player maximize it.
2. **Forgetting the opponent also plays optimally** — you must *subtract* the opponent's best result (`nums[i] - dp[i+1][j]`), not add a greedy pick. Greedy "take the larger end" is wrong (fails on `[1, 5, 233, 7]`).
3. **Reaching for DP when a parity trick exists** — Nim / Divisor Game are O(1); writing an O(n) DP wastes interview time and can TLE on huge `n`.
4. **Wrong base / overflow** — initialize `dp[i][i]` to the element itself; watch `int` overflow when summing large piles (use `long` if needed).

---

## Problems

- [ ] [Nim Game](https://leetcode.com/problems/nim-game/) - Easy *(parity: `n % 4 != 0`)*
- [ ] [Divisor Game](https://leetcode.com/problems/divisor-game/) - Easy *(parity: `n` even)*
- [x] [Predict the Winner](https://leetcode.com/problems/predict-the-winner/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Stone Game](https://leetcode.com/problems/stone-game/) - Medium *(same interval DP; first player always wins)*

### Predict the Winner ⭐ **IMPORTANT** ⭐

**Problem**: [Predict the Winner](https://leetcode.com/problems/predict-the-winner/) - Medium

**Why Important**: The canonical interview game — establishes the **minimax score-difference** DP that every "pick from the ends" / Stone Game variant reuses. The subtract-the-opponent recurrence is the non-obvious insight.

**Approach**:
1. `dp[i][j]` = best score difference the current mover can secure on `nums[i..j]`.
2. Mover takes a boundary element; opponent then optimizes the remaining range, so subtract `dp` of that subrange.
3. First player wins or ties iff `dp[0][n-1] >= 0`.

**Complexity**: O(n²) time, O(n²) space (O(n) with a rolling 1D array).

**Solution**:
```java
public boolean predictTheWinner(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n][n];

    // Base case: single element — mover just takes it
    for (int i = 0; i < n; i++) {
        dp[i][i] = nums[i];
    }

    // Fill by increasing subarray length
    for (int len = 2; len <= n; len++) {
        for (int i = 0; i + len - 1 < n; i++) {
            int j = i + len - 1;
            // Take left end, or right end; opponent plays the rest optimally
            int takeLeft  = nums[i] - dp[i + 1][j];
            int takeRight = nums[j] - dp[i][j - 1];
            dp[i][j] = Math.max(takeLeft, takeRight);
        }
    }

    return dp[0][n - 1] >= 0;   // first player's margin is non-negative
}
```

**Key Points**:
- **Difference, not two totals**: `dp[i][j]` folds both players into one number the mover maximizes.
- **Subtract the opponent**: after your pick, the opponent's optimal `dp` on the remainder works against you.
- **Fill order**: by length (or `i` descending, `j` ascending) so `dp[i+1][j]` and `dp[i][j-1]` are ready.
- **Same skeleton** solves Stone Game and Stone Game variants — only the win condition/constraints change.

---

## Key Takeaways

1. **Most interview games = minimax DP**; frame value as **mover − opponent** and one player maximizes it.
2. **Interval games**: `dp[i][j] = max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1])`, answer `dp[0][n-1] >= 0`.
3. **Check for a parity/Nim shortcut first** — Nim (`n % 4`), Divisor Game (even), Stone Game (always win) are O(1).
4. **Never greedy**: the opponent's optimal reply must be subtracted, not ignored.
5. **Overlaps DP heavily** — if stuck, model the state and recurse as you would any DP.

---

> **[← Back to Questions List](../../docs/questions-list.md)**
