package com.example.javatoo.basic.genericsex;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// A simple generic class.
// Here, T is a type parameter that will be replaced by a real type when an object of type Gen is created.
public class Gen<T> {
    T o;
    Gen(T o){
        this.o = o;
    }
    T getObj(){
        return this.o;
    }
    void showObj(){
        log.info(this.o.getClass().getName());
    }
}
