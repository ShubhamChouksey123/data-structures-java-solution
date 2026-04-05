# Java Pair Concepts

## What is Pair?

**Type**: Container for two values
**Package**: **NOT in standard Java!** Available via:
- `javafx.util.Pair` (JavaFX)
- Custom implementation (recommended for interviews)

**Key Characteristics**:
- Holds exactly **two generic values** (key, value)
- **Immutable** in JavaFX
- Common in **coordinates** and **priority queues**

---

## Time Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **Create** | O(1) | New pair |
| **getKey()** | O(1) | First element |
| **getValue()** | O(1) | Second element |
| **equals()** | O(1) | Compare both |
| **hashCode()** | O(1) | Hash both |

**Space**: O(1) per pair

---

## Common Operations & Methods

| Operation | Method | Notes |
|-----------|--------|-------|
| **Create** | `new Pair<>(k, v)` | Constructor |
| **Get first** | `getKey()` | Returns K |
| **Get second** | `getValue()` | Returns V |
| **Compare** | `equals(other)` | Both must match |

---

## Basic Operations

```java
// Custom implementation (recommended)
class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(key, p.key) && Objects.equals(value, p.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}

// Basic Operations Examples

// 1. Create a Pair
Pair<Integer, Integer> coordinates = new Pair<>(3, 4);
Pair<String, Integer> nameAge = new Pair<>("Alice", 25);
Pair<Integer, String> idName = new Pair<>(101, "Product");

// 2. Access first element (key)
int x = coordinates.getKey();           // x = 3
String name = nameAge.getKey();         // name = "Alice"
int id = idName.getKey();               // id = 101

// 3. Access second element (value)
int y = coordinates.getValue();         // y = 4
int age = nameAge.getValue();           // age = 25
String productName = idName.getValue(); // productName = "Product"

// 4. Use in collections
List<Pair<Integer, Integer>> points = new ArrayList<>();
points.add(new Pair<>(0, 0));
points.add(new Pair<>(1, 2));

// 5. Compare pairs
Pair<Integer, Integer> p1 = new Pair<>(1, 2);
Pair<Integer, Integer> p2 = new Pair<>(1, 2);
boolean isEqual = p1.equals(p2);        // true
```

---

## Comparison with Similar Structures

| Feature | Pair | Map.Entry | Custom Class |
|---------|------|-----------|--------------|
| **Elements** | Exactly 2 | Key-Value | Variable |
| **Standard Java** | ❌ No | ✅ Yes | ✅ Yes |
| **Use Case** | Temporary | Map entries | Domain model |

---

## Common Gotchas

### 1. Implement equals() and hashCode()

**❌ WRONG**:
```java
class Pair<K, V> {
    K key; V value;
    // Missing equals() and hashCode()!
}
// Using in HashSet/HashMap will fail
```

**✅ CORRECT**:
```java
@Override
public boolean equals(Object o) {
    if (!(o instanceof Pair)) return false;
    Pair<?, ?> p = (Pair<?, ?>) o;
    return Objects.equals(key, p.key) && Objects.equals(value, p.value);
}

@Override
public int hashCode() {
    return Objects.hash(key, value);
}
```

---

### 2. Immutability Required for Collections

**❌ WRONG**:
```java
Pair<Integer, Integer> p = new MutablePair<>(1, 2);
set.add(p);
p.setKey(3); // Changes hashCode! Lost in set!
```

**✅ CORRECT**:
```java
// Use immutable Pair (final fields, no setters)
Pair<Integer, Integer> p = new Pair<>(1, 2);
set.add(p); // Safe - can't be modified
```

---

## Interview Tips

### When to Use
✅ Grid coordinates: `Pair<Integer, Integer>` for (row, col)
✅ Priority queues: `Pair<Element, Priority>`
✅ Returning two values from method
✅ Dijkstra's: `Pair<Node, Distance>`

### When NOT to Use
❌ More than 2 elements (use array/custom class)
❌ Need semantic meaning (use domain class)
❌ Standard Java only (not in java.util)

### Remember
- **Define custom Pair** for interviews (no JavaFX dependency)
- **Always implement equals() and hashCode()**
- Use `getKey()` and `getValue()` (not first/second)
- Keep it **immutable** (final fields, no setters)

---

## Quick Reference

### Definition
```java
class Pair<K, V> {
    private final K key;
    private final V value;
    public Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(key, p.key) && Objects.equals(value, p.value);
    }

    @Override
    public int hashCode() { return Objects.hash(key, value); }
}
```

### Common Usage
```java
// Coordinates
Pair<Integer, Integer> point = new Pair<>(row, col);

// Priority Queue (sort by value)
PriorityQueue<Pair<Integer, Integer>> pq =
    new PriorityQueue<>(Comparator.comparingInt(Pair::getValue));

// Return multiple values
public Pair<Integer, Integer> getMinMax(int[] arr) {
    return new Pair<>(min, max);
}
```

---

**Key Insight**: Java doesn't have `java.util.Pair`! Define your own with `equals()` and `hashCode()`. Use for **temporary groupings** in BFS/DFS and priority queues.

