# Salesforce — System Design (HLD) Question Bank

> **[← Back to README](README.md)**

The Infra Team process's rejection had no specific feedback, but the HLD round is the one flagged as the weak spot going into the Availability Team process — this file pulls together **every reported/aggregated Salesforce HLD prompt found** (Oct 2026 research pass) so prep isn't limited to the 5 problems already tracked in the main README. Treat every item here — including ones already marked solved in the README — as needing a *deeper trade-off pass*, not just a first-pass design.

---

## 🔴 Confirmed — Reported by Named Candidates

**Source confidence**: highest. Pulled from individual candidate interview-experience writeups, not aggregator summaries.

### 1. Notification Service ⭐ **Richest reported HLD round found**

**Reported requirements** ([source](https://takeuforward.org/community/interview-experiences/salesforce-senior-software-engineer-interview-experience)): support high- and low-priority notifications, templates, per-user preferences, Do Not Disturb (DND) windows, scalability, high availability, failure handling, resilience.

**Reported deep-dive — the round went heavily into Kafka specifics**:
- Partitions and partition-key choice (how do you avoid hot partitions for a high-fanout user?)
- Consumer groups and how they parallelize processing
- Message **ordering guarantees** — what's guaranteed per-partition vs. across partitions
- Retry strategy and **duplicate processing** (idempotent consumers)
- Failure recovery and **consumer lag** monitoring
- **Scaling consumers** when traffic increases (and the explicit follow-up: *"what happens if consumers can't process notifications as fast as producers are generating them?"* — backpressure / queue depth / autoscaling triggers)

**Prep angle**: don't treat this as "design a notification service" generically — rehearse the Kafka-internals follow-ups specifically, since that's where the reported round actually spent most of its time, not on the initial high-level box diagram.

**Practiced**: [session 18](https://github.com/ShubhamChouksey123/system-design/tree/master/practice/18-session).

### 2. Messaging System (Hiring Manager round, technical cross-over)

Reported as a technical question *within* the HM round itself, not just the dedicated HLD round — "design a Messaging System, including its scalability and reliability" ([source](https://takeuforward.org/community/interview-experiences/salesforce-senior-software-engineer-interview-experience)). Same interviewer also probed **hash function design** ("what makes a good hash function for a String") and a **Temporal-workflow-engine** discussion when the candidate had prior Temporal experience — be ready for system-design questions to surface outside the labeled "HLD round" too.

### 3. Resource Allocation / Token Bucket System

Reported pattern: a token-bucket-flavored resource-grant system with concurrent request support ([source](https://leetcode.com/discuss/post/8286427/)):
- Concurrency control: locking strategy (mutex vs. atomic operations) for grant/release
- Token expiry efficiently tracked via a **min-heap/priority queue**, not a linear scan
- Edge cases: partial grants, per-user token limits, background expiry sweeper

### 4. Distributed Rate Limiter (API Rate Limiting at Scale)

Reported repeatedly across multiple candidate writeups ([source](https://leetcode.com/discuss/post/8286427/)) as both a dedicated HLD prompt and a deep-dive extension of the token-bucket LLD problem:
- Token Bucket vs. Sliding Window algorithm trade-offs
- Centralized (Redis-backed) vs. decentralized/local in-memory limiting
- Consistency vs. performance trade-off, cache invalidation strategy, race conditions across a cluster
- Global limits vs. per-service limits

*(Already tracked as solved in the main README's HLD checklist — re-practice the centralized-vs-decentralized and global-vs-per-service framing specifically, since that's the layer most often probed as a follow-up.)*

### 5. Food Delivery App

Reported in an aggregated July-2026 SMTS hiring-drive writeup as both an HLD prompt and a Hiring-Manager-round design topic ([source](https://leetcode.com/discuss/post/8428080/salesforce-smts-hiring-drive-july-2026-b-g50i/)) — standard high-concurrency marketplace shape (restaurant/menu search, order placement, delivery assignment/dispatch, real-time order tracking, payment).

### 6. Real-Time Analytics / Event-Processing Pipeline

Reported theme: high-volume data-stream processing using Kafka + Flink, with emphasis on data ingestion, event processing, filtering logic, and system extensibility ([source](https://leetcode.com/discuss/post/8286427/)).

---

## 🟡 Thematic — Aggregated Prep-Guide Prompts (Not Individually Confirmed)

**Source confidence**: lower — these come from generic Salesforce-SWE prep-guide aggregations, not a specific candidate's writeup. Useful as backup practice, not guaranteed to appear.

7. **Venue Rental/Booking System** — search, book, manage venues; concurrency handling to prevent double-booking; search indexing. **Practiced**: [session 19](https://github.com/ShubhamChouksey123/system-design/blob/master/practice/19-session/README.md).
8. **Movie Ticket Booking Platform** — seat inventory under high concurrency; optimistic vs. pessimistic locking; payment flow; eventual-consistency patterns.
9. **Third-Party Tool Integration Platform** — securely connect external services; API gateway design; webhook handling and retry mechanisms. (Directly relevant if the Availability Team's domain touches integrations — worth promoting to high-priority practice regardless of confirmation status.)
10. **Synchronized Watch Party System** — real-time video sync across users, chat, permissions; WebSocket vs. polling; state synchronization; scaling video streams.
11. **Multi-Tenant Workflow Engine** (Salesforce-domain-flavored, not confirmed as an actual asked question — a plausible prompt given Salesforce's own platform shape): tenant isolation / noisy-neighbor prevention, metadata-driven workflow definitions, event-driven async execution, plugin/extensibility model for custom actions. Worth a design pass specifically *because* it mirrors what Salesforce itself builds (Flow, Process Builder-style engines) — interviewers in this domain reportedly reward designs that engage with multi-tenancy explicitly rather than treating it as a single-tenant system.

---

## Recurring Deep-Dive Themes (Rehearse Regardless of Which Base Prompt Comes Up)

These follow-up angles were reported across *multiple* different base prompts — meaning they're asked as probes into whatever system you design, not tied to one specific problem:

* **Kafka internals**: partitions, partition-key/hot-partition avoidance, consumer groups, ordering guarantees, retries, deduplication/idempotency, consumer lag, scaling consumers independently of producers.
* **Multi-tenant SaaS architecture**: shared-schema vs. isolated-database-per-tenant, noisy-neighbor prevention, tenant-scoped resource quotas.
* **Trade-off articulation**: "why this over that" — consistency vs. availability, normalization vs. denormalization, synchronous vs. asynchronous processing. Interviewers reportedly grade *justification*, not just the final pick.
* **Failure handling**: idempotent consumers, circuit breakers, dead-letter queues, retry with exponential backoff.
* **Extensibility**: "how would you let users/tenants add custom behavior without touching core logic" — plugin architecture, strategy pattern, rule-engine framing.

---

## Prep Checklist

For every problem below — **including ones already marked solved in the main README** — run this pass, since the gap last time was reportedly depth, not breadth:

1. State functional + non-functional requirements out loud before drawing anything (don't skip straight to boxes).
2. Draw the component diagram, then immediately name the data store and justify it (SQL vs. NoSQL, why).
3. Pick **one** component and go two levels deeper uninvited (e.g. if there's a queue, say what it is and why — Kafka vs. SQS vs. RabbitMQ — before being asked).
4. State one explicit trade-off and defend it when pushed back on.
5. Name one failure mode and how the design degrades gracefully (not just "it scales").

Ordered by how often each showed up across the research pass (most-reported first — tackle these before the thematic/unconfirmed ones):

* [x] Notification Service — 🔥 highest (richest single candidate report + repeated in multiple aggregated lists; priorities, templates, DND, Kafka deep-dive) — practiced in [session 18](https://github.com/ShubhamChouksey123/system-design/tree/master/practice/18-session)
* [ ] Distributed Rate Limiter — 🔥 highest (reported across the most distinct sources, both as a dedicated HLD prompt and an LLD extension)
* [ ] Resource Allocation / Token Bucket System — high (same source cluster as the Rate Limiter; concurrency, min-heap expiry)
* [ ] Food Delivery App — high (named in both an aggregate-guide list and a specific hiring-drive writeup)
* [ ] Messaging System — medium (one named-candidate report, but from the HM round specifically, not the dedicated HLD round)
* [ ] Real-Time Analytics / Event-Processing Pipeline — medium (one aggregate-guide mention; Kafka + Flink)
* [x] Venue Rental/Booking System — low (aggregate-guide only, not individually confirmed) — practiced in [session 19](https://github.com/ShubhamChouksey123/system-design/blob/master/practice/19-session/README.md)
* [ ] Movie Ticket Booking Platform — low (aggregate-guide only, not individually confirmed)
* [ ] Third-Party Tool Integration Platform — low by frequency, but worth promoting in practice order if the Availability Team's domain touches integrations
* [ ] Synchronized Watch Party System — low (aggregate-guide only, not individually confirmed)
* [ ] Multi-Tenant Workflow Engine — lowest (not confirmed as an actual asked question; included for domain relevance, not reported frequency)

---

## References

- https://takeuforward.org/community/interview-experiences/salesforce-senior-software-engineer-interview-experience
- https://leetcode.com/discuss/post/8286427/
- https://leetcode.com/discuss/post/8286427/salesforce-interview-experience-smts-bac-1dx4/
- https://leetcode.com/discuss/post/8428080/salesforce-smts-hiring-drive-july-2026-b-g50i/
- https://leetcode.com/discuss/post/8508596/salesforce-mts-interview-experience-indi-dcdb/
- https://leetcode.com/discuss/post/8480387/salesforce-smts-interview-experience-aug-0oak/

---

> **[← Back to README](README.md)**
