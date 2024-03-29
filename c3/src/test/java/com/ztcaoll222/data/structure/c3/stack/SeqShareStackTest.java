package com.ztcaoll222.data.structure.c3.stack;

import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

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
    void length() {
        SeqShareStack<Integer> stack = new SeqShareStack<>();
        assertEquals(0, stack.getA().length());
        assertEquals(0, stack.getB().length());

        stack.getA().push(1, 2);
        stack.getB().push(3);
        assertEquals(2, stack.getA().length());
        assertEquals(1, stack.getB().length());
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
    void pushs() {
        SeqShareStack<Integer> stack = new SeqShareStack<>(5);
        assertTrue(stack.getA().push(1, 2, 3, 4, 5));
        assertTrue(stack.getB().pushs(stack.getA().pops()));

        assertTrue(stack.getA().stackEmpty());
        assertEquals("{a: {}, b: {5, 4, 3, 2, 1}}", stack.toString());
    }

    @Test
    void pop() {
        SeqShareStack<Integer> stack = new SeqShareStack<>();

        var a = stack.getA();
        a.push(1, 2, 3);
        var value = a.pop();
        assertTrue(value.isPresent());
        assertEquals(3, value.get());

        value = a.pop();
        assertTrue(value.isPresent());
        assertEquals(2, value.get());

        value = a.pop();
        assertTrue(value.isPresent());
        assertEquals(1, value.get());

        value = a.pop();
        assertTrue(value.isEmpty());

        var b = stack.getB();
        b.push(1, 2, 3);
        value = b.pop();
        assertTrue(value.isPresent());
        assertEquals(3, value.get());

        value = b.pop();
        assertTrue(value.isPresent());
        assertEquals(2, value.get());

        value = b.pop();
        assertTrue(value.isPresent());
        assertEquals(1, value.get());

        value = b.pop();
        assertTrue(value.isEmpty());
    }

    @Test
    void pops() {
        SeqShareStack<Integer> stack = new SeqShareStack<>();

        var a = stack.getA();
        a.push(1, 2, 3);

        var b = stack.getB();
        b.push(4, 5, 6);

        var res = a.pops().map(Object::toString).collect(Collectors.joining(", "));
        assertEquals("3, 2, 1", res);

        res = b.pops().map(Object::toString).collect(Collectors.joining(", "));
        assertEquals("6, 5, 4", res);
    }

    @Test
    void getTop() {
        SeqShareStack<Integer> stack = new SeqShareStack<>();

        var a = stack.getA();
        assertTrue(a.getTop().isEmpty());
        assertTrue(a.push(1, 2));
        var value = a.getTop();
        assertTrue(value.isPresent());
        assertEquals(2, value.get());

        var b = stack.getB();
        assertTrue(b.getTop().isEmpty());
        assertTrue(b.push(1, 2));
        value = b.getTop();
        assertTrue(value.isPresent());
        assertEquals(2, value.get());
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
