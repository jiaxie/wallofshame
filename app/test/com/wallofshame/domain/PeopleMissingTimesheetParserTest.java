package com.wallofshame.domain;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: twer
 * Date: 3/19/12
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class PeopleMissingTimesheetParserTest {
    @Test
    public void shouldReturnNamelist(){
        PeopleMissingTimesheetParser parser = new PeopleMissingTimesheetParser();
        String csvdata = "\"Beachball Country\",\"Missing\"\n\"China\",\"Si Xiaojing\"\n\"Brazil\",\"Ssdlfjg\"\n\"American\",\"lily\"";
        Map<String,List<String>> map = parser.parse(csvdata);
        assertEquals("Si Xiaojing", map.get("China").get(0));
        assertEquals("Ssdlfjg", map.get("Brazil").get(0));
        assertEquals("lily", map.get("American").get(0));
        assertEquals(true, map.containsKey("China"));
    }
    
}
