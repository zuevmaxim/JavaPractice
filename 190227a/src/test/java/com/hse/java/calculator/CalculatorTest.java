package com.hse.java.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;

    @Test
    void evaluateOnEmptyString() {
        Stack<Integer> stack = (Stack<Integer>) mock(Stack.class);
        when(stack.push(anyInt())).thenReturn(null);
        when(stack.pop()).thenThrow(new EmptyStackException());
        calculator = new Calculator(stack);
        assertEquals(0, calculator.evaluate(""));
    }
}