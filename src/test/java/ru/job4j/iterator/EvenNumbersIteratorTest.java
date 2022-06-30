package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class EvenNumbersIteratorTest {
    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        Iterator<Integer> it = new EvenNumbersIterator(
                new int[] {1, 2, 3, 4, 5, 6, 7}
        );
        assertTrue(it.hasNext());
        assertEquals((long) it.next(), 2);
        assertTrue(it.hasNext());
        assertEquals((long) it.next(), 4);
        assertTrue(it.hasNext());
        assertEquals((long) it.next(), 6);
        assertFalse(it.hasNext());
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesNotAffectRetrievalOrder() {
        Iterator<Integer> it = new EvenNumbersIterator(
                new int[] {1, 2, 3, 4, 5, 6, 7}
        );
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertEquals((long) it.next(), 2);
        assertEquals((long) it.next(), 4);
        assertEquals((long) it.next(), 6);
    }

    @Test
    public void shouldReturnFalseIfNoAnyEvenNumbers() {
        Iterator<Integer> it = new EvenNumbersIterator(
                new int[] {1}
        );
        assertFalse(it.hasNext());
    }

    @Test
    public void shouldReturnFalseIfNoElements() {
        Iterator<Integer> it = new EvenNumbersIterator(
                new int[] {}
        );
        assertFalse(it.hasNext());
    }

    @Test
    public void shouldReturnTrueIfOnlyLastEvenElements() {
        Iterator<Integer> it = new EvenNumbersIterator(
                new int[] {1, 1, 1, 1, 1, 1, 1, 8}
        );
        assertTrue(it.hasNext());
        assertEquals((long) it.next(), 8);
    }

    @Test
    public void allNumbersAreEven() {
        Iterator<Integer> it = new EvenNumbersIterator(
                new int[] {2, 4, 6}
        );
        assertTrue(it.hasNext());
        assertEquals((long) it.next(), 2);
        assertTrue(it.hasNext());
        assertEquals((long) it.next(), 4);
        assertTrue(it.hasNext());
        assertEquals((long) it.next(), 6);
    }
}