package ng.com.asciidev.arrays;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynamicArray <T> implements Iterable <T> {

    private T [] internalArray; // internal static array
    private int length = 0; // length we show to the user
    private int capacity = 0; // the actual size of the internal array

    public DynamicArray() { this(4); }
    public DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException(("Illegal Capacity: " + capacity));
        this.capacity = capacity;
        internalArray = (T[]) new Object[capacity];
    }

    public int size() { return length; }
    public boolean isEmpty() { return size() == 0; }

    // TODO: Do bounds check for get() and set()
    public T get(int index) { return internalArray[index]; }
    public void set(int index, T element) { internalArray[index] = element; }

    public void clear() {
        for (int i = 0; i < capacity; i++)
            internalArray[i] = null;
        length = 0;
    }

    public void add(T element) {
        if ((length + 1) >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2; // double the size
            T[] newInternalArray = (T[]) new Object[capacity];
            // TODO: keeping this commented block to better understand how it
            // TODO: translates into System.arraycopy() sometime in the future
            // TODO: as IntelliJ suggests it
//            for (int i = 0; i < length; i++)
//                newInternalArray[i] = internalArray[i];
            if (length >= 0)
                System.arraycopy(internalArray, 0, newInternalArray, 0, length);
            internalArray = newInternalArray;
        }
        internalArray[length++] = element;
    }

    public T removeAt(int index) {
        // TODO: Use this bounds check in set() and get() also
        if (index >= length && index < 0) throw new IndexOutOfBoundsException("Invalid index");
        T data = internalArray[index];
        // Set up a new array for the array elements without the element we want to remove
        T[] newInternalArray = (T[]) new Object[length - 1];
        for (int i = 0, j = 0; i < length; i++, j++) {
            if (i == index) j--; // Skip over index by fixing j temporarily
            else newInternalArray[j] = internalArray[i];
        }
        internalArray = newInternalArray;
        capacity = --length;
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < length; i++) {
            if (internalArray[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < length; i++) {
            if (internalArray[i].equals(obj))
                return i;
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() { return index < length; }

            @Override
            public T next() { return internalArray[index++]; }
        };
    }

    @Override
    public String toString() {
        if (length == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(length).append("[");
            for (int i = 0; i < length - 1; i++)
                sb.append(internalArray[i]).append(", ");
            return sb.append(internalArray[length - 1]).append("]").toString();
        }
    }
}
