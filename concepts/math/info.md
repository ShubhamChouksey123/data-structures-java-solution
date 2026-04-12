# Java Math Concepts

## What is Java Math?

**Type**: Utility class with static methods
**Package**: `java.lang.Math`
**Implementation**: Native methods for performance-critical operations

**Key Characteristics**:
- **All methods are static** - No instantiation needed ✅
- **Thread-safe** - All methods are stateless ✅
- **Returns primitive types** - No autoboxing overhead
- **Handles edge cases** - NaN, Infinity, overflow
- **Final class** - Cannot be extended

---

## Time & Space Complexity

| Operation | Method | Return Type | Complexity | Notes |
|-----------|--------|-------------|------------|-------|
| **Minimum** | `min(a, b)` | int/long/float/double | O(1) | Returns smaller value |
| **Maximum** | `max(a, b)` | int/long/float/double | O(1) | Returns larger value |
| **Absolute** | `abs(x)` | int/long/float/double | O(1) | Always non-negative |
| **Power** | `pow(x, y)` | double | O(log y) | x raised to y, exponentiation by squaring |
| **Square Root** | `sqrt(x)` | double | O(1) | Non-negative result, hardware-accelerated |
| **Floor** | `floor(x)` | double | O(1) | Largest integer ≤ x |
| **Ceiling** | `ceil(x)` | double | O(1) | Smallest integer ≥ x |
| **Round** | `round(x)` | long/int | O(1) | Round to nearest integer |
| **Random** | `random()` | double | O(1) | Generate random in [0.0, 1.0) |
| **Sign** | `signum(x)` | double/float | O(1) | Returns -1, 0, or 1 |
| **Log** | `log(x)` | double | O(1) | Natural logarithm |
| **Log10** | `log10(x)` | double | O(1) | Base-10 logarithm |
| **Exp** | `exp(x)` | double | O(1) | Exponential e^x |
| **Floor Division** | `floorDiv(x, y)` | int/long | O(1) | Rounds toward negative infinity |
| **Floor Modulo** | `floorMod(x, y)` | int/long | O(1) | Always non-negative modulo |

**Space**: O(1) - All operations use constant space

---

## Basic Operations

```java
// Min and Max
int min = Math.min(5, 10);           // 5
int max = Math.max(5, 10);           // 10

// Absolute value
int abs1 = Math.abs(-5);             // 5
int abs2 = Math.abs(5);              // 5

// Power and Square Root
double pow = Math.pow(2, 3);         // 8.0
double sqrt = Math.sqrt(16);         // 4.0

// Rounding operations
double floor = Math.floor(4.7);      // 4.0
double ceil = Math.ceil(4.1);        // 5.0
long round = Math.round(4.5);        // 5

// Random number [0.0, 1.0)
double random = Math.random();

// Random integer in range [min, max]
int randomInt = (int)(Math.random() * (max - min + 1)) + min;

// Sign of number
double sign = Math.signum(-5.0);     // -1.0

// Logarithms
double ln = Math.log(Math.E);        // 1.0 (natural log)
double log10 = Math.log10(100);      // 2.0

// Exponential
double exp = Math.exp(1);            // 2.718... (e^1)
```

---

## Common Patterns & Use Cases

### Pattern 2: Integer Overflow Safe Operations ⭐

**Use Case**: Prevent overflow in calculations

```java
// Check if multiplication overflows
public boolean willOverflow(int a, int b) {
    if (a == 0 || b == 0) return false;
    return Math.abs(a) > Integer.MAX_VALUE / Math.abs(b);
}

// Use Math.addExact, multiplyExact for overflow detection
try {
    int result = Math.addExact(Integer.MAX_VALUE, 1);  // Throws
} catch (ArithmeticException e) {
    // Handle overflow
}
```

**Complexity**: O(1) time, O(1) space

---

### Pattern 6: GCD and LCM

**Use Case**: Find Greatest Common Divisor and Least Common Multiple

