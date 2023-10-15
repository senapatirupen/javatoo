package com.example.javatoo.designpattern.behavioraldesignpatterns.others;

import java.util.Map;
import java.util.function.Supplier;

public class MelonFactory {
    public Fruit getMelon(Class<?> clazz){
        switch (clazz.getSimpleName()){
            case "Gac": return new Gac();
            case "Hemi": return new Hemi();
            default: throw new IllegalArgumentException("Illegal class argument"+clazz);
        }
    }

    public Fruit getMelonSupplier(Class<?> clazz){
        Supplier<Fruit> fruitSupplier = MELONS.get(clazz.getSimpleName());
        if(fruitSupplier == null){
            throw new IllegalArgumentException("Illegal class argument"+clazz);
        }
        return fruitSupplier.get();
    }

    public static void main(String[] args) {
        Gac gac = (Gac) new MelonFactory().getMelon(Gac.class);
        gac.type();
        Hemi hemi = (Hemi) new MelonFactory().getMelon(Hemi.class);
        hemi.type();
        // However, Java 8 functional style allows us to refer to constructors using the method reference
        // technique. This means that we can define a Supplier<Fruit> to refer to the Fact empty constructor, as follows
        Supplier<Fruit> gacFruit = Gac::new;

    }
    //How about Hemi.java and so on? Well, we can simply put all of them in a Map(notice that no melon type is instantiated here; these are just lazy method references)
    public static final Map<String, Supplier<Fruit>> MELONS = Map.of("Gac", Gac::new, "Hemi", Hemi::new);

}
