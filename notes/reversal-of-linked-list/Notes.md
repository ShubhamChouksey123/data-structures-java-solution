# Reversal of Linked List (In-place)

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Reverse entire list, reverse sublist (m to n), or reverse in groups (every k nodes)**

### Keywords
- "reverse linked list"
- "in-place", "O(1) space"
- "reverse between positions m and n"
- "reverse k nodes", "reverse in groups"

### Examples
- Reverse entire linked list
- Reverse nodes between positions m and n
- Reverse nodes in k-groups
- Swap pairs of nodes

---

## Core Concept

Use three pointers (prev, curr, next) to reverse links in-place by changing `next` pointers one by one.

**Key Insight**: Break the link, point backwards, move forward. Repeat until done.

**Complexity**: O(n) time, O(1) space

---

## Pattern 1: Reverse Entire List

**Use Case**: Reverse all nodes in the linked list

**Algorithm**:
1. Initialize `prev = null`, `curr = head`
2. While `curr != null`:
   - Save `next = curr.next`
   - Reverse link: `curr.next = prev`
   - Move forward: `prev = curr`, `curr = next`
3. Return `prev` (new head)

**Complexity**: O(n) time, O(1) space

### Template

```java
public ListNode reverseList(ListNode head) {
    ListNode prev = null, curr = head;

    while (curr != null) {
        ListNode next = curr.next;  // Save next
        curr.next = prev;           // Reverse link
        prev = curr;                // Move prev forward
        curr = next;                // Move curr forward
    }

    return prev;  // New head
}
```

### Visual Example

```
Original: 1 → 2 → 3 → 4 → null

Step 1: prev=null, curr=1
        null ← 1   2 → 3 → 4 → null

Step 2: prev=1, curr=2
        null ← 1 ← 2   3 → 4 → null

Step 3: prev=2, curr=3
        null ← 1 ← 2 ← 3   4 → null

Step 4: prev=3, curr=4
        null ← 1 ← 2 ← 3 ← 4

Result: 4 → 3 → 2 → 1 → null (prev = 4 is new head)
```

---

## Pattern 2: Reverse Sublist (m to n)

**Use Case**: Reverse only nodes between positions m and n (1-indexed)

**Algorithm**:
1. Move to position m-1 (node before reversal starts)
2. Save connection point (`beforeReverse`)
3. Reverse nodes from m to n using three-pointer technique
4. Reconnect: `beforeReverse.next = new head of reversed portion`
5. Connect tail of reversed portion to remaining list

**Complexity**: O(n) time, O(1) space

### Template

```java
public ListNode reverseBetween(ListNode head, int m, int n) {
    if (head == null || m == n) return head;

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode beforeReverse = dummy;

    // Move to position m-1
    for (int i = 1; i < m; i++) {
        beforeReverse = beforeReverse.next;
    }

    // Reverse from m to n
    ListNode prev = null, curr = beforeReverse.next;
    ListNode tailOfReversed = curr;  // Will become tail after reversal

    for (int i = 0; i <= n - m; i++) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }

    // Reconnect
    beforeReverse.next = prev;         // Connect to new head of reversed
    tailOfReversed.next = curr;        // Connect tail to remaining list

    return dummy.next;
}
```

---

## Pattern 3: Reverse in k-Groups ⭐ **IMPORTANT** ⭐

**Use Case**: Reverse nodes in groups of k; if remaining nodes < k, leave as-is

**Algorithm**:
1. Create dummy node pointing to head
2. Use prev pointer to track node before current group
3. Check if k nodes available from prev.next
4. If yes, reverse those k nodes using helper method
5. Update prev to point to last node of reversed group
6. Repeat until less than k nodes remain

**Complexity**: O(n) time, O(1) space (iterative approach)

### Template

```java
class Solution {

    private boolean hasKElements(ListNode head, int k) {
        int count = 0;
        while (head != null) {
            head = head.next;
            count++;
            if (count == k) {
                return true;
            }
        }
        return false;
    }

    private ListNode reverseNextKNodes(ListNode prevNode, int k) {
        ListNode firstNode = prevNode.next;
        ListNode prev = null;
        ListNode cur = null;
        ListNode next = prevNode.next;

        while (k > 0) {
            k--;
            cur = next;
            next = next.next;
            cur.next = prev;
            prev = cur;
        }

        firstNode.next = next;
        prevNode.next = cur;

        return firstNode;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;

        while (prev != null && prev.next != null) {
            if (!hasKElements(prev.next, k)) {
                break;
            }
            prev = reverseNextKNodes(prev, k);
        }

        return dummy.next;
    }
}
```

