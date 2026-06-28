# Greedy

> **[← Back to Questions List](../../docs/questions-list.md)**

---

## When to Use

✅ **Make a locally optimal choice at each step, trusting that those local choices add up to a globally optimal answer**

### Keywords
- "minimum / maximum number of jumps / boats / steps"
- "earliest / latest / fewest", "as few as possible"
- "schedule", "pair up", "assign", "allocate"
- "stations", "intervals", "boarding / dropping"
- "always pick the smallest / largest available"

### Examples
- Min jumps to reach end of array (Jump Game II)
- Find a valid circular gas station starting point
- Pair people into the fewest boats under a weight cap
- Distribute candies satisfying neighbor constraints

---

## Core Concept

A greedy algorithm builds the answer by **committing** to a locally best choice at each step — no backtracking, no revisiting. Two things must be true for it to work:

1. **Greedy choice property**: an optimal solution can be built by making one locally optimal choice now and recursing on what remains
2. **Optimal substructure**: optimal answers to subproblems combine into an optimal answer for the whole

**Key Insight**: most greedy problems boil down to one of a few substrates — a single-pass scan with running state, sorting + two pointers, a two-pass scan that fixes neighbor constraints, or a sweep over event points. Recognize the substrate first; the local choice usually follows.

**Complexity**: typically O(n) for scans / sweeps, O(n log n) when sorting is required.

---

## Pattern: One-Pass Scan with Running State

**Use Case**: Walk the array once, maintain a small running state (furthest reach, tank balance, direction), commit decisions as you go.

**Examples**:

- **Jump Game II** — Treat positions reachable with `j` jumps as BFS layers. Track `currentEnd` (rightmost index reachable with current jumps) and `farthest` (rightmost reachable from any index in `[0..i]`). When `i == currentEnd`, increment jumps and set `currentEnd = farthest`. O(n) time, O(1) space.
- **Gas Station** — If `sum(gas) - sum(cost) < 0`, no solution. Otherwise, scan once tracking `tank`; whenever `tank < 0`, reset `start = i + 1` and `tank = 0`. The final `start` is the unique valid station. O(n) time, O(1) space.
- **Wiggle Subsequence** — Count direction flips. Walk left to right, comparing each pair; whenever the sign of `nums[i] - nums[i-1]` differs from the last sign, extend the subsequence by 1. O(n) time, O(1) space.

**Why it works**: in each case, an earlier index can't "wait" for a better future — committing the locally best choice (max reach / clean restart / direction flip) provably matches the optimum.

**Complexity**: O(n) time, O(1) space

---

## Pattern: Sort + Two Pointers

**Use Case**: Pair the smallest with the largest (or vice versa) under a constraint. Sorting reveals the canonical matching order.

**Examples**:

- **Boats to Save People** — Sort weights. Two pointers from both ends: if `lightest + heaviest <= limit`, both share a boat (move both pointers); else the heaviest goes alone (move only the high pointer). Each boat trip moves at least one pointer, so O(n) after the sort.
- **Bag of Tokens** — Sort tokens. Two pointers: spend the **cheapest** token face-up for score, sell the **most expensive** token face-down for power. Continue while you can do either; track max score seen.

**Why it works**: sorting collapses an `O(n²)` search-for-best-partner into `O(n)` — the optimal partner is always at the other end.

**Complexity**: O(n log n) time (sort dominates), O(1) extra space

---

## Pattern: Two-Pass Scan ⭐ **IMPORTANT** ⭐

**Use Case**: A constraint depends on **both** neighbors (left AND right). A single pass only sees one side, so do two passes and take the max.

**Why Important**: Single-direction greedy is the natural first instinct and quietly wrong — left-to-right satisfies the left constraint but breaks the right, and vice versa. The fix (run both directions, take the max per index) generalizes to any "must beat both neighbors" problem, including some interval and ratings tasks.

**Algorithm (Candy)**:
1. Initialize `candies[i] = 1` for all i (everyone gets one)
2. **Left → Right**: if `ratings[i] > ratings[i-1]`, set `candies[i] = candies[i-1] + 1` — satisfies the left-neighbor constraint
3. **Right → Left**: if `ratings[i] > ratings[i+1]`, set `candies[i] = max(candies[i], candies[i+1] + 1)` — `max` preserves the left pass's gain while satisfying the right constraint
4. Return `sum(candies)`

