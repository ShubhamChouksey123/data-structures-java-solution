# Data Structures and Algorithms in Java

A comprehensive collection of data structures and algorithms implementations and solutions in Java 21.

## Overview

This repository contains implementations and solutions to various data structures and algorithms problems, organized by topic. Each topic includes working examples with test cases to demonstrate the concepts.

## Tech Stack

- **Java**: 21 (LTS)
- **Build Tool**: Maven
- **Framework**: Spring Boot 3.4.1 (for dependency management only)
- **Testing**: JUnit Jupiter 5.11.4
- **Logging**: SLF4J 2.0.16
- **Code Formatting**: Spotless Maven Plugin 2.46.1

## Prerequisites

- Java 21 or higher
- Maven 3.6+

## Getting Started

### Build the Project

```bash
mvn clean install
```

### Compile Only

```bash
mvn compile
```

### Run a Specific Topic

Each topic has its own `MainClass.java` with test cases. To run a specific topic:

```bash
mvn compile exec:java -Dexec.mainClass="dev.shubham.algorithms.<topic>.MainClass"
```

**Examples:**

```bash
# Run array problems
mvn compile exec:java -Dexec.mainClass="dev.shubham.algorithms.array.MainClass"

# Run tree problems
mvn compile exec:java -Dexec.mainClass="dev.shubham.algorithms.tree.MainClass"

# Run linked list problems
mvn compile exec:java -Dexec.mainClass="dev.shubham.algorithms.linkedlist.MainClass"
```

## Project Structure

The codebase is organized by topic under `dev.shubham.algorithms`:

### Data Structures
- **array/** - Array problems and ArrayList utilities
- **linkedlist/** - Linked list implementations and problems
- **doublelinkedlist/** - Doubly linked list implementations (LRU Cache)
- **stack/** - Stack implementations (MinStack)
- **queue/** - Queue implementations
- **heap/** - Heap problems
- **priorityqueue/** - Priority queue implementations (MedianFinder, custom comparators)
- **tree/** - Binary tree problems, BST operations, tree traversals
- **graph/** - Graph algorithms, BFS/DFS, topological sort
- **segmenttree/** - Segment tree implementation

### Algorithms
- **binarysearch/** - Binary search variations
- **backtracking/** - Backtracking algorithms
- **dynamicprogramming/** - Dynamic programming problems and solutions
- **bitmanupulation/** - Bit manipulation techniques
- **math/** - Mathematical algorithms
- **matrix/** - Matrix problems

### Java Concepts
- **map/** - Java Map examples (HashMap, TreeMap, LinkedHashMap)
- **set/** - Java Set examples (HashSet, TreeSet)
- **string/** - String manipulation problems
- **lambda/** - Java 8 lambda expressions and streams examples
- **streams/** - Java Streams API examples
- **optional/** - Java Optional examples
- **innerclass/** - Inner class patterns (static, non-static, local, anonymous)
- **serialization/** - Java serialization and externalization
- **general/** - General Java concepts

## Code Formatting

This project uses Spotless for automated code formatting. Formatting is enforced during the compile phase.

### Check Formatting

```bash
mvn spotless:check
```

### Apply Formatting

```bash
mvn spotless:apply
```

**Note:** If you encounter build failures due to formatting violations, run `mvn spotless:apply` to auto-fix the issues.

## Code Pattern

Each topic directory typically contains:
- `MainClass.java` - Entry point with test cases using JUnit assertions
- `Solution.java` - Problem solutions (may contain multiple methods)
- Supporting classes for data structures (e.g., `TreeNode`, `ListNode`, `Node`)

### Testing Approach

Tests are embedded in `MainClass.java` files using JUnit's assertion methods. Run the main method to execute all test cases for that topic.

Example pattern:
```java
Solution solution = new Solution();
assertEquals(expected, solution.methodName(input));
assertTrue(solution.checkCondition(input));
```

## Development Guidelines

- Use 4 spaces for indentation (enforced by Spotless)
- Platform-native line endings
- Import organization is automatically handled by Spotless
- Each topic is self-contained with no cross-package dependencies

## Git Configuration

### Update Commit Author History

If you need to update the author information for all commits in the repository:

```bash
# Update all commits to use the correct author
git filter-branch --env-filter '
if [ "$GIT_COMMITTER_EMAIL" != "shubhamchouksey1998@gmail.com" ]; then
    export GIT_COMMITTER_NAME="ShubhamChouksey123"
    export GIT_COMMITTER_EMAIL="shubhamchouksey1998@gmail.com"
fi
if [ "$GIT_AUTHOR_EMAIL" != "shubhamchouksey1998@gmail.com" ]; then
    export GIT_AUTHOR_NAME="ShubhamChouksey123"
    export GIT_AUTHOR_EMAIL="shubhamchouksey1998@gmail.com"
fi
' --tag-name-filter cat -- --all

# Force push to remote (if already pushed)
git push --force-with-lease
```

**For just the last commit:**
```bash
git commit --amend --author="ShubhamChouksey123 <shubhamchouksey1998@gmail.com>" --no-edit
```

**Warning:** These commands rewrite Git history. If you've already pushed commits to a shared repository, coordinate with your team before force pushing.

## Author

**Shubham Chouksey**
- GitHub: [@ShubhamChouksey123](https://github.com/ShubhamChouksey123)
- Email: shubhamchouksey1998@gmail.com

## License

This project is for educational and practice purposes.
