# Java Primitives vs Wrappers (Boxing/Unboxing)

## What are Primitives and Wrappers?

- **Primitives**: Basic data types built into Java language (not objects)
- **Wrappers**: Object representations of primitive types in `java.lang` package
- **Boxing**: Converting primitive to wrapper (e.g., `int` → `Integer`)
- **Unboxing**: Converting wrapper to primitive (e.g., `Integer` → `int`)

**Key Characteristics**:
- **Primitives stored on stack** - Faster access, less memory ✅
- **Wrappers stored on heap** - Object overhead, nullable ✅
- **Autoboxing/Unboxing** - Automatic conversion since Java 5 ✅
- **Wrappers are immutable** - Cannot change value after creation ✅
- **Null safety** - Primitives cannot be null, wrappers can ⚠️

---

## Time & Space Complexity

| Operation | Primitive | Wrapper | Notes |
|-----------|-----------|---------|-------|
| **Declaration** | O(1) | O(1) | Wrapper has object allocation overhead |
| **Assignment** | O(1) | O(1) | Direct vs reference assignment |
| **Comparison** | O(1) | O(1) | `==` vs `equals()` |
| **Arithmetic** | O(1) | O(1) | Unboxing happens automatically |
| **Collection Add** | N/A | O(1)* | Primitives cannot be stored in collections |

**Space Complexity**:
- **Primitive**: 1-8 bytes (depending on type)
- **Wrapper**: 16-24 bytes (object header + primitive value + padding)

| Type | Primitive Size | Wrapper Size | Memory Overhead |
|------|----------------|--------------|-----------------|
| **boolean** | 1 bit (1 byte) | ~16 bytes | 16x |
| **byte** | 1 byte | ~16 bytes | 16x |
| **char** | 2 bytes | ~16 bytes | 8x |
| **short** | 2 bytes | ~16 bytes | 8x |
| **int** | 4 bytes | ~16 bytes | 4x |
| **long** | 8 bytes | ~24 bytes | 3x |
| **float** | 4 bytes | ~16 bytes | 4x |
| **double** | 8 bytes | ~24 bytes | 3x |

---

## Primitive and Wrapper Types

| Primitive | Wrapper Class | Default Value | Range |
|-----------|---------------|---------------|-------|
| **boolean** | `Boolean` | false | true/false |
| **byte** | `Byte` | 0 | -128 to 127 |
| **char** | `Character` | '\u0000' | 0 to 65,535 |
| **short** | `Short` | 0 | -32,768 to 32,767 |
| **int** | `Integer` | 0 | -2³¹ to 2³¹-1 |
| **long** | `Long` | 0L | -2⁶³ to 2⁶³-1 |
| **float** | `Float` | 0.0f | ~±3.4E+38 |
| **double** | `Double` | 0.0d | ~±1.8E+308 |

---

## Basic Operations

```java
// Primitives
int primitive = 42;
boolean flag = true;
double decimal = 3.14;

// Wrappers
Integer wrapper = 42;
Boolean wrapperFlag = true;
Double wrapperDecimal = 3.14;

// Boxing (primitive → wrapper)
Integer boxed = Integer.valueOf(10);        // Explicit
Integer autoBoxed = 10;                     // Autoboxing (Java 5+)

// Unboxing (wrapper → primitive)
int unboxed = boxed.intValue();             // Explicit
int autoUnboxed = boxed;                    // Auto-unboxing (Java 5+)

// Null handling
Integer nullWrapper = null;                 // Valid
// int nullPrimitive = null;                // Compile error!

// Collections require wrappers
List<Integer> list = new ArrayList<>();
list.add(10);                               // Autoboxing: int → Integer
int value = list.get(0);                    // Auto-unboxing: Integer → int
```

---

## Primitives vs Wrappers Comparison

