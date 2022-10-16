package com.hef.review.designpatterns.structural.proxy.remoteproxy;


/**
 * @Date 2022/10/17
 * @Author lifei
 */
public class GumballMachineTestDrive {

    public static void main(String[] args) {
        GumbalMachine gumbalMachine = new GumbalMachine("Seattle" , 112);
        GumbalMonitor monitor = new GumbalMonitor(gumbalMachine);
        monitor.report();
    }
}
