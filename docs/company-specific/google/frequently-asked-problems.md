# Google — Frequently Asked Coding Problems

## ⚠️ 2026-Dated Interview Reports — verification status

A follow-up search specifically for *dated 2025–2026* candidate reports (not generic aggregator lists) found that most "2026" content is unverifiable SEO/content-marketing material (tryexponent, prepfully, interviewcoder.co, dataford.io, finalroundai, refontelearning, thelinuxcode, a Northeastern career-blog page) asserting trends with no real candidate account behind them. Treat anything below not marked "confirmed" as unverified.

**Confirmed (independently fetched, real post, real date):**
- [Teamblind — "Google L3 Interview Experience – March 2026"](https://www.teamblind.com/post/google-l3-interview-experience-march-2026-jjevvyqf), posted 24-06-2026 by user "metamateup". **Note: L3, not Senior/L4+**, so only loosely applicable to this role/level.
  - Phone screen: custom array problem (not a named LeetCode problem) — alternating left/right nearest-double-value search with value injection.
  - Onsite round 3: trie-based prefix search ("given words + a query prefix, return all words starting with that prefix") — maps loosely to [LeetCode 14](https://leetcode.com/problems/longest-common-prefix/) / [208](https://leetcode.com/problems/implement-trie-prefix-tree/) / [720](https://leetcode.com/problems/longest-word-in-dictionary/)-style problems, not a single canonical one.
  - Onsite round 4: explicitly **[LeetCode 2188](https://leetcode.com/problems/minimum-time-to-finish-the-race/)** (Minimum Time to Finish the Race, DP) with an unsolved follow-up extension. Candidate was rejected ~1 week later.
  - [ ] Practice LeetCode 2188 — the one problem in this file with a confirmed 2026 date behind it.

**Unverified / second-hand (could not independently confirm — tool-reported only):**
- A Glassdoor review summary citing a **Hyderabad "Full Stack Developer Interview," June 2026** — reportedly DSA on trees/maps plus managerial rounds. Could not open Glassdoor directly (login/paywall) to confirm this is real.
- LeetCode Discuss "L4 Interview Experience" (post 6339257) citing Burst Balloons, Substring with Concatenation of All Words, and Moving Average from Data Stream as Google L4 round problems — LeetCode Discuss blocked direct fetch (Cloudflare), so this could not be verified as a real/dated page.
- GreatFrontend lists two Google-tagged frontend problems (a string-grouping-by-offset problem, and a "random-removal map" with `put()`/`getAndRemoveRandom()`) — no date found, can't confirm these are current.

**Moderately corroborated trend (repeated across multiple secondary sources, none a candidate self-report):** a new 2026 **"Code Comprehension" round** where candidates use Google's own Gemini AI assistant to debug/analyze existing code, graded on prompt engineering and output validation. Repeats across tryexponent/prepfully/Northeastern career blog, but all are content-marketing sites, not verified candidate accounts — moderate confidence at best.

**Full-stack/frontend specific:** no dated 2026 candidate report found mentioning React/JS specifics for this role.

---

## Reported L5 Scenario Questions (no source link provided — unverified)

User-supplied summary with no citation/URL attached — treat with the same caution as the unverified items above, not as a confirmed candidate report. Kept because the scenarios are specific and plausible, but they could not be independently traced to a real posting.

### Phone Screen / Early Onsite Coding

* [ ] **Intervals & Car Rental Scheduling** — given N available rental cars and a set of reservation requests (pickup/return timestamps), assign requests to reusable cars while minimizing total cars required.
* [ ] **Graph Traversals (grid path)** — given a matrix/grid, find the longest path or return all paths satisfying a constraint (e.g. adjacent elements increasing or differing by 1).
* [ ] **String Parsing & Manipulation** — write an expression evaluator for math expressions, or a string-transform converter (e.g. `snake_case` → `camelCase`).
* [ ] **Meeting Concurrency** — determine all specific time intervals where exactly X meetings are active concurrently.

### Senior Coding & Algorithmic Problem Solving (Onsite)

* [ ] **Inferred Player Rankings / Graph Reachability** — given N players and M head-to-head outcomes, determine which players have a fully inferable ranking against every other competitor via DAG reachability.
* [ ] **Task Scheduler with Cooldown** — given task types where same-type tasks must be at least N units apart, calculate the minimum intervals needed to execute all tasks. (Maps closely to [LeetCode 621](https://leetcode.com/problems/task-scheduler/).)
* [ ] **Robot Vacuum Cleaner / Unknown Grid Exploration** — design a cleaning algorithm for a robot with limited sensor APIs (move forward, turn left/right) in an unknown grid layout. (Maps closely to [LeetCode 489](https://leetcode.com/problems/robot-room-cleaner/).)
* [ ] **Confusing Numbers / Digit Transformation** — given integer N, count numbers from 1 to N that produce valid, distinct numbers when rotated 180°. (Maps closely to [LeetCode 1067](https://leetcode.com/problems/confusing-number-ii/).)
* [ ] **Axis-Aligned Rectangles** — given 2D integer coordinates, find the area of the largest axis-aligned rectangle formed by 4 points. (Maps closely to [LeetCode 939](https://leetcode.com/problems/minimum-area-rectangle/).)

---

## Older Aggregator Baseline (not role/year-specific)

Pulled from LeetCode discuss threads reporting Google interview questions generally, grouped by topic — **not verified as current for 2026 or specific to the Senior Full Stack role**; kept as a baseline DSA refresher only. Linked from [README.md](README.md) → Onsite Coding Rounds. Sources: [leetcode.com/discuss/post/5196335](https://leetcode.com/discuss/post/5196335) ("Google DSA Interview Topics with Questions"), [leetcode.com/discuss/post/7417074](https://leetcode.com/discuss/post/7417074), [leetcode.com/discuss/post/7365897](https://leetcode.com/discuss/post/7365897) ("25 DP problems"), [interviewcoder.co/blog/leetcode-75](https://interviewcoder.co/blog/leetcode-75).

---

### Arrays & Strings

* [ ] **Two Sum** — [LeetCode 1](https://leetcode.com/problems/two-sum/)
* [ ] **Maximum Subarray** — [LeetCode 53](https://leetcode.com/problems/maximum-subarray/)
* [ ] **Longest Substring Without Repeating Characters** — [LeetCode 3](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
* [ ] **Product of Array Except Self** — [LeetCode 238](https://leetcode.com/problems/product-of-array-except-self/)
* [ ] **Search in Rotated Sorted Array** — [LeetCode 33](https://leetcode.com/problems/search-in-rotated-sorted-array/)

### Trees

* [ ] **Binary Tree Inorder Traversal** — [LeetCode 94](https://leetcode.com/problems/binary-tree-inorder-traversal/)
* [ ] **Lowest Common Ancestor of a BST** — [LeetCode 235](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)
* [ ] **Construct Binary Tree from Preorder and Inorder Traversal** — [LeetCode 105](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
* [ ] **Validate Binary Search Tree** — [LeetCode 98](https://leetcode.com/problems/validate-binary-search-tree/)
* [ ] **Binary Tree Maximum Path Sum** — [LeetCode 124](https://leetcode.com/problems/binary-tree-maximum-path-sum/)

### Graphs

* [ ] **Number of Islands** — [LeetCode 200](https://leetcode.com/problems/number-of-islands/)
* [ ] **Course Schedule** — [LeetCode 207](https://leetcode.com/problems/course-schedule/)
* [ ] **Word Ladder** — [LeetCode 127](https://leetcode.com/problems/word-ladder/)
* [ ] **Clone Graph** — [LeetCode 133](https://leetcode.com/problems/clone-graph/)
* [ ] **Evaluate Division** — [LeetCode 399](https://leetcode.com/problems/evaluate-division/)

### Dynamic Programming

* [ ] **Climbing Stairs** — [LeetCode 70](https://leetcode.com/problems/climbing-stairs/)
* [ ] **Coin Change** — [LeetCode 322](https://leetcode.com/problems/coin-change/)
* [ ] **Longest Increasing Subsequence** — [LeetCode 300](https://leetcode.com/problems/longest-increasing-subsequence/)
* [ ] **Unique Paths** — [LeetCode 62](https://leetcode.com/problems/unique-paths/)
* [ ] **Edit Distance** — [LeetCode 72](https://leetcode.com/problems/edit-distance/)
* [ ] https://leetcode.com/problems/h-index-ii/description/

---

> **See also**: [Prep tracker](README.md).
