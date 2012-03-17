package com.wallofshame.domain;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PeopleMissingTimesheetParser {

    public static final String BEACHBALL_HEADER = "Beachball";
    private String country;

    public PeopleMissingTimesheetParser(String country) {
        this.country = country;
    }

    public List<String> parse(String cvsData) {
        ArrayList<String> namesOfPeopleMissingTimeSheet = new ArrayList<String>();
        String[] lines = StringUtils.split(cvsData, '\n');
        for (String line : lines) {
            if (isHeaderLine(line))
                continue;

            String countryCol = StringUtils.substringBefore(line, ",");
            String nameCol = StringUtils.substringAfter(line, ",");
            if (countryCol.contains(this.country))
                namesOfPeopleMissingTimeSheet.add(StringUtils.substringBetween(nameCol, "\""));
        }
        return namesOfPeopleMissingTimeSheet;
    }


    private boolean isHeaderLine(String line) {
        return line.contains(BEACHBALL_HEADER);
    }
}