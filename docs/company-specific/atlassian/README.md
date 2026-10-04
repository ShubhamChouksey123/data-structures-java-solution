# Atlassian — Technical Screen Prep (Senior Backend Software Engineer)

**Target role**: [Senior Backend Software Engineer @ Atlassian](https://simplify.jobs/p/470f544d-5181-41e5-ba1e-b9bc4fb32078/Senior-Backend-Software-Engineer) (Bengaluru, remote-eligible)

**Role signal** (from the posting — shapes what the screen likely probes):
- 5+ years backend; senior-level in **one of Java, Python, Kotlin, Go, Scala** — note the posting explicitly expects you to "transfer depth of knowledge from your current language to excel as a Java developer," so be ready to reason in Java even if that's not your primary language day-to-day.
- Hands-on with a public cloud (AWS/GCP/Azure) and SaaS/PaaS/IaaS concepts.
- Fluent in one DB technology — RDBMS (Oracle/Postgres) **or** NoSQL (DynamoDB/Cassandra).
- Builds well-tested, scalable, performant **microservices / distributed systems**.
- Evaluates trade-offs across correctness, robustness, performance, space, time.
- Mentors engineers, leads code reviews, drives projects autonomously — expect experience questions to probe seniority/leadership, not just raw coding.

## 📊 Progress Tracking

Track your overall progress: `3/33 items completed`

| Section | Progress |
|---------|----------|
| Concurrency & Data Structure Fundamentals | `0/4` |
| Coding — Actually-Reported Questions | `3/10` |
| Coding — Additional Practice (Lower Confidence) | `0/5` |
| Coding — Concurrency Practice | `0/4` |
| System Design Talking Points (Escalating) | `0/4` |
| Fallback: Karat-Run Screen Fundamentals & DSA | `0/6` |

---

## About the Round

**Source**: recruiter email confirming the actual process for this role (authoritative — treat as the primary expected format). A second, **unconfirmed fallback format** is kept further below in case the actual call doesn't match the email — recruiter emails are templated and the person sending it may not have full visibility into which interviewer/process variant you'll actually get.

- **Format**: 45-minute **video call with an Atlassian engineer** (not a third-party screener). First technical conversation in the process. Designed for **breadth over depth**.
- **Part 1 — Coding (20 min)**: One **multi-part** question that **escalates in complexity** as you progress (e.g., basic solution → add a constraint → handle concurrency/scale → optimize). Assessed on:
  - Conceptual thinking and how you write code (not just "does it run").
  - Adaptability — how you modify your existing solution when the problem evolves, rather than starting over.
  - Domain-specific concepts for backend: **data structures and concurrency** explicitly called out.
  - Your own language/IDE, or the **CodePair** link from the CX coordinator if you don't have one set up — **have this ready before the call**.
  - **No AI tools** (Copilot, ChatGPT, etc.) — disable AI plugins in your IDE beforehand. Debugging, docs, print statements, Stack Overflow, Google are fine.
  - The interviewer may screenshot/save your code for later reference.
- **Hard cutover at the 25-minute mark**: the call moves to system design regardless of coding progress. Don't panic if you're not "done" — partial progress with clear reasoning is the signal, not completion.
- **Part 2 — System Design (15 min)**: Conversation-based, also **escalates in complexity** (e.g., single-node solution → add scale → add a new requirement). Assessed on:
  - Building a workable solution and reasoning about it out loud.
  - Decision discussion — justify trade-offs when asked "why this over that?"
  - Systems thinking — how components interact, not just a list of buzzwords.
  - You can use a whiteboard, online diagramming (Zoom Whiteboard, Miro, Draw.io), or a text editor.
- **General tip from the recruiter**: it moves fast and is meant to gather early signal — keep moving forward, but it's fine to get stuck; the interviewer will clarify or guide if needed.

---

## Concurrency & Data Structure Fundamentals

Called out explicitly as a backend-specific assessment area within the coding round — review before the call, not during it.

* [ ] **Concurrency primitives**: `synchronized`, `ReentrantLock`, `Semaphore`, `volatile`, atomic variables (`AtomicInteger`, etc.) — know when each applies and why.
* [ ] **`java.util.concurrent` toolkit**: `ConcurrentHashMap`, `BlockingQueue` family, `ExecutorService`/thread pools, `CountDownLatch`, `CyclicBarrier`.
* [ ] **Common concurrency bugs**: race conditions, deadlock vs. livelock vs. starvation — be able to spot and name these in a code snippet, not just define them.
* [ ] **Data structure complexity refresh**: Big-O for array/ArrayList, LinkedList, HashMap, TreeMap, Heap/PriorityQueue operations — the question will likely pick a structure and probe your reasoning as it escalates.

---

## Coding — Actually-Reported Questions

**Source**: aggregated from Atlassian candidate interview-experience posts (LeetCode Discuss interview-experience threads, GeeksforGeeks "Atlassian Interview Experience" posts) — Oct 2026. These are question **themes actually reported** for Atlassian technical/Karat screens, not generic pattern practice — prioritize these over the generic trackers in [`docs/questions-list.md`](../../questions-list.md) if time is short.

### String & Stream Processing

* [x] **First Unique Character in a String** (reported follow-up: handle a streaming input / Unicode / huge strings) — [LeetCode 387](https://leetcode.com/problems/first-unique-character-in-a-string/)
* [ ] **First Unique Number in a Stream** — the natural escalation of the above when data arrives continuously instead of as a fixed string — [LeetCode 1429](https://leetcode.com/problems/first-unique-number/)
* [x] **Find Words That Can Be Formed by Characters** (reported as: "count the number of words that can be formed from a given string") — [LeetCode 1160](https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/)
* [ ] **Word Wrap / Split a String Into Lines Without Breaking Words** (reported as: "count characters and split a string into lines without breaking words") — closest LeetCode equivalent: [LeetCode 68 - Text Justification](https://leetcode.com/problems/text-justification/)

### Search & Arrays

* [x] **Find K Closest Elements** (binary search + two pointers) — [LeetCode 658](https://leetcode.com/problems/find-k-closest-elements/)

### Trees & Graphs

* [ ] **Word Search in Matrix, Restricted Movement** (reported: find if a word exists in a 2D matrix, moving **only down or right** — a tighter DFS/DP variant of the classic Word Search, not the free-direction [LeetCode 79](https://leetcode.com/problems/word-search/)) — [source](https://leetcode.com/discuss/post/6344788/Atlassian-or-Senior-Software-Engineer-or-Offer/)
* [x] **Lowest Common Ancestor** of a binary tree, with a reported follow-up to **generalize to an N-ary tree** — [LeetCode 236](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/), follow-up [source (Bengaluru SSE report)](https://leetcode.com/discuss/post/6504813/atlassian-sse-interview-bengaluru-reject-o1jm/)

### Design / Simulation (multi-part, escalating — closest match to the recruiter-described format)

* [ ] **Design Snake Game** (reported variant: "snake grows every 5 moves") — [LeetCode 353](https://leetcode.com/problems/design-snake-game/)
* [ ] **Rate Limiter**: build incrementally — fixed window → sliding window → token bucket → make it thread-safe (`ConcurrentHashMap`) → make it distributed. No single LeetCode problem matches this; practice building it from scratch in Java, since it's the most consistently reported coding question for backend screens.
* [ ] **Subscription/Billing Service** (reported with concrete numbers: JIRA $10/mo, CONFLUENCE $7.5/mo, BITBUCKET $8/mo; users buy any combination; given a year of usage (e.g. JIRA 14 days, CONFLUENCE 30 days, BITBUCKET 7 days) calculate monthly billing; follow-ups add discounts and trial periods, extensible design) — an OOP/LLD-style exercise, not a LeetCode problem; model it the way the Salesforce LLD checklist problems are modeled in [`docs/company-specific/salesforce/README-2.md`](../salesforce/README-2.md).

### Additional Practice (Lower Confidence)

**Source**: generic aggregator summaries of Atlassian Senior Backend (P50/P40) interview prep — these titles are **not independently confirmed** by a specific candidate report the way the section above is; treat as reasonable backup practice rather than guaranteed questions.

* [ ] **Merge Intervals** — [LeetCode 56](https://leetcode.com/problems/merge-intervals/)
* [ ] **Top K Frequent Elements** — [LeetCode 347](https://leetcode.com/problems/top-k-frequent-elements/)
* [ ] **Word Break** — [LeetCode 139](https://leetcode.com/problems/word-break/)
* [ ] **Clone Graph** — [LeetCode 133](https://leetcode.com/problems/clone-graph/)
* [ ] **LRU Cache with O(1) get/put** — [LeetCode 146](https://leetcode.com/problems/lru-cache/) (also doubles as backend-relevant: this is the most-cited LLD-adjacent coding problem for Atlassian senior backend loops specifically, even outside the technical screen).

## Coding — Concurrency Practice

Practice these specifically to rehearse the "escalate to concurrency" twist the coding question may apply to a plain data-structure problem.

* [ ] **Design Bounded Blocking Queue** — [LeetCode 1188](https://leetcode.com/problems/design-bounded-blocking-queue/)
* [ ] **Producer-Consumer with `BlockingQueue`**: implement from scratch using `wait`/`notify`, then again with `java.util.concurrent.BlockingQueue` — be ready to explain both.
* [ ] **Thread-Safe Counter / Bank Account**: implement with `synchronized`, then re-implement with `AtomicLong` or `ReentrantLock` — articulate the trade-off.
* [ ] **Thread-Safe LRU Cache**: take your LRU Cache solution above and make it safe for concurrent `get`/`put` — a very plausible "escalation" twist on a classic problem.

---

## System Design Talking Points (Escalating)

Practice walking each of these from a simple single-node version to a scaled, more complete version — mirroring how the real round escalates. For each: start with the simplest workable design, then layer on constraints (scale, consistency, new requirement) and narrate the trade-off at each step.

* [ ] **Rate Limiter**: single-instance in-memory token bucket → distributed, Redis-backed, multi-instance consistent limiting.
* [ ] **Notification System**: simple single-channel send → multi-channel fan-out (email/in-app/push) with dedup and retry/backoff.
* [ ] **Key-Value Store**: in-memory hash map → add TTL/eviction → add concurrent access safety → add sharding across nodes.
* [ ] **Tagging / Hashtag Aggregation** (Atlassian-flavored — Jira issues, Confluence pages): single-table tag lookup → efficient cross-entity tag queries at scale → real-time index updates as tags change.

---

## Fallback: Karat-Run Screen (Unconfirmed — Hedge Only)

**Why this section exists**: before the recruiter email arrived, research on Atlassian's technical screen pointed to a different, commonly-reported format — a **Karat-run** (third-party) call with a rapid-fire fundamentals segment before coding. The recruiter email above describes an Atlassian-engineer-run call instead. Keeping both here because:
- The recruiter may be working from a template and not know which variant you'll get.
- Process can vary by team/region, or get swapped last-minute (interviewer availability, scheduling tooling).

**Treat the section above as primary** — prep that first. Use this section only as a quick secondary pass if time allows, or as a fallback mental model if the actual call opens differently than described (e.g., a different interviewer than expected, or a rapid-fire opening before any coding starts).

**Concrete structure reported for a P50 Backend candidate** ([GeeksforGeeks interview experience](https://www.geeksforgeeks.org/interview-experiences/atlassian-interview-experience-for-sde-3-p50-backend-role/)): a preceding **HR screen** asked basic trees/binary-search/DB questions, then the **Karat round itself** was ~20 minutes across **5 rapid-fire mini system-design questions** followed by ~30 minutes of **two coding problems** (one Medium, one Hard LeetCode-style) — both had to produce running code. Notably: that candidate was offered a **retake within 48 hours** if the first attempt felt off, with the better of the two attempts counted.

**Reported topics for the 5 rapid-fire mini-design questions** (P50 Karat round, [source](https://leetcode.com/discuss/post/6817408/atlassian-interview-experience-p50-senio-t1zu/)):
- Scale a Facebook-clone feature that shows a user's current friend count next to every post (read-heavy at massive scale).
- Product-agnostic tagging system across Jira/Confluence/Bitbucket (add/remove tags, view all content for a tag, popular-tags dashboard).
- Rate limiter — Fixed Window vs. Sliding Window, concurrency via `ConcurrentHashMap`/`synchronized`.
- Subscription/billing service for Jira/Confluence/Bitbucket (usage-based, discounts, trial periods, extensibility).
- Maintain and calculate users' average rating, printed in descending order.

These are "explain your approach in a few minutes" style, not full whiteboard sessions — rehearse a 2–3 minute verbal walkthrough for each rather than a deep design.

**Karat-reported DSA coding problems** (distinct Karat-stage report, [source](https://leetcode.com/discuss/post/6926273/atlassian-p50-karat-stage-by-anonymous_u-yja9/)):

* [ ] **Robot Factory Parts**: given a list of available parts and, for each robot, its list of required parts, return which robots can be fully built (multiset/frequency-count matching — `HashMap<String, Integer>` of available-part counts vs. each robot's requirement).
* [ ] **Delivery Cart Routes**: given a set of directed routes between locations, identify valid start locations (no incoming edges) and all possible ending locations (leaf nodes) reachable from each start — build a graph, then DFS/BFS from origin nodes to leaves.

**Also worth knowing for this role level**: the same report stresses that for P50/P60 seniors, the **Values and Managerial rounds later in the loop** (not the technical screen) carry real weight — Atlassian can downlevel or reject on these even after strong technical rounds. Not actionable for *this* screen, but worth keeping in mind for the rounds that follow it.

* [ ] **OOP (Java-flavored)**: SOLID principles, inheritance vs. composition, polymorphism, interface vs. abstract class — one-line definition + quick Java example ready for each.
* [ ] **Networking**: TCP vs. UDP, HTTP request lifecycle, DNS resolution, REST basics, latency vs. bandwidth, "what happens when you type a URL into a browser."
* [ ] **OS fundamentals**: process vs. thread, CPU scheduling algorithms, deadlock vs. starvation, virtual memory basics — overlaps with the Concurrency Fundamentals section above, but phrased as rapid-fire short answers rather than applied coding.
* [ ] **Experience talking points**: same 2–3 minute narration prepped above (a system you built/debugged, the trade-off you made, a mentoring example) — reusable regardless of which format shows up.

If this format shows up instead: the **Coding** and **System Design** problem lists above still apply — a Karat-run screen typically still ends in one Easy/Medium coding problem and may fold in a lighter, less interactive system-design discussion.

---

## Round-Day Checklist

- [ ] Set up your IDE/language ahead of time (or confirm the CodePair link works) — **AI tools and plugins must be disabled**.
- [ ] Ask clarifying questions before coding: input size/constraints, data types, duplicate/empty/null edge cases.
- [ ] State a brute-force approach and its complexity out loud before optimizing.
- [ ] When the question escalates (new constraint, concurrency, scale), **adapt your existing code/design rather than restarting from scratch** — that adaptability is explicitly graded.
- [ ] Keep narrating your thinking even if stuck — the interviewer will clarify or guide; silence is worse than a wrong guess said out loud.
- [ ] Watch the clock yourself — expect a hard pivot to system design around the 25-minute mark regardless of coding completion.
- [ ] For system design: state the simplest workable solution first, then let the interviewer (or your own narration) drive the escalation — justify every trade-off, don't just list buzzwords.

---

## References

- Recruiter email confirming the actual Atlassian technical screen format for this role (primary source — Oct 2026).
- [Atlassian: How to Nail Your Engineering Interview](https://www.atlassian.com/company/careers/resources/interviewing/how-to-nail-your-engineering-interview)
- [Target job posting: Senior Backend Software Engineer @ Atlassian](https://simplify.jobs/p/470f544d-5181-41e5-ba1e-b9bc4fb32078/Senior-Backend-Software-Engineer)
