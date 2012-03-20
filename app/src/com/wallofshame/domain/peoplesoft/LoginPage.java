package com.wallofshame.domain.peoplesoft;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;

/**
 * Since: 3/16/12
 */
public class LoginPage {

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

    public void login(String username, String password) {
        userIdTextInput().setValueAttribute(username);
        passwordTextInput().setValueAttribute(password);
        clickSignInButton();
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
