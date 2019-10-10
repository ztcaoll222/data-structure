package com.ztcaoll222.data.structure.c2.impl.table;

import com.ztcaoll222.data.structure.c2.impl.table.SingleLinkTableWithHead;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Flogger
class SingleLinkTableWithHeadTest {

    @Test
    void length() {
        var table = SingleLinkTableWithHead.of(1, 2, 3);
        assertEquals(3, table.length());
    }

    @Test
    void locateElem() {
        var table = SingleLinkTableWithHead.of(1, 2, 3);
        var elem = table.locateElem(3);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = table.locateElem(4);
        assertTrue(elem.isEmpty());
    }

    @Test
    void getElem() {
        var table = SingleLinkTableWithHead.of(1, 2, 3);
        var value = table.getElem(3);
        assertTrue(value.isPresent());
        assertEquals(3, value.get());
    }

    @Test
    void listInsert() {
        var table = SingleLinkTableWithHead.of(1, 2, 3);
        assertTrue(table.listInsert(1, 0));
        assertEquals("1, 0, 2, 3", table.printList());
    }

    @Test
    void listDeleteLast() {
        var table = SingleLinkTableWithHead.of(1, 2, 3);
        var elem = table.listDeleteLast();
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());
        assertEquals("1, 2", table.printList());
    }

    @Test
    void listDelete() {
        var table = SingleLinkTableWithHead.of(1, 2, 3);
        var elem = table.listDelete(2);
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());
        assertEquals("1, 3", table.printList());
    }

    @Test
    void printList() {
        var table = SingleLinkTableWithHead.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }

    @Test
    void empty() {
        var table = SingleLinkTableWithHead.of();
        assertTrue(table.empty());
    }

    @Test
    void destroyList() {
        var table = SingleLinkTableWithHead.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
        table.destroyList();
        assertTrue(table.empty());
    }

    @Test
    void of() {
        var table = SingleLinkTableWithHead.of(1, 2, 3);
        assertEquals("1, 2, 3", table.printList());
    }
}
