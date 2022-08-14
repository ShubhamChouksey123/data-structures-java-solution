package Map;

import java.util.HashMap;
import java.util.Map;

public class JavaHashMap {

    public static void main(String[] args) {
        System.out.println("Hello Map !");
        Map<String, Integer> mp = new HashMap<>();

        mp.put("Shubham", 10);
        mp.put("Shiv", 23);
        mp.put("Aditya", 41);


        for (Map.Entry<String, Integer> me : mp.entrySet()) {
            System.out.println(me.getKey() + " : " + me.getValue());
        }


    }

}
