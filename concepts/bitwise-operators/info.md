# Java Bitwise Operators Concepts

## What is Bitwise Operators?

**Type**: Primitive operators that work on individual bits
**Level**: Operates at bit-level (binary representation)
**Language**: Built into Java language (not a class/interface)

**Key Characteristics**:
- Work on integer types: `byte`, `short`, `int`, `long` ✅
- Operate on binary representation of numbers
- **Extremely fast** - O(1) constant time operations ✅
- Used for low-level programming, optimization, and bit manipulation
- Essential for many interview problems (finding duplicates, missing numbers, etc.)
- **Not applicable to floating-point types** (`float`, `double`) ❌

---

## Time Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **AND (`&`)** | O(1) | Bitwise AND |
| **OR (`\|`)** | O(1) | Bitwise OR |
| **XOR (`^`)** | O(1) | Bitwise exclusive OR |
| **NOT (`~`)** | O(1) | Bitwise complement |
| **Left Shift (`<<`)** | O(1) | Shift bits left |
| **Right Shift (`>>`)** | O(1) | Signed right shift |
| **Unsigned Right Shift (`>>>`)** | O(1) | Unsigned right shift |

**Space**: O(1) - All bitwise operations use constant space

---

## Common Operations & Methods

| Operator | Symbol | Description | Example | Result |
|----------|--------|-------------|---------|--------|
| **AND** | `&` | Returns 1 if both bits are 1 | `5 & 3` (101 & 011) | 1 (001) |
| **OR** | `\|` | Returns 1 if at least one bit is 1 | `5 \| 3` (101 \| 011) | 7 (111) |
| **XOR** | `^` | Returns 1 if bits are different | `5 ^ 3` (101 ^ 011) | 6 (110) |
| **NOT (Complement)** | `~` | **Unary operator**: Inverts all bits (`~n = -(n+1)`) | `~5` | -6 |
| **Left Shift** | `<<` | Shifts bits left, fills with 0s | `5 << 1` (101 << 1) | 10 (1010) |
| **Signed Right Shift** | `>>` | Shifts bits right, preserves sign | `5 >> 1` (101 >> 1) | 2 (010) |
| **Unsigned Right Shift** | `>>>` | Shifts bits right, fills with 0s | `-5 >>> 1` | Large positive number |

### Bitwise Complement (~) - Detailed Explanation

This operator is a **unary operator**, denoted by `~`. It inverts all the bits of the given number (0 becomes 1 and 1 becomes 0).

**Important**: In Java, `int` is a **32-bit signed integer**.

**Example:**
```
a = 5

32-bit binary representation of 5:
00000000 00000000 00000000 00000101

After applying ~a (bitwise complement):
11111111 11111111 11111111 11111010

In decimal, this value is -6.
```

This is because in Java:
```
~N = -(N + 1)

So:
~5 = -(5 + 1) = -6
```

### Key Properties

```java
// NOT Properties
~n = -(n + 1)      // Formula: ~5 = -6, ~0 = -1, ~(-1) = 0

// XOR Properties (most useful for interviews)
a ^ a = 0          // Self-canceling
a ^ 0 = a          // Identity
a ^ b = b ^ a      // Commutative
(a ^ b) ^ c = a ^ (b ^ c)  // Associative

// AND Properties
a & 0 = 0          // Zero property
a & a = a          // Identity
a & -1 = a         // All bits set

// OR Properties
a | 0 = a          // Identity
a | a = a          // Idempotent
a | -1 = -1        // All bits set

// Shift Properties
a << b = a * (2 to the power b)   // Left shift multiplies by 2^b
a >> b = a / (2 to the power b)   // Right shift divides by 2^b (for positive numbers)
```

---

## Basic Operations

### Operator Examples

```java
// AND: Both bits must be 1
int a = 5;    // Binary: 0101
int b = 3;    // Binary: 0011
int result = a & b;  // Result: 1 (0001)

// OR: At least one bit must be 1
result = a | b;  // Result: 7 (0111)

// XOR: Bits must be different
result = a ^ b;  // Result: 6 (0110)


// Left Shift: Multiply by 2
result = 5 << 1;  // Result: 10 (multiply by 2)
result = 5 << 2;  // Result: 20 (multiply by 4)

// Signed Right Shift: Divide by 2 (preserves sign)
result = 8 >> 1;   // Result: 4 (divide by 2)
result = -8 >> 1;  // Result: -4 (sign preserved)

// Unsigned Right Shift: Divide by 2 (fills with 0s)
result = -8 >>> 1; // Result: 2147483644 (large positive)
```

