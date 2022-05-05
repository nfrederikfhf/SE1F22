package grp18.software.acceptanceTests;
import grp18.software.app.EventOverlapException;
import grp18.software.app.IllegalDateException;
import grp18.software.domain.Activity;
import grp18.software.domain.Event;
import grp18.software.domain.Worker;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//Niels
public class ValidateNoEventOverlapWhiteBoxTest extends TestCase{

    private ErrorMessageHolder errorMessage;

    private void eventSetup(Worker worker) throws IllegalDateException {
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        try{
            worker.registerHours( "10,00", "13,00", "01,01,2022", activity1);
        }catch (EventOverlapException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Test
    @DisplayName("Case A")
    public void testValidateNoEventOverlapCaseA() throws IllegalDateException {
        Worker worker = new Worker("NFF");
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        Event newEvent = null;
        try {
            newEvent = new Event("8,00", "17,00", "01,01,2022", activity1, 1);
        } catch (IllegalDateException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(worker.validateNoEventOverlap(newEvent));
    }

    @Test
    @DisplayName("Case B")
    public void testValidateNoEventOverlapCaseB() throws IllegalDateException {
        Worker worker = new Worker("NFF");
        eventSetup(worker);
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        Event newEvent = null;
        try {
            newEvent = new Event("8,00", "9,00", "01,01,2022", activity1, 1);
        } catch (IllegalDateException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(worker.validateNoEventOverlap(newEvent));
    }

    @Test
    @DisplayName("Case C")
    public void testValidateNoEventOverlapCaseC() throws IllegalDateException {
        Worker worker = new Worker("NFF");
        eventSetup(worker);
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        Event newEvent = null;
        try {
            newEvent = new Event("11,00", "14,00", "01,01,2022", activity1, 1);
        } catch (IllegalDateException e) {
            e.printStackTrace();
        }
        Assertions.assertFalse(worker.validateNoEventOverlap(newEvent));
    }

    @Test
    @DisplayName("Case D")
    public void testValidateNoEventOverlapCaseD() throws IllegalDateException {
        Worker worker = new Worker("NFF");
        eventSetup(worker);
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        Event newEvent = null;
        try {
            newEvent = new Event("8,00", "12,00", "01,01,2022", activity1, 1);
        } catch (IllegalDateException e) {
            e.printStackTrace();
        }
        Assertions.assertFalse(worker.validateNoEventOverlap(newEvent));
    }

    @Test
    @DisplayName("Case E")
    public void testValidateNoEventOverlapCaseE() throws IllegalDateException {
        Worker worker = new Worker("NFF");
        eventSetup(worker);
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        Event newEvent = null;
        try {
            newEvent = new Event("11,00", "12,00", "01,01,2022", activity1, 1);
        } catch (IllegalDateException e) {
            e.printStackTrace();
        }
        Assertions.assertFalse(worker.validateNoEventOverlap(newEvent));
    }

    @Test
    @DisplayName("Case F")
    public void testValidateNoEventOverlapCaseF() throws IllegalDateException {
        Worker worker = new Worker("NFF");
        eventSetup(worker);
        Activity activity1 = new Activity("Activity1", "01,01,2022", "01,02,2022");
        Event newEvent = null;
        try {
            newEvent = new Event("8,00", "14,00", "01,01,2022", activity1, 1);
        } catch (IllegalDateException e) {
            e.printStackTrace();
        }
        Assertions.assertFalse(worker.validateNoEventOverlap(newEvent));
    }
}
