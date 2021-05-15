package ng.com.asciidev.queues;

public class LinkedQueue <T> {
    private Node <T> front;
    private Node <T> back;

    private static class Node <T> {
        private T data;
        private Node <T> next;

        public Node(T data, Node <T> next) {
            this.data = data;
            this.next = next;
        }

    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(T data) {
        Node <T> newData = new Node <T> (data, null);
        if (back != null) {
            back.next = newData;
        }
        back = newData;
        if (front == null) {
            front = back;
        }
    }

    public T dequeue() {
        if (isEmpty()) throw new RuntimeException("Empty queue");
        T data = front.data;
        front = front.next;
        if (front == null) {
            back = null;
        }
        return data;
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException("Empty queue");
        return front.data;
    }
}
