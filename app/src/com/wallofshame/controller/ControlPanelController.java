package com.wallofshame.controller;

import com.wallofshame.domain.PeopleMissingTimeSheet;
import com.wallofshame.repository.peoplesoft.Credential;
import com.wallofshame.service.MailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Since: 3/27/12
 */
@Controller
public class ControlPanelController {

    private MailNotificationService mailNotificationService;

    @Autowired
    public ControlPanelController(MailNotificationService mailNotificationService) {
        this.mailNotificationService = mailNotificationService;
    }

    @RequestMapping(value = "/control.html", method = RequestMethod.GET)
    public String show() {
        if (Credential.getInstance().isEmpty())
            return "login";
        return "control";
    }

    @RequestMapping(value = "/control.html", method = RequestMethod.POST)
    public String sendEmail(Model model) {
        mailNotificationService.notifyMissingPeopleAsyn();
        String info;
        if (PeopleMissingTimeSheet.getInstance().isEmpty()) {

            info = "Everyone has submited timsheet!";
        } else {
            info = "Mails are sent!";
        }
        model.addAttribute("info", info);
        return "control";
    }
}
