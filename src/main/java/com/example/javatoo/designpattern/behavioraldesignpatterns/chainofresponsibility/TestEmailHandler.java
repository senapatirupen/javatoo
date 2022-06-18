package com.example.javatoo.designpattern.behavioraldesignpatterns.chainofresponsibility;

public class TestEmailHandler {
    public static void main(String[] args) {
        MainEmailHandler mainEmailHandler = new AcademicEmailHandler();
//        mainEmailHandler.theNextHandlerInTheChain = new AlumniEmailHandler();
        mainEmailHandler.handleEmail("academic");
    }
}
