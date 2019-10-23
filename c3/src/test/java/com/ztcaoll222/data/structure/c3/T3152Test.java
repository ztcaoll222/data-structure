package com.ztcaoll222.data.structure.c3;

import com.ztcaoll222.data.structure.c2.table.SingleLinkTable;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 第三章栈综合题的单元测试
 */
@Flogger
class T3152Test {

    @Test
    void t3_2() {
        assertTrue(T3152.t3_2('I', 'O', 'I', 'I', 'O', 'I', 'O', 'O'));
        assertFalse(T3152.t3_2('I', 'O', 'O', 'I', 'O', 'I', 'I', 'O'));
        assertFalse(T3152.t3_2('I', 'I', 'I', 'O', 'I', 'O', 'I', 'O'));
        assertTrue(T3152.t3_2('I', 'I', 'I', 'O', 'O', 'I', 'O', 'O'));
    }

    @Test
    void t4() {
        var table = SingleLinkTable.of(1, 2, 3, 2, 1);
        assertTrue(T3152.t4(table));

        table = SingleLinkTable.of(1, 2, 2, 1);
        assertTrue(T3152.t4(table));

        table = SingleLinkTable.of(1, 2, 2, 2);
        assertFalse(T3152.t4(table));
    }
}
