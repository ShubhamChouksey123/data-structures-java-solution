package dev.shubham.problems.atlassian;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeliveryCart {

    public static void main(String[] args) {
       System.out.println("Welcome to the Delivery Cart Program!");

        List<List<String>> edges =  new ArrayList<>();
        /**
         * A -> B
         * A -> C
         * B -> D
         * C -> D
         * E -> F
         * F -> G
         */
        edges.add(List.of("A", "B"));
        edges.add(List.of("A", "C"));
        edges.add(List.of("B", "D"));
        edges.add(List.of("C", "D"));
        edges.add(List.of("E", "F"));
        edges.add(List.of("F", "G"));

        List<Pair<String, String>> ans = getStartAndEndLocations(edges);
        System.out.println(ans);



        List<List<String>> edges2 =  new ArrayList<>();
        edges2.add(List.of("A", "B"));
        edges2.add(List.of("B", "C"));
        edges2.add(List.of("C", "D"));
        edges2.add(List.of("D", "E"));
        edges2.add(List.of("E", "F"));
        edges2.add(List.of("F", "G"));
        edges2.add(List.of("G", "H"));
        edges2.add(List.of("H", "C"));
        edges2.add(List.of("E", "I"));
        edges2.add(List.of("I", "J"));
        edges2.add(List.of("E", "K"));
        List<Pair<String, String>> ans2 = getStartAndEndLocations(edges2);
        System.out.println(ans2);

    }

    public record Pair<A, B> (A start, B end) {}

    private static void dfs(Map<String, List<String>> adj, Set<String> visited, List<String> endNodes,  String node){

        visited.add(node);

        if(adj.get(node).isEmpty()){
            endNodes.add(node);
        }

        for(String neighbor : adj.get(node)){
            if(!visited.contains(neighbor)){

                dfs(adj, visited, endNodes, neighbor);
            }
        }

    }

    /**
     * Time Complexity : O( V * (V + E))
     * Space Complexity : O(V * E)
     * @param edges
     * @return
     */
    private static List<Pair<String, String>> getStartAndEndLocations(List<List<String>> edges){

        Map<String, List<String>> adj = new HashMap<>();
        Map<String, Integer> nodeToInwardEdgesCount = new HashMap<>();

        for(int i = 0; i < edges.size(); i++){
            String u =  edges.get(i).get(0);
            String v =  edges.get(i).get(1);
            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());
            nodeToInwardEdgesCount.putIfAbsent(u, 0);
            nodeToInwardEdgesCount.putIfAbsent(v, 0);

            adj.get(u).add(v);
            nodeToInwardEdgesCount.put(v, nodeToInwardEdgesCount.getOrDefault(v, 0) + 1);
        }

        List<Pair<String, String>> ans = new ArrayList<>();

        for(Map.Entry<String, Integer> entry : nodeToInwardEdgesCount.entrySet()){

            if(entry.getValue() != 0) continue;

            String startNode = entry.getKey();
            Set<String> visited = new HashSet<>();
            List<String> endNodes = new ArrayList<>();


            dfs(adj, visited, endNodes, startNode);

            for(String endNode : endNodes){
                ans.add(new Pair<>(startNode, endNode));
            }
        }
        return ans;
    }




}

