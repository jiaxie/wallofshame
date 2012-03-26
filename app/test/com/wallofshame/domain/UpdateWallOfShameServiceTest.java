package com.wallofshame.domain;

import static org.junit.Assert.*;

import com.wallofshame.domain.peoplesoft.PeopleSoftSite;
import com.wallofshame.service.UpdateWallOfShameService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * Since: 3/16/12
 */
public class UpdateWallOfShameServiceTest {

    //fixture
    @Before
    public void setUp() {
        Credential.getInstance().save("testuser","testpassword");
    }

    @Test
    public void canPullUpdatesFromPeopleSoftSite() throws Exception {
        PeopleSoftSite site =  createMock(PeopleSoftSite.class);
        site.login(Credential.getInstance().username(), Credential.getInstance().password());
        String cvsData = loadCSVData();
        expect(site.fetchCvsOfPeopleMissingTimesheet(isA(String.class), isA(String.class))).andReturn(cvsData);
        site.cleanUp();
        replay(site);

        List<MissingPeople> names = PeopleMissingTimeSheet.getInstance().names();
        assertTrue(names.isEmpty());

        UpdateWallOfShameService service = new UpdateWallOfShameService();
        service.setPeopleSoftSite(site);
        service.pullUpdates();
        names = PeopleMissingTimeSheet.getInstance().names();
        assertFalse(names.isEmpty());
        assertContainsName(names,new MissingPeople("13770","An,Hui"));

        verify(site);
    }


    private String loadCSVData() throws Exception {
        File sample = new File("./app/scripts/TW_TIME_COUNTRY_MISSING.csv");
        InputStream is = FileUtils.openInputStream(sample);
        assertNotNull(is);
        String result = IOUtils.toString(is);
        IOUtils.closeQuietly(is);
        return result;
    }


    private void assertContainsName(List<MissingPeople> names, MissingPeople exMissingPeople) {
        for(MissingPeople name : names)
            if(name.equals(exMissingPeople))
                return;
        fail("name does not exist..."+exMissingPeople);
    }


}
