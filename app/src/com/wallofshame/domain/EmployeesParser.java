package com.wallofshame.domain;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeesParser {

    public static final String HEADER_LINE_KEYWORD = "Payroll";
    public static final int EMPLOYEE_ID_COLUMN_INDEX = 7;
    public static final int EMPLOYEE_NAME_COLUMN_INDEX = 6;
    public static final int EMPLOYEE_OFFICE_COLUMN_INDEX = 3;

    public Employees parse(String cvsData) {
        Employees employees = new Employees();
        String[] lines = StringUtils.split(cvsData, "\n");
        for (String line : lines) {
            if (isHeaderLine(line)) {
                continue;
            }
            employees.add(extractFromLine(line));
        }
        return employees;
    }

    private Employee extractFromLine(String line) {
        String[] cols = StringUtils.substringsBetween(line, "\"", "\"");
        return new Employee(cols[EMPLOYEE_ID_COLUMN_INDEX], cols[EMPLOYEE_NAME_COLUMN_INDEX], cols[EMPLOYEE_OFFICE_COLUMN_INDEX]);
    }

    private boolean isHeaderLine(String line) {
        return line.contains(HEADER_LINE_KEYWORD);
    }
}