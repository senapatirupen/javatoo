package com.example.javatoo.designpattern.factorydesignpattern;

import java.util.Map;
import java.util.function.Supplier;

public class MelonFactory {

    public Map<String, Supplier<Fruit>> MELONS =
            Map.of("Gac", Gac::new, "Hemi", Hemi::new, "Cantaloupe", Cantaloupe::new);


    public Fruit getInstanceUsingSupplier(Class<?> clazz) {
        Supplier<Fruit> supplier = MELONS.get(clazz.getSimpleName());
        if (supplier.get() == null) {
            throw new IllegalArgumentException("Invalid clazz argument: " + clazz);
        }
        return supplier.get();
    }

    public Fruit getInstance(Class<?> clazz) {
        String name = clazz.getSimpleName();
        switch (name) {
            case "Gac":
                return new Gac();
            case "Hemi":
                return new Hemi();
            case "Cantaloupe":
                return new Cantaloupe();
            default:
                throw new IllegalArgumentException("Invalid clazz argument: " + clazz);
        }
    }
}
