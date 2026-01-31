# Data Structures & Concepts Reference

This directory contains comprehensive reference documentation for Java data structures, algorithms, and core concepts. Each file follows a standardized 11-section format for consistency and quick reference.

## 📋 Guidelines

- **[GUIDELINES.md](GUIDELINES.md)** - Documentation standards and structure for creating new concept files

---

## 📚 Data Structures

### Arrays & Lists

| Data Structure | Files | Description |
|----------------|-------|-------------|
| **Array** | [info.md](array/info.md) | Fixed-size, contiguous memory arrays with O(1) access |
| **2D Array** | [2-d-array.md](array/2-d-array.md) | Matrix operations, traversal patterns, and sorting |
| **List** | [info.md](list/info.md) | ArrayList - dynamic arrays with O(1) access |
| **LinkedList** | [linkedlist.md](list/linkedlist.md) | Doubly linked list with O(1) operations at both ends |

### Primitive Types

| Data Structure | Files | Description |
|----------------|-------|-------------|
| **Character** | [info.md](char/info.md) | char primitive, ASCII operations, frequency counting |
| **String** | [info.md](string/info.md) | Immutable strings, StringBuilder, string manipulation |

### Queues, Stacks & Deques

| Data Structure | Files | Description |
|----------------|-------|-------------|
| **Queue** | [info.md](queue/info.md) | FIFO queue interface, BFS patterns, ArrayDeque vs LinkedList |
| **Deque** | [deque.md](queue/deque.md) | Double-ended queue, sliding window, monotonic deque patterns |
| **Stack** | [info.md](stack/info.md) | LIFO stack operations, DFS, monotonic stack - use Deque instead of legacy Stack |
| **PriorityQueue** | [info.md](priorityqueue/info.md) | Min/max heap, top K problems, merge K sorted lists |

### Sets

| Data Structure | Files | Description |
|----------------|-------|-------------|
| **HashSet** | ⏳ Pending | O(1) operations, no order, no duplicates |
| **LinkedHashSet** | ⏳ Pending | Maintains insertion order, O(1) operations |
| **TreeSet** | ⏳ Pending | Sorted order, O(log n) operations, implements NavigableSet |

---

## 🎯 Quick Navigation

### By Use Case

**Array Manipulation**
- [Array Basics](array/info.md) - Two pointers, sliding window, prefix sum
- [2D Arrays](array/2-d-array.md) - Matrix operations, spiral traversal

**String Processing**
- [String Operations](string/info.md) - StringBuilder, pattern matching
- [Character Operations](char/info.md) - ASCII arithmetic, frequency counting

**Tree & Graph Traversal**
- [Queue (BFS)](queue/info.md) - Level-order traversal, multi-source BFS
- [Stack (DFS)](stack/info.md) - DFS, backtracking, monotonic stack, parentheses validation

**Priority-Based Problems**
- [PriorityQueue (Heap)](priorityqueue/info.md) - Kth largest/smallest, running median

**Sliding Window Problems**
- [Deque (Monotonic)](queue/deque.md) - Sliding window maximum/minimum

**Uniqueness & Membership**
- HashSet - Fast lookups, deduplication
- TreeSet - Sorted unique elements, range queries
- LinkedHashSet - Insertion-ordered unique elements

---

## 📖 Documentation Structure

Each concept file follows this 11-section format:

1. **Title** - Data structure name
2. **What is [Concept]?** - Overview and key characteristics
3. **Time & Space Complexity** - Complexity analysis table
4. **Common Operations & Methods** - Methods with complexity in tables
5. **Core Characteristics/Creation** - Creation and basic operations
6. **Comparison with Similar Structures** - When to use what
7. **Common Patterns & Use Cases** - Interview patterns with code
8. **Common Gotchas & Best Practices** - Mistakes to avoid
9. **Interview Tips** - Key points to remember
10. **Quick Reference** - Code templates and cheat sheet
11. **Key Insight** - Main takeaway

---

## 🔑 Key Insights

- **Array**: O(1) random access, fixed size
- **ArrayList**: Use by default for 95% of list operations
- **LinkedList**: Rarely needed - use ArrayDeque instead
- **String**: Immutable - use StringBuilder for concatenation in loops
- **Character**: Use `int[26]` for lowercase letter frequency (faster than HashMap)
- **Queue**: Use ArrayDeque for BFS (FIFO)
- **Stack**: Use Deque (ArrayDeque) for DFS (LIFO) - never use legacy Stack class
- **Deque**: Swiss Army knife - replaces both Queue and Stack
- **PriorityQueue**: Top K largest → min-heap of size K
- **HashSet**: O(1) operations - use for fast lookups and deduplication
- **TreeSet**: O(log n) operations - use for sorted unique elements
- **LinkedHashSet**: O(1) operations with insertion order maintained

---

## 📝 Contributing

When adding new concept documentation:

1. Follow the structure defined in [GUIDELINES.md](GUIDELINES.md)
2. Use numbered headings (2-11)
3. Include operations in table format
4. Keep files under 300 lines when possible
5. Use ❌/✅ for wrong/correct examples
6. Include complexity analysis
7. Add practical code examples

---

## 📊 Coverage Status

| Category | Status | Files |
|----------|--------|-------|
| **Arrays & Lists** | ✅ Complete | 4 files |
| **Primitive Types** | ✅ Complete | 2 files |
| **Queues, Stacks & Deques** | ✅ Complete | 4 files (Queue, Deque, Stack, PriorityQueue) |
| **Sets** | ⏳ Pending | HashSet, TreeSet, LinkedHashSet |
| **Maps** | ⏳ Pending | HashMap, TreeMap, LinkedHashMap |
| **Trees** | ⏳ Pending | Binary Tree, BST |
| **Graphs** | ⏳ Pending | Graph representations, traversals |
| **Heaps** | ✅ Complete | PriorityQueue |

---

**Last Updated**: January 2026

For project setup and build instructions, see the main [README.md](../README.md) in the project root.
