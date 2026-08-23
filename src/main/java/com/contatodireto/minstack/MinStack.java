package com.contatodireto.minstack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
    Deque<Integer> stack = new ArrayDeque<>();

    public void push(int value) {
        stack.addFirst(value);
    }

    public int pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException();
        } else {
            int first =  stack.getFirst();
            stack.removeFirst();
            return first;
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            throw new RuntimeException();
        } else return stack.getFirst();
    }

    public int getMin () {
        return stack.stream().min(Integer::compareTo).get();
    }
}
