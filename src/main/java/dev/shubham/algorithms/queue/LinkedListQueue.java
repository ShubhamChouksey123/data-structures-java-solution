package dev.shubham.algorithms.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Normal Queue is implemented by LinkedList
 */
public class LinkedListQueue {

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();

        q.add(1);
        q.add(2);
        q.add(100);
        q.add(200);
        q.add(222);

        System.out.println("q : " + q);
        System.out.println("front : " + q.poll());
        System.out.println("new front : " + q.peek());

    }
}
