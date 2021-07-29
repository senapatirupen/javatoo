package com.example.javatoo.challenge.numbers;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
public class SmallestAndLargestInArray {
    static int arr[] = new int[]{12, 56, 76, 89, 100, 343, 21, 234};

    public static void main(String[] args) {
        log.info("Given List: " + Arrays.toString(arr));
        new SmallestAndLargestInArray().smallestAndLargestInArray(arr);
    }

    public void smallestAndLargestInArray(int[] arr) {
        int smallest = arr[0];
        int largest = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            } else if (arr[i] < smallest) {
                smallest = arr[i];
            }
        }
        log.info("Largest: " + largest);
        log.info("smallest: " + smallest);
    }

}
