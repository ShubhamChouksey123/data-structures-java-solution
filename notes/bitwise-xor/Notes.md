# Bitwise XOR

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Find missing numbers, single elements, or efficiently pair elements using XOR properties**

### Keywords
- "find missing number"
- "single number"
- "appears once"
- "all others appear twice/three times"
- "prefix XOR"
- "subarray XOR"
- "pairs of unique numbers"

### Examples
- Find missing number in array [0, n]
- Find single number when all others appear twice
- Find two unique numbers when all others appear twice
- Calculate XOR of subarray ranges
- Find original array from prefix XOR

---

## Core Concept

XOR (exclusive OR) is a bitwise operator with unique mathematical properties that make it powerful for finding missing or unique elements.

**Key Properties**:
1. `a ^ a = 0` - Any number XORed with itself is 0
2. `a ^ 0 = a` - Any number XORed with 0 is itself
3. **Commutative**: `a ^ b = b ^ a`
4. **Associative**: `(a ^ b) ^ c = a ^ (b ^ c)`
5. **Self-Inverse**: If `a ^ b = c`, then `a = b ^ c` and `b = a ^ c` (XOR is its own inverse)

**Key Insight**: XOR of all elements cancels out pairs, leaving only unique elements.

**Complexity**: O(n) time, O(1) space for most XOR problems

---

## Pattern 1: Find Missing/Single Number ⭐ **IMPORTANT** ⭐

**⚠️ Key Pattern - Review Regularly**

**Use Case**: Find missing number or single occurrence when all others appear in pairs

**Algorithm**:
1. XOR all elements together
2. Pairs cancel out (a ^ a = 0)
3. Result is the unique/missing element

**Complexity**: O(n) time, O(1) space

**Why Important**: Most fundamental XOR pattern, demonstrates core XOR properties, frequently asked in interviews

### Template

```java
// Find missing number in [0, n]
public int missingNumber(int[] nums) {
    int xor = nums.length;  // Start with n

    for (int i = 0; i < nums.length; i++) {
        xor ^= i ^ nums[i];  // XOR index and value
    }

    return xor;
}

// Find single number (all others appear twice)
public int singleNumber(int[] nums) {
    int xor = 0;

    for (int num : nums) {
        xor ^= num;
    }

    return xor;  // All pairs cancel out
}
```


---

## Pattern 2: XOR with Bit Manipulation

**Use Case**: Find single number when all others appear three times

**Algorithm**:
1. For each of the 32 bit positions
2. Count how many numbers have that bit set
3. If count % 3 != 0, the single number has that bit set
4. Build the answer by setting those bits

**Complexity**: O(32n) = O(n) time, O(1) space

### Template

```java
public int singleNumber(int[] nums) {
    int ans = 0;

    // Check each of the 32 bit positions
    for (int i = 0; i < 32; i++) {
        int count = 0;

        // Count how many numbers have bit i set
        for (int num : nums) {
            count += (1 & (num >> i));  // Check if bit i is set
        }

        // If count is not divisible by 3, single number has this bit set
        if (count % 3 != 0) {
            ans = ans | (1 << i);  // Set bit i in answer
        }
    }

    return ans;
}
```

**Key Points**:
- Check all 32 bit positions (Java int is 32-bit)
- `(num >> i)` shifts bit i to position 0
- `1 & (num >> i)` extracts bit i (0 or 1)
- If bit appears in single number, count % 3 will be 1 (not 0)
- `ans | (1 << i)` sets bit i in the answer

---

## Pattern 3: Find Two Unique Numbers

**Use Case**: Find two unique numbers when all others appear twice

**Algorithm**:
1. XOR all numbers → result is `a ^ b` (two unique numbers)
2. Find rightmost set bit in result (where a and b differ)
3. Partition array by this bit
4. XOR each partition to get a and b

**Complexity**: O(n) time, O(1) space

### Template

```java
public int[] singleNumber(int[] nums) {
    // Step 1: Get XOR of two unique numbers
    int xor = 0;
    for (int num : nums) {
        xor ^= num;
    }

    // Step 2: Find rightmost set bit
    int rightmostBit = xor & -xor;  // Isolate rightmost 1

    // Step 3: Partition and XOR each group
    int num1 = 0, num2 = 0;
    for (int num : nums) {
        if ((num & rightmostBit) == 0) {
            num1 ^= num;
        } else {
            num2 ^= num;
        }
    }

    return new int[]{num1, num2};
}
```

### Visual Example

```
Array: [1, 2, 1, 3, 2, 5]  (unique: 3, 5)

Step 1: XOR all → 3 ^ 5 = 6 (binary: 110)
Step 2: Rightmost bit → 2 (binary: 010)

Step 3: Partition by bit position 1:
  Group 0 (bit=0): [1, 1, 3] → XOR = 3
  Group 1 (bit=1): [2, 2, 5] → XOR = 5

Result: [3, 5]
```

---

## Pattern 4: Prefix XOR

**Use Case**: Calculate XOR of subarray ranges efficiently

**Algorithm**:
1. Build prefix XOR array: `prefix[i] = nums[0] ^ ... ^ nums[i-1]`
2. XOR of range [L, R] = `prefix[R+1] ^ prefix[L]`

**Complexity**: O(n) preprocessing, O(1) per query

### Template

