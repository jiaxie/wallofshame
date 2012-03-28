package com.wallofshame.service;

import com.wallofshame.domain.Credential;
import com.wallofshame.domain.MissingPeople;
import com.wallofshame.domain.PeopleMissingTimeSheet;
import com.wallofshame.domain.PeopleMissingTimesheetParser;
import com.wallofshame.domain.peoplesoft.BadCredentialException;
import com.wallofshame.domain.peoplesoft.PeopleSoftSite;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Since: 3/16/12
 */
@Service("updateWallOfShameService")
public class UpdateWallOfShameService {

    private static final Logger logger = Logger.getLogger(UpdateWallOfShameService.class);

    private PeopleSoftSite site;

    public UpdateWallOfShameService() {
        this.setPeopleSoftSite(new PeopleSoftSite());
    }

    //scheduled at every 2 hours
    @Scheduled(fixedRate = 1000 * 60 * 60 * 2)
    public void pullUpdates() {
        if (Credential.getInstance().isEmpty())
            return;
        try {
            site.login(Credential.getInstance().username(), Credential.getInstance().password());
        } catch (BadCredentialException badCredentialException) {
            logger.info("Wrong password or username.Please check! People missing timesheet were not fetched.");
            return;
        }
        String cvsData = site.fetchCvsOfPeopleMissingTimesheet(lastSunday(), companyId());
        site.cleanUp();
        List<MissingPeople> peoples = new PeopleMissingTimesheetParser().parse(cvsData);
        PeopleMissingTimeSheet.getInstance().replaceAll(peoples);

    }

    private String lastSunday() {

        Calendar today = Calendar.getInstance();
        int dayOfWeek = today.get(Calendar.DAY_OF_WEEK) - 1;
        Date lastSunday = DateUtils.addDays(today.getTime(), 0 - dayOfWeek);
        return FastDateFormat.getInstance("dd/MM/yyyy").format(lastSunday);
    }

    private String companyId() {
        //TCH mean China
        return "TCH";
    }

    public void setPeopleSoftSite(PeopleSoftSite site) {
        this.site = site;
    }

}