### Checking, Setting, Clearing, and Toggling Bits

```java
// Check if bit at position i is set
boolean isBitSet(int num, int i) {
    return (num & (1 << i)) != 0;
}

// Set bit at position i
int setBit(int num, int i) {
    return num | (1 << i);
}

// Clear bit at position i
int clearBit(int num, int i) {
    return num & ~(1 << i);
}

// Toggle bit at position i
int toggleBit(int num, int i) {
    return num ^ (1 << i);
}

// Clear all bits from MSB to i (inclusive)
int clearBitsMSBtoI(int num, int i) {
    int mask = (1 << i) - 1;
    return num & mask;
}

// Clear all bits from i to 0 (inclusive)
int clearBitsIto0(int num, int i) {
    int mask = ~((1 << (i + 1)) - 1);
    return num & mask;
}

// Update bit at position i to value
int updateBit(int num, int i, boolean bitValue) {
    int value = bitValue ? 1 : 0;
    int mask = ~(1 << i);
    return (num & mask) | (value << i);
}
```

---

## Comparison with Similar Structures

| Feature | Bitwise (`&`, `\|`) | Logical (`&&`, `\|\|`) |
|---------|-------------------|----------------------|
| **Operates on** | Integer bits | Boolean expressions |
| **Short-circuit** | No ❌ | Yes ✅ |
| **Return type** | Integer | boolean |
| **Example** | `5 & 3 = 1` | `true && false = false` |

```java
if (x != 0 && y / x > 5) { }  // ✅ Safe: && short-circuits, avoids division by zero
if (x != 0 & y / x > 5) { }   // ❌ Unsafe: & always evaluates both sides
```

---

## Common Patterns & Use Cases

### Pattern 1: Check if Number is Power of 2 ⭐

**Use Case**: Determine if a number is a power of 2

```java
public boolean isPowerOfTwo(int n) {
    // Power of 2 has only one bit set
    // n = 8:  1000, n-1 = 7:  0111
    // n & (n-1) clears the lowest set bit
    return n > 0 && (n & (n - 1)) == 0;
}
```

**Complexity**: O(1) time, O(1) space

**Why it works**: Powers of 2 have exactly one bit set. `n & (n-1)` clears the lowest bit, so result is 0 only for powers of 2.

---

### Pattern 2: Count Number of Set Bits (Hamming Weight)

**Use Case**: Count how many 1s are in binary representation

```java
public int hammingWeight(int n) {
    int count = 0;
    while (n != 0) {
        n &= (n - 1);  // Clear lowest set bit
        count++;
    }
    return count;
}

// Alternative: Brian Kernighan's Algorithm
public int countSetBits(int n) {
    int count = 0;
    while (n != 0) {
        count += n & 1;  // Check last bit
        n >>>= 1;        // Unsigned right shift
    }
    return count;
}
```

**Complexity**: O(k) time where k = number of set bits, O(1) space

---

### Pattern 3: Get Rightmost Set Bit

**Use Case**: Isolate the rightmost 1 bit

```java
public int getRightmostSetBit(int n) {
    // n = 12:     1100
    // -n:         0100 (two's complement)
    // n & -n:     0100 (isolates rightmost 1)
    return n & -n;
}

// Example: 12 & -12 = 1100 & 0100 = 0100 = 4
```

**Complexity**: O(1) time, O(1) space

**Why it works**: Two's complement (`-n`) flips all bits and adds 1, making only the rightmost set bit match.

---

### Pattern 4: Find Missing Number (XOR) ⭐

**Use Case**: Find missing number in array [0, n]

```java
public int missingNumber(int[] nums) {
    int xor = nums.length;

    for (int i = 0; i < nums.length; i++) {
        xor ^= i ^ nums[i];
    }

    return xor;
}
```