```java
// Build prefix XOR array
public int[] buildPrefixXOR(int[] nums) {
    int n = nums.length;
    int[] prefix = new int[n + 1];

    for (int i = 0; i < n; i++) {
        prefix[i + 1] = prefix[i] ^ nums[i];
    }

    return prefix;
}

// Query XOR of range [L, R]
public int rangeXOR(int[] prefix, int L, int R) {
    return prefix[R + 1] ^ prefix[L];
}

// Find original array from prefix XOR
public int[] findArray(int[] pref) {
    int n = pref.length;
    int[] arr = new int[n];
    arr[0] = pref[0];

    for (int i = 1; i < n; i++) {
        arr[i] = pref[i] ^ pref[i - 1];
    }

    return arr;
}
```

### Visual Example

```
Array: [1, 3, 4, 8]
Prefix XOR: [0, 1, 2, 6, 14]
            (0, 1, 1^3, 1^3^4, 1^3^4^8)

Query: XOR of [1, 3] (elements 3, 4, 8)
  = prefix[4] ^ prefix[1]
  = 14 ^ 1
  = 15

Why? prefix[4] = 1^3^4^8
     prefix[1] = 1
     14 ^ 1 = (1^3^4^8) ^ 1 = 3^4^8
```

---

## Common Mistakes

### ❌ Mistake 1: Forgetting Initial XOR Value

```java
// WRONG - Missing initial value for missing number
public int missingNumber(int[] nums) {
    int xor = 0;  // Should start with nums.length
    for (int i = 0; i < nums.length; i++) {
        xor ^= i ^ nums[i];
    }
    return xor;
}

// CORRECT
public int missingNumber(int[] nums) {
    int xor = nums.length;  // Include n
    for (int i = 0; i < nums.length; i++) {
        xor ^= i ^ nums[i];
    }
    return xor;
}
```

### ❌ Mistake 2: Wrong Bit Isolation

```java
// WRONG - Incorrect way to find rightmost set bit
int rightmostBit = xor & (xor - 1);  // This clears rightmost bit

// CORRECT - Isolates rightmost set bit
int rightmostBit = xor & -xor;  // Uses two's complement
// Alternative: xor & (~xor + 1)
```

### ❌ Mistake 3: Incorrect Prefix XOR Range Query

```java
// WRONG - Off-by-one error
public int rangeXOR(int[] prefix, int L, int R) {
    return prefix[R] ^ prefix[L];  // Missing elements
}

// CORRECT
public int rangeXOR(int[] prefix, int L, int R) {
    return prefix[R + 1] ^ prefix[L];  // Includes all [L, R]
}
```

---

## Problems

- [x] [Missing Number](https://leetcode.com/problems/missing-number/) - Easy ⭐ **IMPORTANT** ⭐
- [x] [Single Number II](https://leetcode.com/problems/single-number-ii/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Single Number III](https://leetcode.com/problems/single-number-iii/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Find The Original Array of Prefix Xor](https://leetcode.com/problems/find-the-original-array-of-prefix-xor/) - Medium
- [x] [XOR Queries of a Subarray](https://leetcode.com/problems/xor-queries-of-a-subarray/) - Medium

### Missing Number ⭐ **IMPORTANT** ⭐

**Problem**: [Missing Number](https://leetcode.com/problems/missing-number/) - Easy

**Why Important**: Classic application of XOR properties, demonstrates self-canceling pairs, most fundamental XOR pattern, frequently asked in interviews

**Approach**:
1. XOR all indices [0, n] with all array elements
2. Pairs cancel out (index ^ value = 0 when equal)
3. Missing number remains as it only appears in indices

**Complexity**: O(n) time, O(1) space

**Solution**:

```java
public int missingNumber(int[] nums) {
    int xor = nums.length;  // Start with n

    // XOR all indices and values
    for (int i = 0; i < nums.length; i++) {
        xor ^= i ^ nums[i];
    }

    return xor;
}

// Alternative: Mathematical approach (for comparison)
public int missingNumber_sum(int[] nums) {
    int n = nums.length;
    int expectedSum = n * (n + 1) / 2;
    int actualSum = 0;

    for (int num : nums) {
        actualSum += num;
    }

    return expectedSum - actualSum;
}
```

**Key Points**:
- **XOR approach is overflow-safe** (no risk of integer overflow unlike sum)
- Start with `nums.length` to include n in the XOR
- Each matching index-value pair cancels out: `i ^ nums[i] = 0` when `i == nums[i]`
- Missing number only appears in index range, so it remains
- Mathematical proof: `(0^1^...^n) ^ (nums[0]^...^nums[n-1]) = missing`

---

## Key Takeaways

1. **XOR Properties**: `a ^ a = 0`, `a ^ 0 = a`, commutative, associative
2. **Self-Canceling**: XOR pairs cancel out, leaving unique elements
3. **Rightmost Bit**: Use `x & -x` to isolate rightmost set bit
4. **Prefix XOR**: Enables O(1) range XOR queries after O(n) preprocessing
5. **Space Efficiency**: Most XOR solutions use O(1) space
6. **Multiple Appearances**: Use bit tracking (ones/twos) for 3+ appearances
7. **Two Unique**: Partition by differentiating bit to separate two unique numbers

---

> **[← Back to Overview](../README.md)** | **[Modified Binary Search ←](../binary-search/Notes.md)**
