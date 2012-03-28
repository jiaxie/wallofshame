package com.wallofshame.domain;

/**
 * Since: 3/26/12
 */
public class Employee {

    private String id;
    private String name;
    private String office;

    public Employee(String id, String name, String office) {
        this.id = id;
        this.name = name;
        this.office = office;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (office != null ? office.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + office;
    }

    public String getOffice() {
        return office;
    }
}
