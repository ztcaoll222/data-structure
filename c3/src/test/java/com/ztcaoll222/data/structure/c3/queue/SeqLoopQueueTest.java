package com.ztcaoll222.data.structure.c3.queue;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 循环顺序队列的单元测试
 */
@Flogger
class SeqLoopQueueTest {

    @Test
    void queueEmpty() {
        var queue = new SeqLoopQueue<Integer>();
        assertTrue(queue.queueEmpty());
    }

    @Test
    void length() {
        var queue = new SeqLoopQueue<Integer>(3);
        assertEquals(0, queue.length());

        queue.enQueue(1, 2);
        assertEquals(2, queue.length());

        assertTrue(queue.deQueue().isPresent());
        queue.enQueue(3);
        assertEquals(2, queue.length());
    }

    @Test
    void enQueue() {
        var queue = new SeqLoopQueue<Integer>(3);
        assertTrue(queue.enQueue(1, 2));
        assertEquals("1, 2", queue.toString());

        assertFalse(queue.enQueue(3));
    }

    @Test
    void deQueue() {
        var queue = new SeqLoopQueue<Integer>();
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
        var queue = new SeqLoopQueue<Integer>();
        queue.enQueue(1, 2);

        var elem = queue.getHead();
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());

        queue = new SeqLoopQueue<>();
        elem = queue.getHead();
        assertTrue(elem.isEmpty());
    }

    @Test
    void testToString() {
        var queue = new SeqLoopQueue<Integer>();
        assertEquals("", queue.toString());

        assertTrue(queue.enQueue(1, 2));
        assertEquals("1, 2", queue.toString());
    }
}
