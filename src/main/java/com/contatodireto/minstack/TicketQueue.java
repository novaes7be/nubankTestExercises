package com.contatodireto.minstack;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class TicketQueue {

    ArrayDeque<Integer> ticketQueue = new ArrayDeque<>();

    public void enqueue(int ticketId) {
        ticketQueue.add(ticketId);
    }
    public int call_next() {
        if (ticketQueue.isEmpty()) {
            throw new RuntimeException();
        } else {
            return ticketQueue.pop();
        }
    }
    public int peek_next() {
        if (ticketQueue.isEmpty()) {
            throw new RuntimeException();
        } else {
            return ticketQueue.peek();
        }
    }

    public int get_waiting_count() {
        return ticketQueue.size();
    }


}
