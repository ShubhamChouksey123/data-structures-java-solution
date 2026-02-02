# Sliding Window Pattern

## Core Concept

A **sliding window** moves over data to solve problems in **O(n) time** instead of O(n²) brute force.

**Key Insight**: Maintain a window and update incrementally (add new, remove old) instead of recalculating from scratch.

---

## Two Patterns

| Pattern | When to Use | Keywords | Algorithm |
|---------|------------|----------|-----------|
| **[Fixed Size](fixed-size.md)** | Window size K given | "K consecutive", "length K" | Build first K → Slide |
| **[Variable Size](variable-size.md)** | Find optimal size | "longest", "shortest", "at most K" | Expand right → Shrink left |

### Decision Tree

```
Is window size K given?
├─ YES → Fixed Size Window (fixed-size.md)
└─ NO  → Variable Size Window (variable-size.md)
           ├─ "Longest" → Shrink when INVALID
           ├─ "Shortest" → Shrink when VALID
           └─ "Count" → Add window size to count
```

---

## Time Complexity: O(n)

**Both patterns are O(n)** because pointers never go backwards:
- **Fixed**: Each element visited once
- **Variable**: Right pointer visits once, left pointer visits once
- Each element processed at most twice → O(2n) = O(n)

---

## Common Data Structures

| Structure | Use Case | Space |
|-----------|----------|-------|
| **Variables** | Sum, product, count | O(1) |
| **HashSet** | Unique elements, no duplicates | O(k) |
| **HashMap** | Frequencies, counts | O(k) |
| **int[26]** | Letter frequencies | O(1) |
| **Deque** | Monotonic max/min | O(k) |

---

## Quick Templates

### Fixed Size
```java
int sum = 0;
for (int i = 0; i < k; i++) sum += arr[i];
int max = sum;

for (int i = k; i < arr.length; i++) {
    sum += arr[i] - arr[i - k];
    max = Math.max(max, sum);
}
```

### Variable Size - Longest
```java
int start = 0, maxLen = 0;
Set<Integer> window = new HashSet<>();

for (int end = 0; end < arr.length; end++) {
    while (window.contains(arr[end])) {
        window.remove(arr[start++]);
    }
    window.add(arr[end]);
    maxLen = Math.max(maxLen, end - start + 1);
}
```

### Variable Size - Shortest
```java
int start = 0, minLen = Integer.MAX_VALUE, sum = 0;

for (int end = 0; end < arr.length; end++) {
    sum += arr[end];
    while (sum >= target) {
        minLen = Math.min(minLen, end - start + 1);
        sum -= arr[start++];
    }
}
```

---

## Interview Tips

### Pattern Recognition
- **"K consecutive/length K"** → Fixed Size
- **"longest/maximum length"** → Variable Size (shrink when invalid)
- **"shortest/minimum length"** → Variable Size (shrink when valid)
- **"at most K distinct"** → Variable Size + HashMap
- **"without repeating"** → Variable Size + HashSet

### Common Mistakes
❌ Using `if` instead of `while` for shrinking
❌ Updating result before window is valid
❌ Forgetting to remove from map when count becomes 0
❌ Not validating K <= array length

### Window Size Calculation
```java
windowSize = windowEnd - windowStart + 1
```

---



## Detailed Guides

📐 **[Fixed Size Window](fixed-size.md)** - Constant window size, monotonic deque for max/min

🔄 **[Variable Size Window](variable-size.md)** - Dynamic window, longest/shortest/count patterns
