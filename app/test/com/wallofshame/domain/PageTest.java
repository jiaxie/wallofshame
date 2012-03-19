package com.wallofshame.domain;

import com.gargoylesoftware.htmlunit.WebClient;
import com.wallofshame.service.UpdateWallOfShameService;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Since: 3/16/12
 */
public class PageTest {

    @Test
    public void itMustHavePeopleMissTimesheetIfISearchFor10YearsLater(){
        Credential.getInstance().save("HJIAO","jiao980701");
        Map<String, List<String>> names = PeopleMissingTimeSheet.getInstance().names();
        assertTrue(names.isEmpty());
        UpdateWallOfShameService service = new UpdateWallOfShameService();
        service.fetchPeopleMissingTimesheetBefore("31/12/2022");
        names = PeopleMissingTimeSheet.getInstance().names();
        assertFalse(names.isEmpty());
    }
}
