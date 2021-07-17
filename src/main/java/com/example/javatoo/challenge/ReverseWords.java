package com.example.javatoo.challenge;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class ReverseWords {
    private static final String TEXT = "My high school, the Illinois Mathematics and Science Academy, ";
    private static final String WHITESPACE = " ";
    private static final Pattern PATTERN = Pattern.compile(" +");

    public static void main(String[] args) {
        new ReverseWords().reverseWordsV1(TEXT);
        new ReverseWords().reverseWordsV2(TEXT);
        new ReverseWords().reverseWordsV3(TEXT);
    }

    public String reverseWordsV1(String str) {
        StringBuilder reverseWords = new StringBuilder();
        String[] words = str.split(WHITESPACE);
        for (String word : words) {
            StringBuilder reverseWord = new StringBuilder();
            for (int i = word.length() - 1; i >= 0; i--) {
                reverseWord.append(word.charAt(i));
            }
            reverseWords.append(reverseWord).append(WHITESPACE);
        }
        log.info(reverseWords.toString());
        return reverseWords.toString();
    }

    public String reverseWordsV2(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public String reverseWordsV3(String str) {
        return PATTERN.splitAsStream(str).map(w -> new StringBuilder(w).reverse()).collect(Collectors.joining(WHITESPACE));
    }
}
