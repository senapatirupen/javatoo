package com.example.javatoo.challenge;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class GeneratePermutations {
    private static final String TEXT = "ABC";

    public static void main(String[] args) {
        new GeneratePermutations().generatePermutationsV1("", TEXT);
    }

    public void generatePermutationsV1(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            log.info(prefix + " ");
        } else {
            for (int i = 0; i < n; i++) {
                generatePermutationsV1(prefix + str.charAt(i), str.substring(i + 1, n) + str.substring(0, i));
            }
        }
    }

    public Set<String> generatePermutationsAndStoreV1(String prefix, String str) {
        Set<String> permutations = new HashSet<>();
        int n = str.length();
        if (n == 0) {
            permutations.add(prefix + " ");
        } else {
            for (int i = 0; i < n; i++) {
                permutations.addAll(generatePermutationsAndStoreV1(prefix + str.charAt(i), str.substring(i + 1, n) + str.substring(0, i)));
            }
        }
        return permutations;
    }

    public static Stream<String> generatePermutationsAndReturnStreamV1(String str) {

        if (str == null || str.isBlank()) {
            return Stream.of("");
        }
        return IntStream.range(0, str.length())
                .parallel()
                .boxed()
                .flatMap(i -> generatePermutationsAndReturnStreamV1(str.substring(0, i) + str.substring(i + 1))
                        .map(c -> str.charAt(i) + c)
                );
    }
}
