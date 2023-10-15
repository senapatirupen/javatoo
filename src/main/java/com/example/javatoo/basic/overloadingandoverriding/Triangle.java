package com.example.javatoo.basic.overloadingandoverriding;

import lombok.extern.slf4j.Slf4j;
// A subclass of TwoDShape for triangles.
@Slf4j
public class Triangle extends TwoDShape {
    String style;

    double area(){
        return width * height /2;
    }
    void showStyle() {
        System.out.println("Triangle is " + style);
    }
}
