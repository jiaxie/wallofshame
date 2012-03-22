package com.wallofshame.domain.peoplesoft;

import com.gargoylesoftware.htmlunit.WebClient;

/**
 * Since: 3/19/12
 */
public class PeopleSoftSite {

    private final WebClient webClient;

    public PeopleSoftSite() {
        webClient = new WebClient();
    }

    public void login(String username, String password) {
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
