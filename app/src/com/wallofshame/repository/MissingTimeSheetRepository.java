package com.wallofshame.repository;

import com.wallofshame.domain.Employees;
import org.joda.time.DateTime;

public interface MissingTimeSheetRepository {
    public Employees lookUp(DateTime lastSunDay, String officeId);
}
