# Prefix Sum

## Core Concept

**Prefix Sum** is a cumulative array where each element stores the sum of all elements up to that index.

**Key Insight**: Convert O(n) range sum queries to O(1) by precomputing cumulative sums.

```
arr     = [2, 4, 6, 8, 10]
prefix  = [0, 2, 6, 12, 20, 30]  // size n+1, dummy 0 at start
```

**Range Sum Formula**: `sum(i, j) = prefix[j+1] - prefix[i]`

---

## Pattern 1: Basic Prefix Sum (1D)

**Algorithm**:
1. Create prefix array of size `n+1` (dummy 0 at index 0)
2. For each `i`: `prefix[i+1] = prefix[i] + arr[i]`
3. Range sum `[i, j]` = `prefix[j+1] - prefix[i]`

**Complexity**: O(n) preprocessing, O(1) query, O(n) space

**Use Cases**: Range sum queries, subarray sum equals K, equilibrium index

---

## Pattern 2: Prefix Product

**⚠️ Key Pattern - Review Regularly**

**Algorithm**:
1. Create left product: `left[i]` = product of all elements left of `i`
2. Create right product: `right[i]` = product of all elements right of `i`
3. Result: `result[i] = left[i] * right[i]`

**Complexity**: O(n) time, O(n) space (optimizes to O(1) extra space)

**Space Optimization**: Use result array for left products, multiply with right products in reverse pass.

**Why important**: Non-obvious two-pass approach to avoid division. Classic interview problem.

**Trick**: Product except self = left products × right products

---

## Pattern 3: 2D Prefix Sum (Matrix) ⭐ **IMPORTANT** ⭐

**Algorithm**:
1. Build: `prefix[i][j] = matrix[i-1][j-1] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1]`
2. Query `(r1,c1)` to `(r2,c2)`: `sum = prefix[r2+1][c2+1] - prefix[r1][c2+1] - prefix[r2+1][c1] + prefix[r1][c1]`

**Complexity**: O(m×n) preprocessing, O(1) query, O(m×n) space

**Key**: Use inclusion-exclusion principle (add top + left, subtract overlap, add back element)

---

## Pattern 4: Split Array / Equilibrium Index

**Algorithm**:
1. Calculate total sum
2. Traverse with running `leftSum`
3. At each index: `rightSum = totalSum - leftSum - arr[i]`
4. Check condition (depends on problem)

**Complexity**: O(n) time, O(1) space

**Use Cases**: Find middle index (left = right sum), count valid splits, partition array

---

## Pattern 5: Maximum Product Subarray ⭐ **IMPORTANT** ⭐

**⚠️ Key Pattern - Review Regularly**

