package com.example.javatoo.designpattern.functionalinterface;

@FunctionalInterface
public interface Predicate<T> {
    public boolean test(T t);
}
