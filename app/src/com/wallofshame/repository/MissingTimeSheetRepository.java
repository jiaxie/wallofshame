package com.wallofshame.repository;

import com.wallofshame.domain.Employees;

public interface MissingTimeSheetRepository {
    public Employees lookUp(String lastSunDay, String officeId);
}
