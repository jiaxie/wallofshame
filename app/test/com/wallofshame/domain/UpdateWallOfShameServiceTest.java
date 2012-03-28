package com.wallofshame.domain;

import com.wallofshame.repository.MissingTimeSheetRepository;
import com.wallofshame.service.UpdateWallOfShameService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Since: 3/16/12
 */
public class UpdateWallOfShameServiceTest {

    @Test
    public void canPullUpdatesFromPeopleSoftSite() throws Exception {

        List<Employee> names = PeopleMissingTimeSheet.getInstance().names();
        assertTrue(names.isEmpty());

        UpdateWallOfShameService service = new UpdateWallOfShameService(new MissingTimeSheetRepository(){
            public Employees lookUp(String lastSunDay, String officeId) {
                String cvsData = loadCSVData();
                return new EmployeesParser().parse(cvsData);
            }
        });
        service.pullUpdates();
        names = PeopleMissingTimeSheet.getInstance().names();
        assertFalse(names.isEmpty());
        assertContainsName(names,new Employee("13770","An,Hui", "Beijing"));

    }
    
    @Test
    public void shouldRecordLastUpdateTime(){

        Date timeBeforeUpdate = DateUtils.addSeconds(new Date(),-2);
        UpdateWallOfShameService service = new UpdateWallOfShameService(new MissingTimeSheetRepository(){
            public Employees lookUp(String lastSunDay, String officeId) {
                String cvsData = loadCSVData();
                return new EmployeesParser().parse(cvsData);
            }
        });
        service.pullUpdates();
        Date lastUpdateTime = PeopleMissingTimeSheet.getInstance().lastUpdateTime();
        assertTrue(lastUpdateTime.after(timeBeforeUpdate));
    }
    
    private void assertContainsName(List<Employee> names, Employee exEmployee) {
        for(Employee name : names)
            if(name.equals(exEmployee))
                return;
        fail("name does not exist..."+ exEmployee);
    }




    private String loadCSVData() throws RuntimeException {
        File sample = new File("./app/scripts/TW_TIME_COUNTRY_MISSING.csv");
        String result = null;
        try {
            InputStream is = FileUtils.openInputStream(sample);
            assertNotNull(is);
            result = IOUtils.toString(is);
            IOUtils.closeQuietly(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }




}
