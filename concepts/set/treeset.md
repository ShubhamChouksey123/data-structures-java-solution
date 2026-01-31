# Java TreeSet Concepts

## 2. What is TreeSet?

**Type**: Sorted collection of unique elements
**Package**: `java.util.TreeSet`
**Implementation**: Red-Black Tree (self-balancing BST)

**Key Characteristics**:
- **Sorted order** - maintains elements in natural or custom order
- **No duplicates** - enforces uniqueness
- **O(log n) operations** - add, remove, contains
- **Does NOT allow null** ❌
- **Not thread-safe** - use Collections.synchronizedSortedSet()

---

## 3. Time & Space Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **add** | O(log n) | Add element in sorted position |
| **remove** | O(log n) | Remove element |
| **contains** | O(log n) | Check membership |
| **first/last** | O(log n) | Get smallest/largest |
| **floor/ceiling** | O(log n) | NavigableSet operations |
| **size** | O(1) | Get size |

**Space**: O(n)

**All operations O(log n)** due to tree structure

---

## 4. Common Operations & Methods

### Basic Operations

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Add element** | `set.add(e)` | O(log n) | Returns false if duplicate |
| **Remove element** | `set.remove(e)` | O(log n) | Returns true if removed |
| **Contains** | `set.contains(e)` | O(log n) | Check membership |
| **Get size** | `set.size()` | O(1) | Number of elements |
| **First** | `set.first()` | O(log n) | Smallest element |
| **Last** | `set.last()` | O(log n) | Largest element |

### NavigableSet Operations

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Floor** | `set.floor(e)` | O(log n) | Largest ≤ e |
| **Ceiling** | `set.ceiling(e)` | O(log n) | Smallest ≥ e |
| **Lower** | `set.lower(e)` | O(log n) | Largest < e |
| **Higher** | `set.higher(e)` | O(log n) | Smallest > e |
| **Poll first** | `set.pollFirst()` | O(log n) | Remove & return smallest |
| **Poll last** | `set.pollLast()` | O(log n) | Remove & return largest |

---

## 5. Core Characteristics/Creation

```java
// Natural ordering (Comparable)
TreeSet<Integer> set = new TreeSet<>();

// Custom comparator
TreeSet<Integer> set = new TreeSet<>((a, b) -> b - a);  // Descending

// From collection
TreeSet<Integer> set = new TreeSet<>(Arrays.asList(3, 1, 2));  // [1, 2, 3]

// Add elements (automatically sorted)
set.add(5);
set.add(1);
set.add(3);  // Set is now [1, 3, 5]

// NavigableSet operations
int first = set.first();       // 1 (smallest)
int last = set.last();         // 5 (largest)
int floor = set.floor(4);      // 3 (largest ≤ 4)
int ceiling = set.ceiling(2);  // 3 (smallest ≥ 2)

// Range views
SortedSet<Integer> sub = set.subSet(2, 5);  // [2, 5) = [3]
```

---

## 6. Comparison with Similar Structures

| Feature | TreeSet | HashSet | LinkedHashSet |
|---------|---------|---------|---------------|
| **Order** | Sorted ⭐ | No order | Insertion order |
| **Performance** | O(log n) | O(1) ⭐ | O(1) ⭐ |
| **Null** | ❌ No null | ✅ One null | ✅ One null |
| **Use case** | Sorted, ranges | Fast lookups | Ordered lookups |

**When to Use TreeSet**: Need sorted order, range queries, floor/ceiling operations

**When to Use HashSet**: Just need fast lookups without order

**When to Use LinkedHashSet**: Need insertion order preserved

---

## 7. Common Patterns & Use Cases

### Pattern 1: Maintain Sorted Order

**Use Case**: Keep elements sorted automatically

```java
TreeSet<Integer> set = new TreeSet<>();
set.add(5);
set.add(1);
set.add(3);
// Set is [1, 3, 5] automatically
```

**Complexity**: O(log n) per insertion

---

### Pattern 2: Range Queries

**Use Case**: Find elements in range

```java
TreeSet<Integer> set = new TreeSet<>(Arrays.asList(1, 3, 5, 7, 9));

// Elements in range [3, 8)
SortedSet<Integer> range = set.subSet(3, 8);  // [3, 5, 7]

// Elements < 5
SortedSet<Integer> head = set.headSet(5);  // [1, 3]

// Elements >= 5
SortedSet<Integer> tail = set.tailSet(5);  // [5, 7, 9]
```

