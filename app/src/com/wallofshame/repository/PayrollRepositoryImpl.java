package com.wallofshame.repository;

import com.wallofshame.domain.Payroll;
import com.wallofshame.domain.Payrolls;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.File;
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
        XMLConfiguration configuration = loadConfiguration();
        for (int index = 0; index < configuredPayrollCount(configuration); index++) {
            Payroll payroll = extractPayroll(configuration, index);
            payrolls.add(payroll);
        }
        return payrolls;
    }

    private XMLConfiguration loadConfiguration() {
        XMLConfiguration configuration;
        try {
            configuration = new XMLConfiguration(resolveConfigFile());
        } catch (ConfigurationException e) {
            throw new RuntimeException("Failed to load payrolls configuration.", e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load payrolls configuration file.", e);
        }
        return configuration;
    }

    private int configuredPayrollCount(XMLConfiguration configuration) {
        List definedPayrolls = configuration.getList("payrolls.payroll.code", Collections.emptyList());
        return definedPayrolls.size();
    }

    private Payroll extractPayroll(XMLConfiguration configuration, int index) {
        String code = configuration.getString("payrolls.payroll(" + index + ").code");
        String name = configuration.getString("payrolls.payroll(" + index + ").name");
        boolean emailNotication = configuration.getBoolean("payrolls.payroll(" + index + ").emailNotification", false);
        List ccList = configuration.getList("payrolls.payroll(" + index + ").ccList", Collections.emptyList());
        Payroll payroll = new Payroll(code, name, emailNotication);
        for (Object cc : ccList)
            payroll.addCC((String) cc);
        return payroll;
    }

    private File resolveConfigFile() throws IOException {
        return applicationContext.getResource(configFile).getFile();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
