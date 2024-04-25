package queue;

import java.util.LinkedList;
import java.util.Queue;

public class LinkedListQueue {

    public static void main(String[] args){
        Queue<Integer> q = new LinkedList<>();

        q.add(1);
        q.add(2);
        q.add(100);
        q.add(200);
        q.add(222);

        System.out.println("q : " + q);
    }

}
