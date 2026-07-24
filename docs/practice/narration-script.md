# Interview Narration Script

> A read-aloud script for rehearsing interview communication — your consistent weak axis. Solving is already at a passing bar; this trains the *talking*. Read the template, then the worked transcripts, out loud. The goal is for this structure to become reflex.

**How to use**: Pick a problem you've already solved. Read the matching worked transcript aloud once. Then re-solve that problem yourself, out loud, following the template — recorded. Play it back, note silent gaps, do it again. ~15 min/day. Do **not** grind new problems; the point is to free your brain from solving so it can train speaking.

---

## The Universal Template

Say these six beats **in order**, every time. Fill the blanks. It's meant to be mechanical — predictable structure beats eloquence.

```
1. RESTATE
   "Let me restate to make sure I understand: I have ___,
    and I need to ___. Let me confirm with the example: ___ → ___."

2. APPROACH  (name 2–3 options, then commit)
   "A brute-force way is ___. A better way is ___ because ___.
    I'll go with ___." — briefly weighing options reads as depth.

3. DATA STRUCTURE / STATE + WHY
   "I'll use ___ because ___."
   (DP: "I'll define my state as dp[i] = ___.")

4. THE CORE STEP
   "The key step / recurrence is: ___.
    Base case(s): ___.  The answer is ___."

5. COMPLEXITY (say the reasoning, THEN the Big-O)
   "Each ___ costs ___, and I do it ___ times, so time is O(___).
    For space, I keep ___, so O(___)."

6. TRACE + EDGE CASES
   "Let me trace a small example: ___.
    Edge cases I'll check: ___."
   → then code, narrating each block; → then trace once after coding.
```

**Golden rule**: never let a silent gap last more than a couple seconds. If you're thinking, *say what you're thinking about*: *"I'm deciding whether this check should be `<` or `≤`…"*

**Two rules the 05 re-solve exposed** (structure was solid — these are the polish that separates 2.5 from 3.5):
- **Replace "uh" with silence, not sound.** A short pause reads as thoughtful; "uh… uh… means… like…" reads as unsure. When a filler word is coming, close your mouth instead.
- **Never skip beat 6 (the trace).** Restating the expected *output* is not a trace — you must walk the actual values through, e.g. *"dp starts [8,1,2]; at index 3: 3+min(2,1,8)=4; …"*. Do it once before coding to validate the logic and once after to validate the code.

---

## Worked Transcript A — Road Trip (DP)

Read this aloud. Notice how boring and structured it is — that is the target.

> "Let me restate to make sure I understand: I have `n` rest stops, each with a detour cost, and I can't skip more than 2 in a row. I want the minimum total detour. Let me confirm with the example — `[8,1,2,3,9,6,2,4]` gives 6.
>
> This looks like a **dynamic programming** problem, because the best choice at each stop depends on earlier choices.
>
> I'll define my state as **`dp[i]` = the minimum detour cost for a valid trip that stops at stop `i`.**
>
> The recurrence: if I stop at `i`, my previous stop was at most 2 back — so `i-1`, `i-2`, or `i-3`, because I can't skip 3 in a row. So **`dp[i] = times[i] + min(dp[i-1], dp[i-2], dp[i-3])`**. Base cases are the first three stops, each just its own cost. The answer is the min of the last three `dp` values, since my final stop must be within 2 of the end.
>
> For complexity: it's a single pass and each step does constant work, so **O(n) time**. I only ever look 3 back, so instead of an array I'll keep **three rolling variables — O(1) space**.
>
> Let me trace `[10,10]`: `n ≤ 2`, so I return 0 — matches. Now let me code it… [narrate each block] … and let me trace the first example through the code to confirm."

---

## Worked Transcript B — Sub Permutations (Sliding Window)

