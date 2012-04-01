package com.wallofshame.controller;


import com.wallofshame.domain.Employees;
import com.wallofshame.domain.PeopleMissingTimeSheet;
import com.wallofshame.repository.peoplesoft.Credential;
import com.wallofshame.service.MailNotificationService;
import com.wallofshame.service.TimesheetUpdateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Set;

@Controller
public class ShameController {


    @Autowired
    private TimesheetUpdateService updateWallOfShameService;
    @Autowired
    private MailNotificationService mailNotificationService;

    @RequestMapping(value = "/{country}.html", method = RequestMethod.GET)
    public String index(Model model, @PathVariable("country") String country,
                        @RequestParam("office") String office) {

        if (Credential.getInstance().isEmpty())
            return "redirect:/login.html";

        PeopleMissingTimeSheet timeSheet = PeopleMissingTimeSheet.getInstance();
        Employees employees = timeSheet.employeesOf(country);
        Date lastUpdateTime = timeSheet.lastUpdateTime();
        model.addAttribute("peoples", employees.atOffice(office));
        Set<String> offices = employees.availableOffices();
             office = "All";
        model.addAttribute("offices", offices);
        model.addAttribute("country", country);
        model.addAttribute("selectedOffice", office);
        model.addAttribute("payrolls", timeSheet.supportedPayrolls());
        model.addAttribute("lastUpdateTime", lastUpdateTime);

        return "index";

    }

    private boolean stillValidOffice(String office, Set<String> offices) {
        if("All".equals(office))
            return true;
        for(String each : offices)
            if(each.equals(office))
                return true;
        return false;
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String postCredential(HttpServletRequest request, Model model) {
        String username = StringUtils.trimToEmpty(request.getParameter("username"));
        String password = StringUtils.trimToEmpty(request.getParameter("password"));

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            model.addAttribute("error", "User name and password required.");
            return "login";
        }

        Credential.getInstance().save(username, password);
        updateAsyn();
        return "redirect:/loading.html";
    }

    @RequestMapping(value = "/loading.html", method = RequestMethod.GET)
    public String loading(Model model) {
        model.addAttribute("payroll", "TCH");
        model.addAttribute("office", "All");
        return "load";
    }

    private void updateAsyn() {
        new Thread(new Runnable() {
            public void run() {

                updateWallOfShameService.batchPullUpdates();

            }
        }).start();
    }


    @RequestMapping(value = "/{country}.html", method = RequestMethod.POST)
    public String sendEmail(Model model, @PathVariable("country") String country,
                        @RequestParam("office") String office) {

        mailNotificationService.notifyMissingPeopleAsyn();
        String info = "Mails are sent!";
        if (PeopleMissingTimeSheet.getInstance().isEmpty()) {
            info = "Everyone has submited timsheet!";
        }
        model.addAttribute("info", info);

        return index(model,country,office);
    }

    public void setUpdateWallOfShameService(TimesheetUpdateService updateWallOfShameService) {
        this.updateWallOfShameService = updateWallOfShameService;
    }

    public void setMailNotificationService(MailNotificationService mailNotificationService) {
        this.mailNotificationService = mailNotificationService;
    }
}
