package com.ztcaoll222.data.structure.c2.impl;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 双链表单元测试
 */
@Flogger
class DoubleLinkTableTest {

    @Test
    void length() {
        var table = DoubleLinkTable.of(1, 2, 3);
        assertEquals(3, table.length());
    }

    @Test
    void locateElem() {
        var table = DoubleLinkTable.of(1, 2, 3);
        var elem = table.locateElem(3);
        assertTrue(elem.isPresent());
        elem = table.locateElem(0);
        assertTrue(elem.isEmpty());
    }

    @Test
    void findElem() {
        var table = DoubleLinkTable.of(1, 2, 3);
        var elem = table.findElem(2);
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());
    }

    @Test
    void getElem() {
        var table = DoubleLinkTable.of(1, 2, 3);
        var value = table.getElem(2);
        assertTrue(value.isPresent());
        assertEquals(2, value.get());
    }

    @Test
    void listInsert() {
        var table = DoubleLinkTable.of(1, 2, 3);
        assertTrue(table.listInsert(1, 0));
        assertEquals("1, 0, 2, 3", table.printList());
    }

    @Test
    void listDeleteLast() {
        var table = DoubleLinkTable.of(1, 2, 3);
        var elem = table.listDeleteLast();
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());
        assertEquals("1, 2", table.printList());
    }

    @Test
    void listDelete() {
        var table = DoubleLinkTable.of(1, 2, 3);
        var elem = table.listDelete(2);
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());
        assertEquals("1, 3", table.printList());
    }

    @Test
    void printList() {
        var table = DoubleLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }

    @Test
    void empty() {
        var table = new DoubleLinkTable<Integer>();
        assertTrue(table.empty());
    }

    @Test
    void destroyList() {
        var table = DoubleLinkTable.of(1, 2, 3);
        assertFalse(table.empty());
        table.destroyList();
        assertTrue(table.empty());
    }

    @Test
    void of() {
        var table = DoubleLinkTable.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }
}
