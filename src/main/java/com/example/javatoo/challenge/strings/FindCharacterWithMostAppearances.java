package com.example.javatoo.challenge.strings;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
public class FindCharacterWithMostAppearances {
    private static final String TEXT = "My high school, the Illinois Mathematics and Science Academy, ";

    public static void main(String[] args) {
        PairCharCount pairV1 = new FindCharacterWithMostAppearances().findCharacterWithMostAppearancesV1(TEXT);
        PairCharCount pairV2 = new FindCharacterWithMostAppearances().findCharacterWithMostAppearancesV2(TEXT);
        log.info(pairV1.character.toString() + " " + pairV1.occurrences.toString());
        log.info(pairV2.character.toString() + " " + pairV2.occurrences.toString());
    }

    public PairCharCount<Character, Integer> findCharacterWithMostAppearancesV1(String str) {
        if (str == null && str.isEmpty()) {
            return PairCharCount.of(Character.MIN_VALUE, -1);
        }
        Map<Character, Integer> counter = new HashMap<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            if (!Character.isWhitespace(currentChar)) {//Ignore spaces
                Integer noCh = counter.get(currentChar);
                if (noCh == null) {
                    counter.put(currentChar, 1);
                } else {
                    counter.put(currentChar, ++noCh);
                }
            }
        }
        int maxOccurrences = Collections.max(counter.values());
        char maxCharacter = Character.MIN_VALUE;
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == maxOccurrences) {
                maxCharacter = entry.getKey();
            }
        }
        return PairCharCount.of(maxCharacter, maxOccurrences);
    }

    //need to check
    public static PairCharCount<Character, Long> findCharacterWithMostAppearancesV2(String str) {

        if (str == null || str.isBlank()) {
            return PairCharCount.of(Character.MIN_VALUE, -1L);
        }
        return str.chars()
                .filter(c -> Character.isWhitespace(c) == false) // ignoring space
                .mapToObj(c -> (char) c)
                .collect(groupingBy(c -> c, counting()))
                .entrySet()
                .stream()
                .max(comparingByValue())
                .map(p -> PairCharCount.of(p.getKey(), p.getValue()))
                .orElse(PairCharCount.of(Character.MIN_VALUE, -1L));
    }

}
