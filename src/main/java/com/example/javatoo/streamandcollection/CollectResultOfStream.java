package com.example.javatoo.streamandcollection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;


public class CollectResultOfStream {
    public static void main(String[] args) {
        new CollectResultOfStream().collectResultOfStream();
    }

    List<Melon> melons = Arrays.asList(new Melon("Crenshaw", 2000),
            new Melon("Hemi", 1600), new Melon("Gac", 3000),
            new Melon("Apollo", 2000), new Melon("Horned", 1700),
            new Melon("Gac", 3000), new Melon("Cantaloupe", 2600)
    );

    public void collectResultOfStream() {
        List<Integer> resultList = melons.stream()
                .map(Melon::getWeight)
                .filter(m -> m >= 1000)
                .collect(Collectors.toList());
//        .collect(Collectors.toCollection(ArrayList::new));  or
//        .collect(Collectors.toSet()); or
//        .collect(Collectors.toCollection(HashSet::new)); or
//        .collect(Collectors.toCollection(TreeSet::new)); or
        resultList.forEach(r -> System.out.print(r));

        Map<String, Integer> resultToMap1 = melons.stream()
                .distinct()
                .collect(Collectors.toMap(Melon::getType, Melon::getWeight));

        Map<Integer, Integer> resultToMap2 = melons.stream()
                .distinct()
                .map(x -> Map.entry(new Random().nextInt(Integer.MAX_VALUE), x.getWeight()))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

        Map<String, Integer> resultToMap3 = melons.stream()
                .collect(Collectors.toMap(Melon::getType, Melon::getWeight,
                        (oldValue, newValue) -> oldValue));

        Map<String, Integer> resultToMap4 = melons.stream()
                .sorted(Comparator.comparingInt(Melon::getWeight))
                .collect(Collectors.toMap(Melon::getType, Melon::getWeight,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));

        System.out.println("\nCollect via toMap():\n" + resultToMap1);
        System.out.println("\nCollect via toMap() with random keys:\n" + resultToMap2);
        System.out.println("\nCollect via toMap() with old key in case of collision:\n" + resultToMap3);
        System.out.println("\nCollect via toMap() sorted:\n" + resultToMap4);
    }
}
