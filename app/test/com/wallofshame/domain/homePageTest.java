package com.wallofshame.domain;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: twer
 * Date: 3/20/12
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class homePageTest{
    private WebClient webClient = new WebClient();
    @Test
    public void canReturnPageTitle() throws Exception{

        final HtmlPage htmlPage = webClient.getPage("http://htmlunit.sourceforge.net");
        assertEquals("Welcome to HtmlUnit",htmlPage.getTitleText());
        final String pageAsxml = htmlPage.asXml();
        assertTrue(pageAsxml.contains("<div id=\"bodyColumn\">"));
        final String pageAsText = htmlPage.asText();
        assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
        webClient.closeAllWindows();
    }
    @Test
    public void canReturnTitle() throws Exception{
       // final WebClient webClient = new WebClient(BrowserVersion.getDefault());
        final HtmlPage page = webClient.getPage("http://www.iteye.com/topic/583213");
        assertEquals("Spring学习笔记：Spring 框架概述 - Spring - Java - ITeye论坛",page.getTitleText());
        webClient.closeAllWindows();
    }
    @Test
    public void canLogin() throws Exception{
       // final WebClient webClient = new WebClient();
        final HtmlPage page = webClient.getPage("http://psfs89.thoughtworks.com/psp/fsprd89/?cmd=login&languageCd=ENG");
        final HtmlForm form = page.getFormByName("login");
        final HtmlPasswordInput passwordInput = form.getInputByName("pwd");
        final HtmlTextInput useridInput = form.getInputByName("userid");
        final HtmlSubmitInput submitBotton = form.getInputByName("Submit");
        useridInput.setValueAttribute("HJIAO");
        passwordInput.setValueAttribute("jiao980701");

        HtmlPage page1 = submitBotton.click();
      //  final HtmlPage page2 = webClient.getPage("http://psfs89.thoughtworks.com/psp/fsprd89/EMPLOYEE/ERP/h/?tab=DEFAULT");
        String title = page1.getTitleText();
        assertEquals("Employee-facing registry content",title);

    }
    @Test
    public void canFetchMail() throws Exception{
        HtmlPage htmlPage = webClient.getPage("http://www.renren.com/SysHome.do");
       // HtmlElement form = htmlPage.getElementById("loginForm");
       // HtmlTextInput emailInput =
        HtmlElement inputEmail = htmlPage.getElementById("email");
        HtmlElement inputPsw = htmlPage.getElementById("password");
        inputEmail.click();
        inputEmail.type("happywutongshu@163.com");
        //inputEmail.setNodeValue("happywutongshu@163.com");
        inputPsw.click();
        inputPsw.type("sixiaojing");
        HtmlElement login = htmlPage.getElementById("login");
        HtmlPage page1 = login.click();
      //  assertEquals("人人网 - 司晓静",page1.getTitleText());

      //  HtmlButton loginBtn = form.getButtonByName("loginBtn");


    }

}
