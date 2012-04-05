package com.wallofshame.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Since: 4/1/12
 */
public class Payroll {

    private String code;
    private String name;
    private List<String> ccList;
    private boolean emailNotification;

    public Payroll(String code, String name, boolean emailNotification) {
        this.code = code;
        this.name = name;
        this.emailNotification = emailNotification;
        ccList = new ArrayList<String>();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payroll payroll = (Payroll) o;

        if (code != null ? !code.equals(payroll.code) : payroll.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public List<String> ccList() {
        return ccList;
    }

    public void addCC(String cc) {
        ccList.add(cc);
    }

    public boolean isEmailNotification() {
        return emailNotification;
    }
}
