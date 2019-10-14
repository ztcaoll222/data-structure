package com.ztcaoll222.data.structure.c2.impl.table;

import com.ztcaoll222.data.structure.c2.impl.node.SingleLinkTableNodeImpl;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 单链表单元测试
 */
@Flogger
class SingleLinkTableTest {

    @Test
    void locateElem() {
        SingleLinkTable<Integer> table = SingleLinkTable.of();
        assertTrue(table.locateElem(0).isEmpty());

        table = SingleLinkTable.of(1, 2, 3);
        assertTrue(table.locateElem(0).isEmpty());
        var res = table.locateElem(3);
        assertTrue(res.isPresent());
        assertEquals(3, res.get().getValue());
    }

    @Test
    void findElem() {
        SingleLinkTable<Integer> table = SingleLinkTable.of();
        assertTrue(table.findElem(0).isEmpty());

        table = SingleLinkTable.of(1, 2, 3);
        var elem = table.findElem(3);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = table.findElem(4);
        assertTrue(elem.isEmpty());
    }

    @Test
    void listInsert() {
        SingleLinkTable<Integer> table = SingleLinkTable.of();
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
        SingleLinkTable<Integer> table = SingleLinkTable.of();
        var elem = table.listDeleteLast();
        assertTrue(elem.isEmpty());

        table = SingleLinkTable.of(1, 2);
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
        SingleLinkTable<Integer> table = SingleLinkTable.of();
        var elem = table.listDelete(1);
        assertTrue(elem.isEmpty());

        table = SingleLinkTable.of(1, 2, 3);
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
        SingleLinkTable<Integer> table = SingleLinkTable.of();
        assertEquals("", table.printList());

        table = SingleLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }

    @Test
    void getElem() {
        SingleLinkTable<Integer> table = SingleLinkTable.of();
        assertTrue(table.getElem(0).isEmpty());

        table = SingleLinkTable.of(1, 2, 3);
        var elem = table.getElem(3);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get());

        elem = table.getElem(4);
        assertTrue(elem.isEmpty());
    }

    @Test
    void listInsertLast() {
        SingleLinkTable<Integer> table = SingleLinkTable.of();
        table.listInsertLast(1);
        assertEquals("1", table.printList());

        table.listInsertLast(2);
        assertEquals("1, 2", table.printList());
    }

    @Test
    void empty() {
        SingleLinkTable<Integer> table = SingleLinkTable.of();
        assertTrue(table.empty());
    }

    @Test
    void destroyList() {
        var table = SingleLinkTable.of(1, 2, 3);
        table.destroyList();
        assertTrue(table.empty());
    }

    @Test
    void length() {
        SingleLinkTable<Integer> table = SingleLinkTable.of();
        assertEquals(0, table.length());

        table = SingleLinkTable.of(1, 2, 3);
        assertEquals(3, table.length());
    }

    @Test
    void setFirst() {
        var table = SingleLinkTable.of(1, 2, 3);
        table.setFirst(null);
        assertTrue(table.empty());

        table.setFirst(new SingleLinkTableNodeImpl<>(2));
        assertEquals("2", table.printList());

        table.setFirst(new SingleLinkTableNodeImpl<>(1));
        assertEquals("1, 2", table.printList());
    }

    @Test
    void getFirst() {
        SingleLinkTable<Integer> table = SingleLinkTable.of();
        assertNull(table.getFirst());

        table = SingleLinkTable.of(1, 2, 3);
        assertEquals(1, table.getFirst().getValue());
    }

    @Test
    void of() {
        var table = SingleLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }
}
