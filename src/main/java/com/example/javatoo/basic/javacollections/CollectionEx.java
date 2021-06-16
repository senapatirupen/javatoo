package com.example.javatoo.basic.javacollections;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
@Slf4j
public class CollectionEx {
    public static void main(String[] args) {
        new CollectionEx().addElementToArrayList();
    }
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
