package com.example.javatoo.threading.executorandexecutorservice;

import java.util.concurrent.Executor;

public class SimpleExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
