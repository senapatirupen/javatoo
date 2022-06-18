package com.example.javatoo.designpattern.behavioraldesignpatterns.chainofresponsibility;

public interface UniversityEmailHandler {
    public void setNextEmailHandler(UniversityEmailHandler emailHandler);

    public void processEmailHandler(String emailText);
}
