package com.wallofshame.repository.peoplesoft;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Since: 3/16/12
 */
public class LoginPage {
    
    private static Logger logger = Logger.getLogger(LoginPage.class);

    public static final String LOGIN_PAGE_URL = "http://psfs89.thoughtworks.com/psp/fsprd89/?cmd=login&languageCd=ENG";
    public static final String SIGN_IN = "Sign In";
    public static final String PASSWORD = "pwd";
    public static final String USERID = "userid";
    public static final String LOGIN = "login";
    private WebClient webClient;
    private HtmlPage loginPage;

    public LoginPage(WebClient webClient) {
        initializePage(webClient);
        //keep orignal web client
        this.webClient = webClient;
    }

    public void login(String username, String password) throws BadCredentialException {
        userIdTextInput().setValueAttribute(username);
        passwordTextInput().setValueAttribute(password);
        clickSignInButton();
        shouldOnDefaultPage();
    }

    private void shouldOnDefaultPage() throws BadCredentialException {
        URL currentLocation = webClient.getCurrentWindow().getEnclosedPage().getUrl();
        if(!defaultPage().equals(currentLocation))
            throw new BadCredentialException();
    }

    private URL defaultPage(){
        try {
            return new URL("http://psfs89.thoughtworks.com/psp/fsprd89/EMPLOYEE/ERP/h/?tab=DEFAULT");
        } catch (MalformedURLException e) {
            logger.error("please",e);
        }
        //should never come here.
        return null;
    }

    private void clickSignInButton() {
        try {
            webClient.setJavaScriptEnabled(false);
            HtmlPage page = signinButton().click();
            webClient.setJavaScriptEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HtmlSubmitInput signinButton() {
        return loginForm().getInputByValue(SIGN_IN);
    }

    private HtmlPage loginPage() {
        return loginPage;
    }

    private void initializePage(WebClient webClient) {
        if (loginPage == null) {
            try {
                loginPage = webClient.getPage(LOGIN_PAGE_URL);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private HtmlPasswordInput passwordTextInput() {
        return loginForm().getInputByName(PASSWORD);
    }

    private HtmlTextInput userIdTextInput() {
        return loginForm().getInputByName(USERID);
    }

    private HtmlForm loginForm() {
        return loginPage().getFormByName(LOGIN);
    }

}
