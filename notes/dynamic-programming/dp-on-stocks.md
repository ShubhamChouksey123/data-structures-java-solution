# DP on Stocks

> **[← Back to Dynamic Programming Overview](Notes.md)**

---

## When to Use

✅ **Maximize profit from buying/selling a stock over a price series, possibly with constraints (transaction limit, cooldown, fee)**

### Keywords
- "best time to buy and sell stock", "max profit"
- "at most k transactions", "two transactions"
- "cooldown", "transaction fee", "unlimited transactions"

### Examples
- Unlimited buy/sell (Stock II)
- At most `k` transactions (Stock III: k=2, Stock IV: arbitrary k)
- One-day cooldown after each sell
- Fixed fee per completed transaction

---

## Core Concept

Model each day as a recursive decision over the state `(index, buy, k)`:

- `index` = current day
- `buy` = `1` if next legal action is **buy** (NOT holding); `0` if next legal action is **sell** (HOLDING)
- `k` = transactions remaining (a transaction = one full buy + sell pair)

Two branches at every state:

- **Skip**: advance to `index + 1`, keep `(buy, k)` unchanged
- **Act**: if `buy == 1`, buy (`-prices[i]`, flip `buy → 0`); if `buy == 0`, sell (`+prices[i]`, flip `buy → 1`, **decrement `k`**)

**Key Insight**: top-down memoization with table `memo[index][buy][k]` is the universal template — every variant is a 1-2 line tweak.

**Complexity**: O(n × k) time, O(n × k) space for K-transaction variants; O(n) when no cap.

---

## Pattern: Unlimited Transactions (Stock II)

**Use Case**: Buy/sell as many times as you want, one share at a time.

**Algorithm**: Recurse on `(index, buy)` only — drop the `k` dimension from the Core Concept state since no cap can bind.

```java
class Solution {

    public int maxProfit(int[] prices, int n, int[][] memoCache, int index, int buy) {

        if (index == n) {
            return 0;
        }

        if (memoCache[index][buy] != -1)
            return memoCache[index][buy];

        int optimalValue = 0;
        if (buy == 1) {
            int skipDay = maxProfit(prices, n, memoCache, index + 1, 1);
            int buyDay = -prices[index] + maxProfit(prices, n, memoCache, index + 1, 0);
            optimalValue = Math.max(skipDay, buyDay);
        } else {
            int skipDay = maxProfit(prices, n, memoCache, index + 1, 0);
            int sellDay = prices[index] + maxProfit(prices, n, memoCache, index + 1, 1);
            optimalValue = Math.max(skipDay, sellDay);
        }
        memoCache[index][buy] = optimalValue;
        return optimalValue;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] memoCache = new int[n][2];
        for (int[] row : memoCache) Arrays.fill(row, -1);
        return maxProfit(prices, n, memoCache, 0, 1);
    }
}
```

**Complexity**: O(n) time, O(n) space.
**Greedy equivalent**: sum every positive `prices[i] - prices[i-1]` — unlimited transactions capture every uphill segment.

---

## Pattern: K Transactions ⭐ **IMPORTANT** ⭐

**Use Case**: At most `k` complete buy-sell transactions (Stock III is `k = 2`; Stock IV is general `k`).

**Why Important**: `(index, buy, k)` is the universal stock template — once written, every variant (cooldown, fee, unlimited) is a 1-2 line edit. Two recurring gotchas: (a) init memo to `-1` since `0` is a valid answer, (b) decrement `k` on exactly one side of the transaction.

**Algorithm**: State `(index, buy, k)`. Base: `index == n` or `k == 0` → return `0`. Otherwise compute `max(skip, act)` with memoization. Decrement `k` only on **sell**.

**Complexity**: O(n × k) time, O(n × k) space

### Template (Stock III, k = 2)

```java
class Solution {

    private int maxProfit(int[] prices, int[][][] memoCache, int index, int buy, int k) {

        if (index == prices.length || k == 0) {
            return 0;
        }

        if (memoCache[index][buy][k] != -1) {
            return memoCache[index][buy][k];
        }

        int optimalValue = 0;
        if (buy == 1) {
            int a = maxProfit(prices, memoCache, index + 1, 1, k);
            int b = -prices[index] + maxProfit(prices, memoCache, index + 1, 0, k);

            optimalValue = Math.max(a, b);
        } else {
            int a = maxProfit(prices, memoCache, index + 1, 0, k);
            int b = prices[index] + maxProfit(prices, memoCache, index + 1, 1, k - 1);

            optimalValue = Math.max(a, b);
        }

        memoCache[index][buy][k] = optimalValue;
        return optimalValue;
    }

    public int maxProfit(int[] prices) {

        int n = prices.length;
        int[][][] memoCache = new int[n][2][3];
        for (int[][] slice : memoCache) {
            for (int[] row : slice) {
                Arrays.fill(row, -1);
            }
        }

        return maxProfit(prices, memoCache, 0, 1, 2);
    }
}
```

