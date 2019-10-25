package com.ztcaoll222.data.structure.c3.stack;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 共享栈的单元测试
 */
@Flogger
class SeqShareStackTest {

    @Test
    void stackEmpty() {
        SeqShareStack<Integer> stack = new SeqShareStack<>();
        assertTrue(stack.stackEmpty());
    }

    @Test
    void stackOverFlow() {
        SeqShareStack<Integer> stack = new SeqShareStack<>();
        assertFalse(stack.getA().stackOverFlow());

        stack = new SeqShareStack<>(0);
        assertTrue(stack.getA().stackOverFlow());
    }

    @Test
    void push() {
        SeqShareStack<Integer> stack = new SeqShareStack<>(2);
        assertTrue(stack.getA().push(1));
        assertFalse(stack.getA().push());
        assertTrue(stack.getB().push(2));
        assertFalse(stack.getA().push(3));
        assertEquals("{a: {1}, b: {2}}", stack.toString());
    }

    @Test
    void pop() {
        SeqShareStack<Integer> stack = new SeqShareStack<>();

        var a = stack.getA();
        a.push(1, 2, 3);
        var elem = a.pop();
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = a.pop();
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());

        elem = a.pop();
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());

        elem = a.pop();
        assertTrue(elem.isEmpty());

        var b = stack.getB();
        b.push(1, 2, 3);
        elem = b.pop();
        assertTrue(elem.isPresent());
        assertEquals(3, elem.get().getValue());

        elem = b.pop();
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());

        elem = b.pop();
        assertTrue(elem.isPresent());
        assertEquals(1, elem.get().getValue());

        elem = b.pop();
        assertTrue(elem.isEmpty());
    }

    @Test
    void getTop() {
        SeqShareStack<Integer> stack = new SeqShareStack<>();

        var a = stack.getA();
        assertTrue(a.getTop().isEmpty());
        assertTrue(a.push(1, 2));
        var elem = a.getTop();
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());

        var b = stack.getB();
        assertTrue(b.getTop().isEmpty());
        assertTrue(b.push(1, 2));
        elem = b.getTop();
        assertTrue(elem.isPresent());
        assertEquals(2, elem.get().getValue());
        assertEquals("{a: {1, 2}, b: {1, 2}}", stack.toString());
    }

    @Test
    void clearStack() {
        SeqShareStack<Integer> stack = new SeqShareStack<>();
        var a = stack.getA();
        a.push(1, 2);
        var b = stack.getB();
        b.push(3);
        assertEquals("{a: {1, 2}, b: {3}}", stack.toString());
        stack.clearStack();
        assertEquals("{a: {}, b: {}}", stack.toString());
    }

    @Test
    void testToString() {
        SeqShareStack<Integer> stack = new SeqShareStack<>();
        var a = stack.getA();
        a.push(1, 2);
        var b = stack.getB();
        b.push(3, 4);
        assertEquals("{a: {1, 2}, b: {3, 4}}", stack.toString());

        stack = new SeqShareStack<>();
        assertEquals("{a: {}, b: {}}", stack.toString());
    }
}
