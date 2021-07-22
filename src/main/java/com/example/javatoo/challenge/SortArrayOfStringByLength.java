package com.example.javatoo.challenge;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;

@Slf4j
public class SortArrayOfStringByLength {
    private static String[] strs = {"one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine", "ten"};

    public static void main(String[] args) {
        log.info(Arrays.toString(new SortArrayOfStringByLength().sortArrayOfStringByLengthV1(strs)));
    }

    public String[] sortArrayOfStringByLengthV1(String[] strs) {
//        Arrays.sort(strs, (String s1, String s2) -> Integer.compare(s1.length(), s2.length()));
//        for reverse
//        Arrays.sort(strs, (String s1, String s2) -> (-1) * Integer.compare(s1.length(), s2.length()));
//        OR
//        Arrays.sort(strs, Comparator.comparingInt(String::length));
//        for reverse
//        Arrays.sort(strs, Comparator.comparingInt(String::length).reversed());
        return Arrays.stream(strs).sorted(Comparator.comparingInt(String::length)).toArray(String[]::new);
    }
}
