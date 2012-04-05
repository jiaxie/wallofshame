package com.wallofshame.service;

import com.wallofshame.domain.Employees;
import com.wallofshame.domain.Payroll;
import com.wallofshame.domain.Payrolls;
import com.wallofshame.domain.PeopleMissingTimeSheet;
import com.wallofshame.repository.MissingTimeSheetRepository;
import com.wallofshame.repository.PayrollRepository;
import com.wallofshame.utils.DateTimeUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Since: 3/16/12
 */
@Service("timesheetUpdateService")
public class TimesheetUpdateServiceImpl implements TimesheetUpdateService {

    private MissingTimeSheetRepository repo;
    private PayrollRepository payrollRepository;

    @Autowired
    public TimesheetUpdateServiceImpl(final MissingTimeSheetRepository repo,
                                      final PayrollRepository payrollRepository) {
        this.repo = repo;
        this.payrollRepository = payrollRepository;
    }

    //scheduled at every 2 hours
    @Scheduled(fixedRate = 1000 * 60 * 60 * 2)
    public void batchPullUpdates() {
        Payrolls payrolls = payrollRepository.load();
        List<Payroll> payrollList = payrolls.list();
        for (Payroll payroll : payrollList) {
            pullSingleUpdate(payroll);
        }
    }

    private void pullSingleUpdate(final Payroll payroll) {
        Employees employees = repo.lookUp(lastSunday(), payroll.getCode());
        PeopleMissingTimeSheet.getInstance().replaceAll(payroll.getCode(), employees);
    }

    private DateTime lastSunday() {
        return DateTimeUtils.lastSunday(new DateTime());
    }

}
