# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Java 21 data structures & algorithms practice repository, built with Maven and Spring Boot 3.4.1 (Spring Boot is used for dependency management only — this is **not** a web application). Alongside the Java solutions, the repo maintains a substantial **revision-notes, progress-tracking, and interview-prep system** under `notes/`, `concepts/`, `docs/`, and `practice/` — see [Documentation System](#documentation-system) below, as it is where much of the ongoing work happens.

## Build and Development Commands

```bash
mvn clean install                 # Full build (runs Spotless + compiles)
mvn compile                       # Compile only
mvn spotless:check                # Verify formatting
mvn spotless:apply                # Auto-fix formatting
```

### Running / "Testing" a Topic

There is **no JUnit test runner and no `src/test` directory**. Tests are `assertEquals`/`assertTrue`/`assertFalse` calls embedded in each topic's `MainClass.java`, executed by running that class's `main`:

```bash
mvn compile exec:java -Dexec.mainClass="dev.shubham.algorithms.<topic>.MainClass"
# e.g. dev.shubham.algorithms.array.MainClass
```

To run a "single test", edit the relevant `MainClass.java` to call only the assertion(s) you want, then run it. (`exec-maven-plugin` is not declared in `pom.xml`; the `exec:java` prefix resolves automatically.)

**Spotless runs during the `compile` phase and fails the build on violations** — run `mvn spotless:apply` first if a build fails on formatting. Rules: 4-space indent, organized imports (order `org`, `com`, `java|javax|jakarta`, static), no unused imports, platform-native line endings.

## Java Code Architecture

- All solution code lives under `src/main/java/dev/shubham/algorithms/`, organized **one package per topic** (`array`, `tree`, `graph`, `dynamicprogramming`, `backtracking`, `binarysearch`, `bitmanupulation`, `doublelinkedlist`, `segmenttree`, `lambda`, `streams`, `optional`, `innerclass`, `serialization`, etc.).
- Each topic package is **self-contained** — no cross-package dependencies. A package typically holds:
  - `MainClass.java` — entry point with embedded assertion-based test cases
  - `Solution.java` — the solution methods (often several per file)
  - Supporting types as needed (`TreeNode`, `ListNode`, `Node`)
- Because each package is isolated, adding a new problem means working within (or adding) a single topic package; there is no shared framework to wire into.

## Documentation System

Three parallel documentation trees, each with its own authoring rules. **Read the relevant `GUIDELINES.md` before creating or editing docs** — the two formats are deliberately different.

### `notes/` — Algorithm & Pattern notes
- One folder per pattern (e.g. `notes/sliding-window/`, `notes/monotonic-stack/`), each containing `Notes.md`.
- Format: **flexible, UNNUMBERED H2 sections**, order `When to Use → Core Concept → Patterns → Common Mistakes → Problems → Key Takeaways`.
- Concept-focused, **minimal code** — full Java solutions only for problems marked `⭐ **IMPORTANT** ⭐` (max 1–2 per file). Target 2–5 min read (~150–200 lines).
- Rules: `notes/GUIDELINES.md`.

### `concepts/` — Data-structure reference
- One folder per structure (e.g. `concepts/stack/`, `concepts/queue/`), with `info.md` as the main file plus optional subtopic files (`concepts/queue/deque.md`, `concepts/array/2-d-array.md`).
- Format: **fixed 11-section structure with the exact heading names** in `concepts/GUIDELINES.md` (What is? → Time Complexity → Operations → Basic Operations → [Comparison] → Patterns → Gotchas → Interview Tips → Quick Reference → Key Insight). Code-rich. Max 300 lines/file.
- Rules: `concepts/GUIDELINES.md`.

### `docs/` — Trackers
- `docs/questions-list.md` — the master pattern-based study guide: a Table-of-Contents table (22 topics) plus per-topic problem checklists. **Date format is DD-MM-YYYY.**
- `docs/salesforce.md` — company-wise list (188 problems from the liquidslr company-wise repo), frequency-sorted, with its own solved-progress counter.

### `practice/` — Mock-interview log (repo root, **not** under `docs/`)
- One `NN.java` per mock interview, each holding the question (as comments), the code written during the session, and the interviewer's feedback verbatim.
- `practice/README.md` — the running log: a Sessions table (problem, pattern, verdict, and the three per-axis scores — Coding / Problem Solving / Communication), per-session summaries, consolidated tips, and a "How to Improve" section.
- `practice/narration-script.md` — a read-aloud rehearsal script (six-beat template + worked transcripts) for the communication axis.
- **When adding a session**: append a row to the Sessions table (linking `NN.java`), add a session summary, update the communication-score tally, and fold any new feedback into the tips. Links from these files to `notes/`/`concepts/` use a single `../` (the folder sits one level below root). In interview prep/review, lead with **communication coaching** — across all logged sessions it is the consistent weak axis, while coding/problem-solving already pass.

## Progress-Tracking Conventions (docs/)

These files are hand-maintained and must stay internally consistent. When a problem is solved or a topic revised, update **every** dependent field:

- **Marking a problem solved**: flip its checkbox `[ ]` → `[x]` (or `✅` in `salesforce.md`), then update:
  - the topic's `**Problems (N)**` list and, in `questions-list.md`, the TOC row's **Solved** cell (e.g. `1/6` → `2/6`, or `[ ]` → `✅` when all done);
  - the top **Progress Tracking** counter (`X/Y problems completed`) in that file. Each tracker file has its own counter — update only the file(s) that contain the problem. Not every `questions-list.md` problem appears in `salesforce.md` and vice-versa.
- **Adding a problem** to a topic: bump both the `**Problems (N)**` heading count and the TOC **Questions** count, and increment the total in the Progress counter.
- **Recording a revision**: set the TOC **Revised** cell to `✅` and append today's date to the comma-separated **Last Revision** list (DD-MM-YYYY).
- The same problem may be tracked in multiple places (`questions-list.md`, `salesforce.md`, a `notes/*/Notes.md` Problems list, a `concepts/*/info.md` Common Problems list) — when asked to mark it solved, update each place the user names.

## Key Points

- Java 21 language features are available and used.
- Keep each topic package dependency-free of other packages.
- After editing docs, prefer the DD-MM-YYYY date format and keep counts/counters consistent across the TOC, per-section headings, and the top progress line.
