package com.example.javatoo.streamandcollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static java.util.Comparator.comparingInt;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.toSet;

public class Grouping {
    public static void main(String[] args) {
        List<Melon> melons = Arrays.asList(new Melon("Crenshaw", 1200),
                new Melon("Gac", 3000), new Melon("Hemi", 2600),
                new Melon("Hemi", 1600), new Melon("Gac", 1200),
                new Melon("Apollo", 2600), new Melon("Horned", 1700),
                new Melon("Gac", 3000), new Melon("Hemi", 2600)
        );

        Map<String, List<Melon>> byTypeInList = melons.stream()
                .collect(groupingBy(Melon::getType));

        Map<Integer, List<Melon>> byWeightInList = melons.stream()
                .collect(groupingBy(Melon::getWeight));

        System.out.println("Melons grouped by type with duplicates:\n" + byTypeInList);
        System.out.println("\nMelons grouped by weight with duplicates:\n" + byWeightInList);

        Map<String, Set<Melon>> byTypeInSet = melons.stream()
                .collect(groupingBy(Melon::getType, toSet()));

        Map<Integer, Set<Melon>> byWeightInSet = melons.stream()
                .collect(groupingBy(Melon::getWeight, toSet()));

        System.out.println("\nMelons grouped by type without duplicates:\n" + byTypeInSet);
        System.out.println("\nMelons grouped by weight without duplicates:\n" + byWeightInSet);

        Map<Integer, Long> weightsCount = melons.stream()
                .collect(groupingBy(Melon::getWeight, counting()));

        Map<String, Long> typesCount = melons.stream()
                .collect(groupingBy(Melon::getType, counting()));

        System.out.println("\nGroup melons by type and count:\n" + typesCount);
        System.out.println("\nGroup melons by weight and count:\n" + weightsCount);

        Map<String, Optional<Melon>> minMelonByType1 = melons.stream()
                .collect(groupingBy(Melon::getType, minBy(comparingInt(Melon::getWeight))));

        Map<String, Optional<Melon>> maxMelonByType1 = melons.stream()
                .collect(groupingBy(Melon::getType, maxBy(comparingInt(Melon::getWeight))));

        System.out.println("\nLightest melons by type:\n" + minMelonByType1);
        System.out.println("\nHeaviest melons by type:\n" + maxMelonByType1);

    }
}
