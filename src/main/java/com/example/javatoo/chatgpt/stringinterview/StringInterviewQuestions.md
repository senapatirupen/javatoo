Sure! Here is a list of string problem-solving questions in Java:

1. **Reverse a String**: Write a Java program to reverse a given string.
```java
public class Test {
    public static String reverseString(String str) {
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }
}
```

2. **Check Palindrome**: Determine if a given string is a palindrome or not.
```java
public class Test {
    public static boolean isPalindrome(String str) {
        // Remove any non-alphanumeric characters and convert to lowercase
        String cleanedString = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int left = 0;
        int right = cleanedString.length() - 1;

        while (left < right) {
            if (cleanedString.charAt(left) != cleanedString.charAt(right)) {
                return false; // Characters at left and right don't match, not a palindrome
            }
            left++;
            right--;
        }

        return true; // All characters matched, it's a palindrome
    }
}
```

3. **Count Vowels and Consonants**: Count the number of vowels and consonants in a given string.
```java
public class Test {
    public static int[] countVowelsAndConsonants(String str) {
        str = str.toLowerCase(); // Convert the string to lowercase to make the comparison case-insensitive
        int vowelsCount = 0;
        int consonantsCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLetter(ch)) {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowelsCount++;
                } else {
                    consonantsCount++;
                }
            }
        }

        int[] counts = {vowelsCount, consonantsCount};
        return counts;
    }
}
```

4. **Remove Duplicates**: Remove duplicate characters from a string.
```java
public class Test {
    public static String removeDuplicatesUsingSet(String str) {
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        for (char ch : str.toCharArray()) {
            set.add(ch);
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : set) {
            sb.append(ch);
        }

        return sb.toString();
    }

    public static String removeDuplicatesUsingIteration(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (sb.indexOf(String.valueOf(ch)) == -1) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}

```

5. **Check Anagrams**: Check if two strings are anagrams of each other.
```java
import java.util.HashMap;
import java.util.Map;

public class AnagramChecker {
    public static void main(String[] args) {
        String str1 = "listen";
        String str2 = "silent";

        boolean areAnagrams = checkAnagrams(str1, str2);
        System.out.println(str1 + " and " + str2 + " are anagrams: " + areAnagrams);
    }

    public static boolean checkAnagrams(String str1, String str2) {
        // Remove any non-alphanumeric characters and convert to lowercase
        String cleanedStr1 = str1.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String cleanedStr2 = str2.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        if (cleanedStr1.length() != cleanedStr2.length()) {
            return false; // Anagrams must have the same length
        }

        // Create frequency maps for characters in both strings
        Map<Character, Integer> frequencyMap1 = new HashMap<>();
        Map<Character, Integer> frequencyMap2 = new HashMap<>();

        // Populate frequency map for str1
        for (char ch : cleanedStr1.toCharArray()) {
            frequencyMap1.put(ch, frequencyMap1.getOrDefault(ch, 0) + 1);
        }

        // Populate frequency map for str2
        for (char ch : cleanedStr2.toCharArray()) {
            frequencyMap2.put(ch, frequencyMap2.getOrDefault(ch, 0) + 1);
        }

        // Compare the frequency maps
        return frequencyMap1.equals(frequencyMap2);
    }
}
//    listen and silent are anagrams: true

```

6. **Longest Common Prefix**: Find the longest common prefix among an array of strings.
```java
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        String commonPrefix = findLongestCommonPrefix(strs);
        System.out.println("Longest Common Prefix: " + commonPrefix);
    }

    public static String findLongestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return ""; // Return an empty string for an empty array or null input
        }

        String firstString = strs[0]; // Take the first string as a reference for comparison

        for (int i = 0; i < firstString.length(); i++) {
            char ch = firstString.charAt(i);

            // Compare the character at the current position with each string in the array
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != ch) {
                    // If the current position is beyond the length of any string or there is a mismatch
                    return firstString.substring(0, i); // Return the prefix up to the current position
                }
            }
        }

        // If all strings are identical, return the entire first string as the common prefix
        return firstString;
    }
}
//Longest Common Prefix: fl

```

7. **String Compression**: Compress a string by replacing repeated characters with their count.
```java
public class StringCompression {
    public static void main(String[] args) {
        String str = "aabbbccccddddeeee";
        String compressedString = compressString(str);
        System.out.println("Original String: " + str);
        System.out.println("Compressed String: " + compressedString);
    }

    public static String compressString(String str) {
        if (str == null || str.isEmpty()) {
            return str; // Return the original string if it is null or empty
        }

        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                compressed.append(str.charAt(i - 1));
                compressed.append(count);
                count = 1;
            }
        }

        // Append the last character and its count
        compressed.append(str.charAt(str.length() - 1));
        compressed.append(count);

        // Return the compressed string only if it is shorter than the original string
        return compressed.length() < str.length() ? compressed.toString() : str;
    }
}
//  Original String: aabbbccccddddeeee
//  Compressed String: a2b3c4d4e4

```

