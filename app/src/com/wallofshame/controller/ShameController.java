package com.wallofshame.controller;


import com.wallofshame.domain.Credential;
import com.wallofshame.domain.PeopleMissingTimeSheet;
import com.wallofshame.service.MailService;
import com.wallofshame.service.UpdateWallOfShameService;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ShameController {


    @RequestMapping(value = "/{country}.html", method = RequestMethod.GET)
    public String index(Model model, @PathVariable("country") String country) {
        Map<String, List<String>> names = PeopleMissingTimeSheet.getInstance().names();
        List<String> nameList = names.get(country);
        if (nameList == null) {
            nameList = new ArrayList<String>();
        }
        model.addAttribute("names", nameList);
        model.addAttribute("country", country);
        return "index";

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
        UpdateWallOfShameService service = new UpdateWallOfShameService();
        service.pullUpdates();
        ApplicationContext context = new FileSystemXmlApplicationContext("/app/webapp/WEB-INF/spring-timesheet-servlet.xml");
        MailService mailService = (MailService) context.getBean("mailService");
        mailService.sendMail("robotforwallofshame@gmail.com", "1987quchen@gmail.com", "Testing another day", "Testing only \n\n Hello Spring Email Sender");


  //      updateAsyn();
        return "redirect:/China.html";
    }

    private void updateAsyn() {
        new Thread(new Runnable() {
            public void run() {


            }
        }).start();
    }
//    @RequestMapping(value = Array("/login.html"), method = Array(RequestMethod.POST))
//    def save(request:HttpServletRequest){
//        Credential.save(request.getParameter("username"), request.getParameter("password"))
//        "redirect:/wallofshame/china"
//    }

//    @RequestMapping(value = Array("/login.html"), method = Array(RequestMethod.GET))
//    def login = {
//            new ModelAndView("login")
//    }
//
//    @RequestMapping(value = Array("/login.html"), method = Array(RequestMethod.POST))
//    def save(request:HttpServletRequest){
//        Credential.save(request.getParameter("username"), request.getParameter("password"))
//        "redirect:/wallofshame/china"
//    }

}
