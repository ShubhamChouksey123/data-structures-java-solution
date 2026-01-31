# Java String Concepts

## 2. What is String?

**Type**: Immutable sequence of characters
**Package**: `java.lang.String`
**Internal**: char array (Java 8) or byte array (Java 9+)

**Key Characteristics**:
- **Immutable** - cannot be modified after creation
- **Thread-safe** - safe to share between threads
- **String pool** - literals stored in special memory pool
- **Cached hashcode** - efficient for HashMap/HashSet
- **Final class** - cannot be extended

---

## 3. Time & Space Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **Access charAt(i)** | O(1) | Direct array access |
| **Length** | O(1) | Cached field |
| **Substring** | O(n) | Creates new string |
| **Concat** | O(n + m) | Creates new string |
| **indexOf** | O(n) | Linear search |
| **equals** | O(n) | Compares each char |
| **split** | O(n) | Creates array |
| **replace** | O(n) | Creates new string |
| **toLowerCase/toUpperCase** | O(n) | Creates new string |

**Space**: O(n) where n = string length

**Important**: Every modification creates a new String object

---

## 4. Common Operations & Methods

### String Methods

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Get char at index** | `s.charAt(i)` | O(1) | 0-indexed |
| **Length** | `s.length()` | O(1) | Cached field |
| **Substring** | `s.substring(i, j)` | O(n) | End exclusive |
| **Contains** | `s.contains(sub)` | O(n) | Substring check |
| **Equals** | `s.equals(str)` | O(n) | Content comparison |
| **Equals ignore case** | `s.equalsIgnoreCase(str)` | O(n) | Case-insensitive |
| **Starts with** | `s.startsWith(pre)` | O(m) | m = prefix length |
| **Ends with** | `s.endsWith(suf)` | O(m) | m = suffix length |
| **Index of** | `s.indexOf(ch)` | O(n) | First occurrence |
| **Last index of** | `s.lastIndexOf(ch)` | O(n) | Last occurrence |
| **Split** | `s.split(regex)` | O(n) | Returns array |
| **Replace** | `s.replace(old, new)` | O(n) | All occurrences |
| **To lowercase** | `s.toLowerCase()` | O(n) | Creates new string |
| **To uppercase** | `s.toUpperCase()` | O(n) | Creates new string |
| **Trim** | `s.trim()` | O(n) | Remove whitespace |
| **Strip** | `s.strip()` | O(n) | Unicode-aware trim (Java 11+) |
| **Is empty** | `s.isEmpty()` | O(1) | length == 0 |
| **Is blank** | `s.isBlank()` | O(n) | Only whitespace (Java 11+) |
| **To char array** | `s.toCharArray()` | O(n) | Creates char[] |

### StringBuilder Methods

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Append** | `sb.append(str)` | O(1) amortized | Add at end |
| **Insert** | `sb.insert(i, str)` | O(n) | Shifts elements |
| **Delete** | `sb.delete(start, end)` | O(n) | Shifts elements |
| **Reverse** | `sb.reverse()` | O(n) | In-place |
| **To string** | `sb.toString()` | O(n) | Creates string |

---

## 5. Core Characteristics/Creation

### String Creation

```java
// Literal (stored in String Pool)
String s = "hello";

// new keyword (Heap, not pooled)
String s = new String("hello");

// intern() - move to pool
String s = new String("hello").intern();

// From char array
char[] arr = {'h', 'i'};
String s = new String(arr);
String s = String.valueOf(arr);

// From other types
String s = String.valueOf(123);
```

### String Pool

```java
String s1 = "hello";              // Pool
String s2 = "hello";              // Pool (reuses s1)
String s3 = new String("hello");  // Heap (new object)

s1 == s2          // true (same reference)
s1 == s3          // false (different locations)
s1.equals(s3)     // true (same content)
```

**Key Rules**:
- Use `==` for reference comparison
- Use `.equals()` for content comparison
- Prefer string literals for pooling benefits

### Immutability

```java
String s = "hello";
s = s + " world";  // Creates NEW string, doesn't modify original
```

**Benefits**: Thread-safe, hashcode caching, security
**Drawback**: Concatenation in loops inefficient

---

## 6. Comparison with Similar Structures

| Feature | String | StringBuilder | StringBuffer |
|---------|--------|---------------|--------------|
| **Mutability** | Immutable | Mutable ⭐ | Mutable |
| **Thread-Safe** | Yes | No | Yes (synchronized) |
| **Performance** | Slow for concat | Fast ⭐ | Slower |
| **Use Case** | Fixed strings | Single thread concat | Multi-thread concat |

**When to Use String**: Fixed values, few concatenations (1-2)

**When to Use StringBuilder**: Loop concatenations, dynamic building (95% of cases)

**When to Use StringBuffer**: Multi-threaded (rarely needed)

