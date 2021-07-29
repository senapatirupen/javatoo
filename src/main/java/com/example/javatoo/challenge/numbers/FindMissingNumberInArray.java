package com.example.javatoo.challenge.numbers;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class FindMissingNumberInArray {
    static int[] arr = {7, 5, 6, 1, 4, 2};

    public static void main(String[] args) {
        log.info("Given List: " + Arrays.toString(arr));
        log.info("Missing Number: " + new FindMissingNumberInArray().findMissingNumberInArray(arr));
    }

    public int findMissingNumberInArray(int[] arr) {
//        Solution: Find the sum of n numbers using formula n=n*(n+1)/2
//        Find the sum of elements present in given array.
//        Substract (sum of n numbers – sum of elements present in the array).
        int n = arr.length + 1;
        int sum = n * (n + 1) / 2;
        int restSum = 0;
        for (int i = 0; i < arr.length; i++) {
            restSum += arr[i];
        }
        int missingNumber = sum - restSum;
        return missingNumber;
    }
}
