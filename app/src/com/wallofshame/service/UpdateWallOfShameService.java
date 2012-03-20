package com.wallofshame.service;

import com.wallofshame.domain.*;
import com.wallofshame.domain.peoplesoft.PeopleSoftSite;
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

    private PeopleSoftSite site;

    public UpdateWallOfShameService() {
        this.setPeopleSoftSite(new PeopleSoftSite());
    }

    //scheduled at every 2 hours
    @Scheduled(fixedRate = 1000 * 60 * 60 * 2)
    public void pullUpdates() {
        if(Credential.getInstance().isEmpty())
            return;
        site.login(Credential.getInstance().username(),Credential.getInstance().password());
        String cvsData = site.fetchCvsOfPeopleMissingTimesheet(lastSunday(), departmentId());
        Map<String,List<String>> names = new PeopleMissingTimesheetParser().parse(cvsData);
        PeopleMissingTimeSheet.getInstance().replaceAll(names);
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


    public void setPeopleSoftSite(PeopleSoftSite site) {
          this.site = site;
    }
}
