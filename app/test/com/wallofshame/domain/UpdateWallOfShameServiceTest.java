package com.wallofshame.domain;

import com.wallofshame.domain.peoplesoft.PeopleSoftSite;
import com.wallofshame.service.UpdateWallOfShameService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Since: 3/16/12
 */
public class UpdateWallOfShameServiceTest {

    private PeopleSoftSite peopleSoftSite;

    //fixture
    @Before
    public void setUp() throws Exception{
        Credential.getInstance().save("testuser","testpassword");
        peopleSoftSite = createMock(PeopleSoftSite.class);
        peopleSoftSite.login(Credential.getInstance().username(), Credential.getInstance().password());
        String cvsData = loadCSVData();
        expect(peopleSoftSite.fetchCvsOfPeopleMissingTimesheet(isA(String.class), isA(String.class))).andReturn(cvsData);
        peopleSoftSite.cleanUp();
        replay(peopleSoftSite);
    }

    @Test
    public void canPullUpdatesFromPeopleSoftSite() throws Exception {

        List<MissingPeople> names = PeopleMissingTimeSheet.getInstance().names();
        assertTrue(names.isEmpty());

        UpdateWallOfShameService service = new UpdateWallOfShameService();
        service.setPeopleSoftSite(peopleSoftSite);
        service.pullUpdates();
        names = PeopleMissingTimeSheet.getInstance().names();
        assertFalse(names.isEmpty());
        assertContainsName(names,new MissingPeople("13770","An,Hui", "Beijing"));

    }
    
    @Test
    public void shouldRecordLastUpdateTime(){

        Date timeBeforeUpdate = DateUtils.addSeconds(new Date(),-2);
        UpdateWallOfShameService service = new UpdateWallOfShameService();
        service.setPeopleSoftSite(peopleSoftSite);
        service.pullUpdates();
        Date lastUpdateTime = PeopleMissingTimeSheet.getInstance().lastUpdateTime();
        assertTrue(lastUpdateTime.after(timeBeforeUpdate));
    }
    
    private void assertContainsName(List<MissingPeople> names, MissingPeople exMissingPeople) {
        for(MissingPeople name : names)
            if(name.equals(exMissingPeople))
                return;
        fail("name does not exist..."+exMissingPeople);
    }

    @After
    public void after() {
        verify(peopleSoftSite);
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
