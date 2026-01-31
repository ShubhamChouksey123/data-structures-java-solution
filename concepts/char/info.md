# Java Character Concepts

## 2. What is char?

**Type**: Primitive data type (2 bytes)
**Wrapper Class**: `Character`
**Range**: 0 to 65,535 (Unicode characters)

**Key Characteristics**:
- **Primitive type** - stores single character
- **16-bit Unicode** - supports all Unicode characters
- **Cannot be null** - use Character wrapper for null
- **Single quotes** - `'a'`, `'5'`, `' '`
- **Contiguous ASCII** - letters and digits in sequence

---

## 3. Time & Space Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **Access/assignment** | O(1) | Direct primitive access |
| **Comparison** | O(1) | Compare ASCII values |
| **Arithmetic** | O(1) | Treat as integer |
| **Type checking** | O(1) | Character.isLetter(), etc. |
| **Case conversion** | O(1) | toUpperCase/toLowerCase |

**Space**: O(1) - 2 bytes per char

---

## 4. Common Operations & Methods

### Character Class Methods

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Check letter** | `Character.isLetter(c)` | O(1) | a-z, A-Z |
| **Check digit** | `Character.isDigit(c)` | O(1) | 0-9 |
| **Check alphanumeric** | `Character.isLetterOrDigit(c)` | O(1) | Letters or digits |
| **Check whitespace** | `Character.isWhitespace(c)` | O(1) | Space, tab, newline |
| **Check uppercase** | `Character.isUpperCase(c)` | O(1) | A-Z |
| **Check lowercase** | `Character.isLowerCase(c)` | O(1) | a-z |
| **To uppercase** | `Character.toUpperCase(c)` | O(1) | 'a' → 'A' |
| **To lowercase** | `Character.toLowerCase(c)` | O(1) | 'A' → 'a' |

### ASCII Ranges

| Characters | Range | ASCII Values |
|------------|-------|--------------|
| **Digits** | '0' to '9' | 48-57 |
| **Uppercase** | 'A' to 'Z' | 65-90 |
| **Lowercase** | 'a' to 'z' | 97-122 |

**Key Facts**: '0' = 48, 'A' = 65, 'a' = 97, case difference = 32

---

## 5. Core Characteristics/Creation

```java
// Declaration
char c = 'a';                   // Single character
char digit = '5';               // Digit character
char space = ' ';               // Space is valid

// Wrapper (for collections or null)
Character ch = 'a';             // Autoboxing
Character nullable = null;      // Can be null

// Common conversions
int num = c - '0';              // '5' → 5 (char digit to int)
char c = (char)(n + '0');       // 5 → '5' (int to char digit)
int idx = c - 'a';              // 'c' → 2 (lowercase to index 0-25)
char next = (char)(c + 1);      // 'a' → 'b' (MUST CAST)

// String conversions
char[] arr = s.toCharArray();   // String → char[]
String s = new String(arr);     // char[] → String
char c = s.charAt(i);           // Get char at index
```

---

## 6. Comparison with Similar Structures

| Feature | char | Character |
|---------|------|--------------|
| **Type** | Primitive | Wrapper Object |
| **Null** | ❌ Cannot be null | ✅ Can be null |
| **Collections** | ❌ No | ✅ Yes |
| **Performance** | Fast ⭐ | Slower (autoboxing) |
| **Size** | 2 bytes | Object overhead |

**When to Use char**: Default choice (95% of cases), better performance

**When to Use Character**: Collections (List, Set, Map), need null values

---

## 7. Common Patterns & Use Cases



## 8. Common Gotchas & Best Practices

### 1. Single vs Double Quotes

**❌ WRONG**:
```java
char c = "a";              // Double quotes = String
char c = "";               // Empty char not allowed
```

**✅ CORRECT**:
```java
char c = 'a';              // Single quotes for char
char space = ' ';          // Space is valid char
```

---

### 2. Character Arithmetic Requires Cast

**❌ WRONG**:
```java
char next = c + 1;         // Type mismatch (int to char)
```

**✅ CORRECT**:
```java
char next = (char)(c + 1); // Must cast result
```

---

### 3. Case Conversion Comparison

**❌ SUBOPTIMAL**:
```java
c >= 'A' && c <= 'Z';      // Manual check
```

**✅ BETTER**:
```java
Character.isUpperCase(c);  // More readable
```

---

### 4. Char vs String charAt

**Remember**:
```java
char c = 'a';              // Single character
String s = "a";            // String with one character
char ch = s.charAt(0);     // Extract char from string
```

---

## 9. Interview Tips

### When to Use char
✅ Single character operations
✅ ASCII arithmetic (a-z indexing)
✅ Performance-critical code
✅ Character frequency with `int[26]`

### When to Use Character
✅ Collections (List<Character>, Set<Character>)
✅ Need null values
✅ Passing to generic methods

### Remember
- **char uses single quotes** - `'a'`, not `"a"`
- **'0' = 48** - subtract '0' to get digit value
- **'A' = 65, 'a' = 97** - difference is 32
- **`c - 'a'`** gives index 0-25 for lowercase letters
- **MUST CAST arithmetic** - `(char)(c + 1)`
- **Use `int[26]`** for lowercase letter frequency (faster than HashMap)
- Character methods handle Unicode properly

### Time Complexity Quick Check
- All char operations: O(1)
- Frequency counting: O(n)

---

## 10. Quick Reference

### Most Used Conversions
```java
c - '0'                    // Char digit to int: '5' → 5
c - 'a'                    // Lowercase to index: 'c' → 2
c - 'A'                    // Uppercase to index: 'C' → 2
(char)(n + '0')            // Int to char digit: 5 → '5'
(char)(c + 1)              // Next char: 'a' → 'b'
```

### Type Checking
```java
Character.isLetter(c)
Character.isDigit(c)
Character.isLetterOrDigit(c)
Character.isWhitespace(c)
Character.isUpperCase(c)
Character.isLowerCase(c)
```

### Case Conversion
```java
Character.toUpperCase(c)   // 'a' → 'A'
Character.toLowerCase(c)   // 'A' → 'a'
```

### String ↔ char[]
```java
char[] arr = s.toCharArray();
String s = new String(arr);
char c = s.charAt(i);
```

### Frequency Counting
```java
// Lowercase only (a-z)
int[] freq = new int[26];
freq[c - 'a']++;

// All characters
Map<Character, Integer> freq = new HashMap<>();
freq.put(c, freq.getOrDefault(c, 0) + 1);
```

---

## 11. Key Insight

Use **`int[26]`** array for lowercase letter frequency - it's faster than HashMap and uses O(1) space! Remember **`c - 'a'`** gives you the perfect index (0-25) for array mapping!
