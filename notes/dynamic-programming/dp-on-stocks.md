# DP on Stocks

> **[← Back to Dynamic Programming Overview](Notes.md)**

---

## When to Use

✅ **Maximize profit from buying/selling a stock over a price series, possibly with constraints (transaction limit, cooldown, fee)**

### Keywords
- "best time to buy and sell stock"
- "max profit", "buy low sell high"
- "at most k transactions", "two transactions"
- "cooldown", "transaction fee"
- "unlimited transactions"

### Examples
- Unlimited buy/sell (Stock II)
- At most `k` transactions (Stock III with k=2, Stock IV with arbitrary k)
- One-day cooldown after each sell
- Pay a fixed fee per completed transaction

---

## Core Concept

Model the trader as a **state machine**: on each day you are either **holding** a share or **not holding** (cash). Transitions:

- `hold → hold` (do nothing) or `hold → cash` (sell, add price)
- `cash → cash` (do nothing) or `cash → hold` (buy, subtract price)

**Key Insight**: `dp[i][holding]` = max profit at end of day `i` in that state. Walk the days left-to-right; the answer is `dp[n-1][cash]` (always exit holding nothing — no point ending the series owning a stock you won't sell).

**Complexity**: O(n) time, O(1) space (rolling two scalars) for unconstrained variants; O(n × k) for `k`-transaction limit.

---

## Pattern: Unlimited Transactions (Stock II)

**Use Case**: Buy/sell as many times as you want, but only one share at a time.

**Algorithm**: Two rolling scalars — `hold` and `cash`. Each day:
- `cash = max(cash, hold + price)` — keep cash, or sell today
- `hold = max(hold, cash - price)` — keep holding, or buy today

Use the **previous** `cash` when updating `hold` (or vice versa). Order matters in the simple version, but `max(cash, hold + price)` then `max(hold, cash - price)` works because using the just-updated `cash` to buy back is equivalent to "doing nothing" — same-day buy after sell cancels out.

**Complexity**: O(n) time, O(1) space

### Template

```java
public int maxProfit(int[] prices) {
    int hold = -prices[0], cash = 0;
    for (int i = 1; i < prices.length; i++) {
        cash = Math.max(cash, hold + prices[i]);
        hold = Math.max(hold, cash - prices[i]);
    }
    return cash;
}
```

**Greedy equivalent**: sum every positive `prices[i] - prices[i-1]` — same answer, since unlimited transactions let you capture every uphill segment.

---

## Pattern: K Transactions ⭐ **IMPORTANT** ⭐

**Use Case**: At most `k` complete buy-sell transactions (Stock III is `k=2`; Stock IV is general `k`).

**Why Important**: The 3D state `dp[i][j][holding]` is the canonical state-machine DP — the same template handles `k=1, 2, ..., ∞`, with cooldown, with fees. Get this once and the rest are 5-line tweaks.

**Algorithm**:
1. State: `dp[j][0]` = max cash after at most `j` transactions, not holding; `dp[j][1]` = same, holding
2. Count a transaction on **buy** (or alternatively on sell — pick one and stick with it)
3. Transition for each day's price `p`:
   - `dp[j][1] = max(dp[j][1], dp[j-1][0] - p)` — stay holding, or buy (consumes a transaction slot)
   - `dp[j][0] = max(dp[j][0], dp[j][1] + p)` — stay in cash, or sell
4. **Optimization**: if `k >= n/2`, fall back to unlimited transactions (no cap can bind), avoiding huge memory
5. Answer: `dp[k][0]`

**Complexity**: O(n × k) time, O(k) space

### Template

```java
public int maxProfit(int k, int[] prices) {
    int n = prices.length;
    if (n == 0 || k == 0) return 0;
    if (k >= n / 2) return unlimited(prices);     // unlimited fallback

    int[] hold = new int[k + 1];
    int[] cash = new int[k + 1];
    Arrays.fill(hold, Integer.MIN_VALUE);

    for (int p : prices) {
        for (int j = 1; j <= k; j++) {
            hold[j] = Math.max(hold[j], cash[j - 1] - p);  // buy: consumes slot j
            cash[j] = Math.max(cash[j], hold[j] + p);      // sell: stays at slot j
        }
    }
    return cash[k];
}

private int unlimited(int[] prices) {
    int profit = 0;
    for (int i = 1; i < prices.length; i++) {
        if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
    }
    return profit;
}
```

**Key Points**:
- **Decide where to count transactions** (buy or sell): either works; mixing them double-counts. The template counts on **buy** — `cash[j - 1]` reads the cash from one fewer transaction
- **Iterate `j` outer or inner?** Either works because we only read `cash[j-1]` (already-finalized lower-`j` value) and `hold[j]` (own row)
- **`k >= n/2` fallback**: each transaction needs at least 2 days; with more slots than days, the cap is meaningless — switch to O(n) greedy to avoid allocating a giant `dp` table
- **Init `hold[j] = MIN_VALUE`** so "haven't bought yet" never wins a `max`

---

## Pattern: With Cooldown

**Use Case**: After a sell, you must wait one day before buying again.

**Algorithm**: Add a third state `cooldown` (just sold), or equivalently use the cash value from **two days ago** when buying.

```java
public int maxProfit(int[] prices) {
    int hold = -prices[0], cash = 0, prevCash = 0;
    for (int i = 1; i < prices.length; i++) {
        int prevHold = hold;
        hold = Math.max(hold, prevCash - prices[i]);   // buy uses cash from i-2
        prevCash = cash;
        cash = Math.max(cash, prevHold + prices[i]);
    }
    return cash;
}
```

**Complexity**: O(n) time, O(1) space

**Key Point**: `prevCash` lags by one day, encoding the cooldown gap. Without it you'd buy back on the same day or the day after sell.

---

## Pattern: With Transaction Fee

**Use Case**: Pay a fixed fee `f` on every completed buy-sell transaction.

**Algorithm**: Same as unlimited transactions, but subtract `f` on sell (or on buy — pick one):

```java
public int maxProfit(int[] prices, int fee) {
    int hold = -prices[0], cash = 0;
    for (int i = 1; i < prices.length; i++) {
        cash = Math.max(cash, hold + prices[i] - fee);   // pay fee on sell
        hold = Math.max(hold, cash - prices[i]);
    }
    return cash;
}
```

**Complexity**: O(n) time, O(1) space

**Key Point**: Fee discourages tiny profits — only sells where `prices[i] - prices[buy] > fee` end up booked.

---

## Common Mistakes

- ❌ **Counting a transaction on both buy and sell** — doubles the count, halves your effective `k`
- ❌ **Forgetting to init `hold = -prices[0]`** (or `MIN_VALUE`) — "haven't bought" must lose to any real buy
- ❌ **Using the just-updated `cash` to update `hold` in cooldown** — must be `prevCash` (lagged by one day)
- ❌ **Allocating `dp[n][k+1][2]` for huge `k`** — when `k >= n/2`, fall back to unlimited

---

## Problems

- [x] [Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/) - Medium
- [x] [Best Time to Buy and Sell Stock III](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/) - Hard
- [x] [Best Time to Buy and Sell Stock IV](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/) - Hard ⭐ **IMPORTANT** ⭐
- [x] [Best Time to Buy and Sell Stock with Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) - Medium
- [x] [Best Time to Buy and Sell Stock with Transaction Fee](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/) - Medium

---

## Key Takeaways

1. **State machine**: each day has a `hold`/`cash` state; transitions pair "do nothing" with "buy" or "sell"
2. **Unlimited variant** is two rolling scalars — O(n) time, O(1) space
3. **K-transaction variant** adds a `j` dimension; count the transaction on **one** side (buy or sell), never both
4. **Cooldown** = lag the cash value by one day before buying
5. **Fee** = subtract on sell (or on buy); same recurrence shape
6. **`k >= n/2` shortcut**: cap can't bind, fall through to unlimited

---

> **[← Back to Dynamic Programming Overview](Notes.md)**
