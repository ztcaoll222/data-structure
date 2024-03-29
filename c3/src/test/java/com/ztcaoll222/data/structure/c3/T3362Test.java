package com.ztcaoll222.data.structure.c3;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 第三章栈和队列综合题的单元测试
 */
@Flogger
class T3362Test {

    @Test
    void t1() {
        assertTrue(T3362.t1("([]{})"));
        assertTrue(T3362.t1("[([]{})]"));
        assertFalse(T3362.t1("[(]}"));
        assertFalse(T3362.t1("([(})"));
        assertFalse(T3362.t1("({)]"));
    }

    @Test
    void t2() {
        var res = T3362.t2("HSHSHHSHSSSH");
        assertEquals("IIOIIOIIIOIIOIOIOIOOOOOO", res.getK());
        assertEquals("SSSSSSHHHHHH", res.getV());
    }

    @Test
    void t3() {
        assertEquals(1, T3362.t3(0, 1));
        assertEquals(2, T3362.t3(1, 1));
        assertEquals(-20, T3362.t3(4, 1));
    }

    @Test
    void t4() {
        var res = T3362.t4("HKHKHKHKHKKKKHHHHHHHHHHK");
        assertEquals("K, K, K, K, H, K, K, K, K, H; K, H, H, H, H, H, H, H, H, H", res);
    }
}
