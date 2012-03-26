package com.wallofshame.domain;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PeopleMissingTimesheetParser {

    public static final String HEADER_LINE_KEYWORD = "Payroll";
    public static final int EMPLOYEE_ID_COLUMN_INDEX = 7;
    public static final int EMPLOYEE_NAME_COLUMN_INDEX = 6;

    public List<MissingPeople> parse(String cvsData) {

        List<MissingPeople> peoples = new ArrayList<MissingPeople>();
        String[] lines = StringUtils.split(cvsData, "\n");

        for (String line : lines) {
            if (isHeaderLine(line))
                continue;
            MissingPeople people = extractFromLine(line);
            peoples.add(people);
        }
        return peoples;
    }

    private MissingPeople extractFromLine(String line) {
        String[] cols = StringUtils.substringsBetween(line, "\"", "\"");
        return new MissingPeople(cols[EMPLOYEE_ID_COLUMN_INDEX], cols[EMPLOYEE_NAME_COLUMN_INDEX]);
    }

    private boolean isHeaderLine(String line) {
        return line.contains(HEADER_LINE_KEYWORD);
    }
}