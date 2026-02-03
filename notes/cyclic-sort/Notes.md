# Cyclic Sort

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Find missing/duplicate numbers in arrays with numbers in range [0,n] or [1,n]**

### Keywords
- "numbers from 1 to n" or "0 to n-1"
- "find missing/duplicate"
- "in-place", "O(1) space"

### Examples
- Missing/duplicate numbers, First missing positive

---

## Core Concept

Place each number at its correct index by swapping: number `x` → index `x-1` (for [1,n]) or index `x` (for [0,n]).

**Key Insight**: Each number swapped at most once → O(n) time, O(1) space.

**Complexity**: O(n) time, O(1) space

---

## Basic Template

```java
private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
```

### Core Algorithm Pattern

```java
int i = 0;
while (i < nums.length) {
    int correctIndex = nums[i] - 1; // For [1,n]. Use nums[i] for [0,n]
    if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[correctIndex]) {
        swap(nums, i, correctIndex);
    } else {
        i++;
    }
}
```

**Visual**: `[3,1,5,4,2]` → Swap 3→5→2→1 at i=0 → `[1,2,3,4,5]`

**Complexity**: O(n) time, O(1) space

---

## Common Variations

| Problem | After Sort | Return |
|---------|------------|--------|
| **Missing Number** | `nums[i] != i` | First index `i` |
| **All Missing** | `nums[i] != i+1` | All indices `i+1` |
| **Duplicate** | `nums[i] == nums[correctIndex]` but `i != correctIndex` | `nums[i]` during sort |
| **All Duplicates** | `nums[i] != i+1` | All `nums[i]` |

---

## Common Mistakes

### ❌ Using For Loop (doesn't recheck after swap)

```java
// WRONG
for (int i = 0; i < nums.length; i++) {
    swap(nums, i, correctIndex);
}

// CORRECT - while loop to recheck same index
while (i < nums.length) {
    if (nums[i] != nums[correctIndex]) swap(nums, i, correctIndex);
    else i++;
}
```

### ❌ Infinite Loop (always swapping duplicates)

```java
// WRONG - loops forever if nums[i] == nums[correctIndex]
swap(nums, i, correctIndex);

// CORRECT - check before swap
if (nums[i] != nums[correctIndex]) swap(nums, i, correctIndex);
```

---

## Problems

- [x] [Missing Number](https://leetcode.com/problems/missing-number/) - Easy
- [x] [Find All Numbers Disappeared in Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) - Easy
- [x] [Find Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) - Medium
- [x] [Find All Duplicates in Array](https://leetcode.com/problems/find-all-duplicates-in-an-array/) - Medium
- [x] [Set Mismatch](https://leetcode.com/problems/set-mismatch/) - Easy
- [x] [First Missing Positive](https://leetcode.com/problems/first-missing-positive/) - Hard ⭐ **IMPORTANT** ⭐

### First Missing Positive ⭐ **IMPORTANT** ⭐

**Problem**: [First Missing Positive](https://leetcode.com/problems/first-missing-positive/) - Hard

**Why Important**: Handles negatives/out-of-range values, frequently asked at FAANG

**Approach**:
1. Ignore numbers ≤ 0 or > n (only [1,n] matters)
2. Apply cyclic sort: number `x` → index `x-1`
3. Find first `nums[i] != i+1`, return `i+1`
4. If all correct, return `n+1`

**Complexity**: O(n) time, O(1) space

**Solution**:

```java
public int firstMissingPositive(int[] nums) {
    int i = 0;
    while (i < nums.length) {
        int correctIndex = nums[i] - 1;
        if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[correctIndex]) {
            swap(nums, i, correctIndex);
        } else {
            i++;
        }
    }

    for (i = 0; i < nums.length; i++) {
        if (nums[i] != i + 1) return i + 1;
    }
    return nums.length + 1;
}
```

**Example**: `[3,4,-1,1]` → Swap to `[1,-1,3,4]` → Missing at index 1 → Return `2`

**Key Points**:
- Ignore out-of-range (≤0 or >n) - only [1,n] matters for size-n array
- Edge case: If [1,2,...,n] complete, return n+1
- Must check `nums[i] != nums[correctIndex]` to avoid infinite loop

---

## Key Takeaways

1. **Use when**: Array has numbers in range [0,n] or [1,n]
2. **Index formula**: `x` → index `x-1` for [1,n], or index `x` for [0,n]
3. **While loop required**: Recheck same index after swap (not for loop)
4. **Check before swap**: `nums[i] != nums[correctIndex]` to avoid infinite loop
5. **Complexity**: O(n) time (each element swapped ≤ once), O(1) space

---

> **[← Back to Overview](../README.md)** | **[Two Pointers →](../two-pointers/Notes.md)**
