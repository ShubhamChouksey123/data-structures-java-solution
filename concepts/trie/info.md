# Java Trie Concepts

## What is Trie?

**Type**: Tree-like structure for storing strings (also called prefix tree)
**Package**: No built-in Java class — implemented manually with a `TrieNode` class
**Implementation**: Each node holds a fixed-size array (or `HashMap`) of children keyed by character, plus an end-of-word flag

**Key Characteristics**:
- **Prefix sharing** - words with common prefixes share the same path from root
- **Fast prefix queries** - O(L) lookup/insert where L = word length, independent of dictionary size
- **No built-in Java implementation** ❌ - always hand-rolled
- **Space-heavy per node** - each node reserves space for all possible children (e.g. 26 for lowercase letters)
- **NOT thread-safe** by default

---

## Time Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **insert(word)** | O(L) | L = length of word |
| **search(word)** | O(L) | Exact word match |
| **startsWith(prefix)** | O(L) | Prefix existence check |
| **delete(word)** | O(L) | Needs care to avoid breaking shared prefixes |

**Space**: O(N * L * A) worst case — N = number of words, L = average word length, A = alphabet size (26 for lowercase); shared prefixes reduce this in practice.

---

## Common Operations & Methods

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Insert word** | `trie.insert(word)` | O(L) | Creates nodes for missing characters |
| **Search exact word** | `trie.search(word)` | O(L) | Must end on a node with `isEnd = true` |
| **Check prefix** | `trie.startsWith(prefix)` | O(L) | Doesn't require `isEnd` on the last node |
| **Delete word** | `trie.delete(word)` | O(L) | Unset `isEnd`; prune nodes only if no other word depends on them |

---

## Basic Operations

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

class Trie {
    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = find(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }

    private TrieNode find(String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                return null;
            }
            node = node.children[idx];
        }
        return node;
    }
}
```

---

## Comparison with Similar Structures

| Feature | Trie | HashSet<String> | TreeMap<String, V> |
|---------|------|------------------|---------------------|
| **Prefix search** | O(L) ✅ | O(n) scan ❌ | O(log n + k) via `subMap` |
| **Exact search** | O(L) | O(1) | O(log n) |
| **Memory overhead** | High (per-char nodes) | Low | Moderate |
| **Sorted iteration** | Natural (DFS) | ❌ Not sorted | ✅ Sorted |
| **Use case** | Autocomplete, prefix/word search | Fast membership test | Sorted string ranges |

**When to Use Trie**: Need prefix-based lookups (autocomplete, word search, longest common prefix) at scale.

**When to Use HashSet**: Only need exact membership, no prefix queries.

**When to Use TreeMap**: Need sorted order and range queries but prefix queries are rare.

---

## Common Patterns & Use Cases

### Pattern 1: Autocomplete / Prefix Matching ⭐

**Use Case**: Given a prefix, return/count all words starting with it

```java
public List<String> wordsWithPrefix(String prefix) {
    List<String> result = new ArrayList<>();
    TrieNode node = find(prefix);
    if (node != null) {
        collectWords(node, new StringBuilder(prefix), result);
    }
    return result;
}

private void collectWords(TrieNode node, StringBuilder path, List<String> result) {
    if (node.isEnd) {
        result.add(path.toString());
    }
    for (int i = 0; i < 26; i++) {
        if (node.children[i] != null) {
            path.append((char) ('a' + i));
            collectWords(node.children[i], path, result);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
```

**Complexity**: O(P + collected characters), P = prefix length

---

### Pattern 2: Word Search with Wildcards

**Use Case**: `search(".a.")`-style queries where `.` matches any character

```java
public boolean search(String word) {
    return dfs(root, word, 0);
}

private boolean dfs(TrieNode node, String word, int i) {
    if (i == word.length()) return node.isEnd;
    char c = word.charAt(i);
    if (c != '.') {
        int idx = c - 'a';
        return node.children[idx] != null && dfs(node.children[idx], word, i + 1);
    }
    for (TrieNode child : node.children) {
        if (child != null && dfs(child, word, i + 1)) return true;
    }
    return false;
}
```

**Complexity**: O(26^k) worst case where k = number of wildcards

---

### Pattern 3: Longest Common Prefix of a Word List

**Use Case**: Build a trie from all words, then walk down from root while each node has exactly one child and isn't `isEnd`

**Complexity**: O(sum of word lengths) to build, O(L) to walk

---

## Common Gotchas & Best Practices

### 1. Confusing `search` with `startsWith`

**❌ WRONG**:
```java
public boolean search(String word) {
    return find(word) != null;  // matches prefixes too, not just exact words
}
```

**✅ CORRECT**:
```java
public boolean search(String word) {
    TrieNode node = find(word);
    return node != null && node.isEnd;  // must land exactly on a word-ending node
}
```

---

### 2. Forgetting Case Sensitivity / Non-Lowercase Input

**❌ WRONG**:
```java
int idx = c - 'a';  // breaks on uppercase or digits, index goes out of bounds
```

**✅ CORRECT**:
```java
// Either lowercase the input first, or size children array for the actual alphabet
// (e.g. new TrieNode[36] for lowercase+digits), or use a HashMap<Character, TrieNode>
// when the character set is unknown/sparse.
```

---

### 3. Deleting a Word Breaks a Shared Prefix

**❌ WRONG**:
```java
// Deleting "car" by removing all its nodes also deletes "card" if it shares the prefix
```

**✅ CORRECT**:
```java
// Only unset isEnd on the last node of the deleted word.
// Only prune a node if it has no children AND isEnd is false AND no other word needs it.
```

---

## Interview Tips

### When to Use Trie
✅ Autocomplete / typeahead search
✅ Word search puzzles (Boggle-style, with backtracking)
✅ Longest common prefix among many strings
✅ IP routing / longest prefix match (binary trie variant)
✅ Spell checkers, dictionary-based word validation

### When NOT to Use Trie
❌ Only need exact-match membership → use `HashSet`
❌ Memory is tightly constrained and alphabet is large/unknown → `HashMap<Character, TrieNode>` children helps, but still costly
❌ Need sorted iteration without prefix needs → `TreeMap`/`TreeSet` is simpler

### Remember
- A trie node needs **both** children links and an `isEnd` boolean — missing the boolean is the most common bug
- `search()` requires reaching a node where `isEnd == true`; `startsWith()` only requires reaching any node
- Use `HashMap<Character, TrieNode>` for children when the character set is large or sparse (e.g. Unicode); use a fixed array for small fixed alphabets (lowercase a-z) for speed
- Classic combo: **Trie + DFS/backtracking** for word-search-on-a-grid problems

---

## Quick Reference

### Creation
```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

Trie trie = new Trie();
```

### Essential Operations
```java
trie.insert(word)          // Add word
trie.search(word)          // Exact word match
trie.startsWith(prefix)    // Prefix exists
```

---

## Key Insight

**A trie trades memory for prefix speed**: every insert/search/startsWith is O(L) regardless of how many words are stored, because the path through the tree *is* the word. Reach for it the moment a problem mentions **prefixes**, **autocomplete**, or **word search on a grid**.