For **Stock IV (general k)**: swap constant `2` for parameter `k` in `new int[n][2][k + 1]` and the initial call `maxProfit(..., 0, 1, k)`.

### Bottom-Up Tabulation (equivalent)

Same recurrence written iteratively. `dp[i][buy][cap]` mirrors `solve(i, buy, cap)`; fill from `i = n - 1` down (base case `dp[n][*][*] = 0` is free from array init):

```java
class Solution {
    public int maxProfit(int k, int[] prices) {

        int n = prices.length;
        int[][][] dp = new int[n + 1][2][k + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int cap = 1; cap <= k; cap++) {

                int skipBuy = dp[i + 1][1][cap];
                int buyDay = -prices[i] + dp[i + 1][0][cap];
                dp[i][1][cap] = Math.max(skipBuy, buyDay);

                int skipSell = dp[i + 1][0][cap];
                int sellDay = prices[i] + dp[i + 1][1][cap - 1];
                dp[i][0][cap] = Math.max(skipSell, sellDay);
            }
        }

        return dp[0][1][k];
    }
}
```

**Top-down vs bottom-up**: top-down visits only reachable states (fewer calls but recursion overhead); bottom-up fills every cell with predictable O(n × k) work and no stack risk. Iterate `i` backwards so `dp[i + 1]` is filled when read.

---

## Pattern: With Cooldown

**Use Case**: After a sell, wait one full day before buying again.

**Algorithm**: Same as Unlimited — on **sell**, jump `index + 2` (skip the cooldown day). Use `index >= n` in the base case (`index + 2` can overshoot).

```text
// only the sell branch changes:
sellDay = prices[index] + maxProfit(..., index + 2, 1);   // jump 2 days
```

**Complexity**: O(n) time, O(n) space

---

## Pattern: With Transaction Fee

**Use Case**: Pay a fixed `fee` per completed buy-sell.

**Algorithm**: Same as Unlimited — subtract `fee` on the sell branch (or on buy — pick one and stick with it).

```text
// only the sell branch changes:
sellDay = prices[index] - fee + maxProfit(..., index + 1, 1, fee);
```

**Complexity**: O(n) time, O(n) space
**Key Point**: Fee suppresses tiny trades — only sells with `prices[sell] - prices[buy] > fee` get booked.

---

## Common Mistakes

- ❌ **Init memo to `0`** — `0` is a valid answer; use `-1` as the "not computed" sentinel
- ❌ **Decrementing `k` on both buy and sell** — halves the effective cap; pick one side
- ❌ **Flipping the `buy` flag** — see Core Concept for the canonical convention; reversing it inverts every branch
- ❌ **Cooldown base case `==` instead of `>=`** — `index + 2` can overshoot by one

---

## Problems

- [x] [Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/) - Medium
- [x] [Best Time to Buy and Sell Stock III](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/) - Hard
- [x] [Best Time to Buy and Sell Stock IV](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/) - Hard ⭐ **IMPORTANT** ⭐
- [x] [Best Time to Buy and Sell Stock with Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) - Medium
- [x] [Best Time to Buy and Sell Stock with Transaction Fee](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) - Medium

---

## Key Takeaways

1. **One state, four variants** — `(index, buy, k)` covers K-transactions; drop `k` for Unlimited; jump `index + 2` for Cooldown; subtract `fee` for Transaction Fee
2. **Two gotchas to remember**: init memo to `-1` (since `0` is a valid answer), and decrement `k` on exactly one side (sell)
3. **Top-down ⇄ bottom-up are equivalent** — `dp[i][buy][k]` mirrors `solve(i, buy, k)`; pick top-down for clarity, bottom-up for predictable work / no stack
4. **Memo dimensions follow the state**: `[n][2][k+1]` for K-transactions, `[n][2]` when no cap

---

> **[← Back to Dynamic Programming Overview](Notes.md)**
