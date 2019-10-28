package com.ztcaoll222.data.structure.c3.queue;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 循环顺序双端队列的单元测试
 */
@Flogger
class SeqLoopDeQueueTest {

    @Test
    void getTail() {
        var queue = new SeqLoopDeQueue<Integer>();

        var value = queue.getTail();
        assertTrue(value.isEmpty());

        queue.enQueue(1, 2, 3);
        value = queue.getTail();
        assertTrue(value.isPresent());
        assertEquals(3, value.get());
    }

    @Test
    void enQueueHead() {
        var queue = new SeqLoopDeQueue<Integer>(3);

        assertTrue(queue.enQueueHead(2, 1));
        assertEquals("1, 2", queue.toString());

        assertFalse(queue.enQueueHead(3));
    }

    @Test
    void deQueueTail() {
        var queue = new SeqLoopDeQueue<Integer>();
        assertTrue(queue.enQueue(1, 2, 3));

        var value = queue.deQueueTail();
        assertTrue(value.isPresent());
        assertEquals(3, value.get());

        value = queue.deQueueTail();
        assertTrue(value.isPresent());
        assertEquals(2, value.get());

        value = queue.deQueueTail();
        assertTrue(value.isPresent());
        assertEquals(1, value.get());

        value = queue.deQueueTail();
        assertFalse(value.isPresent());
    }

    @Test
    void queueEmpty() {
        var queue = new SeqLoopDeQueue<Integer>(0);
        assertFalse(queue.queueEmpty());

        queue = new SeqLoopDeQueue<>();
        assertTrue(queue.queueEmpty());
    }

    @Test
    void queueOverFlow() {
        var queue = new SeqLoopDeQueue<Integer>(0);
        assertTrue(queue.queueOverFlow());

        queue = new SeqLoopDeQueue<>(2);
        assertFalse(queue.queueOverFlow());
        assertTrue(queue.enQueue(1));
        assertTrue(queue.queueOverFlow());
    }

    @Test
    void length() {
        var queue = new SeqLoopDeQueue<Integer>(3);
        assertEquals(0, queue.length());

        queue.enQueue(1, 2);
        assertEquals(2, queue.length());

        assertTrue(queue.deQueue().isPresent());
        queue.enQueue(3);
        assertEquals(2, queue.length());
    }

    @Test
    void enQueue() {
        var queue = new SeqLoopDeQueue<Integer>(4);
        assertTrue(queue.enQueue(1, 2));
        assertEquals("1, 2", queue.toString());

        queue.enQueueHead(0);
        assertEquals("0, 1, 2", queue.toString());

        assertFalse(queue.enQueue(3));
    }

    @Test
    void deQueue() {
        var queue = new SeqLoopDeQueue<Integer>();
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
        var queue = new SeqLoopDeQueue<Integer>();
        queue.enQueue(1, 2);

        var value = queue.getHead();
        assertTrue(value.isPresent());
        assertEquals(1, value.get());

        queue = new SeqLoopDeQueue<>();
        value = queue.getHead();
        assertTrue(value.isEmpty());
    }

    @Test
    void testToString() {
        var queue = new SeqLoopDeQueue<Integer>();
        assertEquals("", queue.toString());

        assertTrue(queue.enQueue(1, 2));
        assertEquals("1, 2", queue.toString());
    }
}
