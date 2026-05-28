# Take / Not Take (0/1 Knapsack)

> **[← Back to Dynamic Programming Overview](Notes.md)**

---

## When to Use

✅ **Pick or skip items to optimize a value under a capacity constraint — each item used at most once**

### Keywords
- "pick or skip", "include or exclude"
- "subset sum", "partition"
- "count ways to reach target"
- "maximize/minimize with capacity"
- "each element used at most once"

### Examples
- Can we split an array into two equal-sum halves?
- Count ways to assign +/- signs to reach a target
- Rob houses without robbing adjacent ones
- Fill a knapsack with max value under weight limit

---

## Core Concept

For each item, make a binary decision: **take it** (reduce capacity, gain value) or **skip it** (keep capacity unchanged).

**Key Insight**: Use a 1D DP array where `dp[j]` = best result with capacity `j`. Iterate capacity in **reverse** (high → low) to ensure each item is used at most once.

**Complexity**: O(n × W) time, O(W) space

### Why Reverse Iteration (High → Low)?

In the 1D approach, `dp[j] = max(dp[j], dp[j - weight] + value)`.

When iterating **high → low**, `dp[j - weight]` has NOT been updated yet in this pass — it still holds the value from **before** the current item. So the item is counted at most once.

When iterating **low → high**, `dp[j - weight]` was already updated earlier in the same pass — meaning the current item was already "included" at that smaller capacity. Including it again at `dp[j]` effectively uses the same item twice → **unbounded knapsack**.

```
Item: weight=2, value=4.  dp before this item: [0, 1, 1, 1, 1, 1]

❌ Low → High (item used twice):
  j=2: dp[2] = max(1, dp[0]+4) = 4   → dp: [0,1,4,1,1,1]
  j=4: dp[4] = max(1, dp[2]+4) = 8   → dp[2] was already updated! Item counted twice.

✅ High → Low (item used once):
  j=4: dp[4] = max(1, dp[2]+4) = 5   → dp[2] still holds old value (1)
  j=2: dp[2] = max(1, dp[0]+4) = 4   → reads old dp[0], item counted once only
```

**Note**: This restriction only applies to the 1D optimization. The 2D table always reads from the previous row (`dp[i-1][...]`), so either iteration direction works.

---

## Pattern: Maximize Value (Classic Knapsack)

**Use Case**: Maximize total value without exceeding weight capacity

**Algorithm**:
1. Initialize `dp[0..W] = 0`
2. For each item with `weight` and `value`:
   - Iterate `j` from `W` down to `weight` (**reverse!**)
   - `dp[j] = max(dp[j], dp[j - weight] + value)`
3. Answer is `dp[W]`

**Complexity**: O(n × W) time, O(W) space

### Template

```java
public int knapsack(int[] weights, int[] values, int capacity) {
    int[] dp = new int[capacity + 1];

    for (int i = 0; i < weights.length; i++) {
        for (int j = capacity; j >= weights[i]; j--) {  // reverse!
            dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
        }
    }

    return dp[capacity];
}
```

### Visual Example

```
Items: weight=[1,2,3], value=[1,4,5], capacity=5

Initial dp:        [0, 0, 0, 0, 0, 0]

Item 0 (w=1, v=1): [0, 1, 1, 1, 1, 1]

Item 1 (w=2, v=4): [0, 1, 4, 5, 5, 5]

Item 2 (w=3, v=5): [0, 1, 4, 5, 6, 9]

Answer: dp[5] = 9  (take items 1+2: value 4+5=9)
```

---

## Pattern: 2D Tabulation (Bottom-Up)

**Use Case**: Same classic knapsack — 2D table is the most readable bottom-up form; the 1D optimization is derived from this

**Algorithm**:
1. `dp[i][w]` = max value using items `0..i` with capacity `w`
2. Base case: `dp[0][w] = val[0]` for all `w >= wt[0]`, else `0`
3. Transition: `dp[i][w] = max(dp[i-1][w], val[i] + dp[i-1][w - wt[i]])` if item fits
4. Answer is `dp[n-1][W]`

**Why low → high works here**: Every read is from `dp[index-1][...]` (previous row, already finalized) — never the current row. Iteration direction for `weight` doesn't matter.

**Complexity**: O(n × W) time, O(n × W) space

### Template

