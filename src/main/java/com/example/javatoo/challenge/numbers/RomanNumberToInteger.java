package com.example.javatoo.challenge.numbers;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/*
Given a string in roman no format (s)  your task is to convert it to an integer . Various symbols and their values are given below.
I 1
V 5
X 10
L 50
C 100
D 500
M 1000

Example 1:

Input:
s = V
Output: 5
Example 2:

Input:
s = III
Output: 3
 */
@Slf4j
public class RomanNumberToInteger {

    public static void main(String[] args) {
        log.info(Integer.toString(new RomanNumberToInteger().romanToDecimal("III")));
        log.info(Integer.toString(new RomanNumberToInteger().romanToDecimal("XII")));
    }

    int romanToDecimal(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i < str.length() - 1 && map.get(str.charAt(i)) < map.get(str.charAt(i + 1))) {
                ans += map.get(str.charAt(i + 1)) - map.get(str.charAt(i));
                i++;
            } else
                ans += map.get(str.charAt(i));
        }
        return ans;
    }
}
