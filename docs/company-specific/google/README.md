# Google — Senior Software Engineer, Full Stack Prep (Hyderabad)

- Role: Senior Software Engineer, Full Stack (L5-equivalent), Hyderabad.
- Google's standard SWE loop (DSA + System Design + Behavioral) applies regardless of specialization — "Full Stack" mainly affects **team placement** and may surface frontend/web topics in the Role-Related Knowledge or System Design round. No Google-specific source confirms a formally distinct full-stack interview track — treat the Full-Stack section below as commonly-reported prep guidance, not confirmed Google policy.
- Round counts below are ranges aggregated from third-party sources (not Google's own careers site) — treat as approximate, not fixed. Sources are cited inline per section.

---

## 📊 Progress Tracking

Track your overall progress: `1/31 items completed`

| Section | Progress |
|---------|----------|
| Online Assessments | `1/2` |
| Recruiter & Phone Screens | `0/2` |
| Onsite Coding Rounds (DSA) | `0/7` |
| System Design Round | `0/8` |
| Googleyness & Leadership Round | `0/8` |
| Full-Stack Role-Related Knowledge | `0/4` |

---

### Online Assessments

* [ ] **Coding Online Assessment** — platform/details in Candidate Portal. Due **17-10-2026, 7:17 AM ET / 4:17 AM PT**.
* [x] **Google Hiring Assessment (GHA)** — ~30 min, work-style/behavioral assessment (not coding). **Passed on 15-10-2026**, valid until 14-10-2028.
  - Delivered via a Pallidium-hosted workflow, in three sections (ipsative/forced-choice personality format, not scored for "right answers"):
    1. **Most/Least section** (~15–20 items): each item gives 3 statements — pick the one that most describes you and the one that least describes you.
    2. **Likert section** (~10–15 items): a single statement, 5-point scale (Strongly Disagree → Strongly Agree), pick one.
    3. **Situational Judgment section** (~10–15 items): a workplace situation + 5 response options — pick the one you'd most likely do and the one you'd least likely do.

> ⚠️ Two separate deadlines on the same day — confirm exact times/timezone for each in the Candidate Portal (`https://www.google.com/about/careers/applications/dashboard`) before assuming either one.

---

### Recruiter & Phone Screens

After the OAs clear, the typical path is a recruiter screen followed by technical phone screens (live coding), then the onsite loop. — [hellointerview.com/guides/google/l5](https://www.hellointerview.com/learn/system-design/interview-guides/google), [tryexponent.com/guides/google-swe-interview](https://www.tryexponent.com/guides/google-swe-interview)

* [ ] Recruiter screen — role/level/timeline alignment call.
* [ ] 2× Technical phone screens — live coding, standard DSA format (shared doc or online IDE).

---

### Onsite Coding Rounds (DSA)

L5 onsite loops report **2–4 coding/algorithm rounds** (phone-screen-style early rounds plus senior-level onsite rounds mixing OOP design with algorithmic efficiency). Full problem lists: [frequently-asked-problems.md](frequently-asked-problems.md) (Arrays/Strings/Trees + reported scenarios) and [dp-graph-problems.md](dp-graph-problems.md) (DP + Graph, frequency-ranked from Google's full company-wise list). — [leetcode.com/discuss/post/5196335](https://leetcode.com/discuss/post/5196335), [leetcode.com/discuss/post/7417074](https://leetcode.com/discuss/post/7417074), [liquidslr/leetcode-company-wise-problems → Google](https://github.com/liquidslr/leetcode-company-wise-problems/blob/main/Google/5.%20All.csv)

* [ ] **Arrays & Strings** topic group — see [frequently-asked-problems.md](frequently-asked-problems.md).
* [ ] **Trees** topic group — see [frequently-asked-problems.md](frequently-asked-problems.md).
* [ ] **Graphs** topic group (top 55 unsolved, frequency-ranked) — see [dp-graph-problems.md](dp-graph-problems.md).
* [ ] **Dynamic Programming** topic group (top 55 unsolved, frequency-ranked) — see [dp-graph-problems.md](dp-graph-problems.md).
* [ ] **Phone-screen-style scenario questions** (intervals/scheduling, grid traversal, string parsing, meeting concurrency) — see [frequently-asked-problems.md](frequently-asked-problems.md).
* [ ] **Senior-level scenario questions** (DAG reachability, task scheduler cooldown, unknown-grid robot exploration, digit transformation, largest axis-aligned rectangle) — see [frequently-asked-problems.md](frequently-asked-problems.md).
* [ ] General mock run-through (mixed topics, timed) once each group above is drilled.

---

### System Design Round

Expected at senior level (less emphasized at L3/L4), typically 1–2 rounds. Commonly-cited Google-relevant prompts:

* [ ] **Design a URL Shortener** — hashing/Base62, read:write ratio ~100-1000:1, caching/CDN, consistency vs. availability tradeoffs. — [hellointerview.com](https://www.hellointerview.com/learn/system-design/interview-guides/google)
* [ ] **Design a Notification System** — producer/consumer via message queue (Kafka/RabbitMQ), retries/dead-letter queues, multi-channel delivery. — [firecode.io](https://firecode.io/)
* [ ] **Design Google Docs collaborative editing** — Operational Transformation, WebSockets for real-time sync, conflict resolution, sharding/versioning. — [medium.com/lets-code-future](https://medium.com/lets-code-future)
* [ ] **Design an AI Agent Token Rate Limiter** — token-bucket model keyed on token consumption rather than request count, multi-tenant burst handling. (No source link provided — user-supplied summary, unverified.)
* [ ] **Design a Distributed Blocking/Denylist System** — real-time, low-latency global check/block of fraudulent IPs or user IDs. (No source link provided — unverified.)
* [ ] **Design a Distributed Cache System** — multi-region caching, invalidation, cache-warmth strategy, eviction policy. (No source link provided — unverified.)
* [ ] **Design a Global VM Monitoring System** — telemetry collecting metrics/logs/health from millions of VMs with minimal reporting latency. (No source link provided — unverified.)
* [ ] **Design a Trending Hashtags / Metrics Aggregator** — real-time streaming pipeline for top terms over sliding time windows. (No source link provided — unverified.)

---

### Googleyness & Leadership Round

Evaluates thriving in ambiguity, valuing feedback, challenging status quo appropriately, "doing the right thing" (ethics/user-first), and leadership — initiative without formal authority, mobilizing others, decision-making in teams. STAR method recommended for answers. — [igotanoffer.com](https://igotanoffer.com/blogs/tech/googleyness-leadership-interview-questions), [finalroundai.com](https://www.finalroundai.com/)

* [ ] Prepare a story: a time you dealt with **ambiguity**.
* [ ] Prepare a story: a time you received **hard feedback**.
* [ ] Prepare a story: a time you **challenged the status quo**.
* [ ] Prepare a story: an **ethical decision** you faced.
* [ ] Prepare a story: a time you **led a team/project**.
* [ ] Prepare a story: **influencing without authority**.
* [ ] Prepare a story: **prioritizing user needs** over internal convenience.
* [ ] Prepare a story: **mentoring a struggling or behind-schedule teammate**. (No source link provided — unverified.)

---

### Full-Stack Role-Related Knowledge

Not confirmed as a guaranteed Google round — commonly-reported frontend-prep guidance to have ready in case System Design or RRK leans web-side, given the "Full Stack" title.

* [ ] **HTML/CSS fundamentals** — semantic elements, forms, a11y/ARIA, box model, flexbox/grid, specificity, responsive design.
* [ ] **JavaScript fundamentals** — closures, event loop, async/promises, `this` binding, DOM/event propagation.
* [ ] **React fundamentals** — hooks, reconciliation/virtual DOM, state management, performance (`memo`/`useCallback`).
* [ ] **API & backend basics** — REST API design, auth (JWT/OAuth), basic DB and deployment concepts.

---
