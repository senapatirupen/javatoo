package com.example.javatoo.problemsolving;

/*
https://practice.geeksforgeeks.org/problems/longest-palindromic-subsequence-1612327878/1

S = "bbabcbcab"
Output: 7
Explanation: Subsequence "babcbab" is the
longest subsequence which is also a palindrome.
 */
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubsequence().longestPalinSubseq("bbabcbcab"));
    }

    public int longestPalinSubseq(String A) {
        int n = A.length();

        StringBuilder st = new StringBuilder();
        st.append(A);
        st.reverse();

        String B = st.toString();

        // a palindrome reads the same from front and reverse.
        // hence, the longest palindromic subsequence is
        // basically the longest common subsequence in
        // A and reverse(A)
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else {
                    if (A.charAt(i - 1) == B.charAt(j - 1))
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    else
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];
    }

}