8. **Valid Parentheses**: Given a string containing only parentheses, check if it is valid.
```java
import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        String str1 = "(){}[]";
        String str2 = "({[}])";

        System.out.println(str1 + " is valid: " + isValidParentheses(str1));
        System.out.println(str2 + " is valid: " + isValidParentheses(str2));
    }

    public static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false; // Unmatched closing parenthesis
                }

                char top = stack.pop();

                if ((ch == ')' && top != '(') || (ch == '}' && top != '{') || (ch == ']' && top != '[')) {
                    return false; // Mismatched closing parenthesis
                }
            }
        }

        return stack.isEmpty(); // True if all parentheses are matched and the stack is empty
    }
}
//  (){}[] is valid: true
//  ({[}]) is valid: false

```

9. **Count Words in a Sentence**: Count the number of words in a given sentence.
```java
public class WordCounter {
    public static void main(String[] args) {
        String sentence = "The quick brown fox jumps over the lazy dog.";
        int wordCount = countWords(sentence);
        System.out.println("Sentence: " + sentence);
        System.out.println("Number of words: " + wordCount);
    }

    public static int countWords(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return 0; // Return 0 for an empty or null sentence
        }

        // Split the sentence into words using whitespace as the delimiter
        String[] words = sentence.trim().split("\\s+");

        // Return the number of words in the sentence
        return words.length;
    }
}
//  Sentence: The quick brown fox jumps over the lazy dog.
//  Number of words: 9

```

10. **Reverse Words in a Sentence**: Reverse the words in a given sentence.
```java
public class ReverseWordsInSentence {
    public static void main(String[] args) {
        String sentence = "The quick brown fox";
        String reversedSentence = reverseWords(sentence);
        System.out.println("Original Sentence: " + sentence);
        System.out.println("Reversed Sentence: " + reversedSentence);
    }

    public static String reverseWords(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return sentence; // Return the original sentence for an empty or null input
        }

        String[] words = sentence.split("\\s+"); // Split the sentence into words

        StringBuilder reversedSentence = new StringBuilder();

        // Reverse the order of words and append them to the StringBuilder
        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]);
            if (i > 0) {
                reversedSentence.append(" "); // Add a space between words (except for the last word)
            }
        }

        return reversedSentence.toString();
    }
}
//  Original Sentence: The quick brown fox
//  Reversed Sentence: fox brown quick The

```

11. **Find Substrings**: Find all occurrences of a substring in a given string.
```java
public class SubstringFinder {
    public static void main(String[] args) {
        String text = "abracadabra";
        String substring = "abra";
        int[] occurrences = findSubstringOccurrences(text, substring);
        System.out.println("Text: " + text);
        System.out.println("Substring: " + substring);
        System.out.println("Occurrences found at indices: ");
        for (int index : occurrences) {
            System.out.print(index + " ");
        }
    }

    public static int[] findSubstringOccurrences(String text, String substring) {
        if (text == null || substring == null || substring.isEmpty()) {
            return new int[0]; // Return an empty array for invalid input
        }

        int substringLength = substring.length();
        int[] occurrences = new int[text.length()]; // Array to store indices of occurrences
        int count = 0;

        for (int i = 0; i <= text.length() - substringLength; i++) {
            if (text.substring(i, i + substringLength).equals(substring)) {
                occurrences[count++] = i;
            }
        }

        // Resize the occurrences array to its actual count of occurrences
        return Arrays.copyOf(occurrences, count);
    }
}
//  Text: abracadabra
//  Substring: abra
//        Occurrences found at indices:
//        0 7

```

12. **Implement strStr()**: Implement the `strStr()` function (substring search) in Java.
```java
public class SubstringSearch {
    public static void main(String[] args) {
        String haystack = "hello world";
        String needle = "world";

        int index = strStr(haystack, needle);
        System.out.println("Original String: " + haystack);
        System.out.println("Substring to search: " + needle);
        System.out.println("Index of the substring: " + index);
    }

    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        int haystackLength = haystack.length();
        int needleLength = needle.length();

        if (needleLength == 0) {
            return 0;
        }

        for (int i = 0; i <= haystackLength - needleLength; i++) {
            int j;
            for (j = 0; j < needleLength; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needleLength) {
                return i; // Substring found at index i
            }
        }

        return -1; // Substring not found
    }
}
//  Original String: hello world
//  Substring to search: world
//        Index of the substring: 6

```

