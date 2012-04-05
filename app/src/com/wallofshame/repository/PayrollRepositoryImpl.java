package com.wallofshame.repository;

import com.wallofshame.domain.Payroll;
import com.wallofshame.domain.Payrolls;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Since: 4/5/12
 */
public class PayrollRepositoryImpl implements PayrollRepository, ApplicationContextAware {

    private String configFile;
    private ApplicationContext applicationContext;

    public PayrollRepositoryImpl(final String configFile) {
        this.configFile = configFile;
    }

    public Payrolls load() {
        Payrolls payrolls = new Payrolls();
        XMLConfiguration configuration;
        Resource resource = applicationContext.getResource(configFile);
        try {
            configuration = new XMLConfiguration(resource.getFile());
        } catch (ConfigurationException e) {
            return new Payrolls();
        } catch (IOException e) {
            throw new RuntimeException("No payrolls config file found.", e);
        }

        List definedPayrolls = configuration.getList("payrolls.payroll.code", Collections.emptyList());
        int size = definedPayrolls.size();
        for (int n = 0; n < size; n++) {
            String code = configuration.getString("payrolls.payroll(" + n + ").code");
            String name = configuration.getString("payrolls.payroll(" + n + ").name");
            boolean emailNotication = configuration.getBoolean("payrolls.payroll(" + n + ").emailNotification", false);
            List ccList = configuration.getList("payrolls.payroll(" + n + ").ccList", Collections.emptyList());
            Payroll payroll = new Payroll(code, name, emailNotication);
            for (Object cc : ccList)
                payroll.addCC((String) cc);
            payrolls.add(payroll);
        }
        return payrolls;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