**Algorithm** (Kadane's variant):
1. Track `maxSoFar` = maximum product ending at current position
2. Track `minSoFar` = minimum product ending at current position
3. At each position: update both by considering:
   - Current element alone (start fresh)
   - Extend `maxSoFar * current`
   - Extend `minSoFar * current` (important for negatives!)
4. Update global result with `maxSoFar`

**Complexity**: O(n) time, O(1) space

**Why track minimum?** Negative numbers flip signs. A large negative × negative = large positive.

**Why important**: Non-obvious DP pattern. Unlike max sum (Kadane's), you must track both max AND min.

**Trick**: `maxSoFar = max(cur, max(maxSoFar*cur, minSoFar*cur))` and same for `minSoFar` with min.

**Key Insight**: At each step, choose:
- Start new subarray from current element
- Extend previous max product
- Extend previous min product (becomes max if current is negative)

---

## Pattern 6: Maximum Subarray Sum (Kadane's Algorithm)

**Algorithm**:
1. Track `curSum` = best sum of a subarray ending at the current position
2. At each position: `curSum = max(arr[i], curSum + arr[i])` — either extend or start fresh
3. Update global `maxSum` with `curSum` at every step

**Complexity**: O(n) time, O(1) space

**Why it's a prefix-sum relative**: `curSum` is really "running prefix sum, reset to 0 whenever it would go negative" — dropping a prefix as soon as it stops helping is the whole trick.

**Key Insight**: Unlike Maximum Product Subarray (Pattern 5), sums don't flip sign — so there's no need to track a running minimum, only a running maximum.

### Template

```java
public int maxSubArray(int[] nums) {
    int curSum = nums[0], maxSum = nums[0];

    for (int i = 1; i < nums.length; i++) {
        curSum = Math.max(nums[i], curSum + nums[i]);
        maxSum = Math.max(maxSum, curSum);
    }

    return maxSum;
}
```

---

## Pattern 7: Subarray Sum Equals K (Prefix Sum + HashMap) ⭐ **IMPORTANT** ⭐

**⚠️ Key Pattern - Review Regularly**

**Algorithm**:
1. Maintain a running `prefixSum` and a `HashMap<Integer, Integer>` of `prefixSum → count of times seen` — seed with `{0: 1}` (empty prefix)
2. At each index: `prefixSum += arr[i]`
3. A subarray ending here sums to `k` iff some earlier prefix equalled `prefixSum - k` → add `map.getOrDefault(prefixSum - k, 0)` to the answer
4. Increment `map[prefixSum]` **after** checking (don't let the current index count against itself)

**Complexity**: O(n) time, O(n) space

**Why important**: The seed `{0: 1}` is the single most-missed detail — it accounts for subarrays starting at index 0. The lookup key `prefixSum - k` (not `prefixSum + k`) is the second most-missed detail.

**Trick**: `sum(i, j) = prefix[j] - prefix[i] = k` rearranges to `prefix[i] = prefix[j] - k` — for each `j`, count how many earlier prefixes equal `prefix[j] - k`.

### Template

```java
public int subarraySum(int[] nums, int k) {
    // prefixSumFrequency[s] = how many prefixes so far have summed to s
    Map<Integer, Integer> prefixSumFrequency = new HashMap<>();
    prefixSumFrequency.put(0, 1);  // empty prefix — accounts for subarrays starting at index 0

    int prefixSum = 0;
    int subarrayCount = 0;

    for (int num : nums) {
        prefixSum += num;

        // A subarray ending here sums to k iff an earlier prefix equalled (prefixSum - k)
        int neededPrefixSum = prefixSum - k;
        subarrayCount += prefixSumFrequency.getOrDefault(neededPrefixSum, 0);

        // Record this prefix AFTER checking, so the current index never counts against itself
        prefixSumFrequency.merge(prefixSum, 1, Integer::sum);
    }

    return subarrayCount;
}
```

### Variant: Subarray Sums Divisible by K

**Same exact technique** — only the hashmap key changes from the raw prefix sum to its **remainder mod K**, since two prefixes are "equivalent" for divisibility purposes whenever they share a remainder.

**Algorithm**:
1. Same as above, but map key is `((prefixSum % k) + k) % k` — the `+ k) % k` normalizes negative remainders in Java
2. Seed with `{0: 1}` for the same reason (empty prefix has remainder 0)

```java
public int subarraysDivByK(int[] nums, int k) {
    Map<Integer, Integer> seen = new HashMap<>();
    seen.put(0, 1);

    int prefixSum = 0, count = 0;
    for (int num : nums) {
        prefixSum += num;
        int remainder = ((prefixSum % k) + k) % k;  // normalize negative remainders
        count += seen.getOrDefault(remainder, 0);
        seen.merge(remainder, 1, Integer::sum);
    }

    return count;
}
```

**Key Points**:
- **Negative remainders**: Java's `%` can return negative values for negative dividends — always normalize with `((x % k) + k) % k`
- Same seed, same lookup logic, same off-by-one pitfalls as the exact-sum version — only the map key changes

---

## Common Use Cases

| Pattern | Description | Complexity | Space |
|---------|-------------|------------|-------|
| **Range Sum** | Sum of subarray [i, j] | O(1) query | O(n) |
| **Prefix Product** | Product except self | O(n) | O(n) or O(1) |
| **2D Range Sum** | Sum of submatrix | O(1) query | O(m×n) |
| **Split Array** | Find equilibrium/pivot | O(n) | O(1) |
| **Max Product** | Maximum product subarray | O(n) | O(1) |
| **Max Sum (Kadane's)** | Maximum subarray sum | O(n) | O(1) |
| **Subarray Sum = K** | Count subarrays summing to K | O(n) | O(n) with HashMap |
| **Subarray Sum % K** | Count subarrays divisible by K | O(n) | O(n) with HashMap |

---

## Critical Points

**Size n+1 with dummy 0**: Always use `n+1` size array with `0` at index 0
- `prefix[i]` = sum of first `i` elements from arr
- Range `[i, j]` = `prefix[j+1] - prefix[i]`

**Common Mistake**:
- ❌ `prefix[i] = prefix[i-1] + arr[i]` (index error at i=0)
- ✅ `prefix[i+1] = prefix[i] + arr[i]` (fills prefix[1..n])

**When to Use**:
- Multiple range queries → Prefix sum
- Single query → Just iterate
- Frequent updates → Segment Tree/Fenwick Tree

---

## Quick Reference

**Prefix Sum**:
```
int[] prefix = new int[n + 1];
for (int i = 0; i < n; i++) {
    prefix[i + 1] = prefix[i] + arr[i];
}
// Query: sum(i, j) = prefix[j+1] - prefix[i]
```

**Split Array**:
```
int total = sum(arr), leftSum = 0;
for (int i = 0; i < n; i++) {
    int rightSum = total - leftSum - arr[i];
    // Check condition
    leftSum += arr[i];
}
```

**Maximum Product Subarray**:
```
int minSoFar = arr[0], maxSoFar = arr[0], result = arr[0];
for (int i = 1; i < n; i++) {
    int cur = arr[i];
    int tmpMax = maxSoFar;
    maxSoFar = Math.max(cur, Math.max(maxSoFar * cur, minSoFar * cur));
    minSoFar = Math.min(cur, Math.min(tmpMax * cur, minSoFar * cur));
    result = Math.max(result, maxSoFar);
}
```

---

## Problems to Practice

- [x] [Find the Middle Index in Array](https://leetcode.com/problems/find-the-middle-index-in-array/) - Easy
- [x] [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) - Medium
- [x] [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Number of Ways to Split Array](https://leetcode.com/problems/number-of-ways-to-split-array/) - Medium
- [x] [Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) - Medium
- [x] [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/) - Medium

---

**Remember**: Prefix sum converts O(n) range queries to O(1) by trading space for time. Always use size n+1 with dummy 0 at start to avoid off-by-one errors!
