package com.example.javatoo.challenge.strings;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class CheckPalindrome {
    private static final String TEXT = "ABCDEFEDCBA";

    public static void main(String[] args) {
        log.info(new CheckPalindrome().checkPalindromeV1(TEXT).toString());
        log.info(new CheckPalindrome().checkPalindromeV2(TEXT).toString());
    }

    public Boolean checkPalindromeV1(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (right > left) {
            if (str.charAt(left) != str.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public Boolean checkPalindromeV2(String str) {
        int n = str.length();
        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public Boolean checkPalindromeV3(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    public Boolean checkPalindromeV4(String str) {
        return IntStream.range(0, str.length() / 2)
                .noneMatch(p -> str.charAt(p) != str.charAt(str.length() - p - 1));
    }
}
