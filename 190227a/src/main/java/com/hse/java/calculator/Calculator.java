package com.hse.java.calculator;

import java.util.Stack;

import static java.lang.Integer.parseInt;

public class Calculator {
    private final Stack<Integer> stack;

    public Calculator(Stack<Integer> stack) {
        this.stack = stack;
    }

    public int evaluate(String s) {
        String operators[] = s.split("[*/+-]");
        for (var op : operators) {
            switch(op) {
                case "+" :
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-" :
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "*" :
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "/" :
                    stack.push(stack.pop() + stack.pop());
                    break;
                default : stack.push(parseInt(op));
            }
        }
        return stack.peek();
    }
}
