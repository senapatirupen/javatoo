package com.example.javatoo.util;

import java.util.concurrent.TimeUnit;

public class DisplayExecutionTime {
    public static void displayExecutionTime(long time) {
        System.out.println("Execution time: " + time + " ns" + " (" +
                TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS) + " ms)");
    }
}
