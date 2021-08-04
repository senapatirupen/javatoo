package com.example.javatoo.challenge.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;

@Slf4j
public class CountingSort {
    static int[] arr1 = {7, 5, 6, 1, 4, 2};

    public static void main(String[] args) {
        new CountingSort().countingSortV1(arr1);
    }

    public static void countingSortV1(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > max) {
                max = arr[i];
            }
        }
        int[] counts = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            counts[arr[i] - min]++;
        }
        int sortedIndex = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                arr[sortedIndex++] = i + min;
                counts[i]--;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