```java
public int knapsack(int W, int[] val, int[] wt) {
    int n = val.length;
    int[][] dp = new int[n][W + 1];

    // Base case: only item 0 available
    for (int w = wt[0]; w <= W; w++) {
        dp[0][w] = val[0];
    }

    for (int index = 1; index < n; index++) {
        for (int w = 0; w <= W; w++) {
            dp[index][w] = dp[index - 1][w];  // exclude

            if (wt[index] <= w) {
                dp[index][w] = Math.max(dp[index][w], val[index] + dp[index - 1][w - wt[index]]);  // include
            }
        }
    }

    return dp[n - 1][W];
}
```

---

## Pattern: Memoization (Top-Down)

**Use Case**: Same classic knapsack — recursive approach with caching, easier to reason about for some

**Algorithm**:
1. Define `solve(index, remainingCapacity)` = max value using items `0..index` with given capacity
2. Base cases: `remainingCapacity <= 0` → 0; `index == 0` → take item 0 if it fits
3. Cache result in `memo[index][remainingCapacity]` before returning
4. At each step: `max(exclude, include)` where include reduces capacity and moves to previous item

**Complexity**: O(n × W) time, O(n × W) space (memo) + O(n) stack space

### Template

```java
class Solution {

    private int[][] memo;

    private int knapsackUtil(int[] val, int[] wt, int index, int remainingCapacity) {
        if (remainingCapacity <= 0) return 0;

        if (index == 0) {
            return (remainingCapacity >= wt[0]) ? val[0] : 0;
        }

        if (memo[index][remainingCapacity] != -1) {
            return memo[index][remainingCapacity];
        }

        int exclude = knapsackUtil(val, wt, index - 1, remainingCapacity);
        int include = 0;
        if (remainingCapacity >= wt[index]) {
            include = val[index] + knapsackUtil(val, wt, index - 1, remainingCapacity - wt[index]);
        }

        return memo[index][remainingCapacity] = Math.max(include, exclude);
    }

    public int knapsack(int W, int[] val, int[] wt) {
        int n = val.length;
        memo = new int[n][W + 1];
        for (int[] row : memo) Arrays.fill(row, -1);
        return knapsackUtil(val, wt, n - 1, W);
    }
}
```

### Comparison: All Three Approaches

| | 2D Tabulation | Memoization (Top-Down) | 1D Tabulation |
|---|---|---|---|
| **Direction** | `0` → `n-1`, low→high | `n-1` → `0` (recursive) | `0` → `n-1`, **high→low** |
| **Space** | O(n × W) | O(n × W) + stack | O(W) |
| **Ease** | Most readable, direct recurrence | Natural recursive thinking | Compact, no stack overflow |
| **Weight iteration** | Either direction ✓ | N/A | Must be reverse ⚠️ |
| **When to prefer** | Learning / understanding | Deriving from scratch | Interviews, optimal space |

---

## Pattern: Boolean Subset Sum

**Use Case**: Can we pick a subset that sums to exactly `target`?

**Algorithm**:
1. Initialize `dp[0] = true`, all others `false`
2. For each `num`, iterate `j` from `target` down to `num` (reverse)
3. `dp[j] = dp[j] || dp[j - num]`
4. Answer is `dp[target]`

**Complexity**: O(n × target) time, O(target) space

### Template

```java
public boolean canReachTarget(int[] nums, int target) {
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;  // empty subset sums to 0

    for (int num : nums) {
        for (int j = target; j >= num; j--) {
            dp[j] = dp[j] || dp[j - num];
        }
    }

    return dp[target];
}
```

---

## Pattern: Count Ways (Subset Sum Count)

**Use Case**: How many subsets sum to exactly `target`?

**Algorithm**:
1. Initialize `dp[0] = 1`, all others `0`
2. For each `num`, iterate `j` from `target` down to `num` (reverse)
3. `dp[j] += dp[j - num]`
4. Answer is `dp[target]`

**Complexity**: O(n × target) time, O(target) space

### Template

```java
public int countSubsets(int[] nums, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;  // one way to reach sum 0: take nothing

    for (int num : nums) {
        for (int j = target; j >= num; j--) {
            dp[j] += dp[j - num];
        }
    }

    return dp[target];
}
```

---

## Common Mistakes

### ❌ Forward Iteration (Reuses Same Item)

```java
// WRONG - forward loop lets the same item be used multiple times (unbounded knapsack)
for (int j = weight; j <= capacity; j++) {
    dp[j] = Math.max(dp[j], dp[j - weight] + value);
}

// CORRECT - reverse loop ensures each item used at most once
for (int j = capacity; j >= weight; j--) {
    dp[j] = Math.max(dp[j], dp[j - weight] + value);
}
```

