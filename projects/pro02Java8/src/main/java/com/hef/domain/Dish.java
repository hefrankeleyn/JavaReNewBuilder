package com.hef.domain;

/**
 * @Date 2021/8/25
 * @Author lifei
 */
public class Dish {
    private String name;
    private String Type;

    public Dish() {
    }

    public Dish(String name, String type) {
        this.name = name;
        Type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
