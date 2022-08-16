package com.hef.guava.beans;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2022/8/15
 * @Author lifei
 */
@Data
@Builder
public class Car {
    private String name;
    private String color;
    private List<String> others;

    public static void main(String[] args) {
        Car car = Car.builder().build();
        System.out.println(car);
    }
}
