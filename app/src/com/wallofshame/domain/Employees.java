package com.wallofshame.domain;

import java.util.ArrayList;
import java.util.List;

public class Employees {
    public List<Employee> employees;

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employees() {
        employees =  new ArrayList<Employee>();
    }
}
