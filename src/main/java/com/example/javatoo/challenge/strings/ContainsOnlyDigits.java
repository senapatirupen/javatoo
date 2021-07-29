package com.example.javatoo.challenge.strings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContainsOnlyDigits {
    private static final String ONLY_DIGITS = "45566336754493420932877387482372374982374823";

    public static void main(String[] args) {
        new ContainsOnlyDigits().containsOnlyDigitsV1(ONLY_DIGITS);
        new ContainsOnlyDigits().containsOnlyDigitsV2(ONLY_DIGITS);
        new ContainsOnlyDigits().containsOnlyDigitsV3(ONLY_DIGITS);

    }

    public Boolean containsOnlyDigitsV1(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public Boolean containsOnlyDigitsV2(String str) {
        return str.matches("[0-9]+");
    }

    public Boolean containsOnlyDigitsV3(String str) {
        return !str.chars().anyMatch(n -> !Character.isDigit(n));
    }
}
