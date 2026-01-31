# Java TreeMap Concepts

## 2. What is TreeMap?

**Type**: Sorted key-value pairs
**Package**: `java.util.TreeMap`
**Implementation**: Red-Black Tree (self-balancing BST)

**Key Characteristics**:
- **Sorted keys** - maintains keys in natural or custom order
- **No duplicate keys** - overwrites if key exists
- **O(log n) operations** - get, put, remove
- **Does NOT allow null key** ❌ (allows null values)
- **Not thread-safe** - use Collections.synchronizedSortedMap()

---

## 3. Time & Space Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **put** | O(log n) | Add/update key-value |
| **get** | O(log n) | Get value by key |
| **remove** | O(log n) | Remove key-value |
| **containsKey** | O(log n) | Check key exists |
| **firstKey/lastKey** | O(log n) | Get smallest/largest key |
| **floor/ceiling** | O(log n) | NavigableMap operations |
| **size** | O(1) | Get size |

**Space**: O(n)

---

## 4. Common Operations & Methods

### Basic Operations

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Put** | `map.put(k, v)` | O(log n) | Add/update in sorted order |
| **Get** | `map.get(k)` | O(log n) | Get value by key |
| **Remove** | `map.remove(k)` | O(log n) | Remove key-value |
| **Contains key** | `map.containsKey(k)` | O(log n) | Check key exists |
| **First key** | `map.firstKey()` | O(log n) | Smallest key |
| **Last key** | `map.lastKey()` | O(log n) | Largest key |
| **Size** | `map.size()` | O(1) | Number of entries |

### NavigableMap Operations

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Floor key** | `map.floorKey(k)` | O(log n) | Largest key ≤ k |
| **Ceiling key** | `map.ceilingKey(k)` | O(log n) | Smallest key ≥ k |
| **Lower key** | `map.lowerKey(k)` | O(log n) | Largest key < k |
| **Higher key** | `map.higherKey(k)` | O(log n) | Smallest key > k |
| **Poll first** | `map.pollFirstEntry()` | O(log n) | Remove & return smallest |
| **Poll last** | `map.pollLastEntry()` | O(log n) | Remove & return largest |

---

## 5. Core Characteristics/Creation

```java
// Natural ordering (Comparable)
TreeMap<Integer, String> map = new TreeMap<>();

// Custom comparator (descending keys)
TreeMap<Integer, String> map = new TreeMap<>((a, b) -> b - a);

// Put key-value pairs (automatically sorted)
map.put(3, "three");
map.put(1, "one");
map.put(2, "two");  // Keys: [1, 2, 3]

// Get value
String val = map.get(2);  // "two"

// NavigableMap operations
int first = map.firstKey();       // 1 (smallest)
int last = map.lastKey();         // 3 (largest)
int floor = map.floorKey(2);      // 2 (largest ≤ 2)
int ceiling = map.ceilingKey(2);  // 2 (smallest ≥ 2)

// Range views
SortedMap<Integer, String> sub = map.subMap(1, 3);  // [1, 3) = {1, 2}

// Iterate (sorted order)
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    // Visits in ascending key order
}
```

---

## 6. Comparison with Similar Structures

| Feature | TreeMap | HashMap | LinkedHashMap |
|---------|---------|---------|---------------|
| **Order** | Sorted ⭐ | No order | Insertion order |
| **Performance** | O(log n) | O(1) ⭐ | O(1) ⭐ |
| **Null key** | ❌ No null | ✅ One null | ✅ One null |
| **Use case** | Sorted keys, ranges | Fast lookups | Ordered lookups |

**When to Use TreeMap**: Need keys in sorted order, range queries, floor/ceiling operations

**When to Use HashMap**: Just need fast lookups without order

**When to Use LinkedHashMap**: Need insertion order preserved

---

## 7. Common Patterns & Use Cases

### Pattern 1: Sorted Frequency Count

**Use Case**: Count frequencies in sorted order

```java
TreeMap<Character, Integer> freq = new TreeMap<>();
for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
// Keys automatically sorted
```

**Complexity**: O(n log n)

---

### Pattern 2: Range Sum Queries

**Use Case**: Sum of values in key range

```java
TreeMap<Integer, Integer> map = new TreeMap<>();
// ... populate map

// Sum values with keys in [start, end]
int sum = 0;
for (Map.Entry<Integer, Integer> entry : map.subMap(start, end + 1).entrySet()) {
    sum += entry.getValue();
}
```

**Complexity**: O(log n + k) where k = range size

