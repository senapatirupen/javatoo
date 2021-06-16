package com.example.javatoo.model;

import java.util.Comparator;

public class LastNameComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int i,j,k;
        // Find index of beginning of last name.
        i = o1.lastIndexOf(' ');
        j = o2.lastIndexOf(' ');
        k = o1.substring(i).compareToIgnoreCase(o2.substring(j));
        if(k == 0){ //last names match, check entire name
            return o1.compareToIgnoreCase(o2);
        } else {
            return k;
        }
    }
    // No need to override equals.
}
