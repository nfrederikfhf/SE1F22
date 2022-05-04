package grp18.software.acceptanceTests;
import grp18.software.app.EventOverlapException;
import grp18.software.app.RegistrationApp;

import grp18.software.domain.Activity;
import grp18.software.domain.Event;
import grp18.software.domain.Worker;
import grp18.software.tools.StringToCalender;
import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*public class ValidateNoEventOverlapWhiteBoxTest extends TestCase{

    private Worker worker;
    private ErrorMessageHolder errorMessage;

    private void eventSetup(){
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
        StringToCalender dateData = new StringToCalender("01,01,2022", "10,00", "13,00");
        Event newEvent = new Event(dateData)
    }

}*/
