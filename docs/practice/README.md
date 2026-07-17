# Mock Interview Practice

> Log of mock interview questions, solutions, and feedback — plus consolidated tips distilled from the feedback across sessions.

Each `NN.java` file in this folder contains the question (as comments), the code written during the interview, and the interviewer's feedback.

📣 **[Narration Script](narration-script.md)** — a read-aloud template + worked transcripts for rehearsing the communication axis (the consistent weak spot).

---

## Sessions

| # | Problem | Pattern | Verdict | Coding | Problem Solving | Communication |
|---|---------|---------|---------|--------|-----------------|---------------|
| [01](01.java) | Sum of First K Prime Powers | Min-heap `{value, prime}` (Top-K generation) | ⚠️ Borderline | 3/4 | 2/4 | 2/4 |
| [02](02.java) | King Kong vs Godzilla in the Fog | Monotonic stack (2 passes) | ✅ Pass | 3.5/4 | 3.5/4 | 3/4 |
| [03](03.java) | Most Non-Overlapping Intervals | Greedy (sort by end time) | ✅ Pass | 3/4 | 3/4 | 2/4 |
| [04](04.java) | Sub Permutations | Sliding window + freq count (`missing` counter) | ✅ Pass | 3/4 | 3/4 | 2.5/4 |
| [05](05.java) | Road Trip | DP (rolling variables, O(1) space) | ✅ Pass | 3.5/4 | 3/4 | 2.5/4 |
| [06](06.java) | Aligned Chain | Tree DFS (depth down, chain length up, global max) | ✅ Pass | 3/4 | 3/4 | **3/4** |

**Related notes**: [Monotonic Stack](../../notes/monotonic-stack/Notes.md) (02) · [Overlapping Intervals](../../notes/overlapping-intervals/Notes.md) (03) · [Top 'K' Elements](../../notes/top-k-elements/Notes.md) (01) · [Sliding Window](../../notes/sliding-window/Notes.md) (04) · [Dynamic Programming](../../notes/dynamic-programming/Notes.md) (05) · [Trees](../../notes/trees/Notes.md) (06)

---

## Session Summaries

### 01 — Sum of First K Prime Powers ⚠️ Borderline

**Problem**: Given distinct primes and `k`, return the sum of the first `k` positive powers of any prime, modulo `10^9+7`.

**Approach**: Min-heap of `{value, prime}` pairs; poll the smallest, reinsert `value * prime` only if it stays under the threshold (avoids overflow instead of using modulo mid-generation).

**What went well**: Identified the optimal min-heap approach quickly; handled the core logic and the overflow edge case.

**What hurt the score**: Struggled with the modulo/overflow reasoning (needed several hints), shaky time-complexity analysis, and unclear explanations. All logic was in `main` rather than a proper method.

---

### 02 — King Kong vs Godzilla in the Fog ✅ Pass

**Problem**: A building `street[i]` is spared iff there is a **taller** building within `k` to the **right** AND a **shorter** building within `k` to the **left**. Return the boolean array.

**Approach**: Two monotonic-stack passes — next-greater-to-the-right and previous-smaller-to-the-left, each with a distance-`≤ k` check — then AND the two results.

**What went well**: Strong problem decomposition into two independent subproblems; correct monotonic-stack choice; correct O(n) justification for the nested loops.

**What hurt the score**: Early left/right and "< k vs ≤ k" mix-ups; leftover debug prints; didn't proactively test edge cases.

---

### 03 — Most Non-Overlapping Intervals ✅ Pass

**Problem**: Given intervals `[l, r]` (inclusive), return the largest number of non-overlapping intervals.

**Approach**: Greedy — sort by end time, then walk through skipping every interval that overlaps the current selection.

**What went well**: Found the greedy "sort by end time" insight quickly; tested against multiple cases; correct complexity.

**What hurt the score**: Weak communication — asserted "it works" without explaining *why*; couldn't cleanly trace examples when challenged. Used an unconventional grouping variant instead of the standard `prevEnd`-tracking approach.

---

### 04 — Sub Permutations ✅ Pass

**Problem**: Count how many **distinct** permutations of `s1` appear as substrings in `s2` (`n1 ≤ n2`, lowercase only).

**Approach**: Fixed-size sliding window of length `n1` over `s2` with frequency counting. A `missing` counter tracks how many required characters the window still lacks, updated in O(1) as the window slides — a window matches when `missing == 0`. Dedup with a `HashSet`.

