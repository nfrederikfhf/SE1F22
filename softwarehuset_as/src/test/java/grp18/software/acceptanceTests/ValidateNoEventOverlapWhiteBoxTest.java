package grp18.software.acceptanceTests;
import grp18.software.app.EventOverlapException;
import grp18.software.app.RegistrationApp;

import grp18.software.domain.Activity;
import grp18.software.domain.Event;
import grp18.software.domain.Worker;
import grp18.software.tools.StringToCalender;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateNoEventOverlapWhiteBoxTest extends TestCase{

    private ErrorMessageHolder errorMessage;

    private void eventSetup(Worker worker){
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        try{
            worker.registerHours( "10,00", "13,00", "01,01,2022", activity1);
        }catch (EventOverlapException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Test
    @DisplayName("Case A")
    public void testValidateNoEventOverlapCaseA(){
        Worker worker = new Worker("NFF");
        StringToCalender dateData = new StringToCalender("01,01,2022", "8,00", "17,00");
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        Event newEvent = new Event(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, activity1, 1);
        Assertions.assertTrue(worker.validateNoEventOverlap(newEvent));
    }

    @Test
    @DisplayName("Case B")
    public void testValidateNoEventOverlapCaseB(){
        Worker worker = new Worker("NFF");
        eventSetup(worker);
        StringToCalender dateData = new StringToCalender("01,01,2022", "8,00", "9,00");
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        Event newEvent = new Event(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, activity1, 1);
        Assertions.assertTrue(worker.validateNoEventOverlap(newEvent));
    }

    @Test
    @DisplayName("Case C")
    public void testValidateNoEventOverlapCaseC(){
        Worker worker = new Worker("NFF");
        eventSetup(worker);
        StringToCalender dateData = new StringToCalender("01,01,2022", "11,00", "14,00");
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        Event newEvent = new Event(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, activity1, 1);
        Assertions.assertFalse(worker.validateNoEventOverlap(newEvent));
    }

    @Test
    @DisplayName("Case D")
    public void testValidateNoEventOverlapCaseD(){
        Worker worker = new Worker("NFF");
        eventSetup(worker);
        StringToCalender dateData = new StringToCalender("01,01,2022", "8,00", "12,00");
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        Event newEvent = new Event(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, activity1, 1);
        Assertions.assertFalse(worker.validateNoEventOverlap(newEvent));
    }

    @Test
    @DisplayName("Case E")
    public void testValidateNoEventOverlapCaseE(){
        Worker worker = new Worker("NFF");
        eventSetup(worker);
        StringToCalender dateData = new StringToCalender("01,01,2022", "11,00", "12,00");
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        Event newEvent = new Event(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, activity1, 1);
        Assertions.assertFalse(worker.validateNoEventOverlap(newEvent));
    }

    @Test
    @DisplayName("Case F")
    public void testValidateNoEventOverlapCaseF(){
        Worker worker = new Worker("NFF");
        eventSetup(worker);
        StringToCalender dateData = new StringToCalender("01,01,2022", "8,00", "14,00");
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        Event newEvent = new Event(dateData.startTimeCal, dateData.endTimeCal, dateData.dateCal, activity1, 1);
        Assertions.assertFalse(worker.validateNoEventOverlap(newEvent));
    }


}
