package com.wallofshame.domain;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeopleMissingTimesheetParser {

    public static final String BEACHBALL_HEADER = "Beachball";
    public Map<String, List<String>> parse(String cvsData) {
        Map<String, List<String>> listOfPeopleMissingTimeSheet = new HashMap<String, List<String>>();
        String[] lines = StringUtils.split(cvsData, '\n');

        for (String line : lines) {
            if (isHeaderLine(line))
                continue;
            String countryCol = StringUtils.substringBefore(line, ",");
            String nameCol = StringUtils.substringAfter(line, ",");

            String country = StringUtils.substringBetween(countryCol, "\"");
            String name = StringUtils.substringBetween(nameCol, "\"");

            collectNamesToCountry(listOfPeopleMissingTimeSheet, name, country);
        }

        return listOfPeopleMissingTimeSheet;
    }

    private void collectNamesToCountry(Map<String, List<String>> listOfPeopleMissingTimeSheet, String name, String country) {
        List<String> names = listOfPeopleMissingTimeSheet.get(country);
        if (names == null)
            listOfPeopleMissingTimeSheet.put(country, new ArrayList<String>());

        listOfPeopleMissingTimeSheet.get(country).add(name);
    }


    private boolean isHeaderLine(String line) {
        return line.contains(BEACHBALL_HEADER);
    }
}