# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java-based data structures and algorithms practice repository containing solutions to various coding problems. The project is built using Maven and Spring Boot 3.4.1 (Java 21), though Spring Boot is primarily used for dependency management rather than as a web application framework.

## Build and Development Commands

### Build the project
```bash
mvn clean install
```

### Compile only
```bash
mvn compile
```

### Run a specific MainClass
Each topic has its own `MainClass.java` with test cases. To run:
```bash
mvn compile exec:java -Dexec.mainClass="dev.shubham.algorithms.<topic>.MainClass"
```

Examples:
```bash
mvn compile exec:java -Dexec.mainClass="dev.shubham.algorithms.array.MainClass"
mvn compile exec:java -Dexec.mainClass="dev.shubham.algorithms.tree.MainClass"
mvn compile exec:java -Dexec.mainClass="dev.shubham.algorithms.linkedlist.MainClass"
```

### Code Formatting
The project uses Spotless for code formatting (basic formatting rules + import organization):
```bash
# Check formatting
mvn spotless:check

# Apply formatting
mvn spotless:apply
```

Note: Spotless runs automatically during the compile phase and will fail the build if code is not formatted correctly. If you encounter build failures due to formatting violations, run `mvn spotless:apply` first.

## Architecture and Structure

### Package Organization

The codebase is organized by data structure and algorithm topic under `dev.shubham.algorithms`:

- `array/` - Array problems and ArrayList utilities
- `tree/` - Binary tree problems, BST operations, tree traversals
- `linkedlist/` - Linked list implementations and problems
- `graph/` - Graph algorithms, BFS/DFS, topological sort
- `dynamicprogramming/` - DP problems and solutions
- `backtracking/` - Backtracking algorithms
- `binarysearch/` - Binary search variations
- `bitmanupulation/` - Bit manipulation techniques
- `heap/` - Heap problems
- `priorityqueue/` - Priority queue implementations (MedianFinder, custom comparators)
- `stack/` - Stack implementations (MinStack)
- `queue/` - Queue implementations
- `map/` - Java Map examples (HashMap, TreeMap, LinkedHashMap)
- `set/` - Java Set examples (HashSet, TreeSet)
- `string/` - String manipulation problems
- `math/` - Mathematical algorithms
- `matrix/` - Matrix problems
- `doublelinkedlist/` - Doubly linked list implementations (LRU Cache)
- `segmenttree/` - Segment tree implementation
- `lambda/` - Java 8 lambda expressions and streams examples
- `innerclass/` - Inner class patterns (static, non-static, local, anonymous)
- `optional/` - Java Optional examples
- `serialization/` - Java serialization and externalization
- `streams/` - Java Streams API examples
- `general/` - General Java concepts

### Code Pattern

Each topic directory typically contains:
- `MainClass.java` - Entry point with test cases using JUnit assertions
- `Solution.java` - Problem solutions (may contain multiple methods)
- Supporting classes (e.g., `TreeNode`, `ListNode`, `Node`) for data structures

### Testing Approach

Tests are embedded in `MainClass.java` files using JUnit's `assertEquals`, `assertTrue`, and `assertFalse` methods. Run the main method to execute all test cases for that topic.

Example pattern from `array/MainClass.java`:
```java
Solution solution = new Solution();
assertEquals(expected, solution.methodName(input));
```

### Import Organization

Spotless enforces import organization:
- Standard import order is maintained
- Custom import order configured: `org`, `com` (with special grouping), `java|javax|jakarta`, static imports (with `#` prefix)
- Wildcard imports can be configured to come first or last
- Unused imports are automatically removed

## Key Points for Development

- The project uses Java 21 language features
- Code formatting is enforced via Spotless (import ordering, trailing whitespace, etc.)
- If the build fails with formatting violations, run `mvn spotless:apply` to auto-fix
- Each topic is self-contained with its own MainClass for testing
- No formal test directory structure; tests are in main classes
- Use 4 spaces for indentation (enforced by Spotless)
- Platform-native line endings are used
