package com.example.javatoo.designpattern.behavioraldesignpatterns.others;

public class GacMelonPredicate implements MelonPredicate{

    @Override
    public boolean test(Melon melon) {
        if(melon != null && "GAC".equalsIgnoreCase(melon.getType())){
            return true;
        }
        return false;
    }
}
