# Java HashMap Concepts

## 2. What is HashMap?

**Type**: Key-value pairs with no order
**Package**: `java.util.HashMap`
**Implementation**: Hash table (array + linked lists/trees)

**Key Characteristics**:
- **Key-value mapping** - associates keys with values
- **No duplicate keys** - overwrites if key exists
- **O(1) operations** - get, put, remove (average case)
- **Allows null** - one null key, multiple null values
- **Not thread-safe** - use ConcurrentHashMap for thread-safety

---

## 3. Time & Space Complexity

| Operation | Average | Worst | Notes |
|-----------|---------|-------|-------|
| **put** | O(1) | O(n) | Add/update key-value |
| **get** | O(1) | O(n) | Get value by key |
| **remove** | O(1) | O(n) | Remove key-value |
| **containsKey** | O(1) | O(n) | Check key exists |
| **containsValue** | O(n) | O(n) | Check value exists |
| **size** | O(1) | O(1) | Get size |

**Space**: O(n)

**Worst case O(n)** occurs with hash collisions (rare with good hash function)

---

## 4. Common Operations & Methods

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Put** | `map.put(k, v)` | O(1) avg | Returns old value or null |
| **Get** | `map.get(k)` | O(1) avg | Returns value or null |
| **Remove** | `map.remove(k)` | O(1) avg | Returns removed value |
| **Contains key** | `map.containsKey(k)` | O(1) avg | Check key exists |
| **Contains value** | `map.containsValue(v)` | O(n) | Linear search |
| **Get or default** | `map.getOrDefault(k, def)` | O(1) avg | Returns default if missing |
| **Put if absent** | `map.putIfAbsent(k, v)` | O(1) avg | Only if key absent |
| **Size** | `map.size()` | O(1) | Number of entries |
| **Is empty** | `map.isEmpty()` | O(1) | Check if empty |
| **Clear** | `map.clear()` | O(n) | Remove all entries |

---

## 5. Core Characteristics/Creation

```java
// Create HashMap
Map<String, Integer> map = new HashMap<>();

// With initial capacity
Map<String, Integer> map = new HashMap<>(100);

// Put key-value pairs
map.put("apple", 5);      // {apple=5}
map.put("banana", 3);     // {apple=5, banana=3}
map.put("apple", 10);     // {apple=10, banana=3} - updates value

// Get value
Integer count = map.get("apple");  // 10
Integer missing = map.get("orange");  // null

// Get with default
int count = map.getOrDefault("orange", 0);  // 0

// Check key exists
boolean has = map.containsKey("apple");  // true

// Remove
Integer removed = map.remove("apple");  // 10

// Size
int size = map.size();  // 1

// Iterate
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();
    Integer value = entry.getValue();
}
```

---

## 6. Comparison with Similar Structures

| Feature | HashMap | TreeMap | LinkedHashMap |
|---------|---------|---------|---------------|
| **Order** | No order | Sorted keys | Insertion order |
| **Performance** | O(1) ⭐ | O(log n) | O(1) ⭐ |
| **Null key** | ✅ One null | ❌ No null | ✅ One null |
| **Use case** | Fast lookups | Sorted keys | Ordered lookups |

**When to Use HashMap**: Fast key-value lookups, frequency counting (most common)

**When to Use TreeMap**: Need keys in sorted order

**When to Use LinkedHashMap**: Need insertion order preserved

---

## 7. Common Patterns & Use Cases

### Pattern 1: Frequency Counter

**Use Case**: Count occurrences

