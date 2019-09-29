package com.ztcaoll222.data.structure.c2.impl;

import com.ztcaoll222.data.structure.c2.Elem;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Flogger
class SequenceTableTest {

    @org.junit.jupiter.api.Test
    void length() {
        SequenceTable table = new SequenceTable(16);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        assertEquals(4, table.length());
    }

    @org.junit.jupiter.api.Test
    void locateElem() {
        SequenceTable table = new SequenceTable(16);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        assertTrue(table.locateElem("5").isEmpty());

        Optional<Elem> elem = table.locateElem("3");
        assertTrue(elem.isPresent());
        assertEquals("3", elem.get().getValue());
    }

    @org.junit.jupiter.api.Test
    void getElem() {
        SequenceTable table = new SequenceTable(16);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        assertTrue(table.getElem(5).isEmpty());

        Optional<String> value = table.getElem(1);
        assertTrue(value.isPresent());
        assertEquals("1", value.get());
    }

    @org.junit.jupiter.api.Test
    void listInsert() {
        SequenceTable table = new SequenceTable(4);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        assertEquals(4, table.length());

        table.listInsert(1, "5");
        assertEquals("0, 5, 1, 2, 3", table.printList());
        assertEquals(8, table.getMaxSize());
    }

    @org.junit.jupiter.api.Test
    void listDelete() {
        SequenceTable table = new SequenceTable(16);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        Optional<Elem<String>> elem = table.listDelete(1);
        assertTrue(elem.isPresent());
        assertEquals("1", elem.get().getValue());
        assertEquals(3, table.length());

        assertTrue(table.getElem(4).isEmpty());
    }

    @org.junit.jupiter.api.Test
    void printList() {
        SequenceTable table = new SequenceTable(16);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        assertEquals("0, 1, 2, 3", table.printList());
    }

    @org.junit.jupiter.api.Test
    void empty() {
        SequenceTable table = new SequenceTable(16);
        assertTrue(table.empty());
    }

    @org.junit.jupiter.api.Test
    void destroyList() {
        SequenceTable table = new SequenceTable(16);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        table.destroyList();

        assertTrue(table.empty());
    }

    @org.junit.jupiter.api.Test
    void t22321() {
        SequenceTable table1 = new SequenceTable(16);
        table1.listInsert(0, "2");
        table1.listInsert(1, "0");
        table1.listInsert(2, "1");
        table1.listInsert(3, "3");

        Optional<String> s0 = table1.t22321();
        assertTrue(s0.isPresent());
        assertEquals("0", s0.get());
        assertEquals("2, 3, 1", table1.printList());

        SequenceTable table0 = new SequenceTable(16);
        table0.listInsert(0, "2");
        table0.listInsert(1, "1");
        table0.listInsert(2, "0");
        table0.listInsert(3, "3");

        table0.t22321();
        assertEquals("2, 1, 3", table0.printList());

        SequenceTable table2 = new SequenceTable(16);
        table2.listInsert(0, "0");
        table2.listInsert(1, "2");
        table2.listInsert(2, "1");
        table2.listInsert(3, "3");

        table2.t22321();
        assertEquals("3, 2, 1", table2.printList());
    }

    @Test
    void t22322() {
        SequenceTable table = new SequenceTable(16);
        table.listInsert(0, "0");
        table.listInsert(1, "2");
        table.listInsert(2, "1");
        table.listInsert(3, "3");

        table.t22322();

        assertEquals("3, 1, 2, 0", table.printList());
    }
}
