package com.shubham.app.linkedlist;

public class MainClass {

    private static ListNode createLinkedList() {

        ListNode head = new ListNode(6);
        head = head.appendBack(head, 5);
        head = head.appendBack(head, 4);
        head = head.appendBack(head, 3);
        head = head.appendBack(head, 2);
        head = head.appendBack(head, 1);

        return head;
    }

    public static ListNode appendAtStart(ListNode head, int newValue) {
        ListNode newNode = new ListNode(newValue);
        head.next = newNode;
        return newNode;
    }

    private static ListNode createLinkedList(int[] nums) {

        int n = nums.length;

        if (n == 0) {
            return null;
        }

        int value = nums[0];
        ListNode head = new ListNode(value);

        ListNode current = head;

        for (int i = 1; i < n; i++) {
            current = appendAtStart(current, nums[i]);
        }
        return head;
    }

    public static Node appendAtStart(Node head, int newValue) {
        Node newNode = new Node(newValue);
        head.next = newNode;
        return newNode;
    }

    private static Node createLinkedListNode(int[] nums) {

        int n = nums.length;

        if (n == 0) {
            return null;
        }

        int value = nums[0];
        Node head = new Node(value);

        Node current = head;

        for (int i = 1; i < n; i++) {
            current = appendAtStart(current, nums[i]);
        }
        return head;
    }

    public static void traverse(Node head) {

        if (head != null) {
            System.out.print(head.val);
            head = head.next;
        }

        while (head != null) {
            System.out.print(" -> " + head.val);
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums1 = new int[]{9, 9, 9};
        ListNode head1 = createLinkedList(nums1);

        head1.traverse(head1);
        Solution solution = new Solution();
        // head1 = solution.removeNthFromEnd(head1, 5);

        head1 = solution.doubleIt(head1);
        head1.traverse(head1);
    }
}
