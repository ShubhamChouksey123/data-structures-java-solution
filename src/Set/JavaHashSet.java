package Set;

import java.util.*;

/**
 * <a href="https://www.baeldung.com/java-hashset">...</a>
 */

/**
 * A hash set
 * <ul>
 *     <li>does not contain duplicates</li>
 *     <li>unordered set</li>
 *     <li>not thread safe</li>
 * </ul>
 */
public class JavaHashSet {


    /**
     * Mutable maps  - can be modified
     */
    private static Set<String> map = new HashSet<>(Set.of("A", "B", "C"));


    /**
     * Immutable maps - can't be modified
     */
    private static Set<Character> decode = Set.of(
            'A', 'B', 'C'
    );

    private static Set<Integer> st = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Hello Set !");

        st.add(1);
        st.add(2);
        st.add(8);
        st.add(10);
        st.add(100);

        System.out.println("st : " + st);
        System.out.println("set contains 1 : " + st.contains(1));
        System.out.println("set does not contains  : " + st.contains(200));

        System.out.println("removing 100 from the set : " + st.remove(100));
        System.out.println("st : " + st);

        System.out.println("size of set is : " + st.size());
        System.out.println("is set empty : " + st.isEmpty());

        iterate();


    }

    private static void sortMapBasedOnKeyAscending() {

        Map<String, Integer> sortedBasedOnKey = new LinkedHashMap<>();

//        mp.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByKey())
//                .forEachOrdered(x -> sortedBasedOnKey.put(x.getKey(), x.getValue()));


        System.out.println("sortedBasedOnKey : " + sortedBasedOnKey);

    }

    private static void sortMapBasedOnKeyDescending() {

        Map<String, Integer> sortedBasedOnKey = new LinkedHashMap<>();

//        mp.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
//                .forEach(x -> sortedBasedOnKey.put(x.getKey(), x.getValue()));

        System.out.println("sortedBasedOnValue : " + sortedBasedOnKey);
    }

    private static void sortMapBasedOnValueDescending() {

        Map<String, Integer> sortedBasedOnValue = new LinkedHashMap<>();

//        mp.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .forEach(x -> sortedBasedOnValue.put(x.getKey(), x.getValue()));

        System.out.println("sortedBasedOnValue : " + sortedBasedOnValue);

    }

    private static void sortedMapBasedOnValueAscending() {
        Map<String, Integer> sortedMapBasedOnValue = new LinkedHashMap<>();

//        mp.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue())
//                .forEach(x -> sortedMapBasedOnValue.put(x.getKey(), x.getValue()));

        System.out.println("sortedMapBasedOnValue : " + sortedMapBasedOnValue);
    }

    private static void iterate() {

        for (Integer integer : st) {
            System.out.print(integer + " ");
        }
        System.out.println();


        Iterator<Integer> itr = st.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
    }

    public int getRandom() {

        List<Integer> list = new ArrayList<>(st);

        Random rand = new Random();
        int no = rand.nextInt();
        if (no < 0) {
            no = no * -1;
        }

        no = no % list.size();

        return list.get(no);


    }
}