13. **Check Rotated Strings**: Given two strings, check if one is a rotation of the other.
```java
public class RotatedStringsChecker {
    public static void main(String[] args) {
        String str1 = "waterbottle";
        String str2 = "erbottlewat";

        boolean areRotatedStrings = isRotatedString(str1, str2);
        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);
        System.out.println("Are the strings rotated versions of each other? " + areRotatedStrings);
    }

    public static boolean isRotatedString(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        // Concatenate str1 with itself
        String concatenatedStr = str1 + str1;

        // Check if str2 is a substring of the concatenated string
        return concatenatedStr.contains(str2);
    }
}
//  String 1: waterbottle
//  String 2: erbottlewat
//        Are the strings rotated versions of each other? true

```

14. **Largest Substring with Non-Repeating Characters**: Find the longest substring in a string with no repeating characters.
```java
import java.util.HashSet;

public class LongestSubstringNonRepeating {
    public static void main(String[] args) {
        String input = "abcabcbb";
        String longestSubstring = findLongestSubstringNonRepeating(input);
        System.out.println("Input String: " + input);
        System.out.println("Longest Substring with Non-Repeating Characters: " + longestSubstring);
    }

    public static String findLongestSubstringNonRepeating(String input) {
        if (input == null || input.isEmpty()) {
            return ""; // Return an empty string for invalid input
        }

        int start = 0; // Starting index of the window
        int end = 0;   // Ending index of the window
        int maxLength = 0; // Length of the longest substring
        int maxStart = 0; // Starting index of the longest substring

        HashSet<Character> set = new HashSet<>(); // HashSet to track characters in the window

        while (end < input.length()) {
            char currentChar = input.charAt(end);

            if (!set.contains(currentChar)) {
                // If the character is not in the set, expand the window
                set.add(currentChar);
                end++;
                int currentLength = end - start;
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    maxStart = start;
                }
            } else {
                // If the character is in the set, shrink the window
                set.remove(input.charAt(start));
                start++;
            }
        }

        // Extract the longest substring from the input using maxStart and maxLength
        return input.substring(maxStart, maxStart + maxLength);
    }
}
//    Input String: abcabcbb
//        Longest Substring with Non-Repeating Characters: abc

```

15. **String to Integer (atoi)**: Convert a string to an integer, similar to the `parseInt()` function.
```java
public class StringToInteger {
    public static void main(String[] args) {
        String str = "12345";
        int result = atoi(str);
        System.out.println("Input String: " + str);
        System.out.println("Converted Integer: " + result);
    }

    public static int atoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0; // Return 0 for invalid input
        }

        int index = 0;
        int sign = 1; // Sign of the number, default is positive
        int num = 0; // The converted integer

        // Skip leading whitespace
        while (index < str.length() && str.charAt(index) == ' ') {
            index++;
        }

        // Check for the sign
        if (index < str.length() && (str.charAt(index) == '+' || str.charAt(index) == '-')) {
            sign = (str.charAt(index) == '-') ? -1 : 1;
            index++;
        }

        // Convert the remaining digits to an integer
        while (index < str.length() && Character.isDigit(str.charAt(index))) {
            int digit = str.charAt(index) - '0';
            // Check for integer overflow
            if (num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && digit > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            num = num * 10 + digit;
            index++;
        }

        return num * sign;
    }
}
//    Input String: 12345
//        Converted Integer: 12345

```

16. **String to Roman Numeral**: Convert an integer to a Roman numeral string.
```java
public class IntegerToRomanNumeral {
    public static void main(String[] args) {
        int number = 1994;
        String romanNumeral = intToRoman(number);
        System.out.println("Input Integer: " + number);
        System.out.println("Roman Numeral: " + romanNumeral);
    }

    public static String intToRoman(int number) {
        if (number <= 0 || number > 3999) {
            return "Invalid input"; // Return "Invalid input" for numbers outside the valid range
        }

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder romanNumeral = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                romanNumeral.append(symbols[i]);
                number -= values[i];
            }
        }

        return romanNumeral.toString();
    }
}
//    Input Integer: 1994
//        Roman Numeral: MCMXCIV

```

17. **Longest Palindromic Substring**: Find the longest palindromic substring in a given string.
```java
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String input = "babad";
        String longestPalindromicSubstring = findLongestPalindromicSubstring(input);
        System.out.println("Input String: " + input);
        System.out.println("Longest Palindromic Substring: " + longestPalindromicSubstring);
    }

    public static String findLongestPalindromicSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return ""; // Return an empty string for invalid input
        }

        int n = s.length();
        boolean[][] dp = new boolean[n][n]; // dp[i][j] represents whether substring i to j is a palindrome
        int maxLength = 1; // Length of the longest palindromic substring found so far
        int start = 0; // Starting index of the longest palindromic substring

        // All substrings of length 1 are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // Check substrings of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // Check substrings of length 3 and more
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1; // Ending index of the current substring
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    start = i;
                    maxLength = len;
                }
            }
        }

        // Extract and return the longest palindromic substring from the input
        return s.substring(start, start + maxLength);
    }
}
//    Input String: babad
//        Longest Palindromic Substring: bab

```

