package com.wallofshame.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Since: 4/5/12
 */
public class Payrolls {

    List<Payroll> payrolls;

    public Payrolls() {
        payrolls = new ArrayList<Payroll>();
    }

    public List<Payroll> list() {
        return payrolls;
    }

    public void add(Payroll payroll) {
        this.payrolls.add(payroll);
    }
}
