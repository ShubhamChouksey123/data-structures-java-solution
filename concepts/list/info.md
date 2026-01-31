# Java List Concepts

## 2. What is List?

**Type**: Ordered collection interface
**Package**: `java.util.List`
**Main Implementation**: ArrayList (use 95% of the time)

**Key Characteristics**:
- **Ordered** - maintains insertion order
- **Index-based** - 0-indexed access
- **Allows duplicates** ✅
- **Allows null** ✅
- **Resizable** - grows dynamically

---

## 3. Time & Space Complexity

### ArrayList

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **get(i)** | O(1) | Direct array access |
| **add(e)** | O(1) amortized | Add at end, resize when needed |
| **add(i, e)** | O(n) | Shift elements |
| **remove(i)** | O(n) | Shift elements |
| **contains(e)** | O(n) | Linear search |
| **indexOf(e)** | O(n) | Linear search |
| **size()** | O(1) | Cached field |

**Space**: O(n)

**Growth**: 1.5x when full (10 → 15 → 22 → 33)

---

## 4. Common Operations & Methods

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Create (mutable)** | `new ArrayList<>()` | O(1) | Default capacity 10 |
| **Create (capacity)** | `new ArrayList<>(100)` | O(1) | Avoid resizing |
| **Create (from collection)** | `new ArrayList<>(collection)` | O(n) | Mutable copy |
| **Create (fixed-size)** | `Arrays.asList(1, 2, 3)` | O(n) | Can modify, not add/remove |
| **Create (immutable)** | `List.of(1, 2, 3)` | O(n) | Cannot modify (Java 9+) |
| **Add at end** | `list.add(e)` | O(1) | Amortized |
| **Add at index** | `list.add(i, e)` | O(n) | Shifts elements |
| **Add all** | `list.addAll(collection)` | O(m) | m = collection size |
| **Get element** | `list.get(i)` | O(1) | Direct array access |
| **Set element** | `list.set(i, e)` | O(1) | Replace at index |
| **Remove by index** | `list.remove(i)` | O(n) | Shifts elements |
| **Remove by value** | `list.remove(Object)` | O(n) | Finds then removes |
| **Remove if** | `list.removeIf(predicate)` | O(n) | Conditional removal (Java 8+) |
| **Clear all** | `list.clear()` | O(n) | Removes all elements |
| **Contains** | `list.contains(e)` | O(n) | Linear search |
| **Index of** | `list.indexOf(e)` | O(n) | Returns -1 if not found |
| **Last index of** | `list.lastIndexOf(e)` | O(n) | Searches from end |
| **Size** | `list.size()` | O(1) | Cached field |
| **Is empty** | `list.isEmpty()` | O(1) | Checks size == 0 |
| **Sort ascending** | `Collections.sort(list)` | O(n log n) | Natural order |
| **Sort descending** | `list.sort(Collections.reverseOrder())` | O(n log n) | Reverse order |
| **Sort custom** | `list.sort(comparator)` | O(n log n) | Custom comparator |
| **Reverse** | `Collections.reverse(list)` | O(n) | In-place reversal |

**Array ↔ List Conversion**:
```java
// Array → List
int[] arr = {1, 2, 3};
List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

// List → Array
int[] arr = list.stream().mapToInt(i -> i).toArray();
Integer[] arr = list.toArray(new Integer[0]);
```

---

## 5. Core Characteristics/Creation

**Internal**: Resizable array
**Initial Capacity**: 10
**Growth Factor**: 1.5x

```java
// Mutable lists
List<Integer> list = new ArrayList<>();
List<Integer> list = new ArrayList<>(1000);  // Avoid resizing

// Immutable (Java 9+) - cannot add/remove/modify
List<Integer> list = List.of(1, 2, 3);

// Fixed-size - can modify values, but not add/remove
List<Integer> list = Arrays.asList(1, 2, 3);
```

---

## 6. Comparison with Similar Structures

| Need | Use | Why |
|------|-----|-----|
| **General purpose list** | ArrayList ⭐ | O(1) access, good performance |
| **Random access by index** | ArrayList | O(1) get/set operations |
| **Frequent insertions at beginning** | LinkedList | O(1) addFirst/removeFirst |
| **Queue/Stack operations** | ArrayDeque | Better than LinkedList |
| **Unique elements** | HashSet | No duplicates, O(1) lookup |
| **Sorted elements** | TreeSet | Maintains sorted order |
| **Key-value pairs** | HashMap | Associate keys with values |

**LinkedList**: See `linkedlist.md` for detailed LinkedList concepts

---

## 7. Common Patterns & Use Cases

### Pattern 1: Building Dynamic Arrays

**Use Case**: Collecting results when size is unknown

