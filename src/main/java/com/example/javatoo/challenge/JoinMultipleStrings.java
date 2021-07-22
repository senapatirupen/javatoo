package com.example.javatoo.challenge;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Slf4j
public class JoinMultipleStrings {
    private static final String TEXT_1 = "Illinois";
    private static final String TEXT_2 = "Mathematics";
    private static final String TEXT_3 = "and";
    private static final String TEXT_4 = "Science";
    private static final String TEXT_5 = "Academy";
    public static void main(String[] args) {
        log.info(new JoinMultipleStrings().joinMultipleStringsV1(' ', TEXT_1, TEXT_2, TEXT_3));
        log.info(new JoinMultipleStrings().joinMultipleStringsV2(' ', TEXT_1, TEXT_2));
        log.info(new JoinMultipleStrings().joinMultipleStringsV3(' ', TEXT_1, TEXT_2));
    }

    public String joinMultipleStringsV1(char delimiter, String... args){
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for(i=0; i< args.length-1; i++){
            stringBuilder.append(args[i]).append(delimiter);
        }
        stringBuilder.append(args[i]);
        return stringBuilder.toString();
    }

    public String joinMultipleStringsV2(char delimiter, String... args){
        return Arrays.stream(args, 0, args.length).collect(Collectors.joining(String.valueOf(delimiter)));
    }

    public String joinMultipleStringsV3(char delimiter, String... args){
        StringJoiner stringJoiner = new StringJoiner(String.valueOf(delimiter));
        for(String arg: args){
            stringJoiner.add(arg);
        }
        return stringJoiner.toString();
    }
}
