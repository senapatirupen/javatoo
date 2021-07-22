package com.example.javatoo.challenge;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class RemoveGivenCharacter {
    private static final String TEXT = "oobotooorogshsoootorgo";
    private static final char CHAR = 's';

    public static void main(String[] args) {
        log.info(new RemoveGivenCharacter().removeGivenCharacterV1(TEXT, CHAR));
        log.info(new RemoveGivenCharacter().removeGivenCharacterV2(TEXT, CHAR));
        log.info(new RemoveGivenCharacter().removeGivenCharacterV3(TEXT, CHAR));
    }

    public String removeGivenCharacterV1(String str, char c) {
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : chars) {
            if (ch != c) {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

    public String removeGivenCharacterV2(String str, char c) {
        return str.replaceAll(Pattern.quote(String.valueOf(c)), "");
    }

    public String removeGivenCharacterV3(String str, char c) {
        return str.chars().filter(ch -> ch != c).mapToObj(ch -> String.valueOf((char) ch)).collect(Collectors.joining());
    }
}
