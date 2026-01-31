# Java LinkedHashMap Concepts

## 2. What is LinkedHashMap?

**Type**: Ordered key-value pairs (insertion or access order)
**Package**: `java.util.LinkedHashMap`
**Implementation**: Hash table + Doubly-linked list

**Key Characteristics**:
- **Insertion order** - maintains order keys were added (default)
- **Access order** - optional mode for LRU cache
- **O(1) operations** - get, put, remove (average case)
- **Allows null** - one null key, multiple null values
- **Not thread-safe** - use Collections.synchronizedMap()

---

## 3. Time & Space Complexity

| Operation | Average | Worst | Notes |
|-----------|---------|-------|-------|
| **put** | O(1) | O(n) | Add/update in order |
| **get** | O(1) | O(n) | Get value by key |
| **remove** | O(1) | O(n) | Remove key-value |
| **containsKey** | O(1) | O(n) | Check key exists |
| **size** | O(1) | O(1) | Get size |
| **iteration** | O(n) | O(n) | In insertion/access order |

**Space**: O(n) + overhead for maintaining doubly-linked list

**Slightly more memory** than HashMap due to links

---

## 4. Common Operations & Methods

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Put** | `map.put(k, v)` | O(1) avg | Add/update (maintains order) |
| **Get** | `map.get(k)` | O(1) avg | Get value (updates access order if enabled) |
| **Remove** | `map.remove(k)` | O(1) avg | Remove key-value |
| **Contains key** | `map.containsKey(k)` | O(1) avg | Check key exists |
| **Get or default** | `map.getOrDefault(k, def)` | O(1) avg | Returns default if missing |
| **Size** | `map.size()` | O(1) | Number of entries |
| **Is empty** | `map.isEmpty()` | O(1) | Check if empty |
| **Clear** | `map.clear()` | O(n) | Remove all |

---

## 5. Core Characteristics/Creation

```java
// Insertion order (default)
Map<Integer, String> map = new LinkedHashMap<>();

// Access order (for LRU cache)
Map<Integer, String> map = new LinkedHashMap<>(16, 0.75f, true);

// With capacity
Map<Integer, String> map = new LinkedHashMap<>(100);

// Put key-value pairs
map.put(3, "three");
map.put(1, "one");
map.put(2, "two");
// Iteration order: 3, 1, 2 (insertion order)

// Get value
String val = map.get(1);  // "one"

// Iterate (insertion order)
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    // Visits in insertion order: 3, 1, 2
}
```

### Access Order Mode (LRU Cache)

```java
// Enable access order
Map<Integer, String> lru = new LinkedHashMap<>(16, 0.75f, true) {
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
        return size() > MAX_SIZE;  // Remove oldest when exceeds capacity
    }
};

lru.put(1, "one");
lru.put(2, "two");
lru.put(3, "three");
lru.get(1);  // Moves 1 to end
// Order now: 2, 3, 1
```

---

## 6. Comparison with Similar Structures

| Feature | LinkedHashMap | HashMap | TreeMap |
|---------|---------------|---------|---------|
| **Order** | Insertion/Access ⭐ | No order | Sorted |
| **Performance** | O(1) ⭐ | O(1) ⭐ | O(log n) |
| **Null key** | ✅ One null | ✅ One null | ❌ No null |
| **Memory** | High | Medium | Medium |
| **Use case** | LRU cache, ordered | Fast lookups | Sorted keys |

**When to Use LinkedHashMap**: Need insertion order, LRU cache, ordered iteration

**When to Use HashMap**: Don't care about order, want minimal memory

**When to Use TreeMap**: Need sorted keys

---

## 7. Common Patterns & Use Cases

### Pattern 1: LRU Cache

**Use Case**: Implement Least Recently Used cache

```java
class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);  // true = access order
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
```

**Complexity**: O(1) for get/put

---

### Pattern 2: Maintain Insertion Order

**Use Case**: Keep track of order items were added

```java
Map<String, Integer> map = new LinkedHashMap<>();
map.put("apple", 5);
map.put("banana", 3);
map.put("orange", 7);
// Iteration maintains order: apple, banana, orange
```

**Complexity**: O(1) per operation

---

### Pattern 3: Ordered Frequency Counter

**Use Case**: Count frequencies preserving first occurrence order

```java
Map<Character, Integer> freq = new LinkedHashMap<>();
for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
// Iterates in order characters first appeared
```

**Complexity**: O(n)

---

### Pattern 4: First Non-Repeating Character

**Use Case**: Find first unique character

```java
Map<Character, Integer> freq = new LinkedHashMap<>();
for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
    if (entry.getValue() == 1) {
        return entry.getKey();  // First unique char
    }
}
```

**Complexity**: O(n)

---

## 8. Common Gotchas & Best Practices

### 1. Access Order vs Insertion Order

**❌ WRONG ASSUMPTION**:
```java
Map<Integer, String> map = new LinkedHashMap<>();  // Insertion order
map.put(1, "one"); map.put(2, "two");
map.get(1);  // Does NOT move 1 to end
// Still insertion order, not access order
```

**✅ CORRECT**:
```java
// For access order, must specify in constructor
Map<Integer, String> map = new LinkedHashMap<>(16, 0.75f, true);
```

---

### 2. Higher Memory Overhead

**Remember**:
```java
// LinkedHashMap uses more memory than HashMap
// Due to maintaining doubly-linked list
// Use HashMap if order not needed
```

---

### 3. removeEldestEntry Must Be Overridden

**✅ CORRECT LRU**:
```java
Map<Integer, String> lru = new LinkedHashMap<Integer, String>(16, 0.75f, true) {
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, String> e) {
        return size() > MAX_CAPACITY;
    }
};
```

---

## 9. Interview Tips

### When to Use LinkedHashMap
✅ LRU Cache implementation
✅ Need insertion order preserved
✅ First non-repeating character
✅ Maintain key order for iteration

### When NOT to Use LinkedHashMap
❌ Don't care about order → Use HashMap (less memory)
❌ Need sorted keys → Use TreeMap
❌ Just need fast lookups → Use HashMap

### Remember
- **Insertion order** by default, **access order** if enabled
- **O(1) operations** - same as HashMap
- **Allows one null key**
- **More memory** than HashMap
- **Access order** requires constructor parameter `accessOrder=true`
- Perfect for **LRU Cache**
- Override **removeEldestEntry** for cache eviction
- Iteration is **predictable** (ordered)

### Time Complexity Quick Check
- Get/Put/Remove: O(1) average
- Iteration: O(n) in insertion/access order

---

## 10. Quick Reference

### Creation
```java
// Insertion order (default)
Map<K, V> map = new LinkedHashMap<>();

// Access order (LRU cache)
Map<K, V> map = new LinkedHashMap<>(capacity, 0.75f, true);

// With capacity
Map<K, V> map = new LinkedHashMap<>(100);
```

### Operations
```java
map.put(k, v)            // Add/update (maintains order)
map.get(k)               // Get value
map.remove(k)            // Remove
map.containsKey(k)       // Check key exists
map.size()               // Get size
```

### LRU Cache Template
```java
class LRUCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }
}
```

### Ordered Iteration
```java
// Iterate in insertion order (or access order if enabled)
for (Map.Entry<K, V> entry : map.entrySet()) {
    K key = entry.getKey();
    V value = entry.getValue();
}
```

---

## 11. Key Insight

LinkedHashMap combines **HashMap's O(1) performance** with **predictable iteration order**! Perfect for **LRU Cache** (with access order) and maintaining **insertion order**. Remember: set `accessOrder=true` for LRU!
