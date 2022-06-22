package ru.job4j.iterator;

import java.util.NoSuchElementException;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class MatrixItTest {
    @Test
    public void when4El() {
        int[][] in = {
                {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals((long) it.next(), 1);
        assertFalse(it.hasNext());
    }

    @Test
    public void whenFirstEmptyThenNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals((long) it.next(), 1);
        assertFalse(it.hasNext());
    }

    @Test
    public void whenRowHasDiffSize() {
        int[][] in = {
                {1}, {2, 3}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals((long) it.next(), 1);
        assertEquals((long) it.next(), 2);
        assertEquals((long) it.next(), 3);
    }

    @Test
    public void whenFewEmpty() {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals((long) it.next(), 1);
        assertEquals((long) it.next(), 2);
    }

    @Test
    public void whenEmpty() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyThenNext() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        it.next();
    }

    @Test
    public void whenMultiHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenNoElements() {
        int[][] in = {
                {}, {}, {}
        };
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
    }
}
