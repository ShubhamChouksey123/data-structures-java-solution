package dev.shubham.problems.salesforce;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CycleDetection {
    
    public static void main(String[] args){

        example1();
        example2();

        example3();
    }

    private static void example1(){

        int n = 4;
        List<Integer>[] adj = new ArrayList[n];
        for(int i = 0 ; i < n ; i++){
            adj[i] = new ArrayList<>();
        }
        adj[0].add(1);
        adj[1].add(2);
        adj[2].add(3);
        adj[3].add(1);

        boolean isCyclicGraph = isCyclic(adj, n);
        System.out.println("isCyclicGraph : " + isCyclicGraph);

        findNodesInCycle(adj, n);
    }

    private static void example2(){

        int n = 13;
        List<Integer>[] adj = new ArrayList[n];
        for(int i = 0 ; i < n ; i++){
            adj[i] = new ArrayList<>();
        }
        adj[0].add(1); adj[0].add(5); adj[0].add(4);
        adj[1].add(2); adj[1].add(3);
        adj[2].add(5);
        adj[3].add(7); adj[3].add(8);
        adj[4].add(6);
        adj[5].add(12);

        adj[7].add(10); adj[7].add(11);
        adj[8].add(9);

        adj[11].add(12);

        boolean isCyclicGraph = isCyclic(adj, n);
        System.out.println("isCyclicGraph : " + isCyclicGraph);

        findNodesInCycle(adj, n);
    }

    private static void example3(){

        int n = 9;
        List<Integer>[] adj = new ArrayList[n];
        for(int i = 0 ; i < n ; i++){
            adj[i] = new ArrayList<>();
        }
        adj[0].add(1);
        adj[1].add(2);
        adj[2].add(3);
        adj[3].add(4);
        adj[4].add(5); adj[4].add(6);
        adj[5].add(7); adj[5].add(8);
        adj[7].add(2);


        boolean isCyclicGraph = isCyclic(adj, n);
        System.out.println("isCyclicGraph : " + isCyclicGraph);

        findNodesInCycle(adj, n);
    }
    
    private static boolean isCyclic(List<Integer>[] adj, int n){
        
        int[] inwardEdges = new int[n];
        
        for(List<Integer> neighbours : adj){
            for(int neighbour : neighbours){
                inwardEdges[neighbour]++;
            }
        }
        
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i  = 0 ; i < n; i++){
            if(inwardEdges[i] == 0) queue.offerLast(i);
        }
        
        int visited = 0;
        
        while (!queue.isEmpty()){
            int node = queue.pollFirst();
            visited++;
            for(int neighbour : adj[node]){
                inwardEdges[neighbour]--;
                if(inwardEdges[neighbour] == 0){
                    queue.offerLast(neighbour);
                }
            }
        }

        return (visited != n);
    }

    private static void printCycleNodes(Deque<Integer> stack, int start){
        Deque<Integer> deque = new ArrayDeque<>(stack);
        while(deque.peekFirst() != start) deque.pollFirst();

        System.out.println("The nodes in cycle are stack : " + deque);
    }

    private static void dfs(List<Integer>[] adj, boolean[] visited, boolean[] inStack, int start, Deque<Integer> stack){

        if(visited[start]) return ;

        inStack[start] = true;
        stack.offerLast(start);

        for(int neighbour : adj[start]){

            if(visited[neighbour]) continue;

            if(inStack[neighbour]){
                printCycleNodes(stack, neighbour);
                continue;
            }
            dfs(adj, visited, inStack, neighbour, stack);
        }

        visited[start] = true;
        inStack[start] = false;
        stack.pollLast();
    }

    private static void findNodesInCycle(List<Integer>[] adj, int n){

        boolean[] visited = new boolean[n];

        for(int i = 0 ; i < n ; i++){
            if(!visited[i]){
                boolean[] inStack = new boolean[n];
                dfs(adj, visited, inStack, i, new ArrayDeque<>());
            }
        }

    }
    
}
