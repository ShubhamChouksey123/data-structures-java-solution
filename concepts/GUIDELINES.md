# Concepts Documentation Guidelines

This document provides guidelines for creating new documentation files for data structures and algorithms in the `concepts/` directory.

## Purpose

The concepts directory contains comprehensive reference documentation for Java data structures, algorithms, and core concepts. Each file serves as a quick reference guide covering theory, implementation, complexity analysis, patterns, and interview tips.

## File Organization

### Directory Structure

```
concepts/
├── GUIDELINES.md           # This file
├── <topic>/
│   ├── info.md            # Main concept file
│   └── <subtopic>.md      # Optional: specific subtopic details
```

**Examples**:
- `concepts/string/info.md`
- `concepts/array/info.md`
- `concepts/array/2-d-array.md`
- `concepts/queue/info.md`
- `concepts/queue/deque.md`

### Naming Conventions

- **Directory names**: Lowercase, singular form (e.g., `string`, `array`, `queue`)
- **Main file**: Always `info.md`
- **Subtopic files**: Descriptive kebab-case (e.g., `2-d-array.md`, `deque.md`)

## Document Structure

Every concept documentation file **must** follow this exact 11-section structure with the same heading names and order:

**IMPORTANT**: Use the exact heading text as specified below. Do not modify heading names or add custom sections.

### 1. Title (Required)

```markdown
# Java [Data Structure/Concept] Concepts
```

**Example**: `# Java String Concepts`, `# Java PriorityQueue (Heap) Concepts`

---

### 2. What is [Concept]? (Required)

Brief introduction covering:
- **Type**: Interface/Class/Primitive
- **Package**: Java package (if applicable)
- **Implementation**: Internal implementation details
- **Key Characteristics**: Bullet list of core features

**Example**:
```markdown
## What is PriorityQueue?

**Type**: Min-heap by default (smallest element at top)
**Implementation**: Binary heap using array
**Package**: `java.util.PriorityQueue`

**Key Characteristics**:
- Elements ordered by priority
- **Allows duplicate elements** ✅
- **NOT thread-safe**
- Does NOT allow `null` elements
```

**Guidelines**:
- Use ✅ and ❌ for boolean features
- Highlight important points with **bold**
- Keep it concise (3-5 bullet points max)

---

### 3. Time & Space Complexity (Required)

Table showing complexity for all major operations:

```markdown
## Time Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
| **add(e)** | O(log n) | Insert element |
| **poll()** | O(log n) | Remove top element |
| **peek()** | O(1) | View top element |

**Space**: O(n)
```

**Guidelines**:
- Use **bold** for operation names
- Include all major operations
- Add notes column for clarification
- Always include space complexity

---

### 4. Common Operations & Methods (Required)

Comprehensive list of methods in **table format**:

```markdown
## Common Operations & Methods

| Operation | Method | Complexity | Notes |
|-----------|--------|------------|-------|
| **Add** | `add(e)` | O(1) | Add at end |
| **Add at index** | `add(i, e)` | O(n) | Shifts elements |
| **Access** | `get(i)` | O(1) | Direct access |
| **Remove** | `remove(i)` | O(n) | Shifts elements |
| **Search** | `contains(e)` | O(n) | Linear search |
```

**Guidelines**:
- Use table format for clarity and quick reference
- Group operations by category (Add, Access, Remove, etc.)
- Include method signature, complexity, and brief notes
- Add code examples after the table if needed for complex operations

---

### 5. Core Characteristics/Creation (Required)

Cover how to create and initialize the data structure:

```markdown
## Basic Operations

```java
// Creation
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Add elements
pq.offer(10);

// View top
int top = pq.peek();
```
```

**Guidelines**:
- Show actual Java code with syntax highlighting
- Include comments for clarity
- Cover all creation variants
- Show basic CRUD operations

**Note**: Section 6 "Comparison with Similar Structures" is optional. All other sections are required.

