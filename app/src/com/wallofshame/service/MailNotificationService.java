package com.wallofshame.service;

import com.wallofshame.domain.MissingPeople;
import com.wallofshame.domain.PeopleMissingTimeSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Since: 3/26/12
 */

@Service("mailNotificationService")
public class MailNotificationService {

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    @Autowired
    public MailNotificationService(final MailSender mailSender, final SimpleMailMessage templateMessage) {
        this.mailSender = mailSender;
        this.templateMessage = templateMessage;
    }

    @Scheduled(cron = "0 30 08 ? * Mon,Tue")
    public void notifyMissingPeople() {

        List<MissingPeople> people = PeopleMissingTimeSheet.getInstance().names();

        for (MissingPeople each : people)
            sendMailTo(each);
    }

    private void sendMailTo(MissingPeople missingPeople) {
        SimpleMailMessage message = new SimpleMailMessage(templateMessage);
        message.setTo(calculateAddress(missingPeople));
        mailSender.send(message);
    }

    private String calculateAddress(MissingPeople missingPeople) {
        return missingPeople.getId() + "@thoughtworks.com";
    }

}
