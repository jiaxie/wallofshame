package com.wallofshame.domain;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: twer
 * Date: 3/29/12
 * Time: 4:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueryCondition {
    private  String lastSunday;
    private String companyId;

    public static String getLastSunday() {
        Calendar today = Calendar.getInstance();
        int dayOfWeek = today.get(Calendar.DAY_OF_WEEK) - 1;
        Date lastSunday = DateUtils.addDays(today.getTime(), 0 - dayOfWeek);
        return FastDateFormat.getInstance("dd/MM/yyyy").format(lastSunday);
    }

    public static String getCompanyId() {
        //TCH mean China
        return "TCH";
    }
}
