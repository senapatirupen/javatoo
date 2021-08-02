package com.example.javatoo.challenge.collections;

import java.util.Arrays;
import java.util.Comparator;

public class SortObjectUsingArrays {
    public static Melon[] melons = new Melon[]{new Melon("large", 30),
            new Melon("small", 10),
            new Melon("medium", 20)
            };

    public static void main(String[] args) {
//        new SortObjectUsingArrays().sortObjectUsingArraysV1(melons);
//        new SortObjectUsingArrays().sortObjectUsingArraysV2(melons);
        new SortObjectUsingArrays().sortObjectUsingArraysV3(melons);

    }

    public void sortObjectUsingArraysV1(Melon[] melons) {
        Arrays.sort(melons, new Comparator<Melon>() {
            @Override
            public int compare(Melon o1, Melon o2) {
                return Integer.compare(o1.getWeight(), o2.getWeight());
            }
        });
        Arrays.asList(melons).stream().forEach(System.out::print);
    }

    public void sortObjectUsingArraysV2(Melon[] melons) {
        Arrays.sort(melons, (Melon o1, Melon o2) -> Integer.compare(o1.getWeight(), o2.getWeight()));
        Arrays.asList(melons).stream().forEach(System.out::print);
    }

    public void sortObjectUsingArraysV3(Melon[] melons) {
        Arrays.parallelSort(melons, (Melon o1, Melon o2) -> Integer.compare(o1.getWeight(), o2.getWeight()));
        Arrays.asList(melons).stream().forEach(System.out::print);
    }

    public void reverseSortObjectUsingArraysV1(Melon[] melons) {
        Arrays.sort(melons, (Melon o1, Melon o2) -> (-1) * Integer.compare(o1.getWeight(), o2.getWeight()));
        Arrays.asList(melons).stream().forEach(System.out::print);
    }

}
