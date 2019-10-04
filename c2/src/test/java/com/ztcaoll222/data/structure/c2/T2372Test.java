package com.ztcaoll222.data.structure.c2;

import com.ztcaoll222.data.structure.c2.impl.SingleLinkTable;
import com.ztcaoll222.data.structure.c2.impl.SingleLinkTableWithHead;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

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
        var table = SingleLinkTableWithHead.of(1, 2, 3, 3, 3, 5, 6, 6, 7);
        T2372.t1(table, 3);
        assertEquals("1, 2, 5, 6, 6, 7", table.printList());
    }
}
