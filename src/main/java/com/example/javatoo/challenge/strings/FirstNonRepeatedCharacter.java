package com.example.javatoo.challenge.strings;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class FirstNonRepeatedCharacter {
    private static final String TEXT = "My high school, the Illinois Mathematics and Science Academy, ";
    public static void main(String[] args) {
        new FirstNonRepeatedCharacter().firstNonRepeatedCharacterV1(TEXT);
        new FirstNonRepeatedCharacter().firstNonRepeatedCharacterV2(TEXT);
        new FirstNonRepeatedCharacter().firstNonRepeatedCharacterV3(TEXT);
    }

    public char firstNonRepeatedCharacterV1(String str) {
        if (str == null & str.isBlank()) {
            return Character.MIN_VALUE;
        }
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }
        for (Map.Entry<Character, Integer> entry : result.entrySet()) {
            if (entry.getValue() == 1) {
                return Character.MIN_VALUE;
            }
        }
        return Character.MIN_VALUE;
    }

    public char firstNonRepeatedCharacterV2(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int count = 0;
            for (int j = 0; j < str.length(); j++) {
                if (ch == str.charAt(j) && i != j) {
                    count++;
                    break;
                }
            }
            if (count == 0) {
                log.info("firstNonRepeatedCharacterV2: "+String.valueOf(ch));
                return ch;
            }
        }
        return Character.MIN_VALUE;
    }

    public String firstNonRepeatedCharacterV3(String str) {
        Map<Integer, Long> chs = str.codePoints().mapToObj(cp -> cp).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        int cp =chs.entrySet().stream().filter(e -> e.getValue() == 1L).findFirst().map(Map.Entry::getKey).orElse(Integer.valueOf(Character.MIN_VALUE));
        log.info("firstNonRepeatedCharacterV3: "+String.valueOf(Character.toChars(cp)));
        return String.valueOf(Character.toChars(cp));
    }
}
