package com.wallofshame.domain;

import com.wallofshame.domain.peoplesoft.BadCredentialException;
import com.wallofshame.domain.peoplesoft.PeopleSoftSite;
import org.junit.Test;
/**
 * Since: 3/26/12
 */
public class PeopleSoftSiteTest {
    
    @Test(expected = BadCredentialException.class)
    public void shouldThrowExceptionIfBadCredential() throws BadCredentialException {
        String badUsername = "badEddwhat";
        String badPassword = "Eee";
        PeopleSoftSite site = new PeopleSoftSite();
        site.login(badUsername,badPassword);
    }
}
