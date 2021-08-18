package com.example.javatoo.threading;
/*
The RUNNABLE state
The transition from NEW to RUNNABLE is obtained by calling the start() method. In this state, a thread can be running or ready to run.
When it is ready to run, a thread is waiting for the JVM thread-scheduler to allocate the needed resources and time to run to it.
As soon as the processor is available, the thread-scheduler will run the thread.

The following snippet of code should print RUNNABLE, since we print the state of the thread after calling start(). But because of
thread-scheduler internal mechanisms, this is not guaranteed:
 */
public class RunnableState {
    public void runnableThread(){
        Thread t = new Thread(() -> {});
        t.start();

        System.out.println("RunnableThread: "+t.getState()); //RUNNABLE
    }

    public static void main(String[] args) {
        new RunnableState().runnableThread();
    }
}
