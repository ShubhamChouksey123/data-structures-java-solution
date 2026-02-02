# Variable Size Sliding Window

> **[← Back to Overview](Notes.md)**

## When to Use

✅ **Need to find optimal window size (longest/shortest)**

### Keywords
- **"longest"** substring/subarray
- **"shortest"** substring/subarray
- **"minimum"** length
- **"maximum"** length
- "at most K"
- "without repeating"

### Examples
- Longest substring without repeating characters
- Shortest subarray with sum ≥ target
- At most K distinct elements
- Count subarrays with product < K

---

## Four Sub-Patterns

| Pattern | Shrink When | Update When | Use Case |
|---------|-------------|-------------|----------|
| **Longest** | Invalid | After shrink | Max length window |
| **Shortest** | Valid | While shrinking | Min length window |
| **HashMap** | Condition broken | After shrink | Track frequencies |
| **Count** | Invalid | After shrink | Count valid subarrays |

---

## 1. Longest Window

**Shrink when condition is INVALID**

```java
public int longestWindow(int[] arr) {
    int start = 0, maxLen = 0;
    Set<Integer> window = new HashSet<>();

    for (int end = 0; end < arr.length; end++) {
        // Shrink until valid
        while (window.contains(arr[end])) {
            window.remove(arr[start++]);
        }

        window.add(arr[end]);
        maxLen = Math.max(maxLen, end - start + 1);
    }
    return maxLen;
}
```

**Example**: Longest Substring Without Repeating Characters

```
"abcabcbb"
[a]         → valid, len=1
[ab]        → valid, len=2
[abc]       → valid, len=3 ✓
[abca]      → invalid! shrink → [bca], len=3
[bcab]      → invalid! shrink → [cab], len=3
```

---

## 2. Shortest Window

**Shrink when condition is VALID**

```java
public int shortestWindow(int[] arr, int target) {
    int start = 0, minLen = Integer.MAX_VALUE, sum = 0;

    for (int end = 0; end < arr.length; end++) {
        sum += arr[end];

        // Shrink while valid
        while (sum >= target) {
            minLen = Math.min(minLen, end - start + 1);
            sum -= arr[start++];
        }
    }
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
}
```

**Example**: Minimum Subarray Sum ≥ 7

```
arr = [2, 3, 1, 2, 4, 3], target = 7

[2,3,1,2]   → sum=8 ✓ minLen=4, shrink
  [3,1,2]   → sum=6, not enough
  [3,1,2,4] → sum=10 ✓ minLen=4, shrink
    [1,2,4] → sum=7 ✓ minLen=3 ✓, shrink
      [2,4] → sum=6, not enough
    [2,4,3] → sum=9 ✓ minLen=3, shrink
      [4,3] → sum=7 ✓ minLen=2 ✓
```

---

## 3. HashMap Pattern

**Track frequencies, shrink when condition broken**

```java
public int atMostKDistinct(String s, int k) {
    Map<Character, Integer> freq = new HashMap<>();
    int start = 0, maxLen = 0;

    for (int end = 0; end < s.length(); end++) {
        char right = s.charAt(end);
        freq.put(right, freq.getOrDefault(right, 0) + 1);

        // Shrink if too many distinct
        while (freq.size() > k) {
            char left = s.charAt(start);
            freq.put(left, freq.get(left) - 1);
            if (freq.get(left) == 0) freq.remove(left);
            start++;
        }

        maxLen = Math.max(maxLen, end - start + 1);
    }
    return maxLen;
}
```

**Example**: Fruit Into Baskets (At Most 2 Types)

```
fruits = [1, 2, 3, 2, 2]

[1,2]     → 2 types ✓ len=2
[1,2,3]   → 3 types! shrink
  [2,3]   → 2 types ✓ len=2
  [2,3,2] → 2 types ✓ len=3 ✓
```

---

## 4. Count Subarrays

**Count all valid subarrays ending at current position**

