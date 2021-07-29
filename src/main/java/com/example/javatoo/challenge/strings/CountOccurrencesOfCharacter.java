package com.example.javatoo.challenge.strings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountOccurrencesOfCharacter {
    private static final String TEXT = "My high school, the Illinois Mathematics and Science Academy, ";
    public static void main(String[] args) {
        new CountOccurrencesOfCharacter().countOccurrencesOfCharacterV1(TEXT, 'h');
        new CountOccurrencesOfCharacter().countOccurrencesOfCharacterV2(TEXT, 'h');
    }

    public Integer countOccurrencesOfCharacterV1(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public Long countOccurrencesOfCharacterV2(String str, char ch) {
        return str.chars().filter(c -> c == ch).count();
    }
}
