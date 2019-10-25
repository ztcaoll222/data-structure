package com.ztcaoll222.data.structure.c3.queue;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 使用头结点的链式队列的单元测试
 */
@Flogger
class SingleHeadLinkQueueTest {

    @Test
    void queueEmpty() {
        var queue = new SingleHeadLinkQueue<Integer>();
        assertTrue(queue.queueEmpty());
    }

    @Test
    void length() {
        var queue = new SingleHeadLinkQueue<Integer>();
        assertEquals(0, queue.length());

        queue.enQueue(1, 2);
        assertEquals(2, queue.length());
    }

    @Test
    void enQueue() {
        var queue = new SingleHeadLinkQueue<Integer>();
        assertTrue(queue.enQueue(1, 2));
        assertTrue(queue.enQueue(3));
        assertEquals("1, 2, 3", queue.toString());
    }

    @Test
    void deQueue() {
        var queue = new SingleHeadLinkQueue<Integer>();
        queue.enQueue(1, 2);

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
        var queue = new SingleHeadLinkQueue<Integer>();
        queue.enQueue(1, 2);

        var elem = queue.getHead();
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());

        queue = new SingleHeadLinkQueue<>();
        elem = queue.getHead();
        assertTrue(elem.isEmpty());
    }

    @Test
    void testToString() {
        var queue = new SingleHeadLinkQueue<Integer>();
        assertEquals("", queue.toString());

        queue.enQueue(1, 2);
        assertEquals("1, 2", queue.toString());
    }
}
