# Complete Program List from "Think Like a Programmer"
Upload a book in any AI then ask for extract all the programs present in this book and then also provide the dry run output for same.

## Section 1: Beginner Problems

### Chapter 1: Simple Loops

---

#### Program 1.1: Print the contents of an array

**Description**: Take a simple list (array) of numbers and print them to the standard output.

**Java Code**:
```java
private static void printArray(Integer[] array) {
    for (Integer number: array) {
        System.out.println(number);
    }
    System.out.println();
}
```

**Dry Run**:
```java
// Input: array = [10, 20, 30, 40, 50]
// Iteration 1: number = 10 → prints 10
// Iteration 2: number = 20 → prints 20
// Iteration 3: number = 30 → prints 30
// Iteration 4: number = 40 → prints 40
// Iteration 5: number = 50 → prints 50
// After loop → prints blank line
// Output: 
// 10
// 20
// 30
// 40
// 50
// (blank line)
```

---

#### Program 1.2: Reverse and print an array

**Description**: Take a simple list (array) of numbers and print them in reverse order.

**Java Code**:
```java
private static Integer[] reverseArray(Integer[] array) {
    Integer[] reversedArray = new Integer[array.length];
    int reversedIndex = 0;
    for (int index = array.length; index > 0; index--) {
        reversedArray[reversedIndex] = array[index - 1];
        reversedIndex++;
    }
    return reversedArray;
}
```

**Dry Run**:
```java
// Input: array = [10, 20, 30, 40, 50]
// array.length = 5
// reversedArray = new Integer[5]
// reversedIndex = 0

// Iteration 1: index = 5 → reversedArray[0] = array[4] = 50, reversedIndex = 1
// Iteration 2: index = 4 → reversedArray[1] = array[3] = 40, reversedIndex = 2
// Iteration 3: index = 3 → reversedArray[2] = array[2] = 30, reversedIndex = 3
// Iteration 4: index = 2 → reversedArray[3] = array[1] = 20, reversedIndex = 4
// Iteration 5: index = 1 → reversedArray[4] = array[0] = 10, reversedIndex = 5

// Return: [50, 40, 30, 20, 10]
```

---

#### Program 1.3: FizzBuzz

**Description**: Loop through numbers 1-20. Print "Fizz" if divisible by 3, "Buzz" if divisible by 5, "FizzBuzz" if divisible by both.

**Java Code**:
```java
private static void fizzBuzz(int maxCount) {
    for (int counter = 1; counter <= maxCount; counter++) {
        if (counter % 3 == 0 && counter % 5 == 0) {
            System.out.print("FizzBuzz = ");
            System.out.println(counter);
        } else if (counter % 3 == 0) {
            System.out.print("Fizz = ");
            System.out.println(counter);
        } else if (counter % 5 == 0) {
            System.out.print("Buzz = ");
            System.out.println(counter);
        }
        System.out.println();
    }
    System.out.println();
}
```

**Dry Run**:
```java
// Input: maxCount = 5
// Iteration 1: counter = 1 → 1%3 != 0, 1%5 != 0 → no print, prints blank line
// Iteration 2: counter = 2 → 2%3 != 0, 2%5 != 0 → no print, prints blank line
// Iteration 3: counter = 3 → 3%3 == 0 → prints "Fizz = 3", prints blank line
// Iteration 4: counter = 4 → 4%3 != 0, 4%5 != 0 → no print, prints blank line
// Iteration 5: counter = 5 → 5%5 == 0 → prints "Buzz = 5", prints blank line
// Output:
// Fizz = 3
// (blank line)
// Buzz = 5
// (blank line)
```

---

#### Program 1.4: Print a diamond of numbers

**Description**: Print a diamond shape of numbers with the given number as the middle point.

**Java Code**:
```java
private static void printDiamond(int size) {
    if (size < 10) {
        // upper half
        for (int topCounter = 1; topCounter <= size; topCounter++) {
            // compute and print leading spaces
            for (int spaceCounter = 0; spaceCounter < (size - topCounter); spaceCounter++) {
                System.out.print(" ");
            }
            // print number(s)
            for (int numCounter = 0; numCounter < ((topCounter * 2) - 1); numCounter++) {
                System.out.print(topCounter);
            }
            System.out.println();
        }
        // bottom half
        for (int bottomCounter = (size - 1); bottomCounter > 0; bottomCounter--) {
            // compute and print leading spaces
            for (int spaceCounter2 = 0; spaceCounter2 < (size - bottomCounter); spaceCounter2++) {
                System.out.print(" ");
            }
            // print number(s)
            for (int numCounter2 = 0; numCounter2 < ((bottomCounter * 2) - 1); numCounter2++) {
                System.out.print(bottomCounter);
            }
            System.out.println();
        }
    } else {
        System.out.println("Please provide a single-digit number.");
    }
    System.out.println();
}
```

**Dry Run**:
```java
// Input: size = 3

// Top half:
// topCounter = 1: spaces = 2, print "1" → "  1"
// topCounter = 2: spaces = 1, print "222" → " 222"
// topCounter = 3: spaces = 0, print "33333" → "33333"

// Bottom half:
// bottomCounter = 2: spaces = 1, print "222" → " 222"
// bottomCounter = 1: spaces = 2, print "1" → "  1"

// Output:
//   1
//  222
// 33333
//  222
//   1
```

---

#### Program 1.5: Diamond outline of numbers

**Description**: Print the outline of a diamond shape of numbers.

**Java Code**:
```java
private static void printDiamondOutline(int size) {
    if (size < 10) {
        // upper half
        for (int topCounter = 1; topCounter <= size; topCounter++) {
            // compute and print leading spaces
            for (int spaceCounter = 0; spaceCounter < (size - topCounter); spaceCounter++) {
                System.out.print(" ");
            }
            // print number(s)
            if (topCounter > 1) {
                System.out.print(topCounter);
                // filler spaces, not needed for the first iteration
                for (int fillCounter = 0; fillCounter < ((topCounter * 2) - 3); fillCounter++) {
                    System.out.print(" ");
                }
            }
            System.out.println(topCounter);
        }
        // bottom half
        for (int bottomCounter = (size - 1); bottomCounter > 0; bottomCounter--) {
            // compute and print leading spaces
            for (int spaceCounter2 = 0; spaceCounter2 < (size - bottomCounter); spaceCounter2++) {
                System.out.print(" ");
            }
            // print number(s)
            if (bottomCounter > 1) {
                System.out.print(bottomCounter);
                // filler spaces, not needed for the first iteration
                for (int fillCounter2 = 0; fillCounter2 < ((bottomCounter * 2) - 3); fillCounter2++) {
                    System.out.print(" ");
                }
            }
            System.out.println(bottomCounter);
        }
    } else {
        System.out.println("Please provide a single-digit number.");
    }
    System.out.println();
}
```

**Dry Run**:
```java
// Input: size = 3

// Top half:
// topCounter = 1: spaces = 2, prints "1" → "  1"
// topCounter = 2: spaces = 1, prints "2", fillCounter runs 1 time → " 2 2"
// topCounter = 3: spaces = 0, prints "3", fillCounter runs 3 times → "3   3"

// Bottom half:
// bottomCounter = 2: spaces = 1, prints "2", fillCounter runs 1 time → " 2 2"
// bottomCounter = 1: spaces = 2, prints "1" → "  1"

// Output:
//   1
//  2 2
// 3   3
//  2 2
//   1
```

---

#### Program 1.6: Checking valid number using while

**Description**: Accept a number as keyboard input and display the corresponding ASCII/UTF character.

**Java Code**:
```java
import java.util.Scanner;

private static void readNumber() {
    Scanner inputScanner = new Scanner(System.in);
    int number = 9999;
    while (number != 0) {
        System.out.println("Enter a number > 31,");
        System.out.print("or zero (0) to quit: ");
        String inputStr = inputScanner.nextLine();
        try {
            number = Integer.parseInt(inputStr);
            if (number > 31) {
                System.out.printf("The character for ASCII code %d is %c\n", 
                    number, (char) number);
            } else if (number != 0) {
                System.out.println("Sorry, only numbers 32 or higher are permitted.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Sorry, only numbers are permitted.");
        }
    }
    inputScanner.close();
    System.out.println("0 entered, exiting.");
}
```

**Dry Run**:
```java
// Input sequence: 65, 97, 0

// Iteration 1: number = 9999
// User enters: 65
// 65 > 31 → prints "The character for ASCII code 65 is A"

// Iteration 2: number = 65
// User enters: 97
// 97 > 31 → prints "The character for ASCII code 97 is a"

// Iteration 3: number = 97
// User enters: 0
// 0 == 0 → loop condition false
// prints "0 entered, exiting."
```

---

#### Program 1.7: Display user names

**Description**: Accept usernames as keyboard input and display each user's name as they are entered.

**Java Code**:
```java
private static void readName() {
    Scanner inputScanner = new Scanner(System.in);
    System.out.println("What is your name?");
    String inputStr = inputScanner.nextLine();
    do {
        System.out.printf("Hello %s!\n", inputStr);
        System.out.println("What is your name?");
        inputStr = inputScanner.nextLine();
    } while (!inputStr.equals("exit"));
    inputScanner.close();
    System.out.println("Exiting.");
}
```