```java
public int countSubarrays(int[] nums, int k) {
    int start = 0, product = 1, count = 0;

    for (int end = 0; end < nums.length; end++) {
        product *= nums[end];

        // Shrink if product too large
        while (product >= k) {
            product /= nums[start++];
        }

        // Count all subarrays ending at end
        count += end - start + 1;
    }
    return count;
}
```

**Why `end - start + 1`?** Window `[i, i+1, i+2]` has 3 valid subarrays ending at `i+2`:
- `[i, i+1, i+2]`
- `[i+1, i+2]`
- `[i+2]`

---

## Complete Examples

### Example 1: Longest Substring Without Repeating

```java
public int lengthOfLongestSubstring(String s) {
    Set<Character> window = new HashSet<>();
    int start = 0, maxLen = 0;

    for (int end = 0; end < s.length(); end++) {
        while (window.contains(s.charAt(end))) {
            window.remove(s.charAt(start++));
        }
        window.add(s.charAt(end));
        maxLen = Math.max(maxLen, end - start + 1);
    }
    return maxLen;
}
```

### Example 2: Minimum Subarray Sum

```java
public int minSubArrayLen(int target, int[] nums) {
    int start = 0, sum = 0, minLen = Integer.MAX_VALUE;

    for (int end = 0; end < nums.length; end++) {
        sum += nums[end];

        while (sum >= target) {
            minLen = Math.min(minLen, end - start + 1);
            sum -= nums[start++];
        }
    }
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
}
```

### Example 3: Subarray Product Less Than K

```java
public int numSubarrayProductLessThanK(int[] nums, int k) {
    if (k <= 1) return 0;

    int start = 0, product = 1, count = 0;

    for (int end = 0; end < nums.length; end++) {
        product *= nums[end];

        while (product >= k) {
            product /= nums[start++];
        }

        count += end - start + 1;
    }
    return count;
}
```

---

## Common Mistakes

### ❌ Using `if` Instead of `while`
```java
// WRONG - only shrinks once
if (window.size() > k) {
    window.remove(arr[start++]);
}

// CORRECT - shrinks until valid
while (window.size() > k) {
    window.remove(arr[start++]);
}
```

### ❌ Updating Before Valid
```java
// WRONG - window might still be invalid
for (int end = 0; end < arr.length; end++) {
    window.add(arr[end]);
    maxLen = Math.max(maxLen, end - start + 1);  // TOO EARLY
    while (invalid()) shrink();
}

// CORRECT - update after ensuring valid
for (int end = 0; end < arr.length; end++) {
    window.add(arr[end]);
    while (invalid()) shrink();
    maxLen = Math.max(maxLen, end - start + 1);  // AFTER shrinking
}
```

### ❌ Forgetting to Remove from Map
```java
// WRONG - map grows unbounded
freq.put(left, freq.get(left) - 1);

// CORRECT - remove when count is 0
freq.put(left, freq.get(left) - 1);
if (freq.get(left) == 0) freq.remove(left);
```

---

## Pattern Selection Guide

| Problem Statement | Pattern | Shrink When |
|-------------------|---------|-------------|
| "Longest substring without..." | Longest | Invalid (has duplicate) |
| "Longest with at most K..." | HashMap | Map size > K |
| "Shortest subarray with sum ≥..." | Shortest | Valid (sum ≥ target) |
| "Count subarrays with product <..." | Count | Invalid (product ≥ K) |
| "Minimum window containing..." | Shortest | Valid (has all chars) |

---

## Problems

- [x] [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) - Medium
- [x] [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) - Medium
- [x] [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/) - Medium ⭐
- [x] [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) - Medium
- [x] [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) - Medium ⭐
- [x] [Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays) - Medium ⭐
- [ ] [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) - Hard

### Subarray Product Less Than K ⭐ **IMPORTANT** ⭐

**Problem**: [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/) - Medium

