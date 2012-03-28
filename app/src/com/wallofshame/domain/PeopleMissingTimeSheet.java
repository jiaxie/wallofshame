package com.wallofshame.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Since: 3/15/12
 */
public class PeopleMissingTimeSheet {

    private static PeopleMissingTimeSheet instance = new PeopleMissingTimeSheet();

    private List<MissingPeople> names;
    private Date lastUpdateTime;


    public static PeopleMissingTimeSheet getInstance() {
        return instance;
    }

    private PeopleMissingTimeSheet() {
        names = new ArrayList<MissingPeople>();
        this.lastUpdateTime = new Date();
    }


    public void replaceAll(List<MissingPeople> names) {
        this.names = names;
        dataUpdated();
    }

    private void dataUpdated() {
        this.lastUpdateTime = new Date();
    }

    public List<MissingPeople> names() {
        return names;
    }

    public Date lastUpdateTime() {
        return lastUpdateTime;
    }
}