**Dry Run**:
```java
// Input sequence: "Alice", "Bob", "exit"

// Initial prompt: "What is your name?"
// User enters: "Alice"
// inputStr = "Alice"

// do-while iteration 1:
// prints "Hello Alice!"
// "What is your name?"
// User enters: "Bob"
// inputStr = "Bob"
// condition check: "Bob".equals("exit")? false → continue

// do-while iteration 2:
// prints "Hello Bob!"
// "What is your name?"
// User enters: "exit"
// inputStr = "exit"
// condition check: "exit".equals("exit")? true → exit loop
// prints "Exiting."
```

---

### Chapter 2: File Operations

---

#### Program 2.1: Write to a text file

**Description**: Read a list of strings and write each item into a text file.

**Java Code**:
```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

private static void writeListToFile(String filename, List<String> list) {
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + filename));
        for (String item: list) {
            writer.write(item + "\n");
        }
        writer.close();
    } catch (IOException ioex) {
        System.out.println("Error occurred while writing:");
        ioex.printStackTrace();
    }
    System.out.println(filename + " written!");
}
```

**Dry Run**:
```java
// Input: filename = "data1.txt", list = ["Pac-Man", "Space Invaders", "Donkey Kong"]
// Creates BufferedWriter to "data/data1.txt"
// Iteration 1: writes "Pac-Man\n"
// Iteration 2: writes "Space Invaders\n"
// Iteration 3: writes "Donkey Kong\n"
// Closes writer
// Prints "data1.txt written!"
// File content:
// Pac-Man
// Space Invaders
// Donkey Kong
```

---

#### Program 2.2: Read and print from a text file

**Description**: Read a small text file and print each line.

**Java Code**:
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

private static void readTextFile(String filename) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("data/" + filename));
        String line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            line = reader.readLine();
        }
        reader.close();
    } catch (IOException ieox) {
        System.out.println("Error occurred while reading:");
        ieox.printStackTrace();
    }
}
```

**Dry Run**:
```java
// Input: filename = "data1.txt" (containing: "Pac-Man\nSpace Invaders\nDonkey Kong\n")
// BufferedReader reads from "data/data1.txt"
// line = "Pac-Man"
// while loop:
//   iteration 1: line != null → prints "Pac-Man", line = "Space Invaders"
//   iteration 2: line != null → prints "Space Invaders", line = "Donkey Kong"
//   iteration 3: line != null → prints "Donkey Kong", line = null
//   iteration 4: line == null → exit loop
// Closes reader
```

---

#### Program 2.3: Read and print from a CSV file

**Description**: Read a CSV file of video arcade games, players, and their scores, and print them to the screen.

**Java Code**:
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

private static void readCSVFile(String filename) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("data/" + filename));
        String line = reader.readLine();
        boolean header = true;
        while (line != null) {
            if (!header) {
                String[] columns = line.split(",");
                String game = columns[0];
                String player = columns[1];
                String score = columns[2];
                System.out.printf("High score for %s ", game);
                System.out.printf("by player %s of ", player);
                System.out.printf("%s points\n", score);
            } else {
                System.out.println(line);
                header = false;
            }
            line = reader.readLine();
        }
        reader.close();
    } catch (IOException iex) {
        System.out.println("Error occurred while reading:");
        iex.printStackTrace();
    }
}
```

**Dry Run**:
```java
// File content: "Game,Player,Score\nPac-Man,Aaron,188870\nDonkey Kong Jr.,Emily,63000"
// line = "Game,Player,Score", header = true
// while loop:
//   iteration 1: !header is false → prints "Game,Player,Score", header = false
//                line = "Pac-Man,Aaron,188870"
//   iteration 2: !header is true → columns = ["Pac-Man", "Aaron", "188870"]
//                prints "High score for Pac-Man by player Aaron of 188870 points"
//                line = "Donkey Kong Jr.,Emily,63000"
//   iteration 3: !header is true → columns = ["Donkey Kong Jr.", "Emily", "63000"]
//                prints "High score for Donkey Kong Jr. by player Emily of 63000 points"
//                line = null
//   iteration 4: line == null → exit loop
// Closes reader
```

---

#### Program 2.4: Write a password file

**Description**: Accept a username and password combination, encrypt the password, and write them to a file.

**Java Code**:
```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

private static void writeToPasswordFile(String username, String password) {
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/password.csv", true));
        BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
        String hashedPassword = pEncoder.encode(password);
        writer.write(username + "," + hashedPassword + "\n");
        writer.close();
    } catch (IOException ieox) {
        System.out.println("Error occurred while writing:");
        ieox.printStackTrace();
    }
    System.out.println(username + " written to password file.");
}
```

**Dry Run**:
```java
// Input: username = "aaron", password = "Brewers82"
// Opens password.csv in append mode (true)
// Creates BCryptPasswordEncoder
// hashedPassword = "$2a$10$8FXsQ0YbH2j/Ha75nPFDj.gRldGgAXLTEEaMByAoCPDdOfbTR/ftG"
// Writes "aaron,$2a$10$8FXsQ0YbH2j/Ha75nPFDj.gRldGgAXLTEEaMByAoCPDdOfbTR/ftG\n"
// Closes writer
// Prints "aaron written to password file."
```

---

#### Program 2.5: Validate a user from a password file

**Description**: Authenticate a user with their password.

**Java Code**:
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

private static void validateUserFromFile(String username, String password) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("data/password.csv"));
        BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
        boolean found = false;
        String line = reader.readLine();
        while (line != null) {
            String[] columns = line.split(",");
            String fileUser = columns[0];
            if (fileUser.equals(username)) {
                found = true;
                String fileHashedPwd = columns[1];
                if (pEncoder.matches(password, fileHashedPwd)) {
                    System.out.printf("User %s has valid credentials.\n", username);
                } else {
                    System.out.printf("User %s could not be authenticated.\n", username);
                }
                break;
            }
            line = reader.readLine();
        }
        if (!found) {
            System.out.printf("User %s was not found.\n", username);
        }
        reader.close();
    } catch (IOException ieox) {
        System.out.println("Error occurred while reading:");
        ieox.printStackTrace();
    }
}
```

**Dry Run**:
```java
// Input: username = "aaron", password = "Brewers82"
// File content: "aaron,$2a$10$8FXsQ0YbH2j/Ha75nPFDj...\ncoriene,$2a$10$EbzpFkcbL...\n"
// line = "aaron,$2a$10$8FXsQ0YbH2j/Ha75nPFDj..."
// while loop:
//   iteration 1: columns = ["aaron", "$2a$10$8FXsQ0YbH2j/Ha75nPFDj..."]
//                fileUser = "aaron"
//                fileUser.equals("aaron") → true
//                found = true
//                pEncoder.matches("Brewers82", hash) → true
//                prints "User aaron has valid credentials."
//                break → exit loop
// !found is false → no "not found" message
// Closes reader
```

---

#### Program 2.6: Write a unique password file

**Description**: Accept a username and password combination, encrypt the password, and write them to a file, as long as the username does not already exist.

**Java Code**:
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.List;

private static void writeToPasswordFileUnique(String username, String password) {
    List<String> currentUsers = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("data/passwordU.csv"))) {
        String line = reader.readLine();
        while (line != null) {
            String[] columns = line.split(",");
            currentUsers.add(columns[0]);
            line = reader.readLine();
        }
        reader.close();
    } catch (FileNotFoundException fnex) {
        System.out.println("File was not found; must be first user. Adding...");
    } catch (IOException ieox) {
        System.out.println("Error occurred while writing:");
        ieox.printStackTrace();
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/passwordU.csv", true))) {
        if (currentUsers.contains(username)) {
            System.out.printf("User %s exists, credentials not written.\n", username);
        } else {
            BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
            String hashedPassword = pEncoder.encode(password);
            writer.write(username + "," + hashedPassword + "\n");
            writer.close();
            System.out.println(username + " written to password file.");
        }
    } catch (IOException ieox) {
        System.out.println("Error occurred while writing:");
        ieox.printStackTrace();
    }
}
```

**Dry Run**:
```java
// First call: writeToPasswordFileUnique("aaron", "Brewers82")
// File not found → catch FileNotFoundException → prints "File was not found; must be first user. Adding..."
// currentUsers = []
// currentUsers.contains("aaron") → false
// Creates BCryptPasswordEncoder, hashes password
// Writes "aaron,$2a$10$8FXsQ0YbH2j/Ha75nPFDj...\n"
// Prints "aaron written to password file."

// Second call: writeToPasswordFileUnique("aaron", "newPassword")
// Reads file → currentUsers = ["aaron"]
// currentUsers.contains("aaron") → true
// Prints "User aaron exists, credentials not written."
// No write occurs
```

---

### Chapter 3: Characters and Strings

---

#### Program 3.1: Reverse a string

**Description**: Take a string as input and return it with its characters in reverse order.

**Java Code**:
```java
private static String reverseString(String inputStr) {
    StringBuilder reversed = new StringBuilder();
    for (int arrayIndex = inputStr.length(); arrayIndex > 0; arrayIndex--) {
        reversed.append(inputStr.charAt(arrayIndex - 1));
    }
    return reversed.toString();
}
```

**Dry Run**:
```java
// Input: inputStr = "hello"
// inputStr.length() = 5
// reversed = StringBuilder()

// Iteration 1: arrayIndex = 5 → inputStr.charAt(4) = 'o' → reversed = "o"
// Iteration 2: arrayIndex = 4 → inputStr.charAt(3) = 'l' → reversed = "ol"
// Iteration 3: arrayIndex = 3 → inputStr.charAt(2) = 'l' → reversed = "oll"
// Iteration 4: arrayIndex = 2 → inputStr.charAt(1) = 'e' → reversed = "olle"
// Iteration 5: arrayIndex = 1 → inputStr.charAt(0) = 'h' → reversed = "olleh"
// Return: "olleh"
```