### ❌ Wrong Initialization for Boolean DP

```java
// WRONG - dp[0] must be true (empty subset sums to 0)
boolean[] dp = new boolean[target + 1];  // all false — nothing reachable

// CORRECT
boolean[] dp = new boolean[target + 1];
dp[0] = true;
```

---

## Problems

**Problem List**: [0/1 Knapsack — LeetCode Collection](https://leetcode.com/problem-list/50vif4uc/)

- [x] [0 - 1 Knapsack Problem](https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1) - Medium *(GeeksForGeeks)*
- [x] [House Robber II](https://leetcode.com/problems/house-robber-ii/) - Medium
- [x] [Target Sum](https://leetcode.com/problems/target-sum/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Ones and Zeroes](https://leetcode.com/problems/ones-and-zeroes/) - Medium
- [x] [Last Stone Weight II](https://leetcode.com/problems/last-stone-weight-ii/) - Medium

---

### Partition Equal Subset Sum ⭐ **IMPORTANT** ⭐

**Problem**: [Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/) - Medium

**Why Important**: Most direct application of boolean 0/1 knapsack — the template problem every other subset sum variant builds on

**Approach**:
1. If total sum is odd → impossible, return false
2. Target = `total / 2`
3. Boolean DP: can we pick a subset summing to `target`?

**Complexity**: O(n × sum) time, O(sum) space

**Solution**:

```java
public boolean canPartition(int[] nums) {
    int total = 0;
    for (int num : nums) total += num;
    if (total % 2 != 0) return false;

    int target = total / 2;
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;

    for (int num : nums) {
        for (int j = target; j >= num; j--) {
            dp[j] = dp[j] || dp[j - num];
        }
    }

    return dp[target];
}
```

**Key Points**:
- **Early exit**: odd total → no valid partition exists, return false immediately
- `dp[0] = true` — base case: empty subset sums to 0
- Reverse iteration ensures each number used at most once
- If `dp[target]` becomes `true` mid-loop, can break early as an optimization

---

### Target Sum ⭐ **IMPORTANT** ⭐

**Problem**: [Target Sum](https://leetcode.com/problems/target-sum/) - Medium

**Why Important**: Non-obvious reduction to subset count — the mathematical transformation `S+ = (total + target) / 2` is the key trick, frequently missed in interviews

**Approach**:
1. Each number gets `+` or `-`; let S+ = sum of positives, S- = sum of negatives
2. `S+ - S- = target` AND `S+ + S- = total` → **S+ = (total + target) / 2**
3. Count subsets that sum to S+

**Complexity**: O(n × total) time, O(total) space

**Solution**:

```java
public int findTargetSumWays(int[] nums, int target) {
    int total = 0;
    for (int num : nums) total += num;

    if (Math.abs(target) > total || (total + target) % 2 != 0) return 0;

    int s = (total + target) / 2;
    int[] dp = new int[s + 1];
    dp[0] = 1;

    for (int num : nums) {
        for (int j = s; j >= num; j--) {
            dp[j] += dp[j - num];
        }
    }

    return dp[s];
}
```

**Key Points**:
- **Math trick**: derive S+ = `(total + target) / 2` from two simultaneous equations
- **Validity check**: `(total + target) % 2 != 0` → no integer S+ exists, return 0
- **Validity check**: `Math.abs(target) > total` → target unreachable, return 0
- Reduces to the count-subsets template once S+ is computed

---

## Key Takeaways

1. **Reverse iteration** (`j` from W down to weight) is what makes it 0/1 — prevents reusing items
2. **Three DP flavors**: maximize value (`max`), boolean reachability (`||`), count ways (`+=`)
3. **Partition Equal Subset Sum**: boolean DP to target = `total / 2`
4. **Target Sum**: reduce to count subsets via `S+ = (total + target) / 2`
5. **House Robber II** (circular): split into two linear subproblems `[0, n-2]` and `[1, n-1]`
6. **Last Stone Weight II**: find subset sum closest to `total / 2`, minimize `total - 2 * best`
7. **Ones and Zeroes** (2D knapsack): `dp[i][j]` with two capacity constraints (zeros and ones)

---

> **[← Back to Dynamic Programming Overview](Notes.md)**
