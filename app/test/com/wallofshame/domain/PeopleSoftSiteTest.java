package com.wallofshame.domain;

import com.wallofshame.domain.peoplesoft.BadCredentialException;
import com.wallofshame.domain.peoplesoft.PeopleSoftSite;
import org.junit.Ignore;
import org.junit.Test;


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
}
