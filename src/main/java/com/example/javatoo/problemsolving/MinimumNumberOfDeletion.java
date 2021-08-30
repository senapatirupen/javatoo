package com.example.javatoo.problemsolving;

/*
https://practice.geeksforgeeks.org/problems/minimum-number-of-deletions4610/1
Given a string 'str' of size ‘n’. The task is to remove or delete the minimum number of characters from the string
so that the resultant string is a palindrome. Find the minimum numbers of characters we need to remove.
Note: The order of characters should be maintained.

Example 1:

Input: n = 7, str = "aebcbda"
Output: 2
Explanation: We'll remove 'e' and
'd' and the string become "abcba".
 */
public class MinimumNumberOfDeletion {
    public static void main(String[] args) {
        System.out.println(new MinimumNumberOfDeletion().minDeletions("aebcbda", 7));
    }

    int lps(String str) {
        int n = str.length();
        int[][] L = new int[n][n];
        for (int i = 0; i < n; i++) {
            L[i][i] = 1;
        }

        for (int cl = 2; cl <= n; cl++) {
            for (int i = 0; i < n - cl + 1; i++) {
                int j = i + cl - 1;
                if (str.charAt(i) == str.charAt(j) && cl == 2)
                    L[i][j] = 2;
                else if (str.charAt(i) == str.charAt(j))
                    L[i][j] = L[i + 1][j - 1] + 2;
                else
                    L[i][j] = Math.max(L[i][j - 1], L[i + 1][j]);
            }
        }
        return L[0][n - 1];
    }

    int minDeletions(String str, int n) {
        int len = lps(str);
        return (n - len);
    }
}
