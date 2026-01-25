package dev.shubham.algorithms.map;

import java.util.Map;
import java.util.TreeMap;


/**
 * Java TreeMap are ordered set
 * <ol>
 *     <li>ordered map</li>
 *     <li>does not contain duplicates of key</li>
 * </ol>
 */
public class JavaTreeMap {

    /**
     * Order is maintained in the Tree HashMap, default increasing order similar to
     * C++ set
     */
    public static void main(String[] args) {
        System.out.println("Tree Map !");
        TreeMap<Integer, Integer> mp = new TreeMap<>();

        mp.put(4, 40);
        mp.put(2, 20);
        mp.put(1, 10);
        mp.put(7, 70);
        mp.put(4, 50);

        for (Map.Entry<Integer, Integer> me : mp.entrySet()) {
            System.out.println(me.getKey() + " : " + me.getValue());
        }

        System.out.println("the first key : " + mp.firstKey());
        System.out.println("the last key : " + mp.lastKey());

        mp.firstKey();


    }
}
