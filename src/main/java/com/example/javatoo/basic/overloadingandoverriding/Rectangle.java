package com.example.javatoo.basic.overloadingandoverriding;
// A subclass of TwoDShape for rectangles.

public class Rectangle extends TwoDShape {
    boolean isSquare() {
        if (width == height) return true;
        return false;
    }

    double area() {
        return width * height;
    }
}