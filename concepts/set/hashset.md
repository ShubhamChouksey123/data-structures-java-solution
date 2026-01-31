# Java HashSet Concepts

## 2. What is HashSet?

**Type**: Unordered collection of unique elements
**Package**: `java.util.HashSet`
**Implementation**: Hash table (HashMap internally)

**Key Characteristics**:
- **No duplicates** - automatically enforces uniqueness
- **No ordering** - elements not in any specific order
- **O(1) operations** - add, remove, contains (average case)
- **Allows null** - one null element allowed
- **Not thread-safe** - use Collections.synchronizedSet() for thread-safety

---

## 3. Time & Space Complexity

| Operation | Average | Worst | Notes |
|-----------|---------|-------|-------|
| **add** | O(1) | O(n) | Add element (ignores if duplicate) |
| **remove** | O(1) | O(n) | Remove element |
| **contains** | O(1) | O(n) | Check membership |
| **size** | O(1) | O(1) | Get size |
| **isEmpty** | O(1) | O(1) | Check if empty |

**Space**: O(n)

**Worst case O(n)** occurs when all elements hash to same bucket (rare with good hash function)

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
| **Add all** | `set.addAll(collection)` | O(m) | m = collection size |
| **Remove all** | `set.removeAll(collection)` | O(m) | Remove matching elements |
| **Retain all** | `set.retainAll(collection)` | O(m) | Keep only matching |

---

## 5. Core Characteristics/Creation

```java
// Create HashSet
Set<Integer> set = new HashSet<>();

// With initial capacity
Set<Integer> set = new HashSet<>(100);

// From collection
Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));

// Add elements
set.add(1);    // true (added)
set.add(2);    // true
set.add(1);    // false (duplicate, not added)

// Check membership
boolean exists = set.contains(1);  // true

// Remove element
boolean removed = set.remove(2);   // true

// Size
int size = set.size();  // 1

// Iterate
for (int num : set) {
    process(num);
}
```

---

## 6. Comparison with Similar Structures

| Feature | HashSet | TreeSet | LinkedHashSet |
|---------|---------|---------|---------------|
| **Order** | No order | Sorted | Insertion order |
| **Performance** | O(1) ⭐ | O(log n) | O(1) ⭐ |
| **Null** | ✅ One null | ❌ No null | ✅ One null |
| **Use case** | Fast lookups | Sorted unique | Ordered unique |

**When to Use HashSet**: Fast lookups, deduplication, membership checks (most common)

**When to Use TreeSet**: Need sorted order, range queries

**When to Use LinkedHashSet**: Need insertion order preserved

---

## 7. Common Patterns & Use Cases

### Pattern 1: Deduplication

**Use Case**: Remove duplicates from array

```java
Set<Integer> set = new HashSet<>(Arrays.asList(arr));
// Or
Set<Integer> set = new HashSet<>();
for (int num : arr) {
    set.add(num);
}
```

**Complexity**: O(n)

---

### Pattern 2: Fast Lookup

**Use Case**: Check if element exists

```java
Set<Integer> seen = new HashSet<>();
for (int num : arr) {
    if (seen.contains(num)) {
        // Duplicate found
    }
    seen.add(num);
}
```

**Complexity**: O(n)

---

### Pattern 3: Set Operations

**Use Case**: Union, intersection, difference

```java
Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
Set<Integer> set2 = new HashSet<>(Arrays.asList(2, 3, 4));

// Union
Set<Integer> union = new HashSet<>(set1);
union.addAll(set2);  // {1, 2, 3, 4}

// Intersection
Set<Integer> intersection = new HashSet<>(set1);
intersection.retainAll(set2);  // {2, 3}

// Difference
Set<Integer> difference = new HashSet<>(set1);
difference.removeAll(set2);  // {1}
```

**Complexity**: O(n + m)

---

### Pattern 4: Two Sum (Using Set)

**Use Case**: Find pair that sums to target

```java
Set<Integer> seen = new HashSet<>();
for (int num : arr) {
    if (seen.contains(target - num)) {
        return true;
    }
    seen.add(num);
}
return false;
```

**Complexity**: O(n)

---

## 8. Common Gotchas & Best Practices

### 1. Modifying Objects in Set

**❌ WRONG**:
```java
class Person {
    String name;
    int age;
}
Set<Person> set = new HashSet<>();
Person p = new Person("Alice", 25);
set.add(p);
p.age = 26;  // Modifying after adding - breaks hashCode!
set.contains(p);  // May return false now
```

**✅ CORRECT**:
```java
// Use immutable objects or don't modify after adding
// Or remove, modify, then re-add
set.remove(p);
p.age = 26;
set.add(p);
```

---

### 2. Set Doesn't Maintain Order

**❌ WRONG**:
```java
Set<Integer> set = new HashSet<>();
set.add(1); set.add(2); set.add(3);
// Don't assume order [1, 2, 3]
```

**✅ CORRECT**:
```java
// Use LinkedHashSet for insertion order
Set<Integer> set = new LinkedHashSet<>();
// Or TreeSet for sorted order
```

---

### 3. add() Returns Boolean

**Remember**:
```java
boolean added = set.add(5);  // true if added, false if duplicate
```

---

## 9. Interview Tips

### When to Use HashSet
✅ Fast membership checks (O(1))
✅ Remove duplicates from collection
✅ Track seen elements
✅ Set operations (union, intersection)
✅ Two Sum / Contains Duplicate problems

### When NOT to Use HashSet
❌ Need sorted order → Use TreeSet
❌ Need insertion order → Use LinkedHashSet
❌ Need indexed access → Use ArrayList

### Remember
- **No duplicates** - enforces uniqueness
- **O(1) average** for add/remove/contains
- **No order** - use LinkedHashSet or TreeSet for ordering
- **Allows one null** element
- **equals() and hashCode()** must be implemented for custom objects
- **add() returns false** if element already exists
- Don't modify objects after adding to set

### Time Complexity Quick Check
- Add/Remove/Contains: O(1) average
- Iteration: O(n)
- Set operations: O(n + m)

---

## 10. Quick Reference

### Creation
```java
Set<Integer> set = new HashSet<>();
Set<Integer> set = new HashSet<>(100);  // With capacity
Set<Integer> set = new HashSet<>(list);  // From collection
```

### Operations
```java
set.add(x)           // Add element (false if duplicate)
set.remove(x)        // Remove element
set.contains(x)      // Check membership
set.size()           // Get size
set.isEmpty()        // Check if empty
set.clear()          // Remove all
```

### Set Operations
```java
// Union
Set<Integer> union = new HashSet<>(set1);
union.addAll(set2);

// Intersection
Set<Integer> inter = new HashSet<>(set1);
inter.retainAll(set2);

// Difference
Set<Integer> diff = new HashSet<>(set1);
diff.removeAll(set2);
```

### Deduplication
```java
// Array to unique elements
Set<Integer> unique = new HashSet<>(Arrays.asList(arr));

// Back to array
Integer[] array = unique.toArray(new Integer[0]);
```

---

## 11. Key Insight

HashSet is your go-to for **O(1) membership checks** and **deduplication**! Remember: **no order**, **no duplicates**, and elements must have proper **equals() and hashCode()** implementations!
