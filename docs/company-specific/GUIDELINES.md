# Company-Specific Docs Guidelines — `docs/company-specific/`

Rules for the two recurring artifact types under `docs/company-specific/<company>/`: the per-company `README.md` tracker, and diagrams shared across all companies' docs.

---

## Part 1: Company `README.md` & Progress Tracking

Each company folder (`docs/company-specific/<company>/`) has its own `README.md` as the entry point — the master tracker for that company's interview prep, with any deeper-dive files (problem writeups, Q&A files, round-specific breakdowns) linked out from it.

### Required structure

1. **Title**: `# <Company> — <Role/Interview Type> Prep` (e.g. `# Atlassian — Technical Screen Prep (Senior Backend Software Engineer)`).
2. **Role/context signal** (optional but recommended): a short bullet list of what the target role expects, pulled from the job posting — shapes what to prioritize, not generic advice.
3. **`## 🏁 Interview Outcome`** — only once the round(s) actually happened. State the result plainly (passed/rejected, which stage) and whether specific feedback was given. Don't speculate on a cause without evidence — if HR gave a generic rejection with no detail, say that plainly rather than guessing which round was the weak point.
4. **`## 📊 Progress Tracking`** (required, near the top):
   - A one-line overall counter: `` Track your overall progress: `X/Y items completed` ``
   - A table immediately below it, one row per major section further down the doc:
     ```markdown
     | Section | Progress |
     |---------|----------|
     | <Section Name> | `x/y` |
     ```
5. Horizontal rules (`---`) separating each major section.
6. Each major section is a checklist (`* [ ]` / `* [x]`) of topics/questions, each with a source citation (link) where applicable, and a note + link when the actual solution/write-up lives in a different file or a different repo rather than inline.

### Keeping counts in sync (critical — do this on every checkbox flip)

When a checkbox flips `[ ]` → `[x]` (or a new item is added), update **all three** of these together, every time:

1. The checkbox itself.
2. That section's cell in the `## 📊 Progress Tracking` table (`x/y` → incremented `x`, or `y` incremented too if an item was added).
3. The overall counter at the very top of the file.

These three numbers drifting out of sync is the single most common error in these trackers — always re-derive the overall counter as the sum of every section's `x` and `y`, don't just increment it by one and assume it still matches, since cross-checking the counts has caught real drift in this repo before.

### Linking to solutions in other repos

A company's prep often spans multiple repos (this DSA repo for algorithmic coding, `low-level-design` for LLD/class-design problems, `system-design` for HLD/architecture talking points). When a problem is solved elsewhere:
- Mark the checkbox `[x]` in this repo's tracker regardless of where the actual solution lives.
- Add a `**Solved**: [`<file>`](<full-github-url>) (`<repo-name>` repo).` note right after the item's description.
- If a question spans both an LLD and HLD half (e.g. Rate Limiter: algorithm/class-design *and* distributed-scaling), split it explicitly and link each half to wherever it was actually solved — don't force a two-sided question into a single link.

### Splitting into additional files

Once a single round/section gets detailed enough (a full question list with worked answers, a deep per-round breakdown), split it into its own file inside the company folder (e.g. `karat-p50-round.md`, `karat-rapid-fire-qna.md`) and link it from the main `README.md` rather than inlining everything. The main `README.md` stays the index + overall tracker; satellite files hold the depth.

---

## Part 2: Diagrams

Rules for creating diagrams used across company-specific interview-prep docs (Atlassian, Salesforce, etc.). Mirrors the convention already used in the sibling `low-level-design` repo (`concepts/diagrams/`) and the `system-design` repo (`practice/NN-session/diagrams/`), so diagrams stay diff-friendly and editable across all three repos rather than being baked into opaque images.

### Where diagrams live

All diagrams go in **`docs/company-specific/diagrams/`** — one shared folder, not per-company subfolders. Diagrams are referenced *from* a company-specific doc (e.g. `docs/company-specific/atlassian/karat-rapid-fire-qna.md`), but the diagram itself isn't owned by one company — keeping them together avoids duplicating the same shape (e.g. a generic cache-aside pattern) across folders.

**Naming**: kebab-case, descriptive of the thing being diagrammed, not the question/file that uses it — e.g. `friend-count-at-scale.mmd`, not `q1-diagram.mmd`. A diagram should be identifiable on its own if reused from a second doc later.

### Two files per diagram

Every diagram is **two files, same basename**:

| File | Role |
|---|---|
| `<name>.mmd` | The Mermaid source — plain text, diffable, this is what you edit. |
| `<name>.png` | The rendered image — this is what gets embedded in markdown. |

Never embed a `​```mermaid` code block directly in a doc. GitHub renders those fine, but it's inconsistent with the sibling repos' convention and makes the diagram harder to reuse/re-render with a fixed scale. Always render to a `.png` and embed that instead.

### Creating a diagram

1. Write the Mermaid source in `docs/company-specific/diagrams/<name>.mmd`. Favor `flowchart` for architecture/data-flow diagrams (read vs. write paths, request flow); use `sequenceDiagram` for multi-step interactions between specific actors over time.
2. Render it to PNG:
   ```bash
   cd docs/company-specific/diagrams
   npx -y @mermaid-js/mermaid-cli@11 -i <name>.mmd -o <name>.png -b white -s 5
   ```
   - `-b white` sets a white background (diagrams otherwise render transparent, which looks broken on GitHub's dark mode).
   - `-s 5` is the baseline scale — bump to `-s 6` or higher for a wide/dense diagram (many nodes, overlaid flows) so text survives zooming. A small, focused diagram (a handful of boxes) is fine at `-s 3`–`-s 4`. Check the result with `file <name>.png` — aim for a longest edge of roughly 4000px+.
3. Embed the PNG in the referencing doc:
   ```markdown
   ![Plain descriptive sentence of what the diagram shows.](../diagrams/<name>.png)
   ```
   **Alt text must be a plain sentence** — no parentheses or brackets (they break Markdown image rendering). Describe what the diagram shows, not just its title (e.g. "Flowchart separating the read path, which serves from a cache, from the write path, which persists asynchronously" — not "Friend count diagram").

### Updating a diagram

Edit the `.mmd`, then re-run the same render command to regenerate the `.png` in place. Never hand-edit or regenerate the PNG by other means — the `.mmd` is always the source of truth; the PNG is a disposable build artifact that happens to be committed for GitHub's benefit (GitHub doesn't render `.mmd` files inline).

### Referencing from a doc

- Link/embed with a path relative to the referencing file, e.g. from `docs/company-specific/atlassian/karat-rapid-fire-qna.md` the path is `../diagrams/<name>.png`.
- Don't create a separate markdown wrapper file just to hold the diagram (e.g. a `<name>.md` containing only an image and a caption) — embed the PNG directly in the doc that needs it. If multiple docs need the *same* diagram, embed it in each with its own surrounding context rather than funneling readers through an extra intermediate file.

### Content expectations

A diagram earns its place when it clarifies something a sentence can't — a shape (read path vs. write path, which store sits where), not a restatement of prose that's already clear. Keep each diagram focused on one thing (one data flow, one request lifecycle) rather than cramming an entire system's architecture into a single diagram — split into two diagrams if the shape is actually two separate concerns (e.g. "read path" and "write path" as drawn separately, or combined in one flowchart with clearly separated subgraphs, whichever reads cleaner for that specific diagram).
