package com.ztcaoll222.data.structure.c3;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 第三章栈综合题的单元测试
 */
@Flogger
class T3152Test {

    @Test
    void t1() {
        assertTrue(T3152.t3_2('I', 'O', 'I', 'I', 'O', 'I', 'O', 'O'));
        assertFalse(T3152.t3_2('I', 'O', 'O', 'I', 'O', 'I', 'I', 'O'));
        assertFalse(T3152.t3_2('I', 'I', 'I', 'O', 'I', 'O', 'I', 'O'));
        assertTrue(T3152.t3_2('I', 'I', 'I', 'O', 'O', 'I', 'O', 'O'));
    }
}
