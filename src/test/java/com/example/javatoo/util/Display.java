package com.example.javatoo.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Display {
    public static void displayIntArray(int array[]){
        for(int i: array){
            log.info("int array: "+ i);
        }
    }
}
