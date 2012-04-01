package com.wallofshame.utils;

import org.joda.time.DateTime;

public class DateTimeUtils {

    public DateTimeUtils() {
    }

    public static DateTime lastSunday(DateTime today) {
        int indexOfDate = today.getDayOfWeek();
        return today.minusDays(indexOfDate);
    }
}