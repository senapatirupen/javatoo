package com.example.javatoo.designpattern.factorydesignpattern;

import java.util.function.Supplier;

public class MainTest {
    public static void main(String[] args) {
        Fruit gacFruit = new MelonFactory().getInstance(Gac.class);
        Supplier<Fruit> gacFruit1 = Gac::new;
    }
}
