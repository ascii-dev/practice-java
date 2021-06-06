package ng.com.asciidev.datastructures.stacks;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class DoublyLinkedStack <T> implements Iterable <T> {
    private LinkedList<T> list;

    // Create an empty stack
    public DoublyLinkedStack () {
        list = new LinkedList<>();
    }

    // Create a stack with an initial element
    public DoublyLinkedStack(T element) {
        this();
        push(element);
    }

    // Return the number of elements in the stack
    public int size() {
        return list.size();
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Push an element on the stack
    public void push(T element) {
        list.addLast(element);
    }

    // Pop an element off the stack
    // Throws an error if the stack is empty
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    // Peek the top of the stack without removing an element
    // Throws an exception if the stack is empty
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return list.getLast();
    }

    // Allow users to iterate through the stack using an iterator

    @Override
    public Iterator<T> iterator() {
        return list.iterator(); // java's linked list iterator supports concurrent modifications
    }
}
