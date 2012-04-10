package com.wallofshame.repository.peoplesoft;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Since: 4/9/12
 */
public class TimesheetSubmitDateQueryPage {

    private static Logger logger = Logger.getLogger(TimesheetSubmitDateQueryPage.class);

    private static final String QUERY_URL = "http://psfs89.thoughtworks.com/psc/fsprd89_1/EMPLOYEE/ERP/q/?ICAction=ICQryNameURL=TW_TIME_COUNTRY";
    private WebClient webClient;
    private String paroll;
    private String start;
    private String end;

    public TimesheetSubmitDateQueryPage(WebClient webClient, String payroll, String start, String end) {
        this.webClient = webClient;
        this.paroll = payroll;
        this.start = start;
        this.end = end;
    }

    public String searchAndDownloadCSV() {
        String result = null;
        try {
            HtmlPage page = openPage();
            HtmlForm form = focusOnSearchForm(page);
            fillSearchConditions(form);
            HtmlPage resultPage = doSearch(form);
            result = downloadCSV(resultPage);
        } catch (IOException e) {
            logger.error("failed to download TW_TIME_COUNTRY csv.", e);
        }
        return result;
    }

    private String downloadCSV(HtmlPage resultPage) throws IOException {
        HtmlAnchor csvLink = resultPage.getAnchorByText("CSV Text File");
        Page csvFile = csvLink.click();
        String result = csvFile.getWebResponse().getContentAsString();
        return result;
    }

    private HtmlPage doSearch(HtmlForm form) throws IOException {
        HtmlInput viewResultsButton = form.getInputByValue("View Results");
        return viewResultsButton.click();
    }

    private void fillSearchConditions(HtmlForm form) {
        HtmlTextInput payrollInput = form.getInputByName("InputKeys_COMPANY");
        HtmlTextInput startInput = form.getInputByName("InputKeys_bind3");
        HtmlTextInput endInput = form.getInputByName("InputKeys_bind4");
        payrollInput.setValueAttribute(paroll);
        startInput.setValueAttribute(start);
        endInput.setValueAttribute(end);
    }

    private HtmlForm focusOnSearchForm(HtmlPage page) {
        return page.getFormByName("win1");
    }

    private HtmlPage openPage() throws IOException {
        HtmlPage page;
        page = webClient.getPage(QUERY_URL);
        return page;
    }
}
