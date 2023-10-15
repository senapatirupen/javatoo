package com.example.javatoo.designpattern.behavioraldesignpatterns.others;

public class HugeMelonPredicate implements MelonPredicate{

    @Override
    public boolean test(Melon melon) {
        if(melon != null && melon.getWeight() > 10000){
            return true;
        }
        return false;
    }
}
