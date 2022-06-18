package com.example.javatoo.challenge.strings;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class ReverseWordsNotWord {
    private static final String TEXT = "My high school, the Illinois Mathematics and Science Academy, ";

    public static void main(String[] args) {
        log.info(new ReverseWordsNotWord().reverseWords(TEXT));
    }

    public String reverseWords(String s) {
        String[] str1 = s.split(" ");
        String temp = "";
        int j = str1.length - 1;
        for (int i = 0; i < str1.length / 2; i++) {
            temp = str1[j];
            str1[j] = str1[i];
            str1[i] = temp;
            j--;
        }
        return String.join(" ", str1);
    }

}
