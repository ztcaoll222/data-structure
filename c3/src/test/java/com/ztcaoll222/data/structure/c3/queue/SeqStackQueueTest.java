package com.ztcaoll222.data.structure.c3.queue;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 基于栈的队列的单元测试
 */
@Flogger
class SeqStackQueueTest {

    @Test
    void queueEmpty() {
        var queue = new SeqStackQueue<Integer>();
        assertTrue(queue.queueEmpty());
    }

    @Test
    void length() {
        var queue = new SeqStackQueue<Integer>(3);
        assertEquals(0, queue.length());

        queue.enQueue(1, 2);
        assertEquals(2, queue.length());

        assertTrue(queue.deQueue().isPresent());
        queue.enQueue(3);
        assertEquals(2, queue.length());
    }

    @Test
    void enQueue() {
        var queue = new SeqStackQueue<Integer>(2);
        assertTrue(queue.enQueue(1, 2));
        assertTrue(queue.enQueue(3, 4));
        assertFalse(queue.enQueue(5));
        // 因为本身只能存 2 个, 所以只看后两位正确就可以了
        assertEquals("2, 1, 3, 4", queue.toString());
    }

    @Test
    void deQueue() {
        var queue = new SeqStackQueue<Integer>();
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
        var queue = new SeqStackQueue<Integer>(2);
        queue.enQueue(1, 2);
        queue.enQueue(3, 4);
        var value = queue.getHead();
        assertTrue(value.isPresent());
        assertEquals(1, value.get());

        queue = new SeqStackQueue<>(2);
        queue.enQueue(1, 2);
        value = queue.getHead();
        assertTrue(value.isPresent());
        assertEquals(1, value.get());

        queue = new SeqStackQueue<>(2);
        value = queue.getHead();
        assertTrue(value.isEmpty());
    }

    @Test
    void testToString() {
        var queue = new SeqStackQueue<Integer>();
        assertEquals("", queue.toString());

        queue.enQueue(1, 2);
        assertEquals("1, 2", queue.toString());
    }
}
