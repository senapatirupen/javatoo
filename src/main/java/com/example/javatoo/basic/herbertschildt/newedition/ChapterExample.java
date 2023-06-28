package com.example.javatoo.basic.herbertschildt.newedition;

import java.io.IOException;

public class ChapterExample {
    public static void main(String[] args) throws IOException {
        new ChapterExample().preventDivisionByZero();
        new ChapterExample().arrayInitialization();
        new ChapterExample().foundMinMaxInArray();
    }

    public void readOneCharacter() throws IOException {
        char ch = (char)System.in.read();//read a single character
        System.out.println(ch);
    }

    public void preventDivisionByZero(){
        for(int i = -5; i < 6 ; i++){
            if(i != 0 ? true : false){
                System.out.println("100 / " + i + " is " + 100 / i);
            }
        }
    }

    public void arrayInitialization(){
        int[] numbers = new int[10];
        for(int i = 0 ; i < 10 ; i = i+1){
            numbers[i] = i;
        }
        for(int i = 0; i < 10 ; i = i+1){
            System.out.print(numbers[i]);
        }
    }

    public void foundMinMaxInArray(){
        int min, max;
        int[] numbers = {1, 4, 7, 2, 4};
        System.out.println(" ");
        min = numbers[0];
        max = numbers[0];
        for(int i = 1 ; i < 5 ; i = i+1){
            System.out.print(numbers[i]);
            if(numbers[i] < min)
                min = numbers[i];
            if(numbers[i] > max)
                max = numbers[i];
        }
        System.out.println(min + " Min and Max "+ max);
    }
}
