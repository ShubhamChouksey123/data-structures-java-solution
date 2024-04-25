package linkedlist;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void traverse(ListNode head) {

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

    public ListNode addNode(ListNode head, int newValue) {
        return new ListNode(newValue, head);
    }
}