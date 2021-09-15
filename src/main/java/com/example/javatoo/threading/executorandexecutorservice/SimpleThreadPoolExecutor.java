package com.example.javatoo.threading.executorandexecutorservice;

public class SimpleThreadPoolExecutor implements Runnable {
    private final int taskId;

    public SimpleThreadPoolExecutor(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Executing task " + taskId + " via " + Thread.currentThread().getName());
    }

    public int getTaskId() {
        return taskId;
    }

}
