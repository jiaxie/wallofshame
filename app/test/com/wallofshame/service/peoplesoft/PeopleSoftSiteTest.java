package com.wallofshame.service.peoplesoft;

import com.wallofshame.domain.Employees;
import com.wallofshame.repository.peoplesoft.BadCredentialException;
import com.wallofshame.repository.peoplesoft.Credential;
import com.wallofshame.repository.peoplesoft.PeopleSoftSite;
import com.wallofshame.repository.peoplesoft.NoContentException;
import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Since: 3/26/12
 */
public class PeopleSoftSiteTest {

    private final String HEADER_LINE = "\"Employee/Contractor\",\"Week Ending Dt\",\"Payroll\",\"Work in Office\",\"Work in Ctry\",\"Dept\",\"Name\",\"Empl ID\"";

    @Test(expected = BadCredentialException.class)
    public void shouldThrowExceptionIfBadCredential() throws Exception {
        String badUsername = "badEddwhat";
        String badPassword = "Eee";
        PeopleSoftSite site = new PeopleSoftSite();
        site.login(badUsername,badPassword);
    }

    @Test
    public void canFetchCSV() throws Exception {
        PeopleSoftSite site = new PeopleSoftSite();
        site.login("HJIAO","jiao980701");
        String csvData = site.fetchCvsOfPeopleMissingTimesheet("31/12/2016","TCH");
        assertTrue(csvData.contains(HEADER_LINE));
    }

    @Test(expected = NoContentException.class)
    public void should_throw_no_content_exception_if_no_missing_people() throws Exception {
        PeopleSoftSite site = new PeopleSoftSite();
        site.login("HJIAO","jiao980701");
        //it's tricky. 25/03/2012 certainly has no missing people.
        site.fetchCvsOfPeopleMissingTimesheet("25/03/2012", "TCH");
    }

    @Test
    public void should_return_empty_employees_if_fecth_the_sunday_with_no_missing_people() throws Exception {
        Credential.getInstance().save("HJIAO","jiao980701");
        PeopleSoftSite site = new PeopleSoftSite();
        DateTime theSundayNoMissing = new DateTime().withDate(2012, 3, 25);
        Employees employees = site.fetch(theSundayNoMissing, "TCH");
        assertThat(employees, notNullValue());
        assertThat(employees.getEmployees().size(),equalTo(0));
    }

    @Test
    @Ignore
    public void should_parse_the_date_to_certain_format() throws Exception {

    }


}
