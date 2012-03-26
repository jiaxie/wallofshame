package com.wallofshame.domain;

/**
 * Since: 3/26/12
 */
public class MissingPeople {

    private String id;
    private String name;

    public MissingPeople(String id, String name) {
        this.id = id;
        this.name = name;
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
        return "[" + id + ":" + name + "]";
    }
}
