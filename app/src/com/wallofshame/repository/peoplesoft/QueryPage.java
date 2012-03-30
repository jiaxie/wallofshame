package com.wallofshame.repository.peoplesoft;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Since: 3/16/12
 */
public class QueryPage {

    private static Logger logger = Logger.getLogger(QueryPage.class);

    public static final String QUERY_PAGE_URL = "http://psfs89.thoughtworks.com/psc/fsprd89_1/EMPLOYEE/ERP/q/?ICAction=ICQryNameURL=TW_TIME_COUNTRY_MISSING";
    private WebClient webClient;
    private String deptId;
    private String dateStr;
    private HtmlPage queryPage;

    public QueryPage(WebClient webClient, String dateStr, String deptId) {
        this.webClient = webClient;
        this.dateStr = dateStr;
        this.deptId = deptId;
        initializePage(webClient);
    }

    public String searchAndDownloadPeopleCSV(){

        prepareSearchConditions();
        String result = "";
        HtmlPage searchResultPage = null;
        try {
            searchResultPage = viewResultsButton().click();
        } catch (IOException e) {
            logger.error("failed to search people missing timesheet.",e);
        }
        result = downloadPeopleCSV(searchResultPage);
        return result;
    }

    private String downloadPeopleCSV(HtmlPage resultPage) {
        UnexpectedPage csvFile = null;
        String csv = null;
        try {
            csvFile = cvsDownloadLink(resultPage).click();
            csv = IOUtils.toString(csvFile.getInputStream());
        } catch (IOException e) {
            logger.error("failed to download csv.",e);
        } catch (ElementNotFoundException enfe){
            throw new NoContentException();
        }
        return csv;
    }

    private HtmlAnchor cvsDownloadLink(HtmlPage resultPage) {
        return resultPage.getAnchorByText("CSV Text File");
    }

    private void prepareSearchConditions() {
        dateSearchInput().setValueAttribute(dateStr);
        departmentSearchInput().setValueAttribute(deptId);
    }

    private HtmlButtonInput viewResultsButton() {
        return queryForm().getInputByValue("View Results");
    }

    private HtmlTextInput departmentSearchInput() {
        return queryForm().getInputByName("InputKeys_COMPANY");
    }

    private HtmlTextInput dateSearchInput() {
        return queryForm().getInputByName("InputKeys_TW_WEEK_END_DT");
    }

    private HtmlForm queryForm() {
        return queryPage().getFormByName("win1");
    }

    private HtmlPage queryPage() {
        return queryPage;
    }

    private void initializePage(WebClient webClient) {
        if (queryPage == null) {
            try {
                queryPage = webClient.getPage(QUERY_PAGE_URL);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }


}
