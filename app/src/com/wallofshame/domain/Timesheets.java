package com.wallofshame.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Since: 4/10/12
 */
public class Timesheets {

    private Set<Timesheet> timesheets;

    public Timesheets() {
        this.timesheets = new HashSet<Timesheet>();
    }

    public int amount() {
        return timesheets.size();
    }

    public Set<Timesheet> values() {
        return timesheets;
    }

    public void add(Timesheet timesheet) {
        this.timesheets.add(timesheet);
    }
}
