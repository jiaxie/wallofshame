package com.wallofshame.repository.peoplesoft;

import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: twer
 * Date: 3/15/12
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Credential {
    private static Credential ourInstance = new Credential();
    private String username;
    private String password;

    public static Credential getInstance() {
        return ourInstance;
    }

    private Credential() {
    }

    public void save(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }


    public boolean isEmpty(){
        return  StringUtils.isBlank(username) || StringUtils.isBlank(password);
    }
}
