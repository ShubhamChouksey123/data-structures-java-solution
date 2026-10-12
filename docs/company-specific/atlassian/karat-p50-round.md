# Atlassian Karat Round — P50 (Senior) Only

Fresh research pass scoped **strictly to P50 (Senior)** candidate reports — P40 and other-level reports (e.g. Principal Engineer) were deliberately excluded, even where a question theme looked similar, to keep this list honest about what P50 candidates specifically faced.

## 📊 Progress Tracking

Track your overall progress: `7/12 items completed`

| Section | Progress |
|---------|----------|
| System Design — Rapid Fire (10, 2 sources) | `5/10` |
| Coding — Confirmed Karat Pairing | `2/2` |

---

## Round Structure (confirmed, P50)

- **~1 hour total**, run via **Karat** (third-party), an eliminator round.
- **~20 minutes — 5 rapid-fire system design questions**, roughly 2–3 minutes each. Not full whiteboard sessions; a crisp verbal approach is what's graded.
- **~30 minutes — 2 coding questions**, both requiring actual running code.
- One P50 candidate reported a **48-hour retake option** if the first attempt felt weak, with the better of the two attempts counted.
- Interviewers reportedly listen for specific terminology while you talk through designs: **scalability, fault tolerance, availability, chunking, security, geo-availability** — source: [P50 SSE Backend report](https://leetcode.com/discuss/post/7567242/atlassian-p50sse-backend-interview-exper-lb43/).

---

## Part 1: Rapid-Fire System Design Questions (10, from 2 confirmed P50 sources)

**Quick-review Q&A**: [`karat-rapid-fire-qna.md`](karat-rapid-fire-qna.md) — all 10 questions with condensed answers, self-contained in this repo for a fast last-pass read-through. Fuller worked versions (constraint/tension breakdown) live in the [`system-design` repo](https://github.com/ShubhamChouksey123/system-design/blob/master/docs/company-specific/atlassian/karat-system-design-rapid-fire.md).

**Source A**: [Atlassian Interview Experience, P50 Senior](https://leetcode.com/discuss/post/6817408/atlassian-interview-experience-p50-senio-t1zu/) — single candidate report, all 5 confirmed from the same P50 Karat session.

* [x] **1. Friend Count at Scale**: design a Facebook-clone feature that shows a user's current friend count next to every post, at massive read-heavy scale. Talking points: caching (Redis), read replicas, denormalized counters vs. live joins, sharding.
* [x] **2. Critique a Round-Robin Load Balancer**: a Google-Docs-like system assigns each document to a single server via round-robin — what breaks? Talking points: hotspots when a doc gets popular, server-capacity mismatches, consistent hashing or dynamic/least-loaded routing as alternatives.
* [x] **3. Consistency Models Across Three Systems**: choose strong vs. eventual consistency for (a) a low-latency video metadata API, (b) a web analytics/clickstream logging platform, (c) a banking system — and justify each choice differently.
* [x] **4. Multi-Stage Pipeline Throughput**: a pipeline has stages with capacities 10, 20, and 50 docs/second — what's the max throughput, and how would you optimize it? Talking point: throughput is bounded by the slowest stage; discuss parallelizing or scaling just that stage.
* [x] **5. Product-Agnostic Tagging System**: design tagging across Jira/Confluence/Bitbucket — add/remove tags, view all content for a given tag, a popular-tags dashboard. Talking points: inverted index (tag → entity IDs), cross-product entity modeling, top-N aggregation.

**Source B**: [Atlassian SSE Offer report](https://leetcode.com/discuss/post/6344788/atlassian-senior-software-engineer-offer-ywz3/) — P50, India-based — described as **"Round 1 – Third Party / Scenario Questions (20 min)"** (same ~20-min/5-question shape as Source A, "Third Party" strongly implying the same Karat-style vendor round even though not Karat-branded by name). Zero overlap with Source A's 5 questions.

* [ ] **6. Consistent Hashing Issues in Music Streaming Servers**: critique/discuss what goes wrong with consistent hashing for distributing load across music-streaming servers. Talking points: node addition/removal causing minimal but non-zero remapping, hot-key skew if some songs are far more popular than others, virtual nodes to smooth distribution.
* [ ] **7. Pre-loaded Hints vs. Server-loaded Hints**: pros/cons of shipping hints bundled with the app vs. fetching them from the server at runtime. Talking points: latency/offline-availability (client-side) vs. freshness/update-without-release (server-side), payload size trade-off, caching with TTL as a middle ground.
* [ ] **8. Processing Files Larger Than RAM**: how do you process a file that doesn't fit in memory on a single machine? Talking points: streaming/chunked reads, external merge sort pattern, memory-mapped files, or distributing the file across workers (MapReduce-style) if it's a repeated/large-scale need.
* [ ] **9. Sports News Classification Service (ML-based bias detection)**: design considerations for a service that classifies news content for bias using ML. Talking points: this is unusually ML/infra-flavored for a backend screen — focus on the *systems* half (ingestion pipeline, feature/model serving, async inference queue, feedback loop for retraining) rather than the ML modeling itself, since that's what a backend interviewer is actually grading.
* [ ] **10. Backend Considerations for Going Multi-Country**: what backend changes are needed when a production app expands beyond a single country? Talking points: data residency/compliance (GDPR-style regional storage), latency via regional deployments/CDN, timezone/locale handling, multi-region data consistency trade-offs.

**Also surfaced in Source B, NOT rapid-fire** (a separate full 30-min system-design round, kept here for awareness, not part of the 10 above): **Web Scraper** — crawl nested URLs and extract images. Worth a mental pass (BFS/queue over URLs, dedup via visited-set, rate-limiting per domain, distributed crawl queue at scale) but treat as lower priority than the 10 rapid-fire questions given the time constraint before this round.

**⚠️ Unconfirmed leads (NOT added as confirmed questions — do not treat as part of the 10 above)**: a broader Oct 2026 web sweep across LeetCode Discuss, Glassdoor, Blind, and GeeksforGeeks largely re-confirmed the 10 questions already listed and surfaced no new fully-confirmed P50-Karat question. Two leads came back below this file's confirmation bar and are flagged here only for optional, low-priority skimming — not practice targets:
- **Dynamic route-matching with wildcard paths (Trie-based)** and **"user action grouping"** (categorize users by action type, e.g. connect/disconnect) — reported in a post titled "Atlassian Karat round technical round," but candidate level wasn't stated (not confirmed P50) and round attribution is ambiguous (title suggests either the Karat vendor screen or a separate technical round). Direct page fetches were blocked by Cloudflare, so even the wording above is AI-search-synthesized, not a verified quote — if you want to spend 2 minutes confirming it yourself before the round: `leetcode.com/discuss/post/6766868/atlassian-karat-round-technical-round-co-3njv/`.
- Several other posts were surfaced only as bare IDs with no retrievable content, or were explicitly mixed P40/P50 reports with no way to attribute specific questions to the P50 candidate — excluded per this file's scope rule rather than guessed at.

---

## Part 2: 2 Coding Questions

### Confirmed Karat-Stage Pairing (P50)

**Source**: [Atlassian P50 Karat Stage](https://leetcode.com/discuss/post/6926273/atlassian-p50-karat-stage-by-anonymous_u-yja9/) — this report explicitly labels both as the Karat-stage coding pair for a P50 candidate.

* [x] **Robot Factory Parts**: given a list of available parts and, for each robot, its required parts, return which robots can be fully built. Approach: `HashMap<String,Integer>` frequency count of available parts; for each robot, check every required part's count is sufficient.
  - Problem writeup: [`robot-factory-parts.md`](robot-factory-parts.md)
  - Solution: [`RobotFactory.java`](../../../src/main/java/dev/shubham/problems/atlassian/RobotFactory.java)
* [x] **Delivery Cart Routes**: given a set of directed routes between locations, identify valid start locations (no incoming edges) and all possible ending locations (leaf nodes) reachable from each start. Approach: build an adjacency list/graph, find in-degree-0 nodes, DFS/BFS from each to collect leaves.
  - Problem writeup: [`delivery-cart-routes.md`](delivery-cart-routes.md)
  - Solution: [`DeliveryCart.java`](../../../src/main/java/dev/shubham/problems/atlassian/DeliveryCart.java)

### Other P50-Reported Coding Questions (round attribution unclear)

These showed up in P50-level candidate reports, but the source posts don't clearly distinguish whether they occurred in the **Karat round itself** vs. an **onsite round immediately following it** (Code Design / DSA). Listed here for completeness since Atlassian appears to draw from the same question bank across both — worth practicing regardless of which round they land in.

* **Rate Limiter** (with a credit system for unused requests; multi-threaded — discuss `ConcurrentHashMap` vs. `synchronized`) — same source as the confirmed pairing above, [6926273](https://leetcode.com/discuss/post/6926273/atlassian-p50-karat-stage-by-anonymous_u-yja9/), but attribution to Karat vs. a later round was ambiguous in aggregation.
* **Average Rating Calculation**: maintain customer-support agents' ratings (1–5 scale) from feedback, return agents ordered by average rating descending. Approach: `HashMap` for running sum/count per agent + a sorted structure (heap or `TreeMap`) for the ordering. Reported in the **"Code Design" round** of a [P50 interview experience](https://leetcode.com/discuss/post/6583356/atlassian-p50-interview-experience-by-an-1ov2/) — likely onsite, not Karat, but kept here since it's explicitly P50-attributed.
* **All O(1) Data Structure** (analog of [LeetCode 432](https://leetcode.com/problems/all-oone-data-structure/)): design a structure supporting insert/delete/increment/decrement and get-max/get-min all in O(1). Reported in a [P50 SSE Backend report](https://leetcode.com/discuss/post/7567242/atlassian-p50sse-backend-interview-exper-lb43/)'s DSA round.
* **Voting Algorithm**: determine the most-voted person from a stream of votes, with a reported follow-up to discuss the **Open-Closed Principle** in extending it. Attribution to Karat vs. onsite unclear.

---

## How to Prepare

Given the round is ~20 min design talk + ~30 min of two coding problems, the prep splits the same way — don't over-invest in one half at the other's expense.

### For the 5 rapid-fire design questions
- You get ~2–3 minutes each — that's barely enough for *one* structured answer, not a back-and-forth. Rehearse a **fixed micro-template**: (1) restate the constraint in one line, (2) name the core tension (e.g. latency vs. consistency, hotspot vs. simplicity), (3) state your pick and the one keyword that justifies it (sharding, consistent hashing, eventual consistency, bottleneck stage, inverted index), (4) stop. Don't ramble into a full design — that's not what 2–3 minutes rewards.
- Drill the 5 above specifically, out loud, with a timer: Friend Count at Scale, Load Balancer critique, Consistency Models (×3 sub-cases), Pipeline Throughput, Tagging System. Time-box each to under 3 minutes.
- Memorize the **interviewer-cue terms** from the Round Structure section (scalability, fault tolerance, availability, chunking, security, geo-availability) and consciously work at least 2–3 into each answer — they're reportedly used as a scoring signal, so saying the right word, not just the right idea, matters here.
- For the "critique X" style question (#2), practice critiquing *before* proposing — naming what's wrong (hotspots, SPOF, capacity mismatch) is the actual skill being tested, the fix is secondary.

### For the 2 coding questions
- Treat **Robot Factory Parts** and **Delivery Cart Routes** as your two must-solve problems — code them from scratch, from a blank file, under a 15-minute-each timer (30 min total matches the round). Don't just read the approach; type it out. (Both are now solved and reviewed — see [`RobotFactory.java`](../../../src/main/java/dev/shubham/problems/atlassian/RobotFactory.java) and [`DeliveryCart.java`](../../../src/main/java/dev/shubham/problems/atlassian/DeliveryCart.java) — re-type them from scratch without looking, don't just re-read the finished version.)
  - Robot Factory Parts is a frequency-counting / multiset-subset check — this pattern (count available resources, check each "recipe" fits) shows up a lot; once comfortable, it should take under 10 minutes.
  - Delivery Cart Routes is directed-graph source/leaf-node finding — practice computing in-degree-0 nodes and running DFS/BFS to leaves cleanly; this is the slower one to get right under pressure.
- Then run the four "unclear attribution" problems (Rate Limiter, Average Rating Calculation, All O(1) Data Structure, Voting Algorithm) as a second pass — same reasoning muscles, lower certainty they'll show up, so prioritize after the confirmed pair.
- Since no AI tools are allowed and you may need to recover mid-bug under time pressure, practice your **debugging habits** specifically: print-statement tracing and reading your own stack trace fast, since those are the only tools you're allowed to lean on if stuck.

### General
- Given the 48-hour retake policy some candidates got: if you ever get offered a retake, take it — don't assume the first attempt is final.
- Use the [`README.md`](README.md) in this same folder for the broader (non-Karat-specific) technical screen prep — fundamentals, concurrency practice, and the recruiter-confirmed format for *this specific role's* screen, which may differ from the Karat format researched here.

---

## Sources

- [Atlassian Interview Experience, P50 Senior](https://leetcode.com/discuss/post/6817408/atlassian-interview-experience-p50-senio-t1zu/) — primary source for rapid-fire questions 1–5.
- [Atlassian SSE Offer report](https://leetcode.com/discuss/post/6344788/atlassian-senior-software-engineer-offer-ywz3/) — second confirmed P50 source for rapid-fire questions 6–10 (its "Round 1 – Third Party / Scenario Questions"), plus the separate Word Search/Multithreaded Report Generation coding questions already tracked in `README.md`.
- [Atlassian P50 Karat Stage](https://leetcode.com/discuss/post/6926273/atlassian-p50-karat-stage-by-anonymous_u-yja9/) — primary source for the confirmed Karat coding pairing (Robot Factory Parts + Delivery Cart Routes) and the Rate Limiter question.
- [Atlassian P50 Interview Experience](https://leetcode.com/discuss/post/6583356/atlassian-p50-interview-experience-by-an-1ov2/) — Average Rating Calculation (Code Design round).
- [Atlassian P50 SSE Backend Interview Experience](https://leetcode.com/discuss/post/7567242/atlassian-p50sse-backend-interview-exper-lb43/) — All O(1) Data Structure, interviewer terminology cues, values/managerial round notes.

**Explicitly excluded from this file** (per scope): any P40-labeled report (e.g. "P40 India offer", "P40 5 YOE" posts) and the Principal Engineer report that originally seemed to share a similar "missed notifications" system-design theme — kept out since that candidate was not P50.
