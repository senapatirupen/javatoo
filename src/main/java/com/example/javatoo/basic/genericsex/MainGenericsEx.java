package com.example.javatoo.basic.genericsex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainGenericsEx {
    public static void main(String[] args) {
        new MainGenericsEx().test();
    }

    public void test() {
        new Gen<Integer>(10).showObj();
        new Gen<String>("name").showObj();

        Integer nums[] = {1, 2, 3, 4, 5};
        Stats<Integer> numsa = new Stats<Integer>(nums);
        Double average = numsa.average();
        log.info(average + "");
        Float numsf[] = {1.1f, 2.5f, 3.6f, 4.4f, 5.2f};
        Stats<Float> numsfa = new Stats<Float>(numsf);
        average = numsfa.average();
        log.info(average + "");
        boolean flg = numsa.sameAverage(numsfa);
        log.info(flg + "");

        //Bounded wild card example
        TwoD td[] = {
                new TwoD(0, 0),
                new TwoD(7, 9),
                new TwoD(18, 4),
                new TwoD(-1, -23)
        };
        Coords<TwoD> tdlocs = new Coords<TwoD>(td);
        new BoundedWildcard().showXY(tdlocs);

        //Generic Method example
        Integer numsx[] = {1, 2, 3, 4, 5};
        if (isIn(2, numsx))
            log.info("2 is in numsx");

        // Use isIn() on Strings.
        String strs[] = {"one", "two", "three", "four", "five"};
        if (isIn("two", strs))
            log.info("two is in strs");

    }

    // Generic Method
    // Determine if an object is in an array.
    static <T extends Comparable<T>, V extends T> boolean isIn(T x, V[] y) {
        for (int i = 0; i < y.length; i++)
            if (x.equals(y[i])) return true;
        return false;
    }

//    listing 9 need to start
// Use a generic constructor.
}
