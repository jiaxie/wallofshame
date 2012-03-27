package com.wallofshame.domain;

/**
 * Since: 3/26/12
 */
public class MissingPeople {

    private String id;
    private String name;
    private String office;

    public MissingPeople(String id, String name, String office) {
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
        MissingPeople another = (MissingPeople) o;
        return another.id.equals(id);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + office;
    }

    public String getOffice() {
        return office;
    }
}
