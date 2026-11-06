# Salesforce SMTS Interview Prep

**See also**: [Company-Wise Questions (188 problems, frequency-ranked)](company-wise-questions.md)

This file now spans **two separate Salesforce SMTS processes** — an earlier **Infra Team** loop (closed, rejected) and a new **Availability Team** loop (active). The DSA / LLD / HLD prep below is **level-based, not team-specific**, and is reused across both; only the hiring-manager/culture round and the final outcome differ per process.

## 🆕 Active Process — Availability Team

**Interview confirmed**: **Fri, Nov 13, 2026**. A Salesforce HR recruiter called to restart the loop for a different team (**Availability**, not Infra). Treat this as a fresh process — the earlier Infra Team rejection (below) was for a different team and isn't assumed to carry any specific feedback forward.

---

## 🏁 Infra Team Process — Outcome (Closed, Sept–Oct 2026)

**Result: Rejected**, after clearing the Online Assessment (HackerRank) and completing both the **DSA Round** and the **HLD / System Design Round**. No specific feedback was given — HR sent a generic rejection email with no detail on which round or area was the deciding factor. Kept here as a factual record, not as a diagnosis — don't assume a particular weak spot without evidence.

**Post-mortem practice**: the actual DSA round question was a multi-part graph problem — (1) detect a cycle in a directed graph, (2) find the specific nodes involved in that cycle. Re-solved both parts afterward: **Solved**: [`CycleDetection.java`](../../../src/main/java/dev/shubham/problems/salesforce/CycleDetection.java).

---

## 📊 Progress Tracking

Track your overall progress: `31/46 items completed`

| Round | Progress |
|-------|----------|
| DSA Round | `10/14` |
| LLD / Machine Coding Round | `6/7` |
| HLD / System Design Round | `5/8` |
| HM / Behavioral Round | `10/17` |

---

### Data Structures & Algorithms (DSA) Round

DSA rounds for the SMTS level in Salesforce Hyderabad test algorithmic efficiency, edge-case handling, and the ability to write production-grade code on platforms like HackerRank.

#### Graphs & Shortest Paths

