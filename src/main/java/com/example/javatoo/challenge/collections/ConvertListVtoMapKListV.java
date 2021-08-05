package com.example.javatoo.challenge.collections;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ConvertListVtoMapKListV {
    public static List<String> names
            = List.of("joana", "mark", "adela", "leo", "stan", "marius", "kely");

    public static void main(String[] args) {
        Map<Integer, List<String>> nameList = new ConvertListVtoMapKListV().convertListVtoMapKListV_V1(names);
        System.out.println(nameList);
//        output: {3=[leo], 4=[mark, stan, kely], 5=[joana, adela], 6=[marius]}
        Map<Integer, List<String>> nameList2 = new ConvertListVtoMapKListV().convertListVtoMapKListV_V2(names,
                String::length, HashMap::new, ArrayList::new);
        System.out.println(nameList2);

    }

    public Map<Integer, List<String>> convertListVtoMapKListV_V1(List<String> list) {
        if (list == null && list.isEmpty()) {
            return Collections.emptyMap();
        }
        return list.stream().collect(Collectors.groupingBy(String::length,
                HashMap::new, Collectors.toCollection(ArrayList::new)));
    }

    public static <K, V, C extends Collection<V>, M extends Map<K, C>> M convertListVtoMapKListV_V2(
            List<V> list, Function<? super V, ? extends K> c, Supplier<M> ms, Supplier<C> cs) {

        if (list == null || c == null || ms == null || cs == null
                || list.isEmpty()) {

            throw new IllegalArgumentException("Non of the arguments can be null or empty");
        }

        return list.stream().collect(
                Collectors.groupingBy(c, ms, Collectors.toCollection(cs))
        );
    }
}
