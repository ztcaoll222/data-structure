package com.ztcaoll222.data.structure.c3.queue;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 使用 tab 区分队满还是队空的循环顺序队列的单元测试
 */
@Flogger
class SeqTabLoopQueueTest {

    @Test
    void queueEmpty() {
        var queue = new SeqTabLoopQueue<Integer>();
        assertTrue(queue.queueEmpty());
    }

    @Test
    void enQueue() {
        var queue = new SeqTabLoopQueue<Integer>(2);
        assertTrue(queue.enQueue(1, 2));
        assertEquals("1, 2", queue.toString());

        assertFalse(queue.enQueue(3));
    }

    @Test
    void deQueue() {
        var queue = new SeqTabLoopQueue<Integer>();
        assertTrue(queue.enQueue(1, 2));

        var elem = queue.deQueue();
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());

        elem = queue.deQueue();
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());

        elem = queue.deQueue();
        assertTrue(elem.isEmpty());

        assertTrue(queue.queueEmpty());
    }

    @Test
    void getHead() {
        var queue = new SeqTabLoopQueue<Integer>();
        queue.enQueue(1, 2);

        var elem = queue.getHead();
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());

        queue = new SeqTabLoopQueue<>();
        elem = queue.getHead();
        assertTrue(elem.isEmpty());
    }

    @Test
    void testToString() {
        var queue = new SeqTabLoopQueue<Integer>();
        assertEquals("", queue.toString());

        assertTrue(queue.enQueue(1, 2));
        assertEquals("1, 2", queue.toString());
    }
}