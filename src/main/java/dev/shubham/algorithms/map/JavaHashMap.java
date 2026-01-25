package dev.shubham.algorithms.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.entry;

/** <a href="https://www.baeldung.com/java-initialize-hashmap">...</a> */
public class JavaHashMap {

    /** Mutable maps - can be modified */
    static Map<String, String> map = new HashMap<>(Map.of("key1", "value1", "key2", "value2"));

    /**
     * Roman Numbers and their values I 1 V 5 X 10 L 50 C 100 D 500 M 1000
     *
     * <p>
     * Immutable maps - can't be modified
     */
    private static Map<Character, Integer> decode = Map.ofEntries(entry('I', 1), entry('V', 5), entry('X', 10),
            entry('L', 50), entry('C', 100), entry('D', 500), entry('M', 1000));

    private static Map<String, Integer> mp = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Hello Map !");

        mp.put("Shubham", 10);
        mp.put("Shiv", 23);
        mp.put("Aditya", 41);
        mp.put("Vijay", 25);
        mp.put("Ram", 86);

        System.out.println("mp : " + mp);

        sortMapBasedOnKeyAscending();
        sortMapBasedOnKeyDescending();
        sortedMapBasedOnValueAscending();
        sortMapBasedOnValueDescending();

        for (Map.Entry<String, Integer> me : mp.entrySet()) {
            System.out.println(me.getKey() + " : " + me.getValue());
        }

        Boolean isPresentKey = mp.containsKey("Shiv");
        Boolean isPresentValue = mp.containsKey(20);
        System.out.println("isPresentKey : " + isPresentKey);
        System.out.println("isPresentValue : " + isPresentValue);

        Integer value = mp.get("Shiv");
        System.out.println("value : " + value);

        mp.put("Aditya", 100);
        System.out.println("Aditya : " + mp.get("Aditya"));

        mp.replace("Aditya", 101);
        System.out.println("Aditya : " + mp.get("Aditya"));

        System.out.println("randomName : " + mp.get("randomName"));
    }

    private static void sortMapBasedOnKeyAscending() {

        Map<String, Integer> sortedBasedOnKey = new LinkedHashMap<>();

        mp.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortedBasedOnKey.put(x.getKey(), x.getValue()));

        System.out.println("sortedBasedOnKey : " + sortedBasedOnKey);
    }

    private static void sortMapBasedOnKeyDescending() {

        Map<String, Integer> sortedBasedOnKey = new LinkedHashMap<>();

        mp.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(x -> sortedBasedOnKey.put(x.getKey(), x.getValue()));

        System.out.println("sortedBasedOnValue : " + sortedBasedOnKey);
    }

    private static void sortMapBasedOnValueDescending() {

        Map<String, Integer> sortedBasedOnValue = new LinkedHashMap<>();

        mp.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(x -> sortedBasedOnValue.put(x.getKey(), x.getValue()));

        System.out.println("sortedBasedOnValue : " + sortedBasedOnValue);
    }

    private static void sortedMapBasedOnValueAscending() {
        Map<String, Integer> sortedMapBasedOnValue = new LinkedHashMap<>();

        mp.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(x -> sortedMapBasedOnValue.put(x.getKey(), x.getValue()));

        System.out.println("sortedMapBasedOnValue : " + sortedMapBasedOnValue);
    }
}
