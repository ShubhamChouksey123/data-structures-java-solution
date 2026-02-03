# Two Pointers

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Use two pointers when working with sorted arrays or when you need to process elements from both ends**

### Keywords
- "sorted array"
- "pair sum"
- "partition"
- "three-way partition"
- "container"
- "move from both ends"

### Examples
- Find pair with target sum in sorted array
- Container with most water
- Dutch National Flag (three-way partition)
- Trapping rain water
- Remove duplicates from sorted array

---

## Core Concept

Two pointers technique uses two indices to traverse an array or list, either moving toward each other from opposite ends or moving in the same direction at different speeds.

**Key Insight**: By maintaining two pointers and moving them based on problem constraints, we avoid nested loops and achieve O(n) time complexity.

**Complexity**: O(n) time, O(1) space (typically)

---

## Pattern 1: Opposite Ends

**Use Case**: When array is sorted or you need to consider pairs from both ends

**Algorithm**:
1. Initialize left pointer at start (0), right pointer at end (n-1)
2. Process elements at both pointers
3. Move pointers based on condition:
   - If sum/product too small, move left pointer right (left++)
   - If sum/product too large, move right pointer left (right--)
   - If condition met, process and move both
4. Continue until pointers meet (left >= right)

**Complexity**: O(n) time, O(1) space

### Template

```java
public int oppositeEnds(int[] arr) {
    int left = 0, right = arr.length - 1;

    while (left < right) {
        // Process arr[left] and arr[right]

        if (condition) {
            // Found answer or process pair
            left++;
            right--;
        } else if (needIncrease) {
            left++;  // Move left pointer right
        } else {
            right--; // Move right pointer left
        }
    }

    return result;
}
```

### Visual Example

```
Array: [1, 2, 3, 4, 5, 6]  Target: 7

Step 1: left=0(1), right=5(6) → 1+6=7 ✓
Step 2: left=1(2), right=4(5) → 2+5=7 ✓
Step 3: left=2(3), right=3(4) → 3+4=7 ✓
```

---

## Pattern 2: Same Direction (Partitioning)

**Use Case**: When you need to partition array elements or remove/skip certain elements

**Algorithm**:
1. Initialize slow pointer at start (tracks position for valid elements)
2. Initialize fast pointer at start (explores array)
3. Fast pointer scans through array
4. When fast finds valid element, swap with slow and increment slow
5. Elements before slow are valid, elements after are invalid/processed

**Complexity**: O(n) time, O(1) space

### Template

```java
public void partition(int[] arr) {
    int slow = 0;  // Boundary of valid elements

    for (int fast = 0; fast < arr.length; fast++) {
        if (isValid(arr[fast])) {
            swap(arr, slow, fast);
            slow++;
        }
    }

    // Elements [0, slow) are valid
}
```

---

## Pattern 3: Dutch National Flag (Three-Way Partition)

**Use Case**: Partition array into three sections (e.g., < pivot, = pivot, > pivot)

