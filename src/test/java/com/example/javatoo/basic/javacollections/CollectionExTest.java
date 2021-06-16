package com.example.javatoo.basic.javacollections;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
@Slf4j
public class CollectionExTest {
//    @BeforeAll
//    static void setup() {
//        log.info("@BeforeAll - executes once before all test methods in this class");
//    }
//
//    @BeforeEach
//    void init() {
//        log.info("@BeforeEach - executes before each test method in this class");
//    }
    @Test
    public void addElementToArrayList(){
        ArrayList<String> listOfNames = new ArrayList<>();
        listOfNames.add("A");
        listOfNames.add("B");
        listOfNames.add("C");
        listOfNames.add("D");
        listOfNames.add("E");
        log.info("names size: " + listOfNames.size());
        log.info("display the content: " + listOfNames);
        listOfNames.remove("E");
        listOfNames.remove(2);
        log.info("display the content: " + listOfNames);
    }
}
