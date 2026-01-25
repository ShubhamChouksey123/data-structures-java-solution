package dev.shubham.algorithms.linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public ListNode head;

    public boolean hasCycle(ListNode head) {

        if (head == null) {
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
                return true;
        }

        return false;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode dummy = new ListNode(-300);

        ListNode head1 = list1;
        ListNode head2 = list2;
        ListNode head = dummy;
        ListNode current = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                current.next = head1;
                current = current.next;
                head1 = head1.next;
            } else {
                current.next = head2;
                current = current.next;
                head2 = head2.next;
            }
            current.next = null;
        }

        if (head1 != null) {
            current.next = head1;
        } else {
            current.next = head2;
        }
        return head.next;
    }

    public Node appendNode(Node copyCurrent, Node originalNode, int index, Map<Node, Integer> originalNodeIndex,
            Map<Integer, Node> copyIndexNode) {

        if (originalNode == null)
            return null;

        copyCurrent.next = new Node(originalNode.val);
        copyCurrent = copyCurrent.next;

        originalNodeIndex.put(originalNode, index);
        copyIndexNode.put(index, copyCurrent);

        return copyCurrent;
    }

    private void connectNodes(Node originalCurrent, Node copyCurrent, Map<Node, Integer> originalNodeIndex,
            Map<Integer, Node> copyIndexNode) {

        while (originalCurrent != null) {
            if (originalCurrent.random != null) {
                int indexConnectedNode = originalNodeIndex.get(originalCurrent.random);
                copyCurrent.random = copyIndexNode.get(indexConnectedNode);
            }
            originalCurrent = originalCurrent.next;
            copyCurrent = copyCurrent.next;
        }
    }

    public Node copyRandomList(Node head) {

        if (head == null)
            return null;

        Map<Node, Integer> originalNodeIndex = new HashMap<>();
        Map<Integer, Node> copyIndexNode = new HashMap<>();
        Node currentOriginal = head;
        Node dummy = new Node(0);
        Node currentCopy = dummy;
        int index = 0;
        while (currentOriginal != null) {
            currentCopy = appendNode(currentCopy, currentOriginal, index, originalNodeIndex, copyIndexNode);
            currentOriginal = currentOriginal.next;
            index++;
        }

        connectNodes(head, dummy.next, originalNodeIndex, copyIndexNode);

        return dummy.next;
    }

    public ListNode addNode(ListNode current) {

        return null;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode current1 = l1;
        ListNode current2 = l2;

        ListNode headSumList = new ListNode(0);
        ListNode currentSumList = headSumList;

        int carry = 0;
        while (current1 != null || current2 != null) {
            int sum = carry;
            if (current1 != null) {
                sum += current1.val;
                current1 = current1.next;
            }
            if (current2 != null) {
                sum += current2.val;
                current2 = current2.next;
            }

            if (sum >= 10) {
                sum = sum % 10;
                carry = 1;
            } else {
                carry = 0;
            }

            currentSumList.next = new ListNode(sum);
            currentSumList = currentSumList.next;
        }
        if (carry == 1) {
            currentSumList.next = new ListNode(1);
            currentSumList = currentSumList.next;
        }

        return headSumList.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null || head.next == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode current = head;

        int times = n;
        while (times > 0) {
            times--;
            current = current.next;
        }

        ListNode leading = current;
        ListNode trailing = head;

        if (leading == null) {
            return head.next;
        }

        while (leading != null && leading.next != null) {
            leading = leading.next;
            trailing = trailing.next;
        }

        trailing.next = trailing.next.next;
        return head;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    private ListNode removeNodes(ListNode head, List<Integer> numbers, List<Integer> maxElementRight, int n) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        for (int i = 0; i < n; i++) {
            if (numbers.get(i) < maxElementRight.get(i)) {
                /** remove this node */
                current.next = current.next.next;
            } else
                current = current.next;
        }

        return dummy.next;
    }

    public ListNode removeNodes(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode current = head;
        List<Integer> numbers = new ArrayList<>();

        while (current != null) {
            numbers.add(current.val);
            current = current.next;
        }

        int n = numbers.size();
        List<Integer> maxElementRight = new ArrayList<>(numbers);
        maxElementRight.set(n - 1, Integer.MIN_VALUE);
        int maxValue = numbers.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            maxElementRight.set(i, maxValue);
            maxValue = Math.max(maxValue, numbers.get(i));
        }

        head = removeNodes(head, numbers, maxElementRight, n);

        return head;
    }

    public ListNode doubleItUtil(ListNode head) {

        ListNode current = head;

        if (head.val >= 5) {
            ListNode carry = new ListNode(1);
            carry.next = head;

            head = carry;
        }

        while (current != null) {
            int num = current.val * 2;
            if (current.next != null && current.next.val >= 5) {
                num++;
            }
            int val = num % 10;
            current.val = val;
            current = current.next;
        }

        return head;
    }

    public ListNode doubleIt(ListNode head) {
        ListNode current = head;

        if (head.val >= 5) {
            ListNode carry = new ListNode(1);
            carry.next = head;

            head = carry;
        }

        while (current != null) {
            int num = current.val * 2;
            if (current.next != null && current.next.val >= 5) {
                num++;
            }
            int val = num % 10;
            current.val = val;
            current = current.next;
        }

        return head;
    }
}
