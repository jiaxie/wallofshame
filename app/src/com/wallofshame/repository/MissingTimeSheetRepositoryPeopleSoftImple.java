package com.wallofshame.repository;

import com.wallofshame.domain.*;
import com.wallofshame.domain.peoplesoft.BadCredentialException;
import com.wallofshame.domain.peoplesoft.PeopleSoftSite;
import com.wallofshame.service.UpdateWallOfShameService;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class MissingTimeSheetRepositoryPeopleSoftImple implements MissingTimeSheetRepository {
    private static final Logger logger = Logger.getLogger(UpdateWallOfShameService.class);
    private PeopleSoftSite peopleSoft = new PeopleSoftSite();


    public Employees lookUp(String lastSunDay, String officeId) {
        if (Credential.getInstance().isEmpty())
            return new Employees();
        try {
            peopleSoft.login(Credential.getInstance().username(), Credential.getInstance().password());
        } catch (BadCredentialException badCredentialException) {
            logger.info("Wrong password or username.Please check! People missing timesheet were not fetched.");
            return new Employees();
        }
        String cvsData = peopleSoft.fetchCvsOfPeopleMissingTimesheet(QueryCondition.getLastSunday(), QueryCondition.getCompanyId());
        peopleSoft.cleanUp();
        return new EmployeesParser().parse(cvsData);
    }

}
