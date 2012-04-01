package com.wallofshame.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Employees {

    private List<Employee> employees;

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employees() {
        employees =  new ArrayList<Employee>();
    }

    public void add(Employee person) {
        getEmployees().add(person);
    }

    public boolean contains(Employee employee) {
        return getEmployees().contains(employee);
    }

    public Set<String> availableOffices(){
        Set<String>  offices = new HashSet<String>();
        for (Employee employee : getEmployees()) {
            offices.add(employee.getOffice());
        }
        return offices;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Employee> atOffice(String office){
        if("All".equals(office))
            return this.employees;

        List<Employee> result = new ArrayList<Employee>();
          for(Employee employee : employees)
            if(employee.getOffice().equals(office))
                result.add(employee);
        return result;
    }
}
