package com.wallofshame.domain;

import com.wallofshame.domain.peoplesoft.PeopleSoftSite;
import com.wallofshame.service.UpdateWallOfShameService;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Since: 3/16/12
 */
public class PageTest {

    @Test
    public void itMustHavePeopleMissTimesheetIfISearchFor10YearsLater(){
        Credential.getInstance().save("testuser","testpassword");
        Map<String, List<String>> names = PeopleMissingTimeSheet.getInstance().names();
        assertTrue(names.isEmpty());
        PeopleSoftSite site =  createMock(PeopleSoftSite.class);
        UpdateWallOfShameService service = new UpdateWallOfShameService();
        service.setPeopleSoftSite(site);
        site.login(Credential.getInstance().username(), Credential.getInstance().password());
        String cvsData = "\"Beachball Country\",\"Missing\"\n\"China\",\"Li,Zhiheng\"";
        expect(site.fetchCvsOfPeopleMissingTimesheet("18/03/2012","01")).andReturn(cvsData);
        replay(site);
        service.pullUpdates();
        names = PeopleMissingTimeSheet.getInstance().names();
        assertFalse(names.isEmpty());
        assertContainsName(names,"Li,Zhiheng", "China");
        verify(site);
    }

    private void assertContainsName(Map<String, List<String>> names, String expectedName, String country) {
        for(String name : names.get(country))
            if(name.equals(expectedName))
                return;
        fail("name does not exist..."+expectedName);
    }


}