| Feature | Primitive | Wrapper |
|---------|-----------|---------|
| **Null Values** | ❌ Cannot be null | ✅ Can be null |
| **Memory** | Less (4-8 bytes) | More (16-24 bytes) |
| **Speed** | Faster | Slower (object overhead) |
| **Collections** | ❌ Not allowed | ✅ Required |
| **Generics** | ❌ Not allowed | ✅ Required |
| **Default Value** | 0, false, '\u0000' | null (if not initialized) |
| **Comparison** | Use `==` | Use `equals()` |
| **Immutability** | Mutable | Immutable |
| **Utility Methods** | ❌ None | ✅ Many (parseInt, valueOf, etc.) |

### When to Use Primitives
✅ Performance-critical code (tight loops, large arrays)
✅ When null is not needed
✅ Local variables in calculations
✅ Method parameters (when null not expected)

### When to Use Wrappers
✅ Collections (List, Set, Map)
✅ Generics (`List<Integer>`, not `List<int>`)
✅ When null has semantic meaning
✅ When utility methods needed (Integer.parseInt())
✅ Reflection and serialization

---

## Common Patterns & Use Cases

### Pattern 1: Integer Caching ⭐

**Use Case**: Understand Integer cache to avoid bugs

```java
// Integer caching: -128 to 127 are cached
Integer a = 100;
Integer b = 100;
System.out.println(a == b);        // true (same cached object)

Integer c = 200;
Integer d = 200;
System.out.println(c == d);        // false (different objects)

// Always use equals() for wrapper comparison
System.out.println(c.equals(d));   // true (value comparison)
```

**Key Insight**: Integer.valueOf() caches values -128 to 127. Outside this range, new objects are created.

**Complexity**: O(1) time, O(1) space

---

### Pattern 2: Safe Null Handling ⭐

**Use Case**: Avoid NullPointerException during auto-unboxing

```java
// Check for null before unboxing
public int safeParse(String str) {
    Integer result = parseString(str);

    // Null check before auto-unboxing
    if (result != null) {
        return result;  // Safe auto-unboxing
    }
    return 0;  // Default value
}

// Using Optional (Java 8+)
public int safeParseWithOptional(String str) {
    return Optional.ofNullable(parseString(str))
                   .orElse(0);
}
```

**Complexity**: O(1) time, O(1) space

---

### Pattern 3: Converting Between Types

**Use Case**: String ↔ Primitive/Wrapper conversions

```java
// String to primitive
int num1 = Integer.parseInt("123");           // Returns primitive
double d1 = Double.parseDouble("3.14");

// String to wrapper
Integer num2 = Integer.valueOf("123");        // Returns wrapper
Double d2 = Double.valueOf("3.14");

// Primitive/Wrapper to String
String s1 = String.valueOf(42);               // Works for both
String s2 = Integer.toString(42);             // Wrapper method
String s3 = "" + 42;                          // String concatenation

// Radix conversion
int binary = Integer.parseInt("1010", 2);     // 10 (binary to int)
int hex = Integer.parseInt("FF", 16);         // 255 (hex to int)
String hexStr = Integer.toHexString(255);     // "ff"
```

**Complexity**: O(n) where n = string length for parsing


---

### Pattern 5: Collection Operations

**Use Case**: Working with collections requires wrappers

```java
// Primitives cannot be used in collections
List<Integer> numbers = new ArrayList<>();
numbers.add(10);                              // Autoboxing
numbers.add(20);

// Stream operations
int sum = numbers.stream()
                 .mapToInt(Integer::intValue) // Unbox to IntStream
                 .sum();

// Avoid creating unnecessary wrappers in loops
int total = 0;
for (int num : numbers) {                     // Auto-unboxing
    total += num;                             // Works with primitive
}
```

**Complexity**: O(n) for iteration

---

## Common Gotchas

### 1. NullPointerException on Auto-Unboxing

**❌ WRONG**:
```java
Integer count = null;
int result = count + 1;  // NullPointerException!
```

**✅ CORRECT**:
```java
Integer count = null;
int result = (count != null ? count : 0) + 1;  // Safe

// Or use Objects utility
int result = Objects.requireNonNullElse(count, 0) + 1;
```

