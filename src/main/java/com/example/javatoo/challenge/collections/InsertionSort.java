package com.example.javatoo.challenge.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;

@Slf4j
public class InsertionSort {
    static int[] arr1 = {7, 5, 6, 1, 4, 2};
    public static Melon[] melons = new Melon[]{new Melon("large", 30),
            new Melon("small", 10),
            new Melon("medium", 20)
    };

    public static void main(String[] args) {
        new InsertionSort().insertionSortV1(arr1);
        new InsertionSort().insertionSortForObjectV1(melons);
    }

    public void insertionSortV1(int[] arr) {
        int size = arr.length;
        for (int i = 1; i < size; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        System.out.println(Arrays.toString(arr));
    }

    public void insertionSortForObjectV1(Melon[] arr) {
        // Ascending
        Comparator<Melon> byType = Comparator.comparing(Melon::getWeight);
//         Descending
//        Comparator<Melon> byType = Comparator.comparing(Melon::getWeight).reversed();
        insertionSortWithComparator(arr, byType);
        System.out.println(Arrays.toString(arr));
    }

    public static <T> void insertionSortWithComparator(T arr[], Comparator<? super T> c) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && c.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}
