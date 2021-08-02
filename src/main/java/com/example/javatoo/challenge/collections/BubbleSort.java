package com.example.javatoo.challenge.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;

@Slf4j
public class BubbleSort {
    static int[] arr1 = {7, 5, 6, 1, 4, 2};
    public static Melon[] melons = new Melon[]{new Melon("large", 30),
            new Melon("small", 10),
            new Melon("medium", 20)
    };

    public static void main(String[] args) {
        new BubbleSort().bubbleSortV1(arr1);
        new BubbleSort().bubbleSortForObjectV1(melons);
    }

    public void bubbleSortV1(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public void bubbleSortForObjectV1(Melon[] arr) {
        // Ascending
        Comparator<Melon> byType = Comparator.comparing(Melon::getType);
//         Descending
//        Comparator<Melon> byType = Comparator.comparing(Melon::getType).reversed();
        bubbleSortWithComparator(arr, byType);
        System.out.println(Arrays.toString(arr));
    }

    public static <T> void bubbleSortWithComparator(T arr[], Comparator<? super T> c) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (c.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