**Performance Example**:
```java
// ❌ BAD: O(n²) - creates n string objects
String result = "";
for (int i = 0; i < n; i++) {
    result += i;
}

// ✅ GOOD: O(n) - modifies same object
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) {
    sb.append(i);
}
String result = sb.toString();
```

---

## 7. Common Patterns & Use Cases



### Pattern 3: Sliding Window

**Use Case**: Longest substring without repeating characters

```java
Map<Character, Integer> map = new HashMap<>();
int maxLen = 0, start = 0;
for (int end = 0; end < s.length(); end++) {
    char c = s.charAt(end);
    if (map.containsKey(c)) {
        start = Math.max(start, map.get(c) + 1);
    }
    map.put(c, end);
    maxLen = Math.max(maxLen, end - start + 1);
}
```

**Complexity**: O(n)

---

### Pattern 4: StringBuilder for Dynamic Building

**Use Case**: String compression

```java
StringBuilder sb = new StringBuilder();
int count = 1;
for (int i = 1; i < s.length(); i++) {
    if (s.charAt(i) == s.charAt(i-1)) {
        count++;
    } else {
        sb.append(s.charAt(i-1)).append(count);
        count = 1;
    }
}
sb.append(s.charAt(s.length()-1)).append(count);
```

**Complexity**: O(n)

---

## 8. Common Gotchas & Best Practices

### 1. String Comparison

**❌ WRONG**:
```java
if (s1 == s2)  // Compares references, not content
```

**✅ CORRECT**:
```java
if (s1.equals(s2))           // Content comparison
if (s1.equalsIgnoreCase(s2)) // Ignore case
```

---

### 2. Concatenation in Loops

**❌ WRONG**:
```java
String result = "";
for (String s : list) {
    result += s;  // O(n²) - creates new string each time
}
```

**✅ CORRECT**:
```java
StringBuilder sb = new StringBuilder();
for (String s : list) {
    sb.append(s);  // O(n)
}
String result = sb.toString();
```

---

### 3. Null Handling

**❌ WRONG**:
```java
if (s.isEmpty())  // NullPointerException if s is null
```

**✅ CORRECT**:
```java
if (s != null && !s.isEmpty())
if (s != null && !s.isBlank())  // Java 11+
```

---

### 4. substring() End Index

**Remember**: End index is exclusive
```java
String s = "hello";
s.substring(1, 4)  // "ell" (indices 1, 2, 3)
s.substring(2)     // "llo" (from 2 to end)
```

---

## 9. Interview Tips

### When to Use String
✅ Value doesn't change
✅ Few concatenations (1-2)
✅ Need thread safety
✅ Use as HashMap key

### When to Use StringBuilder
✅ Loop concatenations (most common)
✅ Building strings dynamically
✅ Performance critical code

### Remember
- **Strings are immutable** - every modification creates new object
- **Use `.equals()`** for content comparison, not `==`
- **StringBuilder for loops** - avoid `+=` in loops
- **String pool** - literals are pooled, `new String()` is not
- **Check null first** - before calling any string method
- **charAt() is O(1)** - use for character access
- **substring creates new string** - O(n) operation
- **Convert to char[]** for in-place modifications

### Time Complexity Quick Check
- Access/Length: O(1)
- Most operations: O(n)
- Concatenation in loop with +=: O(n²)
- StringBuilder append: O(1) amortized

---

## 10. Quick Reference

### Creation
```java
String s = "literal";              // Pool
String s = new String("heap");     // Heap
String s = String.valueOf(123);    // From int
char[] arr = s.toCharArray();      // To char[]
String s = new String(arr);        // From char[]
```

### Common Operations
```java
s.length()                         // Length
s.charAt(i)                        // Get char
s.substring(i, j)                  // Substring
s.indexOf(ch)                      // Find char
s.contains(sub)                    // Check substring
s.equals(str)                      // Compare
s.split(",")                       // Split
s.replace(old, new)                // Replace
s.toLowerCase()                    // To lower
s.toUpperCase()                    // To upper
s.trim()                           // Remove whitespace
```

### StringBuilder
```java
StringBuilder sb = new StringBuilder();
sb.append(str);                    // Add at end
sb.insert(i, str);                 // Insert at index
sb.delete(start, end);             // Delete range
sb.reverse();                      // Reverse
String result = sb.toString();     // To string
```

### Checking
```java
s.isEmpty()                        // length == 0
s.isBlank()                        // Only whitespace
s.startsWith(pre)                  // Prefix
s.endsWith(suf)                    // Suffix
s.contains(sub)                    // Substring
```

---

## 11. Key Insight

Strings are **immutable** - use **StringBuilder** for dynamic string building to avoid O(n²) complexity! Remember: use `.equals()` for comparison, not `==`!
