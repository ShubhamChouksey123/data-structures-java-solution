# Salesforce SMTS Interview Prep

## 📊 Progress Tracking

Track your overall progress: `0/33 items completed`

| Round | Progress |
|-------|----------|
| DSA Round | `0/13` |
| LLD / Machine Coding Round | `0/7` |
| HLD / System Design Round | `0/5` |
| HM / Behavioral Round | `0/8` |

---

### Data Structures & Algorithms (DSA) Round

DSA rounds for the SMTS level in Salesforce Hyderabad test algorithmic efficiency, edge-case handling, and the ability to write production-grade code on platforms like HackerRank.

#### Graphs & Shortest Paths

* [x] **Shortest Cycle in a Graph:** Find the shortest cycle length in a Directed Acyclic Graph (DAG) or directed graph. — [LeetCode 2608](https://leetcode.com/problems/shortest-cycle-in-a-graph/)
* [x] **Social Network Connectivity:** Given connections between users as an undirected graph, answer queries asking how many profile pages a specific user can view (using Connected Components via BFS/DFS or Disjoint Set Union). — [LeetCode 323](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/) / [LeetCode 547](https://leetcode.com/problems/number-of-provinces/)
* [x] **Rotten Oranges / Grid Traversal:** Multi-source BFS to calculate the minimum time required for all items in a grid to transition states. — [LeetCode 994](https://leetcode.com/problems/rotting-oranges/)

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

* [ ] **LRU Cache Implementation:** Implement an in-memory Least Recently Used cache using a hash map and a doubly linked list.
* [ ] **LFU Cache Implementation:** Implement a Least Frequently Used cache handling tie-breakers for equal frequencies using a double hash map with doubly linked lists.

#### Systems & Domain Modeling

* [ ] **Stock Trading & Order Matching Engine:** Design an LLD for a stock trading platform handling Limit and Market orders, portfolio updates, execution engine logic, and thread safety.
* [ ] **In-Memory Connection Pool:** Design a connection pool with an internal request queue.
  * Fixed number of active connections.
  * Blocking requests using synchronization primitives (`ReentrantLock`, `Condition`, `Semaphore`) when all connections are checked out.
* [ ] **Elevator System:** Design a multi-lift elevator controller. Use the **State Pattern** to represent lift movements (Idle, Moving Up, Moving Down) and the **Strategy Pattern** for optimal floor request dispatching.
* [ ] **Parking Lot System:** Design a multi-floor parking lot supporting spot allocation strategies, pricing rules, and concurrent spot updates.
* [ ] **Meeting Room Scheduler:** Design a booking system handling time-interval overlaps, recurrent meetings, room capacities, and booking cancellations.

---

### High-Level Design (HLD) / System Design Round

HLD rounds for SMTS demand focus on multi-tenant cloud architectures, data partitioning, caching, asynchronous processing, and API design.

#### Distributed Systems Infrastructure

* [ ] **Distributed Rate Limiter:**
  * Compare Token Bucket, Leaky Bucket, and Sliding Window Counter algorithms.
  * Architect a centralized Redis-backed rate limiter vs local in-memory caching layers, handling network latencies and race conditions across clusters.
* [ ] **Social Media Celebrity Problem (High-Fanout System):**
  * Design a post/feed fan-out system handling high-follower accounts.
  * Compare Push vs Pull feed models, message queues (Kafka), and write/read amplification mitigation strategies.
* [ ] **Real-time Messaging Platform (Slack / WhatsApp Web):**
  * Design core messaging, channel/group threads, user tagging, and presence notifications.
  * Select protocols (WebSockets vs Server-Sent Events vs MQTT), storage architecture (Cassandra/ScyllaDB for messages, Redis for online status), and message delivery status (Sent, Delivered, Read).

#### Cloud & Business Applications

* [ ] **Scalable Property Search & Reservation System:**
  * Design a cloud-native property platform handling listing, search, and booking concurrency.
  * Prevent double bookings using two-step API reservation (`POST /reserve` with temporary TTL hold via Redis distributed locks and `POST /confirm` post-payment).
  * Sync database changes to Elasticsearch under 500ms using Change Data Capture (CDC) via Debezium/Kafka.
  * Handle asynchronous bulk updating of thousands of merchant properties without blocking real-time reads.
* [ ] **Deployment & Rollout Infrastructure:**
  * Design zero-downtime deployment pipelines utilizing Blue-Green/Canary strategies for microservices.
  * Build a feature flag and incremental rollout system for mobile client feature releases.

---

### Hiring Manager (HM) / Behavioral & Leadership Round

This round combines architectural defense of past work with Salesforce leadership competencies.

#### Technical Leadership & Architecture Deep-Dive

* [ ] Walk through the end-to-end architecture of your most complex system. What components did you personally design, and what were the scaling constraints?
* [ ] How do you draft, review, and finalize technical design documents (RFDs) with principal architects and security teams?
* [ ] If you were asked to rebuild your current system from scratch with no cloud or vendor restrictions using open-source tools, what changes would you make?
* [ ] Explain your strategy for zero-downtime database migrations, backward-compatible API versioning, automated testing, and rollback mechanisms.

#### Situational & Behavioral Scenarios

* [ ] **Conflict Resolution:** Describe a time you had a strong technical disagreement with a Senior Engineer, QA lead, or Product Manager. How did you resolve it and align the team on a direction?
* [ ] **Incident Management & RCAs:** Walk through a critical production outage or bug caused by code you pushed. How did you handle the live incident, conduct the Root Cause Analysis (RCA), and implement preventive measures?
* [ ] **Unclear Requirements & Deadlines:** How do you approach designing and delivering a project when business requirements are ambiguous or change mid-sprint?
* [ ] **On-Call Pressure:** Share an example of a high-severity on-call incident you debugged under strict SLA constraints. How did you manage stakeholder communications?

---

Watch this [Salesforce Technical Interview Experience](https://www.youtube.com/live/qG5mKW5CTmg) to learn about candidate preparation strategies for technical rounds and DSA question expectations at Salesforce.
