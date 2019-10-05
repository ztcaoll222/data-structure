package com.ztcaoll222.data.structure.c2;

import com.ztcaoll222.data.structure.c2.impl.SingleLinkTable;
import com.ztcaoll222.data.structure.c2.impl.SingleLinkTableWithHead;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        var table = SingleLinkTableWithHead.of(1, 2, 3, 3, 3, 5, 6, 6, 7);
        T2372.t2(table, 3);
        assertEquals("1, 2, 5, 6, 6, 7", table.printList());
    }

    @Test
    void t3() {
        var table = SingleLinkTableWithHead.of(1, 2, 3, 3, 3, 5, 6, 6, 7);
        assertEquals("7, 6, 6, 5, 3, 3, 3, 2, 1", T2372.t3(table));
    }

    @Test
    void t4() {
        var table = SingleLinkTableWithHead.of(3, 5, 1, 6, 9, 2);
        Optional<Integer> min = T2372.t4(table);
        assertTrue(min.isPresent());
        assertEquals(1, min.get());
    }

    @Test
    void t5() {
        var table = SingleLinkTableWithHead.of(1, 2, 3, 4, 5);
        T2372.t5(table);
        assertEquals("5, 4, 3, 2, 1", table.printList());
    }

    @Test
    void t6() {
        var table = SingleLinkTableWithHead.of(5, 1, 3, 6, 6, 9, 8, 1);
        T2372.t6(table);
        assertEquals("1, 1, 3, 5, 6, 6, 8, 9", table.printList());
    }

    @Test
    void t7() {
        var table = SingleLinkTableWithHead.of(5, 1, 3, 6, 6, 9, 8, 1);
        int sum = T2372.t7(table, 4, 7);
        assertEquals(3, sum);
        assertEquals("5, 1, 3, 9, 8, 1", table.printList());
    }
}