---

### 2. Using == for Wrapper Comparison

**❌ WRONG**:
```java
Integer a = 1000;
Integer b = 1000;
if (a == b) {  // false (different objects)
    // Never executes
}
```

**✅ CORRECT**:
```java
Integer a = 1000;
Integer b = 1000;
if (a.equals(b)) {  // true (value comparison)
    // Correct comparison
}

// Or unbox to primitive
if (a.intValue() == b.intValue()) {  // true
    // Also correct
}
```

---

### 3. Performance Issues with Unnecessary Boxing

**❌ WRONG**:
```java
// Creating millions of wrapper objects
Integer sum = 0;
for (int i = 0; i < 1_000_000; i++) {
    sum += i;  // Repeated unboxing and boxing!
}
```

**✅ CORRECT**:
```java
// Use primitive for calculations
int sum = 0;
for (int i = 0; i < 1_000_000; i++) {
    sum += i;  // No boxing overhead
}
Integer result = sum;  // Box once at the end
```

---

### 4. Wrapper Immutability Confusion

**❌ WRONG**:
```java
public void increment(Integer n) {
    n = n + 1;  // Creates new Integer, doesn't modify original
}

Integer x = 5;
increment(x);
System.out.println(x);  // Still 5!
```

**✅ CORRECT**:
```java
// Return new value or use primitive
public int increment(int n) {
    return n + 1;
}

int x = 5;
x = increment(x);  // x is now 6
```

---

### 5. Collections.sort() with Primitives

**❌ WRONG**:
```java
int[] arr = {3, 1, 2};
// Collections.sort(arr);  // Compile error!
```

**✅ CORRECT**:
```java
// For primitives, use Arrays.sort()
int[] arr = {3, 1, 2};
Arrays.sort(arr);

// For collections, use wrappers
List<Integer> list = Arrays.asList(3, 1, 2);
Collections.sort(list);
```

---

### 6. Integer Overflow with Wrappers

**❌ WRONG**:
```java
Integer a = Integer.MAX_VALUE;
Integer b = a + 1;  // Overflow! b = -2147483648
```

**✅ CORRECT**:
```java
// Check before operation
if (a == Integer.MAX_VALUE) {
    // Handle overflow
}

// Or use larger type
Long result = (long) a + 1;

// Or use Math.addExact() for exception
try {
    int result = Math.addExact(a, 1);
} catch (ArithmeticException e) {
    // Handle overflow
}
```

---

## Interview Tips

### When to Use Primitives vs Wrappers
- ✅ **Primitives**: Performance-critical loops, local variables, method parameters
- ✅ **Wrappers**: Collections, generics, when null is meaningful, utility methods
- ❌ **Avoid**: Unnecessary boxing/unboxing in tight loops
- ❌ **Avoid**: Using `==` for wrapper comparison

### Remember
- Integer caches -128 to 127 (use `equals()` for comparison)
- Auto-unboxing null wrapper throws NullPointerException
- Wrappers are immutable (cannot modify, creates new object)
- Primitives cannot be used in generics or collections
- Wrapper memory overhead: 4-16x larger than primitives
- Use `Integer.valueOf()` instead of `new Integer()` (deprecated)
- `parseInt()` returns primitive, `valueOf()` returns wrapper

### Common Interview Scenarios
- **Collections**: Must use wrappers (`List<Integer>`)
- **Null checks**: Wrappers allow null, primitives don't
- **Performance**: Primitives faster in tight loops
- **Caching**: Integer cache affects `==` comparison
- **Parsing**: `parseInt()` vs `valueOf()` differences
- **Comparison**: `==` vs `equals()` for wrappers



---

**Key Insight**: Always use `equals()` for wrapper comparison, never `==`! Integer caches -128 to 127, but outside this range new objects are created. Watch for NullPointerException when auto-unboxing null wrappers!