> "Let me restate: given `s1` and `s2`, I need to count how many **distinct** permutations of `s1` appear as substrings of `s2`. Example: `s1 = "bat"`, `s2 = "tabbathat"` → 2, which are "tab" and "bat".
>
> A permutation match means a substring with the **same character frequencies** as `s1`. Since all such substrings have length `n1`, this is a **fixed-size sliding window** problem over `s2`.
>
> I'll use a **frequency array of size 26** for `s1`, and slide a window of length `n1` across `s2`, keeping a `missing` counter — the number of characters the window still needs. When `missing == 0`, the window is a match. I'll collect matches in a **HashSet** to keep them distinct.
>
> As the window slides one step: the character leaving on the left and the character entering on the right each adjust `missing` in **O(1)**.
>
> For complexity: the window slides `n2` times with constant work per step, so **O(n2) time**; the frequency arrays are fixed size 26, and the set holds the distinct matches, so **O(n1 × number of matches)** space in the worst case. Note `substring()` to build each match key is O(n1), not O(1).
>
> Edge cases: `n1 > n2` → zero; all-identical characters like `s1="aaa"`, `s2="aaaa"` → 1. Let me code it… and trace `"bat"` / `"tabbathat"` to confirm."

---

## Worked Transcript C — Merge Two Sorted Arrays (Two Pointers)

Short problems still get the full six beats — **and state complexity without being asked**.

> "Let me restate: I'm given two ascending-sorted arrays and I need one merged sorted array, keeping duplicates. Confirm with the example — `[1,3,4,5]` and `[2,4,4]` → `[1,2,3,4,4,4,5]`.
>
> Both inputs are already sorted, so this is a **two-pointer merge** — I interleave them rather than concatenating and re-sorting.
>
> I'll keep pointer `i` on `arr1`, `j` on `arr2`, and a result array of size `n + m`. At each step I compare `arr1[i]` and `arr2[j]`, write the smaller, and advance that pointer. Once one array is exhausted, I append whatever remains of the other.
>
> Complexity — stating it now: every element is written exactly once, so **O(n + m) time** and **O(n + m) space** for the result.
>
> Edge cases: one array empty → return the other; very different lengths; all duplicates. Let me trace `[1,3]` + `[2]` → `1, 2, 3`. Now I'll code it as three loops — the main merge, then drain `arr1`, then drain `arr2` — which reads cleaner than one nested if-else."

---

## Phrase Bank — for the moments you freeze

**Opening (buys thinking time, sounds deliberate):**
- "Let me restate the problem in my own words first."
- "Before I code, let me lay out the full approach."

**Justifying WHY (not just what):**
- "I'm choosing ___ because it gives me ___ in O(___)."
- Greedy: "Picking ___ first is safe because it leaves the most room for later choices."

**Complexity, stated confidently:**
- "Heap push and poll are O(log k) where k is the heap size."
- "String `substring`, hashing, and comparison are O(length), not O(1)."
- "With two inputs I'll call their sizes `n1` and `n2`, not just `n`."

**When you get stuck (stay audible):**
- "Let me think out loud — the tricky part here is ___."
- "One risk I see is ___ (e.g. integer overflow); let me handle that by ___."

**Driving optimizations (never say "I'm not sure"):**
- "A more space-efficient version would ___ — for a string/array, can I do this in-place?"
- "The classic trick here is **reverse-twice**: reverse the whole thing, then reverse each piece."
- "This is O(___) now; to improve it I'd look at ___."

**Before finishing:**
- "Let me trace one example through my code."
- "Edge cases: empty input, single element, all-equal, `k` = length. Let me check each."

---

## Daily Rehearsal Routine (~15 min)

1. Pick one already-solved problem (01–09).
2. Read a worked transcript (A, B, or C) — or write your own from the template — **aloud**.
3. Re-solve it out loud, **recording**, following the six beats.
4. Play back and score yourself: **count the "uh"/"um"/"means"/"like"** (aim to halve it each week), silent gaps > 2s, any "it works" without a *why*, a missing trace, missed complexity.
5. Immediately do a **second take**. It will be cleaner.

Two problems/day, each twice. In a week the six beats become automatic and the silent gaps close.

---

> **See also**: [Practice log & tips](README.md) · related pattern notes are linked from the sessions table there.
