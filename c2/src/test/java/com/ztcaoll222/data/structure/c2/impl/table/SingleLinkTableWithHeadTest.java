package com.ztcaoll222.data.structure.c2.impl.table;

import com.ztcaoll222.data.structure.c2.impl.node.SingleLinkTableNodeImpl;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Flogger
class SingleLinkTableWithHeadTest {

    @Test
    void locateElem() {
        SingleLinkTableWithHead<Integer> table = SingleLinkTableWithHead.of();
        assertTrue(table.locateElem(0).isEmpty());

        table = SingleLinkTableWithHead.of(1, 2, 3);
        assertTrue(table.locateElem(0).isEmpty());
        var res = table.locateElem(3);
        assertTrue(res.isPresent());
        assertEquals(3, res.get().getValue());
    }

    @Test
    void findElem() {
        SingleLinkTableWithHead<Integer> table = SingleLinkTableWithHead.of();
        assertTrue(table.findElem(0).isEmpty());

        table = SingleLinkTableWithHead.of(1, 2, 3);
        var elem = table.findElem(3);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = table.findElem(4);
        assertTrue(elem.isEmpty());
    }

    @Test
    void listInsert() {
        SingleLinkTableWithHead<Integer> table = SingleLinkTableWithHead.of();
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
        SingleLinkTableWithHead<Integer> table = SingleLinkTableWithHead.of();
        var elem = table.listDeleteLast();
        assertTrue(elem.isEmpty());

        table = SingleLinkTableWithHead.of(1, 2);
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
        SingleLinkTableWithHead<Integer> table = SingleLinkTableWithHead.of();
        var elem = table.listDelete(1);
        assertTrue(elem.isEmpty());

        table = SingleLinkTableWithHead.of(1, 2, 3);
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
        SingleLinkTableWithHead<Integer> table = SingleLinkTableWithHead.of();
        assertEquals("", table.printList());

        table = SingleLinkTableWithHead.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }

    @Test
    void getElem() {
        SingleLinkTableWithHead<Integer> table = SingleLinkTableWithHead.of();
        assertTrue(table.getElem(0).isEmpty());

        table = SingleLinkTableWithHead.of(1, 2, 3);
        var elem = table.getElem(3);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get());

        elem = table.getElem(4);
        assertTrue(elem.isEmpty());
    }

    @Test
    void listInsertLast() {
        SingleLinkTableWithHead<Integer> table = SingleLinkTableWithHead.of();
        table.listInsertLast(1);
        assertEquals("1", table.printList());

        table.listInsertLast(2);
        assertEquals("1, 2", table.printList());
    }

    @Test
    void empty() {
        SingleLinkTableWithHead<Integer> table = SingleLinkTableWithHead.of();
        assertTrue(table.empty());
    }

    @Test
    void destroyList() {
        var table = SingleLinkTableWithHead.of(1, 2, 3);
        table.destroyList();
        assertTrue(table.empty());
    }

    @Test
    void length() {
        SingleLinkTableWithHead<Integer> table = SingleLinkTableWithHead.of();
        assertEquals(0, table.length());

        table = SingleLinkTableWithHead.of(1, 2, 3);
        assertEquals(3, table.length());
    }

    @Test
    void setFirst() {
        var table = SingleLinkTableWithHead.of(1, 2, 3);
        table.setFirst(null);
        assertTrue(table.empty());

        table.setFirst(new SingleLinkTableNodeImpl<>(2));
        assertEquals("2", table.printList());

        table.setFirst(new SingleLinkTableNodeImpl<>(1));
        assertEquals("1, 2", table.printList());
    }

    @Test
    void getFirst() {
        SingleLinkTableWithHead<Integer> table = SingleLinkTableWithHead.of();
        assertNull(table.getFirst());

        table = SingleLinkTableWithHead.of(1, 2, 3);
        assertEquals(1, table.getFirst().getValue());
    }

    @Test
    void of() {
        var table = SingleLinkTableWithHead.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }
}
