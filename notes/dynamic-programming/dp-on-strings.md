# DP on Strings

> **[← Back to Dynamic Programming Overview](Notes.md)**

---

## When to Use

✅ **Compare, transform, or count over one or two strings, where the answer at `(i, j)` depends on a small number of preceding states**

### Keywords
- "longest common subsequence", "LCS"
- "edit distance", "minimum operations to convert"
- "palindromic subsequence / substring"
- "distinct subsequences", "shortest common supersequence"
- "wildcard matching", "regex", "pattern with `*` and `?`"

### Examples
- Longest subsequence shared by two strings (LCS)
- Min insert/delete/replace to convert `s1 → s2` (Edit Distance)
- Longest palindromic subsequence in a single string
- Count occurrences of `t` as a subsequence of `s`
- Match a string against a wildcard pattern

---

## Core Concept

Two flavors of state space dominate this family:

- **Two-string DP**: `dp[i][j]` = best result over prefixes `s1[0..i)` and `s2[0..j)`. Transitions branch on `s1[i-1] == s2[j-1]` (match → diagonal) vs mismatch (max/min of `dp[i-1][j]` and `dp[i][j-1]`).
- **Substring DP**: `dp[i][j]` = best result over a single substring `s[i..j]`. Fill in order of **increasing length**, expanding from short ranges to the full string.

**Complexity**: O(m × n) time, O(m × n) → O(min(m, n)) space (rolling 1D)

---

## Pattern: Two-String LCS-Style DP

**Use Case**: Any problem framed as "best alignment / common structure between two strings"

**Algorithm (LCS)**:
1. `dp[i][j]` = LCS length of `s1[0..i)` and `s2[0..j)`
2. Base case: `dp[0][*] = dp[*][0] = 0` (empty prefix)
3. If `s1[i-1] == s2[j-1]`: `dp[i][j] = dp[i-1][j-1] + 1` (extend the match)
4. Else: `dp[i][j] = max(dp[i-1][j], dp[i][j-1])` (drop one char from either string)
5. Answer: `dp[m][n]`

**Complexity**: O(m × n) time, O(m × n) space (1D rolling possible)

### Template (LCS)

```java
public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length(), n = text2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    return dp[m][n];
}
```

**Variations**:
- **Longest Palindromic Subsequence** = `LCS(s, reverse(s))` — clever reframe
- **Shortest Common Supersequence**: length = `m + n - LCS(s1, s2)`; reconstruct by walking the DP table
- **Distinct Subsequences** (count `t` as subsequence of `s`): `dp[i][j] = dp[i-1][j-1] + dp[i-1][j]` if match, else `dp[i-1][j]`
- **Min ASCII Delete Sum**: same shape as LCS but minimize sum of deleted ASCII codes (`dp[i][j] = dp[i-1][j-1]` on match, else `min(dp[i-1][j] + s1[i-1], dp[i][j-1] + s2[j-1])`)

---

## Pattern: Edit Distance ⭐ **IMPORTANT** ⭐

**Use Case**: Minimum insert / delete / replace operations to convert `s1` → `s2`

**Why Important**: The three-operation `1 + min(top, left, diagonal)` recurrence is the textbook DP question — frequently asked at FAANG, and the mapping of each neighbor to a specific edit operation is the insight interviewers probe.

**Algorithm**:
1. `dp[i][j]` = min edits to convert `s1[0..i)` → `s2[0..j)`
2. Base: `dp[i][0] = i` (delete all of `s1`), `dp[0][j] = j` (insert all of `s2`)
3. If `s1[i-1] == s2[j-1]`: `dp[i][j] = dp[i-1][j-1]` (no op needed)
4. Else: `dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])`
   - `dp[i-1][j-1] + 1` → **replace**
   - `dp[i-1][j] + 1` → **delete** from `s1`
   - `dp[i][j-1] + 1` → **insert** into `s1`
5. Answer: `dp[m][n]`

**Complexity**: O(m × n) time, O(m × n) → O(n) space

### Template

```java
public int minDistance(String word1, String word2) {
    int m = word1.length(), n = word2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) dp[i][0] = i;
    for (int j = 0; j <= n; j++) dp[0][j] = j;

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
    }
    return dp[m][n];
}
```

**Key Points**:
- **Map neighbors to operations**: diagonal = replace, top = delete, left = insert — knowing which is which lets you reconstruct the edit script
- **Base cases are non-zero**: `dp[i][0] = i` (i deletes), `dp[0][j] = j` (j inserts) — easy to forget and seed with `0`
- **Match → diagonal with no `+1`**: copying a matching char is free, only the three edit ops cost 1

---

## Pattern: Palindromic Substring DP

**Use Case**: Problems over a single string framed as "is `s[i..j]` a palindrome?" or "best palindrome inside `s`"

