package com.wallofshame.service.peoplesoft;

import com.wallofshame.domain.Employees;
import com.wallofshame.repository.peoplesoft.BadCredentialException;
import com.wallofshame.repository.peoplesoft.Credential;
import com.wallofshame.repository.peoplesoft.NoContentException;
import com.wallofshame.repository.peoplesoft.PeopleSoftSite;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Since: 3/26/12
 */
public class PeopleSoftSiteTest {

    public static final String TIMESHEET_SUBMIT_DATE_CSV_HEADER = "\"Employee/Contractor\",\"Assoc\",\"Client\",\"Project\",\"Sub Proj1\",\"Date\",\"Client Billable Hours\",\"Client Nonbillable Hours\",\"TW Nonbillable Hours\",\"Task\",\"PSID\",\"Dept\",\"Co\",\"Week Ending Dt\",\"Submit Datetime\",\"Cntry\",\"St\"";
    private final String HEADER_LINE = "\"Employee/Contractor\",\"Week Ending Dt\",\"Payroll\",\"Work in Office\",\"Work in Ctry\",\"Dept\",\"Name\",\"Empl ID\"";

    @Test(expected = BadCredentialException.class)
    public void should_throw_exception_if_bad_credential() throws Exception {
        String badUsername = "badEddwhat";
        String badPassword = "Eee";
        PeopleSoftSite site = new PeopleSoftSite();
        site.login(badUsername, badPassword);
    }

    @Test
    public void should_download_csv_from_peoplesoft_site() throws Exception {
        PeopleSoftSite site = new PeopleSoftSite();
        site.login("HJIAO", "jiao980701");
        String csvData = site.fetchCvsOfPeopleMissingTimesheet("31/12/2016", "TCH");
        assertTrue(csvData.contains(HEADER_LINE));
    }

    @Test(expected = NoContentException.class)
    public void should_throw_no_content_exception_if_no_missing_people() throws Exception {
        PeopleSoftSite site = new PeopleSoftSite();
        site.login("HJIAO", "jiao980701");
        //it's tricky. 25/03/2012 certainly has no missing people.
        site.fetchCvsOfPeopleMissingTimesheet("25/03/2012", "TCH");
    }

    @Test
    public void should_return_empty_employees_if_fecth_the_sunday_with_no_missing_people() throws Exception {
        Credential.getInstance().save("HJIAO", "jiao980701");
        PeopleSoftSite site = new PeopleSoftSite();
        DateTime theSundayNoMissing = new DateTime().withDate(2012, 3, 25);
        Employees employees = site.fetch(theSundayNoMissing, "TCH");
        assertThat(employees, notNullValue());
        assertThat(employees.getEmployees().size(), equalTo(0));
    }

    @Test
    public void should_return_csv_of_timesheet_entry_with_valid_date_range() throws BadCredentialException {
        PeopleSoftSite site = new PeopleSoftSite();
        DateTime start = new DateTime().withDate(2012, 4, 2);
        DateTime end = new DateTime().withDate(2012, 4, 2);
        String payroll = "TCH";
        site.login("HJIAO", "jiao980701");
        String csv = site.fetchCSVOfTimesheet(payroll, start, end);
        assertTrue(csv.contains(TIMESHEET_SUBMIT_DATE_CSV_HEADER));
    }

}
