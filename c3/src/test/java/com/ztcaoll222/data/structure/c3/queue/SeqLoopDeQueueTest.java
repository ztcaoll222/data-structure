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

        var elem = queue.getTail();
        assertTrue(elem.isEmpty());

        queue.enQueue(1, 2, 3);
        elem = queue.getTail();
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());
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

        var elem = queue.deQueueTail();
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = queue.deQueueTail();
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());

        elem = queue.deQueueTail();
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());

        elem = queue.deQueueTail();
        assertFalse(elem.isPresent());
    }

    @Test
    void queueEmpty() {
        var queue = new SeqLoopDeQueue<Integer>();
        assertTrue(queue.queueEmpty());
    }

    @Test
    void length() {
        var queue = new SeqLoopDeQueue<Integer>();
        assertEquals(0, queue.length());

        queue.enQueue(1, 2);
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
        var queue = new SeqLoopDeQueue<Integer>();
        queue.enQueue(1, 2);

        var elem = queue.getHead();
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());

        queue = new SeqLoopDeQueue<>();
        elem = queue.getHead();
        assertTrue(elem.isEmpty());
    }

    @Test
    void testToString() {
        var queue = new SeqLoopDeQueue<Integer>();
        assertEquals("", queue.toString());

        assertTrue(queue.enQueue(1, 2));
        assertEquals("1, 2", queue.toString());
    }
}
