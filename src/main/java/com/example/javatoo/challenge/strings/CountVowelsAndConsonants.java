package com.example.javatoo.challenge.strings;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class CountVowelsAndConsonants {
    private static final String TEXT = " ... Illinois Mathematics & Science Academy ... ";
    public static final Set<Character> allVowels = new HashSet<>(Arrays.asList('a','e','i','o','u'));
    public static void main(String[] args) {
        new CountVowelsAndConsonants().countVowelsAndConsonantsV1(TEXT);
    }

    public Pair<Integer, Integer> countVowelsAndConsonantsV1(String str) {

        if (str == null || str.isBlank()) {
            // or throw IllegalArgumentException
            return Pair.of(-1, -1);
        }

        str = str.toLowerCase();

        int vowels = 0;
        int consonants = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (allVowels.contains(ch)) {
                vowels++;
            } else if ((ch >= 'a' && ch <= 'z')) {
                consonants++;
            }
        }

        return Pair.of(vowels, consonants);

        /*
        long vowels = str.chars()
                .filter(c -> allVowels.contains((char) c))
                .count();

        long consonants = str.chars()
                .filter(c -> !allVowels.contains((char) c))
                .filter(ch -> (ch >= 'a' && ch <= 'z'))
                .count();

        return Pair.of(vowels, consonants);
        */

        /*
        Map<Boolean, Long> result = str.chars()
                .mapToObj(c -> (char) c)
                .filter(ch -> (ch >= 'a' && ch <= 'z'))
                .collect(partitioningBy(c -> allVowels.contains(c), counting()));

        return Pair.of(result.get(true), result.get(false));
        */
    }
}
