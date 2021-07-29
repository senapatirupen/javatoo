package com.example.javatoo.challenge.strings;

import java.util.stream.Stream;

public class TransformStringWithStream {
    public static void main(String[] args) {

        String result1Map = Stream.of("hello")
                .map(s -> s + " world")
                .findFirst()
                .get();
        System.out.println(result1Map);

        String result2Map = Stream.of("gooool! ")
                .map(String::toUpperCase)
                .map(s -> s.repeat(2))
                .map(s -> s.replaceAll("O", "OOOO"))
                .findFirst()
                .get();
        System.out.println(result2Map);

    }

}
