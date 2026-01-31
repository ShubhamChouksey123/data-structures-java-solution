# Sliding Window Pattern

## Core Concept

A sliding window is a **subarray/substring** that moves over data to solve problems efficiently in **linear time**.

**Key Insight**: Instead of recalculating from scratch for each position, maintain a window and update incrementally by adding new elements and removing old ones.

---

## Pattern 1: Fixed Size Window

**Algorithm**:
1. Initialize window of size K
2. Calculate result for first window
3. Slide window: remove leftmost, add rightmost
4. Update result at each step

**Complexity**: O(n) time, O(1) space

### Visual Example

```
Array: [1, 3, 2, 6, -1, 4, 1, 8, 2]  K = 3

Window 1:  [1, 3, 2] 6, -1, 4, 1, 8, 2  → Sum = 6
Window 2:   1, [3, 2, 6] -1, 4, 1, 8, 2  → Sum = 11
Window 3:   1, 3, [2, 6, -1] 4, 1, 8, 2  → Sum = 7
...
```

### Template

```java
int windowStart = 0;
int windowSum = 0;

// Build first window
for (int i = 0; i < k; i++) {
    windowSum += arr[i];
}

int maxSum = windowSum;

// Slide window
for (int windowEnd = k; windowEnd < arr.length; windowEnd++) {
    windowSum += arr[windowEnd];           // Add new element
    windowSum -= arr[windowStart];         // Remove old element
    windowStart++;

    maxSum = Math.max(maxSum, windowSum);
}
```

---

## Pattern 2: Variable Size Window (Expanding/Shrinking)

**Algorithm**:
1. Expand window by moving right pointer
2. Add element to window
3. While window violates condition:
   - Shrink from left
   - Remove leftmost element
4. Update result

**Complexity**: O(n) time, O(k) space (for tracking window contents)

### Visual Example

```
Find longest substring without repeating characters
String: "abcabcbb"

Step 1: [a]bcabcbb           → Valid, length = 1
Step 2: [ab]cabcbb           → Valid, length = 2
Step 3: [abc]abcbb           → Valid, length = 3
Step 4: [abca]bcbb           → Invalid! 'a' repeats
        - Remove 'a': [bca]bcbb → Valid, length = 3
Step 5: [bcab]cbb            → Invalid! 'b' repeats
        - Remove 'b': [cab]cbb → Valid, length = 3
        - Remove 'c': [ab]cbb  → Valid, length = 2
```

### Template

```java
int windowStart = 0;
int maxLength = 0;
Set<Character> windowSet = new HashSet<>();

for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
    char rightChar = s.charAt(windowEnd);

    // Shrink window while condition violated
    while (windowSet.contains(rightChar)) {
        char leftChar = s.charAt(windowStart);
        windowSet.remove(leftChar);
        windowStart++;
    }

    // Add new element
    windowSet.add(rightChar);

    // Update result
    maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
}
```

---

## Common Use Cases

| Pattern | Description | Key Points |
|---------|-------------|------------|
| **Fixed Size** | Maximum/minimum sum of K elements | Window size constant |
| **Longest Substring** | Longest substring with condition | Expand right, shrink left |
| **Shortest Subarray** | Minimum length with sum ≥ target | Shrink when condition met |
| **Count Subarrays** | Count subarrays with property | Track valid windows |
| **Permutation/Anagram** | Find pattern in string | Use frequency map |

---

## Pattern 3: Dynamic Window with HashMap

**Use Case**: Track character/element frequencies

**Algorithm**:
1. Use HashMap to track window contents
2. Expand window by adding element to map
3. Shrink window when map size exceeds limit
4. Update result based on window state

**Complexity**: O(n) time, O(k) space where k = distinct elements

### Example: At Most K Distinct Characters

```java
Map<Character, Integer> freq = new HashMap<>();
int windowStart = 0;
int maxLength = 0;

for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
    char rightChar = s.charAt(windowEnd);
    freq.put(rightChar, freq.getOrDefault(rightChar, 0) + 1);

    // Shrink if too many distinct chars
    while (freq.size() > k) {
        char leftChar = s.charAt(windowStart);
        freq.put(leftChar, freq.get(leftChar) - 1);
        if (freq.get(leftChar) == 0) {
            freq.remove(leftChar);
        }
        windowStart++;
    }

    maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
}
```

---

## Pattern 4: Sliding Window Maximum (with Deque) ⭐ **IMPORTANT** ⭐

**⚠️ Key Pattern - Review Regularly**

**Problem**: Find the maximum element in each sliding window of size k.

**Use Case**: Track maximum/minimum in fixed-size sliding window

**Key Insight**: Use a **monotonic decreasing deque** to efficiently track maximum in O(1) per window.

**Algorithm**:
1. Use Deque to store **indices** (not values) in decreasing order of values
2. For each element:
   - Remove indices outside current window from front
   - Remove indices with smaller values from back (maintain decreasing order)
   - Add current index to back
   - If window is ready (i >= k-1), front of deque is the maximum
3. Front of deque always contains index of maximum element in current window

**Complexity**: O(n) time, O(k) space

**Why it works**:
- Deque maintains indices in decreasing order of their values
- Front always has the largest element in current window
- Each element is added and removed at most once → O(n) total

### Visual Example

```
Array: [1, 3, -1, -3, 5, 3, 6, 7]  k = 3

Window [1, 3, -1]:
- Deque: [1 (index of 3)] → Max = 3

Window [3, -1, -3]:
- Deque: [1 (index of 3)] → Max = 3

Window [-1, -3, 5]:
- Deque: [4 (index of 5)] → Max = 5

Window [-3, 5, 3]:
- Deque: [4, 5 (indices of 5, 3)] → Max = 5

Window [5, 3, 6]:
- Deque: [6 (index of 6)] → Max = 6

Window [3, 6, 7]:
- Deque: [7 (index of 7)] → Max = 7

Result: [3, 3, 5, 5, 6, 7]
```

