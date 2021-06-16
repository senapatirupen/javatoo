package com.example.javatoo.model;

import java.util.Comparator;

public class MyStringComparator implements Comparator<String> {
    // Reverse the comparison.
    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }
    // No need to override equals or the default methods.
}
