package com.hef.guava.service;

import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.ServiceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Date 2022/7/31
 * @Author lifei
 */
public class ServiceDemo {

    public static void main(String[] args) {
        Service service = null;
        service.awaitTerminated();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        List<Service> serviceList = new ArrayList<>();
        ServiceManager serviceManager = new ServiceManager(serviceList);
        serviceManager.startupTimes();
    }
}
