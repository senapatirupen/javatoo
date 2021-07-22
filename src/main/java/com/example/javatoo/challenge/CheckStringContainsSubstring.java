package com.example.javatoo.challenge;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class CheckStringContainsSubstring {
    private static final String TEXT = "My high school, the Illinois Mathematics and Science Academy, ";
    private static final String SUBTEXT = "Science";

    public static void main(String[] args) {
        log.info(new CheckStringContainsSubstring().checkStringContainsSubstringV1(TEXT, SUBTEXT).toString());
        log.info(new CheckStringContainsSubstring().checkStringContainsSubstringV2(TEXT, SUBTEXT).toString());

    }

    public Boolean checkStringContainsSubstringV1(String str, String subStr) {
        return str.matches("(?i).*" + Pattern.quote(subStr) + ".*");
    }

    public Boolean checkStringContainsSubstringV2(String str, String subStr) {
        return str.indexOf(subStr) != -1; //or lastIndexOf()
    }
}
