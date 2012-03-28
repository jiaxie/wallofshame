package com.wallofshame.service;

import com.wallofshame.domain.*;
import com.wallofshame.domain.peoplesoft.BadCredentialException;
import com.wallofshame.domain.peoplesoft.PeopleSoftSite;
import com.wallofshame.repository.MissingTimeSheetRepository;
import com.wallofshame.repository.MissingTimeSheetRepositoryPeopleSoftImple;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    private MissingTimeSheetRepository repo;

    @Autowired
    public UpdateWallOfShameService(MissingTimeSheetRepository repo) {
        this.repo = repo;
    }

    //scheduled at every 2 hours
    @Scheduled(fixedRate = 1000 * 60 * 60 * 2)
    public void pullUpdates() {
        Employees employees = repo.lookUp(lastSunday(), companyId());
        PeopleMissingTimeSheet.getInstance().replaceAll(employees);
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
}
