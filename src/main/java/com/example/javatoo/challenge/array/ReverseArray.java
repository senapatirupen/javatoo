package com.example.javatoo.challenge.array;

import com.example.javatoo.challenge.collections.Melon;

import java.util.*;
import java.util.stream.IntStream;

public class ReverseArray {
    public static void main(String[] args) {
        int[] integers = {-1, 2, 3, 1, 4, 5, 3, 2, 22};
        //find duplicate in array
        Set<Integer> nums = new HashSet<>();
         for(int i=0; i<= integers.length-1; i++){
            for(int j=0; j<= integers.length-1; j++){
                if(integers[i] == integers[j] && i != j){
                    nums.add(integers[i]);
                }
            }
        }
        nums.stream().forEach(System.out::print);
        System.out.println("end of for loop >>>>");

        Melon[] melons = new Melon[]{
                new Melon("Crenshaw", 2000), new Melon("Gac", 1200), new Melon("Bitter", 2200)};

        int[] cloneIntegers = integers.clone();
        new ReverseArray().reverseArrayV1(cloneIntegers);
        System.out.println("Reversed: " + Arrays.toString(cloneIntegers));

        int[] reversedInt = IntStream.rangeClosed(1, integers.length)
                .map(i -> integers[integers.length - i]).toArray();
        System.out.println("Reversed: " + Arrays.toString(reversedInt));

        Melon[] cloneMelons1 = melons.clone();
        new ReverseArray().reverseArrayV2(cloneMelons1);
        System.out.println("Reversed: " + Arrays.toString(cloneMelons1));

        Melon[] cloneMelons2 = melons.clone();
        Collections.reverse(Arrays.asList(cloneMelons2));
        System.out.println("Reversed: " + Arrays.toString(cloneMelons2));

        Melon[] reversedMelon = IntStream.rangeClosed(1, melons.length)
                .mapToObj(i -> melons[melons.length - i])
                .toArray(Melon[]::new);

        System.out.println("Reversed: " + Arrays.toString(reversedMelon));

    }

    public void reverseArrayV1(int[] arr) {
        for (int leftHead = 0, rightHead = arr.length - 1; leftHead < rightHead; leftHead++, rightHead--) {
            int ele = arr[leftHead];
            arr[leftHead] = arr[rightHead];
            arr[rightHead] = ele;
        }
    }

    public static <T> void reverseArrayV2(T[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        for (int leftHead = 0, rightHead = arr.length - 1;
             leftHead < rightHead; leftHead++, rightHead--) {
            T elem = arr[leftHead];
            arr[leftHead] = arr[rightHead];
            arr[rightHead] = elem;
        }
    }
}