**Complexity**: O(n) time, O(1) space

**Why it works**: XOR of duplicate numbers cancels out (a ^ a = 0), leaving only the missing number.

---

### Pattern 5: Swap Two Numbers

**Use Case**: Swap without temporary variable

```java
// Swap array elements using XOR
public void swapArrayElements(int[] arr, int i, int j) {
    if (i != j) {  // Must check: same index would zero out the value
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];  // arr[j] = (arr[i] ^ arr[j]) ^ arr[j] = arr[i]
        arr[i] = arr[i] ^ arr[j];  // arr[i] = (arr[i] ^ arr[j]) ^ arr[i] = arr[j]
    }
}

// Local variable swap (demonstrates XOR properties)
int a = 5, b = 10;
a = a ^ b;
b = a ^ b;  // b = (a ^ b) ^ b = a (now b = 5)
a = a ^ b;  // a = (a ^ b) ^ a = b (now a = 10)
```

**Complexity**: O(1) time, O(1) space

**Note**: Rarely used in practice (use temp variable for clarity). **Important**: In Java, parameters are pass-by-value, so swapping parameters doesn't affect original variables. Use this with array elements or local variables.

---

### Pattern 6: Bit Masking ⭐

**Use Case**: Extract, set, clear, or toggle multiple bits at once; work with flags and permissions

**What is a Mask?** A mask is a bit pattern used to select or manipulate specific bits in a number. It acts as a filter to isolate, modify, or check certain bits while leaving others unchanged.

**Common Mask Operations**:

```java
// 1. Create masks
int mask = (1 << k) - 1;           // k rightmost bits set (e.g., k=3: 0111 = 7)
int mask = 0xFF;                   // Lower 8 bits set (11111111)
int mask = 0xF0;                   // Bits 4-7 set (11110000)
int mask = (1 << 5) | (1 << 3);    // Bits 3 and 5 set (00101000)

// 2. Extract bits using mask
int lower4Bits = num & 0xF;        // Extract lower 4 bits
int extractBits = (num & mask) >> shift;  // Extract specific bits and shift

// 3. Set bits using mask (OR)
num |= mask;                       // Set all bits in mask to 1

// 4. Clear bits using mask (AND with complement)
num &= ~mask;                      // Clear all bits in mask to 0

// 5. Toggle bits using mask (XOR)
num ^= mask;                       // Flip all bits in mask

// 6. Check if bits are set
boolean allSet = (num & mask) == mask;     // All mask bits are set
boolean anySet = (num & mask) != 0;        // At least one mask bit is set
boolean noneSet = (num & mask) == 0;       // No mask bits are set
```

**Practical Example: File Permissions (Unix-style)**

```java
// Permission bits: rwx rwx rwx (owner, group, others)
final int OWNER_READ    = 1 << 8;  // 0400 octal
final int OWNER_WRITE   = 1 << 7;  // 0200 octal
final int OWNER_EXECUTE = 1 << 6;  // 0100 octal
final int GROUP_READ    = 1 << 5;  // 0040 octal
final int GROUP_WRITE   = 1 << 4;  // 0020 octal
final int GROUP_EXECUTE = 1 << 3;  // 0010 octal

int permissions = 0;

// Set owner read/write permissions
permissions |= (OWNER_READ | OWNER_WRITE);

// Check if owner has write permission
boolean canWrite = (permissions & OWNER_WRITE) != 0;

// Remove group write permission
permissions &= ~GROUP_WRITE;

// Toggle execute permission for owner
permissions ^= OWNER_EXECUTE;
```

**Color Masking Example (RGB)**

```java
// Extract RGB components from 32-bit color (0xAARRGGBB)
int color = 0xFF3A5F7C;  // Format: AARRGGBB

int alpha = (color >> 24) & 0xFF;  // Extract alpha (shift right 24, mask 8 bits)
int red   = (color >> 16) & 0xFF;  // Extract red
int green = (color >> 8)  & 0xFF;  // Extract green
int blue  = color & 0xFF;          // Extract blue

// Construct color from components
int newColor = (alpha << 24) | (red << 16) | (green << 8) | blue;

// Set alpha to fully opaque
color = (color & 0x00FFFFFF) | 0xFF000000;

// Clear red component
color = color & 0xFF00FFFF;
```