**Algorithm**:
1. `dp[i][j]` = `true` iff `s[i..j]` is a palindrome
2. Base: `dp[i][i] = true` (single char), `dp[i][i+1] = (s[i] == s[i+1])` (two chars)
3. Recurrence: `dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1]`
4. **Iterate by length** (`len = 2..n`), then `i` (and `j = i + len - 1`) — short ranges before long ones

**Complexity**: O(n²) time, O(n²) space

### Template (Count Palindromic Substrings)

```java
public int countSubstrings(String s) {
    int n = s.length(), count = 0;
    boolean[][] dp = new boolean[n][n];

    for (int len = 1; len <= n; len++) {
        for (int i = 0; i + len - 1 < n; i++) {
            int j = i + len - 1;
            if (s.charAt(i) == s.charAt(j) && (len <= 2 || dp[i + 1][j - 1])) {
                dp[i][j] = true;
                count++;
            }
        }
    }
    return count;
}
```

**Variations**:
- **Longest Palindromic Substring**: same `dp[i][j]` table; track best `(i, j)` while filling
- **Longest Palindromic Subsequence**: different recurrence — `dp[i][j] = dp[i+1][j-1] + 2` on match, else `max(dp[i+1][j], dp[i][j-1])`. Equivalent to `LCS(s, reverse(s))`.

---

## Pattern: Wildcard Matching ⭐ **IMPORTANT** ⭐

**Use Case**: Match string `s` against pattern `p` containing `?` (any single char) and `*` (any sequence, including empty)

**Why Important**: The `*` transition has two cases that combine — matching zero characters OR extending a previous match — and getting them right is the only hard part. Frequently asked.

**Algorithm**:
1. `dp[i][j]` = does `p[0..j)` match `s[0..i)`?
2. Base: `dp[0][0] = true`. `dp[0][j] = dp[0][j-1]` if `p[j-1] == '*'` (empty `*`s can prefix-match empty `s`)
3. If `p[j-1] == s[i-1]` or `p[j-1] == '?'`: `dp[i][j] = dp[i-1][j-1]`
4. If `p[j-1] == '*'`: `dp[i][j] = dp[i][j-1]` (`*` matches empty) **OR** `dp[i-1][j]` (`*` consumes another `s` char)
5. Else: `dp[i][j] = false`
6. Answer: `dp[m][n]`

**Complexity**: O(m × n) time, O(m × n) → O(n) space

### Template

```java
public boolean isMatch(String s, String p) {
    int m = s.length(), n = p.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;

    for (int j = 1; j <= n; j++) {
        if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 1];
    }

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            char pc = p.charAt(j - 1);
            if (pc == s.charAt(i - 1) || pc == '?') {
                dp[i][j] = dp[i - 1][j - 1];
            } else if (pc == '*') {
                dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
            }
        }
    }
    return dp[m][n];
}
```

**Key Points**:
- **`*` has two transitions**, OR-ed together: `dp[i][j-1]` (match zero chars) and `dp[i-1][j]` (extend by one char)
- **Seed first row of `*`s**: `dp[0][j]` is true only while every preceding pattern char is `*`
- **Distinguish from regex `.*`**: regex `*` qualifies the **previous char**; wildcard `*` is standalone — different recurrence

---

## Common Mistakes

- ❌ **Off-by-one with `s.charAt(i - 1)`** — DP uses 1-indexed prefixes (`s[0..i)`), so the i-th character is at index `i - 1`
- ❌ **Forgetting Edit Distance base cases** — `dp[i][0] = i` and `dp[0][j] = j`, not `0`
- ❌ **Filling palindrome DP row-by-row** — must iterate by **length** (short → long) so `dp[i+1][j-1]` is ready
- ❌ **Confusing palindromic subsequence vs substring** — subsequence = LCS-style (drops chars freely), substring = contiguous range DP

---

## Problems

- [x] [Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/) - Medium
- [x] [Longest Palindromic Subsequence](https://leetcode.com/problems/longest-palindromic-subsequence/) - Medium
- [x] [Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/) - Medium
- [x] [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/) - Medium
- [x] [Edit Distance](https://leetcode.com/problems/edit-distance/) - Medium ⭐ **IMPORTANT** ⭐
- [ ] [Minimum ASCII Delete Sum for Two Strings](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/) - Medium
- [ ] [Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences/) - Hard
- [ ] [Shortest Common Supersequence](https://leetcode.com/problems/shortest-common-supersequence/) - Hard
- [ ] [Wildcard Matching](https://leetcode.com/problems/wildcard-matching/) - Hard ⭐ **IMPORTANT** ⭐

---

> **[← Back to Dynamic Programming Overview](Notes.md)**
