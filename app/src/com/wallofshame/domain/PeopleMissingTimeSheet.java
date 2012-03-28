package com.wallofshame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Since: 3/15/12
 */
public class PeopleMissingTimeSheet {

    private static PeopleMissingTimeSheet instance = new PeopleMissingTimeSheet();

    private Employees names;
    private Date lastUpdateTime;


    public static PeopleMissingTimeSheet getInstance() {
        return instance;
    }

    private PeopleMissingTimeSheet() {
        names = new Employees(new ArrayList<Employee>());
        this.lastUpdateTime = new Date();
    }

    public void replaceAll(Employees names) {
        this.names = names;
        dataUpdated();
    }

    private void dataUpdated() {
        this.lastUpdateTime = new Date();
    }

    public List<Employee> names() {
        return names.employees;
    }

    public Date lastUpdateTime() {
        return lastUpdateTime;
    }
}
