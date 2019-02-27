package com.hse.java.calculator;


import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CalculatorTest {
    private Calculator calculator;

    @Test
    void evaluateOnEmptyString() {
        @SuppressWarnings("unchecked")
        Stack<Integer> stack = mock(Stack.class);
        when(stack.push(anyInt())).thenReturn(null);
        when(stack.pop()).thenThrow(new EmptyStackException());
        when(stack.peek()).thenThrow(new UnsupportedOperationException());
        calculator = new Calculator(stack);
        assertEquals(0, calculator.evaluate(""));
    }

    @Test
    void evaluateSimpleString() {
        @SuppressWarnings("unchecked")
        Stack<Integer> stack = mock(Stack.class);
        when(stack.push(1)).thenReturn(1);
        when(stack.push(2)).thenReturn(2);
        when(stack.push(3)).thenReturn(3);
        when(stack.pop()).thenReturn(1).thenReturn(2);
        when(stack.peek()).thenReturn(3);
        when(stack.size()).thenReturn(1);
        calculator = new Calculator(stack);
        assertEquals(3, calculator.evaluate("1 2 +"));
        verify(stack).push(1);
        verify(stack).push(2);
        verify(stack).push(3);
    }

    @Test
    void evaluate() {
        @SuppressWarnings("unchecked")
        Stack<Integer> stack = mock(Stack.class);
        when(stack.push(1)).thenReturn(1);
        when(stack.push(2)).thenReturn(2);
        when(stack.push(3)).thenReturn(3);
        when(stack.push(3)).thenReturn(3);
        when(stack.push(9)).thenReturn(9);

        when(stack.pop()).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(3).thenReturn(9);
        when(stack.peek()).thenReturn(9);
        when(stack.size()).thenReturn(1);
        calculator = new Calculator(stack);
        assertEquals(9, calculator.evaluate("1 2 + 3 *"));
        verify(stack).push(1);
        verify(stack).push(2);
        verify(stack, times(2)).push(3);
        verify(stack).push(9);
    }

    @Test
    void evaluateWrong() {
        @SuppressWarnings("unchecked")
        Stack<Integer> stack = mock(Stack.class);
        when(stack.push(1)).thenReturn(1);
        when(stack.push(2)).thenReturn(2);
        when(stack.push(3)).thenReturn(3);
        when(stack.push(3)).thenReturn(3);
        when(stack.push(9)).thenReturn(9);

        when(stack.pop()).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(3).thenReturn(9);
        when(stack.peek()).thenReturn(9);

        calculator = new Calculator(stack);
        assertThrows(IllegalStateException.class, () -> calculator.evaluate("1 2 3 +"));
    }

    @Test
    void evaluateWrongString() {
        @SuppressWarnings("unchecked")
        Stack<Integer> stack = mock(Stack.class);
        when(stack.push(1)).thenReturn(1);
        when(stack.push(2)).thenReturn(2);

        //noinspection unchecked
        when(stack.pop()).thenReturn(1).thenReturn(2).thenThrow(EmptyStackException.class);


        calculator = new Calculator(stack);
        assertThrows(EmptyStackException.class, () -> calculator.evaluate("1 2 + *"));
    }

    @Test
    void stackTest() {
        Stack<Integer> stack = new Stack<>();
        calculator = new Calculator(stack);
        assertEquals(9, calculator.evaluate("1 2 + 3 *"));
    }
}