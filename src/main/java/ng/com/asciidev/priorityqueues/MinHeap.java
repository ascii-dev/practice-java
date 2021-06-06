package ng.com.asciidev.priorityqueues;

import java.util.*;

public class MinHeap <T extends Comparable<T>>  {
    private List<T> heap = null;

    public MinHeap() {
        this(1);
    }

    public MinHeap(int size) {
        heap = new ArrayList<>(size);
    }

    public MinHeap(T[] elements) {
        int size = elements.length;
        heap = new ArrayList<T>(size);

        // Arrays.asList(): It is cheap, O(1). As you suspect the list is merely a wrapper around the array.
        // This is confirmed by the Java API documentation: Returns a fixed-size list backed by the specified
        // array. (Changes to the returned list "write through" to the array.)
        // https://stackoverflow.com/questions/1552783/performance-of-arrays-aslist

        // .subList(): I think it runs in O(n) where n is size - 0; I don't think we need subList in addAll
        // since addAll accepts a list which is returned by asList. So this would be:
        // heap.addAll(Arrays.asList(elements);
        // instead of:
        // heap.addAll(Arrays.asList(elements).subList(0, size));

        for (int i = 0; i < size; i++) heap.add(elements[i]);

        // Heapify process
        for (int i = Math.max(0, (size / 2) - 1); i >= 0; i--) siftDown(i);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    public T poll() {
        if (isEmpty()) return null;

        int lastElementIndex = size() - 1;
        T removedData = heap.get(0);
        swap(0 , lastElementIndex);

        heap.remove(lastElementIndex);

        if (0 == lastElementIndex) return removedData;

        siftDown(0);

        return removedData;
    }

    public void add(T element) {
        if (element == null) throw new IllegalArgumentException();

        heap.add(element);

        int lastElementIndex = size() - 1;
        siftUp(lastElementIndex);
    }

    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    private void siftDown(int j) {
        int size = size();
        while ( true ) {
            int left = 2 * j + 1;
            int right = 2 * j + 2;
            int smallest = left;

            if (right < size && less(right, left)) smallest = right;

            if (left >= size || less(j, smallest)) break;

            swap(smallest, j);
            j = smallest;
        }
    }

    private void siftUp(int j) {
        int parent = (j - 1) / 2;

        while (j > 0 && less(j, parent)) {
            swap(parent, j);
            j = parent;

            parent = (j - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);

        heap.set(i, node2);
        heap.set(j, node1);
    }
}
