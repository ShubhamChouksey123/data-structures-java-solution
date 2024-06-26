package com.shubham.app.array;

import java.util.*;

public class Solution {

    private void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int tmp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = tmp;
    }

    public void print(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isTopCornerZero(int[][] matrix, int m, int n) {

        if (matrix[0][0] == 0)
            return true;

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0)
                return true;
        }

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0)
                return true;
        }

        return false;
    }

    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] isRowZero = new boolean[m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    isRowZero[i] = true;
                }
            }
        }

        System.out.println("After Setting top Row as zero");
        print(matrix);

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                setEntireColumnZero(matrix, j, m);
            }
        }

        System.out.println("Setting entire column zero");
        print(matrix);

        for (int i = 0; i < m; i++) {
            if (isRowZero[i]) {
                setEntireRowZero(matrix, i, n);
            }
        }

        System.out.println("Setting entire row zero");
        print(matrix);
    }

    private void setEntireColumnZero(int[][] matrix, int column, int m) {

        for (int i = 0; i < m; i++) {
            matrix[i][column] = 0;
        }
    }

    private void setEntireRowZero(int[][] matrix, int row, int n) {
        for (int j = 0; j < n; j++) {
            matrix[row][j] = 0;
        }
    }

    private boolean isValidClosing(char openingBracket, char closingBracket) {

        if (openingBracket == '(' && closingBracket == ')')
            return true;
        if (openingBracket == '{' && closingBracket == '}')
            return true;
        if (openingBracket == '[' && closingBracket == ']')
            return true;

        return false;
    }

    public boolean isValid(String s) {

        int n = s.length();

        if (n % 2 != 0)
            return false;

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Objects.equals(c, '(') || Objects.equals(c, '{') || Objects.equals(c, '[')) {
                st.push(c);
                continue;
            }

            if (st.isEmpty()) {
                return false;
            }
            char openingBracket = st.pop();

            if (!isValidClosing(openingBracket, c)) {
                return false;
            }
        }

        if (st.isEmpty())
            return true;

        return false;
    }

    public int longestConsecutive(int[] nums) {

        Set<Integer> st = new HashSet<>();

        for (int i : nums) {
            st.add(i);
        }

        int n = nums.length;
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (st.contains(num - 1)) {
                continue;
            }

            int length = 1;
            while (st.contains(num + 1)) {
                num = num + 1;
                length++;
            }
            maxLength = Math.max(length, maxLength);
        }

        System.out.println("maxLength : " + maxLength);
        return maxLength;
    }

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> valueIndexMap = new HashMap<>();

        int[] ans = new int[2];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (valueIndexMap.containsKey(target - num)) {
                ans[0] = i;
                ans[1] = valueIndexMap.get(target - num);
                break;
            }

            valueIndexMap.put(num, i);
        }
        return ans;
    }

    public String reversePrefix(String word, char ch) {

        int n = word.length();

        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (c == ch) {
                StringBuilder stringBuilder = new StringBuilder();
                // System.out.println("substring at i " + i + " is " + word.substring(0,
                // i+1));
                stringBuilder.append(word.substring(0, i + 1));
                stringBuilder.reverse();
                if (i + 1 < n) {
                    stringBuilder.append(word.substring(i + 1, n));
                }
                return stringBuilder.toString();
            }
        }
        return word;
    }

    public int findMaxK(int[] nums) {

        int n = nums.length;
        if (n < 2) {
            return -1;
        }

        Integer ans = -1;
        Map<Integer, Boolean> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Boolean isPresent = mp.get(-1 * nums[i]);
            if (Objects.equals(isPresent, Boolean.TRUE)) {
                ans = Math.max(ans, Math.abs(nums[i]));
            }
            mp.put(nums[i], true);
        }

        return ans;
    }

    public int compareVersion(String version1, String version2) {

        String[] str1 = version1.split("\\.");
        System.out.println("str1 : " + Arrays.toString(str1));

        String[] str2 = version2.split("\\.");
        System.out.println("str2 : " + Arrays.toString(str2));

        for (int i = 0; i < Math.max(str1.length, str2.length); i++) {

            Integer num1 = 0;
            Integer num2 = 0;
            if (i < str1.length) {
                num1 = Integer.valueOf(str1[i]);
            }
            if (i < str2.length) {
                num2 = Integer.valueOf(str2[i]);
            }

            System.out.println("num1 : " + num1 + " and num2 : " + num2);

            if (num1.compareTo(num2) < 0) {
                return -1;
            } else if (num1.compareTo(num2) > 0) {
                return 1;
            }
        }

        return 0;
    }

    public int numRescueBoats(int[] people, int limit) {

        int n = people.length;

        Arrays.sort(people);

        boolean[] isCounted = new boolean[n];

        int totalBoats = 0;
        int start = n - 2;
        for (int i = n - 1; i >= 0; i--) {
            if (isCounted[i]) {
                continue;
            }
            if (people[i] == limit) {
                totalBoats++;
                continue;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (people[i] + people[j] <= limit && !isCounted[j]) {
                    isCounted[j] = true;
                    break;
                }
            }
            totalBoats++;
        }
        return totalBoats;
    }

    public int candy(int[] ratings) {

        int n = ratings.length;
        int[] candy = new int[n];
        Arrays.fill(candy, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i - 1] < ratings[i] && candy[i - 1] >= candy[i]) {
                candy[i] = candy[i - 1] + 1;
            }
        }

        System.out.println("candy : " + Arrays.toString(candy));

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]) {
                candy[i] = candy[i + 1] + 1;
            }
        }
        System.out.println("candy : " + Arrays.toString(candy));

        int totalCandies = 0;
        for (int num : candy) {
            totalCandies += num;
        }
        return totalCandies;
    }

    public int maxSubArray(int[] nums) {

        int n = nums.length;
        int sum = 0;
        int maxSubArraySum = Integer.MIN_VALUE;
        for (int end = 0; end < n; end++) {
            if (sum > 0) {
                sum = sum + nums[end];
            } else {
                sum = nums[end];
            }
            maxSubArraySum = Math.max(maxSubArraySum, sum);
        }

        return maxSubArraySum;
    }

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int curMax = nums[0];
        int curMin = nums[0];
        int maxSubArraySum = nums[0];
        int minSubArraySum = nums[0];
        int totalSum = nums[0];
        for (int end = 1; end < n; end++) {
            if (curMax > 0) {
                curMax = curMax + nums[end];
            } else {
                curMax = nums[end];
            }

            if (curMin < 0) {
                curMin = curMin + nums[end];
            } else {
                curMin = nums[end];
            }

            totalSum += nums[end];
            maxSubArraySum = Math.max(maxSubArraySum, curMax);
            minSubArraySum = Math.min(minSubArraySum, curMin);
        }

        if (maxSubArraySum < 0) {
            return maxSubArraySum;
        }

        return Math.max(maxSubArraySum, totalSum - minSubArraySum);
    }

    public int findPermutationDifference(String s, String t) {

        int n = s.length();
        int[] indexS = new int[26];
        int[] indexT = new int[26];

        for (int i = 0; i < n; i++) {
            indexS[s.charAt(i) - 'a'] = i;
            indexT[t.charAt(i) - 'a'] = i;
        }

        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += Math.abs(indexS[i] - indexT[i]);
        }

        return sum;
    }

    public int specialArray(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        if (nums[n - 1] < 0) {
            return 0;
        }
        if (nums[0] >= n) {
            return n;
        }

        for (int i = 1; i < n - 1; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == n - i) {
                    return nums[i];
                }
                int numberOfElementsOnRight = n - i;
                if (numberOfElementsOnRight < nums[i] && nums[i - 1] < numberOfElementsOnRight) {
                    return numberOfElementsOnRight;
                }
            }
        }

        return -1;
    }

    public int equalSubstring(String s, String t, int maxCost) {

        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }

        int start = 0, sum = 0, maxLength = 0;

        for (int end = 0; end < n; end++) {
            sum += arr[end];
            while (sum > maxCost && start < end) {
                sum = sum - arr[start];
                start++;
            }
            if (sum <= maxCost) {
                maxLength = Math.max(maxLength, end - start + 1);
            }
        }

        return maxLength;
    }

    public int appendCharacters(String s, String t) {

        int m = s.length();
        int n = t.length();

        int indexOnT = 0;
        for (int i = 0; i < m; i++) {
            if (indexOnT == n) {
                break;
            }
            if (s.charAt(i) == t.charAt(indexOnT)) {
                indexOnT++;
            }
        }

        return n - indexOnT;
    }

    public int scoreOfString(String s) {

        int n = s.length();
        int sum = 0;
        for (int i = 1; i < n; i++) {
            sum += Math.abs(s.charAt(i) - s.charAt(i - 1));
        }
        return sum;
    }

    public int longestPalindrome(String s) {

        int[] small = new int[26];
        int[] big = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                small[c - 'a']++;
            } else {
                big[c - 'A']++;
            }
        }

        System.out.println("small : " + Arrays.toString(small));
        System.out.println("small : " + Arrays.toString(big));

        int count = 0;
        boolean isOdd = false;
        for (int i = 0; i < 26; i++) {
            count += 2 * (small[i] / 2);
            count += 2 * (big[i] / 2);

            if ((small[i] & 1) == 1) {
                isOdd = true;
            }
            if ((big[i] & 1) == 1) {
                isOdd = true;
            }
        }

        if (isOdd) {
            return count + 1;
        }
        return count;
    }


    private int[] createArrayOfCount(String word) {
        int[] chars = new int[26];
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            chars[c - 'a']++;
        }
        return chars;
    }


    private void modifyOriginalCount(int[] originalCount, int[] currentCount) {
        for (int i = 0; i < 26; i++) {
            originalCount[i] = Math.min(originalCount[i], currentCount[i]);
        }
    }

    public List<String> commonChars(String[] words) {

        int n = words.length;
        int[] originalCount = createArrayOfCount(words[0]);

        for (int i = 1; i < n; i++) {
            int[] currentCount = createArrayOfCount(words[i]);
            modifyOriginalCount(originalCount, currentCount);
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            while (originalCount[i] > 0) {
                char c = (char) ('a' + i);
                ans.add(String.valueOf(c));
                originalCount[i]--;
            }
        }

        return ans;
    }

    private String getShortestPrefix(String word, List<String> dictionary) {

        for (int i = 0; i < dictionary.size(); i++) {
            String prefix = dictionary.get(i);
            if (prefix.length() > word.length()) {
                continue;
            }
            String expectedCommonPart = word.substring(0, prefix.length());
            if (Objects.equals(expectedCommonPart, prefix)) {
                word = prefix;
            }
        }
        return word;
    }

    public String replaceWords(List<String> dictionary, String sentence) {


        List<String> words = Arrays.asList(sentence.split("\\s+"));

        String ans = "";
        if (words.isEmpty()) {
            return ans;
        }

        ans = getShortestPrefix(words.get(0), dictionary);

        for (int i = 1; i < words.size(); i++) {
            String word = words.get(i);
            ans = ans + " " + getShortestPrefix(word, dictionary);
        }
        return ans;
    }


    public boolean checkSubarraySum(int[] nums, int k) {

        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();

        int sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] % k;
            sum += nums[i];
            sum = sum % k;

            if (sum == 0 && i != 0) {
                return true;
            }

            Integer indexOfPrevious = mp.get(sum);
            if (indexOfPrevious != null) {
                if (i - indexOfPrevious > 1) {
                    return true;
                }
            } else {
                mp.put(sum, i);
            }
        }

        return false;
    }


    public int subarraysDivByKBrut(int[] nums, int k) {

        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum = sum + nums[j];
                if (sum % k == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    public int subarraysDivByK(int[] nums, int k) {

        int n = nums.length;
        int count = 0;
        Map<Integer, Integer> remCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int rem = nums[i] % k;
            nums[i] = rem;
        }
        System.out.println("nums : " + Arrays.toString(nums));
        for (int i = 0; i < n; i++) {
            int rem = nums[i] % k;
            System.out.println("rem " + rem + " for number : " + nums[i]);
            Integer alreadyCount = 0;
            if (rem >= 0) {
                alreadyCount = remCount.get((k - rem) % k);
            } else {
                alreadyCount = remCount.get(-rem);
            }
            if (alreadyCount == null) {
                alreadyCount = 0;
            }
            System.out.println("rem " + rem + " for number : " + nums[i] + " with alreadyCount : " + alreadyCount);


            count += alreadyCount;
            if (rem == 0) {
                count++;
            }

            remCount.put(rem, ++alreadyCount);

        }

        return count;
    }

    public int heightChecker(int[] heights) {

        int n = heights.length;
        List<Integer> sortedHeights = new ArrayList<>();
        Arrays.stream(heights).boxed().forEach(a -> sortedHeights.add(a));

        Collections.sort(sortedHeights);

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (heights[i] != sortedHeights.get(i)) {
                count++;
            }
        }
        return count;
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        int n = arr1.length;
        int[] ans = new int[n];
        int index = 0;

        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : arr1) {
            Integer freq = counts.get(num);
            if (freq == null) {
                freq = 0;
            }
            counts.put(num, freq + 1);
        }

        for (int i = 0; i < arr2.length; i++) {
            int num = arr2[i];
            Integer freq = counts.get(num);
            if (freq == null) {
                freq = 0;
            }
            while (freq > 0) {
                ans[index] = num;
                index++;
                freq--;
            }
            counts.put(num, 0);
        }

        List<Integer> nonExistingElements = new ArrayList<>();
        counts.forEach((k, v) -> {

            if (v != 0) {
                while (v > 0) {
                    nonExistingElements.add(k);
                    v--;
                }
            }
        });

        Collections.sort(nonExistingElements);

        int currentIndex = 0;

        for (int i = n - nonExistingElements.size(); i < n; i++, currentIndex++) {
            ans[i] = nonExistingElements.get(currentIndex);

        }

        System.out.println("ans : " + Arrays.toString(ans));

        return ans;
    }

    public void sortColors(int[] nums) {
        int n = nums.length;

        int zerCount = 0, oneCount = 0, twoCount = 0;
        for (int num : nums) {
            switch (num) {
                case 1 -> oneCount++;
                case 2 -> twoCount++;
                default -> zerCount++;
            }
        }

        int index = 0;
        while (zerCount-- > 0) {
            nums[index] = 0;
            index++;
        }

        while (oneCount-- > 0) {
            nums[index] = 0;
            index++;
        }

        while (twoCount-- > 0) {
            nums[index] = 0;
            index++;
        }
    }

    public int minIncrementForUnique(int[] nums) {

        int n = nums.length;

        Arrays.sort(nums);

        int next = nums[0];
        int increment = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < next) {
                increment += (next - nums[i]);
            }
            next = Math.max(next + 1, nums[i] + 1);
        }
        return increment;
    }
}
