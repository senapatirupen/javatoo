package com.example.javatoo.challenge.strings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindLongestCommonPrefix {
    private static String[] texts = {"abc", "abcd", "abcde", "ab", "abcd", "abcdef"};

    public static void main(String[] args) {
        log.info(new FindLongestCommonPrefix().findLongestCommonPrefixV1(texts));
    }

    public String findLongestCommonPrefixV1(String[] strs) {
        if (strs == null || strs.length == 0) {
            // or throw IllegalArgumentException
            return "";
        }

        if (strs.length == 1) {
            return strs[0];
        }

        int firstLen = strs[0].length();
        for (int prefixLen = 0; prefixLen < firstLen; prefixLen++) {

            char ch = strs[0].charAt(prefixLen);
            for (int i = 1; i < strs.length; i++) {
                if (prefixLen >= strs[i].length()
                        || strs[i].charAt(prefixLen) != ch) {

                    return strs[i].substring(0, prefixLen);
                }
            }
        }

        return strs[0];
    }
}
