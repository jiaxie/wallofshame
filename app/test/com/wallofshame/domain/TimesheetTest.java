package com.wallofshame.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.hasItem;

/**
 * Since: 4/10/12
 */
public class TimesheetTest {

    @Test
    public void should_not_equal_if_same_name_and_different_ending_week_day() {
        Timesheet oneWeek = new Timesheet("An,Hui", "08/04/2012", "08/04/2012  9:25:49PM");
        Timesheet anotherWeek = new Timesheet("An,Hui", "15/04/2012", "15/04/2012  9:25:49PM");
        assertFalse(anotherWeek.equals(oneWeek));
    }

    @Test
    public void should_not_equal_if_different_name() {
        Timesheet oneWeek = new Timesheet("An,Hui", "08/04/2012", "08/04/2012  9:25:49PM");
        Timesheet anotherWeek = new Timesheet("Ku,Kai", "08/04/2012", "08/04/2012  9:25:49PM");
        assertFalse(anotherWeek.equals(oneWeek));
    }

    @Test
    public void should_equal_if_same_name_and_same_ending_week_day() {
        Timesheet oneWeek = new Timesheet("An,Hui", "08/04/2012", "08/04/2012  9:25:49PM");
        Timesheet anotherWeek = new Timesheet("An,Hui", "08/04/2012", "15/04/2012  9:25:49PM");
        assertTrue(anotherWeek.equals(oneWeek));
    }

    @Test
    public void should_contains_only_one_timesheet_for_each_people_and_each_ending_week_day() {
        Timesheet oneWeek = new Timesheet("An,Hui", "08/04/2012", "08/04/2012  9:25:49PM");
        Timesheet sameWeek = new Timesheet("An,Hui", "08/04/2012", "08/04/2012  10:25:49PM");
        Timesheets timesheets = new Timesheets();
        timesheets.add(oneWeek);
        timesheets.add(sameWeek);
        assertThat(timesheets.amount(), is(1));
    }

    @Test
    public void should_overwrite_existing_timesheet_if_same_people_same_ending_week_day() {
        Timesheet oneWeek = new Timesheet("An,Hui", "08/04/2012", "08/04/2012  9:25:49PM");
        Timesheet sameWeek = new Timesheet("An,Hui", "08/04/2012", "08/04/2012  10:25:49PM");
        Timesheets timesheets = new Timesheets();
        timesheets.add(oneWeek);
        assertThat(timesheets.amount(), is(1));
        assertThat(timesheets.values(), hasItem(oneWeek));
        timesheets.add(sameWeek);
        assertThat(timesheets.amount(), is(1));
        assertThat(timesheets.values(), hasItem(sameWeek));
    }
}
