package grp18.software.acceptanceTests;

import grp18.software.app.EventOverlapException;
import grp18.software.app.IllegalDateException;
import grp18.software.domain.Activity;
import grp18.software.domain.Event;
import grp18.software.domain.Worker;
import io.cucumber.java.et.Eeldades;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class registerHoursWhiteBoxTest extends TestCase {
    private ErrorMessageHolder errorMessage;
    private Worker worker = new Worker("VINN");
    private Activity activity1 = new Activity("Activity 1","2022,06,01","2022,06,10");

    registerHoursWhiteBoxTest() throws IllegalDateException{
    }

    @Test
    @DisplayName("Case A")
    public void registerHoursWhiteBoxTestCaseA(){
        try {
            worker.registerHours("09,00", "17,30", "2022,06,02", activity1);
        } catch (IllegalDateException | EventOverlapException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(8.5, worker.getHoursWorkedOnActivity(activity1));
    }

    @Test
    @DisplayName("Case B")
    public void registerHoursWhiteBoxTestCaseB(){
        try {
            worker.registerHours("09,00", "17,30", "2022,06,02", activity1);
        } catch (IllegalDateException | EventOverlapException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals("",errorMessage.getErrorMessage());
    }
}