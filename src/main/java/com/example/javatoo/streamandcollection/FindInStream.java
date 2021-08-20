package com.example.javatoo.streamandcollection;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindInStream {
    public static void main(String[] args) {
        new FindInStream().findInStream();
    }

    List<String> melons = Arrays.asList("Gac", "Cantaloupe", "Hemi",
            "Gac", "Gac", "Hemi", "Cantaloupe", "Horned", "Hemi", "Hemi");

    List<Integer> ints = Arrays.asList(4, 8, 4, 5, 5, 7);

    public void findInStream() {

        Optional<String> anyMelon = melons.stream().findAny();
        if (!anyMelon.isEmpty()) {
            System.out.println("Any melon: " + anyMelon.get());
        } else {
            System.out.println("No melon found");
        }

        String anyApollo = melons.stream().filter(m -> m.equals("Apollo")).findAny().orElse("nope");
        System.out.println("Any Apollo? " + anyApollo);

        Optional<String> firstMelon = melons.stream().findFirst();
        if (!firstMelon.isEmpty()) {
            System.out.println("First melon: " + firstMelon.get());
        } else {
            System.out.println("No melon was found");
        }

        String firstApollo = melons.stream()
                .filter(m -> m.equals("Apollo"))
                .findFirst()
                .orElse("nope");
        System.out.println("First Apollo? " + firstApollo);

        int result = ints.stream().map(i -> i * i - 1)    // 23, 63, 23, 24, 24, 48
                .filter(i -> i % 2 == 0)    // 24, 24, 48
                .findFirst()                // 24
                .orElse(0);
        System.out.println("Result: " + result);

    }

}
