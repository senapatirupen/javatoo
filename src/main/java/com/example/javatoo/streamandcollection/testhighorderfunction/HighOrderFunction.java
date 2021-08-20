package com.example.javatoo.streamandcollection.testhighorderfunction;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class HighOrderFunction {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ann a 15", "Mir el 28", "D oru 33");
        List<String> resultWs = new ReplacerUse().replace(names, (String s) -> s.replaceAll("\\s", ""));
        List<String> resultNr = new ReplacerUse().replace(names, (String s) -> s.replaceAll("\\d", ""));
        Function<String, String> f1 = (String s) -> s.toUpperCase();
        Function<String, String> f2 = (String s) -> s.concat(" DONE");
        Function<String, String> f = new ReplacerUse().reduceStrings(f1, f2);
        f.apply("test");
    }
}
