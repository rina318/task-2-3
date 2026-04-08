package org.example;

import java.util.Deque;

public class TaskLogic {

    public static Deque<Integer> solve(Deque<Integer> stack) {
        Deque<Integer> newStack = new SimpleDeque<>();

        while (!stack.isEmpty()) {
            newStack.push(stack.pop());
        }

        return newStack;
    }
}