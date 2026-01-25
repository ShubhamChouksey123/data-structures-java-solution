package dev.shubham.algorithms.doublelinkedlist;

public class ListNode {

    public int key;
    public int val;
    public ListNode next;
    public ListNode previous;

    public ListNode() {
    }

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    // public ListNode appendBack(ListNode head, int newValue) {
    // return new ListNode(newValue, head);
    // }
}
