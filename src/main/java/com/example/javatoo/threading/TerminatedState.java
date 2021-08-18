package com.example.javatoo.threading;

/*
The TERMINATED state
A thread that successfully finishes its job or is abnormally interrupted is in the TERMINATE state.
This is very simple to simulate, as in the following snippet of code (the main thread of the application prints the state of the
thread, t—when this is happening, the thread, t, has done its job):
 */
public class TerminatedState {
    public void terminatedThread() throws InterruptedException {
        Thread t = new Thread(() -> {
        });
        t.start();
        Thread.sleep(1000);
        System.out.println("TerminatedThread t: " + t.getState()); // TERMINATED
    }

    public static void main(String[] args) throws InterruptedException {
        new TerminatedState().terminatedThread();
    }
}