---

#### Program 3.2: Check if a primitive character is uppercase

**Description**: Accept a primitive character type and return a Boolean indicating whether it is uppercase.

**Java Code**:
```java
private static boolean isPrimitiveUppercase(char inputChar) {
    int asciiCode = (int) inputChar;
    if (asciiCode > 64 && asciiCode < 91) {
        return true;
    }
    return false;
}
```

**Dry Run**:
```java
// Input 1: inputChar = 'A'
// asciiCode = 65
// 65 > 64 && 65 < 91 → true
// Return: true

// Input 2: inputChar = 'a'
// asciiCode = 97
// 97 > 64 && 97 < 91 → false (97 < 91 is false)
// Return: false
```

---

#### Program 3.3: Check if a reference character is uppercase

**Description**: Accept a reference character type and return a Boolean indicating whether it is uppercase.

**Java Code**:
```java
private static boolean isReferenceUppercase(Character inputChar) {
    return Character.isUpperCase(inputChar);
}
```

**Dry Run**:
```java
// Input 1: inputChar = 'A'
// Character.isUpperCase('A') → true
// Return: true

// Input 2: inputChar = 'a'
// Character.isUpperCase('a') → false
// Return: false
```

---

#### Program 3.4: Format a phone number

**Description**: Take a 10-digit phone number from user input and return it with consistent formatting.

**Java Code**:
```java
private static String formatPhoneNumber(String phoneNumber) {
    String number = phoneNumber.trim();
    number = number.replaceAll("\\D", "");
    if (number.length() == 10) {
        String areaCode = number.substring(0, 3);
        String prefixCode = number.substring(3, 6);
        String lineCode = number.substring(6);
        StringBuilder returnVal = new StringBuilder("(");
        returnVal.append(areaCode);
        returnVal.append(")");
        returnVal.append(prefixCode);
        returnVal.append("-");
        returnVal.append(lineCode);
        return returnVal.toString();
    } else {
        return "INVALID NUMBER, 10 digits only!";
    }
}
```

**Dry Run**:
```java
// Input: phoneNumber = " (414) 452-4824 "
// trim() → "(414) 452-4824"
// replaceAll("\\D", "") → "4144524824"
// length = 10 → proceed
// areaCode = number.substring(0,3) → "414"
// prefixCode = number.substring(3,6) → "452"
// lineCode = number.substring(6) → "4824"
// returnVal = "(" + "414" + ")" + "452" + "-" + "4824" → "(414)452-4824"

// Input: phoneNumber = "414452482"
// trim() → "414452482"
// replaceAll("\\D", "") → "414452482"
// length = 9 → else branch
// Return: "INVALID NUMBER, 10 digits only!"
```

---

#### Program 3.5: Check if a state code is valid

**Description**: Take a 2-character state code and return whether it is valid (both uppercase letters).

**Java Code**:
```java
private static final int STATE_LENGTH = 2;

private static boolean isStateCodeValid(String state) {
    boolean returnVal = false;
    if (state.length() == STATE_LENGTH) {
        for (int counter = 0; counter < STATE_LENGTH; counter++) {
            if (Character.isUpperCase(state.charAt(counter))) {
                returnVal = true;
            } else {
                returnVal = false;
                break;
            }
        }
    }
    return returnVal;
}
```

**Dry Run**:
```java
// Input 1: state = "WI"
// state.length() = 2 → proceed
// counter = 0: Character.isUpperCase('W') → true, returnVal = true
// counter = 1: Character.isUpperCase('I') → true, returnVal = true
// Return: true

// Input 2: state = "wi"
// state.length() = 2 → proceed
// counter = 0: Character.isUpperCase('w') → false, returnVal = false, break
// Return: false
```

---

#### Program 3.6: Check if a state code is valid using regular expression

**Description**: Take a 2-character state code and (using a regular expression) return whether it is valid.

**Java Code**:
```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

private static boolean isStateCodeValidRegex(String state) {
    Pattern statePattern = Pattern.compile("[A-Z]{2}");
    Matcher stateMatcher = statePattern.matcher(state);
    return stateMatcher.matches();
}
```

**Dry Run**:
```java
// Input 1: state = "WI"
// Pattern: [A-Z]{2} matches exactly 2 uppercase letters
// stateMatcher.matches() → true
// Return: true

// Input 2: state = "wi"
// Pattern: [A-Z]{2} does not match "wi" (lowercase)
// stateMatcher.matches() → false
// Return: false
```

---

#### Program 3.7: Check if a string is a palindrome

**Description**: Determine if a string can be expressed the same when its characters are in reverse order.

**Java Code**:
```java
private static boolean isPalindrome(String word) {
    for (int index = 0; index < (word.length() / 2); index++) {
        char fromFront = word.charAt(index);
        char fromBack = word.charAt(word.length() - 1 - index);
        if (fromFront != fromBack) {
            return false;
        }
    }
    return true;
}
```

**Dry Run**:
```java
// Input: word = "madam"
// word.length() = 5, word.length()/2 = 2
// Iteration 1: index = 0
//   fromFront = word.charAt(0) = 'm'
//   fromBack = word.charAt(4) = 'm'
//   'm' != 'm'? false
// Iteration 2: index = 1
//   fromFront = word.charAt(1) = 'a'
//   fromBack = word.charAt(3) = 'a'
//   'a' != 'a'? false
// Loop completes
// Return: true

// Input: word = "hello"
// word.length() = 5, word.length()/2 = 2
// Iteration 1: index = 0
//   fromFront = 'h'
//   fromBack = 'o'
//   'h' != 'o'? true → return false
```

---

#### Program 3.8: Parse and format hockey team data

**Description**: Take a pipe-delimited row of hockey team data, parse out the team's name and location.

**Java Code**:
```java
private static String getValue(String property) {
    int colonPosition = property.indexOf(':');
    return property.substring(colonPosition + 2).trim();
}

private static String getFullTeamName(String teamData) {
    StringBuilder returnVal = new StringBuilder();
    String[] teamDataArray = teamData.split("[|]");
    StringBuilder name = new StringBuilder();
    StringBuilder location = new StringBuilder();

    for (int index = 0; index < teamDataArray.length; index++) {
        if (teamDataArray[index].contains("team:")) {
            name.append(getValue(teamDataArray[index]));
        }
        if (teamDataArray[index].contains("location:")) {
            location.append(getValue(teamDataArray[index]));
        }
    }

    returnVal.append(location);
    returnVal.append(" ");
    returnVal.append(name);
    return returnVal.toString();
}
```

**Dry Run**:
```java
// Input: teamData = "city: St. Paul, MN | team: Wild | location: Minnesota | colors: Green, White"
// split("[|]") → ["city: St. Paul, MN ", " team: Wild ", " location: Minnesota ", " colors: Green, White"]

// index = 0: contains("team:")? false, contains("location:")? false
// index = 1: contains("team:")? true → getValue(" team: Wild ") → "Wild" → name = "Wild"
//            contains("location:")? false
// index = 2: contains("team:")? false, contains("location:")? true → getValue(" location: Minnesota ") → "Minnesota" → location = "Minnesota"
// index = 3: contains("team:")? false, contains("location:")? false

// returnVal = "Minnesota" + " " + "Wild" → "Minnesota Wild"
// Return: "Minnesota Wild"
```

---

## Section 2: Intermediate Problems

### Chapter 4: Arithmetic Solutions

---

#### Program 4.1: Multiply two numbers

**Description**: Take two numbers and compute their product without using the multiplication (*) operator.

**Java Code**:
```java
public static int multiply(int numX, int numY) {
    if (numX == 0 || numY == 0) {
        return 0;
    }
    boolean isYNegative = false;
    int returnVal = 0;
    if (numY < 0) {
        isYNegative = true;
    }
    if (isYNegative) {
        for (int counter = 0; counter > numY; counter--) {
            returnVal -= numX;
        }
    } else {
        for (int counter = 0; counter < numY; counter++) {
            returnVal += numX;
        }
    }
    return returnVal;
}
```

**Dry Run**:
```java
// Input: multiply(3, 4)
// 3 != 0 && 4 != 0 → continue
// isYNegative = false, returnVal = 0
// numY = 4 (not < 0)
// isYNegative = false → else branch
// for loop: counter = 0, counter < 4
//   iteration 1: returnVal = 0 + 3 = 3, counter = 1
//   iteration 2: returnVal = 3 + 3 = 6, counter = 2
//   iteration 3: returnVal = 6 + 3 = 9, counter = 3
//   iteration 4: returnVal = 9 + 3 = 12, counter = 4 → loop ends
// Return: 12

// Input: multiply(3, -4)
// 3 != 0 && -4 != 0 → continue
// isYNegative = false, returnVal = 0
// numY = -4 < 0 → isYNegative = true
// isYNegative = true → first branch
// for loop: counter = 0, counter > -4
//   iteration 1: returnVal = 0 - 3 = -3, counter = -1
//   iteration 2: returnVal = -3 - 3 = -6, counter = -2
//   iteration 3: returnVal = -6 - 3 = -9, counter = -3
//   iteration 4: returnVal = -9 - 3 = -12, counter = -4 → loop ends
// Return: -12
```

---

#### Program 4.2: Subtract two numbers

**Description**: Take two numbers, subtract the second from the first, without using the subtraction (-) operator.

**Java Code**:
```java
private static int applyNegative(int number) {
    return multiply(number, -1);
}

private static int subtract(int numX, int numY) {
    return numX + applyNegative(numY);
}
```

