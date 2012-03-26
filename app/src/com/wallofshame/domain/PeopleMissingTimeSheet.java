package com.wallofshame.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Since: 3/15/12
 */
public class PeopleMissingTimeSheet {

    private static PeopleMissingTimeSheet instance = new PeopleMissingTimeSheet();

    private List<MissingPeople> names;


    public static PeopleMissingTimeSheet getInstance() {
        return instance;
    }

    private PeopleMissingTimeSheet() {
        names = new ArrayList<MissingPeople>();
    }


    public void replaceAll(List<MissingPeople> names) {
        this.names = names;
    }

    public List<MissingPeople> names() {
        return names;
    }
}
