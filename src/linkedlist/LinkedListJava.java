package linkedlist;

public class LinkedListJava {

    private static ListNode createLinkedList() {

        ListNode head = new ListNode(6);
        head = head.appendBack(head, 5);
        head = head.appendBack(head, 4);
        head = head.appendBack(head, 3);
        head = head.appendBack(head, 2);
        head = head.appendBack(head, 1);


        return head;
    }

    private static void reverse() {
        ListNode head = createLinkedList();
        head.traverse(head);
    }


    public static void main(String[] args) {
        reverse();
    }
}
