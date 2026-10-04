 # Delivery Cart Routes

> Atlassian P50 Karat-round coding problem. See [`karat-p50-round.md`](karat-p50-round.md) for round context — this file only explains the problem itself, no solution.

## Problem Statement

A fleet of autonomous delivery carts moves between locations in a facility. Movement between locations is described as a set of **directed routes** — a route from location `A` to location `B` means a cart can travel from `A` to `B`, but not necessarily the reverse.

Given this set of directed routes, you need to:

1. **Identify all valid start locations** — a location is a valid start if no route leads *into* it (it has **no incoming edges**). These are the locations a cart's journey could actually begin from, since nothing else feeds into them.
2. **For each valid start location, find all possible ending locations reachable from it** — an ending location is a **leaf node**: a location with no outgoing routes (a cart that reaches it has nowhere further to go).

The output should map each valid start location to the set of ending locations reachable from it by following the directed routes forward.

## Input / Output Shape

- **Input**: a list of directed routes, each route being an ordered pair `(from, to)` representing a one-way path between two locations.
- **Output**: for every valid start location (in-degree 0), the set of all leaf locations reachable from it.

## Worked Example

Routes:
```
A -> B
A -> C
B -> D
C -> D
E -> F
F -> G
```

- **Valid start locations** (no incoming edges): `A`, `E`
  - `B`, `C`, `D` all have at least one incoming route (from `A`, `A`, and `B`/`C` respectively).
  - `F`, `G` have incoming routes (from `E` and `F` respectively).
- **Leaf locations reachable from each start**:
  - From `A`: `D` (both `A -> B -> D` and `A -> C -> D` end at `D`)
  - From `E`: `G` (via `E -> F -> G`)

So the result would conceptually be: `A -> {D}`, `E -> {G}`.

## Things to Clarify Before Coding

These are the kinds of questions worth asking out loud before writing anything, since the problem statement as reported doesn't pin them all down:

- **Cycles**: can the route graph contain a cycle? If so, a naive DFS/BFS could loop forever — need cycle detection or a visited-set guard.
- **Disconnected locations**: can a location appear with no routes at all (isolated node)? Is an isolated node both a valid start *and* a leaf simultaneously (reachable "ending location" from itself, trivially)?
- **Multiple starts reaching the same leaf**: is that expected/allowed, or does each leaf belong to exactly one start?
- **Duplicate routes**: can the same `(from, to)` pair appear more than once in the input?
- **Self-loops**: can a route go from a location back to itself (`A -> A`)? Does that make `A` ineligible as a leaf (it has an outgoing edge) while still looking like it "ends" there?
- **Output format**: should the result preserve route/traversal order, or is an unordered set per start location sufficient?
- **Scale**: how many locations/routes should the solution handle efficiently — does it need to be more than a simple adjacency-list traversal (e.g. very large graphs needing an iterative rather than recursive DFS to avoid stack overflow)?
