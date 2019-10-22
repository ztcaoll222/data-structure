package com.ztcaoll222.data.structure.c3.stack;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 顺序栈的单元测试
 */
@Flogger
class SeqStackTest {

    @Test
    void stackEmpty() {
        SeqStack<Integer> stack = SeqStack.of();
        assertTrue(stack.stackEmpty());
    }

    @Test
    void push() {
        SeqStack<Integer> stack = new SeqStack<>(2);
        assertTrue(stack.push(1, 2));
        assertFalse(stack.push(3));
        assertEquals("1, 2", stack.toString());
    }

    @Test
    void pop() {
        var stack = SeqStack.of(1, 2, 3);
        var elem = stack.pop();
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = stack.pop();
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());

        elem = stack.pop();
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());

        elem = stack.pop();
        assertTrue(elem.isEmpty());
    }

    @Test
    void getTop() {
        SeqStack<Integer> stack = SeqStack.of();
        assertTrue(stack.getTop().isEmpty());

        assertTrue(stack.push(1, 2));
        var elem = stack.getTop();
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());
        assertEquals("1, 2", stack.toString());
    }

    @Test
    void clearStack() {
        var stack = SeqStack.of(1, 2, 3);
        assertEquals("1, 2, 3", stack.toString());
        stack.clearStack();
        assertEquals("", stack.toString());
    }

    @Test
    void testToString() {
        var stack = SeqStack.of(1, 2, 3);
        assertEquals("1, 2, 3", stack.toString());

        stack = SeqStack.of();
        assertEquals("", stack.toString());
    }

    @Test
    void of() {
        var stack = SeqStack.of(1, 2, 3);
        assertEquals("1, 2, 3", stack.toString());
    }
}
