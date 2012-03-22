package com.wallofshame.service;

import com.wallofshame.domain.Credential;
import com.wallofshame.domain.PeopleMissingTimeSheet;
import com.wallofshame.domain.PeopleMissingTimesheetParser;
import com.wallofshame.domain.peoplesoft.PeopleSoftSite;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Since: 3/16/12
 */
@Service("updateWallOfShameService")
public class UpdateWallOfShameService {
    private PeopleSoftSite site;
    public UpdateWallOfShameService() {
        this.setPeopleSoftSite(new PeopleSoftSite());
    }

    @Autowired
    private MailService mailService;


    //scheduled at every 2 hours
    @Scheduled(fixedRate = 1000 * 60 * 60 * 2)
    public void pullUpdates() {
        if(Credential.getInstance().isEmpty())
            return;
        site.login(Credential.getInstance().username(),Credential.getInstance().password());
        String cvsData = site.fetchCvsOfPeopleMissingTimesheet(lastSunday(), departmentId());
        site.cleanUp();
        Map<String,List<String>> names = new PeopleMissingTimesheetParser().parse(cvsData);
        PeopleMissingTimeSheet.getInstance().replaceAll(names);
        mailService.sendMail("robotforwallofshame@gmail.com", "1987quchen@gmail.com", "Testing today", "Testing only \n\n Hello Spring Email Sender");
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
