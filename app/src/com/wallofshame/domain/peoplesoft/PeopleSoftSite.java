package com.wallofshame.domain.peoplesoft;

import com.gargoylesoftware.htmlunit.WebClient;
import com.wallofshame.domain.Credential;
import com.wallofshame.domain.Employees;
import com.wallofshame.domain.EmployeesParser;
import com.wallofshame.repository.MissingTimeSheetRepositoryPeopleSoftImple;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Date;

/**
 * Since: 3/19/12
 */
public class PeopleSoftSite {
    public static final Logger logger = Logger.getLogger(PeopleSoftSite.class);
    private final WebClient webClient;

    public PeopleSoftSite() {
        webClient = new WebClient();
    }



    public Employees fetch(String lastSunDay, String officeId) {
        if (Credential.getInstance().isEmpty()) {
            return new Employees();
        }
        try {
            this.login(Credential.getInstance().username(), Credential.getInstance().password());
            String cvsData = this.fetchCvsOfPeopleMissingTimesheet(lastSunDay, officeId);
            return new EmployeesParser().parse(cvsData);
        } catch (BadCredentialException badCredentialException) {
            logger.info("Wrong password or username.Please check! People missing timesheet were not fetched.");
        } finally {
            this.cleanUp();
        }
        return new Employees();
    }

    public void login(String username, String password) throws BadCredentialException {
        LoginPage loginPage = new LoginPage(webClient);
        loginPage.login(username, password);
    }

    public String fetchCvsOfPeopleMissingTimesheet(String endDate, String deptId) {
        QueryPage queryPage = new QueryPage(webClient, endDate, deptId);
        return queryPage.searchAndDownloadPeopleCSV();
    }

    public void cleanUp() {
        webClient.closeAllWindows();
    }


}
