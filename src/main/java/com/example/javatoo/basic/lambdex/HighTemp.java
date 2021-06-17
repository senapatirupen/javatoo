package com.example.javatoo.basic.lambdex;

public class HighTemp {

    private int hTemp;

    HighTemp(int ht) {
        this.hTemp = ht;
    }

    //return if having same temperature
    boolean sameTemp(HighTemp ht2) {
        return hTemp == ht2.hTemp;
    }

    //return if having less than temperature
    boolean lessThanTemp(HighTemp ht2) {
        return hTemp < ht2.hTemp;
    }
}
