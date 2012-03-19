package com.wallofshame.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.wallofshame.domain.*;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Since: 3/16/12
 */
@Component
public class UpdateWallOfShameService {


    //scheduled at every 2 hours
    @Scheduled(fixedRate = 1000*60*60*2 )
    public void pullUpdates() {
        if(Credential.getInstance().isEmpty())
            return;
        this.fetchPeopleMissingTimesheetBefore(lastSunday());
//        this.fetchPeopleMissingTimesheetBefore("23/03/2012");
    }

    private String lastSunday() {

        Calendar today = Calendar.getInstance();
        int dayOfWeek = today.get(Calendar.DAY_OF_WEEK) - 1;
        Date lastSunday = DateUtils.addDays(today.getTime(), 0 - dayOfWeek);
        return FastDateFormat.getInstance("dd/MM/yyyy").format(lastSunday);
    }


    private String departmentId() {
        return "01";
    }


    public void fetchPeopleMissingTimesheetBefore(String dateStr) {
        WebClient webClient = new WebClient();
        LoginPage loginPage = new LoginPage(webClient);
        loginPage.login();
        QueryPage queryPage = new QueryPage(webClient,dateStr,"01");
        Map<String,List<String>> names = new PeopleMissingTimesheetParser().parse(queryPage.searchAndDownloadPeopleCSV());
        PeopleMissingTimeSheet.getInstance().replaceAll(names);
    }
}
