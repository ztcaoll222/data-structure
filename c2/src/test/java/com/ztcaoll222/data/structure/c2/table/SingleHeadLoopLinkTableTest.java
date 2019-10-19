package com.ztcaoll222.data.structure.c2.table;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 带头结点的循环单链表的单元测试
 */
@Flogger
class SingleHeadLoopLinkTableTest {

    @Test
    void length() {
        SingleHeadLoopLinkTable<Integer> table = SingleHeadLoopLinkTable.of();
        assertEquals(0, table.length());

        table = SingleHeadLoopLinkTable.of(1, 2, 3);
        assertEquals(3, table.length());
    }

    @Test
    void locateElem() {
        SingleHeadLoopLinkTable<Integer> table = SingleHeadLoopLinkTable.of();
        assertTrue(table.locateElem(0).isEmpty());

        table = SingleHeadLoopLinkTable.of(1, 2, 3);
        assertTrue(table.locateElem(0).isEmpty());
        var res = table.locateElem(3);
        assertTrue(res.isPresent());
        assertEquals(3, res.get().getValue());
    }

    @Test
    void findElem() {
        SingleHeadLoopLinkTable<Integer> table = SingleHeadLoopLinkTable.of();
        assertTrue(table.findElem(0).isEmpty());
        assertTrue(table.findElem(-1).isEmpty());

        table = SingleHeadLoopLinkTable.of(1, 2, 3);
        var elem = table.findElem(3);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = table.findElem(4);
        assertTrue(elem.isEmpty());
    }

    @Test
    void listInsertFirst() {
        SingleHeadLoopLinkTable<Integer> table = SingleHeadLoopLinkTable.of();
        assertTrue(table.listInsertFirst(3));
        assertEquals("3", table.printList());

        assertTrue(table.listInsertFirst(1, 2));
        assertEquals("1, 2, 3", table.printList());

        assertFalse(table.listInsertFirst());
    }

    @Test
    void listInsert() {
        SingleHeadLoopLinkTable<Integer> table = SingleHeadLoopLinkTable.of();
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
        SingleHeadLoopLinkTable<Integer> table = SingleHeadLoopLinkTable.of();
        assertTrue(table.listInsertLast(1, 2));
        assertEquals("1, 2", table.printList());

        assertTrue(table.listInsertLast(3, 4));
        assertEquals("1, 2, 3, 4", table.printList());

        assertFalse(table.listInsertLast());
    }

    @Test
    void listDeleteFirst() {
        SingleHeadLoopLinkTable<Integer> table = SingleHeadLoopLinkTable.of();
        var node = table.listDeleteFirst();
        assertTrue(node.isEmpty());

        table = SingleHeadLoopLinkTable.of(1, 2, 3);
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
        SingleHeadLoopLinkTable<Integer> table = SingleHeadLoopLinkTable.of();
        var elem = table.listDelete(-1);
        assertTrue(elem.isEmpty());

        elem = table.listDelete(1);
        assertTrue(elem.isEmpty());

        table = SingleHeadLoopLinkTable.of(1, 2, 3, 4);
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
        SingleHeadLoopLinkTable<Integer> table = SingleHeadLoopLinkTable.of();
        var elem = table.listDeleteLast();
        assertTrue(elem.isEmpty());

        table = SingleHeadLoopLinkTable.of(1, 2, 3);
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
        SingleHeadLoopLinkTable<Integer> table = SingleHeadLoopLinkTable.of();
        assertEquals("", table.printList());

        table = SingleHeadLoopLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }

    @Test
    void empty() {
        SingleHeadLoopLinkTable<Integer> table = SingleHeadLoopLinkTable.of();
        assertTrue(table.empty());
    }

    @Test
    void destroyList() {
        var table = SingleHeadLoopLinkTable.of(1, 2, 3);
        table.destroyList();
        assertTrue(table.empty());
    }

    @Test
    void of() {
        var table = SingleHeadLoopLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }
}
