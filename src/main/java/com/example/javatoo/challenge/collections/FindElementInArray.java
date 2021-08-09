package com.example.javatoo.challenge.collections;

import java.time.Clock;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class FindElementInArray {

    public static void main(String[] args) {
        Clock clock = Clock.systemUTC();

        // array of numbers
        int[] numbers = new int[]{4, 5, 1, 3, 7, 4, 1, 5};

        // array of Melon
        Melon[] melons = new Melon[]{
                new Melon("Crenshaw", 2000), new Melon("Gac", 1200), new Melon("Bitter", 2200)};

        // Comparators
        Comparator<Melon> byType = Comparator.comparing(Melon::getType);
        Comparator<Melon> byWeight = Comparator.comparing(Melon::getWeight);

        System.out.println("Check only for presence:");
        System.out.println("------------------------");

        System.out.println("\nSimple solution for numbers:");
        long startTimeV1 = clock.millis();
        boolean found1 = FindElementInArray.containsElementV1(numbers.clone(), 1);
        displayExecutionTime(clock.millis() - startTimeV1);
        System.out.println("The element was found?: " + found1);

        System.out.println("\nSolution based on Arrays.binarySearch() for numbers:");
        long startTimeV2 = clock.millis();
        boolean found2 = FindElementInArray.containsElementV2(numbers.clone(), 1);
        displayExecutionTime(clock.millis() - startTimeV2);
        System.out.println("The element was found?: " + found2);

        System.out.println("\nSolution based on Stream.anyMatch() for numbers:");
        long startTimeV3 = clock.millis();
        boolean found3 = FindElementInArray.containsElementV3(numbers.clone(), 1);
        displayExecutionTime(clock.millis() - startTimeV3);
        System.out.println("The element was found?: " + found3);

        System.out.println("\nSimple solution for Melon:");
        long startTimeV4 = clock.millis();
        boolean found4 = FindElementInArray.containsElementObjectV1(melons.clone(), new Melon("Gac", 1200));
        displayExecutionTime(clock.millis() - startTimeV4);
        System.out.println("The Melon was found?: " + found4);

        System.out.println("\nSolution based on Comparator for Melon:");
        long startTimeV5 = clock.millis();
        boolean found5 = FindElementInArray.containsElementObjectV2(melons.clone(), new Melon("Gac", 1205), byType);
        displayExecutionTime(clock.millis() - startTimeV5);
        System.out.println("The Melon was found?: " + found5);

        System.out.println("\nSolution based on binarySearch() for Melon:");
        long startTimeV6 = clock.millis();
        boolean found6 = FindElementInArray.containsElementObjectV3(melons.clone(), new Melon("Honeydew", 1200), byWeight);
        displayExecutionTime(clock.millis() - startTimeV6);
        System.out.println("The Melon was found?: " + found6);

        System.out.println("\nCheck only for index:");
        System.out.println("---------------------");

        System.out.println("\nSimple solution for numbers:");
        long startTimeV7 = clock.millis();
        int index1 = FindElementInArray.findIndexOfElementV1(numbers.clone(), 7);
        displayExecutionTime(clock.millis() - startTimeV7);
        System.out.println("Found at index (-1 means not found): " + index1);

        System.out.println("\nSolution based on filter() for numbers:");
        long startTimeV8 = clock.millis();
        int index2 = FindElementInArray.findIndexOfElementV2(numbers.clone(), 7);
        displayExecutionTime(clock.millis() - startTimeV8);
        System.out.println("Found at index (-1 means not found): " + index2);

        System.out.println("\nSimple solution for Melon:");
        long startTimeV9 = clock.millis();
        int index3 = FindElementInArray.findIndexOfElementObjectV1(melons.clone(), new Melon("Gac", 1200));
        displayExecutionTime(clock.millis() - startTimeV9);
        System.out.println("Melon found at index (-1 means not found): " + index3);

        System.out.println("\nSolution based on Comparator for Melon:");
        long startTimeV10 = clock.millis();
        int index4 = FindElementInArray.findIndexOfElementObjectV2(melons.clone(), new Melon("Gac", 1205), byType);
        displayExecutionTime(clock.millis() - startTimeV10);
        System.out.println("Melon found at index (-1 means not found): " + index4);

        System.out.println("\nSolution based on filter() and Comparator for Melon:");
        long startTimeV11 = clock.millis();
        int index5 = FindElementInArray.findIndexOfElementObjectV3(melons.clone(), new Melon("Honeydew", 1200), byWeight);
        displayExecutionTime(clock.millis() - startTimeV11);
        System.out.println("Melon found at index (-1 means not found): " + index5);
    }

    private static void displayExecutionTime(long time) {
        System.out.println("Execution time: " + time + " ms" + " ("
                + TimeUnit.SECONDS.convert(time, TimeUnit.MILLISECONDS) + " s)");
    }

    public static boolean containsElementV1(int[] arr, int toContain) {

        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        for (int elem : arr) {
            if (elem == toContain) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsElementV2(int[] arr, int toContain) {

        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        Arrays.sort(arr);
        int index = Arrays.binarySearch(arr, toContain);

        return (index >= 0);
    }

    public static boolean containsElementV3(int[] arr, int toContain) {

        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        return Arrays.stream(arr)
                .anyMatch(e -> e == toContain);
    }

    public static <T> boolean containsElementObjectV1(T[] arr, T toContain) {

        if (arr == null || toContain == null) {
            throw new IllegalArgumentException("None of the arguments can be null");
        }

        for (T elem : arr) {
            if (elem.equals(toContain)) {
                return true;
            }
        }

        return false;
    }

    public static <T> boolean containsElementObjectV2(T[] arr, T toContain, Comparator<? super T> c) {

        if (arr == null || toContain == null || c == null) {
            throw new IllegalArgumentException("None of the arguments can be null");
        }

        for (T elem : arr) {
            if (c.compare(elem, toContain) == 0) {
                return true;
            }
        }

        return false;
    }

    public static <T> boolean containsElementObjectV3(T[] arr, T toContain, Comparator<? super T> c) {

        if (arr == null || toContain == null || c == null) {
            throw new IllegalArgumentException("None of the arguments can be null");
        }

        Arrays.sort(arr, c);
        int index = Arrays.binarySearch(arr, toContain, c);

        return (index >= 0);
    }

    public static int findIndexOfElementV1(int[] arr, int toFind) {

        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == toFind) {
                return i;
            }
        }

        return -1;
    }

    public static int findIndexOfElementV2(int[] arr, int toFind) {

        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        return IntStream.range(0, arr.length)
                .filter(i -> toFind == arr[i])
                .findFirst()
                .orElse(-1);
    }

    public static <T> int findIndexOfElementObjectV1(T[] arr, T toFind) {

        if (arr == null || toFind == null) {
            throw new IllegalArgumentException("None of the arguments can be null");
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(toFind)) {
                return i;
            }
        }

        return -1;
    }

    public static <T> int findIndexOfElementObjectV2(T[] arr, T toFind, Comparator<? super T> c) {

        if (arr == null || toFind == null || c == null) {
            throw new IllegalArgumentException("None of the arguments can be null");
        }

        for (int i = 0; i < arr.length; i++) {
            if (c.compare(arr[i], toFind) == 0) {
                return i;
            }
        }

        return -1;
    }

    public static <T> int findIndexOfElementObjectV3(T[] arr, T toFind, Comparator<? super T> c) {

        if (arr == null || toFind == null || c == null) {
            throw new IllegalArgumentException("None of the arguments can be null");
        }

        return IntStream.range(0, arr.length)
                .filter(i -> c.compare(toFind, arr[i]) == 0)
                .findFirst()
                .orElse(-1);
    }
}
