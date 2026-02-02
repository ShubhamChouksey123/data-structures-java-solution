# Notes Creation Guidelines

> Rules and best practices for creating effective revision notes for DSA interview preparation

---

## Quick Format Reference

### Standard Structure (UNNUMBERED H2 sections)

```
# Pattern Name (H1)
> Navigation links

## When to Use (H2)
### Keywords (H3)
### Examples (H3)

## Core Concept (H2)

## Pattern 1: Descriptive Name (H2)
### Template (H3)
### Visual Example (H3)

## Pattern 2: Descriptive Name (H2)

## Common Mistakes (H2)
### ❌ Mistake Name (H3)

## Problems (H2)
### ⭐ IMPORTANT Problem with Solution (H3)

## Key Takeaways (H2)

> Navigation links
```

### Key Formatting Rules

✅ **DO**:
- Use H2 (##) for main sections - **NO NUMBERING**
- Include "When to Use" section with keywords
- Add complexity for every pattern/algorithm
- Mark 1-2 important items per document with ⭐
- Include complete solution for ⭐ IMPORTANT ⭐ problems
- Use horizontal rules (---) between major sections
- Keep it concise (2-5 min read)

❌ **DON'T**:
- Number sections (~~## 1. When to Use~~)
- Include full code except for important problems
- Make it longer than necessary
- Mark everything as important

### Format Comparison: notes/ vs concepts/

| Aspect | notes/ (Algorithms/Patterns) | concepts/ (Data Structures) |
|--------|------------------------------|----------------------------|
| **Sections** | Flexible, unnumbered | Fixed 11 sections, numbered 2-11 |
| **Numbering** | NO numbering | YES, numbered sections |
| **Structure** | When to Use → Patterns → Problems | What is? → Operations → Patterns → Quick Reference |
| **Code** | Minimal (except ⭐ problems) | Templates and examples throughout |
| **Guidelines** | This file (notes/GUIDELINES.md) | concepts/GUIDELINES.md |
| **Purpose** | Algorithm patterns for solving problems | Data structure reference and operations |
| **Example** | notes/sliding-window/Notes.md | concepts/map/hashmap.md |

**This guideline applies to**: `notes/` directory only

**For data structures**: See `concepts/GUIDELINES.md` for numbered format

---

## Core Principles

### 1. **Focus on Concepts, Not Code**
- Explain the **algorithm** in plain English
- Describe **why** it works, not just how to implement it
- Use pseudocode steps instead of language-specific syntax
- Code implementations are in the actual solution files, not notes
- **Exception**: Problems marked as ⭐ **IMPORTANT** ⭐ should include complete solution with detailed explanation

**Example**:
```
✅ GOOD: "Move slow by 1 step, fast by 2 steps until they meet"
❌ BAD: "while (fast != null) { slow = slow.next; fast = fast.next.next; }"
```

**Exception for Important Problems**:
```
✅ IMPORTANT PROBLEM: Include full Java solution with inline comments
✅ Add "Why Important" section
✅ Explain key insights and non-obvious tricks
```

---

### 2. **Keep It Short for Quick Revision**
- Target: **2-5 minutes** reading time per topic
- Remove verbose explanations
- Use bullet points and tables
- Be concise but complete
- Every word should add value

**Length Guidelines**:
- Simple patterns: ~100-150 lines
- Complex patterns: ~150-200 lines
- If longer, consider splitting into sub-topics

---

### 3. **Always Include Visual Diagrams**
- Create or include diagrams for complex concepts
- Use ASCII art for simple illustrations
- Include actual image files (.png, .jpg) for detailed diagrams
- Label all important elements (variables, distances, nodes)
- Use markdown image syntax: `![Description](filename.png)`

**Visual Elements to Include**:
- Data structure layout
- Pointer movements
- Step-by-step progression
- Mathematical relationships

---

### 4. **Mandatory: Time and Space Complexity**
- **Every pattern/algorithm must include complexity**
- Format: `**Complexity**: O(n) time, O(1) space`
- Place it right after the algorithm description
- Explain why if not obvious

**Example**:
```markdown
## Pattern: Two Sum

**Algorithm**:
1. Use HashMap to store seen numbers
2. Check if complement exists
3. Return indices if found

**Complexity**: O(n) time, O(n) space
```

---

## Document Structure

### Standard Format for Pattern/Algorithm Notes

All notes files should follow this structure with **unnumbered H2 sections**:

```markdown
# [Pattern Name]

> **[← Back to Overview](Notes.md)** (if split into multiple files)

---

## When to Use

✅ **Brief description of when this pattern applies**

### Keywords
- "keyword 1"
- "keyword 2"

### Examples
- Example use case 1
- Example use case 2

---

## Core Concept (or "Algorithm")

Brief 1-2 sentence explanation of the core idea.

**Key Insight**: Why this pattern works.

**Complexity**: O(?) time, O(?) space

---

## Pattern 1: [Sub-pattern Name] (or "Templates")

**Use Case**: When to use this specific variation

**Algorithm**:
1. Step 1
2. Step 2
3. Step 3

**Complexity**: O(?) time, O(?) space

### Template (H3 for code templates)

```java
// Concise template code
```

**Visual Example**: (Optional, use H3 if needed)
```
ASCII diagram or step-by-step example
```

---

## Pattern 2: [Sub-pattern Name]

(Repeat structure from Pattern 1)

---

## Common Mistakes

### ❌ Mistake 1: Description

```java
// WRONG
code example

// CORRECT
code example
```

### ❌ Mistake 2: Description

(Repeat for each common mistake)

---

## Problems (or "Problems to Practice")

- [ ] [Problem Name](link) - Difficulty
- [x] [Completed Problem](link) - Difficulty
- [x] [Important Problem](link) - Difficulty ⭐ **IMPORTANT** ⭐

### [Important Problem Name] ⭐ **IMPORTANT** ⭐

**Problem**: [Link](url) - Difficulty

**Why Important**: Explanation

**Approach**:
1. Algorithm steps

**Complexity**: O(?) time, O(?) space

**Solution**:
```java
public ReturnType methodName(Parameters params) {
    // Implementation
}
```

**Key Points**:
- Insight 1
- Insight 2

---

## Key Takeaways

1. Key point 1
2. Key point 2
3. Key point 3

---

> **[← Navigation](link)** | **[Back to Overview](Notes.md)** | **[Next Pattern →](link)**
```

### Section Guidelines

#### Required Sections (must have)
1. **Title** (H1) - Pattern/topic name
2. **When to Use** (H2) - Clear criteria for pattern selection
3. **Core Concept** (H2) - Brief explanation with key insight
4. **Patterns/Templates** (H2) - At least one pattern with algorithm
5. **Problems** (H2) - List of practice problems
6. **Key Takeaways** (H2) - Summary of critical points

#### Optional Sections (include if relevant)
- **Common Mistakes** (H2) - Typical errors and corrections
- **Common Use Cases** (H2) - Table of use cases
- **Time Complexity Analysis** (H2) - Detailed complexity explanation
- **Visual Diagrams** (H3 within patterns) - ASCII or images

#### Section Order

Always follow this order:
1. Title + Navigation
2. When to Use
3. Core Concept/Algorithm
4. Pattern 1, Pattern 2, ... (or Templates)
5. Common Mistakes
6. Problems
7. Key Takeaways
8. Bottom Navigation

---

## Heading Hierarchy

### Use This Hierarchy:

```markdown
# Document Title (H1 - only one per file)

## Main Section (H2 - unnumbered)

### Subsection (H3 - unnumbered)

#### Detail Level (H4 - rarely needed)
```

### Heading Rules:

1. **H1 (#)**: Only for document title
2. **H2 (##)**: Main sections - NO NUMBERING
3. **H3 (###)**: Subsections within main sections
4. **H4 (####)**: Only if absolutely necessary (avoid if possible)

### DO NOT Number Sections

❌ **WRONG**:
```markdown
## 1. When to Use
## 2. Core Concept
## 3. Pattern 1
```

✅ **CORRECT**:
```markdown
## When to Use
## Core Concept
## Pattern 1: Descriptive Name
```

**Why?** Numbering makes reorganization difficult and adds no value for quick revision.

---

## Special Formatting

### Sub-patterns and Templates

Use descriptive names:
```markdown
## Pattern 1: Longest Window
## Pattern 2: Shortest Window
## Template: Basic Sum/Count
```

### Important Items

```markdown
## Pattern 2: Find Cycle Start ⭐ **IMPORTANT** ⭐
### Template: Sliding Window Maximum ⭐ **IMPORTANT** ⭐
- [x] [Problem](link) - Difficulty ⭐ **IMPORTANT** ⭐
```

### Visual Breaks

Use `---` (horizontal rule) between major sections:
```markdown
## When to Use
Content...

---

## Core Concept
Content...
```

---

## Style Guidelines

### Variable Naming
- Use **clear, descriptive names**: `x`, `y`, `c` instead of `L`, `C`, `k`
- Lowercase for distances: `x`, `y`, `z`
- Lowercase for cycle/length: `c`
- Descriptive for pointers: `slow`, `fast`, `p1`, `p2`
- **Define all variables** before using them

### Mathematical Proofs
- Include when it helps understanding
- Keep it simple and concise
- Show key steps, not every detail
- Explain the final insight clearly

**Format**:
```markdown
**Mathematical Proof**:
- x = definition
- y = definition

When condition:
- Formula 1
- Formula 2
- Simplifies to: key result

**Key Insight**: Plain English explanation
```

### Tables and Formatting
- Use tables for quick reference
- Use **bold** for emphasis
- Use `code` for variable names and values
- Use bullet points for lists
- Use numbered lists for sequential steps

---

## What NOT to Include

❌ **Don't Include**:
1. **Full code implementations**
   - Exception: Tiny snippets (2-3 lines) for clarity
   - **Exception**: Problems marked as ⭐ **IMPORTANT** ⭐ should include complete solution with explanation

2. **Language-specific syntax**
   - Focus on algorithm, not Java/Python/etc.
   - Exception: Important problems should have Java implementation

3. **Lengthy explanations**
   - Keep it concise for revision
   - Exception: Important problems deserve detailed explanation of key insights

4. **Multiple examples**
   - One clear example is enough

5. **Obvious information**
   - Assume reader knows basics

6. **Unrelated topics**
   - Stay focused on the pattern

---

## Highlighting Important Concepts

### When to Mark as Important

Mark concepts/problems as **IMPORTANT** when they are:
- **Non-obvious applications** of the pattern
- **Frequently asked** in interviews
- **Tricky patterns** that need regular revision
- **Key insights** that are easy to forget
- **Counter-intuitive** approaches

### How to Mark Important

**In Pattern Sections**:
```markdown
### 4. Find Duplicate in Array [1,n] ⭐ **IMPORTANT** ⭐

**⚠️ Key Pattern - Review Regularly**

- Concept description
- **Why important**: Explanation of why this needs attention
- **Trick**: Key insight or non-obvious technique
```

**In Practice Problems**:
```markdown
- [x] [Problem Name](link) - Difficulty ⭐ **IMPORTANT** ⭐
```

**In Pattern Headers** (for entire sections):
```markdown
## Pattern 2: Find Cycle Start ⭐ **IMPORTANT** ⭐
```

### Formatting Guidelines

- **Use ⭐ stars** around **IMPORTANT** label for visibility
- **Add ⚠️ warning symbol** for key patterns that need regular review
- **Include context**: Always explain **why** it's important
- **Highlight the trick**: Mention the non-obvious insight
- **Be selective**: Don't mark everything as important (max 1-2 per document)

### Example

```markdown
### 4. Find Duplicate in Array [1,n] ⭐ **IMPORTANT** ⭐

**⚠️ Key Pattern - Review Regularly**

- Treat array as linked list: index → arr[index]
- Apply cycle detection (duplicate creates cycle)
- **Why important**: Non-obvious application of fast/slow pointer to array problems
- **Trick**: Duplicate value acts as cycle entry point
```

### Including Solutions for Important Problems

**Rule**: When a problem is marked as ⭐ **IMPORTANT** ⭐, **include the complete solution with explanation**.

**Format**:
```markdown
### [Problem Name] ⭐ **IMPORTANT** ⭐

**Problem**: [LeetCode link](url) - Difficulty

**Why Important**: Brief explanation of why this needs special attention

**Approach**:
1. Algorithm step 1
2. Algorithm step 2
3. Algorithm step 3

**Complexity**: O(?) time, O(?) space

**Solution**:
```java
public ReturnType methodName(Parameters params) {
    // Implementation with inline comments explaining key steps
    // Focus on the non-obvious parts
}
```

**Key Points**:
- Insight 1
- Insight 2
- Common mistake to avoid
```

**Example**:
```markdown
### Sliding Window Maximum ⭐ **IMPORTANT** ⭐

**Problem**: [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) - Hard

**Why Important**: Classic monotonic deque pattern, frequently asked, non-intuitive approach

**Approach**:
1. Use deque to store indices in decreasing order of values
2. Remove indices outside current window from front
3. Remove indices with smaller values from back
4. Front of deque always has maximum

**Complexity**: O(n) time, O(k) space

**Solution**:
```java
public int[] maxSlidingWindow(int[] nums, int k) {
    Deque<Integer> deque = new ArrayDeque<>();
    int[] result = new int[nums.length - k + 1];

    for (int i = 0; i < nums.length; i++) {
        // Remove indices outside window
        while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
            deque.pollFirst();
        }

        // Maintain decreasing order
        while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
            deque.pollLast();
        }

        deque.offerLast(i);

        if (i >= k - 1) {
            result[i - k + 1] = nums[deque.peekFirst()];
        }
    }
    return result;
}
```

**Key Points**:
- Store **indices** not values (need to check window boundaries)
- Deque maintains **decreasing** order for maximum (increasing for minimum)
- Each element added/removed at most once → O(n) time
```

---

## Checklist Before Committing

Use this checklist for every note:

### Content Requirements
- [ ] Title (H1) clearly states pattern name
- [ ] **"When to Use"** section with keywords and examples
- [ ] Core concept with key insight
- [ ] All patterns have algorithm steps
- [ ] **Time and space complexity included for each pattern**
- [ ] Visual diagram included (image or ASCII) for complex patterns
- [ ] All variables defined and used consistently
- [ ] Critical points and common mistakes listed
- [ ] Practice problems linked with difficulty
- [ ] Important concepts marked with ⭐ **IMPORTANT** ⭐ (max 1-2 per document)
- [ ] **Important problems include complete solution with explanation**
- [ ] Key takeaways section summarizing main points

### Structure Requirements
- [ ] Follows standard section order (When to Use → Core Concept → Patterns → Mistakes → Problems → Takeaways)
- [ ] H1 (#) used only for title
- [ ] H2 (##) used for main sections - **UNNUMBERED**
- [ ] H3 (###) used for subsections
- [ ] Horizontal rules (---) separate major sections
- [ ] Navigation links at top and bottom (if multi-file)
- [ ] Tables used for quick reference where appropriate

### Code and Formatting
- [ ] No full code implementations **except for ⭐ IMPORTANT ⭐ problems**
- [ ] Templates use clean, readable code
- [ ] Java syntax for all code examples
- [ ] Inline comments explain non-obvious parts
- [ ] Common mistakes show WRONG vs CORRECT examples
- [ ] Markdown formatting correct (no broken links, proper code blocks)

### Length and Style
- [ ] Length appropriate (2-5 min read time, longer if including important solutions)
- [ ] Concise but complete - every word adds value
- [ ] Consistent variable naming throughout
- [ ] Professional tone, clear explanations

### Final Checks
- [ ] File passes `mvn spotless:check` (if applicable)
- [ ] All links work correctly
- [ ] Images display properly (if included)
- [ ] Readable on GitHub markdown renderer

---

## File Organization

```
notes/
├── GUIDELINES.md (this file)
├── pattern-name/
│   ├── Notes.md
│   ├── img.png (or other image files)
│   └── [additional diagrams]
```

### Naming Conventions
- Folder: `kebab-case` (e.g., `fast-and-slow-pointer`)
- Notes file: Always `Notes.md`
- Images: Descriptive names (e.g., `cycle-detection.png`, `img.png`)

---

## Complete Example Template

```markdown
# Fast and Slow Pointer

> **[← Back to Overview](../README.md)**

---

## When to Use

✅ **Detect cycles, find middle elements, or solve problems with linked structures**

### Keywords
- "detect cycle"
- "find middle"
- "linked list"
- "nth from end"

### Examples
- Detect cycle in linked list
- Find middle of linked list
- Happy number problem
- Find duplicate in array [1,n]

---

## Core Concept

Use two pointers moving at different speeds (slow: 1 step, fast: 2 steps) to detect cycles or find specific positions.

**Key Insight**: If there's a cycle, fast pointer will eventually catch slow pointer. If no cycle, fast reaches end.

**Complexity**: O(n) time, O(1) space

---

## Pattern 1: Cycle Detection

**Use Case**: Determine if linked list has a cycle

**Algorithm**:
1. Initialize slow and fast pointers at head
2. Move slow by 1, fast by 2
3. If they meet, cycle exists
4. If fast reaches null, no cycle

**Complexity**: O(n) time, O(1) space

### Template

```java
public boolean hasCycle(ListNode head) {
    ListNode slow = head, fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) return true;
    }
    return false;
}
```

### Visual Example

```
List: 1 → 2 → 3 → 4 → 5
              ↑       ↓
              8 ← 7 ← 6

Step 1: slow=1, fast=1
Step 2: slow=2, fast=3
Step 3: slow=3, fast=5
Step 4: slow=4, fast=7
Step 5: slow=5, fast=3
Step 6: slow=6, fast=5
Step 7: slow=7, fast=7 ✓ (cycle detected)
```

---

## Pattern 2: Find Cycle Start ⭐ **IMPORTANT** ⭐

**Use Case**: Find the node where cycle begins

**Algorithm**:
1. Detect cycle (fast meets slow)
2. Reset slow to head
3. Move both by 1 until they meet
4. Meeting point is cycle start

**Complexity**: O(n) time, O(1) space

### Template

```java
public ListNode detectCycle(ListNode head) {
    ListNode slow = head, fast = head;

    // Phase 1: Detect cycle
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) break;
    }

    if (fast == null || fast.next == null) return null;

    // Phase 2: Find start
    slow = head;
    while (slow != fast) {
        slow = slow.next;
        fast = fast.next;
    }
    return slow;
}
```

---

## Common Mistakes

### ❌ Mistake 1: Not Checking fast.next

```java
// WRONG - NullPointerException
while (fast != null) {
    fast = fast.next.next;  // Fails if fast.next is null
}

// CORRECT
while (fast != null && fast.next != null) {
    fast = fast.next.next;
}
```

### ❌ Mistake 2: Wrong Initialization

```java
// WRONG - starts at different positions
ListNode slow = head, fast = head.next;

// CORRECT - both start at head
ListNode slow = head, fast = head;
```

---

## Problems

- [x] [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) - Easy
- [x] [Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Find Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) - Medium
- [x] [Happy Number](https://leetcode.com/problems/happy-number/) - Easy

### Linked List Cycle II ⭐ **IMPORTANT** ⭐

**Problem**: [Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/) - Medium

**Why Important**: Non-obvious two-phase approach, mathematical proof required, frequently asked

**Approach**:
1. Phase 1: Use fast/slow to detect cycle (meeting point)
2. Phase 2: Reset slow to head, move both by 1 until they meet
3. Meeting point in phase 2 is the cycle start

**Complexity**: O(n) time, O(1) space

**Solution**:

```java
public ListNode detectCycle(ListNode head) {
    ListNode slow = head, fast = head;

    // Phase 1: Detect cycle
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            // Phase 2: Find cycle start
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;  // Cycle start
        }
    }

    return null;  // No cycle
}
```

**Key Points**:
- Distance from head to cycle start = distance from meeting point to cycle start
- Mathematical proof: if slow travels x, fast travels 2x when they meet
- After meeting, reset one pointer to head and move both by 1
- Common mistake: not checking `fast.next != null`

---

## Key Takeaways

1. **Two pointers at different speeds** - slow moves 1, fast moves 2
2. **Cycle detection** - if pointers meet, cycle exists
3. **Cycle start** - two-phase approach: detect, then find start
4. **Always check** `fast != null && fast.next != null`
5. **O(n) time, O(1) space** - optimal for linked list problems

---

> **[← Back to Overview](../README.md)** | **[Two Pointers →](../two-pointers/Notes.md)**
```

---

## Tips for Effective Notes

1. **Write for future you** - Assume you'll forget details in 3 months
2. **Visual > Text** - A diagram often explains better than paragraphs
3. **Test your notes** - Can you solve a problem after reading them?
4. **Iterate** - Revise notes after solving problems
5. **Be consistent** - Follow these guidelines for all notes
6. **Important problems deserve detail** - When marking ⭐ IMPORTANT ⭐, include complete solution and explanation
7. **Be selective with importance** - Max 1-2 important items per document to maintain focus

---

## References

- Based on pattern: [Fast and Slow Pointer Notes](fast-and-slow-pointer/Notes.md)
- Questions list: [docs/questions-list.md](../docs/questions-list.md)

---

**Remember**: These notes are for **quick revision before interviews**, not for learning from scratch. Keep them concise, visual, and focused on key concepts!
