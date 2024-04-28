package linkedlist;

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


    public static void main(String[] args) {

        int[] nums1 = new int[]{1, 2, 4};
        ListNode head1 = createLinkedList(nums1);

        int[] nums2 = new int[]{1, 3, 4};
        ListNode head2 = createLinkedList(nums2);

        head1.traverse(head1);
        head2.traverse(head2);


        Solution solution = new Solution();
        ListNode merged = solution.mergeTwoLists(head1, head2);
        merged.traverse(merged);
    }
}
