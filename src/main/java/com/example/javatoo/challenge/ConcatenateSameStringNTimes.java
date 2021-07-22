package com.example.javatoo.challenge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcatenateSameStringNTimes {
    private static final String TEXT = "hello";

    public static void main(String[] args) {
        log.info(new ConcatenateSameStringNTimes().concatenateSameStringNTimesV1(TEXT, 5));
    }

    public String concatenateSameStringNTimesV1(String str, Integer n) {
        if (Integer.MAX_VALUE / n < str.length()) {
            throw new OutOfMemoryError("Maximum size of a String will be exceeded");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < n; i++) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

}
