package com.wallofshame.domain;

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
        List<MissingPeople> people = new ArrayList<MissingPeople>();
        people.add(new MissingPeople("noreply.timesheet","NoReployTimeSheet"));
        PeopleMissingTimeSheet.getInstance().replaceAll(people);
    }

    @Test
    public void canSendEmailToPeopleMissingTimesheet() throws Exception {
        mailNotificationService.notifyMissingPeople();
    } 

    @After
    public void after() throws Exception{
        PeopleMissingTimeSheet.getInstance().replaceAll(new ArrayList<MissingPeople>());
    }
    public void setMailNotificationService(MailNotificationService mailNotificationService) {
        this.mailNotificationService = mailNotificationService;
    }
}
