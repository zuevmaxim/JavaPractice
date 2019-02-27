package com.hse.java.calculator;

import org.jetbrains.annotations.NotNull;

import java.util.Stack;

import static java.lang.Integer.parseInt;

public class Calculator {
    private final Stack<Integer> stack;

    public Calculator(@NotNull Stack<Integer> stack) {
        this.stack = stack;
    }

    public int evaluate(@NotNull String s) {
        String[] operators = s.split(" ");
        if (s.equals("")) {
            return 0;
        }
        for (var op : operators) {
            switch(op) {
                case " " :
                    break;
                case "+" :
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-" :
                    stack.push(stack.pop() - stack.pop());
                    break;
                case "*" :
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/" :
                    stack.push(stack.pop() / stack.pop());
                    break;
                default : stack.push(parseInt(op));
            }
        }
        if (stack.size() > 1) {
            throw new IllegalStateException("Wrong string!");
        }
        return stack.peek();
    }
}