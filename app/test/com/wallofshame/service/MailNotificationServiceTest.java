package com.wallofshame.service;

import com.wallofshame.domain.Employee;
import com.wallofshame.domain.Employees;
import com.wallofshame.domain.PeopleMissingTimeSheet;
import com.wallofshame.service.MailNotificationService;
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
        people.add(new Employee("zhengli","Zhiheng Li", "Chengdu"));
        people.add(new Employee("xjsi","Xiaojing Si", "Xi'an"));
        PeopleMissingTimeSheet.getInstance().replaceAll(new Employees(people));
    }

    @Test
    public void canSendEmailToPeopleMissingTimesheet() throws Exception {
        mailNotificationService.notifyMissingPeople();
    } 

    @After
    public void after() throws Exception{
        PeopleMissingTimeSheet.getInstance().replaceAll(new Employees(new ArrayList<Employee>()));
    }
    public void setMailNotificationService(MailNotificationService mailNotificationService) {
        this.mailNotificationService = mailNotificationService;
    }
}