package com.example.javatoo.threading.cancelthread;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class RandomList implements Runnable {
    public volatile boolean cancelled;
    private final List<Integer> randoms = new CopyOnWriteArrayList<>();
    private final Random rnd = new Random();

    @Override
    public void run() {
        while (!cancelled) {
            randoms.add(rnd.nextInt(100));
        }
    }

    public void cancelled(){
        cancelled = true;
    }

    public List<Integer> getRandoms(){
        return randoms;
    }


}
