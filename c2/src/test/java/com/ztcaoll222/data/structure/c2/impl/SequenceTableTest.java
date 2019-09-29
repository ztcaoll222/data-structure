package com.ztcaoll222.data.structure.c2.impl;

import com.ztcaoll222.data.structure.c2.Elem;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Flogger
class SequenceTableTest {

    @Test
    void length() {
        SequenceTable<String> table = new SequenceTable<>(16);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        assertEquals(4, table.length());
    }

    @Test
    void locateElem() {
        SequenceTable<String> table = new SequenceTable<>(16);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        assertTrue(table.locateElem("5").isEmpty());

        Optional<Elem<String>> elem = table.locateElem("3");
        assertTrue(elem.isPresent());
        assertEquals("3", elem.get().getValue());
    }

    @Test
    void getElem() {
        SequenceTable<String> table = new SequenceTable<>(16);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        assertTrue(table.getElem(5).isEmpty());

        Optional<String> value = table.getElem(1);
        assertTrue(value.isPresent());
        assertEquals("1", value.get());
    }

    @Test
    void listInsert() {
        SequenceTable<String> table = new SequenceTable<>(4);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        assertEquals(4, table.length());

        table.listInsert(1, "5");
        assertEquals("0, 5, 1, 2, 3", table.printList());
        assertEquals(8, table.getMaxSize());
    }

    @Test
    void listDelete() {
        SequenceTable<String> table = new SequenceTable<>(16);
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

    @Test
    void printList() {
        SequenceTable<String> table = new SequenceTable<>(16);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        assertEquals("0, 1, 2, 3", table.printList());
    }

    @Test
    void empty() {
        SequenceTable<String> table = new SequenceTable<>(16);
        assertTrue(table.empty());
    }

    @Test
    void destroyList() {
        SequenceTable<String> table = new SequenceTable<>(16);
        table.listInsert(0, "0");
        table.listInsert(1, "1");
        table.listInsert(2, "2");
        table.listInsert(3, "3");

        table.destroyList();

        assertTrue(table.empty());
    }

    @Test
    void t22321() {
        SequenceTable<String> table1 = new SequenceTable<>(16);
        table1.listInsert(0, "2");
        table1.listInsert(1, "0");
        table1.listInsert(2, "1");
        table1.listInsert(3, "3");

        Optional<String> s0 = table1.t22321();
        assertTrue(s0.isPresent());
        assertEquals("0", s0.get());
        assertEquals("2, 3, 1", table1.printList());

        SequenceTable<String> table0 = new SequenceTable<>(16);
        table0.listInsert(0, "2");
        table0.listInsert(1, "1");
        table0.listInsert(2, "0");
        table0.listInsert(3, "3");

        table0.t22321();
        assertEquals("2, 1, 3", table0.printList());

        SequenceTable<String> table2 = new SequenceTable<>(16);
        table2.listInsert(0, "0");
        table2.listInsert(1, "2");
        table2.listInsert(2, "1");
        table2.listInsert(3, "3");

        table2.t22321();
        assertEquals("3, 2, 1", table2.printList());
    }

    @Test
    void t22322() {
        SequenceTable<String> table = new SequenceTable<>(16);
        table.listInsert(0, "0");
        table.listInsert(1, "2");
        table.listInsert(2, "1");
        table.listInsert(3, "3");

        table.t22322();

        assertEquals("3, 1, 2, 0", table.printList());
    }

    @Test
    void t22323() {
        SequenceTable<String> table = new SequenceTable<>(16);
        table.listInsert(0, "0");
        table.listInsert(1, "2");
        table.listInsert(2, "2");
        table.listInsert(3, "3");
        table.listInsert(4, "2");
        table.listInsert(5, "5");

        int sum = table.t22323("2");

        assertEquals(3, sum);
        assertEquals("0, 3, 5", table.printList());
    }
}
