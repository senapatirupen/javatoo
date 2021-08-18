package com.example.javatoo.threading;

/*
The WAITING state
A thread, t1, that waits (without a timeout period) for another thread, t2, to finish is in the WAITING state.
This scenario is shaped in the following snippet of code:
1.Create a thread: t1.
2.Start t1 via the start() method.
3.In the run() method of t1:
    1.Create another thread: t2.
    2.Start t2 via the start() method.
    3.While t2 is running, call t2.join()—since t2 needs to join t1 (or, in other words, t1 needs to wait for t2 to die), t1 is in the WAITING state.
4.In the run() method of t2, t2 prints the state of t1, which should be WAITING (while printing the t1 state, t2 is running, therefore t1 is waiting).
 */
public class WaitingState {
    public void waitingThread() {
        new Thread(() -> {
            Thread t1 = Thread.currentThread();
            Thread t2 = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("WaitingThread t1: " + t1.getState()); // WAITING
            });
            t2.start();
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        new WaitingState().waitingThread();
    }
}
