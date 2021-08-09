package com.example.javatoo.challenge.collections;

import java.util.Arrays;

public class IncreaseSizeOfArray {
    public static void main(String[] args) {
        int[] arrI = new int[2];
        arrI[0] = 5;
        arrI[1] = 1;

        System.out.println("Initial array (arrI): " + Arrays.toString(arrI));

        System.out.println("\nAdd number 2 in the initial array");
        int[] arrIAfterAdd = IncreaseSizeOfArray.add(arrI, 2);
        System.out.println("Result: " + Arrays.toString(arrIAfterAdd));

        System.out.println("\nRemove last number from the initial array (1):");
        int[] arrIAfterRemove = IncreaseSizeOfArray.remove(arrI);
        System.out.println("Result: " + Arrays.toString(arrIAfterRemove));

        System.out.println("\nResize the initial array to 12:");
        int[] arrIResized = IncreaseSizeOfArray.resize(arrI, 10);
        System.out.println("Result: " + Arrays.toString(arrIResized));

        String[] arrS = new String[2];
        arrS[0] = "abc";
        arrS[1] = "def";

        System.out.println("\n\nInitial array (arrS): " + Arrays.toString(arrS));

        System.out.println("\nAdd string 'ghi' in the initial array");
        String[] arrSAfterAdd = IncreaseSizeOfArray.addObject(arrS, "ghi");
        System.out.println("Result: " + Arrays.toString(arrSAfterAdd));

        System.out.println("\nRemove last string from the initial array ('def'):");
        String[] arrSAfterRemove = IncreaseSizeOfArray.removeObject(arrS);
        System.out.println("Result: " + Arrays.toString(arrSAfterRemove));

        System.out.println("\nResize the initial array to 12:");
        String[] arrSResized = IncreaseSizeOfArray.resize(arrS, 10);
        System.out.println("Result: " + Arrays.toString(arrSResized));
    }

    public static int[] add(int[] arr, int item) {

        if (arr == null) {
            throw new IllegalArgumentException("The given array cannot be null");
        }

        int[] newArr = Arrays.copyOf(arr, arr.length + 1);
        newArr[newArr.length - 1] = item;

        // or, using System.arraycopy()
        // int[] newArr = new int[arr.length + 1];
        // System.arraycopy(arr, 0, newArr, 0, arr.length);
        // newArr[newArr.length - 1] = item;
        return newArr;
    }

    public static int[] remove(int[] arr) {

        if (arr == null) {
            throw new IllegalArgumentException("The given array cannot be null");
        }

        if (arr.length < 1) {
            throw new IllegalArgumentException("The given array length must be greater than 0");
        }

        int[] newArr = Arrays.copyOf(arr, arr.length - 1);

        // or, using System.arraycopy()
        // int[] newArr = new int[arr.length - 1];
        // System.arraycopy(arr, 0, newArr, 0, arr.length - 1);
        return newArr;
    }

    public static int[] resize(int[] arr, int length) {

        if (arr == null) {
            throw new IllegalArgumentException("The given array cannot be null");
        }

        if (length < 0) {
            throw new IllegalArgumentException("The given length cannot be smaller than 0");
        }

        int[] newArr = Arrays.copyOf(arr, arr.length + length);

        // or, using System.arraycopy()
        // int[] newArr = new int[arr.length + length];
        // System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }

    public static <T> T[] addObject(T[] arr, T item) {

        if (arr == null) {
            throw new IllegalArgumentException("The given array cannot be null");
        }

        if (item == null) {
            throw new IllegalArgumentException("The given item cannot be null");
        }

        T[] newArr = Arrays.copyOf(arr, arr.length + 1);
        newArr[newArr.length - 1] = item;

        return newArr;
    }

    public static <T> T[] removeObject(T[] arr) {

        if (arr == null) {
            throw new IllegalArgumentException("The given array cannot be null");
        }

        T[] newArr = Arrays.copyOf(arr, arr.length - 1);

        return newArr;
    }

    public static <T> T[] resize(T[] arr, int length) {

        if (arr == null) {
            throw new IllegalArgumentException("The given array cannot be null");
        }

        if (length < 0) {
            throw new IllegalArgumentException("The given length cannot be smaller than 0");
        }

        T[] newArr = Arrays.copyOf(arr, arr.length + length);

        return newArr;
    }
}
