# Fixed Size Sliding Window

> **[← Back to Overview](Notes.md)**

## When to Use

✅ **Window size K is given/constant**

### Keywords
- "K consecutive elements"
- "subarray/substring of **length K**"
- "every window of **size K**"
- "average of K elements"

### Examples
- Maximum sum of K consecutive elements
- Average of all K-size subarrays
- Sliding window maximum
- Permutation in string (pattern length = K)

---

## Algorithm

1. **Build** first window (0 to K-1)
2. **Calculate** result for first window
3. **Slide** window (K to length-1):
   - Add new element (right)
   - Remove old element (left)
   - Update result

**Time**: O(n), **Space**: O(1) to O(k)

---

## Templates

### 1. Basic Sum/Count

```java
public int maxSum(int[] arr, int k) {
    int sum = 0;
    for (int i = 0; i < k; i++) sum += arr[i];
    int max = sum;

    for (int i = k; i < arr.length; i++) {
        sum += arr[i] - arr[i - k];
        max = Math.max(max, sum);
    }
    return max;
}
```

### 2. With HashSet (Distinct Elements)

```java
public int maxDistinct(int[] arr, int k) {
    Set<Integer> window = new HashSet<>();
    for (int i = 0; i < k; i++) window.add(arr[i]);
    int max = window.size();

    for (int i = k; i < arr.length; i++) {
        window.remove(arr[i - k]);
        window.add(arr[i]);
        max = Math.max(max, window.size());
    }
    return max;
}
```

### 3. With HashMap (Frequencies)

```java
public boolean hasPermutation(String s, String pattern) {
    if (s.length() < pattern.length()) return false;

    Map<Character, Integer> patternFreq = new HashMap<>();
    Map<Character, Integer> windowFreq = new HashMap<>();

    // Build pattern frequency
    for (char c : pattern.toCharArray()) {
        patternFreq.put(c, patternFreq.getOrDefault(c, 0) + 1);
    }

    int k = pattern.length();

    // Build first window
    for (int i = 0; i < k; i++) {
        char c = s.charAt(i);
        windowFreq.put(c, windowFreq.getOrDefault(c, 0) + 1);
    }
    if (windowFreq.equals(patternFreq)) return true;

    // Slide window
    for (int i = k; i < s.length(); i++) {
        // Remove leftmost
        char left = s.charAt(i - k);
        windowFreq.put(left, windowFreq.get(left) - 1);
        if (windowFreq.get(left) == 0) windowFreq.remove(left);

        // Add rightmost
        char right = s.charAt(i);
        windowFreq.put(right, windowFreq.getOrDefault(right, 0) + 1);

        if (windowFreq.equals(patternFreq)) return true;
    }
    return false;
}
```

---

## Monotonic Deque Pattern ⭐

**Problem**: Find max/min in each K-size window

**Key**: Use deque storing **indices** in decreasing order (for max)

### Why Monotonic Deque?
- Maintains max/min in O(1) per window
- Each element added/removed at most once → O(n) total
- Front always has current max/min

### Visual Example

```
arr = [1, 3, -1, -3, 5, 3, 6, 7], k = 3

Window [1, 3, -1]:  deque = [1] (idx of 3)  → max = 3
Window [3, -1, -3]: deque = [1] (idx of 3)  → max = 3
Window [-1, -3, 5]: deque = [4] (idx of 5)  → max = 5
Window [-3, 5, 3]:  deque = [4, 5]          → max = 5
Window [5, 3, 6]:   deque = [6] (idx of 6)  → max = 6
Window [3, 6, 7]:   deque = [7] (idx of 7)  → max = 7

Result: [3, 3, 5, 5, 6, 7]
```

### Template: Sliding Window Maximum

```java
public int[] maxSlidingWindow(int[] nums, int k) {
    Deque<Integer> deque = new ArrayDeque<>();  // stores indices
    int[] result = new int[nums.length - k + 1];

    for (int i = 0; i < nums.length; i++) {
        // Remove indices outside window
        while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
            deque.pollFirst();
        }

        // Remove indices with smaller values (maintain decreasing order)
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
            deque.pollLast();
        }

        deque.offerLast(i);

        // Record result when window is ready
        if (i >= k - 1) {
            result[i - k + 1] = nums[deque.peekFirst()];
        }
    }
    return result;
}
```

**For minimum**: Use increasing order
```java
// Remove indices with LARGER values
while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
    deque.pollLast();
}
```

**Why indices not values?** Need to check if element is in current window

---

## Common Mistakes

### ❌ Wrong Window Size
```java
// WRONG
int size = end - start;

// CORRECT
int size = end - start + 1;
```

### ❌ Not Validating K
```java
// WRONG - ArrayIndexOutOfBounds
for (int i = 0; i < k; i++) sum += arr[i];

// CORRECT
if (k > arr.length) return -1;
```

### ❌ Using Values in Deque
```java
// WRONG - can't check window boundaries
deque.offer(nums[i]);

// CORRECT - use indices
deque.offer(i);
```

---

## Problems

- [x] [Maximum Sum of Distinct Subarrays With Length K](https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/) - Medium
- [x] [Number of Sub-arrays of Size K and Average ≥ Threshold](https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/) - Medium
- [x] [Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences/) - Medium
- [x] [Permutation in String](https://leetcode.com/problems/permutation-in-string/) - Medium
- [x] [Sliding Subarray Beauty](https://leetcode.com/problems/sliding-subarray-beauty/) - Medium ⭐
- [x] [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) - Hard ⭐

### Sliding Window Maximum ⭐ **IMPORTANT** ⭐

**Problem**: [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) - Hard

**Why Important**: Classic monotonic deque pattern, frequently asked in FAANG interviews, non-intuitive O(n) solution using deque instead of brute force O(nk)

**Approach**:
1. Use deque to store indices in decreasing order of their values
2. Remove indices outside current window from front
3. Remove indices with smaller values from back (maintain decreasing order)
4. Front of deque always contains index of maximum element
5. Record maximum when window is complete

**Complexity**: O(n) time, O(k) space

**Solution**:

```java
public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length == 0) return new int[0];

    Deque<Integer> deque = new ArrayDeque<>();  // Stores indices
    int[] result = new int[nums.length - k + 1];

    for (int i = 0; i < nums.length; i++) {
        // 1. Remove indices outside current window from front
        while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
            deque.pollFirst();
        }

        // 2. Remove indices with smaller values from back
        //    This maintains decreasing order of values
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
            deque.pollLast();
        }

        // 3. Add current index to back
        deque.offerLast(i);

        // 4. Record result when window is complete (i >= k-1)
        if (i >= k - 1) {
            result[i - k + 1] = nums[deque.peekFirst()];
        }
    }

    return result;
}
```

**Key Points**:
- **Store indices not values** - needed to check if element is still in window
- **Deque maintains decreasing order** - front has largest, back has smallest
- **Why remove smaller values?** If `nums[j] < nums[i]` and `j < i`, then `nums[j]` will never be max (nums[i] is larger and lasts longer)
- **Each element added/removed at most once** - O(n) total time
- **For minimum**: Use increasing order instead (remove larger values from back)

---

## Key Takeaways

1. Window size K is **constant**
2. **Build** first K elements, then **slide**
3. Add new, remove old: `sum += arr[i] - arr[i-k]`
4. Use **monotonic deque** for max/min ⭐
5. Store **indices** in deque, not values
6. Validate `k <= arr.length`

> **[← Back to Overview](Notes.md)** | **[Variable Size →](variable-size.md)**
