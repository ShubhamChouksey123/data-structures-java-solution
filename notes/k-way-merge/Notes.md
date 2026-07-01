# K-way Merge

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Merge multiple sorted arrays, lists, or streams efficiently**

### Keywords
- "merge K sorted"
- "smallest range"
- "K pairs with smallest sums"
- "sorted matrix"

### Examples
- Merge K sorted linked lists
- Smallest range covering elements from K lists
- K pairs with smallest sums
- Kth smallest element in a sorted matrix

---

## Core Concept

Seed a **min-heap** with the first element of each of the K sorted inputs. Repeatedly poll the smallest and push the *next* element from the **same** input — the heap always holds one candidate per input.

**Key Insight**: Finding the min across K inputs costs O(log K) with a heap vs O(K) by scanning.

**Complexity**: O(N log K) time, O(K) space
- N = total elements across all inputs, K = number of inputs

**Track the source** in the heap entry: store `{value, listIndex, elementIndex}` (or the node itself for lists) so you know where to pull the next element from.

---

## Pattern 1: Merge K Sorted Lists

**Use Case**: Merge multiple sorted lists into one

**Algorithm**:
1. Push the head of each list into the min-heap (skip nulls)
2. Poll the smallest, append it to the result
3. Push `polled.next` if it exists
4. Repeat until the heap is empty

**Complexity**: O(N log K) time, O(K) space

_Full solution in [Problems](#merge-k-sorted-lists-)._

### Visual Example

```
Lists: [1→4→5], [1→3→4], [2→6]

heap [1(L1),1(L2),2(L3)] → poll 1(L1), push 4(L1)
heap [1(L2),2(L3),4(L1)] → poll 1(L2), push 3(L2)
heap [2(L3),3(L2),4(L1)] → poll 2(L3), push 6(L3)
...
Result: 1→1→2→3→4→4→5→6
```

---

## Pattern 2: Kth Smallest in Sorted Matrix

**Use Case**: Kth smallest in a row- and column-sorted matrix

**Algorithm**:
1. Push `(r, 0)` for each of the first `min(n, k)` rows
2. Poll the min K times; each poll at `(row, col)` pushes `(row, col+1)`
3. The Kth polled value is the answer

**Complexity**: O(K log K) time, O(K) space

---

## Pattern 3: Smallest Range Covering K Lists ⭐ **IMPORTANT** ⭐

**Use Case**: Smallest range `[min, max]` containing ≥1 element from each of K sorted lists

**Algorithm**:
1. Push the first element of each list; track `max` separately
2. The current range is `[heap.min, max]`
3. Poll the min, update the best range, then push the next from that list and refresh `max`
4. Stop when any list is exhausted (heap size < K → can't cover all)

**Complexity**: O(N log K) time, O(K) space

_Full solution in [Problems](#smallest-range-covering-elements-from-k-lists-)._

---

## Pattern 4: K Pairs with Smallest Sums

**Use Case**: K smallest-sum pairs, one element from each of two sorted arrays

**Algorithm**:
1. Push pairs `(nums1[i], nums2[0])` for the first `min(k, n1)` values of `i`
2. Poll the smallest-sum pair K times
3. On polling `(nums1[i], nums2[j])`, push `(nums1[i], nums2[j+1])`

**Complexity**: O(K log K) time, O(K) space

---

## Common Mistakes

### ❌ Mistake 1: Not Tracking Element Source

Store `{value, listIndex, elementIndex}` (or the node), not just the value — otherwise you can't fetch the next element from the same input.

### ❌ Mistake 2: Adding All Elements to the Heap

Keep only K elements in the heap (one per input). Dumping every element makes it O(N) space and defeats the pattern.

### ❌ Mistake 3: Wrong Comparator for Arrays

```java
new PriorityQueue<int[]>();                              // WRONG - compares references
new PriorityQueue<int[]>((a, b) -> Integer.compare(a[0], b[0]));  // CORRECT
```

### ❌ Mistake 4: Not Skipping Null/Empty Inputs

Check for `null`/empty before offering — a null head throws `NullPointerException`.

---

## Problems

- [x] [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/) - Medium
- [x] [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) - Medium
- [x] [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) - Hard ⭐ **IMPORTANT** ⭐
- [x] [Smallest Range Covering Elements from K Lists](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/) - Hard ⭐ **IMPORTANT** ⭐
- [x] [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) - Easy

### Merge k Sorted Lists ⭐ **IMPORTANT** ⭐

**Problem**: [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/) - Hard

**Why Important**: Foundation of the K-way merge pattern; frequently asked.

**Complexity**: O(N log K) time, O(K) space

**Solution**:

```java
public ListNode mergeKLists(ListNode[] lists) {
    Queue<ListNode> minHeap = new PriorityQueue<>(
        (a, b) -> Integer.compare(a.val, b.val)
    );
    for (ListNode node : lists) {
        if (node != null) minHeap.offer(node);
    }

    ListNode dummy = new ListNode(0), current = dummy;
    while (!minHeap.isEmpty()) {
        ListNode min = minHeap.poll();
        current.next = min;
        current = current.next;
        if (min.next != null) minHeap.offer(min.next);  // next from same list
    }
    return dummy.next;
}
```

**Key Points**:
- **Dummy node** simplifies result construction
- Store **nodes** (not values) to keep the list linkage
- Only K nodes in the heap at once → O(K) space

---

### Smallest Range Covering Elements from K Lists ⭐ **IMPORTANT** ⭐

**Problem**: [Smallest Range Covering Elements from K Lists](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/) - Hard

**Why Important**: Advanced application — must track both min (heap) and max (separately) with tricky range logic.

**Complexity**: O(N log K) time, O(K) space

**Solution**:

```java
public int[] smallestRange(List<List<Integer>> nums) {
    Queue<int[]> minHeap = new PriorityQueue<>(   // {value, listIndex, elementIndex}
        (a, b) -> Integer.compare(a[0], b[0])
    );
    int max = Integer.MIN_VALUE, k = nums.size();
    for (int i = 0; i < k; i++) {
        int val = nums.get(i).get(0);
        minHeap.offer(new int[]{val, i, 0});
        max = Math.max(max, val);
    }

    int rangeStart = 0, rangeEnd = Integer.MAX_VALUE;
    while (minHeap.size() == k) {                 // all K lists still represented
        int[] min = minHeap.poll();
        int minVal = min[0], listIdx = min[1], elemIdx = min[2];

        if (max - minVal < rangeEnd - rangeStart) {
            rangeStart = minVal;
            rangeEnd = max;
        }
        if (elemIdx + 1 < nums.get(listIdx).size()) {
            int next = nums.get(listIdx).get(elemIdx + 1);
            minHeap.offer(new int[]{next, listIdx, elemIdx + 1});
            max = Math.max(max, next);
        }
    }
    return new int[]{rangeStart, rangeEnd};
}
```

**Key Points**:
- Heap gives the **min**; track **max** separately as you push
- Loop only while `heap.size() == k` — a shorter list means no valid range remains
- Update `max` on push, never on poll

---

## Key Takeaways

1. **Min-heap seeded with one element per input** — poll min, push next from the same input
2. **Track the source** in each heap entry: `{value, listIndex, elementIndex}`
3. **Heap holds only K elements** → O(K) space, not O(N)
4. **Complexity**: O(N log K) — N total elements, K inputs
5. **Range problems**: heap gives the min, track the max separately

---

> **[← Back to Overview](../README.md)** | **[Top 'K' Elements →](../top-k-elements/Notes.md)**
