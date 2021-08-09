package com.example.javatoo.challenge.collections;

import java.util.Arrays;
import java.util.Comparator;

public class MinMaxAverageArray {
    static int[] integers = {2, 3, 4, 1, -4, 6, 2};

    static Melon[] melons = {new Melon("Horned", 1500), new Melon("Gac", 2200),
            new Melon("Hami", 1600), new Melon("Gac", 2100)};

    public static void main(String[] args) {

        Comparator<Melon> byType = Comparator.comparing(Melon::getType);

        int maxInt1 = new MinMaxAverageArray().minMaxAverageArrayV1(integers);

        int maxInt2 = new MinMaxAverageArray().minMaxAverageArrayV1(integers);

        int maxInt3 = Arrays.stream(integers).max().getAsInt();
        System.out.println(maxInt3);

        Melon maxMelon1 = new MinMaxAverageArray().minMaxAverageArrayV3(melons, byType);
        System.out.println(maxMelon1);

        Melon maxMelon3 = Arrays.stream(melons).max(byType).orElseThrow();

        double avg1 = new MinMaxAverageArray().minMaxAverageArrayV0(integers);

        double avg2 = Arrays.stream(integers).average().getAsDouble();
    }

    public double minMaxAverageArrayV0(int[] arr) {
        double sum = 0;
        for (int element : arr) {
            sum += element;
        }
        return sum / arr.length;
    }

    public int minMaxAverageArrayV1(int[] arr) {
        int max = arr[0];
        for (int element : arr) {
            if (element > max) {
                max = element;
            }
        }
        return max;
    }

    public int minMaxAverageArrayV2(int[] arr) {
        int max = arr[0];
        for (int element : arr) {
            max = Math.max(max, element);
        }
        return max;
    }

    public static <T> T minMaxAverageArrayV3(T[] arr, Comparator<? super T> c) {
        if (arr == null || c == null) {
            throw new IllegalArgumentException("Array/Comparator cannot be null");
        }
        T max = arr[0];
        for (T elem : arr) {
            if (c.compare(elem, max) > 0) {
                max = elem;
            }
        }
        return max;
    }

    public static <T extends Comparable<T>> T minMaxAverageArrayV4(T[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        T max = arr[0];
        for (T elem : arr) {
            if (elem.compareTo(max) > 0) {
                max = elem;
            }
        }
        return max;
    }

}
