package com.wallofshame.repository;

import com.wallofshame.domain.*;
import com.wallofshame.domain.peoplesoft.PeopleSoftSite;
import org.springframework.stereotype.Component;

@Component
public class MissingTimeSheetRepositoryPeopleSoftImple implements MissingTimeSheetRepository {

    private PeopleSoftSite peopleSoft = new PeopleSoftSite();

    public Employees lookUp(String lastSunDay, String officeId) {
        return peopleSoft.fetch(lastSunDay, officeId);
    }
}
