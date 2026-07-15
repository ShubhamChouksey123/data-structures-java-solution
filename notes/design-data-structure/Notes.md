# Design Data Structure

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Build a custom class with specific operations under strict time/space constraints — pick and combine the right underlying structures**

### Keywords
- "design a ..."
- "implement a class that supports ..."
- "O(1) get and put"
- "least/most recently used", "least frequently used"
- "get value at a snapshot / version"

### Examples
- LRU / LFU Cache
- Design Twitter (news feed)
- Design Browser History
- Design Circular Deque
- Snapshot Array

---

## Core Concept

Design problems are about **composition**: no single structure gives you everything, so combine two that cover each other's weakness.

**Key Insight**: A HashMap gives O(1) lookup but no ordering; a linked list / array gives ordering but O(n) lookup. Pair them so each operation hits its O(1)/O(log n) target.

**Common pairings**:
| Requirement | Structure combo |
|-------------|-----------------|
| O(1) get/put + recency order | HashMap + Doubly Linked List |
| O(1) with frequency tiers | HashMap + freq→DLL buckets |
| Value history by version | array of `(version, value)` + binary search |
| Merge K ordered streams | HashMap of lists + heap |
| Fixed-size ring / both ends | array + head/tail pointers |

---

## Pattern 1: HashMap + Doubly Linked List

**Use Case**: O(1) access **and** O(1) reordering (LRU Cache)

**Algorithm**:
1. HashMap maps `key → node`, giving O(1) lookup
2. Doubly linked list keeps usage order (most recent near head)
3. On access, unlink the node and move it to the head — O(1) because DLL nodes know both neighbors
4. On overflow, evict the tail (least recently used)

**Complexity**: O(1) time per op, O(capacity) space

**Why a DLL?** Removing an arbitrary node in O(1) needs pointers to *both* neighbors — a singly linked list can't do it without a scan.

---

## Pattern 2: HashMap + Frequency Buckets

**Use Case**: O(1) eviction by lowest use count (LFU Cache)

**Algorithm**:
1. `key → (value, freq)` map for lookup
2. `freq → DLL of keys` map, each bucket ordered by recency
3. Track `minFreq`; on access bump a key from bucket `f` to `f+1`
4. Evict from the `minFreq` bucket's tail when full

**Complexity**: O(1) time per op, O(capacity) space

---

## Pattern 3: Versioned Values + Binary Search

**Use Case**: Read a value as of a given snapshot/version (Snapshot Array)

**Algorithm**:
1. For each index store a list of `(snapId, value)` in increasing snapId order
2. `set` appends/overwrites the entry for the current snapId
3. `get(index, snap)` binary-searches for the largest `snapId ≤ snap`

**Complexity**: `set` O(1), `get` O(log k), space O(total writes) — far cheaper than copying the whole array per snapshot

---

## Pattern 4: Stacks / Circular Buffer

**Use Case**: Ordered navigation or fixed-capacity ends

- **Browser History**: keep an array + current pointer (or two stacks — back/forward). `visit` truncates forward history.
- **Circular Deque**: fixed array with `front`/`rear` indices advanced modulo capacity — O(1) push/pop at both ends, no shifting.

**Complexity**: O(1) per operation

---

## Common Mistakes

### ❌ Mistake 1: Singly Linked List for LRU

A singly linked list can't unlink an arbitrary node in O(1) (no `prev`). Use a **doubly** linked list, or an ordered map like `LinkedHashMap`.

### ❌ Mistake 2: Forgetting to Update Order on `get`

In LRU/LFU, a **read** also counts as a use — move/bump the node on `get`, not just on `put`. Missing this corrupts eviction order.

### ❌ Mistake 3: Copying State per Snapshot

Snapshot Array should store only *changes* with version stamps + binary search. Deep-copying the array each `snap()` is O(n) space per snapshot and TLEs.

### ❌ Mistake 4: Dummy Nodes Skipped

Use dummy `head`/`tail` sentinels in the DLL so insert/remove never special-case null neighbors.

---

## Problems

- [x] [Design Twitter](https://leetcode.com/problems/design-twitter/) - Medium *(HashMap + heap merge)*
- [x] [Design Browser History](https://leetcode.com/problems/design-browser-history/) - Medium *(array/stacks)*
- [ ] [Design Circular Deque](https://leetcode.com/problems/design-circular-deque/) - Medium *(ring buffer)*
- [ ] [Snapshot Array](https://leetcode.com/problems/snapshot-array/) - Medium *(versioned + binary search)*
- [x] [LRU Cache](https://leetcode.com/problems/lru-cache/) - Medium ⭐ **IMPORTANT** ⭐
- [ ] [LFU Cache](https://leetcode.com/problems/lfu-cache/) - Hard

### LRU Cache ⭐ **IMPORTANT** ⭐

**Problem**: [LRU Cache](https://leetcode.com/problems/lru-cache/) - Medium

**Why Important**: The canonical design problem; the HashMap + doubly-linked-list combo appears everywhere and must be O(1) for both `get` and `put`.

**Approach**:
1. HashMap `key → node` for O(1) lookup
2. DLL with dummy `head`/`tail`; most-recent sits right after `head`
3. `get`: return value and move node to front
4. `put`: upsert, move to front, evict tail's predecessor when over capacity

**Complexity**: O(1) time per op, O(capacity) space

**Solution**:

```java
class LRUCache {
    private static class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, 0), tail = new Node(0, 0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;          // dummy sentinels avoid null checks
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        moveToFront(node);         // a read counts as a use
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            moveToFront(node);
            return;
        }
        if (map.size() == capacity) {
            Node lru = tail.prev;  // least recently used
            remove(lru);
            map.remove(lru.key);
        }
        Node fresh = new Node(key, value);
        map.put(key, fresh);
        addToFront(fresh);
    }

    private void addToFront(Node n) {
        n.prev = head;
        n.next = head.next;
        head.next.prev = n;
        head.next = n;
    }

    private void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void moveToFront(Node n) {
        remove(n);
        addToFront(n);
    }
}
```

**Key Points**:
- **Dummy head/tail** remove all null-neighbor edge cases
- Both `get` and `put` refresh recency by moving the node to the front
- Evict `tail.prev` (the real LRU node), never the `tail` sentinel
- `LinkedHashMap` with `accessOrder=true` is the shortcut if custom code isn't required

---

## Key Takeaways

1. **Compose structures** — HashMap for lookup + list/array for order
2. **DLL for O(1) arbitrary removal** — needs both `prev` and `next`
3. **Reads mutate state** in LRU/LFU — refresh order on `get` too
4. **Version + binary search** beats per-snapshot copies (Snapshot Array)
5. **Ring buffer** = fixed array + modulo pointers for O(1) both-ends ops
6. **Dummy sentinels** keep linked-list edits branch-free

---

> **[← Back to Overview](../README.md)** | **[Greedy ←](../greedy/Notes.md)**
