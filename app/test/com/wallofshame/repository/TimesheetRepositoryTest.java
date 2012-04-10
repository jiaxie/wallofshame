package com.wallofshame.repository;

import com.wallofshame.domain.Timesheets;
import com.wallofshame.repository.peoplesoft.Credential;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Since: 4/10/12
 */
public class TimesheetRepositoryTest {

    @Test
    public void should_return_timesheets_if_valid_date_range() {
        Credential.getInstance().save("HJIAO", "jiao980701");
        TimesheetRepository timesheetRepository = new TimesheetRepositoryImpl();
        String payroll = "TCH";
        DateTime start = new DateTime().withDate(2012, 4, 2);
        DateTime end = new DateTime().withDate(2012, 4, 2);
        Timesheets timesheets = timesheetRepository.find(payroll, start, end);
        assertThat(timesheets.amount(), is(176));
    }
}