**Algorithm**:
1. Initialize three pointers: low=0, mid=0, high=n-1
2. Process element at mid:
   - If element belongs to first section: swap with low, increment both low and mid
   - If element belongs to middle section: just increment mid
   - If element belongs to last section: swap with high, decrement high (don't increment mid)
3. Continue until mid > high

**Complexity**: O(n) time, O(1) space

### Template

```java
public void dutchFlag(int[] arr) {
    int low = 0, mid = 0, high = arr.length - 1;

    while (mid <= high) {
        if (arr[mid] == 0) {
            swap(arr, low, mid);
            low++;
            mid++;
        } else if (arr[mid] == 1) {
            mid++;
        } else {  // arr[mid] == 2
            swap(arr, mid, high);
            high--;
            // Don't increment mid (need to check swapped element)
        }
    }
}
```

**Visual Example** (Sort Colors: 0=red, 1=white, 2=blue):
```
Initial: [2, 0, 2, 1, 1, 0]
         low=0, mid=0, high=5

Step 1: arr[mid]=2, swap with high
        [0, 0, 2, 1, 1, 2]
        low=0, mid=0, high=4

Step 2: arr[mid]=0, swap with low
        [0, 0, 2, 1, 1, 2]
        low=1, mid=1, high=4

Step 3: arr[mid]=0, swap with low
        [0, 0, 2, 1, 1, 2]
        low=2, mid=2, high=4

Step 4: arr[mid]=2, swap with high
        [0, 0, 1, 1, 2, 2]
        low=2, mid=2, high=3

Step 5: arr[mid]=1, just move mid
        low=2, mid=3, high=3

Step 6: arr[mid]=1, just move mid
        low=2, mid=4, high=3 → STOP (mid > high)

Result: [0, 0, 1, 1, 2, 2]
```

---

## Common Mistakes

### ❌ Mistake 1: Wrong Loop Condition

```java
// WRONG - misses middle element when left == right
while (left < right) { }

// CORRECT - for processing all elements including middle
while (left <= right) { }

// Note: Use left < right for pair problems, left <= right for element processing
```

### ❌ Mistake 2: Moving Both Pointers When Shouldn't

```java
// WRONG - in Dutch National Flag after swapping with high
swap(arr, mid, high);
mid++;    // BUG: haven't checked the swapped element yet!
high--;

// CORRECT
swap(arr, mid, high);
high--;   // Only decrement high, check arr[mid] in next iteration
```

### ❌ Mistake 3: Not Checking Array Bounds

```java
// WRONG - can cause ArrayIndexOutOfBoundsException
while (left < right) {
    if (arr[left] + arr[right + 1] == target) { }
}

// CORRECT
while (left < right) {
    if (arr[left] + arr[right] == target) { }
}
```

---

## Problems

- [x] [Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) - Medium
- [x] [Sort Colors](https://leetcode.com/problems/sort-colors/) - Medium *(Dutch National Flag)*
- [x] [Next Permutation](https://leetcode.com/problems/next-permutation/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Bag of Tokens](https://leetcode.com/problems/bag-of-tokens/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) - Hard ⭐ **IMPORTANT** ⭐

### Next Permutation ⭐ **IMPORTANT** ⭐

**Problem**: [Next Permutation](https://leetcode.com/problems/next-permutation/) - Medium

**Why Important**: Classic algorithm design problem requiring in-place array manipulation, tests understanding of lexicographic ordering, frequently asked at FAANG companies, non-intuitive multi-step approach

**Approach**:
1. **Find pivot**: Scan from right to left, find first element where arr[i] < arr[i+1] (first decreasing element)
2. **Handle edge case**: If no pivot found, array is in descending order → reverse entire array and return
3. **Find successor**: Scan from right, find smallest element larger than pivot
4. **Swap**: Swap pivot with successor
5. **Reverse suffix**: Reverse the subarray after pivot position to get next smallest permutation

**Key Insight**:
- Next permutation is formed by making the **smallest possible increase** to the number
- After finding pivot, everything to its right is in **descending order**
- After swap, we need the suffix in **ascending order** (smallest arrangement)
- Reversing a descending sequence gives ascending sequence

**Complexity**: O(n) time, O(1) space

**Solution**:

```java
public void nextPermutation(int[] nums) {
    if (nums == null || nums.length <= 1) {
        return;
    }

    // Step 1: Find the pivot (first decreasing element from right)
    int pivot = nums.length - 2;
    while (pivot >= 0 && nums[pivot] >= nums[pivot + 1]) {
        pivot--;
    }

    // Step 2: If pivot found, find successor and swap
    if (pivot >= 0) {
        // Find smallest element larger than pivot (from right)
        int successor = nums.length - 1;
        while (successor > pivot && nums[successor] <= nums[pivot]) {
            successor--;
        }
        // Swap pivot with successor
        swap(nums, pivot, successor);
    }

    // Step 3: Reverse the suffix after pivot to get smallest arrangement
    // If no pivot (pivot == -1), this reverses entire array
    reverse(nums, pivot + 1, nums.length - 1);
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}

private void reverse(int[] nums, int left, int right) {
    while (left < right) {
        swap(nums, left, right);
        left++;
        right--;
    }
}
```

**Visual Example**:

```
Example 1: [1, 2, 3]
Step 1: Find pivot
        [1, 2, 3]
            ↑ pivot=1 (nums[1]=2 < nums[2]=3)

Step 2: Find successor
        [1, 2, 3]
               ↑ successor=2 (smallest > 2)

Step 3: Swap
        [1, 3, 2]

Step 4: Reverse suffix after pivot
        [1, 3, 2] → [1, 3, 2] (nothing to reverse)

Result: [1, 3, 2]

---

Example 2: [1, 3, 2]
Step 1: Find pivot
        [1, 3, 2]
         ↑ pivot=0 (nums[0]=1 < nums[1]=3)

Step 2: Find successor
        [1, 3, 2]
               ↑ successor=2 (smallest element > 1 in suffix)

Step 3: Swap
        [2, 3, 1]

Step 4: Reverse suffix [pivot+1 to end]
        [2, 3, 1] → [2, 1, 3]

Result: [2, 1, 3]

---

Example 3: [3, 2, 1]
Step 1: Find pivot
        [3, 2, 1]
        No pivot (entire array is descending)
        pivot = -1

Step 2: Skip (no pivot found)

Step 3: Reverse entire array [0 to end]
        [3, 2, 1] → [1, 2, 3]

Result: [1, 2, 3] (wrap around to smallest permutation)
```

**Step-by-Step Execution**:

```
Input: [1, 5, 8, 4, 7, 6, 5, 3, 1]

Step 1: Find pivot (scan right to left)
        [1, 5, 8, 4, 7, 6, 5, 3, 1]
               ↑
        pivot = 3 (nums[3]=4 < nums[4]=7)
        Everything after pivot is descending: [7,6,5,3,1]

Step 2: Find successor (smallest element > 4 in suffix)
        [1, 5, 8, 4, 7, 6, 5, 3, 1]
                     ↑
        successor = 5 (nums[5]=5 is smallest > 4)

Step 3: Swap pivot with successor
        [1, 5, 8, 5, 7, 6, 4, 3, 1]
               swap ↑ ↔ ↑

Step 4: Reverse suffix after pivot position
        [1, 5, 8, 5, | 7, 6, 4, 3, 1]
                    reverse this part ↓
        [1, 5, 8, 5, | 1, 3, 4, 6, 7]

Result: [1, 5, 8, 5, 1, 3, 4, 6, 7]
```



---

### Container With Most Water ⭐ **IMPORTANT** ⭐

**Problem**: [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) - Medium

**Why Important**: Classic greedy + two pointers problem, tests understanding of when to move which pointer, frequently asked

**Approach**:
1. Start with widest container (left=0, right=n-1)
2. Calculate area: min(height[left], height[right]) * (right - left)
3. Move the pointer with smaller height (this is the key insight!)
4. Track maximum area seen

**Key Insight**: We move the shorter line because:
- Area is limited by shorter height
- Moving shorter line might find a taller line (increasing area)
- Moving taller line will only decrease width with no guarantee of better height

**Complexity**: O(n) time, O(1) space

**Solution**:

```java
public int maxArea(int[] height) {
    int left = 0, right = height.length - 1;
    int maxArea = 0;

    while (left < right) {
        // Calculate current area
        int width = right - left;
        int currentHeight = Math.min(height[left], height[right]);
        int area = width * currentHeight;

        maxArea = Math.max(maxArea, area);

        // Move pointer with smaller height
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }

    return maxArea;
}
```

**Key Points**:
- Always move the pointer with **smaller height** (greedy choice)
- Width decreases with each step, so we need potential for greater height
- Don't move both pointers - only move the limiting factor
- Maximum possible area is with widest container, so start from ends

---

### Bag of Tokens ⭐ **IMPORTANT** ⭐

**Problem**: [Bag of Tokens](https://leetcode.com/problems/bag-of-tokens/) - Medium

**Why Important**: Combines sorting, greedy strategy, and two pointers; tests decision-making about which end to process

**Approach**:
1. Sort tokens array (enables greedy approach)
2. Use two pointers: left (smallest tokens) and right (largest tokens)
3. Strategy:
   - Face up smallest token (costs power, gains score) when we have power
   - Face down largest token (gains power, costs score) when we need power and have score
4. Track maximum score achieved at any point

**Key Insight**:
- Sort first to enable greedy choices
- Buy low (use small tokens face-up to gain score cheaply)
- Sell high (use large tokens face-down to gain maximum power)

**Complexity**: O(n log n) time (sorting), O(1) space

**Solution**:

```java
public int bagOfTokensScore(int[] tokens, int power) {
    Arrays.sort(tokens);

    int left = 0, right = tokens.length - 1;
    int score = 0, maxScore = 0;

    while (left <= right) {
        // Face up: use smallest token to gain score
        if (power >= tokens[left]) {
            power -= tokens[left];
            score++;
            left++;
            maxScore = Math.max(maxScore, score);
        }
        // Face down: use largest token to gain power (if we have score)
        else if (score > 0) {
            power += tokens[right];
            score--;
            right--;
        }
        // Can't do anything
        else {
            break;
        }
    }

    return maxScore;
}
```

**Key Points**:
- **Must sort first** - enables greedy strategy
- **Buy low, sell high** - minimize cost of gaining score, maximize gain when spending score
- **Track max score continuously** - answer might not be at the end
- **Face-up when possible** - prioritize gaining score over saving power
- Loop condition is `left <= right` (can process last remaining token)

---

### Trapping Rain Water ⭐ **IMPORTANT** ⭐

**Problem**: [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) - Hard

**Why Important**: Classic hard problem combining two pointers with max-tracking, tests deep understanding of water level constraints, very frequently asked in top tech companies

**Approach**:
1. Use two pointers from opposite ends (left=0, right=n-1)
2. Track maximum heights seen so far from left (leftMax) and right (rightMax)
3. Water at position is determined by min(leftMax, rightMax) - height[position]
4. Process the side with smaller max height (similar to Container With Most Water)
5. If current height updates the max, no water trapped; otherwise calculate trapped water

**Key Insight**:
- Water level at any position is limited by the **minimum** of max heights on both sides
- We can process from the side with smaller max because:
  - If leftMax < rightMax, water at left is limited by leftMax (rightMax doesn't matter)
  - If rightMax < leftMax, water at right is limited by rightMax (leftMax doesn't matter)
- This allows us to solve in one pass without extra space

**Complexity**: O(n) time, O(1) space

**Solution**:

```java
public int trap(int[] height) {
    if (height == null || height.length == 0) {
        return 0;
    }

    int left = 0, right = height.length - 1;
    int leftMax = 0, rightMax = 0;
    int totalWater = 0;

    while (left < right) {
        // Process side with smaller max height
        if (height[left] < height[right]) {
            // Process left side
            if (height[left] >= leftMax) {
                leftMax = height[left];  // Update max, no water trapped
            } else {
                totalWater += leftMax - height[left];  // Trap water
            }
            left++;
        } else {
            // Process right side
            if (height[right] >= rightMax) {
                rightMax = height[right];  // Update max, no water trapped
            } else {
                totalWater += rightMax - height[right];  // Trap water
            }
            right--;
        }
    }

    return totalWater;
}
```

**Visual Example**:
```
Height: [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]

         █
     █   █ █   █
 █   █ █ █ █ █ █
 0 1 0 2 1 0 1 3 2 1 2 1

Water trapped (marked with ~):
         █
     █ ~ █ █ ~ █
 █ ~ █ █ █ █ █ █
 0 1 0 2 1 0 1 3 2 1 2 1

Water at each position:
- Position 2: min(1, 3) - 0 = 1
- Position 4: min(2, 3) - 1 = 1
- Position 5: min(2, 3) - 0 = 2
- Position 6: min(2, 3) - 1 = 1
- Position 9: min(2, 2) - 1 = 1
Total: 1 + 1 + 2 + 1 + 1 = 6
```

**Step-by-Step Execution**:
```
Initial: left=0, right=11, leftMax=0, rightMax=0

Step 1: height[left]=0 < height[right]=1
        height[0]=0 >= leftMax=0 → leftMax=0, left=1

Step 2: height[left]=1 < height[right]=1
        height[1]=1 >= leftMax=0 → leftMax=1, left=2

Step 3: height[left]=0 < height[right]=1
        height[2]=0 < leftMax=1 → water += 1-0=1, left=3

... continues until left >= right
```

**Key Points**:
- **Two pointers + max tracking** - combines convergent pointers with state tracking
- **Process smaller side** - water is limited by smaller of the two max heights
- **One pass, O(1) space** - unlike DP approach which needs O(n) space
- **Compare height[left] vs height[right]**, not leftMax vs rightMax, to decide which side to process
- **Update max before checking water** - ensures we have correct boundary
- Common mistake: comparing leftMax vs rightMax instead of height[left] vs height[right]

**Alternative Approaches**:
- **DP (Two passes)**: O(n) time, O(n) space - precompute leftMax and rightMax arrays
- **Stack**: O(n) time, O(n) space - monotonic stack approach
- **Two Pointers**: O(n) time, O(1) space - optimal solution shown above

---

## Key Takeaways

1. **Two main patterns**: Opposite ends (convergent) and same direction (partitioning)
2. **Opposite ends** - use when array is sorted or you need pairs from both ends
3. **Same direction** - use for partitioning or filtering elements in place
4. **Dutch National Flag** - special case for three-way partition
5. **Greedy + Two Pointers** - often combined (sort first, then use pointers)
6. **Move the limiting factor** - in problems like Container With Most Water, move the smaller height
7. **O(n) time, O(1) space** - typical complexity for two pointers
8. **Watch loop conditions** - `left < right` vs `left <= right` matters

---

> **[← Back to Overview](../README.md)** | **[Sliding Window →](../sliding-window/Notes.md)**
