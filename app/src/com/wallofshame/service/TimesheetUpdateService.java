package com.wallofshame.service;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * Since: 3/31/12
 */
public interface TimesheetUpdateService {

    public void batchPullUpdates();

}
