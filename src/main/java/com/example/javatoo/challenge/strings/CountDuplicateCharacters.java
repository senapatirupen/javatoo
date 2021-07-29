package com.example.javatoo.challenge.strings;

import com.example.javatoo.util.DisplayExecutionTime;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class CountDuplicateCharacters {
    private static final String TEXT = "Be strong, be fearless, be beautiful. ";

    public static void main(String[] args) {
        long startTimeV1 = System.nanoTime();
        new CountDuplicateCharacters().countDuplicateCharactersV1(TEXT);
        DisplayExecutionTime.displayExecutionTime(System.nanoTime() - startTimeV1);
        long startTimeV2 = System.nanoTime();
        new CountDuplicateCharacters().countDuplicateCharactersV2(TEXT);
        DisplayExecutionTime.displayExecutionTime(System.nanoTime() - startTimeV2);
    }

    public Map<Character, Integer> countDuplicateCharactersV1(String str) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < str.length() ; i++) {
            char ch = str.charAt(i);
//            map.compute(key, (k, v) -> (v == null) ? msg : v.concat(msg))
            result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }
        log.info("countDuplicateCharactersV1: "+result);
        return result;
    }
//    Output: countDuplicateCharactersV1: { =6, a=2, b=3, B=1, e=6, f=2, g=1, i=1, l=2, ,=2, .=1, n=1, o=1, r=2, s=3, t=2, u=2}

    public Map<Character, Long> countDuplicateCharactersV2(String str) {
        if (str == null || str.isBlank()) {
            return Collections.emptyMap();
        }
        Map<Character, Long> result = str.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
//        or
//        Map<String, Long> result = str.codePoints().mapToObj(c -> String.valueOf(Character.toChars(c))).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        log.info("countDuplicateCharactersV2: "+result);
        return result;
    }
//    Output: countDuplicateCharactersV2: { =6, a=2, b=3, B=1, e=6, f=2, g=1, i=1, l=2, ,=2, .=1, n=1, o=1, r=2, s=3, t=2, u=2}

}
