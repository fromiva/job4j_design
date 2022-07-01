package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.NoSuchElementException;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class FlatMapTest {
    @Test
    public void whenDiffNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator(),
                List.of(2, 3).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(data);
        assertEquals((long) flat.next(), 1);
        assertEquals((long) flat.next(), 2);
        assertEquals((long) flat.next(), 3);
    }

    @Test
    public void whenSeqNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(data);
        assertEquals((long) flat.next(), 1);
        assertEquals((long) flat.next(), 2);
        assertEquals((long) flat.next(), 3);
    }

    @Test
    public void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(data);
        assertTrue(flat.hasNext());
        assertTrue(flat.hasNext());
    }

    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(data);
        assertEquals((long) flat.next(), 1);
        assertFalse(flat.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmpty() {
        Iterator<Integer> empty = Collections.emptyIterator();
        Iterator<Iterator<Integer>> data = List.of(
                empty
        ).iterator();
        FlatMap flat = new FlatMap(data);
        flat.next();
    }

    @Test
    public void whenSeveralEmptyAndNotEmpty() {
        Iterator<Integer> empty = Collections.emptyIterator();
        Iterator<Iterator<Integer>> data = List.of(
                empty,
                empty,
                empty,
                List.of(1).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(data);
        assertTrue(flat.hasNext());
        assertEquals((long) flat.next(), 1);
    }

    @Test
    public void whenSeveralEmptyThenReturnFalse() {
        Iterator<Integer> empty = Collections.emptyIterator();
        Iterator<Iterator<Integer>> data = List.of(
                empty,
                empty,
                empty,
                empty
        ).iterator();
        FlatMap flat = new FlatMap(data);
        assertFalse(flat.hasNext());
    }
}