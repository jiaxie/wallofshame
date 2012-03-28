package com.wallofshame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Since: 3/15/12
 */
public class PeopleMissingTimeSheet {

    private static PeopleMissingTimeSheet instance = new PeopleMissingTimeSheet();

    private List<Employee> names;
    private Date lastUpdateTime;


    public static PeopleMissingTimeSheet getInstance() {
        return instance;
    }

    private PeopleMissingTimeSheet() {
        names = new ArrayList<Employee>();
        this.lastUpdateTime = new Date();
    }


    public void replaceAll(List<Employee> names) {
        this.names = names;
        dataUpdated();
    }

    public void replaceAll(Employees names) {
        this.names = names.employees;
        dataUpdated();
    }

    private void dataUpdated() {
        this.lastUpdateTime = new Date();
    }

    public List<Employee> names() {
        return names;
    }

    public Date lastUpdateTime() {
        return lastUpdateTime;
    }
}
