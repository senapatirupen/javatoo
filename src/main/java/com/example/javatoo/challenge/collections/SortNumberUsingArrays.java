package com.example.javatoo.challenge.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
public class SortNumberUsingArrays {
    static int[] arr1 = {7, 5, 6, 1, 4, 2};
    static Integer[] arr2 = new Integer[]{7, 5, 6, 1, 4, 2};
    static int[] arr3 = {7, 5, 6, 1, 4, 2};
    static int[] arr4 = {7, 5, 6, 1, 4, 2};


    public static void main(String[] args) {
        new SortNumberUsingArrays().sortNumberInArray(arr1);
        new SortNumberUsingArrays().reverseSortNumberInArrayV1(arr2);
        new SortNumberUsingArrays().reverseSortNumberInArrayV2(arr3);
        new SortNumberUsingArrays().reverseSortNumberInArrayV3(arr4);

    }

    public void sortNumberInArray(int[] nums) {
        Arrays.sort(nums);
        log.info(Arrays.toString(nums));
    }

    public void reverseSortNumberInArrayV1(Integer[] nums) {
        Arrays.sort(nums, Collections.reverseOrder());
        log.info(Arrays.toString(nums));
    }

    public void reverseSortNumberInArrayV2(int[] nums) {
        //first make the nums in sorted order
        Arrays.sort(nums);
        //then this swap logic will reverse it
        for (int leftHead = 0, rightHead = nums.length - 1; leftHead < rightHead; leftHead++, rightHead--) {
            int elem = nums[leftHead];
            nums[leftHead] = nums[rightHead];
            nums[rightHead] = elem;
        }
        log.info(Arrays.toString(nums));
    }

    public void reverseSortNumberInArrayV3(int[] nums) {
        int[] reverse = Arrays.stream(nums)
                .mapToObj(i -> i)
                .sorted((l1, l2) -> Integer.compare(l2, l1))
                .mapToInt(Integer::intValue).toArray();
        log.info(Arrays.toString(reverse));
    }
}