**Dry Run**:
```java
// Input: subtract(10, 3)
// applyNegative(3) → multiply(3, -1) = -3
// numX + (-3) = 10 + (-3) = 7
// Return: 7

// Input: subtract(5, -2)
// applyNegative(-2) → multiply(-2, -1) = 2
// numX + 2 = 5 + 2 = 7
// Return: 7
```

---

#### Program 4.3: Divide two numbers

**Description**: Take two numbers, a dividend and a divisor, and compute their quotient without using the division (/) operator.

**Java Code**:
```java
private static int abs(int number) {
    if (number < 0) {
        return applyNegative(number);
    }
    return number;
}

private static int divide(int dividend, int divisor) {
    boolean resultShouldBeNegative = true;
    if ((dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0)) {
        resultShouldBeNegative = false;
    }
    int absoluteDividend = abs(dividend);
    int absoluteDivisor = abs(divisor);
    int total = 0;
    int quotient = 0;
    while ((total + absoluteDivisor) <= absoluteDividend) {
        total = total + absoluteDivisor;
        quotient++;
    }
    if (resultShouldBeNegative) {
        return applyNegative(quotient);
    }
    return quotient;
}
```

**Dry Run**:
```java
// Input: divide(10, 3)
// dividend = 10, divisor = 3
// (10 > 0 && 3 > 0) → true → resultShouldBeNegative = false
// absoluteDividend = abs(10) = 10
// absoluteDivisor = abs(3) = 3
// total = 0, quotient = 0
// while loop:
//   iteration 1: (0 + 3) = 3 <= 10 → total = 3, quotient = 1
//   iteration 2: (3 + 3) = 6 <= 10 → total = 6, quotient = 2
//   iteration 3: (6 + 3) = 9 <= 10 → total = 9, quotient = 3
//   iteration 4: (9 + 3) = 12 <= 10 → false, exit loop
// resultShouldBeNegative = false → return quotient = 3

// Input: divide(-10, 3)
// (-10 < 0 && 3 > 0) → false → resultShouldBeNegative = true
// absoluteDividend = abs(-10) = 10
// absoluteDivisor = abs(3) = 3
// while loop: same as above → quotient = 3
// resultShouldBeNegative = true → return applyNegative(3) = -3
// Return: -3
```

---

#### Program 4.4: Check if a number is a power of two

**Description**: Determine if a given number is a valid power of two.

**Java Code**:
```java
private static boolean isEven(int number) {
    if (number % 2 == 0) {
        return true;
    }
    return false;
}

private static boolean isPowerOfTwo(int number) {
    boolean returnVal = false;
    if (number == 2) {
        returnVal = true;
    } else if (number % 2 != 0) {
        returnVal = false;
    } else {
        returnVal = isPowerOfTwo(divide(number, 2));
    }
    return returnVal;
}
```

**Dry Run**:
```java
// Input: isPowerOfTwo(8)
// returnVal = false
// number == 2? false
// number % 2 != 0? 8 % 2 = 0 → false
// else: returnVal = isPowerOfTwo(divide(8, 2)) = isPowerOfTwo(4)
//   isPowerOfTwo(4):
//   number == 2? false
//   4 % 2 == 0 → else
//   returnVal = isPowerOfTwo(divide(4, 2)) = isPowerOfTwo(2)
//     isPowerOfTwo(2):
//     number == 2? true → returnVal = true
//   Return: true
// Return: true

// Input: isPowerOfTwo(6)
// number == 2? false
// number % 2 != 0? 6 % 2 = 0 → false
// else: returnVal = isPowerOfTwo(divide(6, 2)) = isPowerOfTwo(3)
//   isPowerOfTwo(3):
//   number == 2? false
//   3 % 2 != 0 → true → returnVal = false
// Return: false
```

---

#### Program 4.5: Build a binary error handler

**Description**: Build an error handling method that takes an integer as a parameter and decodes it into one or more error messages.

**Java Code**:
```java
public static int highestOrderBit(int number) {
    int value = 0;
    int divisor = 1;
    if (number == 0 || number == 1) {
        return number;
    }
    while (value != 1) {
        divisor = multiply(divisor, 2);
        value = divide(number, divisor);
    }
    return divisor;
}

public static boolean isBitFlipped(int number, int bit) {
    boolean returnVal = false;
    int value = number;
    int highBit = 0;
    do {
        value -= highBit;
        highBit = highestOrderBit(value);
    } while (highBit > bit);
    if (highBit == bit) {
        returnVal = true;
    }
    return returnVal;
}

private static String getErrorMessages(int errorNumber) {
    final int UNKNOWN_ERROR = 1;
    final int DIVIDE_BY_ZERO_ERROR = 2;
    final int INVALID_DATA_TYPE = 4;
    final int PARSING_ERROR = 8;
    StringBuilder returnVal = new StringBuilder();

    if (isBitFlipped(errorNumber, PARSING_ERROR)) {
        returnVal.append("PARSING_ERROR, ");
    }
    if (isBitFlipped(errorNumber, INVALID_DATA_TYPE)) {
        returnVal.append("INVALID_DATA_TYPE, ");
    }
    if (isBitFlipped(errorNumber, DIVIDE_BY_ZERO_ERROR)) {
        returnVal.append("DIVIDE_BY_ZERO_ERROR, ");
    }
    if (isBitFlipped(errorNumber, UNKNOWN_ERROR)) {
        returnVal.append("UNKNOWN_ERROR, ");
    }

    if (returnVal.length() > 0) {
        if (returnVal.charAt(returnVal.length() - 2) == ' ') {
            return returnVal.substring(0, returnVal.length() - 2) + "\n";
        } else {
            returnVal.append("\n");
        }
    } else {
        returnVal.append("\n");
    }
    return returnVal.toString();
}
```

**Dry Run**:
```java
// Input: errorNumber = 6 (binary: 110)
// isBitFlipped(6, 8) → 8 > 6 → false
// isBitFlipped(6, 4) → highestOrderBit(6) = 4 → true → returnVal = "INVALID_DATA_TYPE, "
// isBitFlipped(6, 2) → highestOrderBit(6) = 4, then 2 → true → returnVal = "INVALID_DATA_TYPE, DIVIDE_BY_ZERO_ERROR, "
// isBitFlipped(6, 1) → highestOrderBit(6) = 4, then 2, then 1 → false
// Remove trailing comma and space → "INVALID_DATA_TYPE, DIVIDE_BY_ZERO_ERROR\n"
// Return: "INVALID_DATA_TYPE, DIVIDE_BY_ZERO_ERROR"

// Input: errorNumber = 1 (binary: 1)
// isBitFlipped(1, 8) → false
// isBitFlipped(1, 4) → false
// isBitFlipped(1, 2) → false
// isBitFlipped(1, 1) → true → returnVal = "UNKNOWN_ERROR, "
// Remove trailing comma and space → "UNKNOWN_ERROR\n"
// Return: "UNKNOWN_ERROR"
```

---

### Chapter 5: Basic Data Structures

---

#### Program 5.1: Build a stack to process truck pallets

**Description**: Build a stack to accept and manage pallets of products for a retail store being loaded into a truck.

**Java Code**:
```java
public class Stack {
    private int maxCount = 0;
    private int palletCount = 0;
    private String[] pallets;

    public Stack(int maxPallets) {
        this.maxCount = maxPallets;
        pallets = new String[maxPallets];
    }

    public int getPalletCount() {
        return palletCount;
    }

    public String peek() {
        if (palletCount > 0) {
            return pallets[palletCount - 1];
        } else {
            return "EMPTY";
        }
    }

    public String pop() {
        if (palletCount > 0) {
            palletCount--;
            return pallets[palletCount];
        } else {
            return "EMPTY";
        }
    }

    public void push(String pallet) {
        if (palletCount < maxCount) {
            pallets[palletCount] = pallet;
            palletCount++;
        }
    }
}
```

**Dry Run**:
```java
// Stack truckStack = new Stack(8)
// maxCount = 8, palletCount = 0, pallets = new String[8]

// truckStack.push("41A - Housewares")
// palletCount(0) < maxCount(8) → true
// pallets[0] = "41A - Housewares", palletCount = 1

// truckStack.push("22F - Personal Care")
// palletCount(1) < 8 → true
// pallets[1] = "22F - Personal Care", palletCount = 2

// truckStack.peek()
// palletCount(2) > 0 → true
// return pallets[1] = "22F - Personal Care"

// truckStack.pop()
// palletCount(2) > 0 → true
// palletCount = 1
// return pallets[1] = "22F - Personal Care"

// truckStack.pop()
// palletCount(1) > 0 → true
// palletCount = 0
// return pallets[0] = "41A - Housewares"

// truckStack.pop()
// palletCount(0) > 0 → false
// return "EMPTY"
```

---

#### Program 5.2: Build a queue for airport takeoffs

**Description**: Build a queue to accept and manage the order of aircraft takeoffs for an airport.

**Java Code**:
```java
public class Queue {
    private String[] aircraft;
    private int currentCount = 0;

    public Queue(int maxCount) {
        aircraft = new String[maxCount];
    }

    public String enqueue(String name) {
        aircraft[currentCount] = name;
        currentCount++;
        return name;
    }

    public String getFront() {
        if (currentCount > 0) {
            return aircraft[0];
        } else {
            return "Queue is empty";
        }
    }

    public String dequeue() {
        if (currentCount > 0) {
            String front = getFront();
            for (int counter = 1; counter < currentCount; counter++) {
                aircraft[counter - 1] = aircraft[counter];
            }
            currentCount--;
            return front;
        } else {
            return "Queue is empty";
        }
    }
}
```

