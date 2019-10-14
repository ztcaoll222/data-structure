package com.ztcaoll222.data.structure.c2.impl.table;

import com.ztcaoll222.data.structure.c2.impl.node.DoubleLinkTableNodeImpl;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 双链表单元测试
 */
@Flogger
class DoubleLinkTableTest {
    @Test
    void locateElem() {
        DoubleLinkTable<Integer> table = DoubleLinkTable.of();
        assertTrue(table.locateElem(0).isEmpty());

        table = DoubleLinkTable.of(1, 2, 3);
        assertTrue(table.locateElem(0).isEmpty());
        var res = table.locateElem(3);
        assertTrue(res.isPresent());
        assertEquals(3, res.get().getValue());
    }

    @Test
    void findElem() {
        DoubleLinkTable<Integer> table = DoubleLinkTable.of();
        assertTrue(table.findElem(0).isEmpty());

        table = DoubleLinkTable.of(1, 2, 3);
        var elem = table.findElem(3);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = table.findElem(4);
        assertTrue(elem.isEmpty());
    }

    @Test
    void listInsert() {
        DoubleLinkTable<Integer> table = DoubleLinkTable.of();
        assertTrue(table.listInsert(1, 1));
        assertEquals("1", table.printList());

        assertTrue(table.listInsert(2, 4));
        assertEquals("1, 4", table.printList());

        assertTrue(table.listInsert(2, 2));
        assertEquals("1, 2, 4", table.printList());

        assertTrue(table.listInsert(3, 3));
        assertEquals("1, 2, 3, 4", table.printList());

        int length = table.length();
        assertFalse(table.listInsert(length + 2, length + 2));
        assertFalse(table.listInsert(-1, -1));
    }

    @Test
    void listDeleteLast() {
        DoubleLinkTable<Integer> table = DoubleLinkTable.of();
        var elem = table.listDeleteLast();
        assertTrue(elem.isEmpty());

        table = DoubleLinkTable.of(1, 2);
        elem = table.listDeleteLast();
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());

        elem = table.listDeleteLast();
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());
        assertTrue(table.empty());
    }

    @Test
    void listDelete() {
        DoubleLinkTable<Integer> table = DoubleLinkTable.of();
        var elem = table.listDelete(1);
        assertTrue(elem.isEmpty());

        table = DoubleLinkTable.of(1, 2, 3);
        elem = table.listDelete(2);
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());

        elem = table.listDelete(2);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = table.listDelete(1);
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());
    }

    @Test
    void printList() {
        DoubleLinkTable<Integer> table = DoubleLinkTable.of();
        assertEquals("", table.printList());

        table = DoubleLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }

    @Test
    void getElem() {
        DoubleLinkTable<Integer> table = DoubleLinkTable.of();
        assertTrue(table.getElem(0).isEmpty());

        table = DoubleLinkTable.of(1, 2, 3);
        var elem = table.getElem(3);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get());

        elem = table.getElem(4);
        assertTrue(elem.isEmpty());
    }

    @Test
    void listInsertLast() {
        DoubleLinkTable<Integer> table = DoubleLinkTable.of();
        table.listInsertLast(1);
        assertEquals("1", table.printList());

        table.listInsertLast(2);
        assertEquals("1, 2", table.printList());
    }

    @Test
    void empty() {
        DoubleLinkTable<Integer> table = DoubleLinkTable.of();
        assertTrue(table.empty());
    }

    @Test
    void destroyList() {
        var table = DoubleLinkTable.of(1, 2, 3);
        table.destroyList();
        assertTrue(table.empty());
    }

    @Test
    void length() {
        DoubleLinkTable<Integer> table = DoubleLinkTable.of();
        assertEquals(0, table.length());

        table = DoubleLinkTable.of(1, 2, 3);
        assertEquals(3, table.length());
    }

    @Test
    void setFirst() {
        var table = DoubleLinkTable.of(1, 2, 3);
        table.setFirst(null);
        assertTrue(table.empty());

        table.setFirst(new DoubleLinkTableNodeImpl<>(2));
        assertEquals("2", table.printList());

        table.setFirst(new DoubleLinkTableNodeImpl<>(1));
        assertEquals("1, 2", table.printList());
    }

    @Test
    void getFirst() {
        DoubleLinkTable<Integer> table = DoubleLinkTable.of();
        assertNull(table.getFirst());

        table = DoubleLinkTable.of(1, 2, 3);
        assertEquals(1, table.getFirst().getValue());
    }

    @Test
    void of() {
        var table = DoubleLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }
}
