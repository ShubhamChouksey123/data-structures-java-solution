# Notes Creation Guidelines

> Rules and best practices for creating effective revision notes for DSA interview preparation

---

## Core Principles

### 1. **Focus on Concepts, Not Code**
- Explain the **algorithm** in plain English
- Describe **why** it works, not just how to implement it
- Use pseudocode steps instead of language-specific syntax
- Code implementations are in the actual solution files, not notes

**Example**:
```
✅ GOOD: "Move slow by 1 step, fast by 2 steps until they meet"
❌ BAD: "while (fast != null) { slow = slow.next; fast = fast.next.next; }"
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

### Required Sections

```markdown
# [Pattern/Topic Name]

## Core Concept
- Brief 1-2 sentence explanation
- Key insight that makes the pattern work

---

## Pattern 1: [Sub-pattern Name]

**Algorithm**:
1. Step 1
2. Step 2
3. Step 3

**Complexity**: O(?) time, O(?) space

[Optional: Visual diagram or explanation]

---

## Common Use Cases
| Pattern | Description | Key Points |
|---------|-------------|------------|

---

## Critical Points
- Important gotchas
- Edge cases to remember
- Common mistakes

---

## Problems to Practice
- [ ] [Problem Name](link) - Difficulty
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

2. **Language-specific syntax**
   - Focus on algorithm, not Java/Python/etc.

3. **Lengthy explanations**
   - Keep it concise for revision

4. **Multiple examples**
   - One clear example is enough

5. **Obvious information**
   - Assume reader knows basics

6. **Unrelated topics**
   - Stay focused on the pattern

---

## Checklist Before Committing

Use this checklist for every note:

- [ ] Title and core concept clearly stated
- [ ] All patterns have algorithm steps
- [ ] **Time and space complexity included for each pattern**
- [ ] Visual diagram included (image or ASCII)
- [ ] All variables defined and used consistently
- [ ] Tables used for quick reference
- [ ] Length appropriate (2-5 min read time)
- [ ] No code implementations (only concept)
- [ ] Critical points and gotchas listed
- [ ] Practice problems linked with difficulty
- [ ] Markdown formatting correct
- [ ] File passes `mvn spotless:check`

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

## Example Template

```markdown
# [Pattern Name]

## Core Concept

Brief explanation in 1-2 sentences.

**Key Insight**: Why this works in plain English.

---

## Pattern 1: [Name]

**Algorithm**:
1. Step 1
2. Step 2
3. Step 3

**Complexity**: O(?) time, O(?) space

### Visual Diagram

![Description](image.png)

**Why it works?**

Brief explanation with optional math proof.

---

## Common Use Cases

| Pattern | Description | Pointer Movement |
|---------|-------------|------------------|
| Use Case 1 | Description | Details |
| Use Case 2 | Description | Details |

---

## Critical Points

- Important point 1
- Important point 2
- Common mistake to avoid

---

## Problems to Practice

- [ ] [Problem 1](link) - Difficulty
- [ ] [Problem 2](link) - Difficulty
```

---

## Tips for Effective Notes

1. **Write for future you** - Assume you'll forget details in 3 months
2. **Visual > Text** - A diagram often explains better than paragraphs
3. **Test your notes** - Can you solve a problem after reading them?
4. **Iterate** - Revise notes after solving problems
5. **Be consistent** - Follow these guidelines for all notes

---

## References

- Based on pattern: [Fast and Slow Pointer Notes](fast-and-slow-pointer/Notes.md)
- Questions list: [docs/questions-list.md](../docs/questions-list.md)

---

**Remember**: These notes are for **quick revision before interviews**, not for learning from scratch. Keep them concise, visual, and focused on key concepts!
