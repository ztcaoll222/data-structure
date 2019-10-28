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
        var queue = new SeqTabLoopQueue<Integer>(0);
        assertFalse(queue.queueEmpty());

        queue = new SeqTabLoopQueue<>();
        assertTrue(queue.queueEmpty());
    }

    @Test
    void queueOverFlow() {
        var queue = new SeqTabLoopQueue<Integer>(0);
        assertTrue(queue.queueOverFlow());

        queue = new SeqTabLoopQueue<>(1);
        assertFalse(queue.queueOverFlow());
        assertTrue(queue.enQueue(1));
        assertTrue(queue.queueOverFlow());
    }

    @Test
    void length() {
        var queue = new SeqTabLoopQueue<Integer>(3);
        assertEquals(0, queue.length());

        queue.enQueue(1, 2);
        assertEquals(2, queue.length());

        assertTrue(queue.deQueue().isPresent());
        queue.enQueue(3);
        assertEquals(2, queue.length());
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

        var value = queue.deQueue();
        assertTrue(value.isPresent());
        assertEquals(1, value.get());

        value = queue.deQueue();
        assertTrue(value.isPresent());
        assertEquals(2, value.get());

        value = queue.deQueue();
        assertTrue(value.isEmpty());

        assertTrue(queue.queueEmpty());
    }

    @Test
    void getHead() {
        var queue = new SeqTabLoopQueue<Integer>();
        queue.enQueue(1, 2);

        var value = queue.getHead();
        assertTrue(value.isPresent());
        assertEquals(1, value.get());

        queue = new SeqTabLoopQueue<>();
        value = queue.getHead();
        assertTrue(value.isEmpty());
    }

    @Test
    void testToString() {
        var queue = new SeqTabLoopQueue<Integer>();
        assertEquals("", queue.toString());

        assertTrue(queue.enQueue(1, 2));
        assertEquals("1, 2", queue.toString());
    }
}
