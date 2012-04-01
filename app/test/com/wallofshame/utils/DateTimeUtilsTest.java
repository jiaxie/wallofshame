package com.wallofshame.utils;

import com.wallofshame.utils.DateTimeUtils;
import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DateTimeUtilsTest {

    @Test
    public void sun_day_is_the_last_day_of_week(){
        DateTime sunday = new DateTime().withDate(2012,3,25).withTime(0,0,1,0);
        assertThat(sunday.dayOfWeek().get(),equalTo(7));
    }
    @Test
    public void monday_day_is_the_first_day_of_week(){
        DateTime sunday = new DateTime().withDate(2012,3,26).withTime(0,0,1,0);
        assertThat(sunday.dayOfWeek().get(),equalTo(1));
    }
    @Test
    public void saturday_day_is_the_second_day_of_week(){
        DateTime sunday = new DateTime().withDate(2012,3,24).withTime(0,0,1,0);
        assertThat(sunday.dayOfWeek().get(),equalTo(6));
    }

    @Test
    public void should_return_last_sun_day_if_it_is_one_second_after_sunday() {
        DateTime date = new DateTime().withDate(2012,3,26).withTime(0,0,1,0);
        DateTime lastSunDay = DateTimeUtils.lastSunday(date);
        assertThat(lastSunDay.dayOfWeek().get(),equalTo(7));
        assertThat(1,equalTo(date.weekOfWeekyear().get()-lastSunDay.weekOfWeekyear().get()));
        assertThat(lastSunDay.year().get(),equalTo(date.year().get()));
    }

    @Test
    public void should_not_return_today_if_it_is_one_second_before_sunday_finished() {
        DateTime date = new DateTime().withDate(2012,3,25).withTime(23,59,59,0);
        DateTime lastSunDay = DateTimeUtils.lastSunday(date);
        assertThat(lastSunDay.dayOfWeek().get(),equalTo(7));
        assertThat(lastSunDay.weekOfWeekyear().get(),not(date.weekOfWeekyear().get()));
    }

    @Test
    public void should_return_last_sunday_if_today_is_tuesday() {
        DateTime tuesday = new DateTime().withDate(2012,3,27).withTime(3,54,59,0);
        DateTime lastSunDay = DateTimeUtils.lastSunday(tuesday);
        assertThat(lastSunDay.dayOfWeek().get(),equalTo(7));
        assertThat(1,equalTo(tuesday.weekOfWeekyear().get()-lastSunDay.weekOfWeekyear().get()));
        assertThat(lastSunDay.year().get(),equalTo(tuesday.year().get()));
    }

    @Test
    public void should_return_last_sunday_evn_today_is_anyday_cross_the_year() {
        DateTime anyDayOfTheYear = anyDayOfTheYear();
        DateTime lastSunDay = DateTimeUtils.lastSunday(anyDayOfTheYear);
        assertThat(lastSunDay.dayOfWeek().get(),equalTo(7));
    }

    private DateTime anyDayOfTheYear() {
        return new DateTime();
    }
}
