package com.example.javatoo.challenge.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
public class ConvertIterableToList {
    public static void main(String[] args) {
        Iterable<String> names = Arrays.asList("ana", "george", "mark");
        new ConvertIterableToList().convertIterableToList(names).stream().forEach(System.out::print);

    }

    public <T> List<T> convertIterableToList(Iterable<T> iterable){
        if(iterable == null){
            return Collections.emptyList();
        }
        List<T> result = new ArrayList<>();
        iterable.forEach(result::add);
//        or
//        List<T> result = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
//        or
//        iterable.iterator().forEachRemaining(result::add);
//        or
//        List<T> result
//                = StreamSupport.stream(Spliterators.
//                spliteratorUnknownSize(iterable.iterator(), Spliterator.ORDERED), false)
//                .collect(Collectors.toList());
//        or
//        for(T t: iterable){
//            result.add(t);
//        }
//        or
//        Iterator<T> iterator = iterable.iterator();
//        while (iterator.hasNext()) {
//            result.add(iterator.next());
//        }
        return result;
    }
}