**What went well**: Found the sliding-window + frequency-count approach quickly; the `missing`-counter optimization; recognized the need for deduplication.

**What hurt the score**: Redundant `foundPermutations.contains()` check (line 64) — `HashSet.add()` already dedups. Unclear explanation of the `missing` conditions and complexity; went silent while thinking. Imprecise terminology ("headset" for HashSet).

---

### 05 — Road Trip ✅ Pass

**Problem**: `n` rest stops each with a detour time; never go more than 2 stops without a break. Return the minimum total detour time.

**Approach**: DP with recurrence `dp[i] = times[i] + min(dp[i-1], dp[i-2], dp[i-3])`, base cases the first three elements, answer = min of the last three. Space-optimized to O(1) with three rolling variables.

**What went well**: Identified DP and the recurrence; **proactively optimized O(n) → O(1) space before being asked**; correct edge case (`n ≤ 2` → 0); correct complexity; clean code with no hints needed.

**What hurt the score**: Recurrence initially stated imprecisely (added `1` instead of `times[i]`; "jump at i = jump at i-1 + 1" confused *cost* with *count*), fixed quickly. Took several rounds to articulate the recurrence clearly upfront.

**Re-solve (recorded, out-loud)**: Big step forward — **narrated continuously with no silent gaps** and hit nearly every template beat (restate → approach → DP state → recurrence → base cases → answer → space optimization → complexity), including proactive O(1) space + complexity with reasoning. Remaining polish:
- **Heavy filler** — 40+ "uh" and frequent "means/like"; the top issue now. Fix: replace "uh" with a short silent pause.
- **Skipped the trace** — restated the expected output but never walked the DP through `[8,1,2,3,9,6,2,4] → 6`. Add a trace before *and* after coding.
- **Imprecise framing** — called it "least *number of stops*" (it's least total *time*) and mixed "jump of 2/3"; tie the recurrence to the constraint: *"≤2 stops without a break ⇒ consecutive stops differ by ≤3 ⇒ look back 3."*
- Code slips in the re-solve: missing `;` in the `for` header and a `timToReach` typo — compile/run before declaring done.

---

### 06 — Aligned Chain ✅ Pass — 🎉 first 3/3/3

**Problem**: In a binary tree, a node is *aligned* if `value == depth`. Return the length of the longest chain of parent→child nodes that are all aligned (chain need not start at the root).

**Approach**: Single DFS — pass `depth` **down**, return the longest aligned-chain length **up**; a node contributes `1 + max(childChains)` only if `value == depth`, else the chain breaks (returns 0). Track the answer in a global max.

**What went well**: Reached the correct approach with **no hints**; explained the approach and **walked through the code clearly**. First session to score **3/4 on communication** — the deliberate-narration practice is paying off.

**What hurt the score**: A redundant leaf-node case (minor); explanations could still be a touch more concise and structured.

---

## Consolidated Tips

The historically weakest axis is **communication** (2, 3, 2, 2.5, 2.5, **3**) — but it is **trending up**, hitting a clean 3/4 in session 06. Keep drilling the polish items below. These tips are grouped by the axis interviewers score.

### Communication (highest priority)
- **State the approach in plain English before writing any code.** Use explicit structure: *"Step 1: … Step 2: … Step 3: …"*
- **Use the template**: *"My approach is X. I'll use data structure Y because Z. The time complexity is A because B."*
- **Explain WHY it's correct, not just THAT it works.** e.g. intervals: *"Picking the interval that ends earliest leaves the most room for future intervals, so it's always safe."*
- **When asked to trace an example, do it out loud, step by step** — show the state at each iteration, don't jump to the answer. **Don't go silent while thinking** (04) — verbalize even half-formed reasoning.
- **Restate the problem in your own words first**, and **clarify constraints upfront** (the `< k` vs `≤ k` confusion cost time in 02).
- **Use precise terminology** — say "HashSet," "remove from the left of the window," not "headset" / "delete the top" (04). Precision signals competence.
- **Cut filler words** — replace "uh / um / means / like" with a **short silent pause** (05 re-solve had 40+ "uh"). Silence reads as thoughtful; filler reads as unsure. This is the current top polish item.

### Complexity Analysis
- Make it **automatic** — 01 explicitly flagged this as a gap.
- Know the **size of each data structure** and the **cost of each operation** (heap push/poll = O(log n) in *heap size*, not input size).
- **Name variables precisely** — use `n1`/`n2`, not a single "n," when there are two inputs (04).
- **String ops are O(length), not O(1)** — `substring()`, hashing, and comparison all cost O(length) (04).
- **Lead with the reasoning**, then state the Big-O.

### Problem Solving
- **Identify tricky aspects early** (overflow/modulo in 01) *before* coding, not after.
- **Generate your own edge cases** before the interviewer does: empty input, single element, all-equal, none/all overlapping, shared endpoints, `k = array length`.
- After solving, **mention alternative approaches** even if you don't implement them (e.g. 02: reuse King Kong's logic for Godzilla by reversing/negating the array) — it shows depth.

