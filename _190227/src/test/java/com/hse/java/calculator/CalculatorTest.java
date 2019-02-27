package com.hse.java.calculator;


import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculatorTest {
    private Calculator calculator;

    @Test
    void evaluateOnEmptyString() {
        Stack<Integer> stack = (Stack<Integer>) mock(Stack.class);
        when(stack.push(anyInt())).thenReturn(null);
        when(stack.pop()).thenThrow(new EmptyStackException());
        when(stack.peek()).thenThrow(new UnsupportedOperationException());
        calculator = new Calculator(stack);
        assertEquals(0, calculator.evaluate(""));
    }

    @Test
    void evaluateSimpleString() {
        Stack<Integer> stack = (Stack<Integer>) mock(Stack.class);
        when(stack.push(1)).thenReturn(1);
        when(stack.push(2)).thenReturn(2);
        when(stack.push(3)).thenReturn(3);
        when(stack.pop()).thenReturn(1).thenReturn(2);
        when(stack.peek()).thenReturn(3);
        calculator = new Calculator(stack);
        assertEquals(3, calculator.evaluate("1 2 +"));
    }

    @Test
    void evaluate() {
        Stack<Integer> stack = (Stack<Integer>) mock(Stack.class);
        when(stack.push(1)).thenReturn(1);
        when(stack.push(2)).thenReturn(2);
        when(stack.push(3)).thenReturn(3);
        when(stack.push(3)).thenReturn(3);
        when(stack.push(9)).thenReturn(9);
        when(stack.pop()).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(3).thenReturn(9);
        when(stack.peek()).thenReturn(9);
        calculator = new Calculator(stack);
        assertEquals(9, calculator.evaluate("1 2 + 3 *"));
    }

    @Test
    void stackTest() {
        Stack<Integer> stack = new Stack<>();
        calculator = new Calculator(stack);
        assertEquals(9, calculator.evaluate("1 2 + 3 *"));
    }
}