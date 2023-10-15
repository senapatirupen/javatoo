package com.example.javatoo.designpattern.behavioraldesignpatterns.others;

import java.util.ArrayList;
import java.util.List;
/*
Implementation of different type of filter for Melon
 */
public class MelonFilter {

    public static List<Melon> filterByType(List<Melon> melons, String type){
        List<Melon> result = new ArrayList<>();
        for(Melon melon: melons){
            if(melon != null && type.equalsIgnoreCase(melon.getType())){
                result.add(melon);
            }
        }
        return result;
    }

    public static List<Melon> filterByWeight(List<Melon> melons, int weight){
        List<Melon> result = new ArrayList<>();
        for(Melon melon: melons){
            if(melon != null && weight == melon.getWeight()){
                result.add(melon);
            }
        }
        return result;
    }

    public static List<Melon> filterByTypeAndWeight(List<Melon> melons, String type, int weight){
        List<Melon> result = new ArrayList<>();
        for(Melon melon: melons){
            if(melon != null && weight == melon.getWeight() && type.equalsIgnoreCase(melon.getType())){
                result.add(melon);
            }
        }
        return result;
    }

    public List<Melon> filterMelon(List<Melon> melons, MelonPredicate melonPredicate){
        List<Melon> result = new ArrayList<>();
        for(Melon melon: melons){
            if(melonPredicate.test(melon)){
                result.add(melon);
            }
        }
        return result;
    }

    public <T> List<T> filter(List<T> list, MPredicate<T> predicate){
        List<T> result = new ArrayList<>();
        for(T t: list){
            if(predicate.test(t)){
                result.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Melon> input = new ArrayList<>();
        List<Melon> result = new ArrayList<>();
        new MelonFilter().filterMelon(input, new GacMelonPredicate());
        new MelonFilter().filterMelon(input, new HugeMelonPredicate());

        new MelonFilter().filterMelon(input, new MelonPredicate() {
            @Override
            public boolean test(Melon melon) {
                if(melon != null && 10000 == melon.getWeight()){
                    return true;
                }
                return false;
            }
        });

        new MelonFilter().filterMelon(input, melon -> {
            if(melon != null && 10000 == melon.getWeight()){
                return true;
            }
            return false;
        });

        new MelonFilter().filter(input, melon -> {
            if(melon != null && 10000 == melon.getWeight()){
                return true;
            }
            return false;
        });

        MPredicate<Melon> mPredicate = (melon -> {
            if(melon != null && 10000 == melon.getWeight()){
                return true;
            }
            return false;
        });

        new MelonFilter().filter(input, mPredicate);
    }
}
