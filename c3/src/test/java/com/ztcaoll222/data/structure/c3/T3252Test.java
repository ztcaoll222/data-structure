package com.ztcaoll222.data.structure.c3;

import com.ztcaoll222.data.structure.c3.queue.SeqLoopQueue;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 第三章队列综合题的单元测试
 */
@Flogger
class T3252Test {

    @Test
    void t2() {
        var queue = new SeqLoopQueue<Integer>();
        queue.enQueue(5, 4, 3, 2, 1);
        T3252.t2(queue);
        assertEquals("1, 2, 3, 4, 5", queue.toString());
    }
}
