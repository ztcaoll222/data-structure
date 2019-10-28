package com.ztcaoll222.data.structure.c3.queue;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 顺序队列的单元测试
 */
@Flogger
class SeqDoNotUseQueueTest {

    @Test
    void queueEmpty() {
        var queue = new SeqDoNotUseQueue<Integer>(0);
        assertFalse(queue.queueEmpty());

        queue = new SeqDoNotUseQueue<>();
        assertTrue(queue.queueEmpty());
    }

    @Test
    void queueOverFlow() {
        var queue = new SeqDoNotUseQueue<Integer>(0);
        assertTrue(queue.queueOverFlow());

        queue = new SeqDoNotUseQueue<>(1);
        assertFalse(queue.queueOverFlow());
        assertTrue(queue.enQueue(1));
        assertTrue(queue.queueOverFlow());
    }

    @Test
    void length() {
        var queue = new SeqDoNotUseQueue<Integer>(3);
        assertEquals(0, queue.length());

        queue.enQueue(1, 2);
        assertEquals(2, queue.length());

        assertTrue(queue.deQueue().isPresent());
        queue.enQueue(3);
        assertEquals(2, queue.length());
    }

    @Test
    void enQueue() {
        var queue = new SeqDoNotUseQueue<Integer>(2);
        assertTrue(queue.enQueue(1, 2));
        assertFalse(queue.enQueue(3));
        assertEquals("1, 2", queue.toString());
    }

    @Test
    void deQueue() {
        var queue = new SeqDoNotUseQueue<Integer>();
        queue.enQueue(1, 2);

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
        var queue = new SeqDoNotUseQueue<Integer>();
        queue.enQueue(1, 2);

        var value = queue.getHead();
        assertTrue(value.isPresent());
        assertEquals(1, value.get());

        queue = new SeqDoNotUseQueue<>();
        value = queue.getHead();
        assertTrue(value.isEmpty());
    }

    @Test
    void testToString() {
        var queue = new SeqDoNotUseQueue<Integer>();
        assertEquals("", queue.toString());

        queue.enQueue(1, 2);
        assertEquals("1, 2", queue.toString());
    }
}
