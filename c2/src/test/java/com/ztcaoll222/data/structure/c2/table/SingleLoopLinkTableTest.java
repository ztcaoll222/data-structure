package com.ztcaoll222.data.structure.c2.table;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 循环单链表的单链表
 */
@Flogger
class SingleLoopLinkTableTest {

    @Test
    void length() {
        SingleLoopLinkTable<Integer> table = SingleLoopLinkTable.of();
        assertEquals(0, table.length());

        table = SingleLoopLinkTable.of(1, 2, 3);
        assertEquals(3, table.length());
    }

    @Test
    void locateElem() {
        SingleLoopLinkTable<Integer> table = SingleLoopLinkTable.of();
        assertTrue(table.locateElem(0).isEmpty());

        table = SingleLoopLinkTable.of(1, 2, 3);
        assertTrue(table.locateElem(0).isEmpty());
        var res = table.locateElem(3);
        assertTrue(res.isPresent());
        assertEquals(3, res.get().getValue());
    }

    @Test
    void findElem() {
        SingleLoopLinkTable<Integer> table = SingleLoopLinkTable.of();
        assertTrue(table.findElem(0).isEmpty());
        assertTrue(table.findElem(-1).isEmpty());

        table = SingleLoopLinkTable.of(1, 2, 3);
        var elem = table.findElem(3);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = table.findElem(4);
        assertTrue(elem.isEmpty());
    }

    @Test
    void listInsertFirst() {
        SingleLoopLinkTable<Integer> table = SingleLoopLinkTable.of();
        assertTrue(table.listInsertFirst(3));
        assertEquals("3", table.printList());

        assertTrue(table.listInsertFirst(1, 2));
        assertEquals("1, 2, 3", table.printList());

        assertFalse(table.listInsertFirst());
    }

    @Test
    void listInsert() {
        SingleLoopLinkTable<Integer> table = SingleLoopLinkTable.of();
        assertTrue(table.listInsert(1, 1));
        assertEquals("1", table.printList());

        assertTrue(table.listInsert(2, 6));
        assertEquals("1, 6", table.printList());

        assertTrue(table.listInsert(2, 2));
        assertEquals("1, 2, 6", table.printList());

        assertTrue(table.listInsert(3, 3));
        assertEquals("1, 2, 3, 6", table.printList());

        table.listInsert(4, 4, 5);
        assertEquals("1, 2, 3, 4, 5, 6", table.printList());

        int length = table.length();
        assertTrue(table.listInsert(length + 2, length + 2));
        assertEquals("1, 2, 3, 4, 5, 6, " + (length + 2), table.printList());

        assertTrue(table.listInsert(-1, -1));
        assertEquals("-1, 1, 2, 3, 4, 5, 6, " + (length + 2), table.printList());

        assertFalse(table.listInsert(Integer.MAX_VALUE));
    }

    @Test
    void listInsertLast() {
        SingleLoopLinkTable<Integer> table = SingleLoopLinkTable.of();
        assertTrue(table.listInsertLast(1, 2));
        assertEquals("1, 2", table.printList());

        assertTrue(table.listInsertLast(3, 4));
        assertEquals("1, 2, 3, 4", table.printList());

        assertFalse(table.listInsertLast());
    }

    @Test
    void listDeleteFirst() {
        SingleLoopLinkTable<Integer> table = SingleLoopLinkTable.of();
        var node = table.listDeleteFirst();
        assertTrue(node.isEmpty());

        table = SingleLoopLinkTable.of(1, 2, 3);
        node = table.listDeleteFirst();
        assertTrue(node.isPresent());
        assertEquals(1, node.get().getValue());

        node = table.listDeleteFirst();
        assertTrue(node.isPresent());
        assertEquals(2, node.get().getValue());

        node = table.listDeleteFirst();
        assertTrue(node.isPresent());
        assertEquals(3, node.get().getValue());

        assertTrue(table.empty());
    }

    @Test
    void listDelete() {
        SingleLoopLinkTable<Integer> table = SingleLoopLinkTable.of();
        var elem = table.listDelete(-1);
        assertTrue(elem.isEmpty());

        elem = table.listDelete(1);
        assertTrue(elem.isEmpty());

        table = SingleLoopLinkTable.of(1, 2, 3, 4);
        elem = table.listDelete(5);
        assertTrue(elem.isEmpty());

        elem = table.listDelete(2);
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());

        elem = table.listDelete(3);
        assertTrue(elem.isPresent());
        assertEquals(4, elem.get().getValue());

        elem = table.listDelete(2);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = table.listDelete(1);
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());

        assertTrue(table.empty());
    }

    @Test
    void listDeleteLast() {
        SingleLoopLinkTable<Integer> table = SingleLoopLinkTable.of();
        var elem = table.listDeleteLast();
        assertTrue(elem.isEmpty());

        table = SingleLoopLinkTable.of(1, 2, 3);
        elem = table.listDeleteLast();
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = table.listDeleteLast();
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());

        elem = table.listDeleteLast();
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());

        assertTrue(table.empty());
    }

    @Test
    void printList() {
        SingleLoopLinkTable<Integer> table = SingleLoopLinkTable.of();
        assertEquals("", table.printList());

        table = SingleLoopLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }

    @Test
    void empty() {
        SingleLoopLinkTable<Integer> table = SingleLoopLinkTable.of();
        assertTrue(table.empty());
    }

    @Test
    void destroyList() {
        var table = SingleLoopLinkTable.of(1, 2, 3);
        table.destroyList();
        assertTrue(table.empty());
    }

    @Test
    void of() {
        var table = SingleLoopLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }
}
