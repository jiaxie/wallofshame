package com.wallofshame.repository;

import com.wallofshame.domain.Timesheets;
import org.joda.time.DateTime;

/**
 * Since: 4/10/12
 */
public interface TimesheetRepository {

    Timesheets find(String payroll, DateTime start, DateTime end);
}
