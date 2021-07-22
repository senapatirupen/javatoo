package com.example.javatoo.challenge;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class CountStringInString {
    private static final String STRING = "111111";
    private static final String SUBSTRING = "11";

    public static void main(String[] args) {
        log.info(new CountStringInString().countStringInStringV1(STRING, SUBSTRING).toString());
    }

    public Integer countStringInStringV1(String str, String toFind) {
        int position = 0;
        int count = 0;
        int n = toFind.length();
        while ((position = str.indexOf(toFind, position)) != -1) {
            position = position + n;
            count++;
        }
        return count;
//        or
//        return str.split(Pattern.quote(toFind), -1).length - 1;
    }
}