```java
Map<Character, Integer> freq = new HashMap<>();
for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

**Complexity**: O(n)

---

### Pattern 2: Two Sum

**Use Case**: Find pair that sums to target

```java
Map<Integer, Integer> map = new HashMap<>();  // value -> index
for (int i = 0; i < nums.length; i++) {
    int complement = target - nums[i];
    if (map.containsKey(complement)) {
        return new int[]{map.get(complement), i};
    }
    map.put(nums[i], i);
}
```

**Complexity**: O(n)

---

### Pattern 3: Group Anagrams

**Use Case**: Group strings by pattern

```java
Map<String, List<String>> map = new HashMap<>();
for (String word : words) {
    char[] chars = word.toCharArray();
    Arrays.sort(chars);
    String key = new String(chars);
    map.putIfAbsent(key, new ArrayList<>());
    map.get(key).add(word);
}
```

**Complexity**: O(n * m log m) where m = word length

---

### Pattern 4: Cache / Memoization

**Use Case**: Store computed results

```java
Map<Integer, Integer> memo = new HashMap<>();
public int fib(int n) {
    if (n <= 1) return n;
    if (memo.containsKey(n)) return memo.get(n);
    int result = fib(n-1) + fib(n-2);
    memo.put(n, result);
    return result;
}
```

**Complexity**: O(n) with memoization

---

## 8. Common Gotchas & Best Practices

### 1. Null Key Allowed

**Remember**:
```java
map.put(null, 100);  // Valid - one null key allowed
int val = map.get(null);  // 100
```

---

### 2. get() Returns Null

**❌ WRONG**:
```java
int count = map.get(key);  // NullPointerException if key missing
```

**✅ CORRECT**:
```java
Integer count = map.get(key);  // null if missing
int count = map.getOrDefault(key, 0);  // 0 if missing
```

---

### 3. containsValue is O(n)

**❌ SLOW**:
```java
if (map.containsValue(target)) { }  // O(n) - searches all values
```

**✅ BETTER**:
```java
// If you need fast value lookups, maintain reverse map
Map<Value, Key> reverseMap = new HashMap<>();
```

---

### 4. Modifying Keys

**❌ WRONG**:
```java
List<Integer> key = new ArrayList<>(Arrays.asList(1, 2));
map.put(key, "value");
key.add(3);  // Modifying key breaks hashCode!
map.get(key);  // May not find it
```

**✅ CORRECT**:
```java
// Use immutable keys or don't modify after putting
```

---

## 9. Interview Tips

### When to Use HashMap
✅ Fast key-value lookups (O(1))
✅ Frequency counting
✅ Two Sum / Contains problems
✅ Group by key (anagrams, etc.)
✅ Caching / memoization

### When NOT to Use HashMap
❌ Need sorted keys → Use TreeMap
❌ Need insertion order → Use LinkedHashMap
❌ Need indexed access → Use Array/List

### Remember
- **O(1) average** for get/put/remove
- **Allows one null key** and multiple null values
- **No order** - use LinkedHashMap or TreeMap for ordering
- **get() returns null** if key missing - use getOrDefault()
- **Keys must implement equals() and hashCode()**
- **containsValue() is O(n)** - slow
- Use **Integer, String** etc. as keys (immutable)

### Time Complexity Quick Check
- Get/Put/Remove: O(1) average
- containsKey: O(1) average
- containsValue: O(n)
- Iteration: O(n)

---

## 10. Quick Reference

### Creation
```java
Map<String, Integer> map = new HashMap<>();
Map<String, Integer> map = new HashMap<>(100);  // Capacity
```

### Operations
```java
map.put(k, v)            // Add/update
map.get(k)               // Get value (null if missing)
map.getOrDefault(k, 0)   // Get with default
map.remove(k)            // Remove
map.containsKey(k)       // Check key exists
map.size()               // Get size
map.isEmpty()            // Check if empty
```

### Iteration
```java
// Iterate entries
for (Map.Entry<K, V> entry : map.entrySet()) {
    K key = entry.getKey();
    V value = entry.getValue();
}

// Iterate keys
for (K key : map.keySet()) { }

// Iterate values
for (V value : map.values()) { }

// Java 8+
map.forEach((k, v) -> process(k, v));
```

### Frequency Counter Template
```java
Map<Character, Integer> freq = new HashMap<>();
for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

### Two Sum Template
```java
Map<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    if (map.containsKey(target - nums[i])) {
        return new int[]{map.get(target - nums[i]), i};
    }
    map.put(nums[i], i);
}
```

---

## 11. Key Insight

HashMap is your go-to for **O(1) key-value lookups**! Perfect for **frequency counting**, **Two Sum**, and **caching**. Remember: keys must be **immutable** and implement **equals()/hashCode()**!
