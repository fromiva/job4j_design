package ru.job4j.generics;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class UserStoreTest {
    @Test
    public void whenAddAndFindThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        User actual = store.findById("1");
        assertEquals(actual.getUsername(), "Petr");
    }

    @Test
    public void whenAddAndFindThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        User actual = store.findById("10");
        assertNull(actual);
    }

    @Test
    public void whenAddDuplicateAndFindUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.add(new User("1", "Maxim"));
        User actual = store.findById("1");
        assertEquals(actual.getUsername(), "Petr");
    }

    @Test
    public void whenReplaceThenUsernameIsMaxim() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.replace("1", new User("1", "Maxim"));
        User actual = store.findById("1");
        assertEquals(actual.getUsername(), "Maxim");
    }

    @Test
    public void whenNoReplaceUserThenNoChangeUsername() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        store.replace("10", new User("10", "Maxim"));
        User actual = store.findById("1");
        assertEquals(actual.getUsername(), "Petr");
        assertNull(store.findById("10"));
    }

    @Test
    public void whenDeleteUserThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        assertTrue(store.delete("1"));
        assertNull(store.findById("1"));
    }

    @Test
    public void whenNoDeleteUserThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("1", "Petr"));
        assertFalse(store.delete("10"));
        User actual = store.findById("1");
        assertEquals(actual.getUsername(), "Petr");
        assertNull(store.findById("10"));
    }
}