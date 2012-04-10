package com.wallofshame.domain;

/**
 * Since: 4/10/12
 */
public class Timesheet {

    private String submitDate;
    private String name;
    private String endingWeekDay;

    public Timesheet(String name, String endingWeekDay, String submitDate) {
        this.name = name;
        this.endingWeekDay = endingWeekDay;
        this.submitDate = submitDate;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public String getEndingWeekDay() {
        return endingWeekDay;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Timesheet timesheet = (Timesheet) o;

        if (endingWeekDay != null ? !endingWeekDay.equals(timesheet.endingWeekDay) : timesheet.endingWeekDay != null)
            return false;
        if (name != null ? !name.equals(timesheet.name) : timesheet.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (endingWeekDay != null ? endingWeekDay.hashCode() : 0);
        return result;
    }
}
