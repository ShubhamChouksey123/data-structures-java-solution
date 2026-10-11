# Concurrency & Data Structure Fundamentals — Cheat Sheet

> Condensed last-read-through for the 4 items in [`README.md`](README.md)'s "Concurrency & Data Structure Fundamentals" section. Called out explicitly as a backend-specific assessment area within the coding round — the interviewer is reported to probe this *as the coding question escalates*, not as a separate quiz.

---

## 1. Concurrency Primitives

**Moved to the `low-level-design` repo** — this is generic Java/LLD knowledge, not Atlassian-specific, and that repo already had a planned-but-unstarted slot for it. Full writeup (with code examples, gotchas, and a decision table): [`concepts/concurrency-primitives.md`](https://github.com/ShubhamChouksey123/low-level-design/blob/main/concepts/concurrency-primitives.md).

Quick recall for this cheat sheet's purposes — `synchronized` (simplest lock), `ReentrantLock` (adds `tryLock`/timeout/fairness), `Semaphore` (bound concurrent access to N resources), `volatile` (visibility only, not atomicity), `AtomicInteger`/`AtomicLong` (lock-free single-variable updates via CAS).

---

## 2. `java.util.concurrent` Toolkit

| Tool | Role |
|---|---|
| `ConcurrentHashMap` | Thread-safe map, lock striping (segment-level locking internally) — no global lock on reads, fine-grained on writes. Use instead of `synchronized HashMap` or `Collections.synchronizedMap`. |
| `BlockingQueue` family (`ArrayBlockingQueue`, `LinkedBlockingQueue`) | Queue with blocking `put`/`take` — the backbone of producer-consumer. |
| `ExecutorService` / thread pools | Managed thread lifecycle — submit tasks, don't manually spawn `Thread`s. `Executors.newFixedThreadPool(n)` is the common one to name. |
| `CountDownLatch` | One-time gate — N threads call `countDown()`, waiters block on `await()` until count hits 0. Not reusable. |
| `CyclicBarrier` | Like `CountDownLatch` but **reusable** — all N threads must arrive before any proceed, then it resets. |

**One-liner distinguishing the last two**: `CountDownLatch` = "wait for N things to finish" (one-shot); `CyclicBarrier` = "wait for N threads to reach this point together, repeatedly" (reusable rendezvous).

---

## 3. Common Concurrency Bugs — Name & Spot These

| Bug | Definition | Spot it in code by | How to fix |
|---|---|---|---|
| **Race condition** | Outcome depends on thread timing/interleaving — unsynchronized read-modify-write on shared state. | Multiple threads touching the same non-atomic field/collection without a lock. | Guard the read-modify-write with a lock (`synchronized`/`ReentrantLock`), or replace it with an atomic type (`AtomicInteger`) / a concurrent collection (`ConcurrentHashMap`) if it's a single variable/map. |
| **Deadlock** | Two+ threads each hold a lock the other needs — circular wait, nobody proceeds. | Nested locks acquired in **inconsistent order** across different code paths (classic: Thread A locks X then Y, Thread B locks Y then X). | **Lock ordering** — always acquire locks in the same global order everywhere (e.g. order by object hash/ID before locking two objects). Alternatively use `tryLock(timeout)` and back off/retry instead of blocking indefinitely. |
| **Livelock** | Threads keep changing state in response to each other but **no thread makes progress** (like two people repeatedly stepping aside for each other in a hallway). | Retry-on-conflict loops with no backoff/priority — both sides keep yielding. | **Randomized backoff** before retrying (breaks the lockstep symmetry), or a deterministic **priority/ordering rule** (e.g. lower thread ID always wins a conflict) so retries stop colliding identically. |
| **Starvation** | A thread **never** gets CPU time or a lock because other threads are prioritized indefinitely. | Unfair locks, or a thread always losing a race to higher-priority/more-frequent threads. | **Fairness policy** (`new ReentrantLock(true)` — first-come-first-served queueing), or bound how long any one thread can hold a resource so others get a guaranteed turn. |

---

## 4. Data Structure Complexity Refresher

| Structure | Access | Search | Insert | Delete | Notes |
|---|---|---|---|---|---|
| Array / ArrayList | O(1) | O(n) | O(n) worst (shift), O(1) amortized at end | O(n) (shift) | Contiguous memory, cache-friendly. |
| LinkedList | O(n) | O(n) | O(1) at known node | O(1) at known node | No random access; rarely the right choice over `ArrayDeque`. |
| HashMap | — | O(1) avg | O(1) avg | O(1) avg | O(n) worst case on hash collisions; unordered. |
| TreeMap | — | O(log n) | O(log n) | O(log n) | Sorted keys, red-black tree underneath. |
| Heap / PriorityQueue | O(1) peek | O(n) | O(log n) | O(log n) (poll) | Only guarantees top element ordering, not full sort. |

**Likely escalation pattern**: interviewer picks a structure for your coding answer, then asks "what if there are a million of these" or "what if two threads modify this concurrently" — that's items 1–3 above getting folded into whatever you built with item 4. Expect this cheat sheet to get used *together*, not as four separate topics.