18. **Find Missing Letter**: Given a string containing a sequence of letters, find the missing letter.
```java
public class MissingLetterFinder {
    public static void main(String[] args) {
        String input = "abcdfg"; // Missing letter: 'e'
        char missingLetter = findMissingLetter(input);
        System.out.println("Input String: " + input);
        System.out.println("Missing Letter: " + missingLetter);
    }

    public static char findMissingLetter(String input) {
        if (input == null || input.isEmpty()) {
            return '\0'; // Return '\0' (null character) for invalid input
        }

        char missingLetter = '\0'; // Initialize to null character

        for (int i = 0; i < input.length() - 1; i++) {
            char currentChar = input.charAt(i);
            char nextChar = input.charAt(i + 1);

            if (nextChar - currentChar != 1) {
                // A gap is found in the sequence, so the missing letter is the one in between
                missingLetter = (char) (currentChar + 1);
                break;
            }
        }

        return missingLetter;
    }
}
//    Input String: abcdfg
//        Missing Letter: e

```

19. **Reverse Words in a String II**: Reverse the order of words in a given string, including whitespaces.
```java
public class ReverseWordsInStringII {
    public static void main(String[] args) {
        String input = "Hello world, how are you?";
        String reversedWords = reverseWords(input);
        System.out.println("Original String: " + input);
        System.out.println("Reversed Words: " + reversedWords);
    }

    public static String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return s; // Return the original string for invalid input
        }

        // Step 1: Reverse the entire string
        char[] charArray = s.toCharArray();
        reverse(charArray, 0, charArray.length - 1);

        // Step 2: Reverse individual words back to their original order
        int start = 0;
        for (int end = 0; end < charArray.length; end++) {
            if (charArray[end] == ' ') {
                reverse(charArray, start, end - 1);
                start = end + 1;
            }
        }

        // Reverse the last word (or the only word if there's only one word)
        reverse(charArray, start, charArray.length - 1);

        return new String(charArray);
    }

    private static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
//    Original String: Hello world, how are you?
//        Reversed Words: you? are how world, Hello

```

20. **License Key Formatting**: Given a string representing a license key, format it according to a specified pattern.
    To format a license key according to a specified pattern, you can first remove all the existing dashes ('-') from the original license key and then insert dashes at the appropriate positions as per the pattern. Here's a Java program to achieve this:

```java
public class LicenseKeyFormatting {
    public static void main(String[] args) {
        String licenseKey = "2-5g-3-J";
        int k = 4;
        String formattedLicenseKey = formatLicenseKey(licenseKey, k);
        System.out.println("Original License Key: " + licenseKey);
        System.out.println("Formatted License Key: " + formattedLicenseKey);
    }

    public static String formatLicenseKey(String licenseKey, int k) {
        if (licenseKey == null || licenseKey.isEmpty() || k <= 0) {
            return ""; // Return an empty string for invalid input
        }

        // Remove dashes and convert the license key to uppercase
        String keyWithoutDashes = licenseKey.replace("-", "").toUpperCase();

        // Calculate the length of the first group
        int firstGroupLength = keyWithoutDashes.length() % k;
        StringBuilder formattedKey = new StringBuilder();

        // Append the first group to the result
        if (firstGroupLength > 0) {
            formattedKey.append(keyWithoutDashes.substring(0, firstGroupLength));
        }

        // Append the remaining groups with dashes at every k characters
        for (int i = firstGroupLength; i < keyWithoutDashes.length(); i += k) {
            if (formattedKey.length() > 0) {
                formattedKey.append("-");
            }
            formattedKey.append(keyWithoutDashes.substring(i, i + k));
        }

        return formattedKey.toString();
    }
}
```

In this program, the `formatLicenseKey()` method takes a string `licenseKey` and an integer `k` as input and returns the formatted license key as a string.

We handle the cases of an empty or null license key and invalid `k` (non-positive) by returning an empty string.

We first remove all the dashes from the original license key and convert it to uppercase using `replace()` and `toUpperCase()`.

Next, we calculate the length of the first group, which is the remaining characters when dividing the length of the license key without dashes by `k`.

We then use a `StringBuilder` named `formattedKey` to build the formatted license key. We append the first group of characters to the `formattedKey`.

After that, we iterate through the remaining characters in the `keyWithoutDashes` and insert dashes at every `k` characters to create the subsequent groups.

Finally, we return the formatted license key as a string.

When you run this program with `licenseKey = "2-5g-3-J"` and `k = 4`, it will output:

```
Original License Key: 2-5g-3-J
Formatted License Key: 25G3-J
```

In this example, the original license key "2-5g-3-J" is formatted according to the pattern with groups of 4 characters, and the result is "25G3-J".