# Infinite Supply (Unbounded Knapsack)

> **[← Back to Dynamic Programming Overview](Notes.md)**

---

## When to Use

✅ **Pick items to optimize a value under a capacity constraint — each item can be used unlimited times**

### Keywords
- "unlimited supply", "infinite items"
- "minimum coins / minimum count"
- "number of ways to make change"
- "each item can be reused"
- "fewest operations to reach target"

### Examples
- Minimum number of coins to make an amount
- Number of distinct ways to make change
- Minimum perfect squares summing to n
- Minimum travel pass cost covering required days

---

## Core Concept

For each item, you can use it **any number of times** — unlike 0/1 knapsack where each item is used at most once.

**Key Insight**: Use a 1D DP array and iterate capacity **forward** (low → high). When `dp[j - coin]` is read, it has **already been updated in this pass** — meaning the current item was already counted at that smaller capacity. This is exactly what we want for unbounded reuse.

**Complexity**: O(n × W) time, O(W) space

### Why Forward Iteration (Low → High)?

In the 1D approach, `dp[j] = min(dp[j], dp[j - coin] + 1)` (or `dp[j] += dp[j - coin]`).

When iterating **low → high**, `dp[j - coin]` was already updated in this pass — it already includes the current item. So using it again is valid: we're counting an additional use of the same item.

When iterating **high → low** (0/1 knapsack style), `dp[j - coin]` is the value **before** this item — each item can only be used once.

```
Coin: value=2.  dp before: [1, 0, 0, 0, 0]  (dp[0]=1, count ways)

✅ Low → High (coin reused):
  j=2: dp[2] += dp[0] = 1   → dp: [1,0,1,0,0]
  j=4: dp[4] += dp[2] = 1   → dp[2] already reflects coin=2 being used once → coin used twice ✓

❌ High → Low (0/1 knapsack — coin used once):
  j=4: dp[4] += dp[2] = 0   → dp[2] not yet updated, old value
  j=2: dp[2] += dp[0] = 1   → coin used once only
```

**Contrast with 0/1 Knapsack**: The only code change is the loop direction — `for (j = coin; j <= capacity; j++)` instead of `for (j = capacity; j >= coin; j--)`.

---

## Pattern: Minimize Count (Fewest Items)

**Use Case**: Find the minimum number of items needed to reach exactly `target`

**Algorithm**:
1. Initialize `dp[0] = 0`, all others = `target + 1` (acts as infinity)
2. For each item, iterate `j` from `item` to `target` (**forward!**)
3. `dp[j] = min(dp[j], dp[j - item] + 1)`
4. Answer is `dp[target]` if `< target + 1`, else `-1` (unreachable)

**Complexity**: O(n × target) time, O(target) space

### Template

```java
public int minItems(int[] items, int target) {
    int[] dp = new int[target + 1];
    Arrays.fill(dp, target + 1);  // "infinity"
    dp[0] = 0;

    for (int item : items) {
        for (int j = item; j <= target; j++) {  // forward!
            dp[j] = Math.min(dp[j], dp[j - item] + 1);
        }
    }

    return dp[target] <= target ? dp[target] : -1;
}
```

---

## Pattern: Count Ways

**Use Case**: Count distinct combinations of items that sum to exactly `target`

**Algorithm**:
1. Initialize `dp[0] = 1`, all others = `0`
2. For each item, iterate `j` from `item` to `target` (**forward!**)
3. `dp[j] += dp[j - item]`
4. Answer is `dp[target]`

**Complexity**: O(n × target) time, O(target) space

### Template

```java
public int countWays(int[] items, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;  // one way to reach 0: use nothing

    for (int item : items) {
        for (int j = item; j <= target; j++) {  // forward!
            dp[j] += dp[j - item];
        }
    }

    return dp[target];
}
```

---

## Common Mistakes

### ❌ Reverse Iteration (Treats as 0/1 Knapsack)

```java
// WRONG - reverse loop prevents reusing the same coin
for (int j = amount; j >= coin; j--) {
    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
}

// CORRECT - forward loop allows reusing coins
for (int j = coin; j <= amount; j++) {
    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
}
```

### ❌ Wrong "Infinity" Initialization for Minimize