**Complexity**: O(log n) to find boundaries

---

### Pattern 3: Floor and Ceiling

**Use Case**: Find closest elements

```java
TreeSet<Integer> set = new TreeSet<>(Arrays.asList(1, 3, 5, 7, 9));

int floor = set.floor(6);      // 5 (largest ≤ 6)
int ceiling = set.ceiling(6);  // 7 (smallest ≥ 6)
int lower = set.lower(5);      // 3 (largest < 5)
int higher = set.higher(5);    // 7 (smallest > 5)
```

**Complexity**: O(log n)

---

### Pattern 4: Meeting Rooms / Interval Scheduling

**Use Case**: Track intervals in sorted order

```java
TreeSet<int[]> intervals = new TreeSet<>((a, b) -> a[0] - b[0]);
intervals.add(new int[]{1, 3});
intervals.add(new int[]{2, 4});
// Automatically sorted by start time
```

**Complexity**: O(log n) per insertion

---

## 8. Common Gotchas & Best Practices

### 1. Null Not Allowed

**❌ WRONG**:
```java
TreeSet<Integer> set = new TreeSet<>();
set.add(null);  // NullPointerException
```

**✅ CORRECT**:
```java
// Don't use null with TreeSet
// Use HashSet if null needed
```

---

### 2. Comparator Consistency

**❌ WRONG**:
```java
TreeSet<int[]> set = new TreeSet<>((a, b) -> a[0] - b[0]);  // Can overflow
```

**✅ CORRECT**:
```java
TreeSet<int[]> set = new TreeSet<>((a, b) -> Integer.compare(a[0], b[0]));
```

---

### 3. Elements Must Be Comparable

**❌ WRONG**:
```java
class Person { }  // Doesn't implement Comparable
TreeSet<Person> set = new TreeSet<>();
set.add(new Person());  // ClassCastException
```

**✅ CORRECT**:
```java
// Provide comparator
TreeSet<Person> set = new TreeSet<>((a, b) -> a.age - b.age);
// Or implement Comparable
```

---

## 9. Interview Tips

### When to Use TreeSet
✅ Need sorted unique elements
✅ Range queries (subSet, headSet, tailSet)
✅ Floor/ceiling operations
✅ Kth smallest/largest queries
✅ Interval scheduling problems

### When NOT to Use TreeSet
❌ Just need fast lookups → Use HashSet (O(1))
❌ Don't care about order → Use HashSet
❌ Need to store null → Use HashSet

### Remember
- **Sorted order** - maintains elements sorted
- **O(log n)** for add/remove/contains
- **No null** allowed
- **NavigableSet** - provides floor, ceiling, etc.
- Elements must be **Comparable** or use custom **Comparator**
- Use `Integer.compare()` to avoid overflow
- **subSet(from, to)** - range [from, to) exclusive end

### Time Complexity Quick Check
- Add/Remove/Contains: O(log n)
- First/Last: O(log n)
- Floor/Ceiling: O(log n)
- Range queries: O(log n + m) where m = result size

---

## 10. Quick Reference

### Creation
```java
TreeSet<Integer> set = new TreeSet<>();  // Natural order
TreeSet<Integer> set = new TreeSet<>((a, b) -> b - a);  // Custom
TreeSet<Integer> set = new TreeSet<>(list);  // From collection
```

### Basic Operations
```java
set.add(x)           // Add element
set.remove(x)        // Remove element
set.contains(x)      // Check membership
set.size()           // Get size
set.first()          // Smallest element
set.last()           // Largest element
```

### NavigableSet Operations
```java
set.floor(x)         // Largest ≤ x
set.ceiling(x)       // Smallest ≥ x
set.lower(x)         // Largest < x
set.higher(x)        // Smallest > x
set.pollFirst()      // Remove & return smallest
set.pollLast()       // Remove & return largest
```

### Range Views
```java
set.subSet(from, to)     // [from, to)
set.headSet(to)          // < to
set.tailSet(from)        // ≥ from
```

### Iteration (Sorted Order)
```java
for (int num : set) {
    // Visits in ascending order
}

// Descending order
for (int num : set.descendingSet()) {
    // Visits in descending order
}
```

---

## 11. Key Insight

TreeSet keeps elements **automatically sorted** with **O(log n)** operations! Perfect for problems needing **sorted unique elements**, **range queries**, or **floor/ceiling** operations. Remember: **no null allowed**!
