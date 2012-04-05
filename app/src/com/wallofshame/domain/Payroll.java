package com.wallofshame.domain;

/**
 * Since: 4/1/12
 */
public class Payroll {

    private String code;
    private String name;

    public Payroll(String code, String name) {
        this.code = code;
        this.name = name;
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
}