**Complexity**: O(1) time, O(1) space

**Key Insights**:
- **Extract**: Use `&` with mask, then shift if needed
- **Set**: Use `|` with mask (turns bits ON)
- **Clear**: Use `&` with `~mask` (turns bits OFF)
- **Toggle**: Use `^` with mask (flips bits)
- **Check all set**: `(num & mask) == mask`
- **Check any set**: `(num & mask) != 0`

---

## Common Gotchas

### 1. Operator Precedence

**❌ WRONG**:
```java
if (flags & MASK == MASK) {  // Wrong: == evaluated first
    // This checks if (flags & false) which is wrong
}
```

**✅ CORRECT**:
```java
if ((flags & MASK) == MASK) {  // Correct: & evaluated first
    // Parentheses ensure correct order
}
```

**Why**: Comparison operators (`==`, `!=`) have higher precedence than bitwise operators.

---

### 2. Signed vs Unsigned Right Shift

**❌ WRONG**:
```java
int negative = -8;
int result = negative >> 1;  // Result: -4 (sign preserved)
// If you wanted positive: Wrong operator!
```

**✅ CORRECT**:
```java
int negative = -8;
int result = negative >>> 1;  // Result: 2147483644 (unsigned)
// Use >>> for logical shift (fills with 0s)
```

**Why**: `>>` preserves sign bit (arithmetic shift), `>>>` fills with 0s (logical shift).

---

### 3. NOT Operator with Equality

**❌ WRONG**:
```java
if (~flags == MASK) {  // Unexpected result due to two's complement
    // May not work as expected
}
```

**✅ CORRECT**:
```java
if ((~flags & MASK) == MASK) {  // Check specific bits
    // Or use explicit comparison
}
```

**Why**: `~` flips all bits including sign bit, which may produce unexpected negative numbers.

---

### 4. Integer Overflow with Shifts

**❌ WRONG**:
```java
int result = 1 << 31;   // Result: -2147483648 (overflow to negative)
int result2 = 1 << 32;  // Result: 1 (shift amount is mod 32)
```

**✅ CORRECT**:
```java
long result = 1L << 31;  // Use long for large shifts
int result2 = 1 << (k % 32);  // Be aware of modulo behavior
```

**Why**: Shift amounts are taken modulo the bit width (32 for int, 64 for long).

---

## Interview Tips

### When to Use Bitwise Operators

✅ Finding missing/duplicate numbers (XOR pattern)
✅ Checking if number is power of 2
✅ Counting set bits
✅ Space optimization (bit flags)
✅ Fast multiplication/division by powers of 2
✅ Subset generation and bitmasks

### When NOT to Use

❌ Regular arithmetic when clarity is important
❌ Operations on floating-point numbers
❌ When code readability suffers significantly

### Remember

- **XOR properties**: `a ^ a = 0`, `a ^ 0 = a` (self-canceling pairs)
- **Check power of 2**: `n & (n-1) == 0`
- **Isolate rightmost bit**: `n & -n`
- **Clear rightmost bit**: `n & (n-1)`
- **Parentheses matter**: Bitwise operators have low precedence
- **Use `>>>` for unsigned**: `>>` preserves sign, `>>>` fills with 0s
- **Left shift = multiply by power of 2**: `n << k` multiplies by 2^k
- **Right shift = divide by power of 2**: `n >> k` divides by 2^k (for positive numbers)

### Common Interview Problems

1. **Single Number** - XOR all elements (pairs cancel out)
2. **Missing Number** - XOR indices and values
3. **Power of Two** - `n & (n-1) == 0`
4. **Hamming Distance** - Count set bits in `a ^ b`
5. **Number of 1 Bits** - Use `n & (n-1)` repeatedly
6. **Reverse Bits** - Process bit by bit
7. **Find Two Unique Numbers** - Partition by differentiating bit

---

**Key Insight**: XOR is your best friend for interview problems! Remember `a ^ a = 0` and `a ^ 0 = a` - these properties make XOR perfect for finding missing or unique elements. For bit manipulation, always think about masks and the rightmost bit pattern `n & (n-1)`.