```java
List<Integer> result = new ArrayList<>();
for (int i = 0; i < n; i++) {
    if (condition) {
        result.add(i);  // O(1) amortized
    }
}
// Convert to array if needed
int[] array = result.stream().mapToInt(i -> i).toArray();
```


---

### Pattern 2: Safe Removal During Iteration

**Use Case**: Filter elements while iterating

```java
List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

// Option 1: removeIf (Java 8+) - Recommended
list.removeIf(x -> x > 3);

// Option 2: Iterator
Iterator<Integer> it = list.iterator();
while (it.hasNext()) {
    if (it.next() > 3) {
        it.remove();  // Safe removal
    }
}

// Option 3: Stream filter (creates new list)
List<Integer> filtered = list.stream()
                             .filter(x -> x <= 3)
                             .collect(Collectors.toList());
```

**Complexity**: O(n)

---

## 8. Common Gotchas & Best Practices

### 1. Arrays.asList() is Fixed-Size

**❌ WRONG**:
```java
List<Integer> list = Arrays.asList(1, 2, 3);
list.add(4);  // UnsupportedOperationException
```

**✅ CORRECT**:
```java
List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
list.add(4);  // Works
```

---

### 2. List.of() is Immutable

**❌ WRONG**:
```java
List<Integer> list = List.of(1, 2, 3);
list.set(0, 10);  // UnsupportedOperationException
```

**✅ CORRECT**:
```java
List<Integer> list = new ArrayList<>(List.of(1, 2, 3));
list.set(0, 10);  // Works
```

---

### 3. ConcurrentModificationException

**❌ WRONG**:
```java
for (Integer x : list) {
    list.remove(x);  // ConcurrentModificationException
}
```

**✅ CORRECT**:
```java
list.removeIf(x -> x > 5);  // Recommended
```

---

### 4. remove() Ambiguity with Integer

**Gotcha**: `remove(int)` vs `remove(Object)`

```java
List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

list.remove(0);                   // Removes at INDEX 0 → [1, 2, 3]
list.remove(Integer.valueOf(0));  // Removes VALUE 0 → [1, 2, 3]
```

**Best Practice**: Be explicit about intent using `Integer.valueOf()` for values

---

### 5. Primitive Arrays to List

**❌ WRONG**:
```java
int[] arr = {1, 2, 3};
List<Integer> list = Arrays.asList(arr);  // Creates List<int[]>, not List<Integer>!
```

**✅ CORRECT**:
```java
int[] arr = {1, 2, 3};
List<Integer> list = Arrays.stream(arr)
                           .boxed()
                           .collect(Collectors.toList());
```

---

## 9. Interview Tips

### When to Use List
✅ Need ordered collection with duplicates
✅ Frequent access by index (ArrayList)
✅ Building dynamic arrays
✅ Collections operations (sort, reverse)

### When NOT to Use List
❌ Need unique elements → Use HashSet
❌ Need key-value pairs → Use HashMap
❌ Need O(1) search → Use HashSet/HashMap
❌ Frequent insertions at beginning → Use LinkedList

### Remember
- **ArrayList is default** - use 95% of the time
- Grows by **1.5x** (10 → 15 → 22 → 33)
- `Arrays.asList()` = **fixed-size** (can modify, not add/remove)
- `List.of()` = **immutable** (cannot modify at all)
- Use `removeIf()` for safe removal during iteration
- `remove(int)` vs `remove(Object)` - be explicit with `Integer.valueOf()`
- For primitive arrays, use streams with `.boxed()`

### Time Complexity Quick Check
- Access: O(1)
- Add at end: O(1) amortized
- Insert/Remove: O(n)
- Search: O(n)

---

## 10. Quick Reference

### Creation
```java
new ArrayList<>()                              // Mutable, default capacity
new ArrayList<>(capacity)                      // Mutable, with capacity
new ArrayList<>(Arrays.asList(1, 2, 3))        // Mutable copy
Arrays.asList(1, 2, 3)                         // Fixed-size
List.of(1, 2, 3)                               // Immutable
```

### Operations
```java
list.add(x)           // O(1) - add at end
list.get(i)           // O(1) - get at index
list.set(i, x)        // O(1) - replace
list.remove(i)        // O(n) - remove at index
list.contains(x)      // O(n) - check existence
list.indexOf(x)       // O(n) - find index
list.size()           // O(1) - get size
```

### Sort
```java
Collections.sort(list)                         // Ascending
list.sort(Collections.reverseOrder())          // Descending
list.sort((a, b) -> a.field - b.field)         // Custom
```

### Conversion
```java
// Array → List
Arrays.stream(arr).boxed().collect(Collectors.toList())

// List → Array
list.stream().mapToInt(i -> i).toArray()
list.toArray(new Integer[0])
```

---

## 11. Key Insight

Use **ArrayList** by default for 95% of use cases. It provides O(1) random access and excellent overall performance!
