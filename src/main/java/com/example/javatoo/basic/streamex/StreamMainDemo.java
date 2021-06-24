package com.example.javatoo.basic.streamex;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
public class StreamMainDemo {
    public static void main(String[] args) {
        new StreamMainDemo().test();
    }

    public void test() {
        // Obtain the minimum and maximum value by uses of min(), max(), isPresent(), and get().
        Stream<Integer> list = getList().stream();
        Optional<Integer> minVal = list.min(Integer::compare);
        if (minVal.isPresent()) {
            log.info("min val: " + minVal.get());
        }
        //IllegalStateException: stream has already been operated upon or closed
//        Optional<Integer> maxVal = list.max(Integer::compare);
        Stream<Integer> newList = getList().stream();
        Optional<Integer> maxVal = newList.max(Integer::compare);
        if (maxVal.isPresent()) {
            log.info("max val: " + maxVal.get());
        }

        //sorting
        Stream<Integer> sortList = getList().stream().sorted();
        sortList.forEach(t -> log.info("val " + t));

        //only odd and greater than 5
        Stream<Integer> onlyOdd = getList().stream().filter((t) -> (t % 2) == 1)
                .filter((n) -> n > 5);
        onlyOdd.forEach(t -> log.info("odd val " + t));

        //use of reduce()
        Optional<Integer> redVal = getList().stream().reduce((a, b) -> a * b);
        if (redVal.isPresent())
            log.info(redVal.get() + "");

        // Demonstrate the use of a combiner with reduce()
        double productOfSqrRoots = getList().parallelStream().reduce(
                1.0,
                (a, b) -> a * Math.sqrt(b),
                (a, b) -> a * b
        );
        log.info("reduce combiner " + productOfSqrRoots);

        // Map one stream to another.
        Stream<Double> sqrt = getDoubleList().stream().map((t) -> Math.sqrt(t));
        // Map the square root of the elements in myList to a new stream.
        // It will show the stream object hashcode
        log.info("sqrt " + sqrt);
        log.info(sqrt.reduce(1.0, (a, b) -> a * b) + " sqrt of all");

        // Map the ceiling of the elements in myList to an InStream.
        IntStream stream = getDoubleList().stream().mapToInt(t -> (int)Math.ceil(t));
        stream.forEach(t -> log.info(t+""));

        // Use map() to create a new stream that contains only selected aspects of the original stream.
        Stream<NamePhone> namePhones = getNameList().stream().map((t)-> new NamePhone(t.name, t.phonenum));
        namePhones.forEach(t -> log.info(t.name+" "+t.phonenum));

        // Use collect() to create a List and a Set from a stream.
        List<NamePhone> namePhoneList = getNameList().stream().map((t) -> new NamePhone(t.name, t.phonenum)).collect(Collectors.toList());
        Set<NamePhone> namePhoneSet = getNameList().stream().map((t) -> new NamePhone(t.name, t.phonenum)).collect(Collectors.toSet());

        // Use an iterator with a stream.
        Iterator<String> iterator = getStringList().stream().iterator();
        while(iterator.hasNext()){
            log.info(iterator.next());
        }

        // Use a Spliterator.
        Spliterator<String> stringSpliterator = getStringList().stream().spliterator();
        while(stringSpliterator.tryAdvance(t -> log.info(t+"")));

        // Demonstrate trySplit().
        // Obtain a Spliterator.
        Spliterator<String> splitItr = getStringList().stream().spliterator();
        // Now, split the first iterator.
        Spliterator<String> splitItr2 = splitItr.trySplit();
        // If splitItr could be split, use splitItr2 first.
        if(splitItr2 != null) {
            log.info("Output from splitItr2: ");
            splitItr2.forEachRemaining((n) -> log.info(n));
        }
        // Now, use the splitItr.
        log.info("\nOutput from splitItr: ");
        splitItr.forEachRemaining((n) -> log.info(n));
    }

    public ArrayList<Integer> getList() {
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(7);
        myList.add(18);
        myList.add(10);
        myList.add(24);
        myList.add(17);
        myList.add(5);
        return myList;
    }

    public ArrayList<String> getStringList(){
        ArrayList<String> myList = new ArrayList<>( );
        myList.add("Alpha");
        myList.add("Beta");
        myList.add("Gamma");
        myList.add("Delta");
        myList.add("Phi");
        myList.add("Omega");
        return myList;
    }

    public ArrayList<Double> getDoubleList() {
        // A list of double values.
        ArrayList<Double> myList = new ArrayList<>();
        myList.add(7.0);
        myList.add(18.0);
        myList.add(10.0);
        myList.add(24.0);
        myList.add(17.0);
        myList.add(5.0);
        return myList;
    }

    public ArrayList<NamePhoneEmail> getNameList(){
        ArrayList<NamePhoneEmail> myList = new ArrayList<>( );
        myList.add(new NamePhoneEmail("Larry", "555-5555",
                "Larry@HerbSchildt.com"));
        myList.add(new NamePhoneEmail("James", "555-4444",
                "James@HerbSchildt.com"));
        myList.add(new NamePhoneEmail("Mary", "555-3333",
                "Mary@HerbSchildt.com"));
        return myList;
    }

    class NamePhoneEmail {
        String name;
        String phonenum;
        String email;
        NamePhoneEmail(String n, String p, String e) {
            name = n;
            phonenum = p;
            email = e;
        }
    }

    class NamePhone {
        String name;
        String phonenum;
        NamePhone(String n, String p) {
            name = n;
            phonenum = p;
        }
    }

}
