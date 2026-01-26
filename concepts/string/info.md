# Java String Concepts

## Core Characteristics

### Immutability

**Key Point**: Strings in Java are **immutable** - once created, their content cannot be changed.

**What happens when you "modify" a string**:
```
String s = "hello";
s = s + " world";  // Creates NEW string object, doesn't modify original
```

**Benefits**:
- Thread-safe by default
- Can be shared safely
- Hashcode caching (efficient for HashMap/HashSet)
- Security (can't be modified after validation)

**Drawback**:
- Concatenation in loops creates many objects → use StringBuilder

---

## String Pool (String Interning)

**Concept**: JVM maintains a pool of string literals in heap memory for reuse.

### String Creation Methods

| Method | Location | Pool? | Example |
|--------|----------|-------|---------|
| **Literal** | String Pool | Yes | `String s = "hello";` |
| **new keyword** | Heap | No | `String s = new String("hello");` |
| **intern()** | Moves to Pool | Yes | `s.intern();` |

**Comparison**:
```
String s1 = "hello";              // Pool
String s2 = "hello";              // Pool (reuses s1)
String s3 = new String("hello");  // Heap (new object)

s1 == s2          // true (same reference in pool)
s1 == s3          // false (different locations)
s1.equals(s3)     // true (same content)
```

**Key Rules**:
- Use `==` for reference comparison
- Use `.equals()` for content comparison
- Always prefer string literals for pooling benefits

---

## String vs StringBuilder vs StringBuffer

| Feature | String | StringBuilder | StringBuffer |
|---------|--------|---------------|--------------|
| **Mutability** | Immutable | Mutable | Mutable |
| **Thread-Safe** | Yes | No | Yes (synchronized) |
| **Performance** | Slow for concat | Fast | Slower than StringBuilder |
| **Use Case** | Fixed strings | Single thread concat | Multi-thread concat |

### When to Use

**String**:
- Value doesn't change
- Few concatenations (1-2)

**StringBuilder**:
- Loop concatenations
- Building strings dynamically
- Single-threaded environment
- **Most common choice**

**StringBuffer**:
- Multi-threaded environments
- Rarely needed in modern code

**Performance Example**:
```
// BAD: O(n²) - creates n string objects
String result = "";
for (int i = 0; i < n; i++) {
    result += i;  // Creates new string each time
}

// GOOD: O(n) - modifies same object
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) {
    sb.append(i);  // Modifies in-place
}
String result = sb.toString();
```

### StringBuilder Operations

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Append (add at end)** | `sb.append(str)` | O(1) amortized | Most efficient, adds to end |
| **Prepend (add at start)** | `sb.insert(0, str)` | O(n) | Shifts all chars, slower |
| **Insert at position** | `sb.insert(i, str)` | O(n) | Shifts chars from index i |
| **Delete range** | `sb.delete(start, end)` | O(n) | Shifts remaining chars |
| **Reverse** | `sb.reverse()` | O(n) | In-place reversal |
| **Replace range** | `sb.replace(start, end, str)` | O(n) | Delete + insert |

**Key Points**:
- **Append is O(1)** - just adds to the end (amortized due to capacity doubling)
- **Prepend/Insert is O(n)** - must shift all existing characters
- If you need many prepends, consider:
  1. Append in reverse order, then reverse the entire string
  2. Use a Deque or LinkedList for building, then convert to string

**Prepend Example**:
```
StringBuilder sb = new StringBuilder("world");
sb.insert(0, "hello ");  // "hello world" (O(n) - shifts "world")
```

**Better approach for multiple prepends**:
```
// Instead of multiple O(n) prepends:
StringBuilder sb = new StringBuilder();
sb.insert(0, "c");  // O(n)
sb.insert(0, "b");  // O(n)
sb.insert(0, "a");  // O(n)

// Do this (append + reverse):
StringBuilder sb = new StringBuilder();
sb.append("c");  // O(1)
sb.append("b");  // O(1)
sb.append("a");  // O(1)
sb.reverse();    // O(n) once at end
// Result: "abc"
```

---

## Important String Methods

### Complexity Reference

| Method | Complexity | Notes |
|--------|------------|-------|
| `.length()` | O(1) | Cached field |
| `.charAt(i)` | O(1) | Array access |
| `.substring(i, j)` | O(n) | Creates new string |
| `.indexOf(char)` | O(n) | Linear search |
| `.equals(str)` | O(n) | Compares each char |
| `.split(regex)` | O(n) | Creates array |
| `.replace(old, new)` | O(n) | Creates new string |
| `.trim()` | O(n) | Creates new string |
| `.toLowerCase()` | O(n) | Creates new string |
| `Integer.toBinaryString(n)` | O(log n) | Converts to binary string representation |

### Common Operations

**Substring**:
```
String s = "hello";
s.substring(1, 4)  // "ell" (start inclusive, end exclusive)
s.substring(2)     // "llo" (from index to end)
```

**Split**:
```
"a,b,c".split(",")        // ["a", "b", "c"]
"a,,b".split(",")         // ["a", "", "b"]
"a,b,c".split(",", 2)     // ["a", "b,c"] (limit splits)
```

**Replace**:
```
s.replace("old", "new")      // All occurrences
s.replaceFirst("old", "new") // First only
s.replaceAll("[0-9]", "X")   // Regex-based
```

**Case Conversion**:
```
s.toLowerCase()     // All lowercase
s.toUpperCase()     // All uppercase
```

**Trimming**:
```
s.trim()         // Removes leading/trailing whitespace
s.strip()        // Unicode-aware trim (Java 11+)
s.stripLeading() // Remove only leading
s.stripTrailing() // Remove only trailing
```

---

## Character Array Conversion

### String ↔ char[]

**String to char array**:
```
String s = "hello";
char[] arr = s.toCharArray();  // ['h', 'e', 'l', 'l', 'o']
```

**char array to String**:
```
char[] arr = {'h', 'i'};
String s = new String(arr);           // "hi"
String s = String.valueOf(arr);       // "hi"
```

**Why use char array?**
- Strings are immutable, char[] is mutable
- Useful for in-place modifications
- Common in array/string manipulation problems

**Pattern**:
```
1. Convert string to char array
2. Modify array in-place
3. Convert back to string if needed
```

---

## String Formatting

### Methods

| Method | Use Case | Example |
|--------|----------|---------|
| `String.format()` | Template-based | `String.format("Hello %s", name)` |
| `printf()` | Direct output | `System.out.printf("%d", num)` |
| `StringBuilder` | Dynamic building | `sb.append(x).append(y)` |

### Format Specifiers

| Specifier | Type | Example |
|-----------|------|---------|
| `%s` | String | `String.format("%s", "hello")` → "hello" |
| `%d` | Integer | `String.format("%d", 42)` → "42" |
| `%f` | Float | `String.format("%.2f", 3.14159)` → "3.14" |
| `%c` | Character | `String.format("%c", 'A')` → "A" |
| `%x` | Hexadecimal | `String.format("%x", 255)` → "ff" |

---

## Common Patterns & Gotchas

### 1. String Comparison

**❌ WRONG**:
```
if (s1 == s2)  // Compares references, not content
```

**✅ CORRECT**:
```
if (s1.equals(s2))           // Content comparison
if (s1.equalsIgnoreCase(s2)) // Ignore case
```

### 2. Null Handling

**Safe patterns**:
```
if (s != null && s.length() > 0)     // Check null first
if (s != null && !s.isEmpty())       // isEmpty() checks length == 0
if (s != null && !s.isBlank())       // isBlank() checks if only whitespace (Java 11+)
```

### 3. Concatenation in Loops

**❌ BAD**:
```
String result = "";
for (String s : list) {
    result += s;  // O(n²)
}
```

**✅ GOOD**:
```
StringBuilder sb = new StringBuilder();
for (String s : list) {
    sb.append(s);  // O(n)
}
String result = sb.toString();
```

### 4. String Pool Pitfall

**Surprising behavior**:
```
String s1 = "hello";
String s2 = new String("hello");
String s3 = s2.intern();

s1 == s2  // false (different locations)
s1 == s3  // true (both in pool after intern)
```

### 5. Substring Memory Leak (Java 6)

**Historical issue** (fixed in Java 7+):
- Old substring() shared char array with original
- Could prevent garbage collection
- Modern Java creates new char array

---

## Interview Tips

### Time Complexity

**Common mistakes**:
- Forgetting string operations create new objects
- Not counting substring/concat costs in loops
- Assuming `.length()` is O(n) (it's O(1))

### Space Complexity

**Remember**:
- Each string modification creates new object
- StringBuilder has O(n) space for capacity
- char[] is mutable, doesn't create copies

### Best Practices

1. **Use StringBuilder** for multiple concatenations
2. **Use .equals()** for content comparison, not `==`
3. **Check null** before calling string methods
4. **Prefer string literals** for pooling benefits
5. **Convert to char[]** for in-place modifications
6. **Use appropriate methods**:
   - `.isEmpty()` vs `.length() == 0`
   - `.isBlank()` for whitespace-only check
   - `.strip()` vs `.trim()` (Unicode handling)

---

## Common String Problems

### Pattern: Two Pointers
- Valid Palindrome
- Reverse String
- Remove Duplicates

### Pattern: Sliding Window
- Longest Substring Without Repeating Characters
- Minimum Window Substring

### Pattern: HashMap/HashSet
- Anagram Detection
- Group Anagrams
- First Unique Character

### Pattern: StringBuilder
- String Compression
- Reverse Words in String
- Integer to English Words

---

## Quick Reference

### Creating Strings

```
String s = "literal";           // Pool
String s = new String("heap");  // Heap
String s = String.valueOf(123); // Convert types
```

### Checking Properties

```
s.isEmpty()           // length == 0
s.isBlank()          // isEmpty() or only whitespace
s.startsWith("pre")  // Prefix check
s.endsWith("suf")    // Suffix check
s.contains("sub")    // Substring check
```

### Modification (returns new String)

```
s.concat("add")           // Append
s.repeat(3)              // "aaabbb" (Java 11+)
s.join(",", arr)         // Join array with delimiter
String.join("-", "a", "b") // "a-b"
```

### Searching

```
s.indexOf('c')           // First occurrence
s.lastIndexOf('c')       // Last occurrence
s.indexOf("sub")         // Substring index
s.indexOf('c', fromIdx)  // Start from index
```

---

**Remember**: Strings are immutable, thread-safe, and pooled. Use StringBuilder for performance when building strings dynamically!