```java
// WRONG - Integer.MAX_VALUE + 1 overflows to Integer.MIN_VALUE
Arrays.fill(dp, Integer.MAX_VALUE);
dp[j] = Math.min(dp[j], dp[j - coin] + 1);  // overflow!

// CORRECT - use amount + 1 as safe sentinel
Arrays.fill(dp, amount + 1);
dp[j] = Math.min(dp[j], dp[j - coin] + 1);
```

### ❌ Wrong Answer Check

```java
// WRONG - forgets to check if target is actually reachable
return dp[amount];

// CORRECT - sentinel value means no valid combination found
return dp[amount] <= amount ? dp[amount] : -1;
```

---

## Problems

- [x] [Coin Change](https://leetcode.com/problems/coin-change/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Coin Change II](https://leetcode.com/problems/coin-change-ii/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Perfect Squares](https://leetcode.com/problems/perfect-squares/) - Medium
- [ ] [Minimum Cost For Tickets](https://leetcode.com/problems/minimum-cost-for-tickets/) - Medium

---

### Coin Change ⭐ **IMPORTANT** ⭐

**Problem**: [Coin Change](https://leetcode.com/problems/coin-change/) - Medium

**Why Important**: Classic minimize unbounded knapsack — the `amount + 1` sentinel trick and forward iteration are the two most common interview mistakes

**Approach**:
1. `dp[j]` = fewest coins to make amount `j`
2. Base case: `dp[0] = 0`; sentinel: fill rest with `amount + 1`
3. For each coin, iterate `j` forward from `coin` to `amount`
4. `dp[j] = min(dp[j], dp[j - coin] + 1)`
5. If `dp[amount] > amount`, return `-1` (unreachable)

**Complexity**: O(n × amount) time, O(amount) space

**Solution**:

```java
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;

    for (int coin : coins) {
        for (int j = coin; j <= amount; j++) {
            dp[j] = Math.min(dp[j], dp[j - coin] + 1);
        }
    }

    return dp[amount] <= amount ? dp[amount] : -1;
}
```

**Key Points**:
- **Forward iteration** (low → high) — allows each coin to be reused
- **Sentinel `amount + 1`** instead of `Integer.MAX_VALUE` — avoids overflow on `+1`
- **Answer check**: `dp[amount] <= amount` (not `!= amount + 1`) is safe since the max valid answer is `amount` itself (all 1-value coins)
- Coin order doesn't matter — each coin is processed independently

---

### Coin Change II ⭐ **IMPORTANT** ⭐

**Problem**: [Coin Change II](https://leetcode.com/problems/coin-change-ii/) - Medium

**Why Important**: Count-ways variant of unbounded knapsack; easy to confuse with 0/1 count-ways (Target Sum) — the only difference is forward vs reverse iteration

**Approach**:
1. `dp[j]` = number of combinations to make amount `j`
2. Base case: `dp[0] = 1` (one way to make 0: use no coins)
3. For each coin, iterate `j` forward from `coin` to `amount`
4. `dp[j] += dp[j - coin]`
5. Answer is `dp[amount]`

**Complexity**: O(n × amount) time, O(amount) space

**Solution**:

```java
public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;

    for (int coin : coins) {
        for (int j = coin; j <= amount; j++) {
            dp[j] += dp[j - coin];
        }
    }

    return dp[amount];
}
```

**Key Points**:
- **Forward iteration** counts combinations (order doesn't matter — each coin processed once across all amounts)
- **`dp[0] = 1`** — base case: one way to make 0
- Result counts **combinations** (not permutations): `{1,2}` and `{2,1}` are the same
- Compare with 0/1 count-ways (Target Sum): same template, only change is `j` direction

---

## Key Takeaways

1. **Forward iteration** (`j` from coin to amount) is what makes it unbounded — allows item reuse
2. **Only code difference from 0/1**: loop direction (`low → high` vs `high → low`)
3. **Minimize flavor**: init `dp[0]=0`, rest `amount+1`; transition `min(dp[j], dp[j-item]+1)`
4. **Count ways flavor**: init `dp[0]=1`, rest `0`; transition `dp[j] += dp[j-item]`
5. **Sentinel `amount+1`**: safer than `Integer.MAX_VALUE` (avoids overflow on `+1`)
6. **Perfect Squares**: same as Coin Change — treat squares as coins (`1, 4, 9, 16...`)
7. **Min Cost Tickets**: day-indexed DP, not capacity DP — distinct pattern from classic knapsack

---

> **[← Back to Dynamic Programming Overview](Notes.md)**
