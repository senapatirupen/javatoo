package com.example.javatoo.algorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringAlgorithmsMain {
    public static void main(String[] args) {
        new StringAlgorithmsMain().reverseString("new one");
        String reverse = new StringAlgorithmsMain().reverseStringRecursive("new one");
        log.info("reverseStringRecursive " + reverse);
        new StringAlgorithmsMain().bubbleSort();
    }

    //reverse string using charAt()
    public String reverseString(String args) {
        String reverse = "";
        for (int i = args.length() - 1; i >= 0; i--) {
            reverse += args.charAt(i);
        }
        log.info("reverseString " + reverse);
        return reverse;
    }

    //string reverse using recursive
    public String reverseStringRecursive(String args) {
        if (args.length() == 1)
            return args;
        else
            return args.charAt(args.length() - 1) + reverseStringRecursive(args.substring(0, args.length() - 1));
    }

    //bubble sort
    public void bubbleSort(){
        String arr[] = {
                "Now", "is", "the", "time", "for", "all", "good", "men",
                "to", "come", "to", "the", "aid", "of", "their", "country"
        };
        for(int j = 0; j < arr.length; j++) {
            for (int i = j + 1; i < arr.length; i++) {
                if (arr[i].compareTo(arr[j]) < 0) {
                    String t = arr[j];
                    arr[j] = arr[i];
                    arr[i] = t;
                }
            }
            log.info(arr[j]);
        }
    }
}
