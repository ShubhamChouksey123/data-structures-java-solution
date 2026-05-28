# Longest Increasing Subsequence (LIS)

> **[← Back to Dynamic Programming Overview](Notes.md)**

---

## When to Use

✅ **Find the longest subsequence where elements satisfy an ordering relation (increasing, divisible, chainable, etc.)**

### Keywords
- "longest increasing subsequence"
- "largest chain", "longest chain"
- "subsequence" (NOT subarray — order kept, but not contiguous)
- "divisible pairs", "follower / predecessor"
- "ascending order", "strictly increasing"

### Examples
- Longest strictly increasing subsequence in an array
- Largest subset where every pair is divisible
- Longest pair chain `(a, b)` where `b1 < a2`
- Longest word chain via single-character insertion
- Count of all LISes (not just length)

---

## Core Concept

For each index `i`, compute `dp[i]` = length of the best subsequence ending **exactly at index `i`**.

**Key Insight**: To extend the subsequence at `i`, look back at every `j < i`. If element `j` can come before `i` (e.g. `nums[j] < nums[i]`), then `dp[i] = max(dp[i], dp[j] + 1)`. The answer is `max(dp)` across all `i`.

**Complexity**: O(n²) time, O(n) space — improvable to O(n log n) with patience sorting

---

## Pattern: O(n²) Dynamic Programming

**Use Case**: Standard LIS — works for any "extendable" relation, not just `<`. Foundational pattern.

**Algorithm**:
1. Initialize `dp[i] = 1` for all `i` (each element alone is a length-1 subsequence)
2. For each `i` from `1` to `n-1`:
   - For each `j` from `0` to `i-1`:
     - If `nums[j] < nums[i]`, set `dp[i] = max(dp[i], dp[j] + 1)`
3. Answer is `max(dp)`

**Complexity**: O(n²) time, O(n) space

### Template

```java
public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int best = 1;

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        best = Math.max(best, dp[i]);
    }

    return best;
}
```

### Visual Example

```
nums = [10, 9, 2, 5, 3, 7, 101, 18]

i=0: dp[0]=1            [10]
i=1: dp[1]=1            [9]
i=2: dp[2]=1            [2]
i=3: dp[3]=2 (after 2)  [2,5]
i=4: dp[4]=2 (after 2)  [2,3]
i=5: dp[5]=3 (after 5)  [2,5,7] or [2,3,7]
i=6: dp[6]=4 (after 7)  [2,5,7,101]
i=7: dp[7]=4 (after 7)  [2,5,7,18]

dp = [1, 1, 1, 2, 2, 3, 4, 4]   →  answer: 4
```

---

## Pattern: O(n log n) Patience Sorting

**Use Case**: Same problem, faster — when only the **length** is required and `n` is large

**Algorithm**:
1. Maintain `tails[]` where `tails[k]` = smallest possible tail of any increasing subsequence of length `k + 1`
2. For each `num`:
   - Binary-search the smallest index `i` in `tails` with `tails[i] >= num`
   - If `i == tails.length`, append `num` (extends the LIS)
   - Else, replace `tails[i] = num` (better tail for that length)
3. Answer is `tails.length`

**Complexity**: O(n log n) time, O(n) space

### Template

```java
public int lengthOfLIS(int[] nums) {
    List<Integer> tails = new ArrayList<>();
    for (int num : nums) {
        int lo = 0, hi = tails.size();
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (tails.get(mid) < num) lo = mid + 1;
            else hi = mid;
        }
        if (lo == tails.size()) tails.add(num);
        else tails.set(lo, num);
    }
    return tails.size();
}
```

**⚠️ Note**: `tails[]` gives the correct **length**, but is **not** a valid LIS (elements may belong to different subsequences). Use the O(n²) DP if reconstruction is needed.

---

## Pattern: LIS with Reconstruction

**Use Case**: Need the actual subsequence, not just its length (e.g. Largest Divisible Subset)

**Algorithm**:
1. Sort the array (so `nums[j] < nums[i]` implies `j < i` — required for divisibility)
2. Compute `dp[i]` and a `prev[i]` array tracking the previous element index in the best chain
3. Track `bestIdx` = index where `dp[bestIdx]` is maximum
4. Walk back from `bestIdx` via `prev[]` to reconstruct the subsequence

**Complexity**: O(n²) time, O(n) space

### Template

```java
public List<Integer> largestSubset(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;
    int[] dp = new int[n], prev = new int[n];
    Arrays.fill(dp, 1);
    Arrays.fill(prev, -1);

    int bestIdx = 0;
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                dp[i] = dp[j] + 1;
                prev[i] = j;
            }
        }
        if (dp[i] > dp[bestIdx]) bestIdx = i;
    }

    LinkedList<Integer> result = new LinkedList<>();
    for (int k = bestIdx; k != -1; k = prev[k]) {
        result.addFirst(nums[k]);
    }
    return result;
}
```

---

## Common Mistakes

### ❌ Initializing `dp[i] = 0`

```java
// WRONG - each element by itself forms a length-1 subsequence
int[] dp = new int[n];  // all zeros
dp[i] = Math.max(dp[i], dp[j] + 1);  // never reaches correct base case

// CORRECT - start with 1 (the element itself)
Arrays.fill(dp, 1);
```

