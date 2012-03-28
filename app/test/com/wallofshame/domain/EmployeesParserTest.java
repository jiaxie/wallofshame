package com.wallofshame.domain;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.List;

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
    public void canFindSampleCSVData() throws Exception {
        loadCSVData();
    }


    @Test
    public void canParseCSVData() throws Exception {
        String csvSample = loadCSVData();
        Employees employees = new EmployeesParser().parse(csvSample);
        assertTrue(employees.contains(new Employee("13770","An,Hui", "Beijing")));

    }

    @Test
    public void testEqualityById() {
        assertThat(new Employee("1", "a", "Beijing"), is(new Employee("1", "a", "Beijing")));
        assertThat(new Employee("1", "a", "Beijing"), not(new Employee("2", "b", "Beijing")));
        assertThat(new Employee("1", "a", "Beijing"), is(new Employee("1", "b", "Beijing")));
    }

    private void assertContainsPeople(List<Employee> people, Employee employee) {
        for (Employee each : people){
            if(each.getId().equals(employee.getId())
                && each.getName().equals(employee.getName())
                    && each.getOffice().equals(employee.getOffice()))
                return;
        }
        fail("not found missing people :" + employee);
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