---

### Pattern 3: Meeting Rooms / Event Scheduling

**Use Case**: Track events by time

```java
TreeMap<Integer, Integer> events = new TreeMap<>();
events.put(startTime, +1);  // Start event
events.put(endTime, -1);    // End event

// Find max concurrent events
int concurrent = 0, maxConcurrent = 0;
for (int delta : events.values()) {
    concurrent += delta;
    maxConcurrent = Math.max(maxConcurrent, concurrent);
}
```

**Complexity**: O(n log n)

---

### Pattern 4: Closest Elements

**Use Case**: Find closest key to target

```java
TreeMap<Integer, String> map = new TreeMap<>();
// ... populate

Integer floor = map.floorKey(target);      // Largest ≤ target
Integer ceiling = map.ceilingKey(target);  // Smallest ≥ target

// Find closest
int closest = (ceiling - target < target - floor) ? ceiling : floor;
```

**Complexity**: O(log n)

---

## 8. Common Gotchas & Best Practices

### 1. Null Key Not Allowed

**❌ WRONG**:
```java
TreeMap<Integer, String> map = new TreeMap<>();
map.put(null, "value");  // NullPointerException
```

**✅ CORRECT**:
```java
// Don't use null keys with TreeMap
// Use HashMap if null key needed
```

---

### 2. Keys Must Be Comparable

**❌ WRONG**:
```java
class Person { }  // Doesn't implement Comparable
TreeMap<Person, String> map = new TreeMap<>();
map.put(new Person(), "name");  // ClassCastException
```

**✅ CORRECT**:
```java
// Provide comparator
TreeMap<Person, String> map = new TreeMap<>((a, b) -> a.age - b.age);
```

---

### 3. Comparator Overflow

**❌ WRONG**:
```java
TreeMap<Integer, String> map = new TreeMap<>((a, b) -> a - b);  // Can overflow
```

**✅ CORRECT**:
```java
TreeMap<Integer, String> map = new TreeMap<>((a, b) -> Integer.compare(a, b));
```

---

## 9. Interview Tips

### When to Use TreeMap
✅ Need keys in sorted order
✅ Range queries (subMap, headMap, tailMap)
✅ Floor/ceiling operations
✅ Event scheduling problems
✅ Sorted frequency counting

### When NOT to Use TreeMap
❌ Just need fast lookups → Use HashMap (O(1))
❌ Don't care about order → Use HashMap
❌ Need null key → Use HashMap

### Remember
- **Sorted keys** - maintains keys in order
- **O(log n)** for get/put/remove
- **No null key** allowed (null values OK)
- **NavigableMap** - provides floor, ceiling, etc.
- Keys must be **Comparable** or use **Comparator**
- Use **Integer.compare()** to avoid overflow
- **subMap(from, to)** - range [from, to) exclusive end
- Iteration is in **ascending key order**

### Time Complexity Quick Check
- Get/Put/Remove: O(log n)
- Floor/Ceiling: O(log n)
- Range queries: O(log n + k)
- Iteration: O(n) in sorted order

---

## 10. Quick Reference

### Creation
```java
TreeMap<Integer, String> map = new TreeMap<>();  // Natural order
TreeMap<Integer, String> map = new TreeMap<>((a, b) -> b - a);  // Custom
```

### Basic Operations
```java
map.put(k, v)            // Add/update
map.get(k)               // Get value
map.remove(k)            // Remove
map.containsKey(k)       // Check key exists
map.firstKey()           // Smallest key
map.lastKey()            // Largest key
```

### NavigableMap Operations
```java
map.floorKey(k)          // Largest key ≤ k
map.ceilingKey(k)        // Smallest key ≥ k
map.lowerKey(k)          // Largest key < k
map.higherKey(k)         // Smallest key > k
map.pollFirstEntry()     // Remove & return smallest
map.pollLastEntry()      // Remove & return largest
```

### Range Views
```java
map.subMap(from, to)     // Keys in [from, to)
map.headMap(to)          // Keys < to
map.tailMap(from)        // Keys ≥ from
```

### Iteration (Sorted)
```java
// Ascending order
for (Map.Entry<K, V> entry : map.entrySet()) { }

// Descending order
for (Map.Entry<K, V> entry : map.descendingMap().entrySet()) { }
```

---

## 11. Key Insight

TreeMap keeps keys **automatically sorted** with **O(log n)** operations! Perfect for problems needing **sorted keys**, **range queries**, or **floor/ceiling** lookups. Remember: **no null keys**!
