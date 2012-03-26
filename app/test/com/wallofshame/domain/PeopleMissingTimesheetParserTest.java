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
public class PeopleMissingTimesheetParserTest {

    @Test
    public void canFindSampleCSVData() throws Exception {
        loadCSVData();
    }


    @Test
    public void canParseCSVData() throws Exception {
        String csvSample = loadCSVData();
        List<MissingPeople> people = new PeopleMissingTimesheetParser().parse(csvSample);
        assertContainsPeople(people,new MissingPeople("13770","An,Hui"));
    }

    @Test
    public void testEqualityById() {
        assertThat(new MissingPeople("1", "a"), is(new MissingPeople("1", "a")));
        assertThat(new MissingPeople("1", "a"), not(new MissingPeople("2", "b")));
        assertThat(new MissingPeople("1", "a"), is(new MissingPeople("1", "b")));
    }

    private void assertContainsPeople(List<MissingPeople> people, MissingPeople missingPeople) {
        for (MissingPeople each : people)
            if (each.equals(missingPeople))
                return;
        fail("not found missing people :" + missingPeople);
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
