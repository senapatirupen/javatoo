package com.example.javatoo.threading;
/*
The TIMED_WAITING state
A thread, t1, that waits for an explicit period of time for another thread, t2, to finish is in the TIMED_WAITING state.
This scenario is shaped in the following snippet of code:
1.Create a thread: t1.
2.Start t1 via the start() method.
3.In the run() method of t1, add a sleep time of two seconds (arbitrary time).
4.While t1 is running, the main thread prints the t1 state—the state should be TIMED_WAITING since t1 is in a sleep() that will expire after two seconds.
 */
public class TimedWaitingState {
    public void timedWaitingThread() throws InterruptedException {
        Thread t = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        Thread.sleep(500);
        System.out.println("TimedWaitingThread t: " + t.getState()); // TIMED_WAITING
    }

    public static void main(String[] args) throws InterruptedException {
        new TimedWaitingState().timedWaitingThread();
    }
}
