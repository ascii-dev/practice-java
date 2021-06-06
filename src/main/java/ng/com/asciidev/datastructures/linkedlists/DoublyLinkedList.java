package ng.com.asciidev.datastructures.linkedlists;

import java.util.Iterator;

public class DoublyLinkedList <T> implements Iterable <T> {
    private int size = 0;
    private Node <T> head = null;
    private Node <T> tail = null;

    // Internal node class to represent the data
    private class Node <T> {
        T data;
        Node <T> previous, next;

        public Node(T data, Node <T> previous, Node <T> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // Empty the linked list, O(n)
    public void clear() {
        Node <T> iter = head;
        while (iter != null) {
            Node <T> next = iter.next;
            iter.previous = iter.next = null;
            iter.data = null;
            iter = next;
        }
        head = tail = iter = null;
        size = 0;
    }

    // Return the size of this linked list
    public int size() {
        return size;
    }

    // Is this linked list empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Add an element to the tail of this linked list: O(1)
    public void add(T element) {
        addLast(element);
    }

    // Add an element to the beginning of this linked list: O(1)
    public void addFirst(T element) {
        //The linked list is empty
        if (isEmpty()) {
            head = tail = new Node <T> (element, null, null);
        } else {
            head.previous = new Node <T> (element, null, head);
            head = head.previous;
        }

        size++;
    }

    // Add an element to the tail of the linked list: O(1)
    public void addLast(T element) {
        // The linked list is empty
        if (isEmpty()) {
            head = tail = new Node <T> (element, null, null);
        } else {
            tail.next = new Node <T> (element, tail, null);
            tail = tail.next;
        }

        size++;
    }

    // Check the value of the first node if it exists: O(1)
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }

    // Check the value of the last node if it exists: O(1)
    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return tail.data;
    }

    // Remove the value at the head of the linked list: O(1)
    public T removeFirst() {
        // Can't remove data from an empty list
        if (isEmpty()) throw new RuntimeException("Empty list");

        // Extract data from head and move head pointer to next node
        T temp = head.data;
        head = head.next;
        --size;

        // If the list is now empty, set the tail to null as well
        if (isEmpty()) tail = null;
        else head.previous = null; // remove all references to the just removed node

        return temp;
    }

    // Remove the value at the tail of the linked list: O(1)
    public T removeLast() {
        // Check for emptiness
        if (isEmpty()) throw new RuntimeException("Empty list");

        // Extract data from tail and move tail pointer to previous node
        T temp = tail.data;
        tail = tail.previous;
        --size;

        // If the list is now empty, set the head to null as well
        if (isEmpty()) head = null;
        else tail.next = null; // remove all references to the just removed node

        return temp;
    }

    // Remove an arbitrary node from the linked list: O(1)
    private T remove(Node <T> node) {
        // if the node to remove is somewhere either at the head or the tail
        if (node.previous == null) return removeFirst();
        if (node.next == null) return removeLast();

        // Make the pointers of adjacent nodes skip over current node
        node.next.previous = node.previous;
        node.previous.next = node.next;

        // Temporary store the data we want to return
        T data = node.data;

        // Memory cleanup
        node.data = null;
        node = node.previous = node.next = null;

        --size;

        return data;
    }

    // Remove a node at a particular index: O(n)
    public T removeAt(int index) {
        // Make sure the index provided is valid
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        int i;
        Node <T> iter;

        // Search from the front of the list
        if (index < (size / 2)) {
            for (i = 0, iter = head; i != index; i++) {
                iter = iter.next;
            }
        // Search from the back of the list
        } else {
            for (i = size - 1, iter = tail; i != index; i--) {
                iter = iter.previous;
            }
        }

        return remove(iter);
    }

    // Remove a particular value in the linked list: O(n)
    public boolean remove(Object obj) {
        Node <T> iter = head;

        // Support searching for null
        if (obj == null) {
            for (iter = head; iter != null; iter = iter.next) {
                if (iter.data == null) {
                    remove(iter);
                    return true;
                }
            }
        } else { // Search for non null object
            for (iter = head; iter != null; iter = iter.next) {
                if (obj.equals(iter.data)) {
                    remove(iter);
                    return true;
                }
            }
        }

        return false;
    }

    // Find the index of a particular value in the linked list: O(n)
    public int indexOf(Object obj) {
        int index = 0;
        Node <T> iter = head;

        // Support searching for null
        if (obj == null) {
            for (iter = head; iter != null; iter = iter.next, index++) {
                if (iter.data == null) {
                    return index;
                }
            }
        } else { // Search for non null object
            for (iter = head; iter != null; iter = iter.next, index++) {
                if (obj.equals(iter.data)) {
                    return index;
                }
            }
        }

        return -1;
    }

    // Check if a value is contained within the linked list
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator <T> () {
            private Node <T> iter = head;
            @Override
            public boolean hasNext() {
                return iter != null;
            }

            @Override
            public T next() {
                T data = iter.data;
                iter = iter.next;

                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("[");
        for (Node <T> iter = head; iter != null; iter = iter.next)
            sb.append(iter.data).append(", ");
        return sb.append("]").toString();
    }
}
