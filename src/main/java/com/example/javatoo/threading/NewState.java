package com.example.javatoo.threading;

/*
The NEW state
A Java thread is in the NEW state if it is created but not started (the thread constructor creates threads in the NEW state).
This is its state until the start() method is invoked.
 */
public class NewState {
    public void newThread() {
        Thread t = new Thread(() -> {
        });
        System.out.println("NewThread:" + t.getState());//NEW
    }

    public static void main(String[] args) {
        new NewState().newThread();
    }
}
