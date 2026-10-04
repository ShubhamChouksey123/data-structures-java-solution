# Robot Factory Parts

> Atlassian P50 Karat-round coding problem. See [`karat-p50-round.md`](karat-p50-round.md) for round context — this file only explains the problem itself, no solution.

## Problem Statement

A robot factory has a limited pool of **available parts**. Each type of robot that can be built has a **recipe** — a list of parts it requires. Given the pool of available parts and the recipes for a set of candidate robots, determine **which robots can be fully built** from the available parts.

A robot can be built only if, for every part type its recipe requires, the available pool has **at least as many** of that part as the recipe needs.

## Input / Output Shape

- **Input**:
  - A list (or count-map) of **available parts** — e.g. a flat list where a part type may repeat, representing how many of each part are on hand.
  - A list of **robots**, each with its own list of **required parts** (which may also contain repeats, if a robot needs more than one of a given part).
- **Output**: the list of robot names/identifiers that can be fully built with the available parts.

## Worked Example

Available parts:
```
["wheel", "wheel", "arm", "sensor", "sensor", "sensor"]
```
(i.e. 2 wheels, 1 arm, 3 sensors on hand)

Robots and their requirements:
```
RobotA: ["wheel", "wheel", "sensor"]      -> needs 2 wheels, 1 sensor
RobotB: ["wheel", "arm", "sensor", "sensor"] -> needs 1 wheel, 1 arm, 2 sensors
RobotC: ["arm", "arm"]                    -> needs 2 arms
```

- **RobotA**: needs 2 wheels (have 2 ✓), 1 sensor (have 3 ✓) → **can be built**
- **RobotB**: needs 1 wheel (have 2 ✓), 1 arm (have 1 ✓), 2 sensors (have 3 ✓) → **can be built**
- **RobotC**: needs 2 arms (have only 1 ✗) → **cannot be built**

Result: `[RobotA, RobotB]`

## Things to Clarify Before Coding

Worth raising out loud before writing anything, since the reported problem statement leaves these open:

- **Shared pool across robots, or independent checks?** If RobotA and RobotB are both "buildable" individually, does building both *simultaneously* need to respect the shared pool (i.e. is this a one-robot-at-a-time check, or does availability get consumed across robots)? The example above treats each robot's check independently against the *original* pool — confirm that's the intended semantics.
- **Case sensitivity / exact string matching** for part names — are `"Wheel"` and `"wheel"` the same part?
- **Unknown part types**: if a robot requires a part that doesn't exist in the available pool at all, does it simply fail that robot (count of 0), or is it treated as an error/invalid input?
- **Zero or negative quantities**: can the input contain a robot with an empty requirement list (trivially buildable)? Can available-parts quantities be zero or is that just omission?
- **Output format**: list of robot names, boolean per robot, or count of how many can be built — which does the interviewer want?
- **Scale**: how many part types and robots should the solution handle efficiently — does this stay a simple hash-map frequency comparison, or is there a follow-up about optimizing repeated lookups across many robots?
