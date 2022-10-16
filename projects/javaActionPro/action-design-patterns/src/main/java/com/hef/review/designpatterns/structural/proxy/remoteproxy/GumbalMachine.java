package com.hef.review.designpatterns.structural.proxy.remoteproxy;

/**
 * @Date 2022/10/17
 * @Author lifei
 */
public class GumbalMachine {

    private String location;
    private int count;

    public GumbalMachine(String location, int count) {
        this.location = location;
        this.count = count;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
