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
        assertEquals(8, table.maxSize);
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
    void listDeleteLast() {
        SequenceTable<Integer> table = new SequenceTable<>(16);
        table.listInsert(0);
        table.listInsert(4);
        table.listInsert(2);
        table.listInsert(1);
        table.listInsert(3);

        Elem<Integer> elem = table.listDeleteLast();
        assertEquals(3, elem.getValue());
        assertEquals("0, 4, 2, 1", table.printList());
    }
}
