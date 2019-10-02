package com.ztcaoll222.data.structure.c2.impl;

import com.ztcaoll222.data.structure.c2.Elem;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Flogger
class SequenceTableTest {

    @Test
    void length() {
        SequenceTable<String> table = SequenceTable.of("0", "1", "2", "3");
        assertEquals(4, table.length());
    }

    @Test
    void locateElem() {
        SequenceTable<String> table = SequenceTable.of("0", "1", "2", "3");
        assertTrue(table.locateElem("5").isEmpty());
        Optional<Elem<String>> elem = table.locateElem("3");
        assertTrue(elem.isPresent());
        assertEquals("3", elem.get().getValue());
    }

    @Test
    void getElem() {
        SequenceTable<String> table = SequenceTable.of("0", "1", "2", "3");
        assertTrue(table.getElem(5).isEmpty());
        Optional<String> value = table.getElem(1);
        assertTrue(value.isPresent());
        assertEquals("0", value.get());
    }

    @Test
    void listInsert() {
        SequenceTable<String> table = SequenceTable.of("0", "1", "2", "3");
        assertEquals(4, table.length());
        table.listInsert(1, "5");
        assertEquals("0, 5, 1, 2, 3", table.printList());
        assertEquals(8, table.maxSize);
    }

    @Test
    void listDelete() {
        SequenceTable<String> table = SequenceTable.of("0", "1", "2", "3");
        var elem = table.listDelete(1);
        assertTrue(elem.isPresent());
        assertEquals("0", elem.get().getValue());
        assertEquals("1, 2, 3", table.printList());
    }

    @Test
    void printList() {
        SequenceTable<String> table = SequenceTable.of("0", "1", "2", "3");
        assertEquals("0, 1, 2, 3", table.printList());
    }

    @Test
    void empty() {
        SequenceTable<String> table = new SequenceTable<>(16);
        assertTrue(table.empty());
    }

    @Test
    void destroyList() {
        SequenceTable<String> table = SequenceTable.of("0", "1", "2", "3");
        table.destroyList();
        assertTrue(table.empty());
    }

    @Test
    void listDeleteLast() {
        SequenceTable<Integer> table =  SequenceTable.of(0, 4, 2, 1, 3);
        var elem = table.listDeleteLast();
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());
        assertEquals("0, 4, 2, 1", table.printList());
    }
}
