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
        Employees employees = repo.lookUp(QueryCondition.getLastSunday(), QueryCondition.getCompanyId());
        PeopleMissingTimeSheet.getInstance().replaceAll(employees);
    }

}