**Complexity**: O(n) time, O(n) space

### Solution (Candy)

```java
public int candy(int[] ratings) {
    int n = ratings.length;
    int[] candies = new int[n];
    Arrays.fill(candies, 1);

    // Left → Right: respect left neighbor
    for (int i = 1; i < n; i++) {
        if (ratings[i] > ratings[i - 1]) {
            candies[i] = candies[i - 1] + 1;
        }
    }

    // Right → Left: respect right neighbor (without breaking left pass)
    for (int i = n - 2; i >= 0; i--) {
        if (ratings[i] > ratings[i + 1]) {
            candies[i] = Math.max(candies[i], candies[i + 1] + 1);
        }
    }

    int total = 0;
    for (int c : candies) total += c;
    return total;
}
```

**Key Points**:
- **Take `max` in the second pass**, not blind assignment — overwriting would discard valid gains from the first pass
- **Strict `>`, not `>=`** — equal ratings don't trigger the rule (a child can get the same as a tied neighbor)
- **Why two passes are sufficient**: each constraint is a one-sided inequality; one pass per direction covers both

---

## Pattern: Sweep Line / Difference Array

**Use Case**: Range updates over an axis (time, location) with a per-point capacity check. Convert each interval to two events (`+v` at start, `-v` at end) and prefix-sum across the axis.

**Example — Car Pooling**:
1. For each trip `(passengers, from, to)`: `diff[from] += passengers`, `diff[to] -= passengers`
2. Prefix sum across the axis; if any running total exceeds `capacity`, return `false`
3. Otherwise `true`

**Why it works**: a difference array turns each O(range) update into O(1), then one O(n) sweep checks the constraint everywhere.

**Complexity**: O(n + maxLoc) time, O(maxLoc) space

---

## Common Mistakes

- ❌ **Assuming greedy works without proof** — verify the greedy choice property; for many "minimize" problems a naive greedy fails and DP is needed (e.g. Coin Change with arbitrary denominations)
- ❌ **Single-pass when constraints are bidirectional** — Candy and similar problems need two passes with `max`
- ❌ **Forgetting the feasibility check** in Gas Station — if `total gas < total cost`, no answer exists; the restart trick only finds the unique start if one exists
- ❌ **Counting jumps from index** in Jump Game II — increment jumps when `i` hits `currentEnd`, not on every `farthest` update
- ❌ **Off-by-one on sweep-line end events** — typically `to` is the drop-off index; subtract there (not at `to + 1`)

---

## Problems

- [x] [Jump Game II](https://leetcode.com/problems/jump-game-ii/) - Medium
- [x] [Gas Station](https://leetcode.com/problems/gas-station/) - Medium
- [x] [Bag of Tokens](https://leetcode.com/problems/bag-of-tokens/) - Medium
- [x] [Boats to Save People](https://leetcode.com/problems/boats-to-save-people/) - Medium
- [x] [Wiggle Subsequence](https://leetcode.com/problems/wiggle-subsequence/) - Medium
- [x] [Car Pooling](https://leetcode.com/problems/car-pooling/) - Medium
- [x] [Candy](https://leetcode.com/problems/candy/) - Hard ⭐ **IMPORTANT** ⭐

---

## Key Takeaways

1. **Greedy needs proof** — locally optimal is only globally optimal when the greedy choice property holds; when in doubt, fall back to DP
2. **Four common substrates**: one-pass scan, sort + two pointers, two-pass scan, sweep line — recognize the substrate before reasoning about the local choice
3. **Two-pass scans handle bidirectional constraints** — use `max` in the second pass so it doesn't overwrite valid first-pass gains
4. **Sorting often unlocks two-pointer greedy** — pair extremes from both ends after sort
5. **Sweep line / difference array** turns range updates into O(1) per event + one O(n) prefix sum

---

> **[← Back to Questions List](../../docs/questions-list.md)**