**Dry Run**:
```java
// Queue takeoff = new Queue(10)
// aircraft = new String[10], currentCount = 0

// takeoff.enqueue("DL2150")
// aircraft[0] = "DL2150", currentCount = 1, return "DL2150"

// takeoff.enqueue("AA1822")
// aircraft[1] = "AA1822", currentCount = 2, return "AA1822"

// takeoff.getFront()
// currentCount(2) > 0 → true
// return aircraft[0] = "DL2150"

// takeoff.dequeue()
// currentCount(2) > 0 → true
// front = getFront() = "DL2150"
// for loop: counter = 1 to 1
//   aircraft[0] = aircraft[1] = "AA1822"
// currentCount = 1
// return "DL2150"

// takeoff.enqueue("DL2205")
// aircraft[1] = "DL2205", currentCount = 2
```

---

#### Program 5.3: Build a linked list for navigation

**Description**: Build a linked list structure to manage step-by-step automotive driving directions.

**Java Code**:
```java
import java.util.UUID;

public class Step {
    private UUID id;
    private String description;
    private Step nextStep;

    public Step(UUID id, String desc) {
        this.id = id;
        this.description = desc;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Step getNextStep() {
        return nextStep;
    }

    public void setNextStep(Step nextStep) {
        this.nextStep = nextStep;
    }
}

public class LinkedList {
    private Step firstStep;
    private Step lastStep;
    private int stepCount = 0;

    public void addStep(Step step) {
        if (stepCount > 0) {
            Step currentLast = lastStep;
            currentLast.setNextStep(step);
            lastStep = step;
        } else {
            setFirstStep(step);
        }
        stepCount++;
    }

    public Step getFirstStep() {
        return firstStep;
    }

    public Step getLastStep() {
        return lastStep;
    }

    private void setFirstStep(Step step) {
        firstStep = step;
        lastStep = step;
    }
}

private static void printDirections(LinkedList directions) {
    Step currentStep = directions.getFirstStep();
    while (currentStep != null) {
        System.out.printf("%s\n", currentStep.getDescription());
        currentStep = currentStep.getNextStep();
    }
}
```

**Dry Run**:
```java
// LinkedList directions = new LinkedList()
// firstStep = null, lastStep = null, stepCount = 0

// Step step1 = new Step(UUID.randomUUID(), "After 0.1 miles, turn left...")
// directions.addStep(step1)
// stepCount(0) > 0 → false
// setFirstStep(step1): firstStep = step1, lastStep = step1
// stepCount = 1

// Step step2 = new Step(UUID.randomUUID(), "After 1.3 miles, turn right...")
// directions.addStep(step2)
// stepCount(1) > 0 → true
// currentLast = lastStep = step1
// currentLast.setNextStep(step2) → step1.nextStep = step2
// lastStep = step2
// stepCount = 2

// printDirections(directions)
// currentStep = getFirstStep() = step1
// while loop:
//   iteration 1: currentStep != null → print step1.description
//                currentStep = step1.nextStep = step2
//   iteration 2: currentStep != null → print step2.description
//                currentStep = step2.nextStep = null
//   iteration 3: currentStep == null → exit loop
```

---

### Chapter 6: Pattern Matching

---

#### Program 6.1: Determine a valid phone number

**Description**: Using regular expressions, build a method to check whether user-entered phone numbers are valid.

**Java Code**:
```java
import java.util.regex.Pattern;
import java.util.regex.Matcher;

Pattern phonePattern = Pattern.compile("[(.]*[0-9]{3}[.-]*[0-9]{3}[.-]*[0-9]{4}");

private static boolean isValidPhoneNumber(String number, Pattern pattern) {
    Matcher phoneMatcher = pattern.matcher(number);
    return phoneMatcher.matches();
}
```

**Dry Run**:
```java
// Pattern: [(.]*[0-9]{3}[.-]*[0-9]{3}[.-]*[0-9]{4}
// Meaning: optional parentheses, 3 digits, optional separator, 3 digits, optional separator, 4 digits

// Input: "4144524824"
// Matches pattern → true

// Input: "(313)472-3929"
// Matches pattern → true

// Input: "331234567" (only 9 digits)
// Does not match → false

// Input: "44-458-3831" (first group has 2 digits)
// Does not match → false
```

---

#### Program 6.2: Determine if a new password is valid

**Description**: Using regular expressions, validate a password with requirements: at least 1 lowercase, 1 uppercase, 1 number, between 12-25 characters.

**Java Code**:
```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

Pattern passwordPattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{12,25}");

private static boolean isValidPassword(String password, Pattern pattern) {
    Matcher passwordMatcher = pattern.matcher(password);
    return passwordMatcher.matches();
}
```

**Dry Run**:
```java
// Pattern: (?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{12,25}
// Meaning: lookahead for lowercase, uppercase, digit, then 12-25 characters

// Input: "blahblah" (8 chars, no uppercase, no digits)
// Does not match → false

// Input: "blahBlah@1218" (13 chars, has lowercase, uppercase, digits)
// Matches pattern → true

// Input: "correctHorseBattery" (20 chars, has lowercase and uppercase, no digits)
// Does not match → false
```

---

#### Program 6.3: Determine if an IP address is valid

**Description**: Using regular expressions, build a method to check if an IP address is valid.

**Java Code**:
```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

Pattern ipV4Pattern = Pattern.compile("^((25[0-5]|(2[0-4]|1[0-9]|[1-9])[0-9])\\.?\\b){4}$");

private static boolean isValidIPv4(String ipAddress, Pattern pattern) {
    Matcher addressMatcher = pattern.matcher(ipAddress);
    return addressMatcher.matches();
}
```

**Dry Run**:
```java
// Pattern: ^((25[0-5]|(2[0-4]|1[0-9]|[1-9])[0-9])\\.?\\b){4}$
// Meaning: 4 octets, each 0-255, separated by dots

// Input: "127.0.0.1"
// Matches pattern → true

// Input: "192.168.10.101"
// Matches pattern → true

// Input: "034.10.2.7" (leading zero)
// Does not match → false

// Input: "33.71.304.88" (304 > 255)
// Does not match → false

// Input: "192.168.0.01" (leading zero)
// Does not match → false
```

---

#### Program 6.4: Compute tollway charges by vehicle

**Description**: Build a program to compute appropriate charges for vehicles on a tollway.

**Java Code**:
```java
abstract class MotorVehicle {
    private String licensePlate;
    private String model;
    private String make;

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
}

public class Car extends MotorVehicle {
    public Car(String licensePlate, String make, String model) {
        this.setLicensePlate(licensePlate);
        this.setMake(make);
        this.setModel(model);
    }
}

public class Motorcycle extends MotorVehicle {
    public Motorcycle(String licensePlate, String make, String model) {
        this.setLicensePlate(licensePlate);
        this.setMake(make);
        this.setModel(model);
    }
}

public class Truck extends MotorVehicle {
    private int axles;
    private int cargoCapLbs;

    public Truck(String licensePlate, String make, String model, int axles, int cargoCapLbs) {
        this.setLicensePlate(licensePlate);
        this.setMake(make);
        this.setModel(model);
        this.axles = axles;
        this.cargoCapLbs = cargoCapLbs;
    }

    public int getAxles() { return axles; }
    public void setAxles(int axles) { this.axles = axles; }
    public int getCargoCapLbs() { return cargoCapLbs; }
    public void setCargoCapLbs(int cargoCapLbs) { this.cargoCapLbs = cargoCapLbs; }
}

import java.math.BigDecimal;
import java.math.RoundingMode;

public final static BigDecimal TOLL_PER_AXLE = new BigDecimal(7.8);

private static BigDecimal computeToll(MotorVehicle vehicle) {
    switch (vehicle) {
        case Motorcycle m:
            return new BigDecimal(2);
        case Car c:
            return new BigDecimal(4);
        case Truck t:
            int axles = t.getAxles();
            if (axles > 2) {
                return TOLL_PER_AXLE.multiply(BigDecimal.valueOf(axles))
                    .setScale(2, RoundingMode.HALF_EVEN);
            } else {
                return new BigDecimal(4);
            }
        default:
            return new BigDecimal(3);
    }
}
```

**Dry Run**:
```java
// Input: new Car("ABM-223", "Ford", "Mustang")
// computeToll(car1) → matches Car c → return new BigDecimal(4)

// Input: new Motorcycle("NP-2112", "BMW", "R1200")
// computeToll(cycle1) → matches Motorcycle m → return new BigDecimal(2)

// Input: new Truck("JLV-925", "Dodge", "Ram 2500", 2, 4000)
// computeToll(truck1) → matches Truck t
// axles = 2, axles > 2? false
// return new BigDecimal(4)

// Input: new Truck("TRO-227", "Volvo", "VNL64760", 5, 46000)
// computeToll(truck2) → matches Truck t
// axles = 5, axles > 2? true
// TOLL_PER_AXLE * 5 = 7.8 * 5 = 39.0 → setScale(2, HALF_EVEN) = 39.00
// return new BigDecimal(39.00)
```

---

## Section 3: Challenging Problems

### Chapter 7: Complex Data Structures

---

#### Program 7.1: Build a linked list for browser history

**Description**: Build a two-way linked list for a web browser's navigation history.

