package com.wallofshame.domain;

import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Since: 3/16/12
 */
public class QueryPage {

    public static final String QUERY_PAGE_URL = "http://psfs89.thoughtworks.com/psc/fsprd89_1/EMPLOYEE/ERP/q/?ICAction=ICQryNameURL=PUBLIC.TW_UTIL_TIME_MISSING_DETAIL";
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
        try {
        HtmlPage searchResultPage = viewResultsButton().click();
            result = downloadPeopleCSV(searchResultPage);
        } catch (IOException e) {
            return "";
        }
        return result;
    }

    private String downloadPeopleCSV(HtmlPage resultPage) throws IOException {
        UnexpectedPage csvFile = null;
        csvFile = cvsDownloadLink(resultPage).click();
        return IOUtils.toString(csvFile.getInputStream());
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
        return queryForm().getInputByName("InputKeys_DEPTID");
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
