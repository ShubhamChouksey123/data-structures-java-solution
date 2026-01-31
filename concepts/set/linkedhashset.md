# Java LinkedHashSet Concepts

## 2. What is LinkedHashSet?

**Type**: Ordered collection of unique elements (insertion order)
**Package**: `java.util.LinkedHashSet`
**Implementation**: Hash table + Linked list

**Key Characteristics**:
- **Insertion order** - maintains order elements were added
- **No duplicates** - enforces uniqueness
- **O(1) operations** - add, remove, contains (average case)
- **Allows null** - one null element allowed
- **Not thread-safe** - use Collections.synchronizedSet()

---

## 3. Time & Space Complexity

| Operation | Average | Worst | Notes |
|-----------|---------|-------|-------|
| **add** | O(1) | O(n) | Add element in insertion order |
| **remove** | O(1) | O(n) | Remove element |
| **contains** | O(1) | O(n) | Check membership |
| **size** | O(1) | O(1) | Get size |
| **iteration** | O(n) | O(n) | In insertion order |

**Space**: O(n) + overhead for maintaining links

**Slightly more memory** than HashSet due to maintaining doubly-linked list

---

## 4. Common Operations & Methods

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Add element** | `set.add(e)` | O(1) avg | Returns false if duplicate |
| **Remove element** | `set.remove(e)` | O(1) avg | Returns true if removed |
| **Contains** | `set.contains(e)` | O(1) avg | Check membership |
| **Get size** | `set.size()` | O(1) | Number of elements |
| **Check empty** | `set.isEmpty()` | O(1) | true if empty |
| **Clear all** | `set.clear()` | O(n) | Remove all elements |
| **Iterator** | `set.iterator()` | O(1) | Returns in insertion order |

---

## 5. Core Characteristics/Creation

```java
// Create LinkedHashSet
Set<Integer> set = new LinkedHashSet<>();

// With initial capacity
Set<Integer> set = new LinkedHashSet<>(100);

// From collection (maintains insertion order)
Set<Integer> set = new LinkedHashSet<>(Arrays.asList(3, 1, 2));  // [3, 1, 2]

// Add elements
set.add(5);    // [5]
set.add(1);    // [5, 1]
set.add(3);    // [5, 1, 3]
set.add(1);    // [5, 1, 3] - duplicate ignored

// Iteration (insertion order)
for (int num : set) {
    System.out.println(num);  // 5, 1, 3
}

// Remove element
set.remove(1);  // [5, 3]

// Check membership
boolean exists = set.contains(5);  // true
```

---

## 6. Comparison with Similar Structures

| Feature | LinkedHashSet | HashSet | TreeSet |
|---------|---------------|---------|---------|
| **Order** | Insertion ⭐ | No order | Sorted |
| **Performance** | O(1) ⭐ | O(1) ⭐ | O(log n) |
| **Null** | ✅ One null | ✅ One null | ❌ No null |
| **Memory** | High | Medium | Medium |
| **Use case** | Ordered unique | Fast lookups | Sorted unique |

**When to Use LinkedHashSet**: Need insertion order preserved with O(1) operations

**When to Use HashSet**: Don't care about order, want minimal memory

**When to Use TreeSet**: Need sorted order

---

## 7. Common Patterns & Use Cases

### Pattern 1: LRU Cache (Access Order)

**Use Case**: Track order of access

```java
// Note: Use LinkedHashMap for true LRU with access order
Set<Integer> lru = new LinkedHashSet<>();
// LinkedHashSet maintains insertion order, not access order
// For LRU, use LinkedHashMap with accessOrder=true
```

---

### Pattern 2: Deduplication with Order

**Use Case**: Remove duplicates while preserving order

```java
Integer[] arr = {3, 1, 2, 1, 3, 4};
Set<Integer> unique = new LinkedHashSet<>(Arrays.asList(arr));
// Result: [3, 1, 2, 4] - order preserved, duplicates removed
```

