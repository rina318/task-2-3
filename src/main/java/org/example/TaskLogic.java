package org.example;

import java.util.Deque;

public class TaskLogic {

    public static void solve(Deque<Integer> stack) {

        Deque<Integer> tempStack1 = new SimpleDeque<>();
        Deque<Integer> tempStack2 = new SimpleDeque<>();

        while (!stack.isEmpty()) {
            tempStack1.push(stack.pop());
        }

        while (!tempStack1.isEmpty()) {
            tempStack2.push(tempStack1.pop());
        }

        while (!tempStack2.isEmpty()) {
            stack.push(tempStack2.pop());
        }
    }
}