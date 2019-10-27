package com.ztcaoll222.data.structure.c3.stack;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

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
    void stackOverFlow() {
        SeqStack<Integer> stack = new SeqStack<>();
        assertFalse(stack.stackOverFlow());

        stack = new SeqStack<>(0);
        assertTrue(stack.stackOverFlow());
    }

    @Test
    void length() {
        SeqStack<Integer> stack = new SeqStack<>();
        assertEquals(0, stack.length());

        stack.push(1, 2, 3);
        assertEquals(3, stack.length());
    }

    @Test
    void push() {
        SeqStack<Integer> stack = new SeqStack<>(2);
        assertFalse(stack.push());
        assertTrue(stack.push(1, 2));
        assertFalse(stack.push(3));
        assertEquals("1, 2", stack.toString());
    }

    @Test
    void pushs() {
        SeqStack<Integer> stack0 = new SeqStack<>();
        assertTrue(stack0.push(1, 2, 3, 4, 5));

        SeqStack<Integer> stack1 = new SeqStack<>(0);
        assertFalse(stack1.pushs(stack0.pops()));

        stack1 = new SeqStack<>(stack0.length());
        assertTrue(stack1.pushs(stack0.pops()));
        assertTrue(stack0.stackEmpty());
        assertEquals("5, 4, 3, 2, 1", stack1.toString());
    }

    @Test
    void pop() {
        var stack = SeqStack.of(1, 2, 3);
        var value = stack.pop();
        assertTrue(value.isPresent());
        assertEquals(3, value.get());

        value = stack.pop();
        assertTrue(value.isPresent());
        assertEquals(2, value.get());

        value = stack.pop();
        assertTrue(value.isPresent());
        assertEquals(1, value.get());

        value = stack.pop();
        assertTrue(value.isEmpty());
    }

    @Test
    void pops() {
        var stack = SeqStack.of(1, 2, 3);

        var res = stack.pops().map(Object::toString).collect(Collectors.joining(", "));
        assertEquals("3, 2, 1", res);
    }

    @Test
    void getTop() {
        SeqStack<Integer> stack = SeqStack.of();
        assertTrue(stack.getTop().isEmpty());

        assertTrue(stack.push(1, 2));
        var value = stack.getTop();
        assertTrue(value.isPresent());
        assertEquals(2, value.get());
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
