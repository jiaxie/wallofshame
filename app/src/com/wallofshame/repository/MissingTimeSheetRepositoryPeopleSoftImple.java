package com.wallofshame.repository;

import com.wallofshame.domain.*;
import com.wallofshame.repository.peoplesoft.PeopleSoftSite;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class MissingTimeSheetRepositoryPeopleSoftImple implements MissingTimeSheetRepository {

    private PeopleSoftSite peopleSoft = new PeopleSoftSite();


    public Employees lookUp(DateTime lastSunDay, String officeId) {
        return peopleSoft.fetch(lastSunDay, officeId);
    }
}
