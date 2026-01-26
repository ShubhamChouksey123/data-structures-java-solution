# Overlapping Intervals

## Core Concept

An **interval** is a range: `[start, end]`

**Key Insight**: Sorting by start time converts O(n²) comparisons to O(n) linear scan.

**Overlap Check**: Intervals `[a, b]` and `[c, d]` overlap if `a < d && c < b`

---

## Pattern 1: Merge Overlapping Intervals

**Algorithm**:
1. Sort by start time
2. For each interval: if overlaps with last → merge (update end), else add to result

**Complexity**: O(n log n) time, O(n) space

**Merge Logic**: `end = max(currentEnd, newEnd)`

**Example**: `[[1,3], [2,6], [8,10]]` → Sort → Merge [1,3] and [2,6] → `[[1,6], [8,10]]`

---

## Pattern 2: Insert Interval

**Algorithm**:
1. Add all intervals before new interval
2. Merge all overlapping intervals with new interval
3. Add all intervals after new interval

**Complexity**: O(n) time, O(n) space

**Key**: Already sorted input, no sorting needed!

---

## Pattern 3: Non-Overlapping Intervals (Remove Minimum) ⭐ **IMPORTANT** ⭐

**Algorithm**:
1. Sort by **end time**
2. Track last kept interval's end
3. Keep intervals where `start >= lastEnd`, remove others

**Complexity**: O(n log n) time, O(1) space

**Why end time?** Greedy - keep intervals with earliest end to maximize space for future intervals.

---

## Pattern 4: Minimum Arrows (Burst Balloons) ⭐ **IMPORTANT** ⭐

**Algorithm**:
1. Sort by **end time**
2. Place arrow at first interval's end
3. For each interval: if `start > lastArrow` → need new arrow

**Complexity**: O(n log n) time, O(1) space

**Key**: Arrow at end hits maximum overlapping intervals.

---

## Pattern 5: Calendar (Double/Triple Booking) ⭐ **IMPORTANT** ⭐

**Double Booking**:
- Keep list of booked intervals
- Check new event against all existing
- Reject if overlap found

**Triple Booking**:
- Keep two lists: bookings and overlaps
- Check new event against overlaps first (would create triple)
- Add overlapping parts to overlaps list

**Complexity**: O(n) per check, O(n) space

---

## Pattern Summary

| Pattern | Sort By | Complexity | Use Case |
|---------|---------|------------|----------|
| **Merge** | Start time | O(n log n) | Combine overlaps |
| **Insert** | Already sorted | O(n) | Add to sorted list |
| **Remove** | End time | O(n log n) | Maximize non-overlapping |
| **Arrows** | End time | O(n log n) | Minimize resources |
| **Calendar** | No sort | O(n) check | Validate bookings |

---

## Overlap Detection

**Two intervals overlap**: `a.start < b.end && b.start < a.end`

**Overlap range**: `[max(a.start, b.start), min(a.end, b.end)]`

**Visual**:
```
[a -------- a.end]
     [b -------- b.end]  → Overlap exists

[a --- a.end]   [b --- b.end]  → No overlap
```

---

## Sorting Strategies ⭐ **IMPORTANT** ⭐

### When to Sort by Start Time
- **Merge intervals**: Process left to right
- **Insert interval**: Already sorted, just scan
- **Most common approach**

### When to Sort by End Time
- **Non-overlapping intervals**: Greedy selection
- **Minimum arrows**: Place at end to cover maximum
- **Maximize interval count**

**Code**:
```
// By start
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

// By end
Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
```

---

## Critical Points

**Avoid Overflow**:
- ✅ Use `Integer.compare(a[0], b[0])`
- ❌ Don't use `a[0] - b[0]` (can overflow)

**Touching Intervals**: `[1,2]` and `[2,3]`
- Check if touching counts as overlap (depends on problem)
- Usually: `a <= d` includes touching

**Merging**: Always use `max(currentEnd, newEnd)` for end time
- Example: `[1,5]` and `[2,3]` → `[1,5]` not `[1,3]`

**Edge Cases**:
- Empty/single interval
- All intervals overlap → single merged interval
- Nested intervals: `[1,5]` contains `[2,3]`

---

## Quick Reference

**Sort intervals**:
```
// By start time (most common)
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

// By end time (greedy)
Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
```

**Check overlap**:
```
// Two intervals [a,b] and [c,d]
boolean overlaps = (a < d && c < b);
```

**Merge intervals**:
```
int newStart = min(a, c);
int newEnd = max(b, d);
```

---

## Problems to Practice

- [x] [Merge Intervals](https://leetcode.com/problems/merge-intervals/) - Medium
- [x] [Insert Interval](https://leetcode.com/problems/insert-interval/) - Medium
- [x] [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) - Medium ⭐ **IMPORTANT** ⭐
- [x] [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/) - Medium ⭐ **IMPORTANT** ⭐
- [ ] [My Calendar II](https://leetcode.com/problems/my-calendar-ii/) - Medium

---

**Remember**: Sort by start for merging, sort by end for greedy selection. Always check if touching intervals count as overlaps!
