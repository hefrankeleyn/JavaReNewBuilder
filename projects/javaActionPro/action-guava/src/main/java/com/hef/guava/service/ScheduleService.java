package com.hef.guava.service;

import com.google.common.util.concurrent.AbstractScheduledService;

/**
 * @Date 2022/7/31
 * @Author lifei
 */
public class ScheduleService extends AbstractScheduledService {
    @Override
    protected void runOneIteration() throws Exception {

    }

    @Override
    protected Scheduler scheduler() {
        return null;
    }
}
