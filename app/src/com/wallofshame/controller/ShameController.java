package com.wallofshame.controller;


import com.wallofshame.domain.Credential;
import com.wallofshame.domain.PeopleMissingTimeSheet;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShameController {

    private static PeopleMissingTimeSheet timeSheet = new PeopleMissingTimeSheet();

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index(Model model) {
        List<String> names = timeSheet.names();
        model.addAttribute("names", names);
        model.addAttribute("country", "index");
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
        
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            model.addAttribute("error","User name and password required.");
            return "login";
        }

        Credential.getInstance().save(username,password);
        return "redirect:/index.html";
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
