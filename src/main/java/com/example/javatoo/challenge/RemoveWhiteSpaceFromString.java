package com.example.javatoo.challenge;

public class RemoveWhiteSpaceFromString {
    private static final String TEXT = "My high school, the Illinois Mathematics and Science Academy, ";
    public static void main(String[] args) {
        String str = TEXT.replaceAll("\\s", "");
        System.out.println(str);
    }
}
