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

    private boolean toggle = false;

    @Autowired
    public MailNotificationService(final MailSender mailSender, final SimpleMailMessage templateMessage) {
        this.mailSender = mailSender;
        this.templateMessage = templateMessage;
    }

    @Scheduled(cron = "0 30 08 ? * Mon,Tue")
    public void notifyMissingPeople() {

        List<MissingPeople> people = PeopleMissingTimeSheet.getInstance().names();

        if(!toggle){
           StringBuilder builder = new StringBuilder();
           for(MissingPeople each :people)
               builder.append(each).append("\n");

            SimpleMailMessage message = new SimpleMailMessage(templateMessage);
            message.setTo("zhengli@thoughtworks.com");
            message.setCc(new String[]{"khu@thoughtworks.com","sjsi@thoughtworks.com","hjiao@thoughtworks.com"});
            message.setText(builder.toString());
            mailSender.send(message);
            return;
        }

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
