package com.wallofshame.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class ShameController {
    @RequestMapping(value = "/{country}.html", method = RequestMethod.GET)
    public ModelAndView index(@PathVariable String country) {
        return new ModelAndView("login");
//        if (Credential.isEmpty) {
//            new ModelAndView("login");
//        } else {
//            new ModelAndView("index",
//                    JavaConversions.asJavaMap(Map(
//                            "names" -> PeopleMissingTimeSheet.names(country),
//                            "country" -> country.toLowerCase.replaceAll("\\s", "")
//                    )))
//        }

    }
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("login");
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
