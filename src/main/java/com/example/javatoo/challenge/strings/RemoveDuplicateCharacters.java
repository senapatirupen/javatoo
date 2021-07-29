package com.example.javatoo.challenge.strings;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class RemoveDuplicateCharacters {
    private static final String TEXT = "!ABCBA;C D E-D  D  DFA;";
    public static void main(String[] args) {
        log.info(new RemoveDuplicateCharacters().removeDuplicateCharactersV1(TEXT));
        log.info(new RemoveDuplicateCharacters().removeDuplicateCharactersV2(TEXT));
        log.info(new RemoveDuplicateCharacters().removeDuplicateCharactersV3(TEXT));
    }

    public String removeDuplicateCharactersV1(String str){
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(char ch: chars){
            if(stringBuilder.indexOf(String.valueOf(ch)) == -1){
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

    public String removeDuplicateCharactersV2(String str){
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Set<Character> strs = new HashSet<>();
        for(char ch: chars){
            if(strs.add(ch)){
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

    public String removeDuplicateCharactersV3(String str){
        return Arrays.asList(str.split("")).stream().distinct().collect(Collectors.joining());
    }
}
