package com.ztcaoll222.data.structure.c2;

import com.ztcaoll222.data.structure.c2.table.SequenceTable;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 第二章顺序表综合题单元测试
 */
@Flogger
class T2232Test {

    @Test
    void t1() {
        SequenceTable<Integer> table0 = SequenceTable.of(2, 1, 0, 3);
        T2232.t1(table0);
        assertEquals("2, 1, 3", table0.printList());

        SequenceTable<Integer> table1 = SequenceTable.of(2, 0, 1, 3);
        Optional<Integer> s0 = T2232.t1(table1);
        assertTrue(s0.isPresent());
        assertEquals(0, s0.get());
        assertEquals("2, 3, 1", table1.printList());

        SequenceTable<Integer> table2 = SequenceTable.of(0, 2, 1, 3);
        T2232.t1(table2);
        assertEquals("3, 2, 1", table2.printList());
    }

    @Test
    void t2() {
        SequenceTable<String> table = SequenceTable.of("0", "2", "1", "3");
        T2232.t2(table);
        assertEquals("3, 1, 2, 0", table.printList());
    }

    @Test
    void t3() {
        SequenceTable<String> table = SequenceTable.of("0", "2", "2", "3", "2", "5");
        int sum = T2232.t3(table, "2");
        assertEquals(3, sum);
        assertEquals("0, 3, 5", table.printList());
    }

    @Test
    void t4() {
        SequenceTable<Integer> table = SequenceTable.of(0, 1, 2, 3, 4);
        int sum = T2232.t4(table, 1, 3);
        assertEquals(3, sum);
        assertEquals("0, 4", table.printList());
    }

    @Test
    void t5() {
        SequenceTable<Integer> table = SequenceTable.of(0, 4, 2, 1, 3);
        int sum = T2232.t5(table, 1, 3);
        assertEquals(3, sum);
        assertEquals("0, 4", table.printList());
    }

    @Test
    void t6() {
        SequenceTable<Integer> table = SequenceTable.of(0, 1, 1, 1, 2, 2, 3, 4, 4);
        int sum = T2232.t6(table);
        assertEquals(4, sum);
        assertEquals("0, 1, 2, 3, 4", table.printList());
    }

    @Test
    void t7() {
        SequenceTable<Integer> table0 = SequenceTable.of(0, 1, 2, 3, 4);
        SequenceTable<Integer> table1 = SequenceTable.of(0, 2, 3);
        SequenceTable<Integer> newTable = T2232.t7(table0, table1);
        assertEquals("0, 0, 1, 2, 2, 3, 3, 4", newTable.printList());

        table0 = SequenceTable.of(0, 1, 2, 3);
        table1 = SequenceTable.of(0, 2, 3);
        newTable = T2232.t7(table0, table1);
        assertEquals("0, 0, 1, 2, 2, 3, 3", newTable.printList());
    }

    @Test
    void t8() {
        SequenceTable<Integer> table = SequenceTable.of(0, 1, 1, 1, 2, 2, 3, 4, 4);
        assertTrue(T2232.t8(table, 6));
        assertEquals("3, 4, 4, 0, 1, 1, 1, 2, 2", table.printList());
    }

    @Test
    void t9() {
        SequenceTable<Integer> table0 = SequenceTable.of(0, 1, 3, 4);
        T2232.t9(table0, 3);
        assertEquals("0, 1, 4, 3", table0.printList());

        SequenceTable<Integer> table1 = SequenceTable.of(0, 1, 3, 4);
        T2232.t9(table1, 2);
        assertEquals("0, 1, 2, 3, 4", table1.printList());
    }

    @Test
    void t10() {
        SequenceTable<Integer> table = SequenceTable.of(0, 1, 1, 1, 2, 2, 3, 4, 4);
        assertTrue(T2232.t10(table, 6));
        assertEquals("3, 4, 4", table.printList());
    }

    @Test
    void t11() {
        SequenceTable<Integer> table0 = SequenceTable.of(0, 2, 4, 6, 8);
        SequenceTable<Integer> table1 = SequenceTable.of(1, 3, 5, 7, 9);
        Optional<Double> value = T2232.t11(table0, table1);
        assertTrue(value.isPresent());
        assertEquals(4.5, value.get());

        table0 = SequenceTable.of(11, 13, 15, 17, 19);
        table1 = SequenceTable.of(2, 4, 6, 8, 20);
        value = T2232.t11(table0, table1);
        assertTrue(value.isPresent());
        assertEquals(12, value.get());
    }

    @Test
    void t12() {
        SequenceTable<Integer> table0 = SequenceTable.of(0, 5, 5, 3, 5, 7, 5, 5);
        Optional<Integer> main = T2232.t12(table0);
        assertTrue(main.isPresent());
        assertEquals(5, main.get());

        SequenceTable<Integer> table1 = SequenceTable.of(0, 5, 5, 3, 5, 1, 5, 7);
        main = T2232.t12(table1);
        assertTrue(main.isEmpty());
    }

    @Test
    void t13() {
        SequenceTable<Integer> table0 = SequenceTable.of(-5, 3, 2, 3);
        int i = T2232.t13(table0);
        assertEquals(1, i);

        SequenceTable<Integer> table1 = SequenceTable.of(1, 2, 3);
        i = T2232.t13(table1);
        assertEquals(4, i);
    }
}
