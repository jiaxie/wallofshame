package com.wallofshame.service;

import com.wallofshame.domain.Employee;
import com.wallofshame.domain.PeopleMissingTimeSheet;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Since: 3/26/12
 */

@Service("mailNotificationService")
public class MailNotificationService {

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    @Autowired
    public MailNotificationService(final MailSender mailSender,
                                   final SimpleMailMessage templateMessage) {

        this.mailSender = mailSender;
        this.templateMessage = templateMessage;
    }
    
    public void notifyMissingPeopleAsyn() {
        ExecutorService service  = Executors.newSingleThreadExecutor();
        service.submit(new AsynNotificationTask());
        service.shutdown();
    }


    @Scheduled(cron = "0 30 08 ? * Mon,Tue")
    public void notifyMissingPeople() {

        List<Employee> peoples = PeopleMissingTimeSheet.getInstance().names();
        if(peoples.isEmpty()){
            return ;
        }
        String[] toList = collectEmailToList(peoples);
        String text = buildMailText(peoples);
        SimpleMailMessage message = new SimpleMailMessage(templateMessage);
        message.setTo(toList);
        message.setText(text);
        mailSender.send(message);

    }

    private String[] collectEmailToList(List<Employee> peoples) {
        List<String> tos = new ArrayList<String>();
        for (Employee people : peoples)
            tos.add(calculateAddress(people));
        return tos.toArray(new String[]{});
    }

    private String buildMailText(List<Employee> peoples) {
        StringBuilder builder = new StringBuilder();
        builder.append(templateHeader())
                .append(collectNames(peoples))
                .append(templateFooter());
        return builder.toString();
    }

    private String collectNames(List<Employee> peoples) {
        StringBuilder names = new StringBuilder();
        names.append("People missing timesheet,\n");
        String header = formatRow("ID", "Name", "Office");
        names.append(header).append("\n");
        for(Employee people : peoples)
            names.append(formatRow(people.getId(),people.getName(),people.getOffice())).append("\n");
        return names.toString();
    }

    private String formatRow(String id, String name, String office) {
        return StringUtils.rightPad(id, 20, " ")+StringUtils.rightPad(name,50," ")+StringUtils.rightPad(office,20," ");
    }

    private String templateHeader() {
        return "Dear ThoughtWorker:\n" +
                "Just kindly remind you, you forgot to submit the timesheet, could you please submit it once you saw the email, it would not take more than 2 minutes, It will help us a lot in term of invoice processing, report generation and etc.\n" +
                "\n\n\n" ;
    }
    private String templateFooter() {
        return "\n\n\nThanks very much.";
    }


    private String calculateAddress(Employee employee) {
        return employee.getId() + "@thoughtworks.com";
    }

    private class AsynNotificationTask implements Runnable {
        public void run() {
            MailNotificationService.this.notifyMissingPeople();
        }
    }
}