### ❌ Returning `dp[n - 1]` Instead of `max(dp)`

```java
// WRONG - LIS may end at any index, not necessarily the last
return dp[n - 1];

// CORRECT - the longest can end anywhere
int best = 0;
for (int v : dp) best = Math.max(best, v);
return best;
```

### ❌ Forgetting to Sort for Reconstruction Variants

```java
// WRONG - largestDivisibleSubset on unsorted input misses chains
int[] nums = {1, 2, 3};
// without sort, divisibility checks depend on order

// CORRECT - sort first so `nums[j] | nums[i]` implies `j < i`
Arrays.sort(nums);
```

---

## Problems

- [x] [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Largest Divisible Subset](https://leetcode.com/problems/largest-divisible-subset/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/) - Medium
- [x] [Number of Longest Increasing Subsequence](https://leetcode.com/problems/number-of-longest-increasing-subsequence/) - Medium
- [x] [Longest String Chain](https://leetcode.com/problems/longest-string-chain/) - Medium

---

### Longest Increasing Subsequence ⭐ **IMPORTANT** ⭐

**Problem**: [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence) - Medium

**Why Important**: Foundational pattern — every LIS variant (count, reconstruction, chain, divisible subset) builds on this. Knowing both the O(n²) DP and the O(n log n) patience-sorting approach is a frequent FAANG interview expectation.

**Approach (O(n²))**:
1. `dp[i]` = length of LIS ending at index `i`
2. For each `i`, scan all `j < i`; if `nums[j] < nums[i]`, update `dp[i] = max(dp[i], dp[j] + 1)`
3. Answer is `max(dp)`

**Complexity**: O(n²) time, O(n) space

**Solution**:

```java
public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    int best = 1;

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        best = Math.max(best, dp[i]);
    }

    return best;
}
```

**Key Points**:
- **Initialize `dp[i] = 1`** — every element alone is a length-1 subsequence
- **Answer is `max(dp)`**, not `dp[n-1]` — LIS can end at any index
- For O(n log n), use patience sorting (`tails[]` + binary search) — but `tails[]` is **not** a valid LIS, only its length
- Strictly increasing → `<`; non-decreasing → `<=`

---

### Largest Divisible Subset ⭐ **IMPORTANT** ⭐

**Problem**: [Largest Divisible Subset](https://leetcode.com/problems/largest-divisible-subset/) - Medium

**Why Important**: Generalizes LIS to any partial-order relation, AND requires **reconstructing** the actual subset (not just its length) — the `prev[]` parent-tracking pattern is reusable across many DP problems.

**Approach**:
1. **Sort** `nums` — so divisibility implies index ordering: if `nums[j] | nums[i]` and both are in the subset, then `j < i`
2. `dp[i]` = size of largest divisible chain ending at index `i`; `prev[i]` = parent index in that chain
3. For each pair `j < i`, if `nums[i] % nums[j] == 0` and extending improves `dp[i]`, update both
4. Track `bestIdx` (index where `dp` is largest)
5. Walk back from `bestIdx` through `prev[]` to rebuild the subset

**Complexity**: O(n²) time, O(n) space

**Solution**:

```java
public List<Integer> largestDivisibleSubset(int[] nums) {
    Arrays.sort(nums);
    int n = nums.length;
    int[] dp = new int[n], prev = new int[n];
    Arrays.fill(dp, 1);
    Arrays.fill(prev, -1);

    int bestIdx = 0;
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                dp[i] = dp[j] + 1;
                prev[i] = j;
            }
        }
        if (dp[i] > dp[bestIdx]) bestIdx = i;
    }

    LinkedList<Integer> result = new LinkedList<>();
    for (int k = bestIdx; k != -1; k = prev[k]) {
        result.addFirst(nums[k]);
    }
    return result;
}
```

**Key Points**:
- **Sort first** — divisibility is transitive only on a totally ordered chain; sorting linearizes the search
- **`prev[]` for reconstruction** — store the parent index, then walk backwards from `bestIdx`
- **Track `bestIdx` separately** — the largest subset can end at any index, not necessarily `n-1`
- Use `addFirst` (or reverse) so reconstruction yields the chain in increasing order

---

## Key Takeaways

1. **`dp[i]` = best subsequence ending at `i`** — the universal LIS frame
2. **Initialize `dp[i] = 1`**; answer is `max(dp)`, not `dp[n-1]`
3. **O(n²) DP** for length and reconstruction; **O(n log n) patience sorting** for length only
4. **Reconstruction**: track `prev[i]` and walk back from `bestIdx`
5. **Largest Divisible Subset**: sort first so divisibility aligns with index order
6. **Maximum Length of Pair Chain**: sort by second element + greedy is O(n log n) optimal — DP O(n²) also works
7. **Number of LIS**: maintain two arrays — `length[i]` and `count[i]`; on tie, accumulate counts
8. **Longest String Chain**: sort words by length, then DP keyed by string — try removing each char to find a predecessor

---

> **[← Back to Dynamic Programming Overview](Notes.md)**