```java
// GCD using Euclidean algorithm
public int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
}

// LCM using GCD
public int lcm(int a, int b) {
    return Math.abs(a * b) / gcd(a, b);
}
```

**Complexity**: O(log min(a, b)) time, O(log min(a, b)) space (recursive)

---

## Common Gotchas

### 1. Integer Division Truncation

**❌ WRONG**:
```java
double result = 5 / 2;  // result = 2.0 (integer division!)
```

**✅ CORRECT**:
```java
double result = 5.0 / 2;           // 2.5
double result = (double) 5 / 2;    // 2.5
double result = 1.0 * 5 / 2;       // 2.5
```

---

### 2. Math.abs() with Integer.MIN_VALUE

**❌ WRONG**:
```java
int abs = Math.abs(Integer.MIN_VALUE);  // Still negative! (-2147483648)
```

**✅ CORRECT**:
```java
// Check for MIN_VALUE
if (n == Integer.MIN_VALUE) {
    // Handle specially
}

// Or use long
long abs = Math.abs((long) Integer.MIN_VALUE);  // 2147483648
```

**Why**: `Integer.MIN_VALUE = -2147483648`, but `Integer.MAX_VALUE = 2147483647`. The absolute value cannot fit in an int.

---

### 3. Math.pow() Returns Double

**❌ WRONG**:
```java
int result = Math.pow(2, 10);  // Compile error
```

**✅ CORRECT**:
```java
int result = (int) Math.pow(2, 10);  // Cast to int

// Better for integer powers: Use bit shifting
int result = 1 << 10;  // 2^10 = 1024 (faster!)
```

---

### 4. Floating Point Comparison

**❌ WRONG**:
```java
if (Math.sqrt(4) == 2.0) {  // Risky with floating point
    // May fail due to precision
}
```

**✅ CORRECT**:
```java
double epsilon = 1e-9;
if (Math.abs(Math.sqrt(4) - 2.0) < epsilon) {
    // Safe comparison
}
```

---

### 5. Math.random() Range Confusion

**❌ WRONG**:
```java
// This gives [0, max) not [0, max]
int random = (int)(Math.random() * max);
```

**✅ CORRECT**:
```java
// For [0, max] inclusive
int random = (int)(Math.random() * (max + 1));

// For [min, max] inclusive
int random = (int)(Math.random() * (max - min + 1)) + min;
```

---

### 6. FloorDiv vs Regular Division

**❌ WRONG**:
```java
int result = -5 / 2;           // -2 (rounds toward zero)
```

**✅ CORRECT**:
```java
int result = Math.floorDiv(-5, 2);  // -3 (rounds toward negative infinity)

// Similarly for modulo
int mod1 = -5 % 2;                  // -1
int mod2 = Math.floorMod(-5, 2);    // 1 (always non-negative)
```

---

## Interview Tips

### When to Use Math Functions
✅ Finding min/max in comparisons
✅ Calculating distances (Euclidean, Manhattan)
✅ Power calculations (prefer bit shifting for 2^n)
✅ GCD/LCM calculations
✅ Rounding operations (floor, ceil, round)
❌ Simple operations where bit manipulation is faster
❌ When you need exact control (use BigInteger/BigDecimal)

### Remember
- `Math.pow()` returns double, cast if needed
- `Math.abs(Integer.MIN_VALUE)` stays negative
- Use `1 << n` instead of `Math.pow(2, n)` for powers of 2
- `Math.floorDiv()` and `Math.floorMod()` for consistent negative handling
- `Math.random()` returns [0.0, 1.0), not [0, 1]
- For overflow safety: `Math.addExact()`, `Math.multiplyExact()`

### Common Interview Scenarios
- **Min/Max**: Sliding window, greedy algorithms
- **Abs**: Distance calculations, difference problems
- **Pow**: Exponentiation, modular arithmetic
- **Sqrt**: Binary search, geometry problems
- **Floor/Ceil**: Pagination, bucket calculations
- **GCD/LCM**: Fraction problems, cycle detection

---