**Why Important**: Classic "count subarrays" pattern using variable window, tricky counting logic that's easy to get wrong, frequently asked

**Approach**:
1. Use variable-size window with product tracking
2. Expand window by multiplying new element
3. Shrink window while product >= k
4. Count all valid subarrays ending at current position
5. Key insight: if window [start...end] is valid, there are `end - start + 1` subarrays ending at end

**Complexity**: O(n) time, O(1) space

**Solution**:

```java
public int numSubarrayProductLessThanK(int[] nums, int k) {
    // Edge case: k <= 1 means no valid subarrays
    if (k <= 1) return 0;

    int start = 0;
    int product = 1;
    int count = 0;

    for (int end = 0; end < nums.length; end++) {
        // Expand: multiply new element
        product *= nums[end];

        // Shrink: while product >= k, divide and move start
        while (product >= k) {
            product /= nums[start];
            start++;
        }

        // Count all subarrays ending at end
        // Window [start...end] has (end - start + 1) valid subarrays
        // [start,end], [start+1,end], ..., [end]
        count += end - start + 1;
    }

    return count;
}
```

**Key Points**:
- **Edge case**: If k <= 1, no positive product can be less than k, return 0
- **Counting trick**: Window size = number of subarrays ending at current position
- **Why?** Window `[2, 3, 4]` has 3 subarrays ending at 4: `[2,3,4]`, `[3,4]`, `[4]`
- **Each element counted multiple times** - once for each window it's the rightmost element of
- Common mistake: trying to count all subarrays at once instead of counting incrementally

---

### Fruit Into Baskets ⭐ **IMPORTANT** ⭐

**Problem**: [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) - Medium

**Why Important**: Disguised "at most K distinct" problem (K=2), tests ability to recognize pattern variations, common interview question

**Approach**:
1. Problem is actually: longest subarray with at most 2 distinct elements
2. Use HashMap to track fruit types and their counts
3. Expand window by adding new fruit
4. Shrink window while more than 2 distinct types
5. Track maximum window size

**Complexity**: O(n) time, O(1) space (at most 3 entries in map)

**Solution**:

```java
public int totalFruit(int[] fruits) {
    Map<Integer, Integer> basket = new HashMap<>();
    int start = 0;
    int maxFruits = 0;

    for (int end = 0; end < fruits.length; end++) {
        // Expand: add current fruit to basket
        int fruit = fruits[end];
        basket.put(fruit, basket.getOrDefault(fruit, 0) + 1);

        // Shrink: while more than 2 types, remove from left
        while (basket.size() > 2) {
            int leftFruit = fruits[start];
            basket.put(leftFruit, basket.get(leftFruit) - 1);

            // Remove fruit type completely when count reaches 0
            if (basket.get(leftFruit) == 0) {
                basket.remove(leftFruit);
            }

            start++;
        }

        // Update maximum fruits collected
        maxFruits = Math.max(maxFruits, end - start + 1);
    }

    return maxFruits;
}
```

**Key Points**:
- **Pattern recognition**: "2 baskets" = "at most 2 distinct elements" = "longest substring with K distinct"
- **Must remove from map**: Don't let map grow unbounded, remove when count = 0
- **Window size = fruits collected**: All fruits in current window go into baskets
- **HashMap size never exceeds 3**: At most 3 types briefly (2 valid + 1 being removed)
- Common mistake: Using HashSet instead of HashMap (need counts to properly remove)
- Variation: "At most K baskets" → change condition to `basket.size() > k`

---

## Key Takeaways

1. Window size is **dynamic** (changes)
2. **Right pointer** expands (outer loop)
3. **Left pointer** shrinks (inner `while`)
4. Both pointers **only move forward**
5. **"Longest"** → shrink when **invalid**
6. **"Shortest"** → shrink when **valid**
7. **Count** → add `end - start + 1`
8. Use `while` not `if` for shrinking

> **[← Fixed Size](fixed-size.md)** | **[Back to Overview](Notes.md)**
