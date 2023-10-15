package com.example.javatoo.basic.overloadingandoverriding;

import lombok.extern.slf4j.Slf4j;
// A class for two-dimensional objects.
@Slf4j
public class TwoDShape {
    // In case of Private; members are not inherited.
    // Use accessor methods to set and get private members. like setters and getters.
    double width;
    double height;
    void showDim(){
        System.out.println("Width and height are " + width + " and " + height);
    }
}
