package com.example.javatoo.challenge.numbers;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FindNumberOccurringOddNumberOfTimesInArray {
    static int[] arr = {7, 7, 5, 6, 6, 1, 1, 4, 4, 2, 2, 5, 5};

    public static void main(String[] args) {
        log.info("Given List: " + Arrays.toString(arr));
        log.info("Number with odd occurrences : " + new FindNumberOccurringOddNumberOfTimesInArray().findNumberOccurringOddNumberOfTimesInArray(arr));

    }

    public int findNumberOccurringOddNumberOfTimesInArray(int[] arr) {
        HashMap<Integer, Integer> elements = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int element = arr[i];
            if (elements.get(element) == null) {
                elements.put(element, 1);
            } else {
                elements.put(element, elements.get(element) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : elements.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    int findNumberOccurringOddNumberOfTimesInArrayUsingXOR(int ar[]) {
        int i;
        int result = 0;
        for (i = 0; i < ar.length; i++) {
            result = result ^ ar[i];
        }
        return result;
    }
}
