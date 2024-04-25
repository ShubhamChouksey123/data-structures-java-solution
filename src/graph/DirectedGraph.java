package graph;

import java.util.HashMap;
import java.util.Map;

public class DirectedGraph {

    public static int edgeScore(int[] edges) {
        System.out.println("Reached");
        int n = edges.length;
        Map<Integer, Long> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Integer start = i;
            Integer end = edges[i];
            Long currentSum = mp.get(end);
            if(currentSum == null){
                currentSum = Long.valueOf(0);
            }
            mp.put(end, currentSum + start);
        }

        Integer maxSumElement = 0;
        Long maxSum = Long.valueOf(0);
        for(Map.Entry<Integer, Long> element : mp.entrySet()){
            if(element.getValue() > maxSum ){
                maxSum = element.getValue();
                maxSumElement = element.getKey();
            }
        }

        return maxSumElement;
    }


    public static void main(String[] args) {
        System.out.println("Hello Graph World");

        int[] edges = new int[]{1, 0, 0, 0, 0, 7, 7, 5};

        System.out.println("Ans : " + edgeScore(edges));
    }

}
