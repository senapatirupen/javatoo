package com.example.javatoo.basic.javacollections;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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

    @Test
    public void arrayListToArray(){
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        Integer[] numbersArray = new Integer[numbers.size()];
        numbersArray = numbers.toArray(numbersArray);
        //sum of the array
        Integer sum = 0;
        for(Integer n: numbersArray) sum += n;
        log.info("display the content: " + numbers);
        log.info("sum of integers: "+ sum);
    }

    @Test
    public void linkedListTest(){
        LinkedList<String> names = new LinkedList<String>();
        names.add("B");
        names.add("C");
        names.add("D");
        names.addFirst("A");
        names.addLast("E");
        log.info("display the content: " + names);
        names.add(1, "A1");
        names.set(3, "B1");
        log.info("display the content: " + names);
        names.remove(1);
        names.removeLast();
        names.removeFirst();
        log.info("display the content: " + names);
    }
}
