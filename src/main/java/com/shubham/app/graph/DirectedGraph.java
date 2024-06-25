package com.shubham.app.graph;

import java.util.*;

public class DirectedGraph {

    public static int edgeScore(int[] edges) {
        System.out.println("Reached");
        int n = edges.length;
        Map<Integer, Long> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Integer start = i;
            Integer end = edges[i];
            Long currentSum = mp.get(end);
            if (currentSum == null) {
                currentSum = Long.valueOf(0);
            }
            mp.put(end, currentSum + start);
        }

        Integer maxSumElement = 0;
        Long maxSum = Long.valueOf(0);
        for (Map.Entry<Integer, Long> element : mp.entrySet()) {
            if (element.getValue() > maxSum) {
                maxSum = element.getValue();
                maxSumElement = element.getKey();
            }
        }

        return maxSumElement;
    }

    public static void main(String[] args) {

        int[] edges = new int[]{1, 0, 0, 0, 0, 7, 7, 5};
        System.out.println("Ans : " + edgeScore(edges));

        testCreateGraph();
    }

    private static void createGraph(List<List<Integer>> graph) {
        graph.add(List.of(1, 2, 5)); // 0
        graph.add(List.of(0, 3, 4)); //1
        graph.add(List.of(0, 5)); //2
        graph.add(List.of(1)); //3
        graph.add(List.of(1)); //4
        graph.add(List.of(0, 2)); //5
        System.out.println("graph : " + graph);
    }

    private static void testCreateGraph() {
        List<List<Integer>> graph = new ArrayList<>();
        createGraph(graph);

        bfsOfGraph(0, graph);

        dfsOfGraph(0, graph);
    }

    private static void bfsOfGraph(int vertex, List<List<Integer>> graph) {

        int numberOfVertexes = graph.size();

        List<Boolean> visited = Arrays.asList(new Boolean[numberOfVertexes]);
        Collections.fill(visited, Boolean.FALSE);

        System.out.println("visited : " + visited);

        List<Integer> ans = new ArrayList<>();
        bfsFromVertex(vertex, graph, ans, visited);


        System.out.println("bfs : " + ans);
    }

    private static void bfsFromVertex(int vertex, List<List<Integer>> graph, List<Integer> ans, List<Boolean> visited) {

        Queue<Integer> queue = new LinkedList<>();
        visited.set(vertex, true);
        queue.add(vertex);

        while (!queue.isEmpty()) {

            int currentVertex = queue.poll();
            ans.add(currentVertex);

            for (int adjacentVertex : graph.get(currentVertex)) {
                // already not visited => add it to the queue
                if (!visited.get(adjacentVertex)) {
                    visited.set(adjacentVertex, true);
                    queue.add(adjacentVertex);
                }
            }
        }

    }


    private static void dfsOfGraph(int vertex, List<List<Integer>> graph) {

        int numberOfVertexes = graph.size();
        List<Boolean> visited = Arrays.asList(new Boolean[numberOfVertexes]);
        Collections.fill(visited, Boolean.FALSE);

        List<Integer> ans = new ArrayList<>();
        dfsFromVertex(vertex, graph, ans, visited);

        System.out.println("dfs : " + ans);

    }

    private static void dfsFromVertex(int vertex, List<List<Integer>> graph, List<Integer> ans, List<Boolean> visited) {

        ans.add(vertex);
        visited.set(vertex, Boolean.TRUE);

        for (int neighbors : graph.get(vertex)) {
            if (!visited.get(neighbors)) {
                dfsFromVertex(neighbors, graph, ans, visited);
            }
        }
    }


}
