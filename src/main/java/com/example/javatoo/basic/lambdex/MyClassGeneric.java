package com.example.javatoo.basic.lambdex;

public class MyClassGeneric<T> {
    private T val;
    MyClassGeneric(){ val = null;}
    MyClassGeneric(T val){
        this.val = val;
    }
    public T getVal() {
        return val;
    }
}
