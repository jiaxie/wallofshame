package com.wallofshame.domain;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: twer
 * Date: 3/19/12
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmployeesParserTest {

    @Test
    public void should_parse_csv() throws Exception {
        String csvSample = loadCSVData();
        Employees employees = new EmployeesParser().parse(csvSample);
        assertTrue(employees.contains(new Employee("13770", "An,Hui", "Beijing")));

    }

    @Test
    public void should_equals_by_employee_id() {
        assertThat(new Employee("1", "a", "Beijing"), is(new Employee("1", "a", "Beijing")));
        assertThat(new Employee("1", "a", "Beijing"), not(new Employee("2", "b", "Beijing")));
        assertThat(new Employee("1", "a", "Beijing"), is(new Employee("1", "b", "Beijing")));
    }

    private String loadCSVData() throws Exception {
        File sample = new File("./app/scripts/TW_TIME_COUNTRY_MISSING.csv");
        InputStream is = FileUtils.openInputStream(sample);
        assertNotNull(is);
        String result = IOUtils.toString(is);
        IOUtils.closeQuietly(is);
        return result;
    }


}
