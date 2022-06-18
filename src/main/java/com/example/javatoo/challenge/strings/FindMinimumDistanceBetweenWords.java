package com.example.javatoo.challenge.strings;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Given a list of words followed by two words, the task to find the minimum distance between the given two words in the list of words


Example 1:

Input:
S = { "the", "quick", "brown", "fox",
     "quick"}
word1 = "the"
word2 = "fox"
Output: 3
Explanation: Minimum distance between the
words "the" and "fox" is 3
Example 2:

Input:
S = {"geeks", "for", "geeks", "contribute",
     "practice"}
word1 = "geeks"
word2 = "practice"
Output: 2
Explanation: Minimum distance between the
words "geeks" and "practice" is 2

 */

@Slf4j
public class FindMinimumDistanceBetweenWords {
    public static void main(String[] args) {
        String[] str = {"the", "quick", "brown", "fox", "quick"};
        List<String> str1 = Arrays.asList(str);
        log.info(String.valueOf(new FindMinimumDistanceBetweenWords().shortestDistance(str1, "the", "fox")));
    }

    public int shortestDistance(List<String> s, String word1, String word2) {
        int count1 = -1, count2 = -1, ans = Integer.MAX_VALUE;
        for (int i = 0; i < s.size(); i++) {
            if (s.get(i).equals(word1)) {
                count1 = i;
            }
            if (s.get(i).equals(word2)) {
                count2 = i;
            }
            if (count1 != -1 && count2 != -1) {
                ans = Math.min(ans, Math.abs(count2 - count1));
            }
        }
        return ans;
    }
}