---

### 6. Comparison with Similar Structures (If Applicable)

Compare with related data structures:

```markdown
## ArrayList vs LinkedList

| Feature | ArrayList | LinkedList |
|---------|-----------|------------|
| **Access by index** | O(1) | O(n) |
| **Add at end** | O(1) | O(1) |
| **Use Case** | Random access | Frequent add/remove at ends |
```

**Guidelines**:
- Use tables for clear comparison
- Focus on practical differences
- Include "When to Use" guidance

---

### 7. Common Patterns & Use Cases (Required)

Document common algorithmic patterns:

```markdown
## Common Patterns & Use Cases

### Pattern 1: Top K Elements

**Use Case**: Find K largest elements

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int num : nums) {
    minHeap.offer(num);
    if (minHeap.size() > k) {
        minHeap.poll();
    }
}
```

**Complexity**: O(n log k)
```
```

**Guidelines**:
- Use H3 (###) for pattern names
- Mark important patterns with ⭐
- Include complexity analysis
- Provide working code examples
- Explain why this pattern works

---

### 8. Common Gotchas & Best Practices (Required)

Document mistakes and pitfalls:

```markdown
## Common Gotchas

### 1. Null Elements

**❌ WRONG**:
```java
pq.add(null);  // NullPointerException
```

**✅ CORRECT**:
```java
// Check for null before adding
if (value != null) {
    pq.add(value);
}
```
```
```

**Guidelines**:
- Use ❌ for wrong examples
- Use ✅ for correct examples
- Number each gotcha
- Explain why it's wrong and how to fix it

---

### 9. Interview Tips (Required)

Practical advice for interviews:

```markdown
## Interview Tips

### When to Use PriorityQueue
✅ Finding Kth largest/smallest element
✅ Merge K sorted arrays
❌ Need guaranteed sorted order (use TreeSet instead)

### Remember
- Default is min-heap
- For max-heap, use `Collections.reverseOrder()`
- Top K largest → use min-heap of size K
```

**Guidelines**:
- Use ✅ for when to use
- Use ❌ for when NOT to use
- Include common mistakes
- List key points to remember

---

### 10. Quick Reference (Required)

Condensed reference for quick lookup:

```markdown
## Quick Reference

### Creation
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

### Essential Operations
```java
pq.offer(x)      // Add element
pq.poll()        // Remove top
pq.peek()        // View top
```
```
```

**Guidelines**:
- Keep it concise
- Include only most common operations
- Use comments for clarity
- Group related operations

---

### 11. Key Insight (Required)

Final takeaway message:

```markdown
**Key Insight**: For "K largest", think **min-heap** of size K. For "K smallest", think **max-heap** of size K!
```

**Guidelines**:
- Bold the text
- One or two sentences max
- Focus on the most important concept
- Make it memorable

---

## Formatting Standards

### Headings

- **H1 (`#`)**: Document title only
- **H2 (`##`)**: Major sections
- **H3 (`###`)**: Subsections and patterns
- **H4 (`####`)**: Sub-subsections (use sparingly)

### Code Blocks

Always specify language for syntax highlighting:

```markdown
```java
// Java code here
```
```

### Tables

Use pipes for tables, align headers:

```markdown
| Header 1 | Header 2 | Header 3 |
|----------|----------|----------|
| Data 1   | Data 2   | Data 3   |
```

### Emphasis

- **Bold** (`**text**`): Important concepts, method names, keywords
- *Italic* (`*text*`): Rarely used, prefer bold
- `Code` (`` `text` ``): Method names, code snippets, values
- ✅ Success/correct examples
- ❌ Wrong examples/mistakes
- ⭐ Important/frequently used patterns

### Separators

Use horizontal rules (`---`) to separate major sections.

### Complexity Notation

- Always use Big-O notation: `O(1)`, `O(n)`, `O(log n)`
- For amortized complexity: `O(1) amortized`
- Specify what `n` represents when ambiguous

---

## Content Guidelines

### Document Length

**Maximum Length**: 300 lines per file

**Guidelines**:
- Keep documentation focused and concise
- If content exceeds 300 lines, consider splitting into subtopics
- Example: `queue/info.md` + `queue/deque.md` instead of one large file
- Prioritize essential information over exhaustive coverage
- Use links to reference detailed Java documentation when needed

### 1. Be Concise

- Focus on practical usage over theory
- Avoid redundant explanations
- Use bullet points for lists
- Keep paragraphs short (3-4 sentences max)

### 2. Prioritize Examples

- Show code examples for every concept
- Use realistic variable names
- Include comments for clarity
- Demonstrate common use cases

### 3. Highlight Practical Patterns

- Focus on interview-relevant patterns
- Show complete working examples
- Explain when to use each pattern
- Include complexity analysis

### 4. Document Edge Cases

- Null handling
- Empty collections
- Integer overflow
- Thread safety
- Immutability concerns

### 5. Be Consistent

- Use same terminology throughout
- Follow the structure template
- Maintain consistent formatting
- Use established conventions

---

## Writing Style

### Voice and Tone

- **Direct and clear**: Avoid flowery language
- **Imperative mood**: "Use ArrayList for..." not "You should use ArrayList..."
- **Present tense**: "Returns null" not "Will return null"
- **Active voice**: "Use this method" not "This method can be used"

### Technical Accuracy

- Test all code examples
- Verify complexity claims
- Reference Java documentation when needed
- Update for Java version features

### Audience

- Assume intermediate Java knowledge
- Explain non-obvious behaviors
- Don't explain basic Java syntax
- Focus on data structure-specific concepts

---

## Review Checklist

Before finalizing a concept document, verify:

- [ ] Title follows naming convention
- [ ] "What is [Concept]?" section present
- [ ] Time/space complexity documented
- [ ] Code examples are syntactically correct
- [ ] Common patterns included with complexity
- [ ] Gotchas section with ❌/✅ examples
- [ ] Interview tips provided
- [ ] Quick reference section included
- [ ] Key insight provided at end
- [ ] All code blocks specify language
- [ ] Tables are properly formatted
- [ ] Horizontal rules separate sections
- [ ] No spelling/grammar errors
- [ ] Consistent formatting throughout

---

## Example Template

Use this template as a starting point:

```markdown
# Java [DataStructure] Concepts

## What is [DataStructure]?

**Type**:
**Implementation**:
**Package**:

**Key Characteristics**:
-
-

---

## Time Complexity

| Operation | Complexity | Notes |
|-----------|------------|-------|
|  |  |  |

**Space**: O(n)

---

## Basic Operations

```java
// Creation

// Add

// Access

// Remove
```

---

## Common Patterns & Use Cases

### Pattern 1: [Pattern Name]

**Use Case**:

```java
// Code example
```

**Complexity**: O(?)

---

## Common Gotchas

### 1. [Gotcha Name]

**❌ WRONG**:
```java
// Wrong code
```

**✅ CORRECT**:
```java
// Correct code
```

---

## Interview Tips

### When to Use
✅

### Remember
-

---

## Quick Reference

### Creation
```java
// Code
```

### Essential Operations
```java
// Code
```

---

**Key Insight**: [Main takeaway]
```

---

## Contributing

When adding new concept files:

1. Create appropriate directory structure
2. Follow the template above
3. Test all code examples
4. Review against the checklist
5. Ensure consistency with existing files
6. Keep it focused and practical

---

## Maintenance

- Update for new Java versions
- Add patterns as you discover them
- Fix errors and improve clarity
- Keep examples current and relevant

---

**Remember**: These documents are living references meant to be practical study guides, not comprehensive textbooks. Prioritize clarity, examples, and interview-relevant patterns over exhaustive coverage.