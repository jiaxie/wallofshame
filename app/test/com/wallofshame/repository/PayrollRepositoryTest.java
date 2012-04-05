package com.wallofshame.repository;

import com.wallofshame.domain.Payroll;
import com.wallofshame.domain.Payrolls;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Since: 4/5/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-mail-test.xml")
public class PayrollRepositoryTest {

    @Autowired
    private PayrollRepository payrollRepository;

    @Test
    public void should_return_payrolls() throws Exception {
        Payrolls payrolls = payrollRepository.load();
        List<Payroll> payrollList = payrolls.list();
        assertThat(2, equalTo(payrollList.size()));
        Payroll payrollTCH = payrollList.get(0);
        assertThat(payrollTCH.getCode(), equalTo("TCH"));
        assertThat(payrollTCH.isEmailNotification(), is(true));
        List<String> ccList = payrollTCH.ccList();
        assertThat(ccList.get(0), equalTo("zhengli@thoughtworks.com"));
        assertThat(ccList.get(1), equalTo("xjsi@thoughtworks.com"));
    }
}
