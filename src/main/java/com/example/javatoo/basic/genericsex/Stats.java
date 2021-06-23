package com.example.javatoo.basic.genericsex;

// In this version of Stats, the type argument for T must be either Number, or a class derived from Number.
public class Stats<T extends Number> {
    T[] nums;

    Stats(T[] o) {
        nums = o;
    }

    double average() {
        double sum = 0.0;
        for (int i = 0; i < nums.length - 1; i++) {
            sum += nums[i].doubleValue();
        }
        return sum / nums.length;
    }

    // Determine if two averages are the same.
    // Notice the use of the wildcard.
    boolean sameAverage(Stats<?> obj) {
        if (average() == obj.average()) {
            return true;
        }
        return false;
    }
}
