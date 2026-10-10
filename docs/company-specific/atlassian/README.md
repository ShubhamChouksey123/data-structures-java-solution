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

Track your overall progress: `7/36 items completed`

| Section | Progress |
|---------|----------|
| Concurrency & Data Structure Fundamentals | `0/4` |
| Coding — Actually-Reported Questions | `5/10` |
| Coding — Additional Practice (Lower Confidence) | `1/7` |
| Coding — Concurrency Practice | `0/5` |
| System Design Talking Points (Escalating) | `1/4` |
| Fallback: Karat-Run Screen Fundamentals & DSA | `0/6` |

---

## About the Round

**Interview confirmed**: Tuesday, Oct 13, 2026, 6:45–7:30 PM IST — **"Backend Technical Screen Interview"** with **Addison Chung (Senior Software Engineer)** at Atlassian, via Zoom, with a **HackerRank CodePair** link offered as a fallback if a local IDE isn't available. This is a direct Atlassian-engineer-run screen — **not** the Karat-branded round (see the Fallback section below), confirmed by: a named Atlassian SWE as interviewer (Karat uses its own interviewers, never Atlassian employees), Zoom + HackerRank CodePair rather than Karat's own platform, and the invite's own title.

**Sources, in order of authority**:
1. **Atlassian's own official candidate-prep PDFs** (found via web search on Atlassian's CDN) — the most authoritative source, most likely what the invite's "Engineering Interview Handbook" link points to:
   - [P30-P50 Backend Interview Guide](https://wac-cdn.atlassian.com/dam/jcr:cf9c7fc1-ab28-47d6-bb56-9c01fb09b871/P30-P50-Backend-Interview-Guide.pdf)
   - [Backend.pdf](https://wac-cdn.atlassian.com/dam/jcr:931efbe4-fa94-4988-9867-7ad172981257/Backend.pdf)
   - [P60 Backend Engineer Guide](https://wac-cdn.atlassian.com/dam/jcr:7a71f595-39ee-48d1-979b-80f027b9c2d9/P60%20Backend%20Engineer%20Guide.pdf)
   - [Engineering interviewing resource hub](https://www.atlassian.com/company/careers/resources/interviewing/engineering)
2. Recruiter email for this role (templated, lower confidence than the official guide where the two conflict — see correction below).

**⚠️ Correction (per the official guide, supersedes the recruiter email's fixed split)**: the official guide describes this specific 45-minute screen as **coding-focused**, assessing coding skill, problem-solving, code quality, and communication. **System design may come up if time allows, but there is no confirmed fixed 20-min-coding/25-min-design split** — treat the "Part 1/Part 2 with a hard cutover at 25 min" structure below as the recruiter's (unconfirmed, possibly templated) description, not an official guarantee. Prepare coding as the primary focus; have a system-design mental model ready as a secondary possibility, not a certainty.

- **Format**: 45-minute **video call with an Atlassian engineer** (not a third-party screener). First technical conversation in the process. Designed for **breadth over depth**.
- **Part 1 — Coding (primary focus; ~20 min per the recruiter's description, unconfirmed by the official guide)**: One **multi-part** question that **escalates in complexity** as you progress (e.g., basic solution → add a constraint → handle concurrency/scale → optimize). Assessed on:
  - Conceptual thinking and how you write code (not just "does it run").
  - Adaptability — how you modify your existing solution when the problem evolves, rather than starting over.
  - Domain-specific concepts for backend: **data structures and concurrency** explicitly called out.
  - Your own language/IDE, or the **CodePair** link if you don't have one set up — **have this ready before the call** (confirmed in the actual invite).
  - **No AI tools** (Copilot, ChatGPT, etc.) — disable AI plugins in your IDE beforehand. Debugging, docs, print statements, Stack Overflow, Google are fine.
  - The interviewer may screenshot/save your code for later reference.
- **Possible cutover around the 25-minute mark** (recruiter-described, not officially confirmed): the call *may* move to system design regardless of coding progress. Don't panic if you're not "done" — partial progress with clear reasoning is the signal, not completion. If it doesn't happen, be ready to keep going deeper on the coding problem instead.
- **Part 2 — System Design (secondary, time-permitting — not guaranteed per the official guide)**: Conversation-based, also **escalates in complexity** (e.g., single-node solution → add scale → add a new requirement). Assessed on:
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

**Source**: aggregated from Atlassian candidate interview-experience posts (LeetCode Discuss, GeeksforGeeks, Glassdoor) — Oct 2026 research pass, deliberately scoped to **exclude Karat-attributed questions** (those live only in the [Fallback: Karat-Run Screen](#fallback-karat-run-screen-unconfirmed--hedge-only) section below — Robot Factory Parts and Delivery Cart Routes are Karat-specific and are *not* duplicated here).

**⚠️ Stage-attribution caveat**: no source found explicitly and unambiguously labels a question as "the single-Atlassian-engineer 45-min screen, specifically not Karat, not onsite." Atlassian interview-experience posts rarely name the screener's identity precisely enough to isolate this exact round. There's also a duration ambiguity worth flagging: some sources describe the **Karat-run** stage as 45 minutes, while Atlassian's own official guide describes the **direct-engineer coding round** as 60 minutes — your actual screen is 45 min but run by a named Atlassian engineer, so it may follow the Karat stage's *duration* while following the direct-engineer loop's *content/format* (per the official PDF: coding skill + code quality + communication, system design only if time allows). Treat everything below as **thematically** reported for Atlassian backend screens/loops in general, not confirmed to this exact round — prioritize the recurring *themes* (rate limiter/concurrency, small extensible OOD class design, string processing) over chasing an exact problem match.

**🏷️ LLD vs. HLD tagging (Oct 2026 research pass)**: every item below is now tagged **`[LLD]`** (write actual code — class/interface design, OOP patterns, in a live IDE), **`[HLD]`** (verbal/whiteboard architecture only — scaling, DBs, caching, no code), or **`[LLD+HLD]`** (reported as both, usually a coding stage followed by a "now scale it" verbal follow-up). This matters because `[LLD]` items need hands-on code-writing reps; `[HLD]` items need verbal rehearsal only — don't spend code-writing time on an `[HLD]`-only item. Items confirmed `[HLD]`-only were cross-checked against the DSA-vs-system-design repo split and, where miscategorized, moved to the `system-design` repo (see URL Shortener below).

### String & Stream Processing

* [x] **First Unique Character in a String** (reported follow-up: handle a streaming input / Unicode / huge strings) — [LeetCode 387](https://leetcode.com/problems/first-unique-character-in-a-string/)
* [ ] **First Unique Number in a Stream** — the natural escalation of the above when data arrives continuously instead of as a fixed string — [LeetCode 1429](https://leetcode.com/problems/first-unique-number/)
* [x] **Find Words That Can Be Formed by Characters** (reported as: "count the number of words that can be formed from a given string") — [LeetCode 1160](https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/)
* [ ] **Word Wrap / Split a String Into Lines Without Breaking Words** (reported as: "count characters and split a string into lines without breaking words") — closest LeetCode equivalent: [LeetCode 68 - Text Justification](https://leetcode.com/problems/text-justification/)

### Search & Arrays

* [x] **Find K Closest Elements** (binary search + two pointers) — [LeetCode 658](https://leetcode.com/problems/find-k-closest-elements/)

### Trees & Graphs

* [x] **Word Search in Matrix, Restricted Movement** (reported: find if a word exists in a 2D matrix, moving **only down or right** — a tighter DFS/DP variant of the classic Word Search, not the free-direction [LeetCode 79](https://leetcode.com/problems/word-search/)) — [source](https://leetcode.com/discuss/post/6344788/Atlassian-or-Senior-Software-Engineer-or-Offer/)
* [x] **Lowest Common Ancestor** of a binary tree, with a reported follow-up to **generalize to an N-ary tree** — [LeetCode 236](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/), follow-up [source (Bengaluru SSE report)](https://leetcode.com/discuss/post/6504813/atlassian-sse-interview-bengaluru-reject-o1jm/)

### Design / Simulation (multi-part, escalating — closest match to the recruiter-described format)

* [ ] **`[LLD]`** **Design Snake Game** (reported variant: "snake grows every 5 moves") — [LeetCode 353](https://leetcode.com/problems/design-snake-game/). Confirmed `[LLD]`: reported solutions use a concrete deque + hash-set data structure for the snake body/collision check — [source](https://leetcode.com/discuss/post/6807250).
* [ ] **`[LLD]`, with an `[HLD]` follow-up** **Subscription/Billing Service** (reported with concrete numbers: JIRA $10/mo, CONFLUENCE $7.5/mo, BITBUCKET $8/mo; users buy any combination; given a year of usage (e.g. JIRA 14 days, CONFLUENCE 30 days, BITBUCKET 7 days) calculate monthly billing; follow-ups add discounts and trial periods, extensible design) — an OOP/LLD-style exercise, not a LeetCode problem; model it the way the Salesforce LLD checklist problems are modeled in [`docs/company-specific/salesforce/README-2.md`](../salesforce/README-2.md). Confirmed `[LLD]`-first: source explicitly says "write modular, extensible code using OOP principles" with a concrete `monthlyCostList()`/`annualCost()` API; a separate report frames a "Cost Explorer" follow-up as partitioning/caching discussion (`[HLD]`) — [sources](https://leetcode.com/discuss/post/6907694).
* [ ] **`[LLD]`** **Voting Algorithm**: determine the most-voted-for person from a stream of votes, with a reported follow-up to discuss extending it via the **Open-Closed Principle** — an OOD/extensibility exercise, not a pure algorithm problem. Confirmed `[LLD]` (medium confidence — exact source post couldn't be re-verified, closest match implements ballot processing with weighted points + tie-break logic using concrete data structures) — [source](https://leetcode.com/discuss/interview-question/6061641).

### Additional Practice (Lower Confidence)

**Source**: generic aggregator summaries of Atlassian Senior Backend (P50/P40) interview prep, plus Glassdoor's role-filtered aggregate text for Senior Backend/Backend SWE — these titles are **not independently confirmed** by a specific candidate report the way the section above is; treat as reasonable backup practice rather than guaranteed questions.

* [ ] **`[LLD]`** **Merge Intervals** — [LeetCode 56](https://leetcode.com/problems/merge-intervals/)
* [x] **`[LLD]`** **Top K Frequent Elements** — [LeetCode 347](https://leetcode.com/problems/top-k-frequent-elements/)
* [ ] **`[LLD]`** **Word Break** — [LeetCode 139](https://leetcode.com/problems/word-break/)
* [ ] **`[LLD]`** **Clone Graph** — [LeetCode 133](https://leetcode.com/problems/clone-graph/)
* [ ] **`[LLD]`** **LRU Cache with O(1) get/put** — [LeetCode 146](https://leetcode.com/problems/lru-cache/) (also doubles as backend-relevant: this is the most-cited LLD-adjacent coding problem for Atlassian senior backend loops specifically, even outside the technical screen).
* [ ] **`[LLD]`** **Trie Implementation** (insert/search/startsWith from scratch) — see [`concepts/trie/info.md`](../../../concepts/trie/info.md) for the reference implementation and patterns. Confirmed `[LLD]`: a standard coding-round question, not a standalone system-design one.
* [ ] **`[LLD]` (lean, ambiguous source)** **Middleware Router with Regex Route Matching**: design a small HTTP-router-like class that registers path patterns (with wildcards/regex segments) and dispatches to the right handler — an OOD/string-matching hybrid, matches the "small system/class design over pure algorithm" theme. Source only confirms the *topic* (regex route matching), not code-vs-verbal framing — classified `[LLD]` by convention (it's phrased as "design a router *class*").

**Moved to `system-design` repo (confirmed `[HLD]`-only, no code-writing signal in any source)**:
- **URL Shortener** — every source frames this as a full system-design exercise (load balancer, cache, DB sharding, ID-generation scheme), never a coding exercise. Problem writeup: [`url-shortener.md`](https://github.com/ShubhamChouksey123/system-design/blob/master/docs/company-specific/atlassian/url-shortener.md).

## Coding — Concurrency Practice

**All `[LLD]`** — concurrency primitives are universally taught/asked as hands-on "write the class" exercises (`ReentrantLock` + condition variables, `LinkedHashMap`-based LRU, etc.), never as verbal-only system design, per general industry convention. Practice these specifically to rehearse the "escalate to concurrency" twist the coding question may apply to a plain data-structure problem.

* [ ] **`[LLD]`** **Design Bounded Blocking Queue** — [LeetCode 1188](https://leetcode.com/problems/design-bounded-blocking-queue/)
* [ ] **`[LLD]`** **Producer-Consumer with `BlockingQueue`**: implement from scratch using `wait`/`notify`, then again with `java.util.concurrent.BlockingQueue` — be ready to explain both.
* [ ] **`[LLD]`** **Thread-Safe Counter / Bank Account**: implement with `synchronized`, then re-implement with `AtomicLong` or `ReentrantLock` — articulate the trade-off.
* [ ] **`[LLD]`** **Thread-Safe LRU Cache**: take your LRU Cache solution above and make it safe for concurrent `get`/`put` — a very plausible "escalation" twist on a classic problem.
* [ ] **`[LLD]`** **Multithreaded Report Generation**: given files grouped by a collection ID, generate a report per collection using multiple threads safely (aggregation + thread-pool coordination). Confirmed `[LLD]`: source describes designing classes to aggregate file/collection sizes with explicit multithreading, not a verbal architecture discussion — [source](https://leetcode.com/discuss/post/6344788).

---

## System Design Talking Points (Escalating)

**Repo split**: as of this pass, DSA-flavored coding problems are tracked and solved in *this* repo; system-design-flavored problems (scaling, concurrency-as-architecture, distributed state) are tracked and solved in the sibling [`system-design`](https://github.com/ShubhamChouksey123/system-design) repo under its own `docs/company-specific/atlassian/`. Rate Limiter moved there in full (see below) since it's reported as escalating specifically into distributed/concurrent territory.

**All four items below confirmed `[HLD]`-only** (Oct 2026 research pass) — every source frames them as verbal/architecture discussions (partitioning, replication, consistency, caching), with no code-writing signal found anywhere, so no code-writing reps are needed for these specifically (beyond Rate Limiter's separately-tracked LLD half):

Practice walking each of these from a simple single-node version to a scaled, more complete version — mirroring how the real round escalates. For each: start with the simplest workable design, then layer on constraints (scale, consistency, new requirement) and narrate the trade-off at each step.

* [x] **`[LLD+HLD]`** **Rate Limiter**: single-instance in-memory token bucket → distributed, Redis-backed, multi-instance consistent limiting. Problem writeup (`[HLD]` scaling/distributed half): [`rate-limiter.md`](https://github.com/ShubhamChouksey123/system-design/blob/master/docs/company-specific/atlassian/rate-limiter.md) (`system-design` repo). **Solved** (`[LLD]` class-design/algorithm half — pluggable fixed-window/sliding-window/token-bucket strategy): [`session-07-rate-limiter.md`](https://github.com/ShubhamChouksey123/low-level-design/blob/main/practice/session-07-rate-limiter.md) (`low-level-design` repo).
* [ ] **`[HLD]`** **Notification System**: simple single-channel send → multi-channel fan-out (email/in-app/push) with dedup and retry/backoff.
* [ ] **`[HLD]`** **Key-Value Store**: in-memory hash map → add TTL/eviction → add concurrent access safety → add sharding across nodes.
* [ ] **`[HLD]`** **Tagging / Hashtag Aggregation** (Atlassian-flavored — Jira issues, Confluence pages): single-table tag lookup → efficient cross-entity tag queries at scale → real-time index updates as tags change. Confirmed `[HLD]`-only: the Karat-reported version of this question is explicitly framed as a "system design round" (API design, DB schema, scalability) in every source found — no report shows it as a code-writing exercise, so it correctly belongs only here and in the Karat fallback section below, not in any coding list.

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
- https://leetcode.com/discuss/post/6817408/atlassian-interview-experience-p50-senio-t1zu/
- https://www.glassdoor.co.in/Interview/Atlassian-Senior-Backend-Software-Engineer-Interview-Questions-EI_IE115699.0,9_KO10,42.htm
