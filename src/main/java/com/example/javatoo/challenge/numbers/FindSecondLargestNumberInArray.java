package com.example.javatoo.challenge.numbers;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class FindSecondLargestNumberInArray {
    static int[] arr = {7, 5, 6, 1, 4, 2};

    public static void main(String[] args) {
        log.info("Given List: " + Arrays.toString(arr));
        log.info("Second Largest Number: " + new FindSecondLargestNumberInArray().findSecondLargestNumberInArray(arr));
    }

    public int findSecondLargestNumberInArray(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int secLargest = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largest) {
                secLargest = largest;
                largest = arr[i];
            } else if (arr[i] > secLargest && arr[i] != largest) {
                secLargest = arr[i];
            }
        }
        return secLargest;
    }
}