**Java Code**:
```java
import java.util.UUID;

public class HistoryItem {
    private UUID id;
    private String url;
    private HistoryItem nextHistoryItem;
    private HistoryItem previousHistoryItem;

    public HistoryItem(String url) {
        this.id = UUID.randomUUID();
        this.url = url;
    }

    public UUID getId() { return id; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public HistoryItem getNextHistoryItem() { return nextHistoryItem; }
    public void setNextHistoryItem(HistoryItem nextHistoryItem) { this.nextHistoryItem = nextHistoryItem; }
    public HistoryItem getPreviousHistoryItem() { return previousHistoryItem; }
    public void setPreviousHistoryItem(HistoryItem previousHistoryItem) { this.previousHistoryItem = previousHistoryItem; }
}

public class TwoWayLinkedList {
    private HistoryItem firstHistoryItem;
    private HistoryItem lastHistoryItem;
    private int historyItemCount = 0;

    public void addHistoryItem(HistoryItem histItem) {
        if (historyItemCount > 0) {
            HistoryItem currentLast = lastHistoryItem;
            currentLast.setNextHistoryItem(histItem);
            histItem.setPreviousHistoryItem(currentLast);
        } else {
            firstHistoryItem = histItem;
        }
        lastHistoryItem = histItem;
        historyItemCount++;
    }

    public HistoryItem getFirstHistoryItem() { return firstHistoryItem; }
    public HistoryItem getLastHistoryItem() { return lastHistoryItem; }
    public int getHistoryItemCount() { return this.historyItemCount; }
}

private static void traverseHistoryForward(TwoWayLinkedList browserHistory) {
    System.out.printf("\nTraversing browser history forward over %d items:\n",
        browserHistory.getHistoryItemCount());
    HistoryItem current = browserHistory.getFirstHistoryItem();
    while (current != null) {
        System.out.printf("%s\n", current.getUrl());
        current = current.getNextHistoryItem();
    }
}

private static void traverseHistoryBackward(TwoWayLinkedList browserHistory) {
    System.out.printf("\nTraversing browser history backward over %d items:\n",
        browserHistory.getHistoryItemCount());
    HistoryItem current = browserHistory.getLastHistoryItem();
    while (current != null) {
        System.out.printf("%s\n", current.getUrl());
        current = current.getPreviousHistoryItem();
    }
}
```

**Dry Run**:
```java
// TwoWayLinkedList browserHistory = new TwoWayLinkedList()

// HistoryItem item1 = new HistoryItem("https://stackoverflow.com/...")
// browserHistory.addHistoryItem(item1)
// historyItemCount(0) > 0 → false → firstHistoryItem = item1
// lastHistoryItem = item1, historyItemCount = 1

// HistoryItem item2 = new HistoryItem("https://stackoverflow.com/tagged/cassandra")
// browserHistory.addHistoryItem(item2)
// historyItemCount(1) > 0 → true
// currentLast = item1, currentLast.setNextHistoryItem(item2) → item1.next = item2
// item2.setPreviousHistoryItem(item1) → item2.prev = item1
// lastHistoryItem = item2, historyItemCount = 2

// traverseHistoryForward(browserHistory)
// current = item1
// while: print item1.url, current = item1.next = item2
// while: print item2.url, current = item2.next = null
// exit

// traverseHistoryBackward(browserHistory)
// current = item2
// while: print item2.url, current = item2.prev = item1
// while: print item1.url, current = item1.prev = null
// exit
```

---

#### Program 7.2: Build a binary tree to store fruit data

**Description**: Build a binary tree as an underlying structure to store data for a simple fruit application.

**Java Code**:
```java
public class Node {
    private int key;
    private String name;
    private Node leftChild;
    private Node rightChild;

    public Node(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public void insert(Node newNode) {
        if (newNode.getKey() < this.key) {
            if (leftChild == null) {
                leftChild = newNode;
            } else {
                leftChild.insert(newNode);
            }
        } else if (newNode.getKey() > this.key) {
            if (rightChild == null) {
                rightChild = newNode;
            } else {
                rightChild.insert(newNode);
            }
        } else {
            this.name = newNode.getName();
        }
    }

    public Node findByKey(int lookupKey) {
        if (lookupKey < this.key) {
            if (leftChild == null) {
                return new Node(-1, "NOT FOUND");
            } else {
                return leftChild.findByKey(lookupKey);
            }
        } else if (lookupKey > this.key) {
            if (rightChild == null) {
                return new Node(-1, "NOT FOUND");
            } else {
                return rightChild.findByKey(lookupKey);
            }
        } else {
            return this;
        }
    }

    public int getKey() { return key; }
    public void setKey(int key) { this.key = key; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

public class BinaryTree {
    private Node root;

    public void insert(Node newNode) {
        if (root == null) {
            root = newNode;
        } else {
            root.insert(newNode);
        }
    }

    public Node find(int key) {
        return root.findByKey(key);
    }
}
```

**Dry Run**:
```java
// Node fruit1 = new Node(0, "apple")
// Node fruit2 = new Node(1, "banana")
// Node fruit3 = new Node(2, "orange")
// Node fruit4 = new Node(3, "grape")
// Node fruit5 = new Node(4, "grapefruit")

// BinaryTree tree = new BinaryTree()
// tree.insert(fruit1) → root = fruit1 (key=0)

// tree.insert(fruit2) → root.insert(fruit2)
//   2 > 0 → rightChild = fruit2 (key=1)

// tree.insert(fruit3) → root.insert(fruit3)
//   3 > 0 → rightChild.insert(fruit3)
//   3 > 1 → rightChild = fruit3 (key=2)

// tree.find(1)
// root.findByKey(1)
//   1 > 0 → rightChild.findByKey(1)
//   1 == 1 → return fruit2
// Returns fruit2 (banana)

// tree.find(7)
// root.findByKey(7)
//   7 > 0 → rightChild.findByKey(7)
//   7 > 1 → rightChild.findByKey(7)
//   7 > 2 → rightChild.findByKey(7)
//   rightChild == null → return new Node(-1, "NOT FOUND")
// Returns "NOT FOUND"
```

---

#### Program 7.3: Build a heap for a medical queue

**Description**: Using Java's PriorityQueue library, build a heap structure to support priority queuing for medical patients.

**Java Code**:
```java
public class Patient {
    private String name;
    private int priority;

    public Patient(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
}

import java.util.Comparator;

public class PatientComparator implements Comparator<Patient> {
    @Override
    public int compare(Patient p1, Patient p2) {
        if (p1.getPriority() > p2.getPriority()) {
            return 1;
        } else if (p1.getPriority() < p2.getPriority()) {
            return -1;
        } else {
            return 0;
        }
    }
}

import java.util.PriorityQueue;

public class MedicalHeapJava {
    PriorityQueue<Patient> patientHeap;

    public MedicalHeapJava(Patient[] patientArray) {
        patientHeap = new PriorityQueue<>(new PatientComparator());
        for (Patient patient : patientArray) {
            patientHeap.add(patient);
        }
    }

    public void push(Patient patient) {
        patientHeap.add(patient);
    }

    public Patient pop() {
        return patientHeap.poll();
    }

    public String toString() {
        StringBuilder returnVal = new StringBuilder("[ ");
        boolean first = true;
        for (Patient patient : patientHeap) {
            if (!first) {
                returnVal.append(", ");
            } else {
                first = false;
            }
            returnVal.append(patient.getName());
            returnVal.append(":");
            returnVal.append(patient.getPriority());
        }
        returnVal.append(" ]");
        return returnVal.toString();
    }
}
```

**Dry Run**:
```java
// Patient[] patients = {
//   new Patient("Paul", 3),
//   new Patient("Jenny", 2),
//   new Patient("Tom", 8),
//   new Patient("Jamie", 1),
//   new Patient("Tiffany", 11)
// }

// MedicalHeapJava medQueue = new MedicalHeapJava(patients)
// PriorityQueue automatically heapifies
// Heap order (min-heap): Jamie:1 (root), Jenny:2, Tom:8, Paul:3, Tiffany:11

// medQueue.push(new Patient("Robert", 4))
// Heap: Jamie:1, Jenny:2, Robert:4, Paul:3, Tiffany:11, Tom:8

// medQueue.pop() → removes root (Jamie:1), heapifies
// Heap: Jenny:2, Paul:3, Robert:4, Tom:8, Tiffany:11

// medQueue.push(new Patient("Ron", 5))
// Heap: Jenny:2, Paul:3, Robert:4, Tom:8, Tiffany:11, Ron:5

// medQueue.pop() → removes Jenny:2
// Heap: Paul:3, Ron:5, Robert:4, Tom:8, Tiffany:11

// medQueue.pop() → removes Paul:3
// Heap: Robert:4, Ron:5, Tiffany:11, Tom:8

// medQueue.pop() → removes Robert:4
// Heap: Ron:5, Tom:8, Tiffany:11

// medQueue.pop() → removes Ron:5
// Heap: Tom:8, Tiffany:11

// medQueue.pop() → removes Tom:8
// Heap: Tiffany:11
```

---

### Chapter 8: Sorting

---

#### Program 8.1: Sort users with a bubble sort

**Description**: Sort an array of user's last names using a bubble sort.

**Java Code**:
```java
private static String[] bubbleSort(String[] array) {
    boolean flippedAPair = false;
    int index = 0;
    while (index < array.length - 1) {
        if (array[index].compareTo(array[index + 1]) > 0) {
            String temp = array[index];
            array[index] = array[index + 1];
            array[index + 1] = temp;
            flippedAPair = true;
        }
        index++;
        if (index >= (array.length - 1) && flippedAPair) {
            index = 0;
            flippedAPair = false;
        }
    }
    return array;
}

private static void printStringArray(String[] array) {
    for (String item : array) {
        System.out.println(item);
    }
    System.out.println();
}
```

