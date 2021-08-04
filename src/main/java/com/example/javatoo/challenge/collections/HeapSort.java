package com.example.javatoo.challenge.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;

@Slf4j
public class HeapSort {
    static int[] arr1 = {7, 5, 6, 1, 4, 2};
    public static Melon[] melons = new Melon[]{new Melon("large", 30),
            new Melon("small", 10),
            new Melon("medium", 20)
    };

    public static void main(String[] args) {
        new HeapSort().heapSortV1(arr1);
    }

    public static void heapSortV1(int[] arr) {
        int n = arr.length;
        buildHeap(arr, n);
        while (n > 1) {
            swap(arr, 0, n - 1);
            n--;
            heapify(arr, n, 0);
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void buildHeap(int[] arr, int n) {
        for (int i = arr.length / 2; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int greater;

        if (left < n && arr[left] > arr[i]) {
            greater = left;
        } else {
            greater = i;
        }

        if (right < n && arr[right] > arr[greater]) {
            greater = right;
        }

        if (greater != i) {
            swap(arr, i, greater);
            heapify(arr, n, greater);
        }
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

}
