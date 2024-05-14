package com.shubham.app.map;

import java.util.Map;
import java.util.TreeMap;

public class JavaTreeMap {

    /**
     * Order is maintained in the Tree HashMap, default increasing order similar to
     * C++ set
     */
    public static void main(String[] args) {
        System.out.println("Tree Map !");
        Map<Integer, Integer> mp = new TreeMap<>();

        mp.put(4, 40);
        mp.put(2, 20);
        mp.put(1, 10);
        mp.put(7, 70);

        for (Map.Entry<Integer, Integer> me : mp.entrySet()) {
            System.out.println(me.getKey() + " : " + me.getValue());
        }
    }
}
