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

}