### Coding
- **Extract the solution into a method** with parameters and a return value — don't write everything in `main`.
- **Remove debug print statements** before presenting the final answer (02 left prints on lines 35/53).
- **Prefer the standard, readable form** of an algorithm over a clever variant (03: track `prevEnd` rather than grouping) — simpler code = fewer bugs and easier discussion.
- **Drop redundant guards** — don't guard `HashSet.add()` with a `contains()` check; the set already dedups (04).
- **Trace through at least one example** after writing, walking the interviewer through it.

---

## Recurring Action Items

1. Drill **spoken complexity analysis** until it's reflexive.
2. Practice a **fixed "approach → data structure → why → complexity"** opening script.
3. Always **dry-run one example out loud** after coding.
4. **Surface edge cases and overflow risks upfront.**
5. Write solutions as **clean methods**, prints removed.

---

## How to Improve

### The diagnosis

Coding and problem-solving are **already at a passing bar** — scores sit at 3–3.5, and the right pattern gets found fast every session. **Communication is the sole bottleneck**: 2 / 3 / 2 / 2.5 / 2.5, last place in all five sessions. The good news — this is the *most trainable* axis. It's a performance skill (like rehearsing a talk), not missing knowledge.

**Implication**: grinding new problems won't move the needle much. Practicing *narration* will.

**Update (after the 05 re-solve and session 06)**: the *structural* problem is solved — continuous narration, full template beats, proactive optimization + complexity — and **06 landed the first 3/4 communication score**. The remaining gap is **polish, not structure**: (1) cut filler ("uh"/"means"/"like") — replace with silence; (2) always include an example **trace**, before and after coding; (3) state the recurrence/framing precisely on the first try; (4) keep explanations concise. Trajectory is upward; keep the recorded re-solve habit going.

### The opening script

Memorize this skeleton and fill the blanks out loud, every time. With a script you never improvise speech — so silence and rambling both disappear:

> "Let me restate the problem: I need to ___.
> My approach is ___. I'll use ___ because ___.
> Let me trace a small example: ___ → ___.
> Time is O(___) because ___; space is O(___).
> Edge cases: ___."

### Drills (ranked by impact)

1. **Re-solve, don't solve.** Re-do problems you've already done (01–04) *out loud on a timer*. The solution is easy the second time — that frees your whole brain for the talking, which is the weak part. Two recorded re-solves/week beats ten new problems.
2. **Record and rewatch.** Painful but decisive — you'll hear the mumbling, the "it works," the pauses you can't feel in the moment.
3. **No silent gaps.** The instant you think silently, narrate what you're weighing: *"I'm deciding whether the window check is `<` or `≤`…"* An interviewer can't score a silent brain. This alone likely moves 2 → 3.
4. **Narrate complexity as you write, not after.** When you add a heap, say "push is O(log heap size)"; when you call `substring`, say "that's O(length)." Bolt it to the code so you never recall it cold.
5. **State the *why*, not just the *what*.** After a working solution, give the one-sentence correctness argument (e.g. "earliest-ending interval leaves the most room, so it's always safe").

### What "improved" looks like

- Zero silent stretches longer than a few seconds.
- Complexity stated confidently, with `n1`/`n2`-style precise variables and correct O(length) for string ops.
- A traced example *before* coding and another *after*.
- Precise terminology throughout ("HashSet", "remove from the left of the window").

Target: **2–3 weeks of deliberate out-loud re-solving** to lift the communication score a full point. The hard part (solving) is already there — the points being left on the table are on the coachable part.
