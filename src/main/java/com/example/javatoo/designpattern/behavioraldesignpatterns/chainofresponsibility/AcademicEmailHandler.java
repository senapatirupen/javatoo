package com.example.javatoo.designpattern.behavioraldesignpatterns.chainofresponsibility;

public class AcademicEmailHandler extends MainEmailHandler {
    @Override
    protected String[] keyWords() {
        // setup keywords for the receiver team
        return new String[]{"academic"};
    }

    @Override
    protected void processEmailFinal(String emailText) {
        System.out.println("The Academic Team processed the email.");
    }

    @Override
    public void setNextEmailHandler(UniversityEmailHandler emailHandler) {

    }
}
