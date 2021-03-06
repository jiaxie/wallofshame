package com.wallofshame.service;

import com.wallofshame.domain.Employee;
import com.wallofshame.domain.Employees;
import com.wallofshame.domain.PeopleMissingTimeSheet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Since: 3/26/12
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-mail-test.xml")
public class MailNotificationServiceTest {


    @Autowired
    private MailNotificationService mailNotificationService;

    @Before
    public void setUp() throws Exception {
        List<Employee> people = new ArrayList<Employee>();
        people.add(new Employee("zhengli", "Zhiheng Li", "Chengdu"));
        people.add(new Employee("xjsi", "Xiaojing Si", "Xi'an"));
        PeopleMissingTimeSheet.getInstance().replaceAll("TCH", new Employees(people));
    }

    @Test
    public void should_send_email_to_people_missing_timesheet() throws Exception {
        mailNotificationService.notifyMissingPeople();
    }

    @Test
    public void should_not_send_email_if_no_people_missing_timesheet() throws Exception {
        PeopleMissingTimeSheet.getInstance().employeesOf("TCH").getEmployees().clear();
        mailNotificationService.notifyMissingPeople();
    }


    @After
    public void after() throws Exception {
        PeopleMissingTimeSheet.getInstance().replaceAll("TCH", new Employees(new ArrayList<Employee>()));
    }

    public void setMailNotificationService(MailNotificationService mailNotificationService) {
        this.mailNotificationService = mailNotificationService;
    }
}
