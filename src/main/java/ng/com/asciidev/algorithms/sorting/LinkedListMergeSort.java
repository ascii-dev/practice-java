package ng.com.asciidev.algorithms.sorting;

// Merge Sort is often the preferred for sorting a linked list. The slow random-access
// performance of a linked list makes some other algorithms (such as quicksort) perform
// poorly and others (such as heapsort) completely impossible
public class LinkedListMergeSort {
    private class Node {
        int data;
        Node next;

        public Node(int data) { this.data = data; }
    }

    private Node getMiddle(Node head) {
        if (head == null || head.next == null) return head;

        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    private Node merge(Node left, Node right) {
        if (left == null) return right;
        else if (right == null) return left;
        if (left.data < right.data) {
            left.next = merge(left.next, right);
            return left;
        } else {
            right.next = merge(left, right.next);
            return right;
        }
    }

    public Node sort(Node head) {
        if (head == null || head.next == null) return head;

        Node middle = getMiddle(head);
        Node left = sort(head);
        Node right = sort(middle.next);
        middle.next = null;

        return merge(left, right);
    }

    static void print(Node head) {
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.data + "->");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public void main() {
        Node head = new Node(10);
        head.next = new Node(5);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        head.next.next.next.next = new Node(6);
        head.next.next.next.next.next = new Node(8);
        head.next.next.next.next.next.next = new Node(3);
        head.next.next.next.next.next.next.next = new Node(2);

        print(head);

        Node sortedHead = sort(head);
        print(sortedHead);
    }
}