### Template

```java
Deque<Integer> deque = new ArrayDeque<>();  // Stores indices
int[] result = new int[nums.length - k + 1];
int resultIdx = 0;

for (int i = 0; i < nums.length; i++) {
    // 1. Remove indices outside window from front
    while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
        deque.pollFirst();
    }

    // 2. Remove indices with smaller values from back
    //    (maintain decreasing order)
    while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
        deque.pollLast();
    }

    // 3. Add current index
    deque.offerLast(i);

    // 4. Window is ready, record maximum
    if (i >= k - 1) {
        result[resultIdx++] = nums[deque.peekFirst()];
    }
}
```

### Why Store Indices (Not Values)?

**Reason**: Need to know if element is still in window
- Can't determine window position from value alone
- Index allows checking: `if (index < windowStart)` → remove

### Monotonic Deque Invariant

**Decreasing Order** (for maximum):
- Front has largest element
- Back has smallest element
- All elements in deque: `nums[deque[0]] >= nums[deque[1]] >= ... >= nums[deque[n]]`

**Why remove smaller elements?**
- If `nums[j] < nums[i]` and `j < i`:
  - `nums[j]` will never be maximum (nums[i] is larger and lasts longer)
  - Safe to remove nums[j] from consideration

### Sliding Window Minimum (Variation)

**Same pattern, but maintain increasing order**:

```java
// Remove indices with LARGER values (maintain increasing order)
while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
    deque.pollLast();
}
```

Front of deque contains **minimum** instead of maximum.

---

## Critical Points

### When to Use Sliding Window
✅ Contiguous subarray/substring problems
✅ Need to find max/min/optimal window
✅ Brute force would be O(n²) or O(n³)
✅ Can track window state incrementally

### Common Mistakes
❌ Forgetting to shrink window
❌ Not updating result before/after shrinking
❌ Off-by-one errors in window size calculation
❌ Not handling edge cases (empty array, K > array size)

### Window Size Calculation
- **Window size** = `windowEnd - windowStart + 1`
- **Elements in window**: indices [windowStart, windowEnd] inclusive

### Two Pointer Movement
- **Right pointer**: Always moves forward (expands window)
- **Left pointer**: Only moves forward (never goes back)
- **Total iterations**: O(n) because each element visited at most twice

---

## Problem-Specific Patterns

### 1. Fixed Size Window
**Pattern**: Calculate for first K, then slide
**Problems**: Maximum sum, average, distinct elements in K-size window

### 2. Longest Window with Condition
**Pattern**: Expand right, shrink left when violated
**Problems**: Longest substring without repeating chars, at most K distinct

### 3. Shortest Window with Condition
**Pattern**: Shrink when condition met
**Problems**: Minimum subarray sum ≥ target, smallest substring containing pattern

### 4. Count Windows
**Pattern**: Count valid windows while sliding
**Problems**: Subarrays with product < K, nice subarrays

### 5. Permutation/Anagram
**Pattern**: Use frequency map, match counts
**Problems**: Permutation in string, find all anagrams

### 6. Sliding Window Max/Min ⭐ **IMPORTANT**
**Pattern**: Monotonic deque to track maximum/minimum
**Problems**: Sliding window maximum, sliding window median
**Key**: Use deque storing indices in decreasing (max) or increasing (min) order

---

## Time Complexity Analysis

### Why O(n)?
- Right pointer visits each element once: O(n)
- Left pointer visits each element at most once: O(n)
- Total: O(n) + O(n) = O(2n) = **O(n)**

### Space Complexity
- **Fixed window**: O(1) - just track sum/count
- **Variable window with Set/Map**: O(k) - k = distinct elements/chars
- **Frequency array**: O(26) for lowercase letters = O(1)

---

## Interview Tips

### Identify Sliding Window Problems
Look for keywords:
- "Contiguous" subarray/substring
- "Maximum/Minimum" length/sum
- "Longest/Shortest" window
- "Find all subarrays" with condition

### Template Selection
1. **Fixed K** → Use two loops (initial + sliding)
2. **Longest** → Expand right, shrink when invalid
3. **Shortest** → Expand right, shrink when valid
4. **Frequencies** → Use HashMap/Array

### Common Data Structures
- **Set**: Track unique elements (no repeats)
- **HashMap**: Track frequencies
- **Array[26]**: Frequency of lowercase letters
- **Deque**: Track min/max in window using monotonic deque pattern ⭐

---

## Problems to Practice

### Fixed Size Window
- [x] [Maximum Sum of Distinct Subarrays With Length K](https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/) - Medium
- [x] [Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold](https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/) - Medium
- [x] [Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences/) - Medium
- [x] [Permutation in String](https://leetcode.com/problems/permutation-in-string/) - Medium
- [x] [Sliding Subarray Beauty](https://leetcode.com/problems/sliding-subarray-beauty/) - Medium
- [x] [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) - Hard ⭐ **IMPORTANT** ⭐ (Monotonic Deque)

### Variable Size Window
- [ ] [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) - Medium
- [ ] [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) - Medium
- [ ] [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/) - Medium
- [ ] [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/) - Medium
- [ ] [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/) - Medium
- [ ] [Count Number of Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays) - Medium
- [ ] [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) - Hard

---

**Key Takeaway**: Sliding window converts O(n²) brute force to O(n) by reusing previous computation!
