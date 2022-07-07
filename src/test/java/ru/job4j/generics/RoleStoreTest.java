package ru.job4j.generics;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRoleIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        Role actual = store.findById("1");
        assertEquals(actual.getRole(), "User");
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        Role actual = store.findById("10");
        assertNull(actual);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.add(new Role("1", "Admin"));
        Role actual = store.findById("1");
        assertEquals(actual.getRole(), "User");
    }

    @Test
    public void whenReplaceThenRoleIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.replace("1", new Role("1", "Admin"));
        Role actual = store.findById("1");
        assertEquals(actual.getRole(), "Admin");
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRoleTitle() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.replace("10", new Role("10", "Admin"));
        Role actual = store.findById("1");
        assertEquals(actual.getRole(), "User");
        assertNull(store.findById("10"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        assertTrue(store.delete("1"));
        assertNull(store.findById("1"));
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        assertFalse(store.delete("10"));
        Role actual = store.findById("1");
        assertEquals(actual.getRole(), "User");
        assertNull(store.findById("10"));
    }
}