**Dry Run**:
```java
// Input: users = ["Lowell", "Jacobs", "Scott", "Tripp", "Gartman"]
// flippedAPair = false, index = 0

// Pass 1:
// index=0: "Lowell".compareTo("Jacobs") > 0 → swap → ["Jacobs", "Lowell", "Scott", "Tripp", "Gartman"], flippedAPair=true
// index=1: "Lowell".compareTo("Scott") > 0? false
// index=2: "Scott".compareTo("Tripp") > 0? false
// index=3: "Tripp".compareTo("Gartman") > 0 → swap → ["Jacobs", "Lowell", "Scott", "Gartman", "Tripp"], flippedAPair=true
// index=4: index >= 4 && flippedAPair → reset index=0, flippedAPair=false

// Pass 2:
// index=0: "Jacobs".compareTo("Lowell") > 0? false
// index=1: "Lowell".compareTo("Scott") > 0? false
// index=2: "Scott".compareTo("Gartman") > 0 → swap → ["Jacobs", "Lowell", "Gartman", "Scott", "Tripp"], flippedAPair=true
// index=3: "Scott".compareTo("Tripp") > 0? false
// index=4: reset

// Pass 3:
// index=0: "Jacobs".compareTo("Lowell") > 0? false
// index=1: "Lowell".compareTo("Gartman") > 0 → swap → ["Jacobs", "Gartman", "Lowell", "Scott", "Tripp"], flippedAPair=true
// index=2: "Lowell".compareTo("Scott") > 0? false
// index=3: "Scott".compareTo("Tripp") > 0? false
// index=4: reset

// Pass 4:
// index=0: "Jacobs".compareTo("Gartman") > 0 → swap → ["Gartman", "Jacobs", "Lowell", "Scott", "Tripp"], flippedAPair=true
// index=1: "Jacobs".compareTo("Lowell") > 0? false
// index=2: "Lowell".compareTo("Scott") > 0? false
// index=3: "Scott".compareTo("Tripp") > 0? false
// index=4: reset

// Pass 5:
// index=0: "Gartman".compareTo("Jacobs") > 0? false
// index=1: "Jacobs".compareTo("Lowell") > 0? false
// index=2: "Lowell".compareTo("Scott") > 0? false
// index=3: "Scott".compareTo("Tripp") > 0? false
// index=4: index >= 4 && flippedAPair? false → exit loop
// Return: ["Gartman", "Jacobs", "Lowell", "Scott", "Tripp"]
```

---

#### Program 8.2: Sort users with a merge sort

**Description**: Sort an array of user's last names using a merge sort.

**Java Code**:
```java
private static String[] splitArray(String[] array, int start, int end) {
    String[] returnVal = new String[end - start];
    int returnIndex = 0;
    for (int index = start; index < end; index++) {
        returnVal[returnIndex] = array[index];
        returnIndex++;
    }
    return returnVal;
}

private static String[] mergeSort(String[] array) {
    if (array.length > 1) {
        int middle = (array.length / 2);
        String[] leftArray = splitArray(array, 0, middle);
        String[] rightArray = splitArray(array, middle, array.length);

        leftArray = mergeSort(leftArray);
        rightArray = mergeSort(rightArray);

        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = 0;

        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (leftArray[leftIndex].compareTo(rightArray[rightIndex]) < 0) {
                array[mergedIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                array[mergedIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            mergedIndex++;
        }

        for (int index = leftIndex; index < leftArray.length; index++) {
            array[mergedIndex] = leftArray[index];
            mergedIndex++;
        }

        for (int index = rightIndex; index < rightArray.length; index++) {
            array[mergedIndex] = rightArray[index];
            mergedIndex++;
        }
    }
    return array;
}
```

**Dry Run**:
```java
// Input: users = ["Lowell", "Jacobs", "Scott", "Tripp", "Gartman"]

// mergeSort(["Lowell", "Jacobs", "Scott", "Tripp", "Gartman"])
// middle = 2
// leftArray = ["Lowell", "Jacobs"], rightArray = ["Scott", "Tripp", "Gartman"]
// leftArray = mergeSort(["Lowell", "Jacobs"])
//   middle = 1
//   leftArray = ["Lowell"], rightArray = ["Jacobs"]
//   leftArray = mergeSort(["Lowell"]) → return ["Lowell"]
//   rightArray = mergeSort(["Jacobs"]) → return ["Jacobs"]
//   merge:
//     leftIndex=0, rightIndex=0, mergedIndex=0
//     "Lowell".compareTo("Jacobs") < 0? false → array[0] = "Jacobs", rightIndex=1, mergedIndex=1
//     leftIndex(0) < 1 && rightIndex(1) < 1 → false
//     copy remaining from left: array[1] = "Lowell"
//   return ["Jacobs", "Lowell"]

// rightArray = mergeSort(["Scott", "Tripp", "Gartman"])
//   middle = 1
//   leftArray = ["Scott"], rightArray = ["Tripp", "Gartman"]
//   leftArray = mergeSort(["Scott"]) → ["Scott"]
//   rightArray = mergeSort(["Tripp", "Gartman"])
//     middle = 1
//     leftArray = ["Tripp"], rightArray = ["Gartman"]
//     leftArray = mergeSort(["Tripp"]) → ["Tripp"]
//     rightArray = mergeSort(["Gartman"]) → ["Gartman"]
//     merge: ["Gartman", "Tripp"]
//   merge rightArray: ["Scott"] and ["Gartman", "Tripp"]
//   merge: ["Gartman", "Scott", "Tripp"]

// Merge left = ["Jacobs", "Lowell"] and right = ["Gartman", "Scott", "Tripp"]
// Final: ["Gartman", "Jacobs", "Lowell", "Scott", "Tripp"]
```

---

#### Program 8.3: Sort with a binary tree sort

**Description**: Sort players from a hockey team by their last name using a binary tree.

**Java Code**:
```java
public class Player {
    private int jersey;
    private String lastName;
    private String firstName;
    private String position;
    private Player leftNode;
    private Player rightNode;

    public Player(int jersey, String lName, String fName, String pos) {
        this.jersey = jersey;
        this.lastName = lName;
        this.firstName = fName;
        this.position = pos;
    }

    public void insert(Player newNode) {
        if ((newNode.getFullName()).compareTo(getFullName()) < 0) {
            if (leftNode == null) {
                leftNode = newNode;
            } else {
                leftNode.insert(newNode);
            }
        } else if ((newNode.getFullName()).compareTo(getFullName()) > 0) {
            if (rightNode == null) {
                rightNode = newNode;
            } else {
                rightNode.insert(newNode);
            }
        } else {
            this.jersey = newNode.getJersey();
            this.position = newNode.getPosition();
        }
    }

    public String getFullName() { return lastName + firstName; }
    public int getJersey() { return jersey; }
    public void setJersey(int jersey) { this.jersey = jersey; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public Player getLeftNode() { return this.leftNode; }
    public Player getRightNode() { return this.rightNode; }

    public String toString() {
        StringBuilder returnVal = new StringBuilder();
        returnVal.append(jersey);
        returnVal.append(" ");
        returnVal.append(firstName);
        returnVal.append(" ");
        returnVal.append(lastName);
        returnVal.append(" - ");
        returnVal.append(position);
        return returnVal.toString();
    }
}

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private Player root;
    private List<Player> playerList;

    public void insert(Player newNode) {
        if (root == null) {
            root = newNode;
        } else {
            root.insert(newNode);
        }
    }

    public List<Player> toList() {
        if (playerList == null) {
            playerList = new ArrayList<>();
        } else {
            playerList.clear();
        }
        return getNodesAsList(root);
    }

    private List<Player> getNodesAsList(Player node) {
        List<Player> tempList = new ArrayList<>();
        if (node != null) {
            tempList.addAll(getNodesAsList(node.getLeftNode()));
            tempList.add(node);
            tempList.addAll(getNodesAsList(node.getRightNode()));
        }
        return tempList;
    }
}

private static void printPlayerList(List<Player> list) {
    for (Player player : list) {
        System.out.println(player);
    }
    System.out.println();
}
```

**Dry Run**:
```java
// Input: Players sorted by last name
// Player(66, "Lemieux", "Mario", "C")
// Player(25, "Stevens", "Kevin", "LW")
// Player(68, "Jagr", "Jaromir", "RW")
// Player(55, "Murphy", "Larry", "D")

// BinaryTree tree = new BinaryTree()

// insert(66, "Lemieux") → root = Lemieux

// insert(68, "Jagr")
// "Jagr".compareTo("Lemieux") < 0 → leftNode = Jagr

// insert(55, "Murphy")
// "Murphy".compareTo("Lemieux") < 0 → leftNode.insert(Murphy)
// "Murphy".compareTo("Jagr") > 0 → rightNode = Murphy

// insert(25, "Stevens")
// "Stevens".compareTo("Lemieux") > 0 → rightNode = Stevens

// toList(): In-order traversal
// getNodesAsList(root=Lemieux)
//   getNodesAsList(left=Jagr)
//     getNodesAsList(left=null) → []
//     add(Jagr) → [Jagr]
//     getNodesAsList(right=Murphy)
//       getNodesAsList(left=null) → []
//       add(Murphy) → [Murphy]
//       getNodesAsList(right=null) → []
//     return [Jagr, Murphy]
//   add(Lemieux) → [Jagr, Murphy, Lemieux]
//   getNodesAsList(right=Stevens)
//     getNodesAsList(left=null) → []
//     add(Stevens) → [Stevens]
//     getNodesAsList(right=null) → []
//     return [Stevens]
//   return [Jagr, Lemieux, Murphy, Stevens]

// Output:
// 68 Jaromir Jagr - RW
// 66 Mario Lemieux - C
// 55 Larry Murphy - D
// 25 Kevin Stevens - LW
```

