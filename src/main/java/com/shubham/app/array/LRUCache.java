package com.shubham.app.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LRUCache {

    Map<Integer, Integer> lastIndex;
    Map<Integer, Integer> keyValue;
    Integer head;
    Integer capacity;

    public LRUCache(int capacity) {
        lastIndex = new HashMap<>();
        keyValue = new HashMap<>();
        head = -1;
        this.capacity = capacity;
    }

    public int get(int key) {

        System.out.println("searching for key : " + key + " and now the map state : " + lastIndex);
        Integer value = keyValue.get(key);
        if (value == null) {
            return -1;
        }
        int indexOfKey = lastIndex.get(key);

        if (head - capacity >= indexOfKey) {
            return -1;
        }

        if (Objects.equals(indexOfKey, head)) {
            return value;
        }

        lastIndex.put(key, head + 1);
        head++;

        return value;
    }

    public void put(int key, int value) {

        boolean isHeadTobeIncreased = true;
        Integer indexOfKey = lastIndex.get(key);
        if (indexOfKey != null && Objects.equals(indexOfKey, head)) {
            isHeadTobeIncreased = false;
        }

        keyValue.put(key, value);

        if (isHeadTobeIncreased) {
            lastIndex.put(key, head + 1);
            head++;
        }
    }
}