**Visual**: `1→2→3→4→5→6` with k=3 → Reverse [1,2,3] → `3→2→1` → Reverse [4,5,6] → `6→5→4` → Result: `3→2→1→6→5→4`

---

## Common Mistakes

### ❌ Losing Reference to Next Node

```java
// WRONG - loses reference to rest of list
curr.next = prev;
curr = curr.next;  // Now curr = prev, not original next!

// CORRECT - save next before breaking link
ListNode next = curr.next;
curr.next = prev;
curr = next;
```

### ❌ Not Using Dummy Node for Edge Cases

```java
// WRONG - fails when reversing from head (m=1)
ListNode beforeReverse = head;  // No node before head

// CORRECT - dummy node handles all cases
ListNode dummy = new ListNode(0);
dummy.next = head;
ListNode beforeReverse = dummy;
```

### ❌ Off-by-One in Loop Count

```java
// WRONG - reverses m to n-1 (one less node)
for (int i = 0; i < n - m; i++) {
    // reverse
}

// CORRECT - reverses m to n (inclusive)
for (int i = 0; i <= n - m; i++) {
    // reverse
}
```

---

## Problems

- [x] [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) - Easy
- [x] [Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/) - Medium
- [x] [Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) - Hard ⭐ **IMPORTANT** ⭐

### Reverse Nodes in k-Group ⭐ **IMPORTANT** ⭐

**Problem**: [Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/) - Hard

**Why Important**: Common FAANG question combining reversal with recursion/iteration, tests edge case handling

**Approach**:
1. Create dummy node pointing to head for easier edge case handling
2. Use prev pointer to track node before current group
3. Check if k nodes available from prev.next using helper method
4. If yes, reverse those k nodes using reverseNextKNodes helper
5. Update prev to point to last node of reversed group (originally first node)
6. Repeat until less than k nodes remain

**Complexity**: O(n) time, O(1) space (iterative approach with dummy node)

**Solution**:

```java
class Solution {

    private boolean hasKElements(ListNode head, int k) {
        int count = 0;
        while (head != null) {
            head = head.next;
            count++;
            if (count == k) {
                return true;
            }
        }
        return false;
    }

    private ListNode reverseNextKNodes(ListNode prevNode, int k) {
        ListNode firstNode = prevNode.next;
        ListNode prev = null;
        ListNode cur = null;
        ListNode next = prevNode.next;

        while (k > 0) {
            k--;
            cur = next;
            next = next.next;
            cur.next = prev;
            prev = cur;
        }

        firstNode.next = next;
        prevNode.next = cur;

        return firstNode;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy;

        while (prev != null && prev.next != null) {
            if (!hasKElements(prev.next, k)) {
                break;
            }
            prev = reverseNextKNodes(prev, k);
        }

        return dummy.next;
    }
}
```

**Key Points**:
- Dummy node simplifies edge cases (when reversing from head)
- `hasKElements` helper checks if k nodes available before reversing
- `reverseNextKNodes` helper reverses k nodes starting from prevNode.next
- Original first node becomes last node of reversed group and is returned as new prev
- Iterative approach uses O(1) space vs O(n/k) space for recursion
- More complex pointer management but better space efficiency

**Example**: Input: `1→2→3→4→5`, k=2
- Reverse [1,2]: `2→1→...`
- Reverse [3,4]: `2→1→4→3→5`
- [5] unchanged (less than k=2 nodes)
- Output: `2→1→4→3→5`

---

## Key Takeaways

1. **Three pointers**: `prev`, `curr`, `next` for in-place reversal
2. **Save next first**: Always save `next = curr.next` before breaking link
3. **Dummy node**: Use dummy node for edge cases (reversing from head)
4. **Return prev**: After reversal, `prev` points to new head
5. **O(n) time, O(1) space**: Optimal for iterative reversal (O(n/k) space for k-group recursion)
6. **Reconnection**: Remember to reconnect reversed portion to remaining list

---

> **[← Back to Overview](../README.md)** | **[Cyclic Sort →](../cyclic-sort/Notes.md)**
