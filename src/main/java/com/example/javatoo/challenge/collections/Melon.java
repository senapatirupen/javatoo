package com.example.javatoo.challenge.collections;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Melon {
    public final String type;
    public final int weight;
    public Melon(String type, int weight){
        this.type = type;
        this.weight = weight;
    }

}
