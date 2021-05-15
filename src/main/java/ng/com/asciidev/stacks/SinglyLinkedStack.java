package ng.com.asciidev.stacks;

public class SinglyLinkedStack <T> {
    private int size = 0;
    private Node<T> head = null;

    private class Node <T> {
        T element;
        Node <T> next;

        public Node(T element, Node <T> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.element;
    }

    public T pop() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        T temp = head.element;
        head = head.next;
        --size;
        return temp;
    }

    public void push(T element) {
        head = new Node<T>(element, head);
    }
}
