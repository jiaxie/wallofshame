package com.wallofshame.utils;

import org.joda.time.DateTime;

public class DateTimeUtils {

    public DateTimeUtils() {
    }

    public static DateTime lastSunday(DateTime today) {
        int indexOfDate = today.getDayOfWeek();
        if(indexOfDate == 7)
            return new DateTime(today);
        return today.minusDays(indexOfDate);
    }
}