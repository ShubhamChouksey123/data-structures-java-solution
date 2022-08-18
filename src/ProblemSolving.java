import java.util.*;

public class ProblemSolving {

    private static final int offset[] = {-1, 0, 1};

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[][] grid = new int[][]{{9, 9, 8, 1}, {5, 6, 2, 6}, {8, 2, 6, 4}, {6, 2, 2, 2}};

    }


    public static String countAndSay(int n) {

        System.out.println("Hello World!");

        String str = String.valueOf(n);
        int len = str.length();
        char lastValue = str.charAt(0);
        int index = 1;
        Integer count = 1;

        String ans = "";

        while (index < len) {
            char c = str.charAt(index);
            if (c == lastValue) {
                count++;
            } else {
                ans = ans + String.valueOf(count) + lastValue;
                lastValue = c;
                count = 1;
            }
            index++;
        }

        ans = ans + String.valueOf(count) + lastValue;

        System.out.println("ans : " + ans);
        return ans;
    }

    public List<String> topKFrequent1(String[] words, int k) {

        Map<String, Integer> mp = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            Integer currentCount = mp.get(words[i]);

            if (currentCount == null) {
                currentCount = Integer.valueOf(0);
            }

            mp.put(words[i], currentCount + 1);
        }


        Map<Integer, Set<String>> countMap = new TreeMap<>();
        for (Map.Entry<String, Integer> element : mp.entrySet()) {

            Integer count = element.getValue();
            String str = element.getKey();
            Set<String> st = countMap.get(count);

            if (st == null) {
                st = new HashSet<String>();
            }
            st.add(str);
            countMap.put(element.getValue(), st);
        }
        System.out.println("countMap : " + countMap);

        Map<Integer, Set<String>> reverseMap = ((TreeMap<Integer, Set<String>>) countMap).descendingMap();

        List<String> list = new ArrayList<>();

        for (Map.Entry<Integer, Set<String>> eachElement : reverseMap.entrySet()) {
            if (list.size() > k)
                break;

            Set<String> stringSet = eachElement.getValue();

            for (String string : stringSet) {
                if (list.size() > k)
                    break;
                list.add(string);
            }
        }


        return list;

    }


}
