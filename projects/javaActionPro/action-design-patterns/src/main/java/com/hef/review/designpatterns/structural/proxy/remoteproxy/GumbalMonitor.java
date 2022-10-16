package com.hef.review.designpatterns.structural.proxy.remoteproxy;

/**
 * @Date 2022/10/17
 * @Author lifei
 */
public class GumbalMonitor {

    private GumbalMachine machine;

    public GumbalMonitor(GumbalMachine gumbalMachine) {
        this.machine = gumbalMachine;
    }

    public void report() {
        System.out.println("Gumbal machine:" + machine.getLocation());
        System.out.println(";Current inventory:" + machine.getCount() + " gumballs");
    }

}
