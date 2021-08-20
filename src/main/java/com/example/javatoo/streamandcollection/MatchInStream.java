package com.example.javatoo.streamandcollection;

import java.util.Arrays;
import java.util.List;

public class MatchInStream {
    public static void main(String[] args) {
        new MatchInStream().matchInStream();
    }

    List<String> melons = Arrays.asList("Gac", "Cantaloupe", "Hemi",
            "Gac", "Gac", "Hemi", "Cantaloupe", "Horned", "Hemi", "Hemi");

    public void matchInStream() {

        boolean isAnyGac = melons.stream().anyMatch(m -> m.equals("Gac"));
        System.out.println("is any element matching the string, Gac? " + isAnyGac);

        boolean isNoneGac = melons.stream().noneMatch(m -> m.equals("Gac"));
        System.out.println("are none elements matching the string, Gac? " + isNoneGac);

        boolean areAllLargerThan2 = melons.stream().allMatch(m -> m.length() > 2);
        System.out.println("are all elements larger than 2? " + areAllLargerThan2);
    }
}
