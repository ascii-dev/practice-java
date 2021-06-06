package ng.com.asciidev.datastructures.arrays;

import org.junit.Test;
import org.junit.jupiter.api.*;
import static com.google.common.truth.Truth.assertThat;

// TODO: Write more tests (maybe draw out / research a test plan)

public class DynamicArrayTest {
    @Test
    @DisplayName("Should test that empty returns true is list is actually empty")
    public void testEmptyListIsTrue() {
        DynamicArray<Integer> list = new DynamicArray<>();
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should test that empty returns false is list is not actually empty")
    public void testEmptyListIsFalse() {
        DynamicArray<Integer> list = new DynamicArray<>();
        list.add(1);
        assertThat(list.isEmpty()).isFalse();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    @DisplayName("Should test that removing from an invalid index raises out of bounds error")
    public void testInvalidIndexRemoveOutOfBounds() {
        DynamicArray<Integer> list = new DynamicArray<>();
        list.add(1);
        list.add(2);
        list.removeAt(3);
    }

    @Test
    @DisplayName("Should test that removal of an existing element returns true")
    public void testRemoveExistingElementTrue() {
        DynamicArray<Character> list = new DynamicArray<>();
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');
        assertThat(list.remove('c')).isTrue();
    }

    @Test
    @DisplayName("Should test that removal of a non existing element returns false")
    public void testRemoveNonExistingElementFalse() {
        DynamicArray<Character> list = new DynamicArray<>();
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('d');
        assertThat(list.remove('e')).isFalse();
    }
}