**Complexity**: O(n)

---

### Pattern 3: Visited Tracking with Order

**Use Case**: Track visited elements in order

```java
Set<String> visited = new LinkedHashSet<>();
visited.add("page1");
visited.add("page2");
visited.add("page3");
// Can iterate in visit order
```

**Complexity**: O(1) per add

---

### Pattern 4: Stream Distinct (Ordered)

**Use Case**: Get distinct elements preserving order

```java
List<Integer> list = Arrays.asList(1, 2, 2, 3, 1);
Set<Integer> distinct = new LinkedHashSet<>(list);  // [1, 2, 3]

// Back to list
List<Integer> result = new ArrayList<>(distinct);
```

**Complexity**: O(n)

---

## 8. Common Gotchas & Best Practices

### 1. Not Access Order (Unlike LinkedHashMap)

**❌ WRONG ASSUMPTION**:
```java
Set<Integer> set = new LinkedHashSet<>();
set.add(1); set.add(2); set.add(3);
set.contains(1);  // Does NOT move 1 to end
// Still [1, 2, 3], not [2, 3, 1]
```

**✅ CORRECT**:
```java
// LinkedHashSet maintains INSERTION order, not access order
// For access order, use LinkedHashMap
```

---

### 2. Higher Memory Overhead

**Remember**:
```java
// LinkedHashSet uses more memory than HashSet
// Due to maintaining doubly-linked list
// Use HashSet if order not needed
```

---

### 3. Iteration Order is Insertion Order

**✅ CORRECT**:
```java
Set<Integer> set = new LinkedHashSet<>();
set.add(3); set.add(1); set.add(2);
// Iterates in order: 3, 1, 2 (not sorted)
```

---

## 9. Interview Tips

### When to Use LinkedHashSet
✅ Need insertion order preserved
✅ Remove duplicates while keeping order
✅ Fast lookups with predictable iteration order
✅ Cache with insertion order tracking

### When NOT to Use LinkedHashSet
❌ Don't care about order → Use HashSet (less memory)
❌ Need sorted order → Use TreeSet
❌ Need access order → Use LinkedHashMap

### Remember
- **Insertion order** - maintains add order
- **O(1) operations** - same as HashSet
- **Allows one null** element
- **More memory** than HashSet
- **Not access order** - use LinkedHashMap for that
- Useful for **maintaining order while deduplicating**

### Time Complexity Quick Check
- Add/Remove/Contains: O(1) average
- Iteration: O(n) in insertion order

---

## 10. Quick Reference

### Creation
```java
Set<Integer> set = new LinkedHashSet<>();
Set<Integer> set = new LinkedHashSet<>(100);  // With capacity
Set<Integer> set = new LinkedHashSet<>(list);  // From collection
```

### Operations
```java
set.add(x)           // Add element (maintains order)
set.remove(x)        // Remove element
set.contains(x)      // Check membership
set.size()           // Get size
set.isEmpty()        // Check if empty
set.clear()          // Remove all
```

### Deduplication with Order
```java
// Array to unique with order preserved
Integer[] arr = {3, 1, 2, 1, 3};
Set<Integer> unique = new LinkedHashSet<>(Arrays.asList(arr));
// Result: [3, 1, 2]

// Back to array
Integer[] result = unique.toArray(new Integer[0]);
```

### Iteration (Insertion Order)
```java
for (int num : set) {
    // Visits in insertion order
}
```

### Comparison
```java
// HashSet - no order, fastest
Set<Integer> hs = new HashSet<>();

// LinkedHashSet - insertion order, O(1)
Set<Integer> lhs = new LinkedHashSet<>();

// TreeSet - sorted order, O(log n)
Set<Integer> ts = new TreeSet<>();
```

---

## 11. Key Insight

LinkedHashSet combines **HashSet's O(1) performance** with **predictable insertion order**! Perfect for **deduplication while preserving order**. Use when order matters but sorting doesn't!
