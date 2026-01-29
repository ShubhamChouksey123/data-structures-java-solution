# Java Character Concepts

## char vs Character

| Feature | char | Character |
|---------|------|-----------|
| Type | Primitive (2 bytes) | Wrapper Object |
| Null | ❌ Cannot be null | ✅ Can be null |
| Collections | ❌ No | ✅ Yes |
| Performance | Fast | Slower (autoboxing) |

**Use char by default, Character only for collections or when null is needed.**

---

## ASCII Ranges (Most Important)

| Characters | Range | Values |
|------------|-------|--------|
| **Digits** | '0' to '9' | 48-57 |
| **Uppercase** | 'A' to 'Z' | 65-90 |
| **Lowercase** | 'a' to 'z' | 97-122 |

**Key Facts**:
- '0' = 48
- 'A' = 65, 'a' = 97 (difference = 32)
- Letters and digits are contiguous

---

## Essential Conversions

```java
// Char digit ↔ int
int num = c - '0';              // '5' → 5
char c = (char)(n + '0');       // 5 → '5'

// Char ↔ array index (for a-z)
int idx = c - 'a';              // 'c' → 2 (gives 0-25)

// Character arithmetic (MUST CAST)
char next = (char)(c + 1);      // 'a' → 'b'

// String conversions
char[] arr = s.toCharArray();   // String → char[]
String s = new String(arr);     // char[] → String
char c = s.charAt(i);           // Get char at index
```

---

## Character Class Methods

```java
// Type checking
Character.isLetter(c)
Character.isDigit(c)
Character.isLetterOrDigit(c)
Character.isWhitespace(c)
Character.isUpperCase(c)
Character.isLowerCase(c)

// Case conversion
Character.toUpperCase(c)        // 'a' → 'A'
Character.toLowerCase(c)        // 'A' → 'a'
```

---

## Frequency Counter Pattern

**For lowercase letters (a-z only)**:
```java
int[] freq = new int[26];
for (char c : s.toCharArray()) {
    freq[c - 'a']++;
}
```

**For all characters**:
```java
Map<Character, Integer> freq = new HashMap<>();
for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

**Prefer `int[26]` for better performance when possible.**

---

## Common Gotchas

```java
// ❌ WRONG
char c = "a";              // Double quotes = String
char c = '';               // Empty char literal
char next = c + 1;         // Arithmetic without cast

// ✅ CORRECT
char c = 'a';              // Single quotes
char c = ' ';              // Space is valid
char next = (char)(c + 1); // Must cast arithmetic result
```

---

## Quick Reference

### Most Used Conversions
```java
c - '0'                    // Char digit to int: '5' → 5
c - 'a'                    // Lowercase to index: 'c' → 2
(char)(n + '0')            // Int to char digit: 5 → '5'
(char)(c + 1)              // Next char: 'a' → 'b'
```

### String ↔ char[]
```java
char[] arr = s.toCharArray();
String s = new String(arr);
```

### Frequency Counting
- **Lowercase only**: `int[26]` with `c - 'a'`
- **All chars**: `HashMap<Character, Integer>`

---

**Interview Tip**: Use `int[26]` array instead of HashMap when working with lowercase letters only - it's faster and uses O(1) space!
