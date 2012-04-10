package com.wallofshame.repository;

import com.wallofshame.domain.Timesheet;
import com.wallofshame.domain.Timesheets;
import com.wallofshame.repository.peoplesoft.BadCredentialException;
import com.wallofshame.repository.peoplesoft.Credential;
import com.wallofshame.repository.peoplesoft.PeopleSoftSite;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;


/**
 * Since: 4/10/12
 */
@Repository
public class TimesheetRepositoryImpl implements TimesheetRepository {

    private Logger logger = Logger.getLogger(TimesheetRepositoryImpl.class);

    public Timesheets find(String payroll, DateTime start, DateTime end) {
        PeopleSoftSite site = new PeopleSoftSite();
        try {
            site.login(Credential.getInstance().username(), Credential.getInstance().password());
        } catch (BadCredentialException e) {
            logger.error("failed to login to peoplesoft. please check the credential provided.", e);
            throw new RuntimeException(e);
        }
        String csv = site.fetchCSVOfTimesheet(payroll, start, end);
        Timesheets timesheets = parse(csv);
        return timesheets;
    }

    private Timesheets parse(String csv) {
        Timesheets timesheets = new Timesheets();
        String[] lines = StringUtils.split(csv, "\n");
        for (String line : lines) {
            if (isHeaderLine(line))
                continue;
            Timesheet timesheet = parseSingle(line);
            timesheets.add(timesheet);
        }
        return timesheets;
    }

    private Timesheet parseSingle(String line) {
        String[] columns = StringUtils.substringsBetween(line, "\"", "\"");
        String name = columns[1];
        String endingWeekDay = columns[13];
        String submitDate = columns[14];
        Timesheet timesheet = new Timesheet(name, endingWeekDay, submitDate);
        return timesheet;
    }

    private boolean isHeaderLine(String line) {
        return line.contains("Submit Datetime");
    }
}
