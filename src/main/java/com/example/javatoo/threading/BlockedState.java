package com.example.javatoo.threading;

/*
The BLOCKED state
When a thread is trying to execute I/O tasks or synchronized blocks, it may enter into the BLOCKED state. For example, if a thread,
t1, tries to enter into a synchronized block of code that is already being accessed by another thread, t2, then t1 is kept in the
BLOCKED state until it can acquire the lock.

This scenario is shaped in the following snippet of code:
1.Create two threads: t1 and t2.
2.Start t1 via the start() method:
    1.t1 will execute the run() method and will acquire the lock for the synchronized method, syncMethod().
    2.The syncMethod() will keep t1 inside forever, since it has an infinite loop.
3.After two seconds (arbitrary time), start t2 via the start() method:
    1.t2 will execute the run() code and will end up in the BLOCKED state since it cannot acquire the lock of syncMethod().
 */
public class BlockedState {
    public static void main(String[] args) throws InterruptedException {
        new BlockedState().blockThread();
    }

    public void blockThread() throws InterruptedException {
        Thread t1 = new Thread(new SyncCode());
        Thread t2 = new Thread(new SyncCode());
        t1.start();
        Thread.sleep(2000);
        t2.start();
        Thread.sleep(2000);

        System.out.println("BlockedState t1: " + t1.getState() + "(" + t1.getName() + ")");
        System.out.println("BlockedState t2: " + t2.getState() + "(" + t2.getName() + ")");

        System.exit(0);
    }

    private static class SyncCode implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread " + Thread.currentThread().getName() + " is in run() method");
            syncMethod();
        }

        public static synchronized void syncMethod() {
            System.out.println("Thread " + Thread.currentThread().getName() + " is in syncMethod()");
            while (true) {
                //t1 will stay here forever, therefore t2 is blocked
            }
        }
    }

}
// output:
//        Thread Thread-0 is in run() method
//        Thread Thread-0 is in syncMethod()
//        Thread Thread-1 is in run() method
//        BlockedState t1: RUNNABLE(Thread-0)
//        BlockedState t2: BLOCKED(Thread-1)