---

### Chapter 9: Graphics

---

#### Program 9.1: Draw the moon with a starfield

**Description**: Draw a crescent moon with a randomly generated field of stars.

**Java Code**:
```java
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;

public class StarfieldPanel extends JPanel {
    private static final long serialVersionUID = 6361841055806902341L;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private int stars;

    public StarfieldPanel(int stars) {
        this.stars = stars;
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        Random randomInts = new Random();

        for (int starCounter = 0; starCounter < stars; starCounter++) {
            int x = randomInts.nextInt(0, WIDTH);
            int y = randomInts.nextInt(0, HEIGHT);
            g2.drawLine(x, y, x+1, y+1);
        }

        g2.fillOval(600, 100, 100, 100);
        g2.setColor(Color.BLACK);
        g2.fillOval(580, 90, 100, 100);
        g2.dispose();
    }
}
```

**Dry Run**:
```java
// StarfieldPanel panel = new StarfieldPanel(1500)
// stars = 1500, WIDTH = 1000, HEIGHT = 800

// paintComponent called:
// g2.setColor(Color.WHITE)
// randomInts = new Random()

// for starCounter = 0 to 1499:
//   x = random 0-999, y = random 0-799
//   g2.drawLine(x, y, x+1, y+1) → draws a small star

// g2.fillOval(600, 100, 100, 100) → draws a white circle at (600,100) with size 100x100
// g2.setColor(Color.BLACK)
// g2.fillOval(580, 90, 100, 100) → draws a black circle at (580,90) with size 100x100
// Creates crescent moon effect (black circle covers part of white circle)
```

---

#### Program 9.2: Animate a bouncing ball

**Description**: Draw a ball on a panel, start its movement in a random direction, and have it bounce off the edges.

**Java Code**:
```java
import java.awt.Color;
import java.util.Random;

public class Ball {
    private boolean movingUp;
    private boolean movingLeft;
    private int xCoord;
    private int yCoord;
    private int size;
    private int speed;
    private Color color;

    public Ball(int size, int speed, int xCoord, int yCoord, Color color) {
        Random booleanDirection = new Random();
        movingLeft = booleanDirection.nextBoolean();
        movingUp = booleanDirection.nextBoolean();
        this.size = size;
        this.speed = speed;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.color = color;
    }

    // Getters and setters omitted for brevity

    public void update() {
        if (movingLeft) {
            xCoord -= speed;
        } else {
            xCoord += speed;
        }
        if (movingUp) {
            yCoord -= speed;
        } else {
            yCoord += speed;
        }
    }
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JPanel;

public class BallPanel extends JPanel implements Runnable {
    private static final long serialVersionUID = 2043779718874812497L;
    private final int fPS = 60;
    private int width;
    private int height;
    private Ball ball;
    private Thread panelThread;

    public BallPanel(int width, int height) {
        Random randomInts = new Random();
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.panelThread = Thread.ofVirtual().unstarted(this);
        ball = new Ball(50, 10,
            randomInts.nextInt(0, width),
            randomInts.nextInt(0, height),
            Color.CYAN);
    }

    @Override
    public void run() {
        while (panelThread.isAlive()) {
            update();
            repaint();
            try {
                Thread.sleep(1000 / fPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        panelThread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(ball.getColor());
        g2.fillOval(ball.getxCoord(), ball.getyCoord(),
            ball.getSize(), ball.getSize());
        g2.dispose();
    }

    private void update() {
        checkCollision();
        ball.update();
    }

    private void checkCollision() {
        if (ball.getxCoord() <= 0 && ball.isMovingLeft()) {
            ball.setMovingLeft(false);
        } else if (ball.getxCoord() + ball.getSize() >= width
                && !ball.isMovingLeft()) {
            ball.setMovingLeft(true);
        }
        if (ball.getyCoord() <= 0 && ball.isMovingUp()) {
            ball.setMovingUp(false);
        } else if (ball.getyCoord() + ball.getSize() >= height
                && !ball.isMovingUp()) {
            ball.setMovingUp(true);
        }
    }
}
```

**Dry Run**:
```java
// BallPanel panel = new BallPanel(1000, 800)
// width = 1000, height = 800, ball at random position

// panel.start() → panelThread.start() → calls run()

// run() loop (60 FPS):
// iteration 1:
//   update(): checkCollision() → ball.update()
//   repaint(): paintComponent() draws ball at new position
//   sleep(16.67ms)

// Ball.update() example:
// Initial position: (100, 100), movingLeft=true, movingUp=true, speed=10
// update() called:
//   movingLeft=true → xCoord = 100 - 10 = 90
//   movingUp=true → yCoord = 100 - 10 = 90

// checkCollision() example:
// Ball position: (0, 100), movingLeft=true
// xCoord <= 0 && movingLeft → setMovingLeft(false)
// Ball now moves right

// Ball position: (980, 100), size=50, movingLeft=false
// xCoord + size >= width → 980 + 50 = 1030 >= 1000 → setMovingLeft(true)
// Ball now moves left
```

---

#### Program 9.3: Animate multiple bouncing balls

**Description**: Draw multiple balls on a panel, start their movements in random directions, and have them bounce off the edges and each other.

**Java Code**:
```java
// Ball class reused from Program 9.2

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

public class MultipleBallPanel extends JPanel implements Runnable {
    private static final long serialVersionUID = 2043779718874812497L;
    private final int fPS = 60;
    private int width;
    private int height;
    private Thread panelThread;
    private List<Ball> ballList;
    private Random randomInts;

    public MultipleBallPanel(int width, int height, int numBalls) {
        randomInts = new Random();
        ballList = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.panelThread = Thread.ofVirtual().unstarted(this);

        for (int ballCounter = 0; ballCounter < numBalls; ballCounter++) {
            ballList.add(new Ball(50, 10,
                randomInts.nextInt(0, width),
                randomInts.nextInt(0, height),
                randomColor()));
        }
    }

    @Override
    public void run() {
        while (panelThread.isAlive()) {
            update();
            repaint();
            try {
                Thread.sleep(1000 / fPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        panelThread.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for (Ball ball : ballList) {
            g2.setColor(ball.getColor());
            g2.fillOval(ball.getxCoord(), ball.getyCoord(),
                ball.getSize(), ball.getSize());
        }
        g2.dispose();
    }

    private Color randomColor() {
        int red = randomInts.nextInt(0, 256);
        int green = randomInts.nextInt(0, 256);
        int blue = randomInts.nextInt(0, 256);
        return new Color(red, green, blue);
    }

    private void update() {
        for (Ball ball : ballList) {
            checkWallCollision(ball);
            checkBallCollision(ball);
            ball.update();
        }
    }

    private void checkWallCollision(Ball ball) {
        if (ball.getxCoord() <= 0 && ball.isMovingLeft()) {
            ball.setMovingLeft(false);
        } else if (ball.getxCoord() + ball.getSize() >= width
                && !ball.isMovingLeft()) {
            ball.setMovingLeft(true);
        }
        if (ball.getyCoord() <= 0 && ball.isMovingUp()) {
            ball.setMovingUp(false);
        } else if (ball.getyCoord() + ball.getSize() >= height
                && !ball.isMovingUp()) {
            ball.setMovingUp(true);
        }
    }

    private void checkBallCollision(Ball ball) {
        for (Ball otherBall : ballList) {
            if (otherBall.getColor() != ball.getColor()) {
                if (ball.getxCoord() <= otherBall.getxCoord() + ball.getSize()
                    && ball.getxCoord() >= otherBall.getxCoord()
                    && ball.getyCoord() <= otherBall.getyCoord() + ball.getSize()
                    && ball.getyCoord() >= otherBall.getyCoord()) {

                    if (ball.isMovingLeft()) {
                        ball.setMovingLeft(false);
                    } else {
                        ball.setMovingLeft(true);
                    }
                    if (ball.isMovingUp()) {
                        ball.setMovingUp(false);
                    } else {
                        ball.setMovingUp(true);
                    }
                }
            }
        }
    }
}
```

**Dry Run**:
```java
// MultipleBallPanel panel = new MultipleBallPanel(1000, 800, 20)
// Creates 20 balls at random positions with random colors

// run() loop (60 FPS):
// update() called for each ball:
//   checkWallCollision(ball)
//   checkBallCollision(ball)
//   ball.update()

// checkBallCollision example:
// Ball A at (100, 100), size=50, movingLeft=true, movingUp=true
// Ball B at (120, 120), size=50, movingLeft=false, movingUp=false
// Collision detection:
//   A.x(100) <= B.x(120)+50(170) → true
//   A.x(100) >= B.x(120) → false
//   No collision (100 < 120, A is left of B)

// Ball A at (100, 100)
// Ball B at (80, 80)
// Collision detection:
//   A.x(100) <= B.x(80)+50(130) → true
//   A.x(100) >= B.x(80) → true
//   A.y(100) <= B.y(80)+50(130) → true
//   A.y(100) >= B.y(80) → true
//   All conditions met → collision!
//   Ball A reverses direction: movingLeft becomes false, movingUp becomes false
```

---

This completes the extraction of all programs from the book "Think Like a Programmer" with their descriptions and dry run outputs.
