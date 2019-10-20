package com.ztcaoll222.data.structure.c2;

import com.ztcaoll222.data.structure.c2.table.*;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 第二章链表综合题单元测试
 */
@Flogger
class T2372Test {

    @Test
    void t1() {
        var table = SingleLinkTable.of(1, 2, 3, 3, 3, 5, 6, 6, 7);
        T2372.t1(table, 3);
        assertEquals("1, 2, 5, 6, 6, 7", table.printList());
    }

    @Test
    void t2() {
        var table = SingleHeadLinkTable.of(1, 2, 3, 3, 3, 5, 6, 6, 7);
        T2372.t2(table, 3);
        assertEquals("1, 2, 5, 6, 6, 7", table.printList());
    }

    @Test
    void t3() {
        var table = SingleHeadLinkTable.of(1, 2, 3, 3, 3, 5, 6, 6, 7);
        assertEquals("7, 6, 6, 5, 3, 3, 3, 2, 1", T2372.t3(table));
    }

    @Test
    void t4() {
        var table = SingleHeadLinkTable.of(3, 5, 1, 6, 9, 2);
        Optional<Integer> min = T2372.t4(table);
        assertTrue(min.isPresent());
        assertEquals(1, min.get());
    }

    @Test
    void t5() {
        var table = SingleHeadLinkTable.of(1, 2, 3, 4, 5);
        T2372.t5(table);
        assertEquals("5, 4, 3, 2, 1", table.printList());
    }

    @Test
    void t6() {
        var table = SingleHeadLinkTable.of(5, 1, 3, 6, 6, 9, 8, 1);
        T2372.t6(table);
        assertEquals("1, 1, 3, 5, 6, 6, 8, 9", table.printList());
    }

    @Test
    void t7() {
        var table = SingleHeadLinkTable.of(5, 1, 3, 6, 6, 9, 8, 1);
        int sum = T2372.t7(table, 4, 7);
        assertEquals(3, sum);
        assertEquals("1, 3, 9, 8, 1", table.printList());
    }

    @Test
    void t8() {
        var table0 = SingleLinkTable.of(1);
        var table1 = SingleLinkTable.of(4, 5);
        var table3 = SingleLinkTable.of(7, 8, 9);
        table0.findElem(table0.length()).ifPresent(node -> node.setNext(table3.first));
        table1.findElem(table1.length()).ifPresent(node -> node.setNext(table3.first));
        var res = T2372.t8(table0, table1);
        var table4 = new SingleLinkTable<Integer>();
        assertTrue(res.isPresent());
        table4.first = res.get();
        assertEquals("7, 8, 9", table4.printList());

        table0 = SingleLinkTable.of(1);
        table1 = SingleLinkTable.of(4, 5);
        res = T2372.t8(table0, table1);
        assertTrue(res.isEmpty());
    }

    @Test
    void t9() {
        var table = SingleHeadLinkTable.of(5, 1, 3, 6, 6, 9, 8, 1);
        String res = T2372.t9(table);
        assertEquals("1, 1, 3, 5, 6, 6, 8, 9", res);
    }

    @Test
    void t10() {
        var table = SingleHeadLinkTable.of(1, 2, 3, 4, 5);
        var res = T2372.t10(table);
        assertEquals("1, 3, 5", res.getK().printList());
        assertEquals("2, 4", res.getV().printList());
    }

    @Test
    void t11() {
        var table = SingleHeadLinkTable.of(1, 2, 3, 4, 5);
        var res = T2372.t11(table);
        assertEquals("1, 3, 5", res.getK().printList());
        assertEquals("2, 4", res.getV().printList());
    }

    @Test
    void t12() {
        var table = SingleLinkTable.of(1, 2, 2, 3, 4, 4, 4, 5);
        T2372.t12(table);
        assertEquals("1, 2, 3, 4, 5", table.printList());
    }

    @Test
    void t13() {
        var table0 = SingleLinkTable.of(1, 3, 5, 7);
        var table1 = SingleLinkTable.of(2, 4, 6);
        var table = T2372.t13(table0, table1);
        assertEquals("7, 6, 5, 4, 3, 2, 1", table.printList());
    }

    @Test
    void t14() {
        var table0 = SingleHeadLinkTable.of(1, 3, 4, 7);
        var table1 = SingleHeadLinkTable.of(2, 3, 4);
        var table = T2372.t14(table0, table1);
        assertEquals("3, 4", table.printList());
        assertEquals("1, 3, 4, 7", table0.printList());
        assertEquals("2, 3, 4", table1.printList());
    }

    @Test
    void t15() {
        var table0 = SingleHeadLinkTable.of(1, 3, 4, 7, 8, 9);
        var table1 = SingleHeadLinkTable.of(2, 3, 5, 8);
        T2372.t15(table0, table1);
        assertEquals("3, 8", table0.printList());
    }

    @Test
    void t16() {
        var table0 = SingleLinkTable.of(1, 3, 4, 7, 8, 9);
        var table1 = SingleLinkTable.of(0);
        assertFalse(T2372.t16(table0, table1));

        table1 = SingleLinkTable.of(3, 4);
        assertTrue(T2372.t16(table0, table1));

        table0 = SingleLinkTable.of(3);
        assertFalse(T2372.t16(table0, table1));
    }

    @Test
    void t17() {
        var table = DoubleHeadLoopLinkTable.of(1, 2, 3, 2, 1);
        assertTrue(T2372.t17(table));

        table = DoubleHeadLoopLinkTable.of(1, 2, 2, 1);
        assertTrue(T2372.t17(table));

        table = DoubleHeadLoopLinkTable.of(1, 2, 2, 2);
        assertFalse(T2372.t17(table));
    }

    @Test
    void t18() {
        var table0 = SingleLoopLinkTable.of(1);
        var table1 = SingleLoopLinkTable.of(4, 5);

        T2372.t18(table0, table1);
        assertEquals("1, 4, 5", table0.printList());
    }

    @Test
    void t19() {
        var table = SingleHeadLoopLinkTable.of(1, 2, 3, 2, 1);
        assertEquals("11223", T2372.t19(table));
    }

    @Test
    void t20() {
        var table = DoubleLinkTableX.of(1, 2, 3);
        var elem = table.findElem(3);
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());
        assertEquals("1, 3, 2", table.printList());
    }
}
