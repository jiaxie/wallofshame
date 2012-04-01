package com.wallofshame.domain;

import java.util.*;

/**
 * Since: 3/15/12
 */
public class PeopleMissingTimeSheet {

    private static PeopleMissingTimeSheet instance = new PeopleMissingTimeSheet();

    private Date lastUpdateTime;
    private Map<String, Employees> employees;

    public static PeopleMissingTimeSheet getInstance() {
        return instance;
    }

    private PeopleMissingTimeSheet() {
        this.lastUpdateTime = new Date();
        this.employees = new HashMap<String, Employees>();
    }

    public void replaceAll(String company, Employees employees) {
        this.employees.put(company, employees);
        dataUpdated();
    }

    private void dataUpdated() {
        this.lastUpdateTime = new Date();
    }

    public Employees employeesOf(String company) {
        Employees result = this.employees.get(company);
        return result;
    }

    public Date lastUpdateTime() {
        return lastUpdateTime;
    }

    public List<Payroll> supportedPayrolls() {
        return Arrays.asList(new Payroll("TCH", "China"));
    }

    public boolean isEmpty() {
        return PeopleMissingTimeSheet.getInstance().employeesOf("TCH").getEmployees().isEmpty();
    }
}
