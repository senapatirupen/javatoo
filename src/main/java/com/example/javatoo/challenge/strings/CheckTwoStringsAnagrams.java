package com.example.javatoo.challenge.strings;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class CheckTwoStringsAnagrams {
    private static final int EXTENDED_ASCII_CODES = 256;
    private static final String TEXT1 = "hello world";
    private static final String TEXT2 = "dh le rlo l wo";

    public static void main(String[] args) {
        log.info(new CheckTwoStringsAnagrams().checkTwoStringsAnagramsV1(TEXT1, TEXT2).toString());
        log.info(new CheckTwoStringsAnagrams().checkTwoStringsAnagramsV2(TEXT1, TEXT2).toString());


    }

    public Boolean checkTwoStringsAnagramsV1(String str1, String str2) {
        char[] chars1 = str1.replaceAll("\\s", "").toLowerCase().toCharArray();
        char[] chars2 = str2.replaceAll("\\s", "").toLowerCase().toCharArray();
        if (chars1.length != chars2.length) {
            return false;
        }
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);
    }

    public Boolean checkTwoStringsAnagramsV2(String str1, String str2) {
        int[] chCounts = new int[EXTENDED_ASCII_CODES];
        char[] chStr1 = str1.replaceAll("\\s", "").toLowerCase().toCharArray();
        char[] chStr2 = str2.replaceAll("\\s", "").toLowerCase().toCharArray();

        if (chStr1.length != chStr2.length) {
            return false;
        }

        for (int i = 0; i < chStr1.length; i++) {
            chCounts[chStr1[i]]++;
            chCounts[chStr2[i]]--;
        }

        for (int i = 0; i < chCounts.length; i++) {

            if (chCounts[i] != 0) {
                return false;
            }
        }

        return true;
    }
}