* [x] **Shortest Cycle in a Graph:** Find the shortest cycle length in a Directed Acyclic Graph (DAG) or directed graph. — [LeetCode 2608](https://leetcode.com/problems/shortest-cycle-in-a-graph/)
* [x] **Social Network Connectivity:** Given connections between users as an undirected graph, answer queries asking how many profile pages a specific user can view (using Connected Components via BFS/DFS or Disjoint Set Union). — [LeetCode 323](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/) / [LeetCode 547](https://leetcode.com/problems/number-of-provinces/)
* [x] **Rotten Oranges / Grid Traversal:** Multi-source BFS to calculate the minimum time required for all items in a grid to transition states. — [LeetCode 994](https://leetcode.com/problems/rotting-oranges/)
* [x] **Cycle Detection in a Directed Graph (multi-part):** First detect whether a directed graph has a cycle (Kahn's algorithm via in-degree/topological sort), then find the specific nodes involved in the cycle (DFS with a recursion-stack marker, trimming the path down to the back-edge target). **Solved**: [`CycleDetection.java`](../../../src/main/java/dev/shubham/problems/salesforce/CycleDetection.java).

#### Dynamic Programming & String Processing

* [x] **Subsequences Divisible by 4:** Given a large number string (length up to $10^5$), return the count of subsequences divisible by 4 modulo $10^9 + 7$. — Related: [LeetCode 974](https://leetcode.com/problems/subarray-sums-divisible-by-k/) & [LeetCode 115](https://leetcode.com/problems/distinct-subsequences/)
* [x] **Longest Substring Without Repeating Characters:** Optimized using sliding window and two-pointer technique. Candidates are probed on space complexity ($O(1)$ bound based on character set size vs $O(N)$). — [LeetCode 3](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
* [x] **ZigZag Traversal & Keypad Combinations:** Convert a string into ZigZag format across $K$ rows; generate letter combinations of a phone number using backtracking. — [LeetCode 6](https://leetcode.com/problems/zigzag-conversion/) / [LeetCode 17](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)
* [ ] **Maximum Profit Triplet:** Dynamic programming and array optimization to find valid triplets maximizing profit criteria. — [LeetCode 2874](https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/) / [LeetCode 2908](https://leetcode.com/problems/minimum-sum-of-mountain-triplets-i/)

#### Arrays, Sliding Window & Heaps

* [ ] **Subarray with K Distinct Integers:** Find the minimum length subarray containing exactly $K$ distinct integers. — [LeetCode 992](https://leetcode.com/problems/subarrays-with-k-different-integers/)
* [ ] **Max of Minima in Sliding Window:** Compute the minimum element for every sliding window of size $K$, then find the maximum among all these minima (Monotonic Queue / Stack). — [LeetCode 1950](https://leetcode.com/problems/maximum-of-minimum-values-in-all-subarrays/) / [LeetCode 239](https://leetcode.com/problems/sliding-window-maximum/)
* [ ] **Stream Operations with Offsets:** Process queries on a stream of numbers — [LeetCode 1825](https://leetcode.com/problems/finding-mk-average/) / [LeetCode 703](https://leetcode.com/problems/kth-largest-element-in-a-stream/)
  1. `(1, X)`: Add $X$ to the collection.
  2. `(2, X)`: Add $X$ to all existing elements (handled via global offset tracking).
  3. `(3)`: Output and evict the minimum element (Min-Heap with offset normalization).

#### Trees & Backtracking

* [x] **Vertical Order Traversal:** Print binary tree nodes column by column, optimizing space/time complexity by avoiding unnecessary sorting structures. — [LeetCode 987](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/) / [LeetCode 314](https://leetcode.com/problems/binary-tree-vertical-order-traversal/)
* [x] **Nodes at Distance Y:** Given a binary tree, a target node $X$, and a distance $Y$, return all nodes located at distance $Y$. — [LeetCode 863](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/)
* [x] **Sudoku Solver:** Implement a complete backtracking solver with constraint propagation and run custom test cases. — [LeetCode 37](https://leetcode.com/problems/sudoku-solver/)

---

### Low-Level Design (LLD) / Machine Coding Round

The LLD round evaluates object-oriented design (SOLID principles), concurrency handling, state management, design patterns, and runnable code quality.

#### Cache Engineering

* [x] **LRU Cache Implementation:** Implement an in-memory Least Recently Used cache using a hash map and a doubly linked list.
* [ ] **LFU Cache Implementation:** Implement a Least Frequently Used cache handling tie-breakers for equal frequencies using a double hash map with doubly linked lists.

#### Systems & Domain Modeling

* [x] **Stock Trading & Order Matching Engine:** Design an LLD for a stock trading platform handling Limit and Market orders, portfolio updates, execution engine logic, and thread safety.
* [x] **In-Memory Connection Pool:** Design a connection pool with an internal request queue.
  * Fixed number of active connections.
  * Blocking requests using synchronization primitives (`ReentrantLock`, `Condition`, `Semaphore`) when all connections are checked out.
* [x] **Elevator System:** Design a multi-lift elevator controller. Use the **State Pattern** to represent lift movements (Idle, Moving Up, Moving Down) and the **Strategy Pattern** for optimal floor request dispatching.
* [x] **Parking Lot System:** Design a multi-floor parking lot supporting spot allocation strategies, pricing rules, and concurrent spot updates.
* [x] **Meeting Room Scheduler:** Design a booking system handling time-interval overlaps, recurrent meetings, room capacities, and booking cancellations.

---

### High-Level Design (HLD) / System Design Round

HLD rounds for SMTS demand focus on multi-tenant cloud architectures, data partitioning, caching, asynchronous processing, and API design. **This was the flagged weak round from the Infra Team process** — [`system-design-question-bank.md`](system-design-question-bank.md) has the full research pass (confirmed + thematic prompts, recurring deep-dive themes like Kafka internals, and a re-practice checklist) and should be worked through before Nov 13, including re-practicing the items already checked off below.

#### Distributed Systems Infrastructure

* [x] **Distributed Rate Limiter:**
  * Compare Token Bucket, Leaky Bucket, and Sliding Window Counter algorithms.
  * Architect a centralized Redis-backed rate limiter vs local in-memory caching layers, handling network latencies and race conditions across clusters.
* [x] **Social Media Celebrity Problem (High-Fanout System):**
  * Design a post/feed fan-out system handling high-follower accounts.
  * Compare Push vs Pull feed models, message queues (Kafka), and write/read amplification mitigation strategies.
* [x] **Real-time Messaging Platform (Slack / WhatsApp Web):**
  * Design core messaging, channel/group threads, user tagging, and presence notifications.
  * Select protocols (WebSockets vs Server-Sent Events vs MQTT), storage architecture (Cassandra/ScyllaDB for messages, Redis for online status), and message delivery status (Sent, Delivered, Read).

#### Cloud & Business Applications

* [x] **Scalable Property Search & Reservation System:**
  * Design a cloud-native property platform handling listing, search, and booking concurrency.
  * Prevent double bookings using two-step API reservation (`POST /reserve` with temporary TTL hold via Redis distributed locks and `POST /confirm` post-payment).
  * Sync database changes to Elasticsearch under 500ms using Change Data Capture (CDC) via Debezium/Kafka.
  * Handle asynchronous bulk updating of thousands of merchant properties without blocking real-time reads.
* [x] **Deployment & Rollout Infrastructure:**
  * Design zero-downtime deployment pipelines utilizing Blue-Green/Canary strategies for microservices.
  * Build a feature flag and incremental rollout system for mobile client feature releases.

#### Newly Added (from the Oct 2026 HLD research pass — not yet practiced)

* [ ] **Notification Service**: high/low priority notifications, templates, per-user preferences, DND windows; heavy reported Kafka deep-dive (partitions, consumer groups, ordering, retries/dedup, consumer lag, scaling consumers vs. producers). Full detail: [`system-design-question-bank.md`](system-design-question-bank.md#1-notification-service--richest-reported-hld-round-found).
* [ ] **Resource Allocation / Token Bucket System**: concurrent grant/release with locking, token expiry via min-heap, per-user limits. Full detail: [`system-design-question-bank.md`](system-design-question-bank.md#3-resource-allocation--token-bucket-system).
* [ ] **Food Delivery App**: restaurant/menu search, order placement, delivery dispatch, real-time tracking, payment. Full detail: [`system-design-question-bank.md`](system-design-question-bank.md#5-food-delivery-app).

---

### Hiring Manager (HM) / Behavioral & Leadership Round

This round combines architectural defense of past work with Salesforce leadership competencies, scored against Salesforce's Core Values (Trust, Customer Success, Innovation, Equality, Sustainability). **Full prep doc, STAR(R) drafts, and the open action-item list** live in the separate [`behavioral-interview-prep`](https://github.com/ShubhamChouksey123/behavioral-interview-prep) repo — this checklist tracks status only; follow each link for the actual draft/answer.

**Primary system to walk through**: the Secrets Store platform (New Relic) — three-layer envelope encryption, 30K RPM, 99.99% SLA, with an evolution story (CDC pipeline added later) built in. Secondary backup: the Signing & Verification platform.

#### Technical Deep-Dive on Past Projects

* [x] **Architecture & scale** — draw the three-layer encryption design from memory, name the trade-offs. **Ready**: [`docs/salesforce-hm-round-prep.md` §1](https://github.com/ShubhamChouksey123/behavioral-interview-prep/blob/master/docs/salesforce-hm-round-prep.md#1-technical-deep-dive-on-past-projects).
* [x] **Non-functional (testing, deploy, monitoring, rollback)** — >90% test coverage, Terraform-managed infra; still missing an explicit zero-downtime migration/rollback story. **Adapt**: same doc, §1.
* [ ] **"If redesigned today, what would you change?"** — a judgment question, needs a real opinion on the CDC pipeline's build-in-from-day-one trade-off, not a hedge. **Gap**.
* [ ] **Hardest bug/outage investigated** — candidate: the connection-pool-exhaustion incident; needs a full STAR(R) draft (shared with the On-call item below — told with different emphasis each time). **Gap** (raw material exists).

#### Senior Technical Leadership & Ownership

* [x] **On-call & production incidents, RCAs** — full STAR(R) draft exists ([`notes/behavioral-question-bank.md` Q4](https://github.com/ShubhamChouksey123/behavioral-interview-prep/blob/master/notes/behavioral-question-bank.md)), currently generalized — needs re-angling toward *one specific instance* (the connection-pool-exhaustion incident) with leadership/prioritization emphasis. **Adapt**.
* [ ] **Design Doc approval & consensus** — likely candidate: CDC-pipeline proposal pushback; needs a DACI/Influence-Ladder-framed reconstruction of who pushed back and how it was resolved. **Gap**.
* [ ] **Mentorship & code reviews** — raw material: 200+ reviews, explicit mentoring; needs one concrete named PR/engineer instance, not the aggregate stat. **Gap**.

#### Behavioral Scenarios & Salesforce Core Values

* [x] **Trust & Accountability** — the immutable-map `NullPointerException` incident (PRs #383/#387/#388), self-caught and corrected next day. **Ready**: [`notes/behavioral-question-bank.md` Q5](https://github.com/ShubhamChouksey123/behavioral-interview-prep/blob/master/notes/behavioral-question-bank.md).
* [x] **Conflict Resolution** — a real conflict story exists ([Q3](https://github.com/ShubhamChouksey123/behavioral-interview-prep/blob/master/notes/behavioral-question-bank.md)) but is process/approval-flavored, not technical-direction; decide whether to reframe it or draft the CDC-pipeline pushback instead as a more literal fit. **Adapt, partial fit**.
* [x] **Innovation** — the SDD-workflow story (Q2) already is an Innovation story, filed under "Leading Without Authority"; just re-angle, no new drafting. **Adapt** (effectively ready).
* [ ] **Customer Success** — needs an MVP-slicing-under-ambiguity story (likely the Signing & Verification platform build); name what was deferred and why. **Gap**.
* [ ] **Equality** — no raw material exists; needs a genuine memory (mentoring across underrepresented groups, a bias-reducing process change), not a force-fit. **Gap**.
* [ ] **Sustainability** — no raw material exists; needs a real long-term-maintainability/cost angle, not a generic "I care about the environment" answer. **Gap**.

#### Questions to Ask the Hiring Manager

* [x] "What are the biggest technical or scaling challenges this specific team is tackling over the next 2–3 quarters?"
* [x] "How does the team balance long-term architecture refactoring with day-to-day feature delivery?"
* [x] "What does success look like for an SMTS coming into this team in their first 90 days?"
* [x] Secrets-Store-flavored addition: "Given [team]'s system operates at [scale/reliability bar], what's the current biggest gap between where the architecture is and where it needs to be for the next order of magnitude?" — swap `[team]` for **Availability** when asking.

**Remaining gaps / before the next mock**: full open action-item list (connection-pool-exhaustion STAR(R) draft, CDC-pipeline pushback story, mentorship story, MVP-slicing story, the redesign-today opinion, "Why Salesforce" research, Equality and Sustainability stories) tracked in [`docs/salesforce-hm-round-prep.md` — "Before the next mock" section](https://github.com/ShubhamChouksey123/behavioral-interview-prep/blob/master/docs/salesforce-hm-round-prep.md#before-the-next-mock--action-items).

---

Watch this [Salesforce Technical Interview Experience](https://www.youtube.com/live/qG5mKW5CTmg) to learn about candidate preparation strategies for technical rounds and DSA question expectations at Salesforce.
