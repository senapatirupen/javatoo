package com.example.javatoo.challenge.collections;

import java.util.Comparator;

public class MelonComparator implements Comparator<Melon> {

    @Override
    public int compare(Melon o1, Melon o2) {
        return o1.getType().compareTo(o2.getType());
    }
}
