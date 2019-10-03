package com.ztcaoll222.data.structure.c2.impl;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 单链表单元测试
 */
@Flogger
class SingleLinkTableTest {

    @Test
    void length() {
        var table = SingleLinkTable.of(1, 2, 3);
        assertEquals(3, table.length());
    }

    @Test
    void locateElem() {
        var table = SingleLinkTable.of(1, 2, 3);
        var elem = table.locateElem(3);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = table.locateElem(4);
        assertTrue(elem.isEmpty());
    }

    @Test
    void getElem() {
        var table = SingleLinkTable.of(1, 2, 3);
        var value = table.getElem(3);
        assertTrue(value.isPresent());
        assertEquals(3, value.get());
    }

    @Test
    void listInsert() {
        var table = SingleLinkTable.of(1, 2, 3);
        assertTrue(table.listInsert(1, 0));
        assertEquals("1, 0, 2, 3", table.printList());
    }

    @Test
    void listDeleteLast() {
        var table = SingleLinkTable.of(1, 2, 3);
        var elem = table.listDeleteLast();
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());
        assertEquals("1, 2", table.printList());
    }

    @Test
    void listDelete() {
        var table = SingleLinkTable.of(1, 2, 3);
        var elem = table.listDelete(2);
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());
        assertEquals("1, 3", table.printList());
    }

    @Test
    void printList() {
        var table = SingleLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }

    @Test
    void empty() {
        var table = new SingleLinkTable<Integer>();
        assertTrue(table.empty());
    }

    @Test
    void destroyList() {
        var table = SingleLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
        table.destroyList();
        assertTrue(table.empty());
    }

    @Test
    void of() {
        var table = SingleLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }
}
