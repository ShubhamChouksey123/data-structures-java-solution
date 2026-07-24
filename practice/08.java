/**
 *
 * A heated election has n parties. We are given two arrays of length n, candidates and votes, such that candidates[i] is the name of the candidate of the i-th party and votes[i] is the number of votes of the i-th party.
 *
 * If a party has more than 50% of the votes, they win automatically. Otherwise, the following happens until a party wins: the two parties with the fewest votes create a coalition (if there is a tie, they all join the coalition): a new party with the votes of both and where the candidate is the candidate of the party that had more votes (breaking ties by the smallest alphabetical name of the candidate, which are unique). Return the name of the candidate that wins the election.
 *
 * Example: candidates = ["Ale", "Bloop", "Chip", "Dart", "Zing"] votes = [10, 20, 30, 15, 25] output: Dart Initially, no candidate has more than 50% of the votes (51 or more). Then, the parties of Ale and Dart form a coalition party with 25 votes and Dart as the representative. After that, Bloop's party has the fewest votes at 20, followed by Dart's and Zing's parties at 25. Therefore, the three form a new coalition party led by Dart (because Dart goes before Zing alphabetically). This new party has 70 votes, so it wins the election.
 *
 * Constraints:
 *
 * 1 <= n <= 1000 where n is the number of parties
 * 1 <= votes[i] <= 10^6 for each party
 * All candidate names are unique
 * Candidate names consist of English letters only and have length at most 20
 */